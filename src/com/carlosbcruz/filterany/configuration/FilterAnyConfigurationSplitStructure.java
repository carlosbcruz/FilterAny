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
 * Configure the layout of the split structure.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationSplitStructure {

	// When using split select if it is a vertical or horizontal split.
	private static final String USE_VERTICAL_TEXT_SPLIT_PARAMETER = "use.vertical.text.split"; //$NON-NLS-1$
	private static boolean useVerticalTextSplit = false;

	// When using split indicate if the controls are on the left or right.
	private static final String CONTROLS_ARE_ON_THE_RIGHT = "controls.are.on.the.right"; //$NON-NLS-1$
	private static boolean controlsAreOnTheRight = false;

	private static final String YES = "Y"; //$NON-NLS-1$
	private static final String NO = "N"; //$NON-NLS-1$

	/**
	 * Initialize the theme.
	 */
	static void initialize() {

		String useVerticalTextSplitParameter = SimpleProperties
				.getStringPropertyWithDefault(
						USE_VERTICAL_TEXT_SPLIT_PARAMETER, NO);
		useVerticalTextSplit = YES.equals(useVerticalTextSplitParameter
				.toUpperCase());

		String controlsAreOnTheRightParameter = SimpleProperties
				.getStringPropertyWithDefault(CONTROLS_ARE_ON_THE_RIGHT, NO);
		controlsAreOnTheRight = YES.equals(controlsAreOnTheRightParameter
				.toUpperCase());

	}

	/**
	 * Indicate if the text area split is horizontal or vertical.
	 * 
	 * @return the useVerticalTextSplit
	 */
	public static boolean isUseVerticalTextSplit() {

		return useVerticalTextSplit;
	}

	/**
	 * Indicate if the controls are on the right or not.
	 * 
	 * @return the controlsAreOnTheRight true if the controls are on the right.
	 */
	public static boolean isControlsAreOnTheRight() {

		return controlsAreOnTheRight;
	}

	/**
	 * Set if the text area split is horizontal or vertical.
	 * 
	 * @param useVerticalTextSplitParameter
	 *            the useVerticalTextSplit to set
	 */
	protected static void setUseVerticalTextSplit(
			boolean useVerticalTextSplitParameter) {

		useVerticalTextSplit = useVerticalTextSplitParameter;

		// Save the configuration of text area split
		if (useVerticalTextSplit) {
			SimpleProperties.setStringProperty(
					USE_VERTICAL_TEXT_SPLIT_PARAMETER, YES);
		} else {
			SimpleProperties.setStringProperty(
					USE_VERTICAL_TEXT_SPLIT_PARAMETER, NO);
		}
	}

	/**
	 * Set if the controls are on the right or not.
	 * 
	 * @param controlsAreOnTheRightParameter
	 *            the controlsAreOnTheRight to set: true if the controls are on
	 *            the right.
	 */
	protected static void setControlsAreOnTheRight(
			boolean controlsAreOnTheRightParameter) {

		controlsAreOnTheRight = controlsAreOnTheRightParameter;

		// Save the indication of there the controls are.
		if (controlsAreOnTheRight) {
			SimpleProperties.setStringProperty(CONTROLS_ARE_ON_THE_RIGHT, YES);
		} else {
			SimpleProperties.setStringProperty(CONTROLS_ARE_ON_THE_RIGHT, NO);
		}

	}

}
