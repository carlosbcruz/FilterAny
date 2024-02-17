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
class FilterAnyConfigurationAutomaticHelp {

	// Indicate that the help automatic adjust on filter change.
	private static String AUTOMATIC_HELP_KEY = "automatic.help.mode"; //$NON-NLS-1$
	private static boolean automaticHelp = false;

	/**
	 * Initialize the tree line.
	 */
	static void initialize() {

		// Get the automatic mode status.
		String automaticHelpParameter = SimpleProperties
				.getStringPropertyWithDefault(AUTOMATIC_HELP_KEY, "Y"); //$NON-NLS-1$
		if ("N".equals(automaticHelpParameter)) { //$NON-NLS-1$
			automaticHelp = false;
		}

	}

	/**
	 * Indicate if the automatic mode it turned on or off.
	 * 
	 * @return the automaticHelp
	 */
	public static boolean isAutomaticHelp() {

		return automaticHelp;
	}

	/**
	 * Set if the automatic mode it turned on or off.
	 * 
	 * @param automaticHelp
	 *            the automaticHelp to set
	 */
	public static void setAutomaticHelp(boolean automaticHelp) {

		FilterAnyConfigurationAutomaticHelp.automaticHelp = automaticHelp;
		SimpleProperties.setStringProperty(AUTOMATIC_HELP_KEY,
				automaticHelp ? "Y" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
