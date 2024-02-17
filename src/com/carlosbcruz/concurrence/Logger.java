package com.carlosbcruz.concurrence;

import java.util.ArrayList;

/**
 * Gather logs and print them out.
 */
public class Logger {

	private static final ArrayList<String> log = new ArrayList<>();

	/**
	 * Add a log message.
	 * 
	 * @param message
	 *            The message.
	 */
	public static synchronized void log(String message) {

		log.add(message);
	}

	/**
	 * Print the logs.
	 */
	public static synchronized void printLogs() {

		for (String message : log) {

			System.out.println(message);
		}
	}
}
