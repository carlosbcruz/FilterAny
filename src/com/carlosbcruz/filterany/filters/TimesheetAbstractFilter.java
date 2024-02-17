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

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Provide the basic features for time sheet control.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public abstract class TimesheetAbstractFilter extends FilterAdapter {

	private static final long serialVersionUID = 1L;

	protected static ArrayList<PeriodBean> periods = null;

	@SuppressWarnings("nls")
	private static final String possibleNameOfTheDays[] = { "Sun", "Mon",
			"Tue", "Wed", "Thu", "Fri", "Sat" };

	/**
	 * Store a period with a start and end.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	static class PeriodBean implements Serializable {

		private static final long serialVersionUID = 1L;

		public static final String START = "start"; //$NON-NLS-1$
		public static final String STOP = "stop"; //$NON-NLS-1$

		private int startDay = 0;
		private int startMonth = 0;
		private int startYear = 0;
		private int startHour = 0;
		private int startMinute = 0;
		private int startSecond = 0;
		private long startMillis = 0;
		private String startComment = new String();
		private int stopDay = 0;
		private int stopMonth = 0;
		private int stopYear = 0;
		private int stopHour = 0;
		private int stopMinute = 0;
		private int stopSecond = 0;
		private String stopComment = new String();
		private long stopMillis = 0;

		/**
		 * Provide: The start day.
		 * 
		 * @return startDay The start day.
		 */
		public int getStartDay() {
			return this.startDay;
		}

		/**
		 * Store: The start day.
		 * 
		 * @param startDay
		 *            The start day.
		 */
		public void setStartDay(int startDay) {
			this.startDay = startDay;
		}

		/**
		 * Provide: The start month.
		 * 
		 * @return startMonth The start month.
		 */
		public int getStartMonth() {
			return this.startMonth;
		}

		/**
		 * Store: The start month.
		 * 
		 * @param startMonth
		 *            The start month.
		 */
		public void setStartMonth(int startMonth) {
			this.startMonth = startMonth;
		}

		/**
		 * Provide: The start year.
		 * 
		 * @return startYear The start year.
		 */
		public int getStartYear() {
			return this.startYear;
		}

		/**
		 * Store: The start year.
		 * 
		 * @param startYear
		 *            The start year.
		 */
		public void setStartYear(int startYear) {
			this.startYear = startYear;
		}

		/**
		 * Provide: The start hour.
		 * 
		 * @return startHour The start hour.
		 */
		public int getStartHour() {
			return this.startHour;
		}

		/**
		 * Store: The start hour.
		 * 
		 * @param startHour
		 *            The start hour.
		 */
		public void setStartHour(int startHour) {
			this.startHour = startHour;
		}

		/**
		 * Provide: The start minute.
		 * 
		 * @return startMinute The start minute.
		 */
		public int getStartMinute() {
			return this.startMinute;
		}

		/**
		 * Store: The start minute.
		 * 
		 * @param startMinute
		 *            The start minute.
		 */
		public void setStartMinute(int startMinute) {
			this.startMinute = startMinute;
		}

		/**
		 * Provide: The start second.
		 * 
		 * @return startSecond The start second.
		 */
		public int getStartSecond() {
			return this.startSecond;
		}

		/**
		 * Store: The start second.
		 * 
		 * @param startSecond
		 *            The start second.
		 */
		public void setStartSecond(int startSecond) {
			this.startSecond = startSecond;
		}

		/**
		 * Provide: The total milliseconds for the start date.
		 * 
		 * @return startMillis The total milliseconds for the start date.
		 */
		public long getStartMillis() {
			return this.startMillis;
		}

		/**
		 * Store: The total milliseconds for the start date.
		 * 
		 * @param startMillis
		 *            The total milliseconds for the start date.
		 */
		public void setStartMillis(long startMillis) {
			this.startMillis = startMillis;
		}

		/**
		 * Provide: The comments for the start date.
		 * 
		 * @return startComment The comments for the start date.
		 */
		public String getStartComment() {
			return this.startComment;
		}

		/**
		 * Store: The comments for the start date.
		 * 
		 * @param startComment
		 *            The comments for the start date.
		 */
		public void setStartComment(String startComment) {
			this.startComment = startComment;
		}

		/**
		 * Provide: The stop day.
		 * 
		 * @return stopDay The stop day.
		 */
		public int getStopDay() {
			return this.stopDay;
		}

		/**
		 * Store: The stop day.
		 * 
		 * @param stopDay
		 *            The stop day.
		 */
		public void setStopDay(int stopDay) {
			this.stopDay = stopDay;
		}

		/**
		 * Provide: The stop month.
		 * 
		 * @return stopMonth The stop month.
		 */
		public int getStopMonth() {
			return this.stopMonth;
		}

		/**
		 * Store: The stop month.
		 * 
		 * @param stopMonth
		 *            The stop month.
		 */
		public void setStopMonth(int stopMonth) {
			this.stopMonth = stopMonth;
		}

		/**
		 * Provide: The stop year.
		 * 
		 * @return stopYear The stop year.
		 */
		public int getStopYear() {
			return this.stopYear;
		}

		/**
		 * Store: The stop year.
		 * 
		 * @param stopYear
		 *            The stop year.
		 */
		public void setStopYear(int stopYear) {
			this.stopYear = stopYear;
		}

		/**
		 * Provide: The stop hour.
		 * 
		 * @return stopHour The stop hour.
		 */
		public int getStopHour() {
			return this.stopHour;
		}

		/**
		 * Store: The stop hour.
		 * 
		 * @param stopHour
		 *            The stop hour.
		 */
		public void setStopHour(int stopHour) {
			this.stopHour = stopHour;
		}

		/**
		 * Provide: The stop minute.
		 * 
		 * @return stopMinute The stop minute.
		 */
		public int getStopMinute() {
			return this.stopMinute;
		}

		/**
		 * Store: The stop minute.
		 * 
		 * @param stopMinute
		 *            The stop minute.
		 */
		public void setStopMinute(int stopMinute) {
			this.stopMinute = stopMinute;
		}

		/**
		 * Provide: The stop second.
		 * 
		 * @return stopSecond The stop second.
		 */
		public int getStopSecond() {
			return this.stopSecond;
		}

		/**
		 * Store: The stop second.
		 * 
		 * @param stopSecond
		 *            The stop second.
		 */
		public void setStopSecond(int stopSecond) {
			this.stopSecond = stopSecond;
		}

		/**
		 * Provide: The total milliseconds for the stop date.
		 * 
		 * @return stopComment The total milliseconds for the stop date.
		 */
		public String getStopComment() {
			return this.stopComment;
		}

		/**
		 * Store: The total milliseconds for the stop date.
		 * 
		 * @param stopComment
		 *            The total milliseconds for the stop date.
		 */
		public void setStopComment(String stopComment) {
			this.stopComment = stopComment;
		}

		/**
		 * Provide: The comments for the stop date.
		 * 
		 * @return stopMillis The comments for the stop date.
		 */
		public long getStopMillis() {
			return this.stopMillis;
		}

		/**
		 * Store: The comments for the stop date.
		 * 
		 * @param stopMillis
		 *            The comments for the stop date.
		 */
		public void setStopMillis(long stopMillis) {
			this.stopMillis = stopMillis;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@SuppressWarnings("nls")
		@Override
		public String toString() {

			StringBuffer out = new StringBuffer();

			out.append("ClassName [\n");

			out.append("\tstartDay=" + this.startDay + ",\n");
			out.append("\tstartMonth=" + this.startMonth + ",\n");
			out.append("\tstartYear=" + this.startYear + ",\n");
			out.append("\tstartHour=" + this.startHour + ",\n");
			out.append("\tstartMinute=" + this.startMinute + ",\n");
			out.append("\tstartSecond=" + this.startSecond + ",\n");
			out.append("\tstartMillis=" + this.startMillis + ",\n");
			out.append("\tstartComment=" + this.startComment + ",\n");
			out.append("\tstopDay=" + this.stopDay + ",\n");
			out.append("\tstopMonth=" + this.stopMonth + ",\n");
			out.append("\tstopYear=" + this.stopYear + ",\n");
			out.append("\tstopHour=" + this.stopHour + ",\n");
			out.append("\tstopMinute=" + this.stopMinute + ",\n");
			out.append("\tstopSecond=" + this.stopSecond + ",\n");
			out.append("\tstopComment=" + this.stopComment + ",\n");
			out.append("\tstopMillis=" + this.stopMillis + "]\n");

			return out.toString();
		}
	}

	/**
	 * Retrieve the periods from file.
	 * 
	 * @param lines
	 *            The lines to be analyzed.
	 * @return The result from the lines analysis.
	 */
	@SuppressWarnings("null")
	protected static StringBuffer retrievePeriods(String lines[]) {

		// Initialize the periods array.
		periods = new ArrayList<>();

		StringBuffer out = new StringBuffer();

		// The lines have to alternate Start and Stop.
		boolean isStart = true;

		long previousTime = 0;

		PeriodBean period = null;
		for (int i = 0; i < lines.length; i++) {

			// Create a new bean if it is a start.
			if (isStart) {
				period = new PeriodBean();
			}

			String lineNumber = String.valueOf(i + 1);
			String originalLine = lines[i].trim();

			// Ignore empty lines.
			if ("".equals(originalLine)) { //$NON-NLS-1$

				continue;
			}

			String workingLine = originalLine;

			// Verify if there is enough room for a start or stop command.
			if (workingLine.length() < PeriodBean.START.length()
					|| workingLine.length() < PeriodBean.STOP.length()) {

				// There can not exist a start or end command.
				StringBuffer message = new StringBuffer();

				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));

				if (isStart) {
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION2));
				} else {
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION3));
				}
				out.append(message);
				return out;
			}

			// Verify if the line starts with the start command.
			String command = null;
			if (isStart) {
				command = workingLine.substring(0, PeriodBean.START.length());
				if (!command.toUpperCase().equals(
						PeriodBean.START.toUpperCase())) {
					// There can not exist a start or end command.
					StringBuffer message = new StringBuffer();
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
							lineNumber, originalLine));
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION4));
					out.append(message);
					return out;
				}
			} else {
				command = workingLine.substring(0, PeriodBean.STOP.length());
				if (!command.toUpperCase()
						.equals(PeriodBean.STOP.toUpperCase())) {
					// There can not exist a start or end command.
					StringBuffer message = new StringBuffer();
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
							lineNumber, originalLine));
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION5));
					out.append(message);
					return out;
				}
			}

			// Remove the start command to continue the parsing.
			if (isStart) {
				workingLine = workingLine.substring(PeriodBean.START.length())
						.trim();
			} else {
				workingLine = workingLine.substring(PeriodBean.STOP.length())
						.trim();
			}

			// Verify if comma can exist.
			if (workingLine.length() == 0) {
				// There is no date and time information.
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION6));
				out.append(message);
				return out;
			}

			// Verify comma.
			String comma = workingLine.substring(0, 1);
			if (!",".equals(comma)) { //$NON-NLS-1$
				// After the start there should be a comma.
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				if (isStart) {
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION7));
				} else {
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION8));
				}
				out.append(message);
				return out;
			}

			// Remove the comma.
			workingLine = workingLine.substring(1).trim();

			// Verify if the day of the week is there.
			if (workingLine.length() < 3) {
				// After the comma there should be the name of the day.
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				if (isStart) {
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION9));
				} else {
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION10));
				}
				out.append(message);
				return out;
			}

			String nameOfTheDay = workingLine.substring(0, 3);

			// The name of the week is optional. Verify if it exist.
			if (Character.isLetter(nameOfTheDay.charAt(0))) {

				boolean found = false;
				for (int j = 0; j < possibleNameOfTheDays.length; j++) {
					if (possibleNameOfTheDays[j].toUpperCase().equals(
							nameOfTheDay.toUpperCase())) {
						found = true;
						break;
					}
				}

				if (!found) {
					// The name of the day was not one of the valid ones.
					StringBuffer message = new StringBuffer();
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
							lineNumber, originalLine));
					if (isStart) {
						message.append(Text
								.get(Text.FILTER_TIMESHEET_EXCEPTION11));
					} else {
						message.append(Text
								.get(Text.FILTER_TIMESHEET_EXCEPTION12));
					}
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION13));

					out.append(message);
					return out;
				}

				// Remove the name of the day.
				workingLine = workingLine.substring(3).trim();
			}

			// See if there are more information.
			if (workingLine.length() == 0) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION14));
				out.append(message);
				return out;
			}

			// See if there is a valid date separator.
			int index = workingLine.indexOf("/"); //$NON-NLS-1$
			if (index == -1) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION15));
				out.append(message);
				return out;
			}

			// Try to get the month.
			String monthParameter = workingLine.substring(0, index);
			int month = 0;
			try {
				month = Integer.parseInt(monthParameter);
			} catch (NumberFormatException exception) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION16));
				out.append(message);
				return out;
			}
			if (month < 1 || month > 12) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION17));
				out.append(message);
				return out;
			}

			// Internally the months start with zero.
			month--;

			// Remove the month.
			workingLine = workingLine.substring(index + 1).trim();

			// See if there are more information.
			if (workingLine.length() == 0) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION18));
				out.append(message);
				return out;
			}

			// See if there is a valid date separator.
			index = workingLine.indexOf("/"); //$NON-NLS-1$
			if (index == -1) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION19));
				out.append(message);
				return out;
			}

			// Try to get the day.
			String dayParameter = workingLine.substring(0, index).trim();
			int day = 0;
			try {
				day = Integer.parseInt(dayParameter);
			} catch (NumberFormatException exception) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION20));
				out.append(message);
				return out;
			}
			if (day < 1 || day > 31) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION21));
				out.append(message);
				return out;
			}

			// Remove the day.
			workingLine = workingLine.substring(index + 1).trim();

			// See if there are more information.
			if (workingLine.length() == 0) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION22));
				out.append(message);
				return out;
			}

			// See if there is a valid date separator.
			index = workingLine.indexOf(","); //$NON-NLS-1$
			if (index == -1) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION23));
				out.append(message);
				return out;
			}

			// Try to get the year.
			String yearParameter = workingLine.substring(0, index).trim();
			int year = 0;
			try {
				year = Integer.parseInt(yearParameter);
			} catch (NumberFormatException exception) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION24));
				out.append(message);
				return out;
			}
			if (year < 1900 || year > 3000) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION25));
				out.append(message);
				return out;
			}

			// Remove the year.
			workingLine = workingLine.substring(index + 1).trim();

			// See if there are more information.
			if (workingLine.length() == 0) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION26));
				out.append(message);
				return out;
			}

			// See if there is a valid time separator.
			index = workingLine.indexOf(":"); //$NON-NLS-1$
			if (index == -1) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION27));
				out.append(message);
				return out;
			}

			// Try to get the hour.
			String hourParameter = workingLine.substring(0, index).trim();
			int hour = 0;
			try {
				hour = Integer.parseInt(hourParameter);
			} catch (NumberFormatException exception) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION28));
				out.append(message);
				return out;
			}
			if (hour < 0 || hour > 23) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION29));
				out.append(message);
				return out;
			}

			// Remove the hour.
			workingLine = workingLine.substring(index + 1).trim();

			// See if there are more information.
			if (workingLine.length() == 0) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION30));
				out.append(message);
				return out;
			}

			// See if there is a valid time separator.
			index = workingLine.indexOf(":"); //$NON-NLS-1$
			if (index == -1) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION31));
				out.append(message);
				return out;
			}

			// Try to get the minutes.
			String minuteParameter = workingLine.substring(0, index).trim();
			int minute = 0;
			try {
				minute = Integer.parseInt(minuteParameter);
			} catch (NumberFormatException exception) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION32));
				out.append(message);
				return out;
			}
			if (minute < 0 || minute > 59) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION33));
				out.append(message);
				return out;
			}

			// Remove the minutes.
			workingLine = workingLine.substring(index + 1).trim();

			// See if there are more information.
			if (workingLine.length() == 0) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION34));
				out.append(message);
				return out;
			}

			// See if there is a valid time separator.
			index = workingLine.indexOf(","); //$NON-NLS-1$
			if (index == -1) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION35));
				out.append(message);
				return out;
			}

			// Try to get the second.
			String secondParameter = workingLine.substring(0, index).trim();
			int second = 0;
			try {
				second = Integer.parseInt(secondParameter);
			} catch (NumberFormatException exception) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION36));
				out.append(message);
				return out;
			}
			if (second < 0 || second > 59) {
				StringBuffer message = new StringBuffer();
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
						lineNumber, originalLine));
				message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION37));
				out.append(message);
				return out;
			}

			String comment = workingLine.substring(index + 1).trim();

			// Populate the correct fields.
			if (isStart) {

				period.setStartMonth(month);
				period.setStartDay(day);
				period.setStartYear(year);
				period.setStartHour(hour);
				period.setStartMinute(minute);
				period.setStartSecond(second);
				period.setStartComment(comment);

				Calendar startCalendar = Calendar.getInstance();
				startCalendar.setTimeZone(TimeZone.getDefault());
				startCalendar.set(Calendar.YEAR, period.getStartYear());
				startCalendar.set(Calendar.MONTH, period.getStartMonth());
				startCalendar.set(Calendar.DAY_OF_MONTH, period.getStartDay());
				startCalendar.set(Calendar.HOUR_OF_DAY, period.getStartHour());
				startCalendar.set(Calendar.MINUTE, period.getStartMinute());
				startCalendar.set(Calendar.SECOND, period.getStartSecond());
				startCalendar.set(Calendar.MILLISECOND, 0);
				if (previousTime > startCalendar.getTimeInMillis()) {
					StringBuffer message = new StringBuffer();
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
							lineNumber, originalLine));
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION38));
					out.append(message);
					return out;
				}
				previousTime = startCalendar.getTimeInMillis();

			} else {

				period.setStopMonth(month);
				period.setStopDay(day);
				period.setStopYear(year);
				period.setStopHour(hour);
				period.setStopMinute(minute);
				period.setStopSecond(second);
				period.setStopComment(comment);

				Calendar stopCalendar = Calendar.getInstance();
				stopCalendar.setTimeZone(TimeZone.getDefault());
				stopCalendar.set(Calendar.YEAR, period.getStopYear());
				stopCalendar.set(Calendar.MONTH, period.getStopMonth());
				stopCalendar.set(Calendar.DAY_OF_MONTH, period.getStopDay());
				stopCalendar.set(Calendar.HOUR_OF_DAY, period.getStopHour());
				stopCalendar.set(Calendar.MINUTE, period.getStopMinute());
				stopCalendar.set(Calendar.SECOND, period.getStopSecond());
				stopCalendar.set(Calendar.MILLISECOND, 0);
				if (previousTime > stopCalendar.getTimeInMillis()) {
					StringBuffer message = new StringBuffer();
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION1,
							lineNumber, originalLine));
					message.append(Text.get(Text.FILTER_TIMESHEET_EXCEPTION38));
					out.append(message);
					return out;
				}
				previousTime = stopCalendar.getTimeInMillis();

				periods.add(period);

				period = new PeriodBean();
			}

			isStart = !isStart;
		}

		return out;

	}

	/**
	 * Store a specific date and time.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	protected class DateBean implements Serializable {

		private static final long serialVersionUID = 1L;

		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;

		/**
		 * Default constructor.
		 */
		public DateBean() {

			super();
		}

		/**
		 * @param int year.
		 * @param int month.
		 * @param int day.
		 * @param int hour.
		 * @param int minute.
		 * @param int second.
		 */
		public DateBean(int year, int month, int day, int hour, int minute,
				int second) {

			super();

			this.year = year;
			this.month = month;
			this.day = day;
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}

		/**
		 * Provide: The year
		 * 
		 * @return year The year
		 */
		public int getYear() {
			return this.year;
		}

		/**
		 * Store: The year
		 * 
		 * @param year
		 *            The year
		 */
		public void setYear(int year) {
			this.year = year;
		}

		/**
		 * Provide: The month
		 * 
		 * @return month The month
		 */
		public int getMonth() {
			return this.month;
		}

		/**
		 * Store: The month
		 * 
		 * @param month
		 *            The month
		 */
		public void setMonth(int month) {
			this.month = month;
		}

		/**
		 * Provide: The day
		 * 
		 * @return day The day
		 */
		public int getDay() {
			return this.day;
		}

		/**
		 * Store: The day
		 * 
		 * @param day
		 *            The day
		 */
		public void setDay(int day) {
			this.day = day;
		}

		/**
		 * Provide: The hour
		 * 
		 * @return hour The hour
		 */
		public int getHour() {
			return this.hour;
		}

		/**
		 * Store: The hour
		 * 
		 * @param hour
		 *            The hour
		 */
		public void setHour(int hour) {
			this.hour = hour;
		}

		/**
		 * Provide: The minute
		 * 
		 * @return minute The minute
		 */
		public int getMinute() {
			return this.minute;
		}

		/**
		 * Store: The minute
		 * 
		 * @param minute
		 *            The minute
		 */
		public void setMinute(int minute) {
			this.minute = minute;
		}

		/**
		 * Provide: The second
		 * 
		 * @return second The second
		 */
		public int getSecond() {
			return this.second;
		}

		/**
		 * Store: The second
		 * 
		 * @param second
		 *            The second
		 */
		public void setSecond(int second) {
			this.second = second;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@SuppressWarnings("nls")
		@Override
		public String toString() {

			StringBuffer out = new StringBuffer();

			out.append("ClassName [\n");

			out.append("\tyear=" + this.year + ",\n");
			out.append("\tmonth=" + this.month + ",\n");
			out.append("\tday=" + this.day + ",\n");
			out.append("\thour=" + this.hour + ",\n");
			out.append("\tminute=" + this.minute + ",\n");
			out.append("\tsecond=" + this.second + "]\n");

			return out.toString();
		}

	}

	/**
	 * Transform a milliseconds into a specific date and time.
	 * 
	 * @param milliseconds
	 *            The milliseconds.
	 * @return The date bean.
	 */
	protected DateBean getDateBean(long milliseconds) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);

		return new DateBean(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));

	}

	/**
	 * Calculate the time in milliseconds.
	 * 
	 * @param year
	 *            The year
	 * @param month
	 *            The month
	 * @param dayOfMonth
	 *            The day of the month.
	 * @param hourOfDay
	 *            The hour of the day.
	 * @param minute
	 *            The minutes.
	 * @param second
	 *            The seconds.
	 * @return
	 */
	protected static long getMilliseconds(int year, int month, int dayOfMonth,
			int hourOfDay, int minute, int second) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(TimeZone.getDefault());

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTimeInMillis();
	}

	/**
	 * Calculate the duration of the timesheet.
	 * 
	 * @return The total amount of milliseconds for the entire timesheet.
	 */
	protected static long calculateTimesheet() {

		long totalMillis = 0;
		for (PeriodBean bean : periods) {

			bean.setStartMillis(getMilliseconds(bean.getStartYear(),
					bean.getStartMonth(), bean.getStartDay(),
					bean.getStartHour(), bean.getStartMinute(),
					bean.getStartSecond()));

			bean.setStopMillis(getMilliseconds(bean.getStopYear(),
					bean.getStopMonth(), bean.getStopDay(), bean.getStopHour(),
					bean.getStopMinute(), bean.getStopSecond()));

			long millis = bean.getStopMillis() - bean.getStartMillis();
			totalMillis += millis;

		}

		return totalMillis;
	}

	/**
	 * Provide a long textual representation of a particular date and time.
	 * 
	 * @param milliseconds
	 *            The amount of milliseconds.
	 * @return The textual representation of a particular date and time.
	 */
	protected static String getLongDateDescription(long milliseconds) {

		Date startDate = new Date(milliseconds);

		return DateFormat.getDateInstance(DateFormat.FULL).format(startDate)
				+ " " //$NON-NLS-1$
				+ DateFormat.getTimeInstance(DateFormat.FULL).format(startDate);

	}

	/**
	 * Provide a short textual representation of a particular date and time.
	 * 
	 * @param milliseconds
	 *            The amount of milliseconds.
	 * @return The textual representation of a particular date and time.
	 */
	protected static String getShortDateDescription(long milliseconds) {

		Date startDate = new Date(milliseconds);

		return DateFormat.getDateInstance(DateFormat.SHORT).format(startDate)
				+ " " //$NON-NLS-1$
				+ DateFormat.getTimeInstance(DateFormat.SHORT)
						.format(startDate);

	}

	/**
	 * Inform the name of the day according to the locale.
	 * 
	 * @param milliseconds
	 *            The date and time in milliseconds.
	 * @return The name of the day.
	 */
	protected static String getDayOfTheWeekWithLocale(long milliseconds) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);

		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return Text.get(Text.FILTER_TIMESHEET_SUNDAY);
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			return Text.get(Text.FILTER_TIMESHEET_MONDAY);
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
			return Text.get(Text.FILTER_TIMESHEET_TUESDAY);
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			return Text.get(Text.FILTER_TIMESHEET_WEDNESDAY);
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			return Text.get(Text.FILTER_TIMESHEET_THURSDAY);
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			return Text.get(Text.FILTER_TIMESHEET_FRIDAY);
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			return Text.get(Text.FILTER_TIMESHEET_SATURDAY);
		}

		return null;
	}

	/**
	 * Provide the name of the day in a format specific for the timesheets.
	 * 
	 * @return The name of the day.
	 */
	protected static String getDayOfTheWeek() {

		Calendar calendar = Calendar.getInstance();

		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return possibleNameOfTheDays[0];
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			return possibleNameOfTheDays[1];
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
			return possibleNameOfTheDays[2];
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			return possibleNameOfTheDays[3];
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			return possibleNameOfTheDays[4];
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			return possibleNameOfTheDays[5];
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			return possibleNameOfTheDays[6];
		}

		return null;
	}

	/**
	 * Generate a string with the current date and time.
	 */
	protected static String getCurrentDateAndTime() {

		Calendar calendar = Calendar.getInstance();

		String hourOfTheDay = String
				.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		if (hourOfTheDay.length() == 1) {
			hourOfTheDay = "0" + hourOfTheDay; //$NON-NLS-1$
		}

		String minute = String.valueOf(calendar.get(Calendar.MINUTE));
		if (minute.length() == 1) {
			minute = "0" + minute; //$NON-NLS-1$
		}

		String second = String.valueOf(calendar.get(Calendar.SECOND));
		if (second.length() == 1) {
			second = "0" + second; //$NON-NLS-1$
		}

		return (calendar.get(Calendar.MONTH) + 1) + "/" //$NON-NLS-1$
				+ calendar.get(Calendar.DAY_OF_MONTH) + "/" //$NON-NLS-1$
				+ calendar.get(Calendar.YEAR) + "," + hourOfTheDay + ":" //$NON-NLS-1$ //$NON-NLS-2$
				+ minute + ":" + second; //$NON-NLS-1$
	}

	/**
	 * Retrieve the lines from a text.
	 * 
	 * @param text
	 *            The text.
	 * @return The lines of the text.
	 */
	protected static String[] retrieveLines(StringBuffer text) {

		ArrayList<String> linesArray = new ArrayList<>();
		LineTokenizer tokenizer = new LineTokenizer(text);
		while (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();

			linesArray.add(token);
		}

		String[] lines = new String[linesArray.size()];
		lines = linesArray.toArray(lines);
		return lines;
	}

}
