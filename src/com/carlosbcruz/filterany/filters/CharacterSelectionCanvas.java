package com.carlosbcruz.filterany.filters;

import java.util.ArrayList;

import com.carlosbcruz.util.LineTokenizer;

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

/**
 * A free selection canvas to move characters freely.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CharacterSelectionCanvas extends FreeSelectionCanvas {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param contentParameter
	 */
	public CharacterSelectionCanvas(StringBuffer contentParameter) {

		super(contentParameter);

	}

	/**
	 * @param contentParameter
	 *            The text containing the elements.
	 * @return The elements identified.
	 */
	@Override
	ArrayList<ArrayList<SelectedElement>> getElements(
			StringBuffer contentParameter) {

		LineTokenizer lineTokenizer = new LineTokenizer(contentParameter);

		ArrayList<ArrayList<SelectedElement>> lines = new ArrayList<>();

		while (lineTokenizer.hasMoreTokens()) {

			String line = lineTokenizer.nextToken();

			ArrayList<SelectedElement> constructingElements = new ArrayList<>();

			for (char character : line.toCharArray()) {

				SelectedElement element = addElement(String.valueOf(character));

				constructingElements.add(element);
			}

			lines.add(constructingElements);

			this.currentLine += this.fontHeight + SPACE_BETWEEN_LINES;
			this.currentColumn = TEXT_BOX_DELTA_X + INITIAL_HEIGHT;

			if (this.currentLine > this.canvasHeight) {
				this.canvasHeight = this.currentLine;
			}
		}

		return lines;
	}

}
