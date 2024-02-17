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
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Transform an hour from AM PM format to a 24h format.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FromAMPMto24hFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_INSTRUCTIONS);
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

		return new FilterControls[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {};
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
				Behavior.WORK_ONLY_ON_TEXT,
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

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		while (tokenizer.hasMoreTokens()) {

			String line = tokenizer.nextToken().trim();

			// Search for the separation of hours.
			int collonPosition = line.indexOf(':');
			if (collonPosition < 1) {

				output.append(Text
						.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_EXCEPTION_1)
						+ getNewLine());
				continue;
			}
			String hourText = line.substring(0, collonPosition);

			// Get the hour
			int hour = 0;
			try {
				hour = Integer.parseInt(hourText);
			} catch (NumberFormatException e) {

				output.append(Text
						.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_EXCEPTION_1)
						+ getNewLine());
				continue;
			}

			line = line.substring(collonPosition + 1, line.length());

			// Search for the separation of hours.
			collonPosition = line.indexOf(':');
			if (collonPosition == -1) {
				collonPosition = line.indexOf(' ');
			}
			if (collonPosition == -1) {
				collonPosition = line.indexOf('\t');
			}
			if (collonPosition == -1) {
				collonPosition = line.indexOf('A');
			}
			if (collonPosition == -1) {
				collonPosition = line.indexOf('a');
			}
			if (collonPosition == -1) {
				collonPosition = line.indexOf('P');
			}
			if (collonPosition == -1) {
				collonPosition = line.indexOf('p');
			}
			if (collonPosition < 1) {

				output.append(Text
						.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_EXCEPTION_1)
						+ getNewLine());
				continue;
			}
			String minuteText = line.substring(0, collonPosition);

			// Get the hour
			int minute = 0;
			try {
				minute = Integer.parseInt(minuteText);
			} catch (NumberFormatException e) {

				output.append(Text
						.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_EXCEPTION_1)
						+ getNewLine());
				continue;
			}

			if (line.charAt(collonPosition) == ':') {

				line = line.substring(collonPosition + 1, line.length());
			} else {

				line = line.substring(collonPosition, line.length());
			}

			// Get the seconds if exist.
			int seconds = 0;
			int spacePosition = line.indexOf(' ');
			if (spacePosition == -1) {
				spacePosition = line.indexOf('\t');
			}
			if (spacePosition == -1) {
				spacePosition = line.indexOf('A');
			}
			if (spacePosition == -1) {
				spacePosition = line.indexOf('a');
			}
			if (spacePosition == -1) {
				spacePosition = line.indexOf('P');
			}
			if (spacePosition == -1) {
				spacePosition = line.indexOf('p');
			}
			if (spacePosition >= 1) {

				String secondText = line.substring(0, spacePosition);

				boolean hasNumber = true;
				try {
					seconds = Integer.parseInt(secondText);
				} catch (NumberFormatException e) {
					hasNumber = false;
				}

				if (hasNumber) {
					line = line.substring(spacePosition, line.length());
				}
			}

			if ("AM".equals(line.trim().toUpperCase()) //$NON-NLS-1$
					|| "A.M.".equals(line.trim().toUpperCase())) { //$NON-NLS-1$

				if (hour >= 12) {
					hour -= 12;
				}

				output.append((hour < 10 ? "0" + hour : String.valueOf(hour)) + ":" //$NON-NLS-1$ //$NON-NLS-2$
						+ (minute < 10 ? "0" + minute : String.valueOf(minute)) + ":" //$NON-NLS-1$ //$NON-NLS-2$
						+ (seconds < 10 ? "0" + seconds : String.valueOf(seconds)) //$NON-NLS-1$
						+ getNewLine());

			} else {

				if ("PM".equals(line.trim().toUpperCase()) //$NON-NLS-1$
						|| "P.M.".equals(line.trim().toUpperCase())) { //$NON-NLS-1$

					if (hour != 12) {
						hour += 12;
					}

					output.append(hour
							+ ":" //$NON-NLS-1$
							+ (minute < 10 ? "0" + minute : String.valueOf(minute)) + ":" //$NON-NLS-1$ //$NON-NLS-2$
							+ (seconds < 10 ? "0" + seconds : String.valueOf(seconds)) //$NON-NLS-1$
							+ getNewLine());
				} else {

					output.append(Text
							.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_EXCEPTION_1)
							+ getNewLine());
					continue;
				}
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

		return Text.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_FROM_AM_PM_TO_24H_FILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "AMto24"; //$NON-NLS-1$
	}
}
