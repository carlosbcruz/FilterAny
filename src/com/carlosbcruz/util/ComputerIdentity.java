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
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * Identify the computer.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ComputerIdentity {

	/**
	 * Provide an unique number of the mother board.
	 * 
	 * @return The unique number.
	 */
	@SuppressWarnings({ "nls" })
	private static String getMotherboardSN() {

		String result = new String();

		// File to store the VB Script.
		File file = null;

		try {

			file = File.createTempFile("FAmb", ".vbs");

			FileWriter fileWriter = new java.io.FileWriter(file);

			String visualBasicScript = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
					+ "Set colItems = objWMIService.ExecQuery _ \n"
					+ "   (\"Select * from Win32_BaseBoard\") \n"
					+ "For Each objItem in colItems \n"
					+ "    Wscript.Echo objItem.SerialNumber \n"
					+ "    exit for  ' do the first cpu only! \n" + "Next \n";

			fileWriter.write(visualBasicScript);

			fileWriter.close();

			Process process = Runtime.getRuntime().exec(
					"cscript //NoLogo " + file.getPath());

			InputStreamReader inputStreamReader = new InputStreamReader(
					process.getInputStream());

			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String line = new String();
			while ((line = bufferedReader.readLine()) != null) {

				result += line;
			}

			bufferedReader.close();
			inputStreamReader.close();

		} catch (Exception exception) {

			return new String();

		} finally {

			try {

				if (file != null) {
					file.delete();
				}

			} catch (Throwable throwable) {
				// Do nothing.
			}
		}

		String digits = new String();
		for (int i = 0; i < result.length(); i++) {

			char thisChar = result.charAt(i);

			if (Character.isLetterOrDigit(thisChar)) {
				digits += thisChar;
			}
		}

		return digits;
	}

	/**
	 * Provide the file system serial number for drive C.
	 * 
	 * @return The serial number.
	 */
	@SuppressWarnings({ "nls" })
	private static String getSerialNumber() {

		String result = new String();

		File file = null;

		try {

			file = File.createTempFile("FAsn", ".vbs");

			FileWriter fileWriter = new java.io.FileWriter(file);

			String visualBasicScript = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
					+ "Set colDrives = objFSO.Drives\n"
					+ "Set objDrive = colDrives.item(\""
					+ "C"
					+ "\")\n"
					+ "Wscript.Echo objDrive.SerialNumber";

			fileWriter.write(visualBasicScript);

			fileWriter.close();

			Process process = Runtime.getRuntime().exec(
					"cscript //NoLogo " + file.getPath());

			InputStreamReader inputStreamReader = new InputStreamReader(
					process.getInputStream());

			BufferedReader input = new BufferedReader(inputStreamReader);

			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}

			input.close();

			inputStreamReader.close();

		} catch (Exception exception) {

			return new String();

		} finally {

			try {

				if (file != null) {
					file.delete();
				}

			} catch (Throwable throwable) {
				// Do nothing.
			}
		}

		String digits = new String();
		for (int i = 0; i < result.length(); i++) {

			char thisChar = result.charAt(i);

			if (Character.isDigit(thisChar)) {
				digits += thisChar;
			}
		}

		return digits;
	}

	/**
	 * Provide the computer identification.
	 * 
	 * @return The computer identification.
	 */
	public static String getComputerIdentification() {

		return getMotherboardSN() + "-" + getSerialNumber(); //$NON-NLS-1$
	}
}
