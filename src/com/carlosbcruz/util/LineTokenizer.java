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

/**
 * Allows the navigation on a string line by line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class LineTokenizer {

	private StringBuffer content = new StringBuffer();
	private int index = 0;
	private boolean foundNewLine = false;

	/**
	 * Default constructor.
	 * 
	 * @param content
	 *            The text to navigate line by line.
	 */
	public LineTokenizer(StringBuffer content) {
		super();
		this.content = content;
	}

	/**
	 * Indicate if there are more lines to read
	 * 
	 * @return true if there are more lines to read.
	 */
	public boolean hasMoreTokens() {

		return this.index < this.content.length();
	}

	/**
	 * @return the foundNewLine
	 */
	public boolean isFoundNewLine() {
		return this.foundNewLine;
	}

	/**
	 * Provide the next line. If the line is empty than returns an empty String.
	 * 
	 * @return The next line.
	 */
	public String nextToken() {

		StringBuffer nextToken = new StringBuffer();

		this.foundNewLine = false;

		while (this.index < this.content.length()) {

			if (this.content.charAt(this.index) == '\r') {
				this.index++;
				continue;
			}

			if (this.content.charAt(this.index) == '\n') {
				this.foundNewLine = true;
				this.index++;
				return nextToken.toString();
			}

			nextToken.append(this.content.charAt(this.index));
			this.index++;
		}

		// Return the line until the end of the text.
		return nextToken.toString();
	}

}
