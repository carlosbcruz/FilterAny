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
 * Configuration on the behavior of the save area.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationSaveTextAreas {

	// Configuration of the save style.
	private static final String SAVE_STYLE_KEY = "save"; //$NON-NLS-1$
	// Possible styles.
	private static final String SAVE_STYLE_IGNORE = "Ignore"; //$NON-NLS-1$
	private static final String SAVE_STYLE_WARN = "Warn"; //$NON-NLS-1$
	private static final String SAVE_STYLE_PERSIST = "Persist"; //$NON-NLS-1$

	// Possible tree lines.
	enum SAVE_STYLES {
		IGNORE, WARN, PERSIST
	}

	private static SAVE_STYLES currentSelection = SAVE_STYLES.PERSIST;

	/**
	 * Initialize the tree line.
	 */
	static void initialize() {

		// Get the configuration.
		String configuration = SimpleProperties.getStringPropertyWithDefault(
				SAVE_STYLE_KEY, SAVE_STYLE_PERSIST);

		if (SAVE_STYLE_IGNORE.equals(configuration)) {
			currentSelection = SAVE_STYLES.IGNORE;
		}
		if (SAVE_STYLE_WARN.equals(configuration)) {
			currentSelection = SAVE_STYLES.WARN;
		}
		if (SAVE_STYLE_PERSIST.equals(configuration)) {
			currentSelection = SAVE_STYLES.PERSIST;
		}
	}

	/**
	 * Set the save style.
	 * 
	 * @param style
	 *            The style.
	 */
	static void setTreeLineStyle(SAVE_STYLES style) {

		if (style == SAVE_STYLES.IGNORE) {
			SimpleProperties.setStringProperty(SAVE_STYLE_KEY,
					SAVE_STYLE_IGNORE);
			currentSelection = SAVE_STYLES.IGNORE;
		}
		if (style == SAVE_STYLES.WARN) {
			SimpleProperties.setStringProperty(SAVE_STYLE_KEY, SAVE_STYLE_WARN);
			currentSelection = SAVE_STYLES.WARN;
		}
		if (style == SAVE_STYLES.PERSIST) {
			SimpleProperties.setStringProperty(SAVE_STYLE_KEY,
					SAVE_STYLE_PERSIST);
			currentSelection = SAVE_STYLES.PERSIST;
		}
	}

	/**
	 * Inform the current save style type.
	 * 
	 * @return The current save style type.
	 */
	static SAVE_STYLES getSaveStyleType() {

		return currentSelection;
	}
}
