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

import java.awt.Point;

import com.carlosbcruz.util.SimpleProperties;
import com.carlosbcruz.util.SwingUtil;

/**
 * Configure window position
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationWindowPosition {

	// Position of the window.
	private static final String WINDOW_X_POSITION = "position_x"; //$NON-NLS-1$
	private static final String WINDOW_Y_POSITION = "position_y"; //$NON-NLS-1$
	// Window position.
	private static int windowPositionX = 0;
	private static int windowPositionY = 0;

	// Size of the window.
	private static final String WINDOW_WIDTH = "window_width"; //$NON-NLS-1$
	private static final String WINDOW_HEIGHT = "window_height"; //$NON-NLS-1$
	// Window dimension.
	private static int windowWidth = 0;
	private static int windowHeight = 0;

	// Position of the windows splitters.
	private static final String CONTROL_SPLITTER_POSITION = "control.splitter"; //$NON-NLS-1$
	private static final String TEXT_AREAS_SPLITTER_POSITION = "text.areas.splitter"; //$NON-NLS-1$
	private static final String INPUT_SPLITTER_POSITION = "input.splitter"; //$NON-NLS-1$
	// Location of the splitters.
	private static int controlsSplitterLocation = 0;
	private static int textAreaSplitterLocation = 0;
	private static int inputAreaSplitterLocation = 0;

	/**
	 * Read windows position and size.
	 */
	static void initialize() {

		Point centerOfScreen = SwingUtil.getCenterOfScreen();

		// Set the window position.
		String xPosition = SimpleProperties.getStringPropertyWithDefault(
				WINDOW_X_POSITION, String.valueOf(centerOfScreen.x / 4));
		windowPositionX = Integer.parseInt(xPosition);

		String yPosition = SimpleProperties.getStringPropertyWithDefault(
				WINDOW_Y_POSITION, String.valueOf(centerOfScreen.y / 4));
		windowPositionY = Integer.parseInt(yPosition);

		// Set the window size.
		String width = SimpleProperties.getStringPropertyWithDefault(
				WINDOW_WIDTH,
				String.valueOf(centerOfScreen.x + centerOfScreen.x / 2));
		windowWidth = Integer.parseInt(width);

		String height = SimpleProperties.getStringPropertyWithDefault(
				WINDOW_HEIGHT,
				String.valueOf(centerOfScreen.y + centerOfScreen.y / 2));
		windowHeight = Integer.parseInt(height);

		// If splits are being used.
		if (FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS == FilterAnyConfigurationStructure
				.getCurrentStructure()) {

			// Adjust the controls according to the controls
			if (FilterAnyConfigurationSplitStructure.isControlsAreOnTheRight()) {

				// Set the controls splitter.
				String controlsLocation = SimpleProperties
						.getStringPropertyWithDefault(
								CONTROL_SPLITTER_POSITION,
								String.valueOf(centerOfScreen.x
										+ centerOfScreen.x / 6));
				controlsSplitterLocation = Integer.parseInt(controlsLocation);

			} else {

				// Set the controls splitter.
				String controlsLocation = SimpleProperties
						.getStringPropertyWithDefault(
								CONTROL_SPLITTER_POSITION,
								String.valueOf(centerOfScreen.x / 3));
				controlsSplitterLocation = Integer.parseInt(controlsLocation);

			}

			if (FilterAnyConfigurationSplitStructure.isUseVerticalTextSplit()) {

				// Adjust the controls according to the controls
				if (FilterAnyConfigurationSplitStructure
						.isControlsAreOnTheRight()) {

					// Set the text area splitter position
					String textAreaLocation = SimpleProperties
							.getStringPropertyWithDefault(
									TEXT_AREAS_SPLITTER_POSITION,
									String.valueOf((int) (centerOfScreen.x / 1.7)));
					textAreaSplitterLocation = Integer
							.parseInt(textAreaLocation);

					String inputAreaLocation = SimpleProperties
							.getStringPropertyWithDefault(
									INPUT_SPLITTER_POSITION,
									String.valueOf((int) (centerOfScreen.y / 1.55)));
					inputAreaSplitterLocation = Integer
							.parseInt(inputAreaLocation);

				} else {

					// Set the text area splitter position
					String textAreaLocation = SimpleProperties
							.getStringPropertyWithDefault(
									TEXT_AREAS_SPLITTER_POSITION,
									String.valueOf((int) (centerOfScreen.x / 1.8)));
					textAreaSplitterLocation = Integer
							.parseInt(textAreaLocation);

					String inputAreaLocation = SimpleProperties
							.getStringPropertyWithDefault(
									INPUT_SPLITTER_POSITION,
									String.valueOf((int) (centerOfScreen.y / 1.55)));
					inputAreaSplitterLocation = Integer
							.parseInt(inputAreaLocation);

				}

			} else {

				// Adjust the controls according to the controls
				if (FilterAnyConfigurationSplitStructure
						.isControlsAreOnTheRight()) {

					// Set the text area splitter position
					String textAreaLocation = SimpleProperties
							.getStringPropertyWithDefault(
									TEXT_AREAS_SPLITTER_POSITION,
									String.valueOf((int) (centerOfScreen.y / 1.6)));
					textAreaSplitterLocation = Integer
							.parseInt(textAreaLocation);

					String inputAreaLocation = SimpleProperties
							.getStringPropertyWithDefault(
									INPUT_SPLITTER_POSITION,
									String.valueOf((int) (centerOfScreen.x / 1.72)));
					inputAreaSplitterLocation = Integer
							.parseInt(inputAreaLocation);

				} else {

					// Set the text area splitter position
					String textAreaLocation = SimpleProperties
							.getStringPropertyWithDefault(
									TEXT_AREAS_SPLITTER_POSITION,
									String.valueOf((int) (centerOfScreen.y / 1.6)));
					textAreaSplitterLocation = Integer
							.parseInt(textAreaLocation);

					String inputAreaLocation = SimpleProperties
							.getStringPropertyWithDefault(
									INPUT_SPLITTER_POSITION,
									String.valueOf((int) (centerOfScreen.x / 1.77)));
					inputAreaSplitterLocation = Integer
							.parseInt(inputAreaLocation);

				}
			}
		}
	}

	/**
	 * Inform the window x position.
	 * 
	 * @return the windowPositionX The window x position.
	 */
	protected static int getWindowPositionX() {

		return windowPositionX;
	}

	/**
	 * Store the current window x position.
	 * 
	 * @param windowPositionX
	 *            the windowPositionX to set The current window x position.
	 */
	protected static void setWindowPositionX(int windowPositionX) {

		FilterAnyConfigurationWindowPosition.windowPositionX = windowPositionX;

		SimpleProperties.setStringProperty(WINDOW_X_POSITION,
				String.valueOf(windowPositionX));
	}

	/**
	 * Inform the window Y position.
	 * 
	 * @return the windowPositionY The window Y position.
	 */
	protected static int getWindowPositionY() {

		return windowPositionY;
	}

	/**
	 * Store the current window y position.
	 * 
	 * @param windowPositionY
	 *            the windowPositionY to set The current window y position.
	 */
	protected static void setWindowPositionY(int windowPositionY) {

		FilterAnyConfigurationWindowPosition.windowPositionY = windowPositionY;

		SimpleProperties.setStringProperty(WINDOW_Y_POSITION,
				String.valueOf(windowPositionY));
	}

	/**
	 * Inform the window width.
	 * 
	 * @return the windowWidth The window width.
	 */
	protected static int getWindowWidth() {

		return windowWidth;
	}

	/**
	 * Store the window width.
	 * 
	 * @param windowWidth
	 *            the windowWidth to set The window width.
	 */
	protected static void setWindowWidth(int windowWidth) {

		FilterAnyConfigurationWindowPosition.windowWidth = windowWidth;

		// Save the window size
		SimpleProperties.setStringProperty(WINDOW_WIDTH,
				String.valueOf(windowWidth));
	}

	/**
	 * Inform the window height.
	 * 
	 * @return the windowHeight The window height.
	 */
	protected static int getWindowHeight() {

		return windowHeight;
	}

	/**
	 * Store the window height.
	 * 
	 * @param windowHeight
	 *            the windowHeight to set The window height.
	 */
	protected static void setWindowHeight(int windowHeight) {

		FilterAnyConfigurationWindowPosition.windowHeight = windowHeight;

		// Save the window size
		SimpleProperties.setStringProperty(WINDOW_HEIGHT,
				String.valueOf(windowHeight));

	}

	/**
	 * Inform the controls split location.
	 * 
	 * @return the controlsSplitterLocation The controls split location.
	 */
	protected static int getControlsSplitterLocation() {

		return controlsSplitterLocation;
	}

	/**
	 * Store the controls split location.
	 * 
	 * @param controlsSplitterLocation
	 *            the controlsSplitterLocation to set The controls split
	 *            location.
	 */
	protected static void setControlsSplitterLocation(
			int verticalSplitterLocation) {

		controlsSplitterLocation = verticalSplitterLocation;

		// Save the vertical splitter position
		SimpleProperties.setStringProperty(CONTROL_SPLITTER_POSITION,
				String.valueOf(verticalSplitterLocation));

	}

	/**
	 * Inform the horizontal splitter location.
	 * 
	 * @return the textAreaSplitterLocation The the text areas splitter
	 *         location.
	 */
	protected static int getTextAreasSplitterLocation() {

		return textAreaSplitterLocation;
	}

	/**
	 * Store the text areas splitter location.
	 * 
	 * @param textAreaSplitterLocation
	 *            the textAreaSplitterLocation to set The the text areas
	 *            splitter location.
	 */
	protected static void setTextAreasSplitterLocation(
			int horizontalSplitterLocation) {

		textAreaSplitterLocation = horizontalSplitterLocation;

		// Save the horizontal splitter position
		SimpleProperties.setStringProperty(TEXT_AREAS_SPLITTER_POSITION,
				String.valueOf(horizontalSplitterLocation));
	}

	/**
	 * @return the inputAreaSplitterLocation
	 */
	protected static int getInputAreaSplitterLocation() {
		return inputAreaSplitterLocation;
	}

	/**
	 * @param inputAreaSplitterLocation
	 *            the inputAreaSplitterLocation to set
	 */
	protected static void setInputAreaSplitterLocation(
			int inputAreaSplitterLocationParameter) {
		inputAreaSplitterLocation = inputAreaSplitterLocationParameter;

		// Save the input splitter position
		SimpleProperties.setStringProperty(INPUT_SPLITTER_POSITION,
				String.valueOf(inputAreaSplitterLocation));

	}

}
