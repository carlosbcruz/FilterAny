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
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import com.carlosbcruz.filterany.BinaryFilesFilter;
import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterAnyEncoding;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterException;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.RenameFile;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.BinarySupport;
import com.carlosbcruz.util.FileSupport;
import com.carlosbcruz.util.StringSupport;

/**
 * Replace multiple contents and rename files as they were part of the content.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ReplaceAndRenameFilter extends FilterAdapter implements
		CommandLine, RenameFile, Example, BinaryFilesFilter, SpecialBehavior {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_INSTRUCTIONS);
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
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsRequired()
	 */
	@Override
	public FilterControls[] getControls() {

		return new FilterControls[] { FilterControls.TARGET_FILE_1,
				FilterControls.INPUT_FIELD_1, FilterControls.INPUT_FIELD_2 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.DIRECTORY, FilterType.ANYTHING,
				FilterType.ANYTHING };
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
				Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_FILE1),
				Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_INPUT_FIELD_1),
				Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_INPUT_FIELD_2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_FILE1_TOOLTIP),
				Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_INPUT_FIELD_1_TOOLTIP),
				Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_INPUT_FIELD_2_TOOLTIP) };
	}

	/**
	 * Store a content to be searched and the content to be replaced by.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class ContentBean {

		private String searchFor = new String();
		private String replaceBy = new String();

		/**
		 * Contructor.
		 * 
		 * @param searchFor
		 *            The content to be searched for.
		 * @param replaceBy
		 *            The content to be replaced by.
		 */
		public ContentBean(String searchFor, String replaceBy) {

			super();

			this.searchFor = searchFor;
			this.replaceBy = replaceBy;
		}

		/**
		 * Provide the name of the file to search for.
		 * 
		 * @return the searchFor The name of the file to search for.
		 */
		protected String getSearchFor() {
			return this.searchFor;
		}

		/**
		 * Set the name of the file to search for.
		 * 
		 * @param searchFor
		 *            The name of the file to search for.
		 */
		protected void setSearchFor(String searchFor) {
			this.searchFor = searchFor;
		}

		/**
		 * Provide the name of the file to replace by.
		 * 
		 * @return the replaceBy The name of the file to replace by.
		 */
		protected String getReplaceBy() {
			return this.replaceBy;
		}

		/**
		 * Set the name of the file to replace by.
		 * 
		 * @param replaceBy
		 *            The name of the file to replace by.
		 */
		protected void setReplaceBy(String replaceBy) {
			this.replaceBy = replaceBy;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@SuppressWarnings("nls")
		@Override
		public String toString() {

			StringBuffer out = new StringBuffer();

			out.append("ClassName [\n");

			out.append("\tsearchFor=" + this.searchFor + ",\n");
			out.append("\treplaceBy=" + this.replaceBy + "]\n");

			return out.toString();
		}
	}

	/**
	 * Read the file with the contents to be replaced.
	 * 
	 * @return The list of contents to be replaced.
	 * @throws FilterException
	 *             If there is a problem.
	 */
	private ArrayList<ContentBean> getContent() throws FilterException {

		// The file with the contents to be replaced by.
		String replaceFile = getFile1();

		String lines[] = null;
		try {

			lines = FileSupport.readAllEncodedLinesWithExceptions(
					replaceFile,
					FilterAnyEncoding.getInstance().getDefaultFileEncoding());

		} catch (Throwable exception) {

			throw new FilterException(
					Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_EXCEPTION1,
							replaceFile), exception.getLocalizedMessage());

		}
		if (lines == null) {

			throw new FilterException(Text.get(
					Text.FILTER_REPLACEANDRENAMEFILTER_EXCEPTION1, replaceFile));

		}

		// Search for and replace by are separated by a tabulation.
		ArrayList<ContentBean> contents = new ArrayList<>();
		for (String line : lines) {

			int index = line.indexOf('\t');

			// The search for and replace by are separated by a tab.
			if (index == -1) {

				continue;
			}

			String searchFor = line.substring(0, index);
			String replaceBy = line.substring(index + 1);

			ContentBean bean = new ContentBean(searchFor, replaceBy);

			contents.add(bean);
		}

		// Comparator to sort the beans by Search for attribute in descendent
		// order of length.
		Comparator<ContentBean> comparator = new Comparator<ContentBean>() {

			/**
			 * Compare first parameter with second.
			 * 
			 * @param firstParameter
			 *            First parameter.
			 * @param secondParameter
			 *            Second parameter.
			 * @return The order indication -1, 1 or 0.
			 */
			@Override
			public int compare(ContentBean firstParameter,
					ContentBean secondParameter) {

				if (firstParameter.getSearchFor().length() > secondParameter
						.getSearchFor().length()) {
					return -1;
				}

				if (firstParameter.getSearchFor().length() < secondParameter
						.getSearchFor().length()) {
					return 1;
				}
				return 0;
			}

		};

		// Sort the contents on descendent order.
		Collections.sort(contents, comparator);

		return contents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.RenameFile#getNewFileName(java.lang.String)
	 */
	@Override
	public String getNewFileName(String fileName) throws FilterException {

		ArrayList<ContentBean> contents = getContent();

		File file = new File(fileName);

		String name = file.getName();

		String newName = name;

		// Only rename the files allowed to be renamed.
		String extensions[] = getExtensions(getField1());
		if (hasExtension(name, extensions)) {

			for (ContentBean bean : contents) {

				newName = StringSupport.replace(newName, bean.getSearchFor(),
						bean.getReplaceBy());
			}
		}
		String fullName = file.getAbsolutePath();

		int index = fullName.indexOf(name);

		String newFileName = fullName.substring(0, index);

		newFileName += newName;

		return newFileName;

	}

	/**
	 * Indicate if a file name has any of the extensions provided.
	 * 
	 * @param file
	 *            The file name.
	 * @param extensions
	 *            List of extensions.
	 * @return True if the file has the provided extension.
	 */
	private static boolean hasExtension(String file, String[] extensions) {

		for (String extension : extensions) {
			if (file.toUpperCase().endsWith(extension.toUpperCase())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Return the set of extensions of a parameter..
	 * 
	 * @return The extensions of a parameter..
	 */
	private static String[] getExtensions(String field) {

		ArrayList<String> extensions = new ArrayList<>(5);
		StringTokenizer tokenizer = new StringTokenizer(field, ","); //$NON-NLS-1$
		while (tokenizer.hasMoreElements()) {
			extensions.add(tokenizer.nextToken());
		}

		String[] extensionsResult = new String[extensions.size()];
		extensionsResult = extensions.toArray(extensionsResult);

		return extensionsResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.BinaryFilesFilter#filter(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String filter(String fileFrom, String fileTo) throws FilterException {

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {

			return new String();
		}

		File sourceFile = new File(fileFrom);
		File targetFile = new File(fileTo);

		ArrayList<ContentBean> contents = getContent();

		String extensions[] = getExtensions(getField2());
		if (hasExtension(fileFrom, extensions)) {

			// Replace the content and save the file.
			byte[] workingBytes = FileSupport.readBinaryFile(sourceFile);

			for (ContentBean bean : contents) {

				workingBytes = BinarySupport.replaceBinary(workingBytes, bean
						.getSearchFor().getBytes(), bean.getReplaceBy()
						.getBytes());
			}

			guaranteeDirectory(fileTo);

			FileSupport.writeBinaryFile(fileTo, workingBytes);

		} else {

			// Just copy the file.

			guaranteeDirectory(fileTo);

			FileSupport.copyFile(fileFrom, fileTo);
		}

		if (FileSupport.isInSameDirectory(sourceFile, targetFile)) {
			if (FileSupport.hasSameFileName(sourceFile, targetFile)) {
				// Do nothing.
			} else {
				sourceFile.delete();
			}
		}

		return new String();
	}

	/**
	 * Guarantee that the directory to hold the file exists.
	 * 
	 * @param fileTo
	 *            The file.
	 * @throws FilterException
	 *             If a problem happens while creating a directory.
	 */
	private static void guaranteeDirectory(String fileTo)
			throws FilterException {

		String targetPath = FileSupport.getOnlyPath(fileTo);

		if (!FileSupport.createDirectory(targetPath)) {

			throw new FilterException(
					Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_EXCEPTION2));
		}
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

		return Text.get(Text.FILTER_REPLACEANDRENAMEFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return new String();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] { FilterAnyConfiguration
				.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
				: Behavior.NORMAL_ICON };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "RandR"; //$NON-NLS-1$
	}

}
