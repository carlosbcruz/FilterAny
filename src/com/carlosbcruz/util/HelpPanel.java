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

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Provides a panel to show help using HTML text.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class HelpPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JEditorPane filterHelp = new JEditorPane();

	/**
	 * Create a help panel for text in a specific char set.
	 * 
	 * @param charSet
	 *            The char set.
	 * @param backgroundColor
	 *            The background color.
	 */
	public HelpPanel(String charSet, Color backgroundColor) {

		this.filterHelp.setBackground(backgroundColor);
		this.filterHelp.setContentType("text/html;charset=" + charSet); //$NON-NLS-1$
		this.filterHelp.setEditable(false);

		setLayout(new BorderLayout());
		add(new JScrollPane(this.filterHelp), BorderLayout.CENTER);
	}

	/**
	 * Set the HTML text.
	 * 
	 * @param html
	 *            The HTML text.
	 */
	public void setHTMLText(String html) {

		this.filterHelp.setText(html);
		this.filterHelp.setCaretPosition(0);
	}
}
