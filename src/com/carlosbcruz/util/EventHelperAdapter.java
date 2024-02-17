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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Helper class to implement popup.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class EventHelperAdapter extends MouseAdapter implements
		TreeSelectionListener {

	// Listeners to the user objects events.
	private ArrayList<JTreeEventHelperListener> listeners = new ArrayList<>();

	// If it is listening to tree events.
	private JTree tree = null;

	// Popup menus that can be opened
	private JPopupMenu popupMenu = null;

	// Control if the events are enabled or not.
	private boolean enabled = true;

	/**
	 * General constructor to no specific object.
	 */
	public EventHelperAdapter() {
		super();
	}

	/**
	 * Listening to a tree events.
	 * 
	 * @param tree
	 *            The tree.
	 */
	public EventHelperAdapter(JTree tree) {
		super();

		this.tree = tree;
	}

	/**
	 * Add a menu item to the popup menu.
	 * 
	 * @param menuItem
	 *            A menu item.
	 */
	public void addMenuItemToPopup(JMenuItem menuItem) {

		// Create the popup menus, transaction and checks
		if (this.popupMenu == null) {
			this.popupMenu = new JPopupMenu();
		}

		this.popupMenu.add(menuItem);
	}

	/**
	 * Add a separator.
	 */
	public void addSeparatorToPopup() {

		// Create the popup menus, transaction and checks
		if (this.popupMenu == null) {
			this.popupMenu = new JPopupMenu();
		}

		this.popupMenu.addSeparator();
	}

	/**
	 * Add a menu item to the popup.
	 * 
	 * @param menu
	 *            A menu.
	 */
	public void addMenuToPopup(JMenu menu) {

		// Create the popup menus, transaction and checks
		if (this.popupMenu == null) {
			this.popupMenu = new JPopupMenu();
		}

		this.popupMenu.add(menu);
	}

	/*
	 * (n�o-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		if (this.enabled) {
			maybeShowPopup(e, true);
		}
	}

	/*
	 * (n�o-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

		if (this.enabled) {
			maybeShowPopup(e, false);
		}
	}

	/**
	 * Controls the popup menu.
	 * 
	 * @param event
	 *            the mouse event passed
	 */
	private void maybeShowPopup(MouseEvent event, boolean isMousePressed) {

		if (event.isPopupTrigger()) {

			// If popup menu defined.
			if (this.popupMenu != null) {

				tryToSelectNode(event);

				this.popupMenu.show(event.getComponent(), event.getX(),
						event.getY());
			}

		} else {

			// Disconsider other events to avoid duplicated notifications.
			if (!isMousePressed) {

				return;
			}

			// Only consider the main buttom.
			if (event.getButton() != MouseEvent.BUTTON1) {

				return;
			}

			tryToSelectNode(event);
		}
	}

	/**
	 * Try to select a node based on a event.
	 * 
	 * @param event
	 *            The mouse event.
	 */
	private void tryToSelectNode(MouseEvent event) {

		// If listening to tree events.
		if (this.tree != null) {

			int rowSelected = this.tree.getRowForLocation(event.getX(),
					event.getY());

			// Only open popup if above an tree node
			if (rowSelected != -1) {

				TreePath path = this.tree.getPathForRow(rowSelected);
				fireThreePathSelection(path);
			}
		}
	}

	/**
	 * Fire the event of a tree path selection.
	 * 
	 * @param path
	 *            The three path selection.
	 */
	private void fireThreePathSelection(TreePath path) {

		if (path == null) {
			return;
		}

		DefaultMutableTreeNode lastNode = (DefaultMutableTreeNode) path
				.getLastPathComponent();

		// Select the node.
		TreeSelectionModel selectionModel = this.tree.getSelectionModel();
		if (lastNode != null) {
			selectionModel.setSelectionPath(SwingUtil.getTreePath(lastNode));
			// Fire events on user object events listeners.
			Object userObject = lastNode.getUserObject();
			for (JTreeEventHelperListener listener : this.listeners) {

				listener.eventOnUserObject(userObject);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event
	 * .TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent event) {

		TreePath treePath = event.getNewLeadSelectionPath();
		fireThreePathSelection(treePath);
	}

	/**
	 * Add a listener to events on user objects.
	 * 
	 * @param listener
	 *            The listener.
	 */
	public void addEventHelperListener(JTreeEventHelperListener listener) {

		this.listeners.add(listener);
	}

	/**
	 * Enable or disable the events.
	 * 
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
