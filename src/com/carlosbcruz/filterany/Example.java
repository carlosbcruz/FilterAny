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
 * Provides example of the use of the filter.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface Example {

	/**
	 * Get the content of the main input example.
	 */
	public String getMainInputExample();

	/**
	 * Get the content of the auxiliar input example.
	 */
	public String getAuxiliarInputExample();

	/**
	 * Get the content of the output example.
	 */
	public String getOutputExample();

}
