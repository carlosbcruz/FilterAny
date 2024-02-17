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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.Timer;

import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.StringSupport.TextPosition;

/**
 * Provides an indication of the line number.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextLineNumber extends Component {

	private static final long serialVersionUID = 1L;

	private JTextArea textArea = null;
	private Font font = null;
	private Color currentLineColor = null;
	private Color normalLineColor = null;

	private Color colorCycle[] = new Color[] { new Color(100, 0, 0),
			new Color(255, 0, 0) };
	private int colorCycleIndex = 0;
	private long lastColorCycle = 0;

	private int componentWidth = 18;

	/**
	 * Default counstructor.
	 * 
	 * @param textArea
	 *            The text area.
	 * @param normalLineColor
	 *            The color for the normal line.
	 * @param currentLineColor
	 *            The color for the current line.
	 */
	public TextLineNumber(JTextArea textArea, Font font, Color normalLineColor,
			Color currentLineColor) {
		super();

		this.textArea = textArea;
		this.font = font;
		this.normalLineColor = normalLineColor;
		this.currentLineColor = currentLineColor;

		Timer calendarTimer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				repaint();
			}
		});

		calendarTimer.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {

		Dimension dimension = getSize();
		dimension.width = this.componentWidth;

		return dimension;
	}

	/**
	 * Print the view port background.
	 * 
	 * @param graphics
	 *            The graphics
	 */
	public void paintViewPort(Graphics graphics) {

		// Retrieve the visible area.
		Rectangle visibleRectangle = this.textArea.getVisibleRect();

		graphics.setFont(this.font);

		// See how many rows it can hold.
		double fractionedNumberOfRows = visibleRectangle.getMinY() / 12d;

		double sizeOfVisibleArea = visibleRectangle.getMaxY()
				- visibleRectangle.getMinY();

		int numberOfVisibleRows = (int) sizeOfVisibleArea / 12;

		int numberOfRows = (int) fractionedNumberOfRows;

		double deltaFraction = (fractionedNumberOfRows - numberOfRows) * 10d;

		String text = this.textArea.getText();
		int numberOfLinesInText = StringSupport.getNumberOfLines(text);
		TextPosition caretPosition = StringSupport.getTextyPosition(text,
				this.textArea.getCaretPosition());

		int startSelection = this.textArea.getSelectionStart();
		int endSelection = this.textArea.getSelectionEnd();

		int maximumLength = 0;
		for (int i = 0; i <= numberOfVisibleRows; i++) {

			int lineNumber = numberOfRows + 1 + i;

			String lineNumberText = String.valueOf(lineNumber);

			if (lineNumber <= numberOfLinesInText) {

				TextPosition startLine = StringSupport.getTextyPosition(text,
						startSelection);
				TextPosition endLine = StringSupport.getTextyPosition(text,
						endSelection);

				if (lineNumber == caretPosition.getLine()) {

					// If it is outside the selection.
					if (startLine.getLine() != endLine.getLine()) {

						graphics.setColor(this.colorCycle[this.colorCycleIndex]);

					} else {
						graphics.setColor(this.currentLineColor);
					}

				} else {

					// No selection in place.
					if (startSelection == endSelection) {

						graphics.setColor(this.normalLineColor);

					} else {

						// If it is outside the selection.
						if (lineNumber >= startLine.getLine()
								&& lineNumber <= endLine.getLine()) {

							graphics.setColor(this.colorCycle[this.colorCycleIndex]);

						} else {
							graphics.setColor(this.normalLineColor);
						}
					}
				}
				graphics.drawChars(lineNumberText.toCharArray(), 0,
						lineNumberText.toCharArray().length, 1, 9 + (i * 12)
								- (int) deltaFraction);
			}

			if (lineNumberText.toCharArray().length > maximumLength) {

				maximumLength = lineNumberText.toCharArray().length;
			}

		}
		this.componentWidth = maximumLength * 8;

		if (System.currentTimeMillis() - this.lastColorCycle > 400) {
			this.colorCycleIndex++;
			if (this.colorCycleIndex >= this.colorCycle.length) {
				this.colorCycleIndex = 0;
			}
			this.lastColorCycle = System.currentTimeMillis();
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

}
