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
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.carlosbcruz.filterany.Text;

/**
 * Interface to configure the theme.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationThemeInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Theme options
	private static JRadioButton dependOnLookAndFeelOption = null;
	private static JRadioButton forceThemeOption = null;
	private static JRadioButton doNotUseThemeOption = null;

	private static FilterAnyConfigurationTheme.THEME_CONFIGURATION lastSelection = FilterAnyConfigurationTheme.THEME_CONFIGURATION.LOOK_AND_FEEL;

	/**
	 * Constructor.
	 */
	FilterAnyConfigurationThemeInterface() {

		setLayout(new GridLayout(3, 1));

		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.CONFIGURATION_THEME)));

		ButtonGroup radioGroup = new ButtonGroup();
		dependOnLookAndFeelOption = new JRadioButton(
				Text.get(Text.CONFIGURATION_THEME_ACCORDING_TO_LOOK_AND_FEEL));
		forceThemeOption = new JRadioButton(
				Text.get(Text.CONFIGURATION_FORCE_THEME));
		doNotUseThemeOption = new JRadioButton(
				Text.get(Text.CONFIGURATION_DO_NOT_USE_THEME));

		dependOnLookAndFeelOption.setToolTipText(Text
				.get(Text.DEPEND_ON_LOOK_AND_FEEL_OPTION_TOOLTIP));
		forceThemeOption.setToolTipText(Text
				.get(Text.FORCE_THEME_OPTION_TOOLTIP));
		doNotUseThemeOption.setToolTipText(Text
				.get(Text.DO_NOT_USE_THEME_OPTION_TOOLTIP));

		FilterAnyConfigurationTheme.THEME_CONFIGURATION currentThemeConfiguration = FilterAnyConfigurationTheme
				.getCurrentThemeConfiguration();

		// Indicate the current theme.
		if (currentThemeConfiguration == FilterAnyConfigurationTheme.THEME_CONFIGURATION.LOOK_AND_FEEL) {
			dependOnLookAndFeelOption.setSelected(true);
			forceThemeOption.setSelected(false);
			doNotUseThemeOption.setSelected(false);
		}
		if (currentThemeConfiguration == FilterAnyConfigurationTheme.THEME_CONFIGURATION.FORCED) {
			dependOnLookAndFeelOption.setSelected(false);
			forceThemeOption.setSelected(true);
			doNotUseThemeOption.setSelected(false);
		}
		if (currentThemeConfiguration == FilterAnyConfigurationTheme.THEME_CONFIGURATION.DO_NOT_USE) {
			dependOnLookAndFeelOption.setSelected(false);
			forceThemeOption.setSelected(false);
			doNotUseThemeOption.setSelected(true);
		}

		radioGroup.add(dependOnLookAndFeelOption);
		radioGroup.add(forceThemeOption);
		radioGroup.add(doNotUseThemeOption);
		add(dependOnLookAndFeelOption);
		add(forceThemeOption);
		add(doNotUseThemeOption);

		lastSelection = FilterAnyConfigurationTheme
				.getCurrentThemeConfiguration();
	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		if (lastSelection == FilterAnyConfigurationTheme.THEME_CONFIGURATION.LOOK_AND_FEEL) {
			dependOnLookAndFeelOption.setSelected(true);
			forceThemeOption.setSelected(false);
			doNotUseThemeOption.setSelected(false);
		}
		if (lastSelection == FilterAnyConfigurationTheme.THEME_CONFIGURATION.FORCED) {
			dependOnLookAndFeelOption.setSelected(false);
			forceThemeOption.setSelected(true);
			doNotUseThemeOption.setSelected(false);
		}
		if (lastSelection == FilterAnyConfigurationTheme.THEME_CONFIGURATION.DO_NOT_USE) {
			dependOnLookAndFeelOption.setSelected(false);
			forceThemeOption.setSelected(false);
			doNotUseThemeOption.setSelected(true);
		}
	}

	/**
	 * Apply the theme.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applyTheme() {

		boolean restartApplication = false;

		FilterAnyConfigurationTheme.THEME_CONFIGURATION selection = FilterAnyConfigurationTheme.THEME_CONFIGURATION.LOOK_AND_FEEL;

		if (dependOnLookAndFeelOption.isSelected()) {
			selection = FilterAnyConfigurationTheme.THEME_CONFIGURATION.LOOK_AND_FEEL;
		}
		if (forceThemeOption.isSelected()) {
			selection = FilterAnyConfigurationTheme.THEME_CONFIGURATION.FORCED;
		}
		if (doNotUseThemeOption.isSelected()) {
			selection = FilterAnyConfigurationTheme.THEME_CONFIGURATION.DO_NOT_USE;
		}

		// Indicate that a restart if necessary only if the selection is
		// different from the current theme configuration and different from
		// last selection.
		if (FilterAnyConfigurationTheme.getCurrentThemeConfiguration() != selection
				&& lastSelection != selection) {
			restartApplication = true;
		}

		if (dependOnLookAndFeelOption.isSelected()) {
			FilterAnyConfigurationTheme
					.setTheme(FilterAnyConfigurationTheme.THEME_CONFIGURATION.LOOK_AND_FEEL);
			lastSelection = FilterAnyConfigurationTheme.THEME_CONFIGURATION.LOOK_AND_FEEL;
		}
		if (forceThemeOption.isSelected()) {
			FilterAnyConfigurationTheme
					.setTheme(FilterAnyConfigurationTheme.THEME_CONFIGURATION.FORCED);
			lastSelection = FilterAnyConfigurationTheme.THEME_CONFIGURATION.FORCED;
		}
		if (doNotUseThemeOption.isSelected()) {
			FilterAnyConfigurationTheme
					.setTheme(FilterAnyConfigurationTheme.THEME_CONFIGURATION.DO_NOT_USE);
			lastSelection = FilterAnyConfigurationTheme.THEME_CONFIGURATION.DO_NOT_USE;
		}

		return restartApplication;
	}

}
