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

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

/**
 * Adds itself as a key listener of all components.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class GlobalKeyJDialog extends JDialog implements ContainerListener,
		KeyListener {

	private static final long serialVersionUID = 1L;

	private List<GlobalKeyListener> listeners = new ArrayList<>(3);

	/**
	 * Constructor.
	 */
	public GlobalKeyJDialog() {

		addKeyAndContainerListenerRecursively(this);

	}

	// The following function is recursive and is intended for internal use
	// only. It is private to prevent anyone calling it from other classes
	// The function takes a Component as an argument and adds this Dialog as a
	// KeyListener to it.
	// Besides it checks if the component is actually a Container and if it is,
	// there are 2 additional things to be done to this Container :
	// 1 - add this Dialog as a ContainerListener to the Container
	// 2 - call this function recursively for every child of the Container.
	private void addKeyAndContainerListenerRecursively(Component c) {

		// To be on the safe side, try to remove KeyListener first just in case
		// it has been added before.
		// If not, it won't do any harm
		c.removeKeyListener(this);
		// Add KeyListener to the Component passed as an argument
		c.addKeyListener(this);

		if (c instanceof Container) {

			// Component c is a Container. The following cast is safe.
			Container cont = (Container) c;

			// To be on the safe side, try to remove ContainerListener first
			// just in case it has been added before.
			// If not, it won't do any harm
			cont.removeContainerListener(this);
			// Add ContainerListener to the Container.
			cont.addContainerListener(this);

			// Get the Container's array of children Components.
			Component[] children = cont.getComponents();

			// For every child repeat the above operation.
			for (int i = 0; i < children.length; i++) {
				addKeyAndContainerListenerRecursively(children[i]);
			}
		}
	}

	// The following function is the same as the function above with the
	// exception that it does exactly the opposite - removes this Dialog
	// from the listener lists of Components.

	private void removeKeyAndContainerListenerRecursively(Component componente) {

		componente.removeKeyListener(this);

		if (componente instanceof Container) {

			Container cont = (Container) componente;

			cont.removeContainerListener(this);

			Component[] children = cont.getComponents();

			for (int i = 0; i < children.length; i++) {
				removeKeyAndContainerListenerRecursively(children[i]);
			}
		}

	}

	// This function is called whenever a Component or a Container is added to
	// another Container belonging to this Dialog
	@Override
	public void componentAdded(ContainerEvent e) {

		addKeyAndContainerListenerRecursively(e.getChild());
	}

	// This function is called whenever a Component or a Container is removed
	// from another Container belonging to this Dialog
	@Override
	public void componentRemoved(ContainerEvent e) {

		removeKeyAndContainerListenerRecursively(e.getChild());
	}

	// This function is called whenever a Component belonging to this Dialog (or
	// the Dialog itself) gets the KEY_PRESSED event
	@Override
	public void keyPressed(KeyEvent e) {

		for (GlobalKeyListener listener : this.listeners) {

			listener.globalKeyPressed(e);
		}
	}

	// We need the following 2 functions to complete implementation of
	// KeyListener
	@Override
	public void keyReleased(KeyEvent e) {
		// Ignore.
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Ignore.
	}

	/**
	 * activate the listener.
	 */
	public void activateGlobalKeyListener() {

		addKeyAndContainerListenerRecursively(this);

	}

	/**
	 * Deactivate the listener.
	 * 
	 * @return the activated
	 */
	public void deactivateGlobalKeyListener() {

		removeKeyAndContainerListenerRecursively(this);

	}

	/**
	 * Add a global key listener.
	 * 
	 * @param listener
	 *            The listener.
	 */
	public void addGlobalKeyListener(GlobalKeyListener listener) {

		this.listeners.add(listener);
	}

	/**
	 * Remove a global key listener.
	 * 
	 * @param listener
	 *            The listener.
	 */
	public void removeGlobalKeyListener(GlobalKeyListener listener) {

		this.listeners.remove(listener);
	}

}
