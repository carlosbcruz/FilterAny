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

import java.io.Serializable;

/**
 * Store a duration information.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class DurationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	long days = 0L;
	long hours = 0L;
	long minutes = 0L;
	long seconds = 0L;
	long totalMinutes = 0L;
	long totalHours = 0L;

	/**
	 * Default constructor.
	 */
	public DurationBean() {

		super();
	}

	/**
	 * @param long days.
	 * @param long hours.
	 * @param long minutes.
	 * @param long seconds.
	 * @param long totalMinutes
	 * @param long totalHours.
	 */
	public DurationBean(long days, long hours, long minutes, long seconds,
			long totalMinutes, long totalHours) {

		super();

		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.totalMinutes = totalMinutes;
		this.totalHours = totalHours;
	}

	/**
	 * Provide: The number of days.
	 * 
	 * @return days The number of days.
	 */
	public long getDays() {
		return this.days;
	}

	/**
	 * Store: The number of days.
	 * 
	 * @param days
	 *            The number of days.
	 */
	public void setDays(long days) {
		this.days = days;
	}

	/**
	 * Provide: The number of hours.
	 * 
	 * @return hours The number of hours.
	 */
	public long getHours() {
		return this.hours;
	}

	/**
	 * Store: The number of hours.
	 * 
	 * @param hours
	 *            The number of hours.
	 */
	public void setHours(long hours) {
		this.hours = hours;
	}

	/**
	 * Provide: The number of minutes.
	 * 
	 * @return minutes The number of minutes.
	 */
	public long getMinutes() {
		return this.minutes;
	}

	/**
	 * Store: The number of minutes.
	 * 
	 * @param minutes
	 *            The number of minutes.
	 */
	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}

	/**
	 * Provide: The number of seconds.
	 * 
	 * @return seconds The number of seconds.
	 */
	public long getSeconds() {
		return this.seconds;
	}

	/**
	 * Store: The number of seconds.
	 * 
	 * @param seconds
	 *            The number of seconds.
	 */
	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}

	/**
	 * Provide: The total number of minutes.
	 * 
	 * @return totalHours The total number of minutes.
	 */
	public long getTotalMinutes() {
		return this.totalMinutes;
	}

	/**
	 * Store: The total number of minutes.
	 * 
	 * @param totalHours
	 *            The total number of minutes.
	 */
	public void setTotalMinutes(long totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	/**
	 * Provide: The total number of hours.
	 * 
	 * @return totalHours The total number of hours.
	 */
	public long getTotalHours() {
		return this.totalHours;
	}

	/**
	 * Store: The total number of hours.
	 * 
	 * @param totalHours
	 *            The total number of hours.
	 */
	public void setTotalHours(long totalHours) {
		this.totalHours = totalHours;
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

		out.append("\tdays=" + this.days + ",\n");
		out.append("\thours=" + this.hours + ",\n");
		out.append("\tminutes=" + this.minutes + ",\n");
		out.append("\tseconds=" + this.seconds + ",\n");
		out.append("\ttotalMinutes=" + this.totalMinutes + ",\n");
		out.append("\ttotalHours=" + this.totalHours + "]\n");

		return out.toString();
	}

}