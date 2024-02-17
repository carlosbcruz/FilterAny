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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Locale;

import com.carlosbcruz.filterany.UserLevelDialog;
import com.carlosbcruz.util.SimpleProperties;

/**
 * Manages the configuration of FilterAny application.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnyConfiguration {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	// Indicate if FilterAny is running in graphical mode.
	private static boolean runningInGraphicalMode = true;

	// Property files.
	private static final String PROPERTIES_FILE = "Properties.ini"; //$NON-NLS-1$
	private static final String PROPERTIES_FILE_HEADER = "FilterAny Properties"; //$NON-NLS-1$

	// Font for the calendar on the bottom of the window.
	private static Font calendarFont = new Font("Arial", Font.PLAIN, 10); //$NON-NLS-1$

	// Font for the site on the bottom of the window.
	private static Font siteFont = new Font("Arial", Font.PLAIN, 10); //$NON-NLS-1$

	// Some look and filtersWindow changes need to initialize the filter list.
	private static final String INITIALIZE_FILTER_LIST_KEY = "initialize_filter_list"; //$NON-NLS-1$
	private static final String INITIALIZE_FILTER_LIST = "yes"; //$NON-NLS-1$
	private static final String MAINTAIN_FILTER_LIST = "no"; //$NON-NLS-1$

	// Define the user level.
	private static final String USER_LEVEL_KEY = "user_level_key"; //$NON-NLS-1$
	private static final String USER_LEVEL_NOT_DEFINED = "user_level_no_defined"; //$NON-NLS-1$
	private static final String GENERAL_USER = "general_user"; //$NON-NLS-1$
	private static final String ADVANCED_TECHNICAL = "advanced_technical"; //$NON-NLS-1$
	private static String userLevel = new String();

	// Indicate which mode is the active
	private static boolean textMode = true;

	// Number of additional pixels to columns width.
	private static int additionalWidthToColumn = 16;

	// Path for the resources.
	public static final String RESOURCE_LOCATION = "resources/"; //$NON-NLS-1$
	public static final String IMAGE_RESOURCE_LOCATION = "resources/images/"; //$NON-NLS-1$

	// File to store the left tree.
	public static final String TREE_FILE = "Tree.ser"; //$NON-NLS-1$

	// File names to store the top, auxiliar and bottom memory.
	public static final String TOP_MEMORY_FILE = "TopMem.ser"; //$NON-NLS-1$
	public static final String AUXILIAR_MEMORY_FILE = "AuxMem.ser"; //$NON-NLS-1$
	public static final String BOTTOM_MEMORY_FILE = "BotMem.ser"; //$NON-NLS-1$

	// File names to store the editing areas.
	public static final String TOP_PERSIST_FILE = "TopPers.ser"; //$NON-NLS-1$
	public static final String AUXILIAR_PERSIST_FILE = "AuxPers.ser"; //$NON-NLS-1$
	public static final String BOTTOM_PERSIST_FILE = "BotPers.ser"; //$NON-NLS-1$

	// File to store the fields content by filter.
	public static final String FIELDS_MEMORY_FILE = "Fields.ser"; //$NON-NLS-1$

	// Indicate if the filter generates DOS format or Unix format.
	private static boolean generateTextInDOSFormat = true;

	// Singleton
	private static FilterAnyConfiguration configuration = null;

	// Element of the docking frames.
	public static final String DOCKING_FRAME_ELEMENT = "filterany"; //$NON-NLS-1$
	// File to store the docking frames layout.
	public static final String DOCKING_FRAME_FILE = "Frames.xml"; //$NON-NLS-1$

	// Cpl251
	// Filter mime type.
	private static String mimeType = "UTF-8"; //$NON-NLS-1$

	// Default size of filter help window.
	private static final int filterHelpShellWidth = 500;
	private static final int filterHelpShellHeight = 500;

	// Default size of FilterAny help window.
	private static final int filterAnyHelpWidth = 600;
	private static final int filterAnyHelpHeight = 600;

	// Color used on the filter help background.
	private static final Color filterHelpBackgroundColor = new Color(248, 248,
			248);

	// Pertist the caret positions.
	private static final String CARET_POSITION_MAIN_INPUT = "caret.main"; //$NON-NLS-1$
	private static final String CARET_POSITION_AUXILIAR_INPUT = "caret.auxiliar"; //$NON-NLS-1$
	private static final String CARET_POSITION_OUTPUT_INPUT = "caret.output"; //$NON-NLS-1$

	// Indicate if the sound is enabled.
	private static boolean soundEnable = true;

	// Position of scrap book position.
	private static final String SCRAP_BOOP_POSITION = "book.position"; //$NON-NLS-1$

	/**
	 * Initialize the internal content.
	 * 
	 * @param runningInGraphicalModeParameter
	 *            True if the FilterAny is running on graphical mode and can
	 *            persist the configuration.
	 */
	@SuppressWarnings("unused")
	public static void initialize(boolean runningInGraphicalModeParameter) {

		runningInGraphicalMode = runningInGraphicalModeParameter;

		SimpleProperties.setPersistProperties(runningInGraphicalMode);

		// Configure the properties file.
		SimpleProperties.setPropertiesFileName(PROPERTIES_FILE);
		SimpleProperties.setPropertiesFileHeader(PROPERTIES_FILE_HEADER);

		FilterAnyConfigurationLanguage.initialize();

		// Verify if the user is general or advanced.
		if (!isUserLevelDefined() && runningInGraphicalMode) {
			new UserLevelDialog();
		}

		// Initialize graphical structures only when using graphical mode.
		if (runningInGraphicalMode) {

			FilterAnyConfigurationAutomaticMode.initialize();

			FilterAnyConfigurationAutomaticHelp.initialize();

			FilterAnyConfigurationStructure.initialize();

			FilterAnyConfigurationSplitStructure.initialize();

			FilterAnyConfigurationWindowPosition.initialize();

			FilterAnyConfigurationTreeLine.initialize();

			FilterAnyConfigurationTheme.initialize();

			FilterAnyConfigurationDockingTheme.initialize();

			FilterAnyConfigurationSaveFormat.initialize();

			FilterAnyConfigurationSaveTextAreas.initialize();

			FilterAnyConfigurationTextFields.initialize();

			FilterAnyConfigurationEncoding.initialize();
			
			FilterAnyConfigurationJDialogStatus.initialize();
		}
	}

	/**
	 * Indicate if the FilterAny is running in graphical mode.
	 * 
	 * @return the runningInGraphicalMode True if the FilterAny is running in
	 *         graphical mode.
	 */
	public static boolean isRunningInGraphicalMode() {

		return runningInGraphicalMode;
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
	public static FilterAnyInternalWindowsPositionBeam getWindowsPosition(
			int internalDesktopWidth, int internalDesktopHeight,
			FilterAnyInternalWindowPositionLayouts layout) {

		return FilterAnyConfigurationInternalWindowPosition.getWindowsPosition(
				internalDesktopWidth, internalDesktopHeight, layout);
	}

	/**
	 * Provide the layout of the windows according to the desktop size.
	 * 
	 * @param internalDesktopWidth
	 *            The width of the desktop.
	 * @param internalDesktopHeight
	 *            The height of the desktop.
	 * @return The structure with all window positions.
	 */
	public static FilterAnyInternalWindowsPositionBeam loadWindowsPosition(
			int internalDesktopWidth, int internalDesktopHeight) {

		return FilterAnyConfigurationInternalWindowPosition
				.loadWindowsPosition(internalDesktopWidth,
						internalDesktopHeight);
	}

	/**
	 * Save the positions of the windows.
	 * 
	 * @param position
	 *            The window position bean.
	 */
	public static void saveInternalWindowsPosition(
			FilterAnyInternalWindowsPositionBeam position) {

		FilterAnyConfigurationInternalWindowPosition
				.saveInternalWindowsPosition(position);

	}

	/**
	 * Singleton of FilterAnyConfiguration.
	 * 
	 * @return The instance of FilterAnyConfiguration.
	 */
	public static FilterAnyConfiguration getInstance() {

		if (configuration == null) {
			configuration = new FilterAnyConfiguration();
		}

		return configuration;
	}

	/**
	 * Indicate if it is required to initialize the filter list.
	 * 
	 * @return True if it is required to initialize.
	 */
	public static boolean isInitializeFilterList() {

		String initializeConfiguration = SimpleProperties
				.getStringPropertyWithDefault(INITIALIZE_FILTER_LIST_KEY,
						MAINTAIN_FILTER_LIST);

		return INITIALIZE_FILTER_LIST.equals(initializeConfiguration);
	}

	/**
	 * Set if it is necessary to restart the filter list or not.
	 * 
	 * @param flag
	 *            True if it is necessary to restart the filter list.
	 */
	public static void setInitializeFilterList(boolean flag) {

		SimpleProperties.setStringProperty(INITIALIZE_FILTER_LIST_KEY,
				flag ? INITIALIZE_FILTER_LIST : MAINTAIN_FILTER_LIST);
	}

	/**
	 * Set the filtersWindow should generate DOS format or Unix format.
	 * 
	 * @param generateTextInDOSFormat
	 *            the generateTextInDOSFormat to set The filtersWindow should
	 *            generate DOS format or Unix format.
	 */
	public static void setGenerateTextInDOSFormat(
			boolean generateTextInDOSFormat) {

		FilterAnyConfiguration.generateTextInDOSFormat = generateTextInDOSFormat;
	}

	/**
	 * Indicate if the filtersWindow should generate DOS format or Unix format.
	 * 
	 * @return the generateTextInDOSFormat true if it has to generate DOS
	 *         format.
	 */
	public static boolean isGenerateTextInDOSFormat() {

		return generateTextInDOSFormat;
	}

	/**
	 * Inform the window x position.
	 * 
	 * @return the windowPositionX The window x position.
	 */
	public static int getWindowPositionX() {

		return FilterAnyConfigurationWindowPosition.getWindowPositionX();
	}

	/**
	 * Store the current window x position.
	 * 
	 * @param windowPositionX
	 *            the windowPositionX to set The current window x position.
	 */
	public static void setWindowPositionX(int windowPositionX) {

		FilterAnyConfigurationWindowPosition
				.setWindowPositionX(windowPositionX);
	}

	/**
	 * Inform the window Y position.
	 * 
	 * @return the windowPositionY The window Y position.
	 */
	public static int getWindowPositionY() {

		return FilterAnyConfigurationWindowPosition.getWindowPositionY();
	}

	/**
	 * Store the current window y position.
	 * 
	 * @param windowPositionY
	 *            the windowPositionY to set The current window y position.
	 */
	public static void setWindowPositionY(int windowPositionY) {

		FilterAnyConfigurationWindowPosition
				.setWindowPositionY(windowPositionY);
	}

	/**
	 * Inform the window width.
	 * 
	 * @return the windowWidth The window width.
	 */
	public static int getWindowWidth() {

		return FilterAnyConfigurationWindowPosition.getWindowWidth();
	}

	/**
	 * Store the window width.
	 * 
	 * @param windowWidth
	 *            the windowWidth to set The window width.
	 */
	public static void setWindowWidth(int windowWidth) {

		FilterAnyConfigurationWindowPosition.setWindowWidth(windowWidth);
	}

	/**
	 * Inform the window height.
	 * 
	 * @return the windowHeight The window height.
	 */
	public static int getWindowHeight() {

		return FilterAnyConfigurationWindowPosition.getWindowHeight();
	}

	/**
	 * Store the window height.
	 * 
	 * @param windowHeight
	 *            the windowHeight to set The window height.
	 */
	public static void setWindowHeight(int windowHeight) {

		FilterAnyConfigurationWindowPosition.setWindowHeight(windowHeight);
	}

	/**
	 * Inform the controls split location.
	 * 
	 * @return the controlsSplitterLocation The controls split location.
	 */
	public static int getControlsSplitterLocation() {

		return FilterAnyConfigurationWindowPosition
				.getControlsSplitterLocation();
	}

	/**
	 * Store the controls split location.
	 * 
	 * @param controlsSplitterLocation
	 *            the controlsSplitterLocation to set The controls split
	 *            location.
	 */
	public static void setControlsSplitterLocation(int verticalSplitterLocation) {

		FilterAnyConfigurationWindowPosition
				.setControlsSplitterLocation(verticalSplitterLocation);

	}

	/**
	 * Inform the horizontal splitter location.
	 * 
	 * @return The the text areas splitter location.
	 */
	public static int getTextAreasSplitterLocation() {

		return FilterAnyConfigurationWindowPosition
				.getTextAreasSplitterLocation();
	}

	/**
	 * Store the text areas splitter location.
	 * 
	 * @param horizontalSplitterLocation
	 *            The text areas splitter location.
	 */
	public static void setTextAreasSplitterLocation(
			int horizontalSplitterLocation) {

		FilterAnyConfigurationWindowPosition
				.setTextAreasSplitterLocation(horizontalSplitterLocation);
	}

	/**
	 * Inform the input splitter location.
	 * 
	 * @return The the input text areas splitter location.
	 */
	public static int getInputAreasSplitterLocation() {

		return FilterAnyConfigurationWindowPosition
				.getInputAreaSplitterLocation();
	}

	/**
	 * Store the text areas splitter location.
	 * 
	 * @param inputSplitterLocation
	 *            The input text areas splitter location.
	 */
	public static void setInputAreasSplitterLocation(int inputSplitterLocation) {

		FilterAnyConfigurationWindowPosition
				.setInputAreaSplitterLocation(inputSplitterLocation);
	}

	/**
	 * Provide the calendar font.
	 * 
	 * @return the calendarFont The calendar font.
	 */
	public static Font getCalendarFont() {

		return calendarFont;
	}

	/**
	 * Provide the site font.
	 * 
	 * @return The site font
	 */
	public static Font getSiteFont() {

		return siteFont;
	}

	/**
	 * Inform the selected locale.
	 * 
	 * @return the currentLocale The selected locale.
	 */
	public static Locale getCurrentLocale() {

		return FilterAnyConfigurationLanguage.getCurrentLocale();
	}

	/**
	 * Indicate which is the current mode if text mode or file mode.
	 * 
	 * @return the textMode The mode.
	 */
	public static boolean isTextMode() {
		return textMode;
	}

	/**
	 * Set the current mode if the current mode if text mode or file mode.
	 * 
	 * @param textMode
	 *            the textMode to set true if the current mode is text and false
	 *            otherwise..
	 */
	public static void setTextMode(boolean textMode) {
		FilterAnyConfiguration.textMode = textMode;
	}

	/**
	 * Inform the number of additional pixels to column width
	 * 
	 * @return the additionalWidthToColumn
	 */
	public static int getAdditionalWidthToColumn() {
		return additionalWidthToColumn;
	}

	/**
	 * Indicate if the text area split is horizontal or vertical.
	 * 
	 * @return the useVerticalTextSplit
	 */
	public static boolean isUseVerticalTextSplit() {

		return FilterAnyConfigurationSplitStructure.isUseVerticalTextSplit();
	}

	/**
	 * Indicate if the controls are on the right or not.
	 * 
	 * @return the controlsAreOnTheRight true if the controls are on the right.
	 */
	public static boolean isControlsAreOnTheRight() {

		return FilterAnyConfigurationSplitStructure.isControlsAreOnTheRight();
	}

	/**
	 * Inform the current configuration is to use internal windows.
	 * 
	 * @return true if current configuration is to use internal windows.
	 */
	public static boolean isUseInternalWindow() {

		return FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.INTERNAL_WINDOW == FilterAnyConfigurationStructure
				.getCurrentStructure();
	}

	/**
	 * Inform the current configuration is to use splitters.
	 * 
	 * @return true if current configuration is to use splitters.
	 */
	public static boolean isUseSplitters() {

		return FilterAnyConfigurationStructure.INTERNAL_STRUCTURE.SPLITERS == FilterAnyConfigurationStructure
				.getCurrentStructure();
	}

	/**
	 * Return the text area font.
	 * 
	 * @return the font
	 */
	public static Font getTextAreaFont() {

		return new Font("Lucida Console", Font.PLAIN, 11); //$NON-NLS-1$
	}

	/**
	 * Get the dimension of small buttons.
	 * 
	 * @return The small buttons dimension.
	 */
	public static Dimension getSmallButtonsDimension() {

		return new Dimension(20, 20);
	}

	/**
	 * Indicate that the filter list has to be restarted.
	 */
	public static void setInitializeFilterListRequired() {

		// Initialize the filter list on the next FilterAny
		// initialization.
		SimpleProperties.setStringProperty(INITIALIZE_FILTER_LIST_KEY,
				INITIALIZE_FILTER_LIST);
	}

	/**
	 * Inform the current tree line style.
	 * 
	 * @return the treeLineStyle The tree line style.
	 */
	public static String getTreeLineStyle() {

		return FilterAnyConfigurationTreeLine.getTreeLineStyle();
	}

	/**
	 * Indicate if the automatic mode is turned on.
	 * 
	 * @return the automaticMode true if the automatic mode is turned on.
	 */
	public static boolean isAutomaticMode() {

		return FilterAnyConfigurationAutomaticMode.isAutomaticMode();
	}

	/**
	 * Set the automatic mode on and off.
	 * 
	 * @param automaticMode
	 *            the automaticMode to set
	 */
	public static void setAutomaticMode(boolean automaticMode) {

		FilterAnyConfigurationAutomaticMode.setAutomaticMode(automaticMode);
	}

	/**
	 * Inform the mime time of the FilterAny.
	 * 
	 * @return the helpMimeType The mime type of the FilterAny.
	 */
	public static String getMimeType() {

		return mimeType;
	}

	/**
	 * Width for the filter help.
	 * 
	 * @return the filterHelpShellWidth
	 */
	public static int getFilterHelpShellWidth() {

		return filterHelpShellWidth;
	}

	/**
	 * Height for the filter help.
	 * 
	 * @return the filterHelpShellHeight
	 */
	public static int getFilterHelpShellHeight() {

		return filterHelpShellHeight;
	}

	/**
	 * Color used on the filter help.
	 * 
	 * @return the filterHelpBackgroundColor
	 */
	public static Color getFilterHelpBackgroundColor() {
		return filterHelpBackgroundColor;
	}

	/**
	 * Width for the FilterAny help.
	 * 
	 * @return the filterAnyHelpWidth
	 */
	public static int getFilterAnyHelpWidth() {
		return filterAnyHelpWidth;
	}

	/**
	 * Height for the FilterAny help.
	 * 
	 * @return the filterAnyHelpHeight
	 */
	public static int getFilterAnyHelpHeight() {
		return filterAnyHelpHeight;
	}

	/**
	 * Indicate if sound is enabled.
	 * 
	 * @return the soundEnable True if sound is enabled.
	 */
	public static boolean isSoundEnable() {

		return soundEnable;
	}

	/**
	 * Indicate if the automatic mode it turned on or off.
	 * 
	 * @return the automaticHelp
	 */
	public static boolean isAutomaticHelp() {

		return FilterAnyConfigurationAutomaticHelp.isAutomaticHelp();
	}

	/**
	 * Set if the automatic mode it turned on or off.
	 * 
	 * @param automaticHelp
	 *            the automaticHelp to set
	 */
	public static void setAutomaticHelp(boolean automaticHelp) {

		FilterAnyConfigurationAutomaticHelp.setAutomaticHelp(automaticHelp);
	}

	/**
	 * Indicate if the user level is defined.
	 * 
	 * @return true if the user level is defined.
	 */
	private static boolean isUserLevelDefined() {

		userLevel = SimpleProperties.getStringPropertyWithDefault(
				USER_LEVEL_KEY, USER_LEVEL_NOT_DEFINED);

		return (GENERAL_USER.equals(userLevel) || ADVANCED_TECHNICAL
				.equals(userLevel));

	}

	/**
	 * Indicate if the user is general level.
	 * 
	 * @return true if the user is general level.
	 */
	public static boolean isGeneralLevelUser() {

		if ("".equals(userLevel)) { //$NON-NLS-1$

			userLevel = SimpleProperties.getStringPropertyWithDefault(
					USER_LEVEL_KEY, USER_LEVEL_NOT_DEFINED);
		}

		return GENERAL_USER.equals(userLevel);
	}

	/**
	 * Indicate that the uesr is general level user.
	 */
	public static void setGeneralUser() {

		userLevel = GENERAL_USER;
		SimpleProperties.setStringProperty(USER_LEVEL_KEY, GENERAL_USER);
	}

	/**
	 * Indicate that the uesr is an advanced level user.
	 */
	public static void setAdvancedUser() {

		userLevel = ADVANCED_TECHNICAL;
		SimpleProperties.setStringProperty(USER_LEVEL_KEY, ADVANCED_TECHNICAL);
	}

	/**
	 * Indicate if the save style is in warn mode.
	 * 
	 * @return true if the save style is in warn mode.
	 */
	public static boolean isSaveInWarnMode() {

		return FilterAnyConfigurationSaveTextAreas.getSaveStyleType() == FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.WARN;
	}

	/**
	 * Indicate if the save style is in persist mode.
	 * 
	 * @return true if the save style is in persist mode.
	 */
	public static boolean isSaveInPersistMode() {

		return FilterAnyConfigurationSaveTextAreas.getSaveStyleType() == FilterAnyConfigurationSaveTextAreas.SAVE_STYLES.PERSIST;
	}

	/**
	 * Inform the auto clean up configuration.
	 * 
	 * @return the autoCleanupBehavior
	 */
	public static boolean isAutoCleanupBehavior() {

		return FilterAnyConfigurationTextFields.isAutoCleanupBehavior();
	}

	/**
	 * Inform the clear fields button configuration.
	 * 
	 * @return the clearFieldsButton
	 */
	public static boolean isClearFieldsButton() {

		return FilterAnyConfigurationTextFields.isClearFieldsButton();
	}

	/**
	 * Indicate the caret position for the main input.
	 * 
	 * @return the caret position.
	 */
	public static int getCaretPositionForMainInput() {

		String caretPosition = SimpleProperties.getStringPropertyWithDefault(
				CARET_POSITION_MAIN_INPUT, "0"); //$NON-NLS-1$

		int caret = 0;
		try {
			caret = Integer.parseInt(caretPosition);
		} catch (NumberFormatException e) {
			// Ignore.
		}
		return caret;
	}

	/**
	 * Indicate the caret position for the auxiliar input.
	 * 
	 * @return the caret position.
	 */
	public static int getCaretPositionForAuxiliarInput() {

		String caretPosition = SimpleProperties.getStringPropertyWithDefault(
				CARET_POSITION_AUXILIAR_INPUT, "0"); //$NON-NLS-1$

		int caret = 0;
		try {
			caret = Integer.parseInt(caretPosition);
		} catch (NumberFormatException e) {
			// Ignore.
		}
		return caret;
	}

	/**
	 * Indicate the caret position for the output input.
	 * 
	 * @return the caret position.
	 */
	public static int getCaretPositionForOutputInput() {

		String caretPosition = SimpleProperties.getStringPropertyWithDefault(
				CARET_POSITION_OUTPUT_INPUT, "0"); //$NON-NLS-1$

		int caret = 0;
		try {
			caret = Integer.parseInt(caretPosition);
		} catch (NumberFormatException e) {
			// Ignore.
		}
		return caret;
	}

	/**
	 * Persist the caret position for the main input.
	 * 
	 * @param caret
	 *            The caret position.
	 */
	public static void setCaretPositionForMainInput(int caret) {

		SimpleProperties.setStringProperty(CARET_POSITION_MAIN_INPUT,
				String.valueOf(caret));
	}

	/**
	 * Persist the caret position for the auxiliar input.
	 * 
	 * @param caret
	 *            The caret position.
	 */
	public static void setCaretPositionForAuxiliarInput(int caret) {

		SimpleProperties.setStringProperty(CARET_POSITION_AUXILIAR_INPUT,
				String.valueOf(caret));
	}

	/**
	 * Persist the caret position for the output input.
	 * 
	 * @param caret
	 *            The caret position.
	 */
	public static void setCaretPositionForOutputInput(int caret) {

		SimpleProperties.setStringProperty(CARET_POSITION_OUTPUT_INPUT,
				String.valueOf(caret));
	}

	/**
	 * Inform if smooth theme is used.
	 * 
	 * @return If smooth theme is used.
	 */
	public static boolean isSmoothThemeActivated() {

		return FilterAnyConfigurationDockingTheme
				.getCurrentThemeConfiguration() == FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.SMOOTH;
	}

	/**
	 * Inform if basic theme is used.
	 * 
	 * @return If basic theme is used.
	 */
	public static boolean isBasicThemeActivated() {

		return FilterAnyConfigurationDockingTheme
				.getCurrentThemeConfiguration() == FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.BASIC;
	}

	/**
	 * Inform if flat theme is used.
	 * 
	 * @return If flat theme is used.
	 */
	public static boolean isFlatThemeActivated() {

		return FilterAnyConfigurationDockingTheme
				.getCurrentThemeConfiguration() == FilterAnyConfigurationDockingTheme.THEME_CONFIGURATION.FLAT;
	}

	/**
	 * Inform if it is required to used no stack.
	 * 
	 * @return the no stack configuration.
	 */
	public static boolean useNoStackTheme() {

		return FilterAnyConfigurationDockingTheme.useNoStackTheme();
	}

	/**
	 * Inform the scrap book current caret position.
	 * 
	 * @return Inform the scrap book current caret position.
	 */
	public static String getScrapBookPosition() {

		return SimpleProperties.getStringPropertyWithDefault(
				SCRAP_BOOP_POSITION, "0"); //$NON-NLS-1$
	}

	/**
	 * Set the scrap book current caret position.
	 * 
	 * @param position
	 *            the scrap book current caret position.
	 */
	public static void setScrapBookPosition(int position) {

		SimpleProperties.setStringProperty(SCRAP_BOOP_POSITION,
				String.valueOf(position));
	}

	/**
	 * Indicate if this version is running a demonstration or not.
	 * 
	 * @return true if this version is running a demonstration.
	 */
	public static boolean isRunningADemonstrationVersion() {

		return false;
	}

	/**
	 * Inform if the file being save should be in DOS format.
	 * 
	 * @return true if the file being save should be in DOS format.
	 */
	public static boolean isSaveInDOSFormat() {

		return FilterAnyConfigurationSaveFormat.getSaveFormat() == FilterAnyConfigurationSaveFormat.SAVE_FORMATS.DOS;
	}

}
