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

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JButton;

/**
 * Customize the size of the buttom.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CustomSizeButton extends JButton {

	private static final long serialVersionUID = 1L;

	private int width = 0, height = 0;

	/**
	 * Constructor.
	 * 
	 * @param action
	 *            The action.
	 * @param width
	 *            The button width.
	 * @param height
	 *            The button height.
	 */
	public CustomSizeButton(Action action, int width, int height) {

		super(action);

		this.width = width;
		this.height = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {

		return new Dimension(this.width, this.height);
	}
}
