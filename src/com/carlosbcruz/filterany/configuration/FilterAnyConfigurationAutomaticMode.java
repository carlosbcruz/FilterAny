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
 * Configure the automatic help setting.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationAutomaticMode {

	// It is not necessary to execute the filters. The selected on
	// runs continuously.
	private static String AUTOMATIC_MODE_KEY = "automatic.mode"; //$NON-NLS-1$
	private static boolean automaticMode = false;

	/**
	 * Initialize the tree line.
	 */
	static void initialize() {

		// Get the automatic mode status.
		String automaticModeParameter = SimpleProperties
				.getStringPropertyWithDefault(AUTOMATIC_MODE_KEY, "N"); //$NON-NLS-1$
		if ("Y".equals(automaticModeParameter)) { //$NON-NLS-1$
			automaticMode = true;
		}

	}

	/**
	 * Indicate if the automatic mode is turned on.
	 * 
	 * @return the automaticMode true if the automatic mode is turned on.
	 */
	public static boolean isAutomaticMode() {

		return automaticMode;
	}

	/**
	 * Set the automatic mode on and off.
	 * 
	 * @param automaticMode
	 *            the automaticMode to set
	 */
	public static void setAutomaticMode(boolean automaticMode) {

		FilterAnyConfigurationAutomaticMode.automaticMode = automaticMode;
		SimpleProperties.setStringProperty(AUTOMATIC_MODE_KEY,
				automaticMode ? "Y" : "N"); //$NON-NLS-1$ //$NON-NLS-2$

	}
}
