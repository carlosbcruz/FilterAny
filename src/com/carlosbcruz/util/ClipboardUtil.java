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

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Help using the clipboard.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public final class ClipboardUtil implements ClipboardOwner {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.datatransfer.ClipboardOwner#lostOwnership(java.awt.datatransfer
	 * .Clipboard, java.awt.datatransfer.Transferable)
	 */
	@Override
	public void lostOwnership(Clipboard clipboard, Transferable transferable) {

		// Do nothing.
	}

	/**
	 * Place a text on the clipboard, and make this class the owner of the
	 * Clipboard's contents.
	 * 
	 * @param text
	 *            the clipboard content.
	 */
	public void setClipboardContents(String text) {

		StringSelection stringSelection = new StringSelection(text);

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		clipboard.setContents(stringSelection, this);
	}

	/**
	 * Get the text residing on the clipboard.
	 * 
	 * @return any text found on the clipboard.
	 */
	public static String getClipboardContents() {

		String result = new String();

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		// The Object param of getContents is not currently used
		Transferable contents = clipboard.getContents(null);

		boolean hasTransferableText = (contents != null)
				&& contents.isDataFlavorSupported(DataFlavor.stringFlavor);

		if (hasTransferableText) {

			try {
				result = (String) contents
						.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException exeption) {
				// Do nothing.
			} catch (IOException exeption) {
				// Do nothing.
			}
		}
		return result;
	}
}
