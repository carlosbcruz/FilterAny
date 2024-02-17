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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Controls the encodings logic on the application.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnyEncoding {

	// Indicate the encoding that should be used.
	private boolean detectInput = false;
	private String inputFileEncoding = null;
	private String outputFileEncoding = null;
	private static final String defaultEncoding = "UTF-8"; //$NON-NLS-1$

	private static FilterAnyEncoding instance = null;

	/**
	 * Private constructor of a singleton.
	 */
	private FilterAnyEncoding() {

		super();
	}

	/**
	 * Provide the instance.
	 * 
	 * @return The instance.
	 */
	public static FilterAnyEncoding getInstance() {

		if (instance == null) {

			instance = new FilterAnyEncoding();
		}

		return instance;
	}

	/**
	 * Provide the encodings list.
	 * 
	 * @return The encodings list.
	 */
	public static String[] getEncodings() {

		ArrayList<String> encodings = new ArrayList<>();
		Map<String, Charset> map = Charset.availableCharsets();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {

			// Get charset name
			String charsetName = it.next();

			Charset charset = Charset.forName(charsetName);

			encodings.add(charset.name());
		}

		String[] encodingsArray = new String[map.size()];
		return encodings.toArray(encodingsArray);
	}

	/**
	 * Inform the encoding of the input files.
	 * 
	 * @return the inputFileEncoding The encoding.
	 */
	public String getInputFileEncoding() {

		return this.inputFileEncoding;
	}

	/**
	 * Inform the default file encoding.
	 * 
	 * @return the The default encoding.
	 */
	public static String getDefaultFileEncoding() {

		return defaultEncoding;
	}

	/**
	 * Set the encoding of the input files.
	 * 
	 * @param inputFileEncodingParameter
	 *            the inputFileEncodingParameter to set The encoding.
	 */
	public void setInputFileEncoding(String inputFileEncodingParameter) {

		this.inputFileEncoding = inputFileEncodingParameter;
	}

	/**
	 * Inform the encoding of the output files.
	 * 
	 * @return the outputFileEncoding The encoding.
	 */
	public String getOutputFileEncoding() {

		return this.outputFileEncoding;
	}

	/**
	 * Set the encoding of the output files.
	 * 
	 * @param outputFileEncodingParameter
	 *            the outputFileEncodingParameter to set The encoding.
	 */
	public void setOutputFileEncoding(String outputFileEncodingParameter) {

		this.outputFileEncoding = outputFileEncodingParameter;
	}

	/**
	 * Indicate if the input file encoding should be detected.
	 * 
	 * @return the detectInput If the input file encoding should be detected.
	 */
	public boolean isDetectInput() {

		return this.detectInput;
	}

	/**
	 * Set if the input file encoding should be detected.
	 * 
	 * @param detectInputParameter
	 *            the detectInput to set If the input file encoding should be
	 *            detected.
	 */
	public void setDetectInput(boolean detectInputParameter) {

		this.detectInput = detectInputParameter;
	}

}
