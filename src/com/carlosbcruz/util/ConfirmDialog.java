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

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;

/**
 * A dialog to confirm or cancel a question.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ConfirmDialog extends Dialog {

	private static final long serialVersionUID = 1L;

	private Button confirmButton = null;
	private Button cancelButton = null;

	boolean confirmed = false;

	private Label messageLabel;

	/**
	 * A dialog to request a confirm or cancel option
	 * 
	 * @param parent
	 *            the parent frame
	 * @param message
	 *            the message
	 */
	public ConfirmDialog(Frame parent, String message) {

		super(parent, InternalResource.get(InternalResource.ALERT), false);

		this.confirmButton = new Button(
				InternalResource.get(InternalResource.CONFIRM));
		this.cancelButton = new Button(
				InternalResource.get(InternalResource.CANCEL));

		this.messageLabel = new Label(message);

		setLayout(new BorderLayout());

		add(this.messageLabel, BorderLayout.NORTH);

		// Add the buttons centralized.
		Panel buttonPanel = new Panel();
		buttonPanel.add(this.confirmButton);
		buttonPanel.add(this.cancelButton);
		add(buttonPanel, BorderLayout.CENTER);

		// Add space between the buttons and the window border
		add(new Label(" "), BorderLayout.WEST); //$NON-NLS-1$
		add(new Label(" "), BorderLayout.EAST); //$NON-NLS-1$

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#action(java.awt.Event, java.lang.Object)
	 */
	@Override
	public boolean action(Event InEvent, Object Param) {

		if (InEvent.target == this.confirmButton) {

			this.confirmed = true;
			setVisible(false);
		}

		if (InEvent.target == this.cancelButton) {

			this.confirmed = false;
			setVisible(false);
		}

		return true;
	}

	/**
	 * @return Returns the confirmed.
	 */
	public boolean isConfirmed() {

		return this.confirmed;
	}
}
