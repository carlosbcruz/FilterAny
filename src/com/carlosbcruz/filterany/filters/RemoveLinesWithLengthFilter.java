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
 * Replace all the lines based on it's length.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class RemoveLinesWithLengthFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int lessThen = Integer.MIN_VALUE;
	private int moreThen = Integer.MAX_VALUE;
	private int linesRemoved = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_REMVELINEWITHLENGTH_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_REMVELINEWITHLENGTH_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_REMVELINEWITHLENGTH_INSTRUCTIONS);
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
				FilterControls.INPUT_FIELD_2 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NUMBER_POSITION,
				FilterType.NUMBER_POSITION };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.OPTIONAL,
				FilterParameter.OPTIONAL };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text.get(Text.FILTER_REMVELINEWITHLENGTH_FIELD1),
				Text.get(Text.FILTER_REMVELINEWITHLENGTH_FIELD2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_REMVELINEWITHLENGTH_FIELD1_TOOLTIP),
				Text.get(Text.FILTER_REMVELINEWITHLENGTH_FIELD2_TOOLTIP) };
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

		this.lessThen = Integer.MIN_VALUE;
		this.moreThen = Integer.MAX_VALUE;
		this.linesRemoved = 0;

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		String lessThenText = getField1();
		String moreThenText = getField2();

		if (lessThenText == null) {
			lessThenText = new String();
		}
		if (moreThenText == null) {
			moreThenText = new String();
		}

		try {
			this.lessThen = Integer.parseInt(lessThenText);
		} catch (NumberFormatException e) {
			// do nothing
		}

		try {
			this.moreThen = Integer.parseInt(moreThenText);
		} catch (NumberFormatException e) {
			// do nothing
		}

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		while (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();

			if (this.lessThen != Integer.MIN_VALUE
					&& this.moreThen != Integer.MAX_VALUE) {
				if (token.length() < this.lessThen
						&& token.length() > this.moreThen) {
					// Remove the line.
					this.linesRemoved++;
				} else {
					if (tokenizer.isFoundNewLine()) {
						output.append(token + getNewLine());
					} else {
						output.append(token);
					}
				}
			} else {
				if (this.lessThen != Integer.MIN_VALUE) {
					if (token.length() < this.lessThen) {
						// Remove the line.
						this.linesRemoved++;
					} else {
						if (tokenizer.isFoundNewLine()) {
							output.append(token + getNewLine());
						} else {
							output.append(token);
						}
					}
				} else {
					if (token.length() > this.moreThen) {
						// Remove the line.
						this.linesRemoved++;
					} else {
						if (tokenizer.isFoundNewLine()) {
							output.append(token + getNewLine());
						} else {
							output.append(token);
						}
					}
				}
			}
		}

		return new StringBuffer(output);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_REMVELINEWITHLENGTH_COMMAND_LINE_HELP);
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

		return Text.get(Text.FILTER_REMVELINEWITHLENGTH_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_REMVELINEWITHLENGTH_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "RLen"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_REMVELINEWITHLENGTH_REPORT,
				String.valueOf(this.linesRemoved));
	}

}
