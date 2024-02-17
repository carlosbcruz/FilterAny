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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import com.carlosbcruz.util.StringSupport;

/**
 * Provides an indication of the amount of lines distributed along the text and
 * the part being viewed.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextNavigation extends Component implements MouseListener {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollPane = null;
	private JTextArea textArea = null;
	private Color color = null;

	private TextNavigation thisComponent = null;

	private final int COMPONENT_WIDTH = 20;

	/**
	 * @param scrollPane
	 *            The scroll pane that surrounds the text area.
	 * @param textArea
	 *            The text area.
	 * @param color
	 *            The selection color.
	 */
	public TextNavigation(JScrollPane scrollPane, JTextArea textArea,
			Color color) {
		super();

		this.scrollPane = scrollPane;
		this.textArea = textArea;
		this.color = color;
		this.thisComponent = this;

		addMouseListener(this);

		// Update the label when text area is changed.
		textArea.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyReleased(KeyEvent arg0) {

				TextNavigation.this.thisComponent.repaint();
			}
		});

		// Repaint if the user scroll the view.
		scrollPane.getVerticalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * java.awt.event.AdjustmentListener#adjustmentValueChanged
					 * (java.awt.event.AdjustmentEvent)
					 */
					@SuppressWarnings("synthetic-access")
					@Override
					public void adjustmentValueChanged(AdjustmentEvent arg0) {

						TextNavigation.this.thisComponent.repaint();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {

		Dimension dimension = getSize();
		dimension.width = this.COMPONENT_WIDTH;

		return dimension;
	}

	/**
	 * Print the view port background.
	 * 
	 * @param graphics
	 *            The graphics
	 */
	public void paintViewPort(Graphics graphics) {

		graphics.setColor(this.color);

		JViewport viewPort = this.scrollPane.getViewport();

		Dimension dimension = getSize();

		// Draw while view port.
		int componentViewPortStart = dimension.height
				* viewPort.getViewPosition().y / viewPort.getViewSize().height;
		int componentViewPortEnd = dimension.height
				* viewPort.getVisibleRect().height
				/ viewPort.getViewSize().height;

		if (componentViewPortEnd < 1) {
			graphics.fillRect(2, componentViewPortStart,
					this.COMPONENT_WIDTH - 2, 1);
		} else {
			graphics.fillRect(2, componentViewPortStart,
					this.COMPONENT_WIDTH - 2, componentViewPortEnd);
		}

		// Calculate the component thickness.
		int charactersPerLine[] = StringSupport
				.getCharactersPerLine(this.textArea.getText());
		int componentLineLength[] = new int[dimension.height];

		// Calculate component line by line.
		int previousTextLine = 0;
		for (int i = 0; i < dimension.getHeight(); i++) {

			int line = charactersPerLine.length * i / dimension.height;

			int numberOfCharacters = 0;
			for (int j = previousTextLine; j < line; j++) {
				numberOfCharacters += charactersPerLine[j];
			}
			componentLineLength[i] = numberOfCharacters;

			previousTextLine = line;
		}

		// Find the maximum.
		int maximum = 0;
		for (int i = 0; i < dimension.getHeight(); i++) {

			if (componentLineLength[i] > maximum) {

				maximum = componentLineLength[i];
			}
		}

		if (maximum > 0) {
			// Resize the values to line length.
			int maximumLine = this.COMPONENT_WIDTH - 6;
			for (int i = 0; i < dimension.getHeight(); i++) {
				componentLineLength[i] = maximumLine * componentLineLength[i]
						/ maximum;
			}

			graphics.setColor(new Color(0, 0, 0));

			for (int i = 0; i < dimension.getHeight(); i++) {

				if (componentLineLength[i] > 0) {

					graphics.drawLine(4, i, 3 + componentLineLength[i], i);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics graphics) {

		super.paint(graphics);

		paintViewPort(graphics);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {

		Dimension dimension = getSize();

		// Calculate the component thickness.
		int charactersPerLine[] = StringSupport
				.getCharactersPerLine(this.textArea.getText());

		int line = charactersPerLine.length * event.getY() / dimension.height;

		int position = StringSupport.getPositionOfBeginnintOfLine(
				new StringBuffer(this.textArea.getText()), line + 1);

		this.textArea.setCaretPosition(position);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {

		// Ignore.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {

		// Ignore.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {

		// Ignore.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {

		// Ignore.
	}

}
