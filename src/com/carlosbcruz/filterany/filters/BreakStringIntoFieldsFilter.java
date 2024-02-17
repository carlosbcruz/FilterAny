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

import java.util.ArrayList;

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterAnyEncoding;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterException;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.FileSupport;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Transform a string into fields using a mask.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class BreakStringIntoFieldsFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_INSTRUCTIONS);
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

		return new FilterControls[] { FilterControls.TARGET_FILE_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.DIRECTORY };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text
				.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_TARGET1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_TARGET1_TOOLTIP) };
	}

	/**
	 * Store a specific field.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class FieldsBean {

		private String fieldDescription = new String();
		private int size = 0;
		private boolean required = false;

		/**
		 * Default constructor.
		 * 
		 * @param fieldDescription
		 *            The field description.
		 * @param size
		 *            The size.
		 * @param required
		 *            If the field is required or not.
		 */
		FieldsBean(String fieldDescription, int size, boolean required) {

			this.fieldDescription = fieldDescription;
			this.size = size;
			this.required = required;
		}

		/**
		 * @return the fieldDescription
		 */
		protected String getFieldDescription() {
			return this.fieldDescription;
		}

		/**
		 * @return the size
		 */
		protected int getSize() {
			return this.size;
		}

		/**
		 * @return the required
		 */
		protected boolean isRequired() {
			return this.required;
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

			out.append("FieldsBean [\n");

			out.append("\tfieldDescription=" + this.fieldDescription + ",\n");
			out.append("\tsize=" + this.size + ",\n");
			out.append("\trequired=" + this.required + "]\n");

			return out.toString();
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

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		// Read the mask file.
		String maskFile = getFile1();
		String lines[] = null;
		try {
			lines = FileSupport.readAllEncodedLinesWithExceptions(maskFile,
					FilterAnyEncoding.getInstance().getDefaultFileEncoding());
		} catch (Throwable exception) {
			throw new FilterException(Text.get(
					Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXCEPTION1,
					maskFile), exception.getLocalizedMessage());
		}
		if (lines == null) {
			throw new FilterException(Text.get(
					Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXCEPTION1,
					maskFile));
		}

		// Create the fields array with the lines.
		ArrayList<FieldsBean> fields = new ArrayList<>();

		int lineNumber = 1;
		for (String line : lines) {

			line = line.trim();

			if ("".equals(line)) { //$NON-NLS-1$
				throw new FilterException(Text.get(
						Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXCEPTION2,
						String.valueOf(lineNumber), line));
			}

			// Get the space location.
			int spaceIndex = 0;
			for (int i = 0; i < line.length(); i++) {

				char thisChar = line.charAt(i);

				if (' ' == thisChar) {
					spaceIndex = i;
					break;
				}
			}

			// Get the number of characters of the field.
			String fieldSizeText = line.substring(0, spaceIndex);
			int fieldSize = 0;
			try {
				fieldSize = Integer.parseInt(fieldSizeText);
			} catch (NumberFormatException exception) {
				throw new FilterException(Text.get(
						Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXCEPTION2,
						String.valueOf(lineNumber), line),
						exception.getLocalizedMessage());
			}

			fields.add(new FieldsBean(line.substring(spaceIndex), fieldSize,
					line.indexOf("*") != -1)); //$NON-NLS-1$

			lineNumber++;
		}

		// Analyze only the first line.
		StringBuffer output = new StringBuffer();
		LineTokenizer tokenizer = new LineTokenizer(text);
		if (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();

			// Break the line into tokens.
			for (FieldsBean field : fields) {

				int size = field.getSize();

				// If the line is shorter than stop here.
				if (token.length() < size) {
					output.append(Text
							.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXCEPTION3,
									field.getFieldDescription())
							+ getNewLine());
					break;
				}

				String fieldContent = token.substring(0, size);
				token = token.substring(size, token.length());
				output.append("[" + fieldContent + "] " //$NON-NLS-1$ //$NON-NLS-2$
						+ field.getFieldDescription());
				if (field.isRequired()) {
					output.append(" <---------------------------------"); //$NON-NLS-1$
				}
				output.append(getNewLine());

			}
		}

		return output;
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
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text
				.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_COMMAND_LINE_HELP);
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

		return Text
				.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text
				.get(Text.FILTER_BREAKSTRINGINTOFIELDSEFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "BrkS"; //$NON-NLS-1$
	}

}
