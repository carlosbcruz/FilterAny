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

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.carlosbcruz.util.SimpleProperties;

/**
 * Configure the theme.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationTheme {

	// Configuration of the theme window.
	private static final String THEME_CONFIGURATION_KEY = "theme"; //$NON-NLS-1$

	private static final String THEME_ACCORDING_TO_LOOK_AND_FEEL = "LookAndFeel"; //$NON-NLS-1$
	private static final String THEME_FORCE_THE_USE = "Force"; //$NON-NLS-1$
	private static final String THEME_DO_NOT_USE = "DoNotUse"; //$NON-NLS-1$

	// Possible look and feels.
	enum THEME_CONFIGURATION {
		LOOK_AND_FEEL, FORCED, DO_NOT_USE
	}

	// Current look and feel.
	private static THEME_CONFIGURATION currentSelection = THEME_CONFIGURATION.LOOK_AND_FEEL;

	/**
	 * Initialize the theme.
	 */
	static void initialize() {

		// Get the theme configuration.
		String themeConfiguration = SimpleProperties
				.getStringPropertyWithDefault(THEME_CONFIGURATION_KEY,
						THEME_ACCORDING_TO_LOOK_AND_FEEL);

		// Set the current selection.
		if (THEME_ACCORDING_TO_LOOK_AND_FEEL.equals(themeConfiguration)) {

			currentSelection = THEME_CONFIGURATION.LOOK_AND_FEEL;
		}

		if (THEME_FORCE_THE_USE.equals(themeConfiguration)) {

			currentSelection = THEME_CONFIGURATION.FORCED;

			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		}

		if (THEME_DO_NOT_USE.equals(themeConfiguration)) {

			currentSelection = THEME_CONFIGURATION.DO_NOT_USE;

			JFrame.setDefaultLookAndFeelDecorated(false);
			JDialog.setDefaultLookAndFeelDecorated(false);
		}
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
	 * Set the theme.
	 * 
	 * @param configuration
	 *            The new current theme configuration.
	 */
	static void setTheme(THEME_CONFIGURATION configuration) {

		if (configuration == THEME_CONFIGURATION.LOOK_AND_FEEL) {
			SimpleProperties.setStringProperty(THEME_CONFIGURATION_KEY,
					THEME_ACCORDING_TO_LOOK_AND_FEEL);
		}
		if (configuration == THEME_CONFIGURATION.FORCED) {
			SimpleProperties.setStringProperty(THEME_CONFIGURATION_KEY,
					THEME_FORCE_THE_USE);

		}
		if (configuration == THEME_CONFIGURATION.DO_NOT_USE) {
			SimpleProperties.setStringProperty(THEME_CONFIGURATION_KEY,
					THEME_DO_NOT_USE);
		}
	}
}
