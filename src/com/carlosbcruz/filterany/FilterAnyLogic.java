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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.filterany.filters.*;
import com.carlosbcruz.util.FileSupport;
import com.carlosbcruz.util.LineTokenizer;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Implements support logic methods for the Filter Any.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnyLogic {

	private static JMenuItem textOrTableModeMenu = null;
	private static JCheckBoxMenuItem automaticaModeMenu = null;
	private static JCheckBoxMenuItem automaticaHelpMenu = null;

	/**
	 * Construct the tree from the configuration file
	 * 
	 * @return The tree model.
	 */
	static DefaultMutableTreeNode generateTree() {

		CategoryNode rootNodeCategory = new CategoryNode(
				Text.get(Text.TREE_ROOT_NODE),
				Text.get(Text.TREE_ROOT_NODE_TOOLTIP),
				SwingUtil.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "system-icon.png")); //$NON-NLS-1$

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
				rootNodeCategory);

		// A filter has to have a category definition first.
		DefaultMutableTreeNode categoryNode = null;

		Category categories[] = new Category[13];

		categories[0] = new Category(Text.CHARACTER_CATEGORY,
				Text.CHARACTER_CATEGORY_TOOLTIP, "general-font-icon.png", //$NON-NLS-1$
				new Filter[] { new ToUpperCaseFilter(),
						new ToLowerCaseFilter(),
						new FirstCharacterToUpperCaseFilter(),
						new ToDOSFilter(), new ToUNIXFilter(),
						new FromSpacesToTabFilter(),
						new FromTabToSpacesFilter(),
						new RemoveCharacterOnBeginningFilter(),
						new RemoveCharacterOnTheEndFilter(),
						new RemoveCharacterBeforePositionFilter(),
						new RemoveCharacterAfterPositionFilter(),
						new RemoveCharacterBetweenPositionsFilter(),
						new RemoveCharactersBetweenParametersFilter(),
						new RemoveCharactersOutsideParametersFilter(),
						new RemoveSpacesOnBeginningFilter(),
						new RemoveSpacesOnTheEndFilter(),
						new RemoveSpacesFromExtremitiesFilter(),
						new RemoveCharacterBeforeParameterFilter(),
						new RemoveCharacterAfterParameterFilter(),
						new LeaveOnlyNumbersFilter(),
						new CollapseSpacesFilter(),
						new KeepOnlyValidCharactersFilter(), new CopyFilter() });

		categories[1] = new Category(Text.LINE_CATEGORY,
				Text.LINE_CATEGORY_TOOLTIP, "txt-icon.png", new Filter[] { //$NON-NLS-1$
				new LineSimilaritiesFilter(),
						new AddAnchorOnBeginOfLineFilter(),
						new AddAnchorOnEndOfLineFilter(),
						new AddAnchorOnAPositionFilter(),
						new AddParameterBeforeParameterFilter(),
						new AddParameterAfterfParameterFilter(),
						new EnumerateLinesFilter(),
						new RemoveDuplicatedLinesFilter(),
						new EliminateAllDuplicatedLinesFilter(),
						new RemoveNonDuplicatedLinesFilter(),
						new RemoveEmptyLinesFilter(),
						new RemoveLinesWithContentFilter(),
						new RemoveLinesWithoutContentFilter(),
						new RemoveLinesWithLengthFilter(),
						new SimpleContentReplacementFilter(),
						new RegularExpressionReplacementFilter(),
						new ReplicateLineContentFilter(),
						new SortLinesAscendentFilter(),
						new SortLinesDescendentFilter(),
						new SortLinesAscendentBasedOnPositionFilter(),
						new SortLinesDescendentBasedOnPositionFilter(),
						new SortLinesAscendentByLengthFilter(),
						new InvertOrderFilter(), new ScrambleLinesFilter(),
						new CombineAllLinesFilter(),
						new CombineLinesAtARegularStepFilter(),
						new MaintainLinesAtRegularStepFilter(),
						new SwapContentBasedOnParameterFilter() });

		categories[2] = new Category(Text.DUAL_PANE_CATEGORY,
				Text.DUAL_PANE_CATEGORY_TOOLTIP,
				"dual-pane-group-icon-small.png", new Filter[] { //$NON-NLS-1$
				new RemoveLinesThatExistOnMainAndAuxiliar(),
						new RemoveLinesThatDoNotExistOnMainAndAuxiliar(),
						new CombineMainWithAuxiliar(),
						new IntercalateMainWithAuxiliar(),
						new MultipleInsertAuxiliarIntoMainFilter(),
						new ReplaceMultipleTexts(),
						new RemoveLinesWithMultipleParameters(),
						new RemoveLinesWithoutMultipleParameters(),
						new InsertAuxiliarIntoMain(),
						new InsertAuxiliarBetweenParameters() });

		categories[3] = new Category(Text.FREE_SELECTION_CATEGORY,
				Text.FREE_SELECTION_CATEGORY_TOOLTIP, "selection-icon.png", //$NON-NLS-1$
				new Filter[] { new CharacterSelectionFilter(),
						new WordSelectionFilter(), new CharacterMoveFilter(),
						new WordSelectionToTableFilter() });

		categories[4] = new Category(Text.BINARY_CATEGORY,
				Text.BINARY_CATEGORY_TOOLTIP, "BinaryCategory.png", //$NON-NLS-1$
				new Filter[] { new ReplaceAndRenameFilter(),
						new DeleteFilesFilter(), new AppendFilesFilter(),
						new CalculateMD5Filter(), new DuplicatedFilesFilter() });

		categories[5] = new Category(Text.ORTOGRAPHY_CATEGORY,
				Text.ORTOGRAPHY_CATEGORY_TOOLTIP, "check-mark-icon.png", //$NON-NLS-1$
				new Filter[] { new ShowDuplicatedConsecutiveWordsFilter(),
						new BreakLinesOnSpecifiedLengthFilter() });

		categories[6] = new Category(Text.SEARCH_CATEGORY,
				Text.SEARCH_CATEGORY_TOOLTIP, "search-icon.png", //$NON-NLS-1$
				new Filter[] { new SearchFileFilter() });

		categories[7] = new Category(Text.UTILITIES_CATEGORY,
				Text.UTILITIES_CATEGORY_TOOLTIP, "utilities-icon.png", //$NON-NLS-1$
				new Filter[] { new CalculatorFilter(), new CalendarFilter(),
						new RandomDataGeneratorFilter(),
						new TreeDirectoryFilter(), new FileListFilter(),
						new DeleteDirectoryFilter(),
						new KnapSackFileListerFilter(), new KnapSackFilter() });

		categories[8] = new Category(Text.JAVA_CATEGORY,
				Text.JAVA_CATEGORY_TOOLTIP, "java.png", new Filter[] { //$NON-NLS-1$
				new JavaKeyToConstantFilter(), new JavaToMethodOutputFilter(),
						new JavaBeanFilter(), new JspFormatterFilter(),
						new BreakStringIntoFieldsFilter(),
						new CombineFieldsIntoStringFilter(),
						new JSONFormatterFilter(),
						new XMLFormatterFilter(),
						new CharacterToUpperOrLowerCaseFilter() });

		categories[9] = new Category(Text.AWK_CATEGORY,
				Text.AWK_CATEGORY_TOOLTIP, "chip-icon.png", new Filter[] { //$NON-NLS-1$
				new AWK1Filter(), new AWK2Filter(), new AWK3Filter() });

		categories[10] = new Category(Text.TIMESHEET_CATEGORY,
				Text.TIMESHEET_CATEGORY_TOOLTIP, "time-icon.png", new Filter[] { //$NON-NLS-1$
				new StartTaskTimesheetFilter(), new StopTaskTimesheetFilter(),
						new CalculateTotalTimesheetFilter(),
						new FromAMPMto24hFilter(), new From24hToAMPMFilter(),
						new TimesheetTimeSeriesChartFilter(),
						new TimesheetTimeDistributionChartFilter() });

		categories[11] = new Category(Text.FUN_CATEGORY,
				Text.FUN_CATEGORY_TOOLTIP, "fun.png", new Filter[] { //$NON-NLS-1$
				new FigletFontFilter(), new TextWithNumbersFilter(),
						new TextWithErrorsFilter(), new MorseFilter() });

		categories[12] = new Category(Text.INTERNET_CATEGORY,
				Text.INTERNET_CATEGORY_TOOLTIP, "world-icon.png", new Filter[] { //$NON-NLS-1$
				new DownloadInternetBlockerFilter(), new SimpleAlertFilter() });

		for (Category category : categories) {

			if (FilterAnyConfiguration.isGeneralLevelUser()) {

				boolean hasGeneralLevelFiter = false;

				// Verify if there is at least one general user level filter.
				for (Filter filter : category.getFilters()) {

					if (filter.getFilterLevel() == FilterLevel.GENERAL_USER) {
						hasGeneralLevelFiter = true;
						break;
					}
				}

				// If there is no general user level category then skip
				// category.
				if (!hasGeneralLevelFiter) {
					continue;
				}

				CategoryNode node = new CategoryNode(
						Text.get(category.getCategoryName()),
						Text.get(category.getCategoryTooltip()),
						SwingUtil
								.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
										+ category.getIconName()));

				categoryNode = new DefaultMutableTreeNode(node);
				rootNode.add(categoryNode);

				for (Filter filter : category.getFilters()) {

					// Just add the general user level filters.
					if (filter.getFilterLevel() == FilterLevel.GENERAL_USER) {

						DefaultMutableTreeNode filterNode = new DefaultMutableTreeNode(
								filter);
						categoryNode.add(filterNode);
					}
				}

			} else {

				CategoryNode node = new CategoryNode(
						Text.get(category.getCategoryName()),
						Text.get(category.getCategoryTooltip()),
						SwingUtil
								.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
										+ category.getIconName()));

				categoryNode = new DefaultMutableTreeNode(node);
				rootNode.add(categoryNode);

				for (Filter filter : category.getFilters()) {

					DefaultMutableTreeNode filterNode = new DefaultMutableTreeNode(
							filter);

					categoryNode.add(filterNode);
				}
			}
		}

		return rootNode;
	}

	/**
	 * Filter the data to retrieve only the valid files and URLs.
	 * 
	 * @param text
	 *            The text string with on data on each line.
	 * @return The text filtered in file or url elements.
	 */
	static ArrayList<FileOrURLBean> generateTableModel(String text) {

		ArrayList<FileOrURLBean> rows = new ArrayList<>();

		// Initialize a new table content.
		rows = new ArrayList<>();

		// Follow each line of the text.
		LineTokenizer tokenizer = new LineTokenizer(new StringBuffer(text));

		String line = null;
		// Analyze each line.
		while (tokenizer.hasMoreTokens()) {

			line = tokenizer.nextToken();

			File file = new File(line);

			// Import a file if possible.
			if (file.isFile()) {

				if (file.exists()) {

					String fileName = file.getAbsolutePath();
					long size = file.length();

					FileOrURLBean row = new FileOrURLBean(fileName,
							FileOrURLBean.FileOrURLBeanType.FILE, size,
							FileOrURLBean.FileOrURLBeanStatus.OK);
					rows.add(row);

					continue;

				}

				FileOrURLBean row = new FileOrURLBean(line,
						FileOrURLBean.FileOrURLBeanType.FILE, -1,
						FileOrURLBean.FileOrURLBeanStatus.INVALID);
				rows.add(row);

			} else {

				try {
					new URL(line);
				} catch (MalformedURLException exception) {

					FileOrURLBean row = new FileOrURLBean(line,
							FileOrURLBean.FileOrURLBeanType.TEXT, -1,
							FileOrURLBean.FileOrURLBeanStatus.INVALID);
					rows.add(row);

					continue;
				}

				FileOrURLBean row = new FileOrURLBean(line,
						FileOrURLBean.FileOrURLBeanType.URL, -1,
						FileOrURLBean.FileOrURLBeanStatus.NOT_VERIFIED);
				rows.add(row);

				continue;

			}
		}

		return rows;
	}

	/**
	 * Indicate if the current filter implements a specific behavior.
	 * 
	 * @param specialBehavior
	 *            The special behavior object.
	 * @param behavior
	 *            The behavior.
	 * @return true if it implements the behavior.
	 */
	static boolean hasSpecialBehavior(SpecialBehavior specialBehavior,
			SpecialBehavior.Behavior behavior) {

		for (SpecialBehavior.Behavior filterBehavior : specialBehavior
				.getSpecialBehavior()) {
			if (filterBehavior == behavior) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Replace the following strings by a special characters:
	 * 
	 * !nl! by new line !t! by tab
	 * 
	 * @param text
	 *            The text to be verified.
	 * @return The text with the strings replaced by the special characters.
	 */
	public static String replaceSpecialCharacters(String text) {

		String newText = StringSupport.replace(
				text,
				"!nl!", FilterAnyConfiguration //$NON-NLS-1$
						.isGenerateTextInDOSFormat() ? StringSupport
						.getDOSNewLine() : StringSupport.getUnixNewLine());

		newText = StringSupport.replace(newText, "!t!", "\t"); //$NON-NLS-1$ //$NON-NLS-2$

		return newText;
	}

	/**
	 * Filter only the rows that can be considered in a filter.
	 * 
	 * @param rows
	 *            The available rows.
	 * @return The filtered rows with only the valid and not verified.
	 */
	static ArrayList<FileOrURLBean> filterValidRows(
			ArrayList<FileOrURLBean> rows) {
		ArrayList<FileOrURLBean> filteredRows = new ArrayList<>();

		for (FileOrURLBean row : rows) {
			if (row.getCurrentStatus() == FileOrURLBean.FileOrURLBeanStatus.OK
					|| row.getCurrentStatus() == FileOrURLBean.FileOrURLBeanStatus.NOT_VERIFIED) {
				filteredRows.add(row);
			}
		}

		return filteredRows;
	}

	/**
	 * Filter only the rows that can be considered in a filter.
	 * 
	 * @param targetDirectoryField
	 *            Target directory where the files will be written.
	 * @param rows
	 *            The available rows.
	 * @return The filtered rows with only the valid and not verified.
	 */
	static ArrayList<FileOrURLBean> setNewTargets(String targetDirectoryField,
			ArrayList<FileOrURLBean> rows) {

		// Identify the last common directory.
		String commonDirectory = null;
		for (FileOrURLBean row : rows) {

			if (row.getType() == FileOrURLBean.FileOrURLBeanType.FILE) {

				// If the common directory is not set than set the
				// current directory target to be removed.
				if (commonDirectory == null) {
					commonDirectory = FileSupport.getOnlyPath(row.getTarget());
					continue;
				}

				commonDirectory = FileSupport.getCommonPrefixDirectory(
						commonDirectory, row.getTarget());
			}
		}

		ArrayList<FileOrURLBean> filteredRows = new ArrayList<>();

		// Only set the path if the target is a valid path.
		String path = null;
		if (targetDirectoryField != null && !"".equals(targetDirectoryField)) { //$NON-NLS-1$

			File targetPath = new File(targetDirectoryField);

			// If target is a valid path.
			if (targetPath.isDirectory()) {

				path = targetPath.getAbsolutePath();

				path = FileSupport.addDirectorySymbolAtTheEnd(path);
			}
		}

		commonDirectory = commonDirectory == null ? new String()
				: commonDirectory;

		for (FileOrURLBean row : rows) {

			String target = row.getTarget();

			if (row.getType() == FileOrURLBean.FileOrURLBeanType.FILE) {

				// Append the path if it is a valid target path.
				if (path == null) {
					row.setNewTarget(target);
				} else {
					String newTarget = null;

					if (target.indexOf(path) != -1) {

						newTarget = target.substring(path.length(),
								target.length());
					} else {

						newTarget = target.substring(commonDirectory.length(),
								target.length());
					}

					row.setNewTarget(path + newTarget);
				}
				filteredRows.add(row);

			}
			if (row.getType() == FileOrURLBean.FileOrURLBeanType.URL) {

				row.setNewTarget(path
						+ StringSupport.getConvertNonFileCharacters(target));
				filteredRows.add(row);

			}
		}

		return filteredRows;
	}

	/**
	 * Inform the distribution of the file or URLs on a table.
	 * 
	 * @param beans
	 *            The list of files or URLs.
	 * @return The text to be presented to the user.
	 */
	static String getFileOrURLDistributionText(ArrayList<FileOrURLBean> beans) {

		int files = 0, url = 0, text = 0, ok = 0, invalid = 0, notVerified = 0;

		for (FileOrURLBean bean : beans) {

			if (bean.getType() == FileOrURLBean.FileOrURLBeanType.FILE) {
				files++;
			}
			if (bean.getType() == FileOrURLBean.FileOrURLBeanType.URL) {
				url++;
			}
			if (bean.getType() == FileOrURLBean.FileOrURLBeanType.TEXT) {
				text++;
			}
			if (bean.getCurrentStatus() == FileOrURLBean.FileOrURLBeanStatus.OK) {
				ok++;
			}
			if (bean.getCurrentStatus() == FileOrURLBean.FileOrURLBeanStatus.INVALID) {
				invalid++;
			}
			if (bean.getCurrentStatus() == FileOrURLBean.FileOrURLBeanStatus.NOT_VERIFIED) {
				notVerified++;
			}
		}

		return Text.get(Text.FILE_OR_URL_LABEL, String.valueOf(files),
				String.valueOf(url), String.valueOf(text), String.valueOf(ok),
				String.valueOf(invalid), String.valueOf(notVerified));
	}

	/**
	 * Create the application menu bar.
	 * 
	 * @return The menu bar.
	 */
	static JMenuBar getMainMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu(Event.getFileAction());
		fileMenu.add(new JMenuItem(Event.getToolbarSaveTopAsAction()));
		fileMenu.add(new JMenuItem(Event.getToolbarSaveAuxiliarAsAction()));
		fileMenu.add(new JMenuItem(Event.getToolbarSaveBottomAsAction()));
		fileMenu.add(new JMenuItem(Event.getExitAction()));

		JMenu findMenu = new JMenu(Event.getFindAction());
		findMenu.add(new JMenuItem(Event.getTextAreaFindAndReplaceMainAction()));
		findMenu.add(new JMenuItem(Event
				.getTextAreaFindAndReplaceOutputAction()));
		findMenu.add(new JMenuItem(Event
				.getTextAreaFindAndReplaceAuxiliarAction()));

		JMenu compareMenu = new JMenu(Event.getCompareAction());
		compareMenu.add(new JMenuItem(Event.getCompareMainWithOutputAction()));
		compareMenu
				.add(new JMenuItem(Event.getCompareMainWithAuxiliarAction()));
		compareMenu.add(new JMenuItem(Event
				.getCompareAuxiliarWithOutputAction()));

		JMenu viewMenu = new JMenu(Event.getViewAction());
		viewMenu.add(new JMenuItem(Event.getScrapBookAction()));
		viewMenu.add(new JMenuItem(Event.getTaskManagerAction()));
		viewMenu.add(new JMenuItem(Event.getNetworkAction()));
		viewMenu.add(new JMenuItem(Event.getCommandLineGeneratorAction()));

		JMenu optionMenu = new JMenu(Event.getOptionAction());

		if (FilterAnyConfiguration.isTextMode()) {
			textOrTableModeMenu = new JMenuItem(
					Event.getToolbarSwitchBetweenTextAndFile());
		} else {
			textOrTableModeMenu = new JMenuItem(
					Event.getToolbarSwitchBetweenTextAndFileTurnedOn());
		}

		optionMenu.add(textOrTableModeMenu);

		// Show different icons depending on the automatic mode.
		if (FilterAnyConfiguration.isAutomaticMode()) {
			automaticaModeMenu = new JCheckBoxMenuItem(
					Event.getToolBarAutomaticModeActionTurnedOn());
			automaticaModeMenu.setSelected(true);
		} else {
			automaticaModeMenu = new JCheckBoxMenuItem(
					Event.getToolBarAutomaticModeActionTurnedOff());
			automaticaModeMenu.setSelected(false);
		}

		optionMenu.add(automaticaModeMenu);

		if (FilterAnyConfiguration.isAutomaticHelp()) {
			automaticaHelpMenu = new JCheckBoxMenuItem(
					Event.getAutomaticHelpModeActionTurnedOn());
			automaticaHelpMenu.setSelected(true);
		} else {
			automaticaHelpMenu = new JCheckBoxMenuItem(
					Event.getAutomaticHelpModeActionTurnedOff());
			automaticaHelpMenu.setSelected(false);
		}

		optionMenu.add(automaticaHelpMenu);

		optionMenu.add(new JMenuItem(Event.getConfigurationAction()));

		JMenu helpMenu = new JMenu(Event.getHelpAction());

		helpMenu.add(new JMenuItem(Event.getContentAction()));
		helpMenu.add(new JMenuItem(Event.getShortCutAction()));

		if (!FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			helpMenu.add(new JMenuItem(Event.getLicenseAction()));
		}
		helpMenu.add(new JMenuItem(Event.getAboutAction()));

		menuBar.add(fileMenu);
		menuBar.add(findMenu);
		menuBar.add(compareMenu);
		menuBar.add(viewMenu);

		if (FilterAnyConfiguration.isUseInternalWindow()) {

			JMenu positionMenu = new JMenu(Event.getPositionAction());

			positionMenu.add(new JMenuItem(Event.getDefaultPositionAction()));
			positionMenu.add(new JMenuItem(Event.getVerticalAreasAction()));
			positionMenu
					.add(new JMenuItem(Event.getControlOnTheMiddleAction()));
			positionMenu.add(new JMenuItem(Event
					.getControlInTheRightVerticalAction()));
			positionMenu.add(new JMenuItem(Event.getControlInTheRightAction()));

			menuBar.add(positionMenu);
		}

		menuBar.add(optionMenu);

		menuBar.add(helpMenu);

		return menuBar;
	}

	/**
	 * Turn text mode on.
	 */
	public static void turnTextMode() {

		textOrTableModeMenu.setAction(Event
				.getToolbarSwitchBetweenTextAndFile());

	}

	/**
	 * Turn file mode on.
	 */
	public static void turnFileMode() {

		textOrTableModeMenu.setAction(Event
				.getToolbarSwitchBetweenTextAndFileTurnedOn());

	}

	/**
	 * Turn automatic help on.
	 */
	public static void turnAutomaticHelpOn() {

		automaticaHelpMenu
				.setAction(Event.getAutomaticHelpModeActionTurnedOn());
		automaticaHelpMenu.setSelected(true);

	}

	/**
	 * Turn automatic help off.
	 */
	public static void turnAutomaticHelpOff() {

		automaticaHelpMenu.setAction(Event
				.getAutomaticHelpModeActionTurnedOff());
		automaticaHelpMenu.setSelected(false);
	}

	/**
	 * Turn automatic mode on.
	 */
	public static void turnAutomaticModeOn() {

		automaticaModeMenu.setAction(Event
				.getToolBarAutomaticModeActionTurnedOn());
		automaticaModeMenu.setSelected(true);

	}

	/**
	 * Turn automatic mode off.
	 */
	public static void turnAutomaticModeOff() {

		automaticaModeMenu.setAction(Event
				.getToolBarAutomaticModeActionTurnedOff());
		automaticaModeMenu.setSelected(false);
	}

}
