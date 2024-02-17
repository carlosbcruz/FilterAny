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
import com.carlosbcruz.filterany.Text;

/**
 * Delete a specific directory
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class DeleteDirectoryFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private ArrayList<String> directoriesRemoved = new ArrayList<>();
	private ArrayList<String> directoriesNotRemoved = new ArrayList<>();

	private int directoriesDeleted = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_DELETEDIRECTORIES_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_DELETEDIRECTORIES_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_DELETEDIRECTORIES_INSTRUCTIONS);
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
				FilterControls.CHECK_BOX_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.DIRECTORY, FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
				FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text.get(Text.FILTER_DELETEDIRECTORIES_FIELD1),
				Text.get(Text.FILTER_DELETEDIRECTORIES_CHECKBOX1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_DELETEDIRECTORIES_FIELD1_TOOLTIP),
				Text.get(Text.FILTER_DELETEDIRECTORIES_CHECKBOX1_TOOLTIP) };
	}

	/**
	 * Remove the empty directories.
	 * 
	 * @param directory
	 *            The start directory.
	 * @return Can be ignored.
	 */
	ArrayList<String> retrieveDirectories(File directory[]) {

		if (directory == null) {

			return new ArrayList<>();
		}

		if (directory.length == 1) {

			if (directory[0].isDirectory()) {

				File fileInsideDirectory[] = directory[0].listFiles();

				if (fileInsideDirectory.length == 0) {

					String directoryName = directory[0].getAbsoluteFile()
							.toString();

					if (directory[0].delete()) {
						this.directoriesRemoved.add(directoryName);
						this.directoriesDeleted++;
					} else {
						this.directoriesNotRemoved.add(directoryName);
					}

					return new ArrayList<>();

				}

				for (int i = 0; i < fileInsideDirectory.length; i++) {

					if (fileInsideDirectory[i].isDirectory()) {

						File fileParameter[] = new File[1];
						fileParameter[0] = fileInsideDirectory[i];
						retrieveDirectories(fileParameter);

					} else {

						String directoryName = fileInsideDirectory[i]
								.getAbsoluteFile().toString();
						this.directoriesNotRemoved.add(directoryName);
					}
				}

				File fileInsideDirectoryVerification[] = directory[0]
						.listFiles();

				if (fileInsideDirectoryVerification.length == 0) {

					String directoryName = directory[0].getAbsoluteFile()
							.toString();

					if (directory[0].delete()) {
						this.directoriesRemoved.add(directoryName);
						this.directoriesDeleted++;
					} else {
						this.directoriesNotRemoved.add(directoryName);
					}

					return new ArrayList<>();
				}

			} else {

				this.directoriesNotRemoved.add(directory[0].getAbsoluteFile()
						.toString());

				return new ArrayList<>();
			}

		}

		return new ArrayList<>();
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

		this.directoriesRemoved = new ArrayList<>();
		this.directoriesNotRemoved = new ArrayList<>();
		this.directoriesDeleted = 0;

		// Get the target directory or file.
		String target = getTargetFileOrDirectory1();

		File file = new File(target);

		if (file.isDirectory()) {

			if (isCheckBox1()) {

				File fileParameter[] = new File[1];
				fileParameter[0] = file;
				retrieveDirectories(fileParameter);

			} else {

				File files[] = file.listFiles();

				if (files.length == 0) {

					if (file.delete()) {
						this.directoriesRemoved.add(file.getAbsoluteFile()
								.toString());
						this.directoriesDeleted++;
					} else {
						this.directoriesNotRemoved.add(file.getAbsoluteFile()
								.toString());
					}

				} else {

					this.directoriesNotRemoved.add(file.getAbsoluteFile()
							.toString());
				}
			}

		} else {
			throw new FilterException(
					Text.get(Text.FILTER_DELETEDIRECTORIES_FILES_REQUIRED_PARAMETER));

		}

		if (!this.directoriesRemoved.isEmpty()) {

			text.append(Text.get(Text.FILTER_DELETEDIRECTORIES_FILES_REMOVED)
					+ getNewLine());

			for (String fileName : this.directoriesRemoved) {
				text.append(fileName + getNewLine());
			}
		}

		if (!this.directoriesNotRemoved.isEmpty()) {

			text.append(Text
					.get(Text.FILTER_DELETEDIRECTORIES_FILES_NOT_REMOVED)
					+ getNewLine());

			for (String fileName : this.directoriesNotRemoved) {
				text.append(fileName + getNewLine());
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

		return Text.get(Text.FILTER_DELETEDIRECTORIES_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "RDir"; //$NON-NLS-1$
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

		return Text.get(Text.FILTER_DELETEDIRECTORIES_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_DELETEDIRECTORIES_EXAMPLE_OUTPUT);
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
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_DELETEDIRECTORIES_REPORT,
				String.valueOf(this.directoriesDeleted));
	}
}
