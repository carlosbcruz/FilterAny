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

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Handle Figlet Font file
 */
public class FigletFont {

	String lines[];
	int line = 0;
	String test;

	public int height = -1;
	private char font[][][] = null;

	/**
	 * Get a string from a line.
	 * 
	 * @param c
	 *            The character.
	 * @param l
	 *            The line.
	 * @return The string.
	 */
	public String getCharLineString(int c, int l) {

		if (this.font[c] == null) {
			return ""; //$NON-NLS-1$
		}

		if (this.font[c][l] == null) {
			return ""; //$NON-NLS-1$
		}

		return new String(this.font[c][l]);
	}

	/**
	 * Provide the next line.
	 * 
	 * @return The next line.
	 */
	private String getLine() {

		if (this.line >= this.lines.length) {

			return null;
		}

		String returnLine = this.lines[this.line];
		this.line++;

		return returnLine;
	}

	/**
	 * Constructor.
	 * 
	 * @param file
	 *            The font file.
	 * @throws IOException
	 */
	public FigletFont(String[] lines) throws IOException {

		char hardblank;
		this.font = new char[256][][];
		String dummyS;
		int dummyI;

		this.lines = lines;

		dummyS = getLine();

		StringTokenizer stringTokenizer = new StringTokenizer(dummyS, " "); //$NON-NLS-1$

		String token = stringTokenizer.nextToken();

		hardblank = token.charAt(token.length() - 1);

		this.height = Integer.parseInt(stringTokenizer.nextToken());

		stringTokenizer.nextToken();
		stringTokenizer.nextToken();
		stringTokenizer.nextToken();

		dummyI = Integer.parseInt(stringTokenizer.nextToken());

		/*
		 * Try to read the font name as the first word of the first comment
		 * line, but this is not standardized.
		 */
		stringTokenizer = new StringTokenizer(getLine(), " "); //$NON-NLS-1$

		if (stringTokenizer.hasMoreElements()) {
			stringTokenizer.nextToken();
		}

		// Skip the comments
		for (int i = 0; i < dummyI - 1; i++) {

			dummyS = getLine();
		}

		// For all the characters.
		for (int i = 32; i < 256; i++) {

			for (int h = 0; h < this.height; h++) {

				dummyS = getLine();

				if (dummyS == null) {

					i = 256;

				} else {

					int iNormal = i;
					boolean abnormal = true;
					if (h == 0) {

						try {

							i = Integer.parseInt(dummyS);

						} catch (NumberFormatException e) {

							abnormal = false;
						}

						if (abnormal) {

							dummyS = getLine();
						} else {
							i = iNormal;
						}

					}

					if (h == 0) {

						this.font[i] = new char[this.height][];
					}

					int t = dummyS.length() - 1
							- ((h == this.height - 1) ? 1 : 0);

					if (this.height == 1) {
						t++;
					}

					this.font[i][h] = new char[t];
					for (int l = 0; l < t; l++) {

						char a = dummyS.charAt(l);

						this.font[i][h][l] = (a == hardblank) ? ' ' : a;
					}
				}
			}
		}
	}
}
