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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.carlosbcruz.filterany.Text;

/**
 * Interface to configure the layout structure.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationStructureInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Theme options
	private static JRadioButton internalWindowOption = null;
	private static JRadioButton splittersOption = null;

	private static FilterAnyConfigurationStructure.INTERNAL_STRUCTURE lastSelection = FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS;

	/**
	 * Constructor.
	 */
	FilterAnyConfigurationStructureInterface() {

		setLayout(new GridLayout(3, 1));

		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.WINDOW_STRUCTURE_TITLE)));

		ButtonGroup radioGroup = new ButtonGroup();

		internalWindowOption = new JRadioButton(
				Text.get(Text.USE_INTERNAL_WINDOW_STRUCTURE));
		splittersOption = new JRadioButton(
				Text.get(Text.USE_SPLITTERS_STRUCTURE));

		internalWindowOption.setToolTipText(Text
				.get(Text.INTERNAL_WINDOW_OPTION_TOOLTIP));
		splittersOption.setToolTipText(Text.get(Text.SPLITTERS_OPTION_TOOLTIP));

		radioGroup.add(internalWindowOption);
		radioGroup.add(splittersOption);

		add(internalWindowOption);
		add(splittersOption);

		FilterAnyConfigurationStructure.INTERNAL_STRUCTURE currentStructure = FilterAnyConfigurationStructure
				.getCurrentStructure();

		if (currentStructure == FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.INTERNAL_WINDOW) {
			internalWindowOption.setSelected(true);
			splittersOption.setSelected(false);
		}
		if (currentStructure == FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS) {
			internalWindowOption.setSelected(false);
			splittersOption.setSelected(true);
		}

		internalWindowOption.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void actionPerformed(ActionEvent event) {

				if (internalWindowOption.isSelected()) {
					FilterAnyConfigurationDockingThemeInterface
							.setEnabledInterface(false);
					FilterAnyConfigurationSplitStructureInterface
							.setEnabledInterface(false);
				}
			}
		});

		splittersOption.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent event) {

				if (splittersOption.isSelected()) {
					FilterAnyConfigurationDockingThemeInterface
							.setEnabledInterface(false);
					FilterAnyConfigurationSplitStructureInterface
							.setEnabledInterface(true);
				}
			}
		});

		lastSelection = FilterAnyConfigurationStructure.getCurrentStructure();
	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		if (lastSelection == FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.INTERNAL_WINDOW) {
			internalWindowOption.setSelected(true);
			splittersOption.setSelected(false);
		}

		if (lastSelection == FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS) {
			internalWindowOption.setSelected(false);
			splittersOption.setSelected(true);
		}
	}

	/**
	 * Apply the theme.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applyStructure() {

		boolean restartApplication = false;

		FilterAnyConfigurationStructure.INTERNAL_STRUCTURE selection = FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS;

		if (internalWindowOption.isSelected()) {
			selection = FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.INTERNAL_WINDOW;
		}
		if (splittersOption.isSelected()) {
			selection = FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS;
		}

		// Indicate that a restart if necessary only if the selection is
		// different from the current theme configuration and different from
		// last selection.

		if (FilterAnyConfigurationStructure.getCurrentStructure() != selection
				&& lastSelection != selection) {
			restartApplication = true;
		}

		if (internalWindowOption.isSelected()) {
			FilterAnyConfigurationStructure
					.setCurrentStructure(FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.INTERNAL_WINDOW);
			lastSelection = FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.INTERNAL_WINDOW;
		}
		if (splittersOption.isSelected()) {
			FilterAnyConfigurationStructure
					.setCurrentStructure(FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS);
			lastSelection = FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS;
		}

		return restartApplication;
	}

}
