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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Provides general string manipulation support
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class StringSupport {

	/**
	 * Transform a size in bytes into a more meaningful size in kilobytes,
	 * megabytes and gigabytes.
	 * 
	 * @param size
	 *            The size in bytes.
	 * @return The text representing the size in to a more meaningful measure.
	 */
	public static String getUnit(long size) {

		// If it is bytes.
		if (size < 1014) {

			return String.valueOf(size) + " b"; //$NON-NLS-1$

		}
		// If it is kilobytes.
		if (size >= 1024 && size < 1024 * 1024) {

			return String.valueOf(size / 1024) + " Kb"; //$NON-NLS-1$

		}
		// If it is megabytes.
		if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {

			return String.valueOf(size / (1024 * 1024)) + " Mb"; //$NON-NLS-1$

		}

		// The maximum is gigabytes.
		return String.valueOf(size / (1024 * 1024 * 1024)) + " Gb"; //$NON-NLS-1$
	}

	/**
	 * Set the first letter to upper case.
	 * 
	 * @param textParameter
	 *            The text.
	 * @return The text with the first letter in upper case.
	 */
	public static String toUpCaseFirstLetter(String textParameter) {

		String text = textParameter;

		text = text.trim();

		String firstLetter = String.valueOf(Character.toUpperCase(text
				.charAt(0)));

		return (firstLetter + text.substring(1));
	}

	/**
	 * Provide the common prefix between two strings.
	 * 
	 * @param firstText
	 *            The first string.
	 * @param secondText
	 *            The second string.
	 * @return The common prefix.
	 */
	public static String getCommonPrefix(String firstText, String secondText) {

		// Find the minimum length.
		int minLength = firstText.length() < secondText.length() ? firstText
				.length() : secondText.length();

		// Iterate the minimum length and find the last common index.
		int maxEqualChar = 0;
		boolean hasDiferences = false;
		for (int i = 0; i < minLength; i++) {

			maxEqualChar = i;

			if (firstText.charAt(i) != secondText.charAt(i)) {

				hasDiferences = true;
				break;
			}
		}

		// Replace back slash by slash.
		String commonPrefix = hasDiferences ? firstText.substring(0,
				maxEqualChar) : firstText.substring(0, minLength);

		return commonPrefix;
	}

	/**
	 * Append spaces to the right until the text is of a requested size.
	 * 
	 * @param textParameter
	 *            The text
	 * @param size
	 *            The size
	 * @return the text adjusted.
	 */
	public static String adjustSizeLeft(String textParameter, int size) {

		String text = textParameter;

		for (int i = text.length(); i < size; i++) {
			text += " "; //$NON-NLS-1$
		}

		return text;
	}

	/**
	 * Append spaces to the left and right until the text is of a requested
	 * size.
	 * 
	 * @param textParameter
	 *            The text
	 * @param size
	 *            The size
	 * @return the text adjusted.
	 */
	public static String adjustSizeCenter(String textParameter, int size) {

		String text = textParameter;

		boolean addToTheLeft = true;
		for (int i = text.length(); i < size; i++) {
			if (addToTheLeft) {
				text = " " + text; //$NON-NLS-1$
			} else {
				text = text + " "; //$NON-NLS-1$
			}
			addToTheLeft = !addToTheLeft;
		}

		return text;
	}

	/**
	 * Append spaces to the left until the text is of a requested size.
	 * 
	 * @param textParameter
	 *            The text
	 * @param size
	 *            The size
	 * @return the text adjusted.
	 */
	public static String adjustSizeRight(String textParameter, int size) {

		String text = textParameter;

		for (int i = text.length(); i < size; i++) {
			text = " " + text; //$NON-NLS-1$
		}

		return text;
	}

	/**
	 * Remove spaces from the right of the line.
	 * 
	 * @param line
	 *            The line.
	 * @return The line without spaces on the right.
	 */
	public static String trimRight(String line) {

		// Find the first valid character.
		int end = line.length() - 1;
		for (int i = line.length() - 1; i >= 0; i--) {
			if (line.charAt(i) <= ' ') {
				end--;
			} else {
				break;
			}
		}

		return line.substring(0, end + 1);
	}

	/**
	 * Remove spaces from the left of the line.
	 * 
	 * @param line
	 *            The line.
	 * @return The line without spaces on the left.
	 */
	public static String trimLeft(String line) {

		// Find the first valid character.
		int start = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) <= ' ') {
				start++;
			} else {
				break;
			}
		}

		return line.substring(start, line.length());

	}

	/**
	 * Find all ocurrences positions of the specified token into the text
	 * provided.
	 * 
	 * @param text
	 *            The text to searh into
	 * @param token
	 *            the token to look for
	 * @return the positions where the token was fond.
	 */
	public static int[] findOcurrences(StringBuffer text, String token) {

		ArrayList<Integer> ocurrences = new ArrayList<>();

		int lastOcurrence = 0;
		int currentIndex = -1;
		do {

			currentIndex = text.indexOf(token, lastOcurrence);

			// There is one occurrence than continue the search
			if (currentIndex != -1) {
				ocurrences.add(new Integer(currentIndex));
				lastOcurrence = currentIndex + 1;
			}

		} while (currentIndex != -1);

		int resultList[] = new int[ocurrences.size()];
		for (int i = 0; i < ocurrences.size(); i++) {
			resultList[i] = ocurrences.get(i).intValue();
		}

		return resultList;
	}

	/**
	 * Break the text into an array of lines based on the a delimiter.
	 * 
	 * @param text
	 *            The text.
	 * @param delimiter
	 *            The delimiter.
	 * @return The lines in an array format.
	 */
	public static String[] breakContentBasedOnDelimiter(String text,
			String delimiter) {

		StringTokenizer tokenizer = new StringTokenizer(text, delimiter);
		ArrayList<String> lines = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken();
			lines.add(line);
		}

		String[] resultLines = new String[lines.size()];
		resultLines = lines.toArray(resultLines);

		return resultLines;
	}

	/**
	 * Break the text into lines.
	 * 
	 * @param text
	 *            The text.
	 * @return The lines in an array format.
	 */
	public static String[] breakContentInLines(StringBuffer text) {

		LineTokenizer tokenizer = new LineTokenizer(text);
		ArrayList<String> lines = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken();
			lines.add(line);
		}

		String[] resultLines = new String[lines.size()];
		resultLines = lines.toArray(resultLines);

		return resultLines;
	}

	/**
	 * @param content
	 *            The string to have it's length adjusted.
	 * @param size
	 *            The minimum length.
	 * @return The content with spaces appended at the end if necessary.
	 */
	public static String setMinimumLengthRight(String content, int size) {

		if (content != null && content.length() < size) {
			String toAppend = new String();
			for (int i = 0; i < size - content.length(); i++) {
				toAppend += " "; //$NON-NLS-1$
			}
			return content + toAppend;
		}

		return content;
	}

	/**
	 * Remove all characters that are not allowed.
	 * 
	 * @param content
	 *            The string to be filtered.
	 * @param allowedChars
	 *            The allowed characters.
	 * @return The filtered String.
	 */
	public static String filterString(String content, char allowedChars[]) {

		String filteredString = new String();
		for (int i = 0; i < content.length(); i++) {

			char currentChar = content.charAt(i);

			boolean found = false;
			for (char character : allowedChars) {
				if (currentChar == character) {
					found = true;
					break;
				}
			}

			if (found) {
				filteredString += String.valueOf(currentChar);
			}
		}

		return filteredString;

	}

	/**
	 * A replace text information.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public static class ReplaceResult implements Serializable {

		private static final long serialVersionUID = 1L;

		// The final text with replace contents.
		private String text = new String();
		// of replaced contents.
		private int Number = 0;

		/**
		 * Default constructor.
		 */
		public ReplaceResult() {

			super();
		}

		/**
		 * @param String
		 *            textParameter The final text with replace contents.
		 * @param int NumberParameter of replaced contents.
		 */
		public ReplaceResult(String textParameter, int NumberParameter) {

			super();

			this.text = textParameter;
			this.Number = NumberParameter;
		}

		/**
		 * Provide: The final text with replace contents.
		 * 
		 * @return text The final text with replace contents.
		 */
		public String getText() {

			return this.text;
		}

		/**
		 * Set: The final text with replace contents.
		 * 
		 * @param text
		 *            The final text with replace contents.
		 */
		public void setText(String textParameter) {

			this.text = textParameter;
		}

		/**
		 * Provide: of replaced contents.
		 * 
		 * @return Number of replaced contents.
		 */
		public int getNumber() {

			return this.Number;
		}

		/**
		 * Set: of replaced contents.
		 * 
		 * @param Number
		 *            of replaced contents.
		 */
		public void setNumber(int NumberParameter) {

			this.Number = NumberParameter;
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

			out.append("ReplaceResult [\n");

			out.append("\ttext=" + this.text + ",\n");
			out.append("\tNumber=" + this.Number + "]\n");

			return out.toString();
		}
	}

	/**
	 * Replace a string inside a provided content and provide the number of
	 * replacements.
	 * 
	 * @param content
	 *            The content.
	 * @param searchFor
	 *            The string to search for.
	 * @param replaceBy
	 *            The string to replace.
	 * @return The class with the replaced content and the replacement
	 *         information.
	 */
	public static ReplaceResult replaceExtended(String content,
			String searchFor, String replaceBy) {

		StringBuffer out = new StringBuffer();

		int numberOfReplacements = 0;

		int index = -1;
		int startSearch = -1;
		boolean found = false;
		do {

			// See if there is a string it is being searched for.
			if (found) {
				int nextSearch = startSearch + searchFor.length();
				index = content.indexOf(searchFor, nextSearch);
			} else {
				index = content.indexOf(searchFor, startSearch + 1);
			}

			if (index != -1) {

				numberOfReplacements++;

				if (found) {
					out.append(content.substring(
							startSearch + searchFor.length(), index));
				} else {
					out.append(content.substring(startSearch + 1, index));
				}

				out.append(replaceBy);

				// Prepare for next search
				startSearch = index;

				found = true;
			}

		} while (index != -1);

		// Return the result.
		if (found) {

			// Append the remaining of the content.
			out.append(content.substring(startSearch + searchFor.length(),
					content.length()));
			return new ReplaceResult(out.toString(), numberOfReplacements);

		}

		return new ReplaceResult(content, numberOfReplacements);
	}

	/**
	 * Replace a string inside a provided content.
	 * 
	 * @param content
	 *            The content.
	 * @param searchFor
	 *            The string to search for.
	 * @param replaceBy
	 *            The string to replace.
	 * @return The string replaced or the content itself if the string is not
	 *         found.
	 */
	public static String replace(String content, String searchFor,
			String replaceBy) {

		StringBuffer out = new StringBuffer();

		int index = -1;
		int startSearch = -1;
		boolean found = false;
		do {

			// See if there is a string it is being searched for.
			if (found) {
				int nextSearch = startSearch + searchFor.length();
				index = content.indexOf(searchFor, nextSearch);
			} else {
				index = content.indexOf(searchFor, startSearch + 1);
			}

			if (index != -1) {

				if (found) {
					out.append(content.substring(
							startSearch + searchFor.length(), index));
				} else {
					out.append(content.substring(startSearch + 1, index));
				}

				out.append(replaceBy);

				// Prepare for next search
				startSearch = index;

				found = true;
			}

		} while (index != -1);

		// Return the result.
		if (found) {

			// Append the remaining of the content.
			out.append(content.substring(startSearch + searchFor.length(),
					content.length()));
			return out.toString();

		}

		return content;
	}

	/**
	 * Transform a text into a HTML valid text.
	 * 
	 * @param text
	 *            The text.
	 * @return The HTML valid text.
	 */
	public static String textToHTML(String text) {

		String newText = text;

		newText = StringSupport.replace(newText, "&", "&amp;"); //$NON-NLS-1$ //$NON-NLS-2$
		newText = StringSupport.replace(newText, " ", "&nbsp;"); //$NON-NLS-1$ //$NON-NLS-2$
		newText = StringSupport.replace(newText, "\"", "&quot;"); //$NON-NLS-1$ //$NON-NLS-2$
		newText = StringSupport.replace(newText, "<", "&lt;"); //$NON-NLS-1$ //$NON-NLS-2$
		newText = StringSupport.replace(newText, ">", "&gt;"); //$NON-NLS-1$ //$NON-NLS-2$

		return newText;
	}

	/**
	 * Count how many occurrences is found on the content.
	 * 
	 * @param content
	 *            The content.
	 * @param searchFor
	 *            The string to search for.
	 * @return The number of occurrences found.
	 */
	public static int countOccurrences(String content, String searchFor) {

		int index = -1;
		int startSearch = -1;
		boolean found = false;
		int numberOfFounds = 0;
		do {

			// See if there is a string it is being searched for.
			if (found) {
				int nextSearch = startSearch + searchFor.length();
				index = content.indexOf(searchFor, nextSearch);
				numberOfFounds++;
			} else {
				index = content.indexOf(searchFor, startSearch + 1);
			}

			if (index != -1) {

				// Prepare for next search
				startSearch = index;

				found = true;
			}

		} while (index != -1);

		return numberOfFounds;
	}

	/**
	 * Replace specific set of chars on a string.
	 * 
	 * @param content
	 *            The string to be modified.
	 * @param searchFor
	 *            The character to be replaced.
	 * @param replaceBy
	 *            The character to replace.
	 * @return The string with the characters replaced.
	 */
	public static String replaceCharacters(String content, char searchFor,
			char replaceBy) {

		String finalContent = new String();
		for (char character : content.toCharArray()) {

			if (character == searchFor) {
				finalContent += String.valueOf(replaceBy);
			} else {
				finalContent += String.valueOf(character);
			}
		}

		return finalContent;
	}

	/**
	 * Inform the characteristics of a specific text.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public class TextCharacteristics implements Serializable {

		private static final long serialVersionUID = 1L;

		public static final int DOS = 1;
		public static final int UNIX = 2;

		// The length of the text.
		private int length = 0;
		// The number of words.
		private int words = 0;
		// The number of lines.
		private int lines = 0;
		// The type of new lines.
		private int lineType = 0;
		// The number of empty lines.
		private int emptyLines = 0;
		// The number of TABs on the text.
		private int numberOfTabulations = 0;
		// The total number of spaces.
		private int spaces = 0;
		// Total of consecutive sequence of spaces of size greater then two.
		private int spaceSequences = 0;

		/**
		 * Default constructor.
		 */
		public TextCharacteristics() {

			super();
		}

		/**
		 * @param int lengthParameter The length of the text.
		 * @param int wordsParameter The number of words.
		 * @param int linesParameter The number of lines.
		 * @param int lineTypeParameter The type of new lines.
		 * @param int emptyLinesParameter The number of empty lines.
		 * @param int numberOfTabulationsParameter The number of TABs on the
		 *        text.
		 * @param int spacesParameter The total number of spaces.
		 * @param int spaceSequencesParameter Total of consecutive sequence of
		 *        spaces of size greater then two.
		 */
		public TextCharacteristics(int lengthParameter, int wordsParameter,
				int linesParameter, int lineTypeParameter,
				int emptyLinesParameter, int numberOfTabulationsParameter,
				int spacesParameter, int spaceSequencesParameter) {

			super();

			this.length = lengthParameter;
			this.words = wordsParameter;
			this.lines = linesParameter;
			this.lineType = lineTypeParameter;
			this.emptyLines = emptyLinesParameter;
			this.numberOfTabulations = numberOfTabulationsParameter;
			this.spaces = spacesParameter;
			this.spaceSequences = spaceSequencesParameter;
		}

		/**
		 * Provide: The length of the text.
		 * 
		 * @return length The length of the text.
		 */
		public int getLength() {

			return this.length;
		}

		/**
		 * Set: The length of the text.
		 * 
		 * @param length
		 *            The length of the text.
		 */
		public void setLength(int lengthParameter) {

			this.length = lengthParameter;
		}

		/**
		 * Provide: The number of words.
		 * 
		 * @return words The number of words.
		 */
		public int getWords() {

			return this.words;
		}

		/**
		 * Set: The number of words.
		 * 
		 * @param words
		 *            The number of words.
		 */
		public void setWords(int wordsParameter) {

			this.words = wordsParameter;
		}

		/**
		 * Provide: The number of lines.
		 * 
		 * @return lines The number of lines.
		 */
		public int getLines() {

			return this.lines;
		}

		/**
		 * Set: The number of lines.
		 * 
		 * @param lines
		 *            The number of lines.
		 */
		public void setLines(int linesParameter) {

			this.lines = linesParameter;
		}

		/**
		 * Provide: The type of new lines.
		 * 
		 * @return lineType The type of new lines.
		 */
		public int getLineType() {

			return this.lineType;
		}

		/**
		 * Set: The type of new lines.
		 * 
		 * @param lineType
		 *            The type of new lines.
		 */
		public void setLineType(int lineTypeParameter) {

			this.lineType = lineTypeParameter;
		}

		/**
		 * Provide: The number of empty lines.
		 * 
		 * @return emptyLines The number of empty lines.
		 */
		public int getEmptyLines() {

			return this.emptyLines;
		}

		/**
		 * Set: The number of empty lines.
		 * 
		 * @param emptyLines
		 *            The number of empty lines.
		 */
		public void setEmptyLines(int emptyLinesParameter) {

			this.emptyLines = emptyLinesParameter;
		}

		/**
		 * Provide: The number of TABs on the text.
		 * 
		 * @return numberOfTabulations The number of TABs on the text.
		 */
		public int getNumberOfTabulations() {

			return this.numberOfTabulations;
		}

		/**
		 * Set: The number of TABs on the text.
		 * 
		 * @param numberOfTabulations
		 *            The number of TABs on the text.
		 */
		public void setNumberOfTabulations(int numberOfTabulationsParameter) {

			this.numberOfTabulations = numberOfTabulationsParameter;
		}

		/**
		 * Provide: The total number of spaces.
		 * 
		 * @return spaces The total number of spaces.
		 */
		public int getSpaces() {

			return this.spaces;
		}

		/**
		 * Set: The total number of spaces.
		 * 
		 * @param spaces
		 *            The total number of spaces.
		 */
		public void setSpaces(int spacesParameter) {

			this.spaces = spacesParameter;
		}

		/**
		 * Provide: Total of consecutive sequence of spaces of size greater then
		 * two.
		 * 
		 * @return spaceSequences Total of consecutive sequence of spaces of
		 *         size greater then two.
		 */
		public int getSpaceSequences() {

			return this.spaceSequences;
		}

		/**
		 * Set: Total of consecutive sequence of spaces of size greater then
		 * two.
		 * 
		 * @param spaceSequences
		 *            Total of consecutive sequence of spaces of size greater
		 *            then two.
		 */
		public void setSpaceSequences(int spaceSequencesParameter) {

			this.spaceSequences = spaceSequencesParameter;
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

			out.append("TextCharacteristics [\n");

			out.append("\tlength=" + this.length + ",\n");
			out.append("\twords=" + this.words + ",\n");
			out.append("\tlines=" + this.lines + ",\n");
			out.append("\tlineType=" + this.lineType + ",\n");
			out.append("\temptyLines=" + this.emptyLines + ",\n");
			out.append("\tnumberOfTabulations=" + this.numberOfTabulations
					+ ",\n");
			out.append("\tspaces=" + this.spaces + ",\n");
			out.append("\tspaceSequences=" + this.spaceSequences + "]\n");

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

	/**
	 * Inform the number of lines.
	 * 
	 * @param text
	 *            The text.
	 * @return The number of lines.
	 */
	public static int getNumberOfLines(String text) {

		int numberOfLines = 1;
		for (char character : text.toCharArray()) {

			if (character == '\n') {

				numberOfLines++;
			}
		}

		return numberOfLines;
	}

	/**
	 * Inform the length of the longest line.
	 * 
	 * @param text
	 *            The text.
	 * @return Tht length of the longest line.
	 */
	public static int getLengthOfLongestLine(String text) {

		int longestLength = 0;

		int counter = 0;
		for (char character : text.toCharArray()) {

			if (character == '\n') {
				if (counter > longestLength) {
					longestLength = counter;
				}
				counter = 0;
			}

			counter++;
		}

		if (counter > longestLength) {
			longestLength = counter;
		}

		return longestLength;
	}

	/**
	 * Analyze the text and provide statistics.
	 * 
	 * @param text
	 *            The text to be analyzed.
	 * @return The text characteristics.
	 */
	@SuppressWarnings("deprecation")
	public static StringSupport.TextCharacteristics analyzeText(String text) {

		int words = 0;
		int newLines = 0;
		int newLinesWithCarriageReturns = 0;
		int emptyLines = 0;
		int numberOfTabulations = 0;
		int spaces = 0;
		int spacesSequence = 0;
		boolean lastCharacterWasNewLine = false;
		boolean lastCharacterWasCarriageReturn = false;
		boolean insideWord = false;
		boolean isLineEmpty = true;
		boolean isInSpaceSequence = false;
		boolean isSpaceSequenceAlreadyCounted = false;

		for (char character : text.toCharArray()) {

			if (character == ' ') {
				spaces++;
				if (isInSpaceSequence) {
					if (isSpaceSequenceAlreadyCounted) {
						// Do nothing.
					} else {
						isSpaceSequenceAlreadyCounted = true;
						spacesSequence++;
					}
				} else {
					isInSpaceSequence = true;
					isSpaceSequenceAlreadyCounted = false;
				}
			} else {
				isInSpaceSequence = false;
				isSpaceSequenceAlreadyCounted = false;
			}

			if (character == '\t') {
				numberOfTabulations++;
			}

			if (character == '\n') {

				newLines++;
				if (isLineEmpty) {
					emptyLines++;
				}
				isLineEmpty = true;
				lastCharacterWasNewLine = true;

				if (lastCharacterWasCarriageReturn) {
					lastCharacterWasCarriageReturn = false;
					newLinesWithCarriageReturns++;
				}
			} else {
				lastCharacterWasNewLine = false;
			}

			if (character == '\r') {
				lastCharacterWasCarriageReturn = true;
				continue;
			}

			lastCharacterWasCarriageReturn = false;

			if (Character.isLetter(character)) {

				insideWord = true;
				isLineEmpty = false;

			} else {

				if (!Character.isSpace(character)) {
					isLineEmpty = false;
				}

				if (insideWord) {
					words++;
					insideWord = false;
				}
			}
		}

		if (insideWord) {
			words++;
		}

		if (!lastCharacterWasNewLine) {
			if (!"".equals(text)) { //$NON-NLS-1$
				newLines++;
			}
		}

		StringSupport.TextCharacteristics characteristics = (new StringSupport()).new TextCharacteristics(
				text.length(), words, newLines,
				newLinesWithCarriageReturns > 0 ? TextCharacteristics.DOS
						: TextCharacteristics.UNIX, emptyLines,
				numberOfTabulations, spaces, spacesSequence);

		return characteristics;
	}

	/**
	 * Indicate the number of occurrences of a symbol.
	 */
	public static class SymbolsOccurrencesBean implements Serializable {

		private static final long serialVersionUID = 1L;

		// The number of occurrences of ".".
		private int period = 0;
		// The number of occurrences of ",".
		private int comma = 0;
		// The number of occurrences of ":".
		private int colon = 0;
		// The number of occurrences of ";".
		private int semicolon = 0;
		// The number of occurrences of "!".
		private int exclamationPoint = 0;
		// The number of occurrences of "'".
		private int apostrophe = 0;
		// The number of occurrences of "�".
		private int singleQuotation = 0;
		// The number of occurrences of """.
		private int doubleQuotation = 0;
		// The number of occurrences of "(".
		private int openBrackets = 0;
		// The number of occurrences of ")".
		private int closeBrackets = 0;
		// The number of occurrences of "[".
		private int openSquareBrackets = 0;
		// The number of occurrences of "]".
		private int closeSquareBrackets = 0;
		// The number of occurrences of "{".
		private int openCurlyBrace = 0;
		// The number of occurrences of "}".
		private int closeCurlyBrace = 0;
		// The number of occurrences of "|".
		private int or = 0;
		// The number of occurrences of "_".
		private int underline = 0;
		// The number of occurrences of "&".
		private int ampersand = 0;
		// The number of occurrences of "~".
		private int tilde = 0;
		// The number of occurrences of "@".
		private int at = 0;
		// The number of occurrences of "+".
		private int plus = 0;
		// The number of occurrences of "-".
		private int dash = 0;
		// The number of occurrences of "=".
		private int equals = 0;
		// The number of occurrences of "<".
		private int isLessThan = 0;
		// The number of occurrences of ">".
		private int isMoreThan = 0;
		// The number of occurrences of "\".
		private int backSlash = 0;
		// The number of occurrences of "/".
		private int forwardSlash = 0;
		// The number of occurrences of "#".
		private int hash = 0;
		// The number of occurrences of "$".
		private int dollar = 0;
		// The number of occurrences of "%".
		private int percent = 0;
		// The number of occurrences of "^".
		private int caret = 0;
		// The number of occurrences of "*".
		private int asterisk = 0;
		// The number of occurrences of "?".
		private int questionMark = 0;

		/**
		 * Default constructor.
		 */
		public SymbolsOccurrencesBean() {

			super();
		}

		/**
		 * Provide: The number of occurrences of ".".
		 * 
		 * @return period The number of occurrences of ".".
		 */
		public int getPeriod() {

			return this.period;
		}

		/**
		 * Set: The number of occurrences of ".".
		 * 
		 * @param period
		 *            The number of occurrences of ".".
		 */
		public void setPeriod(int periodParameter) {

			this.period = periodParameter;
		}

		/**
		 * Provide: The number of occurrences of ",".
		 * 
		 * @return comma The number of occurrences of ",".
		 */
		public int getComma() {

			return this.comma;
		}

		/**
		 * Set: The number of occurrences of ",".
		 * 
		 * @param comma
		 *            The number of occurrences of ",".
		 */
		public void setComma(int commaParameter) {

			this.comma = commaParameter;
		}

		/**
		 * Provide: The number of occurrences of ":".
		 * 
		 * @return colon The number of occurrences of ":".
		 */
		public int getColon() {

			return this.colon;
		}

		/**
		 * Set: The number of occurrences of ":".
		 * 
		 * @param colon
		 *            The number of occurrences of ":".
		 */
		public void setColon(int colonParameter) {

			this.colon = colonParameter;
		}

		/**
		 * Provide: The number of occurrences of ";".
		 * 
		 * @return semicolon The number of occurrences of ";".
		 */
		public int getSemicolon() {

			return this.semicolon;
		}

		/**
		 * Set: The number of occurrences of ";".
		 * 
		 * @param semicolon
		 *            The number of occurrences of ";".
		 */
		public void setSemicolon(int semicolonParameter) {

			this.semicolon = semicolonParameter;
		}

		/**
		 * Provide: The number of occurrences of "!".
		 * 
		 * @return exclamationPoint The number of occurrences of "!".
		 */
		public int getExclamationPoint() {

			return this.exclamationPoint;
		}

		/**
		 * Set: The number of occurrences of "!".
		 * 
		 * @param exclamationPoint
		 *            The number of occurrences of "!".
		 */
		public void setExclamationPoint(int exclamationPointParameter) {

			this.exclamationPoint = exclamationPointParameter;
		}

		/**
		 * Provide: The number of occurrences of "'".
		 * 
		 * @return apostrophe The number of occurrences of "'".
		 */
		public int getApostrophe() {

			return this.apostrophe;
		}

		/**
		 * Set: The number of occurrences of "'".
		 * 
		 * @param apostrophe
		 *            The number of occurrences of "'".
		 */
		public void setApostrophe(int apostropheParameter) {

			this.apostrophe = apostropheParameter;
		}

		/**
		 * Provide: The number of occurrences of "�".
		 * 
		 * @return singleQuotation The number of occurrences of "�".
		 */
		public int getSingleQuotation() {

			return this.singleQuotation;
		}

		/**
		 * Set: The number of occurrences of "�".
		 * 
		 * @param singleQuotation
		 *            The number of occurrences of "�".
		 */
		public void setSingleQuotation(int singleQuotationParameter) {

			this.singleQuotation = singleQuotationParameter;
		}

		/**
		 * Provide: The number of occurrences of """.
		 * 
		 * @return doubleQuotation The number of occurrences of """.
		 */
		public int getDoubleQuotation() {

			return this.doubleQuotation;
		}

		/**
		 * Set: The number of occurrences of """.
		 * 
		 * @param doubleQuotation
		 *            The number of occurrences of """.
		 */
		public void setDoubleQuotation(int doubleQuotationParameter) {

			this.doubleQuotation = doubleQuotationParameter;
		}

		/**
		 * Provide: The number of occurrences of "(".
		 * 
		 * @return openBrackets The number of occurrences of "(".
		 */
		public int getOpenBrackets() {

			return this.openBrackets;
		}

		/**
		 * Set: The number of occurrences of "(".
		 * 
		 * @param openBrackets
		 *            The number of occurrences of "(".
		 */
		public void setOpenBrackets(int openBracketsParameter) {

			this.openBrackets = openBracketsParameter;
		}

		/**
		 * Provide: The number of occurrences of ")".
		 * 
		 * @return closeBrackets The number of occurrences of ")".
		 */
		public int getCloseBrackets() {

			return this.closeBrackets;
		}

		/**
		 * Set: The number of occurrences of ")".
		 * 
		 * @param closeBrackets
		 *            The number of occurrences of ")".
		 */
		public void setCloseBrackets(int closeBracketsParameter) {

			this.closeBrackets = closeBracketsParameter;
		}

		/**
		 * Provide: The number of occurrences of "[".
		 * 
		 * @return openSquareBrackets The number of occurrences of "[".
		 */
		public int getOpenSquareBrackets() {

			return this.openSquareBrackets;
		}

		/**
		 * Set: The number of occurrences of "[".
		 * 
		 * @param openSquareBrackets
		 *            The number of occurrences of "[".
		 */
		public void setOpenSquareBrackets(int openSquareBracketsParameter) {

			this.openSquareBrackets = openSquareBracketsParameter;
		}

		/**
		 * Provide: The number of occurrences of "]".
		 * 
		 * @return closeSquareBrackets The number of occurrences of "]".
		 */
		public int getCloseSquareBrackets() {

			return this.closeSquareBrackets;
		}

		/**
		 * Set: The number of occurrences of "]".
		 * 
		 * @param closeSquareBrackets
		 *            The number of occurrences of "]".
		 */
		public void setCloseSquareBrackets(int closeSquareBracketsParameter) {

			this.closeSquareBrackets = closeSquareBracketsParameter;
		}

		/**
		 * Provide: The number of occurrences of "{".
		 * 
		 * @return openCurlyBrace The number of occurrences of "{".
		 */
		public int getOpenCurlyBrace() {

			return this.openCurlyBrace;
		}

		/**
		 * Set: The number of occurrences of "{".
		 * 
		 * @param openCurlyBrace
		 *            The number of occurrences of "{".
		 */
		public void setOpenCurlyBrace(int openCurlyBraceParameter) {

			this.openCurlyBrace = openCurlyBraceParameter;
		}

		/**
		 * Provide: The number of occurrences of "}".
		 * 
		 * @return closeCurlyBrace The number of occurrences of "}".
		 */
		public int getCloseCurlyBrace() {

			return this.closeCurlyBrace;
		}

		/**
		 * Set: The number of occurrences of "}".
		 * 
		 * @param closeCurlyBrace
		 *            The number of occurrences of "}".
		 */
		public void setCloseCurlyBrace(int closeCurlyBraceParameter) {

			this.closeCurlyBrace = closeCurlyBraceParameter;
		}

		/**
		 * Provide: The number of occurrences of "|".
		 * 
		 * @return or The number of occurrences of "|".
		 */
		public int getOr() {

			return this.or;
		}

		/**
		 * Set: The number of occurrences of "|".
		 * 
		 * @param or
		 *            The number of occurrences of "|".
		 */
		public void setOr(int orParameter) {

			this.or = orParameter;
		}

		/**
		 * Provide: The number of occurrences of "_".
		 * 
		 * @return underline The number of occurrences of "_".
		 */
		public int getUnderline() {

			return this.underline;
		}

		/**
		 * Set: The number of occurrences of "_".
		 * 
		 * @param underline
		 *            The number of occurrences of "_".
		 */
		public void setUnderline(int underlineParameter) {

			this.underline = underlineParameter;
		}

		/**
		 * Provide: The number of occurrences of "&".
		 * 
		 * @return ampersand The number of occurrences of "&".
		 */
		public int getAmpersand() {

			return this.ampersand;
		}

		/**
		 * Set: The number of occurrences of "&".
		 * 
		 * @param ampersand
		 *            The number of occurrences of "&".
		 */
		public void setAmpersand(int ampersandParameter) {

			this.ampersand = ampersandParameter;
		}

		/**
		 * Provide: The number of occurrences of "~".
		 * 
		 * @return tilde The number of occurrences of "~".
		 */
		public int getTilde() {

			return this.tilde;
		}

		/**
		 * Set: The number of occurrences of "~".
		 * 
		 * @param tilde
		 *            The number of occurrences of "~".
		 */
		public void setTilde(int tildeParameter) {

			this.tilde = tildeParameter;
		}

		/**
		 * Provide: The number of occurrences of "@".
		 * 
		 * @return at The number of occurrences of "@".
		 */
		public int getAt() {

			return this.at;
		}

		/**
		 * Set: The number of occurrences of "@".
		 * 
		 * @param at
		 *            The number of occurrences of "@".
		 */
		public void setAt(int atParameter) {

			this.at = atParameter;
		}

		/**
		 * Provide: The number of occurrences of "+".
		 * 
		 * @return plus The number of occurrences of "+".
		 */
		public int getPlus() {

			return this.plus;
		}

		/**
		 * Set: The number of occurrences of "+".
		 * 
		 * @param plus
		 *            The number of occurrences of "+".
		 */
		public void setPlus(int plusParameter) {

			this.plus = plusParameter;
		}

		/**
		 * Provide: The number of occurrences of "-".
		 * 
		 * @return dash The number of occurrences of "-".
		 */
		public int getDash() {

			return this.dash;
		}

		/**
		 * Set: The number of occurrences of "-".
		 * 
		 * @param dash
		 *            The number of occurrences of "-".
		 */
		public void setDash(int dashParameter) {

			this.dash = dashParameter;
		}

		/**
		 * Provide: The number of occurrences of "=".
		 * 
		 * @return equals The number of occurrences of "=".
		 */
		public int getEquals() {

			return this.equals;
		}

		/**
		 * Set: The number of occurrences of "=".
		 * 
		 * @param equals
		 *            The number of occurrences of "=".
		 */
		public void setEquals(int equalsParameter) {

			this.equals = equalsParameter;
		}

/**
		 * Provide: The number of occurrences of "<".
		 * @return isLessThan
		 *            The number of occurrences of "<".
		 */
		public int getIsLessThan() {

			return this.isLessThan;
		}

/**
		 * Set: The number of occurrences of "<".
		 * @param isLessThan
		 *            The number of occurrences of "<".
		 */
		public void setIsLessThan(int isLessThanParameter) {

			this.isLessThan = isLessThanParameter;
		}

		/**
		 * Provide: The number of occurrences of ">".
		 * 
		 * @return isMoreThan The number of occurrences of ">".
		 */
		public int getIsMoreThan() {

			return this.isMoreThan;
		}

		/**
		 * Set: The number of occurrences of ">".
		 * 
		 * @param isMoreThan
		 *            The number of occurrences of ">".
		 */
		public void setIsMoreThan(int isMoreThanParameter) {

			this.isMoreThan = isMoreThanParameter;
		}

		/**
		 * Provide: The number of occurrences of "\".
		 * 
		 * @return backSlash The number of occurrences of "\".
		 */
		public int getBackSlash() {

			return this.backSlash;
		}

		/**
		 * Set: The number of occurrences of "\".
		 * 
		 * @param backSlash
		 *            The number of occurrences of "\".
		 */
		public void setBackSlash(int backSlashParameter) {

			this.backSlash = backSlashParameter;
		}

		/**
		 * Provide: The number of occurrences of "/".
		 * 
		 * @return forwardSlash The number of occurrences of "/".
		 */
		public int getForwardSlash() {

			return this.forwardSlash;
		}

		/**
		 * Set: The number of occurrences of "/".
		 * 
		 * @param forwardSlash
		 *            The number of occurrences of "/".
		 */
		public void setForwardSlash(int forwardSlashParameter) {

			this.forwardSlash = forwardSlashParameter;
		}

		/**
		 * Provide: The number of occurrences of "#".
		 * 
		 * @return hash The number of occurrences of "#".
		 */
		public int getHash() {

			return this.hash;
		}

		/**
		 * Set: The number of occurrences of "#".
		 * 
		 * @param hash
		 *            The number of occurrences of "#".
		 */
		public void setHash(int hashParameter) {

			this.hash = hashParameter;
		}

		/**
		 * Provide: The number of occurrences of "$".
		 * 
		 * @return dollar The number of occurrences of "$".
		 */
		public int getDollar() {

			return this.dollar;
		}

		/**
		 * Set: The number of occurrences of "$".
		 * 
		 * @param dollar
		 *            The number of occurrences of "$".
		 */
		public void setDollar(int dollarParameter) {

			this.dollar = dollarParameter;
		}

		/**
		 * Provide: The number of occurrences of "%".
		 * 
		 * @return percent The number of occurrences of "%".
		 */
		public int getPercent() {

			return this.percent;
		}

		/**
		 * Set: The number of occurrences of "%".
		 * 
		 * @param percent
		 *            The number of occurrences of "%".
		 */
		public void setPercent(int percentParameter) {

			this.percent = percentParameter;
		}

		/**
		 * Provide: The number of occurrences of "^".
		 * 
		 * @return caret The number of occurrences of "^".
		 */
		public int getCaret() {

			return this.caret;
		}

		/**
		 * Set: The number of occurrences of "^".
		 * 
		 * @param caret
		 *            The number of occurrences of "^".
		 */
		public void setCaret(int caretParameter) {

			this.caret = caretParameter;
		}

		/**
		 * Provide: The number of occurrences of "*".
		 * 
		 * @return asterisk The number of occurrences of "*".
		 */
		public int getAsterisk() {

			return this.asterisk;
		}

		/**
		 * Set: The number of occurrences of "*".
		 * 
		 * @param asterisk
		 *            The number of occurrences of "*".
		 */
		public void setAsterisk(int asteriskParameter) {

			this.asterisk = asteriskParameter;
		}

		/**
		 * Provide: The number of occurrences of "?".
		 * 
		 * @return questionMark The number of occurrences of "?".
		 */
		public int getQuestionMark() {

			return this.questionMark;
		}

		/**
		 * Set: The number of occurrences of "?".
		 * 
		 * @param questionMark
		 *            The number of occurrences of "?".
		 */
		public void setQuestionMark(int questionMarkParameter) {

			this.questionMark = questionMarkParameter;
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

			out.append("SymbolsOccurrencesBean [\n");

			out.append("\tperiod=" + this.period + ",\n");
			out.append("\tcomma=" + this.comma + ",\n");
			out.append("\tcolon=" + this.colon + ",\n");
			out.append("\tsemicolon=" + this.semicolon + ",\n");
			out.append("\texclamationPoint=" + this.exclamationPoint + ",\n");
			out.append("\tapostrophe=" + this.apostrophe + ",\n");
			out.append("\tsingleQuotation=" + this.singleQuotation + ",\n");
			out.append("\tdoubleQuotation=" + this.doubleQuotation + ",\n");
			out.append("\topenBrackets=" + this.openBrackets + ",\n");
			out.append("\tcloseBrackets=" + this.closeBrackets + ",\n");
			out.append("\topenSquareBrackets=" + this.openSquareBrackets
					+ ",\n");
			out.append("\tcloseSquareBrackets=" + this.closeSquareBrackets
					+ ",\n");
			out.append("\topenCurlyBrace=" + this.openCurlyBrace + ",\n");
			out.append("\tcloseCurlyBrace=" + this.closeCurlyBrace + ",\n");
			out.append("\tor=" + this.or + ",\n");
			out.append("\tunderline=" + this.underline + ",\n");
			out.append("\tampersand=" + this.ampersand + ",\n");
			out.append("\ttilde=" + this.tilde + ",\n");
			out.append("\tat=" + this.at + ",\n");
			out.append("\tplus=" + this.plus + ",\n");
			out.append("\tdash=" + this.dash + ",\n");
			out.append("\tequals=" + this.equals + ",\n");
			out.append("\tisLessThan=" + this.isLessThan + ",\n");
			out.append("\tisMoreThan=" + this.isMoreThan + ",\n");
			out.append("\tbackSlash=" + this.backSlash + ",\n");
			out.append("\tforwardSlash=" + this.forwardSlash + ",\n");
			out.append("\thash=" + this.hash + ",\n");
			out.append("\tdollar=" + this.dollar + ",\n");
			out.append("\tpercent=" + this.percent + ",\n");
			out.append("\tcaret=" + this.caret + ",\n");
			out.append("\tasterisk=" + this.asterisk + ",\n");
			out.append("\tquestionMark=" + this.questionMark + "]\n");

			return out.toString();
		}

	}

	/**
	 * Analyze the text and provide the use of symbols.
	 * 
	 * @param text
	 *            The text to be analyzed.
	 * @return The text characteristics.
	 */
	public static StringSupport.SymbolsOccurrencesBean analyzeTextSymbols(
			String text) {

		SymbolsOccurrencesBean bean = new SymbolsOccurrencesBean();

		for (char character : text.toCharArray()) {

			if (character == '.') {
				bean.setPeriod(bean.getPeriod() + 1);
			}

			if (character == ',') {
				bean.setComma(bean.getComma() + 1);
			}

			if (character == ':') {
				bean.setColon(bean.getColon() + 1);
			}

			if (character == ';') {
				bean.setSemicolon(bean.getSemicolon() + 1);
			}

			if (character == '!') {
				bean.setExclamationPoint(bean.getExclamationPoint() + 1);
			}

			if (character == '\'') {
				bean.setApostrophe(bean.getApostrophe() + 1);
			}

			if (character == '`') {
				bean.setSingleQuotation(bean.getSingleQuotation() + 1);
			}

			if (character == '"') {
				bean.setDoubleQuotation(bean.getDoubleQuotation() + 1);
			}

			if (character == '(') {
				bean.setOpenBrackets(bean.getOpenBrackets() + 1);
			}

			if (character == ')') {
				bean.setCloseBrackets(bean.getCloseBrackets() + 1);
			}

			if (character == '[') {
				bean.setOpenSquareBrackets(bean.getOpenSquareBrackets() + 1);
			}

			if (character == ']') {
				bean.setCloseSquareBrackets(bean.getCloseSquareBrackets() + 1);
			}

			if (character == '{') {
				bean.setOpenCurlyBrace(bean.getOpenCurlyBrace() + 1);
			}

			if (character == '}') {
				bean.setCloseCurlyBrace(bean.getCloseCurlyBrace() + 1);
			}

			if (character == '|') {
				bean.setOr(bean.getOr() + 1);
			}

			if (character == '_') {
				bean.setUnderline(bean.getUnderline() + 1);
			}

			if (character == '&') {
				bean.setAmpersand(bean.getAmpersand() + 1);
			}

			if (character == '~') {
				bean.setTilde(bean.getTilde() + 1);
			}

			if (character == '@') {
				bean.setAt(bean.getAt() + 1);
			}

			if (character == '+') {
				bean.setPlus(bean.getPlus() + 1);
			}

			if (character == '-') {
				bean.setDash(bean.getDash() + 1);
			}

			if (character == '=') {
				bean.setEquals(bean.getEquals() + 1);
			}

			if (character == '<') {
				bean.setIsLessThan(bean.getIsLessThan() + 1);
			}

			if (character == '>') {
				bean.setIsMoreThan(bean.getIsMoreThan() + 1);
			}

			if (character == '\\') {
				bean.setBackSlash(bean.getBackSlash() + 1);
			}

			if (character == '/') {
				bean.setForwardSlash(bean.getForwardSlash() + 1);
			}

			if (character == '#') {
				bean.setHash(bean.getHash() + 1);
			}

			if (character == '$') {
				bean.setDollar(bean.getDollar() + 1);
			}

			if (character == '%') {
				bean.setPercent(bean.getPercent() + 1);
			}

			if (character == '^') {
				bean.setCaret(bean.getCaret() + 1);
			}

			if (character == '*') {
				bean.setAsterisk(bean.getAsterisk() + 1);
			}

			if (character == '?') {
				bean.setQuestionMark(bean.getQuestionMark() + 1);
			}

		}
		return bean;
	}

	/**
	 * Bean to indicate a Caret position.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public static class TextPosition {

		private int line = 0;
		private int column = 0;

		/**
		 * Default constructor
		 * 
		 * @param line
		 *            A line
		 * @param column
		 *            A column
		 */
		public TextPosition(int line, int column) {

			super();

			this.line = line;
			this.column = column;
		}

		/**
		 * @return the line
		 */
		public int getLine() {
			return this.line;
		}

		/**
		 * @return the column
		 */
		public int getColumn() {
			return this.column;
		}
	}

	/**
	 * Indicate the text position.
	 * 
	 * @param text
	 *            The text.
	 * @param position
	 *            The position.
	 * @return The line and column
	 */
	public static StringSupport.TextPosition getTextyPosition(String text,
			int position) {

		int line = 1;
		int column = 0;
		for (int i = 0; i < position; i++) {

			if (text.charAt(i) == '\n') {
				line++;
				column = 0;
			} else {
				column++;
			}
		}

		return new TextPosition(line, column);
	}

	/**
	 * Inform the number of characters per line.
	 * 
	 * @param text
	 *            The text.
	 * @return The number of characters per lines.
	 */
	public static int[] getCharactersPerLine(String text) {

		ArrayList<Integer> lines = new ArrayList<>();

		int characters = 0;
		for (char character : text.toCharArray()) {

			if (character == '\n') {

				lines.add(new Integer(characters));
				characters = 0;
			} else {

				characters++;
			}
		}

		int resultLines[] = new int[lines.size()];
		for (int i = 0; i < lines.size(); i++) {

			Integer numberOfCharacters = lines.get(i);
			resultLines[i] = numberOfCharacters.intValue();
		}
		return resultLines;
	}

	/**
	 * Analyze the line provided and break into separated commands. This method
	 * is aware of elements using quotation marks.
	 * 
	 * @param line
	 *            The line to be analyzed.
	 * @return The command line elements.
	 */
	public static String[] breakIntoCommandLineElements(String line) {

		ArrayList<String> elements = new ArrayList<>();

		String currentElement = new String();
		boolean insideQuotes = false;
		boolean specialCharacterFound = false;
		for (char character : line.toCharArray()) {

			// There is a special character.
			if (specialCharacterFound) {

				currentElement += character;
				specialCharacterFound = false;
				continue;
			}

			// Check if a special character is used.
			if ('\\' == character) {
				specialCharacterFound = true;
				continue;
			}

			// Identify continues text parts.
			if ('"' == character) {

				if (insideQuotes) {

					elements.add(currentElement);

					currentElement = new String();
					insideQuotes = false;

					continue;

				}

				insideQuotes = true;

				continue;
			}

			if (insideQuotes) {

				// Inside quotes than include everything.
				currentElement += character;
				continue;
			}

			if (Character.isWhitespace(character)) {

				// If current element has content.
				if ("".equals(currentElement)) { //$NON-NLS-1$

					// There is no current element.
					// Ignore spaces.
					continue;

				}

				// Add to the list.
				elements.add(currentElement);
				currentElement = new String();

			} else {

				currentElement += character;
			}
		}

		// Add the last command if it exist.
		if (!"".equals(currentElement.trim())) { //$NON-NLS-1$

			elements.add(currentElement.trim());
		}

		String elementsArray[] = new String[elements.size()];
		elementsArray = elements.toArray(elementsArray);

		return elementsArray;
	}

	/**
	 * Provide the DOS type new line.
	 * 
	 * @return The DOS type new line.
	 */
	public static String getDOSNewLine() {

		return "\r\n"; //$NON-NLS-1$
	}

	/**
	 * Provide the UNIX type new line.
	 * 
	 * @return The UNIX type new line.
	 */
	public static String getUnixNewLine() {

		return "\n"; //$NON-NLS-1$
	}

	/**
	 * Return the java new line.
	 * 
	 * @return The java new line.
	 */
	public static char getJavaNewLine() {

		return '\n';
	}

	/**
	 * Transform a text into a specific format.
	 * 
	 * @param text
	 *            The text.
	 * @param endOfLine
	 *            The end of line identification.
	 * @return The text formatted.
	 */
	private static StringBuffer getFormattedText(StringBuffer text,
			String endOfLine) {

		LineTokenizer tokenizer = new LineTokenizer(text);

		StringBuffer output = new StringBuffer();
		while (tokenizer.hasMoreTokens()) {

			output.append(tokenizer.nextToken());

			if (tokenizer.isFoundNewLine()) {
				output.append(endOfLine);
			}

		}

		return output;
	}

	/**
	 * Transform a text into a DOS format.
	 * 
	 * @param text
	 *            The text.
	 * @return The text formatted.
	 */
	public static String getDOSText(String text) {

		StringBuffer output = getFormattedText(new StringBuffer(text),
				getDOSNewLine());

		return output.toString();
	}

	/**
	 * Transform a text into a Unix format.
	 * 
	 * @param text
	 *            The text.
	 * @return The text formatted.
	 */
	public static String getUnixText(String text) {

		StringBuffer output = getFormattedText(new StringBuffer(text),
				getUnixNewLine());

		return output.toString();
	}

	/**
	 * Switch the line type between DOS and UNIX
	 * 
	 * @param text
	 *            The text to be converted.
	 * @return The text converted.
	 */
	public static String convertLineType(String text) {

		StringBuffer out = new StringBuffer();

		boolean isDOS2UNIX = false;
		boolean isUNIX2DOS = false;

		boolean isLastCharacterCarriageReturn = false;

		for (char character : text.toCharArray()) {

			if (isDOS2UNIX || isUNIX2DOS) {
				// Change mode.

				if (isDOS2UNIX) {
					if (character == '\r') {
						continue;
					}
				}

				if (isUNIX2DOS) {
					if (character == '\n') {
						out.append(getDOSNewLine());
						continue;
					}
				}

			} else {
				// Identification mode.

				if (character == '\r') {
					isLastCharacterCarriageReturn = true;
					continue;
				}

				// if it is not an isolated carriage return.
				if (character != '\n') {
					isLastCharacterCarriageReturn = false;
				}

				if (character == '\n') {
					if (isLastCharacterCarriageReturn) {
						isDOS2UNIX = true;
					} else {
						isUNIX2DOS = true;
						out.append(getDOSNewLine());
						continue;
					}
				}
			}

			out.append(character);
		}
		return out.toString();
	}

	/**
	 * Creates a new copy array starting from a specific position.
	 * 
	 * @param original
	 *            The original array.
	 * @param statingPosition
	 *            The new position to start copying.
	 * @return The new array containing the elements from the startingPosition
	 *         until the end of the original array.
	 */
	public static String[] arrayAtPosition(String[] original,
			int statingPosition) {

		int newSize = original.length - statingPosition;

		// Safeguard array size.
		if (newSize <= 0) {
			return new String[0];
		}

		String newArray[] = new String[newSize];

		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = original[statingPosition + i];
		}
		return newArray;
	}

	/**
	 * Indicate the character position of the beginning of provided line.
	 * 
	 * @param text
	 *            The text to be analyzed.
	 * @param line
	 *            The line required.
	 * @return The position.
	 */
	public static int getPositionOfBeginnintOfLine(StringBuffer text, int line) {

		int currentLine = 1;
		for (int i = 0; i < text.length(); i++) {

			char currentChar = text.charAt(i);

			if (currentLine == line) {

				return i;
			}

			if (currentChar == '\n') {

				currentLine++;

				continue;

			}
		}

		return -1;
	}

	/**
	 * Indicate the character position of the end of provided line.
	 * 
	 * @param text
	 *            The text to be analyzed.
	 * @param line
	 *            The line required.
	 * @return The position.
	 */
	public static int getPositionOfTheEndOfLine(StringBuffer text, int line) {

		int currentLine = 1;
		for (int i = 0; i < text.length(); i++) {

			char currentChar = text.charAt(i);

			if (currentChar == '\n') {

				if (currentLine == line) {

					return i + 1;
				}

				currentLine++;

				continue;

			}
		}

		return text.length();
	}

	/**
	 * Convert a text into a string that can be used for file names
	 * 
	 * @param text
	 *            A text to be converted.
	 * @return A valid string to be used as a file name.
	 */
	public static String getConvertNonFileCharacters(String textParameter) {

		String text = textParameter;

		String file = new String();

		// Eliminate http header.
		if (text.startsWith("http://")) { //$NON-NLS-1$
			text = text.substring(7, text.length());
		}

		for (char character : text.toCharArray()) {

			if (character == '\\' || character == '/' || character == '%'
					|| character == '@' || character == '=' || character == '&'
					|| character == '.') {
				file += '_';
				continue;
			}

			file += character;
		}

		file += ".txt"; //$NON-NLS-1$

		return file;
	}

	/**
	 * Convert a content from DOS format to UNIX format.
	 * 
	 * @param content
	 *            The content to be transformed.
	 * @return The content in UNIX format.
	 */
	public static String dosToUnix(String content) {

		return replace(content, "\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Convert a content from UNIX format to DOS format.
	 * 
	 * @param content
	 *            The content to be transformed.
	 * @return The content in DOS format.
	 */
	public static String unixToDos(String content) {

		return replace(content, "\n", "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Count the number of differences between two difference texts with the
	 * same size.
	 * 
	 * @param firstText
	 *            The first text.
	 * @param secondText
	 *            The second text.
	 * @return The number of differences between them.
	 */
	public static int getNumberOfDifferentCharacters(String firstText,
			String secondText) {

		if (firstText == null || secondText == null) {
			return 0;
		}

		if (firstText.length() != secondText.length()) {
			return 0;
		}

		int differences = 0;
		for (int i = 0; i < firstText.length(); i++) {
			if (firstText.charAt(i) != secondText.charAt(i)) {
				differences++;
			}
		}

		return differences;
	}

	/**
	 * Prefix a number with a certain number of zeroes according to the size of
	 * the maximum number.
	 * 
	 * @param numberParameter
	 *            the current number.
	 * @param maximumNumberParameter
	 *            the greatest number.
	 * @return the number with preceding zeroes according to the maximum number.
	 */
	public static String prefixWithZeroes(int numberParameter,
			int maximumNumberParameter) {

		int numberOfCharacters = String.valueOf(maximumNumberParameter)
				.length();

		String currentNumber = String.valueOf(numberParameter);
		String prefix = ""; //$NON-NLS-1$
		for (int i = 0; i < numberOfCharacters - currentNumber.length(); i++) {
			prefix += "0"; //$NON-NLS-1$
		}

		return prefix + currentNumber;
	}

	/**
	 * Set all lines to the length of the longest one.
	 * 
	 * @param text
	 *            The original text.
	 * @return The text that has all lines with the same length.
	 */
	public static StringBuffer setLintesToSameLength(StringBuffer text) {

		String lines[] = breakContentInLines(text);

		int longestLength = -1;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].length() > longestLength) {
				longestLength = lines[i].length();
			}
		}

		for (int i = 0; i < lines.length; i++) {

			String newLine = lines[i];
			int numberOfSpaces = longestLength - newLine.length();
			for (int j = 0; j < numberOfSpaces; j++) {
				newLine += " "; //$NON-NLS-1$
			}
			lines[i] = newLine;
		}

		StringBuffer newText = new StringBuffer();
		for (int i = 0; i < lines.length; i++) {

			newText.append(lines[i]);
			newText.append("\n"); //$NON-NLS-1$
		}

		return newText;
	}

	/**
	 * Indicate if a character is placed in between digits.
	 * 
	 * @param line
	 *            The line.
	 * @param position
	 *            A specific position.
	 * @return True if it is placed in between digits.
	 */
	public static boolean isCharacterBetweenDigits(String line, int position) {

		if (position <= 0) {
			return false;
		}

		if (position >= line.length() - 1) {
			return false;
		}

		char previousChar = line.charAt(position - 1);
		char nextChar = line.charAt(position + 1);
		if (Character.isDigit(previousChar) && Character.isDigit(nextChar)) {
			return true;
		}

		return false;
	}

	/**
	 * Indicate the length of the longest word.
	 * 
	 * @param words
	 *            The list of words.
	 * @return The length of the longest word.
	 */
	public static int getLongestLength(ArrayList<String> words) {

		int longest = 0;
		for (int i = 0; i < words.size(); i++) {

			int length = words.get(i).length();

			if (length > longest) {
				longest = length;
			}
		}

		return longest;
	}

	/**
	 * Sort the array by word length.
	 * 
	 * @param words
	 *            The words.
	 * @return The sorted list.
	 */
	public static ArrayList<String> sortByLength(ArrayList<String> words) {

		Collections.sort(words, new Comparator<String>() {

			/**
			 * Compare the length of the strings.
			 * 
			 * @param object1
			 *            the first String.
			 * @param object2
			 *            the second String.
			 * @return If object1 is longer then object2.
			 */
			@Override
			public int compare(String object1, String object2) {

				if (object1.length() > object2.length()) {

					return 1;

				}

				if (object1.length() < object2.length()) {

					return -1;
				}

				return 0;
			}

		});

		return words;
	}

	/**
	 * Store a word that has repetitions.
	 */
	public static class WordRepetition implements Serializable {

		private static final long serialVersionUID = 1L;

		// The word that has repetitions.
		private String word = new String();
		// The number of repetitions that the word had.
		private int repetitions = 0;

		/**
		 * Default constructor.
		 */
		public WordRepetition() {

			super();
		}

		/**
		 * @param String
		 *            wordParameter The word that has repetitions.
		 * @param int repetitionsParameter The number of repetitions that the
		 *        word had.
		 */
		public WordRepetition(String wordParameter, int repetitionsParameter) {

			super();

			this.word = wordParameter;
			this.repetitions = repetitionsParameter;
		}

		/**
		 * Provide: The word that has repetitions.
		 * 
		 * @return word The word that has repetitions.
		 */
		public String getWord() {

			return this.word;
		}

		/**
		 * Set: The word that has repetitions.
		 * 
		 * @param word
		 *            The word that has repetitions.
		 */
		public void setWord(String wordParameter) {

			this.word = wordParameter;
		}

		/**
		 * Provide: The number of repetitions that the word had.
		 * 
		 * @return repetitions The number of repetitions that the word had.
		 */
		public int getRepetitions() {

			return this.repetitions;
		}

		/**
		 * Set: The number of repetitions that the word had.
		 * 
		 * @param repetitions
		 *            The number of repetitions that the word had.
		 */
		public void setRepetitions(int repetitionsParameter) {

			this.repetitions = repetitionsParameter;
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

			out.append("WordRepetition [\n");

			out.append("\tword=" + this.word + ",\n");
			out.append("\trepetitions=" + this.repetitions + "]\n");

			return out.toString();
		}

	}

	/**
	 * Get the most repeated words that have the same or more characters then
	 * the parameter provided.
	 * 
	 * @param words
	 *            The list of words ordered by length.
	 * @param numberOfResults
	 *            The size of the result.
	 * @param numberOfCharacters
	 *            The number of character to compare the length of the words.
	 * @return The most used words.
	 */
	public static WordRepetition[] getMostRepeatedWords(
			ArrayList<String> words, int numberOfResults, int numberOfCharacters) {

		WordRepetition repetitions[] = new WordRepetition[numberOfResults];
		// Initialize.
		for (int i = 0; i < repetitions.length; i++) {
			repetitions[i] = new WordRepetition();
		}

		boolean[] considerThisWord = new boolean[words.size()];
		// Initialize.
		for (int i = 0; i < considerThisWord.length; i++) {
			considerThisWord[i] = true;
		}

		// Search for repetitions.
		for (int i = 0; i < words.size(); i++) {

			if (!considerThisWord[i]) {
				continue;
			}

			String currentWord = words.get(i);

			if (currentWord.length() < numberOfCharacters) {
				continue;
			}

			WordRepetition repetitionBeingAnalyzed = new WordRepetition(
					currentWord, 1);

			for (int j = i + 1; j < words.size(); j++) {

				if (considerThisWord[j]) {

					if (currentWord.equals(words.get(j))) {
						repetitionBeingAnalyzed
								.setRepetitions(repetitionBeingAnalyzed
										.getRepetitions() + 1);
						considerThisWord[j] = false;
					}
				}
			}

			// Find the insert position.
			int insertPosition = -1;
			for (int k = 0; k < repetitions.length; k++) {
				if (repetitionBeingAnalyzed.getRepetitions() > repetitions[k]
						.getRepetitions()) {
					insertPosition = k;
					break;
				}
			}

			if (insertPosition != -1) {

				for (int k = repetitions.length - 2; k >= insertPosition; k--) {
					repetitions[k + 1] = repetitions[k];
				}

				repetitions[insertPosition] = repetitionBeingAnalyzed;
			}
		}

		return repetitions;
	}

	/**
	 * Provide the list of words.
	 * 
	 * @param text
	 *            The text to be analyzed.
	 * @return The list of words.
	 */
	public static ArrayList<String> getWords(StringBuffer text) {

		ArrayList<String> words = new ArrayList<>();

		boolean insideWord = false;
		String currentWord = new String();
		for (int i = 0; i < text.length(); i++) {

			int thisCodePoint = text.codePointAt(i);

			if (Character.isLetter(thisCodePoint)) {
				if (!insideWord) {

					insideWord = true;
					currentWord = new String();
				}
				currentWord += text.charAt(i);

			} else {

				if (insideWord) {

					words.add(currentWord.toLowerCase());
					insideWord = false;
					currentWord = new String();
				}
			}
		}

		if (insideWord) {
			words.add(currentWord.toLowerCase());
		}

		return words;
	}

	/**
	 * Guarantee that a word has a maximum length by adding elipsis.
	 * 
	 * @param word
	 *            The word.
	 * @param maximum
	 *            The maximum length.
	 * @return The word with maximum length.
	 */
	public static String getWordWithMaximumLength(String word, int maximumLength) {

		String newWord = word;
		if (word.length() > maximumLength) {
			newWord = word.substring(0, maximumLength - 3) + "..."; //$NON-NLS-1$
		}
		return newWord;
	}

	/**
	 * Provide the current date text.
	 * 
	 * @return The current date.
	 */
	public static String getCurrentDateText() {

		return DateFormat.getDateInstance(DateFormat.FULL).format(new Date())
				+ " " //$NON-NLS-1$
				+ DateFormat.getTimeInstance(DateFormat.FULL)
						.format(new Date());
	}

	/**
	 * Provide the current date text.
	 * 
	 * @param long A date.
	 * @return The current date.
	 */
	/**
	 * @return
	 */
	@SuppressWarnings("boxing")
	public static String getCurrentDateText(long date) {

		return DateFormat.getDateInstance(DateFormat.FULL).format(date) + " " //$NON-NLS-1$
				+ DateFormat.getTimeInstance(DateFormat.FULL).format(date);
	}

	/**
	 * Remove all the digits of the text.
	 * 
	 * @param text
	 *            The text.
	 * @return The text without digits.
	 */
	public static String removeDigits(String text) {

		if (text == null) {

			return new String();
		}

		StringBuffer newText = new StringBuffer();

		for (char character : text.toCharArray()) {

			if (!Character.isDigit(character)) {

				newText.append(character);
			}
		}

		return newText.toString();
	}

	/**
	 * Remove all the characters except the digits.
	 * 
	 * @param text
	 *            The text.
	 * @return The text without digits.
	 */
	public static String leaveOnlyDigits(String text) {

		if (text == null) {

			return new String();
		}

		StringBuffer newText = new StringBuffer();

		for (char character : text.toCharArray()) {

			if (Character.isDigit(character)) {

				newText.append(character);
			}
		}

		return newText.toString();
	}

	/**
	 * Remove spaces after a specific character
	 * 
	 * @param content
	 *            The text.
	 * @param character
	 *            The character
	 * @return The modified text.
	 */
	public static String removeSpacesAfterCharacter(String content,
			char character) {

		StringBuffer output = new StringBuffer();

		boolean foundCharacter = false;
		for (int i = 0; i < content.length(); i++) {

			char characterToBeAnalyzed = content.charAt(i);

			if (foundCharacter && characterToBeAnalyzed == ' ') {
				foundCharacter = false;
				continue;
			}

			foundCharacter = false;
			if (characterToBeAnalyzed == character) {

				foundCharacter = true;
			}
			output.append(characterToBeAnalyzed);

		}

		return output.toString();
	}

	/**
	 * Return the last occurrence of a character.
	 * 
	 * @param content
	 *            The text.
	 * @param character
	 *            The character to be found.
	 * @return The position of the occurrence or -1 if not found.
	 */
	public static int findLastOccurrenceOfACharacter(String content,
			char character) {

		for (int i = content.length() - 1; i >= 0; i--) {

			if (content.charAt(i) == StringSupport.getJavaNewLine()) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Add space to the beginning of all lines.
	 * 
	 * @param content
	 *            The content.
	 * @return The text with spaces added.
	 */
	public static String addSpaceToTheBeginningOfAllLines(String content) {

		StringBuffer output = new StringBuffer();

		for (int i = 0; i < content.length(); i++) {
			char character = content.charAt(i);

			output.append(character);
			if (character == StringSupport.getJavaNewLine()) {
				output.append(" "); //$NON-NLS-1$
			}
		}

		return output.toString();
	}

	/**
	 * Convert an entire text to upper case.
	 * 
	 * @param input
	 *            The input text.
	 * @return The output text converted to upper case.
	 */
	public static StringBuffer toUpperCase(StringBuffer input) {

		char[] inputChars = input.toString().toCharArray();
		char[] outputChar = new char[inputChars.length];

		for (int i = 0; i < inputChars.length; i++) {
			outputChar[i] = Character.toUpperCase(inputChars[i]);
		}

		String output = String.valueOf(outputChar);

		return new StringBuffer(output);
	}

	/**
	 * Provide a string with the required number of consecutive spaces.
	 * 
	 * @param numberOfSpaces
	 *            the number of spaces.
	 * @return The with the the number of spaces provide.
	 */
	public static String getConsecutiveSpaces(int numberOfSpaces) {

		StringBuffer output = new StringBuffer();

		for (int i = 0; i < numberOfSpaces; i++) {

			output.append(" "); //$NON-NLS-1$
		}

		return output.toString();
	}

	/**
	 * In the context of HTML some new lines, carriage return, tabulation and
	 * spaces can be discarded.
	 * 
	 * @param character
	 *            A character.
	 * @return True if it could be discarded.
	 */
	private static boolean isDiscartableCharacter(char character) {

		char[] discartableCharacters = { '\n', '\r', '\t', ' ' };

		for (char compareCharacter : discartableCharacters) {
			if (compareCharacter == character) {
				return true;
			}
		}

		return false;
	}

	/**
	 * In the context of HTML, indicate if the string has any character that is
	 * relevant. That is, if they are different from new lines, carriage return,
	 * tabulation and spaces.
	 * 
	 * @param content
	 *            The text.
	 * @return If the text has relevant characters.
	 */
	public static boolean hasRelevantCharacters(String content) {

		for (char character : content.toCharArray()) {
			if (!isDiscartableCharacter(character)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Check if the character is alphabetic.
	 * 
	 * @param character
	 *            The character.
	 * @return True if it is alphabetic.
	 */
	public static boolean isAlphabetic(char character) {

		char[] validCharacters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'x', 'y', 'w', 'z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9', '?', '!' };

		for (char compareCharacter : validCharacters) {

			if (Character.toUpperCase(compareCharacter) == Character
					.toUpperCase(character)) {

				return true;
			}
		}

		return false;
	}

	/**
	 * Remove all the empty lines.
	 * 
	 * @param input
	 *            The input text.
	 * @return The text cleaned up.
	 */
	public static StringBuffer removeEmptyLines(StringBuffer input) {

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(input);

		while (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();

			if (!"".equals(token.trim())) { //$NON-NLS-1$

				if (tokenizer.isFoundNewLine()) {

					output.append(token + StringSupport.getDOSNewLine());

				} else {

					output.append((token));
				}
			}
		}

		return output;
	}

	/**
	 * Filter the lines and return only the ones that have the token.
	 * 
	 * @param lines
	 *            The lines of the text.
	 * @param token
	 *            The token to search.
	 * @param caseSensitive
	 *            if the case should be considered.
	 * @return The filtered lines.
	 */
	public static String[] filterLinesWithToken(String[] lines, String token,
			boolean caseSensitive) {

		if (lines == null || token == null) {
			return lines;
		}

		List<String> filteredLines = new ArrayList<>();

		for (String line : lines) {

			if (line != null) {
				if (caseSensitive) {
					if (line.indexOf(token) >= 0) {
						filteredLines.add(line);
					}
				} else {
					if (line.toUpperCase().indexOf(token.toUpperCase()) >= 0) {
						filteredLines.add(line);
					}
				}
			}
		}

		String[] finalList = new String[filteredLines.size()];
		return filteredLines.toArray(finalList);
	}

	/**
	 * Filter the lines and return only the ones that have the tokens.
	 * 
	 * @param lines
	 *            The lines of the text.
	 * @param token
	 *            The tokens to search.
	 * @param caseSensitive
	 *            if the case should be considered.
	 * @return The filtered lines.
	 */
	public static String[] filterLinesWithTokens(String[] lines,
			String[] tokens, boolean[] caseSensitive) {

		if (lines == null || tokens == null || caseSensitive == null) {
			return lines;
		}

		if (tokens.length != caseSensitive.length) {
			return lines;
		}

		String[] linesFiltered = lines;
		for (int i = 0; i < tokens.length; i++) {
			linesFiltered = filterLinesWithToken(linesFiltered, tokens[i],
					caseSensitive[i]);
		}

		return linesFiltered;
	}

}
