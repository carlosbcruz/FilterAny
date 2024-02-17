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
 * Configure the layout of the structure.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationStructure {

	// Indicate if it is requested to use internal frames or fixed structure.
	private static final String USE_INTERNAL_WINDOW = "W"; //$NON-NLS-1$
	private static final String USE_SPLITERS = "S"; //$NON-NLS-1$

	private static final String INTERNAL_STRUCTURE_KEY = "internal.structure"; //$NON-NLS-1$

	static enum INTERNAL_STRUCTURE {
		INTERNAL_WINDOW, SPLITERS
	}

	private static INTERNAL_STRUCTURE currentSelection = INTERNAL_STRUCTURE.SPLITERS;

	/**
	 * Initialize the laytout of the structure.
	 */
	static void initialize() {

		// Get the structure configuration.
		// Read the main configurations first.
		String internalStructureParameter = SimpleProperties
				.getStringPropertyWithDefault(INTERNAL_STRUCTURE_KEY,
						USE_SPLITERS);
		if (USE_INTERNAL_WINDOW
				.equals(internalStructureParameter.toUpperCase())) {
			currentSelection = INTERNAL_STRUCTURE.INTERNAL_WINDOW;
		}
		if (USE_SPLITERS.equals(internalStructureParameter.toUpperCase())) {
			currentSelection = INTERNAL_STRUCTURE.SPLITERS;
		}
	}

	/**
	 * Inform the current structure.
	 * 
	 * @return The current structure.
	 */
	static INTERNAL_STRUCTURE getCurrentStructure() {

		return currentSelection;
	}

	/**
	 * Set the new current structure.
	 * 
	 * @param structure
	 *            The new current structure.
	 */
	static void setCurrentStructure(INTERNAL_STRUCTURE structure) {

		if (structure == INTERNAL_STRUCTURE.INTERNAL_WINDOW) {
			SimpleProperties.setStringProperty(INTERNAL_STRUCTURE_KEY,
					USE_INTERNAL_WINDOW);
		}
		if (structure == INTERNAL_STRUCTURE.SPLITERS) {
			SimpleProperties.setStringProperty(INTERNAL_STRUCTURE_KEY,
					USE_SPLITERS);
		}
	}
}
