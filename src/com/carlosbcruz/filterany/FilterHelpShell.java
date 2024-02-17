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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SwingUtil;

/**
 * Comment
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterHelpShell extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiate a filter help shell.
	 * 
	 * @param superComponent
	 *            The super component.
	 * @param help
	 *            The help panel.
	 */
	public FilterHelpShell(Component superComponent, JPanel help) {

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		Dimension componentSize = superComponent.getSize();
		Point componentPosition = superComponent.getLocation();

		int positionX = (int) (componentSize.getWidth() - FilterAnyConfiguration
				.getFilterHelpShellWidth()) / 2;
		int positionY = (int) (componentSize.getHeight() - FilterAnyConfiguration
				.getFilterHelpShellHeight()) / 2;

		setLocation(componentPosition.x + positionX, componentPosition.y
				+ positionY);

		setSize(FilterAnyConfiguration.getFilterHelpShellWidth(),
				FilterAnyConfiguration.getFilterHelpShellHeight());

		setTitle(Text.get(Text.HELP_PANEL_TITLE));

		setContentPane(help);
	}
}
