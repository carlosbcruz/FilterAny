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
 * Implementation of a simple action that can be subject to be observed.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface SimpleActionSubject {

	/**
	 * Add an observer
	 * 
	 * @param observer
	 *            The observer.
	 */
	public void addObserver(SimpleActionObserver observer);

	/**
	 * Remove an observer
	 * 
	 * @param observer
	 *            The observer.
	 */
	public void removeObserver(SimpleActionObserver observer);

	/**
	 * Notify the observers that a change has occurred
	 */
	public void notifyChange();

}
