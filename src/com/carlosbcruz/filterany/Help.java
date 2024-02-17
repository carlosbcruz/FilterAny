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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SwingUtil;

/**
 * Show the help content.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class Help extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Show the help file.
	 * 
	 * @throws IOException
	 */
	Help(Component superComponent) throws IOException {

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		JEditorPane helpPane = new JEditorPane(
				ClassLoader.getSystemClassLoader().getResource(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "helpContent_" + Locale.getDefault().getLanguage() //$NON-NLS-1$
						+ ".html")); //$NON-NLS-1$

		helpPane.setEditable(false);

		// Get the position and size of the dialog.
		Point superFrameLocation = superComponent.getLocation();
		Dimension superFrameDimension = superComponent.getSize();

		int superFrameCenterX = superFrameLocation.x
				+ ((int) superFrameDimension.getWidth() / 2);
		int superFrameCenterY = superFrameLocation.y
				+ ((int) superFrameDimension.getHeight() / 2);

		int dialongPositionX = superFrameCenterX
				- FilterAnyConfiguration.getFilterAnyHelpWidth() / 2;
		int dialongPositionY = superFrameCenterY
				- FilterAnyConfiguration.getFilterAnyHelpHeight() / 2;

		setTitle(Text.get(Text.FILTERANY_HELP_WINDOW_TITLE));
		setLocation(dialongPositionX, dialongPositionY);
		setSize(FilterAnyConfiguration.getFilterAnyHelpWidth(),
				FilterAnyConfiguration.getFilterAnyHelpHeight());

		setContentPane(new JScrollPane(helpPane));

		helpPane.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					setVisible(false);
				}

			}
		});

		setModal(true);
		setVisible(true);
	}
}
