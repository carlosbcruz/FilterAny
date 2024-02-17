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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Utility class to help retrieving application properties
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class SimpleProperties {

	private static Properties propertiesInstance = new Properties();

	private static boolean propertiesLoaded = false;

	private static String propertiesFileName = "Default.properties"; //$NON-NLS-1$

	private static String propertiesFileHeader = "SimpleProperties"; //$NON-NLS-1$

	private static boolean persistProperties = true;

	/**
	 * Load the default properties
	 */
	private static void loadProperties() {

		File inputPropertiesFile = new File(propertiesFileName);
		try (FileInputStream inputStream = new FileInputStream(
				inputPropertiesFile)) {

			propertiesInstance.load(inputStream);

		} catch (IOException ioException) {
			// Create the file.
			saveProperties();
		}
	}

	/**
	 * Clear the properties file
	 */
	public static void nukeProperties() {

		propertiesInstance = new Properties();

		saveProperties();
	}

	/**
	 * Get the property to a key. A default value is returned if not found
	 * 
	 * @param propertyKey
	 *            The parameter key
	 * @return the value
	 */
	private static String getStringProperty(String propertyKey) {

		String property = propertiesInstance.getProperty(propertyKey);

		return property;
	}

	/**
	 * Retrieve the property. If the property do not exist return the default
	 * 
	 * @param propertyKey
	 *            The parameter key
	 * @param defaultValue
	 *            The default value
	 * @return the property
	 */
	public static String getStringPropertyWithDefault(String propertyKey,
			String defaultValue) {

		// Load properties if not read.
		if (!propertiesLoaded) {

			loadProperties();
			propertiesLoaded = true;
		}

		String property = getStringProperty(propertyKey);

		if (property == null) {
			setStringProperty(propertyKey, defaultValue);
			return defaultValue;
		}

		return property;
	}

	/**
	 * Confirm the current property value from content. Allow the user to browse
	 * a file if necessary.
	 * 
	 * @param propertyKey
	 *            The content key.
	 * @param defaultValue
	 *            The default value
	 * @return the file choosen.
	 */
	public static String confirmFileNamePropertyWithDefault(String propertyKey,
			String defaultValue, String propertyTitle) {

		// Identify the current value.
		String propertyValue = getStringProperty(propertyKey);
		if (propertyValue == null)
			propertyValue = defaultValue;

		JFrame tempFrame = SwingUtil.retrieveCenteredTemporaryFrame();

		// Confirm the current value.
		Object[] options = {
				InternalResource.get(InternalResource.FILE_BROWSE),
				InternalResource.get(InternalResource.CONTINUE) };
		int n = JOptionPane.showOptionDialog(
				tempFrame,
				InternalResource.get(InternalResource.FILE_SELECTION_PROPERTY)
						+ " \"" //$NON-NLS-1$
						+ propertyTitle
						+ "\"\n" //$NON-NLS-1$
						+ InternalResource
								.get(InternalResource.FILE_CONTINUE_SELECTION)
						+ " \"" //$NON-NLS-1$
						+ propertyValue + "\"", InternalResource //$NON-NLS-1$
						.get(InternalResource.FILE_SELECTION),
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, InternalResource.get(InternalResource.CONTINUE));
		tempFrame.dispose();

		// Current value confirmed.
		if (n == JOptionPane.NO_OPTION) {
			setStringProperty(propertyKey, propertyValue);
			return propertyValue;
		}

		// Allow the user to specify an output file.
		JFileChooser fileChooser = null;
		File file = null;
		if (n == JOptionPane.YES_OPTION) {

			// Allow the user to specify an output file.
			fileChooser = new JFileChooser();

			int fileChooserLocationX = SwingUtil.getCenterOfScreen().x
					- (fileChooser.getWidth() / 2);
			int fileChooserLocationY = SwingUtil.getCenterOfScreen().y
					- (fileChooser.getHeight() / 2);

			fileChooser.setLocation(fileChooserLocationX, fileChooserLocationY);

			if (propertyValue != null)
				fileChooser.setCurrentDirectory(new File(propertyValue));

			fileChooser.showDialog(null,
					InternalResource.get(InternalResource.OPEN_FILE));

			file = fileChooser.getSelectedFile();

		}

		// If the file dialog has been closed
		if (file == null)
			return null;

		String fileName = fileChooser.getSelectedFile().getAbsolutePath();
		setStringProperty(propertyKey, fileName);
		return fileName;
	}

	/**
	 * Confirm the current property value from content. Allow the user to browse
	 * a directory if necessary.
	 * 
	 * @param propertyKey
	 *            The content key.
	 * @param defaultValue
	 *            The default value
	 * @return the file chosen.
	 */
	public static String confirmDirectoryNamePropertyWithDefault(
			String propertyKey, String defaultValue, String propertyTitle) {

		// Identify the current value.
		String propertyValue = getStringProperty(propertyKey);
		if (propertyValue == null)
			propertyValue = defaultValue;

		JFrame tempFrame = SwingUtil.retrieveCenteredTemporaryFrame();

		// Confirm the current value.
		Object[] options = {
				InternalResource.get(InternalResource.FILE_BROWSE),
				InternalResource.get(InternalResource.CONTINUE) };
		int n = JOptionPane
				.showOptionDialog(
						tempFrame,
						InternalResource
								.get(InternalResource.DIRECTORY_SELECTION_PROPERTY)
								+ " \"" //$NON-NLS-1$
								+ propertyTitle
								+ "\"\n" //$NON-NLS-1$
								+ InternalResource
										.get(InternalResource.DIRECTORY_CONTINUE_SELECTION)
								+ " \"" + propertyValue + "\"", InternalResource //$NON-NLS-1$ //$NON-NLS-2$
								.get(InternalResource.DIRECTORY_SELECTION),
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options,
						InternalResource.get(InternalResource.CONTINUE));
		tempFrame.dispose();

		// Current value confirmed.
		if (n == JOptionPane.NO_OPTION) {
			setStringProperty(propertyKey, propertyValue);
			return propertyValue;
		}

		// Allow the user to specify an output file.
		JFileChooser directoryChooser = null;
		File file = null;
		if (n == JOptionPane.YES_OPTION) {

			// Allow the user to specify an output file.
			directoryChooser = new JFileChooser();

			directoryChooser
					.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int fileChooserLocationX = SwingUtil.getCenterOfScreen().x
					- (directoryChooser.getWidth() / 2);
			int fileChooserLocationY = SwingUtil.getCenterOfScreen().y
					- (directoryChooser.getHeight() / 2);

			directoryChooser.setLocation(fileChooserLocationX,
					fileChooserLocationY);

			if (propertyValue != null)
				directoryChooser.setCurrentDirectory(new File(propertyValue));

			directoryChooser.showDialog(null,
					InternalResource.get(InternalResource.OPEN_FILE));

			file = directoryChooser.getSelectedFile();

		}

		// If the file dialog has been closed
		if (file == null)
			return null;

		String directoryName = directoryChooser.getSelectedFile()
				.getAbsolutePath();
		setStringProperty(propertyKey, directoryName);
		return directoryName;
	}

	/**
	 * Remove a property
	 * 
	 * @param propertyKey
	 */
	public static void removeProperty(String propertyKey) {

		propertiesInstance.remove(propertyKey);

		saveProperties();
	}

	/**
	 * Set an Account property
	 * 
	 * @param propertyKey
	 *            the Key
	 * @param propertyValue
	 *            the Value
	 */
	public static void setStringProperty(String propertyKey,
			String propertyValue) {

		propertiesInstance.setProperty(propertyKey, propertyValue);

		saveProperties();
	}

	/**
	 * Save the properties in the application file
	 */
	private static void saveProperties() {

		// Do not save properties if it is configure to not touch the file.
		if (!persistProperties) {
			return;
		}

		File outputPropertiesFile = new File(propertiesFileName);

		try (FileOutputStream outputStream = new FileOutputStream(
				outputPropertiesFile)) {

			outputPropertiesFile.createNewFile();
			propertiesInstance.store(outputStream, propertiesFileHeader);

		} catch (IOException ioException) {
			ExceptionSupport.handleException(ioException);
		}
	}

	/**
	 * Set the property file to be used.
	 * 
	 * @param propertiesFileName
	 *            the propertiesFileName to set
	 */
	public static void setPropertiesFileName(String propertiesFileName) {
		SimpleProperties.propertiesFileName = propertiesFileName;
	}

	/**
	 * Set the property file header.
	 * 
	 * @param propertiesFileHeader
	 *            the propertiesFileHeader to set
	 */
	public static void setPropertiesFileHeader(String propertiesFileHeader) {
		SimpleProperties.propertiesFileHeader = propertiesFileHeader;
	}

	/**
	 * Configure if the properties are persisted on file or not.
	 * 
	 * @param persistProperties
	 *            the persistProperties to set If the properties are persisted
	 *            on file or not.
	 */
	public static void setPersistProperties(boolean persistProperties) {
		SimpleProperties.persistProperties = persistProperties;
	}

}
