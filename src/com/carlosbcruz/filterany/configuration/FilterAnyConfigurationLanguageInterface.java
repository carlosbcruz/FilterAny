/**
 * The program(s) herein may be used  freely
 * for personal use only. It may not be sold, 
 * rented, leased, sublicensed  or  modified 
 * without permission of  the  author.  
 * 
 *           www.carlosbcruz.com
 *           
 * This program is provided "AS IS"  without 
 * warranty of any kind. In no event I  will
 * be liable  for  any  direct  or  indirect 
 * damage.
 */

package com.carlosbcruz.filterany.configuration;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.util.ExceptionSupport;

/**
 * Interface to configure the language.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationLanguageInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Language selection.
	static private JComboBox<String> languageList = null;

	// Indicate which is the last selection.
	static private int lastSelection = 0;

	/**
	 * Constructor.
	 */
	FilterAnyConfigurationLanguageInterface() {

		// Create a language selection.
		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.CONFIGURATION_LANGUAGE_TITLE)));

		JPanel internalLanguagePanel = new JPanel();
		internalLanguagePanel.setLayout(new GridLayout(1, 2));

		internalLanguagePanel.add(new JLabel(Text
				.get(Text.CONFIGURATION_LANGUAGE_PARAMETER)));

		// Options to select the language.
		String languageSelectionList[] = null;

		languageSelectionList = new String[] {
				Text.get(Text.CONFIGURATION_LANGUAGE_OPTION_OPERATIONA_SYSTEM),
				Text.get(Text.CONFIGURATION_LANGUAGE_OPTION_ENGLISH),
				Text.get(Text.CONFIGURATION_LANGUAGE_OPTION_PORTUGUES) };

		languageList = new JComboBox<>(languageSelectionList);

		languageList.setToolTipText(Text.get(Text.LANGUAGELIST_TOOLTIP));

		if (FilterAnyConfigurationLanguage.getCurrentLocaleName() == FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.DEFAULT) {
			lastSelection = 0;
		}
		if (FilterAnyConfigurationLanguage.getCurrentLocaleName() == FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.EN_US) {
			lastSelection = 1;
		}
		if (FilterAnyConfigurationLanguage.getCurrentLocaleName() == FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.PT_BR) {
			lastSelection = 2;
		}
		languageList.setSelectedIndex(lastSelection);

		internalLanguagePanel.add(languageList);
		add(internalLanguagePanel);
	}

	/**
	 * Convert a index to available language.
	 * 
	 * @param index
	 *            The index.
	 * @return The available language.
	 */
	private static FilterAnyConfigurationLanguage.AVAILABLE_LOCALES indexToLocale(
			int index) {

		switch (index) {
		case 0: {
			return FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.DEFAULT;
		}
		case 1: {
			return FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.EN_US;
		}
		case 2: {
			return FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.PT_BR;
		}
		default:
			ExceptionSupport.handleException(Text.get(Text.INTERNAL_ERROR_16));
			break;
		}

		return FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.DEFAULT;
	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		languageList.setSelectedIndex(lastSelection);
	}

	/**
	 * Apply the language.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applyLanguage() {

		boolean restartApplicationFlag = false;

		// Indicate that a restart if necessary only if the selection is
		// different
		// from the current locale and different from last selection.
		if (FilterAnyConfigurationLanguage.getCurrentLocaleName() != indexToLocale(languageList
				.getSelectedIndex())
				&& lastSelection != languageList.getSelectedIndex()) {

			restartApplicationFlag = true;
		}

		int selection = languageList.getSelectedIndex();
		// Apply the new selection.
		switch (selection) {
		case 0: {
			FilterAnyConfigurationLanguage
					.setLanguage(FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.DEFAULT);
			lastSelection = 0;
			break;
		}
		case 1: {
			FilterAnyConfigurationLanguage
					.setLanguage(FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.EN_US);
			lastSelection = 1;
			break;
		}
		case 2: {
			FilterAnyConfigurationLanguage
					.setLanguage(FilterAnyConfigurationLanguage.AVAILABLE_LOCALES.PT_BR);
			lastSelection = 2;
			break;
		}
		default:
			ExceptionSupport.handleException(Text.get(Text.INTERNAL_ERROR_16));
			break;
		}

		return restartApplicationFlag;
	}
}
