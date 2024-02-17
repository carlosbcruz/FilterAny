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

package com.carlosbcruz.filterany.filters;

/**
 * Structure the possible action using arrows.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface CharacterMoveArrowListenerInterface {

	/**
	 * Indicate that the go up arrow was pressed.
	 */
	public void goUp();

	/**
	 * Indicate that the go down arrow was pressed.
	 */
	public void goDown();

	/**
	 * Indicate that the go right arrow was pressed.
	 */
	public void goRight();

	/**
	 * Indicate that the go left arrow was pressed.
	 */
	public void goLeft();

}
