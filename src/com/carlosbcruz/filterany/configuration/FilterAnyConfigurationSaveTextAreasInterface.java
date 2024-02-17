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
 * Interface to configure the save style.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationSaveTextAreasInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Save style
	private static JRadioButton ignoreModeOption = null;
	private static JRadioButton warnModeOption = null;
	private static JRadioButton persistModeOption = null;

	private static FilterAnyConfigurationSaveTextAreas.SAVE_STYLES lastSelection = FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.IGNORE;

	/**
	 * Constructor that creates the save style panel.
	 * 
	 * @return The save style panel.
	 */
	FilterAnyConfigurationSaveTextAreasInterface() {

		// Create the theme panel.
		setLayout(new GridLayout(4, 1));
		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.FILTER_CONFIGURATION_SAVE_STYLE_TITLE)));

		ButtonGroup radioGroup = new ButtonGroup();
		ignoreModeOption = new JRadioButton(
				Text.get(Text.FILTER_CONFIGURATION_SAVE_STYLE_IGNORE));
		warnModeOption = new JRadioButton(
				Text.get(Text.FILTER_CONFIGURATION_SAVE_STYLE_WARN));
		persistModeOption = new JRadioButton(
				Text.get(Text.FILTER_CONFIGURATION_SAVE_STYLE_PERSIST));

		ignoreModeOption.setToolTipText(Text
				.get(Text.IGNORE_MODE_OPTION_TOOLTIP));
		warnModeOption.setToolTipText(Text.get(Text.WARN_MODE_OPTION_TOOLTIP));
		persistModeOption.setToolTipText(Text
				.get(Text.PERSIST_MODE_OPTION_TOOLTIP));

		lastSelection = FilterAnyConfigurationSaveTextAreas.getSaveStyleType();

		// Indicate the current configuration.
		if (FilterAnyConfigurationSaveTextAreas.getSaveStyleType() == FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.IGNORE) {

			ignoreModeOption.setSelected(true);
			warnModeOption.setSelected(false);
			persistModeOption.setSelected(false);

		}
		if (FilterAnyConfigurationSaveTextAreas.getSaveStyleType() == FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.WARN) {

			ignoreModeOption.setSelected(false);
			warnModeOption.setSelected(true);
			persistModeOption.setSelected(false);

		}
		if (FilterAnyConfigurationSaveTextAreas.getSaveStyleType() == FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.PERSIST) {

			ignoreModeOption.setSelected(false);
			warnModeOption.setSelected(false);
			persistModeOption.setSelected(true);

		}

		radioGroup.add(ignoreModeOption);
		radioGroup.add(warnModeOption);
		radioGroup.add(persistModeOption);

		add(ignoreModeOption);
		add(warnModeOption);
		add(persistModeOption);

	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		if (lastSelection == FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.IGNORE) {

			ignoreModeOption.setSelected(true);
			warnModeOption.setSelected(false);
			persistModeOption.setSelected(false);

		}
		if (lastSelection == FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.WARN) {

			ignoreModeOption.setSelected(false);
			warnModeOption.setSelected(true);
			persistModeOption.setSelected(false);

		}
		if (lastSelection == FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.PERSIST) {

			ignoreModeOption.setSelected(false);
			warnModeOption.setSelected(false);
			persistModeOption.setSelected(true);

		}
	}

	/**
	 * Apply the save configuration.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applySaveStyle() {

		boolean restartApplication = false;

		// Store the current selection.
		if (ignoreModeOption.isSelected()) {
			lastSelection = FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.IGNORE;
			FilterAnyConfigurationSaveTextAreas
					.setTreeLineStyle(FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.IGNORE);
		}
		if (warnModeOption.isSelected()) {
			lastSelection = FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.WARN;
			FilterAnyConfigurationSaveTextAreas
					.setTreeLineStyle(FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.WARN);
		}
		if (persistModeOption.isSelected()) {
			lastSelection = FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.PERSIST;
			FilterAnyConfigurationSaveTextAreas
					.setTreeLineStyle(FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.PERSIST);
		}

		return restartApplication;
	}

}
