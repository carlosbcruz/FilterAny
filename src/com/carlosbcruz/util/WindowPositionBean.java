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

import java.io.Serializable;

/**
 * Store the position of a window.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class WindowPositionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;

	/**
	 * Default constructor.
	 */
	public WindowPositionBean() {

		super();
	}

	/**
	 * @param int x.
	 * @param int y.
	 * @param int width.
	 * @param int height.
	 */
	public WindowPositionBean(int x, int y, int width, int height) {

		super();

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Provide: Vertical position.
	 * 
	 * @return x Vertical position.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Store: Vertical position.
	 * 
	 * @param x
	 *            Vertical position.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Provide: Horizontal position.
	 * 
	 * @return y Horizontal position.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Store: Horizontal position.
	 * 
	 * @param y
	 *            Horizontal position.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Provide: Window width.
	 * 
	 * @return width Window width.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Store: Window width.
	 * 
	 * @param width
	 *            Window width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Provide: Window height.
	 * 
	 * @return height Window height.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Store: Window height.
	 * 
	 * @param height
	 *            Window height.
	 */
	public void setHeight(int height) {
		this.height = height;
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

		out.append("WindowPositionBean [\n");

		out.append("\tx=" + this.x + ",\n");
		out.append("\ty=" + this.y + ",\n");
		out.append("\twidth=" + this.width + ",\n");
		out.append("\theight=" + this.height + "]\n");

		return out.toString();
	}

}
