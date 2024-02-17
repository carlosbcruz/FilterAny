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

package com.carlosbcruz.util;

/**
 * Provide util methods to work with durations.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class DurationUtil {

	/**
	 * Provide a representation of a duration.
	 * 
	 * @param The
	 *            text representation of a duration.
	 * @return Return the duration of a total o milliseconds.
	 */
	public static DurationBean getDuration(long millisParameter) {

		long millis = millisParameter;

		long seconds = millis / 1000;

		long minutes = seconds / 60;

		long hours = minutes / 60;

		long days = hours / 24;

		long totalMinutes = millis / 1000l / 60L;

		long totalHours = millis / 1000l / 60L / 60L;

		if (days >= 1) {

			millis -= 24l * 60L * 60L * 1000L * days;

			seconds = millis / 1000L;

			minutes = seconds / 60L;

			hours = minutes / 60L;

		}

		if (hours >= 1) {

			millis -= 60L * 60L * 1000L * hours;

			seconds = millis / 1000L;

			minutes = seconds / 60L;

		}

		if (minutes >= 1) {

			millis -= 60L * 1000L * minutes;

			seconds = millis / 1000L;
		}

		return new DurationBean(days, hours, minutes, seconds, totalMinutes,
				totalHours);
	}

}
