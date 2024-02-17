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
 * Indicate that a filter has a special behavior.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface SpecialBehavior {

	public enum Behavior {
		/*
		 * Indicate that the filter does not apply to a set of files. Instead it
		 * only treat the top text as a text filed and changes it. Therefore,
		 * the new target button does not appear.
		 */
		WORK_ONLY_ON_TEXT,
		/*
		 * Indicate that the filter receives the input from Main Input and
		 * Auxiliar Input and treat they together.
		 */
		WORK_ON_DUAL_PANE,
		/*
		 * Indicate that the filter can be executed automatically.
		 */
		ACCEPT_AUTOMATIC_BEHAVIOR,
		/*
		 * Indicate that the filter is a demonstration when demonstration mode
		 * is active. The demonstration icon is presented.
		 */
		DEMONSTRATION_ICON,
		/*
		 * The inverse of demonstration icon.
		 */
		NORMAL_ICON,
		/*
		 * Do not show the target directory when in file mode.
		 */
		DO_NOT_SHOW_TARGET_DIRECTORY,
		/*
		 * Receive the entire input text from file list, instead of line by
		 * line.
		 */
		BINARY_RECEIVE_FULL_FILE_LIST,
		/*
		 * Indicate that the filter will create and handle it`s own thread
		 * and will be showing it`s own message on the task manager.
		 */
		THE_FILTER_WILL_CONTROL_THE_THREAD,
		/*
		 * Indicate that the the binary filter will generate an output content
		 * as the return of the filter. It is relevant to execute this filter
		 * through command line.
		 */
		BINARY_FILTER_GENERATE_OUTPUT_CONTENT
	}

	/**
	 * Indicate which special behavior a filter may implement.
	 * 
	 * @return The special behavior list.
	 */
	public Behavior[] getSpecialBehavior();
}
