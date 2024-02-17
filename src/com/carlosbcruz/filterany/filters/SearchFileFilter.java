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
import com.carlosbcruz.util.FileSupport;

/**
 * Search for files to work with FilterAny
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class SearchFileFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int filesFound = 0;

	private boolean background = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_SEARCHFILES_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_SEARCHFILES_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_SEARCHFILES_INSTRUCTIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getFilterLevel()
	 */
	@Override
	public FilterLevel getFilterLevel() {

		return FilterLevel.GENERAL_USER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsRequired()
	 */
	@Override
	public FilterControls[] getControls() {

		return new FilterControls[] {
				FilterControls.TARGET_FILE_OR_DIRECTORY_1,
				FilterControls.INPUT_FIELD_1, FilterControls.CHECK_BOX_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.DIRECTORY, FilterType.ANYTHING,
				FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
				FilterParameter.OPTIONAL, FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text.get(Text.TARGET_FILE_OR_DIRECTORY_LABEL),
				Text.get(Text.FILTER_SEARCHFILES_FIELD1),
				Text.get(Text.FILTER_SEARCHFILES_CHECKBOX1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.TARGET_FILE_OR_DIRECTORY_LABEL_TOOLTIP),
				Text.get(Text.FILTER_SEARCHFILES_FIELD1_TOOLTIP),
				Text.get(Text.FILTER_SEARCHFILES_CHECKBOX1_TOOLTIP) };
	}

	/**
	 * Provide a list of extensions.
	 * 
	 * @param content
	 *            A list of extension separeted by comma.
	 * @return the extensions on an array.
	 */
	private static String[] getExtensions(String content) {

		StringTokenizer tokeninzer = new StringTokenizer(content.trim(), ","); //$NON-NLS-1$

		ArrayList<String> extensions = new ArrayList<>();
		while (tokeninzer.hasMoreElements()) {

			String extension = tokeninzer.nextToken();
			extensions.add(extension);
		}

		String retrievedExtensions[] = new String[extensions.size()];
		retrievedExtensions = extensions.toArray(retrievedExtensions);

		return retrievedExtensions;
	}

	/**
	 * Create the file list from subdirectories.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class FileOnSubFolderThread implements Runnable {

		String directory = null;
		String suffixes[] = null;

		int totalFilesFound = 0;

		/**
		 * Constructor.
		 * 
		 * @param directoryParameter
		 *            The target directory.
		 * @param suffixesParameter
		 *            The allowed suffixes.
		 */
		FileOnSubFolderThread(String directoryParameter,
				String suffixesParameter[]) {

			this.directory = directoryParameter;
			this.suffixes = suffixesParameter;
		}

		/**
		 * Provide the file list on all sub-directories of a specific directory.
		 * 
		 * @param textInterfaceShare
		 *            Task if it is running from a graphical interface.
		 * @param file
		 *            The file or directory.
		 * @param files
		 *            The array to store the files.
		 * @param sufixes
		 *            The suffixes to be include. If it is null than include all
		 *            file types.
		 * @return The array with all the files on the sub-directories.
		 */
		ArrayList<String> retrieveDirectories(
				TextInterfaceBean textInterfaceShare, File file[],
				ArrayList<String> files, String suffixesParameter[]) {

			if (file == null) {
				return new ArrayList<>();
			}

			for (int i = 0; i < file.length; i++) {

				if (textInterfaceShare != null) {

					if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
						textInterfaceShare.setStatus(TaskStatus.CANCELED);
						return new ArrayList<>();
					}

					textInterfaceShare.setProgress(Text.get(
							Text.FILTER_SEARCHFILES_REPORT_PROGRESS,
							String.valueOf(this.totalFilesFound)));

				}

				if (file[i].isDirectory()) {

					retrieveDirectories(textInterfaceShare,
							file[i].listFiles(), files, suffixesParameter);

				} else {

					// Verify the suffix.
					String fileName = file[i].getAbsoluteFile().toString();

					// If not suffix is provided than add all files.
					if (suffixesParameter == null
							|| "".equals(suffixesParameter)) { //$NON-NLS-1$

						files.add(fileName);
						this.totalFilesFound++;

					} else {

						if (suffixesParameter.length == 0) {

							files.add(fileName);
							this.totalFilesFound++;

						} else {

							for (String suffix : suffixesParameter) {

								if (fileName.toUpperCase().endsWith(
										suffix.toUpperCase())) {

									files.add(fileName);
									this.totalFilesFound++;
								}
							}
						}
					}
				}
			}

			return files;
		}

		/**
		 * Provide the file list on all sub-directories of a specific directory.
		 * 
		 * @param textInterfaceShare
		 *            Task if it is running from a graphical interface.
		 * @param directoryParameter
		 *            The directory to be searched.
		 * @param sufixes
		 *            The suffixes to be include. If it is null than include all
		 *            file types.
		 * @return The array with all the files on the sub-directories.
		 */
		String[] getFileOnSubfolder(TextInterfaceBean textInterfaceShare,
				String directoryParameter, String suffixesParameter[]) {

			ArrayList<String> files = new ArrayList<>();
			files = retrieveDirectories(textInterfaceShare,
					new File[] { new File(directoryParameter) }, files,
					suffixesParameter);

			String[] returnFiles = new String[files.size()];
			return files.toArray(returnFiles);
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
					.get(Text.FILTER_SEARCHFILES_LABEL));

			String[] files = getFileOnSubfolder(textInterfaceShare,
					this.directory, this.suffixes);

			StringBuffer output = new StringBuffer();
			if (files != null) {
				for (String line : files) {
					output.append(line + getNewLine());
				}
			}

			textInterfaceShare.setContent(output);

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
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText)
			throws FilterException {

		this.filesFound = 0;

		// Get the target directory or file.
		String target = getTargetFileOrDirectory1();

		// Guarantee that the target was provided.
		if (target == null || "".equals(target)) { //$NON-NLS-1$
			throw new FilterException(
					Text.get(Text.FILTER_SEARCHFILES_EXCEPTION_1));
		}

		File file = new File(target);

		this.background = false;

		// Verify if the the target is a file.
		if (file.isFile() && file.exists()) {

			// Append the file.
			text.append(file.getAbsolutePath() + getNewLine());
			this.filesFound++;

		} else {

			// Verify if it is a directory.
			if (file.isDirectory() && file.exists()) {

				String[] files = null;
				// If it has to search the sub-directories.
				if (isCheckBox1()) {

					if (FilterAnyConfiguration.isRunningInGraphicalMode()) {

						Thread fileListThread = new Thread(
								new FileOnSubFolderThread(
										file.getAbsolutePath(),
										getExtensions(getField1())));
						fileListThread.start();

						this.background = true;

						return new StringBuffer();

					}

					FileOnSubFolderThread runInThisThread = new FileOnSubFolderThread(
							null, null);
					// Search recursively on all sub-directories
					files = runInThisThread.getFileOnSubfolder(null,
							file.getAbsolutePath(), getExtensions(getField1()));

				} else {

					// Do a simple search only on the provided directory.
					files = FileSupport.getFilesFromDirectory(
							file.getAbsolutePath(), getExtensions(getField1()));
				}

				// Filter the files by the requested suffix.
				for (String fileName : files) {

					text.append(fileName + getNewLine());
					this.filesFound++;
				}

			} else {

				// It is an invalid file. Throw an exception.
				throw new FilterException(
						Text.get(Text.FILTER_SEARCHFILES_EXCEPTION_2));
			}
		}

		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_SEARCHFILES_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Srch"; //$NON-NLS-1$
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

		return Text.get(Text.FILTER_SEARCHFILES_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_SEARCHFILES_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new SpecialBehavior.Behavior[] { SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#isRunInBackground()
	 */
	@Override
	public boolean isRunInBackground() {

		return this.background;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_SEARCHFILES_REPORT,
				String.valueOf(this.filesFound));
	}
}
