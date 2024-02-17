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

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.Timer;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.Serialization;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.SwingUtil;

/**
 * A Scrap book.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ScrapBook extends JDialog implements EscapeKeyListener {

	private static final long serialVersionUID = 1L;

	private static final String SCRAP_CONTENT = "Scrap.ser"; //$NON-NLS-1$

	private TextAreaPanel textAreaPanel = null;

	/**
	 * Default constructor.
	 * 
	 * @param mainComponent
	 *            The super component.
	 */
	ScrapBook(Component mainComponent) {

		setTitle(Text.get(Text.SCRAP_BOOK_WINDOW_TITLE));

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		setWindowPosition();

		addWindowListener(new WindowAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent
			 * )
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void windowClosing(WindowEvent event) {

				FilterAnyConfiguration
						.setScrapBookPosition(ScrapBook.this.textAreaPanel
								.getCaretPosition());

				saveContent();

				ScrapBook.this.textAreaPanel.hideFind();

				super.windowClosing(event);
			}

		});

		this.textAreaPanel = new TextAreaPanel(this,
				Text.get(Text.SCRAP_BOOK_WINDOW_TITLE),
				new Color(240, 240, 240), new Color(255, 255, 255), new Color(
						0, 0, 0), new Color(200, 200, 200), new Color(80, 80,
						80), new Color(200, 80, 80), new Color(200, 200, 200),
				new Color(0, 0, 0), new Color(240, 240, 240), Color.black,
				TextAreaPanel.FIND_LOCATION.CENTER, true);

		this.textAreaPanel.addMenuItemToPopup(new JMenuItem(Event
				.getTextAreaFindAndReplaceScrapbookAction()));

		// Add the event that shows the find dialog to the auxiliar area.
		Event.getTextAreaFindAndReplaceScrapbookAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@SuppressWarnings("synthetic-access")
					@Override
					public void update(SimpleActionSubject simpleSubject) {

						ScrapBook.this.textAreaPanel.showFindDialog();
					}
				});

		try {
			StringBuffer topText = (StringBuffer) Serialization
					.zipDeserializeObject(SCRAP_CONTENT);
			this.textAreaPanel.setTextArea(topText.toString());
			this.textAreaPanel.clearUndo();

		} catch (FileNotFoundException exception) {
			// Ignore. Not relevant.
		} catch (IOException exception) {
			// Ignore. Not relevant.
		} catch (ClassNotFoundException exception) {
			// Ignore. Not relevant.
		}

		setContentPane(this.textAreaPanel);

		// Position the caret on a the previous position.
		int position = 0;
		String positionParameter = FilterAnyConfiguration
				.getScrapBookPosition();
		try {
			position = Integer.parseInt(positionParameter);
		} catch (NumberFormatException e) {
			position = 0;
		}
		this.textAreaPanel.setCaretPosition(position);
		this.textAreaPanel.setEscapeKeyListener(this);

		Timer redrawLineNumber = new Timer(1000, new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {

				ScrapBook.this.textAreaPanel.validate();
			}
		});
		redrawLineNumber.start();

		setVisible(true);
	}

	/**
	 * Position the window.
	 */
	private void setWindowPosition() {

		// Get window dimensions.
		int screenHeight = SwingUtil.getScreenHeight();
		int screenWidth = SwingUtil.getScreenWidth();

		// Get the empty spaces from windows to screen limits.
		int emptyHeight = (int) (screenHeight * 0.2);
		int emptyWidth = (int) (screenWidth * 0.2);

		// Calculate the maximum window size.
		int windowHeight = screenHeight - (emptyHeight * 2);
		int windowWidth = screenWidth - (emptyWidth * 2);

		// Set the size and location of the window.
		setSize(windowWidth, windowHeight);
		setLocation((screenWidth - windowWidth) / 2,
				(screenHeight - windowHeight) / 2);

	}

	/**
	 * Hide the find text dialog.
	 */
	public void hideFindDialog() {

		this.textAreaPanel.hideFind();
	}

	/**
	 * Save the text area content.
	 */
	public void saveContent() {
		try {

			StringBuffer text = new StringBuffer(
					this.textAreaPanel.getTextArea());
			Serialization.zipSerializeObject(SCRAP_CONTENT, text);
		} catch (FileNotFoundException exception) {
			// Ignore.
		} catch (IOException exception) {
			// Ignore.
		}
	}

	/**
	 * Show the find dialog on the formatted editor.
	 */
	public void showGoToLineDialogOnFormattedEditor() {

		this.textAreaPanel.showFindDialogOnFormattedEditor();
	}

	/**
	 * Add an action to the popup menu.
	 * 
	 * @param action
	 *            The action.
	 */
	public void addActionOnInternalFormattedEditorPopup(Action action) {

		this.textAreaPanel.addActionOnInternalFormattedEditorPopup(action);
	}

	/**
	 * Clear history.
	 */
	public void clearHistory() {

		this.textAreaPanel.clearHistory();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.EscapeKeyListener#escapeKeyPressed()
	 */
	@Override
	public void escapeKeyPressed() {

		setVisible(false);
	}

}
