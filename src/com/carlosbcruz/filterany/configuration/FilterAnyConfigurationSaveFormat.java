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
 * Configuration on the save format. If it is DOS or UNIX.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationSaveFormat {

	// Configuration of the save format.
	private static final String SAVE_FORMAT = "save.format"; //$NON-NLS-1$
	// Possible formats.
	private static final String DOS_FORMAT = "DOS"; //$NON-NLS-1$
	private static final String UNIX_FORMAT = "Unix"; //$NON-NLS-1$

	// Possible format types.
	enum SAVE_FORMATS {
		DOS, UNIX
	}

	private static SAVE_FORMATS currentSelection = SAVE_FORMATS.DOS;

	/**
	 * Initialize the format type.
	 */
	static void initialize() {

		// Get the configuration.
		String configuration = SimpleProperties.getStringPropertyWithDefault(
				SAVE_FORMAT, DOS_FORMAT);

		if (DOS_FORMAT.equals(configuration)) {
			currentSelection = SAVE_FORMATS.DOS;
		}
		if (UNIX_FORMAT.equals(configuration)) {
			currentSelection = SAVE_FORMATS.UNIX;
		}
	}

	/**
	 * Set the save style.
	 * 
	 * @param format
	 *            The style.
	 */
	static void setSaveFormat(SAVE_FORMATS format) {

		if (format == SAVE_FORMATS.DOS) {
			SimpleProperties.setStringProperty(SAVE_FORMAT, DOS_FORMAT);
			currentSelection = SAVE_FORMATS.DOS;
		}
		if (format == SAVE_FORMATS.UNIX) {
			SimpleProperties.setStringProperty(SAVE_FORMAT, UNIX_FORMAT);
			currentSelection = SAVE_FORMATS.UNIX;
		}
	}

	/**
	 * Inform the current save format.
	 * 
	 * @return The current save format.
	 */
	static SAVE_FORMATS getSaveFormat() {

		return currentSelection;
	}
}
