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

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the creation of the Control Dialogs
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextInformationController implements TextInformationRemoveListener {

	private static List<TextInformation> textInformationInstances = new ArrayList<>();

	private static TextInformationController textInformationControllerInstance = null;

	/**
	 * Constructor.
	 */
	private TextInformationController() {
		// Private constructor.
	}

	/**
	 * Provide the singleton.
	 * 
	 * @return The dialog controller.
	 */
	public static TextInformationController getInstance() {

		if (textInformationControllerInstance == null) {

			textInformationControllerInstance = new TextInformationController();
		}

		return textInformationControllerInstance;
	}

	/**
	 * Hide all windows.
	 */
	public static void hideAll() {

		for (TextInformation instance : textInformationInstances) {

			instance.dispose();
		}

		textInformationInstances = new ArrayList<>();
	}

	/**
	 * Create a text information.
	 * 
	 * @param title
	 *            Window title.
	 * @param leftText
	 *            The left text to be compared.
	 * @param rightText
	 *            The right text to be compared.
	 */
	public void createTextInformation(String titleComplement,
			StringBuffer textParameter, StringBuffer selectedTextParameter) {

		textInformationInstances.add(new TextInformation(titleComplement,
				textParameter, selectedTextParameter, this));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.carlosbcruz.filterany.TextInformationRemoveListener#remove(com.
	 * carlosbcruz.filterany.TextInformation)
	 */
	@Override
	public void remove(TextInformation textInformation) {

		textInformationInstances.remove(textInformation);
	}
}
