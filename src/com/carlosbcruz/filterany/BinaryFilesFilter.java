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
 * This filter works on binary files only.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface BinaryFilesFilter {

	/**
	 * @param fileFrom
	 *            The source file.
	 * @param fileTo
	 *            The target file.
	 * @return The execution status.
	 * @throws FilterException
	 *             If a problem happens.
	 */
	public String filter(String fileFrom, String fileTo) throws FilterException;

}
