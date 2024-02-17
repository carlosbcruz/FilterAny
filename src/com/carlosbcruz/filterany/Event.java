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

package com.carlosbcruz.filterany;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SimpleActionDecorator;
import com.carlosbcruz.util.SimpleActionProvider;
import com.carlosbcruz.util.SwingUtil;

/**
 * Provide the menu structure to the text utility application.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
@SuppressWarnings("nls")
public class Event {

	// Possible events actions.

	private static SimpleActionDecorator fileAction;
	private static SimpleActionDecorator exitAction;

	private static SimpleActionDecorator optionAction;
	private static SimpleActionDecorator configurationAction;

	private static SimpleActionDecorator helpAction;
	private static SimpleActionDecorator contentAction;
	private static SimpleActionDecorator shortCutAction;

	private static SimpleActionDecorator instructionAction;
	private static SimpleActionDecorator exampleAction;
	private static SimpleActionDecorator executeAction;
	private static SimpleActionDecorator automaticExecuteAction;

	private static SimpleActionDecorator filterTarget_1_ButtomAction;
	private static SimpleActionDecorator filterTarget_Directory_ButtomAction;

	private static SimpleActionDecorator toolbarSwitchBlueRedAction;
	private static SimpleActionDecorator toolbarSwitchBlueGreenAction;
	private static SimpleActionDecorator toolbarSwitchGreenRedAction;
	private static SimpleActionDecorator toolbarSaveTopAsAction;
	private static SimpleActionDecorator toolbarSaveAuxiliarAsAction;
	private static SimpleActionDecorator toolbarSaveBottomAsAction;
	private static SimpleActionDecorator toolbarUpdateAction;
	private static SimpleActionDecorator toolbarNewLineAction;
	private static SimpleActionDecorator toolbarNewLineTopAction;
	private static SimpleActionDecorator toolbarNewLineAuxiliarAction;
	private static SimpleActionDecorator toolbarNewLineBottomAction;
	private static SimpleActionDecorator toolbarSwitchBetweenTextAndFile;
	private static SimpleActionDecorator toolbarSwitchBetweenTextAndFileTurnedOn;

	private static SimpleActionDecorator previewConfirmButtonAction;
	private static SimpleActionDecorator previewCancelButtonAction;

	private static SimpleActionDecorator configurationOkButtonAction;
	private static SimpleActionDecorator configurationCancelButtonAction;

	private static SimpleActionDecorator fileSelectionButtonAction;

	private static SimpleActionDecorator directorySelectionButtonAction;

	private static SimpleActionDecorator defaultPositionAction;
	private static SimpleActionDecorator verticalAreasAction;
	private static SimpleActionDecorator controlOnTheMiddleAction;
	private static SimpleActionDecorator controlInTheRightVerticalAction;
	private static SimpleActionDecorator controlInTheRightAction;

	private static SimpleActionDecorator toolBarAutomaticModeActionTurnedOff;
	private static SimpleActionDecorator toolBarAutomaticModeActionTurnedOn;

	private static SimpleActionDecorator toolBarMaximizeAction;
	private static SimpleActionDecorator toolBarRestoreAction;

	private static SimpleActionDecorator automaticHelpModeActionTurnedOff;
	private static SimpleActionDecorator automaticHelpModeActionTurnedOn;

	private static SimpleActionDecorator userLevelDialogSelectionAction;

	private static SimpleActionDecorator textAreaFindAndReplaceMainAction;
	private static SimpleActionDecorator textAreaFindAndReplaceOutputAction;
	private static SimpleActionDecorator textAreaFindAndReplaceAuxiliarAction;
	private static SimpleActionDecorator textAreaFindAndReplaceScrapbookAction;

	private static SimpleActionDecorator textAreaFindAndReplaceMainFormattedAction;
	private static SimpleActionDecorator textAreaFindAndReplaceOutputFormattedAction;
	private static SimpleActionDecorator textAreaFindAndReplaceAuxiliarFormattedAction;
	private static SimpleActionDecorator textAreaFindAndReplaceScrapBookFormattedAction;

	private static SimpleActionDecorator findAction;

	private static SimpleActionDecorator compareAction;

	private static SimpleActionDecorator compareMainWithOutputAction;
	private static SimpleActionDecorator compareMainWithAuxiliarAction;
	private static SimpleActionDecorator compareAuxiliarWithOutputAction;

	private static SimpleActionDecorator clearFieldAction;

	private static SimpleActionDecorator memory1Action;
	private static SimpleActionDecorator memory2Action;
	private static SimpleActionDecorator memory3Action;
	private static SimpleActionDecorator memory4Action;
	private static SimpleActionDecorator memory5Action;
	private static SimpleActionDecorator clearMemoryAction;

	private static SimpleActionDecorator scrapBookAction;

	private static SimpleActionDecorator positionAction;
	private static SimpleActionDecorator aboutAction;

	private static SimpleActionDecorator createRegistrationAction;
	private static SimpleActionDecorator cancelRegistrationAction;

	private static SimpleActionDecorator registerButtonAction;

	private static SimpleActionDecorator acceptAgreementAction;
	private static SimpleActionDecorator notAcceptAction;

	private static SimpleActionDecorator licenseAction;

	private static SimpleActionDecorator copyFromMainAction;
	private static SimpleActionDecorator cutFromMainAction;
	private static SimpleActionDecorator pasteToMainAction;

	private static SimpleActionDecorator copyFromAuxiliarAction;
	private static SimpleActionDecorator cutFromAuxiliarAction;
	private static SimpleActionDecorator pasteToAuxiliarAction;

	private static SimpleActionDecorator copyFromOutputAction;
	private static SimpleActionDecorator cutFromOutputAction;
	private static SimpleActionDecorator pasteToOutputAction;

	private static SimpleActionDecorator taskManagerAction;

	private static SimpleActionDecorator networkAction;

	private static SimpleActionDecorator networkGetFromMainAction;
	private static SimpleActionDecorator networkGetFromOutputAction;
	private static SimpleActionDecorator networkGetFromAuxiliarAction;
	private static SimpleActionDecorator networkAddFileAction;

	private static SimpleActionDecorator searchForAFilterAction;

	private static SimpleActionDecorator garbageAction;

	private static SimpleActionDecorator commandLineGeneratorAction;

	private static SimpleActionDecorator viewAction;

	private static SimpleActionDecorator goToLineRedAction;
	private static SimpleActionDecorator goToLineBlueAction;
	private static SimpleActionDecorator goToLineGreenAction;

	private static SimpleActionDecorator goToLineRedFormattedEditorAction;
	private static SimpleActionDecorator goToLineBlueFormattedEditorAction;
	private static SimpleActionDecorator goToLineGreenFormattedEditorAction;

	static {

		// Initialize event actions.
		fileAction = SimpleActionProvider.getSimpleAction(null,
				Text.get(Text.FILE),
				SwingUtil.getKeyEvent(Text.get(Text.FILE_KEY)),
				Text.get(Text.FILE_INSTRUCTION));
		exitAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "exit-icon-small.png", Text.get(Text.EXIT),
				SwingUtil.getKeyEvent(Text.get(Text.EXIT_KEY)),
				Text.get(Text.EXIT_INSTRUCTION));
		optionAction = SimpleActionProvider.getSimpleAction(null,
				Text.get(Text.OPTION),
				SwingUtil.getKeyEvent(Text.get(Text.OPTION_KEY)),
				Text.get(Text.OPTION_INSTRUCTION));
		configurationAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "properties-small.png", Text.get(Text.CONFIGURATION),
				SwingUtil.getKeyEvent(Text.get(Text.CONFIGURATION_KEY)),
				Text.get(Text.CONFIGURATION_INSTRUCTION));
		helpAction = SimpleActionProvider.getSimpleAction(null,
				Text.get(Text.HELP),
				SwingUtil.getKeyEvent(Text.get(Text.HELP_KEY)),
				Text.get(Text.HELP_INSTRUCTION));
		shortCutAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "shorcut-icon.png",
				Text.get(Text.SHORTCUT),
				SwingUtil.getKeyEvent(Text.get(Text.SHORTCUT_KEY)),
				Text.get(Text.SHORTCUT_TOOLTIP));
		contentAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "button-help-icon.png", Text.get(Text.HELP_CONTENT),
				SwingUtil.getKeyEvent(Text.get(Text.HELP_CONTENT_KEY)),
				Text.get(Text.HELP_CONTENT_INSTRUCTION));
		instructionAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "help-icon-small.png", Text.get(Text.FILTER_HELP),
				SwingUtil.getKeyEvent(Text.get(Text.FILTER_HELP_KEY)),
				Text.get(Text.FILTER_HELP_INSTRUCTION));
		exampleAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "document-icon.png",
				Text.get(Text.FILTER_EXAMPLE),
				SwingUtil.getKeyEvent(Text.get(Text.FILTER_EXAMPLE_KEY)),
				Text.get(Text.FILTER_EXAMPLE_INSTRUCTION));
		executeAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "execute-icon-small.png", Text.get(Text.EXECUTE),
				SwingUtil.getKeyEvent(Text.get(Text.EXECUTE_KEY)),
				Text.get(Text.EXECUTE_INSTRUCTION));
		automaticExecuteAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "execute-icon-small.png",
				Text.get(Text.AUTOMATIC_EXECUTION_MODE_ON), 0,
				Text.get(Text.AUTOMATIC_EXECUTION_MODE_ON_INSTRUCTIONS));
		filterTarget_1_ButtomAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "search-folder-icon.png",
				Text.get(Text.TARGET_1_BUTTON_LABEL),
				SwingUtil.getKeyEvent(Text.get(Text.TARGET_1_BUTTON_KEY)),
				Text.get(Text.TARGET_1_BUTTON_INSTRUCTIONS));
		filterTarget_Directory_ButtomAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "target-icon.png", Text
						.get(Text.TARGET_DIRECTORY_BUTTON_LABEL),
						SwingUtil.getKeyEvent(Text
								.get(Text.TARGET_DIRECTORY_BUTTON_KEY)), Text
								.get(Text.TARGET_DIRECTORY_BUTTON_INSTRUCTIONS));
		toolbarSwitchBlueRedAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "Blue_Red_reload-icon-small.png", Text
						.get(Text.TOOLBAR_SWITCH_MAIN_OUTPUT), SwingUtil
						.getKeyEvent(Text
								.get(Text.TOOLBAR_SWITCH_MAIN_OUTPUT_KEY)),
				Text.get(Text.TOOLBAR_SWITCH_MAIN_OUTPUT_INSTRUCTION));

		toolbarSwitchBlueGreenAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "Blue_Green_reload-icon-small.png",
				Text.get(Text.TOOLBAR_SWITCH_MAIN_AUXILIAR), 0,
				Text.get(Text.TOOLBAR_SWITCH_MAIN_AUXILIAR_INSTRUCTION));

		toolbarSwitchGreenRedAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "Red_Green_reload-icon-small.png",
				Text.get(Text.TOOLBAR_SWITCH_AUXILIAR_OUTPUT), 0,
				Text.get(Text.TOOLBAR_SWITCH_AUXILIAR_OUTPUT_INSTRUCTION));

		toolbarSaveTopAsAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "floppy-icon-blue-small.png",
				Text.get(Text.TOOLBAR_SAVETOPAS),
				SwingUtil.getKeyEvent(Text.get(Text.TOOLBAR_SAVETOPAS_KEY)),
				Text.get(Text.TOOLBAR_SAVETOPAS_INSTRUCTION));

		toolbarSaveAuxiliarAsAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "floppy-icon-green-small.png", Text
						.get(Text.TOOLBAR_AUXILIAR_SAVETOPAS), SwingUtil
						.getKeyEvent(Text
								.get(Text.TOOLBAR_AUXILIAR_SAVETOPAS_KEY)),
				Text.get(Text.TOOLBAR_AUXILIAR_SAVETOPAS_INSTRUCTION));

		toolbarSaveBottomAsAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "floppy-icon-red-small.png",
				Text.get(Text.TOOLBAR_SAVEBOTTOMAS),
				SwingUtil.getKeyEvent(Text.get(Text.TOOLBAR_SAVEBOTTOMAS_KEY)),
				Text.get(Text.TOOLBAR_SAVEBOTTOMAS_INSTRUCTION));

		toolbarUpdateAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "sign-select-icon-small.png", Text
						.get(Text.TOOLBAR_UPDATE_STATISTCS), SwingUtil
						.getKeyEvent(Text
								.get(Text.TOOLBAR_UPDATE_STATISTCS_KEY)), Text
						.get(Text.TOOLBAR_UPDATE_STATISTCS_INSTRUCTION));
		toolbarNewLineAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "new_line_icon.png",
				Text.get(Text.TOOLBAR_NEWLINE),
				SwingUtil.getKeyEvent(Text.get(Text.TOOLBAR_NEWLINE_KEY)),
				Text.get(Text.TOOLBAR_NEWLINE_INSTRUCTION));
		toolbarNewLineTopAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "new_line_icon.png",
				Text.get(Text.TOOLBAR_NEWLINETOP),
				SwingUtil.getKeyEvent(Text.get(Text.TOOLBAR_NEWLINETOP_KEY)),
				Text.get(Text.TOOLBAR_NEWLINETOP_INSTRUCTION));
		toolbarNewLineAuxiliarAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "new_line_icon.png",
				Text.get(Text.TOOLBAR_NEWLINETOP),
				SwingUtil.getKeyEvent(Text.get(Text.TOOLBAR_NEWLINETOP_KEY)),
				Text.get(Text.TOOLBAR_NEWLINETOP_INSTRUCTION));
		toolbarNewLineBottomAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "new_line_icon.png", Text
						.get(Text.TOOLBAR_NEWLINEBOTTOM), SwingUtil
						.getKeyEvent(Text.get(Text.TOOLBAR_NEWLINEBOTTOM_KEY)),
						Text.get(Text.TOOLBAR_NEWLINEBOTTOM_INSTRUCTION));
		toolbarSwitchBetweenTextAndFile = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "text-or-folder-icon.png",
						Text.get(Text.TOOLBAR_SWITCHBETWEENTEXTANDFILE),
						SwingUtil.getKeyEvent(Text
								.get(Text.TOOLBAR_SWITCHBETWEENTEXTANDFILE_KEY)),
						Text.get(Text.TOOLBAR_SWITCHBETWEENTEXTANDFILE_INSTRUCTION));
		toolbarSwitchBetweenTextAndFileTurnedOn = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "text-or-folder-turned-on-icon.png",
						Text.get(Text.TOOLBAR_SWITCHBETWEENTEXTANDFILE_ON),
						SwingUtil.getKeyEvent(Text
								.get(Text.TOOLBAR_SWITCHBETWEENTEXTANDFILE_ON_KEY)),
						Text.get(Text.TOOLBAR_SWITCHBETWEENTEXTANDFILE_ON_INSTRUCTION));

		previewConfirmButtonAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "ok-icon.png", Text.get(Text.PREVIEW_CONFIRM_BUTTOM),
						SwingUtil.getKeyEvent(Text
								.get(Text.PREVIEW_CONFIRM_BUTTOM_KEY)), Text
								.get(Text.PREVIEW_CONFIRM_BUTTOM_INSTRUCTIONS));

		previewCancelButtonAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "cancel-icon.png", Text
						.get(Text.PREVIEW_CANCEL_BUTTOM), SwingUtil
						.getKeyEvent(Text.get(Text.PREVIEW_CANCEL_BUTTOM_KEY)),
						Text.get(Text.PREVIEW_CANCEL_BUTTOM_INSTRUCTIONS));

		configurationOkButtonAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "ok-icon.png",
				Text.get(Text.CONFIGURATION_OK_BUTTOM),
				SwingUtil.getKeyEvent(Text.get(Text.CONFIGURATION_OK_KEY)),
				Text.get(Text.CONFIGURATION_OK_INSTRUCTIONS));
		configurationCancelButtonAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "cancel-icon.png",
				Text.get(Text.CONFIGURATION_CANCEL_BUTTOM),
				SwingUtil.getKeyEvent(Text.get(Text.CONFIGURATION_CANCEL_KEY)),
				Text.get(Text.CONFIGURATION_CANCEL_INSTRUCTIONS));

		fileSelectionButtonAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "file-blue-icon.png",
						Text.get(Text.CONFIGURATION_FILE_SELECTION_BUTTOM),
						SwingUtil.getKeyEvent(Text
								.get(Text.CONFIGURATION_FILE_SELECTION_KEY)),
						Text.get(Text.CONFIGURATION_FILE_SELECTION_INSTRUCTIONS));

		directorySelectionButtonAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "directory-icon.png",
						Text.get(Text.CONFIGURATION_DIRECTORY_SELECTION_BUTTOM),
						SwingUtil.getKeyEvent(Text
								.get(Text.CONFIGURATION_DIRECTORY_SELECTION_KEY)),
						Text.get(Text.CONFIGURATION_DIRECTORY_SELECTION_INSTRUCTIONS));

		defaultPositionAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "defaultPosition.png", Text
						.get(Text.DEFAULT_POSITION_ACTION_TITLE),
				SwingUtil.getKeyEvent(Text
						.get(Text.DEFAULT_POSITION_ACTION_KEY)), Text
						.get(Text.DEFAULT_POSITION_ACTION_INSTRUCTION));

		verticalAreasAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "verticalAreas.png", Text
						.get(Text.VERTICAL_AREAS_ACTION_TITLE), SwingUtil
						.getKeyEvent(Text.get(Text.VERTICAL_AREAS_ACTION_KEY)),
						Text.get(Text.VERTICAL_AREAS_ACTION_INSTRUCTION));

		controlOnTheMiddleAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "controlOnTheMiddle.png", Text
						.get(Text.CONTROL_ON_THE_MIDDLE_ACTION_TITLE),
				SwingUtil.getKeyEvent(Text
						.get(Text.CONTROL_ON_THE_MIDDLE_ACTION_KEY)), Text
						.get(Text.CONTROL_ON_THE_MIDDLE_ACTION_INSTRUCTION));

		controlInTheRightVerticalAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "controlInTheRightVertical.png",
						Text.get(Text.CONTROL_IN_THE_RIGHT_VERTICAL_ACTION_TITLE),
						SwingUtil.getKeyEvent(Text
								.get(Text.CONTROL_IN_THE_RIGHT_VERTICAL_ACTION_KEY)),
						Text.get(Text.CONTROL_IN_THE_RIGHT_VERTICAL_ACTION_INSTRUCTION));

		controlInTheRightAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "controlInTheRight.png", Text
						.get(Text.CONTROL_IN_THE_RIGHT_ACTION_TITLE), SwingUtil
						.getKeyEvent(Text
								.get(Text.CONTROL_IN_THE_RIGHT_ACTION_KEY)),
				Text.get(Text.CONTROL_IN_THE_RIGHT_ACTION_INSTRUCTION));

		toolBarAutomaticModeActionTurnedOff = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "airplane-icon.png", Text
						.get(Text.AUTOMATIC_MODE_OFF_ICON),
						SwingUtil.getKeyEvent(Text
								.get(Text.AUTOMATIC_MODE_OFF_ICON_KEY)), Text
								.get(Text.AUTOMATIC_MODE_OFF_ICON_INFORMATION));

		toolBarAutomaticModeActionTurnedOn = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "airplane-mode-on-icon.png", Text
						.get(Text.AUTOMATIC_MODE_ON_ICON),
						SwingUtil.getKeyEvent(Text
								.get(Text.AUTOMATIC_MODE_ON_ICON_KEY)), Text
								.get(Text.AUTOMATIC_MODE_ON_ICON_INFORMATION));

		toolBarMaximizeAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "maximize-icon.png",
				Text.get(Text.MAXIMIZE_ICON),
				SwingUtil.getKeyEvent(Text.get(Text.MAXIMIZE_ICON_KEY)),
				Text.get(Text.MAXIMIZE_ICON_INFORMATION));

		toolBarRestoreAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "restore-previous-size-icon.png",
				Text.get(Text.RESTORE_WINDOW_ICON),
				SwingUtil.getKeyEvent(Text.get(Text.RESTORE_WINDOW_ICON_KEY)),
				Text.get(Text.RESTORE_WINDOW_ICON_INFORMATION));
		
		automaticHelpModeActionTurnedOff = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "question-mark-icon.png", Text
						.get(Text.AUTOMATIC_HELP_MODE_TITLE_OFF), 0, Text
						.get(Text.AUTOMATIC_HELP_MODE_TITLE_INFORMATION_OFF));

		automaticHelpModeActionTurnedOn = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "question-mark-icon-on.png",
				Text.get(Text.AUTOMATIC_HELP_MODE_TITLE_ON), 0,
				Text.get(Text.AUTOMATIC_HELP_MODE_TITLE_INFORMATION_ON));

		userLevelDialogSelectionAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "ok-icon.png",
				Text.get(Text.USER_LEVEL_DIALOG_BUTTOM), 0,
				Text.get(Text.USER_LEVEL_DIALOG_INSTRUCTIONS));

		textAreaFindAndReplaceMainAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "find-icon-blue.png",
						Text.get(Text.TEXT_AREA_FIND_MAIN_SHORT_CUT_TEXT),
						SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY)),
						Text.get(Text.TEXT_AREA_FIND_MAIN_SHORT_CUT_TEXT_TOOL_TIP));

		textAreaFindAndReplaceMainFormattedAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "find-icon-blue.png",
						Text.get(Text.TEXT_AREA_FIND_MAIN_SHORT_CUT_TEXT),
						SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY)),
						Text.get(Text.TEXT_AREA_FIND_MAIN_SHORT_CUT_TEXT_TOOL_TIP));

		textAreaFindAndReplaceOutputAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "find-icon-red.png",
						Text.get(Text.TEXT_AREA_FIND_OUTPUT_SHORT_CUT_TEXT),
						SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY)),
						Text.get(Text.TEXT_AREA_FIND_OUTPUT_SHORT_CUT_TEXT_TOOL_TIP));

		textAreaFindAndReplaceOutputFormattedAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "find-icon-red.png",
						Text.get(Text.TEXT_AREA_FIND_OUTPUT_SHORT_CUT_TEXT),
						SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY)),
						Text.get(Text.TEXT_AREA_FIND_OUTPUT_SHORT_CUT_TEXT_TOOL_TIP));

		textAreaFindAndReplaceAuxiliarAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "find-icon-green.png",
						Text.get(Text.TEXT_AREA_FIND_AUXILIAR_SHORT_CUT_TEXT),
						SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY)),
						Text.get(Text.TEXT_AREA_FIND_AUXILIAR_SHORT_CUT_TEXT_TOOL_TIP));

		textAreaFindAndReplaceAuxiliarFormattedAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "find-icon-green.png",
						Text.get(Text.TEXT_AREA_FIND_AUXILIAR_SHORT_CUT_TEXT),
						SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY)),
						Text.get(Text.TEXT_AREA_FIND_AUXILIAR_SHORT_CUT_TEXT_TOOL_TIP));

		textAreaFindAndReplaceScrapBookFormattedAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "find-icon-gray.png",
						Text.get(Text.TEXT_AREA_FIND_SCRAPBOOK_SHORT_CUT_TEXT),
						SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY)),
						Text.get(Text.TEXT_AREA_FIND_SCRAPBOOK_AUXILIAR_SHORT_CUT_TEXT_TOOL_TIP));

		textAreaFindAndReplaceScrapbookAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "find-icon-gray.png",
						Text.get(Text.TEXT_AREA_FIND_SCRAPBOOK_SHORT_CUT_TEXT),
						SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY)),
						Text.get(Text.TEXT_AREA_FIND_SCRAPBOOK_AUXILIAR_SHORT_CUT_TEXT_TOOL_TIP));

		findAction = SimpleActionProvider.getSimpleAction(null,
				Text.get(Text.FIND_AND_REPLACE),
				SwingUtil.getKeyEvent(Text.get(Text.FIND_AND_REPLACE_KEY)),
				Text.get(Text.FIND_AND_REPLACE_INSTRUCTIONS));

		compareAction = SimpleActionProvider.getSimpleAction(null,
				Text.get(Text.COMPARE),
				SwingUtil.getKeyEvent(Text.get(Text.COMPARE_KEY)),
				Text.get(Text.COMPARE_INSTRUCTIONS));

		compareMainWithOutputAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "balance-blue-red-icon.png",
				Text.get(Text.FILTER_COMPARE_TEXTS_TEXT),
				SwingUtil.getKeyEvent(Text.get(Text.FILTER_COMPARE_TEXTS_KEY)),
				Text.get(Text.FILTER_COMPARE_TEXTS_INSTRUCTION));

		compareMainWithAuxiliarAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "balance-blue-green-icon.png",
				Text.get(Text.FILTER_COMPARE_TEXTS_TEXT_MAIN_WITH_AUXILIAR),
				SwingUtil.getKeyEvent(Text.get(Text.FILTER_COMPARE_TEXTS_KEY)),
				Text.get(Text.FILTER_COMPARE_TEXTS_INSTRUCTION));

		compareAuxiliarWithOutputAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "balance-green-red-icon.png",
				Text.get(Text.FILTER_COMPARE_TEXTS_TEXT_OUTPUT_WITH_AUXILIAR),
				SwingUtil.getKeyEvent(Text.get(Text.FILTER_COMPARE_TEXTS_KEY)),
				Text.get(Text.FILTER_COMPARE_TEXTS_INSTRUCTION));

		clearFieldAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "clear-field.png",
				"", 0, Text.get(Text.FILTER_CLEAR_FIELD_INSTRUCTION));

		memory1Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_1_small.png", "", 0, "");

		memory2Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_2_small.png", "", 0, "");

		memory3Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_3_small.png", "", 0, "");

		memory4Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_4_small.png", "", 0, "");

		memory5Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_5_small.png", "", 0, "");

		clearMemoryAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "memory-clear.png",
				"", 0, Text.get(Text.CLEAR_MEMORY_BUTTON));

		scrapBookAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "file-temporary-icon.png", Text.get(Text.SCRAP_BOOK),
				SwingUtil.getKeyEvent(Text.get(Text.SCRAP_BOOK_KEY)),
				Text.get(Text.SCRAP_BOOK_INSTRUCTION));

		positionAction = SimpleActionProvider.getSimpleAction(null,
				Text.get(Text.POSITION),
				SwingUtil.getKeyEvent(Text.get(Text.POSITION_KEY)),
				Text.get(Text.POSITION_INSTRUCTION));

		aboutAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "about-icon.png",
				Text.get(Text.ABOUT_MENU),
				SwingUtil.getKeyEvent(Text.get(Text.ABOUT_MENU_KEY)),
				Text.get(Text.ABOUT_MENU_INFORMATION));

		createRegistrationAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "ok-icon.png", Text
						.get(Text.CREATE_REGISTRATION_EMAIL_BUTTON),
				SwingUtil.getKeyEvent(Text
						.get(Text.CREATE_REGISTRATION_EMAIL_BUTTON_KEY)),
				Text.get(Text.CREATE_REGISTRATION_EMAIL_BUTTON_INSTRUCTIONS));

		cancelRegistrationAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "cancel-icon.png",
				Text.get(Text.CANCEL_REGISTRATION),
				SwingUtil.getKeyEvent(Text.get(Text.CANCEL_REGISTRATION_KEY)),
				Text.get(Text.CANCEL_REGISTRATION_INSTRUCTIONS));

		registerButtonAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ Text.get(Text.REGISTRATION_ICON),
				Text.get(Text.REGISTRATION_BUTTON),
				SwingUtil.getKeyEvent(Text.get(Text.REGISTRATION_KEY)),
				Text.get(Text.REGISTRATION_INFORMATION));

		acceptAgreementAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "ok-icon.png", Text
						.get(Text.ACCEPT_AGREEMENT_BUTTON),
				SwingUtil.getKeyEvent(Text
						.get(Text.ACCEPT_AGREEMENT_BUTTON_KEY)), Text
						.get(Text.ACCEPT_AGREEMENT_BUTTON_INSTRUCTIONS));

		notAcceptAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "cancel-icon.png",
				Text.get(Text.DO_NOT_ACCEPT_AGREEMENT_BUTTON), SwingUtil
						.getKeyEvent(Text
								.get(Text.DO_NOT_ACCEPT_AGREEMENT_BUTTON_KEY)),
				Text.get(Text.DO_NOT_ACCEPT_AGREEMENT_BUTTON_INSTRUCTIONS));

		licenseAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "licence-icon.png",
				Text.get(Text.LICENSE_BUTTON),
				SwingUtil.getKeyEvent(Text.get(Text.LICENSE_BUTTON_KEY)),
				Text.get(Text.LICENSE_BUTTON_INSTRUCTION));

		copyFromMainAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "copy-icon-blue.png",
						Text.get(Text.COPY_FROM_MAIN),
						SwingUtil.getKeyEvent(Text.get(Text.COPY_FROM_MAIN_KEY)),
						Text.get(Text.COPY_FROM_MAIN_INFORMATION));
		cutFromMainAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "cut-icon-blue.png",
				Text.get(Text.CUT_FROM_MAIN),
				SwingUtil.getKeyEvent(Text.get(Text.CUT_FROM_MAIN_KEY)),
				Text.get(Text.CUT_FROM_MAIN_INFORMATION));
		pasteToMainAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "paste-icon-blue.png",
				Text.get(Text.PASTE_FROM_MAIN),
				SwingUtil.getKeyEvent(Text.get(Text.PASTE_FROM_MAIN_KEY)),
				Text.get(Text.PASTE_FROM_MAIN_INFORMATION));

		copyFromAuxiliarAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "copy-icon-green.png",
				Text.get(Text.COPY_FROM_AUXILIAR),
				SwingUtil.getKeyEvent(Text.get(Text.COPY_FROM_AUXILIAR_KEY)),
				Text.get(Text.COPY_FROM_AUXILIAR_INFORMATION));
		cutFromAuxiliarAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "cut-icon-green.png", Text
						.get(Text.CUT_FROM_AUXILIAR), SwingUtil
						.getKeyEvent(Text.get(Text.CUT_FROM_AUXILIAR_KEY)),
						Text.get(Text.CUT_FROM_AUXILIAR_INFORMATION));
		pasteToAuxiliarAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "paste-icon-green.png",
				Text.get(Text.PASTE_FROM_AUXILIAR),
				SwingUtil.getKeyEvent(Text.get(Text.PASTE_FROM_AUXILIAR_KEY)),
				Text.get(Text.PASTE_FROM_AUXILIAR_INFORMATION));

		copyFromOutputAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "copy-icon-red.png",
				Text.get(Text.COPY_FROM_OUTPUT),
				SwingUtil.getKeyEvent(Text.get(Text.COPY_FROM_OUTPUT_KEY)),
				Text.get(Text.COPY_FROM_OUTPUT_INFORMATION));
		cutFromOutputAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "cut-icon-red.png",
				Text.get(Text.CUT_FROM_OUTPUT),
				SwingUtil.getKeyEvent(Text.get(Text.CUT_FROM_OUTPUT_KEY)),
				Text.get(Text.CUT_FROM_OUTPUT_INFORMATION));
		pasteToOutputAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "paste-icon-red.png", Text
						.get(Text.PASTE_FROM_OUTPUT), SwingUtil
						.getKeyEvent(Text.get(Text.PASTE_FROM_OUTPUT_KEY)),
						Text.get(Text.PASTE_FROM_OUTPUT_INFORMATION));

		taskManagerAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "task-manager.png",
				Text.get(Text.TASK_MANAGER),
				SwingUtil.getKeyEvent(Text.get(Text.TASK_MANAGER_KEY)),
				Text.get(Text.TASK_MANAGER_INFORMATION));

		networkAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "network.png",
				Text.get(Text.NETWORK_SERVER),
				SwingUtil.getKeyEvent(Text.get(Text.NETWORK_SERVER_KEY)),
				Text.get(Text.NETWORK_SERVER_INFORMATION));

		networkGetFromMainAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "get-content-icon-blue.png", Text
						.get(Text.NETWORK_SERVER_GET_FROM_MAIN_INPUT),
				SwingUtil.getKeyEvent(Text
						.get(Text.NETWORK_SERVER_GET_FROM_MAIN_INPUT_KEY)),
				Text.get(Text.NETWORK_SERVER_GET_FROM_MAIN_INPUT_INFORMATION));

		networkGetFromOutputAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "get-content-icon-red.png", Text
						.get(Text.NETWORK_SERVER_GET_FROM_OUTPUT), SwingUtil
						.getKeyEvent(Text
								.get(Text.NETWORK_SERVER_GET_FROM_OUTPUT_KEY)),
				Text.get(Text.NETWORK_SERVER_GET_FROM_OUTPUT_INFORMATION));

		networkGetFromAuxiliarAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "get-content-icon-green.png",
						Text.get(Text.NETWORK_SERVER_GET_FROM_AUXILIAR),
						SwingUtil.getKeyEvent(Text
								.get(Text.NETWORK_SERVER_GET_FROM_AUXILIAR_KEY)),
						Text.get(Text.NETWORK_SERVER_GET_FROM_AUXILIAR_INFORMATION));

		networkAddFileAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "add-file_icon.png",
						Text.get(Text.NETWORK_SERVER_ADD_FILE_AUXILIAR),
						SwingUtil.getKeyEvent(Text
								.get(Text.NETWORK_SERVER_ADD_FILE_AUXILIAR_KEY)),
						Text.get(Text.NETWORK_SERVER_ADD_FILE_AUXILIAR_INFORMATION));

		searchForAFilterAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "search-filter-icon.png",
				Text.get(Text.SEARCH_FILTER),
				SwingUtil.getKeyEvent(Text.get(Text.SEARCH_FILTER_KEY)),
				Text.get(Text.SEARCH_FILTER_INFORMATION));

		garbageAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "recycle-icon.png",
				Text.get(Text.GARBAGE_CAN),
				SwingUtil.getKeyEvent(Text.get(Text.GARBAGE_CAN_KEY)),
				Text.get(Text.GARBAGE_CAN_TOOLTIP));

		commandLineGeneratorAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "command-generator-icon.png", Text
						.get(Text.COMMAND_LINE_GENERATOR),
						SwingUtil.getKeyEvent(Text
								.get(Text.COMMAND_LINE_GENERATOR_KEY)), Text
								.get(Text.COMMAND_LINE_GENERATOR_INFORMATION));

		viewAction = SimpleActionProvider.getSimpleAction(null,
				Text.get(Text.VISUALIZE),
				SwingUtil.getKeyEvent(Text.get(Text.VISUALIZE_KEY)),
				Text.get(Text.VISUALIZE_INSTRUCTION));

		goToLineRedAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "go-line-icon-red.png",
				Text.get(Text.GO_TO_LINE_RED), SwingUtil.getKeyEvent(Text
						.get(Text.COMMAND_GO_TO_LINE_KEY_RED)), Text
						.get(Text.GO_TO_LINE_INFORMATION_RED));

		goToLineBlueAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "go-line-icon-blue.png", Text
						.get(Text.GO_TO_LINE_BLUE), SwingUtil.getKeyEvent(Text
						.get(Text.COMMAND_GO_TO_LINE_KEY_BLUE)), Text
						.get(Text.GO_TO_LINE_INFORMATION_BLUE));

		goToLineGreenAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "go-line-icon-green.png", Text
						.get(Text.GO_TO_LINE_GREEN), SwingUtil.getKeyEvent(Text
						.get(Text.COMMAND_GO_TO_LINE_KEY_GREEN)), Text
						.get(Text.GO_TO_LINE_INFORMATION_GREEN));

		goToLineRedFormattedEditorAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "go-line-icon-red.png",
						Text.get(Text.GO_TO_LINE_RED), SwingUtil
								.getKeyEvent(Text
										.get(Text.COMMAND_GO_TO_LINE_KEY_RED)),
						Text.get(Text.GO_TO_LINE_INFORMATION_RED));

		goToLineBlueFormattedEditorAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "go-line-icon-blue.png", Text
						.get(Text.GO_TO_LINE_BLUE), SwingUtil.getKeyEvent(Text
						.get(Text.COMMAND_GO_TO_LINE_KEY_BLUE)), Text
						.get(Text.GO_TO_LINE_INFORMATION_BLUE));

		goToLineGreenFormattedEditorAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "go-line-icon-green.png", Text
						.get(Text.GO_TO_LINE_GREEN), SwingUtil.getKeyEvent(Text
						.get(Text.COMMAND_GO_TO_LINE_KEY_GREEN)), Text
						.get(Text.GO_TO_LINE_INFORMATION_GREEN));

	}

	/**
	 * @return the toolBarAutomaticModeActionTurnedOff
	 */
	public static SimpleActionDecorator getToolBarAutomaticModeActionTurnedOff() {
		return toolBarAutomaticModeActionTurnedOff;
	}

	/**
	 * @return the toolBarAutomaticModeActionTurnedOn
	 */
	public static SimpleActionDecorator getToolBarAutomaticModeActionTurnedOn() {
		return toolBarAutomaticModeActionTurnedOn;
	}

	/**
	 * @return the toolBarMaximizeAction
	 */
	public static SimpleActionDecorator getToolBarMaximizeAction() {
		return toolBarMaximizeAction;
	}

	/**
	 * @return the toolBarRestoreAction
	 */
	public static SimpleActionDecorator getToolBarRestoreAction() {
		return toolBarRestoreAction;
	}

	/**
	 * @return the fileAction
	 */
	protected static SimpleActionDecorator getFileAction() {
		return fileAction;
	}

	/**
	 * @return the exitAction
	 */
	protected static SimpleActionDecorator getExitAction() {
		return exitAction;
	}

	/**
	 * @return the optionAction
	 */
	protected static SimpleActionDecorator getOptionAction() {
		return optionAction;
	}

	/**
	 * @return the toolbarNewLineAuxiliarAction
	 */
	protected static SimpleActionDecorator getToolbarNewLineAuxiliarAction() {
		return toolbarNewLineAuxiliarAction;
	}

	/**
	 * @return the configurationAction
	 */
	protected static SimpleActionDecorator getConfigurationAction() {
		return configurationAction;
	}

	/**
	 * @return the helpAction
	 */
	protected static SimpleActionDecorator getHelpAction() {
		return helpAction;
	}

	/**
	 * @return the shortCutAction
	 */
	public static SimpleActionDecorator getShortCutAction() {
		return shortCutAction;
	}

	/**
	 * @return the contentAction
	 */
	protected static SimpleActionDecorator getContentAction() {
		return contentAction;
	}

	/**
	 * @return the instructionAction
	 */
	protected static SimpleActionDecorator getInstructionAction() {
		return instructionAction;
	}

	/**
	 * @return the exampleAction
	 */
	protected static SimpleActionDecorator getExampleAction() {
		return exampleAction;
	}

	/**
	 * @return the executeAction
	 */
	protected static SimpleActionDecorator getExecuteAction() {
		return executeAction;
	}

	/**
	 * @return the automaticExecuteAction
	 */
	protected static SimpleActionDecorator getAutomaticExecuteAction() {
		return automaticExecuteAction;
	}

	/**
	 * @return the filterTarget_1_ButtomAction
	 */
	protected static SimpleActionDecorator getFilterTarget_1_ButtomAction() {
		return filterTarget_1_ButtomAction;
	}

	/**
	 * @return the filterTarget_Directory_ButtomAction
	 */
	protected static SimpleActionDecorator getFilterTarget_Directory_ButtomAction() {
		return filterTarget_Directory_ButtomAction;
	}

	/**
	 * @return the toolbarSwitchBlueRedAction
	 */
	protected static SimpleActionDecorator getToolbarSwitchBlueRedAction() {
		return toolbarSwitchBlueRedAction;
	}

	/**
	 * @return the toolbarSwitchBlueGreenAction
	 */
	protected static SimpleActionDecorator getToolbarSwitchBlueGreenAction() {
		return toolbarSwitchBlueGreenAction;
	}

	/**
	 * @return the toolbarSwitchGreenRedAction
	 */
	protected static SimpleActionDecorator getToolbarSwitchGreenRedAction() {
		return toolbarSwitchGreenRedAction;
	}

	/**
	 * @return the toolbarSaveTopAsAction
	 */
	protected static SimpleActionDecorator getToolbarSaveTopAsAction() {
		return toolbarSaveTopAsAction;
	}

	/**
	 * @return the toolbarSaveBottomAsAction
	 */
	protected static SimpleActionDecorator getToolbarSaveBottomAsAction() {
		return toolbarSaveBottomAsAction;
	}

	/**
	 * @return the toolbarUpdateAction
	 */
	protected static SimpleActionDecorator getToolbarUpdateAction() {
		return toolbarUpdateAction;
	}

	/**
	 * @return the toolbarNewLineAction
	 */
	protected static SimpleActionDecorator getToolbarNewLineAction() {
		return toolbarNewLineAction;
	}

	/**
	 * @return the toolbarNewLineTopAction
	 */
	protected static SimpleActionDecorator getToolbarNewLineTopAction() {
		return toolbarNewLineTopAction;
	}

	/**
	 * @return the toolbarNewLineBottomAction
	 */
	protected static SimpleActionDecorator getToolbarNewLineBottomAction() {
		return toolbarNewLineBottomAction;
	}

	/**
	 * @return the toolbarSwitchBetweenTextAndFile
	 */
	protected static SimpleActionDecorator getToolbarSwitchBetweenTextAndFile() {
		return toolbarSwitchBetweenTextAndFile;
	}

	/**
	 * @return the toolbarSwitchBetweenTextAndFileTurnedOn
	 */
	protected static SimpleActionDecorator getToolbarSwitchBetweenTextAndFileTurnedOn() {
		return toolbarSwitchBetweenTextAndFileTurnedOn;
	}

	/**
	 * @return the previewConfirmButtonAction
	 */
	protected static SimpleActionDecorator getPreviewConfirmButtonAction() {
		return previewConfirmButtonAction;
	}

	/**
	 * @return the previewCancelButtonAction
	 */
	protected static SimpleActionDecorator getPreviewCancelButtonAction() {
		return previewCancelButtonAction;
	}

	/**
	 * @return the configurationOkButtonAction
	 */
	public static SimpleActionDecorator getConfigurationOkButtonAction() {
		return configurationOkButtonAction;
	}

	/**
	 * @return the configurationCancelButtonAction
	 */
	public static SimpleActionDecorator getConfigurationCancelButtonAction() {
		return configurationCancelButtonAction;
	}

	/**
	 * @return the fileSelectionButtonAction
	 */
	protected static SimpleActionDecorator getFileSelectionButtonAction() {
		return fileSelectionButtonAction;
	}

	/**
	 * @return the directorySelectionButtonAction
	 */
	protected static SimpleActionDecorator getDirectorySelectionButtonAction() {
		return directorySelectionButtonAction;
	}

	/**
	 * @return the defaultPositionAction
	 */
	protected static SimpleActionDecorator getDefaultPositionAction() {
		return defaultPositionAction;
	}

	/**
	 * @return the verticalAreasAction
	 */
	protected static SimpleActionDecorator getVerticalAreasAction() {
		return verticalAreasAction;
	}

	/**
	 * @return the controlOnTheMiddleAction
	 */
	protected static SimpleActionDecorator getControlOnTheMiddleAction() {
		return controlOnTheMiddleAction;
	}

	/**
	 * @return the controlInTheRightVerticalAction
	 */
	protected static SimpleActionDecorator getControlInTheRightVerticalAction() {
		return controlInTheRightVerticalAction;
	}

	/**
	 * @return the controlInTheRightAction
	 */
	protected static SimpleActionDecorator getControlInTheRightAction() {
		return controlInTheRightAction;
	}

	/**
	 * @return the automaticHelpModeActionTurnedOff
	 */
	public static SimpleActionDecorator getAutomaticHelpModeActionTurnedOff() {
		return automaticHelpModeActionTurnedOff;
	}

	/**
	 * @return the automaticHelpModeActionTurnedOn
	 */
	public static SimpleActionDecorator getAutomaticHelpModeActionTurnedOn() {
		return automaticHelpModeActionTurnedOn;
	}

	/**
	 * @return the userLevelDialogSelectionActions
	 */
	public static SimpleActionDecorator getUserLevelDialogSelectionAction() {
		return userLevelDialogSelectionAction;
	}

	/**
	 * @return the textAreaFindAndReplaceMainAction
	 */
	public static SimpleActionDecorator getTextAreaFindAndReplaceMainAction() {
		return textAreaFindAndReplaceMainAction;
	}

	/**
	 * @return the textAreaFindAndReplaceOutputAction
	 */
	public static SimpleActionDecorator getTextAreaFindAndReplaceOutputAction() {
		return textAreaFindAndReplaceOutputAction;
	}

	/**
	 * @return the textAreaFindAndReplaceAuxiliarAction
	 */
	public static SimpleActionDecorator getTextAreaFindAndReplaceAuxiliarAction() {
		return textAreaFindAndReplaceAuxiliarAction;
	}

	/**
	 * @return the textAreaFindAndReplaceScrapbookAction
	 */
	public static SimpleActionDecorator getTextAreaFindAndReplaceScrapbookAction() {
		return textAreaFindAndReplaceScrapbookAction;
	}

	/**
	 * @return the findAction
	 */
	public static SimpleActionDecorator getFindAction() {
		return findAction;
	}

	/**
	 * @return the compareMainWithOutputAction
	 */
	public static SimpleActionDecorator getCompareMainWithOutputAction() {
		return compareMainWithOutputAction;
	}

	/**
	 * @return the compareMainWithAuxiliarAction
	 */
	public static SimpleActionDecorator getCompareMainWithAuxiliarAction() {
		return compareMainWithAuxiliarAction;
	}

	/**
	 * @return the compareAuxiliarWithOutputAction
	 */
	public static SimpleActionDecorator getCompareAuxiliarWithOutputAction() {
		return compareAuxiliarWithOutputAction;
	}

	/**
	 * @return the clearField1Action
	 */
	public static SimpleActionDecorator getClearFieldAction() {
		return clearFieldAction;
	}

	/**
	 * @return the memory1Action
	 */
	public static SimpleActionDecorator getMemory1Action() {
		return memory1Action;
	}

	/**
	 * @return the memory2Action
	 */
	public static SimpleActionDecorator getMemory2Action() {
		return memory2Action;
	}

	/**
	 * @return the memory3Action
	 */
	public static SimpleActionDecorator getMemory3Action() {
		return memory3Action;
	}

	/**
	 * @return the memory4Action
	 */
	public static SimpleActionDecorator getMemory4Action() {
		return memory4Action;
	}

	/**
	 * @return the memory5Action
	 */
	public static SimpleActionDecorator getMemory5Action() {
		return memory5Action;
	}

	/**
	 * @return the clearMemoryAction
	 */
	public static SimpleActionDecorator getClearMemoryAction() {
		return clearMemoryAction;
	}

	/**
	 * @return the scrapBookAction
	 */
	public static SimpleActionDecorator getScrapBookAction() {
		return scrapBookAction;
	}

	/**
	 * @return the positionAction
	 */
	public static SimpleActionDecorator getPositionAction() {
		return positionAction;
	}

	/**
	 * @return the aboutAction
	 */
	public static SimpleActionDecorator getAboutAction() {
		return aboutAction;
	}

	/**
	 * @return the compareAction
	 */
	public static SimpleActionDecorator getCompareAction() {
		return compareAction;
	}

	/**
	 * @return the toolbarSaveAuxiliarAsAction
	 */
	public static SimpleActionDecorator getToolbarSaveAuxiliarAsAction() {
		return toolbarSaveAuxiliarAsAction;
	}

	/**
	 * @return the createRegistrationAction
	 */
	public static SimpleActionDecorator getCreateRegistrationAction() {
		return createRegistrationAction;
	}

	/**
	 * @return the cancelRegistrationAction
	 */
	public static SimpleActionDecorator getCancelRegistrationAction() {
		return cancelRegistrationAction;
	}

	/**
	 * @return the registerButtonAction
	 */
	public static SimpleActionDecorator getRegisterButtonAction() {
		return registerButtonAction;
	}

	/**
	 * @return the acceptAgreementAction
	 */
	public static SimpleActionDecorator getAcceptAgreementAction() {
		return acceptAgreementAction;
	}

	/**
	 * @return the notAcceptAction
	 */
	public static SimpleActionDecorator getNotAcceptAction() {
		return notAcceptAction;
	}

	/**
	 * @return the licenseAction
	 */
	public static SimpleActionDecorator getLicenseAction() {
		return licenseAction;
	}

	/**
	 * @return the copyFromMainAction
	 */
	public static SimpleActionDecorator getCopyFromMainAction() {
		return copyFromMainAction;
	}

	/**
	 * @return the cutFromMainAction
	 */
	public static SimpleActionDecorator getCutFromMainAction() {
		return cutFromMainAction;
	}

	/**
	 * @return the pasteToMainAction
	 */
	public static SimpleActionDecorator getPasteToMainAction() {
		return pasteToMainAction;
	}

	/**
	 * @return the copyFromAuxiliarAction
	 */
	public static SimpleActionDecorator getCopyFromAuxiliarAction() {
		return copyFromAuxiliarAction;
	}

	/**
	 * @return the cutFromAuxiliarAction
	 */
	public static SimpleActionDecorator getCutFromAuxiliarAction() {
		return cutFromAuxiliarAction;
	}

	/**
	 * @return the pasteToToAuxiliarAction
	 */
	public static SimpleActionDecorator getPasteToAuxiliarAction() {
		return pasteToAuxiliarAction;
	}

	/**
	 * @return the copyFromOutputAction
	 */
	public static SimpleActionDecorator getCopyFromOutputAction() {
		return copyFromOutputAction;
	}

	/**
	 * @return the cutFromOutputAction
	 */
	public static SimpleActionDecorator getCutFromOutputAction() {
		return cutFromOutputAction;
	}

	/**
	 * @return the pasteToOutputAction
	 */
	public static SimpleActionDecorator getPasteToOutputAction() {
		return pasteToOutputAction;
	}

	/**
	 * @return the taskManagerAction
	 */
	public static SimpleActionDecorator getTaskManagerAction() {
		return taskManagerAction;
	}

	/**
	 * @return the networkAction
	 */
	public static SimpleActionDecorator getNetworkAction() {
		return networkAction;
	}

	/**
	 * @return the networkGetFromMainAction
	 */
	public static SimpleActionDecorator getNetworkGetFromMainAction() {
		return networkGetFromMainAction;
	}

	/**
	 * @return the networkGetFromOutputAction
	 */
	public static SimpleActionDecorator getNetworkGetFromOutputAction() {
		return networkGetFromOutputAction;
	}

	/**
	 * @return the networkGetFromAuxiliarAction
	 */
	public static SimpleActionDecorator getNetworkGetFromAuxiliarAction() {
		return networkGetFromAuxiliarAction;
	}

	/**
	 * @return the networkAddFileAction
	 */
	public static SimpleActionDecorator getNetworkAddFileAction() {
		return networkAddFileAction;
	}

	/**
	 * @return the searchForAFilterAction
	 */
	public static SimpleActionDecorator getSearchForAFilterAction() {
		return searchForAFilterAction;
	}

	/**
	 * @return the garbageAction
	 */
	public static SimpleActionDecorator getGarbageAction() {
		return garbageAction;
	}

	/**
	 * @return the commandLineGeneratorAction
	 */
	public static SimpleActionDecorator getCommandLineGeneratorAction() {
		return commandLineGeneratorAction;
	}

	/**
	 * @return the viewAction
	 */
	public static SimpleActionDecorator getViewAction() {
		return viewAction;
	}

	/**
	 * @return the goToLineRedAction
	 */
	public static SimpleActionDecorator getGoToLineRedAction() {
		return goToLineRedAction;
	}

	/**
	 * @return the goToLineBlueAction
	 */
	public static SimpleActionDecorator getGoToLineBlueAction() {
		return goToLineBlueAction;
	}

	/**
	 * @return the goToLineGreenAction
	 */
	public static SimpleActionDecorator getGoToLineGreenAction() {
		return goToLineGreenAction;
	}

	/**
	 * @return the textAreaFindAndReplaceMainFormattedAction
	 */
	public static SimpleActionDecorator getTextAreaFindAndReplaceMainFormattedAction() {
		return textAreaFindAndReplaceMainFormattedAction;
	}

	/**
	 * @return the textAreaFindAndReplaceOutputFormattedAction
	 */
	public static SimpleActionDecorator getTextAreaFindAndReplaceOutputFormattedAction() {
		return textAreaFindAndReplaceOutputFormattedAction;
	}

	/**
	 * @return the textAreaFindAndReplaceAuxiliarFormattedAction
	 */
	public static SimpleActionDecorator getTextAreaFindAndReplaceAuxiliarFormattedAction() {
		return textAreaFindAndReplaceAuxiliarFormattedAction;
	}

	/**
	 * @return the textAreaFindAndReplaceScrapBookFormattedAction
	 */
	public static SimpleActionDecorator getTextAreaFindAndReplaceScrapBookFormattedAction() {
		return textAreaFindAndReplaceScrapBookFormattedAction;
	}

	/**
	 * @return the goToLineRedFormattedEditorAction
	 */
	public static SimpleActionDecorator getGoToLineRedFormattedEditorAction() {
		return goToLineRedFormattedEditorAction;
	}

	/**
	 * @return the goToLineBlueFormattedEditorAction
	 */
	public static SimpleActionDecorator getGoToLineBlueFormattedEditorAction() {
		return goToLineBlueFormattedEditorAction;
	}

	/**
	 * @return the goToLineGreenFormattedEditorAction
	 */
	public static SimpleActionDecorator getGoToLineGreenFormattedEditorAction() {
		return goToLineGreenFormattedEditorAction;
	}

}
