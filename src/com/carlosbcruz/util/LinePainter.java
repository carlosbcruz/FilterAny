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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 * Track the movement of the Caret by painting a background line at the current
 * caret position.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class LinePainter implements Highlighter.HighlightPainter,
		CaretListener, MouseListener {

	// The text component.
	private JTextComponent component;

	// The color used.
	private Color color;

	// The last rectangle drawn.
	private Rectangle lastView;

	private int lastComponentWidth = 0;

	/**
	 * The line color will be calculated automatically by attempting to make the
	 * current selection lighter by a factor of 1.2.
	 * 
	 * @param component
	 *            Text component that requires background line painting
	 */
	public LinePainter(JTextComponent component) {

		this(component, null);

		setLighter(component.getSelectionColor());
	}

	/**
	 * Manually control the line color.
	 * 
	 * @param component
	 *            Text component that requires background line painting
	 * @param color
	 *            The color of the background line
	 */
	public LinePainter(JTextComponent component, Color color) {

		this.component = component;

		setColor(color);

		// Add listeners so we know when to change highlighting
		component.addCaretListener(this);
		component.addMouseListener(this);

		// Turn highlighting on by adding a dummy highlight
		try {

			component.getHighlighter().addHighlight(0, 0, this);

		} catch (BadLocationException exception) {

			// Error is irrelevant.

		}
	}

	/**
	 * You can reset the line color at any time
	 * 
	 * @param color
	 *            The color of the background line
	 */
	public void setColor(Color color) {

		this.color = color;
	}

	/**
	 * Calculate the line color by making the selection color lighter.
	 * 
	 * @param color
	 *            The color of the background line
	 */
	public void setLighter(Color color) {

		int red = Math.min(255, (int) (color.getRed() * 1.2));
		int green = Math.min(255, (int) (color.getGreen() * 1.2));
		int blue = Math.min(255, (int) (color.getBlue() * 1.2));

		setColor(new Color(red, green, blue));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.text.Highlighter.HighlightPainter#paint(java.awt.Graphics,
	 * int, int, java.awt.Shape, javax.swing.text.JTextComponent)
	 */
	@Override
	public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c) {

		// Paint the background highlight
		try {

			Rectangle r = c.modelToView(c.getCaretPosition());

			g.setColor(this.color);

			g.fillRect(0, r.y, this.lastComponentWidth = c.getWidth(), r.height);

			this.lastView = r;

		} catch (BadLocationException exception) {

			// Not too big deal.

		}
	}

	/**
	 * Caret position has changed, remove the highlight
	 */
	private void resetHighlight() {

		int offset = this.component.getCaretPosition();
		try {
			Rectangle currentView = this.component.modelToView(offset);

			// Remove the highlighting from the previously highlighted
			// line
			if (this.lastView != null) {
				this.component.repaint(0, this.lastView.y,
						this.lastComponentWidth, this.lastView.height);
			}

			this.lastView = currentView;

		} catch (BadLocationException exception) {

			// Not too big deal.

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.CaretListener#caretUpdate(javax.swing.event.CaretEvent)
	 */
	@Override
	public void caretUpdate(CaretEvent event) {

		resetHighlight();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {

		resetHighlight();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {

		// Do nothing.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {

		// Do nothing.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {

		// Do nothing.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {

		// Do nothing.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	public void mouseDragged(MouseEvent event) {

		resetHighlight();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent event) {

		// Do nothing.

	}
}
