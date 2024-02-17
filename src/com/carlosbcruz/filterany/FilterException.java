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
package com.carlosbcruz.filterany;

/**
 * Indicate that a problem is found on the filter.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Only allows simple messages.
	 * 
	 * @param message
	 *            The message.
	 */
	public FilterException(String message) {
		super(message);
	}

	/**
	 * Only allows simple messages.
	 * 
	 * @param message
	 *            The message.
	 */
	@SuppressWarnings("nls")
	public FilterException(String message, String complement) {
		super(message + " - (" + complement + ")");
	}
}
