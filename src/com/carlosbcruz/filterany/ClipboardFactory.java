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

import com.carlosbcruz.util.ClipboardUtil;

/**
 * Controls the clipboard.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ClipboardFactory {

	private static ClipboardUtil clipboard = new ClipboardUtil();

	/**
	 * Provide the clipboard.
	 * 
	 * @return The clipboard.
	 */
	public static ClipboardUtil getClipboardInstance() {

		return clipboard;
	}
}
