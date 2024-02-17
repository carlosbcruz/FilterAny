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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import com.carlosbcruz.util.SwingUtil;

/**
 * Generates an animation.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnyAnimation extends Window implements Runnable {

	private static final long serialVersionUID = 1L;

	// Sentences to animate.
	private static String firstSentence, secondSentence, thirdSentence;

	// Animation canvas.
	private static int BOUNDS_WIDTH;
	private static final int BOUNDS_HEIGHT = 70;
	private static int STOP = 20, X, Y;

	private Thread runner;

	private int yPos = BOUNDS_HEIGHT, xMove = 1;

	private Image offscreenImg;
	private Graphics offscreen;

	private String firstLine;
	private String secondLine;
	private String thirdLine;

	private static Font font = new Font(Font.MONOSPACED, Font.BOLD
			| Font.ITALIC, 11);

	public FilterAnyAnimation() {

		// Creates a window with no Frame as owner
		super(null);
	}

	/**
	 * Start the animation.
	 * 
	 * @param firstLineParameter
	 *            First sentence.
	 * @param secondLineParameter
	 *            Second sentence.
	 * @param thirdLineParameter
	 *            Third sentence.
	 */
	public void startAnimation(String firstLineParameter,
			String secondLineParameter, String thirdLineParameter) {

		this.firstLine = firstLineParameter;
		this.secondLine = secondLineParameter;
		this.thirdLine = thirdLineParameter;

		setBounds(X, Y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
		setVisible(true);

		this.offscreenImg = createImage(BOUNDS_WIDTH, BOUNDS_HEIGHT);
		this.offscreen = this.offscreenImg.getGraphics();

		if (this.runner == null) {

			this.runner = new Thread(this);
			this.runner.start();
		}

		setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		Thread thisThread = Thread.currentThread();

		while (this.runner == thisThread) {

			if (this.yPos != STOP) {
				this.yPos -= this.xMove;
			}

			repaint();

			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				// Do Nothing.
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {

		super.paint(g);

		if (this.offscreen == null) {
			return;
		}

		this.offscreen.setFont(font);
		this.offscreen.setColor(new Color(200, 255, 255));
		this.offscreen.fillRect(0, 0, BOUNDS_WIDTH, BOUNDS_HEIGHT);
		this.offscreen.setColor(Color.black);
		this.offscreen.drawString(this.firstLine, 5, this.yPos);
		this.offscreen.drawString(this.secondLine, 5, this.yPos + 20);
		this.offscreen.drawString(this.thirdLine, 5, this.yPos + 40);
		g.drawImage(this.offscreenImg, 0, 0, this);
	}

	/**
	 * Animate three sentences.
	 * 
	 * @param firstSentenceParameter
	 *            First sentence.
	 * @param secondSentenceParameter
	 *            Second sentence.
	 * @param thirdSentenceParameter
	 *            Third sentence.
	 */
	public static void animate(String firstSentenceParameter,
			String secondSentenceParameter, String thirdSentenceParameter) {

		firstSentence = firstSentenceParameter;
		secondSentence = secondSentenceParameter;
		thirdSentence = thirdSentenceParameter;

		// Calculate the size of the canvas necessary to show the text.
		String longest = secondSentence.length() > thirdSentenceParameter
				.length() ? secondSentence : thirdSentenceParameter;
		AffineTransform affineTransform = new AffineTransform();
		FontRenderContext fontRenderContext = new FontRenderContext(
				affineTransform, true, true);
		int textwidth = (int) (font.getStringBounds(longest, fontRenderContext)
				.getWidth());
		BOUNDS_WIDTH = textwidth + 40;

		int screenWidth = SwingUtil.getScreenWidth();

		X = screenWidth - BOUNDS_WIDTH;
		Y = 0;

		Runnable starter = new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void run() {

				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					// Do Nothing
				}

				FilterAnyAnimation animation = new FilterAnyAnimation();
				animation.startAnimation(firstSentence, secondSentence,
						thirdSentence);

				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					// Do Nothing
				}
				animation.dispose();
				animation = null;
			}

		};
		Thread runStarter = new Thread(starter);
		runStarter.start();
	}
}
