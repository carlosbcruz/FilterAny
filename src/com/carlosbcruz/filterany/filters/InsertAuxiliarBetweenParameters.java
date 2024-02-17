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

/**
 * Insert the Auxiliar text into a specific location of the Main text.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class InsertAuxiliarBetweenParameters extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int replacements = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text
				.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_INSTRUCTIONS);
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

		return new FilterType[] { FilterType.ANYTHING, FilterType.ANYTHING,
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
				Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_FIELD_1),
				Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_FIELD_2),
				Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_CHECKBOX_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_FIELD_1_TOOLTIP),
				Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_FIELD_2_TOOLTIP),
				Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_CHECKBOX_1_TOOLTIP) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@Override
	protected StringBuffer filter(StringBuffer textParameter,
			StringBuffer auxiliarText) throws FilterException {

		StringBuffer text = new StringBuffer(textParameter.toString());

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		this.replacements = 0;

		String parameterStart = getField1().trim();
		String parameterEnd = getField2().trim();

		if (parameterStart == null || "".equals(parameterStart)) { //$NON-NLS-1$
			throw new FilterException(
					Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXCEPTION_1));
		}

		if (parameterEnd == null || "".equals(parameterEnd)) { //$NON-NLS-1$
			throw new FilterException(
					Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXCEPTION_2));
		}

		if (parameterStart.equals(parameterEnd)) {
			throw new FilterException(
					Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXCEPTION_3));
		}

		StringBuffer output = new StringBuffer();

		boolean inSearchMode = true;
		while (inSearchMode) {

			int parameterStartIndex = -1;

			if (isCheckBox1()) {
				parameterStartIndex = text.indexOf(parameterStart);
			} else {
				StringBuffer textUpper = new StringBuffer(text.toString()
						.toUpperCase());
				parameterStartIndex = textUpper.indexOf(parameterStart
						.toUpperCase());
			}

			if (parameterStartIndex >= 0) {

				int searchFrom = parameterStartIndex + parameterStart.length();

				int parameterEndIndex = -1;

				if (isCheckBox1()) {
					parameterEndIndex = text.indexOf(parameterEnd, searchFrom);
				} else {
					StringBuffer textUpper = new StringBuffer(text.toString()
							.toUpperCase());
					parameterEndIndex = textUpper.indexOf(
							parameterEnd.toUpperCase(), searchFrom);
				}

				output.append(text.substring(0, parameterStartIndex));
				output.append(parameterStart);
				output.append(auxiliarText);
				this.replacements++;

				if (parameterEndIndex >= 0) {

					output.append(parameterEnd);

					int endReplacementIndex = parameterEndIndex
							+ parameterEnd.length();
					text = new StringBuffer(text.substring(endReplacementIndex,
							text.length()));
				} else {

					inSearchMode = false;
				}

			} else {

				output.append(text);
				inSearchMode = false;
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
				.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getAuxiliarInputExample()
	 */
	@Override
	public String getAuxiliarInputExample() {

		return Text
				.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXAMPLE_AUXILIAR_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getMainInputExample()
	 */
	@Override
	public String getMainInputExample() {

		return Text
				.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text
				.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "InPar"; //$NON-NLS-1$
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

		return Text.get(Text.FILTER_INSERTAUXILIARBETWEENPARAMETERS_REPORT,
				String.valueOf(this.replacements));
	}
}
