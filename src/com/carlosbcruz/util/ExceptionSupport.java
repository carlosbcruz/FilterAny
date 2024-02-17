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

import java.awt.Component;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Support the handling of simple exceptions.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ExceptionSupport {

	// The default is to use swing messages.
	private static boolean useSwingMessages = true;

	/**
	 * Write a date stamp on the output
	 */
	private static void writeDate() {

		Err.print(DateFormat.getDateInstance().format(new Date()) + " - " //$NON-NLS-1$
				+ DateFormat.getTimeInstance().format(new Date()) + " [ERROR] "); //$NON-NLS-1$
	}

	/**
	 * Show a message dialog and generate the entrance in the execution error
	 * message.
	 * 
	 * @param mainComponent
	 *            The main component. If null than the dialog is screen
	 *            centered.
	 * @param message
	 *            The exception message.
	 */
	public static void handleException(Component mainComponent, String message) {

		writeDate();
		Err.println(message);

		if (useSwingMessages) {
			Component relativeComponent = (mainComponent == null) ? relativeComponent = SwingUtil
					.retrieveCenteredTemporaryFrame() : mainComponent;
			JOptionPane.showMessageDialog(relativeComponent, message,
					InternalResource.get(InternalResource.EXCEPTION_OCCURRED),
					JOptionPane.ERROR_MESSAGE);

			if (relativeComponent != mainComponent) {
				((JFrame) relativeComponent).dispose();
			}
		}
	}

	/**
	 * Show a message dialog and generate the entrace in the execution error
	 * message.
	 * 
	 * @param message
	 *            The exception message.
	 */
	public static void handleException(String message) {

		handleException(null, message);
	}

	/**
	 * Show a message dialog and generate the entrance in the execution error
	 * file.
	 * 
	 * @param exception
	 *            The exception.
	 */
	public static void handleException(Throwable exception) {

		writeDate();

		String message = throwableToString(exception);
		Err.println(message);

		if (useSwingMessages) {
			JFrame tempFrame = SwingUtil.retrieveCenteredTemporaryFrame();
			JOptionPane.showMessageDialog(tempFrame, message,
					InternalResource.get(InternalResource.EXCEPTION_OCCURRED),
					JOptionPane.ERROR_MESSAGE);
			tempFrame.dispose();
		}
	}

	/**
	 * Show a message dialog and generate the entrance in the execution error
	 * file.
	 * 
	 * @param exception
	 *            The exception.
	 * @param auxiliarMessage
	 *            An auxiliar message.
	 * @param internalMessage
	 *            An internal message.
	 */
	public static void handleException(Throwable exception,
			String auxiliarMessage, String internalMessage) {

		writeDate();

		String message = auxiliarMessage + "\n" + internalMessage + "\n\n"; //$NON-NLS-1$ //$NON-NLS-2$
		Err.println(message + throwableToString(exception));

		message += exception.getClass().getName() + " " //$NON-NLS-1$
				+ exception.getMessage();

		if (useSwingMessages) {
			JFrame tempFrame = SwingUtil.retrieveCenteredTemporaryFrame();
			JOptionPane.showMessageDialog(tempFrame, message,
					InternalResource.get(InternalResource.EXCEPTION_OCCURRED),
					JOptionPane.ERROR_MESSAGE);
			tempFrame.dispose();
		}
	}

	/**
	 * Transform an exception into a string.
	 * 
	 * @param throwable
	 *            The exception
	 * @return The exception in string format.
	 */
	public static String throwableToString(Throwable throwable) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		throwable.printStackTrace(printStream);

		return outputStream.toString();
	}

	/**
	 * Turn the swing alert windows on of off
	 * 
	 * @param useSwingMessages
	 *            the useSwingMessages to set
	 */
	public static void setUseSwingMessages(boolean useSwingMessages) {

		ExceptionSupport.useSwingMessages = useSwingMessages;
	}

	/**
	 * Pring the stack trace into a String.
	 * 
	 * @param throwable
	 *            The error.
	 * @return The text containing the stack trace.
	 */
	public static String stackTrace2String(Throwable throwable) {

		try {

			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);

			throwable.printStackTrace(printWriter);

			return stringWriter.toString();
		} catch (Exception exception) {
			return InternalResource
					.get(InternalResource.ERROR_PRINTINTG_STACKTRACE);
		}
	}
}
