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
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Provide simple file dialogs functionalities
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FileDialogs {

	private static final String AWT_DIRECTORY = "awt.directory"; //$NON-NLS-1$
	private static final String SWING_DIRECTORY = "swing.directory"; //$NON-NLS-1$

	/**
	 * Select an output file using ATW graphics
	 * 
	 * @param parent
	 *            The parent frame
	 * @param filter
	 *            The file filter.
	 * @return the file name or null if not chosen.
	 */
	public static String selectOutputFileByAWT(Frame parent,
			SimpleFileExtension filter) {

		// Guarantee a valid parent frame
		Frame parentFrame = parent;
		if (parentFrame == null)
			parentFrame = new Frame();

		// Allow the user to specify an output file.
		FileDialog fileDialog = new FileDialog(parentFrame,
				InternalResource.get(InternalResource.SAVE_FILE),
				FileDialog.LOAD);

		fileDialog.setFile("*" + filter.getExtension()); //$NON-NLS-1$

		String directory = SimpleProperties.getStringPropertyWithDefault(
				AWT_DIRECTORY, "/"); //$NON-NLS-1$
		if (directory != null)
			fileDialog.setDirectory(directory);

		fileDialog.setVisible(true);

		// If the file dialog has been closed
		if (fileDialog.getDirectory() == null || fileDialog.getFile() == null)
			return null;

		String filePath = new String(fileDialog.getDirectory()
				+ fileDialog.getFile());

		// Guarantee extension
		if (filePath.indexOf(filter.getExtension()) < 0) {
			// append ".ser"
			filePath = filePath + filter.getExtension();
		}

		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			ConfirmDialog confirmDialog = new ConfirmDialog(parentFrame,
					InternalResource
							.get(InternalResource.OVERWRITE_FILE_QUESTION));
			confirmDialog.setModal(true);

			confirmDialog.pack();

			fileDialog.setVisible(true);

			if (!confirmDialog.isConfirmed()) {
				return null;
			}
		}

		SimpleProperties.setStringProperty(AWT_DIRECTORY,
				fileDialog.getDirectory());

		return file.getAbsolutePath();
	}

	/**
	 * Select an output file using Swing graphics
	 * 
	 * @param parent
	 *            The parent component
	 * @param filter
	 *            The file filter.
	 * @return the file name or null if not chosen.
	 */
	public static String selectOutputFileBySwing(Component parent,
			SimpleFileExtension filter) {

		// Allow the user to specify an output file.
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.addChoosableFileFilter(filter);

		String directoryName = SimpleProperties.getStringPropertyWithDefault(
				SWING_DIRECTORY, "/"); //$NON-NLS-1$
		if (directoryName != null) {
			File directory = new File(directoryName);
			if (directory.exists() && directory.isDirectory()) {
				fileChooser.setCurrentDirectory(directory);
			}
		}

		fileChooser.showDialog(parent,
				InternalResource.get(InternalResource.SAVE_FILE));

		File file = fileChooser.getSelectedFile();

		// If the file dialog has been closed
		if (file == null)
			return null;

		// Guarantee extension
		String absoluteFileName = file.getAbsolutePath();
		if (absoluteFileName.indexOf(filter.getExtension()) < 0) {
			// append extension
			absoluteFileName = absoluteFileName + filter.getExtension();
		}
		file = new File(absoluteFileName);

		if (file.isFile() && file.exists()) {

			int option = JOptionPane.showConfirmDialog(parent, InternalResource
					.get(InternalResource.OVERWRITE_FILE_QUESTION),
					InternalResource.get(InternalResource.ALERT),
					JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.CANCEL_OPTION) {
				return null;
			}
		}

		int fileNamePosition = file.getPath().indexOf(file.getName());
		SimpleProperties.setStringProperty(SWING_DIRECTORY, file.getPath()
				.substring(0, fileNamePosition));

		return absoluteFileName;
	}

	/**
	 * Select an input file using ATW graphics
	 * 
	 * @param parent
	 *            The parent frame
	 * @param filter
	 *            The file filter
	 * @return the file name or null if not chosen.
	 */
	public static String selectInputFileByAWT(Frame parent,
			SimpleFileExtension filter) {

		// Guarantee a valid parent frame
		Frame parentFrame = parent;
		if (parentFrame == null)
			parentFrame = new Frame();

		// Allow the user to specify an input file.
		FileDialog fileDialog = new FileDialog(parentFrame,
				InternalResource.get(InternalResource.OPEN_FILE),
				FileDialog.LOAD);

		fileDialog.setFile("*" + filter.getExtension()); //$NON-NLS-1$

		String directory = SimpleProperties.getStringPropertyWithDefault(
				AWT_DIRECTORY, "/"); //$NON-NLS-1$
		if (directory != null)
			fileDialog.setDirectory(directory);

		fileDialog.setVisible(true);

		// If the file dialog has been closed
		if (fileDialog.getDirectory() == null || fileDialog.getFile() == null)
			return null;

		SimpleProperties.setStringProperty(AWT_DIRECTORY,
				fileDialog.getDirectory());

		String filePath = new String(fileDialog.getDirectory()
				+ fileDialog.getFile());

		File file = new File(filePath);

		return file.getAbsolutePath();
	}

	/**
	 * Select an input file using Swing graphics
	 * 
	 * @param parent
	 *            The parent component.
	 * @param filter
	 *            The file filter.
	 * @param selectionMode
	 *            The selection mode. It can be JFileChooser.FILES_ONLY,
	 *            JFileChooser.DIRECTORIES_ONLY or
	 *            JFileChooser.FILES_AND_DIRECTORIES
	 * @return the file name or null if not chosen.
	 */
	public static String selectInputFileOrDirectoryBySwing(Component parent,
			SimpleFileExtension filter, int selectionMode) {
		// Allow the user to specify an output file.
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileSelectionMode(selectionMode);

		if (filter != null) {
			fileChooser.addChoosableFileFilter(filter);
		}

		String directoryName = SimpleProperties.getStringPropertyWithDefault(
				SWING_DIRECTORY, "/"); //$NON-NLS-1$
		if (directoryName != null) {
			File directory = new File(directoryName);
			if (directory.exists() && directory.isDirectory()) {
				fileChooser.setCurrentDirectory(directory);
			}
		}

		fileChooser.showDialog(parent,
				InternalResource.get(InternalResource.OPEN_FILE));

		File file = fileChooser.getSelectedFile();
		// If the file dialog has been closed
		if (file == null)
			return null;

		int fileNamePosition = file.getPath().indexOf(file.getName());
		SimpleProperties.setStringProperty(SWING_DIRECTORY, file.getPath()
				.substring(0, fileNamePosition));

		return fileChooser.getSelectedFile().getAbsolutePath();
	}

	/**
	 * Select an input file using Swing graphics
	 * 
	 * @param parent
	 *            The parent component.
	 * @param filter
	 *            The file filter.
	 * @return the file name or null if not chosen.
	 */
	public static String selectInputFileBySwing(Component parent,
			SimpleFileExtension filter) {

		return selectInputFileOrDirectoryBySwing(parent, filter,
				JFileChooser.FILES_ONLY);
	}
}
