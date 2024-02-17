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
 * Configure the tree line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationTreeLine {

	// Configuration of the tree line style.
	private static final String LINE_STYLE_KEY = "line"; //$NON-NLS-1$
	// Possible line styles.
	private static final String LINE_STYLE_ANGLED = "Angled"; //$NON-NLS-1$
	private static final String LINE_STYLE_HORIZONTAL = "Horizontal"; //$NON-NLS-1$
	private static final String LINE_STYLE_NONE = "None"; //$NON-NLS-1$

	// Possible tree lines.
	enum TREE_LINE_TYPES {
		ANGLED, HORIZONTAL, NONE
	}

	private static TREE_LINE_TYPES currentSelection = TREE_LINE_TYPES.ANGLED;

	/**
	 * Initialize the tree line.
	 */
	static void initialize() {

		// Get the line configuration.
		String lineConfiguration = SimpleProperties
				.getStringPropertyWithDefault(LINE_STYLE_KEY, LINE_STYLE_ANGLED);

		if (LINE_STYLE_ANGLED.equals(lineConfiguration)) {
			currentSelection = TREE_LINE_TYPES.ANGLED;
		}
		if (LINE_STYLE_HORIZONTAL.equals(lineConfiguration)) {
			currentSelection = TREE_LINE_TYPES.HORIZONTAL;
		}
		if (LINE_STYLE_NONE.equals(lineConfiguration)) {
			currentSelection = TREE_LINE_TYPES.NONE;
		}
	}

	/**
	 * Set the line style.
	 * 
	 * @param style
	 *            The style.
	 */
	static void setTreeLineStyle(TREE_LINE_TYPES style) {

		if (style == TREE_LINE_TYPES.ANGLED) {
			SimpleProperties.setStringProperty(LINE_STYLE_KEY,
					LINE_STYLE_ANGLED);
		}
		if (style == TREE_LINE_TYPES.HORIZONTAL) {
			SimpleProperties.setStringProperty(LINE_STYLE_KEY,
					LINE_STYLE_HORIZONTAL);
		}
		if (style == TREE_LINE_TYPES.NONE) {
			SimpleProperties.setStringProperty(LINE_STYLE_KEY, LINE_STYLE_NONE);
		}
	}

	/**
	 * Inform the current line style type.
	 * 
	 * @return The current line style type.
	 */
	static TREE_LINE_TYPES getTreeLineStyleType() {

		return currentSelection;
	}

	/**
	 * Inform the current tree line style.
	 * 
	 * @return the treeLineStyle The tree line style.
	 */
	static String getTreeLineStyle() {

		if (currentSelection == TREE_LINE_TYPES.ANGLED) {
			return LINE_STYLE_ANGLED;
		}
		if (currentSelection == TREE_LINE_TYPES.HORIZONTAL) {
			return LINE_STYLE_HORIZONTAL;
		}
		if (currentSelection == TREE_LINE_TYPES.NONE) {
			return LINE_STYLE_NONE;
		}

		return LINE_STYLE_ANGLED;
	}
}
