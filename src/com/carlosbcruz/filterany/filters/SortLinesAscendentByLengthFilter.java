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
import java.util.Comparator;
import java.util.List;

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
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Sort the lines in an ascendent by the lines length.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class SortLinesAscendentByLengthFilter extends FilterAdapter implements
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

		return Text.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text
				.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_INSTRUCTIONS);
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

		return new FilterControls[] { FilterControls.CHECK_BOX_1 };

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NOT_RELEVANT };
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
				.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_CHECKBOX1) };

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_CHECKBOX1_TOOLTIP) };
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

		this.linesSorted = 0;

		StringBuffer output = new StringBuffer();

		List<String> lines = new ArrayList<>();

		LineTokenizer linesTokenizer = new LineTokenizer(text);

		// Store the lines.
		int lineNumber = 0;
		while (linesTokenizer.hasMoreTokens()) {

			String lineToBeSorted = linesTokenizer.nextToken();

			if (isCheckBox1()) {
				lineToBeSorted = lineToBeSorted.trim();
			}

			lines.add(lineToBeSorted + "#" + lineNumber); //$NON-NLS-1$

			lineNumber++;
		}

		// Sort them.
		Collections.sort(lines, new Comparator<String>() {

			/**
			 * Compare the two strings based on the length.
			 * 
			 * @param first
			 * @param second
			 * @return
			 */
			@Override
			public int compare(String first, String second) {

				int firstPosition = first.length() - 1;
				while (first.charAt(firstPosition) != '#') {
					firstPosition--;
				}
				String firstLineContent = first.substring(0, firstPosition);

				int secondPosition = second.length() - 1;
				while (second.charAt(secondPosition) != '#') {
					secondPosition--;
				}
				String secondLineContent = second.substring(0, secondPosition);

				if (firstLineContent.length() > secondLineContent.length()) {
					return 1;
				}

				if (firstLineContent.length() < secondLineContent.length()) {
					return -1;
				}

				return 0;
			}

		});

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
				ExceptionSupport
						.handleException(Text
								.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_EXCEPTION1));
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

		// Generate the final text.
		for (int i = 0; i < lineNumber; i++) {
			output.append(orderedLines[i] + getNewLine());
			this.linesSorted++;
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
				.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text
				.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "SrtL"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_SORTLINESASCENDENTBYLENGTHFILTER_REPORT,
				String.valueOf(this.linesSorted));
	}

}
