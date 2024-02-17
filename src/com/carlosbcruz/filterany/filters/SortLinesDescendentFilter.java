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
import java.util.Collections;
import java.util.List;

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
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Sort the lines in an descendent order.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class SortLinesDescendentFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int linesSorted = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_INSTRUCTIONS);
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
				Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_CHECKBOX1),
				Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_CHECKBOX2) };

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_CHECKBOX1_TOOLTIP),
				Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_CHECKBOX2_TOOLTIP) };
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
						: Behavior.NORMAL_ICON

		};
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

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		this.linesSorted = 0;

		StringBuffer output = new StringBuffer();

		List<String> lines = new ArrayList<>();

		LineTokenizer linesTokenizer = new LineTokenizer(text);

		// Store the lines.
		int lineNumber = 0;
		while (linesTokenizer.hasMoreTokens()) {

			String lineToBeSorted = linesTokenizer.nextToken();

			if (!isCheckBox1()) {
				lineToBeSorted = lineToBeSorted.toUpperCase();
			}

			if (isCheckBox2()) {
				lineToBeSorted = lineToBeSorted.trim();
			}

			lines.add(lineToBeSorted + "#" + lineNumber); //$NON-NLS-1$

			lineNumber++;
		}

		// Sort them.
		Collections.sort(lines);

		int order[] = new int[lineNumber];

		// Generate the sorted text.
		for (int i = 0; i < lines.size(); i++) {

			// See where the # char is.
			String line = lines.get(i);
			int position = line.length() - 1;
			while (line.charAt(position) != '#') {
				position--;
			}

			String linePosition = line.substring(position + 1);

			int number = 0;
			try {
				number = Integer.parseInt(linePosition);
			} catch (NumberFormatException e) {
				ExceptionSupport.handleException(Text
						.get(Text.FILTER_SORTLINESDESCENDENTFILTER_EXCEPTION1));
			}

			order[i] = number;

		}

		// Read the lines again.
		LineTokenizer originalOrderTokenizer = new LineTokenizer(text);
		String orignalOrderLines[] = new String[lineNumber];
		int counter = 0;
		while (originalOrderTokenizer.hasMoreTokens()) {

			orignalOrderLines[counter++] = originalOrderTokenizer.nextToken();
		}

		// Apply the order.
		String orderedLines[] = new String[lineNumber];
		for (int i = 0; i < lineNumber; i++) {
			orderedLines[i] = orignalOrderLines[order[i]];
		}

		// Invert the order.
		String newOrder[] = new String[lineNumber];
		for (int i = 0; i < lineNumber; i++) {
			newOrder[lineNumber - 1 - i] = orderedLines[i];
		}

		// Generate the sorted text.
		for (int i = 0; i < lines.size(); i++) {
			this.linesSorted++;
			output.append(newOrder[i] + getNewLine());
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
				.get(Text.FILTER_SORTLINESDESCENDENTFILTER_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_SORTLINESDESCENDENTFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "SrtD"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_SORTLINESDESCENDENTFILTER_REPORT,
				String.valueOf(this.linesSorted));
	}

}
