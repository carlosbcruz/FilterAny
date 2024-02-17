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

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Identifies a category.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CategoryNode implements Serializable {

	private static final long serialVersionUID = 1L;

	private String categoryName = new String();
	private String categoryTooltip = new String();
	private ImageIcon categoryIcon = null;

	/**
	 * Default constructor.
	 * 
	 * @param categoryName
	 *            The category name.
	 * @param categoryTooltip
	 *            The category tooltip.
	 * @param categoryIcon
	 *            The category icon.
	 */
	protected CategoryNode(String categoryName, String categoryTooltip,
			ImageIcon categoryIcon) {

		super();

		this.categoryName = categoryName;
		this.categoryTooltip = categoryTooltip;
		this.categoryIcon = categoryIcon;
	}

	/**
	 * Inform the category name.
	 * 
	 * @return the categoryName The category name.
	 */
	protected String getCategoryName() {

		return this.categoryName;
	}

	/**
	 * Inform the category tooltip.
	 * 
	 * @return the categoryTooltip The category tooltip.
	 */
	protected String getCategoryTooltip() {

		return this.categoryTooltip;
	}

	/**
	 * Provide the category icon.
	 * 
	 * @return the categoryIcon The category icon.
	 */
	protected ImageIcon getCategoryIcon() {

		return this.categoryIcon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.categoryName;
	}
}
