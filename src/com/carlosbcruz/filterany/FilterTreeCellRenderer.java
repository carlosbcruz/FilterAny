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
import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;

/**
 * Customize the tree cell render to show different types of filtersWindow.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterTreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	private ImageIcon filterIcon = null;
	private ImageIcon filterWithCommandPromptIcon = null;
	private ImageIcon filterOnlyOnTextSpecialBehaviorWithCommandPromptIcon = null;
	private ImageIcon filterOnlyOnTextSpecialBehaviorBehaviorIcon = null;
	private ImageIcon filterDualInputSpecialBehaviorWithCommandPromptIcon = null;
	private ImageIcon filterDualInputSpecialBehaviorBehaviorIcon = null;
	private ImageIcon filterForBinaryFilesIcon = null;
	private ImageIcon demonstrationBehaviorIcon = null;

	private Color backgroundColor = new Color(255, 255, 255);

	/**
	 * Default constructor. Create the internal icons.
	 */
	public FilterTreeCellRenderer(Color backgroundColorParameter) {

		super();

		if (backgroundColorParameter != null) {
			this.backgroundColor = backgroundColorParameter;
			setBackground(backgroundColorParameter);
		}

		// Retrieve the icon for a simple filter
		URL filterURL = FilterTreeCellRenderer.class
				.getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "process-icon-small.png"); //$NON-NLS-1$
		if (filterURL != null) {
			this.filterIcon = new ImageIcon(filterURL);
		}

		// Retrieve the icon for a filter with command line
		URL filterWithCommandPromptURL = FilterTreeCellRenderer.class
				.getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "process-command-line-icon-small.png"); //$NON-NLS-1$
		if (filterWithCommandPromptURL != null) {
			this.filterWithCommandPromptIcon = new ImageIcon(
					filterWithCommandPromptURL);
		}

		// Retrieve the icon for a filter with only on text special behavior
		// with command
		// line.
		URL filterWithOnlyOnTextSpecialBehaviorWithCommandPromptURL = FilterTreeCellRenderer.class
				.getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "special-process-command-line-icon-small.png"); //$NON-NLS-1$
		if (filterWithOnlyOnTextSpecialBehaviorWithCommandPromptURL != null) {
			this.filterOnlyOnTextSpecialBehaviorWithCommandPromptIcon = new ImageIcon(
					filterWithOnlyOnTextSpecialBehaviorWithCommandPromptURL);
		}

		// Retrieve the icon for a filter with only on text special behavior
		URL filterWithOnlyOnTextSpecialBehaviorURL = FilterTreeCellRenderer.class
				.getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "special-process-icon-small.png"); //$NON-NLS-1$
		if (filterWithOnlyOnTextSpecialBehaviorURL != null) {
			this.filterOnlyOnTextSpecialBehaviorBehaviorIcon = new ImageIcon(
					filterWithOnlyOnTextSpecialBehaviorURL);
		}

		// Retrieve the icon for a filter with dual input special behavior
		URL filterWithDualInputSpecialBehaviorURL = FilterTreeCellRenderer.class
				.getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "process-dual-pane-icon-small.png"); //$NON-NLS-1$
		if (filterWithDualInputSpecialBehaviorURL != null) {
			this.filterDualInputSpecialBehaviorBehaviorIcon = new ImageIcon(
					filterWithDualInputSpecialBehaviorURL);
		}

		// Retrieve the icon for a filter for binary files.
		URL filterForBinaryFilesURL = FilterTreeCellRenderer.class
				.getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "BinaryFilesAndCommandLine.png"); //$NON-NLS-1$
		if (filterForBinaryFilesURL != null) {
			this.filterForBinaryFilesIcon = new ImageIcon(
					filterForBinaryFilesURL);
		}

		// Retrieve the icon for a filter with dual input and command line
		// special behavior
		URL filterWithDualInputAndCommandLineSpecialBehaviorURL = FilterTreeCellRenderer.class
				.getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "process-dual-pane-and_command_line-icon-small.png"); //$NON-NLS-1$
		if (filterWithDualInputAndCommandLineSpecialBehaviorURL != null) {
			this.filterDualInputSpecialBehaviorWithCommandPromptIcon = new ImageIcon(
					filterWithDualInputAndCommandLineSpecialBehaviorURL);
		}

		// Retrieve the icon for a demonstration filter
		URL demonstrationURL = FilterTreeCellRenderer.class
				.getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "demonstration-icon-small.png"); //$NON-NLS-1$
		if (demonstrationURL != null) {
			this.demonstrationBehaviorIcon = new ImageIcon(demonstrationURL);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent
	 * (javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int,
	 * boolean)
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocusParameter) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocusParameter);

		setBackgroundNonSelectionColor(this.backgroundColor);

		if (leaf) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			Filter filterOntheTree = (Filter) node.getUserObject();

			if (filterOntheTree instanceof SpecialBehavior
					&& FilterAnyLogic.hasSpecialBehavior(
							(SpecialBehavior) filterOntheTree,
							SpecialBehavior.Behavior.DEMONSTRATION_ICON)) {

				setIcon(this.demonstrationBehaviorIcon);
				setToolTipText(filterOntheTree.getSmallDescription());

				if (filterOntheTree instanceof CommandLine) {

					if (filterOntheTree instanceof SpecialBehavior
							&& FilterAnyLogic.hasSpecialBehavior(
									(SpecialBehavior) filterOntheTree,
									SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT)) {

						setEnabled(FilterAnyConfiguration.isTextMode());
						return this;
					}
				} else {

					if (filterOntheTree instanceof SpecialBehavior
							&& FilterAnyLogic.hasSpecialBehavior(
									(SpecialBehavior) filterOntheTree,
									SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT)) {

						setEnabled(FilterAnyConfiguration.isTextMode());
						return this;
					}
				}

			} else {

				// The nodes are always a default mutable. It is a safeguard.
				if (value instanceof DefaultMutableTreeNode) {

					// If it works on binary file then it is a commane line too.
					if (filterOntheTree instanceof BinaryFilesFilter) {
						setIcon(this.filterForBinaryFilesIcon);
						setToolTipText(filterOntheTree.getSmallDescription());

						return this;
					}

					if (filterOntheTree instanceof CommandLine) {

						if (filterOntheTree instanceof SpecialBehavior
								&& FilterAnyLogic
										.hasSpecialBehavior(
												(SpecialBehavior) filterOntheTree,
												SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT)) {

							setEnabled(FilterAnyConfiguration.isTextMode());

							setIcon(this.filterOnlyOnTextSpecialBehaviorWithCommandPromptIcon);
							setToolTipText(filterOntheTree
									.getSmallDescription());
							return this;
						}
						if (filterOntheTree instanceof SpecialBehavior
								&& FilterAnyLogic
										.hasSpecialBehavior(
												(SpecialBehavior) filterOntheTree,
												SpecialBehavior.Behavior.WORK_ON_DUAL_PANE)) {
							setIcon(this.filterDualInputSpecialBehaviorWithCommandPromptIcon);
							setToolTipText(filterOntheTree
									.getSmallDescription());
							return this;
						}

					} else {

						if (filterOntheTree instanceof SpecialBehavior
								&& FilterAnyLogic
										.hasSpecialBehavior(
												(SpecialBehavior) filterOntheTree,
												SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT)) {

							setEnabled(FilterAnyConfiguration.isTextMode());

							setIcon(this.filterOnlyOnTextSpecialBehaviorBehaviorIcon);
							setToolTipText(filterOntheTree
									.getSmallDescription());
							return this;
						}
						if (filterOntheTree instanceof SpecialBehavior
								&& FilterAnyLogic
										.hasSpecialBehavior(
												(SpecialBehavior) filterOntheTree,
												SpecialBehavior.Behavior.WORK_ON_DUAL_PANE)) {
							setIcon(this.filterDualInputSpecialBehaviorBehaviorIcon);
							setToolTipText(filterOntheTree
									.getSmallDescription());
							return this;
						}
					}

					if (filterOntheTree instanceof CommandLine) {

						Filter filter = filterOntheTree;

						setIcon(this.filterWithCommandPromptIcon);
						setToolTipText(filter.getSmallDescription());
						return this;

					}

					if (filterOntheTree != null) {

						Filter filter = filterOntheTree;

						setIcon(this.filterIcon);
						setToolTipText(filter.getSmallDescription());
						return this;

					}

					setToolTipText(null);
					return this;

				}

				setToolTipText(null);
			}

		} else {

			// The nodes are always a default mutable. It is a safeguard.
			if (value instanceof DefaultMutableTreeNode) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
				Object userObject = node.getUserObject();

				if (userObject instanceof CategoryNode) {

					CategoryNode categoryNode = (CategoryNode) userObject;

					setToolTipText(categoryNode.getCategoryTooltip());
					setIcon(categoryNode.getCategoryIcon());

				} else {

					setToolTipText(null);
				}
			} else {

				setToolTipText(null);
			}
		}

		return this;
	}
}
