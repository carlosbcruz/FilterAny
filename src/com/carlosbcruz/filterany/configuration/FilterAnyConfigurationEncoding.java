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

package com.carlosbcruz.filterany.configuration;

import com.carlosbcruz.filterany.FilterAnyEncoding;
import com.carlosbcruz.util.SimpleProperties;

/**
 * Configure the encoding.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationEncoding {

	public static final String DO_NOT_SET = "doNotSet"; //$NON-NLS-1$
	public static final String DETECT = "detect"; //$NON-NLS-1$

	// Parameter to store the input encoding.
	private static final String INPUT_ENCODING = "inputEncoding"; //$NON-NLS-1$
	// Parameter to store the output encoding.
	private static final String OUTPUT_ENCODING = "outputEncoding"; //$NON-NLS-1$

	/**
	 * Read the encoding configuration.
	 */
	static void initialize() {

		// Current selection on file.
		String storedInputEncoding = SimpleProperties
				.getStringPropertyWithDefault(INPUT_ENCODING, DO_NOT_SET);

		if (DETECT.equals(storedInputEncoding)) {

			FilterAnyEncoding.getInstance().setDetectInput(true);

		} else {

			FilterAnyEncoding.getInstance().setDetectInput(false);

			if (!DO_NOT_SET.equals(storedInputEncoding)) {

				FilterAnyEncoding.getInstance().setInputFileEncoding(
						storedInputEncoding);
			}
		}

		String storedOutputEncoding = SimpleProperties
				.getStringPropertyWithDefault(OUTPUT_ENCODING, DO_NOT_SET);

		if (!DO_NOT_SET.equals(storedOutputEncoding)) {

			FilterAnyEncoding.getInstance().setOutputFileEncoding(
					storedOutputEncoding);
		}
	}

	/**
	 * Set the input encoding.
	 * 
	 * @param encoding
	 *            The input encoding.
	 */
	protected static void setInputEncoding(String encoding) {

		SimpleProperties.setStringProperty(INPUT_ENCODING, encoding);

		if (DETECT.equals(encoding)) {

			FilterAnyEncoding.getInstance().setDetectInput(true);

		} else {

			FilterAnyEncoding.getInstance().setDetectInput(false);

			if (DO_NOT_SET.equals(encoding)) {

				FilterAnyEncoding.getInstance().setInputFileEncoding(null);

			} else {

				FilterAnyEncoding.getInstance().setInputFileEncoding(encoding);
			}
		}
	}

	/**
	 * Set the output encoding.
	 * 
	 * @param encoding
	 *            The output encoding.
	 */
	protected static void setOutputEncoding(String encoding) {

		SimpleProperties.setStringProperty(OUTPUT_ENCODING, encoding);

		if (DO_NOT_SET.equals(encoding)) {

			FilterAnyEncoding.getInstance().setOutputFileEncoding(null);

		} else {

			FilterAnyEncoding.getInstance().setOutputFileEncoding(encoding);
		}
	}

	/**
	 * Provide the input encoding.
	 * 
	 * @return The input encoding.
	 */
	protected static String getCurrentInputEncoding() {

		if (FilterAnyEncoding.getInstance().isDetectInput()) {

			return DETECT;

		}

		String encoding = FilterAnyEncoding.getInstance()
				.getInputFileEncoding();

		if (encoding == null) {

			return DO_NOT_SET;

		}

		return encoding;
	}

	/**
	 * Provide the output encoding.
	 * 
	 * @return The output encoding.
	 */
	protected static String getCurrentOutputEncoding() {

		String encoding = FilterAnyEncoding.getInstance()
				.getOutputFileEncoding();

		if (encoding == null) {

			return DO_NOT_SET;

		}

		return encoding;
	}
}
