package com.carlosbcruz.filterany.filters;

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

import java.io.Serializable;

/**
 * Define a element.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class Element implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	int x = 0; // Horizontal position of the text.
	int y = 0; // Vertical position of the text.
	int boxXStart = 0; // Horizontal start of the box surrounding the text.
	int boxYStart = 0; // Vertical start of the box surrounding the text.
	int boxXEnd = 0; // Horizontal end of the box surrounding the text.
	int boxYEnd = 0; // Vertical end of the box surrounding the text.
	String text = new String(); // The text being controlled.

	/**
	 * Default constructor.
	 */
	public Element() {

		super();
	}

	/**
	 * @param int xParameter Horizontal position of the text.
	 * @param int yParameter Vertical position of the text.
	 * @param int boxXStartParameter Horizontal start of the box surrounding the
	 *        text.
	 * @param int boxYStartParameter Vertical start of the box surrounding the
	 *        text.
	 * @param int boxXEndParameter Horizontal end of the box surrounding the
	 *        text.
	 * @param int boxYEndParameter Vertical end of the box surrounding the text.
	 * @param String
	 *            textParameter The text being controlled.
	 */
	public Element(int xParameter, int yParameter, int boxXStartParameter,
			int boxYStartParameter, int boxXEndParameter, int boxYEndParameter,
			String textParameter) {

		super();

		this.x = xParameter;
		this.y = yParameter;
		this.boxXStart = boxXStartParameter;
		this.boxYStart = boxYStartParameter;
		this.boxXEnd = boxXEndParameter;
		this.boxYEnd = boxYEndParameter;
		this.text = textParameter;
	}

	/**
	 * Provide: Horizontal position of the text.
	 * 
	 * @return x Horizontal position of the text.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Set: Horizontal position of the text.
	 * 
	 * @param x
	 *            Horizontal position of the text.
	 */
	public void setX(int xParameter) {
		this.x = xParameter;
	}

	/**
	 * Provide: Vertical position of the text.
	 * 
	 * @return y Vertical position of the text.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Set: Vertical position of the text.
	 * 
	 * @param y
	 *            Vertical position of the text.
	 */
	public void setY(int yParameter) {
		this.y = yParameter;
	}

	/**
	 * Provide: Horizontal start of the box surrounding the text.
	 * 
	 * @return boxXStart Horizontal start of the box surrounding the text.
	 */
	public int getBoxXStart() {
		return this.boxXStart;
	}

	/**
	 * Set: Horizontal start of the box surrounding the text.
	 * 
	 * @param boxXStart
	 *            Horizontal start of the box surrounding the text.
	 */
	public void setBoxXStart(int boxXStartParameter) {
		this.boxXStart = boxXStartParameter;
	}

	/**
	 * Provide: Vertical start of the box surrounding the text.
	 * 
	 * @return boxYStart Vertical start of the box surrounding the text.
	 */
	public int getBoxYStart() {
		return this.boxYStart;
	}

	/**
	 * Set: Vertical start of the box surrounding the text.
	 * 
	 * @param boxYStart
	 *            Vertical start of the box surrounding the text.
	 */
	public void setBoxYStart(int boxYStartParameter) {
		this.boxYStart = boxYStartParameter;
	}

	/**
	 * Provide: Horizontal end of the box surrounding the text.
	 * 
	 * @return boxXEnd Horizontal end of the box surrounding the text.
	 */
	public int getBoxXEnd() {
		return this.boxXEnd;
	}

	/**
	 * Set: Horizontal end of the box surrounding the text.
	 * 
	 * @param boxXEnd
	 *            Horizontal end of the box surrounding the text.
	 */
	public void setBoxXEnd(int boxXEndParameter) {
		this.boxXEnd = boxXEndParameter;
	}

	/**
	 * Provide: Vertical end of the box surrounding the text.
	 * 
	 * @return boxYEnd Vertical end of the box surrounding the text.
	 */
	public int getBoxYEnd() {
		return this.boxYEnd;
	}

	/**
	 * Set: Vertical end of the box surrounding the text.
	 * 
	 * @param boxYEnd
	 *            Vertical end of the box surrounding the text.
	 */
	public void setBoxYEnd(int boxYEndParameter) {
		this.boxYEnd = boxYEndParameter;
	}

	/**
	 * Provide: The text being controlled.
	 * 
	 * @return text The text being controlled.
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Set: The text being controlled.
	 * 
	 * @param text
	 *            The text being controlled.
	 */
	public void setText(String textParameter) {
		this.text = textParameter;
	}

	/**
	 * Provide the box width.
	 * 
	 * @return The box width.
	 */
	public int getWidth() {

		return this.boxXEnd - this.boxXStart;
	}

	/**
	 * Provide the box height.
	 * 
	 * @return The box height.
	 */
	public int getHeight() {

		return this.boxYEnd - this.boxYStart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@SuppressWarnings("nls")
	@Override
	public String toString() {

		StringBuffer out = new StringBuffer();

		out.append("Element [\n");

		out.append("\tx=" + this.x + ",\n");
		out.append("\ty=" + this.y + ",\n");
		out.append("\tboxXStart=" + this.boxXStart + ",\n");
		out.append("\tboxYStart=" + this.boxYStart + ",\n");
		out.append("\tboxXEnd=" + this.boxXEnd + ",\n");
		out.append("\tboxYEnd=" + this.boxYEnd + ",\n");
		out.append("\ttext=" + this.text + "]\n");

		return out.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

}
