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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.carlosbcruz.filterany.FileOrURLBean.FileOrURLBeanType;
import com.carlosbcruz.filterany.SpecialBehavior.Behavior;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.filterany.filters.AWK1Filter;
import com.carlosbcruz.filterany.filters.AWK2Filter;
import com.carlosbcruz.filterany.filters.AWK3Filter;
import com.carlosbcruz.filterany.filters.AddAnchorOnAPositionFilter;
import com.carlosbcruz.filterany.filters.AddAnchorOnBeginOfLineFilter;
import com.carlosbcruz.filterany.filters.AddAnchorOnEndOfLineFilter;
import com.carlosbcruz.filterany.filters.AddParameterAfterfParameterFilter;
import com.carlosbcruz.filterany.filters.AddParameterBeforeParameterFilter;
import com.carlosbcruz.filterany.filters.AppendFilesFilter;
import com.carlosbcruz.filterany.filters.BreakLinesOnSpecifiedLengthFilter;
import com.carlosbcruz.filterany.filters.BreakStringIntoFieldsFilter;
import com.carlosbcruz.filterany.filters.CalculateMD5Filter;
import com.carlosbcruz.filterany.filters.CalculateTotalTimesheetFilter;
import com.carlosbcruz.filterany.filters.CalculatorFilter;
import com.carlosbcruz.filterany.filters.CharacterToUpperOrLowerCaseFilter;
import com.carlosbcruz.filterany.filters.CollapseSpacesFilter;
import com.carlosbcruz.filterany.filters.CombineAllLinesFilter;
import com.carlosbcruz.filterany.filters.CombineFieldsIntoStringFilter;
import com.carlosbcruz.filterany.filters.CombineLinesAtARegularStepFilter;
import com.carlosbcruz.filterany.filters.CombineMainWithAuxiliar;
import com.carlosbcruz.filterany.filters.CopyFilter;
import com.carlosbcruz.filterany.filters.DeleteDirectoryFilter;
import com.carlosbcruz.filterany.filters.DeleteFilesFilter;
import com.carlosbcruz.filterany.filters.DownloadInternetBlockerFilter;
import com.carlosbcruz.filterany.filters.DuplicatedFilesFilter;
import com.carlosbcruz.filterany.filters.EliminateAllDuplicatedLinesFilter;
import com.carlosbcruz.filterany.filters.EnumerateLinesFilter;
import com.carlosbcruz.filterany.filters.FileListFilter;
import com.carlosbcruz.filterany.filters.FirstCharacterToUpperCaseFilter;
import com.carlosbcruz.filterany.filters.From24hToAMPMFilter;
import com.carlosbcruz.filterany.filters.FromAMPMto24hFilter;
import com.carlosbcruz.filterany.filters.FromSpacesToTabFilter;
import com.carlosbcruz.filterany.filters.FromTabToSpacesFilter;
import com.carlosbcruz.filterany.filters.InsertAuxiliarBetweenParameters;
import com.carlosbcruz.filterany.filters.InsertAuxiliarIntoMain;
import com.carlosbcruz.filterany.filters.IntercalateMainWithAuxiliar;
import com.carlosbcruz.filterany.filters.InvertOrderFilter;
import com.carlosbcruz.filterany.filters.JSONFormatterFilter;
import com.carlosbcruz.filterany.filters.JavaBeanFilter;
import com.carlosbcruz.filterany.filters.JspFormatterFilter;
import com.carlosbcruz.filterany.filters.KeepOnlyValidCharactersFilter;
import com.carlosbcruz.filterany.filters.KnapSackFileListerFilter;
import com.carlosbcruz.filterany.filters.KnapSackFilter;
import com.carlosbcruz.filterany.filters.LeaveOnlyNumbersFilter;
import com.carlosbcruz.filterany.filters.MaintainLinesAtRegularStepFilter;
import com.carlosbcruz.filterany.filters.MorseFilter;
import com.carlosbcruz.filterany.filters.MultipleInsertAuxiliarIntoMainFilter;
import com.carlosbcruz.filterany.filters.RandomDataGeneratorFilter;
import com.carlosbcruz.filterany.filters.RegularExpressionReplacementFilter;
import com.carlosbcruz.filterany.filters.RemoveCharacterAfterParameterFilter;
import com.carlosbcruz.filterany.filters.RemoveCharacterAfterPositionFilter;
import com.carlosbcruz.filterany.filters.RemoveCharacterBeforeParameterFilter;
import com.carlosbcruz.filterany.filters.RemoveCharacterBeforePositionFilter;
import com.carlosbcruz.filterany.filters.RemoveCharacterBetweenPositionsFilter;
import com.carlosbcruz.filterany.filters.RemoveCharacterOnBeginningFilter;
import com.carlosbcruz.filterany.filters.RemoveCharacterOnTheEndFilter;
import com.carlosbcruz.filterany.filters.RemoveCharactersBetweenParametersFilter;
import com.carlosbcruz.filterany.filters.RemoveCharactersOutsideParametersFilter;
import com.carlosbcruz.filterany.filters.RemoveDuplicatedLinesFilter;
import com.carlosbcruz.filterany.filters.RemoveEmptyLinesFilter;
import com.carlosbcruz.filterany.filters.RemoveLinesThatDoNotExistOnMainAndAuxiliar;
import com.carlosbcruz.filterany.filters.RemoveLinesThatExistOnMainAndAuxiliar;
import com.carlosbcruz.filterany.filters.RemoveLinesWithContentFilter;
import com.carlosbcruz.filterany.filters.RemoveLinesWithLengthFilter;
import com.carlosbcruz.filterany.filters.RemoveLinesWithMultipleParameters;
import com.carlosbcruz.filterany.filters.RemoveLinesWithoutContentFilter;
import com.carlosbcruz.filterany.filters.RemoveLinesWithoutMultipleParameters;
import com.carlosbcruz.filterany.filters.RemoveNonDuplicatedLinesFilter;
import com.carlosbcruz.filterany.filters.RemoveSpacesFromExtremitiesFilter;
import com.carlosbcruz.filterany.filters.RemoveSpacesOnBeginningFilter;
import com.carlosbcruz.filterany.filters.RemoveSpacesOnTheEndFilter;
import com.carlosbcruz.filterany.filters.ReplaceAndRenameFilter;
import com.carlosbcruz.filterany.filters.ReplaceMultipleTexts;
import com.carlosbcruz.filterany.filters.ReplicateLineContentFilter;
import com.carlosbcruz.filterany.filters.ScrambleLinesFilter;
import com.carlosbcruz.filterany.filters.SearchFileFilter;
import com.carlosbcruz.filterany.filters.ShowDuplicatedConsecutiveWordsFilter;
import com.carlosbcruz.filterany.filters.SimpleAlertFilter;
import com.carlosbcruz.filterany.filters.SimpleContentReplacementFilter;
import com.carlosbcruz.filterany.filters.SortLinesAscendentBasedOnPositionFilter;
import com.carlosbcruz.filterany.filters.SortLinesAscendentByLengthFilter;
import com.carlosbcruz.filterany.filters.SortLinesAscendentFilter;
import com.carlosbcruz.filterany.filters.SortLinesDescendentBasedOnPositionFilter;
import com.carlosbcruz.filterany.filters.SortLinesDescendentFilter;
import com.carlosbcruz.filterany.filters.StartTaskTimesheetFilter;
import com.carlosbcruz.filterany.filters.StopTaskTimesheetFilter;
import com.carlosbcruz.filterany.filters.SwapContentBasedOnParameterFilter;
import com.carlosbcruz.filterany.filters.TextWithErrorsFilter;
import com.carlosbcruz.filterany.filters.TextWithNumbersFilter;
import com.carlosbcruz.filterany.filters.ToDOSFilter;
import com.carlosbcruz.filterany.filters.ToLowerCaseFilter;
import com.carlosbcruz.filterany.filters.ToUNIXFilter;
import com.carlosbcruz.filterany.filters.ToUpperCaseFilter;
import com.carlosbcruz.filterany.filters.TreeDirectoryFilter;
import com.carlosbcruz.filterany.filters.XMLFormatterFilter;
import com.carlosbcruz.util.ClipboardUtil;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.FileSupport;
import com.carlosbcruz.util.StringSupport;

