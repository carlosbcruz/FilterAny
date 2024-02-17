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
 * Insert the Auxiliar text into a specific location of the Main text.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class InsertAuxiliarIntoMain extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_INSTRUCTIONS);
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
				FilterControls.INPUT_FIELD_2, FilterControls.CHECK_BOX_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NUMBER_GENERIC,
				FilterType.NUMBER_POSITION, FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.OPTIONAL,
				FilterParameter.OPTIONAL, FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {
				Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_FIELD_1),
				Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_FIELD_2),
				Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_CHECKBOX_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_FIELD_1_TOOLTIP),
				Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_FIELD_2_TOOLTIP),
				Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_CHECKBOX_1_TOOLTIP) };
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

		int line = 1;
		int column = 0;

		try {
			line = Integer.parseInt(getField1());
		} catch (NumberFormatException e) {
			// Do nothing.
		}

		if (line == 0) {
			line = 1;
		}

		try {
			column = Integer.parseInt(getField2());
		} catch (NumberFormatException e) {
			// Do nothing.
		}

		int currentLine = 0;
		boolean auxiliarInserted = false;

		StringBuffer output = new StringBuffer();

		LineTokenizer mainTokenizer = new LineTokenizer(text);
		while (mainTokenizer.hasMoreTokens()) {

			String token = mainTokenizer.nextToken();
			currentLine++;

			if (currentLine == line) {

				if (column <= token.length()) {

					StringBuffer newToken = new StringBuffer(token);
					newToken.insert(column, auxiliarText);
					if (mainTokenizer.isFoundNewLine()) {
						output.append(newToken + getNewLine());
					} else {
						output.append(newToken);
					}

				} else {

					// If force position.
					if (isCheckBox1()) {

						output.append(token);
						for (int i = token.length(); i < column; i++) {
							output.append(" "); //$NON-NLS-1$
						}
						output.append(auxiliarText);
						if (mainTokenizer.isFoundNewLine()) {
							output.append(auxiliarText + getNewLine());
						} else {
							output.append(auxiliarText);
						}

					} else {

						if (mainTokenizer.isFoundNewLine()) {
							output.append(token + auxiliarText + getNewLine());
						} else {
							output.append(token + auxiliarText);
						}
					}

				}

				auxiliarInserted = true;
			} else {

				if (mainTokenizer.isFoundNewLine()) {
					output.append(token + getNewLine());
				} else {
					output.append(token);
				}
			}

		}

		if (!auxiliarInserted) {

			// If force position.
			if (isCheckBox1()) {

				for (int i = currentLine; i < line; i++) {
					output.append(getNewLine());
				}
				for (int i = 0; i < column; i++) {
					output.append(" "); //$NON-NLS-1$
				}
			}
			output.append(auxiliarText);

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

		return Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getAuxiliarInputExample()
	 */
	@Override
	public String getAuxiliarInputExample() {

		return Text
				.get(Text.FILTER_INSERTAUXILIARINTOMAIN_EXAMPLE_AUXILIAR_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getMainInputExample()
	 */
	@Override
	public String getMainInputExample() {

		return Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Ins"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] {
				Behavior.WORK_ON_DUAL_PANE,
				Behavior.ACCEPT_AUTOMATIC_BEHAVIOR,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_INSERTAUXILIARINTOMAIN_REPORT);
	}
}
