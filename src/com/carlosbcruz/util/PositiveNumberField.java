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

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A Text Field that accepts only numbers.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class PositiveNumberField extends AutoCleanUpTextField {

	private static final long serialVersionUID = 1L;

	private int maxValue = 1000;

	/**
	 * Indicate the acceptable number of columns.
	 * 
	 * @param maxValue
	 *            The maximum number acceptable.
	 * @param activated
	 *            Indicate if the clean-up behavior is activated.
	 */
	public PositiveNumberField(int maxValue, boolean activated) {

		super(10, activated);

		this.maxValue = maxValue;

		setDocument(new NumberFilterDocument());
	}

	/**
	 * Analyzes the model of a Text Field.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public class NumberFilterDocument extends PlainDocument {

		private static final long serialVersionUID = 1L;

		private StringBuffer buffer;

		/**
		 * Default document.
		 */
		public NumberFilterDocument() {

			this.buffer = new StringBuffer();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.text.PlainDocument#insertString(int,
		 * java.lang.String, javax.swing.text.AttributeSet)
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public void insertString(int offset, String text, AttributeSet aset)
				throws BadLocationException {

			if (text == null)
				return;

			this.buffer.setLength(0);

			// Reject all strings that cause the contents of the field not
			// to be a valid number (i.e., string representation of a double)
			try {

				this.buffer.append(getText(0, getLength()));

				this.buffer.insert(offset, text);

			} catch (BadLocationException exception) {
				return;
			} catch (StringIndexOutOfBoundsException exception) {
				return;
			}

			try {

				int value = Integer.parseInt(this.buffer.toString());

				// Allow only positive numbers.
				if (value < 0 || value > PositiveNumberField.this.maxValue) {
					return;
				}

			} catch (NumberFormatException nfe) {

				// Resulting string will not be number, so reject it
				return;
			}

			super.insertString(offset, text, aset);
		}
	}
}