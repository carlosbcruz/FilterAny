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

import com.carlosbcruz.util.SimpleProperties;

/**
 * Configuration on the text fields behavior.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationTextFields {

	// Configuration of the text fields.
	private static final String AUTO_CLEANUP_BEHAVIOR_KEY = "auto.cleanup"; //$NON-NLS-1$
	private static final String CLEAR_FIELDS_BUTTON_KEY = "clear.fields.button"; //$NON-NLS-1$

	// Possible values.
	private static final String ACTIVATED = "Y"; //$NON-NLS-1$
	private static final String DESACTIVATED = "N"; //$NON-NLS-1$

	private static boolean autoCleanupBehavior = false;
	private static boolean clearFieldsButton = true;

	/**
	 * Initialize the tree line.
	 */
	static void initialize() {

		// Get the configuration.
		String autoCleanupBehaviorConfiguration = SimpleProperties
				.getStringPropertyWithDefault(AUTO_CLEANUP_BEHAVIOR_KEY,
						DESACTIVATED);

		String clearFieldsButtonConfiguration = SimpleProperties
				.getStringPropertyWithDefault(CLEAR_FIELDS_BUTTON_KEY,
						ACTIVATED);

		autoCleanupBehavior = ACTIVATED
				.equals(autoCleanupBehaviorConfiguration);

		clearFieldsButton = ACTIVATED.equals(clearFieldsButtonConfiguration);
	}

	/**
	 * Set the auto clean up configuration.
	 * 
	 * @param selection
	 *            The configuration.
	 */
	static void setAutoCleanupBehavior(boolean selection) {

		SimpleProperties.setStringProperty(AUTO_CLEANUP_BEHAVIOR_KEY,
				selection ? ACTIVATED : DESACTIVATED);
		autoCleanupBehavior = selection;

	}

	/**
	 * Set the clear fields button configuration.
	 * 
	 * @param selection
	 *            The configuration.
	 */
	static void setClearFieldsButton(boolean selection) {

		SimpleProperties.setStringProperty(CLEAR_FIELDS_BUTTON_KEY,
				selection ? ACTIVATED : DESACTIVATED);
		clearFieldsButton = selection;

	}

	/**
	 * Inform the auto clean up configuration.
	 * 
	 * @return the autoCleanupBehavior
	 */
	static boolean isAutoCleanupBehavior() {
		return autoCleanupBehavior;
	}

	/**
	 * Inform the clear fields button configuration.
	 * 
	 * @return the clearFieldsButton
	 */
	static boolean isClearFieldsButton() {
		return clearFieldsButton;
	}

}
