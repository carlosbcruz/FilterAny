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
import com.carlosbcruz.util.StringSupport;

/**
 * Generate the tree directory.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TreeDirectoryFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	// Counter to create anchors to be substituted by the size of each
	// directory.
	private static int sizeLocationCounter = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_TREEDIRECTORYFILES_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_TREEDIRECTORYFILES_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_TREEDIRECTORYFILES_INSTRUCTIONS);
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

		return new String[] {
				Text.get(Text.FILTER_TREEDIRECTORYFILES_DIRECTORY1),
				Text.get(Text.FILTER_TREEDIRECTORYFILES_CHECKBOX1),
				Text.get(Text.FILTER_TREEDIRECTORYFILES_CHECKBOX2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_TREEDIRECTORYFILES_DIRECTORY1_TOOLTIP),
				Text.get(Text.FILTER_TREEDIRECTORYFILES_CHECKBOX1_TOOLTIP),
				Text.get(Text.FILTER_TREEDIRECTORYFILES_CHECKBOX2_TOOLTIP) };
	}

	/**
	 * Create the directory list.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class TreeDirectoryThread implements Runnable {

		File file = null;

		int totalOfDirectories = 0;

		private StringBuffer output = new StringBuffer();

		/**
		 * Generate a directory tree.
		 * 
		 * @param textInterfaceShare
		 *            Task if it is running from a graphical interface.
		 * @param fileParameter
		 *            The directory to generate the tree.
		 * @param active
		 *            A list of active directories or not.
		 * @param last
		 *            Indicate if it is the last directory or not.
		 * @param sizeLocation
		 *            The current position to help indicate the directory size.
		 * @return The size of the directory.
		 */

		@SuppressWarnings("synthetic-access")
		long retrieveDirectoriesWithSize(TextInterfaceBean textInterfaceShare,
				File fileParameter, boolean[] active, boolean last,
				int sizeLocation) {

			if (textInterfaceShare != null) {

				if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
					textInterfaceShare.setStatus(TaskStatus.CANCELED);
					return 0;
				}

				textInterfaceShare.setProgress(Text.get(
						Text.FILTER_TREEDIRECTORYFILES_TASK_PROGRESS,
						String.valueOf(this.totalOfDirectories)));

			}

			// Generate the previous directories.
			for (int i = 0; i < active.length - 1; i++) {
				if (active[i]) {
					if (isCheckBox1()) {
						this.output.append("|    "); //$NON-NLS-1$
					} else {
						this.output.append("│    "); //$NON-NLS-1$
					}
				} else {
					this.output.append("     "); //$NON-NLS-1$
				}
			}
			// Generate the last directory.
			if (active.length != 0) {
				if (last) {
					if (isCheckBox1()) {
						this.output.append("*----"); //$NON-NLS-1$
					} else {
						this.output.append("└────"); //$NON-NLS-1$
					}
				} else {
					if (isCheckBox1()) {
						this.output.append("+----"); //$NON-NLS-1$
					} else {
						this.output.append("├────"); //$NON-NLS-1$
					}
				}
			}

			// Anchor to be replace by the size.
			String thisSizeLocation = "(" + sizeLocation + ")"; //$NON-NLS-1$ //$NON-NLS-2$

			// Output the file name.
			if (isCheckBox2()) {
				this.output.append(fileParameter.getName()
						+ " " + thisSizeLocation //$NON-NLS-1$
						+ getNewLine());
			} else {
				this.output.append(fileParameter.getName() + getNewLine());
			}

			this.totalOfDirectories++;

			// Get the files of current directory.
			File files[] = fileParameter.listFiles();

			// Return if no files.
			if (files == null) {
				return 0;
			}

			// Count how much directories exist.
			int numberOfDirectories = 0;
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					numberOfDirectories++;
				}
			}

			// Move the directories into a specific directory
			// and count the total size of all files.
			long total = 0;
			File directories[] = new File[numberOfDirectories];
			int position = 0;
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					directories[position] = files[i];
					position++;
				} else {
					total += files[i].length();
				}
			}

			// Bring the previous list of active directories.
			boolean newActive[] = new boolean[active.length + 1];
			for (int i = 0; i < active.length; i++) {
				newActive[i] = active[i];
			}
			// Add the current directory.
			newActive[active.length] = true;

			// Collect the sub-folders recursively.
			for (int i = 0; i < directories.length; i++) {

				if (i == directories.length - 1) {

					newActive[active.length] = false;

					total += retrieveDirectoriesWithSize(textInterfaceShare,
							directories[i], newActive, true,
							++sizeLocationCounter);

				} else {

					total += retrieveDirectoriesWithSize(textInterfaceShare,
							directories[i], newActive, false,
							++sizeLocationCounter);

				}
			}

			// Search for the anchor for the current directory.
			if (isCheckBox2()) {

				int index = this.output.indexOf(thisSizeLocation);
				if (index != -1) {

					// Remove the anchor.
					this.output
							.delete(index, index + thisSizeLocation.length());
					// Replace by the size.
					if (total < 1024) {
						this.output.insert(index, "  (" + total + " b)"); //$NON-NLS-1$ //$NON-NLS-2$
					} else {
						this.output.insert(index, "  (" + total + " b = " //$NON-NLS-1$ //$NON-NLS-2$
								+ StringSupport.getUnit(total) + ")"); //$NON-NLS-1$
					}
				}
			}

			return total;
		}

		/**
		 * Constructor.
		 * 
		 * @param fileParameter
		 *            The file indicating the directory from which the list will
		 *            be generated.
		 */
		TreeDirectoryThread(File fileParameter) {

			this.file = fileParameter;
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
					.get(Text.FILTER_TREEDIRECTORYFILES_LABEL));

			retrieveDirectoriesWithSize(textInterfaceShare, this.file,
					new boolean[] {}, false, 1);

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

		StringBuffer filterOutput = new StringBuffer();

		// Get the target directory
		String target = getDirectory1();

		// Guarantee that the target was provided.
		if (target == null || "".equals(target)) { //$NON-NLS-1$
			throw new FilterException(Text.get(
					Text.FILTER_TREEDIRECTORYFILES_EXCEPTION_1, target));
		}

		File file = new File(target);

		if (!file.isDirectory()) {
			throw new FilterException(Text.get(
					Text.FILTER_TREEDIRECTORYFILES_EXCEPTION_1, target));
		}

		if (FilterAnyConfiguration.isRunningInGraphicalMode()) {

			Thread treeListThread = new Thread(new TreeDirectoryThread(file));
			treeListThread.start();

		} else {

			TreeDirectoryThread runInThisThread = new TreeDirectoryThread(null);
			runInThisThread.retrieveDirectoriesWithSize(null, file,
					new boolean[] {}, false, 1);

			filterOutput = new StringBuffer(runInThisThread.output);

		}

		return filterOutput;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_TREEDIRECTORYFILES_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Tree"; //$NON-NLS-1$
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

		return Text.get(Text.FILTER_TREEDIRECTORYFILES_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_TREEDIRECTORYFILES_EXAMPLE_OUTPUT);
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
						: Behavior.NORMAL_ICON

		};
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

		return Text.get(Text.FILTER_TREEDIRECTORYFILES_REPORT);
	}
}
