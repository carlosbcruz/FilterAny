package com.carlosbcruz.filterany.filters;

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
 * Indicate if a word has been selected.
 */
public class SelectedElement extends Element {

	private static final long serialVersionUID = 1L;

	// Indicate if the element is selected or not.
	private boolean selectedWord = false;
	// Indicate if the element is visible.
	private boolean visible = true;
	// Indicate if the element should be removed during some moment process.
	private boolean shouldBeRemoved = false;

	/**
	 * Default constructor.
	 */
	public SelectedElement() {

		super();
	}

	/**
	 * @param boolean selectedWordParameter Indicate if the element is selected
	 *        or not.
	 * @param boolean visibleParameter Indicate if the element is visible.
	 * @param boolean shouldBeRemovedParameter Indicate if the element should be
	 *        removed during some moment process.
	 */
	public SelectedElement(boolean selectedWordParameter,
			boolean visibleParameter, boolean shouldBeRemovedParameter) {

		super();

		this.selectedWord = selectedWordParameter;
		this.visible = visibleParameter;
		this.shouldBeRemoved = shouldBeRemovedParameter;
	}

	/**
	 * Provide: Indicate if the element is selected or not.
	 * 
	 * @return selectedWord Indicate if the element is selected or not.
	 */
	public boolean isSelectedWord() {
		return this.selectedWord;
	}

	/**
	 * Set: Indicate if the element is selected or not.
	 * 
	 * @param selectedWord
	 *            Indicate if the element is selected or not.
	 */
	public void setSelectedWord(boolean selectedWordParameter) {
		this.selectedWord = selectedWordParameter;
	}

	/**
	 * Provide: Indicate if the element is visible.
	 * 
	 * @return visible Indicate if the element is visible.
	 */
	public boolean isVisible() {
		return this.visible;
	}

	/**
	 * Set: Indicate if the element is visible.
	 * 
	 * @param visible
	 *            Indicate if the element is visible.
	 */
	public void setVisible(boolean visibleParameter) {
		this.visible = visibleParameter;
	}

	/**
	 * Provide: Indicate if the element should be removed during some movment
	 * process.
	 * 
	 * @return shouldBeRemoved Indicate if the element should be removed during
	 *         some movment process.
	 */
	public boolean isShouldBeRemoved() {
		return this.shouldBeRemoved;
	}

	/**
	 * Set: Indicate if the element should be removed during some movment
	 * process.
	 * 
	 * @param shouldBeRemoved
	 *            Indicate if the element should be removed during some movment
	 *            process.
	 */
	public void setShouldBeRemoved(boolean shouldBeRemovedParameter) {
		this.shouldBeRemoved = shouldBeRemovedParameter;
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

		out.append("SelectedElement [\n");

		out.append(super.toString());
		out.append("\tselectedWord=" + this.selectedWord + ",\n");
		out.append("\tvisible=" + this.visible + ",\n");
		out.append("\tshouldBeRemoved=" + this.shouldBeRemoved + "]\n");

		return out.toString();
	}
}
