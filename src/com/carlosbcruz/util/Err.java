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

/**
 * Manage a simple output to both standard error and file.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class Err {

	private static ExecutionStream outStream = new ExecutionStream();

	static {
		outStream.setOutputFileNameSufix("stderr"); //$NON-NLS-1$
	}

	/**
	 * Inform the current file name
	 * 
	 * @return the absolute file name
	 */
	public static String getFileName() {

		return outStream.getFileName();
	}

	/**
	 * Print a message
	 * 
	 * @param message
	 *            the message
	 */
	public static void print(String message) {

		outStream.print(message);
		System.err.print(message);
	}

	/**
	 * Print a message
	 * 
	 * @param message
	 *            the message
	 */
	public static void print(StringBuffer message) {

		outStream.print(message.toString());
		System.err.print(message.toString());
	}

	/**
	 * Print a message followed by a new line
	 * 
	 * @param message
	 *            the message
	 */
	public static void println(String message) {

		outStream.println(message);
		System.err.println(message);
	}

	/**
	 * Print a message followed by a new line
	 * 
	 * @param message
	 *            the message
	 */
	public static void println(StringBuffer message) {

		outStream.println(message.toString());
		System.err.println(message.toString());
	}

	/**
	 * Indicate the directory to create the output file. The default is C:\
	 * 
	 * @param outputDirectory
	 *            The target directory
	 */
	public static void setOutputDirectory(String outputDirectory) {

		outStream.setOutputDirectory(outputDirectory);
	}

	/**
	 * Indicate the output file sufix.
	 * 
	 * @param outputFileNameSufix
	 *            The target file sufix.
	 */
	public static synchronized void setOutputFileNameSufix(
			String outputFileNameSufix) {

		outStream.setOutputFileNameSufix(outputFileNameSufix);
	}
}
