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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;

import com.carlosbcruz.util.SimpleProperties;

/**
 * Keep track of the size and position of the FilterAny window.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnyConfigurationJDialogStatus {

	private static Point location;
	private static Dimension dimension;
	private static boolean maximized = false;

	public static final String IS_MAXIMIZED = "isWindowMaximized"; //$NON-NLS-1$
	public static final String MAXIMIZED = "maximized"; //$NON-NLS-1$
	public static final String NOT_MAXIMIZED = "not_maximized"; //$NON-NLS-1$

	public static final String PREVIOUS_WINDOW_X_POSITION = "previousWindow.x.position"; //$NON-NLS-1$
	public static final String PREVIOUS_WINDOW_Y_POSITION = "previousWindow.y.position"; //$NON-NLS-1$
	public static final String PREVIOUS_WINDOW_WIDTH = "previousWindow.width"; //$NON-NLS-1$
	public static final String PREVIOUS_WINDOW_HEIGHT = "previousWindow.height"; //$NON-NLS-1$

	/**
	 * Initialize dialog status.
	 */
	static void initialize() {

		location = new Point();

		location.setLocation(
				Double.parseDouble(SimpleProperties
						.getStringPropertyWithDefault(
								PREVIOUS_WINDOW_X_POSITION, "0")), Double //$NON-NLS-1$
						.parseDouble(SimpleProperties
								.getStringPropertyWithDefault(
										PREVIOUS_WINDOW_Y_POSITION, "0"))); //$NON-NLS-1$

		dimension = new Dimension();

		dimension.setSize(Double.parseDouble(SimpleProperties
				.getStringPropertyWithDefault(PREVIOUS_WINDOW_WIDTH, "600")), //$NON-NLS-1$
				Double.parseDouble(SimpleProperties
						.getStringPropertyWithDefault(PREVIOUS_WINDOW_HEIGHT,
								"400"))); //$NON-NLS-1$

		String isMaximized = SimpleProperties.getStringPropertyWithDefault(
				IS_MAXIMIZED, NOT_MAXIMIZED);

		maximized = isMaximized.equals(MAXIMIZED);
	}

	/**
	 * Store the current internal state.
	 */
	private static void storeCurrentStatus() {
		
		SimpleProperties.setStringProperty(PREVIOUS_WINDOW_X_POSITION,
				String.valueOf(location.getX()));
		SimpleProperties.setStringProperty(PREVIOUS_WINDOW_Y_POSITION,
				String.valueOf(location.getY()));
		SimpleProperties.setStringProperty(PREVIOUS_WINDOW_WIDTH,
				String.valueOf(dimension.getWidth()));
		SimpleProperties.setStringProperty(PREVIOUS_WINDOW_HEIGHT,
				String.valueOf(dimension.getHeight()));
		
	}
	/**
	 * Record the current window status.
	 * 
	 * @param dialog
	 *            The JDialog component.
	 */
	public static void recordWindowStatus(JDialog dialog) {

		location = dialog.getLocation();
		dimension = dialog.getSize();
		
		storeCurrentStatus();
	}

	/**
	 * Restore the last status of the dialog.
	 * 
	 * @param dialog
	 *            The JDialog component.
	 */
	public static void restorePreviousWindowStatus(JDialog dialog) {

		if (location == null || dimension == null) {
			return;
		}

		dialog.setLocation(location.x, location.y);
		dialog.setSize(dimension.width, dimension.height);
		
		storeCurrentStatus();
		
	}

	/**
	 * Maximize the dialog to use the entire screen.
	 * 
	 * @param The
	 *            JDialog component.
	 */
	public static void maximizeWindow(JDialog dialog) {

		dialog.setLocation(0, 0);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		dialog.setSize(screenSize.width, screenSize.height);
	}

	/**
	 * Inform if the window was previously minimized.
	 * 
	 * @return true if it was previously minimized.
	 */
	// public static boolean wasWindowStatusMaximized() {
	//
	// if (location == null || dimension == null) {
	// return false;
	// }
	//
	// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//
	// return (location.x == 0 && location.y == 0
	// && dimension.getWidth() == screenSize.getWidth() && dimension
	// .getHeight() == screenSize.getHeight());
	// }

	/**
	 * Indicate that the window was maximized.
	 */
	public static void flagWindowMaximized() {

		SimpleProperties.setStringProperty(IS_MAXIMIZED, MAXIMIZED);

		maximized = true;
	}

	/**
	 * Indicate that the window was flagged maximized and clear the flag.
	 * 
	 * @return The indication that the window was flagged maximized and clear
	 *         the flag.
	 */
	public static boolean wasFlaggedMaximized() {

		if (maximized) {
			maximized = false;
			SimpleProperties.setStringProperty(IS_MAXIMIZED, NOT_MAXIMIZED);
			return true;
		}

		return false;
	}
}
