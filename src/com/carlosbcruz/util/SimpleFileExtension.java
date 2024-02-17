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

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Provides a simple filter for file extensions.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class SimpleFileExtension extends FileFilter {

	public static final String SERIALIZABLE_FILE_EXTENSION = ".ser"; //$NON-NLS-1$

	// the file extension.
	private String extension;
	private String extensionDescription;

	/**
	 * Simple constructor.
	 * 
	 * @param extension
	 *            The file extension.
	 * @param extensionDescriptionKey
	 *            The file extension description.
	 */
	public SimpleFileExtension(String extension, String extensionDescription) {

		this.extension = extension;
		this.extensionDescription = extensionDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File f) {

		return (f.getAbsolutePath().indexOf(this.extension) > 0 || f
				.isDirectory());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {

		return this.extensionDescription;
	}

	/**
	 * Inform the file extension.
	 * 
	 * @return the extension The file extension.
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * Set the file extension.
	 * 
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * 
	 * @return the extensionDescription
	 */
	public String getExtensionDescription() {
		return this.extensionDescription;
	}

	/**
	 * @param extensionDescription
	 *            the extensionDescription to set
	 */
	public void setExtensionDescription(String extensionDescription) {
		this.extensionDescription = extensionDescription;
	}

}
