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
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterException;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.DurationBean;
import com.carlosbcruz.util.DurationUtil;

/**
 * Add an entry to start a task on a timesheet.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CalculateTotalTimesheetFilter extends TimesheetAbstractFilter
		implements CommandLine, SpecialBehavior, Example {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_CALCULATESTIMESHEETFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_CALCULATESTIMESHEETFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_CALCULATESTIMESHEETFILTER_INSTRUCTIONS);
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

		String[] lines = retrieveLines(text);

		StringBuffer message = retrievePeriods(lines);

		if (message.length() != 0) {
			return message;
		}

		long total = calculateTimesheet();

		// If there is no input.
		if (periods.size() == 0) {
			return text;
		}

		long cumulativeTime = 0L;

		for (PeriodBean period : periods) {

			long lineDuration = period.getStopMillis()
					- period.getStartMillis();

			cumulativeTime += lineDuration;

			DurationBean duration = DurationUtil.getDuration(lineDuration);
			DurationBean cumulativeDuration = DurationUtil
					.getDuration(cumulativeTime);

			output.append(getShortDateDescription(period.getStartMillis())
					+ " " //$NON-NLS-1$
					+ getDayOfTheWeekWithLocale(period.getStartMillis())
					+ " - " //$NON-NLS-1$
					+ getShortDateDescription(period.getStopMillis())
					+ " " //$NON-NLS-1$
					+ getDayOfTheWeekWithLocale(period.getStopMillis())
					+ " = " //$NON-NLS-1$
					+ Text.get(Text.FILTER_CALCULATESTIMESHEETFILTER_DURATION,
							String.valueOf(duration.getTotalMinutes()),
							String.valueOf(duration.getSeconds()))
					+ " (" //$NON-NLS-1$
					+ Text.get(
							Text.FILTER_CALCULATESTIMESHEETFILTER_CUMULATIVE,
							String.valueOf(cumulativeDuration.getTotalHours()),
							String.valueOf(cumulativeDuration.getMinutes()),
							String.valueOf(cumulativeDuration.getSeconds()))
					+ ")"); //$NON-NLS-1$

			if (!"".equals(period.getStartComment())) { //$NON-NLS-1$
				output.append(" (" + period.getStartComment() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (!"".equals(period.getStopComment())) { //$NON-NLS-1$
				if (!"".equals(period.getStartComment())) { //$NON-NLS-1$
					output.append(" - "); //$NON-NLS-1$
				} else {
					output.append(" "); //$NON-NLS-1$
				}
				output.append("(" + period.getStopComment() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			output.append(getNewLine());

		}

		DurationBean duration = DurationUtil.getDuration(total);

		output.append(getNewLine());
		output.append(Text.get(Text.FILTER_CALCULATESTIMESHEETFILTER_TOTAL,
				String.valueOf(duration.getTotalHours()),
				String.valueOf(duration.getMinutes()),
				String.valueOf(duration.getSeconds()))
				+ getNewLine());

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
				.get(Text.FILTER_CALCULATESTIMESHEETFILTER_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_CALCULATESTIMESHEETFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_CALCULATESTIMESHEETFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Calc"; //$NON-NLS-1$
	}
}
