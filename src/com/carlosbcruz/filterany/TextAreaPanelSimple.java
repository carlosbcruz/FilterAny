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

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.text.Caret;

import com.carlosbcruz.util.SwingUtil;

/**
 * A text area panel for simple texts.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextAreaPanelSimple extends TextAreaPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TextAreaPanelSimple() {

		super(null, "", new Color(240, 240, 240), new Color(255, 255, 255), //$NON-NLS-1$
				new Color(0, 0, 0), new Color(200, 200, 200), new Color(180,
						180, 180), new Color(180, 180, 180), new Color(200,
						200, 200), new Color(0, 0, 0),
				new Color(240, 240, 240), Color.black,
				TextAreaPanel.FIND_LOCATION.CENTER, false);
	}

	/**
	 * Provide the text area for initialization.
	 * 
	 * @return the text area.
	 */
	@Override
	JTextArea getTextAreaInstance() {

		TextAreaWithImageBackground textAreaInitialization = new TextAreaWithImageBackground();

		textAreaInitialization.setEditable(false);

		return textAreaInitialization;
	}

	/**
	 * Provide the caret.
	 * 
	 * @return The caret.
	 */
	@Override
	Caret getCaret() {

		return SwingUtil.getNonSelectableCaret();
	}

	/**
	 * Can be called to indicate that the text area is being closed.
	 */
	@Override
	public void closingTextArea() {

		if (this.textArea instanceof TextAreaWithImageBackground) {

			TextAreaWithImageBackground internalTextArea = (TextAreaWithImageBackground) this.textArea;
			internalTextArea.restoreCaret();
		}
	}

	/**
	 * Highlight the current line.
	 * 
	 * @param hightLightColor
	 *            the high light color.
	 */
	@Override
	void instantiateLinePainter(Color hightLightColor) {
		// Do nothing.
	}

}
