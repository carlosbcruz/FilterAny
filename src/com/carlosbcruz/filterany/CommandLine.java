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
 * Command line listeners.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface CommandLine {

	/**
	 * Show the command help
	 */
	public String getHelpDescription();

	/**
	 * Inform the filter small description.
	 * 
	 * @return The small description.
	 */
	public String getSmallDescription();

	/**
	 * Inform the command name.
	 * 
	 * @return The command name.
	 */
	public String getCommandName();

}
