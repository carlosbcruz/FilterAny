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

import com.carlosbcruz.util.InternalWindowPositionBean;
import com.carlosbcruz.util.SimpleProperties;

/**
 * Configure the internal window position
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationInternalWindowPosition {

	// Constants used to store the window configuration on property files.
	public static final String WINDOW_POSITION_EXIST = "window.position.exist"; //$NON-NLS-1$

	public static final String FILTERS_X_POSITION = "filtersWindow.x.position"; //$NON-NLS-1$
	public static final String FILTERS_Y_POSITION = "filtersWindow.y.position"; //$NON-NLS-1$
	public static final String FILTERS_WIDTH = "filtersWindow.width"; //$NON-NLS-1$
	public static final String FILTERS_HEIGHT = "filtersWindow.height"; //$NON-NLS-1$
	public static final String FILTER_Z_ORDER = "filter.z.order"; //$NON-NLS-1$

	public static final String TOP_AREA_X_POSITION = "top.area.x.position"; //$NON-NLS-1$
	public static final String TOP_AREA_Y_POSITION = "top.area.y.position"; //$NON-NLS-1$
	public static final String TOP_AREA_WIDTH = "top.area.width"; //$NON-NLS-1$
	public static final String TOP_AREA_HEIGHT = "top.area.height"; //$NON-NLS-1$
	public static final String TOP_AREA_Z_ORDER = "top.area.z.order"; //$NON-NLS-1$

	public static final String AUXILIAR_AREA_X_POSITION = "auxiliar.area.x.position"; //$NON-NLS-1$
	public static final String AUXILIAR_AREA_Y_POSITION = "auxiliar.area.y.position"; //$NON-NLS-1$
	public static final String AUXILIAR_AREA_WIDTH = "auxiliar.area.width"; //$NON-NLS-1$
	public static final String AUXILIAR_AREA_HEIGHT = "auxiliar.area.height"; //$NON-NLS-1$
	public static final String AUXILIAR_AREA_Z_ORDER = "auxiliar.area.z.order"; //$NON-NLS-1$

	public static final String BOTTOM_AREA_X_POSITION = "bottom.area.x.position"; //$NON-NLS-1$
	public static final String BOTTOM_AREA_Y_POSITION = "bottom.area.y.position"; //$NON-NLS-1$
	public static final String BOTTOM_AREA_WIDTH = "bottom.area.width"; //$NON-NLS-1$
	public static final String BOTTOM_AREA_HEIGHT = "bottom.area.height"; //$NON-NLS-1$
	public static final String BOTTOM_AREA_Z_ORDER = "bottom.area.z.order"; //$NON-NLS-1$

	public static final String CONTROL_AREA_X_POSITION = "control.area.x.position"; //$NON-NLS-1$
	public static final String CONTROL_AREA_Y_POSITION = "control.area.y.position"; //$NON-NLS-1$
	public static final String CONTROL_AREA_WIDTH = "control.area.width"; //$NON-NLS-1$
	public static final String CONTROL_AREA_HEIGHT = "control.area.height"; //$NON-NLS-1$
	public static final String CONTROL_AREA_Z_ORDER = "control.area.z.order"; //$NON-NLS-1$

	public static final String HELP_AREA_X_POSITION = "help.area.x.position"; //$NON-NLS-1$
	public static final String HELP_AREA_Y_POSITION = "help.area.y.position"; //$NON-NLS-1$
	public static final String HELP_AREA_WIDTH = "help.area.width"; //$NON-NLS-1$
	public static final String HELP_AREA_HEIGHT = "help.area.height"; //$NON-NLS-1$
	public static final String HELP_AREA_Z_ORDER = "help.area.z.order"; //$NON-NLS-1$

	private static final String YES = "Y"; //$NON-NLS-1$

	/**
	 * Indicate if there are window positions.
	 * 
	 * @return True if there is window positions.
	 */
	public static boolean isInternalWindowsPositionExist() {

		String internalWindowsPositionExist = SimpleProperties
				.getStringPropertyWithDefault(WINDOW_POSITION_EXIST, YES);

		return (YES.equals(internalWindowsPositionExist));
	}

	/**
	 * Provide the layout of the windows according to the desktop size.
	 * 
	 * @param internalDesktopWidth
	 *            The width of the desktop.
	 * @param internalDesktopHeight
	 *            The height of the desktop.
	 * @param layout
	 *            The type of the layout requested.
	 * @return The windows position for a specific layout.
	 */
	static FilterAnyInternalWindowsPositionBeam getWindowsPosition(
			int internalDesktopWidth, int internalDesktopHeight,
			FilterAnyInternalWindowPositionLayouts layout) {

		FilterAnyInternalWindowsPositionBeam position = new FilterAnyInternalWindowsPositionBeam();

		// Set the help window in the center of the internal desktop.
		int positionX = (internalDesktopWidth - FilterAnyConfiguration
				.getFilterHelpShellWidth()) / 2;
		int positionY = (internalDesktopHeight - FilterAnyConfiguration
				.getFilterHelpShellHeight()) / 2;

		position.getHelpWindow().setX(positionX);
		position.getHelpWindow().setY(positionY);

		position.getHelpWindow().setWidth(
				FilterAnyConfiguration.getFilterHelpShellWidth());
		position.getHelpWindow().setHeight(
				FilterAnyConfiguration.getFilterHelpShellHeight());

		if (layout == FilterAnyInternalWindowPositionLayouts.DEFAULT_POSITION) {

			int oneThirdWidth = internalDesktopWidth / 3;
			int twoThirdsHeight = (internalDesktopHeight / 3) * 2;
			int middleHeight = internalDesktopHeight / 2;

			position.getFiltersWindow().setX(0);
			position.getFiltersWindow().setY(0);
			position.getFiltersWindow().setWidth(oneThirdWidth);
			position.getFiltersWindow().setHeight(twoThirdsHeight);

			position.getControlWindow().setX(0);
			position.getControlWindow().setY(twoThirdsHeight);
			position.getControlWindow().setWidth(oneThirdWidth);
			position.getControlWindow().setHeight(
					internalDesktopHeight - twoThirdsHeight);

			int textWidth = internalDesktopWidth - oneThirdWidth;
			position.getTopWindow().setX(oneThirdWidth);
			position.getTopWindow().setY(0);
			position.getTopWindow().setWidth(textWidth / 2);
			position.getTopWindow().setHeight(middleHeight);

			position.getAuxiliarWindow().setX(oneThirdWidth + (textWidth / 2));
			position.getAuxiliarWindow().setY(0);
			position.getAuxiliarWindow().setWidth(textWidth / 2);
			position.getAuxiliarWindow().setHeight(middleHeight);

			position.getBottomWindow().setX(oneThirdWidth);
			position.getBottomWindow().setY(middleHeight);
			position.getBottomWindow().setWidth(
					internalDesktopWidth - oneThirdWidth);
			position.getBottomWindow().setHeight(middleHeight);
		}

		if (layout == FilterAnyInternalWindowPositionLayouts.VERTICAL_AREAS) {

			int oneThirdWidth = internalDesktopWidth / 3;
			int twoThirdsHeight = (internalDesktopHeight / 3) * 2;

			position.getFiltersWindow().setX(0);
			position.getFiltersWindow().setY(0);
			position.getFiltersWindow().setWidth(oneThirdWidth);
			position.getFiltersWindow().setHeight(twoThirdsHeight);

			position.getControlWindow().setX(0);
			position.getControlWindow().setY(twoThirdsHeight);
			position.getControlWindow().setWidth(oneThirdWidth);
			position.getControlWindow().setHeight(
					internalDesktopHeight - twoThirdsHeight);

			position.getTopWindow().setX(oneThirdWidth);
			position.getTopWindow().setY(0);
			position.getTopWindow().setWidth(oneThirdWidth);
			position.getTopWindow().setHeight(internalDesktopHeight / 2);

			position.getAuxiliarWindow().setX(oneThirdWidth);
			position.getAuxiliarWindow().setY(internalDesktopHeight / 2);
			position.getAuxiliarWindow().setWidth(oneThirdWidth);
			position.getAuxiliarWindow().setHeight(internalDesktopHeight / 2);

			position.getBottomWindow().setX(oneThirdWidth * 2);
			position.getBottomWindow().setY(0);
			position.getBottomWindow().setWidth(oneThirdWidth);
			position.getBottomWindow().setHeight(internalDesktopHeight);

		}

		if (layout == FilterAnyInternalWindowPositionLayouts.CONTROL_ON_THE_MIDDLE) {

			int oneThirdWidth = internalDesktopWidth / 3;
			int twoThirdsHeight = (internalDesktopHeight / 3) * 2;

			position.getFiltersWindow().setX(oneThirdWidth);
			position.getFiltersWindow().setY(0);
			position.getFiltersWindow().setWidth(oneThirdWidth);
			position.getFiltersWindow().setHeight(twoThirdsHeight);

			position.getControlWindow().setX(oneThirdWidth);
			position.getControlWindow().setY(twoThirdsHeight);
			position.getControlWindow().setWidth(oneThirdWidth);
			position.getControlWindow().setHeight(
					internalDesktopHeight - twoThirdsHeight);

			position.getTopWindow().setX(0);
			position.getTopWindow().setY(0);
			position.getTopWindow().setWidth(oneThirdWidth);
			position.getTopWindow().setHeight(internalDesktopHeight / 2);

			position.getAuxiliarWindow().setX(0);
			position.getAuxiliarWindow().setY(internalDesktopHeight / 2);
			position.getAuxiliarWindow().setWidth(oneThirdWidth);
			position.getAuxiliarWindow().setHeight(internalDesktopHeight / 2);

			position.getBottomWindow().setX(oneThirdWidth * 2);
			position.getBottomWindow().setY(0);
			position.getBottomWindow().setWidth(oneThirdWidth);
			position.getBottomWindow().setHeight(internalDesktopHeight);

		}

		if (layout == FilterAnyInternalWindowPositionLayouts.CONTROL_ON_THE_RIGHT_WITH_VERTICAL_AREAS) {

			int oneThirdWidth = internalDesktopWidth / 3;
			int twoThirdsHeight = (internalDesktopHeight / 3) * 2;

			position.getFiltersWindow().setX(oneThirdWidth * 2);
			position.getFiltersWindow().setY(0);
			position.getFiltersWindow().setWidth(oneThirdWidth);
			position.getFiltersWindow().setHeight(twoThirdsHeight);

			position.getControlWindow().setX(oneThirdWidth * 2);
			position.getControlWindow().setY(twoThirdsHeight);
			position.getControlWindow().setWidth(oneThirdWidth);
			position.getControlWindow().setHeight(
					internalDesktopHeight - twoThirdsHeight);

			position.getTopWindow().setX(0);
			position.getTopWindow().setY(0);
			position.getTopWindow().setWidth(oneThirdWidth);
			position.getTopWindow().setHeight(internalDesktopHeight / 2);

			position.getAuxiliarWindow().setX(0);
			position.getAuxiliarWindow().setY(internalDesktopHeight / 2);
			position.getAuxiliarWindow().setWidth(oneThirdWidth);
			position.getAuxiliarWindow().setHeight(internalDesktopHeight / 2);

			position.getBottomWindow().setX(oneThirdWidth);
			position.getBottomWindow().setY(0);
			position.getBottomWindow().setWidth(oneThirdWidth);
			position.getBottomWindow().setHeight(internalDesktopHeight);

		}

		if (layout == FilterAnyInternalWindowPositionLayouts.CONTROL_ON_THE_RIGHT) {

			int oneThirdWidth = internalDesktopWidth / 3;
			int twoThirdsHeight = (internalDesktopHeight / 3) * 2;
			int middleHeight = internalDesktopHeight / 2;

			position.getFiltersWindow().setX(oneThirdWidth * 2);
			position.getFiltersWindow().setY(0);
			position.getFiltersWindow().setWidth(oneThirdWidth);
			position.getFiltersWindow().setHeight(twoThirdsHeight);

			position.getControlWindow().setX(oneThirdWidth * 2);
			position.getControlWindow().setY(twoThirdsHeight);
			position.getControlWindow().setWidth(oneThirdWidth);
			position.getControlWindow().setHeight(
					internalDesktopHeight - twoThirdsHeight);

			int textWidth = internalDesktopWidth - oneThirdWidth;
			position.getTopWindow().setX(0);
			position.getTopWindow().setY(0);
			position.getTopWindow().setWidth(textWidth / 2);
			position.getTopWindow().setHeight(middleHeight);

			position.getAuxiliarWindow().setX(textWidth / 2);
			position.getAuxiliarWindow().setY(0);
			position.getAuxiliarWindow().setWidth(textWidth / 2);
			position.getAuxiliarWindow().setHeight(middleHeight);

			position.getBottomWindow().setX(0);
			position.getBottomWindow().setY(middleHeight);
			position.getBottomWindow().setWidth(
					internalDesktopWidth - oneThirdWidth);
			position.getBottomWindow().setHeight(middleHeight);

		}

		return position;
	}

	/**
	 * Provide the layout of the windows according to the desktop size.
	 * 
	 * @param internalDesktopWidth
	 *            The width of the desktop.
	 * @param internalDesktopHeight
	 *            The height of the desktop.
	 * @return
	 */
	static FilterAnyInternalWindowsPositionBeam loadWindowsPosition(
			int internalDesktopWidth, int internalDesktopHeight) {

		FilterAnyInternalWindowsPositionBeam defaultPosition = getWindowsPosition(
				internalDesktopWidth, internalDesktopHeight,
				FilterAnyInternalWindowPositionLayouts.DEFAULT_POSITION);

		// Only read configuration if there are information stored
		if (!isInternalWindowsPositionExist()) {
			return defaultPosition;
		}

		FilterAnyInternalWindowsPositionBeam storedPosition = new FilterAnyInternalWindowsPositionBeam();

		try {

			InternalWindowPositionBean filterWindow = storedPosition
					.getFiltersWindow();

			filterWindow
					.setX(Integer.parseInt(SimpleProperties
							.getStringPropertyWithDefault(FILTERS_X_POSITION,
									String.valueOf(defaultPosition
											.getFiltersWindow().getX()))));
			filterWindow
					.setY(Integer.parseInt(SimpleProperties
							.getStringPropertyWithDefault(FILTERS_Y_POSITION,
									String.valueOf(defaultPosition
											.getFiltersWindow().getY()))));
			filterWindow.setWidth(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(FILTERS_WIDTH, String
							.valueOf(defaultPosition.getFiltersWindow()
									.getWidth()))));
			filterWindow.setHeight(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(FILTERS_HEIGHT, String
							.valueOf(defaultPosition.getFiltersWindow()
									.getHeight()))));
			filterWindow
					.setZ(Integer.parseInt(SimpleProperties
							.getStringPropertyWithDefault(FILTER_Z_ORDER,
									String.valueOf(defaultPosition
											.getFiltersWindow().getZ()))));

			InternalWindowPositionBean topWindow = storedPosition
					.getTopWindow();

			topWindow.setX(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(TOP_AREA_X_POSITION, String
							.valueOf(defaultPosition.getTopWindow().getX()))));
			topWindow.setY(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(TOP_AREA_Y_POSITION, String
							.valueOf(defaultPosition.getTopWindow().getY()))));
			topWindow
					.setWidth(Integer.parseInt(SimpleProperties
							.getStringPropertyWithDefault(TOP_AREA_WIDTH,
									String.valueOf(defaultPosition
											.getTopWindow().getWidth()))));
			topWindow.setHeight(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(TOP_AREA_HEIGHT,
							String.valueOf(defaultPosition.getTopWindow()
									.getHeight()))));
			topWindow.setZ(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(TOP_AREA_Z_ORDER, String
							.valueOf(defaultPosition.getTopWindow().getZ()))));

			InternalWindowPositionBean auxiliarWindow = storedPosition
					.getAuxiliarWindow();

			auxiliarWindow.setX(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(AUXILIAR_AREA_X_POSITION,
							String.valueOf(defaultPosition.getAuxiliarWindow()
									.getX()))));
			auxiliarWindow.setY(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(AUXILIAR_AREA_Y_POSITION,
							String.valueOf(defaultPosition.getAuxiliarWindow()
									.getY()))));
			auxiliarWindow.setWidth(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(AUXILIAR_AREA_WIDTH, String
							.valueOf(defaultPosition.getAuxiliarWindow()
									.getWidth()))));
			auxiliarWindow.setHeight(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(AUXILIAR_AREA_HEIGHT, String
							.valueOf(defaultPosition.getAuxiliarWindow()
									.getHeight()))));
			auxiliarWindow.setZ(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(AUXILIAR_AREA_Z_ORDER,
							String.valueOf(defaultPosition.getAuxiliarWindow()
									.getZ()))));

			InternalWindowPositionBean bottomWindow = storedPosition
					.getBottomWindow();

			bottomWindow.setX(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(BOTTOM_AREA_X_POSITION,
							String.valueOf(defaultPosition.getBottomWindow()
									.getX()))));
			bottomWindow.setY(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(BOTTOM_AREA_Y_POSITION,
							String.valueOf(defaultPosition.getBottomWindow()
									.getY()))));
			bottomWindow.setWidth(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(BOTTOM_AREA_WIDTH, String
							.valueOf(defaultPosition.getBottomWindow()
									.getWidth()))));
			bottomWindow.setHeight(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(BOTTOM_AREA_HEIGHT, String
							.valueOf(defaultPosition.getBottomWindow()
									.getHeight()))));
			bottomWindow
					.setZ(Integer.parseInt(SimpleProperties
							.getStringPropertyWithDefault(BOTTOM_AREA_Z_ORDER,
									String.valueOf(defaultPosition
											.getBottomWindow().getZ()))));

			InternalWindowPositionBean controlWindow = storedPosition
					.getControlWindow();

			controlWindow.setX(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(CONTROL_AREA_X_POSITION,
							String.valueOf(defaultPosition.getControlWindow()
									.getX()))));
			controlWindow.setY(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(CONTROL_AREA_Y_POSITION,
							String.valueOf(defaultPosition.getControlWindow()
									.getY()))));
			controlWindow.setWidth(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(CONTROL_AREA_WIDTH, String
							.valueOf(defaultPosition.getControlWindow()
									.getWidth()))));
			controlWindow.setHeight(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(CONTROL_AREA_HEIGHT, String
							.valueOf(defaultPosition.getControlWindow()
									.getHeight()))));
			controlWindow
					.setZ(Integer.parseInt(SimpleProperties
							.getStringPropertyWithDefault(CONTROL_AREA_Z_ORDER,
									String.valueOf(defaultPosition
											.getControlWindow().getZ()))));

			InternalWindowPositionBean helpWindow = storedPosition
					.getHelpWindow();

			helpWindow.setX(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(HELP_AREA_X_POSITION, String
							.valueOf(defaultPosition.getHelpWindow().getX()))));
			helpWindow.setY(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(HELP_AREA_Y_POSITION, String
							.valueOf(defaultPosition.getHelpWindow().getY()))));
			helpWindow.setWidth(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(HELP_AREA_WIDTH,
							String.valueOf(defaultPosition.getHelpWindow()
									.getWidth()))));
			helpWindow.setHeight(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(HELP_AREA_HEIGHT, String
							.valueOf(defaultPosition.getHelpWindow()
									.getHeight()))));
			helpWindow.setZ(Integer.parseInt(SimpleProperties
					.getStringPropertyWithDefault(HELP_AREA_Z_ORDER, String
							.valueOf(defaultPosition.getHelpWindow().getZ()))));

		} catch (NumberFormatException e) {
			return defaultPosition;
		}

		return storedPosition;
	}

	/**
	 * Save the positions of the windows.
	 * 
	 * @param position
	 *            The window position bean.
	 */
	static void saveInternalWindowsPosition(
			FilterAnyInternalWindowsPositionBeam position) {

		SimpleProperties.setStringProperty(WINDOW_POSITION_EXIST, YES);

		InternalWindowPositionBean filterWindow = position.getFiltersWindow();

		SimpleProperties.setStringProperty(FILTERS_X_POSITION,
				String.valueOf(filterWindow.getX()));
		SimpleProperties.setStringProperty(FILTERS_Y_POSITION,
				String.valueOf(filterWindow.getY()));
		SimpleProperties.setStringProperty(FILTERS_WIDTH,
				String.valueOf(filterWindow.getWidth()));
		SimpleProperties.setStringProperty(FILTERS_HEIGHT,
				String.valueOf(filterWindow.getHeight()));
		SimpleProperties.setStringProperty(FILTER_Z_ORDER,
				String.valueOf(filterWindow.getZ()));

		InternalWindowPositionBean topWindow = position.getTopWindow();

		SimpleProperties.setStringProperty(TOP_AREA_X_POSITION,
				String.valueOf(topWindow.getX()));
		SimpleProperties.setStringProperty(TOP_AREA_Y_POSITION,
				String.valueOf(topWindow.getY()));
		SimpleProperties.setStringProperty(TOP_AREA_WIDTH,
				String.valueOf(topWindow.getWidth()));
		SimpleProperties.setStringProperty(TOP_AREA_HEIGHT,
				String.valueOf(topWindow.getHeight()));
		SimpleProperties.setStringProperty(TOP_AREA_Z_ORDER,
				String.valueOf(topWindow.getZ()));

		InternalWindowPositionBean auxiliarWindow = position
				.getAuxiliarWindow();

		SimpleProperties.setStringProperty(AUXILIAR_AREA_X_POSITION,
				String.valueOf(auxiliarWindow.getX()));
		SimpleProperties.setStringProperty(AUXILIAR_AREA_Y_POSITION,
				String.valueOf(auxiliarWindow.getY()));
		SimpleProperties.setStringProperty(AUXILIAR_AREA_WIDTH,
				String.valueOf(auxiliarWindow.getWidth()));
		SimpleProperties.setStringProperty(AUXILIAR_AREA_HEIGHT,
				String.valueOf(auxiliarWindow.getHeight()));
		SimpleProperties.setStringProperty(AUXILIAR_AREA_Z_ORDER,
				String.valueOf(auxiliarWindow.getZ()));

		InternalWindowPositionBean bottomWindow = position.getBottomWindow();

		SimpleProperties.setStringProperty(BOTTOM_AREA_X_POSITION,
				String.valueOf(bottomWindow.getX()));
		SimpleProperties.setStringProperty(BOTTOM_AREA_Y_POSITION,
				String.valueOf(bottomWindow.getY()));
		SimpleProperties.setStringProperty(BOTTOM_AREA_WIDTH,
				String.valueOf(bottomWindow.getWidth()));
		SimpleProperties.setStringProperty(BOTTOM_AREA_HEIGHT,
				String.valueOf(bottomWindow.getHeight()));
		SimpleProperties.setStringProperty(BOTTOM_AREA_Z_ORDER,
				String.valueOf(bottomWindow.getZ()));

		InternalWindowPositionBean controlWindow = position.getControlWindow();

		SimpleProperties.setStringProperty(CONTROL_AREA_X_POSITION,
				String.valueOf(controlWindow.getX()));
		SimpleProperties.setStringProperty(CONTROL_AREA_Y_POSITION,
				String.valueOf(controlWindow.getY()));
		SimpleProperties.setStringProperty(CONTROL_AREA_WIDTH,
				String.valueOf(controlWindow.getWidth()));
		SimpleProperties.setStringProperty(CONTROL_AREA_HEIGHT,
				String.valueOf(controlWindow.getHeight()));
		SimpleProperties.setStringProperty(CONTROL_AREA_Z_ORDER,
				String.valueOf(controlWindow.getZ()));

		InternalWindowPositionBean helpWindow = position.getHelpWindow();

		SimpleProperties.setStringProperty(HELP_AREA_X_POSITION,
				String.valueOf(helpWindow.getX()));
		SimpleProperties.setStringProperty(HELP_AREA_Y_POSITION,
				String.valueOf(helpWindow.getY()));
		SimpleProperties.setStringProperty(HELP_AREA_WIDTH,
				String.valueOf(helpWindow.getWidth()));
		SimpleProperties.setStringProperty(HELP_AREA_HEIGHT,
				String.valueOf(helpWindow.getHeight()));
		SimpleProperties.setStringProperty(HELP_AREA_Z_ORDER,
				String.valueOf(helpWindow.getZ()));

	}
}
