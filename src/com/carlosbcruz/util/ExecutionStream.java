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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Manage a simple output to file.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ExecutionStream {

	private String outputDirectory = UtilityConfiguration.WRITE_OUT_MESSAGES_TO_ROOT ? "/" //$NON-NLS-1$
			: ""; //$NON-NLS-1$

	private String outputFileNameSufix = ""; //$NON-NLS-1$
	private String fileDate = setFileDate();

	private String outputFileNamePrefix = InternalResource
			.get(InternalResource.EXECUTION);

	private File outputFile = null;

	/**
	 * Provide the file date based on the calendar.
	 * 
	 * @return The file date based on the calendar.
	 */
	@SuppressWarnings("nls")
	private static String setFileDate() {

		Calendar calendar = Calendar.getInstance();

		String month = (calendar.get(Calendar.MONTH) + 1) < 10 ? "0"
				+ (calendar.get(Calendar.MONTH) + 1) : ""
				+ (calendar.get(Calendar.MONTH) + 1);
		String day = (calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0"
				+ calendar.get(Calendar.DAY_OF_MONTH) : ""
				+ calendar.get(Calendar.DAY_OF_MONTH);
		String hour = calendar.get(Calendar.HOUR_OF_DAY) < 10 ? "0"
				+ calendar.get(Calendar.HOUR_OF_DAY) : ""
				+ calendar.get(Calendar.HOUR_OF_DAY);
		String minute = calendar.get(Calendar.MINUTE) < 10 ? "0"
				+ calendar.get(Calendar.MINUTE) : ""
				+ calendar.get(Calendar.MINUTE);
		String second = calendar.get(Calendar.SECOND) < 10 ? "0"
				+ calendar.get(Calendar.SECOND) : ""
				+ calendar.get(Calendar.SECOND);

		return "" + calendar.get(Calendar.YEAR) + month + day + "_" + hour
				+ minute + second;
	}

	/**
	 * Inform the current file name
	 * 
	 * @return the absolute file name
	 */
	@SuppressWarnings("nls")
	protected String getFileName() {

		return this.outputDirectory + this.outputFileNamePrefix + "_"
				+ this.fileDate + "_" + this.outputFileNameSufix + ".txt";
	}

	/**
	 * Write the message to a file.
	 * 
	 * @param message
	 *            the message
	 * @param appendNewLine
	 *            true to append a new line and false otherwise
	 */
	protected synchronized void writeMessage(String message,
			boolean appendNewLine) {

		if (this.outputFile == null) {

			this.outputFile = new File(getFileName());

			if (!this.outputFile.exists()) {

				try {
					this.outputFile.createNewFile();
				} catch (IOException exception) {
					ExceptionSupport.handleException(exception);
					return;
				}
			}
		}

		FileOutputStream fileoutputstream;
		try {
			fileoutputstream = new FileOutputStream(this.outputFile, true);
		} catch (FileNotFoundException exception) {
			ExceptionSupport.handleException(exception);
			return;
		}

		byte outputBytes[] = message.getBytes();

		byte newLineBytes[] = CommonConstants.NEW_LINE.getBytes();

		try {

			fileoutputstream.write(outputBytes);

			if (appendNewLine) {
				fileoutputstream.write(newLineBytes);
			}

			fileoutputstream.flush();

			fileoutputstream.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
		}
	}

	/**
	 * Print a message
	 * 
	 * @param message
	 *            the message
	 */
	protected void print(String message) {

		writeMessage(message, false);
	}

	/**
	 * Print a message
	 * 
	 * @param message
	 *            the message
	 */
	protected void print(StringBuffer message) {

		writeMessage(message.toString(), false);
	}

	/**
	 * Print a message followed by a new line
	 * 
	 * @param message
	 *            the message
	 */
	protected void println(String message) {

		writeMessage(message, true);
	}

	/**
	 * Print a message followed by a new line
	 * 
	 * @param message
	 *            the message
	 */
	protected void println(StringBuffer message) {

		writeMessage(message.toString(), true);
	}

	/**
	 * Indicate the directory to create the output file. The default is C:/
	 * 
	 * @param outputDirectory
	 *            The target directory
	 */
	protected synchronized void setOutputDirectory(String outputDirectory) {

		this.outputFile = null;
		this.fileDate = setFileDate();

		this.outputDirectory = outputDirectory;
	}

	/**
	 * Indicate the output file suffix.
	 * 
	 * @param outputFileNameSufix
	 *            The target file suffix.
	 */
	protected synchronized void setOutputFileNameSufix(
			String outputFileNameSufix) {

		this.outputFile = null;
		this.fileDate = setFileDate();

		this.outputFileNameSufix = outputFileNameSufix;
	}
}
