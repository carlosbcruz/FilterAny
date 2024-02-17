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

import java.util.Hashtable;

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.Report;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.util.LineTokenizer;

/**
 * If there are duplicated lines than remove all of them and do not leave any of
 * them.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class EliminateAllDuplicatedLinesFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int linesEliminated = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_INSTRUCTIONS);
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

		return new FilterControls[] { FilterControls.CHECK_BOX_1,
				FilterControls.CHECK_BOX_2 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NOT_RELEVANT,
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
				FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {
				Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_CHECKBOX1),
				Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_CHECKBOX2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_CHECKBOX1_TOOLTIP),
				Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_CHECKBOX2_TOOLTIP) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] { Behavior.ACCEPT_AUTOMATIC_BEHAVIOR,
				Behavior.NORMAL_ICON };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText) {

		this.linesEliminated = 0;

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		Hashtable<Integer, String> texts = new Hashtable<>();
		Hashtable<Integer, String> duplicated = new Hashtable<>();

		// Search for all duplicated lines
		while (tokenizer.hasMoreTokens()) {

			String token = null;
			if (isCheckBox2()) {
				token = tokenizer.nextToken().trim();
			} else {
				token = tokenizer.nextToken();
			}

			// If it is to ignore empty lines
			if (isCheckBox1() && "".equals(token)) { //$NON-NLS-1$
				continue;
			}

			// Identify the repetitions.
			if (texts.contains(token)) {
				duplicated.put(new Integer(token.hashCode()), token);
			} else {
				texts.put(new Integer(token.hashCode()), token);
			}
		}

		// Rescan the text and do not include repetitions.
		LineTokenizer tokenizerOutput = new LineTokenizer(text);

		while (tokenizerOutput.hasMoreTokens()) {

			String token = null;
			String verificationToken = null;
			if (isCheckBox2()) {
				token = tokenizerOutput.nextToken();
				verificationToken = token.trim();
			} else {
				verificationToken = token = tokenizerOutput.nextToken();
			}

			// If it is to ignore empty lines
			if (isCheckBox1() && "".equals(token.trim())) { //$NON-NLS-1$

				if (tokenizerOutput.isFoundNewLine()) {
					output.append((token + getNewLine()));
				} else {
					output.append((token));
				}
				continue;
			}

			if (!duplicated.contains(verificationToken)) {

				if (tokenizerOutput.isFoundNewLine()) {
					output.append((token + getNewLine()));
				} else {
					output.append((token));
				}
			} else {
				this.linesEliminated++;
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

		return Text
				.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "EliD"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_ELIMINATEALLDUPLICATEDLINES_REPORT,
				String.valueOf(this.linesEliminated));
	}

}
