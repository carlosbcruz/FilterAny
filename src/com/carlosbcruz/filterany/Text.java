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

/**
 * Provides a simple internationalization feature only for the text utility
 * application.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
@SuppressWarnings("nls")
public class Text {

	private static ResourceText resourceText = null;

	/**
	 * @param key
	 *            The key to search for.
	 * @return The content.
	 */
	public static String get(String key) {

		if (resourceText == null) {
			resourceText = new ResourceText();
		}
		return resourceText.get(key);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @return The content.
	 */
	public static String get(String key, String arg0) {

		if (resourceText == null) {
			resourceText = new ResourceText();
		}
		return resourceText.get(key, arg0);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @param arg1
	 *            Argument 1
	 * @return The content.
	 */
	public static String get(String key, String arg0, String arg1) {

		if (resourceText == null) {
			resourceText = new ResourceText();
		}
		return resourceText.get(key, arg0, arg1);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @param arg1
	 *            Argument 1
	 * @param arg2
	 *            Argument 2
	 * @return The content.
	 */
	public static String get(String key, String arg0, String arg1, String arg2) {

		if (resourceText == null) {
			resourceText = new ResourceText();
		}
		return resourceText.get(key, arg0, arg1, arg2);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @param arg1
	 *            Argument 1
	 * @param arg2
	 *            Argument 2
	 * @param arg3
	 *            Argument 3
	 * @return The content.
	 */
	public static String get(String key, String arg0, String arg1, String arg2,
			String arg3) {

		if (resourceText == null) {
			resourceText = new ResourceText();
		}
		return resourceText.get(key, arg0, arg1, arg2, arg3);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @param arg1
	 *            Argument 1
	 * @param arg2
	 *            Argument 2
	 * @param arg3
	 *            Argument 3
	 * @param arg4
	 *            Argument 4
	 * @return The content.
	 */
	public static String get(String key, String arg0, String arg1, String arg2,
			String arg3, String arg4) {

		if (resourceText == null) {
			resourceText = new ResourceText();
		}
		return resourceText.get(key, arg0, arg1, arg2, arg3, arg4);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @param arg1
	 *            Argument 1
	 * @param arg2
	 *            Argument 2
	 * @param arg3
	 *            Argument 3
	 * @param arg4
	 *            Argument 4
	 * @param arg5
	 *            Argument 5
	 * @return The content.
	 */
	public static String get(String key, String arg0, String arg1, String arg2,
			String arg3, String arg4, String arg5) {

		if (resourceText == null) {
			resourceText = new ResourceText();
		}
		return resourceText.get(key, arg0, arg1, arg2, arg3, arg4, arg5);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @param arg1
	 *            Argument 1
	 * @param arg2
	 *            Argument 2
	 * @param arg3
	 *            Argument 3
	 * @param arg4
	 *            Argument 4
	 * @param arg5
	 *            Argument 5
	 * @param arg5
	 *            Argument 6
	 * @return The content.
	 */
	public static String get(String key, String arg0, String arg1, String arg2,
			String arg3, String arg4, String arg5, String arg6) {

		if (resourceText == null) {
			resourceText = new ResourceText();
		}
		return resourceText.get(key, arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	// Filters already finished completely.

	// 1-ToUpperCaseFilter

	public static final String FILTER_TOUPPERCASEFILTER_LABEL = "filter.touppercasefilter.label";
	public static final String FILTER_TOUPPERCASEFILTER_TOOLTIP = "filter.touppercasefilter.tooltip";
	public static final String FILTER_TOUPPERCASEFILTER_INSTRUCTIONS = "filter.touppercasefilter.instructions";
	public static final String FILTER_TOUPPERCASEFILTER_COMMAND_LINE_HELP = "filter.touppercasefilter.command.line.help";
	public static final String FILTER_TOUPPERCASEFILTER_EXAMPLE_MAIN_INPUT = "filter.touppercasefilter.example.main.input";
	public static final String FILTER_TOUPPERCASEFILTER_EXAMPLE_OUTPUT = "filter.touppercasefilter.example.output";
	public static final String FILTER_TOUPPERCASEFILTER_FIELD_1 = "filter.touppercasefilter.field.1";
	public static final String FILTER_TOUPPERCASEFILTER_FIELD_2 = "filter.touppercasefilter.field.2";
	public static final String FILTER_TOUPPERCASEFILTER_FIELD_1_TOOLTIP = "filter.touppercasefilter.field.1.tooltip";
	public static final String FILTER_TOUPPERCASEFILTER_FIELD_2_TOOLTIP = "filter.touppercasefilter.field.2.tooltip";
	public static final String FILTER_TOUPPERCASEFILTER_EXCEPTION_1 = "filter.touppercasefilter.exception.1";
	public static final String FILTER_TOUPPERCASEFILTER_EXCEPTION_1_1 = "filter.touppercasefilter.exception.1.1";
	public static final String FILTER_TOUPPERCASEFILTER_EXCEPTION_2 = "filter.touppercasefilter.exception.2";
	public static final String FILTER_TOUPPERCASEFILTER_EXCEPTION_2_1 = "filter.touppercasefilter.exception.2.1";
	public static final String FILTER_TOUPPERCASEFILTER_EXCEPTION_3 = "filter.touppercasefilter.exception.3";
	public static final String FILTER_TOUPPERCASEFILTER_REPORT = "filter.touppercasefilter.report";

	// 2-ToLowerCaseFilter
	public static final String FILTER_TOLOWERCASEFILTER_LABEL = "filter.tolowercasefilter.label";
	public static final String FILTER_TOLOWERCASEFILTER_TOOLTIP = "filter.tolowercasefilter.tooltip";
	public static final String FILTER_TOLOWERCASEFILTER_INSTRUCTIONS = "filter.tolowercasefilter.instructions";
	public static final String FILTER_TOLOWERCASEFILTER_COMMAND_LINE_HELP = "filter.tolowercasefilter.command.line.help";
	public static final String FILTER_TOLOWERCASEFILTER_EXAMPLE_MAIN_INPUT = "filter.tolowercasefilter.example.main.input";
	public static final String FILTER_TOLOWERCASEFILTER_EXAMPLE_OUTPUT = "filter.tolowercasefilter.example.output";
	public static final String FILTER_TOLOWERCASEFILTER_FIELD_1 = "filter.tolowercasefilter.field.1";
	public static final String FILTER_TOLOWERCASEFILTER_FIELD_2 = "filter.tolowercasefilter.field.2";
	public static final String FILTER_TOLOWERCASEFILTER_FIELD_1_TOOLTIP = "filter.tolowercasefilter.field.1.tooltip";
	public static final String FILTER_TOLOWERCASEFILTER_FIELD_2_TOOLTIP = "filter.tolowercasefilter.field.2.tooltip";
	public static final String FILTER_TOLOWERCASEFILTER_EXCEPTION_1 = "filter.tolowercasefilter.exception.1";
	public static final String FILTER_TOLOWERCASEFILTER_EXCEPTION_1_1 = "filter.tolowercasefilter.exception.1.1";
	public static final String FILTER_TOLOWERCASEFILTER_EXCEPTION_2 = "filter.tolowercasefilter.exception.2";
	public static final String FILTER_TOLOWERCASEFILTER_EXCEPTION_2_1 = "filter.tolowercasefilter.exception.2.1";
	public static final String FILTER_TOLOWERCASEFILTER_EXCEPTION_3 = "filter.tolowercasefilter.exception.3";
	public static final String FILTER_TOLOWERCASEFILTER_REPORT = "filter.tolowercasefilter.report";

	// 3-FirstCharacterToUpperCaseFilter
	public static final String FILTER_FIRSTCHARACTERTOUPPERCASEFILTER_LABEL = "filter.firstcharactertouppercasefilter.label";
	public static final String FILTER_FIRSTCHARACTERTOUPPERCASEFILTER_TOOLTIP = "filter.firstcharactertouppercasefilter.tooltip";
	public static final String FILTER_FIRSTCHARACTERTOUPPERCASEFILTER_INSTRUCTIONS = "filter.firstcharactertouppercasefilter.instructions";
	public static final String FILTER_FIRSTCHARACTERTOUPPERCASEFILTER_COMMAND_LINE_HELP = "filter.firstcharactertouppercasefilter.command.line.help";
	public static final String FILTER_FIRSTCHARACTERTOUPPERCASEFILTER_MAIN_INPUT = "filter.firstcharactertouppercasefilter.main.input";
	public static final String FILTER_FIRSTCHARACTERTOUPPERCASEFILTER_OUTPUT = "filter.firstcharactertouppercasefilter.output";
	public static final String FILTER_FIRSTCHARACTERTOUPPERCASEFILTER_REPORT = "filter.firstcharactertouppercasefilter.report";

	// 4-ToDOSFilter
	public static final String FILTER_TODOSFILTER_LABEL = "filter.todosfilter.label";
	public static final String FILTER_TODOSFILTER_TOOLTIP = "filter.todosfilter.tooltip";
	public static final String FILTER_TODOSFILTER_INSTRUCTIONS = "filter.todosfilter.instructions";
	public static final String FILTER_TODOSFILTER_COMMAND_LINE_HELP = "filter.todosfilter.command.line.help";
	public static final String FILTER_TODOSFILTER_EXAMPLE_MAIN_INPUT = "filter.todosfilter.example.main.input";
	public static final String FILTER_TODOSFILTER_EXAMPLE_OUTPUT = "filter.todosfilter.example.output";
	public static final String FILTER_TODOSFILTER_REPORT = "filter.todosfilter.report";

	// 5-ToUNIXFilter
	public static final String FILTER_TOUNIXFILTER_LABEL = "filter.tounixfilter.label";
	public static final String FILTER_TOUNIXFILTER_TOOLTIP = "filter.tounixfilter.tooltip";
	public static final String FILTER_TOUNIXFILTER_INSTRUCTIONS = "filter.tounixfilter.instructions";
	public static final String FILTER_TOUNIXFILTER_COMMAND_LINE_HELP = "filter.tounixfilter.command.line.help";
	public static final String FILTER_TOUNIXFILTER_EXAMPLE_MAIN_INPUT = "filter.tounixfilter.example.main.input";
	public static final String FILTER_TOUNIXFILTER_FILTER_TOUNIXFILTER = "filter.tounixfilter.filter.tounixfilter";
	public static final String FILTER_TOUNIXFILTER_REPORT = "filter.tounixfilter.report";

	// 6-FromSpacesToTabFilter
	public static final String FILTER_FROMSPACESTOTABFILTER_LABEL = "filter.fromspacestotabfilter.label";
	public static final String FILTER_FROMSPACESTOTABFILTER_TOOLTIP = "filter.fromspacestotabfilter.tooltip";
	public static final String FILTER_FROMSPACESTOTABFILTER_INSTRUCTIONS = "filter.fromspacestotabfilter.instructions";
	public static final String FILTER_FROMSPACESTOTABFILTER_FIELD1 = "filter.fromspacestotabfilter.field1";
	public static final String FILTER_FROMSPACESTOTABFILTER_FIELD1_TOOLTIP = "filter.fromspacestotabfilter.field1.tooltip";
	public static final String FILTER_FROMSPACESTOTABFILTER_EXCEPTION1 = "filter.fromspacestotabfilter.exception1";
	public static final String FILTER_FROMSPACESTOTABFILTER_COMMAND_LINE_HELP = "filter.fromspacestotabfilter.command.line.help";
	public static final String FILTER_FROMSPACESTOTABFILTER_EXAMPLE_MAIN_INPUT = "filter.fromspacestotabfilter.example.main.input";
	public static final String FILTER_FROMSPACESTOTABFILTER_EXAMPLE_OUTPUT = "filter.fromspacestotabfilter.example.output";
	public static final String FILTER_FROMSPACESTOTABFILTER_REPORT = "filter.fromspacestotabfilter.report";

	// 7-FromTabToSpacesFilter
	public static final String FILTER_FROMTABTOSPACESFILTER_LABEL = "filter.fromtabtospacesfilter.label";
	public static final String FILTER_FROMTABTOSPACESFILTER_TOOLTIP = "filter.fromtabtospacesfilter.tooltip";
	public static final String FILTER_FROMTABTOSPACESFILTER_INSTRUCTIONS = "filter.fromtabtospacesfilter.instructions";
	public static final String FILTER_FROMTABTOSPACESFILTER_FIELD1 = "filter.fromtabtospacesfilter.field1";
	public static final String FILTER_FROMTABTOSPACESFILTER_FIELD1_TOOLTIP = "filter.fromtabtospacesfilter.field1.tooltip";
	public static final String FILTER_FROMTABTOSPACESFILTER_EXCEPTION1 = "filter.fromtabtospacesfilter.exception1";
	public static final String FILTER_FROMTABTOSPACESFILTER_COMMAND_LINE_HELP = "filter.fromtabtospacesfilter.command.line.help";
	public static final String FILTER_FROMTABTOSPACESFILTER_EXAMPLE_MAIN_INPUT = "filter.fromtabtospacesfilter.example.main.input";
	public static final String FILTER_FROMTABTOSPACESFILTER_EXAMPLE_OUTPUT = "filter.fromtabtospacesfilter.example.output";
	public static final String FILTER_FROMTABTOSPACESFILTER_REPORT = "filter.fromtabtospacesfilter.report";

	// 8-RemoveCharacterOnBeginningFilter
	public static final String FILTER_REMOVECHARACTERBEGINNING_LABEL = "filter.removecharacterbeginning.label";
	public static final String FILTER_REMOVECHARACTERBEGINNING_TOOLTIP = "filter.removecharacterbeginning.tooltip";
	public static final String FILTER_REMOVECHARACTERBEGINNING_INSTRUCTIONS = "filter.removecharacterbeginning.instructions";
	public static final String FILTER_REMOVECHARACTERBEGINNING_COMMAND_LINE_HELP = "filter.removecharacterbeginning.command.line.help";
	public static final String FILTER_REMOVECHARACTERBEGINNING_FIELD1 = "filter.removecharacterbeginning.field1";
	public static final String FILTER_REMOVECHARACTERBEGINNING_CHECKBOX1 = "filter.removecharacterbeginning.checkbox1";
	public static final String FILTER_REMOVECHARACTERBEGINNING_FIELD1_TOOLTIP = "filter.removecharacterbeginning.field1.tooltip";
	public static final String FILTER_REMOVECHARACTERBEGINNING_CHECKBOX1_TOOLTIP = "filter.removecharacterbeginning.checkbox1.tooltip";
	public static final String FILTER_REMOVECHARACTERBEGINNING_EXCEPTION = "filter.removecharacterbeginning.exception";
	public static final String FILTER_REMOVECHARACTERBEGINNING_EXAMPLE_MAIN_INPUT = "filter.removecharacterbeginning.example.main.input";
	public static final String FILTER_REMOVECHARACTERBEGINNING_EXAMPLE_OUTPUT = "filter.removecharacterbeginning.example.output";
	public static final String FILTER_REMOVECHARACTERBEGINNING_REPORT = "filter.removecharacterbeginning.report";

	// 9-RemoveCharacterOnTheEndFilter
	public static final String FILTER_REMOVECHARACTERONTHEEND_LABEL = "filter.removecharacterontheend.label";
	public static final String FILTER_REMOVECHARACTERONTHEEND_TOOLTIP = "filter.removecharacterontheend.tooltip";
	public static final String FILTER_REMOVECHARACTERONTHEEND_INSTRUCTIONS = "filter.removecharacterontheend.instructions";
	public static final String FILTER_REMOVECHARACTERONTHEEND_FIELD1 = "filter.removecharacterontheend.field1";
	public static final String FILTER_REMOVECHARACTERONTHEEND_CHECKBOX1 = "filter.removecharacterontheend.checkbox1";
	public static final String FILTER_REMOVECHARACTERONTHEEND_FIELD1_TOOLTIP = "filter.removecharacterontheend.field1.tooltip";
	public static final String FILTER_REMOVECHARACTERONTHEEND_CHECKBOX1_TOOLTIP = "filter.removecharacterontheend.checkbox1.tooltip";
	public static final String FILTER_REMOVECHARACTERONTHEEND_COMMAND_LINE_HELP = "filter.removecharacterontheend.command.line.help";
	public static final String FILTER_REMOVECHARACTERONTHEEND_EXCEPTION = "filter.removecharacterontheend.exception";
	public static final String FILTER_REMOVECHARACTERONTHEEND_EXAMPLE_MAIN_INPUT = "filter.removecharacterontheend.example.main.input";
	public static final String FILTER_REMOVECHARACTERONTHEEND_EXAMPLE_OUTPUT = "filter.removecharacterontheend.example.output";
	public static final String FILTER_REMOVECHARACTERONTHEEND_REPORT = "filter.removecharacterontheend.report";

	// 10-RemoveCharacterBeforePositionFilter
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_LABEL = "filter.removecharactersbeforeposition.label";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_TOOLTIP = "filter.removecharactersbeforeposition.tooltip";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_INSTRUCTIONS = "filter.removecharactersbeforeposition.instructions";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_FIELD1 = "filter.removecharactersbeforeposition.field1";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_FIELD2 = "filter.removecharactersbeforeposition.field2";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_FIELD1_TOOLTIP = "filter.removecharactersbeforeposition.field1.tooltip";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_FIELD2_TOOLTIP = "filter.removecharactersbeforeposition.field2.tooltip";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_EXCEPTION = "filter.removecharactersbeforeposition.exception";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_EXCEPTION2 = "filter.removecharactersbeforeposition.exception2";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_COMMAND_LINE_HELP = "filter.removecharactersbeforeposition.command.line.help";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_EXAMPLE_MAIN_INPUT = "filter.removecharactersbeforeposition.example.main.input";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_EXAMPLE_OUTPUT = "filter.removecharactersbeforeposition.example.output";
	public static final String FILTER_REMOVECHARACTERSBEFOREPOSITION_REPORT = "filter.removecharactersbeforeposition.report";

	// 11-RemoveCharacterAfterPositionFilter
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_LABEL = "filter.removecharactersafterposition.label";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_TOOLTIP = "filter.removecharactersafterposition.tooltip";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_INSTRUCTIONS = "filter.removecharactersafterposition.instructions";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_FIELD1 = "filter.removecharactersafterposition.field1";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_FIELD2 = "filter.removecharactersafterposition.field2";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_FIELD1_TOOLTIP = "filter.removecharactersafterposition.field1.tooltip";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_FIELD2_TOOLTIP = "filter.removecharactersafterposition.field2.tooltip";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_EXCEPTION = "filter.removecharactersafterposition.exception";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_EXCEPTION2 = "filter.removecharactersafterposition.exception2";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_COMMAND_LINE_HELP = "filter.removecharactersafterposition.command.line.help";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_EXAMPLE_MAIN_INPUT = "filter.removecharactersafterposition.example.main.input";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_EXAMPLE_OUTPUT = "filter.removecharactersafterposition.example.output";
	public static final String FILTER_REMOVECHARACTERSAFTERPOSITION_REPORT = "filter.removecharactersafterposition.report";

	// 12-RemoveCharacterBetweenPositionsFilter
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_LABEL = "filter.removecharactersbetweenpositions.label";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_TOOLTIP = "filter.removecharactersbetweenpositions.tooltip";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_INSTRUCTIONS = "filter.removecharactersbetweenpositions.instructions";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_FIELD1 = "filter.removecharactersbetweenpositions.field1";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_FIELD2 = "filter.removecharactersbetweenpositions.field2";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_FIELD1_TOOLTIP = "filter.removecharactersbetweenpositions.field1.tooltip";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_FIELD2_TOOLTIP = "filter.removecharactersbetweenpositions.field2.tooltip";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_EXCEPTION1 = "filter.removecharactersbetweenpositions.exception1";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_EXCEPTION2 = "filter.removecharactersbetweenpositions.exception2";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_COMMAND_LINE_HELP = "filter.removecharactersbetweenpositions.command.line.help";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_EXAMPLE_MAIN_INPUT = "filter.removecharactersbetweenpositions.example.main.input";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_EXAMPLE_OUTPUT = "filter.removecharactersbetweenpositions.example.output";
	public static final String FILTER_REMOVECHARACTERSBETWEENPOSITIONS_REPORT = "filter.removecharactersbetweenpositions.report";

	// 13-RemoveCharactersBetweenParametersFilter
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_LABEL = "filter.removecharactersbetweenparametersfilter.label";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_TOOLTIP = "filter.removecharactersbetweenparametersfilter.tooltip";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INSTRUCTIONS = "filter.removecharactersbetweenparametersfilter.instructions";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_COMMAND_LINE_HELP = "filter.removecharactersbetweenparametersfilter.command.line.help";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INPUT_1 = "filter.removecharactersbetweenparametersfilter.input.1";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INPUT_2 = "filter.removecharactersbetweenparametersfilter.input.2";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_CHECKBOX_1 = "filter.removecharactersbetweenparametersfilter.checkbox.1";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_CHECKBOX_2 = "filter.removecharactersbetweenparametersfilter.checkbox.2";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INPUT_1_TOOLTIP = "filter.removecharactersbetweenparametersfilter.input.1.tooltip";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INPUT_2_TOOLTIP = "filter.removecharactersbetweenparametersfilter.input.2.tooltip";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_CHECKBOX_1_TOOLTIP = "filter.removecharactersbetweenparametersfilter.checkbox.1.tooltip";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_CHECKBOX_2_TOOLTIP = "filter.removecharactersbetweenparametersfilter.checkbox.2.tooltip";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_MAIN_INPUT = "filter.removecharactersbetweenparametersfilter.main.input";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_OUTPUT = "filter.removecharactersbetweenparametersfilter.output";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_REPORT = "filter.removecharactersbetweenparametersfilter.report";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_REQUIRED_PARAMETER1 = "filter.removecharactersbetweenparametersfilter.required.parameter.1";
	public static final String FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_REQUIRED_PARAMETER2 = "filter.removecharactersbetweenparametersfilter.required.parameter.2";

	// 14-RemoveCharactersOutsideParametersFilter
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_LABEL = "filter.removecharactersoutsideparametersfilter.label";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_TOOLTIP = "filter.removecharactersoutsideparametersfilter.tooltip";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_INSTRUCTIONS = "filter.removecharactersoutsideparametersfilter.instructions";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_INPUT_1 = "filter.removecharactersoutsideparametersfilter.input.1";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_INPUT_2 = "filter.removecharactersoutsideparametersfilter.input.2";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_CHECKBOX_1 = "filter.removecharactersoutsideparametersfilter.checkbox.1";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_CHECKBOX_2 = "filter.removecharactersoutsideparametersfilter.checkbox.2";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_INPUT_1_TOOLTIP = "filter.removecharactersoutsideparametersfilter.input.1.tooltip";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_INPUT_2_TOOLTIP = "filter.removecharactersoutsideparametersfilter.input.2.tooltip";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_CHECKBOX_1_TOOLTIP = "filter.removecharactersoutsideparametersfilter.checkbox.1.tooltip";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_CHECKBOX_2_TOOLTIP = "filter.removecharactersoutsideparametersfilter.checkbox.2.tooltip";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_COMMAND_LINE_HELP = "filter.removecharactersoutsideparametersfilter.command.line.help";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_MAIN_INPUT = "filter.removecharactersoutsideparametersfilter.main.input";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_OUTPUT = "filter.removecharactersoutsideparametersfilter.output";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_REPORT = "filter.removecharactersoutsideparametersfilter.report";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_REQUIRED_PARAMETER1 = "filter.removecharactersoutsideparametersfilter.required.parameter.1";
	public static final String FILTER_REMOVECHARACTERSOUTSIDEPARAMETERSFILTER_REQUIRED_PARAMETER2 = "filter.removecharactersoutsideparametersfilter.required.parameter.2";

	// 15-RemoveSpacesOnBeginningFilter
	public static final String FILTER_REMOVESPACESFROMBEGINNING_LABEL = "filter.removespacesfrombeginning.label";
	public static final String FILTER_REMOVESPACESFROMBEGINNING_TOOLTIP = "filter.removespacesfrombeginning.tooltip";
	public static final String FILTER_REMOVESPACESFROMBEGINNING_INSTRUCTIONS = "filter.removespacesfrombeginning.instructions";
	public static final String FILTER_REMOVESPACESFROMBEGINNING_COMMAND_LINE_HELP = "filter.removespacesfrombeginning.command.line.help";
	public static final String FILTER_REMOVESPACESFROMBEGINNING_EXAMPLE_MAIN_INPUT = "filter.removespacesfrombeginning.example.main.input";
	public static final String FILTER_REMOVESPACESFROMBEGINNING_EXAMPLE_OUTPUT = "filter.removespacesfrombeginning.example.output";
	public static final String FILTER_REMOVESPACESFROMBEGINNING_REPORT = "filter.removespacesfrombeginning.report";

	// 16-RemoveSpacesOnTheEndFilter
	public static final String FILTER_REMOVESPACESONTHEEND_LABEL = "filter.removespacesontheend.label";
	public static final String FILTER_REMOVESPACESONTHEEND_TOOLTIP = "filter.removespacesontheend.tooltip";
	public static final String FILTER_REMOVESPACESONTHEEND_INSTRUCTIONS = "filter.removespacesontheend.instructions";
	public static final String FILTER_REMOVESPACESONTHEEND_COMMAND_LINE_HELP = "filter.removespacesontheend.command.line.help";
	public static final String FILTER_REMOVESPACESONTHEEND_EXAMPLE_MAIN_INPUT = "filter.removespacesontheend.example.main.input";
	public static final String FILTER_REMOVESPACESONTHEEND_EXAMPLE_OUTPUT = "filter.removespacesontheend.example.output";
	public static final String FILTER_REMOVESPACESONTHEEND_REPORT = "filter.removespacesontheend.report";

	// 17-RemoveSpacesFromExtremitiesFilter
	public static final String FILTER_REMOVESPACESFROMEXTREMITIES_LABEL = "filter.removespacesfromextremities.label";
	public static final String FILTER_REMOVESPACESFROMEXTREMITIES_TOOLTIP = "filter.removespacesfromextremities.tooltip";
	public static final String FILTER_REMOVESPACESFROMEXTREMITIES_INSTRUCTIONS = "filter.removespacesfromextremities.instructions";
	public static final String FILTER_REMOVESPACESFROMEXTREMITIES_COMMAND_LINE_HELP = "filter.removespacesfromextremities.command.line.help";
	public static final String FILTER_REMOVESPACESFROMEXTREMITIES_EXAMPLE_MAIN_INPUT = "filter.removespacesfromextremities.example.main.input";
	public static final String FILTER_REMOVESPACESFROMEXTREMITIES_EXAMPLE_OUTPUT = "filter.removespacesfromextremities.example.output";
	public static final String FILTER_REMOVESPACESFROMEXTREMITIES_REPORT = "filter.removespacesfromextremities.report";

	// 18-RemoveCharacterBeforeParameterFilter
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_LABEL = "filter.removecharactersbeforeparameter.label";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_TOOLTIP = "filter.removecharactersbeforeparameter.tooltip";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_INSTRUCTIONS = "filter.removecharactersbeforeparameter.instructions";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD1 = "filter.removecharactersbeforeparameter.field1";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD2 = "filter.removecharactersbeforeparameter.field2";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD3 = "filter.removecharactersbeforeparameter.field3";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_CHECKBOX1 = "filter.removecharactersbeforeparameter.checkbox1";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD1_TOOLTIP = "filter.removecharactersbeforeparameter.field1.tooltip";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD2_TOOLTIP = "filter.removecharactersbeforeparameter.field2.tooltip";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD3_TOOLTIP = "filter.removecharactersbeforeparameter.field3.tooltip";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_CHECKBOX1_TOOLTIP = "filter.removecharactersbeforeparameter.checkbox1.tooltip";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXCEPTION = "filter.removecharactersbeforeparameter.exception";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXCEPTION2 = "filter.removecharactersbeforeparameter.exception2";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXCEPTION3 = "filter.removecharactersbeforeparameter.exception3";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_COMMAND_LINE_HELP = "filter.removecharactersbeforeparameter.command.line.help";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXAMPLE_MAIN_INPUT = "filter.removecharactersbeforeparameter.example.main.input";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXAMPLE_OUTPUT = "filter.removecharactersbeforeparameter.example.output";
	public static final String FILTER_REMOVECHARACTERSBEFOREPARAMETER_REPORT = "filter.removecharactersbeforeparameter.report";

	// 19-RemoveCharacterAfterParameterFilter
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_LABEL = "filter.removecharactersafterparameter.label";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_TOOLTIP = "filter.removecharactersafterparameter.tooltip";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_INSTRUCTIONS = "filter.removecharactersafterparameter.instructions";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_FIELD1 = "filter.removecharactersafterparameter.field1";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_FIELD2 = "filter.removecharactersafterparameter.field2";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_FIELD3 = "filter.removecharactersafterparameter.field3";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_CHECKBOX1 = "filter.removecharactersafterparameter.checkbox1";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_FIELD1_TOOLTIP = "filter.removecharactersafterparameter.field1.tooltip";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_FIELD2_TOOLTIP = "filter.removecharactersafterparameter.field2.tooltip";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_FIELD3_TOOLTIP = "filter.removecharactersafterparameter.field3.tooltip";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_CHECKBOX1_TOOLTIP = "filter.removecharactersafterparameter.checkbox1.tooltip";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_EXCEPTION = "filter.removecharactersafterparameter.exception";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_EXCEPTION2 = "filter.removecharactersafterparameter.exception2";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_EXCEPTION3 = "filter.removecharactersafterparameter.exception3";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_COMMAND_LINE_HELP = "filter.removecharactersafterparameter.command.line.help";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_EXAMPLE_MAIN_INPUT = "filter.removecharactersafterparameter.example.main.input";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_EXAMPLE_OUTPUT = "filter.removecharactersafterparameter.example.output";
	public static final String FILTER_REMOVECHARACTERSAFTERPARAMETER_REPORT = "filter.removecharactersafterparameter.report";

	// 20-LeaveOnlyNumbersFilter
	public static final String FILTER_LEAVEONLYNUMBERS_LABEL = "filter.leaveonlynumbers.label";
	public static final String FILTER_LEAVEONLYNUMBERS_TOOLTIP = "filter.leaveonlynumbers.tooltip";
	public static final String FILTER_LEAVEONLYNUMBERS_INSTRUCTIONS = "filter.leaveonlynumbers.instructions";
	public static final String FILTER_LEAVEONLYNUMBERS_CHECKBOX_1 = "filter.leaveonlynumbers.checkbox.1";
	public static final String FILTER_LEAVEONLYNUMBERS_CHECKBOX_2 = "filter.leaveonlynumbers.checkbox.2";
	public static final String FILTER_LEAVEONLYNUMBERS_CHECKBOX_1_TOOLTIP = "filter.leaveonlynumbers.checkbox.1.tooltip";
	public static final String FILTER_LEAVEONLYNUMBERS_CHECKBOX_2_TOOLTIP = "filter.leaveonlynumbers.checkbox.2.tooltip";
	public static final String FILTER_LEAVEONLYNUMBERS_COMMAND_LINE_HELP = "filter.leaveonlynumbers.command.line.help";
	public static final String FILTER_LEAVEONLYNUMBERS_MAIN_INPUT = "filter.leaveonlynumbers.main.input";
	public static final String FILTER_LEAVEONLYNUMBERS_OUTPUT = "filter.leaveonlynumbers.output";
	public static final String FILTER_LEAVEONLYNUMBERS_REPORT = "filter.leaveonlynumbers.report";

	// 21=CollapseSpacesFilter
	public static final String FILTER_COLLAPSESPACES_LABEL = "filter.collapsespaces.label";
	public static final String FILTER_COLLAPSESPACES_TOOLTIP = "filter.collapsespaces.tooltip";
	public static final String FILTER_COLLAPSESPACES_INSTRUCTIONS = "filter.collapsespaces.instructions";
	public static final String FILTER_COLLAPSESPACES_FIELD_1 = "filter.collapsespaces.field.1";
	public static final String FILTER_COLLAPSESPACES_FIELD_2 = "filter.collapsespaces.field.2";
	public static final String FILTER_COLLAPSESPACES_FIELD_1_TOOLTIP = "filter.collapsespaces.field.1.tooltip";
	public static final String FILTER_COLLAPSESPACES_FIELD_2_TOOLTIP = "filter.collapsespaces.field.2.tooltip";
	public static final String FILTER_COLLAPSESPACES_COMMAND_LINE_HELP = "filter.collapsespaces.command.line.help";
	public static final String FILTER_COLLAPSESPACES_MAIN_INPUT = "filter.collapsespaces.main.input";
	public static final String FILTER_COLLAPSESPACES_OUTPUT = "filter.collapsespaces.output";
	public static final String FILTER_COLLAPSESPACES_REPORT = "filter.collapsespaces.report";

	// 22-KeepOnlyValidCharactersFilter
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_LABEL = "filter.keeponlyvalidcharactersfilter.label";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_TOOLTIP = "filter.keeponlyvalidcharactersfilter.tooltip";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_CHECKBOX_1 = "filter.keeponlyvalidcharactersfilter.checkbox.1";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_FIELD_1 = "filter.keeponlyvalidcharactersfilter.field.1";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_CHECKBOX_1_TOOLTIP = "filter.keeponlyvalidcharactersfilter.checkbox.1.tooltip";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_FIELD_1_TOOLTIP = "filter.keeponlyvalidcharactersfilter.field.1.tooltip";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_INSTRUCTIONS = "filter.keeponlyvalidcharactersfilter.instructions";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_COMMAND_LINE_HELP = "filter.keeponlyvalidcharactersfilter.command.line.help";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_EXAMPLE_MAIN_INPUT = "filter.keeponlyvalidcharactersfilter.example.main.input";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_EXAMPLE_OUTPUT = "filter.keeponlyvalidcharactersfilter.example.output";
	public static final String FILTER_KEEPONLYVALIDCHARACTERSFILTER_REPORT = "filter.keeponlyvalidcharactersfilter.report";

	// 23-CopyFilter
	public static final String FILTER_COPYFILTER_LABEL = "filter.copyfilter.label";
	public static final String FILTER_COPYFILTER_TOOLTIP = "filter.copyfilter.tooltip";
	public static final String FILTER_COPYFILTER_INSTRUCTIONS = "filter.copyfilter.instructions";
	public static final String FILTER_COPYFILTER_COMMAND_LINE_HELP = "filter.copyfilter.command.line.help";
	public static final String FILTER_COPYFILTER_EXAMPLE_MAIN_INPUT = "filter.copyfilter.example.main.input";
	public static final String FILTER_COPYFILTER_EXAMPLE_OUTPU = "filter.copyfilter.example.output";
	public static final String FILTER_COPYFILTER_REPORT = "filter.copyfilter.report";

	// 24-LineSimilaritiesFilter
	public static final String FILTER_LINESIMILARITIESFILTER_LABEL = "filter.linesimilaritiesfilter.label";
	public static final String FILTER_LINESIMILARITIESFILTER_SMALL_DESCRIPTION = "filter.linesimilaritiesfilter.small.description";
	public static final String FILTER_LINESIMILARITIESFILTER_INSTRUCTIONS = "filter.linesimilaritiesfilter.instructions";
	public static final String FILTER_LINESIMILARITIESFILTER_EXCEPTION = "filter.linesimilaritiesfilter.exception";
	public static final String FILTER_LINESIMILARITIESFILTER_CHECKBOX1 = "filter.linesimilaritiesfilter.checkbox1";
	public static final String FILTER_LINESIMILARITIESFILTER_CHECKBOX1_TOOLTIP = "filter.linesimilaritiesfilter.checkbox1.tooltip";
	public static final String FILTER_LINESIMILARITIESFILTER_EXAMPLE_MAIN_INPUT = "filter.linesimilaritiesfilter.example.main.input";
	public static final String FILTER_LINESIMILARITIESFILTER_EXAMPLE_OUTPUT = "filter.linesimilaritiesfilter.example.output";
	public static final String FILTER_LINESIMILARITIESFILTER_CANCEL = "filter.linesimilaritiesfilter.cancel";
	public static final String FILTER_LINESIMILARITIESFILTER_CANCEL_KEY = "filter.linesimilaritiesfilter.cancel.key";
	public static final String FILTER_LINESIMILARITIESFILTER_CANCEL_INSTRUCTIONS = "filter.linesimilaritiesfilter.cancel.instructions";
	public static final String FILTER_LINESIMILARITIESFILTER_LINES = "filter.linesimilaritiesfilter.lines";
	public static final String FILTER_LINESIMILARITIESFILTER_DURATION = "filter.linesimilaritiesfilter.duration";
	public static final String FILTER_LINESIMILARITIESFILTER_DISTRIBUTION = "filter.linesimilaritiesfilter.distribution";
	public static final String FILTER_LINESIMILARITIESFILTER_PROGRESS = "filter.linesimilaritiesfilter.progress";
	public static final String FILTER_LINESIMILARITIESFILTER_OPERATION_CANCELED = "filter.linesimilaritiesfilter.operation.canceled";
	public static final String FILTER_LINESIMILARITIESFILTER_CURRENT_LINE = "filter.linesimilaritiesfilter.current.line";
	public static final String FILTER_LINESIMILARITIESFILTER_REMAINING_LINES = "filter.linesimilaritiesfilter.remaining.lines";
	public static final String FILTER_LINESIMILARITIESFILTER_TOTAL_OF_LINES = "filter.linesimilaritiesfilter.total.of.lines";
	public static final String FILTER_LINESIMILARITIESFILTER_HOUR = "filter.linesimilaritiesfilter.hour";
	public static final String FILTER_LINESIMILARITIESFILTER_MINUTE = "filter.linesimilaritiesfilter.minute";
	public static final String FILTER_LINESIMILARITIESFILTER_SECOND = "filter.linesimilaritiesfilter.second";
	public static final String FILTER_LINESIMILARITIESFILTER_ELAPSED_TIME = "filter.linesimilaritiesfilter.elapsed.time";
	public static final String FILTER_LINESIMILARITIESFILTER_REMAINING = "filter.linesimilaritiesfilter.remaining";
	public static final String FILTER_LINESIMILARITIESFILTER_EXPECTED_TIME = "filter.linesimilaritiesfilter.expected.time";
	public static final String FILTER_LINESIMILARITIESFILTER_SIMILARITY = "filter.linesimilaritiesfilter.similarity";
	public static final String FILTER_LINESIMILARITIESFILTER_LINE = "filter.linesimilaritiesfilter.line";
	public static final String FILTER_LINESIMILARITIESFILTER_FINISHED_SUCCESSFULY = "filter.linesimilaritiesfilter.finished.successfuly";
	public static final String FILTER_LINESIMILARITIESFILTER_TITLE = "filter.linesimilaritiesfilter.title";
	public static final String FILTER_LINESIMILARITIESFILTER_LINE_DEMONSTRATION = "filter.linesimilaritiesfilter.line.demonstration";

	// 25-AddAnchorOnBeginOfLineFilter
	public static final String FILTER_ADDANCHORONBEGINOFLINE_LABEL = "filter.addanchoronbeginofline.label";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_TOOLTIP = "filter.addanchoronbeginofline.tooltip";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_INSTRUCTIONS = "filter.addanchoronbeginofline.instructions";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_FIELD1 = "filter.addanchoronbeginofline.field1";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_FIELD1_TOOLTIP = "filter.addanchoronbeginofline.field1.tooltip";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_FIELD2 = "filter.addanchoronbeginofline.field2";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_FIELD2_TOOLTIP = "filter.addanchoronbeginofline.field2.tooltip";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_EXCEPTION = "filter.addanchoronbeginofline.exception";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_COMMAND_LINE_HELP = "filter.addanchoronbeginofline.command.line.help";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_EXAMPLE_MAIN_INPUT = "filter.addanchoronbeginofline.example.main.input";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_EXAMPLE_OUTPUT = "filter.addanchoronbeginofline.example.output";
	public static final String FILTER_ADDANCHORONBEGINOFLINE_REPORT = "filter.addanchoronbeginofline.report";

	// 26-AddAnchorOnEndOfLineFilter
	public static final String FILTER_ADDANCHORONENDOFLINE_LABEL = "filter.addanchoronendofline.label";
	public static final String FILTER_ADDANCHORONENDOFLINE_TOOLTIP = "filter.addanchoronendofline.tooltip";
	public static final String FILTER_ADDANCHORONENDOFLINE_INSTRUCTIONS = "filter.addanchoronendofline.instructions";
	public static final String FILTER_ADDANCHORONENDOFLINE_FIELD1 = "filter.addanchoronendofline.field1";
	public static final String FILTER_ADDANCHORONENDOFLINE_FIELD1_TOOLTIP = "filter.addanchoronendofline.field1.tooltip";
	public static final String FILTER_ADDANCHORONENDOFLINE_FIELD2 = "filter.addanchoronendofline.field2";
	public static final String FILTER_ADDANCHORONENDOFLINE_FIELD2_TOOLTIP = "filter.addanchoronendofline.field2.tooltip";
	public static final String FILTER_ADDANCHORONENDOFLINE_COMMAND_LINE_HELP = "filter.addanchoronendofline.command.line.help";
	public static final String FILTER_ADDANCHORONENDOFLINE_EXAMPLE_MAIN_INPUT = "filter.addanchoronendofline.example.main.input";
	public static final String FILTER_ADDANCHORONENDOFLINE_EXAMPLE_OUTPUT = "filter.addanchoronendofline.example.output";
	public static final String FILTER_ADDANCHORONENDOFLINE_REPORT = "filter.addanchoronendofline.report";

	// 27-AddAnchorOnAPositionFilter
	public static final String FILTER_ADDANCHORONAPOSITION_LABEL = "filter.addanchoronaposition.label";
	public static final String FILTER_ADDANCHORONAPOSITION_TOOLTIP = "filter.addanchoronaposition.tooltip";
	public static final String FILTER_ADDANCHORONAPOSITION_INSTRUCTIONS = "filter.addanchoronaposition.instructions";
	public static final String FILTER_ADDANCHORONAPOSITION_FIELD1 = "filter.addanchoronaposition.field1";
	public static final String FILTER_ADDANCHORONAPOSITION_FIELD2 = "filter.addanchoronaposition.field2";
	public static final String FILTER_ADDANCHORONAPOSITION_FIELD1_TOOLTIP = "filter.addanchoronaposition.field1.tooltip";
	public static final String FILTER_ADDANCHORONAPOSITION_FIELD2_TOOLTIP = "filter.addanchoronaposition.field2.tooltip";
	public static final String FILTER_ADDANCHORONAPOSITION_FIELD3 = "filter.addanchoronaposition.field3";
	public static final String FILTER_ADDANCHORONAPOSITION_FIELD3_TOOLTIP = "filter.addanchoronaposition.field3.tooltip";
	public static final String FILTER_ADDANCHORONAPOSITION_COMMAND_LINE_HELP = "filter.addanchoronaposition.command.line.help";
	public static final String FILTER_ADDANCHORONAPOSITION_EXCEPTION = "filter.addanchoronaposition.exception";
	public static final String FILTER_ADDANCHORONAPOSITION_EXCEPTION_2 = "filter.addanchoronaposition.exception.2";
	public static final String FILTER_ADDANCHORONAPOSITION_EXAMPLE_MAIN_INPUT = "filter.addanchoronaposition.example.main.input";
	public static final String FILTER_ADDANCHORONAPOSITION_EXAMPLE_OUTPUT = "filter.addanchoronaposition.example.output";
	public static final String FILTER_ADDANCHORONAPOSITION_REPORT = "filter.addanchoronaposition.report";

	// 28-AddParameterBeforeParameterFilter
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_LABEL = "filter.addparameterbeforeparameter.label";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_TOOLTIP = "filter.addparameterbeforeparameter.tooltip";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_INSTRUCTIONS = "filter.addparameterbeforeparameter.instructions";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_FIELD1 = "filter.addparameterbeforeparameter.field1";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_FIELD2 = "filter.addparameterbeforeparameter.field2";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_CHECKBOX1 = "filter.addparameterbeforeparameter.checkbox1";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_FIELD3 = "filter.addparameterbeforeparameter.field3";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_FIELD1_TOOLTIP = "filter.addparameterbeforeparameter.field1.tooltip";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_FIELD2_TOOLTIP = "filter.addparameterbeforeparameter.field2.tooltip";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_CHECKBOX1_TOOLTIP = "filter.addparameterbeforeparameter.checkbox1.tooltip";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_FIELD3_TOOLTIP = "filter.addparameterbeforeparameter.field3.tooltip";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_EXCEPTION = "filter.addparameterbeforeparameter.exception";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_EXCEPTION2 = "filter.addparameterbeforeparameter.exception2";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_COMMAND_LINE_HELP = "filter.addparameterbeforeparameter.command.line.help";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_EXAMPLE_MAIN_INPUT = "filter.addparameterbeforeparameter.example.main.input";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_EXAMPLE_OUTPUT = "filter.addparameterbeforeparameter.example.output";
	public static final String FILTER_ADDPARAMETERBEFOREPARAMETER_REPORT = "filter.addparameterbeforeparameter.report";

	// 29-AddParameterAfterfParameterFilter
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_LABEL = "filter.addparameterafterfparameter.label";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_TOOLTIP = "filter.addparameterafterfparameter.tooltip";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_INSTRUCTIONS = "filter.addparameterafterfparameter.instructions";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_FIELD1 = "filter.addparameterafterfparameter.field1";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_FIELD2 = "filter.addparameterafterfparameter.field2";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_CHECKBOX1 = "filter.addparameterafterfparameter.checkbox1";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_FIELD3 = "filter.addparameterafterfparameter.field3";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_FIELD1_TOOLTIP = "filter.addparameterafterfparameter.field1.tooltip";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_FIELD2_TOOLTIP = "filter.addparameterafterfparameter.field2.tooltip";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_CHECKBOX1_TOOLTIP = "filter.addparameterafterfparameter.checkbox1.tooltip";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_FIELD3_TOOLTIP = "filter.addparameterafterfparameter.field3.tooltip";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_EXCEPTION = "filter.addparameterafterfparameter.exception";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_EXCEPTION2 = "filter.addparameterafterfparameter.exception2";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_COMMAND_LINE_HELP = "filter.addparameterafterfparameter.command.line.help";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_EXAMPLE_MAIN_INPUT = "filter.addparameterafterfparameter.example.main.input";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_EXAMPLE_OUTPUT = "filter.addparameterafterfparameter.example.output";
	public static final String FILTER_ADDPARAMETERAFTERFPARAMETER_REPORT = "filter.addparameterafterfparameter.report";

	// 30-EnumerateLinesFilter
	public static final String FILTER_ENUMERATELINESFILTER_LABEL = "filter.enumeratelinesfilter.label";
	public static final String FILTER_ENUMERATELINESFILTER_TOOLTIP = "filter.enumeratelinesfilter.tooltip";
	public static final String FILTER_ENUMERATELINESFILTER_INSTRUCTIONS = "filter.enumeratelinesfilter.instructions";
	public static final String FILTER_ENUMERATELINESFILTER_COMMAND_LINE_HELP = "filter.enumeratelinesfilter.command.line.help";
	public static final String FILTER_ENUMERATELINESFILTER_CHECKBOX1 = "filter.enumeratelinesfilter.checkbox1";
	public static final String FILTER_ENUMERATELINESFILTER_CHECKBOX2 = "filter.enumeratelinesfilter.checkbox2";
	public static final String FILTER_ENUMERATELINESFILTER_CHECKBOX1_TOOLTIP = "filter.enumeratelinesfilter.checkbox1.tooltip";
	public static final String FILTER_ENUMERATELINESFILTER_CHECKBOX2_TOOLTIP = "filter.enumeratelinesfilter.checkbox2.tooltip";
	public static final String FILTER_ENUMERATELINESFILTER_FIELD1_TOOLTIP = "filter.enumeratelinesfilter.field1.tooltip";
	public static final String FILTER_ENUMERATELINESFILTER_FIELD1 = "filter.enumeratelinesfilter.field1";
	public static final String FILTER_ENUMERATELINESFILTER_EXAMPLE_MAIN_INPUT = "filter.enumeratelinesfilter.example.main.input";
	public static final String FILTER_ENUMERATELINESFILTER_EXAMPLE_OUTPUT = "filter.enumeratelinesfilter.example.output";
	public static final String FILTER_ENUMERATELINESFILTER_REPORT = "filter.enumeratelinesfilter.report";

	// 31-RemoveDuplicatedLinesFilter
	public static final String FILTER_REMOVEDUPLICATEDLINES_LABEL = "filter.removeduplicatedlines.label";
	public static final String FILTER_REMOVEDUPLICATEDLINES_TOOLTIP = "filter.removeduplicatedlines.tooltip";
	public static final String FILTER_REMOVEDUPLICATEDLINES_INSTRUCTIONS = "filter.removeduplicatedlines.instructions";
	public static final String FILTER_REMOVEDUPLICATEDLINES_CHECKBOX1 = "filter.removeduplicatedlines.checkbox1";
	public static final String FILTER_REMOVEDUPLICATEDLINES_CHECKBOX2 = "filter.removeduplicatedlines.checkbox2";
	public static final String FILTER_REMOVEDUPLICATEDLINES_CHECKBOX1_TOOLTIP = "filter.removeduplicatedlines.checkbox1.tooltip";
	public static final String FILTER_REMOVEDUPLICATEDLINES_CHECKBOX2_TOOLTIP = "filter.removeduplicatedlines.checkbox2.tooltip";
	public static final String FILTER_REMOVEDUPLICATEDLINES_COMMAND_LINE_HELP = "filter.removeduplicatedlines.command.line.help";
	public static final String FILTER_REMOVEDUPLICATEDLINES_EXAMPLE_MAIN_INPUT = "filter.removeduplicatedlines.example.main.input";
	public static final String FILTER_REMOVEDUPLICATEDLINES_EXAMPLE_OUTPUT = "filter.removeduplicatedlines.example.output";
	public static final String FILTER_REMOVEDUPLICATEDLINES_REPORT = "filter.removeduplicatedlines.report";

	// 32-EliminateAllDuplicatedLinesFilter
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_LABEL = "filter.eliminateallduplicatedlines.label";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_TOOLTIP = "filter.eliminateallduplicatedlines.tooltip";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_INSTRUCTIONS = "filter.eliminateallduplicatedlines.instructions";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_CHECKBOX1 = "filter.eliminateallduplicatedlines.checkbox1";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_CHECKBOX2 = "filter.eliminateallduplicatedlines.checkbox2";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_CHECKBOX1_TOOLTIP = "filter.eliminateallduplicatedlines.checkbox1.tooltip";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_CHECKBOX2_TOOLTIP = "filter.eliminateallduplicatedlines.checkbox2.tooltip";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_COMMAND_LINE_HELP = "filter.eliminateallduplicatedlines.command.line.help";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_EXAMPLE_MAIN_INPUT = "filter.eliminateallduplicatedlines.example.main.input";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_EXAMPLE_OUTPUT = "filter.eliminateallduplicatedlines.example.output";
	public static final String FILTER_ELIMINATEALLDUPLICATEDLINES_REPORT = "filter.eliminateallduplicatedlines.report";

	// 33-RemoveNonDuplicatedLinesFilter
	public static final String FILTER_REMOVENONDUPLICATEDLINES_LABEL = "filter.removenonduplicatedlines.label";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_TOOLTIP = "filter.removenonduplicatedlines.tooltip";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_INSTRUCTIONS = "filter.removenonduplicatedlines.instructions";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_CHECKBOX1 = "filter.removenonduplicatedlines.checkbox1";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_CHECKBOX2 = "filter.removenonduplicatedlines.checkbox2";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_CHECKBOX1_TOOLTIP = "filter.removenonduplicatedlines.checkbox1.tooltip";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_CHECKBOX2_TOOLTIP = "filter.removenonduplicatedlines.checkbox2.tooltip";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_COMMAND_LINE_HELP = "filter.removenonduplicatedlines.command.line.help";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_EXAMPLE_MAIN_INPUT = "filter.removenonduplicatedlines.example.main.input";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_EXAMPLE_OUTPUT = "filter.removenonduplicatedlines.example.output";
	public static final String FILTER_REMOVENONDUPLICATEDLINES_REPORT = "filter.removenonduplicatedlines.report";

	// 34-RemoveEmptyLinesFilter
	public static final String FILTER_REMOVEEMPTYLINES_LABEL = "filter.removeemptylines.label";
	public static final String FILTER_REMOVEEMPTYLINES_TOOLTIP = "filter.removeemptylines.tooltip";
	public static final String FILTER_REMOVEEMPTYLINES_INSTRUCTIONS = "filter.removeemptylines.instructions";
	public static final String FILTER_REMOVEEMPTYLINES_COMMAND_LINE_HELP = "filter.removeemptylines.command.line.help";
	public static final String FILTER_REMOVEEMPTYLINES_EXAMPLE_MAIN_INPUT = "filter.removeemptylines.example.main.input";
	public static final String FILTER_REMOVEEMPTYLINES_EXAMPLE_OUTPUT = "filter.removeemptylines.example.output";
	public static final String FILTER_REMOVEEMPTYLINES_REPORT = "filter.removeemptylines.report";

	// 35-RemoveLinesWithContentFilter
	public static final String FILTER_REMOVELINESWITHCONTENT_LABEL = "filter.removelineswithcontent.label";
	public static final String FILTER_REMOVELINESWITHCONTENT_TOOLTIP = "filter.removelineswithcontent.tooltip";
	public static final String FILTER_REMOVELINESWITHCONTENT_INSTRUCTIONS = "filter.removelineswithcontent.instructions";
	public static final String FILTER_REMOVELINESWITHCONTENT_FIELD1 = "filter.removelineswithcontent.field1";
	public static final String FILTER_REMOVELINESWITHCONTENT_CHECKBOX1 = "filter.removelineswithcontent.checkbox1";
	public static final String FILTER_REMOVELINESWITHCONTENT_FIELD1_TOOLTIP = "filter.removelineswithcontent.field1.tooltip";
	public static final String FILTER_REMOVELINESWITHCONTENT_CHECKBOX1_TOOLTIP = "filter.removelineswithcontent.checkbox1.tooltip";
	public static final String FILTER_REMOVELINESWITHCONTENT_EXCEPTION = "filter.removelineswithcontent.exception";
	public static final String FILTER_REMOVELINESWITHCONTENT_COMMAND_LINE_HELP = "filter.removelineswithcontent.command.line.help";
	public static final String FILTER_REMOVELINESWITHCONTENT_EXAMPLE_MAIN_INPUT = "filter.removelineswithcontent.example.main.input";
	public static final String FILTER_REMOVELINESWITHCONTENT_EXAMPLE_OUTPUT = "filter.removelineswithcontent.example.output";
	public static final String FILTER_REMOVELINESWITHCONTENT_REPORT = "filter.removelineswithcontent.report";

	// 36-RemoveLinesWithoutContentFilter
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_LABEL = "filter.removelineswithoutcontent.label";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_TOOLTIP = "filter.removelineswithoutcontent.tooltip";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_INSTRUCTIONS = "filter.removelineswithoutcontent.instructions";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_FIELD1 = "filter.removelineswithoutcontent.field1";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_CHECKBOX1 = "filter.removelineswithoutcontent.checkbox1";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_FIELD1_TOOLTIP = "filter.removelineswithoutcontent.field1.tooltip";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_CHECKBOX1_TOOLTIP = "filter.removelineswithoutcontent.checkbox1.tooltip";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_EXCEPTION = "filter.removelineswithoutcontent.exception";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_COMMAND_LINE_HELP = "filter.removelineswithoutcontent.command.line.help";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_EXAMPLE_MAIN_INPUT = "filter.removelineswithoutcontent.example.main.input";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_EXAMPLE_OUTPUT = "filter.removelineswithoutcontent.example.output";
	public static final String FILTER_REMOVELINESWITHOUTCONTENT_REPORT = "filter.removelineswithoutcontent.report";

	// 37-RemoveLinesWithLengthFilter
	public static final String FILTER_REMVELINEWITHLENGTH_LABEL = "filter.remvelinewithlength.label";
	public static final String FILTER_REMVELINEWITHLENGTH_TOOLTIP = "filter.remvelinewithlength.tooltip";
	public static final String FILTER_REMVELINEWITHLENGTH_INSTRUCTIONS = "filter.remvelinewithlength.instructions";
	public static final String FILTER_REMVELINEWITHLENGTH_FIELD1 = "filter.remvelinewithlength.field1";
	public static final String FILTER_REMVELINEWITHLENGTH_FIELD2 = "filter.remvelinewithlength.field2";
	public static final String FILTER_REMVELINEWITHLENGTH_FIELD1_TOOLTIP = "filter.remvelinewithlength.field1.tooltip";
	public static final String FILTER_REMVELINEWITHLENGTH_FIELD2_TOOLTIP = "filter.remvelinewithlength.field2.tooltip";
	public static final String FILTER_REMVELINEWITHLENGTH_COMMAND_LINE_HELP = "filter.remvelinewithlength.command.line.help";
	public static final String FILTER_REMVELINEWITHLENGTH_EXAMPLE_MAIN_INPUT = "filter.remvelinewithlength.example.main.input";
	public static final String FILTER_REMVELINEWITHLENGTH_EXAMPLE_OUTPUT = "filter.remvelinewithlength.example.output";
	public static final String FILTER_REMVELINEWITHLENGTH_REPORT = "filter.remvelinewithlength.report";

	// 38-SimpleContentReplacementFilter
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_LABEL = "filter.simplecontentreplacement.label";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_TOOLTIP = "filter.simplecontentreplacement.tooltip";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_INSTRUCTIONS = "filter.simplecontentreplacement.instructions";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_FIELD1 = "filter.simplecontentreplacement.field1";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_FIELD2 = "filter.simplecontentreplacement.field2";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_FIELD1_TOOLTIP = "filter.simplecontentreplacement.field1.tooltip";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_FIELD2_TOOLTIP = "filter.simplecontentreplacement.field2.tooltip";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_EXCEPTION1 = "filter.simplecontentreplacement.exception1";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_COMMAND_LINE_HELP = "filter.simplecontentreplacement.command.line.help";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_EXAMPLE_MAIN_INPUT = "filter.simplecontentreplacement.example.main.input";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_EXAMPLE_OUTPUT = "filter.simplecontentreplacement.example.output";
	public static final String FILTER_SIMPLECONTENTREPLACEMENT_REPORT = "filter.simplecontentreplacement.report";

	// 39-RegularExpressionReplacementFilter
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_LABEL = "filter.regularexpressionreplacement.label";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_TOOLTIP = "filter.regularexpressionreplacement.tooltip";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_INSTRUCTIONS = "filter.regularexpressionreplacement.instructions";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_FIELD1 = "filter.regularexpressionreplacement.field1";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_FIELD2 = "filter.regularexpressionreplacement.field2";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_FIELD1_TOOLTIP = "filter.regularexpressionreplacement.field1.tooltip";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_FIELD2_TOOLTIP = "filter.regularexpressionreplacement.field2.tooltip";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_EXCEPTION1 = "filter.regularexpressionreplacement.exception1";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_EXCEPTION2 = "filter.regularexpressionreplacement.exception2";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_COMMAND_LINE_HELP = "filter.regularexpressionreplacement.command.line.help";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_EXAMPLE_MAIN_INPUT = "filter.regularexpressionreplacement.example.main.input";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_EXAMPLE_OUTPUT = "filter.regularexpressionreplacement.example.output";
	public static final String FILTER_REGULAREXPRESSIONREPLACEMENT_REPORT = "filter.regularexpressionreplacement.report";

	// 40-ReplicateLineContentFilter
	public static final String FILTER_REPLICATELINECONTENT_LABEL = "filter.replicatelinecontent.label";
	public static final String FILTER_REPLICATELINECONTENT_TOOLTIP = "filter.replicatelinecontent.tooltip";
	public static final String FILTER_REPLICATELINECONTENT_INSTRUCTIONS = "filter.replicatelinecontent.instructions";
	public static final String FILTER_REPLICATELINECONTENT_FIELD1 = "filter_replicatelinecontent_field1";
	public static final String FILTER_REPLICATELINECONTENT_FIELD2 = "filter_replicatelinecontent_field2";
	public static final String FILTER_REPLICATELINECONTENT_FIELD1_TOOLTIP = "filter.replicatelinecontent.field1.tooltip";
	public static final String FILTER_REPLICATELINECONTENT_FIELD2_TOOLTIP = "filter.replicatelinecontent.field2.tooltip";
	public static final String FILTER_REPLICATELINECONTENT_EXCEPTION1 = "filter.replicatelinecontent.exception1";
	public static final String FILTER_REPLICATELINECONTENT_COMMAND_LINE_HELP = "filter.replicatelinecontent.command.line.help";
	public static final String FILTER_REPLICATELINECONTENT_EXAMPLE_MAIN_INPUT = "filter.replicatelinecontent.example.main.input";
	public static final String FILTER_REPLICATELINECONTENT_EXAMPLE_OUTPUT = "filter.replicatelinecontent.example.output";
	public static final String FILTER_REPLICATELINECONTENT_REPORT = "filter.replicatelinecontent.report";

	// 41-SortLinesAscendentFilter
	public static final String FILTER_SORTLINESASCENDENTFILTER_LABEL = "filter.sortlinesascendentfilter.label";
	public static final String FILTER_SORTLINESASCENDENTFILTER_TOOLTIP = "filter.sortlinesascendentfilter.tooltip";
	public static final String FILTER_SORTLINESASCENDENTFILTER_INSTRUCTIONS = "filter.sortlinesascendentfilter.instructions";
	public static final String FILTER_SORTLINESASCENDENTFILTER_CHECKBOX1 = "filter.sortlinesascendentfilter.checkbox1";
	public static final String FILTER_SORTLINESASCENDENTFILTER_CHECKBOX2 = "filter.sortlinesascendentfilter.checkbox2";
	public static final String FILTER_SORTLINESASCENDENTFILTER_CHECKBOX1_TOOLTIP = "filter.sortlinesascendentfilter.checkbox1.tooltip";
	public static final String FILTER_SORTLINESASCENDENTFILTER_CHECKBOX2_TOOLTIP = "filter.sortlinesascendentfilter.checkbox2.tooltip";
	public static final String FILTER_SORTLINESASCENDENTFILTER_COMMAND_LINE_HELP = "filter.sortlinesascendentfilter.command.line.help";
	public static final String FILTER_SORTLINESASCENDENTFILTER_EXCEPTION1 = "filter.sortlinesascendentfilter.exception1";
	public static final String FILTER_SORTLINESASCENDENTFILTER_EXAMPLE_MAIN_INPUT = "filter.sortlinesascendentfilter.example.main.input";
	public static final String FILTER_SORTLINESASCENDENTFILTER_EXAMPLE_OUTPUT = "filter.sortlinesascendentfilter.example.output";
	public static final String FILTER_SORTLINESASCENDENTFILTER_REPORT = "filter.sortlinesascendentfilter.report";

	// 42-SortLinesDescendentFilter
	public static final String FILTER_SORTLINESDESCENDENTFILTER_LABEL = "filter.sortlinesdescendentfilter.label";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_TOOLTIP = "filter.sortlinesdescendentfilter.tooltip";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_INSTRUCTIONS = "filter.sortlinesdescendentfilter.instructions";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_CHECKBOX1 = "filter.sortlinesdescendentfilter.checkbox1";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_CHECKBOX2 = "filter.sortlinesdescendentfilter.checkbox2";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_CHECKBOX1_TOOLTIP = "filter.sortlinesdescendentfilter.checkbox1.tooltip";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_CHECKBOX2_TOOLTIP = "filter.sortlinesdescendentfilter.checkbox2.tooltip";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_EXCEPTION1 = "filter.sortlinesdescendentfilter.exception1";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_COMMAND_LINE_HELP = "filter.sortlinesdescendentfilter.command.line.help";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_EXAMPLE_MAIN_INPUT = "filter.sortlinesdescendentfilter.example.main.input";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_EXAMPLE_OUTPUT = "filter.sortlinesdescendentfilter.example.output";
	public static final String FILTER_SORTLINESDESCENDENTFILTER_REPORT = "filter.sortlinesdescendentfilter.report";

	// 43-SortLinesAscendentBasedOnPositionFilter
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_LABEL = "filter.sortlinesascendentbasedonpositionfilter.label";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_TOOLTIP = "filter.sortlinesascendentbasedonpositionfilter.tooltip";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_INSTRUCTIONS = "filter.sortlinesascendentbasedonpositionfilter.instructions";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_CHECKBOX1 = "filter.sortlinesascendentbasedonpositionfilter.checkbox1";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_FIELD1 = "filter.sortlinesascendentbasedonpositionfilter.field1";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_FIELD1_TOOLTIP = "filter.sortlinesascendentbasedonpositionfilter.field1.tooltip";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_CHECKBOX1_TOOLTIP = "filter.sortlinesascendentbasedonpositionfilter.checkbox1.tooltip";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_EXCEPTION1 = "filter.sortlinesascendentbasedonpositionfilter.exception1";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_EXCEPTION2 = "filter.sortlinesascendentbasedonpositionfilter.exception2";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_COMMAND_LINE_HELP = "filter.sortlinesascendentbasedonpositionfilter.command.line.help";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_EXAMPLE_MAIN_INPUT = "filter.sortlinesascendentbasedonpositionfilter.example.main.input";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_EXAMPLE_OUTPUT = "filter.sortlinesascendentbasedonpositionfilter.example.output";
	public static final String FILTER_SORTLINESASCENDENTBASEDONPOSITIONFILTER_REPORT = "filter.sortlinesascendentbasedonpositionfilter.report";

	// 44-SortLinesDescendentBasedOnPositionFilter
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_LABEL = "filter.sortlinesdescendentbasedonpositionfilter.label";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_TOOLTIP = "filter.sortlinesdescendentbasedonpositionfilter.tooltip";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_INSTRUCTIONS = "filter.sortlinesdescendentbasedonpositionfilter.instructions";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_CHECKBOX1 = "filter.sortlinesdescendentbasedonpositionfilter.checkbox1";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_FIELD1 = "filter.sortlinesdescendentbasedonpositionfilter.field1";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_FIELD1_TOOLTIP = "filter.sortlinesdescendentbasedonpositionfilter.field1.tooltip";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_CHECKBOX1_TOOLTIP = "filter.sortlinesdescendentbasedonpositionfilter.checkbox1.tooltip";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_EXCEPTION1 = "filter.sortlinesdescendentbasedonpositionfilter.exception1";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_EXCEPTION2 = "filter.sortlinesdescendentbasedonpositionfilter.exception2";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_COMMAND_LINE_HELP = "filter.sortlinesdescendentbasedonpositionfilter.command.line.help";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_EXAMPLE_MAIN_INPUT = "filter.sortlinesdescendentbasedonpositionfilter.example.main.input";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_EXAMPLE_OUTPUT = "filter.sortlinesdescendentbasedonpositionfilter.example.output";
	public static final String FILTER_SORTLINESDESCENDENTBASEDONPOSITIONFILTER_REPORT = "filter.sortlinesdescendentbasedonpositionfilter.report";

	// 45-SortLinesAscendentByLengthFilter
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_LABEL = "filter.sortlinesascendentbylengthfilter.label";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_TOOLTIP = "filter.sortlinesascendentbylengthfilter.tooltip";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_INSTRUCTIONS = "filter.sortlinesascendentbylengthfilter.instructions";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_CHECKBOX1 = "filter.sortlinesascendentbylengthfilter.checkbox1";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_CHECKBOX1_TOOLTIP = "filter.sortlinesascendentbylengthfilter.checkbox1.tooltip";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_EXCEPTION1 = "filter.sortlinesascendentbylengthfilter.exception1";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_COMMAND_LINE_HELP = "filter.sortlinesascendentbylengthfilter.command.line.help";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_EXAMPLE_MAIN_INPUT = "filter.sortlinesascendentbylengthfilter.example.main.input";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_EXAMPLE_OUTPUT = "filter.sortlinesascendentbylengthfilter.example.output";
	public static final String FILTER_SORTLINESASCENDENTBYLENGTHFILTER_REPORT = "filter.sortlinesascendentbylengthfilter.report";

	// 46-InvertOrderFilter
	public static final String FILTER_INVERTORDERFILTER_LABEL = "filter.invertorderfilter.label";
	public static final String FILTER_INVERTORDERFILTER_TOOLTIP = "filter.invertorderfilter.tooltip";
	public static final String FILTER_INVERTORDERFILTER_INSTRUCTIONS = "filter.invertorderfilter.instructions";
	public static final String FILTER_INVERTORDERFILTER_COMMAND_LINE_HELP = "filter.invertorderfilter.command.line.help";
	public static final String FILTER_INVERTORDERFILTER_EXAMPLE_MAIN_INPUT = "filter.invertorderfilter.example.main.input";
	public static final String FILTER_INVERTORDERFILTER_EXAMPLE_OUTPUT = "filter.invertorderfilter.example.output";
	public static final String FILTER_INVERTORDERFILTER_REPORT = "filter.invertorderfilter.report";

	// 47-ScrambleLinesFilter
	public static final String FILTER_SCRAMBLELINESFILTER_LABEL = "filter.scramblelinesfilter.label";
	public static final String FILTER_SCRAMBLELINESFILTER_TOOLTIP = "filter.scramblelinesfilter.tooltip";
	public static final String FILTER_SCRAMBLELINESFILTER_INSTRUCTIONS = "filter.scramblelinesfilter.instructions";
	public static final String FILTER_SCRAMBLELINESFILTER_COMMAND_LINE_HELP = "filter.scramblelinesfilter.command.line.help";
	public static final String FILTER_SCRAMBLELINESFILTER_EXAMPLE_MAIN_INPUT = "filter.scramblelinesfilter.example.main.input";
	public static final String FILTER_SCRAMBLELINESFILTER_EXAMPLE_OUTPUT = "filter.scramblelinesfilter.example.output";
	public static final String FILTER_SCRAMBLELINESFILTER_REPORT = "filter.scramblelinesfilter.report";

	// 48-CombineAllLinesFilter
	public static final String FILTER_COMBINEALLLINESFILTER_LABEL = "filter.combinealllinesfilter.label";
	public static final String FILTER_COMBINEALLLINESFILTER_TOOLTIP = "filter.combinealllinesfilter.tooltip";
	public static final String FILTER_COMBINEALLLINESFILTER_FIELD_1 = "filter.combinealllinesfilter.field.1";
	public static final String FILTER_COMBINEALLLINESFILTER_FIELD_1_TOOLTIP = "filter.combinealllinesfilter.field.1.tooltip";
	public static final String FILTER_COMBINEALLLINESFILTER_FIELD_2 = "filter.combinealllinesfilter.field.2";
	public static final String FILTER_COMBINEALLLINESFILTER_FIELD_2_TOOLTIP = "filter.combinealllinesfilter.field.2.tooltip";
	public static final String FILTER_COMBINEALLLINESFILTER_INSTRUCTIONS = "filter.combinealllinesfilter.instructions";
	public static final String FILTER_COMBINEALLLINESFILTER_COMMAND_LINE_HELP = "filter.combinealllinesfilter.command.line.help";
	public static final String FILTER_COMBINEALLLINESFILTER_EXAMPLE_MAIN_INPUT = "filter.combinealllinesfilter.example.main.input";
	public static final String FILTER_COMBINEALLLINESFILTER_EXAMPLE_OUTPUT = "filter.combinealllinesfilter.example.output";
	public static final String FILTER_COMBINEALLLINESFILTER_REPORT = "filter.combinealllinesfilter.report";

	// 49-CombineAllLinesFilter
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_LABEL = "filter.combinelinesataregularstepfilter.label";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_TOOLTIP = "filter.combinelinesataregularstepfilter.tooltip";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_FIELD_1 = "filter.combinelinesataregularstepfilter.field.1";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_FIELD_1_TOOLTIP = "filter.combinelinesataregularstepfilter.field.1.tooltip";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_FIELD_2 = "filter.combinelinesataregularstepfilter.field.2";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_FIELD_2_TOOLTIP = "filter.combinelinesataregularstepfilter.field.2.tooltip";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_INSTRUCTIONS = "filter.combinelinesataregularstepfilter.instructions";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_COMMAND_LINE_HELP = "filter.combinelinesataregularstepfilter.command.line.help";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_EXAMPLE_MAIN_INPUT = "filter.combinelinesataregularstepfilter.example.main.input";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_EXAMPLE_OUTPUT = "filter.combinelinesataregularstepfilter.example.output";
	public static final String FILTER_COMBINELINESATAREGULARSTEPFILTER_REPORT = "filter.combinelinesataregularstepfilter.report";

	// 50-MaintainLinesAtRegularStepFilter
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_LABEL = "filter.maintainlinesatregularstepfilter.label";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_TOOLTIP = "filter.maintainlinesatregularstepfilter.tooltip";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_INSTRUCTIONS = "filter.maintainlinesatregularstepfilter.instructions";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_FIELD1 = "filter.maintainlinesatregularstepfilter.field1";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_FIELD1_TOOLTIP = "filter.maintainlinesatregularstepfilter.field1.tooltip";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_EXCEPTION1 = "filter.maintainlinesatregularstepfilter.exception1";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_EXCEPTION2 = "filter.maintainlinesatregularstepfilter.exception2";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_COMMAND_LINE_HELP = "filter.maintainlinesatregularstepfilter.command.line.help";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_EXAMPLE_MAIN_INPUT = "filter.maintainlinesatregularstepfilter.example.main.input";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_EXAMPLE_OUTPUT = "filter.maintainlinesatregularstepfilter.example.output";
	public static final String FILTER_MAINTAINLINESATREGULARSTEPFILTER_REPORT = "filter.maintainlinesatregularstepfilter.report";

	// 51-SwapContentBasedOnParameterFilter
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_LABEL = "filter.swapcontentbasedonposition.label";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_TOOLTIP = "filter.swapcontentbasedonposition.tooltip";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_INSTRUCTIONS = "filter.swapcontentbasedonposition.instructions";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_FIELD1 = "filter.swapcontentbasedonposition.field1";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_FIELD1_TOOLTIP = "filter.swapcontentbasedonposition.field1.tooltip";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_CHECKBOX1 = "filter.swapcontentbasedonposition.checkbox1";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_CHECKBOX1_TOOLTIP = "filter.swapcontentbasedonposition.checkbox1.tooltip";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_EXCEPTION = "filter.swapcontentbasedonposition.exception";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_COMMAND_LINE_HELP = "filter.swapcontentbasedonposition.command.line.help";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_EXAMPLE_MAIN_INPUT = "filter.swapcontentbasedonposition.example.main.input";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_EXAMPLE_OUTPUT = "filter.swapcontentbasedonposition.example.output";
	public static final String FILTER_SWAPCONTENTBASEDONPOSITION_REPORT = "filter.swapcontentbasedonposition.report";

	// 52-RemoveLinesThatExistOnMainAndAuxiliar
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_LABEL = "filter.removelinesthatexistonmainandauxiliar.label";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_TOOLTIP = "filter.removelinesthatexistonmainandauxiliar.tooltip";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_INSTRUCTIONS = "filter.removelinesthatexistonmainandauxiliar.instructions";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_COMMAND_LINE_HELP = "filter.removelinesthatexistonmainandauxiliar.command.line.help";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_CHECKBOX_1 = "filter.removelinesthatexistonmainandauxiliar.checkbox.1";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_CHECKBOX_1_TOOLTIP = "filter.removelinesthatexistonmainandauxiliar.checkbox.1.tooltip";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_EXAMPLE_MAIN_INPUT = "filter.removelinesthatexistonmainandauxiliar.example.main.input";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_EXAMPLE_AUXILIAR_INPUT = "filter.removelinesthatexistonmainandauxiliar.example.auxiliar.input";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_EXAMPLE_OUTPUT = "filter.removelinesthatexistonmainandauxiliar.example.output";
	public static final String FILTER_REMOVELINESTHATEXISTONMAINANDAUXILIAR_REPORT = "filter.removelinesthatexistonmainandauxiliar.report";

	// 53-RemoveLinesThatDoNotExistOnMainAndAuxiliar
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_LABEL = "filter.removelinesthatdonotexistonmainandauxiliar.label";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_TOOLTIP = "filter.removelinesthatdonotexistonmainandauxiliar.tooltip";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_INSTRUCTIONS = "filter.removelinesthatdonotexistonmainandauxiliar.instructions";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_COMMAND_LINE_HELP = "filter.removelinesthatdonotexistonmainandauxiliar.command.line.help";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_CHECKBOX_1 = "filter.removelinesthatdonotexistonmainandauxiliar.checkbox.1";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_CHECKBOX_1_TOOLTIP = "filter.removelinesthatdonotexistonmainandauxiliar.checkbox.1.tooltip";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_EXAMPLE_MAIN_INPUT = "filter.removelinesthatdonotexistonmainandauxiliar.example.main.input";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_EXAMPLE_AUXILIAR_INPUT = "filter.removelinesthatdonotexistonmainandauxiliar.example.auxiliar.input";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_EXAMPLE_OUTPUT = "filter.removelinesthatdonotexistonmainandauxiliar.example.output";
	public static final String FILTER_REMOVELINESTHATDONOTEXISTONMAINANDAUXILIAR_REPORT = "filter.removelinesthatdonotexistonmainandauxiliar.report";

	// 54-CombineMainWithAuxiliar
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_LABEL = "filter.combinemainwithauxiliar.label";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_TOOLTIP = "filter.combinemainwithauxiliar.tooltip";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_INSTRUCTIONS = "filter.combinemainwithauxiliar.instructions";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_COMMAND_LINE_HELP = "filter.combinemainwithauxiliar.command.line.help";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_FIELD_1 = "filter.combinemainwithauxiliar.field.1";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_FIELD_1_TOOLTIP = "filter.combinemainwithauxiliar.field.1.tooltip";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_EXAMPLE_MAIN_INPUT = "filter.combinemainwithauxiliar.example.main.input";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_EXAMPLE_AUXILIAR_INPUT = "filter.combinemainwithauxiliar.example.auxiliar.input";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_EXAMPLE_OUTPUT = "filter.combinemainwithauxiliar.example.output";
	public static final String FILTER_COMBINEMAINWITHAUXILIAR_REPORT = "filter.combinemainwithauxiliar.report";

	// 55-IntercalateMainWithAuxiliar
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_LABEL = "filter.intercalatemainwithauxiliar.label";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_TOOLTIP = "filter.intercalatemainwithauxiliar.tooltip";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_INSTRUCTIONS = "filter.intercalatemainwithauxiliar.instructions";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_FIELD_1 = "filter.intercalatemainwithauxiliar.field.1";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_FIELD_1_TOOLTIP = "filter.intercalatemainwithauxiliar.field.1.tooltip";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_COMMAND_LINE_HELP = "filter.intercalatemainwithauxiliar.command.line.help";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_EXAMPLE_AUXILIAR_INPUT = "filter.intercalatemainwithauxiliar.example.auxiliar.input";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_EXAMPLE_MAIN_INPUT = "filter.intercalatemainwithauxiliar.example.main.input";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_EXAMPLE_OUTPUT = "filter.intercalatemainwithauxiliar.example.output";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_REPORT = "filter.intercalatemainwithauxiliar.report";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_EXCEPTION_1 = "filter.intercalatemainwithauxiliar.exception.1";
	public static final String FILTER_INTERCALATEMAINWITHAUXILIAR_EXCEPTION_2 = "filter.intercalatemainwithauxiliar.exception.2";

	// 56-MultipleInsertAuxiliarIntoMainFilter
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_LABEL = "filter.multipleinsertauxiliarintomain.label";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_TOOLTIP = "filter.multipleinsertauxiliarintomain.tooltip";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_INSTRUCTIONS = "filter.multipleinsertauxiliarintomain.instructions";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_FIELD_1 = "filter.multipleinsertauxiliarintomain.field.1";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_FIELD_1_TOOLTIP = "filter.multipleinsertauxiliarintomain.field.1.tooltip";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_COMMAND_LINE_HELP = "filter.multipleinsertauxiliarintomain.command.line.help";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_EXAMPLE_AUXILIAR_INPUT = "filter.multipleinsertauxiliarintomain.example.auxiliar.input";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_EXAMPLE_MAIN_INPUT = "filter.multipleinsertauxiliarintomain.example.main.input";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_EXAMPLE_OUTPUT = "filter.multipleinsertauxiliarintomain.example.output";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_REPORT = "filter.multipleinsertauxiliarintomain.report";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_EXCEPTION1 = "filter.multipleinsertauxiliarintomain.exception1";
	public static final String FILTER_MULTIPLEINSERTAUXILIARINTOMAIN_EXCEPTION2 = "filter.multipleinsertauxiliarintomain.exception2";

	// 57-ReplaceMultipleTexts
	public static final String FILTER_REPLACEMULTIPLETEXTS_LABEL = "filter.replacemultipletexts.label";
	public static final String FILTER_REPLACEMULTIPLETEXTS_TOOLTIP = "filter.replacemultipletexts.tooltip";
	public static final String FILTER_REPLACEMULTIPLETEXTS_INSTRUCTIONS = "filter.replacemultipletexts.instructions";
	public static final String FILTER_REPLACEMULTIPLETEXTS_FIELD_1 = "filter.replacemultipletexts.field.1";
	public static final String FILTER_REPLACEMULTIPLETEXTS_FIELD_1_TOOLTIP = "filter.replacemultipletexts.field.1.tooltip";
	public static final String FILTER_REPLACEMULTIPLETEXTS_COMMAND_LINE_HELP = "filter.replacemultipletexts.command.line.help";
	public static final String FILTER_REPLACEMULTIPLETEXTS_EXAMPLE_AUXILIAR_INPUT = "filter.replacemultipletexts.example.auxiliar.input";
	public static final String FILTER_REPLACEMULTIPLETEXTS_EXAMPLE_MAIN_INPUT = "filter.replacemultipletexts.example.main.input";
	public static final String FILTER_REPLACEMULTIPLETEXTS_EXAMPLE_OUTPUT = "filter.replacemultipletexts.example.output";
	public static final String FILTER_REPLACEMULTIPLETEXTS_REPORT = "filter.replacemultipletexts.report";

	// 58-RemoveLinesWithMultipleParameters
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_LABEL = "filter.removelineswithmultipleparameters.label";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_TOOLTIP = "filter.removelineswithmultipleparameters.tooltip";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_INSTRUCTIONS = "filter.removelineswithmultipleparameters.instructions";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_CHECKBOX_1 = "filter.removelineswithmultipleparameters.checkbox.1";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_CHECKBOX_1_TOOLTIP = "filter.removelineswithmultipleparameters.checkbox.1.tooltip";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_COMMAND_LINE_HELP = "filter.removelineswithmultipleparameters.command.line.help";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_EXAMPLE_AUXILIAR_INPUT = "filter.removelineswithmultipleparameters.example.auxiliar.input";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_EXAMPLE_MAIN_INPUT = "filter.removelineswithmultipleparameters.example.main.input";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_EXAMPLE_OUTPUT = "filter.removelineswithmultipleparameters.example.output";
	public static final String FILTER_REMOVELINESWITHMULTIPLEPARAMETERS_REPORT = "filter.removelineswithmultipleparameters.report";

	// 59-RemoveLinesWithoutMultipleParameters
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_LABEL = "filter.removelineswithoutmultipleparameters.label";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_TOOLTIP = "filter.removelineswithoutmultipleparameters.tooltip";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_INSTRUCTIONS = "filter.removelineswithoutmultipleparameters.instructions";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_CHECKBOX_1 = "filter.removelineswithoutmultipleparameters.checkbox.1";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_CHECKBOX_1_TOOLTIP = "filter.removelineswithoutmultipleparameters.checkbox.1.tooltip";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_COMMAND_LINE_HELP = "filter.removelineswithoutmultipleparameters.command.line.help";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_EXAMPLE_AUXILIAR_INPUT = "filter.removelineswithoutmultipleparameters.example.auxiliar.input";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_EXAMPLE_MAIN_INPUT = "filter.removelineswithoutmultipleparameters.example.main.input";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_EXAMPLE_OUTPUT = "filter.removelineswithoutmultipleparameters.example.output";
	public static final String FILTER_REMOVELINESWITHOUTMULTIPLEPARAMETERS_REPORT = "filter.removelineswithoutmultipleparameters.report";

	// 60-InsertAuxiliarIntoMain
	public static final String FILTER_INSERTAUXILIARINTOMAIN_LABEL = "filter.insertauxiliarintomain.label";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_TOOLTIP = "filter.insertauxiliarintomain.tooltip";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_INSTRUCTIONS = "filter.insertauxiliarintomain.instructions";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_FIELD_1 = "filter.insertauxiliarintomain.field.1";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_FIELD_2 = "filter.insertauxiliarintomain.field.2";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_FIELD_1_TOOLTIP = "filter.insertauxiliarintomain.field.1.tooltip";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_FIELD_2_TOOLTIP = "filter.insertauxiliarintomain.field.2.tooltip";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_CHECKBOX_1 = "filter.insertauxiliarintomain.checkbox.1";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_CHECKBOX_1_TOOLTIP = "filter.insertauxiliarintomain.checkbox.1.tooltip";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_COMMAND_LINE_HELP = "filter.insertauxiliarintomain.command.line.help";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_EXAMPLE_AUXILIAR_INPUT = "filter.insertauxiliarintomain.example.auxiliar.input";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_EXAMPLE_MAIN_INPUT = "filter.insertauxiliarintomain.example.main.input";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_EXAMPLE_OUTPUT = "filter.insertauxiliarintomain.example.output";
	public static final String FILTER_INSERTAUXILIARINTOMAIN_REPORT = "filter.insertauxiliarintomain.report";

	// 61-InsertAuxiliarBetweenParameters
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_LABEL = "filter.insertauxiliarbetweenparameters.label";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_TOOLTIP = "filter.insertauxiliarbetweenparameters.tooltip";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_INSTRUCTIONS = "filter.insertauxiliarbetweenparameters.instructions";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_FIELD_1 = "filter.insertauxiliarbetweenparameters.field.1";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_FIELD_2 = "filter.insertauxiliarbetweenparameters.field.2";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_CHECKBOX_1 = "filter.insertauxiliarbetweenparameters.checkbox.1";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_FIELD_1_TOOLTIP = "filter.insertauxiliarbetweenparameters.field.1.tooltip";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_FIELD_2_TOOLTIP = "filter.insertauxiliarbetweenparameters.field.2.tooltip";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_CHECKBOX_1_TOOLTIP = "filter.insertauxiliarbetweenparameters.checkbox.1.tooltip";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_COMMAND_LINE_HELP = "filter.insertauxiliarbetweenparameters.command.line.help";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXAMPLE_AUXILIAR_INPUT = "filter.insertauxiliarbetweenparameters.example.auxiliar.input";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXAMPLE_MAIN_INPUT = "filter.insertauxiliarbetweenparameters.example.main.input";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXAMPLE_OUTPUT = "filter.insertauxiliarbetweenparameters.example.output";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_REPORT = "filter.insertauxiliarbetweenparameters.report";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXCEPTION_1 = "filter.insertauxiliarbetweenparameters.exception.1";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXCEPTION_2 = "filter.insertauxiliarbetweenparameters.exception.2";
	public static final String FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXCEPTION_3 = "filter.insertauxiliarbetweenparameters.exception.3";

	// 62-CharacterSelectionFilter
	public static final String FILTER_CHARACTER_SELECTION_TITLE = "filter.character.selection.title";
	public static final String FILTER_CHARACTER_SELECTION_LABEL = "filter.character.selection.label";
	public static final String FILTER_CHARACTER_SELECTION_TOOLTIP = "filter.character.selection.tooltip";
	public static final String FILTER_CHARACTER_SELECTION_INSTRUCTIONS = "filter.character.selection.instructions";
	public static final String FILTER_CHARACTER_SELECTION_EXAMPLE_MAIN_INPUT = "filter.character.selection.example.main.input";
	public static final String FILTER_CHARACTER_SELECTION_EXAMPLE_OUTPUT = "filter.character.selection.example.output";
	public static final String FILTER_CHARACTER_SELECTION_NO_CONTENT_TITLE = "filter.character.selection.no.content.title";
	public static final String FILTER_CHARACTER_SELECTION_NO_CONTENT_MESSAGE = "filter.character.selection.no.content.message";

	// 63-WordSelectionFilter
	public static final String FILTER_WORD_SELECTION_TITLE = "filter.word.selection.title";
	public static final String FILTER_WORD_SELECTION_LABEL = "filter.word.selection.label";
	public static final String FILTER_WORD_SELECTION_TOOLTIP = "filter.word.selection.tooltip";
	public static final String FILTER_WORD_SELECTION_INSTRUCTIONS = "filter.word.selection.instructions";
	public static final String FILTER_WORD_SELECTION_EXAMPLE_MAIN_INPUT = "filter.word.selection.example.main.input";
	public static final String FILTER_WORD_SELECTION_EXAMPLE_OUTPUT = "filter.word.selection.example.output";
	public static final String FILTER_WORD_SELECTION_NO_CONTENT_TITLE = "filter.word.selection.no.content.title";
	public static final String FILTER_WORD_SELECTION_NO_CONTENT_MESSAGE = "filter.word.selection.no.content.message";

	// 64-CharacterMoveFilter
	public static final String FILTER_CHARACTER_MOVE_LABEL = "filter.character.move.label";
	public static final String FILTER_CHARACTER_MOVE_TOOLTIP = "filter.character.move.tooltip";
	public static final String FILTER_CHARACTER_MOVE_INSTRUCTIONS = "filter.character.move.instructions";
	public static final String FILTER_CHARACTER_MOVE_EXAMPLE_MAIN_INPUT = "filter.character.move.example.main.input";
	public static final String FILTER_CHARACTER_MOVE_EXAMPLE_OUTPUT = "filter.character.move.example.output";
	public static final String FILTER_CHARACTER_MOVE_TITLE = "filter.character.move.title";
	public static final String FILTER_CHARACTER_MOVE_OK = "filter.character.move.ok";
	public static final String FILTER_CHARACTER_MOVE_OK_INSTRUCTION = "filter.character.move.ok.instruction";
	public static final String FILTER_CHARACTER_MOVE_ELEMENT_INSTRUCTION = "filter.character.move.element.instruction";
	public static final String FILTER_CHARACTER_MOVE_NO_CONTENT_MESSAGE = "filter.character.move.no.content.message";
	public static final String FILTER_CHARACTER_MOVE_NO_CONTENT_TITLE = "filter.character.move.no.content.title";

	// 65-WordSelectionToTableFilter
	public static final String FILTER_WORD_SELECTION_TO_TABLE_LABEL = "filter.word.selection.to.table.label";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_TOOLTIP = "filter.word.selection.to.table.tooltip";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_INSTRUCTIONS = "filter.word.selection.to.table.instructions";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_TITLE = "filter.word.selection.to.table.title";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_EXAMPLE_MAIN_INPUT = "filter.word.selection.to.table.example.main.input";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_EXAMPLE_OUTPUT = "filter.word.selection.to.table.example.output";
	public static final String ADD_COLUMN_TO_THE_RIGHT_BUTTOM = "add.column.to.the.right.buttom";
	public static final String ADD_COLUMN_TO_THE_RIGHT_INSTRUCTIONS = "add.column.to.the.right.instructions";
	public static final String ADD_COLUMN_TO_THE_RIGHT_KEY = "add.column.to.the.right.key";
	public static final String ADD_COLUMN_TO_THE_LEFT_BUTTOM = "add.column.to.the.left.buttom";
	public static final String ADD_COLUMN_TO_THE_LEFT_INSTRUCTIONS = "add.column.to.the.left.instructions";
	public static final String ADD_COLUMN_TO_THE_LEFT_KEY = "add.column.to.the.left.key";
	public static final String TABLE_FORMAT_SELECTION_DIALOG_TITLE = "table.format.selection.dialog.title";
	public static final String TABLE_FORMAT_SELECTION_LIST = "table.format.selection.list";
	public static final String TABLE_FORMAT_SELECTION_LIST_TITLE = "table.format.selection.list.title";
	public static final String TABLE_FORMAT_CUSTOM_SELECTION_TITLE = "table.format.custom.selection.title";
	public static final String TABLE_FORMAT_CUSTOM_SELECTION_CHOICE = "table.format.custom.selection.choice";
	public static final String TABLE_FORMAT_CUSTOM_SYMBOL_BEFORE_CELL = "table.format.custom.symbol.before.cell";
	public static final String TABLE_FORMAT_CUSTOM_SYMBOL_AFTER_CELL = "table.format.custom.symbol.after.cell";
	public static final String TABLE_FORMAT_CUSTOM_SYMBOL_BETWEEN_CELL = "table.format.custom.symbol.between.cell";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_OK_BUTTOM = "filter.word.selection.to.table.ok.buttom";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_OK_BUTTOM_INSTRUCTION = "filter.word.selection.to.table.ok.buttom.instruction";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_CANCEL_BUTTOM = "filter.word.selection.to.table.cancel.buttom";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_CANCEL_BUTTOM_INSTRUCTION = "filter.word.selection.to.table.cancel.buttom.instruction";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_TEXT_POSITION = "filter.word.selection.to.table.text.position";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_LEFT = "filter.word.selection.to.table.left";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_RIGHT = "filter.word.selection.to.table.right";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_CENTER = "filter.word.selection.to.table.center";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_SIMPLE = "filter.word.selection.to.table.simple";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_ROUNDED = "filter.word.selection.to.table.rounded";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_SINGLE_LINE = "filter.word.selection.to.table.single.line";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_SHADOW_LIGHT = "filter.word.selection.to.table.shadow.light";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_SHADOW_MEDIUM = "filter.word.selection.to.table.shadow.medium";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_SHADOW_MEDIUM_2 = "filter.word.selection.to.table.shadow.medium.2";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_SHADOW_STRONG = "filter.word.selection.to.table.shadow.strong";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_SHADOW_STRONG_2 = "filter.word.selection.to.table.shadow.strong.2";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_SHADOW_STRONG_3 = "filter.word.selection.to.table.shadow.strong.3";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_THICK_LINES = "filter.word.selection.to.table.thick.lines";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_THICK_LINES_2 = "filter.word.selection.to.table.thick.lines.2";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_THICK_LINES_3 = "filter.word.selection.to.table.thick.lines.3";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_THICK_LINES_4 = "filter.word.selection.to.table.thick.lines.4";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_LINE = "filter.word.selection.to.table.double.line";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_BORDER = "filter.word.selection.to.table.double.border";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_INTERNAL = "filter.word.selection.to.table.double.internal";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_HORIZONTAL = "filter.word.selection.to.table.double.horizontal";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_VERTICAL = "filter.word.selection.to.table.double.vertical";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_HORIZONTAL_2 = "filter.word.selection.to.table.double.horizontal.2";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_VERTICAL_2 = "filter.word.selection.to.table.double.vertical.2";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_ONLY_BORDER_1 = "filter.word.selection.to.table.only.border.1";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_ONLY_BORDER_2 = "filter.word.selection.to.table.only.border.2";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_ONLY_BORDER_3 = "filter.word.selection.to.table.only.border.3";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_ONLY_BORDER_4 = "filter.word.selection.to.table.only.border.4";
	public static final String REMOVE_SELECTED_COLUMN_BUTTON_TITLE = "remove.selected.column.button.title";
	public static final String REMOVE_SELECTED_COLUMN_BUTTON_INSTRUCTIONS = "remove.selected.column.button.instructions";
	public static final String REMOVE_SELECTED_COLUMN_ERROR_MESSAGE_TITLE = "remove.selected.column.error.message.title";
	public static final String REMOVE_SELECTED_COLUMN_ERROR_MESSAGE = "remove.selected.column.error.message";
	public static final String ADD_SELECTED_ERROR_NO_SELECTION_MESSAGE_TITLE = "add.selected.error.no.selection.message.title";
	public static final String ADD_SELECTED_ERROR_NO_SELECTION_MESSAGE = "add.selected.error.no.selection.message";
	public static final String ADD_SELECTED_ELEMENT_INSTRUCTION = "add.selected.element.instruction";
	public static final String ADD_SELECTED_ELEMENT_OK = "add.selected.element.ok";
	public static final String ADD_SELECTED_ELEMENT_OK_INSTRUCTION = "add.selected.element.ok.instruction";
	public static final String ADD_SELECTED_ELEMENT_INVERT = "add.selected.element.invert";
	public static final String ADD_SELECTED_ELEMENT_INVERT_INSTRUCTION = "add.selected.element.invert.instruction";
	public static final String ADD_SELECTED_ELEMENT_CANCEL = "add.selected.element.cancel";
	public static final String ADD_SELECTED_ELEMENT_CANCEL_INSTRUCTION = "add.selected.element.cancel.instruction";
	public static final String SELECT_ELEMENT_INSTRUCTION = "select.element.instruction";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_NO_CONTENT_TITLE = "filter.word.selection.to.table.no.content.title";
	public static final String FILTER_WORD_SELECTION_TO_TABLE_NO_CONTENT_MESSAGE = "filter.word.selection.to.table.no.content.message";
	public static final String FILTER_WORD_SELECTION_TO_INSTRUCTION_1 = "filter.word.selection.to.instruction.1";
	public static final String FILTER_WORD_SELECTION_TO_INSTRUCTION_2 = "filter.word.selection.to.instruction.2";
	public static final String FILTER_WORD_SELECTION_TO_INSTRUCTION_3 = "filter.word.selection.to.instruction.3";
	public static final String FILTER_WORD_SELECTION_TO_INSTRUCTION_4 = "filter.word.selection.to.instruction.4";

	// 66-ReplaceAndRenameFilter
	public static final String FILTER_REPLACEANDRENAMEFILTER_LABEL = "filter.ReplaceAndRenameFilter.label";
	public static final String FILTER_REPLACEANDRENAMEFILTER_TOOLTIP = "filter.ReplaceAndRenameFilter.tooltip";
	public static final String FILTER_REPLACEANDRENAMEFILTER_INSTRUCTIONS = "filter.ReplaceAndRenameFilter.instructions";
	public static final String FILTER_REPLACEANDRENAMEFILTER_FILE1 = "filter.ReplaceAndRenameFilter.file1";
	public static final String FILTER_REPLACEANDRENAMEFILTER_INPUT_FIELD_1 = "filter.ReplaceAndRenameFilter.input.field.1";
	public static final String FILTER_REPLACEANDRENAMEFILTER_INPUT_FIELD_2 = "filter.ReplaceAndRenameFilter.input.field.2";
	public static final String FILTER_REPLACEANDRENAMEFILTER_FILE1_TOOLTIP = "filter.replaceandrenamefilter.file1.tooltip";
	public static final String FILTER_REPLACEANDRENAMEFILTER_INPUT_FIELD_1_TOOLTIP = "filter.replaceandrenamefilter.input.field.1.tooltip";
	public static final String FILTER_REPLACEANDRENAMEFILTER_INPUT_FIELD_2_TOOLTIP = "filter.replaceandrenamefilter.input.field.2.tooltip";
	public static final String FILTER_REPLACEANDRENAMEFILTER_EXCEPTION1 = "filter.ReplaceAndRenameFilter.exception1";
	public static final String FILTER_REPLACEANDRENAMEFILTER_EXCEPTION2 = "filter.ReplaceAndRenameFilter.exception2";
	public static final String FILTER_REPLACEANDRENAMEFILTER_COMMAND_LINE_HELP = "filter.ReplaceAndRenameFilter.command.line.help";
	public static final String FILTER_REPLACEANDRENAMEFILTER_EXAMPLE_MAIN_INPUT = "filter.ReplaceAndRenameFilter.example.main.input";

	// 67-DeleteFilesFilter
	public static final String FILTER_DELETEFILEFILTER_LABEL = "filter.deletefilefilter.label";
	public static final String FILTER_DELETEFILEFILTER_TOOLTIP = "filter.deletefilefilter.tooltip";
	public static final String FILTER_DELETEFILEFILTER_INSTRUCTIONS = "filter.deletefilefilter.instructions";
	public static final String FILTER_DELETEFILEFILTER_COMMAND_LINE_HELP = "filter.deletefilefilter.command.line.help";
	public static final String FILTER_DELETEFILEFILTER_EXAMPLE_MAIN_INPUT = "filter.deletefilefilter.example.main.input";
	public static final String FILTER_DELETEFILEFILTER_FILE_PROBLEM_DELETING_A_FILE = "filter.deletefilefilter.file.problem.deleting.a.file";
	public static final String FILTER_DELETEFILEFILTER_IT_IS_NOT_A_FILE = "filter.deletefilefilter.it.is.not.a.file";

	// 68-AppendFilesFilter
	public static final String FILTER_APPENDFILESFILTER_LABEL = "filter.appendfilesfilter.label";
	public static final String FILTER_APPENDFILESFILTER_TOOLTIP = "filter.appendfilesfilter.tooltip";
	public static final String FILTER_APPENDFILESFILTER_INSTRUCTIONS = "filter.appendfilesfilter.instructions";
	public static final String FILTER_APPENDFILESFILTER_COMMAND_LINE_HELP = "filter.appendfilesfilter.command.line.help";
	public static final String FILTER_APPENDFILESFILTER_EXAMPLE_MAIN_INPUT = "filter.appendfilesfilter.example.main.input";
	public static final String FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1 = "filter.appendfilesfilter.target.file.or.directory.1";
	public static final String FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_TOOLTIP_1_TOOLTIP = "filter.appendfilesfilter.target.file.or.directory.1.tooltip.1.tooltip";
	public static final String FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_1 = "filter.appendfilesfilter.target.file.or.directory.1.exception.1";
	public static final String FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2 = "filter.appendfilesfilter.target.file.or.directory.1.exception.2";
	public static final String FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2_A = "filter.appendfilesfilter.target.file.or.directory.1.exception.2.a";
	public static final String FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2_B = "filter.appendfilesfilter.target.file.or.directory.1.exception.2.b";
	public static final String FILTER_APPENDFILESFILTER_TARGET_FILE_NAME = "filter.appendfilesfilter.target.file.name";
	public static final String FILTER_APPENDFILESFILTER_TARGET_FILE_COULD_NOT_BE_REMOVED = "filter.appendfilesfilter.target.file.could.not.be.removed";
	public static final String FILTER_APPENDFILESFILTER_SOURCE_FILE_READ_PROBLEM = "filter.appendfilesfilter.source.file.read.problem";
	public static final String FILTER_APPENDFILESFILTER_SOURCE_SUCCESS = "filter.appendfilesfilter.source.success";

	// 69-CalculateMD5Filter
	public static final String FILTER_CALCULATEMD5FILTER_LABEL = "filter.calculatemd5filter.label";
	public static final String FILTER_CALCULATEMD5FILTER_TOOLTIP = "filter.calculatemd5filter.tooltip";
	public static final String FILTER_CALCULATEMD5FILTER_INSTRUCTIONS = "filter.calculatemd5filter.instructions";
	public static final String FILTER_CALCULATEMD5FILTER_COMMAND_LINE_HELP = "filter.calculatemd5filter.command.line.help";
	public static final String FILTER_CALCULATEMD5FILTER_EXAMPLE_MAIN_INPUT = "filter.calculatemd5filter.example.main.input";

	// 70-DuplicatedFilesFilter
	public static final String FILTER_DUPLICATEDFILESFILTER_LABEL = "filter.duplicatedfilesfilter.label";
	public static final String FILTER_DUPLICATEDFILESFILTER_TOOLTIP = "filter.duplicatedfilesfilter.tooltip";
	public static final String FILTER_DUPLICATEDFILESFILTER_INSTRUCTIONS = "filter.duplicatedfilesfilter.instructions";
	public static final String FILTER_DUPLICATEDFILESFILTER_COMMAND_LINE_HELP = "filter.duplicatedfilesfilter.command.line.help";
	public static final String FILTER_DUPLICATEDFILESFILTER_EXAMPLE_MAIN_INPUT = "filter.duplicatedfilesfilter.example.main.input";
	public static final String FILTER_DUPLICATEDFILESFILTER_EXAMPLE_OUTPUT = "filter.duplicatedfilesfilter.example.output";
	public static final String FILTER_DUPLICATEDFILESFILTER_FIRST_FILE_PREFIX = "filter.duplicatedfilesfilter.first.file.prefix";
	public static final String FILTER_DUPLICATEDFILESFILTER_DUPLICATED_FILE_PREFIX = "filter.duplicatedfilesfilter.duplicated.file.prefix";
	public static final String FILTER_DUPLICATEDFILESFILTER_FILTER_NAME_TASK_MANAGER = "filter.duplicatedfilesfilter.filter.name.task.manager";

	// 71-ShowDuplicatedConsecutiveWordsFilter
	public static final String FILTER_SHOWDUPLICATEDCONSECUTIVEWORDS_LABEL = "filter.showduplicatedconsecutivewords.label";
	public static final String FILTER_SHOWDUPLICATEDCONSECUTIVEWORDS_TOOLTIP = "filter.showduplicatedconsecutivewords.tooltip";
	public static final String FILTER_SHOWDUPLICATEDCONSECUTIVEWORDS_INSTRUCTIONS = "filter.showduplicatedconsecutivewords.instructions";
	public static final String FILTER_SHOWDUPLICATEDCONSECUTIVEWORDS_COMMAND_LINE_HELP = "filter.showduplicatedconsecutivewords.command.line.help";
	public static final String FILTER_SHOWDUPLICATEDCONSECUTIVEWORDS_RESULT = "filter.showduplicatedconsecutivewords.result";
	public static final String FILTER_SHOWDUPLICATEDCONSECUTIVEWORDS_EXAMPLE_MAIN_INPUT = "filter.showduplicatedconsecutivewords.example.main.input";
	public static final String FILTER_SHOWDUPLICATEDCONSECUTIVEWORDS_EXAMPLE_OUTPUT = "filter.showduplicatedconsecutivewords.example.output";
	public static final String FILTER_SHOWDUPLICATEDCONSECUTIVEWORDS_REPORT = "filter.showduplicatedconsecutivewords.report";

	// 72-BreakLinesOnSpecifiedWidthFilter
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_LABEL = "filter.breaklinesonspecifiedlength.label";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_TOOLTIP = "filter.breaklinesonspecifiedlength.tooltip";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_INSTRUCTIONS = "filter.breaklinesonspecifiedlength.instructions";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_COMMAND_LINE_HELP = "filter.breaklinesonspecifiedlength.command.line.help";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_EXAMPLE_MAIN_INPUT = "filter.breaklinesonspecifiedlength.example.main.input";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_EXAMPLE_OUTPUT = "filter.breaklinesonspecifiedlength.example.output";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_REPORT = "filter.breaklinesonspecifiedlength.report";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_EXCEPTION = "filter.breaklinesonspecifiedlength.exception";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_FIELD1 = "filter.breaklinesonspecifiedlength.field1";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_CHECKBOX1 = "filter.breaklinesonspecifiedlength.checkbox1";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_FIELD1_TOOLTIP = "filter.breaklinesonspecifiedlength.field1.tooltip";
	public static final String FILTER_BREAKLINESONSPECIFIEDLENGTH_CHECKBOX1_TOOLTIP = "filter.breaklinesonspecifiedlength.checkbox1.tooltip";

	// 73-SearchFileFilter
	public static final String FILTER_SEARCHFILES_LABEL = "filter.searchfiles.label";
	public static final String FILTER_SEARCHFILES_TOOLTIP = "filter.searchfiles.tooltip";
	public static final String FILTER_SEARCHFILES_INSTRUCTIONS = "filter.searchfiles.instructions";
	public static final String FILTER_SEARCHFILES_COMMAND_LINE_HELP = "filter.searchfiles.command.line.help";
	public static final String FILTER_SEARCHFILES_FIELD1 = "filter.searchfiles.field1";
	public static final String FILTER_SEARCHFILES_CHECKBOX1 = "filter.searchfiles.checkbox1";
	public static final String TARGET_FILE_OR_DIRECTORY_LABEL = "target.file.or.directory.label";
	public static final String TARGET_FILE_OR_DIRECTORY_LABEL_TOOLTIP = "target.file.or.directory.label.tooltip";
	public static final String FILTER_SEARCHFILES_FIELD1_TOOLTIP = "filter.searchfiles.field1.tooltip";
	public static final String FILTER_SEARCHFILES_CHECKBOX1_TOOLTIP = "filter.searchfiles.checkbox1.tooltip";
	public static final String FILTER_SEARCHFILES_EXCEPTION_1 = "filter.searchfiles.exception1";
	public static final String FILTER_SEARCHFILES_EXCEPTION_2 = "filter.searchfiles.exception2";
	public static final String FILTER_SEARCHFILES_EXAMPLE_MAIN_INPUT = "filter.searchfiles.example.main.input";
	public static final String FILTER_SEARCHFILES_EXAMPLE_OUTPUT = "filter.searchfiles.example.output";
	public static final String FILTER_SEARCHFILES_REPORT = "filter.searchfiles.report";
	public static final String FILTER_SEARCHFILES_REPORT_DIRECTORY_FORMAT_ERROR = "filter.searchfiles.report.directory.format.error";
	public static final String FILTER_SEARCHFILES_REPORT_PROGRESS = "filter.searchfiles.report.progress";

	// 74-CalculatorFilter
	public static final String FILTER_CALCULATOR_LABEL = "filter.calculator.label";
	public static final String FILTER_CALCULATOR_TOOLTIP = "filter.calculator.tooltip";
	public static final String FILTER_CALCULATOR_INSTRUCTIONS = "filter.calculator.instructions";
	public static final String FILTER_CALCULATOR_ERROR1 = "filter.calculator.error1";
	public static final String FILTER_CALCULATOR_ERROR2 = "filter.calculator.error2";
	public static final String FILTER_CALCULATOR_EXAMPLE_MAIN_INPUT = "filter.calculator.example.main.input";
	public static final String FILTER_CALCULATOR_EXAMPLE_OUTPUT = "filter.calculator.example.output";
	public static final String FILTER_CALCULATOR_COMMAND_LINE_HELP = "filter.calculator.command.line.help";

	// 75-CalendarFilter
	public static final String FILTER_CALENDARFILTER_LABEL = "filter.calendarfilter.label";
	public static final String FILTER_CALENDARFILTER_TOOLTIP = "filter.calendarfilter.tooltip";
	public static final String FILTER_CALENDARFILTER_FIELD1 = "filter.calendarfilter.field1";
	public static final String FILTER_CALENDARFILTER_FIELD2 = "filter.calendarfilter.field2";
	public static final String FILTER_CALENDARFILTER_FIELD1_TOOLTIP = "filter.calendarfilter.field1.tooltip";
	public static final String FILTER_CALENDARFILTER_FIELD2_TOOLTIP = "filter.calendarfilter.field2.tooltip";
	public static final String FILTER_CALENDARFILTER_INSTRUCTIONS = "filter.calendarfilter.instructions";
	public static final String FILTER_CALENDARFILTER_EXCEPTION1 = "filter.calendarfilter.exception1";
	public static final String FILTER_CALENDARFILTER_EXCEPTION2 = "filter.calendarfilter.exception2";
	public static final String FILTER_CALENDARFILTER_CALENDAR_TITLE = "filter.calendarfilter.calendar.title";
	public static final String FILTER_CALENDARFILTER_MAIN_INPUT = "filter.calendarfilter.main.input";
	public static final String FILTER_CALENDARFILTER_EXAMPLE_OUTPUT = "filter.calendarfilter.example.output";

	// 76-RandomDataGeneratorFilter
	public static final String FILTER_TESTDATAGENERATORFILTER_LABEL = "filter.testdatageneratorfilter.label";
	public static final String FILTER_TESTDATAGENERATORFILTER_TOOLTIP = "filter.testdatageneratorfilter.tooltip";
	public static final String FILTER_TESTDATAGENERATORFILTER_INSTRUCTIONS = "filter.testdatageneratorfilter.instructions";
	public static final String FILTER_TESTDATAGENERATORFILTER_FIELD_1 = "filter.testdatageneratorfilter.field.1";
	public static final String FILTER_TESTDATAGENERATORFILTER_FIELD_1_TOOLTIP = "filter.testdatageneratorfilter.field.1.tooltip";
	public static final String FILTER_TESTDATAGENERATORFILTER_EXCEPTION_1 = "filter.testdatageneratorfilter.exception.1";
	public static final String FILTER_TESTDATAGENERATORFILTER_EXCEPTION_2 = "filter.testdatageneratorfilter.exception.2";
	public static final String FILTER_TESTDATAGENERATORFILTER_COMMAND_LINE_HELP = "filter.testdatageneratorfilter.command.line.help";
	public static final String FILTER_TESTDATAGENERATORFILTER_EXAMPLE_MAIN_INPUT = "filter.testdatageneratorfilter.example.main.input";
	public static final String FILTER_TESTDATAGENERATORFILTER_EXAMPLE_OUTPUT = "filter.testdatageneratorfilter.example.output";

	// 77-TreeDirectoryFilter
	public static final String FILTER_TREEDIRECTORYFILES_LABEL = "filter.treedirectoryfiles.label";
	public static final String FILTER_TREEDIRECTORYFILES_TOOLTIP = "filter.treedirectoryfiles.tooltip";
	public static final String FILTER_TREEDIRECTORYFILES_INSTRUCTIONS = "filter.treedirectoryfiles.instructions";
	public static final String FILTER_TREEDIRECTORYFILES_DIRECTORY1 = "filter.treedirectoryfiles.directory1";
	public static final String FILTER_TREEDIRECTORYFILES_CHECKBOX1 = "filter.treedirectoryfiles.checkbox1";
	public static final String FILTER_TREEDIRECTORYFILES_CHECKBOX2 = "filter.treedirectoryfiles.checkbox2";
	public static final String FILTER_TREEDIRECTORYFILES_DIRECTORY1_TOOLTIP = "filter.treedirectoryfiles.directory1.tooltip";
	public static final String FILTER_TREEDIRECTORYFILES_CHECKBOX1_TOOLTIP = "filter.treedirectoryfiles.checkbox1.tooltip";
	public static final String FILTER_TREEDIRECTORYFILES_CHECKBOX2_TOOLTIP = "filter.treedirectoryfiles.checkbox2.tooltip";
	public static final String FILTER_TREEDIRECTORYFILES_EXCEPTION_1 = "filter.treedirectoryfiles.exception.1";
	public static final String FILTER_TREEDIRECTORYFILES_COMMAND_LINE_HELP = "filter.treedirectoryfiles.command.line.help";
	public static final String FILTER_TREEDIRECTORYFILES_EXAMPLE_MAIN_INPUT = "filter.treedirectoryfiles.example.main.input";
	public static final String FILTER_TREEDIRECTORYFILES_EXAMPLE_OUTPUT = "filter.treedirectoryfiles.example.output";
	public static final String FILTER_TREEDIRECTORYFILES_REPORT = "filter.treedirectoryfiles.report";
	public static final String FILTER_TREEDIRECTORYFILES_TASK_PROGRESS = "filter.treedirectoryfiles.task.progress";

	// 78-FileListFilter
	public static final String FILTER_FILELIST_LABEL = "filter.filelist.label";
	public static final String FILTER_FILELIST_TOOLTIP = "filter.filelist.tooltip";
	public static final String FILTER_FILELIST_DIRECTORY1 = "filter.filelist.directory1";
	public static final String FILTER_FILELIST_EXCEPTION_1 = "filter.filelist.exception.1";
	public static final String FILTER_FILELIST_INSTRUCTIONS = "filter.filelist.instructions";
	public static final String FILTER_FILELIST_COMMAND_LINE_HELP = "filter.filelist.command.line.help";
	public static final String FILTER_FILELIST_CHECK_BOX_1 = "filter.filelist.check.box.1";
	public static final String FILTER_FILELIST_CHECK_BOX_2 = "filter.filelist.check.box.2";
	public static final String FILTER_FILELIST_DIRECTORY1_TOOLTIP = "filter.filelist.directory1.tooltip";
	public static final String FILTER_FILELIST_CHECK_BOX_1_TOOLTIP = "filter.filelist.check.box.1.tooltip";
	public static final String FILTER_FILELIST_CHECK_BOX_2_TOOLTIP = "filter.filelist.check.box.2.tooltip";
	public static final String FILTER_FILELIST_EXAMPLE_MAIN_INPUT = "filter.filelist.example.main.input";
	public static final String FILTER_FILELIST_EXAMPLE_OUTPUT = "filter.filelist.example.output";
	public static final String FILTER_FILELIST_REPORT = "filter.filelist.report";
	public static final String FILTER_FILELIST_REPORT_TASK_PROGRESS = "filter.filelist.report.task.progress";

	// 79-DeleteDirectoryFilter
	public static final String FILTER_DELETEDIRECTORIES_LABEL = "filter.deletedirectories.label";
	public static final String FILTER_DELETEDIRECTORIES_TOOLTIP = "filter.deletedirectories.tooltip";
	public static final String FILTER_DELETEDIRECTORIES_INSTRUCTIONS = "filter.deletedirectories.instructions";
	public static final String FILTER_DELETEDIRECTORIES_FIELD1 = "filter.deletedirectories.field1";
	public static final String FILTER_DELETEDIRECTORIES_CHECKBOX1 = "filter.deletedirectories.checkbox1";
	public static final String FILTER_DELETEDIRECTORIES_FIELD1_TOOLTIP = "filter.deletedirectories.field1.tooltip";
	public static final String FILTER_DELETEDIRECTORIES_CHECKBOX1_TOOLTIP = "filter.deletedirectories.checkbox1.tooltip";
	public static final String FILTER_DELETEDIRECTORIES_COMMAND_LINE_HELP = "filter.deletedirectories.command.line.help";
	public static final String FILTER_DELETEDIRECTORIES_EXAMPLE_MAIN_INPUT = "filter.deletedirectories.example.main.input";
	public static final String FILTER_DELETEDIRECTORIES_EXAMPLE_OUTPUT = "filter.deletedirectories.example.output";
	public static final String FILTER_DELETEDIRECTORIES_REPORT = "filter.deletedirectories.report";
	public static final String FILTER_DELETEDIRECTORIES_FILES_REMOVED = "filter.deletedirectories.files.removed";
	public static final String FILTER_DELETEDIRECTORIES_FILES_NOT_REMOVED = "filter.deletedirectories.files.not.removed";
	public static final String FILTER_DELETEDIRECTORIES_FILES_REQUIRED_PARAMETER = "filter.deletedirectories.required.parameter";

	// 80-KnapSackFileListerFilter
	public static final String FILTER_KNAPSACKFILELIST_LABEL = "filter.knapsackfilelist.label";
	public static final String FILTER_KNAPSACKFILELIST_TOOLTIP = "filter.knapsackfilelist.tooltip";
	public static final String FILTER_KNAPSACKFILELIST_INSTRUCTIONS = "filter.knapsackfilelist.instructions";
	public static final String FILTER_KNAPSACKFILELIST_DIRECTORY1 = "filter.knapsackfilelist.directory1";
	public static final String FILTER_KNAPSACKFILELIST_CHECK_BOX_1 = "filter.knapsackfilelist.check.box.1";
	public static final String FILTER_KNAPSACKFILELIST_DIVIDER_1 = "filter.knapsackfilelist.divider.1";
	public static final String FILTER_KNAPSACKFILELIST_DIRECTORY1_TOOLTIP = "filter.knapsackfilelist.directory1.tooltip";
	public static final String FILTER_KNAPSACKFILELIST_CHECK_BOX_1_TOOLTIP = "filter.knapsackfilelist.check.box.1.tooltip";
	public static final String FILTER_KNAPSACKFILELIST_DIVIDER_1_TOOLTIP = "filter.knapsackfilelist.divider.1.tooltip";
	public static final String FILTER_KNAPSACKFILELIST_REPORT_TASK_PROGRESS = "filter.knapsackfilelist.report.task.progress";
	public static final String FILTER_KNAPSACKFILELIST_EXCEPTION_1 = "filter.knapsackfilelist.exception.1";
	public static final String FILTER_KNAPSACKFILELIST_COMMAND_LINE_HELP = "filter.knapsackfilelist.command.line.help";
	public static final String FILTER_KNAPSACKFILELIST_EXAMPLE_MAIN_INPUT = "filter.knapsackfilelist.example.main.input";
	public static final String FILTER_KNAPSACKFILELIST_EXAMPLE_OUTPUT = "filter.knapsackfilelist.example.output";
	public static final String FILTER_KNAPSACKFILELIST_REPORT = "filter.knapsackfilelist.report";

	// 81-KnapSackFilter
	public static final String FILTER_KNAPSACK_LABEL = "filter.knapsack.label";
	public static final String FILTER_KNAPSACK_TOOLTIP = "filter.knapsack.tooltip";
	public static final String FILTER_KNAPSACK_INSTRUCTIONS = "filter.knapsack.instructions";
	public static final String FILTER_KNAPSACK_EXAMPLE_MAIN_INPUT = "filter.knapsack.example.main.input";
	public static final String FILTER_KNAPSACK_EXAMPLE_OUTPUT = "filter.knapsack.example.output";
	public static final String FILTER_KNAPSACK_ERROR_1 = "filter.knapsack.error.1";
	public static final String FILTER_KNAPSACK_ERROR_2 = "filter.knapsack.error.2";
	public static final String FILTER_KNAPSACK_ERROR_3 = "filter.knapsack.error.3";
	public static final String FILTER_KNAPSACK_ERROR_4 = "filter.knapsack.error.4";
	public static final String FILTER_KNAPSACK_ERROR_5 = "filter.knapsack.error.5";
	public static final String FILTER_KNAPSACK_ERROR_6 = "filter.knapsack.error.6";
	public static final String FILTER_KNAPSACK_ERROR_7 = "filter.knapsack.error.7";
	public static final String FILTER_KNAPSACK_OUTPUT_1 = "filter.knapsack.output.1";
	public static final String FILTER_KNAPSACK_OUTPUT_2 = "filter.knapsack.output.2";
	public static final String FILTER_KNAPSACK_OUTPUT_3 = "filter.knapsack.output.3";

	// 82-JavaKeyToConstantFilter
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_LABEL = "filter.javakeytoconstantfilter.label";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_TOOLTIP = "filter.javakeytoconstantfilter.tooltip";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_INSTRUCTIONS = "filter.javakeytoconstantfilter.instructions";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_FIELD_1 = "filter.javakeytoconstantfilter.field.1";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_CHECKBOX_1 = "filter.javakeytoconstantfilter.checkbox.1";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_EXAMPLE_MAIN_INPUT = "filter.javakeytoconstantfilter.example.main.input";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_EXAMPLE_OUTPUT = "filter.javakeytoconstantfilter.example.output";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_REPORT = "filter.javakeytoconstantfilter.report";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_FIELD_1_TOOLTIP = "filter.javakeytoconstantfilter.field.1.tooltip";
	public static final String FILTER_JAVAKEYTOCONSTANTFILTER_CHECKBOX_1_TOOLTIP = "filter.javakeytoconstantfilter.checkbox.1.tooltip";

	// 83-JavaToMethodOutputFilter
	public static final String FILTER_JAVATOMETHODOUTPUTFILTER_LABEL = "filter.javatomethodoutputfilter.label";
	public static final String FILTER_JAVATOMETHODOUTPUTFILTER_TOOLTIP = "filter.javatomethodoutputfilter.tooltip";
	public static final String FILTER_JAVATOMETHODOUTPUTFILTER_INSTRUCTIONS = "filter.javatomethodoutputfilter.instructions";
	public static final String FILTER_JAVATOMETHODOUTPUTFILTER_CHECKBOX_1 = "filter.javatomethodoutputfilter.checkbox.1";
	public static final String FILTER_JAVATOMETHODOUTPUTFILTER_EXAMPLE_MAIN_INPUT = "filter.javatomethodoutputfilter.example.main.input";
	public static final String FILTER_JAVATOMETHODOUTPUTFILTER_EXAMPLE_OUTPUT = "filter.javatomethodoutputfilter.example.output";
	public static final String FILTER_JAVATOMETHODOUTPUTFILTER_CHECKBOX_1_TOOLTIP = "filter.javatomethodoutputfilter.checkbox.1.tooltip";

	// 84-JavaBeanFilter
	public static final String FILTER_JAVABEANFILTER_LABEL = "filter.javabeanfilter.label";
	public static final String FILTER_JAVABEANFILTER_TOOLTIP = "filter.javabeanfilter.tooltip";
	public static final String FILTER_JAVABEANFILTER_INSTRUCTIONS = "filter.javabeanfilter.instructions";
	public static final String FILTER_JAVABEANFILTER_FIELD_1 = "filter.javabeanfilter.field.1";
	public static final String FILTER_JAVABEANFILTER_CHECKBOX_1 = "filter.javabeanfilter.checkbox.1";
	public static final String FILTER_JAVABEANFILTER_CHECKBOX_2 = "filter.javabeanfilter.checkbox.2";
	public static final String FILTER_JAVABEANFILTER_FIELD_1_TOOLTIP = "filter.javabeanfilter.field.1.tooltip";
	public static final String FILTER_JAVABEANFILTER_CHECKBOX_1_TOOLTIP = "filter.javabeanfilter.checkbox.1.tooltip";
	public static final String FILTER_JAVABEANFILTER_CHECKBOX_2_TOOLTIP = "filter.javabeanfilter.checkbox.2.tooltip";
	public static final String FILTER_JAVABEANFILTER_EXAMPLE_MAIN_INPUT = "filter.javabeanfilter.example.main.input";
	public static final String FILTER_JAVABEANFILTER_EXAMPLE_OUTPUT = "filter.javabeanfilter.example.output";
	public static final String FILTER_JAVABEANFILTER_COMMAND_LINE_HELP = "filter.javabeanfilter.command.line.help";

	// 85-JspFormatterFilter
	public static final String FILTER_JSPFORMATTER_LABEL = "filter.jspformatter.label";
	public static final String FILTER_JSPFORMATTER_TOOLTIP = "filter.jspformatter.tooltip";
	public static final String FILTER_JSPFORMATTER_INSTRUCTIONS = "filter.jspformatter.instructions";
	public static final String FILTER_JSPFORMATTER_FIELD_1 = "filter.jspformatter.field.1";
	public static final String FILTER_JSPFORMATTER_FIELD_1_TOOLTIP = "filter.jspformatter.field.1.tooltip";
	public static final String FILTER_JSPFORMATTER_EXAMPLE_MAIN_INPUT = "filter.jspformatter.example.main.input";
	public static final String FILTER_JSPFORMATTER_EXAMPLE_OUTPUT = "filter.jspformatter.example.output";
	public static final String FILTER_JSPFORMATTER_COMMAND_LINE_HELP = "filter.jspformatter.command.line.help";
	public static final String FILTER_JSPFORMATTER_ERROR_MESSAGE_1 = "filter.jspformatter.error.message.1";
	public static final String FILTER_JSPFORMATTER_ERROR_MESSAGE_2 = "filter.jspformatter.error.message.2";

	// 86-BreakStringIntoFieldsFilter
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_LABEL = "filter.breakstringintofieldsefilter.label";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_TOOLTIP = "filter.breakstringintofieldsefilter.tooltip";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_INSTRUCTIONS = "filter.breakstringintofieldsefilter.instructions";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_TARGET1 = "filter.breakstringintofieldsefilter.target1";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_TARGET1_TOOLTIP = "filter.breakstringintofieldsefilter.target1.tooltip";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXCEPTION1 = "filter.breakstringintofieldsefilter.exception1";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXCEPTION2 = "filter.breakstringintofieldsefilter.exception2";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXCEPTION3 = "filter.breakstringintofieldsefilter.exception3";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_COMMAND_LINE_HELP = "filter.breakstringintofieldsefilter.command.line.help";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXAMPLE_MAIN_INPUT = "filter.breakstringintofieldsefilter.example.main.input";
	public static final String FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXAMPLE_OUTPUT = "filter.breakstringintofieldsefilter.example.output";

	// 87-CombineFieldsIntoStringFilter
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_LABEL = "filter.combinefieldsintostringfilter.label";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_TOOLTIP = "filter.combinefieldsintostringfilter.tooltip";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_INSTRUCTIONS = "filter.combinefieldsintostringfilter.instructions";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_TARGET1 = "filter.combinefieldsintostringfilter.target1";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_TARGET1_TOOLTIP = "filter.combinefieldsintostringfilter.target1.tooltip";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_EXCEPTION1 = "filter.combinefieldsintostringfilter.exception1";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_EXCEPTION2 = "filter.combinefieldsintostringfilter.exception2";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_EXCEPTION3 = "filter.combinefieldsintostringfilter.exception3";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_EXCEPTION4 = "filter.combinefieldsintostringfilter.exception4";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_EXCEPTION5 = "filter.combinefieldsintostringfilter.exception5";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_EXCEPTION6 = "filter.combinefieldsintostringfilter.exception6";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_COMMAND_LINE_HELP = "filter.combinefieldsintostringfilter.command.line.help";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_EXAMPLE_MAIN_INPUT = "filter.combinefieldsintostringfilter.example.main.input";
	public static final String FILTER_COMBINEFIELDSINTOSTRINGFILTER_EXAMPLE_OUTPUT = "filter.combinefieldsintostringfilter.example.output";

	// 88-JSONFormatterFilter
	public static final String FILTER_JSONFORMATTERFILTER_LABEL = "filter-jsonformatterfilter-label";
	public static final String FILTER_JSONFORMATTERFILTER_TOOLTIP = "filter-jsonformatterfilter-tooltip";
	public static final String FILTER_JSONFORMATTERFILTER_INSTRUCTIONS = "filter-jsonformatterfilter-instructions";
	public static final String FILTER_JSONFORMATTERFILTER_FIELD1 = "filter-jsonformatterfilter-field1";
	public static final String FILTER_JSONFORMATTERFILTER_FIELD1_TOOLTIP = "filter-jsonformatterfilter-field1-tooltip";
	public static final String FILTER_JSONFORMATTERFILTER_EXCEPTION = "filter-jsonformatterfilter-exception";
	public static final String FILTER_JSONFORMATTERFILTER_EXAMPLE_MAIN_INPUT = "filter-jsonformatterfilter-example-main-input";
	public static final String FILTER_JSONFORMATTERFILTER_EXAMPLE_OUTPUT = "filter-jsonformatterfilter-example-output";
	public static final String FILTER_JSONFORMATTERFILTER_COMMAND_LINE_HELP = "filter-jsonformatterfilter-command-line-help";
	public static final String FILTER_JSONFORMATTERFILTER_REPORT_OK = "filter-jsonformatterfilter-report-ok";
	public static final String FILTER_JSONFORMATTERFILTER_REPORT_ERROR = "filter-jsonformatterfilter-report-error";


	
	// 88-XMLFormatterFilter
	public static final String FILTER_XMLFORMATTERFILTER_LABEL = "filter.xmlformatterfilter.label";
	public static final String FILTER_XMLFORMATTERFILTER_TOOLTIP = "filter.xmlformatterfilter.tooltip";
	public static final String FILTER_XMLFORMATTERFILTER_INSTRUCTIONS = "filter.xmlformatterfilter.instructions";
	public static final String FILTER_XMLFORMATTERFILTER_EXAMPLE_MAIN_INPUT = "filter.xmlformatterfilter.example.main.input";
	public static final String FILTER_XMLFORMATTERFILTER_EXAMPLE_OUTPUT = "filter.xmlformatterfilter.example.output";
	public static final String FILTER_XMLFORMATTERFILTER_COMMAND_LINE_HELP = "filter.xmlformatterfilter.command.line.help";
	public static final String FILTER_XMLFORMATTERFILTER_REPORT_OK = "filter.xmlformatterfilter.report.ok";
	public static final String FILTER_XMLFORMATTERFILTER_REPORT_ERROR = "filter.xmlformatterfilter.report.error";
	public static final String FILTER_XMLFORMATTERFILTER_EXCEPTION = "filter.xmlformatterfilter.exception";
	public static final String FILTER_XMLFORMATTERFILTER_EXCEPTION_INVALID_XML = "filter.xmlformatterfilter.exception.invalid.xml";
	public static final String FILTER_XMLFORMATTERFILTER_FIELD1 = "filter.xmlformatterfilter.field1";
	public static final String FILTER_XMLFORMATTERFILTER_FIELD1_TOOLTIP = "filter.xmlformatterfilter.field1.tooltip";

	// 89-CharacterToUpperOrLowerCaseFilter
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_LABEL = "filter.charactertoupperorlowercasefilter.label";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_TOOLTIP = "filter.charactertoupperorlowercasefilter.tooltip";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_INSTRUCTIONS = "filter.charactertoupperorlowercasefilter.instructions";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_CHECKBOX1 = "filter.charactertoupperorlowercasefilter.checkbox1";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_CHECKBOX1_TOOLTIP = "filter.charactertoupperorlowercasefilter.checkbox1.tooltip";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_INPUTFIELD1 = "filter.charactertoupperorlowercasefilter.inputfield1";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_INPUTFIELD1_TOOLTIP = "filter.charactertoupperorlowercasefilter.inputfield1.tooltip";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_COMMAND_LINE_HELP = "filter.charactertoupperorlowercasefilter.command.line.help";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_MAIN_INPUT = "filter.charactertoupperorlowercasefilter.main.input";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_OUTPUT = "filter.charactertoupperorlowercasefilter.output";
	public static final String FILTER_CHARACTERTOUPPERORLOWERCASEFILTER_REPORT = "filter.charactertoupperorlowercasefilter.report";

	// 90-AWK1Filter
	public static final String FILTER_AWK1FILTER_LABEL = "filter.awk1filter.label";
	public static final String FILTER_AWK1FILTER_TOOLTIP = "filter.awk1filter.tooltip";
	public static final String FILTER_AWK1FILTER_INSTRUCTIONS = "filter.awk1filter.instructions";
	public static final String FILTER_AWK1FILTER_COMMAND_LINE_HELP = "filter.awk1filter.command.line.help";
	public static final String FILTER_AWK1FILTER_EXCEPTION1 = "filter.awk1filter.exception1";
	public static final String FILTER_AWK1FILTER_EXCEPTION2 = "filter.awk1filter.exception2";
	public static final String FILTER_AWK1FILTER_EXAMPLE_MAIN_INPUT = "filter.awk1filter.example.main.input";
	public static final String FILTER_AWK1FILTER_EXAMPLE_OUTPUT = "filter.awk1filter.example.output";

	// 91-AWK2Filter
	public static final String FILTER_AWK2FILTER_LABEL = "filter.awk2filter.label";
	public static final String FILTER_AWK2FILTER_TOOLTIP = "filter.awk2filter.tooltip";
	public static final String FILTER_AWK2FILTER_INSTRUCTIONS = "filter.awk2filter.instructions";
	public static final String FILTER_AWK2FILTER_COMMAND_LINE_HELP = "filter.awk2filter.command.line.help";
	public static final String FILTER_AWK2FILTER_EXCEPTION1 = "filter.awk2filter.exception1";
	public static final String FILTER_AWK2FILTER_EXCEPTION2 = "filter.awk2filter.exception2";
	public static final String FILTER_AWK2FILTER_EXAMPLE_MAIN_INPUT = "filter.awk2filter.example.main.input";
	public static final String FILTER_AWK2FILTER_EXAMPLE_OUTPUT = "filter.awk2filter.example.output";

	// 92-AWK3Filter
	public static final String FILTER_AWK3FILTER_LABEL = "filter.awk3filter.label";
	public static final String FILTER_AWK3FILTER_TOOLTIP = "filter.awk3filter.tooltip";
	public static final String FILTER_AWK3FILTER_INSTRUCTIONS = "filter.awk3filter.instructions";
	public static final String FILTER_AWK3FILTER_COMMAND_LINE_HELP = "filter.awk3filter.command.line.help";
	public static final String FILTER_AWK3FILTER_EXCEPTION1 = "filter.awk3filter.exception1";
	public static final String FILTER_AWK3FILTER_EXCEPTION2 = "filter.awk3filter.exception2";
	public static final String FILTER_AWK3FILTER_EXAMPLE_MAIN_INPUT = "filter.awk3filter.example.main.input";
	public static final String FILTER_AWK3FILTER_EXAMPLE_OUTPUT = "filter.awk3filter.example.output";

	// Timesheet General
	public static final String FILTER_TIMESHEET_EXCEPTION1 = "filter.timesheet.exception1";
	public static final String FILTER_TIMESHEET_EXCEPTION2 = "filter.timesheet.exception2";
	public static final String FILTER_TIMESHEET_EXCEPTION3 = "filter.timesheet.exception3";
	public static final String FILTER_TIMESHEET_EXCEPTION4 = "filter.timesheet.exception4";
	public static final String FILTER_TIMESHEET_EXCEPTION5 = "filter.timesheet.exception5";
	public static final String FILTER_TIMESHEET_EXCEPTION6 = "filter.timesheet.exception6";
	public static final String FILTER_TIMESHEET_EXCEPTION7 = "filter.timesheet.exception7";
	public static final String FILTER_TIMESHEET_EXCEPTION8 = "filter.timesheet.exception8";
	public static final String FILTER_TIMESHEET_EXCEPTION9 = "filter.timesheet.exception9";
	public static final String FILTER_TIMESHEET_EXCEPTION10 = "filter.timesheet.exception10";
	public static final String FILTER_TIMESHEET_EXCEPTION11 = "filter.timesheet.exception11";
	public static final String FILTER_TIMESHEET_EXCEPTION12 = "filter.timesheet.exception12";
	public static final String FILTER_TIMESHEET_EXCEPTION13 = "filter.timesheet.exception13";
	public static final String FILTER_TIMESHEET_EXCEPTION14 = "filter.timesheet.exception14";
	public static final String FILTER_TIMESHEET_EXCEPTION15 = "filter.timesheet.exception15";
	public static final String FILTER_TIMESHEET_EXCEPTION16 = "filter.timesheet.exception16";
	public static final String FILTER_TIMESHEET_EXCEPTION17 = "filter.timesheet.exception17";
	public static final String FILTER_TIMESHEET_EXCEPTION18 = "filter.timesheet.exception18";
	public static final String FILTER_TIMESHEET_EXCEPTION19 = "filter.timesheet.exception19";
	public static final String FILTER_TIMESHEET_EXCEPTION20 = "filter.timesheet.exception20";
	public static final String FILTER_TIMESHEET_EXCEPTION21 = "filter.timesheet.exception21";
	public static final String FILTER_TIMESHEET_EXCEPTION22 = "filter.timesheet.exception22";
	public static final String FILTER_TIMESHEET_EXCEPTION23 = "filter.timesheet.exception23";
	public static final String FILTER_TIMESHEET_EXCEPTION24 = "filter.timesheet.exception24";
	public static final String FILTER_TIMESHEET_EXCEPTION25 = "filter.timesheet.exception25";
	public static final String FILTER_TIMESHEET_EXCEPTION26 = "filter.timesheet.exception26";
	public static final String FILTER_TIMESHEET_EXCEPTION27 = "filter.timesheet.exception27";
	public static final String FILTER_TIMESHEET_EXCEPTION28 = "filter.timesheet.exception28";
	public static final String FILTER_TIMESHEET_EXCEPTION29 = "filter.timesheet.exception29";
	public static final String FILTER_TIMESHEET_EXCEPTION30 = "filter.timesheet.exception30";
	public static final String FILTER_TIMESHEET_EXCEPTION31 = "filter.timesheet.exception31";
	public static final String FILTER_TIMESHEET_EXCEPTION32 = "filter.timesheet.exception32";
	public static final String FILTER_TIMESHEET_EXCEPTION33 = "filter.timesheet.exception33";
	public static final String FILTER_TIMESHEET_EXCEPTION34 = "filter.timesheet.exception34";
	public static final String FILTER_TIMESHEET_EXCEPTION35 = "filter.timesheet.exception35";
	public static final String FILTER_TIMESHEET_EXCEPTION36 = "filter.timesheet.exception36";
	public static final String FILTER_TIMESHEET_EXCEPTION37 = "filter.timesheet.exception37";
	public static final String FILTER_TIMESHEET_EXCEPTION38 = "filter.timesheet.exception38";
	public static final String FILTER_TIMESHEET_SUNDAY = "filter.timesheet.sunday";
	public static final String FILTER_TIMESHEET_MONDAY = "filter.timesheet.monday";
	public static final String FILTER_TIMESHEET_TUESDAY = "filter.timesheet.tuesday";
	public static final String FILTER_TIMESHEET_WEDNESDAY = "filter.timesheet.wednesday";
	public static final String FILTER_TIMESHEET_THURSDAY = "filter.timesheet.thurday";
	public static final String FILTER_TIMESHEET_FRIDAY = "filter.timesheet.friday";
	public static final String FILTER_TIMESHEET_SATURDAY = "filter.timesheet.saturday";

	// 93-StartTaskTimesheetFilter
	public static final String FILTER_STARTTASKTIMESHEETFILTER_LABEL = "filter.starttasktimesheetfilter.label";
	public static final String FILTER_STARTTASKTIMESHEETFILTER_TOOLTIP = "filter.starttasktimesheetfilter.tooltip";
	public static final String FILTER_STARTTASKTIMESHEETFILTER_INSTRUCTIONS = "filter.starttasktimesheetfilter.instructions";
	public static final String FILTER_STARTTASKTIMESHEETFILTER_FIELD_1 = "filter.starttasktimesheetfilter.field_1";
	public static final String FILTER_STARTTASKTIMESHEETFILTER_FIELD_1_TOOLTIP = "filter.starttasktimesheetfilter.field.1.tooltip";
	public static final String FILTER_STARTTASKTIMESHEETFILTER_COMMAND_LINE_HELP = "filter.starttasktimesheetfilter.command.line.help";
	public static final String FILTER_STARTTASKTIMESHEETFILTER_EXAMPLE_MAIN_INPUT = "filter.starttasktimesheetfilter.example.main.input";
	public static final String FILTER_STARTTASKTIMESHEETFILTER_EXAMPLE_OUTPUT = "filter.starttasktimesheetfilter.example.output";

	// 94-StopTaskTimesheetFilter
	public static final String FILTER_STOPTASKTIMESHEETFILTER_LABEL = "filter.stoptasktimesheetfilter.label";
	public static final String FILTER_STOPTASKTIMESHEETFILTER_TOOLTIP = "filter.stoptasktimesheetfilter.tooltip";
	public static final String FILTER_STOPTASKTIMESHEETFILTER_INSTRUCTIONS = "filter.stoptasktimesheetfilter.instructions";
	public static final String FILTER_STOPTASKTIMESHEETFILTER_FIELD_1 = "filter.stoptasktimesheetfilter.field.1";
	public static final String FILTER_STOPTASKTIMESHEETFILTER_FIELD_1_TOOLTIP = "filter.stoptasktimesheetfilter.field.1.tooltip";
	public static final String FILTER_STOPTASKTIMESHEETFILTER_COMMAND_LINE_HELP = "filter.stoptasktimesheetfilter.command.line.help";
	public static final String FILTER_STOPTASKTIMESHEETFILTER_EXAMPLE_MAIN_INPUT = "filter.stoptasktimesheetfilter.example.main.input";
	public static final String FILTER_STOPTASKTIMESHEETFILTER_EXAMPLE_OUTPUT = "filter.stoptasktimesheetfilter.example.output";

	// 95-CalculateTotalTimesheetFilter
	public static final String FILTER_CALCULATESTIMESHEETFILTER_LABEL = "filter.calculatestimesheetfilter.label";
	public static final String FILTER_CALCULATESTIMESHEETFILTER_TOOLTIP = "filter.calculatestimesheetfilter.tooltip";
	public static final String FILTER_CALCULATESTIMESHEETFILTER_INSTRUCTIONS = "filter.calculatestimesheetfilter.instructions";
	public static final String FILTER_CALCULATESTIMESHEETFILTER_COMMAND_LINE_HELP = "filter.calculatestimesheetfilter.command.line.help";
	public static final String FILTER_CALCULATESTIMESHEETFILTER_DURATION = "filter.calculatestimesheetfilter.duration";
	public static final String FILTER_CALCULATESTIMESHEETFILTER_TOTAL = "filter.calculatestimesheetfilter.total";
	public static final String FILTER_CALCULATESTIMESHEETFILTER_CUMULATIVE = "filter.calculatestimesheetfilter.cumulative";
	public static final String FILTER_CALCULATESTIMESHEETFILTER_EXAMPLE_MAIN_INPUT = "filter.calculatestimesheetfilter.example.main.input";
	public static final String FILTER_CALCULATESTIMESHEETFILTER_EXAMPLE_OUTPUT = "filter.calculatestimesheetfilter.example.output";

	// 96-FromAMPMto24hFilter
	public static final String FILTER_FROM_AM_PM_TO_24H_FILTER_LABEL = "filter.from.am.pm.to.24h.filter.label";
	public static final String FILTER_FROM_AM_PM_TO_24H_FILTER_TOOLTIP = "filter.from.am.pm.to.24h.filter.tooltip";
	public static final String FILTER_FROM_AM_PM_TO_24H_FILTER_INSTRUCTIONS = "filter.from.am.pm.to.24h.filter.instructions";
	public static final String FILTER_FROM_AM_PM_TO_24H_FILTER_COMMAND_LINE_HELP = "filter.from.am.pm.to.24h.filter.command.line.help";
	public static final String FILTER_FROM_AM_PM_TO_24H_FILTER_EXAMPLE_MAIN_INPUT = "filter.from.am.pm.to.24h.filter.example.main.input";
	public static final String FILTER_FROM_AM_PM_TO_24H_FILTER_EXAMPLE_OUTPUT = "filter.from.am.pm.to.24h.filter.example.output";
	public static final String FILTER_FROM_AM_PM_TO_24H_FILTER_EXCEPTION_1 = "filter.from.am.pm.to.24h.filter.exception.1";

	// 97-From24hToAMPMFilter
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_LABEL = "filter.from.24h.to.am.pm.filter.label";
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_TOOLTIP = "filter.from.24h.to.am.pm.filter.tooltip";
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_INSTRUCTIONS = "filter.from.24h.to.am.pm.filter.instructions";
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_EXCEPTION_1 = "filter.from.24h.to.am.pm.filter.exception.1";
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_COMMAND_LINE_HELP = "filter.from.24h.to.am.pm.filter.command.line.help";
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_EXAMPLE_MAIN_INPUT = "filter.from.24h.to.am.pm.filter.example.main.input";
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_EXAMPLE_OUTPUT = "filter.from.24h.to.am.pm.filter.example.output";
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_CHECKBOX_1 = "filter.from.24h.to.am.pm.filter.checkbox.1";
	public static final String FILTER_FROM_24H_TO_AM_PM_FILTER_CHECKBOX_1_TOOLTIP = "filter.from.24h.to.am.pm.filter.checkbox.1.tooltip";

	// 98-TimesheetTimeSeriesChartFilter
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_LABEL = "filter.timesheettimeserieschartfilter.label";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_TOOLTIP = "filter.timesheettimeserieschartfilter.tooltip";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_INSTRUCTIONS = "filter.timesheettimeserieschartfilter.instructions";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_CHECK_BOX_1 = "filter.timesheettimeserieschartfilter.check.box.1";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_CHECK_BOX_1_TOOLTIP = "filter.timesheettimeserieschartfilter.check.box.1.tooltip";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_WINDOWTITLE = "filter.timesheettimeserieschartfilter.windowtitle";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_PROGRESS = "filter.timesheettimeserieschartfilter.progress";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_PERIOD = "filter.timesheettimeserieschartfilter.period";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_HOURS = "filter.timesheettimeserieschartfilter.hours";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_MINUTES = "filter.timesheettimeserieschartfilter.minutes";
	public static final String FILTER_TIMESHEETTIMESERIESCHARTFILTER_EXAMPLE_MAIN_INPUT = "filter.timesheettimeserieschartfilter.example.main.input";

	// 99-TimesheetTimeDistributionChartFilter
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_LABEL = "filter.timesheettimedistributionchartfilter.label";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_TOOLTIP = "filter.timesheettimedistributionchartfilter.tooltip";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_INSTRUCTIONS = "filter.timesheettimedistributionchartfilter.instructions";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_CHECK_BOX_1 = "filter.timesheettimedistributionchartfilter.check.box.1";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_CHECK_BOX_1_TOOLTIP = "filter.timesheettimedistributionchartfilter.check.box.1.tooltip";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_CHART_TITLE = "filter.timesheettimedistributionchartfilter.chart.title";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_WINDOW_TITLE = "filter.timesheettimedistributionchartfilter.window.title";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_HOUR = "filter.timesheettimedistributionchartfilter.hour";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTER_LEGEND = "filter.timesheettimedistributionchartfilter.legend";
	public static final String FILTER_TIMESHEETTIMEDISTRIBUTIONCHARTFILTEREXAMPLE_MAIN_INPUT = "filter.timesheettimedistributionchartfilterexample.main.input";

	// 100-FigletFontFilter
	public static final String FIGLET_FILE_5LINEOBLIQUE_ZIP_NAME = "figlet.file.5lineoblique.zip.name";
	public static final String FIGLET_FILE_ACROBATIC_ZIP_NAME = "figlet.file.acrobatic.zip.name";
	public static final String FIGLET_FILE_ALLIGATOR_ZIP_NAME = "figlet.file.alligator.zip.name";
	public static final String FIGLET_FILE_ALLIGATOR2_ZIP_NAME = "figlet.file.alligator2.zip.name";
	public static final String FIGLET_FILE_ALPHABET_ZIP_NAME = "figlet.file.alphabet.zip.name";
	public static final String FIGLET_FILE_ASC______ZIP_NAME = "figlet.file.asc_____.zip.name";
	public static final String FIGLET_FILE_BANNER3_D_ZIP_NAME = "figlet.file.banner3-D.zip.name";
	public static final String FIGLET_FILE_BANNER3_ZIP_NAME = "figlet.file.banner3.zip.name";
	public static final String FIGLET_FILE_BARBWIRE_ZIP_NAME = "figlet.file.barbwire.zip.name";
	public static final String FIGLET_FILE_BASIC_ZIP_NAME = "figlet.file.basic.zip.name";
	public static final String FIGLET_FILE_BELL_ZIP_NAME = "figlet.file.bell.zip.name";
	public static final String FIGLET_FILE_BIG_ZIP_NAME = "figlet.file.big.zip.name";
	public static final String FIGLET_FILE_BIGCHIEF_ZIP_NAME = "figlet.file.bigchief.zip.name";
	public static final String FIGLET_FILE_BLOCK_ZIP_NAME = "figlet.file.block.zip.name";
	public static final String FIGLET_FILE_BROADWAY_ZIP_NAME = "figlet.file.broadway.zip.name";
	public static final String FIGLET_FILE_BULBHEAD_ZIP_NAME = "figlet.file.bulbhead.zip.name";
	public static final String FIGLET_FILE_CALGPHY2_ZIP_NAME = "figlet.file.calgphy2.zip.name";
	public static final String FIGLET_FILE_CALIGRAPHY_ZIP_NAME = "figlet.file.caligraphy.zip.name";
	public static final String FIGLET_FILE_CATWALK_ZIP_NAME = "figlet.file.catwalk.zip.name";
	public static final String FIGLET_FILE_CHAR2____ZIP_NAME = "figlet.file.char2___.zip.name";
	public static final String FIGLET_FILE_CHUNKY_ZIP_NAME = "figlet.file.chunky.zip.name";
	public static final String FIGLET_FILE_COINSTAK_ZIP_NAME = "figlet.file.coinstak.zip.name";
	public static final String FIGLET_FILE_COLOSSAL_ZIP_NAME = "figlet.file.colossal.zip.name";
	public static final String FIGLET_FILE_COMPUTER_ZIP_NAME = "figlet.file.computer.zip.name";
	public static final String FIGLET_FILE_CONTESSA_ZIP_NAME = "figlet.file.contessa.zip.name";
	public static final String FIGLET_FILE_COSMIC_ZIP_NAME = "figlet.file.cosmic.zip.name";
	public static final String FIGLET_FILE_CRAWFORD_ZIP_NAME = "figlet.file.crawford.zip.name";
	public static final String FIGLET_FILE_CRICKET_ZIP_NAME = "figlet.file.cricket.zip.name";
	public static final String FIGLET_FILE_CYBERLARGE_ZIP_NAME = "figlet.file.cyberlarge.zip.name";
	public static final String FIGLET_FILE_CYBERMEDIUM_ZIP_NAME = "figlet.file.cybermedium.zip.name";
	public static final String FIGLET_FILE_CYBERSMALL_ZIP_NAME = "figlet.file.cybersmall.zip.name";
	public static final String FIGLET_FILE_DEMO_M___ZIP_NAME = "figlet.file.demo_m__.zip.name";
	public static final String FIGLET_FILE_DOH_ZIP_NAME = "figlet.file.doh.zip.name";
	public static final String FIGLET_FILE_DOOM_ZIP_NAME = "figlet.file.doom.zip.name";
	public static final String FIGLET_FILE_DOSREBEL_ZIP_NAME = "figlet.file.dosrebel.zip.name";
	public static final String FIGLET_FILE_DOTMATRIX_ZIP_NAME = "figlet.file.dotmatrix.zip.name";
	public static final String FIGLET_FILE_DOUBLE_ZIP_NAME = "figlet.file.double.zip.name";
	public static final String FIGLET_FILE_EFTIFONT_ZIP_NAME = "figlet.file.eftifont.zip.name";
	public static final String FIGLET_FILE_EFTIROBOT_ZIP_NAME = "figlet.file.eftirobot.zip.name";
	public static final String FIGLET_FILE_EFTITALIC_ZIP_NAME = "figlet.file.eftitalic.zip.name";
	public static final String FIGLET_FILE_EFTIWATER_ZIP_NAME = "figlet.file.eftiwater.zip.name";
	public static final String FIGLET_FILE_EPIC_ZIP_NAME = "figlet.file.epic.zip.name";
	public static final String FIGLET_FILE_FENDER_ZIP_NAME = "figlet.file.fender.zip.name";
	public static final String FIGLET_FILE_FOURTOPS_ZIP_NAME = "figlet.file.fourtops.zip.name";
	public static final String FIGLET_FILE_FRAKTUR_ZIP_NAME = "figlet.file.fraktur.zip.name";
	public static final String FIGLET_FILE_FUZZY_ZIP_NAME = "figlet.file.fuzzy.zip.name";
	public static final String FIGLET_FILE_GOOFY_ZIP_NAME = "figlet.file.goofy.zip.name";
	public static final String FIGLET_FILE_GOTHIC_ZIP_NAME = "figlet.file.gothic.zip.name";
	public static final String FIGLET_FILE_GRACEFUL_ZIP_NAME = "figlet.file.graceful.zip.name";
	public static final String FIGLET_FILE_GRAFFITI_ZIP_NAME = "figlet.file.graffiti.zip.name";
	public static final String FIGLET_FILE_HOLLYWOOD_ZIP_NAME = "figlet.file.hollywood.zip.name";
	public static final String FIGLET_FILE_INVITA_ZIP_NAME = "figlet.file.invita.zip.name";
	public static final String FIGLET_FILE_ISOMETRIC1_ZIP_NAME = "figlet.file.isometric1.zip.name";
	public static final String FIGLET_FILE_ISOMETRIC2_ZIP_NAME = "figlet.file.isometric2.zip.name";
	public static final String FIGLET_FILE_ISOMETRIC3_ZIP_NAME = "figlet.file.isometric3.zip.name";
	public static final String FIGLET_FILE_ISOMETRIC4_ZIP_NAME = "figlet.file.isometric4.zip.name";
	public static final String FIGLET_FILE_ITALIC_ZIP_NAME = "figlet.file.italic.zip.name";
	public static final String FIGLET_FILE_IVRIT_ZIP_NAME = "figlet.file.ivrit.zip.name";
	public static final String FIGLET_FILE_KBAN_ZIP_NAME = "figlet.file.kban.zip.name";
	public static final String FIGLET_FILE_LARRY3D_ZIP_NAME = "figlet.file.larry3d.zip.name";
	public static final String FIGLET_FILE_LEAN_ZIP_NAME = "figlet.file.lean.zip.name";
	public static final String FIGLET_FILE_LETTERS_ZIP_NAME = "figlet.file.letters.zip.name";
	public static final String FIGLET_FILE_MARQUEE_ZIP_NAME = "figlet.file.marquee.zip.name";
	public static final String FIGLET_FILE_MINI_ZIP_NAME = "figlet.file.mini.zip.name";
	public static final String FIGLET_FILE_NANCYJ_FANCY_ZIP_NAME = "figlet.file.nancyj-fancy.zip.name";
	public static final String FIGLET_FILE_NANCYJ_ZIP_NAME = "figlet.file.nancyj.zip.name";
	public static final String FIGLET_FILE_NIPPLES_ZIP_NAME = "figlet.file.nipples.zip.name";
	public static final String FIGLET_FILE_NPN______ZIP_NAME = "figlet.file.npn_____.zip.name";
	public static final String FIGLET_FILE_NVSCRIPT_ZIP_NAME = "figlet.file.nvscript.zip.name";
	public static final String FIGLET_FILE_O8_ZIP_NAME = "figlet.file.o8.zip.name";
	public static final String FIGLET_FILE_OGRE_ZIP_NAME = "figlet.file.ogre.zip.name";
	public static final String FIGLET_FILE_PAWP_ZIP_NAME = "figlet.file.pawp.zip.name";
	public static final String FIGLET_FILE_PEAKS_ZIP_NAME = "figlet.file.peaks.zip.name";
	public static final String FIGLET_FILE_PEBBLES_ZIP_NAME = "figlet.file.pebbles.zip.name";
	public static final String FIGLET_FILE_PEPPER_ZIP_NAME = "figlet.file.pepper.zip.name";
	public static final String FIGLET_FILE_POISON_ZIP_NAME = "figlet.file.poison.zip.name";
	public static final String FIGLET_FILE_PUFFY_ZIP_NAME = "figlet.file.puffy.zip.name";
	public static final String FIGLET_FILE_RADICAL__ZIP_NAME = "figlet.file.radical_.zip.name";
	public static final String FIGLET_FILE_RECTANGLES_ZIP_NAME = "figlet.file.rectangles.zip.name";
	public static final String FIGLET_FILE_REV_ZIP_NAME = "figlet.file.rev.zip.name";
	public static final String FIGLET_FILE_ROMAN_ZIP_NAME = "figlet.file.roman.zip.name";
	public static final String FIGLET_FILE_ROUNDED_ZIP_NAME = "figlet.file.rounded.zip.name";
	public static final String FIGLET_FILE_ROWANCAP_ZIP_NAME = "figlet.file.rowancap.zip.name";
	public static final String FIGLET_FILE_ROZZO_ZIP_NAME = "figlet.file.rozzo.zip.name";
	public static final String FIGLET_FILE_SBLOOD_ZIP_NAME = "figlet.file.sblood.zip.name";
	public static final String FIGLET_FILE_SCRIPT_ZIP_NAME = "figlet.file.script.zip.name";
	public static final String FIGLET_FILE_SERIFCAP_ZIP_NAME = "figlet.file.serifcap.zip.name";
	public static final String FIGLET_FILE_SHADOW_ZIP_NAME = "figlet.file.shadow.zip.name";
	public static final String FIGLET_FILE_SLANT_ZIP_NAME = "figlet.file.slant.zip.name";
	public static final String FIGLET_FILE_SLSCRIPT_ZIP_NAME = "figlet.file.slscript.zip.name";
	public static final String FIGLET_FILE_SMALL_ZIP_NAME = "figlet.file.small.zip.name";
	public static final String FIGLET_FILE_SMISOME1_ZIP_NAME = "figlet.file.smisome1.zip.name";
	public static final String FIGLET_FILE_SMSCRIPT_ZIP_NAME = "figlet.file.smscript.zip.name";
	public static final String FIGLET_FILE_SMSHADOW_ZIP_NAME = "figlet.file.smshadow.zip.name";
	public static final String FIGLET_FILE_SMSLANT_ZIP_NAME = "figlet.file.smslant.zip.name";
	public static final String FIGLET_FILE_SPACE_OP_ZIP_NAME = "figlet.file.space_op.zip.name";
	public static final String FIGLET_FILE_SPEED_ZIP_NAME = "figlet.file.speed.zip.name";
	public static final String FIGLET_FILE_STACEY_ZIP_NAME = "figlet.file.stacey.zip.name";
	public static final String FIGLET_FILE_STAMPATELLO_ZIP_NAME = "figlet.file.stampatello.zip.name";
	public static final String FIGLET_FILE_STANDARD_ZIP_NAME = "figlet.file.standard.zip.name";
	public static final String FIGLET_FILE_STARWARS_ZIP_NAME = "figlet.file.starwars.zip.name";
	public static final String FIGLET_FILE_STOP_ZIP_NAME = "figlet.file.stop.zip.name";
	public static final String FIGLET_FILE_STRAIGHT_ZIP_NAME = "figlet.file.straight.zip.name";
	public static final String FIGLET_FILE_TANJA_ZIP_NAME = "figlet.file.tanja.zip.name";
	public static final String FIGLET_FILE_THICK_ZIP_NAME = "figlet.file.thick.zip.name";
	public static final String FIGLET_FILE_THIN_ZIP_NAME = "figlet.file.thin.zip.name";
	public static final String FIGLET_FILE_THREEPOINT_ZIP_NAME = "figlet.file.threepoint.zip.name";
	public static final String FIGLET_FILE_TINKER_TOY_ZIP_NAME = "figlet.file.tinker-toy.zip.name";
	public static final String FIGLET_FILE_TOMBSTONE_ZIP_NAME = "figlet.file.tombstone.zip.name";
	public static final String FIGLET_FILE_UNIVERS_ZIP_NAME = "figlet.file.univers.zip.name";
	public static final String FIGLET_FILE_WEIRD_ZIP_NAME = "figlet.file.weird.zip.name";
	public static final String FIGLET_FILE_WHIMSY_ZIP_NAME = "figlet.file.whimsy.zip.name";
	public static final String FIGLET_PREVIEW_WINDOW_TITLE = "figlet.preview.window.title";
	public static final String FIGLET_PREVIEW_WINDOW_INSTRUCTIONS = "figlet.preview.window.instructions";
	public static final String FIGLET_PREVIEW_WINDOW_BUTTON = "figlet.preview.window.button";
	public static final String FIGLET_PREVIEW_WINDOW_BUTTON_KEY = "figlet.preview.window.button.key";
	public static final String FIGLET_PREVIEW_WINDOW_BUTTON_INSTRUCTIONS = "figlet.preview.window.button.instructions";
	public static final String FIGLET_PREVIEW_WINDOW_EXAMPLE = "figlet.preview.window.example";
	public static final String FILTER_FIGLETFONTFILTER_LABEL = "filter.figletfontfilter.label";
	public static final String FILTER_FIGLETFONTFILTER_TOOLTIP = "filter.figletfontfilter.tooltip";
	public static final String FILTER_FIGLETFONTFILTER_INSTRUCTIONS = "filter.figletfontfilter.instructions";
	public static final String FILTER_FIGLETFONTFILTER_EXCEPTION = "filter.figletfontfilter.exception";
	public static final String FILTER_FIGLETFONTFILTER_EXAMPLE_MAIN_INPUT = "filter.figletfontfilter.example.main.input";
	public static final String FILTER_FIGLETFONTFILTER_EXAMPLE_OUTPUT = "filter.figletfontfilter.example.output";

	// 101-TextWithNumbersFilter
	public static final String FILTER_TEXTWITHNUMBERSFILTER_LABEL = "filter.textwithnumbersfilter.label";
	public static final String FILTER_TEXTWITHNUMBERSFILTER_TOOLTIP = "filter.textwithnumbersfilter.tooltip";
	public static final String FILTER_TEXTWITHNUMBERSFILTER_INSTRUCTIONS = "filter.textwithnumbersfilter.instructions";
	public static final String FILTER_TEXTWITHNUMBERSFILTER_CHECKBOX1 = "filter.textwithnumbersfilter.checkbox1";
	public static final String FILTER_TEXTWITHNUMBERSFILTER_CHECKBOX1_TOOLTIP = "filter.textwithnumbersfilter.checkbox1.tooltip";
	public static final String FILTER_TEXTWITHNUMBERSFILTER_COMMAND_LINE_HELP = "filter.textwithnumbersfilter.command.line.help";
	public static final String FILTER_TEXTWITHNUMBERSFILTER_EXAMPLE_MAIN_INPUT = "filter.textwithnumbersfilter.example.main.input";
	public static final String FILTER_TEXTWITHNUMBERSFILTER_EXAMPLE_OUTPUT = "filter.textwithnumbersfilter.example.output";
	public static final String FILTER_TEXTWITHNUMBERSFILTER_REPORT = "filter.textwithnumbersfilter.report";

	// 102-TextWithErrorsFilter
	public static final String FILTER_TEXTWITHERRORSFILTER_LABEL = "filter.textwitherrorsfilter.label";
	public static final String FILTER_TEXTWITHERRORSFILTER_TOOLTIP = "filter.textwitherrorsfilter.tooltip";
	public static final String FILTER_TEXTWITHERRORSFILTER_INSTRUCTIONS = "filter.textwitherrorsfilter.instructions";
	public static final String FILTER_TEXTWITHERRORSFILTER_FIELD1 = "filter.textwitherrorsfilter.field1";
	public static final String FILTER_TEXTWITHERRORSFILTER_FIELD1_TOOLTIP = "filter.textwitherrorsfilter.field1.tooltip";
	public static final String FILTER_TEXTWITHERRORSFILTER_EXCEPTION = "filter.textwitherrorsfilter.exception";
	public static final String FILTER_TEXTWITHERRORSFILTER_COMMAND_LINE_HELP = "filter.textwitherrorsfilter.command.line.help";
	public static final String FILTER_TEXTWITHERRORSFILTER_EXAMPLE_MAIN_INPUT = "filter.textwitherrorsfilter.example.main.input";
	public static final String FILTER_TEXTWITHERRORSFILTER_EXAMPLE_OUTPUT = "filter.textwitherrorsfilter.example.output";
	public static final String FILTER_TEXTWITHERRORSFILTER_REPORT = "filter.textwitherrorsfilter.report";

	// 103-MorseFilter
	public static final String FILTER_MORSE_LABEL = "filter.morse.label";
	public static final String FILTER_MORSE_TOOLTIP = "filter.morse.tooltip";
	public static final String FILTER_MORSE_INSTRUCTIONS = "filter.morse.instructions";
	public static final String FILTER_MORSE_CHECKBOX1 = "filter.morse.checkbox1";
	public static final String FILTER_MORSE_CHECKBOX1_TOOLTIP = "filter.morse.checkbox1.tooltip";
	public static final String FILTER_MORSE_CHECKBOX2 = "filter.morse.checkbox2";
	public static final String FILTER_MORSE_CHECKBOX2_TOOLTIP = "filter.morse.checkbox2.tooltip";
	public static final String FILTER_MORSE_COMMAND_LINE_HELP = "filter.morse.command.line.help";
	public static final String FILTER_MORSE_EXAMPLE_MAIN_INPUT = "filter.morse.example.main.input";
	public static final String FILTER_MORSE_EXAMPLE_OUTPUT = "filter.morse.example.output";
	public static final String FILTER_MORSE_REPORT = "filter.morse.report";

	// 104-DownloadInternetBlockerFilter
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_LABEL = "filter.downloadinternetblockerfilter.label";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_TOOLTIP = "filter.downloadinternetblockerfilter.tooltip";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_INSTRUCTIONS = "filter.downloadinternetblockerfilter.instructions";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_1 = "filter.downloadinternetblockerfilter.field.1";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_2 = "filter.downloadinternetblockerfilter.field.2";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_3 = "filter.downloadinternetblockerfilter.field.3";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_1_TOOLTIP = "filter.downloadinternetblockerfilter.field.1.tooltip";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_2_TOOLTIP = "filter.downloadinternetblockerfilter.field.2.tooltip";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_3_TOOLTIP = "filter.downloadinternetblockerfilter.field.3.tooltip";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_COMMAND_LINE_HELP = "filter.downloadinternetblockerfilter.command.line.help";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXAMPLE_MAIN_INPUT = "filter.downloadinternetblockerfilter.example.main.input";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXAMPLE_OUTPUT = "filter.downloadinternetblockerfilter.example.output";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_REPORT = "filter.downloadinternetblockerfilter.report";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXCEPTION_PARAMETER = "filter.downloadinternetblockerfilter.exception.parameter";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXCEPTION_CONNECTION = "filter.downloadinternetblockerfilter.exception.connection";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXCEPTION_PORT = "filter.downloadinternetblockerfilter.exception.port";
	public static final String FILTER_DOWNLOADINTERNETBLOCKERFILTER_PROGRESS = "filter.downloadinternetblockerfilter.progress";

	// 105-SimpleAlertFilter
	public static final String FILTER_SIMPLEALERT_LABEL = "filter.simplealert.label";
	public static final String FILTER_SIMPLEALERT_TOOLTIP = "filter.simplealert.tooltip";
	public static final String FILTER_SIMPLEALERT_INSTRUCTIONS = "filter.simplealert.instructions";
	public static final String FILTER_SIMPLEALERT_FIELD_1 = "filter.simplealert.field.1";
	public static final String FILTER_SIMPLEALERT_FIELD_1_TOOLTIP = "filter.simplealert.field.1.tooltip";
	public static final String FILTER_SIMPLEALERT_CHECK_BOX_1 = "filter.simplealert.check.box.1";
	public static final String FILTER_SIMPLEALERT_CHECK_BOX_1_TOOLTIP = "filter.simplealert.check.box.1.tooltip";
	public static final String FILTER_SIMPLEALERT_COMMAND_LINE_HELP = "filter.simplealert.command.line.help";
	public static final String FILTER_SIMPLEALERT_EXAMPLE_MAIN_INPUT = "filter.simplealert.example.main.input";
	public static final String FILTER_SIMPLEALERT_EXAMPLE_OUTPUT = "filter.simplealert.example.output";
	public static final String FILTER_SIMPLEALERT_NOT_FOUND_MESSAGE_ALERT = "filter.simplealert.not.found.message.alert";
	public static final String FILTER_SIMPLEALERT_FOUND_MESSAGE_ALERT = "filter.simplealert.found.message.alert";
	public static final String FILTER_SIMPLEALERT_PARAMETER_EXCEPTION = "filter.simplealert.parameter.exception";

	// End of filters already finished completely.

	public static final String APPLICATON_TITLE = "applicaton.title";
	public static final String FILTER_LIST = "filter.list";
	public static final String TREE_ROOT_NODE = "tree.root.node";
	public static final String TREE_ROOT_NODE_TOOLTIP = "tree.root.node.tooltip";
	public static final String TEXT_INITIALIZED = "text.initialized";
	public static final String TEXT_DISABLED = "text.disabled";
	public static final String TEXT_MODIFIED = "text.modified";
	public static final String TEXT_INFORMATION = "text.information";
	public static final String TEXT_FILE_EXTENSION = "text.file.extension";
	public static final String TEXT_AREA_NOT_SAVED = "text.area.not.saved";
	public static final String TEXT_AREA_NOT_SAVED_TEXT = "text.area.not.saved.text";
	public static final String CONFIGURATION_PAGE = "configuration.page";
	public static final String FILE_NOT_DEFINED = "fine.not.defined";
	public static final String CARET_POSITION = "caret.position";
	public static final String ADD_MEMORY = "add.memory";
	public static final String ADD_PREVIOUS = "add.previous";
	public static final String ADD_NEXT = "add.next";
	public static final String ADD_REMOVE = "add.remove";
	public static final String MEMORY_LABEL = "memory.label";
	public static final String CLEAR_CONTENT_BUTTON_INSTRUCTIONS = "clear.content.button.instructions";
	public static final String TREE_ROOT_TOOLTIP = "tree.root.tooltip";
	public static final String TABLE_COLUMN_LOCATION_TITLE = "table.column.location.title";
	public static final String TABLE_COLUMN_ACTION = "table.column.action";
	public static final String TABLE_ACTION_READ = "table.action.read";
	public static final String TABLE_ACTION_OVERWRITE = "table.action.overwrite";
	public static final String TABLE_ACTION_DELETE = "table.action.delete";
	public static final String TABLE_ACTION_CREATE = "table.action.create";
	public static final String TABLE_ACTION_RENAME_TO = "table.action.rename_to";
	public static final String TABLE_COLUMN_ROW_NUMBER = "table.column.row.number";
	public static final String TABLE_COLUMN_TYPE = "table.column.type";
	public static final String TABLE_COLUMN_STATUS = "table.column.status";
	public static final String TABLE_COLUMN_SIZE = "table.column.size";
	public static final String TABLE_COLUMN_SIZE_LABEL_BYTES = "table.column.size.label.bytes";
	public static final String TABLE_COLUMN_SIZE_LABEL_KILOBYTES = "table.column.size.label.kilobytes";
	public static final String TABLE_COLUMN_SIZE_LABEL_MEGABYTES = "table.column.size.label.megabytes";
	public static final String TABLE_COLUMN_SIZE_LABEL_GIGABYTES = "table.column.size.label.gigabytes";
	public static final String TARGET_1_BUTTON_LABEL = "target.1.button.label";
	public static final String TARGET_1_BUTTON_KEY = "target.1.button.key";
	public static final String TARGET_1_BUTTON_INSTRUCTIONS = "target.1.button.instructions";
	public static final String TABLE_TOOLTIP_TYPE_FILE = "table.tooltip.type.file";
	public static final String TABLE_TOOLTIP_TYPE_URL = "table.tooltip.type.url";
	public static final String TABLE_TOOLTIP_TYPE_TEXT = "table.tooltip.type.text";
	public static final String TABLE_TOOLTIP_STATUS_OK = "table.tooltip.status.ok";
	public static final String TABLE_TOOLTIP_STATUS_NOT_VERIFIED = "table.tooltip.status.not.verified";
	public static final String TABLE_TOOLTIP_STATUS_INVALID = "table.tooltip.status.invalid";
	public static final String TARGET_DIRECTORY_BUTTON_LABEL = "target.directory.button.label";
	public static final String TARGET_DIRECTORY_BUTTON_KEY = "target.directory.button.key";
	public static final String TARGET_DIRECTORY_BUTTON_INSTRUCTIONS = "target.directory.button.instructions";
	public static final String PREVIEW_DIALOG_WINDOW_TITLE = "preview.dialog.window.title";
	public static final String PREVIEW_CONFIRM_BUTTOM = "preview.confirm.buttom";
	public static final String PREVIEW_CONFIRM_BUTTOM_KEY = "preview.confirm.buttom.key";
	public static final String PREVIEW_CONFIRM_BUTTOM_INSTRUCTIONS = "preview.confirm.buttom_instructions";
	public static final String PREVIEW_CANCEL_BUTTOM = "preview.cancel.buttom";
	public static final String PREVIEW_CANCEL_BUTTOM_KEY = "preview.cancel.buttom.key";
	public static final String PREVIEW_CANCEL_BUTTOM_INSTRUCTIONS = "preview.cancel.buttom_instructions";
	public static final String PREVIEW_MESSAGE_TOP = "preview.message.top";
	public static final String FILE_OR_URL_LABEL = "file.or.url.label";
	public static final String COMMAND_LINE_FILEMODE_ON = "command.line.filemode.on";
	public static final String COMMAND_LINE_TEXTMODE_ON = "command.line.textmode.on";
	public static final String SUCCESS_PROCESSING_FILE = "success.processing.file";
	public static final String FAILURE_PROCESSING_FILE = "failure.processing.file";
	public static final String COMMAND_LINE_DOS_MODE_ON = "command.line.dos.mode.on";
	public static final String COMMAND_LINE_UNIX_MODE_ON = "command.line.unix.mode.on";
	public static final String COMMAND_LNE_MISSING_TARGET_DIRECTORY = "command.lne.missing.target.directory";
	public static final String COMMAND_LNE_INVALID_TARGET_DIRECTORY = "command.lne.invalid.target.directory";
	public static final String FAILURE_APPLYING_A_FILTER = "failure.applying.a.filter";
	public static final String FAILURE_CREATING_DIRECTORIES = "failure.creating.directories";
	public static final String FILTER_APPLIED_ON_TEXT = "filter.applied.on.text";
	public static final String FILTER_APPLIED_ON_SELECTION = "filter.applied.on.selection";
	public static final String JAVA_CATEGORY = "java.category";
	public static final String JAVA_CATEGORY_TOOLTIP = "java.category.tooltip";
	public static final String AWK_CATEGORY = "awk.category";
	public static final String AWK_CATEGORY_TOOLTIP = "awk.category.tooltip";
	public static final String CONFIGURATION_LANGUAGE_TITLE = "configuration.language.title";
	public static final String CONFIGURATION_LANGUAGE_PARAMETER = "configuration.language.parameter";
	public static final String CONFIGURATION_LANGUAGE_OPTION_OPERATIONA_SYSTEM = "configuration.language.option.operationa.system";
	public static final String CONFIGURATION_LANGUAGE_OPTION_ENGLISH = "configuration.language.option.english";
	public static final String CONFIGURATION_LANGUAGE_OPTION_PORTUGUES = "configuration.language.option.portugues";
	public static final String CONFIGURATION_OK_BUTTOM = "configuration.ok.buttom";
	public static final String CONFIGURATION_OK_KEY = "configuration.ok.key";
	public static final String CONFIGURATION_OK_INSTRUCTIONS = "configuration.ok.instructions";
	public static final String CONFIGURATION_CANCEL_BUTTOM = "configuration.cancel.buttom";
	public static final String CONFIGURATION_CANCEL_KEY = "configuration.cancel.key";
	public static final String CONFIGURATION_CANCEL_INSTRUCTIONS = "configuration.cancel.instructions";
	public static final String CONFIGURATION_WINDOW_TITLE = "configuration.window.title";
	public static final String CONFIGURATION_APPEARANCE = "configuration.appearance";
	public static final String CONFIGURATION_SELECT_THE_APPEARANCE = "configuration.select.the.appearance";
	public static final String CONFIGURATION_LOOKANDFEEL_OK_RESTART_APPLICATION = "configuration.lookandfeel.ok.restart.application";
	public static final String CONFIGURATION_THEME = "configuration.theme";
	public static final String CONFIGURATION_THEME_ACCORDING_TO_LOOK_AND_FEEL = "configuration.theme.according.to.look.and.feel";
	public static final String CONFIGURATION_FORCE_THEME = "configuration.force.theme";
	public static final String CONFIGURATION_DO_NOT_USE_THEME = "configuration.do.not.use.theme";
	public static final String CONFIGURATION_TREE_LINE_STYLE = "configuration.tree_line_style";
	public static final String CONFIGURATION_TREE_LINE_STYLE_ANGLED = "configuration.tree_line_style_angled";
	public static final String CONFIGURATION_TREE_LINE_STYLE_HORIZONTAL = "configuration.tree_line_style_horizontal";
	public static final String CONFIGURATION_TREE_LINE_STYLE_NONE = "configuration.tree_line_style_none";
	public static final String CONFIGURATION_TREE_LINE_STYLE_INSTRUCTION = "configuration.tree_line_style_instruction";

	public static final String FILE = "file";
	public static final String FILE_KEY = "file.key";
	public static final String FILE_INSTRUCTION = "file.instruction";

	public static final String EXIT = "exit";
	public static final String EXIT_KEY = "exit.key";
	public static final String EXIT_INSTRUCTION = "exit.instruction";

	public static final String OPTION = "option";
	public static final String OPTION_KEY = "option.key";
	public static final String OPTION_INSTRUCTION = "option.instruction";

	public static final String FIND_AND_REPLACE = "find.and.replace";
	public static final String FIND_AND_REPLACE_KEY = "find.and.replace.key";
	public static final String FIND_AND_REPLACE_INSTRUCTIONS = "find.and.replace.instructions";

	public static final String COMPARE = "compare";
	public static final String COMPARE_KEY = "compare.key";
	public static final String COMPARE_INSTRUCTIONS = "compare.instructions";

	public static final String VISUALIZE = "visualize";
	public static final String VISUALIZE_KEY = "visualize.key";
	public static final String VISUALIZE_INSTRUCTION = "visualize.instruction";

	public static final String CONFIGURATION = "configuration";
	public static final String CONFIGURATION_KEY = "configuration.key";
	public static final String CONFIGURATION_INSTRUCTION = "configuration.instruction";

	public static final String HELP = "help";
	public static final String HELP_KEY = "help.key";
	public static final String HELP_INSTRUCTION = "help.instruction";

	public static final String HELP_CONTENT = "help.content";
	public static final String HELP_CONTENT_KEY = "help.content.key";
	public static final String HELP_CONTENT_INSTRUCTION = "help.content.instruction";

	public static final String EXECUTE = "execute";
	public static final String EXECUTE_KEY = "execute.key";
	public static final String EXECUTE_INSTRUCTION = "execute.instruction";

	public static final String TOOLBAR_SWITCH_MAIN_OUTPUT = "toolbar.switch.main.output";
	public static final String TOOLBAR_SWITCH_MAIN_OUTPUT_KEY = "toolbar.switch.main.output.key";
	public static final String TOOLBAR_SWITCH_MAIN_OUTPUT_INSTRUCTION = "toolbar.switch.main.output.instruction";

	public static final String TOOLBAR_SWITCH_MAIN_AUXILIAR = "toolbar.switch.main.auxiliar";
	public static final String TOOLBAR_SWITCH_MAIN_AUXILIAR_INSTRUCTION = "toolbar.switch.main.auxiliar.instruction";
	public static final String TOOLBAR_SWITCH_AUXILIAR_OUTPUT = "toolbar.switch.auxiliar.output";
	public static final String TOOLBAR_SWITCH_AUXILIAR_OUTPUT_INSTRUCTION = "toolbar.switch.auxiliar.output.instruction";

	public static final String TOOLBAR_SAVETOPAS = "toolbar.savetopas";
	public static final String TOOLBAR_SAVETOPAS_KEY = "toolbar.savetopas.key";
	public static final String TOOLBAR_SAVETOPAS_INSTRUCTION = "toolbar.savetopas.instruction";

	public static final String TOOLBAR_AUXILIAR_SAVETOPAS = "toolbar.auxiliar.savetopas";
	public static final String TOOLBAR_AUXILIAR_SAVETOPAS_KEY = "toolbar.auxiliar.savetopas.key";
	public static final String TOOLBAR_AUXILIAR_SAVETOPAS_INSTRUCTION = "toolbar.auxiliar.savetopas.instruction";

	public static final String TOOLBAR_SAVEBOTTOMAS = "toolbar.savebottomas";
	public static final String TOOLBAR_SAVEBOTTOMAS_KEY = "toolbar.savebottomas.key";
	public static final String TOOLBAR_SAVEBOTTOMAS_INSTRUCTION = "toolbar.savebottomas.instruction";

	public static final String TOOLBAR_UPDATE_STATISTCS = "toolbar.update.statistcs";
	public static final String TOOLBAR_UPDATE_STATISTCS_KEY = "toolbar.update.statistcs.key";
	public static final String TOOLBAR_UPDATE_STATISTCS_INSTRUCTION = "toolbar.update.statistcs.instruction";

	public static final String TOOLBAR_NEWLINE = "toolbar.newline";
	public static final String TOOLBAR_NEWLINE_KEY = "toolbar.newline.key";
	public static final String TOOLBAR_NEWLINE_INSTRUCTION = "toolbar.newline.instruction";

	public static final String TOOLBAR_NEWLINETOP = "toolbar.newlinetop";
	public static final String TOOLBAR_NEWLINETOP_KEY = "toolbar.newlinetop.key";
	public static final String TOOLBAR_NEWLINETOP_INSTRUCTION = "toolbar.newlinetop.instruction";

	public static final String TOOLBAR_NEWLINEBOTTOM = "toolbar.newlinebottom";
	public static final String TOOLBAR_NEWLINEBOTTOM_KEY = "toolbar.newlinebottom.key";
	public static final String TOOLBAR_NEWLINEBOTTOM_INSTRUCTION = "toolbar.newlinebottom.instruction";

	public static final String TOOLBAR_SWITCHBETWEENTEXTANDFILE = "toolbar.switchbetweentextandfile";
	public static final String TOOLBAR_SWITCHBETWEENTEXTANDFILE_KEY = "toolbar.switchbetweentextandfile.key";
	public static final String TOOLBAR_SWITCHBETWEENTEXTANDFILE_INSTRUCTION = "toolbar.switchbetweentextandfile.instruction";

	public static final String TOOLBAR_SWITCHBETWEENTEXTANDFILE_ON = "toolbar.switchbetweentextandfile.on";
	public static final String TOOLBAR_SWITCHBETWEENTEXTANDFILE_ON_KEY = "toolbar.switchbetweentextandfile.on.key";
	public static final String TOOLBAR_SWITCHBETWEENTEXTANDFILE_ON_INSTRUCTION = "toolbar.switchbetweentextandfile.on.instruction";

	public static final String FILTER_HELP = "filter.help";
	public static final String FILTER_HELP_KEY = "filter.help.key";
	public static final String FILTER_HELP_INSTRUCTION = "filter.help.instruction";

	public static final String CHARACTER_CATEGORY = "character.category";
	public static final String CHARACTER_CATEGORY_TOOLTIP = "character.category.tooltip";
	public static final String LINE_CATEGORY = "line.category";
	public static final String LINE_CATEGORY_TOOLTIP = "line.category.tooltip";
	public static final String ORTOGRAPHY_CATEGORY = "ortography.category";
	public static final String ORTOGRAPHY_CATEGORY_TOOLTIP = "ortography.category.tooltip";
	public static final String TIMESHEET_CATEGORY = "timesheet.category";
	public static final String TIMESHEET_CATEGORY_TOOLTIP = "timesheet.category.tooltip";
	public static final String SEARCH_CATEGORY = "search.category";
	public static final String SEARCH_CATEGORY_TOOLTIP = "search.category.tooltip";
	public static final String UTILITIES_CATEGORY = "utilities.category";
	public static final String UTILITIES_CATEGORY_TOOLTIP = "utilities.category.tooltip";
	public static final String INTERNET_CATEGORY = "internet.category";
	public static final String INTERNET_CATEGORY_TOOLTIP = "internet.category.tooltip";
	public static final String FUN_CATEGORY = "fun.category";
	public static final String FUN_CATEGORY_TOOLTIP = "fun.category.tooltip";

	public static final String CONFIGURATION_FILE_SELECTION_BUTTOM = "configuration.file.selection.buttom";
	public static final String CONFIGURATION_FILE_SELECTION_KEY = "configuration.file.selection.key";
	public static final String CONFIGURATION_FILE_SELECTION_INSTRUCTIONS = "configuration.file.selection.instructions";

	public static final String CONFIGURATION_DIRECTORY_SELECTION_BUTTOM = "configuration.directory.selection.buttom";
	public static final String CONFIGURATION_DIRECTORY_SELECTION_KEY = "configuration.directory.selection.key";
	public static final String CONFIGURATION_DIRECTORY_SELECTION_INSTRUCTIONS = "configuration.directory.selection.instructions";

	public static final String COMMAND_LINE_APPEND_ON = "command.line.append.on";
	public static final String COMMAND_LINE_APPEND_OFF = "command.line.append.off";

	public static final String FILTER_FRAME_TITLE = "filter.frame.title";
	public static final String CONTROL_FRAME_TITLE = "control.frame.title";
	public static final String TOP_AREA_FRAME_TITLE = "top.area.frame.title";
	public static final String BOTTOM_AREA_FRAME_TITLE = "bottom.area.frame.title";
	public static final String AUXILIAR_INPUT_PANEL_TITLE = "auxiliar.input.panel.title";
	public static final String HELP_PANEL_TITLE = "help.panel.title";

	public static final String HELP_PANEL_INITIAL_TEXT = "help.panel.initial.text";
	public static final String HELP_PANEL_INITIAL_TEXT_AUTOMATIC = "help.panel.initial.text.automatic";

	public static final String DEFAULT_POSITION_ACTION_TITLE = "default.position.action.title";
	public static final String DEFAULT_POSITION_ACTION_KEY = "default.position.action.key";
	public static final String DEFAULT_POSITION_ACTION_INSTRUCTION = "default.position.action.instruction";
	public static final String VERTICAL_AREAS_ACTION_TITLE = "vertical.areas.action.title";
	public static final String VERTICAL_AREAS_ACTION_KEY = "vertical.areas.action.key";
	public static final String VERTICAL_AREAS_ACTION_INSTRUCTION = "vertical.areas.action.instruction";
	public static final String CONTROL_ON_THE_MIDDLE_ACTION_TITLE = "control.on.the.middle.action.title";
	public static final String CONTROL_ON_THE_MIDDLE_ACTION_KEY = "control.on.the.middle.action.key";
	public static final String CONTROL_ON_THE_MIDDLE_ACTION_INSTRUCTION = "control.on.the.middle.action.instruction";
	public static final String CONTROL_IN_THE_RIGHT_VERTICAL_ACTION_TITLE = "control.in.the.right.vertical.action.title";
	public static final String CONTROL_IN_THE_RIGHT_VERTICAL_ACTION_KEY = "control.in.the.right.vertical.action.key";
	public static final String CONTROL_IN_THE_RIGHT_VERTICAL_ACTION_INSTRUCTION = "control.in.the.right.vertical.action.instruction";
	public static final String CONTROL_IN_THE_RIGHT_ACTION_TITLE = "control.in.the.right.action.title";
	public static final String CONTROL_IN_THE_RIGHT_ACTION_KEY = "control.in.the.right.action.key";
	public static final String CONTROL_IN_THE_RIGHT_ACTION_INSTRUCTION = "control.in.the.right.action.instruction";

	public static final String METAL = "metal";
	public static final String NIMBUS = "nimbus";
	public static final String CDE_MOTIF = "cde.motif";
	public static final String WINDOWS = "windows";
	public static final String WINDOWS_CLASSIC = "windows.classic";
	public static final String TONIC = "tonic";
	public static final String TINY_YQ = "tiny.yq";
	public static final String TINY_FOREST = "tiny.forest";
	public static final String TINY_GOLDEN = "tiny.golden";
	public static final String TINY_NIGHTLY = "tiny.nightly";
	public static final String TINY_PLASTIC = "tiny.plastic";
	public static final String TINY_SILVER = "tiny.silver";
	public static final String TINY_UNICODE = "tiny.unicode";
	public static final String EASYNTH = "easynth";

	public static final String JTATOO_FAST = "jtatoo.fast";
	public static final String JTATOO_FAST_BLUE = "jtatoo.fast.blue";
	public static final String JTATOO_FAST_GREEN = "jtatoo.fast.green";
	public static final String JTATOO_GRAPHITE = "jtatoo.graphite";
	public static final String JTATOO_GRAPHITE_GREEN = "jtatoo.graphite.green";
	public static final String JTATOO_GRAPHITE_BLUE = "jtatoo.graphite.blue";
	public static final String JTATOO_SMART = "jtatoo.smart";
	public static final String JTATOO_SMART_GOLD = "jtatoo.smart.gold";
	public static final String JTATOO_SMART_GREEN = "jtatoo.smart.green";
	public static final String JTATOO_SMART_BROWN = "jtatoo.smart.brown";
	public static final String JTATOO_SMART_LEMMON = "jtatoo.smart.lemmon";
	public static final String JTATOO_SMART_GRAY = "jtatoo.smart.gray";
	public static final String JTATOO_ACRYL = "jtatoo.acryl";
	public static final String JTATOO_ACRYL_GREEN = "jtatoo.acryl.green";
	public static final String JTATOO_ACRYL_LEMMON = "jtatoo.acryl.lemmon";
	public static final String JTATOO_ACRYL_RED = "jtatoo.acryl.red";
	public static final String JTATOO_AERO = "jtatoo.aero";
	public static final String JTATOO_AERO_GOLD = "jtatoo.aero.gold";
	public static final String JTATOO_AERO_GREEN = "jtatoo.aero.green";
	public static final String JTATOO_BERNSTEIN = "jtatoo.bernstein";
	public static final String JTATOO_ALUMINIUM = "jtatoo.aluminium";
	public static final String JTATOO_MAC_WINDOWS = "jtatoo.mac.windows";
	public static final String JTATOO_MAC_WINDOWS_PINK = "jtatoo.mac.windows.pink";
	public static final String JTATOO_MINT = "jtatoo.mint";
	public static final String JTATOO_HIFI = "jtatoo.hifi";
	public static final String JTATOO_NOIRE = "jtatoo.noire";
	public static final String JTATOO_LUNA = "jtatoo.luna";

	public static final String NAPKING = "napking";
	public static final String QUAQUA = "quaqua";
	public static final String OFFICE_2003 = "office.2003";
	public static final String OFFICE_XP = "office.xp";
	public static final String VISUAL_STUDIO_2005 = "visual.studio.2005";
	public static final String LIQUID = "liquid";
	public static final String FH = "fh";
	public static final String NIMROD = "nimrod";
	public static final String SQUARENESS = "squareness";
	public static final String JGOODIES_BROW = "jgoodies.brow";
	public static final String JGOODIES_3D_BROW = "jgoodies.3d.brow";
	public static final String JGOODIES_DARK = "jgoodies.dark";
	public static final String JGOODIES_3D_DARK = "jgoodies.3d.dark";
	public static final String JGOODIES_BLUE = "jgoodies.blue";
	public static final String JGOODIES_3D_BLUE = "jgoodies.3d.blue";
	public static final String JGOODIES_BLUER = "jgoodies.bluer";
	public static final String JGOODIES_3D_BLUER = "jgoodies.3d.bluer";
	public static final String JGOODIES_GREEN = "jgoodies.green";
	public static final String JGOODIES_3D_GREEN = "jgoodies.3d.green";
	public static final String JGOODIES_RED = "jgoodies.red";
	public static final String JGOODIES_3D_RED = "jgoodies.3d.red";
	public static final String JGOODIES_YELLOW = "jgoodies.yellow";
	public static final String JGOODIES_3D_YELLOW = "jgoodies.3d.yellow";
	public static final String JGOODIES_EXPERIENCE_BLUE = "jgoodies.experience.blue";
	public static final String JGOODIES_3D_EXPERIENCE_BLUE = "jgoodies.3d.experience.blue";
	public static final String JGOODIES_EXPERIENCE_GREEN = "jgoodies.experience.green";
	public static final String JGOODIES_3D_EXPERIENCE_GREEN = "jgoodies.3d.experience.green";
	public static final String JGOODIES_EXPERIENCE_ROYALE = "jgoodies.experience.royale";
	public static final String JGOODIES_3D_EXPERIENCE_ROYALE = "jgoodies.3d.experience.royale";
	public static final String JGOODIES_SILVER = "jgoodies.silver";
	public static final String JGOODIES_3D_SILVER = "jgoodies.3d.silver";
	public static final String JGOODIES_SKY_BLUE = "jgoodies.sky.blue";
	public static final String JGOODIES_3D_SKY_BLUE = "jgoodies.3d.sky.blue";
	public static final String JGOODIES_SKY_KRUPP = "jgoodies.sky.krupp";
	public static final String JGOODIES_3D_SKY_KRUPP = "jgoodies.3d.sky.krupp";
	public static final String JGOODIES_SKY_PINK = "jgoodies.sky.pink";
	public static final String JGOODIES_3D_SKY_PINK = "jgoodies.3d.sky.pink";
	public static final String JGOODIES_SKY_RED = "jgoodies.sky.red";
	public static final String JGOODIES_3D_SKY_RED = "jgoodies.3d.sky.red";
	public static final String JGOODIES_SKY_YELLOW = "jgoodies.sky.yellow";
	public static final String JGOODIES_3D_SKY_YELLOW = "jgoodies.3d.sky.yellow";

	public static final String WINDOW_STRUCTURE_TITLE = "window.structure.title";
	public static final String USE_INTERNAL_WINDOW_STRUCTURE = "use.internal.window.structure";
	public static final String USE_SPLITTERS_STRUCTURE = "use.splitters.structure";

	public static final String COMMAND_LINE_MAIN_HELP_1 = "command.line.main.help.1";
	public static final String COMMAND_LINE_MAIN_HELP_2 = "command.line.main.help.2";
	public static final String COMMAND_NOT_FOUND = "command.not.found";
	public static final String EXCEPTION_BUFFER = "exception.buffer";
	public static final String COMMAND_LINE_LEAVE = "command.line.leave";
	public static final String COMMAND_LINE_INCORRECT_USE = "command.line.incorrect.use";
	public static final String COMMAND_LINE_FILE_NOT_FOUND = "command.line.file.not.found";
	public static final String COMMAND_LINE_PARAMETER_IS_NOT_FILE = "command.line.parameter.is.not.file";
	public static final String COMMAND_LINE_PARAMETER_TARGET_INVALID_FILE_NAME = "command.line.parameter.target.invalid.file.name";
	public static final String COMMAND_LINE_INVALID_FILE = "command.line.invalid.file";
	public static final String COMMAND_LINE_INTERNAL_COMMANDS = "command.line.internal.commands";
	public static final String COMMAND_LINE_OVERWRITE_ON = "command.line.overwrite.on";
	public static final String COMMAND_LINE_OVERWRITE_OFF = "command.line.overwrite.off";
	public static final String COMMAND_LINE_FILE_ALREADY_EXIST = "command.line.file.already.exist";
	public static final String COMMAND_LINE_FILE_WRITING_ON_OUTPUT = "command.line.file.writing.on.output";
	public static final String COMMAND_LINE_EXECUTED = "command.line.executed";
	public static final String COMMAND_LINE_EXECUTED_WITH_ERROR = "command.line.executed.with.error";
	public static final String COMMAND_LINE_INVALID_COMMAND = "command.line.invalid.command";
	public static final String COMMAND_LINE_ENDED_WITH_ERROR = "command.line.ended.with.error";
	public static final String COMMAND_LINE_INCORRECT_NUMBER_OF_PARAMETERS = "command.line.incorrect.number.of.parameters";
	public static final String COMMAND_LINE_ENCODING_NOT_FOUND = "command.line.encoding.not.found";
	public static final String COMMAND_LINE_INPUT_ENCODING_ON_DETECT_MODE = "command.line.input.encoding.on.detect.mode";
	public static final String COMMAND_LINE_MISSING_SECOND_PARAMETER = "command.line.missing.second.parameter";
	public static final String COMMAND_LINE_SCRIPT_NAME_MISSING = "command.line.script.name.missing";
	public static final String COMMAND_LINE_SCRIPT_FILE_PROBLEM = "command.line.script.file.problem";
	public static final String COMMAND_LINE_SCRIPT_EXECUTED = "command.line.script.executed";
	public static final String COMMAND_LINE_REQUIRED_PARAMETER_MISSING = "command.line.required.parameter.missing";
	public static final String DEFINITION_ALREADY_SET = "definition.already.set";
	public static final String COMMAND_LINE_SET_FORMAT_HELP = "command.line.set.format.help";
	public static final String COMMAND_LINE_DEFINITION_SET = "command.line.definition.set";
	public static final String COMMAND_LINE_VARIABLE_NOT_FOUND = "command.line.variable.not.found";
	public static final String CHANGES_REQUIRES_RESTART = "changes.requires.restart";
	public static final String SCRIPT_FILE_DOES_NOT_EXIST = "script.file.does.not.exist";
	public static final String LINE_START_IS_NEGATIVE = "line.start.is.negative";
	public static final String LINE_END_IS_NEGATIVE = "line.end.is.negative";
	public static final String LINE_START_IS_GREATER_THEN_TEXT_LENGTH = "line.start.is.greater.then.text.length";
	public static final String LINE_END_IS_NOT_GREATER_THEN_LINE_START = "line.end.is.not.greater.then.line.start";
	public static final String PROVIDE_BOTH_LINE_START_AND_END = "provide.both.line.start.and.end";
	public static final String CHARATER_START_IS_NEGATIVE = "charater.start.is.negative";
	public static final String CHARATER_END_IS_NEGATIVE = "charater.end.is.negative";
	public static final String CHARATER_START_IS_GREATER_THEN_TEXT_LENGTH = "charater.start.is.greater.then.text.length";
	public static final String CHARATER_END_IS_NOT_GREATER_THEN_LINE_START = "charater.end.is.not.greater.then.line.start";
	public static final String PROVIDE_BOTH_CHARACTER_START_AND_END = "provide.both.character.start.and.end";
	public static final String INVALID_LINE_START = "invalid.line.start";
	public static final String INVALID_LINE_END = "invalid.line.end";
	public static final String INVALID_CHARACTER_START = "invalid.character.start";
	public static final String INVALID_CHARACTER_END = "invalid.character.end";

	public static final String CONFIGURATION_LAYOUT = "configuration.layout";
	public static final String CONFIGURATION_LAYOUT_SPLITTERS = "configuration.layout.splitters";
	public static final String CONFIGURATION_LAYOUT_INTERNAL_DESKTOP = "configuration.layout.internal.desktop";

	public static final String FILTER_EXAMPLE = "filter.example";
	public static final String FILTER_EXAMPLE_KEY = "filter.example.key";
	public static final String FILTER_EXAMPLE_INSTRUCTION = "filter.example.instruction";

	public static final String AUTOMATIC_MODE_ON_ICON = "automatic.mode.on.icon";
	public static final String AUTOMATIC_MODE_ON_ICON_KEY = "automatic.mode.on.icon.key";
	public static final String AUTOMATIC_MODE_ON_ICON_INFORMATION = "automatic.mode.on.icon.information";

	public static final String MAXIMIZE_ICON = "maximize.icon";
	public static final String MAXIMIZE_ICON_KEY = "maximize.icon.key";
	public static final String MAXIMIZE_ICON_INFORMATION = "maximize.icon.information";

	public static final String RESTORE_WINDOW_ICON = "restore.window.icon";
	public static final String RESTORE_WINDOW_ICON_KEY = "restore.window.icon.key";
	public static final String RESTORE_WINDOW_ICON_INFORMATION = "restore.window.icon.information";

	public static final String AUTOMATIC_MODE_OFF_ICON = "automatic.mode.off.icon";
	public static final String AUTOMATIC_MODE_OFF_ICON_KEY = "automatic.mode.off.icon.key";
	public static final String AUTOMATIC_MODE_OFF_ICON_INFORMATION = "automatic.mode.off.icon.information";

	public static final String BINARY_FILTER_WORK_ONLY_ON_FILE_MODE = "binary.filter.work.only.on.file.mode";
	public static final String TARGET_DIRECTORY_OVERWRITE_CURRENT_FILES_EXCEPTION = "target.directory.overwrite.current.files.exception";

	public static final String DUAL_PANE_CATEGORY = "dual.pane.category";
	public static final String DUAL_PANE_CATEGORY_TOOLTIP = "dual.pane.category.tooltip";
	public static final String FILTER_DEMONSTRATION_TEXT = "filter.demonstration.text";
	public static final String AUTOMATIC_EXECUTION_MODE_ON = "automatic.execution.mode.on";
	public static final String AUTOMATIC_EXECUTION_MODE_ON_INSTRUCTIONS = "automatic.execution.mode.on.instructions";

	public static final String EXCEPTION_WHILE_READING_DOCKING_CONFIGURATION = "exception.while.reading.docking.configuration";
	public static final String EXCEPTION_WHILE_READING_HELP_FILE = "exception.while.reading.help.file";
	public static final String FILTERANY_HELP_WINDOW_TITLE = "filterany.help.window.title";

	public static final String EXCEPTION_WHILE_READING_SHORCUT_FILE = "exception.while.reading.shorcut.file";

	public static final String PARAMETER_NOT_USED_BY_FILTER_EXCEPTON = "parameter.not.used.by.filter.excepton";
	public static final String PARAMETER_NOT_USED_WHEN_IN_TEXT_MODE = "parameter.not.used.when.in.text.mode";

	public static final String MAIN_SEND_TO_POPUP_MENU = "main.send.to.popup.menu";

	public static final String REQUIRED_FIELD = "required.field";
	public static final String OPTIONAL_FIELD = "optional.field";

	public static final String AUTOMATIC_HELP_MODE_TITLE_ON = "automatic.help.mode.title.on";
	public static final String AUTOMATIC_HELP_MODE_TITLE_INFORMATION_ON = "automatic.help.mode.title.information.on";
	public static final String AUTOMATIC_HELP_MODE_TITLE_OFF = "automatic.help.mode.title.off";
	public static final String AUTOMATIC_HELP_MODE_TITLE_INFORMATION_OFF = "automatic.help.mode.title.information.off";

	public static final String USER_LEVEL_DIALOG_TITLE = "user.level.dialog.title";
	public static final String USER_LEVEL_DIALOG_QUESTION = "user.level.dialog.question";
	public static final String USER_LEVEL_DIALOG_BASIC_OPTION = "user.level.dialog.basic.option";
	public static final String USER_LEVEL_DIALOG_ADVANCED_OPTION = "user.level.dialog.advanced.option";
	public static final String USER_LEVEL_DIALOG_BUTTOM = "user.level.dialog.buttom";
	public static final String USER_LEVEL_DIALOG_INSTRUCTIONS = "user.level.dialog.instructions";

	public static final String FREE_SELECTION_CATEGORY = "free.selection.category";
	public static final String FREE_SELECTION_CATEGORY_TOOLTIP = "free.selection.category.tooltip";

	public static final String BINARY_CATEGORY = "binary.category";
	public static final String BINARY_CATEGORY_TOOLTIP = "binary.category.tooltip";

	public static final String FIND_DIALOG_TITLE = "find.dialog.title";
	public static final String FIND_DIALOG_FIND_LABEL = "find.dialog.find.label";
	public static final String FIND_DIALOG_REPLACE_WITH_LABEL = "find.dialog.replace.with.label";
	public static final String FIND_DIALOG_FORWARD = "find.dialog.forward";
	public static final String FIND_DIALOG_BACKWARD = "find.dialog.backward";
	public static final String FIND_DIALOG_CASE_SENSITIVE = "find.dialog.case.sensitive";
	public static final String FIND_DIALOG_REGULAR_EXPRESSION = "find.dialog.regular.expression";
	public static final String FIND_DIALOG_FIND_BUTTON = "find.dialog.find.button";
	public static final String FIND_DIALOG_REPLACE_FIND_BUTTON = "find.dialog.replace.find.button";
	public static final String FIND_DIALOG_REPLACE_BUTTON = "find.dialog.replace.button";
	public static final String FIND_DIALOG_REPLACE_ALL = "find.dialog.replace.all";
	public static final String FIND_DIALOG_CLOSE_BUTTON = "find.dialog.close.button";
	public static final String FIND_DIALOG_DIRECTIONS_BORDER = "find.dialog.directions.border";
	public static final String FIND_DIALOG_OPTIONS_BORDER = "find.dialog.options.border";
	public static final String FIND_DIALOG_CONTROLS_BORDER = "find.dialog.controls.border";
	public static final String FIND_DIALOG_MESSAGE_REACHED_END_OF_FILE = "find.dialog.message.reached.end.of.file";
	public static final String FIND_DIALOG_MESSAGE_ALREADY_AT_THE_END_OF_FILE = "find.dialog.message.already.at.the.end.of.file";
	public static final String FIND_DIALOG_MESSAGE_ALREADY_AT_THE_BEGINNING_OF_FILE = "find.dialog.message.already.at.the.beginning.of.file";
	public static final String FIND_DIALOG_MESSAGE_REACHED_BEGINNING_OF_FILE = "find.dialog.message.reached.beginning.of.file";
	public static final String FIND_DIALOG_MESSAGE_ERROR_ON_REGULAR_EXPRESSION = "find.dialog.message.error.on.regular.expression";
	public static final String FIND_DIALOG_TEXT_TOOLTIP = "find.dialog.text.tooltip";
	public static final String FIND_DIALOG_REPLACE_WITH_TOOLTIP = "find.dialog.replace.with.tooltip";
	public static final String FIND_DIALOG_FORWARD_TOOLTIP = "find.dialog.forward.tooltip";
	public static final String FIND_DIALOG_BACKWARD_TOOLTIP = "find.dialog.backward.tooltip";
	public static final String FIND_DIALOG_CASE_SENSITIVE_TOOLTIP = "find.dialog.case.sensitive.tooltip";
	public static final String FIND_DIALOG_REGULAR_EXPRESSION_TOOLTIP = "find.dialog.regular.expression.tooltip";
	public static final String FIND_DIALOG_BUTTON_TOOLTIP = "find.dialog.button.tooltip";
	public static final String FIND_DIALOG_REPLACE_TOOLTIP = "find.dialog.replace.tooltip";
	public static final String FIND_DIALOG_REPLACE_AND_FIND_TOOLTIP = "find.dialog.replace.and.find.tooltip";
	public static final String FIND_DIALOG_REPLACE_ALL_TOOLTIP = "find.dialog.replace.all.tooltip";
	public static final String FIND_DIALOG_CANCEL_TOOLTIP = "find.dialog.cancel.tooltip";

	public static final String TEXT_AREA_SAVE_SHORT_CUT_KEY = "text.area.save.short.cut.key";

	public static final String TEXT_AREA_FIND_SHORT_CUT_KEY = "text.area.find.short.cut.key";

	public static final String TEXT_AREA_FIND_MAIN_SHORT_CUT_TEXT = "text.area.find.main.short.cut.text";
	public static final String TEXT_AREA_FIND_MAIN_SHORT_CUT_TEXT_TOOL_TIP = "text.area.find.main.short.cut.text.tool.tip";

	public static final String TEXT_AREA_FIND_OUTPUT_SHORT_CUT_TEXT = "text.area.find.output.short.cut.text";
	public static final String TEXT_AREA_FIND_OUTPUT_SHORT_CUT_TEXT_TOOL_TIP = "text.area.find.output.short.cut.text.tool.tip";

	public static final String TEXT_AREA_FIND_AUXILIAR_SHORT_CUT_TEXT = "text.area.find.auxiliar.short.cut.text";
	public static final String TEXT_AREA_FIND_AUXILIAR_SHORT_CUT_TEXT_TOOL_TIP = "text.area.find.auxiliar.short.cut.text.tool.tip";

	public static final String TEXT_AREA_FIND_SCRAPBOOK_SHORT_CUT_TEXT = "text.area.find.scrapbook.auxiliar.short.cut.text";
	public static final String TEXT_AREA_FIND_SCRAPBOOK_AUXILIAR_SHORT_CUT_TEXT_TOOL_TIP = "text.area.find.scrapbook.auxiliar.short.cut.text.tool.tip";

	public static final String FILTER_COMPARE_TEXTS_KEY = "filter.compare.texts.key";
	public static final String FILTER_COMPARE_TEXTS_TEXT = "filter.compare.texts.text";
	public static final String FILTER_COMPARE_TEXTS_INSTRUCTION = "filter.compare.texts.instruction";
	public static final String FILTER_COMPARE_TEXTS_TEXT_MAIN_WITH_AUXILIAR = "filter.compare.texts.text.main.with.auxiliar";
	public static final String FILTER_COMPARE_TEXTS_TEXT_OUTPUT_WITH_AUXILIAR = "filter.compare.texts.text.output.with.auxiliar";

	public static final String FILTER_CONFIGURATION_SAVE_STYLE_TITLE = "filter.configuration.save.style.title";
	public static final String FILTER_CONFIGURATION_SAVE_STYLE_IGNORE = "filter.configuration.save.style.ignore";
	public static final String FILTER_CONFIGURATION_SAVE_STYLE_WARN = "filter.configuration.save.style.warn";
	public static final String FILTER_CONFIGURATION_SAVE_STYLE_PERSIST = "filter.configuration.save.style.persist";

	public static final String FILTER_CLEAR_FIELD_INSTRUCTION = "filter.clear.field.instruction";

	public static final String TEXT_FIELDS_BEHAVIOR_BORDER_TITLE = "text.fields.behavior.border.title";
	public static final String TEXT_FIELDS_BEHAVIOR_AUTOMATIC_CLEAN_UP = "text.fields.behavior.automatic.clean.up";
	public static final String TEXT_FIELDS_BEHAVIOR_CLEAR_CONTENT_BUTTONS = "text.fields.behavior.clear.content.buttons";

	public static final String MEMORY_ACTION_TEXT_1_INSTRUCTION = "memory.action.text.1.instruction";
	public static final String MEMORY_ACTION_TEXT_2_INSTRUCTION = "memory.action.text.2.instruction";
	public static final String MEMORY_ACTION_TEXT_3_INSTRUCTION = "memory.action.text.3.instruction";
	public static final String MEMORY_ACTION_TEXT_4_INSTRUCTION = "memory.action.text.4.instruction";
	public static final String MEMORY_ACTION_TEXT_5_INSTRUCTION = "memory.action.text.5.instruction";
	public static final String MEMORY_NOT_DEFINED = "memory.not.defined";

	public static final String ADD_MEMORY_1_TITLE = "add.memory.1.title";
	public static final String ADD_MEMORY_1_INFORMATION = "add.memory.1.information";
	public static final String ADD_MEMORY_2_TITLE = "add.memory.2.title";
	public static final String ADD_MEMORY_2_INFORMATION = "add.memory.2.information";
	public static final String ADD_MEMORY_3_TITLE = "add.memory.3.title";
	public static final String ADD_MEMORY_3_INFORMATION = "add.memory.3.information";
	public static final String ADD_MEMORY_4_TITLE = "add.memory.4.title";
	public static final String ADD_MEMORY_4_INFORMATION = "add.memory.4.information";
	public static final String ADD_MEMORY_5_TITLE = "add.memory.5.title";
	public static final String ADD_MEMORY_5_INFORMATION = "add.memory.5.information";
	public static final String CLEAR_MEMORY_BUTTON = "clear.memory.button";

	public static final String CATEGORY_UNDER_CONSTRUCTION = "category.under.construction";
	public static final String CATEGORY_UNDER_CONSTRUCTION_INFORMATION = "category.under.construction.information";

	public static final String COMPARE_MAIN_WITH_OUTPUT_WINDOW_TITLE = "compare.main.with.output.window.title";

	public static final String SCRAP_BOOK = "scrap.book";
	public static final String SCRAP_BOOK_KEY = "scrap.book.key";
	public static final String SCRAP_BOOK_INSTRUCTION = "scrap.book.instruction";

	public static final String SCRAP_BOOK_WINDOW_TITLE = "scrap.book.window.title";

	public static final String REMOVE_TABULATION_INFORMATION_INSTRUCTION = "remove.tabulation.information.instruction";
	public static final String ADD_TABULATION_INFORMATION_INSTRUCTION = "add.tabulation.information.instruction";

	public static final String TEXT_AREA_INFORMATION_INSTRUCTION = "text.area.information.instruction";

	public static final String LOOK_AND_FEEL_EXAMPLE_TITLE = "look.and.feel.example.title";
	public static final String LOOK_AND_FEEL_EXAMPLE_BORDER = "look.and.feel.example.border";
	public static final String LOOK_AND_FEEL_EXAMPLE_LABEL = "look.and.feel.example.label";
	public static final String LOOK_AND_FEEL_EXAMPLE_ITEM1 = "look.and.feel.example.item1";
	public static final String LOOK_AND_FEEL_EXAMPLE_ITEM2 = "look.and.feel.example.item2";
	public static final String LOOK_AND_FEEL_EXAMPLE_TEXT_AREA = "look.and.feel.example.text.area";
	public static final String LOOK_AND_FEEL_EXAMPLE_TEXT_LABEL = "look.and.feel.example.text.label";
	public static final String LOOK_AND_FEEL_EXAMPLE_TEXT_FIELD = "look.and.feel.example.text.field";
	public static final String LOOK_AND_FEEL_EXAMPLE_TEXT_CHECK_BOX = "look.and.feel.example.text.check.box";
	public static final String LOOK_AND_FEEL_EXAMPLE_PREVIEW = "look.and.feel.example.preview";

	public static final String CONFIGURATION_TAB_LOOK_AND_FEEL = "configuration.tab.look.and.feel";
	public static final String CONFIGURATION_TAB_LOOK_AND_FEEL_INFORMATION = "configuration.tab.look.and.feel.information";
	public static final String CONFIGURATION_TAB_BEHAVIOR = "configuration.tab.behavior";
	public static final String CONFIGURATION_TAB_BEHAVIOR_INFORMATION = "configuration.tab.behavior.information";

	public static final String EXAMPLE_OVERWRITE_QUESTION_TITLE = "example.overwrite.question.title";
	public static final String EXAMPLE_OVERWRITE_QUESTION_MESSAGE = "example.overwrite.question.message";
	public static final String EXAMPLE_OVERWRITE_MAIN_INPUT = "example.overwrite.main.input";
	public static final String EXAMPLE_OVERWRITE_OUTPUT = "example.overwrite.output";
	public static final String EXAMPLE_OVERWRITE_AUXILIAR = "example.overwrite.auxiliar";

	public static final String POSITION = "position";
	public static final String POSITION_KEY = "position.key";
	public static final String POSITION_INSTRUCTION = "position.instruction";

	public static final String DOCKING_THEME_TITLE = "docking.theme.title";
	public static final String DOCKING_THEME_SMOOTH = "docking.theme.smooth";
	public static final String DOCKING_THEME_BASIC = "docking.theme.basic";
	public static final String DOCKING_THEME_FLAT = "docking.theme.flat";
	public static final String DOCKING_THEME_NO_STACK = "docking.theme.no.stack";

	public static final String SPLIT_STRUCTURE_TITLE = "split.structure.title";
	public static final String SPLIT_STRUCTURE_CONTROLS_ON_THE_RIGHT = "split.structure.controls.on.the.right";
	public static final String SPLIT_STRUCTURE_VERTICAL_SPLIT = "split.structure.vertical.split";

	public static final String DEMONSTRATION_TEXT_AREA = "demonstration.text.area";

	public static final String ALERT_MESSAGE = "alert.message";
	public static final String ALERT_WINDOW_TITLE = "alert.window.title";
	public static final String ALERT_WINDOW_MESSAGE = "alert.window.message";

	public static final String ABOUT_WINDOW_TITLE = "about.window.title";

	public static final String ABOUT_MENU = "about.menu";
	public static final String ABOUT_MENU_KEY = "about.menu.key";
	public static final String ABOUT_MENU_INFORMATION = "about.menu.information";

	public static final String COMMERCIAL_LICENCE = "commercial.licence";
	public static final String DEVELOPER_LICENSE = "developer.license";
	public static final String LICENSE_SERIAL = "license.serial";
	public static final String FREE_COMPONENTS = "free.components";
	public static final String REGISTER_NAME = "register.name";

	public static final String REGISTER_PREFIX = "register.prefix";
	public static final String DEMONSTRATION_VERSION_TEXT = "demonstration.version.text";
	public static final String REGISTRATION_WINDOW_TITLE = "registration.window.title";
	public static final String REGISTRATION_MAIN_MESSAGE = "registration.main.message";
	public static final String REGISTRATION_SECONDARY_MESSAGE = "registration.secondary.message";

	public static final String REGISTRATION_FORM_NAME = "registration.form.name";
	public static final String REGISTRATION_FORM_ADDRESS = "registration.form.address";
	public static final String REGISTRATION_FORM_CITY = "registration.form.city";
	public static final String REGISTRATION_FORM_STATE = "registration.form.state";
	public static final String REGISTRATION_FORM_ZIP = "registration.form.zip";
	public static final String REGISTRATION_FORM_COUNTRY = "registration.form.country";
	public static final String REGISTRATION_FORM_E_MAIL = "registration.form.e.mail";
	public static final String REGISTRATION_FORM_PHONE = "registration.form.phone";

	public static final String CREATE_REGISTRATION_EMAIL_BUTTON = "create.registration.email.button";
	public static final String CREATE_REGISTRATION_EMAIL_BUTTON_KEY = "create.registration.email.button.key";
	public static final String CREATE_REGISTRATION_EMAIL_BUTTON_INSTRUCTIONS = "create.registration.email.button.instructions";
	public static final String CANCEL_REGISTRATION = "cancel.registration";
	public static final String CANCEL_REGISTRATION_KEY = "cancel.registration.key";
	public static final String CANCEL_REGISTRATION_INSTRUCTIONS = "cancel.registration.instructions";

	public static final String REGISTRATION_ICON = "registration.icon";
	public static final String REGISTRATION_BUTTON = "registration.button";
	public static final String REGISTRATION_KEY = "registration.key";
	public static final String REGISTRATION_INFORMATION = "registration.information";

	public static final String LICENSE_AGREEMENT = "license.agreement";
	public static final String REGISTER_FORM = "register.form";
	public static final String REGISTER_INSTRUCTIONS = "register.instructions";

	public static final String ACCEPT_AGREEMENT_BUTTON = "accept.agreement.button";
	public static final String ACCEPT_AGREEMENT_BUTTON_KEY = "accept.agreement.button.key";
	public static final String ACCEPT_AGREEMENT_BUTTON_INSTRUCTIONS = "accept.agreement.button.instructions";
	public static final String DO_NOT_ACCEPT_AGREEMENT_BUTTON = "do.not.accept.agreement.button";
	public static final String DO_NOT_ACCEPT_AGREEMENT_BUTTON_KEY = "do.not.accept.agreement.button.key";
	public static final String DO_NOT_ACCEPT_AGREEMENT_BUTTON_INSTRUCTIONS = "do.not.accept.agreement.button.instructions";

	public static final String LICENCE_AGREEMENT_TITLE = "licence.agreement.title";

	public static final String REGISTER_EMAIL_TEXT = "register.email.text";
	public static final String REGISTER_EMAIL_CONTACTATION_TEXT = "register.email.contactation.text";
	public static final String SEND_EMAIL_WINDOW_TITLE = "send.email.window.title";

	public static final String LICENSE_BUTTON = "license.button";
	public static final String LICENSE_BUTTON_KEY = "license.button.key";
	public static final String LICENSE_BUTTON_INSTRUCTION = "license.button.instruction";

	public static final String DOCKING_THEME_SMOOTH_TOOLTIP = "docking.theme.smooth.tooltip";
	public static final String DOCKING_THEME_BASIC_TOOLTIP = "docking.theme.basic.tooltip";
	public static final String DOCKING_THEME_FLAT_TOOLTIP = "docking.theme.flat.tooltip";
	public static final String DOCKING_THEME_BUBBLE_TOOLTIP = "docking.theme.bubble.tooltip";
	public static final String DOCKING_THEME_ECLIPSE_TOOLTIP = "docking.theme.eclipse.tooltip";
	public static final String DOCKING_THEME_NO_STACK_TOOLTIP = "docking.theme.no.stack.tooltip";

	public static final String LANGUAGELIST_TOOLTIP = "languagelist.tooltip";

	public static final String APPEARANCE_LIST_TOOLTIP = "appearance.list.tooltip";

	public static final String IGNORE_MODE_OPTION_TOOLTIP = "ignore.mode.option.tooltip";
	public static final String WARN_MODE_OPTION_TOOLTIP = "warn.mode.option.tooltip";
	public static final String PERSIST_MODE_OPTION_TOOLTIP = "persist.mode.option.tooltip";

	public static final String CONTROLS_ON_THE_RIGHT_OPTION_TOOLTIP = "controls.on.the.right.option.tooltip";
	public static final String VERTICAL_SPLIT_OPTION_TOOLTIP = "vertical.split.option.tooltip";

	public static final String INTERNAL_WINDOW_OPTION_TOOLTIP = "internal.window.option.tooltip";
	public static final String SPLITTERS_OPTION_TOOLTIP = "splitters.option.tooltip";

	public static final String AUTOMATIC_CLEANUP_BEHAVIOR_OPTION_TOOLTIP = "automatic.cleanup.behavior.option.tooltip";
	public static final String CLEAR_TEXT_BUTTONS_OPTION_TOOLTIP = "clear.text.buttons.option.tooltip";

	public static final String DEPEND_ON_LOOK_AND_FEEL_OPTION_TOOLTIP = "depend.on.look.and.feel.option.tooltip";
	public static final String FORCE_THEME_OPTION_TOOLTIP = "force.theme.option.tooltip";
	public static final String DO_NOT_USE_THEME_OPTION_TOOLTIP = "do.not.use.theme.option.tooltip";

	public static final String ANGLED_OPTION_TOOLTIP = "angled.option.tooltip";
	public static final String HORIZONTAL_OPTION_TOOLTIP = "horizontal.option.tooltip";
	public static final String NONE_OPTION_TOOLTIP = "none.option.tooltip";

	public static final String COPY_FROM_MAIN = "copy.from.main";
	public static final String COPY_FROM_MAIN_KEY = "copy.from.main.key";
	public static final String COPY_FROM_MAIN_INFORMATION = "copy.from.main.information";
	public static final String CUT_FROM_MAIN = "cut.from.main";
	public static final String CUT_FROM_MAIN_KEY = "cut.from.main.key";
	public static final String CUT_FROM_MAIN_INFORMATION = "cut.from.main.information";
	public static final String PASTE_FROM_MAIN = "paste.from.main";
	public static final String PASTE_FROM_MAIN_KEY = "paste.from.main.key";
	public static final String PASTE_FROM_MAIN_INFORMATION = "paste.from.main.information";

	public static final String COPY_FROM_AUXILIAR = "copy.from.auxiliar";
	public static final String COPY_FROM_AUXILIAR_KEY = "copy.from.auxiliar.key";
	public static final String COPY_FROM_AUXILIAR_INFORMATION = "copy.from.auxiliar.information";
	public static final String CUT_FROM_AUXILIAR = "cut.from.auxiliar";
	public static final String CUT_FROM_AUXILIAR_KEY = "cut.from.auxiliar.key";
	public static final String CUT_FROM_AUXILIAR_INFORMATION = "cut.from.auxiliar.information";
	public static final String PASTE_FROM_AUXILIAR = "paste.from.auxiliar";
	public static final String PASTE_FROM_AUXILIAR_KEY = "paste.from.auxiliar.key";
	public static final String PASTE_FROM_AUXILIAR_INFORMATION = "paste.from.auxiliar.information";

	public static final String COPY_FROM_OUTPUT = "copy.from.output";
	public static final String COPY_FROM_OUTPUT_KEY = "copy.from.output.key";
	public static final String COPY_FROM_OUTPUT_INFORMATION = "copy.from.output.information";
	public static final String CUT_FROM_OUTPUT = "cut.from.output";
	public static final String CUT_FROM_OUTPUT_KEY = "cut.from.output.key";
	public static final String CUT_FROM_OUTPUT_INFORMATION = "cut.from.output.information";
	public static final String PASTE_FROM_OUTPUT = "paste.from.output";
	public static final String PASTE_FROM_OUTPUT_KEY = "paste.from.output.key";
	public static final String PASTE_FROM_OUTPUT_INFORMATION = "paste.from.output.information";

	public static final String UNDO_TOOLTIP = "undo.tooltip";
	public static final String UNDO_TOOLTIP_KEY = "undo.tooltip.key";
	public static final String REDO_TOOLTIP = "redo.tooltip";
	public static final String REDO_TOOLTIP_KEY = "redo.tooltip.key";
	public static final String UNDO_TOOLTIP_EXCEPTION = "undo.tooltip.exception";
	public static final String REDO_TOOLTIP_EXCEPTION = "redo.tooltip.exception";

	public static final String TASK_MANAGER_TITLE = "task.manager.title";

	public static final String TASK_MANAGER = "task.manager";
	public static final String TASK_MANAGER_KEY = "task.manager.key";
	public static final String TASK_MANAGER_INFORMATION = "task.manager.information";

	public static final String NETWORK_TITLE = "network.title";

	public static final String NETWORK_SERVER = "network.server";
	public static final String NETWORK_SERVER_KEY = "network.server.key";
	public static final String NETWORK_SERVER_INFORMATION = "network.server.information";

	public static final String TASK_MANAGER_COLUMN_FILTER_NUMBER = "task.manager.column.filter.number";
	public static final String TASK_MANAGER_COLUMN_FILTER_NAME = "task.manager.column.filter.name";
	public static final String TASK_MANAGER_COLUMN_FILTER_MESSAGE = "task.manager.column.filter.message";
	public static final String TASK_MANAGER_COLUMN_FILTER_DURATION = "task.manager.column.filter.duration";
	public static final String TASK_MANAGER_COLUMN_FILTER_STATUS = "task.manager.column.filter.status";
	public static final String TASK_MANAGER_COLUMN_FILTER_ACTION = "task.manager.column.filter.action";

	public static final String TASK_MANAGER_COLUMN_FILTER_STATUS_CREATED = "task.manager.column.filter.status.created";
	public static final String TASK_MANAGER_COLUMN_FILTER_STATUS_RUNNING = "task.manager.column.filter.status.running";
	public static final String TASK_MANAGER_COLUMN_FILTER_STATUS_REQUEST_CANCEL = "task.manager.column.filter.status.request.cancel";
	public static final String TASK_MANAGER_COLUMN_FILTER_STATUS_CANCELED = "task.manager.column.filter.status.canceled";
	public static final String TASK_MANAGER_COLUMN_FILTER_STATUS_COMPLETE = "task.manager.column.filter.status.complete";
	public static final String TASK_MANAGER_COLUMN_FILTER_STATUS_ERROR = "task.manager.column.filter.status.error";

	public static final String TASK_MANAGER_COLUMN_FILTER_ACTION_CANCEL = "task.manager.column.filter.action.cancel";
	public static final String TASK_MANAGER_COLUMN_FILTER_ACTION_WAIT = "task.manager.column.filter.action.wait";
	public static final String TASK_MANAGER_COLUMN_FILTER_ACTION_CLEAR = "task.manager.column.filter.action.clear";
	public static final String TASK_MANAGER_COLUMN_FILTER_ACTION_GET_RESULT = "task.manager.column.filter.action.get.result";

	public static final String TASK_STARTED_INFORMATION = "task.started.information";
	public static final String TASK_STARTED_INFORMATION_TITLE = "task.started.information.title";

	public static final String TASK_INFORMATION_SYMBOLS_TITLE = "task.information.symbols.title";
	public static final String TASK_INFORMATION_WINDOW_TITLE = "task.information.window.title";
	public static final String TASK_INFORMATION_GENERAL_INFORMATION = "task.information.general.information";
	public static final String TASK_INFORMATION_LENGTH = "task.information.length";
	public static final String TASK_INFORMATION_WORDS = "task.information.words";
	public static final String TASK_INFORMATION_TOTAL_LINES = "task.information.total.lines";
	public static final String TASK_INFORMATION_EMPTY_LINES = "task.information.empty.lines";
	public static final String TASK_INFORMATION_TABULATIONS = "task.information.tabulations";
	public static final String TASK_INFORMATION_SPACES = "task.information.spaces";
	public static final String TASK_INFORMATION_SPACE_SEQUENCES = "task.information.space.sequences";
	public static final String TASK_INFORMATION_LINE_TYPE = "task.information.line.type";
	public static final String TASK_INFORMATION_CHARACTERISC = "task.information.characterisc";
	public static final String TASK_INFORMATION_ENTIRE_TEXT = "task.information.entire.text";
	public static final String TASK_INFORMATION_SELECTED_TEXT = "task.information.selected.text";
	public static final String TASK_INFORMATION_PERIOD = "task.information.period";
	public static final String TASK_INFORMATION_COMMA = "task.information.comma";
	public static final String TASK_INFORMATION_COLON = "task.information.colon";
	public static final String TASK_INFORMATION_SEMICOLON = "task.information.semicolon";
	public static final String TASK_INFORMATION_EXCLAMATION_POINT = "task.information.exclamation.point";
	public static final String TASK_INFORMATION_QUESTION_MARK = "task.information.question.mark";
	public static final String TASK_INFORMATION_APOSTROPHE = "task.information.apostrophe";
	public static final String TASK_INFORMATION_SINGLE_QUOTATION = "task.information.single.quotation";
	public static final String TASK_INFORMATION_DOUBLE_QUOTATION = "task.information.double.quotation";
	public static final String TASK_INFORMATION_OPEN_BRACKETS = "task.information.open.brackets";
	public static final String TASK_INFORMATION_CLOSE_BRACKETS = "task.information.close.brackets";
	public static final String TASK_INFORMATION_OPEN_SQUARE_BRACKETS = "task.information.open.square.brackets";
	public static final String TASK_INFORMATION_CLOSE_SQUARE_BRACKETS = "task.information.close.square.brackets";
	public static final String TASK_INFORMATION_OPEN_CURLY_BRACE = "task.information.open.curly.brace";
	public static final String TASK_INFORMATION_CLOSE_CURLY_BRACE = "task.information.close.curly.brace";
	public static final String TASK_INFORMATION_OR = "task.information.or";
	public static final String TASK_INFORMATION_UNDERLINE = "task.information.underline";
	public static final String TASK_INFORMATION_AMPERSAND = "task.information.ampersand";
	public static final String TASK_INFORMATION_TILDE = "task.information.tilde";
	public static final String TASK_INFORMATION_AT = "task.information.at";
	public static final String TASK_INFORMATION_PLUS = "task.information.plus";
	public static final String TASK_INFORMATION_DASH = "task.information.dash";
	public static final String TASK_INFORMATION_EQUALS = "task.information.equals";
	public static final String TASK_INFORMATION_IS_LESS_THAN = "task.information.is.less.than";
	public static final String TASK_INFORMATION_IS_GREATER_THAN = "task.information.is.greater.than";
	public static final String TASK_INFORMATION_BACK_SLASH = "task.information.back.slash";
	public static final String TASK_INFORMATION_FORWARD_SLASH = "task.information.forward.slash";
	public static final String TASK_INFORMATION_HASH = "task.information.hash";
	public static final String TASK_INFORMATION_DOLLAR = "task.information.dollar";
	public static final String TASK_INFORMATION_PERCENT = "task.information.percent";
	public static final String TASK_INFORMATION_CARET = "task.information.caret";
	public static final String TASK_INFORMATION_ASTERISK = "task.information.asterisk";
	public static final String TASK_INFORMATION_SYMBOL = "task.information.symbol";
	public static final String TASK_INFORMATION_ENTIRE_TEXT_2 = "task.information.entire.text.2";
	public static final String TASK_INFORMATION_SELECTED_TEXT_2 = "task.information.selected.text.2";
	public static final String TASK_INFORMATION_POSITION = "task.information.position";
	public static final String TASK_INFORMATION_WORD = "task.information.word";
	public static final String TASK_INFORMATION_REPETITIONS = "task.information.repetitions";
	public static final String TASK_INFORMATION_REPEATED_WORDS = "task.information.repeated.words";
	public static final String TASK_INFORMATION_REPEATED_WORDS_WITH_MORE_THEN_CHARACTERS = "task.information.repeated.words.with.more.then.characters";

	public static final String TASK_INFORMATION_FILE_MODE_EXECUTION = "task.information.file.mode.execution";
	public static final String TASK_INFORMATION_FILE_MODE_EXECUTION_PROGRESS = "task.information.file.mode.execution.progress";

	public static final String NETWORK_IMAGE_EXCEPTION = "network.image.exception";
	public static final String NETWORK_SERVER_EXCEPTION = "network.server.exception";

	public static final String NETWORK_GENERATED_PAGE_TITLE = "network.generated.page.title";
	public static final String NETWORK_GENERATED_PAGE_HEADER = "network.generated.page.header";
	public static final String NETWORK_GENERATED_TABLE_CAPTION = "network.generated.table.caption";
	public static final String NETWORK_GENERATED_LINK_COLUMN = "network.generated.link.column";
	public static final String NETWORK_GENERATED_FILE_NAME_COLUMN = "network.generated.file.name.column";
	public static final String NETWORK_GENERATED_DESCRIPTION_COLUMN = "network.generated.description.column";
	public static final String NETWORK_GENERATED_SIZE_COLUMN = "network.generated.size.column";
	public static final String NETWORK_GENERATED_NUMBER_COLUMN = "network.generated.number.column";
	public static final String NETWORK_GENERATED_SIZE_INFORMATION = "network.generated.size.information";
	public static final String NETWORK_GENERATED_FILE_TITLE = "network.generated.file.title";
	public static final String NETWORK_GENERATED_OPEN_LINK_TITLE = "network.generated.open.link.title";
	public static final String NETWORK_GENERATED_FILTERANY_SITE_LINK = "network.generated.filterany.site.link";
	public static final String NETWORK_GENERATED_FILE_TABLE_CAPTION = "network.generated.file.table.caption";
	public static final String NETWORK_GENERATED_OPEN_FILE_LINK_TITLE = "network.generated.open.file.link.title";

	public static final String NETWORK_SERVER_GET_FROM_MAIN_INPUT = "network.server.get.from.main.input";
	public static final String NETWORK_SERVER_GET_FROM_MAIN_INPUT_KEY = "network.server.get.from.main.input.key";
	public static final String NETWORK_SERVER_GET_FROM_MAIN_INPUT_INFORMATION = "network.server.get.from.main.input.information";

	public static final String NETWORK_SERVER_GET_FROM_OUTPUT = "network.server.get.from.output";
	public static final String NETWORK_SERVER_GET_FROM_OUTPUT_KEY = "network.server.get.from.output.key";
	public static final String NETWORK_SERVER_GET_FROM_OUTPUT_INFORMATION = "network.server.get.from.output.information";

	public static final String NETWORK_SERVER_GET_FROM_AUXILIAR = "network.server.get.from.auxiliar";
	public static final String NETWORK_SERVER_GET_FROM_AUXILIAR_KEY = "network.server.get.from.auxiliar.key";
	public static final String NETWORK_SERVER_GET_FROM_AUXILIAR_INFORMATION = "network.server.get.from.auxiliar.information";

	public static final String NETWORK_TEXT_ENABLE_CHECKBOX = "network.text.enable.checkbox";
	public static final String NETWORK_TEXT_ENABLE_CHECKBOX_TOOLTIP = "network.text.enable.checkbox.tooltip";

	public static final String NETWORK_TEXT_HEADER_ENABLE_CHECKBOX = "network.text.header.enable.checkbox";
	public static final String NETWORK_TEXT_HEADER_ENABLE_CHECKBOX_TOOLTIP = "network.text.header.enable.checkbox.tooltip";

	public static final String NETWORK_IS_GOING_TO_BE_CLOSED_CHECKBOX = "network.is.going.to.be.closed.checkbox";
	public static final String NETWORK_IS_GOING_TO_BE_CLOSED_CHECKBOX_TOOLTIP = "network.is.going.to.be.closed.checkbox.tooltip";

	public static final String NETWORK_SHUTDOWN_MESSAGE = "network.shutdown.message";
	public static final String NETWORK_TEXT_INTERNAL_BORDER = "network.text.internal.border";

	public static final String NETWORK_SERVER_ADD_FILE_AUXILIAR = "network.server.add.file.auxiliar";
	public static final String NETWORK_SERVER_ADD_FILE_AUXILIAR_KEY = "network.server.add.file.auxiliar.key";
	public static final String NETWORK_SERVER_ADD_FILE_AUXILIAR_INFORMATION = "network.server.add.file.auxiliar.information";

	public static final String NETWORK_ITEMS_BORDER_TITLE = "network.items.border.title";
	public static final String NETWORK_CONTROLS_BORDER_TITLE = "network.controls.border.title";
	public static final String NETWORK_SERVER_BORDER_TITLE = "network.server.border.title";
	public static final String NETWORK_SERVER_ADDRESS_LABEL = "network.server.address.label";
	public static final String NETWORK_SERVER_START_STOP = "network.server.start.stop";
	public static final String NETWORK_SERVER_START_STOP_TOOLTIP = "network.server.start.stop.tooltip";
	public static final String NETWORK_SERVER_STATUS_BORDER_TITLE = "network.server.status.border.title";
	public static final String NETWORK_SERVER_STATUS_LABEL = "network.server.status.label";
	public static final String NETWORK_FILE_NAME_LABEL = "network.file.name.label";
	public static final String NETWORK_FILE_COMMENT_LABEL = "network.file.comment.label";
	public static final String NETWORK_FILE_COMMENT_LABEL_TOOLTIP = "network.file.comment.label.tooltip";
	public static final String NETWORK_FILE_NUMBER_BORDER_TITLE = "network.file.number.border.title";

	public static final String NETWORK_FILE_TARGET_BUTTON = "network.file.target.button";
	public static final String NETWORK_FILE_TARGET_BUTTON_KEY = "network.file.target.button.key";
	public static final String NETWORK_FILE_TARGET_BUTTON_TOOLTIP = "network.file.target.button.tooltip";

	public static final String NETWORK_TEXT_COMMENT_LABEL = "network.text.comment.label";
	public static final String NETWORK_TEXT_COMMENT_LABEL_TOOLTIP = "network.text.comment.label.tooltip";

	public static final String NETWORK_DELETE_FILE_TARGET = "network.delete.file.target";
	public static final String NETWORK_DELETE_FILE_TARGET_KEY = "network.delete.file.target.key";
	public static final String NETWORK_DELETE_FILE_TARGET_TOOLTIP = "network.delete.file.target.tooltip";

	public static final String NETWORK_DELETE_TEXT_TARGET = "network.delete.text.target";
	public static final String NETWORK_DELETE_TEXT_TARGET_KEY = "network.delete.text.target.key";
	public static final String NETWORK_DELETE_TEXT_TARGET_TOOLTIP = "network.delete.text.target.tooltip";

	public static final String NETWORK_PORT_LABEL = "network.port.label";
	public static final String NETWORK_PORT_LABEL_TOOLTIP = "network.port.label.tooltip";

	public static final String NETWORK_REQUEST_TABLE_TITLE_URI = "network.request.table.title.uri";
	public static final String NETWORK_REQUEST_TABLE_TITLE_REQUEST = "network.request.table.title.request";
	public static final String NETWORK_REQUEST_AGENT_NAME = "network.request.agent.name";

	public static final String NETWORK_PROBLEM_WITH_PORT_INFORMATION = "network.problem.with.port.information";
	public static final String NETWORK_PROBLEM_WITH_PORT_TITLE = "network.problem.with.port.title";

	public static final String NETWORK_RESET_COUNTER = "network.reset.counter";
	public static final String NETWORK_RESET_COUNTER_TOOLTIP = "network.reset.counter.tooltip";

	public static final String REGISTER_PROBLEM_READING_REGISTRATION_FILE = "register.problem.reading.registration.file";
	public static final String REGISTER_PROBLEM_READING_REGISTRATION_FILE_TITLE = "register.problem.reading.registration.file.title";

	public static final String SEARCH_FILTER = "search.filter";
	public static final String SEARCH_FILTER_KEY = "search.filter.key";
	public static final String SEARCH_FILTER_INFORMATION = "search.filter.information";

	public static final String SEARCH_PARAMETER_BORDER = "search.parameter.border";
	public static final String SEARCH_PARAMETER_WINDOW_TITLE = "search.parameter.window.title";

	public static final String MEMORY_TOOLTIP = "memory.tooltip";

	public static final String GARBAGE_CAN = "garbage.can";
	public static final String GARBAGE_CAN_KEY = "garbage.can.key";
	public static final String GARBAGE_CAN_TOOLTIP = "garbage.can.tooltip";

	public static final String COMMAND_LINE_GENERATOR_TITLE = "command.line.generator.title";

	public static final String COMMAND_LINE_INPUT_FILE = "command.line.input.file";
	public static final String COMMAND_LINE_OUTPUT_FILE = "command.line.output.file";
	public static final String COMMAND_LINE_OPTIONAL_PARAMETERS = "command.line.optional.parameters";
	public static final String COMMAND_LINE_AUXILIAR_FILE = "command.line.auxiliar.file";

	public static final String COMMAND_LINE_GENERATOR = "command.line.generator";
	public static final String COMMAND_LINE_GENERATOR_KEY = "command.line.generator.key";
	public static final String COMMAND_LINE_GENERATOR_INFORMATION = "command.line.generator.information";
	public static final String COMMAND_LINE_GENERATOR_INTERNAL_INSTRUCTION = "command.line.generator.internal.instruction";

	public static final String TEXT_MODE_ACTIVATED = "text.mode.activated";
	public static final String FILE_MODE_ACTIVATED = "file.mode.activated";

	public static final String TRAY_MESSAGE = "tray.message";
	public static final String TRAY_MESSAGE_TITLE = "tray.message.title";
	public static final String EXCEPTION_WHILE_ADDING_AN_ICON_TO_TRAY = "exception.while.adding.an.icon.to.tray";
	public static final String STILL_RUNNING_CAPTION = "still.running.caption";
	public static final String STILL_RUNNING_TEXT = "still.running.text";
	public static final String TRAY_TOOLTIP = "tray.tooltip";
	public static final String TRAY_MENY_ITEM_SHOW = "tray.meny.item.show";
	public static final String TRAY_MENY_ITEM_EXIT = "tray.meny.item.exit";
	public static final String TRAY_MENY_ITEM_SHOW_KEY = "tray.meny.item.show.key";
	public static final String TRAY_MENY_ITEM_EXIT_KEY = "tray.meny.item.exit.key";

	public static final String FILTER_CONFIGURATION_SAVE_FORMAT_TITLE = "filter.configuration.save.format.title";
	public static final String FILTER_CONFIGURATION_SAVE_FORMAT_DOS = "filter.configuration.save.format.dos";
	public static final String FILTER_CONFIGURATION_SAVE_FORMAT_UNIX = "filter.configuration.save.format.unix";
	public static final String FILTER_CONFIGURATION_SAVE_FORMAT_DOS_TOOLTIP = "filter.configuration.save.format.dos.tooltip";
	public static final String FILTER_CONFIGURATION_SAVE_FORMAT_UNIX_TOOLTIP = "filter.configuration.save.format.unix.tooltip";

	public static final String THROWABLE_EXCEPTION_MESSAGE = "throwable.exception.message";
	public static final String OUT_OF_MEMORY_EXCEPTION_MESSAGE = "out.of.memory.exception.message";

	public static final String THROWABLE_EXCEPTION_COMPRESS_MESSAGE = "throwable.exception.compress.message";
	public static final String THROWABLE_EXCEPTION_COMPRESS_MESSAGE_COMPLEMENT = "throwable.exception.compress.message.complement";

	public static final String GO_TO_LINE_RED = "go.to.line.red";
	public static final String COMMAND_GO_TO_LINE_KEY_RED = "command.go.to.line.key.red";
	public static final String GO_TO_LINE_INFORMATION_RED = "go.to.line.information.red";

	public static final String GO_TO_LINE_BLUE = "go.to.line.blue";
	public static final String COMMAND_GO_TO_LINE_KEY_BLUE = "command.go.to.line.key.blue";
	public static final String GO_TO_LINE_INFORMATION_BLUE = "go.to.line.information.blue";

	public static final String GO_TO_LINE_GREEN = "go.to.line.green";
	public static final String COMMAND_GO_TO_LINE_KEY_GREEN = "command.go.to.line.key.green";
	public static final String GO_TO_LINE_INFORMATION_GREEN = "go.to.line.information.green";

	public static final String GO_TO_LINE_BORDER_TITLE = "go.to.line.border.title";
	public static final String GO_TO_LINE_GO_BUTTON = "go.to.line.go.button";
	public static final String GO_TO_LINE_GO_BUTTON_TOOLTIP = "go.to.line.go.button.tooltip";
	public static final String GO_TO_LINE_TITLE = "go.to.line.title";

	public static final String BINARY_RECEIVE_FULL_FILE_LIST_EXECUTION_MESSAGE = "binary.receive.full.file.list.execution.message";
	public static final String BINARY_EXECUTION_MESSAGE = "binary.execution.message";

	public static final String MINING_BUTTON_INSTRUCTIONS = "mining.button.instructions";
	public static final String MINING_TOOLTIP = "mining.tooltip";
	public static final String MINING_TOOLTIP_KEY = "mining.tooltip.key";
	public static final String MINING_WINDOW_TITLE = "mining.window.title";
	public static final String MINING_FIRST_FIELD = "mining.first.field";
	public static final String MINING_SECOND_FIELD = "mining.second.field";
	public static final String MINING_THIRD_FIELD = "mining.third.field";
	public static final String MINING_FORTH_FIELD = "mining.forth.field";
	public static final String MINING_FIFTH_FIELD = "mining.fifth.field";
	public static final String MINING_CASE_SENSITIVE = "mining.case.sensitive";
	public static final String MINING_BORDER_TITLE = "mining.border.title";

	public static final String FORMATTING_EDITOR_BUTTON_INSTRUCTIONS = "formatting.editor.button.instructions";
	public static final String FORMATTING_EDITOR_WINDOW_TITLE = "formatting.editor.window.title";
	public static final String FORMAT_BORDER_TITLE = "format.border.title";
	public static final String FORMAT_BORDER_TITLE_LABEL = "format.border.title.label";
	public static final String PRINT_TEXT_AREA_EXCEPTION = "print.text.area.exception";

	public static final String PRINT_DOCUMENT = "print.document";
	public static final String PRINT_DOCUMENT_KEY = "print.document.key";
	public static final String PRINT_DOCUMENT_INFORMATION = "print.document.information";

	public static final String COPY_WITH_FORMAT = "copy.with.format";
	public static final String COPY_WITH_FORMAT_KEY = "copy.with.format.key";
	public static final String COPY_WITH_FORMAT_INFORMATION = "copy.with.format.information";

	public static final String TOGGLE_COLORS = "toggle.colors";
	public static final String TOGGLE_COLORS_KEY = "toggle.colors.key";
	public static final String TOGGLE_COLORS_INFORMATION = "toggle.colors.information";

	public static final String SHOW_TABULATION = "show.tabulation";
	public static final String SHOW_TABULATION_KEY = "show.tabulation.key";
	public static final String SHOW_TABULATION_INFORMATION = "show.tabulation.information";

	public static final String SYNTAX_STYLE_NONE_TEXT = "syntax.style.none.text";
	public static final String SYNTAX_STYLE_ACTIONSCRIPT_TEXT = "syntax.style.actionscript.text";
	public static final String SYNTAX_STYLE_ASSEMBLER_X86_TEXT = "syntax.style.assembler.x86.text";
	public static final String SYNTAX_STYLE_BBCODE_TEXT = "syntax.style.bbcode.text";
	public static final String SYNTAX_STYLE_C_TEXT = "syntax.style.c.text";
	public static final String SYNTAX_STYLE_CLOJURE_TEXT = "syntax.style.clojure.text";
	public static final String SYNTAX_STYLE_CPLUSPLUS_TEXT = "syntax.style.cplusplus.text";
	public static final String SYNTAX_STYLE_CSHARP_TEXT = "syntax.style.csharp.text";
	public static final String SYNTAX_STYLE_CSS_TEXT = "syntax.style.css.text";
	public static final String SYNTAX_STYLE_DELPHI_TEXT = "syntax.style.delphi.text";
	public static final String SYNTAX_STYLE_FORTRAN_TEXT = "syntax.style.fortran.text";
	public static final String SYNTAX_STYLE_GROOVY_TEXT = "syntax.style.groovy.text";
	public static final String SYNTAX_STYLE_HTML_TEXT = "syntax.style.html.text";
	public static final String SYNTAX_STYLE_JAVA_TEXT = "syntax.style.java.text";
	public static final String SYNTAX_STYLE_JAVASCRIPT_TEXT = "syntax.style.javascript.text";
	public static final String SYNTAX_STYLE_JSP_TEXT = "syntax.style.jsp.text";
	public static final String SYNTAX_STYLE_LISP_TEXT = "syntax.style.lisp.text";
	public static final String SYNTAX_STYLE_LUA_TEXT = "syntax.style.lua.text";
	public static final String SYNTAX_STYLE_MAKEFILE_TEXT = "syntax.style.makefile.text";
	public static final String SYNTAX_STYLE_MXML_TEXT = "syntax.style.mxml.text";
	public static final String SYNTAX_STYLE_PERL_TEXT = "syntax.style.perl.text";
	public static final String SYNTAX_STYLE_PHP_TEXT = "syntax.style.php.text";
	public static final String SYNTAX_STYLE_PROPERTIES_FILE_TEXT = "syntax.style.properties.file.text";
	public static final String SYNTAX_STYLE_PYTHON_TEXT = "syntax.style.python.text";
	public static final String SYNTAX_STYLE_RUBY_TEXT = "syntax.style.ruby.text";
	public static final String SYNTAX_STYLE_SAS_TEXT = "syntax.style.sas.text";
	public static final String SYNTAX_STYLE_SCALA_TEXT = "syntax.style.scala.text";
	public static final String SYNTAX_STYLE_SQL_TEXT = "syntax.style.sql.text";
	public static final String SYNTAX_STYLE_TCL_TEXT = "syntax.style.tcl.text";
	public static final String SYNTAX_STYLE_UNIX_SHELL_TEXT = "syntax.style.unix.shell.text";
	public static final String SYNTAX_STYLE_WINDOWS_BATCH_TEXT = "syntax.style.windows.batch.text";
	public static final String SYNTAX_STYLE_XML_TEXT = "syntax.style.xml.text";

	public static final String FILTERANY_SHORTCUT_WINDOW_TITLE = "filterany.shortcut.window.title";

	public static final String SHORTCUT = "shortcut";
	public static final String SHORTCUT_KEY = "shortcut.key";
	public static final String SHORTCUT_TOOLTIP = "shortcut.tooltip";

	public static final String COMMAND_LINE_EXECUTING_COMMAND = "command.line.executing.command";

	public static final String CONFIGURATION_ENCODING_TITLE = "configuration.encoding.title";
	public static final String CONFIGURATION_INPUT_ENCODING_PARAMETER = "configuration.input.encoding.parameter";
	public static final String CONFIGURATION_INPUT_ENCODING_DO_NOT_SET = "configuration.input.encoding.do.not.set";
	public static final String CONFIGURATION_INPUT_ENCODING_DETECT = "configuration.input.encoding.detect";
	public static final String CONFIGURATION_INPUT_ENCODING_TOOLTIP = "configuration.input.encoding.tooltip";
	public static final String CONFIGURATION_INPUT_ENCODING_INTERNAL_PROBLEM = "configuration.input.encoding.internal.problem";
	public static final String CONFIGURATION_OUTPUT_ENCODING_PARAMETER = "configuration.output.encoding.parameter";
	public static final String CONFIGURATION_OUTPUT_ENCODING_TOOLTIP = "configuration.output.encoding.tooltip";

	public static final String CLICK_THE_TRASH_TO_CLEAR_MEMORY = "click.the.trash.to.clear.memory";

	public static final String FILTER_APPLIED_TO_SELECTION_ONLY_DIALOG_TITLE = "filter.applied.to.selection.only.dialog.title";
	public static final String FILTER_APPLIED_TO_SELECTION_ONLY_DIALOG_MESSAGE = "filter.applied.to.selection.only.dialog.message";

	public static final String INTERNAL_ERROR_1 = "internal.error.1";
	public static final String INTERNAL_ERROR_2 = "internal.error.2";
	public static final String INTERNAL_ERROR_3 = "internal.error.3";
	public static final String INTERNAL_ERROR_4 = "internal.error.4";
	public static final String INTERNAL_ERROR_5 = "internal.error.5";
	public static final String INTERNAL_ERROR_7 = "internal.error.7";
	public static final String INTERNAL_ERROR_8 = "internal.error.8";
	public static final String INTERNAL_ERROR_9 = "internal.error.9";
	public static final String INTERNAL_ERROR_10 = "internal.error.10";
	public static final String INTERNAL_ERROR_11 = "internal.error.11";
	public static final String INTERNAL_ERROR_12 = "internal.error.12";
	public static final String INTERNAL_ERROR_13 = "internal.error.13";
	public static final String INTERNAL_ERROR_14 = "internal.error.14";
	public static final String INTERNAL_ERROR_15 = "internal.error.15";
	public static final String INTERNAL_ERROR_16 = "internal.error.16";
	public static final String INTERNAL_ERROR_17 = "internal.error.17 ";

}
