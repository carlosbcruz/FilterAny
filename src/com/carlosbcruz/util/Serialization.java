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

import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Provides serialization functionalities.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class Serialization {

	/**
	 * Serialize a specific object into a file
	 * 
	 * @param fileName
	 *            The file name
	 * @param object
	 *            The serializable object
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void serializeObject(String fileName,
			Serializable serializableObject) throws FileNotFoundException,
			IOException {

		File outputPropertiesFile = new File(fileName);

		// Always create a new file
		outputPropertiesFile.createNewFile();

		// Create a stream for writing.
		FileOutputStream fileOutputStream = new FileOutputStream(
				outputPropertiesFile);

		// Next, create an object that can write to that file.
		ObjectOutputStream outStream = new ObjectOutputStream(fileOutputStream);

		// Save each object.
		outStream.writeObject(serializableObject);
	}

	/**
	 * Provide the file name along with the directory.
	 * 
	 * @param directoryName
	 *            the directory name.
	 * @param fileName
	 *            the file name.
	 * @return The file name along with the directory.
	 */
	public static String getFileName(String directoryName, String fileName) {

		File directory = new File(directoryName);

		return directory.getAbsolutePath() + "/" + fileName; //$NON-NLS-1$
	}

	/**
	 * Serialize a specific object into a file
	 * 
	 * @param directory
	 *            The target directory
	 * @param fileName
	 *            The file name
	 * @param object
	 *            The serializable object
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void serializeObject(String directory, String fileName,
			Serializable serializableObject) throws FileNotFoundException,
			IOException {

		serializeObject(getFileName(directory, fileName), serializableObject);

	}

	/**
	 * Read a serializable object from a file
	 * 
	 * @param fileName
	 *            The file name
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserializeObject(String fileName)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		// Create a stream for reading.
		try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
			// Next, create an object that can read from that file.
			try (ObjectInputStream inStream = new ObjectInputStream(
					fileInputStream)) {
				// Retrieve the Serializable object.
				Object object = inStream.readObject();

				return object;
			}
		}
	}

	/**
	 * Read a serializable object from a file
	 * 
	 * @param directory
	 *            The target directory
	 * @param fileName
	 *            The file name
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserializeObject(String directory, String fileName)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		return deserializeObject(getFileName(directory, fileName));
	}

	/**
	 * Serialize a object by selecting a targe file, confirming overwrite and
	 * serializing.
	 * 
	 * @param parent
	 *            the partent frame
	 * @param object
	 *            the object to be serialized
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void serializeObjectAWTWizard(Frame parent,
			Serializable object) throws FileNotFoundException, IOException {

		SimpleFileExtension filter = new SimpleFileExtension(
				SimpleFileExtension.SERIALIZABLE_FILE_EXTENSION,
				InternalResource
						.get(InternalResource.FILE_SERIALIZATION_DESCRIPTION));
		String fileName = FileDialogs.selectOutputFileByAWT(parent, filter);

		if (fileName != null) {
			serializeObject(fileName, object);
		}
	}

	/**
	 * Serialize a object by selecting a targe file, confirming overwrite and
	 * serializing.
	 * 
	 * @param parent
	 *            the parent Component
	 * @param object
	 *            the object to be serialized
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void serializeObjectSwingWizard(Component parent,
			Serializable object) throws FileNotFoundException, IOException {

		SimpleFileExtension filter = new SimpleFileExtension(
				SimpleFileExtension.SERIALIZABLE_FILE_EXTENSION,
				InternalResource
						.get(InternalResource.FILE_SERIALIZATION_DESCRIPTION));
		String fileName = FileDialogs.selectOutputFileBySwing(parent, filter);

		if (fileName != null) {
			serializeObject(fileName, object);
		}
	}

	/**
	 * Deserialize a object by selecting an input file.
	 * 
	 * @param parent
	 *            the parent frame
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Object deserializeObjectAWTWizard(Frame parent)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		SimpleFileExtension filter = new SimpleFileExtension(
				SimpleFileExtension.SERIALIZABLE_FILE_EXTENSION,
				InternalResource
						.get(InternalResource.FILE_SERIALIZATION_DESCRIPTION));
		String fileName = FileDialogs.selectInputFileByAWT(parent, filter);

		if (fileName != null) {
			return deserializeObject(fileName);
		}

		return null;
	}

	/**
	 * Deserialize a object by selecting an input file.
	 * 
	 * @param parent
	 *            the parent Component
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Object deserializeObjectSwingWizard(Component parent)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		SimpleFileExtension filter = new SimpleFileExtension(
				SimpleFileExtension.SERIALIZABLE_FILE_EXTENSION,
				InternalResource
						.get(InternalResource.FILE_SERIALIZATION_DESCRIPTION));
		String fileName = FileDialogs.selectInputFileBySwing(parent, filter);

		if (fileName != null) {
			return deserializeObject(fileName);
		}

		return null;
	}

	/**
	 * Store an object in a compressed file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param serializableObject
	 *            the object.
	 * @throws FileNotFoundException
	 *             , IOException If a problem happens.
	 */
	public static void zipSerializeObject(String fileName,
			Serializable serializableObject) throws FileNotFoundException,
			IOException {

		try (FileOutputStream file = new FileOutputStream(new File(fileName))) {
			try (GZIPOutputStream zipOutputStream = new GZIPOutputStream(file)) {

				// Next, create an object that can write to that file.
				try (ObjectOutputStream outStream = new ObjectOutputStream(
						zipOutputStream)) {
					// Save each object.
					outStream.writeObject(serializableObject);

					outStream.flush();
					outStream.close();

					zipOutputStream.flush();
					zipOutputStream.close();
				}
			}
		}
	}

	/**
	 * Read a serializable object from a compressed file
	 * 
	 * @param fileName
	 *            The file name
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object zipDeserializeObject(String fileName)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		// Create a stream for reading.
		FileInputStream fileInputStream = new FileInputStream(fileName);

		GZIPInputStream zipInputStream = new GZIPInputStream(fileInputStream);

		// Next, create an object that can read from that file.
		try (ObjectInputStream inStream = new ObjectInputStream(zipInputStream)) {
			// Retrieve the Serializable object.
			Object object = inStream.readObject();

			return object;
		}
	}
}
