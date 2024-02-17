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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Support to handle binary data.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class BinarySupport {

	/**
	 * Replace a specific sequence on the a byte array.
	 * 
	 * @param input
	 *            The main byte array.
	 * @param searchFor
	 *            The sequence to search.
	 * @param replaceBy
	 *            The sequence to replace by.
	 * @return The by array with the content replaced.
	 */
	public static byte[] replaceBinary(byte[] input, byte[] searchFor,
			byte[] replaceBy) {

		ArrayList<Byte> output = new ArrayList<>();

		// Buffer during a possible match.
		ArrayList<Byte> replaceBuffer = new ArrayList<>();

		// Comparison control.
		boolean enteredComparison = false;
		int comparisonIndex = 0;

		for (byte searchForByte : input) {

			if (searchForByte == searchFor[comparisonIndex]) {

				replaceBuffer.add(Byte.valueOf(searchForByte));

				comparisonIndex++;

				// If last byte from search for was found then
				// append the replace by and restart the search.
				if (comparisonIndex == searchFor.length) {

					// Found a match. Replace content.

					// Append new content.
					for (byte replaceByByte : replaceBy) {
						output.add(Byte.valueOf(replaceByByte));
					}

					// Restart the search.
					replaceBuffer = new ArrayList<>();
					enteredComparison = false;
					comparisonIndex = 0;

				} else {

					// Continue the search.
					enteredComparison = true;
				}

			} else {

				if (enteredComparison) {

					// Flush partial match.
					output.addAll(replaceBuffer);

					// Restart the search.
					replaceBuffer = new ArrayList<>();
					enteredComparison = false;
					comparisonIndex = 0;

					output.add(Byte.valueOf(searchForByte));

				} else {

					output.add(Byte.valueOf(searchForByte));
				}
			}
		}

		byte[] finalOutput = new byte[output.size()];

		// Create the output byte array.
		Iterator<Byte> iterator = output.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			Byte thisByte = iterator.next();
			finalOutput[index++] = thisByte.byteValue();
		}
		return finalOutput;
	}
}
