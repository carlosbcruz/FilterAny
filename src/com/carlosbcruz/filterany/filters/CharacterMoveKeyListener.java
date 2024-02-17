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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Listen to key events for the free character move.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CharacterMoveKeyListener extends KeyAdapter {

	private static CharacterMoveKeyListener instance = null;

	private static CharacterMoveArrowListenerInterface listener = null;

	/**
	 * Provides an instance of the singleton CharacterMoveKeyListener.
	 * 
	 * @return the instance of CharacterMoveKeyListener.
	 */
	public static CharacterMoveKeyListener getInstance() {

		if (instance == null) {
			instance = new CharacterMoveKeyListener();
		}
		return instance;
	}

	/**
	 * Set the arrow listener.
	 * 
	 * @param listenerParameter
	 *            The arrow listener.
	 */
	public static void setArrowListener(
			CharacterMoveArrowListenerInterface listenerParameter) {

		listener = listenerParameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent event) {

		if (listener != null) {

			switch (event.getKeyCode()) {
			case (KeyEvent.VK_UP): {
				listener.goUp();
				break;
			}
			case (KeyEvent.VK_DOWN): {
				listener.goDown();
				break;
			}
			case (KeyEvent.VK_RIGHT): {
				listener.goRight();
				break;
			}
			case (KeyEvent.VK_LEFT): {
				listener.goLeft();
				break;
			}
			default:
				// Ignore other keys
				break;
			}
		}
	}
}
