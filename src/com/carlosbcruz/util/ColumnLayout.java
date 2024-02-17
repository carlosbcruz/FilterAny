package com.carlosbcruz.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * Lay out the components in one column
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ColumnLayout implements LayoutManager {

	private static final int SPACE_BETWEEN_COMPONENTS = 10;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String,
	 * java.awt.Component)
	 */
	@Override
	public void addLayoutComponent(String name, Component comp) {
		// Do nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
	 */
	@Override
	public void removeLayoutComponent(Component comp) {
		// Do nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension preferredLayoutSize(Container target) {

		Dimension maximumDimension = new Dimension(0, 0);

		for (int i = 0; i < target.getComponentCount(); i++) {

			Component component = target.getComponent(i);

			if (component.isVisible()) {

				Dimension dimension = component.getPreferredSize();

				maximumDimension.width = Math.max(maximumDimension.width,
						dimension.width);

				// If there are more then one component then add the gap.
				if (i > 0) {

					maximumDimension.height += SPACE_BETWEEN_COMPONENTS;

				}

				maximumDimension.height += dimension.height;
			}
		}

		Insets insets = target.getInsets();

		maximumDimension.width += insets.left + insets.right;

		maximumDimension.height += insets.top + insets.bottom
				+ SPACE_BETWEEN_COMPONENTS * 2;

		return maximumDimension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension minimumLayoutSize(Container target) {

		Dimension minimumDimension = new Dimension(0, 0);

		for (int i = 0; i < target.getComponentCount(); i++) {

			Component component = target.getComponent(i);

			if (component.isVisible()) {

				Dimension dimension = component.getMinimumSize();

				minimumDimension.width = Math.max(minimumDimension.width,
						dimension.width);

				if (i > 0) {

					minimumDimension.height += SPACE_BETWEEN_COMPONENTS;

				}

				minimumDimension.height += dimension.height;
			}
		}

		Insets insets = target.getInsets();

		minimumDimension.width += insets.left + insets.right;

		minimumDimension.height += insets.top + insets.bottom
				+ SPACE_BETWEEN_COMPONENTS * 2;

		return minimumDimension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
	 */
	@Override
	public void layoutContainer(Container target) {

		Insets insets = target.getInsets();

		int verticalPosition = insets.top + SPACE_BETWEEN_COMPONENTS;

		for (int i = 0; i < target.getComponentCount(); i++) {

			Component component = target.getComponent(i);

			if (component.isVisible()) {

				Dimension dimension = component.getPreferredSize();

				component.setSize(dimension.width, dimension.height);

				component.setLocation(insets.left, verticalPosition);

				verticalPosition += SPACE_BETWEEN_COMPONENTS + dimension.height;
			}
		}
	}

}
