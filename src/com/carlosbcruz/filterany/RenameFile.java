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
 * Allow the filtersWindow to rename files
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface RenameFile {

	/**
	 * Provide the new file name.
	 * 
	 * @param fileName
	 *            the file name.
	 * @return the new file name.
	 * @throws FilterException
	 *             If a problem happened.
	 */
	public String getNewFileName(String fileName) throws FilterException;

}
