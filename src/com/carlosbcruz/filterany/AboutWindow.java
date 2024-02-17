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

package com.carlosbcruz.filterany;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SwingUtil;

/**
 * Show the about window.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class AboutWindow extends Window implements WindowFocusListener {

	private static final long serialVersionUID = 1L;

	private static TextAreaPanelSimple textArea = null;

	private static Window thisWindow = null;

	/**
	 * Constructor.
	 * 
	 * @param owner
	 *            The owner window.
	 */
	public AboutWindow(Window owner) {

		super(owner);

		setThisWindow(this);

		add(getAboutJFrame());

		pack();

		// Limit the length of the window.
		setSize(getSize().width, 400);

		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);

		SwingUtil.centerOnScreen(this);

		addWindowFocusListener(this);
	}

	/**
	 * Create the internal about panel.
	 * 
	 * @return The about panel.
	 */
	public static JPanel getAboutJFrame() {

		JPanel mainAboutPanel = new JPanel(new BorderLayout());
		JLabel aboutImage = new JLabel(
				SwingUtil.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "images/FilterAnyHeader.png")); //$NON-NLS-1$
		mainAboutPanel.add(aboutImage, BorderLayout.NORTH);

		setTextArea(new TextAreaPanelSimple());

		StringBuffer text = new StringBuffer();


		text.append("\n"); //$NON-NLS-1$

		// Apache License Version 2.0, January 2004
		// Using latest.
		text.append("List of open source libraries\n"); //$NON-NLS-1$
		text.append("only on full version.\n"); //$NON-NLS-1$
		text.append("Lista de bibliotecas opensource\n"); //$NON-NLS-1$
		text.append("somente na versão completa\n"); //$NON-NLS-1$

		getTextArea().setTextArea(text.toString());

		mainAboutPanel.add(getTextArea(), BorderLayout.CENTER);

		getTextArea().setCaretPosition(0);

		StringBuffer output = new StringBuffer(399);
		output.append("<html>\n"); //$NON-NLS-1$
		output.append("<head>\n"); //$NON-NLS-1$
		output.append("</head>\n"); //$NON-NLS-1$
		output.append("<body\n"); //$NON-NLS-1$
		output.append(" style=\"font-family: sans-serif; font-size: 14pt; background-color: rgb(240, 240, 240);\">\n"); //$NON-NLS-1$
		output.append("<p style=\"text-align: center;\"><span\n"); //$NON-NLS-1$
		output.append(" style=\"font-weight: bold;\">\n"); //$NON-NLS-1$
		output.append((FilterAnyConfiguration.isRunningADemonstrationVersion() ? Text
				.get(Text.DEMONSTRATION_VERSION_TEXT) : Text
				.get(Text.REGISTER_PREFIX))
				+ "<br/></span>\n"); //$NON-NLS-1$
		output.append("<p style=\"text-align: center;\"><span\n"); //$NON-NLS-1$
		output.append(" style=\"font-weight: bold;\">\n"); //$NON-NLS-1$
		output.append("</p>\n"); //$NON-NLS-1$
		output.append("</body>\n"); //$NON-NLS-1$
		output.append("</html>\n"); //$NON-NLS-1$

		JEditorPane registerPane = new JEditorPane("text/html", output //$NON-NLS-1$
				.toString());

		registerPane.setEditable(false);

		mainAboutPanel.add(new JScrollPane(registerPane), BorderLayout.SOUTH);

		Timer redrawLineNumber = new Timer(1000, new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				getTextArea().validate();
			}
		});
		redrawLineNumber.start();

		KeyAdapter keyAdapter = new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyPressed(KeyEvent e) {

				getThisWindow().setVisible(false);
			}
		};

		registerPane.addKeyListener(keyAdapter);

		getTextArea().addTextAreaKeyListener(keyAdapter);

		return mainAboutPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejava.awt.event.WindowFocusListener#windowGainedFocus(java.awt.event.
	 * WindowEvent)
	 */
	@Override
	public void windowGainedFocus(WindowEvent event) {
		// Ignore.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowFocusListener#windowLostFocus(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowLostFocus(WindowEvent event) {

		setVisible(false);

	}

	/**
	 * Internal use only of text area.
	 * 
	 * @return the textArea Provide the text area.
	 */
	static TextAreaPanelSimple getTextArea() {

		return textArea;
	}

	/**
	 * Internal use only of text area.
	 * 
	 * @param textArea
	 *            the textArea to set
	 */
	private static void setTextArea(TextAreaPanelSimple textArea) {

		AboutWindow.textArea = textArea;
	}

	/**
	 * Internal use of thisWindow.
	 * 
	 * @return the thisWindow
	 */
	static Window getThisWindow() {
		return thisWindow;
	}

	/**
	 * Internal use of thisWindow.
	 * 
	 * @param thisWindow
	 *            the thisWindow to set
	 */
	private static void setThisWindow(Window thisWindow) {
		AboutWindow.thisWindow = thisWindow;
	}

}