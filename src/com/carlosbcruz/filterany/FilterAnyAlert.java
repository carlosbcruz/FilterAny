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
import java.io.IOException;

import javax.swing.JOptionPane;

import com.carlosbcruz.filterany.FilterAnySound.SOUNDS;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;

/**
 * Alert the user of some action.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnyAlert {

	private static boolean playAlert = false;

	/**
	 * Alert the user with sound that some filter has issued an alarm.
	 * 
	 * @param superComponent
	 *            The component that will be the superior one of the alert
	 *            message.
	 */
	public static void alertUser(Component superComponent) {

		Runnable alertSound = new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void run() {

				while (playAlert) {

					FilterAnySound.playSound(SOUNDS.ALERT);

					try {

						Thread.sleep(5000);

					} catch (InterruptedException exception) {
						// Do nothing.
					}
				}
			}

		};

		playAlert = true;
		Thread alert = new Thread(alertSound);
		alert.start();

		if (FilterAnyConfiguration.isRunningInGraphicalMode()) {

			JOptionPane.showMessageDialog(superComponent,
					Text.get(Text.ALERT_WINDOW_MESSAGE),
					Text.get(Text.ALERT_WINDOW_TITLE),
					JOptionPane.WARNING_MESSAGE);

			playAlert = false;

		} else {

			System.out.println(Text.get(Text.ALERT_MESSAGE));

			byte[] line = new byte[100];
			try {
				while (System.in.read(line) <= 0) {
					// Do nothing.
				}
			} catch (IOException exception) {
				// Do nothing.
			}
		}

		playAlert = false;
	}
}
