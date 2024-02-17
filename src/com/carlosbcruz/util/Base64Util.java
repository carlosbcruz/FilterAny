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
 * Some util methods to work with Base64.
 */
public class Base64Util {

	private static final char BORDER_CHAR = '#';
	private static final String BEGINNING_OF_LINE = "<div style='color: blue'>"; //$NON-NLS-1$
	private static final String END_OF_LINE = "</div>"; //$NON-NLS-1$

	/**
	 * @param text
	 *            The text to be transformed into a square.
	 * @return The text transformed into a square.
	 */
	public static StringBuffer textToSquare(String text) {

		int textLength = text.length();

		int square = (int) Math.sqrt(textLength);

		StringBuffer outputBuffer = new StringBuffer();

		// Create the top border.

		outputBuffer.append(BEGINNING_OF_LINE);
		for (int i = 0; i <= square + 2; i++) {

			outputBuffer.append(BORDER_CHAR);
		}
		outputBuffer.append(END_OF_LINE);

		outputBuffer.append(BEGINNING_OF_LINE);

		// Create the internal lines.
		int j = 0;
		for (int i = 0; i < textLength; i++) {

			// First character.
			if (j == 0) {

				outputBuffer.append(BORDER_CHAR);
			}

			outputBuffer.append(text.charAt(i));

			// If last character from the line.
			if (j == square) {

				j = 0;

				outputBuffer.append(BORDER_CHAR + END_OF_LINE
						+ BEGINNING_OF_LINE);

				continue;
			}

			j++;
		}

		if (j != 0) {

			// Fill the last line.
			while (j <= square + 1) {

				outputBuffer.append(BORDER_CHAR);
				j++;
			}

			// Write the border on the bottom.
			outputBuffer.append(END_OF_LINE);
			outputBuffer.append(BEGINNING_OF_LINE);
		}

		for (int i = 0; i <= square + 2; i++) {
			outputBuffer.append(BORDER_CHAR);
		}
		outputBuffer.append(END_OF_LINE);

		return outputBuffer;
	}

	/**
	 * Transform a square text into the previous line.
	 * 
	 * @param text
	 *            The square text.
	 * @return The single line.
	 */
	public static StringBuffer squareToText(StringBuffer text) {

		StringBuffer appendedEncode = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		boolean firstCharacter = true;
		boolean characterIsBorder = false;

		while (tokenizer.hasMoreTokens()) {

			String line = tokenizer.nextToken();

			for (int i = 0; i < line.length(); i++) {

				char character = line.charAt(i);

				if (firstCharacter) {

					if (character != BORDER_CHAR) {

						return null;
					}

					firstCharacter = false;
				}

				if (character == BORDER_CHAR) {

					characterIsBorder = true;

				} else {

					appendedEncode.append(character);
					characterIsBorder = false;
				}
			}
		}

		if (!characterIsBorder) {

			return null;
		}

		return appendedEncode;
	}
}
