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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTextField;

/**
 * Field that clean the content by shaking the mouse over it.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class AutoCleanUpTextField extends JTextField implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;

	// Store the content that was cleaned in
	// case it is necessary to return it back.
	private String previousContent = ""; //$NON-NLS-1$

	// Start and end of the rotating buffer.
	private int currentPositionStart = 0, currentPositionEnd = 0;

	// The maximum duration an element can exist on the rotating buffer.
	private static final long MAXIMUM_DURATION_MILLISECONDS = 50;

	// Size of rotating buffer.
	private static final int ROTATING_BUFFER_SIZE = 22;
	// Movement rotating buffer.
	private long movementsRotatingBuffer[] = new long[ROTATING_BUFFER_SIZE];

	// Indicate mouse is over the text field.
	private boolean inside = false;

	// Indicate the last clean up command was issued.
	private long lastCleanup = 0;

	/**
	 * Constructor.
	 * 
	 * @param activated
	 *            Indicate if the clean-up behavior is activated.
	 */
	public AutoCleanUpTextField(boolean activated) {

		super();

		if (activated) {
			addMouseListener(this);
			addMouseMotionListener(this);
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param columns
	 *            The number of columns,
	 * @param activated
	 *            Indicate if the clean-up behavior is activated.
	 */
	public AutoCleanUpTextField(int columns, boolean activated) {

		super(columns);

		if (activated) {
			addMouseListener(this);
			addMouseMotionListener(this);
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param text
	 *            The text inside the text field.
	 * @param columns
	 *            The number of columns.
	 * @param activated
	 *            Indicate if the clean-up behavior is activated.
	 */
	public AutoCleanUpTextField(String text, int columns, boolean activated) {

		super(text, columns);

		if (activated) {
			addMouseListener(this);
			addMouseMotionListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Do nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

		this.inside = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {

		this.inside = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// Do nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// Do nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// Do nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		// Log event and if clean up command is issued
		// then clear the field or bring previous content.
		if (logMovement(System.currentTimeMillis())) {

			String text = getText();
			if ("".equals(text)) { //$NON-NLS-1$

				setText(this.previousContent);

			} else {

				this.previousContent = text;
				setText(""); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Log a movement and identify if a clean up command is issued.
	 * 
	 * @param currentMilliseconds
	 *            The current mouse movement milliseconds.
	 * @return True if clean up command is identified.
	 */
	private boolean logMovement(long currentMilliseconds) {

		// Only log if mouse is inside the text field.
		if (!this.inside) {
			return false;
		}

		// Clear old movements.
		clearMovements(currentMilliseconds);

		// Next buffer position.
		int nextPosition = this.currentPositionEnd + 1;

		// Is end of buffer found?
		if (nextPosition >= this.movementsRotatingBuffer.length) {
			nextPosition = 0;
		}

		// If buffer is full then issue a clean up event.
		if (nextPosition == this.currentPositionStart) {

			this.currentPositionStart = this.currentPositionEnd = 0;

			if (currentMilliseconds - this.lastCleanup < 500) {

				this.lastCleanup = currentMilliseconds;

				// Clean up command too soon.
				return false;
			}

			this.lastCleanup = currentMilliseconds;

			return true;
		}

		// Log current
		this.currentPositionEnd = nextPosition;

		this.movementsRotatingBuffer[this.currentPositionEnd] = currentMilliseconds;

		return false;
	}

	/**
	 * Clear old movements logs.
	 * 
	 * @param currentMilliseconds
	 *            The current mouse movement milliseconds.
	 */
	private void clearMovements(long currentMilliseconds) {

		// While movements are old and buffer has element.
		while (currentMilliseconds
				- this.movementsRotatingBuffer[this.currentPositionStart] > MAXIMUM_DURATION_MILLISECONDS
				&& this.currentPositionStart != this.currentPositionEnd) {

			// Remove the element.
			this.currentPositionStart++;

			if (this.currentPositionStart >= this.movementsRotatingBuffer.length) {

				this.currentPositionStart = 0;
			}
		}
	}
}