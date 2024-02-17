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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.carlosbcruz.filterany.Text;

/**
 * Interface to configure the tree line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationTreeLineInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Tree line style
	private static JRadioButton angledOption = null;
	private static JRadioButton horizontalOption = null;
	private static JRadioButton noneOption = null;

	private static FilterAnyConfigurationTreeLine.TREE_LINE_TYPES lastSelection = FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.ANGLED;

	/**
	 * Constructor that creates the tree line style panel
	 * 
	 * @return The the tree line style panel
	 */
	FilterAnyConfigurationTreeLineInterface() {

		// Create the theme panel.
		setLayout(new GridLayout(4, 1));
		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.CONFIGURATION_TREE_LINE_STYLE)));

		ButtonGroup radioGroup = new ButtonGroup();
		angledOption = new JRadioButton(
				Text.get(Text.CONFIGURATION_TREE_LINE_STYLE_ANGLED));
		horizontalOption = new JRadioButton(
				Text.get(Text.CONFIGURATION_TREE_LINE_STYLE_HORIZONTAL));
		noneOption = new JRadioButton(
				Text.get(Text.CONFIGURATION_TREE_LINE_STYLE_NONE));

		angledOption.setToolTipText(Text.get(Text.ANGLED_OPTION_TOOLTIP));
		horizontalOption.setToolTipText(Text
				.get(Text.HORIZONTAL_OPTION_TOOLTIP));
		noneOption.setToolTipText(Text.get(Text.NONE_OPTION_TOOLTIP));

		// Indicate the current theme.
		if (FilterAnyConfigurationTreeLine.getTreeLineStyleType() == FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.ANGLED) {

			angledOption.setSelected(true);
			horizontalOption.setSelected(false);
			noneOption.setSelected(false);

			lastSelection = FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.ANGLED;
		}
		if (FilterAnyConfigurationTreeLine.getTreeLineStyleType() == FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.HORIZONTAL) {

			angledOption.setSelected(false);
			horizontalOption.setSelected(true);
			noneOption.setSelected(false);

			lastSelection = FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.HORIZONTAL;
		}
		if (FilterAnyConfigurationTreeLine.getTreeLineStyleType() == FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.NONE) {

			angledOption.setSelected(false);
			horizontalOption.setSelected(false);
			noneOption.setSelected(true);

			lastSelection = FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.NONE;
		}

		radioGroup.add(angledOption);
		radioGroup.add(horizontalOption);
		radioGroup.add(noneOption);

		add(angledOption);
		add(horizontalOption);
		add(noneOption);

		add(new JLabel(Text.get(Text.CONFIGURATION_TREE_LINE_STYLE_INSTRUCTION)));

	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		if (lastSelection == FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.ANGLED) {

			angledOption.setSelected(true);
			horizontalOption.setSelected(false);
			noneOption.setSelected(false);
		}
		if (lastSelection == FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.HORIZONTAL) {

			angledOption.setSelected(false);
			horizontalOption.setSelected(true);
			noneOption.setSelected(false);
		}
		if (lastSelection == FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.NONE) {

			angledOption.setSelected(false);
			horizontalOption.setSelected(false);
			noneOption.setSelected(true);
		}
	}

	/**
	 * Apply the tree line.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applyTreeLine() {

		boolean restartApplication = false;

		FilterAnyConfigurationTreeLine.TREE_LINE_TYPES selection = FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.ANGLED;

		if (angledOption.isSelected()) {
			selection = FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.ANGLED;
		}
		if (horizontalOption.isSelected()) {
			selection = FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.HORIZONTAL;
		}
		if (noneOption.isSelected()) {
			selection = FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.NONE;
		}

		// Indicate that a restart if necessary only if the selection is
		// different from the current theme configuration and different from
		// last selection.
		if (FilterAnyConfigurationTreeLine.getTreeLineStyleType() != selection
				&& lastSelection != selection) {
			restartApplication = true;
		}

		// Verify the tree line style.
		if (angledOption.isSelected()) {
			FilterAnyConfigurationTreeLine
					.setTreeLineStyle(FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.ANGLED);
		}
		if (horizontalOption.isSelected()) {
			FilterAnyConfigurationTreeLine
					.setTreeLineStyle(FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.HORIZONTAL);
		}
		if (noneOption.isSelected()) {
			FilterAnyConfigurationTreeLine
					.setTreeLineStyle(FilterAnyConfigurationTreeLine.TREE_LINE_TYPES.NONE);
		}

		return restartApplication;
	}

}
