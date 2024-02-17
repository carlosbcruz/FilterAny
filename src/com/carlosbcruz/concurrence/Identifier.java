package com.carlosbcruz.concurrence;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provides an unique identification number.
 */
public class Identifier {

	private static final AtomicInteger counter = new AtomicInteger(0);

	/**
	 * Provide an unique identification.
	 * 
	 * @return An unique identification.
	 */
	public static String getIdentification() {

		return String.valueOf(counter.incrementAndGet());
	}

}
