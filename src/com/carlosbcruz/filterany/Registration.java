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

import java.util.Date;

/**
 * Provides registration information.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public abstract class Registration {

	public static final String REGISTRATION_KEY = "41.84.49.82.2.84.84.138.93.12.243.206.9.23.87.31"; //$NON-NLS-1$

	/**
	 * Provide the registration text.
	 * 
	 * @param htmlFormat
	 *            Provide the information in HTML format.
	 * 
	 * @return The registration text.
	 */
	public String getRegisteredText() {

		return getRegisteredContent();
	}

	/**
	 * Provide the registered email.
	 * 
	 * @return The registered email.
	 */
	public abstract String getRegisteredEmail();

	/**
	 * Provide the full registration text.
	 * 
	 * @param htmlFormat
	 *            Provide the information in HTML format.
	 * 
	 * @return The registration text.
	 */
	public String getFullRegisteredText(boolean htmlFormat) {

		return getFullRegisteredContent(htmlFormat);
	}

	/**
	 * Provide the content that was registered.
	 * 
	 * @return The key with the registered information.
	 */
	protected abstract String getRegisteredContent();

	/**
	 * Provide the content that was registered.
	 * 
	 * @param htmlFormat
	 *            Provide the information in HTML format.
	 * 
	 * @return The key with the registered information.
	 */
	protected abstract String getFullRegisteredContent(boolean htmlFormat);

	/**
	 * Provide the date the agreement date.
	 * 
	 * @return
	 */
	@SuppressWarnings("static-method")
	public long getAgreementDate() {

		return (new Date()).getTime();
	}

}
