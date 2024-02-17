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
 * Configure the docking theme.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationDockingTheme {

	// Configuration of the theme window.
	private static final String THEME_CONFIGURATION_KEY = "docking.theme"; //$NON-NLS-1$

	private static final String THEME_SMOOTH = "Smooth"; //$NON-NLS-1$
	private static final String THEME_BASIC = "Basic"; //$NON-NLS-1$
	private static final String THEME_FLAT = "Flat"; //$NON-NLS-1$

	private static final String NO_STACK_KEY = "no.stack"; //$NON-NLS-1$
	private static final String NO_STACK_ACTIVATED = "on"; //$NON-NLS-1$
	private static final String NO_STACK_IGNORED = "off"; //$NON-NLS-1$

	// Possible look and feels.
	enum THEME_CONFIGURATION {
		SMOOTH, BASIC, FLAT
	}

	// Current look and feel.
	private static THEME_CONFIGURATION currentSelection = THEME_CONFIGURATION.BASIC;

	// Indicate that the no stack theme should be used.
	private static boolean useNoStackConfiguration = true;

	/**
	 * Initialize the theme.
	 */
	static void initialize() {

		// Get the theme configuration.
		String themeConfiguration = SimpleProperties
				.getStringPropertyWithDefault(THEME_CONFIGURATION_KEY,
						THEME_SMOOTH);

		// Set the current selection.
		if (THEME_SMOOTH.equals(themeConfiguration)) {

			currentSelection = THEME_CONFIGURATION.SMOOTH;
		}

		if (THEME_BASIC.equals(themeConfiguration)) {

			currentSelection = THEME_CONFIGURATION.BASIC;
		}

		if (THEME_FLAT.equals(themeConfiguration)) {

			currentSelection = THEME_CONFIGURATION.FLAT;
		}

		// Indicate if it is required to used no stack.
		String useNoStack = SimpleProperties.getStringPropertyWithDefault(
				NO_STACK_KEY, NO_STACK_ACTIVATED);

		useNoStackConfiguration = NO_STACK_ACTIVATED.equals(useNoStack);

	}

	/**
	 * Inform the current configuration.
	 * 
	 * @return The current configuration.
	 */
	static THEME_CONFIGURATION getCurrentThemeConfiguration() {

		return currentSelection;
	}

	/**
	 * Inform if it is required to used no stack.
	 * 
	 * @return the no stack configuration.
	 */
	static boolean useNoStackTheme() {

		return useNoStackConfiguration;
	}

	/**
	 * Set the theme.
	 * 
	 * @param configuration
	 *            The new current theme configuration.
	 */
	static void setDockingTheme(THEME_CONFIGURATION configuration) {

		if (configuration == THEME_CONFIGURATION.SMOOTH) {
			SimpleProperties.setStringProperty(THEME_CONFIGURATION_KEY,
					THEME_SMOOTH);
		}

		if (configuration == THEME_CONFIGURATION.BASIC) {
			SimpleProperties.setStringProperty(THEME_CONFIGURATION_KEY,
					THEME_BASIC);
		}

		if (configuration == THEME_CONFIGURATION.FLAT) {
			SimpleProperties.setStringProperty(THEME_CONFIGURATION_KEY,
					THEME_FLAT);
		}

	}

	/**
	 * Set if the no stack theme should be used.
	 * 
	 * @param useNoStack
	 *            the no stack parameter.
	 */
	static void setNoStackTheme(boolean useNoStack) {

		if (useNoStack) {
			SimpleProperties
					.setStringProperty(NO_STACK_KEY, NO_STACK_ACTIVATED);
		} else {
			SimpleProperties.setStringProperty(NO_STACK_KEY, NO_STACK_IGNORED);
		}
	}
}
