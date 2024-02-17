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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import com.carlosbcruz.filterany.TextAreaPanel.FIND_LOCATION;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.AutoCleanUpTextField;
import com.carlosbcruz.util.SwingUtil;

/**
 * Alows the selecttion
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FindDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel findLabel = new JLabel(Text.get(Text.FIND_DIALOG_FIND_LABEL));
	private JTextField findText = new AutoCleanUpTextField(
			FilterAnyConfiguration.isAutoCleanupBehavior());

	private JLabel replaceWithLabel = new JLabel(
			Text.get(Text.FIND_DIALOG_REPLACE_WITH_LABEL));
	private JTextField replaceWithText = new AutoCleanUpTextField(
			FilterAnyConfiguration.isAutoCleanupBehavior());

	private JRadioButton forward = new JRadioButton(
			Text.get(Text.FIND_DIALOG_FORWARD));
	private JRadioButton backward = new JRadioButton(
			Text.get(Text.FIND_DIALOG_BACKWARD));

	private boolean previousCaseSensitiveStatus = true;
	private JCheckBox caseSensitive = new JCheckBox(
			Text.get(Text.FIND_DIALOG_CASE_SENSITIVE));
	private JCheckBox regularExpression = new JCheckBox(
			Text.get(Text.FIND_DIALOG_REGULAR_EXPRESSION));

	private JButton find = new JButton(Text.get(Text.FIND_DIALOG_FIND_BUTTON));
	private JButton replaceFind = new JButton(
			Text.get(Text.FIND_DIALOG_REPLACE_FIND_BUTTON));
	private JButton replace = new JButton(
			Text.get(Text.FIND_DIALOG_REPLACE_BUTTON));
	private JButton replaceAll = new JButton(
			Text.get(Text.FIND_DIALOG_REPLACE_ALL));

	private JButton cancel = new JButton(
			Text.get(Text.FIND_DIALOG_CLOSE_BUTTON));

	private JLabel message = new JLabel();

	private JTextArea referenceTextArea = null;

	private JDialog findDialog = null;

	private JTextArea textArea = null;

	// Indicate when the find dialog was hidden due to ESC key.
	private long lastHideEvent = 0L;

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The window title.
	 * @param background
	 *            The background color.
	 * @param foreground
	 *            The foreground color.
	 * @param findDialogLocation
	 *            The location of the find dialog.
	 * @param textAreaParameter
	 *            The text area that this component is attached to.
	 */
	public FindDialog(String title, Color background, Color foreground,
			FIND_LOCATION findDialogLocation, JTextArea textAreaParameter) {

		setTitle(title);

		this.findDialog = this;

		this.textArea = textAreaParameter;

		this.findLabel.setToolTipText(Text.get(Text.FIND_DIALOG_TEXT_TOOLTIP));
		this.findText.setToolTipText(Text.get(Text.FIND_DIALOG_TEXT_TOOLTIP));
		this.replaceWithLabel.setToolTipText(Text
				.get(Text.FIND_DIALOG_REPLACE_WITH_TOOLTIP));
		this.replaceWithText.setToolTipText(Text
				.get(Text.FIND_DIALOG_REPLACE_WITH_TOOLTIP));
		this.forward.setToolTipText(Text.get(Text.FIND_DIALOG_FORWARD_TOOLTIP));
		this.backward.setToolTipText(Text
				.get(Text.FIND_DIALOG_BACKWARD_TOOLTIP));
		this.caseSensitive.setToolTipText(Text
				.get(Text.FIND_DIALOG_CASE_SENSITIVE_TOOLTIP));
		this.regularExpression.setToolTipText(Text
				.get(Text.FIND_DIALOG_REGULAR_EXPRESSION_TOOLTIP));
		this.find.setToolTipText(Text.get(Text.FIND_DIALOG_BUTTON_TOOLTIP));
		this.replaceFind.setToolTipText(Text
				.get(Text.FIND_DIALOG_REPLACE_AND_FIND_TOOLTIP));
		this.replace.setToolTipText(Text.get(Text.FIND_DIALOG_REPLACE_TOOLTIP));
		this.replaceAll.setToolTipText(Text
				.get(Text.FIND_DIALOG_REPLACE_ALL_TOOLTIP));
		this.cancel.setToolTipText(Text.get(Text.FIND_DIALOG_CANCEL_TOOLTIP));

		this.findText.setMinimumSize(new Dimension(100, (int) this.findText
				.getSize().getHeight()));
		this.replaceWithText.setMinimumSize(new Dimension(100,
				(int) this.replaceWithText.getSize().getHeight()));

		JPanel internalPanel = new JPanel();
		GroupLayout layout = new GroupLayout(internalPanel);
		internalPanel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JPanel directionPanel = new JPanel();
		directionPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.FIND_DIALOG_DIRECTIONS_BORDER)));

		directionPanel.setLayout(new GridLayout(2, 1));
		directionPanel.add(this.forward);
		directionPanel.add(this.backward);

		ButtonGroup directionGroup = new ButtonGroup();
		directionGroup.add(this.forward);
		directionGroup.add(this.backward);

		this.forward.setSelected(true);
		this.caseSensitive.setSelected(true);

		JPanel optionsPanel = new JPanel();
		optionsPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.FIND_DIALOG_OPTIONS_BORDER)));
		optionsPanel.setLayout(new GridLayout(2, 1));
		optionsPanel.add(this.caseSensitive);
		optionsPanel.add(this.regularExpression);

		GroupLayout.SequentialGroup horizontalGroup = layout
				.createSequentialGroup();

		GridLayout grid = new GridLayout(2, 2);
		grid.setHgap(5);
		grid.setVgap(5);
		JPanel replaceButtonsPanel = new JPanel(grid);
		replaceButtonsPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.FIND_DIALOG_CONTROLS_BORDER)));

		replaceButtonsPanel.add(this.find);
		replaceButtonsPanel.add(this.replaceFind);
		replaceButtonsPanel.add(this.replace);
		replaceButtonsPanel.add(this.replaceAll);

		JPanel cancelPanel = new JPanel(new BorderLayout());
		cancelPanel.add(this.cancel, BorderLayout.EAST);
		this.referenceTextArea = new JTextArea(title);
		this.referenceTextArea.setBackground(background);
		this.referenceTextArea.setForeground(foreground);
		this.referenceTextArea.setEditable(false);
		this.referenceTextArea.setCaret(SwingUtil.getNonSelectableCaret());
		cancelPanel.add(this.referenceTextArea, BorderLayout.WEST);

		horizontalGroup.addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(this.findLabel)
												.addComponent(
														this.replaceWithLabel))
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(this.findText)
												.addComponent(
														this.replaceWithText)))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(directionPanel)
								.addComponent(optionsPanel))
				.addComponent(replaceButtonsPanel).addComponent(cancelPanel)
				.addComponent(this.message)

		);

		layout.setHorizontalGroup(horizontalGroup);

		GroupLayout.SequentialGroup verticalGroup = layout
				.createSequentialGroup();

		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(this.findLabel).addComponent(this.findText));
		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(this.replaceWithLabel)
				.addComponent(this.replaceWithText));

		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(directionPanel).addComponent(optionsPanel));

		verticalGroup.addComponent(replaceButtonsPanel);

		verticalGroup.addComponent(cancelPanel);

		verticalGroup.addComponent(this.message);

		layout.setVerticalGroup(verticalGroup);

		add(internalPanel);

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		pack();

		setResizable(false);

		int verticalLocation = 0;
		int horizontalLocation = 0;

		int windowHeight = SwingUtil.getScreenHeight();
		int windowWidth = SwingUtil.getScreenWidth();

		int consideredHeight = windowHeight - getSize().height;
		int consideredWidth = windowWidth - getSize().width;

		if (findDialogLocation == FIND_LOCATION.TOP_LEFT) {
			verticalLocation = (int) (consideredHeight * 0.1);
			horizontalLocation = (int) (consideredWidth * 0.1);
		}
		if (findDialogLocation == FIND_LOCATION.BOTTOM_LEFT) {
			verticalLocation = (int) (consideredHeight * 0.9);
			horizontalLocation = (int) (consideredWidth * 0.1);
		}
		if (findDialogLocation == FIND_LOCATION.TOP_RIGHT) {
			verticalLocation = (int) (consideredHeight * 0.1);
			horizontalLocation = (int) (consideredWidth * 0.9);
		}
		if (findDialogLocation == FIND_LOCATION.BOTTOM_RIGHT) {
			verticalLocation = (int) (consideredHeight * 0.9);
			horizontalLocation = (int) (consideredWidth * 0.9);
		}

		if (findDialogLocation == FIND_LOCATION.CENTER) {
			verticalLocation = (int) (consideredHeight * 0.4);
			horizontalLocation = (int) (consideredWidth * 0.4);
		}

		setLocation(horizontalLocation, verticalLocation);

		attachEvents();

	}

	/**
	 * Find the requested text.
	 */
	private void findText() {

		// Reset message.
		this.message.setText(""); //$NON-NLS-1$

		String searchFor = this.findText.getText();

		String textAreaContent = this.textArea.getText();

		if (this.backward.isSelected() && doesSelectionHasContent()) {
			int newCaretPosition = this.textArea.getCaretPosition() - 1;
			if (newCaretPosition < 0) {
				newCaretPosition = 0;
			}
			this.textArea.setCaretPosition(newCaretPosition);
		}

		int caretPosition = this.textArea.getCaretPosition();

		if (this.forward.isSelected()
				&& caretPosition == textAreaContent.length()) {
			this.message.setText(Text
					.get(Text.FIND_DIALOG_MESSAGE_ALREADY_AT_THE_END_OF_FILE));
			pack();
		}

		if (this.backward.isSelected() && caretPosition == 0) {
			this.message
					.setText(Text
							.get(Text.FIND_DIALOG_MESSAGE_ALREADY_AT_THE_BEGINNING_OF_FILE));
			pack();
		}

		if (caretPosition >= 0 && caretPosition <= textAreaContent.length()) {

			String textToBeSearched = new String();

			textToBeSearched = this.forward.isSelected() ? textAreaContent
					.substring(caretPosition) : textAreaContent.substring(0,
					caretPosition);

			int foundPosition = -1;
			int foundPositionRegularExpressionStart = -1;
			int foundPositionRegularExpressionEnd = -1;

			if (this.regularExpression.isSelected()) {

				if (this.forward.isSelected()) {

					try {

						Pattern pattern = Pattern.compile(searchFor);
						Matcher matcher = pattern.matcher(textToBeSearched);

						if (matcher.find()) {
							foundPositionRegularExpressionStart = matcher
									.start();
							foundPositionRegularExpressionEnd = matcher.end();
						}

					} catch (Exception exception) {
						this.message
								.setText(Text
										.get(Text.FIND_DIALOG_MESSAGE_ERROR_ON_REGULAR_EXPRESSION));
						pack();

						return;
					}

				} else {

					int lastFoundPositionRegularExpressionStart = -1;
					int lastFoundPositionRegularExpressionEnd = -1;

					try {

						Pattern pattern = Pattern.compile(searchFor);
						Matcher matcher = pattern.matcher(textToBeSearched);

						do {

							lastFoundPositionRegularExpressionStart = foundPositionRegularExpressionStart;
							lastFoundPositionRegularExpressionEnd = foundPositionRegularExpressionEnd;

							if (matcher
									.find(lastFoundPositionRegularExpressionEnd + 1)) {
								foundPositionRegularExpressionStart = matcher
										.start();
								foundPositionRegularExpressionEnd = matcher
										.end();
							} else {
								foundPositionRegularExpressionStart = -1;
								foundPositionRegularExpressionEnd = -1;
							}

							if (foundPositionRegularExpressionEnd >= textToBeSearched
									.length()) {
								foundPositionRegularExpressionStart = -1;
								foundPositionRegularExpressionEnd = -1;
							}

						} while (foundPositionRegularExpressionStart != -1
								&& foundPositionRegularExpressionEnd != -1);

						if (lastFoundPositionRegularExpressionStart != -1
								&& lastFoundPositionRegularExpressionEnd != -1) {
							foundPositionRegularExpressionStart = lastFoundPositionRegularExpressionStart;
							foundPositionRegularExpressionEnd = lastFoundPositionRegularExpressionEnd;
						}

					} catch (Exception exception) {
						this.message
								.setText(Text
										.get(Text.FIND_DIALOG_MESSAGE_ERROR_ON_REGULAR_EXPRESSION));
						pack();

						return;
					}
				}

			} else {

				if (this.forward.isSelected()) {

					if (this.caseSensitive.isSelected()) {
						foundPosition = textToBeSearched.indexOf(searchFor);
					} else {
						textToBeSearched = textToBeSearched.toUpperCase();
						foundPosition = textToBeSearched.indexOf(searchFor
								.toUpperCase());
					}

				} else {

					int lastFoundIndex = -1;
					do {

						lastFoundIndex = foundPosition;

						if (this.caseSensitive.isSelected()) {
							foundPosition = textToBeSearched.indexOf(searchFor,
									lastFoundIndex + 1);
						} else {
							textToBeSearched = textToBeSearched.toUpperCase();
							foundPosition = textToBeSearched
									.indexOf(searchFor.toUpperCase(),
											lastFoundIndex + 1);
						}

					} while (foundPosition != -1);

					if (lastFoundIndex != -1) {
						foundPosition = lastFoundIndex;
					}
				}
			}

			if (this.regularExpression.isSelected()) {

				if (this.forward.isSelected()) {

					if (foundPositionRegularExpressionStart != -1
							&& foundPositionRegularExpressionEnd != -1) {

						this.textArea.setSelectionStart(caretPosition
								+ foundPositionRegularExpressionStart);
						this.textArea.setSelectionEnd(caretPosition
								+ foundPositionRegularExpressionEnd);
						this.textArea.repaint();
					} else {
						this.textArea.setCaretPosition(this.textArea.getText()
								.length());
						this.message
								.setText(Text
										.get(Text.FIND_DIALOG_MESSAGE_REACHED_END_OF_FILE));
						pack();

						Toolkit.getDefaultToolkit().beep();
					}

				} else {

					if (foundPositionRegularExpressionStart != -1
							&& foundPositionRegularExpressionEnd != -1) {

						this.textArea
								.setSelectionStart(foundPositionRegularExpressionStart);
						this.textArea
								.setSelectionEnd(foundPositionRegularExpressionEnd);
						this.textArea.repaint();
					} else {
						this.textArea.setCaretPosition(0);
						this.message
								.setText(Text
										.get(Text.FIND_DIALOG_MESSAGE_REACHED_BEGINNING_OF_FILE));
						pack();

						Toolkit.getDefaultToolkit().beep();
					}

				}

			} else {

				if (this.forward.isSelected()) {
					if (foundPosition != -1) {
						this.textArea.setSelectionStart(caretPosition
								+ foundPosition);
						this.textArea.setSelectionEnd(caretPosition
								+ foundPosition + searchFor.length());
						this.textArea.repaint();
					} else {
						this.textArea.setCaretPosition(this.textArea.getText()
								.length());
						this.message
								.setText(Text
										.get(Text.FIND_DIALOG_MESSAGE_REACHED_END_OF_FILE));
						pack();

						Toolkit.getDefaultToolkit().beep();
					}
				} else {
					if (foundPosition != -1) {
						this.textArea.setSelectionStart(foundPosition);
						this.textArea.setSelectionEnd(foundPosition
								+ searchFor.length());
						this.textArea.repaint();
					} else {
						this.textArea.setCaretPosition(0);
						this.message
								.setText(Text
										.get(Text.FIND_DIALOG_MESSAGE_REACHED_BEGINNING_OF_FILE));
						pack();

						Toolkit.getDefaultToolkit().beep();
					}
				}
			}
		}
	}

	/**
	 * Request focus to the first text field.
	 */
	public void requestFocusToTextArea() {

		this.findText.requestFocus();
	}

	/**
	 * Set the text on the text area.
	 */
	public void setTextArea(String text) {

		this.findText.setText(text);

		this.findText.setSelectionStart(0);
		this.findText.setSelectionEnd(text.length());
	}

	/**
	 * Select the entire text area.
	 */
	public void selectTextArea() {

		String text = this.findText.getText();

		this.findText.setSelectionStart(0);
		this.findText.setSelectionEnd(text.length());

	}

	/**
	 * Replace the selected text with a new content.
	 */
	private void replaceContent() {

		String replaceWith = this.replaceWithText.getText();

		if (doesSelectionHasContent()) {
			this.textArea.replaceSelection(replaceWith);
		}
	}

	/**
	 * Replace all contents.
	 */
	private void replaceAll() {

		String replaceWith = this.replaceWithText.getText();

		findText();
		while (doesSelectionHasContent()) {
			this.textArea.replaceSelection(replaceWith);
			findText();
		}
	}

	/**
	 * Indicate if the text area has a selection and that selection has a
	 * specific content.
	 * 
	 * @return True if the text area has a selection and that selection has a
	 *         specific content.
	 */
	private boolean doesSelectionHasContent() {

		String content = this.findText.getText();
		String textAreaContent = this.textArea.getText();

		int selectionStart = this.textArea.getSelectionStart();
		int selectionEnd = this.textArea.getSelectionEnd();

		if (selectionStart != selectionEnd) {

			String textSelected = textAreaContent.substring(selectionStart,
					selectionEnd);

			if (this.regularExpression.isSelected()) {

				try {

					Pattern pattern = Pattern.compile(content);
					Matcher matcher = pattern.matcher(textSelected);

					if (matcher.find()) {
						int start = matcher.start();
						int end = matcher.end();

						if (start == 0 && end == textSelected.length()) {
							return true;
						}
					}

				} catch (Exception exception) {
					this.message
							.setText(Text
									.get(Text.FIND_DIALOG_MESSAGE_ERROR_ON_REGULAR_EXPRESSION));
					pack();

					return false;
				}

			} else {

				if (this.caseSensitive.isSelected()) {
					if (content.equals(textSelected)) {
						return true;
					}
				} else {
					if (content.toUpperCase()
							.equals(textSelected.toUpperCase())) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Indicate when the last hide happend due to an ESC key.
	 * 
	 * @return The time.
	 */
	public long getLastHideTime() {

		return this.lastHideEvent;
	}

	/**
	 * Attach the events to buttons.
	 */
	private void attachEvents() {

		// If the enter is pressed on text field than execute the search.
		this.findText.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ENTER) {

					findText();
				}

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		// If the enter is pressed on text field than execute the search.
		this.replaceWithText.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ENTER) {

					findText();
				}

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}
			}
		});

		// The ESC key closes the find dialog.
		this.forward.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		// The ESC key closes the find dialog.
		this.backward.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		// The ESC key closes the find dialog.
		this.caseSensitive.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		// The ESC key closes the find dialog.
		this.regularExpression.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		this.find.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event
			 * .ActionEvent)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void actionPerformed(ActionEvent e) {

				findText();
			}

		});

		// The ESC key closes the find dialog.
		this.find.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		this.replaceFind.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event
			 * .ActionEvent)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void actionPerformed(ActionEvent e) {

				replaceContent();
				findText();
			}

		});

		// The ESC key closes the find dialog.
		this.replaceFind.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		this.replace.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event
			 * .ActionEvent)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void actionPerformed(ActionEvent e) {

				replaceContent();

			}
		});

		// The ESC key closes the find dialog.
		this.replace.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		this.replaceAll.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event
			 * .ActionEvent)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void actionPerformed(ActionEvent e) {

				replaceAll();
			}

		});

		// The ESC key closes the find dialog.
		this.replaceAll.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		this.cancel.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event
			 * .ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});

		// The ESC key closes the find dialog.
		this.cancel.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

		this.regularExpression.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event
			 * .ActionEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {

				if (FindDialog.this.regularExpression.isSelected()) {
					FindDialog.this.previousCaseSensitiveStatus = FindDialog.this.caseSensitive
							.isSelected();
					FindDialog.this.caseSensitive.setSelected(true);
					FindDialog.this.caseSensitive.setEnabled(false);
				} else {
					FindDialog.this.caseSensitive
							.setSelected(FindDialog.this.previousCaseSensitiveStatus);
					FindDialog.this.caseSensitive.setEnabled(true);
				}
			}

		});

		// The ESC key closes the find dialog.
		this.referenceTextArea.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					FindDialog.this.lastHideEvent = event.getWhen();

					FindDialog.this.findDialog.setVisible(false);
				}

			}
		});

	}
}