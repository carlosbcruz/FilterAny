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
package com.carlosbcruz.filterany.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterException;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.Report;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.TaskStatus;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.TextBridgeFactory;
import com.carlosbcruz.filterany.TextBridgeFactory.TextBridge.TextInterfaceBean;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Generate a file list from file system.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class KnapSackFileListerFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int divider = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_KNAPSACKFILELIST_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_KNAPSACKFILELIST_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_KNAPSACKFILELIST_INSTRUCTIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getFilterLevel()
	 */
	@Override
	public FilterLevel getFilterLevel() {

		return FilterLevel.ADVANCED_TECHNICAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsRequired()
	 */
	@Override
	public FilterControls[] getControls() {

		return new FilterControls[] { FilterControls.TARGET_DIRECTORY_1,
				FilterControls.CHECK_BOX_1, FilterControls.INPUT_FIELD_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.DIRECTORY,
				FilterType.NOT_RELEVANT, FilterType.NUMBER_GENERIC };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
				FilterParameter.REQUIRED, FilterParameter.OPTIONAL };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {
				Text.get(Text.FILTER_KNAPSACKFILELIST_DIRECTORY1),
				Text.get(Text.FILTER_KNAPSACKFILELIST_CHECK_BOX_1),
				Text.get(Text.FILTER_KNAPSACKFILELIST_DIVIDER_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_KNAPSACKFILELIST_DIRECTORY1_TOOLTIP),
				Text.get(Text.FILTER_KNAPSACKFILELIST_CHECK_BOX_1_TOOLTIP),
				Text.get(Text.FILTER_KNAPSACKFILELIST_DIVIDER_1_TOOLTIP) };
	}

	/**
	 * Calculate the total size of a list of files with size followed by the
	 * file name.
	 * 
	 * @param text
	 *            The text with a specific format.
	 * @return The total size.
	 */
	private static long calculateSize(StringBuffer text) {

		long totalSize = 0;
		try {
			LineTokenizer tokenizer = new LineTokenizer(text);

			while (tokenizer.hasMoreTokens()) {

				String line = tokenizer.nextToken().trim();

				if (line.length() > 0) {

					StringTokenizer sizeTokenizer = new StringTokenizer(line);

					totalSize += Integer.parseInt(sizeTokenizer.nextToken());
				}
			}
		} catch (RuntimeException e) {

			return 0;
		}
		return totalSize;
	}

	/**
	 * Create the file list.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class FileListThread implements Runnable {

		File file = null;

		long totalFilesFound = 0, totalDirectoriesFound = 0;

		private StringBuffer output = new StringBuffer();

		/**
		 * Constructor.
		 * 
		 * @param fileParameter
		 *            The file indicating the directory from which the list will
		 *            be generated.
		 */
		FileListThread(File fileParameter) {

			this.file = fileParameter;
		}

		/**
		 * Generate a file list from file system.
		 * 
		 * @param temporary
		 *            The temporary text output.
		 * @param textInterfaceShare
		 *            Task if it is running from a graphical interface.
		 * @param directory
		 *            The directory to generate the file list.
		 * @param active
		 *            Indicate the files that are active.
		 * @param last
		 *            Indicate if the current directory is the last one or not.
		 */
		@SuppressWarnings("synthetic-access")
		void retrieveFilesList(StringBuffer temporary,
				TextInterfaceBean textInterfaceShare, File directory,
				boolean[] active, boolean last) {

			if (textInterfaceShare != null) {

				if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
					textInterfaceShare.setStatus(TaskStatus.CANCELED);
					return;
				}

				textInterfaceShare.setProgress(Text.get(
						Text.FILTER_KNAPSACKFILELIST_REPORT_TASK_PROGRESS,
						String.valueOf(this.totalFilesFound),
						String.valueOf(this.totalDirectoriesFound)));
			}

			// Retrieve the files from the current directory.
			File files[] = directory.listFiles();

			// Return if no files.
			if (files == null) {
				return;
			}

			// Calculate the number of directories.
			// See if there are files and also calculate
			// the total space used by them.
			boolean hasFiles = false;
			int numberOfDirectories = 0;

			for (int i = 0; i < files.length; i++) {

				if (files[i].isDirectory()) {

					numberOfDirectories++;

				} else {

					hasFiles = true;

				}
			}

			// If it has files than write the directory name.
			if (hasFiles) {

				this.totalDirectoriesFound++;
			}

			// Copy the directories to a specific list and
			// generate the file list.
			File directories[] = new File[numberOfDirectories];
			int position = 0;

			for (int i = 0; i < files.length; i++) {

				if (files[i].isDirectory()) {

					directories[position] = files[i];

					position++;

				} else {

					long fileSize = files[i].length();
					if (KnapSackFileListerFilter.this.divider > 1) {
						fileSize /= KnapSackFileListerFilter.this.divider;
					}

					temporary.append(String.valueOf(fileSize + " \"" //$NON-NLS-1$
							+ files[i].getAbsoluteFile() + "\"" //$NON-NLS-1$
							+ getNewLine()));

					this.totalFilesFound++;
				}
			}

			// Bring the previous list of active directories.
			boolean newActive[] = new boolean[active.length + 1];
			for (int i = 0; i < active.length; i++) {
				newActive[i] = active[i];
			}
			newActive[active.length] = true;

			// Generate the list of internal directories recursively.
			for (int i = 0; i < directories.length; i++) {

				if (i == directories.length - 1) {

					newActive[active.length] = false;

					retrieveFilesList(temporary, textInterfaceShare,
							directories[i], newActive, true);

				} else {

					retrieveFilesList(temporary, textInterfaceShare,
							directories[i], newActive, false);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public void run() {

			TextBridgeFactory.TextBridge bridge = TextBridgeFactory
					.getInstance();

			TextInterfaceBean textInterfaceShare = bridge
					.createNewInterfaceText();

			textInterfaceShare.setStatus(TaskStatus.RUNNING);

			textInterfaceShare.setSourceFilter(Text
					.get(Text.FILTER_KNAPSACKFILELIST_LABEL));

			if (isCheckBox1()) {

				StringBuffer temporary = new StringBuffer();

				retrieveFilesList(temporary, textInterfaceShare, this.file,
						new boolean[] {}, false);

				this.output.append(temporary);

			} else {

				// Retrieve the files from the current directory.
				File files[] = this.file.listFiles();

				// Return if no files.
				if (files == null) {
					return;
				}

				List<File> directories = new ArrayList<>();
				List<File> fileList = new ArrayList<>();
				int numberOfDirectories = 0;
				for (int i = 0; i < files.length; i++) {

					if (files[i].isDirectory()) {

						directories.add(files[i]);
						numberOfDirectories++;

					} else {
						fileList.add(files[i]);
					}
				}

				if (numberOfDirectories == 0) {
					return;
				}

				StringBuffer directoriesText[] = new StringBuffer[directories
						.size()];
				long sizes[] = new long[directories.size()];
				StringBuffer temporaryOutput = new StringBuffer();
				for (int i = 0; i < numberOfDirectories; i++) {

					directoriesText[i] = new StringBuffer();
					retrieveFilesList(directoriesText[i], textInterfaceShare,
							directories.get(i), new boolean[] {}, false);
					sizes[i] = calculateSize(directoriesText[i]);

					temporaryOutput.append(sizes[i] + " \"" //$NON-NLS-1$
							+ directories.get(i).getAbsolutePath() + "\"" //$NON-NLS-1$
							+ getNewLine());
				}

				this.output.append(temporaryOutput);

				for (int i = 0; i < fileList.size(); i++) {

					long fileSize = fileList.get(i).length();

					if (KnapSackFileListerFilter.this.divider > 1) {
						fileSize /= KnapSackFileListerFilter.this.divider;
					}

					this.output.append(String.valueOf(fileSize + " \"" //$NON-NLS-1$
							+ fileList.get(i).getAbsoluteFile() + "\"" //$NON-NLS-1$
							+ getNewLine()));

				}

			}

			textInterfaceShare.setContent(this.output);

			if (textInterfaceShare.getStatus() == TaskStatus.RUNNING) {
				textInterfaceShare.setStatus(TaskStatus.COMPLETE);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@SuppressWarnings("synthetic-access")
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText)
			throws FilterException {

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		// Get the target directory
		String target = getDirectory1();

		// Guarantee that the target was provided.
		if (target == null || "".equals(target)) { //$NON-NLS-1$
			throw new FilterException(Text.get(
					Text.FILTER_KNAPSACKFILELIST_EXCEPTION_1, target));
		}

		this.divider = 0;
		String dividerText = getField1();
		try {
			this.divider = Integer.parseInt(dividerText);
		} catch (NumberFormatException e) {
			this.divider = 0;
		}

		File file = new File(target);

		StringBuffer newOutput = new StringBuffer();

		if (!file.isDirectory()) {
			throw new FilterException(Text.get(
					Text.FILTER_KNAPSACKFILELIST_EXCEPTION_1, target));
		}

		if (FilterAnyConfiguration.isRunningInGraphicalMode()) {

			Thread fileListThread = new Thread(new FileListThread(file));
			fileListThread.start();

		} else {

			FileListThread runInThisThread = new FileListThread(null);

			runInThisThread.retrieveFilesList(runInThisThread.output, null,
					file, new boolean[] {}, false);

			newOutput = new StringBuffer(runInThisThread.output);
		}

		return newOutput;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_KNAPSACKFILELIST_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getAuxiliarInputExample()
	 */
	@Override
	public String getAuxiliarInputExample() {

		return new String();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getMainInputExample()
	 */
	@Override
	public String getMainInputExample() {

		return Text.get(Text.FILTER_KNAPSACKFILELIST_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_KNAPSACKFILELIST_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "KnapF"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new SpecialBehavior.Behavior[] {
				SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#isRunInBackground()
	 */
	@Override
	public boolean isRunInBackground() {

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_KNAPSACKFILELIST_REPORT);
	}
}