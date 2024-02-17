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
 * Show the list of shortcuts.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ShortCuts extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Show the help file.
	 * 
	 * @throws IOException
	 */
	ShortCuts(Component superComponent) throws IOException {

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		JEditorPane helpPane = new JEditorPane(
				ClassLoader.getSystemClassLoader().
						getResource(FilterAnyConfiguration.RESOURCE_LOCATION
								+ "ShortCuts_" + Locale.getDefault().getLanguage() //$NON-NLS-1$
								+ ".html")); //$NON-NLS-1$

		helpPane.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent event) {

				int key = event.getKeyCode();

				if (key == KeyEvent.VK_ESCAPE) {
					setVisible(false);
				}
			}
		});

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

		setTitle(Text.get(Text.FILTERANY_SHORTCUT_WINDOW_TITLE));
		setLocation(dialongPositionX, dialongPositionY);
		setSize(FilterAnyConfiguration.getFilterAnyHelpWidth(),
				FilterAnyConfiguration.getFilterAnyHelpHeight());

		setContentPane(new JScrollPane(helpPane));
		setModal(true);
		setVisible(true);

	}
}
