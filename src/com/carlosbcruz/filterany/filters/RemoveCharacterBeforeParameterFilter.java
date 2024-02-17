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
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Remove all characters before a specific parameter on the line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class RemoveCharacterBeforeParameterFilter extends FilterAdapter
		implements CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private static final int NOT_DEFINED = -1;

	private int charactersRemoved = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_INSTRUCTIONS);
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

		return new FilterControls[] { FilterControls.INPUT_FIELD_1,
				FilterControls.INPUT_FIELD_2, FilterControls.CHECK_BOX_1,
				FilterControls.INPUT_FIELD_3 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.ANYTHING,
				FilterType.NUMBER_GENERIC, FilterType.NOT_RELEVANT,
				FilterType.NUMBER_GENERIC };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
				FilterParameter.OPTIONAL, FilterParameter.REQUIRED,
				FilterParameter.OPTIONAL };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {
				Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD1),
				Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD2),

				Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_CHECKBOX1),
				Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD3) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD1_TOOLTIP),
				Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD2_TOOLTIP),

				Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_CHECKBOX1_TOOLTIP),
				Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_FIELD3_TOOLTIP) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] {
				Behavior.ACCEPT_AUTOMATIC_BEHAVIOR,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
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

		this.charactersRemoved = 0;

		String parameter = getField1();

		if (parameter == null || "".equals(parameter)) { //$NON-NLS-1$
			throw new FilterException(
					Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXCEPTION));
		}

		int numberOfCharacters = NOT_DEFINED;

		// Retrieve the number of characters.
		String numberOfCharactersParameter = getField2().trim();
		if ("".equals(numberOfCharactersParameter)) { //$NON-NLS-1$

			numberOfCharacters = 999999;

		} else {

			try {
				numberOfCharacters = Integer
						.parseInt(numberOfCharactersParameter);
			} catch (NumberFormatException e) {
				throw new FilterException(Text.get(
						Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXCEPTION2,
						numberOfCharactersParameter));
			}
			if (numberOfCharacters <= 0) {
				throw new FilterException(Text.get(
						Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXCEPTION2,
						numberOfCharactersParameter));
			}
		}

		int order = NOT_DEFINED;

		String orderParameter = getField3().trim();
		if (!"".equals(orderParameter)) { //$NON-NLS-1$

			try {
				order = Integer.parseInt(orderParameter);
			} catch (NumberFormatException e) {
				throw new FilterException(Text.get(
						Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXCEPTION3,
						orderParameter));
			}
			if (order <= 0) {
				throw new FilterException(Text.get(
						Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXCEPTION3,
						orderParameter));
			}
		}

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		while (tokenizer.hasMoreTokens()) {

			String currentLine = tokenizer.nextToken();

			int previousLength = currentLine.length();

			int index = NOT_DEFINED;

			if (order == NOT_DEFINED) {

				if (isCheckBox1()) {
					index = currentLine.indexOf(parameter);
				} else {
					index = currentLine.toUpperCase().indexOf(
							parameter.toUpperCase());
				}
			} else {

				int lastIndex = 0;

				for (int i = 0; i < order; i++) {

					if (isCheckBox1()) {
						index = currentLine.indexOf(parameter, lastIndex);
					} else {
						index = currentLine.toUpperCase().indexOf(
								parameter.toUpperCase(), lastIndex);
					}

					if (index == -1) {
						index = lastIndex - 1;
						break;
					}

					lastIndex = index + 1;

					if (lastIndex > currentLine.length()) {
						index = -1;
						break;
					}
				}
			}

			if (index >= 0) {

				if (numberOfCharacters >= index) {
					currentLine = currentLine.substring(index);
				} else {
					currentLine = currentLine.substring(0, index
							- numberOfCharacters)
							+ currentLine.substring(index);
				}
			}

			this.charactersRemoved += previousLength - currentLine.length();

			if (tokenizer.isFoundNewLine()) {
				output.append(currentLine + getNewLine());
			} else {
				output.append(currentLine);
			}
		}

		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "RemBP"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_REMOVECHARACTERSBEFOREPARAMETER_REPORT,
				String.valueOf(this.charactersRemoved));
	}
}
