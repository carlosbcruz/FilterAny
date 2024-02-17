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

import java.util.Locale;

import com.carlosbcruz.util.SimpleProperties;

/**
 * Configure the language.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationLanguage {

	// Parameter to store the language selection.
	private static final String LANGUAGE_SELECTION = "language"; //$NON-NLS-1$

	// Available locales.
	static enum AVAILABLE_LOCALES {
		DEFAULT, EN_US, PT_BR
	}

	private static final Locale AVAILABLE_LOCALES_INSTANCES[] = new Locale[] {
			Locale.getDefault(), new Locale("en", "US"), new Locale("pt", "BR") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	// Current locale
	private static int currentLocaleIndex = 0;

	/**
	 * Read the language configuration.
	 */
	static void initialize() {

		// Current selection on file.
		String currantLanguageSelection = SimpleProperties
				.getStringPropertyWithDefault(LANGUAGE_SELECTION, "0"); //$NON-NLS-1$

		int languageSelection = 0;

		try {
			languageSelection = Integer.parseInt(currantLanguageSelection);
		} catch (NumberFormatException e) {
			languageSelection = 0;
			SimpleProperties.setStringProperty(LANGUAGE_SELECTION, "0"); //$NON-NLS-1$
		}
		// Verify if the selection makes sense.
		if (languageSelection < 0
				|| languageSelection > AVAILABLE_LOCALES_INSTANCES.length) {
			languageSelection = 0;
			SimpleProperties.setStringProperty(LANGUAGE_SELECTION, "0"); //$NON-NLS-1$
		}

		// Set the selection language.
		currentLocaleIndex = languageSelection;

		Locale.setDefault(getCurrentLocale());
	}

	/**
	 * Set the current language. It only store the new selection. But does not
	 * change the current language of the instance being executed.
	 * 
	 * @param language
	 *            The current language index.
	 */
	protected static void setLanguage(AVAILABLE_LOCALES language) {

		if (language == AVAILABLE_LOCALES.DEFAULT) {
			SimpleProperties.setStringProperty(LANGUAGE_SELECTION, "0"); //$NON-NLS-1$
		}
		if (language == AVAILABLE_LOCALES.EN_US) {
			SimpleProperties.setStringProperty(LANGUAGE_SELECTION, "1"); //$NON-NLS-1$
		}
		if (language == AVAILABLE_LOCALES.PT_BR) {
			SimpleProperties.setStringProperty(LANGUAGE_SELECTION, "2"); //$NON-NLS-1$
		}
	}

	/**
	 * Inform the current locale name.
	 * 
	 * @return The locale name.
	 */
	protected static AVAILABLE_LOCALES getCurrentLocaleName() {

		switch (currentLocaleIndex) {
		case (1): {
			return AVAILABLE_LOCALES.EN_US;
		}
		case (2): {
			return AVAILABLE_LOCALES.PT_BR;
		}
		default: {
			return AVAILABLE_LOCALES.DEFAULT;
		}
		}
	}

	/**
	 * Inform the selected locale.
	 * 
	 * @return the currentLocale The selected locale.
	 */
	protected static Locale getCurrentLocale() {

		return AVAILABLE_LOCALES_INSTANCES[currentLocaleIndex];
	}
}
