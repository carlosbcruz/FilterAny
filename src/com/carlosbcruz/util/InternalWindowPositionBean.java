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
 * Store the tridimensional relative position of windows.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class InternalWindowPositionBean extends WindowPositionBean {

	private static final long serialVersionUID = 1L;

	int z = 0;

	/**
	 * Default constructor.
	 */
	public InternalWindowPositionBean() {

		super();
	}

	/**
	 * @param int z.
	 */
	public InternalWindowPositionBean(int z) {

		super();

		this.z = z;
	}

	/**
	 * Provide: The tridimensional relationship
	 * 
	 * @return z The tridimensional relationship
	 */
	public int getZ() {
		return this.z;
	}

	/**
	 * Store: The tridimensional relationship
	 * 
	 * @param z
	 *            The tridimensional relationship
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@SuppressWarnings("nls")
	@Override
	public String toString() {

		super.toString();

		StringBuffer out = new StringBuffer();

		out.append("InternalWindowPositionBean [\n");
		out.append(super.toString());
		out.append("\tz=" + this.z + "]\n");

		return out.toString();
	}

}
