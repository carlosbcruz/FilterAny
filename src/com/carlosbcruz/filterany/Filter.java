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

import javax.swing.JDialog;

/**
 * Interface to define what a filter has to implement.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public interface Filter {

	/**
	 * Indicate the filter name.
	 * 
	 * @return The filter name.
	 */
	public String getFilterName();

	/**
	 * Inform the filter small description.
	 * 
	 * @return The small description.
	 */
	public String getSmallDescription();

	/**
	 * Indicate the filter instructions.
	 * 
	 * @return The filter instructions.
	 */
	public String getFilterInstructions();

	/**
	 * Indicate the filter level.
	 * 
	 * @return The filter level.
	 */
	public FilterLevel getFilterLevel();

	/**
	 * Indicate which controls are necessary.
	 * 
	 * @return The controls indication.
	 */
	public FilterControls[] getControls();

	/**
	 * Indicate which controls are required and with ones are optional.
	 * 
	 * @return The controls required.
	 */
	public FilterParameter[] gerControlsRequired();

	/**
	 * Indicate which type is each parameter.
	 * 
	 * @return The paramter types.
	 */
	public FilterType[] getControlsType();

	/**
	 * Inform the labels for the controls
	 * 
	 * @return The controls labels.
	 */
	public String[] getControlsLabels();

	/**
	 * Inform the controls tool tips.
	 * 
	 * @return The controls tool tips.
	 */
	public String[] getControlsToolTipTexts();

	/**
	 * @param superComponent
	 *            the superComponent to set
	 */
	public void setSuperComponent(JDialog superComponent);

	/**
	 * Apply a filter on the provided text.
	 * 
	 * @param inputText
	 *            The text to be filtered.
	 * @param auxiliarText
	 *            The text to be filtered on specific filters.
	 * @param selectionStart
	 *            Indicates the start of a selection if it exist.
	 * @param selectionEnd
	 *            Indicates the end of a selection if it exist.
	 * @return The text filtered.
	 */
	public StringBuffer filter(StringBuffer inputText,
			StringBuffer auxiliarText, int selectionStart, int selectionEnd)
			throws FilterException;

}
