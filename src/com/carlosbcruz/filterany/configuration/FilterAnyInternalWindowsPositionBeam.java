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

package com.carlosbcruz.filterany.configuration;

import java.io.Serializable;

import com.carlosbcruz.util.InternalWindowPositionBean;

/**
 * Store a position of the internal windows.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnyInternalWindowsPositionBeam implements Serializable {

	private static final long serialVersionUID = 1L;

	InternalWindowPositionBean filtersWindow = new InternalWindowPositionBean();
	InternalWindowPositionBean topWindow = new InternalWindowPositionBean();
	InternalWindowPositionBean auxiliarWindow = new InternalWindowPositionBean();
	InternalWindowPositionBean bottomWindow = new InternalWindowPositionBean();
	InternalWindowPositionBean controlWindow = new InternalWindowPositionBean();
	InternalWindowPositionBean helpWindow = new InternalWindowPositionBean();

	/**
	 * Provide: Filters Window.
	 * 
	 * @return filtersWindow Filters Window.
	 */
	public InternalWindowPositionBean getFiltersWindow() {
		return this.filtersWindow;
	}

	/**
	 * Store: Filters Window.
	 * 
	 * @param filtersWindow
	 *            Filters Window.
	 */
	void setFiltersWindow(InternalWindowPositionBean filtersWindow) {
		this.filtersWindow = filtersWindow;
	}

	/**
	 * Provide: Top input window.
	 * 
	 * @return topWindow Top input window.
	 */
	public InternalWindowPositionBean getTopWindow() {
		return this.topWindow;
	}

	/**
	 * Store: Top input window.
	 * 
	 * @param topWindow
	 *            Top input window.
	 */
	void setTopWindow(InternalWindowPositionBean topWindow) {
		this.topWindow = topWindow;
	}

	/**
	 * Provide: Bottom output window.
	 * 
	 * @return bottomWindow Bottom output window.
	 */
	public InternalWindowPositionBean getBottomWindow() {
		return this.bottomWindow;
	}

	/**
	 * Provide: Auxiliar Input window.
	 * 
	 * @return the auxiliarWindow
	 */
	public InternalWindowPositionBean getAuxiliarWindow() {
		return this.auxiliarWindow;
	}

	/**
	 * Store: Auxiliar Input window.
	 * 
	 * @param auxiliarWindow
	 *            the auxiliarWindow to set
	 */
	public void setAuxiliarWindow(InternalWindowPositionBean auxiliarWindow) {
		this.auxiliarWindow = auxiliarWindow;
	}

	/**
	 * Store: Bottom output window.
	 * 
	 * @param bottomWindow
	 *            Bottom output window.
	 */
	void setBottomWindow(InternalWindowPositionBean bottomWindow) {
		this.bottomWindow = bottomWindow;
	}

	/**
	 * Provide: Control window.
	 * 
	 * @return controlWindow Control window.
	 */
	public InternalWindowPositionBean getControlWindow() {
		return this.controlWindow;
	}

	/**
	 * Store: Control window.
	 * 
	 * @param controlWindow
	 *            Control window.
	 */
	void setControlWindow(InternalWindowPositionBean controlWindow) {
		this.controlWindow = controlWindow;
	}

	/**
	 * Provide: Help window.
	 * 
	 * @return the helpWindow
	 */
	public InternalWindowPositionBean getHelpWindow() {

		return this.helpWindow;
	}

	/**
	 * Store: Help window.
	 * 
	 * @param helpWindow
	 *            the helpWindow to set
	 */
	void setHelpWindow(InternalWindowPositionBean helpWindow) {

		this.helpWindow = helpWindow;
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

		out.append("FilterAnyInternalWindowsPositionBeam [\n");

		out.append("\tfiltersWindow=" + this.filtersWindow.toString() + ",\n");
		out.append("\ttopWindow=" + this.topWindow.toString() + ",\n");
		out.append("\tauxiliarWindow=" + this.auxiliarWindow.toString() + ",\n");
		out.append("\tbottomWindow=" + this.bottomWindow.toString() + ",\n");
		out.append("\tcontrolWindow=" + this.controlWindow.toString() + "\n");
		out.append("\thelpWindow=" + this.helpWindow.toString() + "]\n");

		return out.toString();
	}

}
