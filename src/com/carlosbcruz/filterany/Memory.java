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
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import javax.swing.JLabel;

import com.carlosbcruz.util.ExceptionSupport;

/**
 * Show the memory used.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class Memory extends JLabel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	Memory() {

		setOpaque(false);

		setToolTipText(Text.get(Text.MEMORY_TOOLTIP));

		showMemory();

		Thread update = new Thread(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void run() {

				while (true) {

					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// do nothing.
					}

					showMemory();
				}
			}

		});

		update.start();
	}

	/**
	 * Show the memory available.
	 */
	private void showMemory() {

		MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heap = memBean.getHeapMemoryUsage();

		long maxMemory = heap.getMax();
		long usedMemory = heap.getUsed();

		int divisions = 0;
		do {

			if (maxMemory >= 10000) {
				maxMemory /= 1024;
				usedMemory /= 1024;
				divisions++;
			}

		} while (maxMemory >= 10000);

		String suffix = new String();
		switch (divisions) {
		case 1: {
			suffix = "Kb"; //$NON-NLS-1$
			break;
		}
		case 2: {
			suffix = "Mb"; //$NON-NLS-1$
			break;
		}
		case 3: {
			suffix = "Gb"; //$NON-NLS-1$
			break;
		}
		case 4: {
			suffix = "Tb"; //$NON-NLS-1$
			break;
		}
		default:
			ExceptionSupport.handleException(Text.get(Text.INTERNAL_ERROR_9));
			break;
		}

		int maximum = (int) maxMemory;
		int used = (int) usedMemory;

		String message = null;
		if (used > maximum / 2) {
			message = Text.get(Text.CLICK_THE_TRASH_TO_CLEAR_MEMORY);
			setForeground(Color.RED);
		} else {
			message = ""; //$NON-NLS-1$
			setForeground(Color.BLACK);
		}

		setText(message + maximum + suffix + "/" + used + suffix); //$NON-NLS-1$

	}
}
