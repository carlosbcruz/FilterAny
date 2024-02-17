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
 * Interface to configure the save format.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationSaveFormatInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Save style
	private static JRadioButton dosFormatOption = null;
	private static JRadioButton unixFormatOption = null;

	private static FilterAnyConfigurationSaveFormat.SAVE_FORMATS lastSelection = FilterAnyConfigurationSaveFormat.SAVE_FORMATS.DOS;

	/**
	 * Constructor that creates the save format panel.
	 * 
	 * @return The save style panel.
	 */
	FilterAnyConfigurationSaveFormatInterface() {

		// Create the theme panel.
		setLayout(new GridLayout(2, 1));
		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.FILTER_CONFIGURATION_SAVE_FORMAT_TITLE)));

		ButtonGroup radioGroup = new ButtonGroup();
		dosFormatOption = new JRadioButton(
				Text.get(Text.FILTER_CONFIGURATION_SAVE_FORMAT_DOS));
		unixFormatOption = new JRadioButton(
				Text.get(Text.FILTER_CONFIGURATION_SAVE_FORMAT_UNIX));

		dosFormatOption.setToolTipText(Text
				.get(Text.FILTER_CONFIGURATION_SAVE_FORMAT_DOS_TOOLTIP));
		unixFormatOption.setToolTipText(Text
				.get(Text.FILTER_CONFIGURATION_SAVE_FORMAT_UNIX_TOOLTIP));

		lastSelection = FilterAnyConfigurationSaveFormat.getSaveFormat();

		// Indicate the current configuration.
		if (FilterAnyConfigurationSaveFormat.getSaveFormat() == FilterAnyConfigurationSaveFormat.SAVE_FORMATS.DOS) {

			dosFormatOption.setSelected(true);
			unixFormatOption.setSelected(false);

		}
		if (FilterAnyConfigurationSaveFormat.getSaveFormat() == FilterAnyConfigurationSaveFormat.SAVE_FORMATS.UNIX) {

			dosFormatOption.setSelected(false);
			unixFormatOption.setSelected(true);

		}

		radioGroup.add(dosFormatOption);
		radioGroup.add(unixFormatOption);

		add(dosFormatOption);
		add(unixFormatOption);

	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		if (lastSelection == FilterAnyConfigurationSaveFormat.SAVE_FORMATS.DOS) {

			dosFormatOption.setSelected(true);
			unixFormatOption.setSelected(false);

		}
		if (lastSelection == FilterAnyConfigurationSaveFormat.SAVE_FORMATS.UNIX) {

			dosFormatOption.setSelected(false);
			unixFormatOption.setSelected(true);

		}
	}

	/**
	 * Apply the save format.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applySaveStyle() {

		boolean restartApplication = false;

		// Store the current selection.
		if (dosFormatOption.isSelected()) {
			lastSelection = FilterAnyConfigurationSaveFormat.SAVE_FORMATS.DOS;
			FilterAnyConfigurationSaveFormat
					.setSaveFormat(FilterAnyConfigurationSaveFormat.SAVE_FORMATS.DOS);
		}
		if (unixFormatOption.isSelected()) {
			lastSelection = FilterAnyConfigurationSaveFormat.SAVE_FORMATS.UNIX;
			FilterAnyConfigurationSaveFormat
					.setSaveFormat(FilterAnyConfigurationSaveFormat.SAVE_FORMATS.UNIX);
		}

		return restartApplication;
	}

}
