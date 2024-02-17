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

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.text.Caret;

/**
 * This is a JTextArea that can draw an image as a background.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextAreaWithImageBackground extends JTextArea {

	private static final long serialVersionUID = 1L;

	// Backup of caret.
	transient private Caret backuptCaret = null;

	transient Image image = null;

	{
		setOpaque(false);
	}

	/**
	 * Constructor.
	 */
	public TextAreaWithImageBackground() {

		this.backuptCaret = getCaret();
	}

	/**
	 * Set the background image.
	 * 
	 * @param image
	 */
	public void setBackGroundImage(Image image) {

		this.image = image;
	}

	/**
	 * Restore previous caret.
	 */
	public void restoreCaret() {

		setCaret(this.backuptCaret);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {

		if (this.image != null) {
			g.drawImage(this.image, 0, 0, this);
		}

		super.paintComponent(g);
	}
}
