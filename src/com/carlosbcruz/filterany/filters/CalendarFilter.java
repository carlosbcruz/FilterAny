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
import java.util.Calendar;

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

/**
 * Generate calendars.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CalendarFilter extends FilterAdapter implements SpecialBehavior,
		Example {

	private static final long serialVersionUID = 1L;

	private static final int NOT_DEFINED = -1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_CALENDARFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_CALENDARFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_CALENDARFILTER_INSTRUCTIONS);
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

		return new FilterType[] { FilterType.NUMBER_GENERIC,
				FilterType.NUMBER_GENERIC };
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

		return new String[] { Text.get(Text.FILTER_CALENDARFILTER_FIELD1),
				Text.get(Text.FILTER_CALENDARFILTER_FIELD2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_CALENDARFILTER_FIELD1_TOOLTIP),
				Text.get(Text.FILTER_CALENDARFILTER_FIELD2_TOOLTIP) };
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
		int year = NOT_DEFINED;

		// Retrieve the year.
		String yearParameter = getField1().trim();

		if ("".equals(yearParameter)) { //$NON-NLS-1$
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
		} else {

			try {
				year = Integer.parseInt(yearParameter);
			} catch (NumberFormatException exception) {
				throw new FilterException(Text.get(
						Text.FILTER_CALENDARFILTER_EXCEPTION1, yearParameter));
			}
			if (year < 1 || year > 5000) {
				throw new FilterException(Text.get(
						Text.FILTER_CALENDARFILTER_EXCEPTION1, yearParameter));
			}
		}

		int columns = NOT_DEFINED;

		// Retrieve the columns.
		String columnsParameter = getField2().trim();
		if ("".equals(columnsParameter)) { //$NON-NLS-1$
			columns = 3;
		} else {
			try {
				columns = Integer.parseInt(columnsParameter);
			} catch (NumberFormatException e) {
				throw new FilterException(
						Text.get(Text.FILTER_CALENDARFILTER_EXCEPTION2,
								columnsParameter));
			}
			if (columns < 1 || columns > 12) {
				throw new FilterException(
						Text.get(Text.FILTER_CALENDARFILTER_EXCEPTION2,
								columnsParameter));
			}
		}

		// Get the calendar.
		ArrayList<String> lines = getYear(year, columns);

		// Get the longest length.
		int size = 0;
		for (String line : lines) {
			if (line.length() > size) {
				size = line.length();
			}
		}

		// Generate a string with halph of the length
		// of the longest line to add the year.
		int append = size / 2;
		String spacesToAppend = new String();
		for (int i = 0; i < append; i++) {
			spacesToAppend += " "; //$NON-NLS-1$
		}
		output.append(spacesToAppend + year + getNewLine());
		output.append(getNewLine());

		// Transfer the calendar to the output buffer.
		for (String line : lines) {
			output.append(line + getNewLine());
		}

		return output;
	}

	/**
	 * Generate the calendar for a specific year and the monts are distributed
	 * on a specific number of columns.
	 * 
	 * @param year
	 *            The year to generate the calendar.
	 * @param columns
	 *            The number of columns.
	 * @return The calendar on a sequence of lines.
	 */
	@SuppressWarnings("null")
	public static ArrayList<String> getYear(int year, int columns) {

		ArrayList<String> lines = new ArrayList<>();

		// Generate all months.
		int currentColumn = 0;
		String previousWeeks[] = null;
		String currentWeeks[] = null;
		for (int i = Calendar.JANUARY; i <= Calendar.DECEMBER; i++) {

			currentWeeks = getMonth(year, i);

			currentColumn++;

			// If it is a new row.
			if (previousWeeks == null) {

				previousWeeks = currentWeeks;

			} else {

				for (int j = 0; j < currentWeeks.length; j++) {

					previousWeeks[j] += " " + currentWeeks[j]; //$NON-NLS-1$
				}
			}

			// If one row is completed.
			if (currentColumn == columns) {

				for (int j = 0; j < currentWeeks.length; j++) {

					lines.add(previousWeeks[j]);
				}
				currentColumn = 0;
				previousWeeks = null;
			}
		}

		// Generate the last month row if necessary.
		if (currentColumn != columns && currentColumn != 0) {

			for (int j = 0; j < currentWeeks.length; j++) {

				lines.add(previousWeeks[j]);
			}
			currentColumn = 0;
			previousWeeks = null;
		}

		return lines;

	}

	/**
	 * Generate one specific month.
	 * 
	 * @param year
	 *            The year.
	 * @param month
	 *            The month.
	 * @return The calendar of a specific month.
	 */
	public static String[] getMonth(int year, int month) {

		// A month has at most 6 weeks plus 2 titles.
		String weeks[] = new String[8];
		for (int i = 0; i < weeks.length; i++) {
			weeks[i] = new String();
		}

		int thisMonth = month + 1;
		weeks[0] = "          " //$NON-NLS-1$
				+ (thisMonth < 10 ? " " + thisMonth : "" + thisMonth) //$NON-NLS-1$ //$NON-NLS-2$
				+ "         "; //$NON-NLS-1$
		weeks[1] = "  " + Text.get(Text.FILTER_CALENDARFILTER_CALENDAR_TITLE); //$NON-NLS-1$

		// Initialize the calendar.
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1, 1, 0, 0);

		// Append the spaces before the first day.
		int spaces = 0;
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {

		case (Calendar.SUNDAY): {
			spaces = 0;
			break;
		}
		case (Calendar.MONDAY): {
			spaces = 3;
			break;
		}
		case (Calendar.TUESDAY): {
			spaces = 6;
			break;
		}
		case (Calendar.WEDNESDAY): {
			spaces = 9;
			break;
		}
		case (Calendar.THURSDAY): {
			spaces = 12;
			break;
		}
		case (Calendar.FRIDAY): {
			spaces = 15;
			break;
		}
		case (Calendar.SATURDAY): {
			spaces = 18;
			break;
		}
		default:
			// Impossible.
			break;
		}

		// Create the necessary spaces for the first week.
		for (int i = 0; i < spaces; i++) {
			weeks[2] += " "; //$NON-NLS-1$
		}

		// Create the month.
		int currentWeek = 2;
		while (calendar.get(Calendar.MONTH) == month) {

			int dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);
			String dayOfTheMonthText = dayOfTheMonth < 10 ? "  " //$NON-NLS-1$
					+ dayOfTheMonth : " " + dayOfTheMonth; //$NON-NLS-1$

			weeks[currentWeek] += dayOfTheMonthText;

			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				currentWeek++;
			}

			calendar.add(Calendar.DAY_OF_MONTH, 1);

		}

		int length = weeks[currentWeek].length();
		int toAppend = 21 - length;
		for (int i = 0; i < toAppend; i++) {
			weeks[currentWeek] += " "; //$NON-NLS-1$
		}

		for (int i = currentWeek + 1; i < weeks.length; i++) {
			weeks[i] += "                     "; //$NON-NLS-1$
		}
		return weeks;
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

		return Text.get(Text.FILTER_CALENDARFILTER_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_CALENDARFILTER_EXAMPLE_OUTPUT);
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
				Behavior.ACCEPT_AUTOMATIC_BEHAVIOR,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}
}
