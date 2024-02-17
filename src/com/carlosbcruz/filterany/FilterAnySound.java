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

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Handles the sounds for FilterAny
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnySound {

	public enum SOUNDS {
		GO, OFF, START, SWITCH, ON, ALERT
	}

	static void playSound(SOUNDS sound) {

		// Only play sound if sound is enabled.
		if (!FilterAnyConfiguration.isSoundEnable()) {
			return;
		}

		switch (sound) {

		case GO: {
			SwingUtil.tryToPlayWavFile(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "1.wav"); //$NON-NLS-1$
			break;
		}
		case OFF: {
			SwingUtil.tryToPlayWavFile(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "2.wav"); //$NON-NLS-1$
			break;
		}
		case START: {
			SwingUtil.tryToPlayWavFile(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "3.wav"); //$NON-NLS-1$
			break;
		}
		case SWITCH: {
			SwingUtil.tryToPlayWavFile(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "4.wav"); //$NON-NLS-1$
			break;
		}
		case ON: {
			SwingUtil.tryToPlayWavFile(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "5.wav"); //$NON-NLS-1$

			break;
		}
		case ALERT: {
			SwingUtil.tryToPlayWavFile(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "alert.wav"); //$NON-NLS-1$

			break;
		}
		default:
			ExceptionSupport.handleException(Text.get(Text.INTERNAL_ERROR_2));
		}
	}
}
