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

/**
 * Store a difference.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class Difference {

	private String text = new String();
	private int lineLeftStart = 0;
	private int lineLeftEnd = 0;
	private int lineRightStart = 0;
	private int lineRightEnd = 0;

	enum TEXT_LOCATION {
		LEFT, RIGHT, BOTH
	}

	private TEXT_LOCATION position = TEXT_LOCATION.BOTH;

	/**
	 * @param text
	 *            One text.
	 * @param lineLeftStart
	 *            The line the left text starts.
	 * @param lineLeftEnd
	 *            The line the left text ends.
	 * @param lineRightStart
	 *            The line the right text starts.
	 * @param lineRightEnd
	 *            The line the right text ends.
	 * @param positionParameter
	 *            The position type parameter.
	 */
	public Difference(String text, int lineLeftStart, int lineLeftEnd,
			int lineRightStart, int lineRightEnd,
			TEXT_LOCATION positionParameter) {
		super();
		this.text = text;
		this.lineLeftStart = lineLeftStart;
		this.lineLeftEnd = lineLeftEnd;
		this.lineRightStart = lineRightStart;
		this.lineRightEnd = lineRightEnd;
		this.position = positionParameter;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the lineLeftStart
	 */
	public int getLineLeftStart() {
		return this.lineLeftStart;
	}

	/**
	 * @param lineLeftStart
	 *            the lineLeftStart to set
	 */
	public void setLineLeftStart(int lineLeftStart) {
		this.lineLeftStart = lineLeftStart;
	}

	/**
	 * @return the lineLeftEnd
	 */
	public int getLineLeftEnd() {
		return this.lineLeftEnd;
	}

	/**
	 * @param lineLeftEnd
	 *            the lineLeftEnd to set
	 */
	public void setLineLeftEnd(int lineLeftEnd) {
		this.lineLeftEnd = lineLeftEnd;
	}

	/**
	 * @return the lineRightStart
	 */
	public int getLineRightStart() {
		return this.lineRightStart;
	}

	/**
	 * @param lineRightStart
	 *            the lineRightStart to set
	 */
	public void setLineRightStart(int lineRightStart) {
		this.lineRightStart = lineRightStart;
	}

	/**
	 * @return the lineRightEnd
	 */
	public int getLineRightEnd() {
		return this.lineRightEnd;
	}

	/**
	 * @param lineRightEnd
	 *            the lineRightEnd to set
	 */
	public void setLineRightEnd(int lineRightEnd) {
		this.lineRightEnd = lineRightEnd;
	}

	/**
	 * @return the position
	 */
	public TEXT_LOCATION getPosition() {
		return this.position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(TEXT_LOCATION position) {
		this.position = position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@SuppressWarnings("nls")
	@Override
	public String toString() {

		return "L(" + this.lineLeftStart + "," + this.lineLeftEnd + ") R("
				+ this.lineRightStart + "," + this.lineRightEnd + ")"
				+ this.position;
	}

}
