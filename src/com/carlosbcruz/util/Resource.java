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

import java.text.DateFormat;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Provides a simple internationalization feature only for the
 * com.carlosbcruz.util classes.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public abstract class Resource {

	private ResourceBundle resource = null;

	/**
	 * Initialize the resource.
	 */
	public Resource() {
		this.resource = ResourceBundle.getBundle(getResourceName());
	}

	public abstract String getResourceName();

	/**
	 * Provides the content for a specific key
	 * 
	 * @param key
	 *            the key of a content
	 * @return the content
	 */
	public String get(String key) {

		String content;

		try {
			content = this.resource.getString(key);
		} catch (MissingResourceException e) {
			ExceptionSupport.handleException("Could not find key: " + key); //$NON-NLS-1$
			throw new RuntimeException(e);
		}

		return content;
	}

	/**
	 * Replace {0} by a parameter provided.
	 * 
	 * @param key
	 *            The key to search for.
	 * @param replaceBy
	 *            The string use in the replacement of {0}
	 * @return The content with replacements.
	 */
	public String get(String key, String replaceBy) {

		String content = get(key);
		return StringSupport.replace(content, "{0}", replaceBy); //$NON-NLS-1$
	}

	/**
	 * Replace {x} by a parameter provided.
	 * 
	 * @param key
	 *            The key to search for.
	 * @param replaceBy0
	 *            The string use in the replacement of {0}
	 * @param replaceBy1
	 *            The string use in the replacement of {1}
	 * @return The content with replacements.
	 */
	public String get(String key, String replaceBy0, String replaceBy1) {

		String content = get(key);

		content = StringSupport.replace(content, "{0}", replaceBy0); //$NON-NLS-1$
		content = StringSupport.replace(content, "{1}", replaceBy1); //$NON-NLS-1$

		return content;
	}

	/**
	 * Replace {x} by a parameter provided.
	 * 
	 * @param key
	 *            The key to search for.
	 * @param replaceBy0
	 *            The string use in the replacement of {0}
	 * @param replaceBy1
	 *            The string use in the replacement of {1}
	 * @param replaceBy2
	 *            The string use in the replacement of {2}
	 * @return The content with replacements.
	 */
	public String get(String key, String replaceBy0, String replaceBy1,
			String replaceBy2) {

		String content = get(key);

		content = StringSupport.replace(content, "{0}", replaceBy0); //$NON-NLS-1$
		content = StringSupport.replace(content, "{1}", replaceBy1); //$NON-NLS-1$
		content = StringSupport.replace(content, "{2}", replaceBy2); //$NON-NLS-1$

		return content;
	}

	/**
	 * Replace {x} by a parameter provided.
	 * 
	 * @param key
	 *            The key to search for.
	 * @param replaceBy0
	 *            The string use in the replacement of {0}
	 * @param replaceBy1
	 *            The string use in the replacement of {1}
	 * @param replaceBy2
	 *            The string use in the replacement of {2}
	 * @param replaceBy3
	 *            The string use in the replacement of {3}
	 * @return The content with replacements.
	 */
	public String get(String key, String replaceBy0, String replaceBy1,
			String replaceBy2, String replaceBy3) {

		String content = get(key);

		content = StringSupport.replace(content, "{0}", replaceBy0); //$NON-NLS-1$
		content = StringSupport.replace(content, "{1}", replaceBy1); //$NON-NLS-1$
		content = StringSupport.replace(content, "{2}", replaceBy2); //$NON-NLS-1$
		content = StringSupport.replace(content, "{3}", replaceBy3); //$NON-NLS-1$

		return content;
	}

	/**
	 * Replace {x} by a parameter provided.
	 * 
	 * @param key
	 *            The key to search for.
	 * @param replaceBy0
	 *            The string use in the replacement of {0}
	 * @param replaceBy1
	 *            The string use in the replacement of {1}
	 * @param replaceBy2
	 *            The string use in the replacement of {2}
	 * @param replaceBy3
	 *            The string use in the replacement of {3}
	 * @param replaceBy4
	 *            The string use in the replacement of {4}
	 * @return The content with replacements.
	 */
	public String get(String key, String replaceBy0, String replaceBy1,
			String replaceBy2, String replaceBy3, String replaceBy4) {

		String content = get(key);

		content = StringSupport.replace(content, "{0}", replaceBy0); //$NON-NLS-1$
		content = StringSupport.replace(content, "{1}", replaceBy1); //$NON-NLS-1$
		content = StringSupport.replace(content, "{2}", replaceBy2); //$NON-NLS-1$
		content = StringSupport.replace(content, "{3}", replaceBy3); //$NON-NLS-1$
		content = StringSupport.replace(content, "{4}", replaceBy4); //$NON-NLS-1$

		return content;
	}

	/**
	 * Replace {x} by a parameter provided.
	 * 
	 * @param key
	 *            The key to search for.
	 * @param replaceBy0
	 *            The string use in the replacement of {0}
	 * @param replaceBy1
	 *            The string use in the replacement of {1}
	 * @param replaceBy2
	 *            The string use in the replacement of {2}
	 * @param replaceBy3
	 *            The string use in the replacement of {3}
	 * @param replaceBy4
	 *            The string use in the replacement of {4}
	 * @param replaceBy5
	 *            The string use in the replacement of {5}
	 * @return The content with replacements.
	 */
	public String get(String key, String replaceBy0, String replaceBy1,
			String replaceBy2, String replaceBy3, String replaceBy4,
			String replaceBy5) {

		String content = get(key);

		content = StringSupport.replace(content, "{0}", replaceBy0); //$NON-NLS-1$
		content = StringSupport.replace(content, "{1}", replaceBy1); //$NON-NLS-1$
		content = StringSupport.replace(content, "{2}", replaceBy2); //$NON-NLS-1$
		content = StringSupport.replace(content, "{3}", replaceBy3); //$NON-NLS-1$
		content = StringSupport.replace(content, "{4}", replaceBy4); //$NON-NLS-1$
		content = StringSupport.replace(content, "{5}", replaceBy5); //$NON-NLS-1$

		return content;
	}

	/**
	 * Replace {x} by a parameter provided.
	 * 
	 * @param key
	 *            The key to search for.
	 * @param replaceBy0
	 *            The string use in the replacement of {0}
	 * @param replaceBy1
	 *            The string use in the replacement of {1}
	 * @param replaceBy2
	 *            The string use in the replacement of {2}
	 * @param replaceBy3
	 *            The string use in the replacement of {3}
	 * @param replaceBy4
	 *            The string use in the replacement of {4}
	 * @param replaceBy5
	 *            The string use in the replacement of {5}
	 * @param replaceBy6
	 *            The string use in the replacement of {6}
	 * @return The content with replacements.
	 */
	public String get(String key, String replaceBy0, String replaceBy1,
			String replaceBy2, String replaceBy3, String replaceBy4,
			String replaceBy5, String replaceBy6) {

		String content = get(key);

		content = StringSupport.replace(content, "{0}", replaceBy0); //$NON-NLS-1$
		content = StringSupport.replace(content, "{1}", replaceBy1); //$NON-NLS-1$
		content = StringSupport.replace(content, "{2}", replaceBy2); //$NON-NLS-1$
		content = StringSupport.replace(content, "{3}", replaceBy3); //$NON-NLS-1$
		content = StringSupport.replace(content, "{4}", replaceBy4); //$NON-NLS-1$
		content = StringSupport.replace(content, "{5}", replaceBy5); //$NON-NLS-1$
		content = StringSupport.replace(content, "{6}", replaceBy6); //$NON-NLS-1$

		return content;
	}

	/**
	 * Format the date according to the locale
	 * 
	 * @param date
	 *            the date
	 * @return the date in string format according to locale
	 */
	public static String formatDate(Date date) {

		DateFormat formatter = DateFormat.getDateTimeInstance();

		return formatter.format(date);
	}
}
