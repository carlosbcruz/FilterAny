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

import java.util.StringTokenizer;

/**
 * Generate banners in ascii mode.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FigletASCII {

	/**
	 * Converts from ASCII to a banner until the width limit is reached.
	 */
	public static String convert(String message, FigletFont figletFont,
			int splitWidth) {

		String result = new String();
		StringTokenizer stringTokenizer = new StringTokenizer(message, " "); //$NON-NLS-1$

		String line = new String();
		while (stringTokenizer.hasMoreElements()) {

			String wordTokenized = stringTokenizer.nextToken(), word;

			if ("".equals(line)) { //$NON-NLS-1$
				word = wordTokenized;
			} else
				word = ' ' + wordTokenized;

			String newLine = append(line, word, figletFont);

			if (width(newLine) > splitWidth && !"".equals(line)) { //$NON-NLS-1$

				result += line + '\n';

				line = append("", wordTokenized, figletFont); //$NON-NLS-1$
			} else
				line = newLine;
		}

		if (!"".equals(line)) { //$NON-NLS-1$
			result += line + '\n';
		}

		return result;
	}

	/**
	 * Provide the maximum width of a converted text.
	 */
	public static int width(String message) {

		StringTokenizer stringTokenizer = new StringTokenizer(message, "\n"); //$NON-NLS-1$

		int word = 0;
		while (stringTokenizer.hasMoreElements()) {
			word = Math.max(word, stringTokenizer.nextToken().length());
		}
		return word;
	}

	/**
	 * Append a word to a banner.
	 */
	private static String append(String messageParameter, String end,
			FigletFont figletFont) {

		String message = messageParameter;

		String result = ""; //$NON-NLS-1$

		int h = 0;
		if ("".equals(message)) { //$NON-NLS-1$
			for (int i = 0; i < figletFont.height; i++) {
				message += " \n"; //$NON-NLS-1$
			}
		}

		StringTokenizer st = new StringTokenizer(message, "\n"); //$NON-NLS-1$
		while (st.hasMoreElements()) {

			result += st.nextToken();

			for (char character : end.toCharArray()) {

				result += figletFont.getCharLineString(character, h);
			}

			result += '\n';
			h++;
		}
		return result;
	}
}
