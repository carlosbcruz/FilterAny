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

import javax.swing.Action;
import javax.swing.Icon;

/**
 * Provide basic actions to swing applications.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class SimpleActionProvider {

	/**
	 * Retrieve the action for a specific menu item
	 * 
	 * @param imageIdentification
	 *            The image
	 * @param label
	 *            The label
	 * @param mnemonicKey
	 *            The mnemonic
	 * @param shortDescription
	 *            A short description
	 * @return the decorator
	 */
	public static SimpleActionDecorator getSimpleAction(
			String imageIdentification, String label, int mnemonicKey,
			String shortDescription) {

		return getSimpleAction(imageIdentification, label, mnemonicKey,
				shortDescription, null);
	}

	/**
	 * Retrieve the action for a specific menu item
	 * 
	 * @param imageIdentification
	 *            The image
	 * @param label
	 *            The label
	 * @param mnemonicKey
	 *            The mnemonic
	 * @param shortDescription
	 *            A short description
	 * @param observer
	 *            An optional observer
	 * @return the decorator
	 */
	public static SimpleActionDecorator getSimpleAction(
			String imageIdentification, String label, int mnemonicKey,
			String shortDescription, SimpleActionObserver observer) {

		/**
		 * Inner class that represents the specific Action
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		class SimpleAction extends SimpleActionDecorator {

			private static final long serialVersionUID = 1L;

			SimpleAction(String name, Icon icon) {

				super(name, icon);
			}
		}

		SimpleActionDecorator simpleAction = new SimpleAction(label,
				SwingUtil.loadImage(imageIdentification));

		if (mnemonicKey != 0) {

			simpleAction
					.putValue(Action.MNEMONIC_KEY, new Integer(mnemonicKey));
		}

		simpleAction.putValue(Action.SHORT_DESCRIPTION, shortDescription);

		if (observer != null) {
			simpleAction.addObserver(observer);
		}

		return simpleAction;
	}
}
