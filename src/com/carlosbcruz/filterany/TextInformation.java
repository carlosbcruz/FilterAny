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
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;
import com.carlosbcruz.util.StringSupport.TextCharacteristics;

/**
 * Show informations regarding a specific text.
 */
public class TextInformation extends JFrame {

	private static final long serialVersionUID = 1L;

	// The text to be analyzed.
	private StringBuffer text = new StringBuffer();
	private StringBuffer selectedText = new StringBuffer();

	// The list of words.
	private ArrayList<String> words = null;

	// Information colors.
	private static final String BEGIN_HTML = "<html>"; //$NON-NLS-1$
	private static final String END_HTML = "</html>"; //$NON-NLS-1$
	private static final String BEGIN_STRONG = "<strong><font color=\"red\">"; //$NON-NLS-1$
	private static final String END_STRONG = "</font></strong>"; //$NON-NLS-1$
	private static final String BEGIN_INFORMATION_BLUE = "<font color=\"blue\"><strong>"; //$NON-NLS-1$
	private static final String END_INFORMATION_BLUE = "</strong></font>"; //$NON-NLS-1$

	private TextInformationRemoveListener textRemovalListener = null;
	private TextInformation thisInstance = null;

	/**
	 * Constructor.
	 * 
	 * @param titleComplement
	 *            A complement to the window title.
	 * @param textParameter
	 *            The complete text.
	 * @param selectedTextParameter
	 *            The selected text.
	 * @param textRemovalListenerParameter
	 *            The listener to the removal.
	 */
	TextInformation(String titleComplement, StringBuffer textParameter,
			StringBuffer selectedTextParameter,
			TextInformationRemoveListener textRemovalListenerParameter) {

		super(Text.get(Text.TASK_INFORMATION_WINDOW_TITLE, titleComplement));

		this.textRemovalListener = textRemovalListenerParameter;
		this.thisInstance = this;

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		this.text = textParameter;
		this.selectedText = selectedTextParameter;

		this.words = StringSupport.getWords(this.text);

		this.words = StringSupport.sortByLength(this.words);

		JPanel mainPanel = new JPanel();

		Box verticalBox = Box.createVerticalBox();

		verticalBox.add(getSimpleInformationInTablePanel());

		verticalBox.add(getMostUsedWordsInTablePanel());

		Box horizontalBox = Box.createHorizontalBox();

		horizontalBox.add(verticalBox);

		horizontalBox.add(getSymbolUsageInformationInTablePanel());

		mainPanel.add(horizontalBox);

		setContentPane(mainPanel);

		pack();

		// Get window dimensions.
		int screenHeight = SwingUtil.getScreenHeight();
		int screenWidth = SwingUtil.getScreenWidth();

		setLocation((screenWidth - getSize().width) / 2,
				(screenHeight - getSize().height) / 2);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
			public void windowClosing(WindowEvent arg0) {

				TextInformation.this.textRemovalListener
						.remove(TextInformation.this.thisInstance);
			}
		});

		setVisible(true);

	}

	/**
	 * Provide simple text information in a table.
	 * 
	 * @return The panel with the information.
	 */
	@SuppressWarnings("static-method")
	private JPanel getSimpleInformationInTablePanel() {

		/**
		 * Presents simple text information in a table.
		 */
		class SimpleInformationInTablePanel extends JPanel {

			private static final long serialVersionUID = 1L;

			/**
			 * Constructor.
			 */
			@SuppressWarnings("synthetic-access")
			SimpleInformationInTablePanel() {

				TextCharacteristics textCharacteristics = StringSupport
						.analyzeText(TextInformation.this.text.toString());
				TextCharacteristics selectedTextCharacteristics = StringSupport
						.analyzeText(TextInformation.this.selectedText
								.toString());

				setBorder(BorderFactory.createTitledBorder(Text
						.get(Text.TASK_INFORMATION_GENERAL_INFORMATION)));

				String data[][] = new String[8][3];

				String lengthMessage = BEGIN_HTML
						+ Text.get(Text.TASK_INFORMATION_LENGTH) + ": " //$NON-NLS-1$
						+ END_HTML;
				data[0][0] = lengthMessage;

				String lengthValue = textCharacteristics.getLength() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getLength() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getLength()
						+ END_INFORMATION_BLUE + END_HTML;
				data[0][1] = lengthValue;

				String lengthSelectedValue = selectedTextCharacteristics
						.getLength() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getLength() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getLength()
						+ END_INFORMATION_BLUE + END_HTML;
				data[0][2] = lengthSelectedValue;

				String wordsMessage = BEGIN_HTML
						+ Text.get(Text.TASK_INFORMATION_WORDS) + ": " //$NON-NLS-1$
						+ END_HTML;
				data[1][0] = wordsMessage;

				String wordsValue = textCharacteristics.getWords() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getWords() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getWords() + END_INFORMATION_BLUE
						+ END_HTML;
				data[1][1] = wordsValue;

				String wordsSelectedValue = selectedTextCharacteristics
						.getWords() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getWords() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getWords()
						+ END_INFORMATION_BLUE + END_HTML;
				data[1][2] = wordsSelectedValue;

				String linesMessage = BEGIN_HTML
						+ Text.get(Text.TASK_INFORMATION_TOTAL_LINES) + ": " //$NON-NLS-1$
						+ END_HTML;
				data[2][0] = linesMessage;

				String linesValue = textCharacteristics.getLines() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getLines() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getLines() + END_INFORMATION_BLUE
						+ END_HTML;
				data[2][1] = linesValue;

				String linesSelectedValue = selectedTextCharacteristics
						.getLines() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getLines() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getLines()
						+ END_INFORMATION_BLUE + END_HTML;
				data[2][2] = linesSelectedValue;

				String emptyLinesMessage = BEGIN_HTML
						+ Text.get(Text.TASK_INFORMATION_EMPTY_LINES) + ": " //$NON-NLS-1$
						+ END_HTML;
				data[3][0] = emptyLinesMessage;

				String emptyLinesValue = textCharacteristics.getEmptyLines() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getEmptyLines()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getEmptyLines()
								+ END_INFORMATION_BLUE + END_HTML;
				data[3][1] = emptyLinesValue;

				String emptyLinesSelectedValue = selectedTextCharacteristics
						.getEmptyLines() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getEmptyLines()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getEmptyLines()
								+ END_INFORMATION_BLUE + END_HTML;
				data[3][2] = emptyLinesSelectedValue;

				String tabulationMessage = BEGIN_HTML
						+ Text.get(Text.TASK_INFORMATION_TABULATIONS) + ": " //$NON-NLS-1$
						+ END_HTML;
				data[4][0] = tabulationMessage;

				String tabulationValue = textCharacteristics
						.getNumberOfTabulations() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getNumberOfTabulations()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getNumberOfTabulations()
								+ END_INFORMATION_BLUE + END_HTML;
				data[4][1] = tabulationValue;

				String tabulationSelectedValue = selectedTextCharacteristics
						.getNumberOfTabulations() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getNumberOfTabulations()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getNumberOfTabulations()
								+ END_INFORMATION_BLUE + END_HTML;
				data[4][2] = tabulationSelectedValue;

				String spacesMessage = BEGIN_HTML
						+ Text.get(Text.TASK_INFORMATION_SPACES) + ": " //$NON-NLS-1$
						+ END_HTML;
				data[5][0] = spacesMessage;

				String spacesValue = textCharacteristics.getSpaces() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getSpaces() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getSpaces()
						+ END_INFORMATION_BLUE + END_HTML;
				data[5][1] = spacesValue;

				String spacesSelectedValue = selectedTextCharacteristics
						.getSpaces() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getSpaces() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getSpaces()
						+ END_INFORMATION_BLUE + END_HTML;
				data[5][2] = spacesSelectedValue;

				String spaceSequencesMessage = BEGIN_HTML
						+ Text.get(Text.TASK_INFORMATION_SPACE_SEQUENCES)
						+ ": " + END_HTML; //$NON-NLS-1$
				data[6][0] = spaceSequencesMessage;

				String spaceSequencesValue = textCharacteristics
						.getSpaceSequences() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getSpaceSequences() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getSpaceSequences()
						+ END_INFORMATION_BLUE + END_HTML;
				data[6][1] = spaceSequencesValue;

				String spaceSequencesSelectedValue = selectedTextCharacteristics
						.getSpaceSequences() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getSpaceSequences()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getSpaceSequences()
								+ END_INFORMATION_BLUE + END_HTML;
				data[6][2] = spaceSequencesSelectedValue;

				String lineTypeMessage = BEGIN_HTML
						+ Text.get(Text.TASK_INFORMATION_LINE_TYPE) + ": " //$NON-NLS-1$
						+ END_HTML;

				data[7][0] = lineTypeMessage;

				String lineTypeValue = BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ (textCharacteristics.getLineType() == TextCharacteristics.DOS ? "DOS" //$NON-NLS-1$
								: "UNIX") + END_INFORMATION_BLUE + END_HTML; //$NON-NLS-1$
				data[7][1] = lineTypeValue;

				String lineTypeSelectedValue = BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ (selectedTextCharacteristics.getLineType() == TextCharacteristics.DOS ? "DOS" //$NON-NLS-1$
								: "UNIX") + END_INFORMATION_BLUE + END_HTML; //$NON-NLS-1$
				data[7][2] = lineTypeSelectedValue;

				String title[] = new String[3];
				title[0] = Text.get(Text.TASK_INFORMATION_CHARACTERISC);
				title[1] = Text.get(Text.TASK_INFORMATION_ENTIRE_TEXT);
				title[2] = Text.get(Text.TASK_INFORMATION_SELECTED_TEXT);

				add(SwingUtil.getViewTable(data, title, new int[] {
						SwingConstants.LEFT, SwingConstants.RIGHT,
						SwingConstants.RIGHT }));

			}
		}

		return new SimpleInformationInTablePanel();
	}

	/**
	 * Provide symbols usage in a table.
	 * 
	 * @return The panel with the information.
	 */
	@SuppressWarnings("static-method")
	private JPanel getSymbolUsageInformationInTablePanel() {

		/**
		 * Presents the symbol usage information in a table.
		 */
		class SymbolUsageInformationInTablePanel extends JPanel {

			private static final long serialVersionUID = 1L;

			/**
			 * Constructor.
			 */
			@SuppressWarnings("synthetic-access")
			SymbolUsageInformationInTablePanel() {

				StringSupport.SymbolsOccurrencesBean textCharacteristics = StringSupport
						.analyzeTextSymbols(TextInformation.this.text
								.toString());
				StringSupport.SymbolsOccurrencesBean selectedTextCharacteristics = StringSupport
						.analyzeTextSymbols(TextInformation.this.selectedText
								.toString());

				setBorder(BorderFactory.createTitledBorder(Text
						.get(Text.TASK_INFORMATION_SYMBOLS_TITLE)));

				String data[][] = new String[32][3];

				// Period

				String periodMessage = BEGIN_HTML + BEGIN_STRONG + "." //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_PERIOD) + " " //$NON-NLS-1$
						+ END_HTML;
				data[0][0] = periodMessage;

				String periodValue = textCharacteristics.getPeriod() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getPeriod() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getPeriod()
						+ END_INFORMATION_BLUE + END_HTML;
				data[0][1] = periodValue;

				String periodSelectedValue = selectedTextCharacteristics
						.getPeriod() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getPeriod() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getPeriod()
						+ END_INFORMATION_BLUE + END_HTML;
				data[0][2] = periodSelectedValue;

				// Comma

				String commaMessage = BEGIN_HTML + BEGIN_STRONG + "," //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_COMMA) + " " //$NON-NLS-1$
						+ END_HTML;
				data[1][0] = commaMessage;

				String commaValue = textCharacteristics.getComma() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getComma() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getComma() + END_INFORMATION_BLUE
						+ END_HTML;
				data[1][1] = commaValue;

				String commaSelectedValue = selectedTextCharacteristics
						.getComma() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getComma() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getComma()
						+ END_INFORMATION_BLUE + END_HTML;
				data[1][2] = commaSelectedValue;

				// Colon

				String colonMessage = BEGIN_HTML + BEGIN_STRONG + ":" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_COLON) + " " //$NON-NLS-1$
						+ END_HTML;
				data[2][0] = colonMessage;

				String colonValue = textCharacteristics.getColon() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getColon() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getColon() + END_INFORMATION_BLUE
						+ END_HTML;
				data[2][1] = colonValue;

				String colonSelectedValue = selectedTextCharacteristics
						.getColon() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getColon() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getColon()
						+ END_INFORMATION_BLUE + END_HTML;
				data[2][2] = colonSelectedValue;

				// Semicolon

				String semicolonMessage = BEGIN_HTML + BEGIN_STRONG + ";" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_SEMICOLON) + " " //$NON-NLS-1$
						+ END_HTML;
				data[3][0] = semicolonMessage;

				String semicolonValue = textCharacteristics.getSemicolon() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getSemicolon()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getSemicolon()
								+ END_INFORMATION_BLUE + END_HTML;
				data[3][1] = semicolonValue;

				String semicolonSelectedValue = selectedTextCharacteristics
						.getSemicolon() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getSemicolon()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getSemicolon()
								+ END_INFORMATION_BLUE + END_HTML;
				data[3][2] = semicolonSelectedValue;

				// Exclamation Point

				String exclamationPointMessage = BEGIN_HTML + BEGIN_STRONG
						+ "!" + END_STRONG + " " //$NON-NLS-1$ //$NON-NLS-2$
						+ Text.get(Text.TASK_INFORMATION_EXCLAMATION_POINT)
						+ " " + END_HTML; //$NON-NLS-1$
				data[4][0] = exclamationPointMessage;

				String exclamationPointValue = textCharacteristics
						.getExclamationPoint() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getExclamationPoint()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getExclamationPoint()
								+ END_INFORMATION_BLUE + END_HTML;
				data[4][1] = exclamationPointValue;

				String exclamationPointSelectedValue = selectedTextCharacteristics
						.getExclamationPoint() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getExclamationPoint()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getExclamationPoint()
								+ END_INFORMATION_BLUE + END_HTML;
				data[4][2] = exclamationPointSelectedValue;

				// QuestionMark

				String questionMarkMessage = BEGIN_HTML + BEGIN_STRONG + "?" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_QUESTION_MARK) + " " //$NON-NLS-1$
						+ END_HTML;
				data[5][0] = questionMarkMessage;

				String questionMarkValue = textCharacteristics
						.getQuestionMark() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getQuestionMark() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getQuestionMark()
						+ END_INFORMATION_BLUE + END_HTML;
				data[5][1] = questionMarkValue;

				String questionMarkSelectedValue = selectedTextCharacteristics
						.getQuestionMark() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getQuestionMark()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getQuestionMark()
								+ END_INFORMATION_BLUE + END_HTML;
				data[5][2] = questionMarkSelectedValue;

				// Apostrophe

				String apostropheMessage = BEGIN_HTML + BEGIN_STRONG + "\'" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_APOSTROPHE) + " " //$NON-NLS-1$
						+ END_HTML;
				data[6][0] = apostropheMessage;

				String apostropheValue = textCharacteristics.getApostrophe() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getApostrophe()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getApostrophe()
								+ END_INFORMATION_BLUE + END_HTML;
				data[6][1] = apostropheValue;

				String apostropheSelectedValue = selectedTextCharacteristics
						.getApostrophe() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getApostrophe()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getApostrophe()
								+ END_INFORMATION_BLUE + END_HTML;
				data[6][2] = apostropheSelectedValue;

				// Single Quotation

				String singleQuotationMessage = BEGIN_HTML + BEGIN_STRONG
						+ "`" //$NON-NLS-1$
						+ END_STRONG
						+ " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_SINGLE_QUOTATION)
						+ " " + END_HTML; //$NON-NLS-1$
				data[7][0] = singleQuotationMessage;

				String singleQuotationValue = textCharacteristics
						.getSingleQuotation() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getSingleQuotation() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getSingleQuotation()
						+ END_INFORMATION_BLUE + END_HTML;
				data[7][1] = singleQuotationValue;

				String singleQuotationSelectedValue = selectedTextCharacteristics
						.getSingleQuotation() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getSingleQuotation()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getSingleQuotation()
								+ END_INFORMATION_BLUE + END_HTML;
				data[7][2] = singleQuotationSelectedValue;

				// Double Quotation

				String doubleQuotationMessage = BEGIN_HTML + BEGIN_STRONG
						+ "\"" + END_STRONG + " " //$NON-NLS-1$ //$NON-NLS-2$
						+ Text.get(Text.TASK_INFORMATION_DOUBLE_QUOTATION)
						+ " " + END_HTML; //$NON-NLS-1$
				data[8][0] = doubleQuotationMessage;

				String doubleQuotationValue = textCharacteristics
						.getDoubleQuotation() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getDoubleQuotation() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getDoubleQuotation()
						+ END_INFORMATION_BLUE + END_HTML;
				data[8][1] = doubleQuotationValue;

				String doubleQuotationSelectedValue = selectedTextCharacteristics
						.getDoubleQuotation() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getDoubleQuotation()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getDoubleQuotation()
								+ END_INFORMATION_BLUE + END_HTML;
				data[8][2] = doubleQuotationSelectedValue;

				// Open Brackets

				String openBracketsMessage = BEGIN_HTML + BEGIN_STRONG + "(" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_OPEN_BRACKETS) + " " //$NON-NLS-1$
						+ END_HTML;
				data[9][0] = openBracketsMessage;

				String openBracketsValue = textCharacteristics
						.getOpenBrackets() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getOpenBrackets() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getOpenBrackets()
						+ END_INFORMATION_BLUE + END_HTML;
				data[9][1] = openBracketsValue;

				String openBracketsSelectedValue = selectedTextCharacteristics
						.getOpenBrackets() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getOpenBrackets()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getOpenBrackets()
								+ END_INFORMATION_BLUE + END_HTML;
				data[9][2] = openBracketsSelectedValue;

				// Close Brackets

				String closeBracketsMessage = BEGIN_HTML + BEGIN_STRONG + ")" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_CLOSE_BRACKETS) + " " //$NON-NLS-1$
						+ END_HTML;
				data[10][0] = closeBracketsMessage;

				String closeBracketsValue = textCharacteristics
						.getCloseBrackets() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getCloseBrackets() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getCloseBrackets()
						+ END_INFORMATION_BLUE + END_HTML;
				data[10][1] = closeBracketsValue;

				String closeBracketsSelectedValue = selectedTextCharacteristics
						.getCloseBrackets() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getCloseBrackets()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getCloseBrackets()
								+ END_INFORMATION_BLUE + END_HTML;
				data[10][2] = closeBracketsSelectedValue;

				// Open Square Brackets

				String openSquareBracketsMessage = BEGIN_HTML + BEGIN_STRONG
						+ "[" + END_STRONG + " " //$NON-NLS-1$ //$NON-NLS-2$
						+ Text.get(Text.TASK_INFORMATION_OPEN_SQUARE_BRACKETS)
						+ " " + END_HTML; //$NON-NLS-1$
				data[11][0] = openSquareBracketsMessage;

				String openSquareBracketsValue = textCharacteristics
						.getOpenSquareBrackets() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getOpenSquareBrackets()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getOpenSquareBrackets()
								+ END_INFORMATION_BLUE + END_HTML;
				data[11][1] = openSquareBracketsValue;

				String openSquareBracketsSelectedValue = selectedTextCharacteristics
						.getOpenSquareBrackets() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getOpenSquareBrackets()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getOpenSquareBrackets()
								+ END_INFORMATION_BLUE + END_HTML;
				data[11][2] = openSquareBracketsSelectedValue;

				// Close Square Brackets

				String closeSquareBracketsMessage = BEGIN_HTML + BEGIN_STRONG
						+ "]" + END_STRONG + " " //$NON-NLS-1$ //$NON-NLS-2$
						+ Text.get(Text.TASK_INFORMATION_CLOSE_SQUARE_BRACKETS)
						+ " " + END_HTML; //$NON-NLS-1$
				data[12][0] = closeSquareBracketsMessage;

				String closeSquareBracketsValue = textCharacteristics
						.getCloseSquareBrackets() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getCloseSquareBrackets()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getCloseSquareBrackets()
								+ END_INFORMATION_BLUE + END_HTML;
				data[12][1] = closeSquareBracketsValue;

				String closeSquareBracketsSelectedValue = selectedTextCharacteristics
						.getCloseSquareBrackets() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getCloseSquareBrackets()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getCloseSquareBrackets()
								+ END_INFORMATION_BLUE + END_HTML;
				data[12][2] = closeSquareBracketsSelectedValue;

				// Open Curly Brace

				String openCurlyBraceMessage = BEGIN_HTML + BEGIN_STRONG
						+ "{" //$NON-NLS-1$
						+ END_STRONG
						+ " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_OPEN_CURLY_BRACE)
						+ " " + END_HTML; //$NON-NLS-1$
				data[13][0] = openCurlyBraceMessage;

				String openCurlyBraceValue = textCharacteristics
						.getOpenCurlyBrace() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getOpenCurlyBrace() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getOpenCurlyBrace()
						+ END_INFORMATION_BLUE + END_HTML;
				data[13][1] = openCurlyBraceValue;

				String openCurlyBraceSelectedValue = selectedTextCharacteristics
						.getOpenCurlyBrace() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getOpenCurlyBrace()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getOpenCurlyBrace()
								+ END_INFORMATION_BLUE + END_HTML;
				data[13][2] = openCurlyBraceSelectedValue;

				// Close Curly Brace

				String closeCurlyBraceMessage = BEGIN_HTML + BEGIN_STRONG
						+ "}" //$NON-NLS-1$
						+ END_STRONG
						+ " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_CLOSE_CURLY_BRACE)
						+ " " + END_HTML; //$NON-NLS-1$
				data[14][0] = closeCurlyBraceMessage;

				String closeCurlyBraceValue = textCharacteristics
						.getCloseCurlyBrace() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getCloseCurlyBrace() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getCloseCurlyBrace()
						+ END_INFORMATION_BLUE + END_HTML;
				data[14][1] = closeCurlyBraceValue;

				String closeCurlyBraceSelectedValue = selectedTextCharacteristics
						.getCloseCurlyBrace() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getCloseCurlyBrace()
						: BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics
										.getCloseCurlyBrace()
								+ END_INFORMATION_BLUE + END_HTML;
				data[14][2] = closeCurlyBraceSelectedValue;

				// Or

				String orMessage = BEGIN_HTML + BEGIN_STRONG + "|" + END_STRONG //$NON-NLS-1$
						+ " " + Text.get(Text.TASK_INFORMATION_OR) + " " //$NON-NLS-1$ //$NON-NLS-2$
						+ END_HTML;
				data[15][0] = orMessage;

				String orValue = textCharacteristics.getOr() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getOr() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE + textCharacteristics.getOr()
						+ END_INFORMATION_BLUE + END_HTML;
				data[15][1] = orValue;

				String orSelectedValue = selectedTextCharacteristics.getOr() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getOr()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getOr()
								+ END_INFORMATION_BLUE + END_HTML;
				data[15][2] = orSelectedValue;

				// Underline

				String underlineMessage = BEGIN_HTML + BEGIN_STRONG + "_" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_UNDERLINE) + " " //$NON-NLS-1$
						+ END_HTML;
				data[16][0] = underlineMessage;

				String underlineValue = textCharacteristics.getUnderline() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getUnderline()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getUnderline()
								+ END_INFORMATION_BLUE + END_HTML;
				data[16][1] = underlineValue;

				String underlineSelectedValue = selectedTextCharacteristics
						.getUnderline() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getUnderline()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getUnderline()
								+ END_INFORMATION_BLUE + END_HTML;
				data[16][2] = underlineSelectedValue;

				// Ampersand

				String ampersandMessage = BEGIN_HTML + BEGIN_STRONG + "&" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_AMPERSAND) + " " //$NON-NLS-1$
						+ END_HTML;
				data[17][0] = ampersandMessage;

				String ampersandValue = textCharacteristics.getAmpersand() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getAmpersand()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getAmpersand()
								+ END_INFORMATION_BLUE + END_HTML;
				data[17][1] = ampersandValue;

				String ampersandSelectedValue = selectedTextCharacteristics
						.getAmpersand() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getAmpersand()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getAmpersand()
								+ END_INFORMATION_BLUE + END_HTML;
				data[17][2] = ampersandSelectedValue;

				// Tilde

				String tildeMessage = BEGIN_HTML + BEGIN_STRONG + "~" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_TILDE) + " " //$NON-NLS-1$
						+ END_HTML;
				data[18][0] = tildeMessage;

				String tildeValue = textCharacteristics.getTilde() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getTilde() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getTilde() + END_INFORMATION_BLUE
						+ END_HTML;
				data[18][1] = tildeValue;

				String tildeSelectedValue = selectedTextCharacteristics
						.getTilde() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getTilde() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getTilde()
						+ END_INFORMATION_BLUE + END_HTML;
				data[18][2] = tildeSelectedValue;

				// At

				String atMessage = BEGIN_HTML + BEGIN_STRONG + "@" + END_STRONG //$NON-NLS-1$
						+ " " + Text.get(Text.TASK_INFORMATION_AT) + " " //$NON-NLS-1$ //$NON-NLS-2$
						+ END_HTML;
				data[19][0] = atMessage;

				String atValue = textCharacteristics.getAt() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getAt() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE + textCharacteristics.getAt()
						+ END_INFORMATION_BLUE + END_HTML;
				data[19][1] = atValue;

				String atSelectedValue = selectedTextCharacteristics.getAt() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getAt()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getAt()
								+ END_INFORMATION_BLUE + END_HTML;
				data[19][2] = atSelectedValue;

				// Plus

				String plusMessage = BEGIN_HTML + BEGIN_STRONG + "+" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_PLUS) + " " + END_HTML; //$NON-NLS-1$
				data[20][0] = plusMessage;

				String plusValue = textCharacteristics.getPlus() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getPlus() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getPlus() + END_INFORMATION_BLUE
						+ END_HTML;
				data[20][1] = plusValue;

				String plusSelectedValue = selectedTextCharacteristics
						.getPlus() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getPlus() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getPlus()
						+ END_INFORMATION_BLUE + END_HTML;
				data[20][2] = plusSelectedValue;

				// Dash

				String dashMessage = BEGIN_HTML + BEGIN_STRONG + "-" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_DASH) + " " + END_HTML; //$NON-NLS-1$
				data[21][0] = dashMessage;

				String dashValue = textCharacteristics.getDash() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getDash() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getDash() + END_INFORMATION_BLUE
						+ END_HTML;
				data[21][1] = dashValue;

				String dashSelectedValue = selectedTextCharacteristics
						.getDash() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getDash() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getDash()
						+ END_INFORMATION_BLUE + END_HTML;
				data[21][2] = dashSelectedValue;

				// Equals

				String equalsMessage = BEGIN_HTML + BEGIN_STRONG + "=" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_EQUALS) + " " //$NON-NLS-1$
						+ END_HTML;
				data[22][0] = equalsMessage;

				String equalsValue = textCharacteristics.getEquals() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getEquals() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getEquals()
						+ END_INFORMATION_BLUE + END_HTML;
				data[22][1] = equalsValue;

				String equalsSelectedValue = selectedTextCharacteristics
						.getEquals() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getEquals() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getEquals()
						+ END_INFORMATION_BLUE + END_HTML;
				data[22][2] = equalsSelectedValue;

				// Is Less Than

				String isLessThanMessage = BEGIN_HTML + BEGIN_STRONG + "&lt;" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_IS_LESS_THAN) + " " //$NON-NLS-1$
						+ END_HTML;
				data[23][0] = isLessThanMessage;

				String isLessThanValue = textCharacteristics.getIsLessThan() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getIsLessThan()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getIsLessThan()
								+ END_INFORMATION_BLUE + END_HTML;
				data[23][1] = isLessThanValue;

				String isLessThanSelectedValue = selectedTextCharacteristics
						.getIsLessThan() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getIsLessThan()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getIsLessThan()
								+ END_INFORMATION_BLUE + END_HTML;
				data[23][2] = isLessThanSelectedValue;

				// Is Greater Than

				String isMoreThanMessage = BEGIN_HTML + BEGIN_STRONG + "&gt;" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_IS_GREATER_THAN) + " " //$NON-NLS-1$
						+ END_HTML;
				data[24][0] = isMoreThanMessage;

				String isMoreThanValue = textCharacteristics.getIsMoreThan() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getIsMoreThan()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getIsMoreThan()
								+ END_INFORMATION_BLUE + END_HTML;
				data[24][1] = isMoreThanValue;

				String isMoreThanSelectedValue = selectedTextCharacteristics
						.getIsMoreThan() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getIsMoreThan()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getIsMoreThan()
								+ END_INFORMATION_BLUE + END_HTML;
				data[24][2] = isMoreThanSelectedValue;

				// Back Slash

				String backSlashMessage = BEGIN_HTML + BEGIN_STRONG + "\\" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_BACK_SLASH) + " " //$NON-NLS-1$
						+ END_HTML;
				data[25][0] = backSlashMessage;

				String backSlashValue = textCharacteristics.getBackSlash() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getBackSlash()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getBackSlash()
								+ END_INFORMATION_BLUE + END_HTML;
				data[25][1] = backSlashValue;

				String backSlashSelectedValue = selectedTextCharacteristics
						.getBackSlash() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getBackSlash()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getBackSlash()
								+ END_INFORMATION_BLUE + END_HTML;
				data[25][2] = backSlashSelectedValue;

				// Forward Slash

				String forwardSlashMessage = BEGIN_HTML + BEGIN_STRONG + "/" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_FORWARD_SLASH) + " " //$NON-NLS-1$
						+ END_HTML;
				data[26][0] = forwardSlashMessage;

				String forwardSlashValue = textCharacteristics
						.getForwardSlash() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getForwardSlash() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getForwardSlash()
						+ END_INFORMATION_BLUE + END_HTML;
				data[26][1] = forwardSlashValue;

				String forwardSlashSelectedValue = selectedTextCharacteristics
						.getForwardSlash() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getForwardSlash()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getForwardSlash()
								+ END_INFORMATION_BLUE + END_HTML;
				data[26][2] = forwardSlashSelectedValue;

				// Hash

				String hashMessage = BEGIN_HTML + BEGIN_STRONG + "#" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_HASH) + " " + END_HTML; //$NON-NLS-1$
				data[27][0] = hashMessage;

				String hashValue = textCharacteristics.getHash() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getHash() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getHash() + END_INFORMATION_BLUE
						+ END_HTML;
				data[27][1] = hashValue;

				String hashSelectedValue = selectedTextCharacteristics
						.getHash() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getHash() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getHash()
						+ END_INFORMATION_BLUE + END_HTML;
				data[27][2] = hashSelectedValue;

				// Dollar

				String dollarMessage = BEGIN_HTML + BEGIN_STRONG + "$" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_DOLLAR) + " " //$NON-NLS-1$
						+ END_HTML;
				data[28][0] = dollarMessage;

				String dollarValue = textCharacteristics.getDollar() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getDollar() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getDollar()
						+ END_INFORMATION_BLUE + END_HTML;
				data[28][1] = dollarValue;

				String dollarSelectedValue = selectedTextCharacteristics
						.getDollar() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getDollar() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getDollar()
						+ END_INFORMATION_BLUE + END_HTML;
				data[28][2] = dollarSelectedValue;

				// Percent

				String percentMessage = BEGIN_HTML + BEGIN_STRONG + "%" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_PERCENT) + " " //$NON-NLS-1$
						+ END_HTML;
				data[29][0] = percentMessage;

				String percentValue = textCharacteristics.getPercent() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getPercent()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getPercent()
								+ END_INFORMATION_BLUE + END_HTML;
				data[29][1] = percentValue;

				String percentSelectedValue = selectedTextCharacteristics
						.getPercent() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getPercent() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getPercent()
						+ END_INFORMATION_BLUE + END_HTML;
				data[29][2] = percentSelectedValue;

				// Caret

				String caretMessage = BEGIN_HTML + BEGIN_STRONG + "^" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_CARET) + " " //$NON-NLS-1$
						+ END_HTML;
				data[30][0] = caretMessage;

				String caretValue = textCharacteristics.getCaret() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getCaret() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ textCharacteristics.getCaret() + END_INFORMATION_BLUE
						+ END_HTML;
				data[30][1] = caretValue;

				String caretSelectedValue = selectedTextCharacteristics
						.getCaret() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getCaret() : BEGIN_HTML
						+ BEGIN_INFORMATION_BLUE
						+ selectedTextCharacteristics.getCaret()
						+ END_INFORMATION_BLUE + END_HTML;
				data[30][2] = caretSelectedValue;

				// Asterisk

				String asteriskMessage = BEGIN_HTML + BEGIN_STRONG + "*" //$NON-NLS-1$
						+ END_STRONG + " " //$NON-NLS-1$
						+ Text.get(Text.TASK_INFORMATION_ASTERISK) + " " //$NON-NLS-1$
						+ END_HTML;
				data[31][0] = asteriskMessage;

				String asteriskValue = textCharacteristics.getAsterisk() == 0 ? "" //$NON-NLS-1$
						+ textCharacteristics.getAsterisk()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ textCharacteristics.getAsterisk()
								+ END_INFORMATION_BLUE + END_HTML;
				data[31][1] = asteriskValue;

				String asteriskSelectedValue = selectedTextCharacteristics
						.getAsterisk() == 0 ? "" //$NON-NLS-1$
						+ selectedTextCharacteristics.getAsterisk()
						: BEGIN_HTML + BEGIN_INFORMATION_BLUE
								+ selectedTextCharacteristics.getAsterisk()
								+ END_INFORMATION_BLUE + END_HTML;
				data[31][2] = asteriskSelectedValue;

				String title[] = new String[3];
				title[0] = Text.get(Text.TASK_INFORMATION_SYMBOL);
				title[1] = Text.get(Text.TASK_INFORMATION_ENTIRE_TEXT_2);
				title[2] = Text.get(Text.TASK_INFORMATION_SELECTED_TEXT_2);

				add(SwingUtil.getViewTable(data, title, new int[] {
						SwingConstants.LEFT, SwingConstants.RIGHT,
						SwingConstants.RIGHT }));

			}
		}

		return new SymbolUsageInformationInTablePanel();
	}

	/**
	 * Indicate which are the most used words.
	 * 
	 * @return the panel with the most used words.
	 */
	private JPanel getMostUsedWordsInTablePanel() {

		/**
		 * Show most used words.
		 */
		class MostUsedWords extends JPanel {

			int originalComponentWidth = 0;

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.JComponent#getPreferredSize()
			 */
			@Override
			public Dimension getPreferredSize() {

				Dimension superComponentDimension = super.getPreferredSize();

				if (this.originalComponentWidth == 0) {
					this.originalComponentWidth = superComponentDimension.width + 20;
				}
				superComponentDimension.width = this.originalComponentWidth;

				return superComponentDimension;
			}

			private static final long serialVersionUID = 1L;

			// Show repeated works greater than the number of characters.
			private JLabel numberOfCharactersLabel = new JLabel();

			// How many words to show.
			private static final int NUMBER_OF_WORDS = 20;

			// Table with words.
			private JPanel currentTablePanel = null;

			// Cache the results.
			Hashtable<Integer, StringSupport.WordRepetition[]> cache = new Hashtable<>();

			private String header[] = new String[] {
					Text.get(Text.TASK_INFORMATION_POSITION),
					Text.get(Text.TASK_INFORMATION_WORD),
					Text.get(Text.TASK_INFORMATION_REPETITIONS) };

			private int[] position = new int[] { SwingConstants.CENTER,
					SwingConstants.LEFT, SwingConstants.CENTER };

			private Box verticalBox = null;

			/**
			 * Constructor.
			 */
			@SuppressWarnings("synthetic-access")
			MostUsedWords() {

				setBorder(BorderFactory.createTitledBorder(Text
						.get(Text.TASK_INFORMATION_REPEATED_WORDS)));

				int longest = StringSupport
						.getLongestLength(TextInformation.this.words);

				JSlider slider = null;

				int startWordLength = -1;
				if (longest == 0) {
					startWordLength = 0;
					slider = new JSlider(SwingConstants.HORIZONTAL, 0, 0, 0);
				} else {
					startWordLength = 1;
					slider = new JSlider(SwingConstants.HORIZONTAL, 1, longest,
							1);
				}
				this.numberOfCharactersLabel
						.setText(getMessage(startWordLength));

				this.verticalBox = Box.createVerticalBox();

				JPanel title = new JPanel(new BorderLayout());
				title.add(this.numberOfCharactersLabel, BorderLayout.NORTH);
				title.add(slider, BorderLayout.SOUTH);

				this.verticalBox.add(title);

				populateWordLabels(1);

				Hashtable<Integer, JLabel> labelTitle = new Hashtable<>();
				labelTitle.put(new Integer(1), new JLabel("1")); //$NON-NLS-1$
				labelTitle.put(new Integer(longest),
						new JLabel(String.valueOf(longest)));

				slider.setLabelTable(labelTitle);
				slider.setPaintLabels(true);

				slider.addChangeListener(new ChangeListener() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * javax.swing.event.ChangeListener#stateChanged(javax.swing
					 * .event.ChangeEvent)
					 */
					@Override
					public void stateChanged(ChangeEvent event) {

						JSlider comp = (JSlider) event.getSource();

						if (comp.getValueIsAdjusting()) {
							MostUsedWords.this.numberOfCharactersLabel
									.setText(getMessage(comp.getValue()));
						} else {
							MostUsedWords.this.numberOfCharactersLabel
									.setText(getMessage(comp.getValue()));
							populateWordLabels(comp.getValue());
						}

					}
				});

				// If ESC is pressed then hide the .
				slider.addKeyListener(new KeyAdapter() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent
					 * )
					 */
					@Override
					public void keyPressed(KeyEvent event) {

						if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

							TextInformation.this.textRemovalListener
									.remove(TextInformation.this.thisInstance);

							TextInformation.this.thisInstance.setVisible(false);

							TextInformation.this.thisInstance.dispose();

						}

					}

				});

				add(this.verticalBox);
			}

			/**
			 * Populate the labels with the words.
			 * 
			 * @param numberOfCharacters
			 *            the number of characters.
			 */
			@SuppressWarnings("synthetic-access")
			private void populateWordLabels(int numberOfCharacters) {

				StringSupport.WordRepetition repetitions[] = this.cache
						.get(Integer.valueOf(numberOfCharacters));

				if (repetitions == null) {
					repetitions = StringSupport.getMostRepeatedWords(
							TextInformation.this.words, NUMBER_OF_WORDS,
							numberOfCharacters);
					this.cache.put(Integer.valueOf(numberOfCharacters),
							repetitions);
				}

				String data[][] = new String[NUMBER_OF_WORDS][3];

				for (int i = 0; i < NUMBER_OF_WORDS; i++) {
					data[i][0] = String.valueOf(i + 1);

					if (repetitions[i].getRepetitions() > 0) {

						data[i][1] = BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ StringSupport.getWordWithMaximumLength(
										repetitions[i].getWord(), 20)
								+ END_INFORMATION_BLUE + END_HTML;
						data[i][2] = repetitions[i].getRepetitions() == 0 ? "" //$NON-NLS-1$
								+ repetitions[i].getRepetitions() : BEGIN_HTML
								+ BEGIN_INFORMATION_BLUE
								+ String.valueOf(repetitions[i]
										.getRepetitions())
								+ END_INFORMATION_BLUE + END_HTML;
					} else {
						data[i][1] = new String();
						data[i][2] = new String();
					}
				}

				if (this.currentTablePanel != null) {
					this.verticalBox.remove(this.currentTablePanel);
				}
				this.currentTablePanel = SwingUtil.getViewTable(data,
						this.header, this.position);
				this.verticalBox.add(this.currentTablePanel);
				pack();
			}

			/**
			 * Get the message of works with more than X characters.
			 * 
			 * @param numberOfCharacters
			 *            The number of characters.
			 * @return the message.
			 */
			private String getMessage(int numberOfCharacters) {

				return BEGIN_HTML
						+ Text.get(
								Text.TASK_INFORMATION_REPEATED_WORDS_WITH_MORE_THEN_CHARACTERS,
								BEGIN_INFORMATION_BLUE,
								String.valueOf(numberOfCharacters),
								END_INFORMATION_BLUE) + END_HTML;

			}
		}

		return new MostUsedWords();
	}

}
