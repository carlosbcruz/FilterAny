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

import java.io.IOException;
import java.io.InputStream;

/**
 * Simple InputStream subclass to fetch <b>all</b> bytes from a String.
 */
public class ByteInputStream extends InputStream {

	private byte[] byteArray;
	private int offset = 0;

	private int available;

	/**
	 * Creates a input stream buffer from a String.
	 * 
	 * @param s
	 *            The String.
	 */
	public ByteInputStream(byte[] byteArray) {

		this.byteArray = byteArray;
		this.available = byteArray.length;
	}

	/**
	 * Read the content of the stream.
	 * 
	 * @return An integer.
	 * @throws IOException
	 *             If there is a problem.
	 */
	@Override
	public int read() throws IOException {

		if (this.available == 0) {
			return -1;
		}

		int content = this.byteArray[this.offset];

		if (content < 0) {
			content += 256;
		}

		this.available--;
		this.offset++;

		return content;
	}

	/**
	 * Indicate if there is more to read.
	 * 
	 * @return How much bytes left.
	 * @throws IOException
	 *             If there is a problem.
	 */
	@Override
	public int available() throws IOException {

		return this.available;
	}
}