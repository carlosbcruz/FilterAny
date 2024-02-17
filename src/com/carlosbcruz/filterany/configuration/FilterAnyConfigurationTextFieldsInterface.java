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
 * Interface to configure the text fields behavior.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationTextFieldsInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Fields behavior choices.
	private static JCheckBox automaticCleanupBehaviorOption = null;
	private static JCheckBox clearTextButtonsOption = null;

	// Previous configurations.
	private static boolean previousAutocleanBehavior = false;
	private static boolean previousClearText = true;

	/**
	 * Constructor that creates the text fields behavior panel.
	 * 
	 * @return The text fields behavior panel.
	 */
	FilterAnyConfigurationTextFieldsInterface() {

		// Create the text fields behavior panel.
		setLayout(new GridLayout(2, 1));
		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.TEXT_FIELDS_BEHAVIOR_BORDER_TITLE)));

		automaticCleanupBehaviorOption = new JCheckBox(
				Text.get(Text.TEXT_FIELDS_BEHAVIOR_AUTOMATIC_CLEAN_UP));
		clearTextButtonsOption = new JCheckBox(
				Text.get(Text.TEXT_FIELDS_BEHAVIOR_CLEAR_CONTENT_BUTTONS));

		automaticCleanupBehaviorOption.setToolTipText(Text
				.get(Text.AUTOMATIC_CLEANUP_BEHAVIOR_OPTION_TOOLTIP));
		clearTextButtonsOption.setToolTipText(Text
				.get(Text.CLEAR_TEXT_BUTTONS_OPTION_TOOLTIP));

		// Store the previous configurations.
		previousAutocleanBehavior = FilterAnyConfigurationTextFields
				.isAutoCleanupBehavior();
		previousClearText = FilterAnyConfigurationTextFields
				.isClearFieldsButton();

		// Indicate the current configuration.
		automaticCleanupBehaviorOption.setSelected(previousAutocleanBehavior);
		clearTextButtonsOption.setSelected(previousClearText);

		add(automaticCleanupBehaviorOption);
		add(clearTextButtonsOption);
	}

	/**
	 * Restore the previous selection.
	 */
	static void restorePreviousSelection() {

		automaticCleanupBehaviorOption.setSelected(previousAutocleanBehavior);
		clearTextButtonsOption.setSelected(previousClearText);
	}

	/**
	 * Apply the text fields behavior configuration..
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applyFieldsBehaviorStyle() {

		boolean restartApplication = false;

		// Store the current selection.
		boolean autoCleanConfiguration = automaticCleanupBehaviorOption
				.isSelected();
		FilterAnyConfigurationTextFields
				.setAutoCleanupBehavior(autoCleanConfiguration);
		if (autoCleanConfiguration != previousAutocleanBehavior) {
			restartApplication = true;
		}

		boolean clearFieldsButtonConfiguration = clearTextButtonsOption
				.isSelected();
		FilterAnyConfigurationTextFields
				.setClearFieldsButton(clearFieldsButtonConfiguration);
		if (clearFieldsButtonConfiguration != previousClearText) {
			restartApplication = true;
		}

		return restartApplication;
	}

}