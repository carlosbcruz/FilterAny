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
 * Command line listeners.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface CommandLineListener {

	/**
	 * Show the available commands.
	 */
	public String getAvailableCommandsDescription();

	/**
	 * Execute a command with specific arguments.
	 * 
	 * @param command
	 *            The command to be executed.
	 * @param arguments
	 *            The list of arguments.
	 */
	public void executeCommand(String command, String arguments[]);

}
