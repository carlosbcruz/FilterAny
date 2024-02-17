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

import java.util.Date;

/**
 * Common time control support
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TimeSupport {

	private static Date startDate = null;

	private static Date endDate = null;

	/**
	 * Indicate to start counting the time
	 */
	public static void startChronometer() {

		startDate = new Date();
	}

	/**
	 * Indicate to stop counting the time
	 */
	public static void stopChronometer() {

		endDate = new Date();
	}

	/**
	 * Provide a textual report of the time measured.
	 * 
	 * @return the textual report
	 */
	@SuppressWarnings("nls")
	public static StringBuffer getExecutionTime() {

		StringBuffer output = new StringBuffer();

		output.append(InternalResource.get(InternalResource.START_DATE) + ": "
				+ Resource.formatDate(startDate) + CommonConstants.NEW_LINE);

		output.append(InternalResource.get(InternalResource.END_DATE) + ": "
				+ Resource.formatDate(endDate) + CommonConstants.NEW_LINE);

		long seconds = endDate.getTime() - startDate.getTime();
		seconds /= 1000;

		output.append(InternalResource.get(InternalResource.SECONDS) + ": "
				+ seconds + CommonConstants.NEW_LINE);

		long minutes = seconds / 60;
		long remainingSeconds = (seconds - (minutes * 60));

		String insertZero = (remainingSeconds < 10) ? "0" : "";

		output.append(InternalResource.get(InternalResource.MINUTES) + ": "
				+ minutes + ":" + insertZero + remainingSeconds
				+ CommonConstants.NEW_LINE);

		return output;
	}

}
