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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * File handling with error support. This may be used for non critical
 * applications.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FileSupport {

	/**
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public interface FileFoundEvent {
		/**
		 * @param fileFound
		 *            The name of the file that was found.
		 */
		public void fileFound(String fileFound);
	}

	/**
	 * Provide the file list on all sub-directories of a specific directory.
	 * 
	 * @param file
	 *            The file or directory.
	 * @param files
	 *            The array to store the files.
	 * @param sufixes
	 *            The suffixes to be include. If it is null than include all
	 *            file types.
	 * @return The array with all the files on the sub-directories.
	 */
	public static ArrayList<String> retrieveDirectories(File file[],
			ArrayList<String> files, String suffixes[]) {

		for (int i = 0; i < file.length; i++) {

			if (file[i].isDirectory()) {
				retrieveDirectories(file[i].listFiles(), files, suffixes);
			} else {

				// Verify the suffix.
				String fileName = file[i].getAbsoluteFile().toString();

				// If not suffix is provided than add all files.
				if (suffixes == null || "".equals(suffixes)) { //$NON-NLS-1$

					files.add(fileName);

				} else {

					if (suffixes.length == 0) {

						files.add(fileName);

					} else {

						for (String suffix : suffixes) {

							if (fileName.toUpperCase().endsWith(
									suffix.toUpperCase())) {

								files.add(fileName);
							}
						}
					}
				}
			}
		}

		return files;
	}

	/**
	 * Provide the file list on all sub-directories of a specific directory.
	 * 
	 * @param file
	 *            The file or directory.
	 * @param files
	 *            The array to store the files.
	 * @param sufixes
	 *            The suffixes to be include. If it is null than include all
	 *            file types.
	 * @param filesFoundEvent
	 *            Indicate that a list of files were found.
	 * @return The array with all the files on the sub-directories.
	 */
	public static ArrayList<String> retrieveDirectories(File file[],
			ArrayList<String> files, String suffixes[],
			FileFoundEvent filesFoundEvent) {

		for (int i = 0; i < file.length; i++) {

			if (file[i].isDirectory()) {
				retrieveDirectories(file[i].listFiles(), files, suffixes,
						filesFoundEvent);
			} else {

				// Verify the suffix.
				String fileName = file[i].getAbsoluteFile().toString();

				// If not suffix is provided than add all files.
				if (suffixes == null || "".equals(suffixes)) { //$NON-NLS-1$

					files.add(fileName);
					filesFoundEvent.fileFound(fileName);

				} else {

					if (suffixes.length == 0) {

						files.add(fileName);
						filesFoundEvent.fileFound(fileName);

					} else {

						for (String suffix : suffixes) {

							if (fileName.toUpperCase().endsWith(
									suffix.toUpperCase())) {

								files.add(fileName);
								filesFoundEvent.fileFound(fileName);
							}
						}
					}
				}
			}
		}

		return files;
	}

	/**
	 * Provide the file list on all sub-directories of a specific directory.
	 * 
	 * @param directory
	 *            The directory to be searched.
	 * @param sufixes
	 *            The suffixes to be include. If it is null than include all
	 *            file types.
	 * @return The array with all the files on the sub-directories.
	 */
	public static String[] getFileOnSubfolder(String directory,
			String suffixes[]) {

		ArrayList<String> files = new ArrayList<>();
		files = retrieveDirectories(new File[] { new File(directory) }, files,
				suffixes);

		String[] returnFiles = new String[files.size()];
		return files.toArray(returnFiles);
	}

	/**
	 * Provide the file list on all sub-directories of a specific directory with
	 * callback
	 * 
	 * @param directory
	 *            The directory to be searched.
	 * @param sufixes
	 *            The suffixes to be include. If it is null than include all
	 *            file types.
	 * @param filesFoundEvent
	 *            Indicate that a list of files were found.
	 * @return The array with all the files on the sub-directories.
	 */
	public static String[] getFileOnSubfolder(String directory,
			String suffixes[], FileFoundEvent filesFoundEvent) {

		ArrayList<String> files = new ArrayList<>();
		files = retrieveDirectories(new File[] { new File(directory) }, files,
				suffixes, filesFoundEvent);

		String[] returnFiles = new String[files.size()];
		return files.toArray(returnFiles);
	}

	/**
	 * Generate a sequence of directories
	 * 
	 * @param directory
	 *            The target directories.
	 * @return The sequence of directories.
	 */
	private static String[] getSequenceOfDirectories(String directory) {

		String targetDirectory = new String();

		ArrayList<String> directories = new ArrayList<>();

		for (char character : directory.toCharArray()) {

			if ('/' == character || '\\' == character) {
				directories.add(new String(targetDirectory));
			}

			targetDirectory += character;
		}
		directories.add(new String(targetDirectory));

		String[] returnDirectoreis = new String[directories.size()];
		returnDirectoreis = directories.toArray(returnDirectoreis);

		return returnDirectoreis;
	}

	/**
	 * Provide only the path of an absolute file name.
	 * 
	 * @param absoluteFileName
	 *            The absolute file name.
	 * @return The path.
	 */
	public static String getOnlyPath(String absoluteFileName) {

		int lastDirectoryPosition = 0;

		for (int i = 0; i < absoluteFileName.length(); i++) {

			char thisCharacter = absoluteFileName.charAt(i);

			if ('/' == thisCharacter || '\\' == thisCharacter) {
				lastDirectoryPosition = i;
			}
		}

		return absoluteFileName.substring(0, lastDirectoryPosition + 1);
	}

	/**
	 * Verify if the two files are in the same directory.
	 * 
	 * @param firstFile
	 *            First file.
	 * @param secondFile
	 *            Second file.
	 * @return True if both files are on the same directory.
	 */
	public static boolean isInSameDirectory(File firstFile, File secondFile) {

		String firstFileName = firstFile.getAbsolutePath();
		String secondFileName = secondFile.getAbsolutePath();

		String firstFileDirectory = getOnlyPath(firstFileName);
		String secondFileDirectory = getOnlyPath(secondFileName);

		return firstFileDirectory.equals(secondFileDirectory);
	}

	/**
	 * Verify if the files has the same name disconsidering the path.
	 * 
	 * @param firstFile
	 *            First file.
	 * @param secondFile
	 *            Second file.
	 * @return True if both files have the same name.
	 */
	public static boolean hasSameFileName(File firstFile, File secondFile) {

		String firstFileName = firstFile.getName();
		String secondFileName = secondFile.getName();

		return firstFileName.equals(secondFileName);

	}

	/**
	 * Retrieve the common prefix between two directory strings.
	 * 
	 * @param firstText
	 *            The first directory string.
	 * @param secondText
	 *            The second directory string.
	 * @return The common directory prefix.
	 */
	public static String getCommonPrefixDirectory(String firstText,
			String secondText) {

		// Replace back slash by slash.
		String commonPrefix = StringSupport.getCommonPrefix(firstText,
				secondText);

		// Find last slash
		int lastSlash = 0;
		for (int i = 0; i < commonPrefix.length(); i++) {

			char thisChar = commonPrefix.charAt(i);

			if (thisChar == '/' || thisChar == '\\') {
				lastSlash = i + 1;
			}
		}

		// Return common directory.
		if (lastSlash == 0) {
			return ""; //$NON-NLS-1$
		}

		return commonPrefix.substring(0, lastSlash);
	}

	/**
	 * Indicate that the listOfFiles being
	 * 
	 * @param directoryName
	 * @return true if the creation was successful and false otherwise.
	 */
	public static boolean createDirectory(String directoryName) {

		String directories[] = getSequenceOfDirectories(directoryName);

		for (String directory : directories) {

			File thisDirectory = new File(directory);

			if (!thisDirectory.exists()) {

				if (!thisDirectory.mkdir()) {
					return false;
				}
			}

		}

		return true;
	}

	/**
	 * Read a text file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param encoding
	 *            The file encoding.
	 * @return
	 */
	public static StringBuffer readEncodedTextFile(String fileName,
			String encoding) {

		if (encoding == null) {

			return readTextFile(fileName);
		}

		InputStreamReader streamReader = null;
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer();
		try {

			streamReader = new InputStreamReader(new FileInputStream(new File(
					fileName)), encoding);

			reader = new BufferedReader(streamReader);

			String line;
			boolean firstLine = true;
			do {

				try {
					line = reader.readLine();
				} catch (IOException exception) {
					ExceptionSupport.handleException(exception);
					return null;
				}

				if (line != null) {

					if (!firstLine) {
						content.append(CommonConstants.NEW_LINE);
					}
					firstLine = false;
					content.append(line);
				}

			} while (line != null);

		} catch (UnsupportedEncodingException exception) {

			ExceptionSupport.handleException(exception);
			return null;

		} catch (FileNotFoundException exception) {

			ExceptionSupport.handleException(exception);
			return null;

		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}
			try {
				if (streamReader != null) {
					streamReader.close();
				}
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}
		}

		return content;

	}

	/**
	 * Read a text file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @return The content or null if there is a problem.
	 */
	@SuppressWarnings("resource")
	public static StringBuffer readTextFile(String fileName) {

		File file = new File(fileName);

		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException exception) {
			ExceptionSupport.handleException(exception);
			return null;
		}

		BufferedReader reader = new BufferedReader(fileReader);

		StringBuffer content = new StringBuffer();

		String line;
		boolean firstLine = true;
		do {

			try {
				line = reader.readLine();
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}

			if (line != null) {

				if (!firstLine) {
					content.append(CommonConstants.NEW_LINE);
				}
				firstLine = false;
				content.append(line);
			}

		} while (line != null);

		try {
			reader.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
		}

		try {
			fileReader.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
		}

		return content;
	}

	/**
	 * Inform if a file exist.
	 * 
	 * @param fileName
	 *            The file name.
	 * @return True if the file exists, false otherwise.
	 */
	public static boolean fileExist(String fileName) {

		File file = new File(fileName);

		return file.exists();
	}

	/**
	 * Write text to file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param content
	 *            The content of the file.
	 * @param encoding
	 *            The encoding.
	 * @return null if no problem or a message is error.
	 */
	@SuppressWarnings("resource")
	public static String writeEncodedTextFile(String fileName, String content,
			String encoding) {

		if (encoding == null) {

			return writeTextFile(fileName, content);
		}

		File file = new File(fileName);

		if (file.exists()) {
			if (!file.delete()) {
				ExceptionSupport.handleException(InternalResource.get(
						InternalResource.EXCEPTION_COULD_NO_DELETE, fileName));
				return InternalResource.get(
						InternalResource.EXCEPTION_COULD_NO_DELETE, fileName);
			}
		}

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
		} catch (FileNotFoundException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		OutputStreamWriter writeStream = null;
		try {
			writeStream = new OutputStreamWriter(outputStream, encoding);
		} catch (UnsupportedEncodingException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		try {
			writeStream.write(content);
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		try {
			writeStream.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		try {
			outputStream.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		return null;

	}

	/**
	 * Write text to file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param content
	 *            The content of the file.
	 * @return null if no problem or a message is error.
	 */
	@SuppressWarnings("resource")
	public static String writeTextFile(String fileName, String content) {

		File file = new File(fileName);

		if (file.exists()) {
			if (!file.delete()) {
				ExceptionSupport.handleException(InternalResource.get(
						InternalResource.EXCEPTION_COULD_NO_DELETE, fileName));
				return InternalResource.get(
						InternalResource.EXCEPTION_COULD_NO_DELETE, fileName);
			}
		}

		FileWriter fileWriter;
		try {

			fileWriter = new FileWriter(file);

		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		BufferedWriter writer = new BufferedWriter(fileWriter);

		try {
			writer.write(content);
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		try {
			writer.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		try {
			fileWriter.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		return null;
	}

	/**
	 * Write text to file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param content
	 *            The content of the file.
	 * @param encoding
	 *            The char set encoding.
	 * @return null if no problem or a message is error.
	 */
	public static String writeTextFileWithEncoding(String fileName,
			String content, String encoding) {

		File file = new File(fileName);

		if (file.exists()) {
			if (!file.delete()) {
				ExceptionSupport.handleException(InternalResource.get(
						InternalResource.EXCEPTION_COULD_NO_DELETE, fileName));
				return InternalResource.get(
						InternalResource.EXCEPTION_COULD_NO_DELETE, fileName);
			}
		}

		FileOutputStream fileOutput = null;

		try {

			fileOutput = new FileOutputStream(file);

		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		OutputStreamWriter output = null;

		try {
			output = new OutputStreamWriter(fileOutput, encoding);
		} catch (UnsupportedEncodingException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		} finally {

			try {
				if (output != null) {
					output.write(content);

				}
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return exception.getLocalizedMessage();
			}
			try {
				if (output != null) {
					output.flush();
					output.close();
				}
				fileOutput.close();
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return exception.getLocalizedMessage();
			}
		}

		return null;
	}

	/**
	 * Append text to file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param content
	 *            The content of the file.
	 * @param outputEncode
	 *            The output encode.
	 * @return null if no problem or a message is error.
	 */
	@SuppressWarnings("resource")
	public static String appendEncodedTextFile(String fileName, String content,
			String outputEncode) {

		File file = new File(fileName);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file, true);
		} catch (FileNotFoundException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		OutputStreamWriter writeStream = null;
		try {
			if (outputEncode == null) {
				writeStream = new OutputStreamWriter(outputStream);
			} else {
				writeStream = new OutputStreamWriter(outputStream, outputEncode);
			}
		} catch (UnsupportedEncodingException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		try {
			writeStream.append(content);
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		try {
			writeStream.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		try {
			outputStream.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		return null;
	}

	/**
	 * Append text to file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param content
	 *            The content of the file.
	 * @return null if no problem or a message is error.
	 */
	public static String appendTextFile(String fileName, String content) {

		File file = new File(fileName);

		FileWriter fileReader;
		try {
			fileReader = new FileWriter(file, true);
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		}

		BufferedWriter writer = new BufferedWriter(fileReader);

		try {
			writer.append(content);
			writer.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return exception.getLocalizedMessage();
		} finally {
			try {
				writer.close();
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return exception.getLocalizedMessage();
			}
			try {
				fileReader.close();
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return exception.getLocalizedMessage();
			}
		}

		return null;
	}

	/**
	 * Read a binary file.
	 * 
	 * @param inputStream
	 *            The input stream with the content requested.
	 * @return The content or null if there is a problem.
	 */
	public static byte[] readBinaryFile(InputStream inputStream) {

		DataInputStream data = new DataInputStream(inputStream);

		ArrayList<Byte> arrayOfByte = new ArrayList<>();
		try {
			while (true) {
				arrayOfByte.add(new Byte(data.readByte()));
			}
		} catch (EOFException exception) {
			// Not a real exception. It is EOF.
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			// There was an eeror. return nothing.
			return new byte[] {};
		} finally {
			try {
				data.close();
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}
		}

		// Copy the array list over the byte array.
		byte content[] = new byte[arrayOfByte.size()];
		for (int i = 0; i < arrayOfByte.size(); i++) {
			content[i] = arrayOfByte.get(i).byteValue();
		}

		return content;
	}

	/**
	 * Read a binary file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @return The content or null if there is a problem.
	 */
	@SuppressWarnings("resource")
	public static byte[] readBinaryFile(File file) {

		FileInputStream stream = null;

		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException exception) {
			ExceptionSupport.handleException(exception);
			return null;
		}

		DataInputStream data = new DataInputStream(stream);

		long size = file.length();

		if (size > 550000000L) {
			ExceptionSupport.handleException(InternalResource.get(
					InternalResource.FILE_TOO_BIG, file.getName()));
			return null;

		}

		byte content[] = new byte[(int) size];

		try {
			data.readFully(content);
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return null;
		} finally {
			try {
				data.close();
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}
			try {
				stream.close();
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}
		}

		return content;
	}

	/**
	 * Read a binary file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @return The content or null if there is a problem.
	 */
	public static byte[] readBinaryFile(String fileName) {

		File file = new File(fileName);

		return readBinaryFile(file);
	}

	/**
	 * Write a binary file.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param content
	 *            The file content.
	 */
	public static void writeBinaryFile(String fileName, byte content[]) {

		File file = new File(fileName);

		FileOutputStream stream = null;

		try {
			stream = new FileOutputStream(file);
		} catch (FileNotFoundException exception) {
			ExceptionSupport.handleException(exception);
			return;
		}

		DataOutputStream data = new DataOutputStream(stream);

		try {
			data.write(content);
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
			return;
		} finally {
			try {
				data.close();
			} catch (IOException exception) {

				ExceptionSupport.handleException(exception);
				return;
			}
			try {
				stream.close();
			} catch (IOException exception) {

				ExceptionSupport.handleException(exception);
				return;
			}
		}

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
	 * A variation to write a file to a specific directory.
	 * 
	 * @param directoryName
	 *            The directory name.
	 * @param fileName
	 *            The file name.
	 * @param content
	 *            The content of the file.
	 * @return null if no problem or a message is error.
	 */
	public static String writeTextFile(String directoryName, String fileName,
			String content) {

		return writeTextFile(getFileName(directoryName, fileName), content);
	}

	/**
	 * Read a text listOfFiles and return an array of strings each one
	 * representing a line.
	 * 
	 * @param fileName
	 *            The file name.
	 * @return The lines of the file.
	 */
	@SuppressWarnings("resource")
	public static String[] readAllLines(String fileName) {

		File file = new File(fileName);

		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException exception) {
			ExceptionSupport.handleException(exception);
			return null;
		}

		BufferedReader reader = new BufferedReader(fileReader);

		ArrayList<String> lines = new ArrayList<>();

		String line;
		do {

			try {
				line = reader.readLine();
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}

			if (line != null) {

				lines.add(line);
			}

		} while (line != null);

		String resultingLines[] = new String[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			resultingLines[i] = lines.get(i);
		}

		try {
			reader.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
		}
		try {
			fileReader.close();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
		}

		return resultingLines;
	}

	/**
	 * Read a text listOfFiles and return an array of strings each one
	 * representing a line.
	 * 
	 * @param fileName
	 *            The file name.
	 * @param encode
	 *            The encode.
	 * @return The lines of the file.
	 * @throws IOException
	 */
	public static String[] readAllEncodedLinesWithExceptions(String fileName,
			String encode) throws IOException {

		if (encode == null) {

			return readAllLinesWithExceptions(fileName);
		}

		ArrayList<String> lines = new ArrayList<>();

		InputStreamReader streamReader = null;
		BufferedReader reader = null;
		try {

			streamReader = new InputStreamReader(new FileInputStream(new File(
					fileName)), encode);

			reader = new BufferedReader(streamReader);

			String line;
			do {

				try {
					line = reader.readLine();
				} catch (IOException exception) {
					ExceptionSupport.handleException(exception);
					return null;
				}

				if (line != null) {
					lines.add(line);
				}

			} while (line != null);

		} catch (UnsupportedEncodingException exception) {

			ExceptionSupport.handleException(exception);
			return null;

		} catch (FileNotFoundException exception) {

			ExceptionSupport.handleException(exception);
			return null;

		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}
			try {
				if (streamReader != null) {
					streamReader.close();
				}
			} catch (IOException exception) {
				ExceptionSupport.handleException(exception);
				return null;
			}
		}

		String resultingLines[] = new String[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			resultingLines[i] = lines.get(i);
		}

		return resultingLines;

	}

	/**
	 * Read a text listOfFiles and return an array of strings each one
	 * representing a line.
	 * 
	 * @param fileName
	 *            The file name.
	 * @return The lines of the file.
	 * @throws IOException
	 */
	public static String[] readAllLinesWithExceptions(String fileName)
			throws IOException {

		File file = new File(fileName);

		FileReader fileReader;
		fileReader = new FileReader(file);

		BufferedReader reader = new BufferedReader(fileReader);

		ArrayList<String> lines = new ArrayList<>();

		String line;
		do {

			line = reader.readLine();

			if (line != null) {

				lines.add(line);
			}

		} while (line != null);

		String resultingLines[] = new String[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			resultingLines[i] = lines.get(i);
		}

		reader.close();
		fileReader.close();

		return resultingLines;
	}

	/**
	 * Retrieve the list of files from a specific directory
	 * 
	 * @param directory
	 *            The directory name
	 * @param suffixesParameter
	 *            The suffixes to be include. If it is null than include all
	 *            file types.
	 * @return The array of file names or null if error.
	 */
	public static String[] getFilesFromDirectory(String directory,
			String suffixesParameter[]) {

		String suffixes[] = suffixesParameter;

		if (suffixes == null) {
			suffixes = new String[0];
		}

		File searchDirectory = new File(directory);
		try {
			searchDirectory = searchDirectory.getCanonicalFile();
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
		}

		if (!searchDirectory.isDirectory()) {
			return null;
		}

		File fileList[] = searchDirectory.listFiles();

		ArrayList<String> searchFiles = new ArrayList<>();
		for (File file : fileList) {

			if (!file.isDirectory()) {

				// Verify the suffix.
				String fileName = file.getAbsoluteFile().toString();

				// If not suffix is provided than add all files.
				if (suffixes.length == 0) {

					searchFiles.add(fileName);

				} else {

					// Only include the ones with the suffix.
					for (String suffix : suffixes) {

						if (fileName.toUpperCase().endsWith(
								suffix.toUpperCase())) {

							searchFiles.add(fileName);
							break;
						}
					}
				}
			}
		}

		String fileNames[] = new String[searchFiles.size()];
		fileNames = searchFiles.toArray(fileNames);

		return fileNames;
	}

	/**
	 * Generate a string with the current date and time.
	 * 
	 * @return The current date to be used in a file name.
	 */
	public static String getCurrentDateForFileName() {

		Calendar calendar = Calendar.getInstance();

		String month = (calendar.get(Calendar.MONTH) + 1) < 10 ? "0" //$NON-NLS-1$
				+ (calendar.get(Calendar.MONTH) + 1) : "" //$NON-NLS-1$
				+ (calendar.get(Calendar.MONTH) + 1);
		String day = (calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0" //$NON-NLS-1$
				+ calendar.get(Calendar.DAY_OF_MONTH) : "" //$NON-NLS-1$
				+ calendar.get(Calendar.DAY_OF_MONTH);
		String hour = calendar.get(Calendar.HOUR_OF_DAY) < 10 ? "0" //$NON-NLS-1$
				+ calendar.get(Calendar.HOUR_OF_DAY) : "" //$NON-NLS-1$
				+ calendar.get(Calendar.HOUR_OF_DAY);
		String minute = calendar.get(Calendar.MINUTE) < 10 ? "0" //$NON-NLS-1$
				+ calendar.get(Calendar.MINUTE) : "" //$NON-NLS-1$
				+ calendar.get(Calendar.MINUTE);
		String second = calendar.get(Calendar.SECOND) < 10 ? "0" //$NON-NLS-1$
				+ calendar.get(Calendar.SECOND) : "" //$NON-NLS-1$
				+ calendar.get(Calendar.SECOND);

		return "" + calendar.get(Calendar.YEAR) + month + day + "_" + hour //$NON-NLS-1$ //$NON-NLS-2$
				+ minute + second;

	}

	/**
	 * Combine a directory name with a file name also on a directory.
	 * 
	 * @param directoryParameter
	 *            The target directory.
	 * @param file
	 *            The file within a directory.
	 * @return The combination of the directory and the file.
	 */
	public static String getCombinedDirectory(String directoryParameter,
			String file) {

		String directory = directoryParameter;

		// If there is no directory than return the file itself.
		if (directory == null || "".equals(directory)) { //$NON-NLS-1$
			return file;
		}

		// Remove the slash at the end of the directory.
		if (directory.charAt(directory.length() - 1) == '\\'
				|| directory.charAt(directory.length() - 1) == '/') {
			directory = directory.substring(0, directory.length() - 1);
		}

		// The file is a required field.
		if (file == null || "".equals(file)) { //$NON-NLS-1$
			throw new InvalidParameterException(
					InternalResource
							.get(InternalResource.EXCEPTION_MISSING_FILE_NAME));
		}

		return directory + file;
	}

	/**
	 * Remove the files from a specific directory.
	 * 
	 * @param directory
	 *            The target directory.
	 * @return The list of files removed successfully.
	 */
	public static String[] deleteFilesFromDirectory(String directory) {

		// Clear output directory.
		String fileNames[] = FileSupport.getFilesFromDirectory(directory, null);
		ArrayList<String> filesRemoved = new ArrayList<>();

		for (String name : fileNames) {

			File file = new File(name);
			// Only remove files.
			if (file.isFile()) {

				if (!file.delete()) {
					ExceptionSupport.handleException(InternalResource.get(
							InternalResource.EXCEPTION_COULD_NO_DELETE,
							file.toString()));
				} else {
					filesRemoved.add(file.getName());
				}
			}
		}

		String removedFileNames[] = new String[filesRemoved.size()];

		return filesRemoved.toArray(removedFileNames);
	}

	/**
	 * Remove all the files and directory from a specific location.
	 * 
	 * @param element
	 *            The initial directory.
	 * @return The files and directories that were removed.
	 */
	public static ArrayList<String> deleteDirectoryTree(String element) {

		File fileOrDirectory = new File(element);
		ArrayList<String> elementsRemoved = new ArrayList<>();

		// Not a valid input.
		if (!fileOrDirectory.isDirectory() && !fileOrDirectory.isFile()) {

			return elementsRemoved;
		}

		// Remove all files from directory.
		if (fileOrDirectory.isDirectory()) {

			String filesRemoved[] = deleteFilesFromDirectory(fileOrDirectory
					.getAbsolutePath());
			ArrayList<String> filesRemovedArray = new ArrayList<>();

			for (String fileToBeRemoved : filesRemoved) {
				filesRemovedArray.add(fileToBeRemoved);
			}
			elementsRemoved.addAll(filesRemovedArray);

		}

		File remainingDirectoreis[] = fileOrDirectory.listFiles();

		if (remainingDirectoreis.length == 0) {

			if (!fileOrDirectory.delete()) {

				ExceptionSupport.handleException(InternalResource.get(
						InternalResource.EXCEPTION_COULD_NO_DELETE,
						fileOrDirectory.getAbsolutePath()));

			}

			return elementsRemoved;

		}

		for (File file : remainingDirectoreis) {

			elementsRemoved.addAll(deleteDirectoryTree(file.getAbsolutePath()));
		}

		return elementsRemoved;
	}

	/**
	 * Copy a file from one location to another.
	 * 
	 * @param fromFileName
	 *            The name of the file to be copied.
	 * @param toFileName
	 *            The destination of the copy.
	 */
	public static void copyFile(String fromFileName, String toFileName) {

		if (new File(fromFileName).equals(new File(toFileName))) {

			// Do not copy the file to itself.
			return;
		}

		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;

		try {

			inputStream = new FileInputStream(new File(fromFileName));

			outputStream = new FileOutputStream(new File(toFileName));

			// Internal buffer.
			byte[] bytes = new byte[1024];

			int noOfBytes = 0;

			// Read bytes from source file and write to destination file
			while ((noOfBytes = inputStream.read(bytes)) != -1) {

				outputStream.write(bytes, 0, noOfBytes);
			}

		} catch (FileNotFoundException exception) {

			ExceptionSupport.handleException(exception);

		} catch (IOException exception) {

			ExceptionSupport.handleException(exception);

		} finally {

			try {

				// Close the streams.
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}

			} catch (IOException exception) {

				ExceptionSupport.handleException(exception);

			}
		}
	}

	/**
	 * Guarantee that a directory ends with the directory separation symbol.
	 * 
	 * @param directory
	 *            The directory.
	 * @return The directory that ends with the directory separation.
	 */
	public static String addDirectorySymbolAtTheEnd(String directory) {

		String newDirectory = new String();

		// Make the directory end with \ or /.
		if (!directory.endsWith("\\") && !directory.endsWith("/")) { //$NON-NLS-1$ //$NON-NLS-2$

			// Select the correct symbol based on any current directory
			// separation.
			if (directory.indexOf("\\") != -1) { //$NON-NLS-1$
				newDirectory = directory + "\\"; //$NON-NLS-1$
			} else {
				newDirectory = directory + "/"; //$NON-NLS-1$
			}
		}

		return newDirectory;
	}
}
