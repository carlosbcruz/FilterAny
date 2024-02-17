package com.carlosbcruz.concurrence;

/**
 * Controls the expiration in seconds.
 */
public class Time {

	private final long currentTime = System.currentTimeMillis();
	private long expireTime;

	/**
	 * Create a timer that expires in a certain number of seconds.
	 * 
	 * @param seconds
	 *            The number of seconds.
	 */
	public Time(int seconds) {

		this.expireTime = this.currentTime + seconds * 1000;
	}

	/**
	 * Indicate if the time has expired.
	 * 
	 * @return True if it has expired.
	 */
	public boolean isExpired() {

		return System.currentTimeMillis() > this.expireTime;
	}
}
