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
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.carlosbcruz.filterany.Text;

/**
 * Interface to configure the split structure.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationSplitStructureInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Split structures
	private static JCheckBox controlsOnTheRightOption = null;
	private static JCheckBox verticalSplitOption = null;

	private static boolean lastControlsOnTheRight = FilterAnyConfigurationSplitStructure
			.isControlsAreOnTheRight();
	private static boolean lastVerticalSplit = FilterAnyConfigurationSplitStructure
			.isUseVerticalTextSplit();

	/**
	 * Constructor.
	 */
	FilterAnyConfigurationSplitStructureInterface() {

		setLayout(new GridLayout(2, 1));

		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.SPLIT_STRUCTURE_TITLE)));

		controlsOnTheRightOption = new JCheckBox(
				Text.get(Text.SPLIT_STRUCTURE_CONTROLS_ON_THE_RIGHT));
		verticalSplitOption = new JCheckBox(
				Text.get(Text.SPLIT_STRUCTURE_VERTICAL_SPLIT));

		controlsOnTheRightOption.setToolTipText(Text
				.get(Text.CONTROLS_ON_THE_RIGHT_OPTION_TOOLTIP));
		verticalSplitOption.setToolTipText(Text
				.get(Text.VERTICAL_SPLIT_OPTION_TOOLTIP));

		add(controlsOnTheRightOption);
		add(verticalSplitOption);

		controlsOnTheRightOption
				.setSelected(FilterAnyConfigurationSplitStructure
						.isControlsAreOnTheRight());
		verticalSplitOption.setSelected(FilterAnyConfigurationSplitStructure
				.isUseVerticalTextSplit());

		controlsOnTheRightOption.setEnabled(FilterAnyConfiguration
				.isUseSplitters());
		verticalSplitOption.setEnabled(FilterAnyConfiguration.isUseSplitters());

	}

	/**
	 * Apply the theme.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applyTheme() {

		boolean restartApplication = false;

		boolean controlsOnTheRight = controlsOnTheRightOption.isSelected();
		boolean verticalSplit = verticalSplitOption.isSelected();

		// Indicate that a restart if necessary only if the selection is
		// different from the current theme configuration and different from
		// last selection.
		if ((FilterAnyConfigurationSplitStructure.isControlsAreOnTheRight() != controlsOnTheRight || FilterAnyConfigurationSplitStructure
				.isUseVerticalTextSplit() != verticalSplit)
				&& (controlsOnTheRight != lastControlsOnTheRight || verticalSplit != lastVerticalSplit)) {
			restartApplication = true;
		}

		FilterAnyConfigurationSplitStructure
				.setControlsAreOnTheRight(controlsOnTheRightOption.isSelected());
		FilterAnyConfigurationSplitStructure
				.setUseVerticalTextSplit(verticalSplitOption.isSelected());

		return restartApplication;
	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		controlsOnTheRightOption.setSelected(lastControlsOnTheRight);
		verticalSplitOption.setSelected(lastVerticalSplit);
	}

	/**
	 * Enable or disable the interface.
	 * 
	 * @param enabled
	 *            True to enable the interface.
	 */
	public static void setEnabledInterface(boolean enabled) {

		controlsOnTheRightOption.setEnabled(enabled);
		verticalSplitOption.setEnabled(enabled);
	}
}
