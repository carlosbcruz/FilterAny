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
import java.awt.Component;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.EventHelperAdapter;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.FileDialogs;
import com.carlosbcruz.util.FileSupport;
import com.carlosbcruz.util.LinePainter;
import com.carlosbcruz.util.Serialization;
import com.carlosbcruz.util.SimpleActionDecorator;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionProvider;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.SimpleFileExtension;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Represents a full text area.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextAreaPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private String title = new String();

	// Status information.
	private JLabel statusLabel = new JLabel(Text.get(Text.TEXT_INITIALIZED));
	private JLabel positionLabel = new JLabel();
	private JLabel memoryLabel = new JLabel("0/0"); //$NON-NLS-1$
	private JLabel reportLabel = new JLabel();

	// Text area components.
	JTextArea textArea = null;
	private Color caretColor = null;
	private Color xorColor = null;

	private JScrollPane textAreaScrollPane = null;
	private TextNavigation textNavigation = null;
	private TextLineNumber textLineNumber = null;

	// Memory actions.
	private static SimpleActionDecorator addAction;
	private static SimpleActionDecorator previousAction;
	private static SimpleActionDecorator nextAction;
	private static SimpleActionDecorator clearAction;

	// Tabulation manipulation
	private static SimpleActionDecorator removeTabulationAction;
	private static SimpleActionDecorator addTabulationAction;

	// Text information.
	private static SimpleActionDecorator textInformationAction;

	// Create buttons.
	private JButton addButtom = null;
	private JButton previousButtom = null;
	private JButton nextButtom = null;
	private JButton clearButtom = null;

	private JButton miningButton = null;
	private static SimpleActionDecorator miningAction = null;

	private JButton informationButton = null;

	private JButton formattingEditorButton = null;
	private static SimpleActionDecorator formattingEditorAction = null;

	private JButton removeTabulationButton = null;
	private JButton addTabulationButton = null;

	// Clear button.
	private JButton clearContentButtom = null;
	private static SimpleActionDecorator clearContentAction;

	// Table components.
	private FilterTableModel tableModel = null;
	private FilterTable filterTable = null;
	private JScrollPane tableScrollPane = null;

	// Indicate if the component content is saved.
	private boolean textAreaSaved = true;

	// Manages the internal memory.
	private ArrayList<String> internalMemory = new ArrayList<>();
	private int currentMemory = -1;

	// The component where the component is being used
	// to be able to position the alerts and dialogs correctly.
	private Component mainComponent = null;

	// Mouse events on this component.
	private EventHelperAdapter textAreaMouseEvents = new EventHelperAdapter();

	// Control if the events are enabled or not.
	private boolean enabled = true;

	// Location of the find dialog.
	public enum FIND_LOCATION {
		TOP_LEFT, BOTTOM_LEFT, TOP_RIGHT, BOTTOM_RIGHT, CENTER
	}

	private FIND_LOCATION findDialogLocation = FIND_LOCATION.TOP_LEFT;

	// The find dialog.
	private FindDialog findDialog = null;

	// The colors.
	private Color foreground = null;
	private Color background = null;
	private Color selectedTextColor = null;
	private Color selectionBackgroundColor = null;
	private Color hightLightColor = null;
	// Listener to the escape key.
	EscapeKeyListener escapeKeyListener = null;

	// Undo helpers
	private UndoAction undoAction;
	private RedoAction redoAction;
	private UndoManager undo = new UndoManager();

	// Indicate if the area is in text or file mode.
	private boolean textMode = true;

	// Instance of the go to dialog.
	private GoToLineDialog goToDialog = null;

	/**
	 * The default constructor.
	 * 
	 * @param mainComponent
	 *            the frame where this component is used.
	 * @param title
	 *            the title of the area.
	 * @param backgroundParameter
	 *            The color used to draw the background.
	 * @param hightLightColorParameter
	 *            The color used to hightlight the selected line.
	 * @param foregroundParameter
	 *            The color used to write the text.
	 * @param selectionColor
	 *            The color of the selected lines.
	 * @param normalLineColor
	 *            The color of the line number.
	 * @param currentLineColor
	 *            The color of the selected line number.
	 * @param selectedTextColorParameter
	 *            The text color on the selected text.
	 * @param selectionBackgroundColorParameter
	 *            The background color on the selected text.
	 * @param caretColor
	 *            The insert caret color.
	 * @param xorColor
	 *            The insert xor caret color.
	 * @param findLocationParameter
	 *            Location of the find dialog.
	 * @param showClearText
	 *            Indicate if it is necessary to show the clear text.
	 */
	public TextAreaPanel(Component mainComponent, String title,
			Color backgroundParameter, Color hightLightColorParameter,
			Color foregroundParameter, Color selectionColor,
			Color normalLineColor, Color currentLineColor,
			Color selectedTextColorParameter,
			Color selectionBackgroundColorParameter, Color caretColor,
			Color xorColor, FIND_LOCATION findLocationParameter,
			boolean showClearText) {

		this.mainComponent = mainComponent;

		this.title = title;
		this.findDialogLocation = findLocationParameter;

		this.background = backgroundParameter;
		this.foreground = foregroundParameter;

		this.selectedTextColor = selectedTextColorParameter;
		this.selectionBackgroundColor = selectionBackgroundColorParameter;

		this.caretColor = caretColor;
		this.xorColor = xorColor;

		this.textArea = getTextAreaInstance();

		addAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "add-icon-small.png", null, 0, Text //$NON-NLS-1$
						.get(Text.ADD_MEMORY));
		previousAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "previous-icon-small.png", null, 0, Text //$NON-NLS-1$
						.get(Text.ADD_PREVIOUS));
		nextAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "next-icon-small.png", null, 0, Text //$NON-NLS-1$
						.get(Text.ADD_NEXT));
		clearAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "clear-icon-small.png", null, 0, Text //$NON-NLS-1$
						.get(Text.ADD_REMOVE));

		removeTabulationAction = SimpleActionProvider
				.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "TabulationLeft.png", "", 0, Text //$NON-NLS-1$ //$NON-NLS-2$
						.get(Text.REMOVE_TABULATION_INFORMATION_INSTRUCTION));
		addTabulationAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "TabulationRight.png", "", 0, Text //$NON-NLS-1$ //$NON-NLS-2$
						.get(Text.ADD_TABULATION_INFORMATION_INSTRUCTION));

		textInformationAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "info-icon.png", "", //$NON-NLS-1$ //$NON-NLS-2$
				0, Text.get(Text.TEXT_AREA_INFORMATION_INSTRUCTION));

		clearContentAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "clear-icon.png", //$NON-NLS-1$
				null, 0, Text.get(Text.CLEAR_CONTENT_BUTTON_INSTRUCTIONS));

		formattingEditorAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "editor-black.png", //$NON-NLS-1$
				null, 0, Text.get(Text.FORMATTING_EDITOR_BUTTON_INSTRUCTIONS));

		miningAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "magnifyingGlassIcon.png", //$NON-NLS-1$
				null, 0, Text.get(Text.MINING_BUTTON_INSTRUCTIONS));

		setLayout(new BorderLayout());

		this.hightLightColor = hightLightColorParameter;

		instantiateLinePainter(this.hightLightColor);

		this.textArea.setBackground(this.background);
		this.textArea.setForeground(this.foreground);

		this.textArea.setSelectedTextColor(this.selectedTextColor);
		this.textArea.setSelectionColor(this.selectionBackgroundColor);

		this.textArea.setFont(FilterAnyConfiguration.getTextAreaFont());
		this.textAreaScrollPane = new JScrollPane(this.textArea);

		this.textNavigation = new TextNavigation(this.textAreaScrollPane,
				this.textArea, selectionColor);
		this.textLineNumber = new TextLineNumber(this.textArea,
				FilterAnyConfiguration.getTextAreaFont(), normalLineColor,
				currentLineColor);

		this.undoAction = new UndoAction();
		JButton undoButton = new JButton(this.undoAction);
		undoButton.setBorderPainted(false);
		undoButton.setOpaque(false);
		undoButton.setContentAreaFilled(false);
		undoButton.setToolTipText(Text.get(Text.UNDO_TOOLTIP));
		undoButton.setMnemonic(SwingUtil.getKeyEvent(Text
				.get(Text.UNDO_TOOLTIP_KEY)));
		undoButton.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());

		this.redoAction = new RedoAction();
		JButton redoButton = new JButton(this.redoAction);
		redoButton.setBorderPainted(false);
		redoButton.setOpaque(false);
		redoButton.setContentAreaFilled(false);

		redoButton.setToolTipText(Text.get(Text.REDO_TOOLTIP));
		redoButton.setMnemonic(SwingUtil.getKeyEvent(Text
				.get(Text.REDO_TOOLTIP_KEY)));
		redoButton.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());

		Document document = this.textArea.getDocument();
		document.addUndoableEditListener(new TextAreaUndoableEditListener());

		JPanel topRightPanel = new JPanel();

		this.removeTabulationButton = new JButton(removeTabulationAction);
		this.removeTabulationButton.setBorderPainted(false);
		this.removeTabulationButton.setOpaque(false);
		this.removeTabulationButton.setContentAreaFilled(false);
		this.removeTabulationButton.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());
		topRightPanel.add(this.removeTabulationButton);

		this.addTabulationButton = new JButton(addTabulationAction);
		this.addTabulationButton.setBorderPainted(false);
		this.addTabulationButton.setOpaque(false);
		this.addTabulationButton.setContentAreaFilled(false);
		this.addTabulationButton.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());
		topRightPanel.add(this.addTabulationButton);

		this.formattingEditorButton = new JButton(formattingEditorAction);

		this.formattingEditorButton.setBorderPainted(false);
		this.formattingEditorButton.setOpaque(false);
		this.formattingEditorButton.setContentAreaFilled(false);
		this.formattingEditorButton.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());

		if (!FilterAnyConfiguration.isGeneralLevelUser()) {

			topRightPanel.add(this.formattingEditorButton);
		}

		topRightPanel.add(undoButton);
		topRightPanel.add(redoButton);

		this.miningButton = new JButton(miningAction);
		this.miningButton.setBorderPainted(false);
		this.miningButton.setOpaque(false);
		this.miningButton.setContentAreaFilled(false);
		this.miningButton.setToolTipText(Text.get(Text.MINING_TOOLTIP));
		this.miningButton.setMnemonic(SwingUtil.getKeyEvent(Text
				.get(Text.MINING_TOOLTIP_KEY)));
		this.miningButton.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());

		topRightPanel.add(this.miningButton);

		if (this.findDialogLocation != FIND_LOCATION.CENTER) {

			JPanel topMessage = new JPanel(new BorderLayout());
			topMessage.add(this.statusLabel, BorderLayout.WEST);

			this.informationButton = new JButton(textInformationAction);
			this.informationButton.setBorderPainted(false);
			this.informationButton.setOpaque(false);
			this.informationButton.setContentAreaFilled(false);
			this.informationButton.setPreferredSize(FilterAnyConfiguration
					.getSmallButtonsDimension());

			topRightPanel.add(this.informationButton);

			topMessage.add(topRightPanel, BorderLayout.EAST);
			add(topMessage, BorderLayout.NORTH);
		}
		add(this.textNavigation, BorderLayout.EAST);
		add(this.textLineNumber, BorderLayout.WEST);
		add(this.textAreaScrollPane, BorderLayout.CENTER);

		// Construct the file table.
		this.tableModel = new FilterTableModel();
		this.filterTable = new FilterTable(this.tableModel);
		this.tableScrollPane = new JScrollPane(this.filterTable);

		// Do not loose the selection during a focus lost.
		// textArea.setCaret(getCaret());

		// Create the memory buttons panel.
		JPanel buttonsPanel = new JPanel();

		// Create buttons.
		this.addButtom = new JButton(addAction);
		this.previousButtom = new JButton(previousAction);
		this.nextButtom = new JButton(nextAction);
		this.clearButtom = new JButton(clearAction);

		// Remove borders.
		this.addButtom.setBorderPainted(false);
		this.addButtom.setOpaque(false);
		this.addButtom.setContentAreaFilled(false);

		this.previousButtom.setBorderPainted(false);
		this.previousButtom.setOpaque(false);
		this.previousButtom.setContentAreaFilled(false);

		this.nextButtom.setBorderPainted(false);
		this.nextButtom.setOpaque(false);
		this.nextButtom.setContentAreaFilled(false);

		this.clearButtom.setBorderPainted(false);
		this.clearButtom.setOpaque(false);
		this.clearButtom.setContentAreaFilled(false);

		// Set sizes.
		this.addButtom.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());
		this.previousButtom.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());
		this.nextButtom.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());
		this.clearButtom.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());

		if (this.findDialogLocation != FIND_LOCATION.CENTER) {
			// Add buttons.
			buttonsPanel.add(this.addButtom);
			buttonsPanel.add(this.previousButtom);
			this.memoryLabel.setToolTipText(Text.get(Text.MEMORY_LABEL));
			buttonsPanel.add(this.memoryLabel);
			buttonsPanel.add(this.nextButtom);
			buttonsPanel.add(this.clearButtom);
		}

		// Create status bar.
		JPanel statusBar = new JPanel(new BorderLayout());

		JPanel positionPanel = new JPanel();
		this.clearContentButtom = new JButton(clearContentAction);

		// Clear button border.
		this.clearContentButtom.setBorderPainted(false);
		this.clearContentButtom.setOpaque(false);
		this.clearContentButtom.setContentAreaFilled(false);

		this.clearContentButtom.setPreferredSize(FilterAnyConfiguration
				.getSmallButtonsDimension());
		positionPanel.add(this.clearContentButtom);
		positionPanel.add(this.positionLabel);

		statusBar.add(positionPanel, BorderLayout.WEST);

		JPanel centerMessage = new JPanel(new FlowLayout(FlowLayout.CENTER));
		centerMessage.add(this.reportLabel);
		statusBar.add(centerMessage, BorderLayout.CENTER);

		if (showClearText) {

			if (this.findDialogLocation == FIND_LOCATION.CENTER) {
				statusBar.add(topRightPanel, BorderLayout.EAST);
			} else {
				statusBar.add(buttonsPanel, BorderLayout.EAST);
			}

			add(statusBar, BorderLayout.SOUTH);
		}

		// Add the popup menu listener.
		this.textArea.addMouseListener(this.textAreaMouseEvents);

		// The ESC key closes the find dialog.
		this.textArea.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					if (TextAreaPanel.this.escapeKeyListener != null) {
						TextAreaPanel.this.escapeKeyListener.escapeKeyPressed();
					}
				}

			}
		});

		attachEvents();

		InputMap inputMap = this.textArea.getInputMap();

		// Ctrl-Z triggers the undo action.
		KeyStroke undoKey = KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				Event.CTRL_MASK);
		inputMap.put(undoKey, this.undoAction);

		// Ctrl-Y triggers the undo action.
		KeyStroke redoKey = KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				Event.CTRL_MASK);
		inputMap.put(redoKey, this.redoAction);

		// Ctrl-I triggers the information action.
		KeyStroke informationKey = KeyStroke.getKeyStroke(KeyEvent.VK_I,
				Event.CTRL_MASK);
		inputMap.put(informationKey, textInformationAction);

		// Ctrl-D triggers the formatted editor action.
		KeyStroke formattedKey = KeyStroke.getKeyStroke(KeyEvent.VK_D,
				Event.CTRL_MASK);
		inputMap.put(formattedKey, formattingEditorAction);

	}

	/**
	 * Set the action that triggers the go to line.
	 * 
	 * @param goToAction
	 *            The go to line action.
	 */
	public void setGoToLineAction(Action goToAction) {

		InputMap inputMap = this.textArea.getInputMap();

		// Ctrl-Z triggers the undo action.
		KeyStroke undoKey = KeyStroke.getKeyStroke(KeyEvent.VK_G,
				Event.CTRL_MASK);
		inputMap.put(undoKey, goToAction);

	}

	/**
	 * Set the action that triggers the go to line on the formatted editor.
	 * 
	 * @param goToAction
	 *            The go to line action.
	 */
	public void setGoToLineOnFormattedEditorAction(Action goToAction) {

		return;
	}

	/**
	 * Set the action that triggers the find dialog on the formatted editor.
	 * 
	 * @param findAction
	 *            The find action.
	 */
	public void setFindOnFormattedEditorAction(Action findAction) {

		return;
	}

	/**
	 * Provide the text area for initialization.
	 * 
	 * @return the text area.
	 */
	JTextArea getTextAreaInstance() {

		return new JTextArea();
	}

	/**
	 * Can be called to indicate that the text area is being closed.
	 */
	public void closingTextArea() {
		// Do nothing.
	}

	/**
	 * Provide the caret.
	 * 
	 * @return The caret.
	 */
	@SuppressWarnings("static-method")
	Caret getCaret() {

		return new DefaultCaret() {

			private static final long serialVersionUID = 1L;

			@Override
			public void focusLost(FocusEvent event) {
				// No implementation.
			}

		};
	}

	/**
	 * This class listens for edits that can be undone.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	protected class TextAreaUndoableEditListener implements
			UndoableEditListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.UndoableEditListener#undoableEditHappened(javax
		 * .swing.event.UndoableEditEvent)
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public void undoableEditHappened(UndoableEditEvent event) {

			// Remember the edit and update the menus.
			TextAreaPanel.this.undo.addEdit(event.getEdit());

			TextAreaPanel.this.undoAction.updateUndoState();
			TextAreaPanel.this.redoAction.updateRedoState();
		}
	}

	/**
	 * Coordinate the undo actions.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class UndoAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		/**
		 * Constructor.
		 */
		public UndoAction() {

			super("", SwingUtil //$NON-NLS-1$
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "undo-icon.png")); //$NON-NLS-1$

			setEnabled(false);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				TextAreaPanel.this.undo.undo();

			} catch (CannotUndoException exception) {

				ExceptionSupport.handleException(Text.get(
						Text.UNDO_TOOLTIP_EXCEPTION, exception.getMessage()));
				return;
			}

			updateUndoState();

			TextAreaPanel.this.redoAction.updateRedoState();
		}

		/**
		 * Update the undo state.
		 */
		@SuppressWarnings("synthetic-access")
		protected void updateUndoState() {

			if (TextAreaPanel.this.undo.canUndo()) {

				setEnabled(true);

			} else {

				setEnabled(false);
			}
		}
	}

	/**
	 * Coordinate the redo actions.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class RedoAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		/**
		 * Constructor.
		 */
		public RedoAction() {

			super("", SwingUtil //$NON-NLS-1$
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "redo-icon.png")); //$NON-NLS-1$

			setEnabled(false);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				TextAreaPanel.this.undo.redo();

			} catch (CannotRedoException exception) {

				ExceptionSupport.handleException(Text.get(
						Text.REDO_TOOLTIP_EXCEPTION, exception.getMessage()));
				return;
			}

			updateRedoState();

			TextAreaPanel.this.undoAction.updateUndoState();
		}

		@SuppressWarnings("synthetic-access")
		protected void updateRedoState() {

			if (TextAreaPanel.this.undo.canRedo()) {

				setEnabled(true);

			} else {

				setEnabled(false);
			}
		}
	}

	/**
	 * Clear history.
	 */
	public void clearHistory() {

		this.undo.discardAllEdits();

		this.undoAction.setEnabled(false);
		this.redoAction.setEnabled(false);

	}

	/**
	 * Highlight the current line.
	 * 
	 * @param hightLightColorParameter
	 *            the high light color.
	 */
	@SuppressWarnings("unused")
	void instantiateLinePainter(Color hightLightColorParameter) {

		new LinePainter(this.textArea, hightLightColorParameter);
	}

	/**
	 * Show a Go To Line dialog.
	 */
	public void showGoToLineDialog() {

		if (this.goToDialog == null) {

			this.goToDialog = new GoToLineDialog(this, this.textArea);
		}

		this.goToDialog.positionOnTheScreen();

		this.goToDialog.setVisible(true);
	}

	/**
	 * Show the find dialog.
	 */
	public void showFindDialog() {

		if (this.findDialog == null) {
			this.findDialog = new FindDialog(Text.get(Text.FIND_DIALOG_TITLE,
					this.title), this.background, this.foreground,
					this.findDialogLocation, this.textArea);
		}

		this.findDialog.setVisible(true);

		this.findDialog.requestFocusToTextArea();

		// Verify if the selection can be copied to the find field.
		int selectionStart = this.textArea.getSelectionStart();
		int selectionEnd = this.textArea.getSelectionEnd();
		String selection = null;
		if (selectionEnd > selectionStart) {

			String analysisSelection = this.textArea.getText().substring(
					selectionStart, selectionEnd);
			boolean hasNewLine = false;
			for (int i = 0; i < analysisSelection.length(); i++) {
				if (analysisSelection.charAt(i) == '\n') {
					hasNewLine = true;
				}
			}
			if (!hasNewLine) {
				selection = analysisSelection;
			}
		}

		// Set the field text or only select the current text.
		if (selection != null) {
			this.findDialog.setTextArea(selection);
		} else {
			this.findDialog.selectTextArea();
		}

	}

	/**
	 * Show the find dialog on the formatted editor.
	 */
	public void showFindDialogOnFormattedEditor() {

		ExceptionSupport.handleException("Formatted editor only on full version/Editor formatado somente na versão completa.");
	}

	/**
	 * Show a Go To Line dialog.
	 */
	public void showGoToLineDialogOnFormattedEditor() {

		ExceptionSupport.handleException("Formatted editor only on full version/Editor formatado somente na versão completa.");
	}

	/**
	 * Attach all events.
	 */
	private void attachEvents() {

		// Updates the label if caret position changes.
		this.textArea.addCaretListener(new CaretListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void caretUpdate(CaretEvent arg0) {

				if (TextAreaPanel.this.enabled) {
					updateCaretLabel();
				}
			}
		});

		// Update the label when text area is changed.
		this.textArea.addKeyListener(new KeyListener() {

			/**
			 * Clear the status bar.
			 */
			@SuppressWarnings("synthetic-access")
			private void clearLabel() {

				setStatusText(Text.get(Text.TEXT_MODIFIED));
				TextAreaPanel.this.textAreaSaved = false;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyTyped(KeyEvent arg0) {

				clearLabel();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyReleased(KeyEvent arg0) {

				clearLabel();

				repaintSupportInformation();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent arg0) {

				// Show the find dialog.
				if (arg0.isControlDown()
						&& arg0.getKeyCode() == SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_FIND_SHORT_CUT_KEY))) {

					showFindDialog();
				}

				if (TextAreaPanel.this.findDialogLocation == FIND_LOCATION.CENTER) {

					return;
				}

				if (arg0.isControlDown()
						&& arg0.getKeyCode() == SwingUtil.getKeyEvent(Text
								.get(Text.TEXT_AREA_SAVE_SHORT_CUT_KEY))) {

					// TODO Implement save top
				}

				clearLabel();
			}
		});

		// Update the line number.
		this.textArea.addMouseListener(new MouseAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void mouseClicked(MouseEvent e) {

				TextAreaPanel.this.textLineNumber.repaint();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent
			 * )
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void mouseReleased(MouseEvent e) {

				TextAreaPanel.this.textLineNumber.repaint();
			}

		});

		// Show text information.
		textInformationAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/**
			 * @param simpleSubject
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				String selectedText = TextAreaPanel.this.textArea
						.getSelectedText();

				if (selectedText == null) {
					selectedText = new String();
				}

				TextInformationController instance = TextInformationController
						.getInstance();

				instance.createTextInformation(
						TextAreaPanel.this.title,
						new StringBuffer(TextAreaPanel.this.textArea.getText()),
						new StringBuffer(selectedText));
			}
		});

		// Add the observer to the add memory button.
		addAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz.util.SimpleActionSubject)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				// If it is on text mode than work with text areas.
				if (FilterAnyConfiguration.isTextMode()) {

					String text = TextAreaPanel.this.textArea.getText();
					TextAreaPanel.this.internalMemory.add(text);
					TextAreaPanel.this.textArea.setText(null);

				} else {

					// Work with the tables.
					String text = TextAreaPanel.this.tableModel.exportContent();
					TextAreaPanel.this.internalMemory.add(text);
					TextAreaPanel.this.tableModel.setRows(FilterAnyLogic
							.generateTableModel("")); //$NON-NLS-1$
					TextAreaPanel.this.filterTable.revalidate();
					TextAreaPanel.this.filterTable.repaint();

				}

				TextAreaPanel.this.currentMemory = 0;
				TextAreaPanel.this.memoryLabel
						.setText("1/" + TextAreaPanel.this.internalMemory.size()); //$NON-NLS-1$
			}
		});

		// Navigate to the previous memory.
		previousAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz.util.SimpleActionSubject)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				if (TextAreaPanel.this.internalMemory.isEmpty()) {

					return;
				}

				TextAreaPanel.this.currentMemory--;

				if (TextAreaPanel.this.currentMemory == -1) {

					TextAreaPanel.this.currentMemory = TextAreaPanel.this.internalMemory
							.size() - 1;
				}

				String text = TextAreaPanel.this.internalMemory
						.get(TextAreaPanel.this.currentMemory);

				// If it is on text mode than work with text areas.
				if (FilterAnyConfiguration.isTextMode()) {

					TextAreaPanel.this.textArea.setText(text);

				} else {

					// Work with the tables.
					TextAreaPanel.this.tableModel.setRows(FilterAnyLogic
							.generateTableModel(text));
					TextAreaPanel.this.filterTable.revalidate();
					TextAreaPanel.this.filterTable.repaint();

				}

				TextAreaPanel.this.memoryLabel
						.setText("" + (TextAreaPanel.this.currentMemory + 1) + "/" //$NON-NLS-1$ //$NON-NLS-2$
								+ TextAreaPanel.this.internalMemory.size());
			}
		});

		// Navigate to the next memory.
		nextAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				if (TextAreaPanel.this.internalMemory.isEmpty()) {

					return;
				}

				TextAreaPanel.this.currentMemory++;

				if (TextAreaPanel.this.currentMemory == TextAreaPanel.this.internalMemory
						.size()) {
					TextAreaPanel.this.currentMemory = 0;
				}

				String text = TextAreaPanel.this.internalMemory
						.get(TextAreaPanel.this.currentMemory);

				// If it is on text mode than work with text areas.
				if (FilterAnyConfiguration.isTextMode()) {

					TextAreaPanel.this.textArea.setText(text);

				} else {

					// Work with the tables.
					TextAreaPanel.this.tableModel.setRows(FilterAnyLogic
							.generateTableModel(text));
					TextAreaPanel.this.filterTable.revalidate();
					TextAreaPanel.this.filterTable.repaint();
				}

				TextAreaPanel.this.memoryLabel
						.setText("" + (TextAreaPanel.this.currentMemory + 1) + "/" //$NON-NLS-1$ //$NON-NLS-2$
								+ TextAreaPanel.this.internalMemory.size());
			}
		});

		// Remove the current memory.
		clearAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				if (TextAreaPanel.this.internalMemory.isEmpty()) {

					return;
				}

				TextAreaPanel.this.internalMemory
						.remove(TextAreaPanel.this.currentMemory);

				if (TextAreaPanel.this.currentMemory == TextAreaPanel.this.internalMemory
						.size()) {

					TextAreaPanel.this.currentMemory--;
				}

				TextAreaPanel.this.memoryLabel
						.setText("" + (TextAreaPanel.this.currentMemory + 1) + "/" //$NON-NLS-1$ //$NON-NLS-2$
								+ TextAreaPanel.this.internalMemory.size());
			}
		});

		// Clear the text content.
		clearContentAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				TextAreaPanel.this.textArea.setText(""); //$NON-NLS-1$

				if (FilterAnyConfiguration.isTextMode()) {

					repaintSupportInformation();

				} else {

					TextAreaPanel.this.tableModel.clearContent();
				}
			}
		});

		// Event for removing a tabulation to the selected text.
		removeTabulationAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				if (FilterAnyConfiguration.isTextMode()) {

					StringBuffer content = new StringBuffer(
							TextAreaPanel.this.textArea.getText());

					int selectionStart = TextAreaPanel.this.textArea
							.getSelectionStart();
					int selectionEnd = TextAreaPanel.this.textArea
							.getSelectionEnd();

					int lastSpacePosition = -1;
					int charactersRemoved = 0;
					int currentPosition = 0;
					for (int i = selectionEnd, counter = 1; i > selectionStart; i--, counter++) {

						currentPosition = selectionEnd - counter;
						char thisCharacter = content.charAt(currentPosition);

						if ((thisCharacter == StringSupport.getJavaNewLine())
								&& lastSpacePosition != -1) {
							TextAreaPanel.this.textArea
									.replaceRange(
											"", lastSpacePosition, lastSpacePosition + 1); //$NON-NLS-1$
							charactersRemoved++;
						}

						if (thisCharacter == ' ') {
							lastSpacePosition = currentPosition;
						} else {
							lastSpacePosition = -1;
						}
					}

					if (currentPosition == 0 && lastSpacePosition != -1) {
						TextAreaPanel.this.textArea.replaceRange(
								"", lastSpacePosition, lastSpacePosition + 1); //$NON-NLS-1$
						charactersRemoved++;
					}

					TextAreaPanel.this.textArea
							.setSelectionStart(selectionStart);
					TextAreaPanel.this.textArea.setSelectionEnd(selectionEnd
							- charactersRemoved);

					TextAreaPanel.this.textArea.requestFocus();
				}
			}
		});

		// Event for adding a tabulation to the selected text.
		addTabulationAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				if (FilterAnyConfiguration.isTextMode()) {

					StringBuffer content = new StringBuffer(
							TextAreaPanel.this.textArea.getText());

					int selectionStart = TextAreaPanel.this.textArea
							.getSelectionStart();
					int selectionEnd = TextAreaPanel.this.textArea
							.getSelectionEnd();

					int numberOfCharacters = selectionEnd - selectionStart;

					int numberOfInserts = 0;

					if (selectionStart == 0) {
						TextAreaPanel.this.textArea.insert(" ", 0); //$NON-NLS-1$
						numberOfInserts++;
					}

					for (int i = 0; i < numberOfCharacters
							&& selectionStart + i < selectionEnd; i++) {
						if (content.charAt(selectionStart + i) == StringSupport
								.getJavaNewLine()) {
							TextAreaPanel.this.textArea
									.insert(" ", selectionStart + i + numberOfInserts + 1); //$NON-NLS-1$
							numberOfInserts++;
						}
					}
					TextAreaPanel.this.textArea
							.setSelectionStart(selectionStart);
					TextAreaPanel.this.textArea.setSelectionEnd(selectionEnd
							+ numberOfInserts);
					TextAreaPanel.this.textArea.requestFocus();
				}
			}
		});

		// Event for the formatting editor.
		formattingEditorAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				ExceptionSupport.handleException("Formatted editor only on full version/Editor formatado somente na versão completa.");
			}
		});

		// Event for the multiple search.
		miningAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@SuppressWarnings({ "synthetic-access", "unused" })
			@Override
			public void update(SimpleActionSubject simpleSubject) {

				String text = TextAreaPanel.this.textArea.getText();
				String[] lines = StringSupport
						.breakContentInLines(new StringBuffer(text));
				new TextMiningWindow(lines,
						TextAreaPanel.this.background,
						TextAreaPanel.this.foreground);

			}
		});

	}

	/**
	 * Set the status text.
	 * 
	 * @param text
	 *            The status text.
	 */
	public void setStatusText(String text) {

		this.statusLabel.setText(text);
	}

	/**
	 * Inform the content of the text area.
	 * 
	 * @return The content of the text area.
	 */
	public String getTextArea() {

		return this.textArea.getText();
	}

	/**
	 * Set the content of the text area.
	 * 
	 * @param text
	 *            The content of the text area.
	 */
	public void setTextArea(String text) {

		this.textArea.setText(text);
	}

	/**
	 * Inform the start of the selection area.
	 * 
	 * @return The start of the selection area.
	 */
	public int getTextStartSelection() {

		return this.textArea.getSelectionStart();
	}

	/**
	 * Set the position of the caret.
	 * 
	 * @param position
	 *            The position of the caret.
	 */
	public void setCaretPosition(int position) {

		this.textArea.setCaretPosition(position);
	}

	/**
	 * Cut the selected text.
	 * 
	 * @return The selected text.
	 */
	public String cut() {

		int start = this.textArea.getSelectionStart();
		int end = this.textArea.getSelectionEnd();

		if (start < end) {
			String text = new String();
			try {
				text = this.textArea.getText(start, end - start);

				String textBefore = this.textArea.getText(0, start);
				String textAfter = this.textArea.getText(end, this.textArea
						.getText().length() - end);

				this.textArea.setText(textBefore + textAfter);
				this.textArea.setCaretPosition(start);

			} catch (BadLocationException e) {
				// Do nothing.
			}
			return text;
		}

		return new String();
	}

	/**
	 * Copy the selected text.
	 * 
	 * @return The selected text.
	 */
	public String copy() {

		int start = this.textArea.getSelectionStart();
		int end = this.textArea.getSelectionEnd();

		if (start < end) {

			try {
				return this.textArea.getText(start, end - start);

			} catch (BadLocationException e) {
				// Do nothing.
			}
		}

		return new String();
	}

	/**
	 * Paste the text on the cursor location.
	 * 
	 * @param text
	 *            The text to be pasted.
	 */
	public void paste(String text) {

		int caret = this.textArea.getCaretPosition();

		String previousText = this.textArea.getText();

		String newText = new String();

		// Only inserted mode on open source version.
		if (true) {

			if (text.length() < previousText.substring(caret).length()) {
				newText = previousText.substring(0, caret) + text
						+ previousText.substring(caret + text.length());

			} else {
				newText = previousText.substring(0, caret) + text;
			}

		} else {
			newText = previousText.substring(0, caret) + text
					+ previousText.substring(caret);
		}

		int start = this.textArea.getSelectionStart();
		int end = this.textArea.getSelectionEnd();
		if (start < end) {
			try {
				String textBefore = this.textArea.getText(0, start);
				String textAfter = this.textArea.getText(end, this.textArea
						.getText().length() - end);

				this.textArea.setText(textBefore + text + textAfter);
				this.textArea.setCaretPosition(textBefore.length()
						+ text.length());

			} catch (BadLocationException e) {
				// Do nothing.
			}
		} else {
			this.textArea.setText(newText);
			this.textArea.setCaretPosition(caret + text.length());
		}

	}

	/**
	 * Inform the caret position.
	 * 
	 * @return The caret position.
	 */
	public int getCaretPosition() {

		return this.textArea.getCaretPosition();
	}

	/**
	 * Inform the end of the selection area.
	 * 
	 * @return The end of the selection area.
	 */
	public int getTextEndSelection() {

		return this.textArea.getSelectionEnd();
	}

	/**
	 * Provide the content of the table model.
	 * 
	 * @return The content of the table model.
	 */
	public String exportTableModel() {

		return this.tableModel.exportContent();
	}

	/**
	 * Set the rows of the table model.
	 * 
	 * @param rows
	 *            The rows of the table model.
	 */
	public void setRows(ArrayList<FileOrURLBean> rows) {

		this.tableModel.setRows(rows);
	}

	/**
	 * Inform the rows of the table model.
	 * 
	 * @return The rows of the table model.
	 */
	public ArrayList<FileOrURLBean> getRows() {

		return this.tableModel.getRows();
	}

	/**
	 * Adapt the table to a nice layout according to content.
	 */
	public void packTable() {

		this.filterTable.adaptLayout();
		this.filterTable.revalidate();
		this.filterTable.repaint();
	}

	/**
	 * Switch from text to table mode.
	 */
	public void switchToTable() {

		this.textMode = false;

		// Remove the text objects from the text area.
		remove(this.textNavigation);
		remove(this.textAreaScrollPane);
		remove(this.textLineNumber);

		// Add the file objects.
		add(this.tableScrollPane, BorderLayout.CENTER);
	}

	/**
	 * Switch from table to text mode.
	 */
	public void swithToTextArea() {

		this.textMode = true;

		// Remove the table components.
		remove(this.tableScrollPane);

		// Add the text navigation.
		add(this.textNavigation, BorderLayout.EAST);
		add(this.textAreaScrollPane, BorderLayout.CENTER);
		add(this.textLineNumber, BorderLayout.WEST);

	}

	/**
	 * Indicate if the area is in text mode.
	 * 
	 * @return the textMode true if in text mode or false for file mode.
	 */
	public boolean isTextMode() {

		return this.textMode;
	}

	/**
	 * Indicate if the text area is saved.
	 * 
	 * @return true if the text area is saved.
	 */
	public boolean isTextAreaSaved() {

		return this.textAreaSaved;
	}

	/**
	 * Set if the text area is saved.
	 * 
	 * @param saved
	 *            The indication if the text area is saved.
	 */
	public void setTextAreaSaved(boolean saved) {

		this.textAreaSaved = saved;
	}

	/**
	 * Update the label indicating the caret position.
	 */
	private void updateCaretLabel() {

		int position = this.textArea.getCaretPosition();

		StringSupport.TextPosition textPosition = StringSupport
				.getTextyPosition(this.textArea.getText(), position);

		// Only shows position on text mode.
		if (FilterAnyConfiguration.isTextMode()) {

			this.positionLabel.setText(Text.get(Text.CARET_POSITION,
					String.valueOf(textPosition.getLine()),
					String.valueOf(textPosition.getColumn())));
		}

	}

	/**
	 * Provide the current caret column.
	 * 
	 * @return The current caret column.
	 */
	public int getCaretColumn() {

		StringSupport.TextPosition textPosition = StringSupport
				.getTextyPosition(this.textArea.getText(),
						this.textArea.getCaretPosition());

		return textPosition.getColumn();
	}

	/**
	 * Set the content of the position label.
	 * 
	 * @param text
	 *            The content of the position label.
	 */
	public void setPositionLabel(String text) {

		this.positionLabel.setText(text);
	}

	/**
	 * Verifies if the content needs to be saved.
	 * 
	 * @param textMessage
	 *            The text to question the user which area is not saved.
	 */
	public void verifyUnsavedContent(String textMessage) {

		if (!this.textAreaSaved) {

			// If it is on text mode than work with text areas.
			String text = FilterAnyConfiguration.isTextMode() ? this.textArea
					.getText() : this.tableModel.exportContent();

			// Ignore empty text.
			if ("".equals(text)) { //$NON-NLS-1$
				return;
			}

			int response = JOptionPane.showConfirmDialog(this.mainComponent,
					textMessage, Text.get(Text.TEXT_AREA_NOT_SAVED),
					JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				saveTextArea();
			}
		}
	}

	/**
	 * Save the area into a file.
	 */
	public void saveTextArea() {

		SimpleFileExtension extension = new SimpleFileExtension(".txt", Text //$NON-NLS-1$
				.get(Text.TEXT_FILE_EXTENSION));
		String fileName = FileDialogs.selectOutputFileBySwing(
				this.mainComponent, extension);

		if (fileName != null) {

			// If it is on text mode than work with text areas.
			String text = FilterAnyConfiguration.isTextMode() ? this.textArea
					.getText() : this.tableModel.exportContent();

			if (FilterAnyConfiguration.isSaveInDOSFormat()) {
				text = StringSupport.getDOSText(text);
			} else {
				text = StringSupport.getUnixText(text);
			}

			String encoding = FilterAnyEncoding.getInstance()
					.getOutputFileEncoding();

			if (encoding == null) {

				FilterAnyEncoding.getInstance();
				encoding = FilterAnyEncoding.getDefaultFileEncoding();
			}

			FileSupport.writeTextFileWithEncoding(fileName, text, encoding);
			this.textAreaSaved = true;
		}
	}

	/**
	 * Update all text area with labels.
	 */
	public void updateLabels() {

		// If it is on text mode than work with text areas.
		if (FilterAnyConfiguration.isTextMode()) {

			StringSupport.TextCharacteristics textCharacteristics = StringSupport
					.analyzeText(this.textArea.getText());

			setStatusText(Text
					.get(Text.TEXT_INFORMATION,
							String.valueOf(textCharacteristics.getLength()),
							String.valueOf(textCharacteristics.getWords()),
							String.valueOf(textCharacteristics.getLines()),
							(textCharacteristics.getLineType() == StringSupport.TextCharacteristics.DOS ? "DOS" //$NON-NLS-1$
									: "UNIX"))); //$NON-NLS-1$

		} else {

			// Work with the tables.
			ArrayList<FileOrURLBean> rows = this.tableModel.getRows();

			setStatusText(FilterAnyLogic.getFileOrURLDistributionText(rows));

		}
	}

	/**
	 * Load the previous content of the memory from file.
	 */
	@SuppressWarnings("unchecked")
	public void loadMemory(String memoryFileName) {

		// Load top memory
		try {

			this.internalMemory = (ArrayList<String>) Serialization
					.zipDeserializeObject(memoryFileName);

			if (this.internalMemory.size() > 0) {

				this.currentMemory = 0;
				this.memoryLabel.setText("1/" + this.internalMemory.size()); //$NON-NLS-1$
			}

		} catch (FileNotFoundException e) {
			// Do nothing.
		} catch (IOException e) {
			ExceptionSupport.handleException(e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			ExceptionSupport.handleException(e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * Serialize the content of the memory.
	 * 
	 * @throws FileNotFoundException
	 *             If a problem happens.
	 * @throws IOException
	 *             If a problem happens.
	 */
	public void serializeMemory(String memoryFileName)
			throws FileNotFoundException, IOException {

		Serialization.zipSerializeObject(memoryFileName, this.internalMemory);
	}

	/**
	 * Repaint the support information such as the line number and the
	 * navigation.
	 */
	public void repaintSupportInformation() {

		this.textLineNumber.repaint();
	}

	/**
	 * Enable or disable the text area.
	 * 
	 * @param enabled
	 *            True to enable and false to disable.
	 */
	public void setEnabledTextArea(boolean enabled) {

		this.enabled = enabled;

		this.textArea.setEnabled(enabled);

		this.textAreaMouseEvents.setEnabled(enabled);

		if (enabled) {
			this.statusLabel.setText(Text.get(Text.TEXT_INITIALIZED));

			this.addButtom.setEnabled(true);
			this.previousButtom.setEnabled(true);
			this.nextButtom.setEnabled(true);
			this.clearButtom.setEnabled(true);

			this.clearContentButtom.setEnabled(true);

			this.informationButton.setEnabled(true);

			this.removeTabulationButton.setEnabled(true);
			this.addTabulationButton.setEnabled(true);

			this.miningButton.setEnabled(true);

			this.formattingEditorButton.setEnabled(true);

		} else {
			this.statusLabel.setText(Text.get(Text.TEXT_DISABLED));

			this.addButtom.setEnabled(false);
			this.previousButtom.setEnabled(false);
			this.nextButtom.setEnabled(false);
			this.clearButtom.setEnabled(false);

			this.clearContentButtom.setEnabled(false);

			this.informationButton.setEnabled(false);

			this.removeTabulationButton.setEnabled(false);
			this.addTabulationButton.setEnabled(false);

			this.miningButton.setEnabled(false);

			this.formattingEditorButton.setEnabled(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#isEnabled()
	 */
	@Override
	public boolean isEnabled() {

		return this.enabled;
	}

	/**
	 * Add a specific menu item to the popup menu.
	 * 
	 * @param menuItem
	 *            A menu item.
	 */
	public void addMenuItemToPopup(JMenuItem menuItem) {

		this.textAreaMouseEvents.addMenuItemToPopup(menuItem);
	}

	/**
	 * Add a separator to the popup menu.
	 */
	public void addSeparatorToPopup() {

		this.textAreaMouseEvents.addSeparatorToPopup();
	}

	/**
	 * Add a menu item to the popup.
	 * 
	 * @param menu
	 *            A menu.
	 */
	public void addMenuToPopup(JMenu menu) {

		this.textAreaMouseEvents.addMenuToPopup(menu);
	}

	/**
	 * Set a text on the report area.
	 * 
	 * @param text
	 *            The text.
	 */
	public void setReportText(String text) {

		this.reportLabel.setText(text);
	}

	/**
	 * Hide the find dialog.
	 */
	public void hideFind() {

		if (this.findDialog != null) {
			this.findDialog.setVisible(false);
		}
	}

	/**
	 * Set a escape key listener.
	 * 
	 * @param listenerParameter
	 *            The escape key listener.
	 */
	public void setEscapeKeyListener(EscapeKeyListener listenerParameter) {

		this.escapeKeyListener = listenerParameter;
	}

	/**
	 * Hide all dialogs that could be open.
	 */
	public void hideAllDialogs() {

		if (this.findDialog != null) {

			this.findDialog.setVisible(false);
		}
	}

	/**
	 * Clear the undo history.
	 */
	public void clearUndo() {

		this.undo.discardAllEdits();

		if (this.undo.canUndo()) {

			this.undoAction.setEnabled(true);

		} else {

			this.undoAction.setEnabled(false);
		}

		if (this.undo.canRedo()) {

			this.redoAction.setEnabled(true);

		} else {

			this.redoAction.setEnabled(false);
		}

	}

	/**
	 * Add a key listener to the text area.
	 * 
	 * @param listener
	 *            The key listener.
	 */
	public void addTextAreaKeyListener(KeyListener listener) {

		this.textArea.addKeyListener(listener);
	}

	/**
	 * Add an action to the popup menu.
	 * 
	 * @param action
	 *            The action.
	 */
	public void addActionOnInternalFormattedEditorPopup(Action action) {

		return;	
	}

	/**
	 * Add a separation to the formatted editor popup menu.
	 */
	public void addActionOnInternalFormattedEditorPopup() {

		return;	
	}
}
