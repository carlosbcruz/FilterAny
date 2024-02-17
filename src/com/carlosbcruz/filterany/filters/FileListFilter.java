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
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.StringSupport;

/**
 * Generate a file list from file system.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FileListFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_FILELIST_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_FILELIST_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_FILELIST_INSTRUCTIONS);
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

		return new FilterControls[] { FilterControls.TARGET_DIRECTORY_1,
				FilterControls.CHECK_BOX_1, FilterControls.CHECK_BOX_2 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.DIRECTORY,
				FilterType.NOT_RELEVANT, FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
				FilterParameter.REQUIRED, FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text.get(Text.FILTER_FILELIST_DIRECTORY1),
				Text.get(Text.FILTER_FILELIST_CHECK_BOX_1),
				Text.get(Text.FILTER_FILELIST_CHECK_BOX_2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_FILELIST_DIRECTORY1_TOOLTIP),
				Text.get(Text.FILTER_FILELIST_CHECK_BOX_1_TOOLTIP),
				Text.get(Text.FILTER_FILELIST_CHECK_BOX_2_TOOLTIP) };
	}

	/**
	 * Create the file list.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class FileListThread implements Runnable {

		File file = null;

		int totalFilesFound = 0, totalDirectoriesFound = 0;

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
		void retrieveFilesList(TextInterfaceBean textInterfaceShare,
				File directory, boolean[] active, boolean last) {

			if (textInterfaceShare != null) {

				if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
					textInterfaceShare.setStatus(TaskStatus.CANCELED);
					return;
				}

				textInterfaceShare.setProgress(Text.get(
						Text.FILTER_FILELIST_REPORT_TASK_PROGRESS,
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
			long maximumSize = 0;

			for (int i = 0; i < files.length; i++) {

				if (files[i].isDirectory()) {

					numberOfDirectories++;

				} else {

					hasFiles = true;

					if (files[i].length() > maximumSize) {

						maximumSize = files[i].length();
					}
				}
			}

			// If it has files than write the directory name.
			if (hasFiles) {

				this.output.append(getNewLine() + directory.getAbsolutePath()
						+ getNewLine());
				this.totalDirectoriesFound++;
			}

			// Copy the directories to a specific list and
			// generate the file list.
			File directories[] = new File[numberOfDirectories];
			int position = 0;

			int maximumSizeString = isCheckBox1() ? String.valueOf(
					"(" + maximumSize + " = " //$NON-NLS-1$ //$NON-NLS-2$
							+ StringSupport.getUnit(maximumSize) + ")") //$NON-NLS-1$
					.length() : String.valueOf(maximumSize).length();

			for (int i = 0; i < files.length; i++) {

				if (files[i].isDirectory()) {

					directories[position] = files[i];

					position++;

				} else {

					String ajudstedSize = StringSupport.adjustSizeRight(
							isCheckBox1() ? String.valueOf("(" //$NON-NLS-1$
									+ files[i].length()
									+ " = " //$NON-NLS-1$
									+ StringSupport.getUnit(files[i].length())
									+ ")") : String.valueOf(files[i].length()), //$NON-NLS-1$
							maximumSizeString);

					this.output.append("     " + ajudstedSize + " " //$NON-NLS-1$ //$NON-NLS-2$
							+ files[i].getName() + getNewLine());

					if (isCheckBox2()) {

						if (files[i].getAbsolutePath().length() > 3
								&& files[i].getAbsolutePath().toLowerCase()
										.endsWith("zip")) { //$NON-NLS-1$

							this.output.append(getNewLine());
							try (ZipFile zipFile = new ZipFile(
									files[i].getAbsolutePath())) {

								Enumeration<? extends ZipEntry> zipEntries = zipFile
										.entries();
								while (zipEntries.hasMoreElements()) {

									if (textInterfaceShare != null) {

										if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
											textInterfaceShare
													.setStatus(TaskStatus.CANCELED);
											return;
										}

										textInterfaceShare
												.setProgress(Text
														.get(Text.FILTER_FILELIST_REPORT_TASK_PROGRESS,
																String.valueOf(this.totalFilesFound),
																String.valueOf(this.totalDirectoriesFound)));
									}

									ZipEntry entry = zipEntries.nextElement();

									String zipAjudstedSize = null;
									if (entry.isDirectory()) {
										zipAjudstedSize = StringSupport
												.adjustSizeRight("", //$NON-NLS-1$
														maximumSizeString);
									} else {
										zipAjudstedSize = StringSupport
												.adjustSizeRight(
														isCheckBox1() ? String.valueOf("(" //$NON-NLS-1$
																+ entry.getSize()
																+ " = " //$NON-NLS-1$
																+ StringSupport
																		.getUnit(entry
																				.getSize())
																+ ")") : String.valueOf(entry.getSize()), //$NON-NLS-1$
														maximumSizeString);
									}

									this.output
											.append("          " + zipAjudstedSize + " " //$NON-NLS-1$ //$NON-NLS-2$
													+ entry.getName()
													+ getNewLine());

									this.totalFilesFound++;
								}

								zipFile.close();

							} catch (Throwable exception) {

								ExceptionSupport
										.handleException(
												exception,
												Text.get(Text.THROWABLE_EXCEPTION_COMPRESS_MESSAGE),
												Text.get(Text.THROWABLE_EXCEPTION_COMPRESS_MESSAGE_COMPLEMENT));
							}

							this.output.append(getNewLine());
						}

						if (files[i].getAbsolutePath().length() > 3
								&& files[i].getAbsolutePath().toLowerCase()
										.endsWith("arj")) { //$NON-NLS-1$
							ExceptionSupport.handleException("ARJ file found. Unarj only on full version. Arquivo ARJ encontrado. Unarj somente na versão completa.");

						}

						if (files[i].getAbsolutePath().length() > 3
								&& files[i].getAbsolutePath().toLowerCase()
										.endsWith("rar")) { //$NON-NLS-1$
							ExceptionSupport.handleException("RAR file found. Unrar only on full version. Arquivo ARJ encontrado. Unarj somente na versão completa.");
						}

						this.output.append(getNewLine());
					}

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

					retrieveFilesList(textInterfaceShare, directories[i],
							newActive, true);

				} else {

					retrieveFilesList(textInterfaceShare, directories[i],
							newActive, false);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {

			TextBridgeFactory.TextBridge bridge = TextBridgeFactory
					.getInstance();

			TextInterfaceBean textInterfaceShare = bridge
					.createNewInterfaceText();

			textInterfaceShare.setStatus(TaskStatus.RUNNING);

			textInterfaceShare.setSourceFilter(Text
					.get(Text.FILTER_FILELIST_LABEL));

			retrieveFilesList(textInterfaceShare, this.file, new boolean[] {},
					false);

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
					Text.FILTER_FILELIST_EXCEPTION_1, target));
		}

		File file = new File(target);

		StringBuffer newOutput = new StringBuffer();

		if (!file.isDirectory()) {
			throw new FilterException(Text.get(
					Text.FILTER_FILELIST_EXCEPTION_1, target));
		}

		if (FilterAnyConfiguration.isRunningInGraphicalMode()) {

			Thread fileListThread = new Thread(new FileListThread(file));
			fileListThread.start();

		} else {

			FileListThread runInThisThread = new FileListThread(null);

			runInThisThread.retrieveFilesList(null, file, new boolean[] {},
					false);

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

		return Text.get(Text.FILTER_FILELIST_COMMAND_LINE_HELP);
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

		return Text.get(Text.FILTER_FILELIST_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_FILELIST_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "File"; //$NON-NLS-1$
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

		return Text.get(Text.FILTER_FILELIST_REPORT);
	}
}
