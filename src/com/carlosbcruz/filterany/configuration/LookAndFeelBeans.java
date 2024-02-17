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

import com.carlosbcruz.filterany.FilterAnyTheme;

/**
 * Store the available Look and Feels.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class LookAndFeelBeans implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name = new String(); // The look and feel name.
	private String className = new String(); // The class name.
	private FilterAnyTheme theme = null; // The class that implements the theme.
	private boolean decorated = false; // If the look and feel is decorated or
	// not.
	private String previewEnglish = new String(); // The image with the look and
	// feel preview in English
	private String previewPortuguese = new String(); // The image with the look

	// and feel preview in
	// Portuguese

	/**
	 * Default constructor.
	 */
	public LookAndFeelBeans() {

		super();
	}

	/**
	 * @param String
	 *            nameParameter The look and feel name.
	 * @param String
	 *            classNameParameter The class name.
	 * @param FilterAnyTheme
	 *            themeParameter The class that implements the theme.
	 * @param boolean decoratedParameter If the look and feel is decorated or
	 *        not.
	 * @param String
	 *            previewEnglishParameter The image with the look and feel
	 *            preview in English
	 * @param String
	 *            previewPortugueseParameter The image with the look and feel
	 *            preview in Portuguese
	 */
	public LookAndFeelBeans(String nameParameter, String classNameParameter,
			FilterAnyTheme themeParameter, boolean decoratedParameter,
			String previewEnglishParameter, String previewPortugueseParameter) {

		super();

		this.name = nameParameter;
		this.className = classNameParameter;
		this.theme = themeParameter;
		this.decorated = decoratedParameter;
		this.previewEnglish = previewEnglishParameter;
		this.previewPortuguese = previewPortugueseParameter;
	}

	/**
	 * Provide: The look and feel name.
	 * 
	 * @return name The look and feel name.
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * Set: The look and feel name.
	 * 
	 * @param name
	 *            The look and feel name.
	 */
	public void setName(String nameParameter) {

		this.name = nameParameter;
	}

	/**
	 * Provide: The class name.
	 * 
	 * @return className The class name.
	 */
	public String getClassName() {

		return this.className;
	}

	/**
	 * Set: The class name.
	 * 
	 * @param className
	 *            The class name.
	 */
	public void setClassName(String classNameParameter) {

		this.className = classNameParameter;
	}

	/**
	 * Provide: The class that implements the theme.
	 * 
	 * @return theme The class that implements the theme.
	 */
	public FilterAnyTheme getTheme() {

		return this.theme;
	}

	/**
	 * Set: The class that implements the theme.
	 * 
	 * @param theme
	 *            The class that implements the theme.
	 */
	public void setTheme(FilterAnyTheme themeParameter) {

		this.theme = themeParameter;
	}

	/**
	 * Provide: If the look and feel is decorated or not.
	 * 
	 * @return decorated If the look and feel is decorated or not.
	 */
	public boolean isDecorated() {

		return this.decorated;
	}

	/**
	 * Set: If the look and feel is decorated or not.
	 * 
	 * @param decorated
	 *            If the look and feel is decorated or not.
	 */
	public void setDecorated(boolean decoratedParameter) {

		this.decorated = decoratedParameter;
	}

	/**
	 * Provide: The image with the look and feel preview in English
	 * 
	 * @return previewEnglish The image with the look and feel preview in
	 *         English
	 */
	public String getPreviewEnglish() {

		return this.previewEnglish;
	}

	/**
	 * Set: The image with the look and feel preview in English
	 * 
	 * @param previewEnglish
	 *            The image with the look and feel preview in English
	 */
	public void setPreviewEnglish(String previewEnglishParameter) {

		this.previewEnglish = previewEnglishParameter;
	}

	/**
	 * Provide: The image with the look and feel preview in Portuguese
	 * 
	 * @return previewPortuguese The image with the look and feel preview in
	 *         Portuguese
	 */
	public String getPreviewPortuguese() {

		return this.previewPortuguese;
	}

	/**
	 * Set: The image with the look and feel preview in Portuguese
	 * 
	 * @param previewPortuguese
	 *            The image with the look and feel preview in Portuguese
	 */
	public void setPreviewPortuguese(String previewPortugueseParameter) {

		this.previewPortuguese = previewPortugueseParameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

}
