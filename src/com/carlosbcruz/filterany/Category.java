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

/**
 * Store the categories and the filtersWindow that belong to this category.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class Category {

	private String categoryName;
	private String categoryTooltip;
	private String iconName;
	private Filter filters[];

	/**
	 * Construct a category object.
	 * 
	 * @param categoryName
	 *            The category name.
	 * @param categoryTooltip
	 *            The category tool tip
	 * @param iconName
	 *            The icon name
	 * @param filtersWindow
	 *            The filtersWindow that belong to this category
	 */
	public Category(String categoryName, String categoryTooltip,
			String iconName, Filter[] filters) {

		super();

		this.categoryName = categoryName;
		this.categoryTooltip = categoryTooltip;
		this.iconName = iconName;
		this.filters = filters;
	}

	/**
	 * Inform the category name.
	 * 
	 * @return the categoryName The category name.
	 */
	public String getCategoryName() {

		return this.categoryName;
	}

	/**
	 * Inform the category tool tip.
	 * 
	 * @return the categoryTooltip The category tool tip.
	 */
	public String getCategoryTooltip() {

		return this.categoryTooltip;
	}

	/**
	 * Inform the icon file name.
	 * 
	 * @return the iconName The icon file name.
	 */
	public String getIconName() {

		return this.iconName;
	}

	/**
	 * Inform the filtersWindow for this category.
	 * 
	 * @return the filtersWindow The filtersWindow for this category.
	 */
	public Filter[] getFilters() {

		return this.filters;
	}
}
