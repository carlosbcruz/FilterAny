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
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION;

/**
 * Interface to configure the docking theme.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationDockingThemeInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Theme options
	private static JRadioButton smoothThemeOption = null;
	private static JRadioButton basicThemeOption = null;
	private static JRadioButton flatThemeOption = null;

	private static JCheckBox noStackOption = null;

	private static FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION lastSelection = FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.BASIC;

	private static boolean lastNoStackOption = FilterAnyConfigurationDockingTheme
			.useNoStackTheme();

	/**
	 * Constructor.
	 */
	FilterAnyConfigurationDockingThemeInterface() {

		setLayout(new GridLayout(6, 1));

		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.DOCKING_THEME_TITLE)));

		ButtonGroup radioGroup = new ButtonGroup();
		smoothThemeOption = new JRadioButton(
				Text.get(Text.DOCKING_THEME_SMOOTH));
		basicThemeOption = new JRadioButton(Text.get(Text.DOCKING_THEME_BASIC));
		flatThemeOption = new JRadioButton(Text.get(Text.DOCKING_THEME_FLAT));

		noStackOption = new JCheckBox(Text.get(Text.DOCKING_THEME_NO_STACK));

		smoothThemeOption.setToolTipText(Text
				.get(Text.DOCKING_THEME_SMOOTH_TOOLTIP));
		basicThemeOption.setToolTipText(Text
				.get(Text.DOCKING_THEME_BASIC_TOOLTIP));
		flatThemeOption.setToolTipText(Text
				.get(Text.DOCKING_THEME_FLAT_TOOLTIP));
		noStackOption.setToolTipText(Text
				.get(Text.DOCKING_THEME_NO_STACK_TOOLTIP));

		FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION currentThemeConfiguration = FilterAnyConfigurationDockingTheme
				.getCurrentThemeConfiguration();

		// Indicate the current theme.
		if (currentThemeConfiguration == THEME_CONFIGURATION.SMOOTH) {
			smoothThemeOption.setSelected(true);
			basicThemeOption.setSelected(false);
			flatThemeOption.setSelected(false);
		}
		if (currentThemeConfiguration == THEME_CONFIGURATION.BASIC) {
			smoothThemeOption.setSelected(false);
			basicThemeOption.setSelected(true);
			flatThemeOption.setSelected(false);
		}
		if (currentThemeConfiguration == THEME_CONFIGURATION.FLAT) {
			smoothThemeOption.setSelected(false);
			basicThemeOption.setSelected(false);
			flatThemeOption.setSelected(true);
		}

		radioGroup.add(smoothThemeOption);
		radioGroup.add(basicThemeOption);
		radioGroup.add(flatThemeOption);
		add(smoothThemeOption);
		add(basicThemeOption);
		add(flatThemeOption);
		add(noStackOption);

		noStackOption.setSelected(FilterAnyConfigurationDockingTheme
				.useNoStackTheme());

		lastSelection = FilterAnyConfigurationDockingTheme
				.getCurrentThemeConfiguration();
	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		if (lastSelection == THEME_CONFIGURATION.SMOOTH) {
			smoothThemeOption.setSelected(true);
			basicThemeOption.setSelected(false);
			flatThemeOption.setSelected(false);
		}
		if (lastSelection == THEME_CONFIGURATION.BASIC) {
			smoothThemeOption.setSelected(false);
			basicThemeOption.setSelected(true);
			flatThemeOption.setSelected(false);
		}
		if (lastSelection == THEME_CONFIGURATION.FLAT) {
			smoothThemeOption.setSelected(false);
			basicThemeOption.setSelected(false);
			flatThemeOption.setSelected(true);
		}
		noStackOption.setSelected(lastNoStackOption);
	}

	/**
	 * Apply the theme.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applyTheme() {

		boolean restartApplication = false;

		FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION selection = FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.BASIC;

		if (smoothThemeOption.isSelected()) {
			selection = THEME_CONFIGURATION.SMOOTH;
		}
		if (basicThemeOption.isSelected()) {
			selection = THEME_CONFIGURATION.BASIC;
		}
		if (flatThemeOption.isSelected()) {
			selection = THEME_CONFIGURATION.FLAT;
		}

		boolean useNoStack = noStackOption.isSelected();

		// Indicate that a restart if necessary only if the selection is
		// different from the current theme configuration and different from
		// last selection.
		if ((FilterAnyConfigurationDockingTheme.getCurrentThemeConfiguration() != selection || FilterAnyConfigurationDockingTheme
				.useNoStackTheme() != useNoStack)
				&& (useNoStack != lastNoStackOption || selection != lastSelection)) {
			restartApplication = true;
		}

		if (smoothThemeOption.isSelected()) {
			FilterAnyConfigurationDockingTheme
					.setDockingTheme(FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.SMOOTH);
			lastSelection = FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.SMOOTH;
		}
		if (basicThemeOption.isSelected()) {
			FilterAnyConfigurationDockingTheme
					.setDockingTheme(FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.BASIC);
			lastSelection = FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.BASIC;
		}
		if (flatThemeOption.isSelected()) {
			FilterAnyConfigurationDockingTheme
					.setDockingTheme(FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.FLAT);
			lastSelection = FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.FLAT;
		}

		FilterAnyConfigurationDockingTheme.setNoStackTheme(noStackOption
				.isSelected());
		lastNoStackOption = noStackOption.isSelected();

		return restartApplication;
	}

	/**
	 * Enable or disable the interface.
	 * 
	 * @param enabled
	 *            True to enable the interface.
	 */
	public static void setEnabledInterface(boolean enabled) {

		smoothThemeOption.setEnabled(enabled);
		basicThemeOption.setEnabled(enabled);
		flatThemeOption.setEnabled(enabled);
		noStackOption.setEnabled(enabled);
	}

}
