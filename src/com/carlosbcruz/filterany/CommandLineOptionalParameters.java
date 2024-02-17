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
 * Store the character start end end.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CommandLineOptionalParameters {

	// The character start and end.
	private int characerStart = 0;
	private int characerEnd = 0;

	// Indicate the the parameters were set.
	private boolean parametersSet = false;

	// Indicate that the parameters were provided with errors.
	private boolean parametersWithErrors = false;

	/**
	 * Set the character start and end.
	 * 
	 * @param characerStart
	 *            The start.
	 * @param characerEnd
	 *            The end.
	 */
	public CommandLineOptionalParameters(int characerStart, int characerEnd) {

		super();

		this.characerStart = characerStart;
		this.characerEnd = characerEnd;
		this.parametersSet = true;
	}

	/**
	 * @param parametersWithErrors
	 *            If there is an error.
	 */
	public CommandLineOptionalParameters(boolean parametersWithErrors) {

		super();

		this.parametersWithErrors = parametersWithErrors;
	}

	/**
	 * Provide the character start.
	 * 
	 * @return the characerStart
	 */
	protected int getCharacerStart() {
		return this.characerStart;
	}

	/**
	 * Set the character start.
	 * 
	 * @param characerStart
	 *            the characerStart to set
	 */
	protected void setCharacerStart(int characerStart) {
		this.characerStart = characerStart;
	}

	/**
	 * Provide the character end.
	 * 
	 * @return the characerEnd
	 */
	protected int getCharacerEnd() {
		return this.characerEnd;
	}

	/**
	 * Set the character end.
	 * 
	 * @param characerEnd
	 *            the characerEnd to set
	 */
	protected void setCharacerEnd(int characerEnd) {
		this.characerEnd = characerEnd;
	}

	/**
	 * Indicate if the parameters were set.
	 * 
	 * @return the parametersSet
	 */
	protected boolean isParametersSet() {
		return this.parametersSet;
	}

	/**
	 * Set if the parameters were set.
	 * 
	 * @param parametersSet
	 *            the parametersSet to set
	 */
	protected void setParametersSet(boolean parametersSet) {
		this.parametersSet = parametersSet;
	}

	/**
	 * Indicate if the parameters have errors.
	 * 
	 * @return the parametersWithErrors
	 */
	protected boolean isParametersWithErrors() {
		return this.parametersWithErrors;
	}

	/**
	 * Set if the parameters have errors.
	 * 
	 * @param parametersWithErrors
	 *            the parametersWithErrors to set
	 */
	protected void setParametersWithErrors(boolean parametersWithErrors) {
		this.parametersWithErrors = parametersWithErrors;
	}
}
