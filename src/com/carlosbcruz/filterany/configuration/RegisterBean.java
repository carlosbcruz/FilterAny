package com.carlosbcruz.filterany.configuration;

import java.io.Serializable;

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

/**
 * Repository of register
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class RegisterBean implements Serializable {

	/*
	 * String name The person who registered the FilterAny. String address The
	 * person address. String region The city, state, zip and country. String
	 * email The person's email. String phone The phone number. String timestamp
	 * The date the user signed the agreement.
	 */

	private static final long serialVersionUID = 1L;

	// The person who registered the FilterAny.
	private String name = new String();
	// The person address.
	private String address = new String();
	// The city, state, zip and country.
	private String region = new String();
	// The person's email.
	private String email = new String();
	// The phone number.
	private String phone = new String();
	// The computer identification.
	private String computer = new String();
	// The date the user signed the agreement.
	private String timestamp = new String();

	/**
	 * Default constructor.
	 */
	public RegisterBean() {

		super();
	}

	/**
	 * @param String
	 *            nameParameter The person who registered the FilterAny.
	 * @param String
	 *            addressParameter The person address.
	 * @param String
	 *            regionParameter The city, state, zip and country.
	 * @param String
	 *            emailParameter The person's email.
	 * @param String
	 *            phoneParameter The phone number.
	 * @param String
	 *            computerParameter The computer identification.
	 * @param String
	 *            timestampParameter The date the user signed the agreement.
	 */
	public RegisterBean(String nameParameter, String addressParameter,
			String regionParameter, String emailParameter,
			String phoneParameter, String computerParameter,
			String timestampParameter) {

		super();

		this.name = nameParameter;
		this.address = addressParameter;
		this.region = regionParameter;
		this.email = emailParameter;
		this.phone = phoneParameter;
		this.computer = computerParameter;
		this.timestamp = timestampParameter;
	}

	/**
	 * Provide: The person who registered the FilterAny.
	 * 
	 * @return name The person who registered the FilterAny.
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * Set: The person who registered the FilterAny.
	 * 
	 * @param name
	 *            The person who registered the FilterAny.
	 */
	public void setName(String nameParameter) {

		this.name = nameParameter;
	}

	/**
	 * Provide: The person address.
	 * 
	 * @return address The person address.
	 */
	public String getAddress() {

		return this.address;
	}

	/**
	 * Set: The person address.
	 * 
	 * @param address
	 *            The person address.
	 */
	public void setAddress(String addressParameter) {

		this.address = addressParameter;
	}

	/**
	 * Provide: The city, state, zip and country.
	 * 
	 * @return region The city, state, zip and country.
	 */
	public String getRegion() {

		return this.region;
	}

	/**
	 * Set: The city, state, zip and country.
	 * 
	 * @param region
	 *            The city, state, zip and country.
	 */
	public void setRegion(String regionParameter) {

		this.region = regionParameter;
	}

	/**
	 * Provide: The person's email.
	 * 
	 * @return email The person's email.
	 */
	public String getEmail() {

		return this.email;
	}

	/**
	 * Set: The person's email.
	 * 
	 * @param email
	 *            The person's email.
	 */
	public void setEmail(String emailParameter) {

		this.email = emailParameter;
	}

	/**
	 * Provide: The phone number.
	 * 
	 * @return phone The phone number.
	 */
	public String getPhone() {

		return this.phone;
	}

	/**
	 * Set: The phone number.
	 * 
	 * @param phone
	 *            The phone number.
	 */
	public void setPhone(String phoneParameter) {

		this.phone = phoneParameter;
	}

	/**
	 * Provide: The computer identification.
	 * 
	 * @return computer The computer identification.
	 */
	public String getComputer() {

		return this.computer;
	}

	/**
	 * Set: The computer identification.
	 * 
	 * @param computer
	 *            The computer identification.
	 */
	public void setComputer(String computerParameter) {

		this.computer = computerParameter;
	}

	/**
	 * Provide: The date the user signed the agreement.
	 * 
	 * @return timestamp The date the user signed the agreement.
	 */
	public String getTimestamp() {

		return this.timestamp;
	}

	/**
	 * Set: The date the user signed the agreement.
	 * 
	 * @param timestamp
	 *            The date the user signed the agreement.
	 */
	public void setTimestamp(String timestampParameter) {

		this.timestamp = timestampParameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@SuppressWarnings("nls")
	@Override
	public String toString() {

		StringBuffer out = new StringBuffer();

		out.append("RegisterBean [\n");

		out.append("\tname=" + this.name + ",\n");
		out.append("\taddress=" + this.address + ",\n");
		out.append("\tregion=" + this.region + ",\n");
		out.append("\temail=" + this.email + ",\n");
		out.append("\tphone=" + this.phone + ",\n");
		out.append("\tcomputer=" + this.computer + ",\n");
		out.append("\ttimestamp=" + this.timestamp + "]\n");

		return out.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

}
