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

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 * A simple decorator to swing actions.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class SimpleActionDecorator extends AbstractAction implements
		SimpleActionSubject {

	private static final long serialVersionUID = 1L;

	// Listeners registered to the Action
	Collection<SimpleActionObserver> listeners = new Vector<>();

	/**
	 * Replicate the constructor specified at AbstractAction.
	 * 
	 * @param name
	 *            The name of the Action
	 * @param icon
	 *            The icon that represents the Action
	 */
	public SimpleActionDecorator(String name, Icon icon) {

		super(name, icon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public synchronized void actionPerformed(ActionEvent e) {

		notifyChange();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.account.util.AccountSubject#addObserver(com.carlosbcruz
	 * .account.util.AccountObserver)
	 */
	@Override
	public synchronized void addObserver(SimpleActionObserver observer) {

		this.listeners.add(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.account.util.AccountSubject#removeObserver(com.carlosbcruz
	 * .account.util.AccountObserver)
	 */
	@Override
	public synchronized void removeObserver(SimpleActionObserver observer) {

		this.listeners.remove(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.account.util.AccountSubject#notifyChange()
	 */
	@Override
	public synchronized void notifyChange() {

		Iterator<SimpleActionObserver> listenersIterator = this.listeners
				.iterator();
		// Follow all the listeners and notify of an Action occurrence.
		while (listenersIterator.hasNext()) {
			SimpleActionObserver observer = listenersIterator.next();
			observer.update(this);
		}
	}
}