/**
 * Controls the interaction with the command line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CommandLineController {

	private static int MAX_BUFFER = 10000;

	// A predefined empty variable.
	private static final String EMPTY_VARIABLE = "%empty"; //$NON-NLS-1$

	// A predefined variable to access the clipboard
	private static final String CLIPBOARD_VARIABLE = "%clipboard"; //$NON-NLS-1$

	private static ArrayList<CommandLine> commands = getCommandLines();

	// Indicate if output files will be overwritten or not.
	private static boolean overwriteFiles = false;

	// Indicate if the output should be appended to a file if the file exists.
	private static boolean appendFile = false;

	// Context to store contents.
	private static Hashtable<String, StringBuffer> context = new Hashtable<>();

	// Definitions.
	private static Hashtable<String, String> definitions = new Hashtable<>();

	static {
		context.put(EMPTY_VARIABLE, new StringBuffer());
	}

	/**
	 * Default constructor.
	 */
	protected CommandLineController() {
		super();
	}

	/**
	 * Analyze a line and execute a command.
	 * 
	 * @param line
	 *            A command line.
	 * @return True if the command executed successfully and false otherwise.
	 */
	private static boolean treatCommandLine(String line) {

		// Ignore empty lines.
		if (line == null || "".equals(line.trim())) { //$NON-NLS-1$
			return false;
		}

		String elements[] = StringSupport.breakIntoCommandLineElements(line);

		// Ignore empty lines.
		if (elements.length == 0) {
			return false;
		}

		String commandRequested = elements[0].toUpperCase();

		// Provide a help.
		if ("QUIT".equals(commandRequested) || "EXIT".equals(commandRequested)) { //$NON-NLS-1$ //$NON-NLS-2$

			System.out.println(Text.get(Text.COMMAND_LINE_LEAVE));
			System.exit(0);
		}

		// Provide a help.
		if ("HELP".equals(commandRequested)) { //$NON-NLS-1$

			// No command so print main help.
			if (elements.length == 1) {

				System.out.println(Text.get(Text.COMMAND_LINE_MAIN_HELP_1));
				System.out.println();

				if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
					System.out.println(Text
							.get(Text.DEMONSTRATION_VERSION_TEXT));
				}
				
				System.out.println();
				System.out.println(Text.get(Text.COMMAND_LINE_MAIN_HELP_2));
				return true;
			}

			// Search for the command to print help.
			for (CommandLine command : commands) {

				if (command.getCommandName().toUpperCase()
						.equals(elements[1].toUpperCase())) {
					System.out.println(command.getHelpDescription());
					return true;
				}
			}

			System.out.println(Text.get(Text.COMMAND_NOT_FOUND, elements[1]));
			return false;
		}

		// Provide the command list.
		if ("LIST".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_INTERNAL_COMMANDS));
			// Search for the command to print help.
			for (CommandLine command : commands) {

				System.out.println("   " //$NON-NLS-1$
						+ StringSupport.adjustSizeLeft(
								command.getCommandName(), 6) + " - " //$NON-NLS-1$
						+ command.getSmallDescription());
			}

			System.out.println();
			return true;
		}

		// Request to overwrite output files.
		if ("OVERWRITE".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_OVERWRITE_ON));
			overwriteFiles = true;
			return true;
		}

		// Request to overwrite output files.
		if ("DONOTOVERWRITE".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_OVERWRITE_OFF));
			overwriteFiles = true;
			return true;
		}

		// Indicate if the output should be appended to a file if the file
		// exists.
		if ("APPEND".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_APPEND_ON));
			appendFile = true;
			return true;
		}

		// Indicate if the output should not be appended to a file if the file
		// exists.
		if ("DONOTAPPEND".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_APPEND_OFF));
			appendFile = false;
			return true;
		}

		// Activate the file mode.
		if ("FILEMODE".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_FILEMODE_ON));
			FilterAnyConfiguration.setTextMode(false);
			return true;
		}

		// Deactivate the file mode.
		if ("TEXTMODE".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_TEXTMODE_ON));
			FilterAnyConfiguration.setTextMode(true);
			return true;
		}

		// Activate DOS type new line.
		if ("DOS".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_DOS_MODE_ON));
			FilterAnyConfiguration.setGenerateTextInDOSFormat(true);
			return true;
		}

		// Activate DOS type new line.
		if ("UNIX".equals(commandRequested)) { //$NON-NLS-1$

			System.out.println(Text.get(Text.COMMAND_LINE_UNIX_MODE_ON));
			FilterAnyConfiguration.setGenerateTextInDOSFormat(false);
			return true;
		}

		// List the available encodings.
		if ("ENCODINGS".equals(commandRequested)) { //$NON-NLS-1$

			String encodings[] = FilterAnyEncoding.getEncodings();

			for (String encode : encodings) {

				System.out.println(encode);
			}
			return true;
		}

		// Set the detect encoding mode.
		if ("DETECT".equals(commandRequested)) { //$NON-NLS-1$

			FilterAnyEncoding.getInstance().setDetectInput(true);

			System.out.println(Text
					.get(Text.COMMAND_LINE_INPUT_ENCODING_ON_DETECT_MODE));
			return true;
		}

		// Specify the input encoding.
		if ("INPUTENCODING".equals(commandRequested)) { //$NON-NLS-1$

			if (elements.length != 2) {
				System.out.println(Text
						.get(Text.COMMAND_LINE_INCORRECT_NUMBER_OF_PARAMETERS));
				return false;
			}

			String encoding = elements[1];

			boolean found = false;
			String encodingFound = null;
			Map<String, Charset> map = Charset.availableCharsets();
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {

				// Get charset name
				String charsetName = it.next();

				Charset charset = Charset.forName(charsetName);

				if (encoding.toUpperCase().equals(charset.name().toUpperCase())) {
					encodingFound = charset.name();
					found = true;
					break;
				}
			}

			if (!found) {
				System.out.println(Text
						.get(Text.COMMAND_LINE_ENCODING_NOT_FOUND));
				return false;
			}

			FilterAnyEncoding.getInstance().setInputFileEncoding(encodingFound);

			System.out.println(Text.get(Text.COMMAND_LINE_EXECUTED,
					commandRequested));
			return true;
		}

		// Specify the output encoding.
		if ("OUTPUTENCODING".equals(commandRequested)) { //$NON-NLS-1$

			if (elements.length != 2) {
				System.out.println(Text
						.get(Text.COMMAND_LINE_INCORRECT_NUMBER_OF_PARAMETERS));
				return false;
			}

			String encoding = elements[1];

			boolean found = false;
			String encodingFound = null;
			Map<String, Charset> map = Charset.availableCharsets();
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {

				// Get charset name
				String charsetName = it.next();

				Charset charset = Charset.forName(charsetName);

				if (encoding.toUpperCase().equals(charset.name().toUpperCase())) {
					encodingFound = charset.name();
					found = true;
					break;
				}
			}

			if (!found) {
				System.out.println(Text
						.get(Text.COMMAND_LINE_ENCODING_NOT_FOUND));
				return false;
			}

			FilterAnyEncoding.getInstance()
					.setOutputFileEncoding(encodingFound);

			System.out.println(Text.get(Text.COMMAND_LINE_EXECUTED,
					commandRequested));
			return true;
		}

		// Execute a system command.
		if ("SYS".equals(commandRequested)) { //$NON-NLS-1$

			if (elements.length < 2) {
				System.out.println(Text
						.get(Text.COMMAND_LINE_MISSING_SECOND_PARAMETER));
				return false;
			}

			String systemCommands[] = StringSupport
					.arrayAtPosition(elements, 1);

			try {
				Runtime.getRuntime().exec(systemCommands);
			} catch (IOException exeption) {
				System.out.println(Text.get(Text.COMMAND_LINE_ENDED_WITH_ERROR,
						exeption.getMessage()));
				return false;
			}
			System.out.println(Text.get(Text.COMMAND_LINE_EXECUTED,
					commandRequested));
			return true;
		}

		// Set a definition.
		if ("SET".equals(commandRequested)) { //$NON-NLS-1$

			// The set command requires 3 parameters.
			if (elements.length != 3) {
				System.out.println(Text.get(Text.COMMAND_LINE_SET_FORMAT_HELP));
				return true;
			}

			String definition = elements[1];
			String content = elements[2];

			String previousContent = definitions.get(definition);

			if (previousContent != null) {
				System.out.println(Text.get(Text.DEFINITION_ALREADY_SET,
						definition, previousContent));
				return true;
			}

			definitions.put(definition, content);

			System.out.println(Text.get(Text.COMMAND_LINE_DEFINITION_SET,
					definition, content));
			return false;
		}

		// If it is a comment.
		if (!"".equals(commandRequested)) { //$NON-NLS-1$
			if (commandRequested.charAt(0) == '#') {

				return true;
			}
		}

		searchAndExecuteCommand(commandRequested, elements);

		return true;
	}

	/**
	 * Clear all the fields of a specific command.
	 * 
	 * @param command
	 *            The command.
	 */
	private static void clearCommand(CommandLine command) {

		FilterAdapter adapter = (FilterAdapter) command;

		adapter.setCheckBox1(true);
		adapter.setCheckBox2(true);
		adapter.setDirectory1(new String());
		adapter.setField1(new String());
		adapter.setField2(new String());
		adapter.setField3(new String());
		adapter.setFile1(new String());
		adapter.setTargetFileOrDirectory1(new String());
	}

	/**
	 * Search and execute a specific command.
	 * 
	 * @param commandRequested
	 *            The command requested.
	 * @param elements
	 *            The parameters.
	 */
	private static void searchAndExecuteCommand(String commandRequested,
			String[] elements) {

		// Search for the command to be executed.
		for (CommandLine command : commands) {

			// If found the command than execute it.
			if (command.getCommandName().toUpperCase()
					.equals(commandRequested.toUpperCase())) {

				clearCommand(command);

				executeCommand(command, elements);

				return;
			}
		}

		System.out.println(Text.get(Text.COMMAND_LINE_INVALID_COMMAND,
				commandRequested));
		return;
	}

	/**
	 * Execute a specific command.
	 * 
	 * @param command
	 *            The command.
	 * @param elements
	 *            The comand parameters.
	 */
	private static void executeCommand(CommandLine command, String[] elements) {

		// Execute a binary filter.
		if (command instanceof BinaryFilesFilter) {

			executeBinaryCommand(command, elements);
			return;
		}

		boolean filterWithDualPaneBehavior = false;

		// If command works on dual pane than there are two inputs
		// instead of one.
		if (command instanceof SpecialBehavior
				&& FilterAnyLogic.hasSpecialBehavior((SpecialBehavior) command,
						SpecialBehavior.Behavior.WORK_ON_DUAL_PANE)) {
			filterWithDualPaneBehavior = true;
		}

		int requiredParameters = filterWithDualPaneBehavior ? 4 : 3;

		// If the there are not input and output or input, alternative
		// and output.
		if (elements.length < requiredParameters) {

			System.out.println(Text.get(Text.COMMAND_LINE_INCORRECT_USE,
					command.getCommandName()));
			System.out.println(command.getHelpDescription());
			return;
		}

		StringBuffer inputContent = getInputContent(elements[1]);
		// The input content is invalid.
		if (inputContent == null) {
			return;
		}

		StringBuffer auxiliarContent = null;
		if (filterWithDualPaneBehavior) {
			auxiliarContent = getAuxiliarContent(elements[2]);

			// The auxiliar content is invalid.
			if (auxiliarContent == null) {
				return;
			}
		}

		String output = getOutputTarget(elements[filterWithDualPaneBehavior ? 3
				: 2]);

		// If the output is invalid.
		if (output == null) {
			return;
		}

		CommandLineParameters commandLineParameters = analyzeAdditionalParameters(
				command, StringSupport.arrayAtPosition(elements,
						filterWithDualPaneBehavior ? 4 : 3));
		// If the parameters are incorrect.
		if (commandLineParameters == null) {
			return;
		}

		CommandLineOptionalParameters optonalParameters = validateOptionalParameters(
				commandLineParameters, inputContent);
		if (optonalParameters.isParametersWithErrors()) {
			return;
		}

		FilterAdapter filter = treatRequiredParameters(command,
				commandLineParameters);
		// If the filter is not set then an error happened.
		if (filter == null) {
			return;
		}

		// See if the filter works only on text.
		boolean filterWorkOnlyOnText = false;
		if (filter instanceof SpecialBehavior) {
			filterWorkOnlyOnText = FilterAnyLogic.hasSpecialBehavior(
					(SpecialBehavior) filter,
					SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT);
		}

		StringBuffer outputContent = new StringBuffer();
		// If in file mode than execute only once.
		if (FilterAnyConfiguration.isTextMode() || filterWorkOnlyOnText) {

			try {
				outputContent = filter.filter(inputContent, auxiliarContent,
						optonalParameters.getCharacerStart(),
						optonalParameters.getCharacerEnd());

				if (filter.isIssueAnAlert()) {
					filter.setIssueAnAlert(false);
					FilterAnyAlert.alertUser(null);
				}
			} catch (FilterException exeption) {
				System.out.println(exeption.getMessage());
				return;
			}

			if (writeOutput(output, outputContent)) {
				System.out.println(Text.get(Text.COMMAND_LINE_EXECUTED,
						(command.getCommandName())));
			} else {
				System.out.println(Text.get(
						Text.COMMAND_LINE_EXECUTED_WITH_ERROR,
						(command.getCommandName())));
			}
			return;
		}

		// It is in file mode.
		writeFileMode(filter, inputContent, auxiliarContent,
				commandLineParameters);

	}

	/**
	 * Write the output or to a variable or to a file.
	 * 
	 * @param outputParameter
	 *            The target output.
	 * @param outputContent
	 *            The output content.
	 * @return True if the write was OK.
	 */
	private static boolean writeOutput(String outputParameter,
			StringBuffer outputContent) {

		String output = outputParameter;

		if (output == null || "".equals(output)) { //$NON-NLS-1$
			return false;
		}

		// Do not allow the write to the reserved variable.
		if (EMPTY_VARIABLE.equals(output)) {
			System.out.println(Text.get(
					Text.COMMAND_LINE_FILE_WRITING_ON_OUTPUT, output));
			return false;
		}

		// If it is a variable than store in the context
		if (output.charAt(0) == '%') {

			// If it is necessary to append files.
			if (appendFile) {

				// Get variable.
				StringBuffer variable = CLIPBOARD_VARIABLE.equals(output) ? new StringBuffer(
						ClipboardUtil.getClipboardContents()) : context
						.get(output);

				// Initialize the variable if necessary.
				if (variable == null) {

					variable = new StringBuffer();
				}

				variable.append(outputContent.toString());

				if (CLIPBOARD_VARIABLE.equals(output)) {
					ClipboardFactory.getClipboardInstance()
							.setClipboardContents(variable.toString());
				} else {
					context.put(output, variable);
				}

			} else {

				if (CLIPBOARD_VARIABLE.equals(output)) {
					ClipboardFactory.getClipboardInstance()
							.setClipboardContents(outputContent.toString());
				} else {
					context.put(output, outputContent);
				}
			}

			return true;
		}

		String content = definitions.get(output);
		if (content != null) {
			output = content;
		}

		// If it is necessary to append files.
		if (appendFile) {

			// Append the content.
			FileSupport.appendEncodedTextFile(output, outputContent.toString(),
					FilterAnyEncoding.getInstance().getOutputFileEncoding());
			return true;
		}

		// If it is possible to overwrite then write the file.
		if (overwriteFiles) {

			FileSupport.writeEncodedTextFile(output, outputContent.toString(),
					FilterAnyEncoding.getInstance().getOutputFileEncoding());
			return true;
		}

		// Try to create the file.
		File outputFile = new File(output);
		boolean created = false;
		try {

			created = outputFile.createNewFile();

			// Verify if the file was created.
			if (!created) {

				System.out.println(Text.get(
						Text.COMMAND_LINE_FILE_ALREADY_EXIST, output));
				return false;
			}

		} catch (IOException e) {

			System.out
					.println(Text.get(Text.COMMAND_LINE_INVALID_FILE, output));
			return false;
		}

		// Guarantee that the parameter is a file.
		if (!outputFile.isFile() && outputFile.exists()) {

			System.out.println(Text.get(
					Text.COMMAND_LINE_PARAMETER_IS_NOT_FILE, output));
			return false;
		}

		// Write the content.
		FileSupport.writeEncodedTextFile(output, outputContent.toString(),
				FilterAnyEncoding.getInstance().getOutputFileEncoding());

		return true;
	}

	/**
	 * Apply the filter and generate the output files.
	 * 
	 * @param filter
	 *            The filter to be applied.
	 * @param inputContent
	 *            The input with the files to be worked on.
	 * @param auxiliarContent
	 *            The auxiliary input.
	 * @param commandLineParameters
	 *            The command line parameters.
	 */
	private static void writeFileMode(FilterAdapter filter,
			StringBuffer inputContent, StringBuffer auxiliarContent,
			CommandLineParameters commandLineParameters) {

		// Get the rows to work with.
		ArrayList<FileOrURLBean> filteredRows = getRows(inputContent);

		String targetDirectory = commandLineParameters
				.getTargetDirectoryFileMode();
		String targetDirectoryContext = getContext(targetDirectory);
		String targetDirectoryName = validateTargetDirectory(filter,
				targetDirectoryContext);
		if (targetDirectoryName == null) {

			System.out.println(Text.get(
					Text.COMMAND_LINE_PARAMETER_TARGET_INVALID_FILE_NAME,
					targetDirectory));

			return;
		}

		filteredRows = FilterAnyLogic.setNewTargets(targetDirectoryName,
				filteredRows);

		// Apply the filter to all rows.
		for (FileOrURLBean row : filteredRows) {

			StringBuffer inputText = new StringBuffer();

			if (row.getType() == FileOrURLBean.FileOrURLBeanType.FILE) {

				inputText = FileSupport.readEncodedTextFile(
						row.getTarget(),
						FilterAnyEncoding.getInstance().getDefaultFileEncoding());
			}

			StringBuffer outputContent = null;
			try {

				outputContent = filter.filter(inputText, auxiliarContent, 0, 0);

			} catch (FilterException exception) {
				System.out.println(Text.get(Text.FAILURE_APPLYING_A_FILTER,
						row.getNewTarget(), exception.getLocalizedMessage()));
				continue;
			}

			String path = FileSupport.getOnlyPath(row.getNewTarget());

			if (!FileSupport.createDirectory(path)) {
				System.out.println(Text.get(Text.FAILURE_CREATING_DIRECTORIES,
						row.getNewTarget()));
				continue;

			}

			String mensagem = FileSupport.writeEncodedTextFile(
					row.getNewTarget(), outputContent.toString(),
					FilterAnyEncoding.getInstance().getOutputFileEncoding());
			if (mensagem != null) {
				System.out.println(Text.get(Text.FAILURE_PROCESSING_FILE,
						row.getNewTarget(), mensagem));
				continue;
			}

			System.out.println(Text.get(Text.SUCCESS_PROCESSING_FILE,
					row.getNewTarget()));

		}

		CommandLine command = (CommandLine) filter;
		System.out.println(Text.get(Text.COMMAND_LINE_EXECUTED,
				(command.getCommandName())));

		return;

	}

	/**
	 * Execute a binary filter.
	 * 
	 * @param finaryFileFilter
	 *            The filter.
	 */
	private static void executeBinaryCommand(CommandLine command,
			String[] elements) {

		// Guarantee that the filter is not binary.
		if (FilterAnyConfiguration.isTextMode()) {

			System.out.println(Text
					.get(Text.BINARY_FILTER_WORK_ONLY_ON_FILE_MODE));
			return;
		}

		// If the there are not input and output or input, alternative and
		// output.
		if (elements.length < 2) {

			System.out.println(Text.get(Text.COMMAND_LINE_INCORRECT_USE,
					command.getCommandName()));
			System.out.println(command.getHelpDescription());
			return;
		}

		StringBuffer inputContent = getInputContent(elements[1]);
		// The input content is invalid.
		if (inputContent == null) {
			return;
		}

		CommandLineParameters commandLineParameters = analyzeAdditionalParameters(
				command, StringSupport.arrayAtPosition(elements, 3));
		// If the parameters are incorrect.
		if (commandLineParameters == null) {
			return;
		}

		FilterAdapter filter = treatRequiredParameters(command,
				commandLineParameters);
		// If the filter is not set then an error happened.
		if (filter == null) {
			return;
		}

		// Get the rows to work with.
		ArrayList<FileOrURLBean> filteredRows = getRows(inputContent);

		String targetDirectoryName = validateTargetDirectory((Filter) command,
				getContext(commandLineParameters.getTargetDirectoryFileMode()));
		if (targetDirectoryName == null) {
			return;
		}

		filteredRows = FilterAnyLogic.setNewTargets(targetDirectoryName,
				filteredRows);

		// Check if there will be any overwrite.
		if (!validateOverwriteFiles(filteredRows, targetDirectoryName)) {
			return;
		}

		try {

			// Apply the rename file name filter if necessary.
			if (filter instanceof RenameFile) {

				RenameFile renameFilter = (RenameFile) filter;

				for (FileOrURLBean bean : filteredRows) {
					bean.setNewTarget(renameFilter.getNewFileName(bean
							.getNewTarget()));
				}
			}

		} catch (FilterException exception) {

			ExceptionSupport.handleException(exception);
			return;
		}

		// Read the file depending on the format requested.
		if (filter instanceof BinaryFilesFilter) {

			BinaryFilesFilter binaryFilter = (BinaryFilesFilter) filter;

			if (binaryFilter instanceof SpecialBehavior
					&& FilterAnyLogic.hasSpecialBehavior(
							(SpecialBehavior) binaryFilter,
							Behavior.BINARY_RECEIVE_FULL_FILE_LIST)) {

				StringBuffer inputText = new StringBuffer();
				for (FileOrURLBean row : filteredRows) {
					inputText
							.append(row.getTarget()
									+ (FilterAnyConfiguration
											.isGenerateTextInDOSFormat() ? StringSupport
											.getDOSNewLine() : StringSupport
											.getUnixNewLine()));
				}

				try {

					String output = binaryFilter.filter(inputText.toString(), new String());

					if (FilterAnyLogic.hasSpecialBehavior(
							(SpecialBehavior) binaryFilter,
							Behavior.BINARY_FILTER_GENERATE_OUTPUT_CONTENT)) {
						
						if (writeOutput(elements[2], new StringBuffer(output))) {
							System.out.println(Text.get(Text.COMMAND_LINE_EXECUTED,
									(command.getCommandName())));
						} else {
							System.out.println(Text.get(
									Text.COMMAND_LINE_EXECUTED_WITH_ERROR,
									(command.getCommandName())));
						}
					}
					
				} catch (FilterException exception) {

					String message = Text.get(Text.FAILURE_APPLYING_A_FILTER,
							filter.getFilterName(), exception.getMessage());

					System.out.println(message);

					return;

				}

				System.out.println(new StringBuffer(Text.get(
						Text.BINARY_RECEIVE_FULL_FILE_LIST_EXECUTION_MESSAGE,
						filter.getFilterName())));

				return;

			}
		}

		// Apply the filter to all rows.
		for (FileOrURLBean row : filteredRows) {

			try {

				BinaryFilesFilter binaryFilter = (BinaryFilesFilter) filter;
				binaryFilter.filter(row.getTarget(), row.getNewTarget());

			} catch (FilterException exception) {
				System.out.println(Text.get(Text.FAILURE_APPLYING_A_FILTER,
						row.getNewTarget(), exception.getLocalizedMessage()));
				continue;
			}

			if (row.getNewTarget() == null) {
				System.out.println(Text.get(Text.SUCCESS_PROCESSING_FILE,
						row.getTarget()));
			} else {
				System.out.println(Text.get(Text.SUCCESS_PROCESSING_FILE,
						row.getNewTarget()));
			}

		}
	}

	/**
	 * Validate if files are going to be overwritten and if overwrite mode is
	 * set.
	 * 
	 * @param filteredRows
	 *            The rows to work on.
	 * @param targetDirectoryName
	 *            The target directory.
	 * @return True if there is no problem, and false if there is a problem.
	 */
	private static boolean validateOverwriteFiles(
			ArrayList<FileOrURLBean> filteredRows, String targetDirectoryName) {

		// Check if there will be any overwrite.
		for (FileOrURLBean testFileDirectory : filteredRows) {

			if (testFileDirectory.getType() == FileOrURLBeanType.FILE) {

				String fileDirectory = FileSupport
						.getOnlyPath(testFileDirectory.getTarget());

				if (targetDirectoryName.equals(fileDirectory)) {

					if (overwriteFiles) {

						break;
					}

					System.out
							.println(Text
									.get(Text.TARGET_DIRECTORY_OVERWRITE_CURRENT_FILES_EXCEPTION));
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Validate if the target directory has no errors.
	 * 
	 * @param currentFilter
	 *            The current filter.
	 * @param targetDirectoryParameter
	 *            The provided target directory.
	 * @return The validated target directory of null if a problem happens.
	 */
	private static String validateTargetDirectory(Filter currentFilter,
			String targetDirectoryParameter) {

		String targetDirectoryName = targetDirectoryParameter;

		if (targetDirectoryParameter == null
				|| "".equals(targetDirectoryParameter)) { //$NON-NLS-1$

			if (currentFilter instanceof SpecialBehavior
					&& FilterAnyLogic
							.hasSpecialBehavior(
									(SpecialBehavior) currentFilter,
									SpecialBehavior.Behavior.DO_NOT_SHOW_TARGET_DIRECTORY)) {
				// Target directory is not required then use the current.
				return "."; //$NON-NLS-1$

			}

			System.out.println(Text
					.get(Text.COMMAND_LNE_MISSING_TARGET_DIRECTORY));

			return null;
		}

		File targetDirectory = new File(targetDirectoryParameter);
		if (!targetDirectory.isDirectory()) {
			System.out.println(Text
					.get(Text.COMMAND_LNE_INVALID_TARGET_DIRECTORY));
			return null;
		}

		targetDirectoryName = FileSupport
				.addDirectorySymbolAtTheEnd(targetDirectory.getAbsolutePath());

		return targetDirectoryName;
	}

	/**
	 * Provide the rows to work on file mode.
	 * 
	 * @param inputContent
	 *            The input content.
	 * @return The rows.
	 */
	private static ArrayList<FileOrURLBean> getRows(StringBuffer inputContent) {

		// It is file mode than iterate on all files and URLs.
		ArrayList<FileOrURLBean> rows = FilterAnyLogic
				.generateTableModel(inputContent.toString());

		return FilterAnyLogic.filterValidRows(rows);
	}

	/**
	 * Provide the input content.
	 * 
	 * @param inputParameter
	 *            The input parameter.
	 * @return The input content.
	 */
	private static StringBuffer getInputContent(String inputParameter) {

		String input = inputParameter;

		if (input == null || "".equals(input)) { //$NON-NLS-1$
			return null;
		}

		// If it is a variable then try to use the variable from the context.

		if (input.charAt(0) == '%') {

			// Retrieve clipboard content if requested.
			if (CLIPBOARD_VARIABLE.equals(input)) {

				return new StringBuffer(ClipboardUtil.getClipboardContents());
			}

			StringBuffer inputValue = context.get(input);

			if (inputValue == null) {
				System.out.println(Text.get(
						Text.COMMAND_LINE_VARIABLE_NOT_FOUND, input));
				return null;
			}

			// Create a copy of the inputValue
			inputValue = new StringBuffer(inputValue);

			return inputValue;
		}

		// See if there is a definition.
		String content = definitions.get(input);
		if (content != null) {
			input = content;
		}

		File inputFile = new File(input);

		if (!inputFile.isFile() || !inputFile.exists()) {
			System.out.println(Text
					.get(Text.COMMAND_LINE_FILE_NOT_FOUND, input));
			return null;
		}

		return (new StringBuffer(FileSupport.readEncodedTextFile(input,
				FilterAnyEncoding.getInstance().getDefaultFileEncoding())));
	}

	/**
	 * Provide the auxiliar content.
	 * 
	 * @param auxiliarParameter
	 *            The auxiliar parameter.
	 * @return The auxiliar content.
	 */
	private static StringBuffer getAuxiliarContent(String auxiliarParameter) {

		if (auxiliarParameter == null || "".equals(auxiliarParameter)) { //$NON-NLS-1$
			return null;
		}

		// If it is a variable then try to use the variable from the context.
		if (auxiliarParameter.charAt(0) == '%') {

			// Retrieve clipboard content if requested.
			if (CLIPBOARD_VARIABLE.equals(auxiliarParameter)) {

				return new StringBuffer(ClipboardUtil.getClipboardContents());
			}

			StringBuffer auxiliarVariable = new StringBuffer(
					context.get(auxiliarParameter));

			// Use a copy of the context so the context does not
			// change.
			return (auxiliarVariable);

		}

		File auxiliarFile = new File(auxiliarParameter);

		if (!auxiliarFile.isFile() || !auxiliarFile.exists()) {
			System.out.println(Text.get(Text.COMMAND_LINE_FILE_NOT_FOUND,
					auxiliarParameter));
			return null;
		}

		return (new StringBuffer(FileSupport.readEncodedTextFile(
				auxiliarParameter, FilterAnyEncoding.getInstance()
						.getDefaultFileEncoding())));
	}

	/**
	 * Provide the output target.
	 * 
	 * @param outputParameter
	 *            The output target.
	 * @return The output target.
	 */
	static String getOutputTarget(String outputParameter) {

		// Output parameter not valid.
		if (outputParameter == null || "".equals(outputParameter)) { //$NON-NLS-1$
			return null;
		}

		// See if the output is a definition.
		String content = definitions.get(outputParameter);
		if (content != null) {
			return content;
		}

		return outputParameter;
	}

	/**
	 * Treat the required parameters and provide the filter with the parameters
	 * set.
	 * 
	 * @param command
	 *            The command line filter.
	 * @param commandLineParameters
	 *            the required parameters.
	 * @return The required parameters and provide the filter with the
	 *         parameters set.
	 */
	private static FilterAdapter treatRequiredParameters(CommandLine command,
			CommandLineParameters commandLineParameters) {

		FilterAdapter filter = (FilterAdapter) command;

		FilterControls controls[] = filter.getControls();
		FilterParameter parameters[] = filter.gerControlsRequired();
		for (int i = 0; i < controls.length; i++) {

			switch (controls[i]) {
			case INPUT_FIELD_1: {

				// Retrieve the variable if it was specified and exist.
				String parameterProvided = commandLineParameters.getField1();

				if (parameterProvided == null
						&& parameters[i] == FilterParameter.REQUIRED) {

					System.out.println(Text
							.get(Text.COMMAND_LINE_REQUIRED_PARAMETER_MISSING,
									"-f1")); //$NON-NLS-1$
					return null;
				}

				// The parameter does not exist and it is not required.
				if (parameterProvided != null) {

					String value = getContext(parameterProvided);
					if (value == null) {
						return null;
					}

					filter.setField1(FilterAnyLogic
							.replaceSpecialCharacters(value));
				}
				break;
			}
			case INPUT_FIELD_2: {

				// Retrieve the variable if it was specified and exist.
				String parameterProvided = commandLineParameters.getField2();

				if (parameterProvided == null
						&& parameters[i] == FilterParameter.REQUIRED) {
					System.out.println(Text
							.get(Text.COMMAND_LINE_REQUIRED_PARAMETER_MISSING,
									"-f2")); //$NON-NLS-1$
					return null;
				}

				// The parameter does not exist and it is not required.
				if (parameterProvided != null) {

					String value = getContext(parameterProvided);
					if (value == null) {
						return null;
					}

					filter.setField2(FilterAnyLogic
							.replaceSpecialCharacters(value));
				}
				break;
			}
			case INPUT_FIELD_3: {

				// Retrieve the variable if it was specified and exist.
				String parameterProvided = commandLineParameters.getField3();

				if (parameterProvided == null
						&& parameters[i] == FilterParameter.REQUIRED) {
					System.out.println(Text
							.get(Text.COMMAND_LINE_REQUIRED_PARAMETER_MISSING,
									"-f3")); //$NON-NLS-1$
					return null;
				}

				// The parameter does not exist and it is not required.
				if (parameterProvided != null) {

					String value = getContext(parameterProvided);
					if (value == null) {
						return null;
					}

					filter.setField3(FilterAnyLogic
							.replaceSpecialCharacters(value));
				}
				break;
			}
			case CHECK_BOX_1: {
				if (!commandLineParameters.isCheckbox1Set()
						&& parameters[i] == FilterParameter.REQUIRED) {
					System.out.println(Text
							.get(Text.COMMAND_LINE_REQUIRED_PARAMETER_MISSING,
									"-c1")); //$NON-NLS-1$
					return null;
				}
				filter.setCheckBox1(commandLineParameters.isCheckbox1());
				break;
			}
			case CHECK_BOX_2: {
				if (!commandLineParameters.isCheckbox2Set()
						&& parameters[i] == FilterParameter.REQUIRED) {
					System.out.println(Text
							.get(Text.COMMAND_LINE_REQUIRED_PARAMETER_MISSING,
									"-c2")); //$NON-NLS-1$
					return null;
				}
				filter.setCheckBox2(commandLineParameters.isCheckbox2());
				break;
			}
			case TARGET_FILE_OR_DIRECTORY_1: {

				// Retrieve the variable if it was specified and exist.
				String parameterProvided = commandLineParameters.getTarget1();

				if (parameterProvided == null
						&& parameters[i] == FilterParameter.REQUIRED) {
					System.out.println(Text
							.get(Text.COMMAND_LINE_REQUIRED_PARAMETER_MISSING,
									"-t1")); //$NON-NLS-1$
					return null;
				}

				String value = getContext(parameterProvided);
				if (value == null) {
					return null;
				}

				filter.setTargetFileOrDirectory1(value);
				break;
			}
			case TARGET_FILE_1: {

				// Retrieve the variable if it was specified and exist.
				String parameterProvided = commandLineParameters.getFile1();

				if (parameterProvided == null
						&& parameters[i] == FilterParameter.REQUIRED) {
					System.out.println(Text
							.get(Text.COMMAND_LINE_REQUIRED_PARAMETER_MISSING,
									"-l1")); //$NON-NLS-1$
					return null;
				}

				String value = getContext(parameterProvided);
				if (value == null) {
					return null;
				}

				filter.setFile1(value);
				break;
			}
			case TARGET_DIRECTORY_1: {

				// Retrieve the variable if it was specified and exist.
				String parameterProvided = commandLineParameters
						.getDirectory1();

				if (parameterProvided == null
						&& parameters[i] == FilterParameter.REQUIRED) {
					System.out.println(Text
							.get(Text.COMMAND_LINE_REQUIRED_PARAMETER_MISSING,
									"-d1")); //$NON-NLS-1$
					return null;
				}

				String value = getContext(parameterProvided);
				if (value == null) {
					return null;
				}

				filter.setDirectory1(value);
				break;
			}
			default:
				break;
			}
		}

		return filter;
	}

	/**
	 * Provide the content of a parameter. It can be itself, or a definition or
	 * a variable.
	 * 
	 * @param parameter
	 *            The parameter.
	 * @return The variable content if parameter is variable or the parameter
	 *         itself.
	 */
	private static String getContext(String parameter) {

		// The parameter is invalid.
		if (parameter == null) {

			return null;
		}

		// If it is a variable then it should be initialized.
		if (!"".equals(parameter) && parameter.charAt(0) == '%') { //$NON-NLS-1$

			// Retrieve clipboard content if requested.
			StringBuffer variableValue = CLIPBOARD_VARIABLE.equals(parameter) ? new StringBuffer(
					ClipboardUtil.getClipboardContents()) : new StringBuffer(
					context.get(parameter));

			return variableValue.toString();
		}

		String content = definitions.get(parameter);
		if (content != null) {
			return content;
		}

		return parameter;
	}

	/**
	 * Validate the optional parameters.
	 * 
	 * @param commandLineParameters
	 *            The optional parameters.
	 * @param inputContent
	 *            The input text.
	 * @return The analysis of the optional parameters.
	 */
	private static CommandLineOptionalParameters validateOptionalParameters(
			CommandLineParameters commandLineParameters,
			StringBuffer inputContent) {

		int start = 0;
		int end = 0;

		if (commandLineParameters.isWellFormattedPosition()) {

			// The preference is line setup.
			if (commandLineParameters.isLineStartProvided()
					|| commandLineParameters.isLineEndProvided()) {

				if (commandLineParameters.isLineStartProvided()
						&& commandLineParameters.getLineStart() < 0) {
					System.out.println(Text
							.get(Text.LINE_START_IS_NEGATIVE, String
									.valueOf(commandLineParameters
											.getLineStart())));
					return new CommandLineOptionalParameters(true);
				}

				if (commandLineParameters.isLineEndProvided()
						&& commandLineParameters.getLineEnd() < 0) {
					System.out
							.println(Text.get(Text.LINE_END_IS_NEGATIVE,
									String.valueOf(commandLineParameters
											.getLineEnd())));
					return new CommandLineOptionalParameters(true);
				}

				int characterStart = StringSupport
						.getPositionOfBeginnintOfLine(inputContent,
								commandLineParameters.getLineStart());

				if (commandLineParameters.isLineStartProvided()
						&& characterStart < 0) {
					System.out.println(Text.get(
							Text.LINE_START_IS_GREATER_THEN_TEXT_LENGTH, String
									.valueOf(commandLineParameters
											.getLineStart())));
					return new CommandLineOptionalParameters(true);
				}

				int characterEnd = StringSupport.getPositionOfTheEndOfLine(
						inputContent, commandLineParameters.getLineEnd());

				if (commandLineParameters.isLineStartProvided()
						&& commandLineParameters.isLineEndProvided()) {
					if (commandLineParameters.getLineStart() > commandLineParameters
							.getLineEnd()) {
						System.out.println(Text.get(
								Text.LINE_END_IS_NOT_GREATER_THEN_LINE_START,
								String.valueOf(commandLineParameters
										.getLineStart()), String
										.valueOf(commandLineParameters
												.getLineEnd())));
						return new CommandLineOptionalParameters(true);
					}
				}

				if (commandLineParameters.isLineStartProvided() != commandLineParameters
						.isLineEndProvided()) {

					System.out
							.println(Text.get(
									Text.PROVIDE_BOTH_LINE_START_AND_END,
									String.valueOf(commandLineParameters
											.getLineStart()), String
											.valueOf(commandLineParameters
													.getLineEnd())));
					return new CommandLineOptionalParameters(true);

				}

				start = characterStart;
				end = characterEnd;

			} else {

				if (commandLineParameters.isCharacterStartProvided()
						|| commandLineParameters.isCharacterEndProvided()) {

					if (commandLineParameters.isCharacterStartProvided()
							&& commandLineParameters.getCharacterStart() < 0) {
						System.out.println(Text.get(
								Text.CHARATER_START_IS_NEGATIVE, String
										.valueOf(commandLineParameters
												.getCharacterStart())));
						return new CommandLineOptionalParameters(true);
					}

					if (commandLineParameters.isCharacterEndProvided()
							&& commandLineParameters.getCharacterEnd() < 0) {
						System.out.println(Text.get(
								Text.CHARATER_END_IS_NEGATIVE, String
										.valueOf(commandLineParameters
												.getCharacterEnd())));
						return new CommandLineOptionalParameters(true);
					}

					int characterStart = commandLineParameters
							.getCharacterStart();

					if (commandLineParameters.isCharacterStartProvided()
							&& characterStart > inputContent.length()) {
						System.out
								.println(Text
										.get(Text.CHARATER_START_IS_GREATER_THEN_TEXT_LENGTH,
												String.valueOf(commandLineParameters
														.getCharacterStart())));
						return new CommandLineOptionalParameters(true);
					}

					int characterEnd = commandLineParameters.getCharacterEnd();
					if (characterEnd > inputContent.length()) {
						characterEnd = inputContent.length();
					}

					if (commandLineParameters.isCharacterStartProvided()
							&& commandLineParameters.isCharacterEndProvided()) {
						if (commandLineParameters.getCharacterStart() > commandLineParameters
								.getCharacterEnd()) {
							System.out
									.println(Text
											.get(Text.CHARATER_END_IS_NOT_GREATER_THEN_LINE_START,
													String.valueOf(commandLineParameters
															.getCharacterStart()),
													String.valueOf(commandLineParameters
															.getCharacterEnd())));
							return new CommandLineOptionalParameters(true);
						}
					}

					if (commandLineParameters.isCharacterStartProvided() != commandLineParameters
							.isCharacterEndProvided()) {

						System.out.println(Text.get(
								Text.PROVIDE_BOTH_CHARACTER_START_AND_END,
								String.valueOf(commandLineParameters
										.getCharacterStart()), String
										.valueOf(commandLineParameters
												.getCharacterEnd())));
						return new CommandLineOptionalParameters(true);

					}

					start = characterStart;
					end = characterEnd;

				}
			}
		} else {

			if (commandLineParameters.isLineStartProvidedWithError()) {
				System.out.println(Text.get(Text.INVALID_LINE_START));
				return new CommandLineOptionalParameters(true);
			}

			if (commandLineParameters.isLineEndProvidedWithError()) {
				System.out.println(Text.get(Text.INVALID_LINE_END));
				return new CommandLineOptionalParameters(true);
			}

			if (commandLineParameters.isCharacterStartProvidedWithError()) {
				System.out.println(Text.get(Text.INVALID_CHARACTER_START));
				return new CommandLineOptionalParameters(true);
			}

			if (commandLineParameters.isCharacterEndProvidedWithError()) {
				System.out.println(Text.get(Text.INVALID_CHARACTER_END));
				return new CommandLineOptionalParameters(true);
			}
		}

		return new CommandLineOptionalParameters(start, end);
	}

	/**
	 * Indicate if the the command requires the provided control.
	 * 
	 * @param command
	 *            The command.
	 * @param control
	 *            The controls
	 * @return True if the the command requires the provided control.
	 */
	private static boolean parameterUsed(CommandLine command,
			FilterControls control) {

		FilterAdapter filter = (FilterAdapter) command;

		for (FilterControls filterControl : filter.getControls()) {

			if (filterControl == control) {

				return true;
			}
		}

		return false;
	}

	/**
	 * Analyze all the additional parameters possible for all filtersWindow.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param options
	 *            The options.
	 * @return The additional parameters.
	 */
	private static CommandLineParameters analyzeAdditionalParameters(
			CommandLine command, String options[]) {

		CommandLineParameters commandLineParameters = new CommandLineParameters();

		// Ignore lack of parameters.
		if (options == null || options.length == 0) {
			return commandLineParameters;
		}

		int currentOption = 0;
		while (currentOption < options.length) {

			String option = options[currentOption].toUpperCase();

			// Retrieve the parameter if it exist.
			String parameter = null;
			if (currentOption + 1 < options.length) {

				parameter = options[currentOption + 1];

			} else {
				// Abort
				commandLineParameters.setWellFormattedPosition(false);
				return commandLineParameters;
			}

			// Character start.
			if ("-S".equals(option)) { //$NON-NLS-1$

				int s = -1;
				try {
					s = Integer.parseInt(parameter);
				} catch (NumberFormatException exception) {
					// Abort
					commandLineParameters.setWellFormattedPosition(false);
					commandLineParameters
							.setCharacterStartProvidedWithError(true);
					return commandLineParameters;
				}

				commandLineParameters.setCharacterStart(s);
				commandLineParameters.setCharacterStartProvided(true);
			}

			// Character end.
			if ("-E".equals(option)) { //$NON-NLS-1$

				int e = -1;
				try {
					e = Integer.parseInt(parameter);
				} catch (NumberFormatException exception) {
					// Abort
					commandLineParameters.setWellFormattedPosition(false);
					commandLineParameters
							.setCharacterEndProvidedWithError(true);
					return commandLineParameters;
				}
				commandLineParameters.setCharacterEnd(e);
				commandLineParameters.setCharacterEndProvided(true);
			}

			// Line start.
			if ("-LS".equals(option)) { //$NON-NLS-1$
				int ls = -1;
				try {
					ls = Integer.parseInt(parameter);
				} catch (NumberFormatException exception) {
					// Abort
					commandLineParameters.setWellFormattedPosition(false);
					commandLineParameters.setLineStartProvidedWithError(true);
					return commandLineParameters;
				}
				commandLineParameters.setLineStart(ls);
				commandLineParameters.setLineStartProvided(true);
			}

			// Line end.
			if ("-LE".equals(option)) { //$NON-NLS-1$
				int le = -1;
				try {
					le = Integer.parseInt(parameter);
				} catch (NumberFormatException exception) {
					// Abort
					commandLineParameters.setWellFormattedPosition(false);
					commandLineParameters.setLineEndProvidedWithError(true);
					return commandLineParameters;
				}
				commandLineParameters.setLineEnd(le);
				commandLineParameters.setLineEndProvided(true);
			}

			// First field
			if ("-F1".equals(option)) { //$NON-NLS-1$

				if (!parameterUsed(command, FilterControls.INPUT_FIELD_1)) {
					System.out.println(Text.get(
							Text.PARAMETER_NOT_USED_BY_FILTER_EXCEPTON, "-f1")); //$NON-NLS-1$
					return null;
				}
				commandLineParameters.setField1(parameter);
			}

			// Second field
			if ("-F2".equals(option)) { //$NON-NLS-1$

				if (!parameterUsed(command, FilterControls.INPUT_FIELD_2)) {
					System.out.println(Text.get(
							Text.PARAMETER_NOT_USED_BY_FILTER_EXCEPTON, "-f2")); //$NON-NLS-1$
					return null;
				}
				commandLineParameters.setField2(parameter);
			}

			// Third field
			if ("-F3".equals(option)) { //$NON-NLS-1$

				if (!parameterUsed(command, FilterControls.INPUT_FIELD_3)) {
					System.out.println(Text.get(
							Text.PARAMETER_NOT_USED_BY_FILTER_EXCEPTON, "-f3")); //$NON-NLS-1$
					return null;
				}
				commandLineParameters.setField3(parameter);
			}

			// First check box.
			if ("-C1".equals(option)) { //$NON-NLS-1$

				if ("Y".equals(parameter.toUpperCase()) //$NON-NLS-1$
						|| "YES".equals(parameter.toUpperCase())) { //$NON-NLS-1$
					commandLineParameters.setCheckbox1(true);
					commandLineParameters.setCheckbox1Set(true);
				} else {
					if ("N".equals(parameter.toUpperCase()) //$NON-NLS-1$
							|| "NO".equals(parameter.toUpperCase())) { //$NON-NLS-1$
						commandLineParameters.setCheckbox1(false);
						commandLineParameters.setCheckbox1Set(true);
					} else {
						commandLineParameters.setWellFormattedPosition(false);
						return commandLineParameters;
					}
				}

				if (commandLineParameters.isCheckbox1Set()) {
					if (!parameterUsed(command, FilterControls.CHECK_BOX_1)) {
						System.out.println(Text.get(
								Text.PARAMETER_NOT_USED_BY_FILTER_EXCEPTON,
								"-c1")); //$NON-NLS-1$
						return null;
					}
				}
			}

			// Second check box.
			if ("-C2".equals(option)) { //$NON-NLS-1$

				if ("Y".equals(parameter.toUpperCase()) //$NON-NLS-1$
						|| "YES".equals(parameter.toUpperCase())) { //$NON-NLS-1$
					commandLineParameters.setCheckbox2(true);
					commandLineParameters.setCheckbox2Set(true);
				} else {
					if ("N".equals(parameter.toUpperCase()) //$NON-NLS-1$
							|| "NO".equals(parameter.toUpperCase())) { //$NON-NLS-1$
						commandLineParameters.setCheckbox2(false);
						commandLineParameters.setCheckbox2Set(true);
					} else {
						commandLineParameters.setWellFormattedPosition(false);
						return commandLineParameters;
					}
				}
				if (commandLineParameters.isCheckbox2Set()) {
					if (!parameterUsed(command, FilterControls.CHECK_BOX_2)) {
						System.out.println(Text.get(
								Text.PARAMETER_NOT_USED_BY_FILTER_EXCEPTON,
								"-c2")); //$NON-NLS-1$
						return null;
					}
				}
			}

			// Target directory 1
			if ("-T1".equals(option)) { //$NON-NLS-1$

				if (!parameterUsed(command,
						FilterControls.TARGET_FILE_OR_DIRECTORY_1)) {
					System.out.println(Text.get(
							Text.PARAMETER_NOT_USED_BY_FILTER_EXCEPTON, "-t1")); //$NON-NLS-1$
					return null;
				}
				commandLineParameters.setTarget1(parameter);
			}

			// Target file 1
			if ("-L1".equals(option)) { //$NON-NLS-1$

				if (!parameterUsed(command, FilterControls.TARGET_FILE_1)) {
					System.out.println(Text.get(
							Text.PARAMETER_NOT_USED_BY_FILTER_EXCEPTON, "-l1")); //$NON-NLS-1$
					return null;
				}
				commandLineParameters.setFile1(parameter);
			}

			// Target directory 1
			if ("-D1".equals(option)) { //$NON-NLS-1$

				if (!parameterUsed(command, FilterControls.TARGET_DIRECTORY_1)) {
					System.out.println(Text.get(
							Text.PARAMETER_NOT_USED_BY_FILTER_EXCEPTON, "-d1")); //$NON-NLS-1$
					return null;
				}
				commandLineParameters.setDirectory1(parameter);
			}

			// Target directory when file mode is activated.
			if ("-TDIR".equals(option)) { //$NON-NLS-1$

				if (FilterAnyConfiguration.isTextMode()) {
					System.out
							.println(Text.get(
									Text.PARAMETER_NOT_USED_WHEN_IN_TEXT_MODE,
									"-tdir")); //$NON-NLS-1$
					return null;
				}

				commandLineParameters.setTargetDirectoryFileMode(parameter);
			}

			currentOption += 2;
		}

		return commandLineParameters;
	}

	/**
	 * Read the command lines available.
	 * 
	 * @return The command lines.
	 */
	private static ArrayList<CommandLine> getCommandLines() {

		ArrayList<CommandLine> lineCommands = new ArrayList<>();

		// Characters Filters.
		lineCommands.add(new ToUpperCaseFilter());
		lineCommands.add(new ToLowerCaseFilter());
		lineCommands.add(new FirstCharacterToUpperCaseFilter());
		lineCommands.add(new ToDOSFilter());
		lineCommands.add(new ToUNIXFilter());
		lineCommands.add(new FromSpacesToTabFilter());
		lineCommands.add(new FromTabToSpacesFilter());
		lineCommands.add(new RemoveCharacterOnBeginningFilter());
		lineCommands.add(new RemoveCharacterOnTheEndFilter());
		lineCommands.add(new RemoveCharacterBeforePositionFilter());
		lineCommands.add(new RemoveCharacterAfterPositionFilter());
		lineCommands.add(new RemoveCharacterBetweenPositionsFilter());
		lineCommands.add(new RemoveCharactersBetweenParametersFilter());
		lineCommands.add(new RemoveCharactersOutsideParametersFilter());
		lineCommands.add(new RemoveSpacesOnBeginningFilter());
		lineCommands.add(new RemoveSpacesOnTheEndFilter());
		lineCommands.add(new RemoveSpacesFromExtremitiesFilter());
		lineCommands.add(new RemoveCharacterBeforeParameterFilter());
		lineCommands.add(new RemoveCharacterAfterParameterFilter());
		lineCommands.add(new LeaveOnlyNumbersFilter());
		lineCommands.add(new CollapseSpacesFilter());
		lineCommands.add(new KeepOnlyValidCharactersFilter());
		lineCommands.add(new CopyFilter());

		// Line Filters.
		lineCommands.add(new AddAnchorOnBeginOfLineFilter());
		lineCommands.add(new AddAnchorOnEndOfLineFilter());
		lineCommands.add(new AddAnchorOnAPositionFilter());
		lineCommands.add(new AddParameterBeforeParameterFilter());
		lineCommands.add(new AddParameterAfterfParameterFilter());
		lineCommands.add(new EnumerateLinesFilter());
		lineCommands.add(new RemoveDuplicatedLinesFilter());
		lineCommands.add(new EliminateAllDuplicatedLinesFilter());
		lineCommands.add(new RemoveNonDuplicatedLinesFilter());
		lineCommands.add(new RemoveEmptyLinesFilter());
		lineCommands.add(new RemoveLinesWithContentFilter());
		lineCommands.add(new RemoveLinesWithoutContentFilter());
		lineCommands.add(new RemoveLinesWithLengthFilter());
		lineCommands.add(new SimpleContentReplacementFilter());
		lineCommands.add(new RegularExpressionReplacementFilter());
		lineCommands.add(new ReplicateLineContentFilter());
		lineCommands.add(new SortLinesAscendentFilter());
		lineCommands.add(new SortLinesDescendentFilter());
		lineCommands.add(new SortLinesAscendentBasedOnPositionFilter());
		lineCommands.add(new SortLinesDescendentBasedOnPositionFilter());
		lineCommands.add(new InvertOrderFilter());
		lineCommands.add(new SortLinesAscendentByLengthFilter());
		lineCommands.add(new ScrambleLinesFilter());
		lineCommands.add(new CombineLinesAtARegularStepFilter());
		lineCommands.add(new CombineAllLinesFilter());
		lineCommands.add(new MaintainLinesAtRegularStepFilter());
		lineCommands.add(new SwapContentBasedOnParameterFilter());

		// Dual pane filters.
		lineCommands.add(new RemoveLinesThatExistOnMainAndAuxiliar());
		lineCommands.add(new RemoveLinesThatDoNotExistOnMainAndAuxiliar());
		lineCommands.add(new CombineMainWithAuxiliar());
		lineCommands.add(new IntercalateMainWithAuxiliar());
		lineCommands.add(new MultipleInsertAuxiliarIntoMainFilter());
		lineCommands.add(new ReplaceMultipleTexts());
		lineCommands.add(new RemoveLinesWithMultipleParameters());
		lineCommands.add(new RemoveLinesWithoutMultipleParameters());
		lineCommands.add(new InsertAuxiliarIntoMain());
		lineCommands.add(new InsertAuxiliarBetweenParameters());

		// Binary filters.
		lineCommands.add(new ReplaceAndRenameFilter());
		lineCommands.add(new DeleteFilesFilter());
		lineCommands.add(new AppendFilesFilter());
		lineCommands.add(new CalculateMD5Filter());
		lineCommands.add(new DuplicatedFilesFilter());

		// Texts filters.
		lineCommands.add(new ShowDuplicatedConsecutiveWordsFilter());
		lineCommands.add(new BreakLinesOnSpecifiedLengthFilter());

		// Search filters.
		lineCommands.add(new SearchFileFilter());

		// Utilities filters.
		lineCommands.add(new CalculatorFilter());
		lineCommands.add(new RandomDataGeneratorFilter());
		lineCommands.add(new TreeDirectoryFilter());
		lineCommands.add(new FileListFilter());
		lineCommands.add(new DeleteDirectoryFilter());
		lineCommands.add(new KnapSackFileListerFilter());
		lineCommands.add(new KnapSackFilter());

		// Java filters.
		lineCommands.add(new JavaBeanFilter());
		lineCommands.add(new JspFormatterFilter());
		lineCommands.add(new BreakStringIntoFieldsFilter());
		lineCommands.add(new CombineFieldsIntoStringFilter());
		lineCommands.add(new JSONFormatterFilter());
		lineCommands.add(new XMLFormatterFilter());
		lineCommands.add(new CharacterToUpperOrLowerCaseFilter());

		// AWT filters.
		lineCommands.add(new AWK1Filter());
		lineCommands.add(new AWK2Filter());
		lineCommands.add(new AWK3Filter());

		// Timesheet filters.
		lineCommands.add(new StartTaskTimesheetFilter());
		lineCommands.add(new StopTaskTimesheetFilter());
		lineCommands.add(new CalculateTotalTimesheetFilter());
		lineCommands.add(new FromAMPMto24hFilter());
		lineCommands.add(new From24hToAMPMFilter());

		// Fun filters
		lineCommands.add(new TextWithNumbersFilter());
		lineCommands.add(new TextWithErrorsFilter());
		lineCommands.add(new MorseFilter());

		// Internet filters.
		lineCommands.add(new DownloadInternetBlockerFilter());
		lineCommands.add(new SimpleAlertFilter());

		return lineCommands;
	}

	/**
	 * Open the console by monitor the input from command line.
	 */
	private static void openConsoleMonitorCommandLine() {

		// Command line does not shows swing alert messages.
		ExceptionSupport.setUseSwingMessages(false);

		System.out.println(Text.get(Text.COMMAND_LINE_MAIN_HELP_1));
		System.out.println();

		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			System.out.println(Text.get(Text.DEMONSTRATION_VERSION_TEXT));
		}

		// Code that validated the hardware information.
		//
		// else {
		//
		// RegistrationUser user = new RegistrationUser();
		// String registerName = user.getRegisteredText();
		//
		// RegistrationComputer registrationComputer = new
		// RegistrationComputer();
		// if (!ComputerIdentity.getComputerIdentification().equals(
		// registrationComputer.getRegisteredText())) {
		//
		// System.out.println(RegistrationDialog.getMainMessage());
		// System.out.println(registerName);
		// System.out.println(RegistrationDialog.getSecondaryMessage());
		// System.exit(0);
		// }
		//
		// System.out.println(Text.get(Text.REGISTER_PREFIX) + " "
		// + registerName);
		// }

		System.out.println();
		System.out.println(Text.get(Text.COMMAND_LINE_MAIN_HELP_2));
		try {

			// While there are commands to be read.
			int bytesRead = 0;
			byte[] command = new byte[MAX_BUFFER];
			while ((bytesRead = System.in.read(command)) != -1) {

				// Verify if typed text is greater than buffer.
				if (bytesRead == MAX_BUFFER) {

					ExceptionSupport.handleException(Text
							.get(Text.EXCEPTION_BUFFER));
					continue;
				}

				treatCommandLine(new String(command, 0, bytesRead));
			}

		} catch (IOException exception) {

			ExceptionSupport.handleException(exception);
		}
	}

	/**
	 * Execute a sequence of commands on a script file.
	 */
	private static void executeScript(String scriptFileName) {

		// Command line does not shows swing alert messages.
		ExceptionSupport.setUseSwingMessages(false);

		// Verify if file exist first.
		if (!FileSupport.fileExist(scriptFileName)) {

			System.out.println(Text.get(Text.SCRIPT_FILE_DOES_NOT_EXIST,
					scriptFileName));
			return;
		}

		String lines[] = FileSupport.readAllLines(scriptFileName);

		if (lines == null) {

			System.out.println(Text.get(Text.COMMAND_LINE_SCRIPT_FILE_PROBLEM));
			return;
		}

		for (String currentLine : lines) {

			if ("".equals(currentLine.trim())) { //$NON-NLS-1$
				continue;
			}
			if (currentLine.trim().charAt(0) == '#') {
				continue;
			}

			// Ignore empty lines.
			if (!"".equals(currentLine.trim())) { //$NON-NLS-1$

				String elements[] = StringSupport
						.breakIntoCommandLineElements(currentLine);

				// Ignore empty lines.
				if (elements.length != 0) {
					String commandRequested = elements[0].toUpperCase();

					// Search for the command to be executed.
					for (CommandLine command : commands) {

						// If found the command than execute it.
						if (command.getCommandName().toUpperCase()
								.equals(commandRequested.toUpperCase())) {

							System.out.println(Text.get(
									Text.COMMAND_LINE_EXECUTING_COMMAND,
									command.getCommandName()));

							break;
						}
					}
				}
			}

			// Execute the command.
			treatCommandLine(currentLine);
		}

		System.out.println(Text.get(Text.COMMAND_LINE_SCRIPT_EXECUTED));

	}

	/**
	 * Analyze the command line provided.
	 * 
	 * @param args
	 *            The command line provided.
	 */
	protected static void analyzeCommandLine(String[] args) {

		// If the request is to use console.
		if (args.length == 0) {

			openConsoleMonitorCommandLine();
		}

		// Analyze the first command.
		String command = args[0].toUpperCase();

		// Run a script.
		if ("-SCRIPT".equals(command)) { //$NON-NLS-1$

			// If the file name was not provide than generate an exception.
			if (args.length <= 1) {

				System.out.println(Text
						.get(Text.COMMAND_LINE_SCRIPT_NAME_MISSING));

				return;

			}

			// Execute the script
			executeScript(args[1]);

			return;
		}

		// Try to execute a command.
		// Construct a line.
		String line = new String();
		for (int i = 0; i < args.length; i++) {

			String argument = args[i];

			line += argument + " "; //$NON-NLS-1$
		}
		treatCommandLine(line.trim());
	}

	/**
	 * Instantiate the FilterAny command line.
	 * 
	 * @param args
	 *            Ignored.
	 */
	public static void start(String[] args) {

		FilterAnyConfiguration.initialize(false);

		Locale.setDefault(FilterAnyConfiguration.getCurrentLocale());

		CommandLineController.analyzeCommandLine(args);

	}
}
