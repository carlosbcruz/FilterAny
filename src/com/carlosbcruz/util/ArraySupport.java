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

/**
 * Simple Array support methods
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ArraySupport {

	/**
	 * Select only the files that has the required extension
	 * 
	 * @param files
	 *            the File name list
	 * @param extension
	 *            the extension
	 * @return The list of files that has the informed extension
	 */
	public static String[] filterBySufix(String[] lines, String sufix) {

		int matches = 0;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].endsWith(sufix)) {
				matches++;
			}
		}

		int newLinesCounter = 0;
		String[] newLines = new String[matches];
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].endsWith(sufix)) {
				newLines[newLinesCounter++] = lines[i];
			}
		}

		return newLines;
	}

	/**
	 * Merge two arrays
	 * 
	 * @param firstArray
	 *            The first array
	 * @param secondArray
	 *            The second array
	 * @return the array that contains both firstArray followed by secondArray.
	 */
	public static Object[] mergeArrays(Object[] firstArray, Object[] secondArray) {

		Object[] newArray = new Object[firstArray.length + secondArray.length];

		for (int i = 0; i < firstArray.length; i++) {
			newArray[i] = firstArray[i];
		}

		for (int i = 0; i < secondArray.length; i++) {
			newArray[firstArray.length + i] = secondArray[i];
		}

		return newArray;
	}

	/**
	 * Convert an array of objects into an Array List.
	 * 
	 * @param array
	 *            The array o objects.
	 * @return The ArrayList.
	 */
	public static ArrayList<Object> toArrayList(Object[] array) {

		ArrayList<Object> list = new ArrayList<>();

		for (Object item : array) {

			list.add(item);
		}
		return list;
	}
}
