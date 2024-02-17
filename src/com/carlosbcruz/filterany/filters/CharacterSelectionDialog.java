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
package com.carlosbcruz.filterany.filters;

/**
 * A dialog to hold the free selection canvas to select characters.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CharacterSelectionDialog extends FreeSelectionDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The window title.
	 * @param contentParameter
	 *            A text.
	 * @param tableSelection
	 *            Incate if a table creaton is necessary.
	 */
	public CharacterSelectionDialog(String title,
			StringBuffer contentParameter, boolean tableSelection) {

		super(title, contentParameter, tableSelection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.filters.FreeSelectionDialog#getCanvas(java.
	 * lang.StringBuffer)
	 */
	@Override
	FreeSelectionCanvas getCanvas(StringBuffer contentParameter) {

		return new CharacterSelectionCanvas(contentParameter);
	}

}
