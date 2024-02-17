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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Graph to show the percentage distribution.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class PercentageGraph extends JComponent {

	private static final long serialVersionUID = 1L;

	// Distributions.
	int[] distribution = new int[10];

	private static final int COMPONENT_WIDTH = 250;
	private static final int COMPONENT_HEIGHT = 50;

	private static final int BORDER = 5;
	private static final int TEXT_HEIGHT = 15;

	/**
	 * Constructor.
	 */
	public PercentageGraph() {

		for (int i = 0; i < 10; i++) {
			this.distribution[i] = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {

		return new Dimension(COMPONENT_WIDTH, COMPONENT_HEIGHT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);

		Dimension dimension = getSize();
		int width = dimension.width;
		int height = dimension.height;

		int usefulWidth = width - (2 * BORDER);
		int usefulHeight = height - TEXT_HEIGHT;

		int columnWidth = usefulWidth / 10;

		graphics.setFont(new Font("Arial", Font.PLAIN, 10)); //$NON-NLS-1$

		int totalElements = 0;
		for (int i = 0; i < 10; i++) {
			totalElements += this.distribution[i];
		}

		for (int i = 0; i < 10; i++) {
			graphics.drawString(String.valueOf(i * 10) + "%", BORDER //$NON-NLS-1$
					+ columnWidth * i + 5, height - BORDER);
		}

		if (totalElements == 0) {
			return;
		}
		for (int i = 0; i < 10; i++) {

			float percentage = (float) this.distribution[i]
					/ (float) totalElements;
			int barHeight = (int) (usefulHeight * percentage);

			graphics.setColor(new Color(150, 150, 180 + 6 * i));

			graphics.fillRect(BORDER + i * columnWidth, 0, columnWidth,
					usefulHeight);

			graphics.setColor(new Color(0, 0, 20 * i));

			graphics.drawRect(BORDER + i * columnWidth, 0, columnWidth,
					usefulHeight);

			graphics.fillRect(BORDER + i * columnWidth, usefulHeight
					- barHeight, columnWidth, barHeight);

		}

	}

	/**
	 * Set the current distribution.
	 * 
	 * @param distribution
	 *            the distribution to set
	 */
	public void setDistribution(int[] distribution) {

		this.distribution = distribution;
		repaint();
	}
}
