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
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Search the token in the text provided and show only the lines that have the
 * token. The token occurrences are presented in bold in each line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextMiningWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private String[] originalText;

	private JTextField firstSearch;
	private JTextField secondSearch;
	private JTextField thirdSearch;
	private JTextField forthSearch;
	private JTextField fifthSearch;

	private JCheckBox firstCheck;
	private JCheckBox secondCheck;
	private JCheckBox thirdCheck;
	private JCheckBox forthCheck;
	private JCheckBox fifthCheck;

	private final Color[] TOKEN_COLORS = new Color[] { Color.BLUE, Color.RED,
			Color.GREEN, Color.ORANGE, Color.MAGENTA };

	private JTextPane textPane;
	private StyledDocument document;

	private static final String FONT_FAMILY = "Lucida Console"; //$NON-NLS-1$

	public TextMiningWindow(String[] textParameter, Color backgroundParameter,
			Color foregroundParameter) {

		super((Frame) null, Text.get(Text.MINING_WINDOW_TITLE));

		setModal(true);

		this.originalText = textParameter;

		this.textPane = new JTextPane();

		this.textPane.setBackground(backgroundParameter);
		this.textPane.setForeground(foregroundParameter);

		JScrollPane scrollPane = new JScrollPane(this.textPane);

		JPanel formPanel = new JPanel();

		JLabel firstSearchLabel = new JLabel(Text.get(Text.MINING_FIRST_FIELD));
		JLabel secondSearchLabel = new JLabel(
				Text.get(Text.MINING_SECOND_FIELD));
		JLabel thirdSearchLabel = new JLabel(Text.get(Text.MINING_THIRD_FIELD));
		JLabel forthSearchLabel = new JLabel(Text.get(Text.MINING_FORTH_FIELD));
		JLabel fifthSearchLabel = new JLabel(Text.get(Text.MINING_FIFTH_FIELD));

		firstSearchLabel.setForeground(this.TOKEN_COLORS[0]);
		secondSearchLabel.setForeground(this.TOKEN_COLORS[1]);
		thirdSearchLabel.setForeground(this.TOKEN_COLORS[2]);
		forthSearchLabel.setForeground(this.TOKEN_COLORS[3]);
		fifthSearchLabel.setForeground(this.TOKEN_COLORS[4]);

		this.firstSearch = new JTextField(10);
		JPanel firstPanel = new JPanel(new BorderLayout());
		firstPanel.setMinimumSize(this.firstSearch.getPreferredSize());
		firstPanel.add(this.firstSearch, BorderLayout.WEST);

		this.secondSearch = new JTextField(10);
		JPanel secondPanel = new JPanel(new BorderLayout());
		secondPanel.setMinimumSize(this.secondSearch.getPreferredSize());
		secondPanel.add(this.secondSearch, BorderLayout.WEST);

		this.thirdSearch = new JTextField(10);
		JPanel thirdPanel = new JPanel(new BorderLayout());
		thirdPanel.setMinimumSize(this.thirdSearch.getPreferredSize());
		thirdPanel.add(this.thirdSearch, BorderLayout.WEST);

		this.forthSearch = new JTextField(10);
		JPanel forthPanel = new JPanel(new BorderLayout());
		forthPanel.setMinimumSize(this.forthSearch.getPreferredSize());
		forthPanel.add(this.forthSearch, BorderLayout.WEST);

		this.fifthSearch = new JTextField(10);
		JPanel fifthPanel = new JPanel(new BorderLayout());
		fifthPanel.setMinimumSize(this.fifthSearch.getPreferredSize());
		fifthPanel.add(this.fifthSearch, BorderLayout.WEST);

		this.firstCheck = new JCheckBox(Text.get(Text.MINING_CASE_SENSITIVE),
				true);
		this.secondCheck = new JCheckBox(Text.get(Text.MINING_CASE_SENSITIVE),
				true);
		this.thirdCheck = new JCheckBox(Text.get(Text.MINING_CASE_SENSITIVE),
				true);
		this.forthCheck = new JCheckBox(Text.get(Text.MINING_CASE_SENSITIVE),
				true);
		this.fifthCheck = new JCheckBox(Text.get(Text.MINING_CASE_SENSITIVE),
				true);

		GroupLayout layout = new GroupLayout(formPanel);
		formPanel.setLayout(layout);
		GroupLayout.SequentialGroup horizontalGroup = layout
				.createSequentialGroup();

		horizontalGroup.addGroup(layout.createParallelGroup()
				.addComponent(firstSearchLabel).addComponent(secondSearchLabel)
				.addComponent(thirdSearchLabel).addComponent(forthSearchLabel)
				.addComponent(fifthSearchLabel));
		horizontalGroup.addGroup(layout.createParallelGroup()
				.addComponent(firstPanel).addComponent(secondPanel)
				.addComponent(thirdPanel).addComponent(forthPanel)
				.addComponent(fifthPanel));
		horizontalGroup.addGroup(layout.createParallelGroup()
				.addComponent(this.firstCheck).addComponent(this.secondCheck)
				.addComponent(this.thirdCheck).addComponent(this.forthCheck)
				.addComponent(this.fifthCheck));
		layout.setHorizontalGroup(horizontalGroup);

		GroupLayout.SequentialGroup verticalGroup = layout
				.createSequentialGroup();
		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(firstSearchLabel).addComponent(firstPanel)
				.addComponent(this.firstCheck));
		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(secondSearchLabel).addComponent(secondPanel)
				.addComponent(this.secondCheck));
		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(thirdSearchLabel).addComponent(thirdPanel)
				.addComponent(this.thirdCheck));
		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(forthSearchLabel).addComponent(forthPanel)
				.addComponent(this.forthCheck));
		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(fifthSearchLabel).addComponent(fifthPanel)
				.addComponent(this.fifthCheck));

		layout.setVerticalGroup(verticalGroup);

		JPanel controlPanel = new JPanel(new BorderLayout());

		TitledBorder titled = BorderFactory
				.createTitledBorder(Text.get(Text.MINING_BORDER_TITLE));
		formPanel.setBorder(titled);
		controlPanel.add(formPanel, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				controlPanel, scrollPane);
		splitPane.setEnabled(false);

		setContentPane(splitPane);

		filterDocument();

		this.textPane.setEditable(false);
		this.textPane.setCaretPosition(0);

		pack();

		SwingUtil.limitWindowByPercentageOfScreen(this, 90d);
		SwingUtil.centerOnScreen(this);

		this.firstSearch.requestFocus();

		attachEvents();

		setVisible(true);

	}

	/**
	 * Attach the events to the fields.
	 */
	private void attachEvents() {

		KeyListener fieldsKeyListener = new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setVisible(false);
					return;
				}

			}
		};

		this.firstSearch.addKeyListener(fieldsKeyListener);
		this.secondSearch.addKeyListener(fieldsKeyListener);
		this.thirdSearch.addKeyListener(fieldsKeyListener);
		this.forthSearch.addKeyListener(fieldsKeyListener);
		this.fifthSearch.addKeyListener(fieldsKeyListener);

		DocumentListener documentListener = new DocumentListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void removeUpdate(DocumentEvent e) {

				filterDocument();

			}

			@SuppressWarnings("synthetic-access")
			@Override
			public void insertUpdate(DocumentEvent e) {

				filterDocument();

			}

			@SuppressWarnings("synthetic-access")
			@Override
			public void changedUpdate(DocumentEvent e) {

				filterDocument();

			}
		};

		this.firstSearch.getDocument().addDocumentListener(documentListener);
		this.secondSearch.getDocument().addDocumentListener(documentListener);
		this.thirdSearch.getDocument().addDocumentListener(documentListener);
		this.forthSearch.getDocument().addDocumentListener(documentListener);
		this.fifthSearch.getDocument().addDocumentListener(documentListener);

		this.textPane.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setVisible(false);
					return;
				}
			}
		});

		KeyListener checksKeyListener = new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setVisible(false);
				}
			}
		};

		this.firstCheck.addKeyListener(checksKeyListener);
		this.secondCheck.addKeyListener(checksKeyListener);
		this.thirdCheck.addKeyListener(checksKeyListener);
		this.forthCheck.addKeyListener(checksKeyListener);
		this.fifthCheck.addKeyListener(checksKeyListener);

		ItemListener checksItemListener = new ItemListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void itemStateChanged(ItemEvent e) {

				filterDocument();
			}
		};

		this.firstCheck.addItemListener(checksItemListener);
		this.secondCheck.addItemListener(checksItemListener);
		this.thirdCheck.addItemListener(checksItemListener);
		this.forthCheck.addItemListener(checksItemListener);
		this.fifthCheck.addItemListener(checksItemListener);

	}

	/**
	 * 
	 */
	private void filterDocument() {

		String[] filteredText = new String[this.originalText.length];

		for (int i = 0; i < this.originalText.length; i++) {
			filteredText[i] = this.originalText[i];
		}

		boolean[] caseSensitive = new boolean[] { this.firstCheck.isSelected(),
				this.secondCheck.isSelected(), this.thirdCheck.isSelected(),
				this.forthCheck.isSelected(), this.fifthCheck.isSelected() };

		String[] tokens = new String[] { this.firstSearch.getText().trim(),
				this.secondSearch.getText().trim(),
				this.thirdSearch.getText().trim(),
				this.forthSearch.getText().trim(),
				this.fifthSearch.getText().trim() };

		filteredText = StringSupport.filterLinesWithTokens(filteredText,
				tokens, caseSensitive);

		boolean hasSearchCriteria = false;
		for (String criteria : tokens) {
			if (!"".equals(criteria)) { //$NON-NLS-1$
				hasSearchCriteria = true;
			}
		}

		this.textPane.setText(""); //$NON-NLS-1$

		this.document = this.textPane.getStyledDocument();

		if (!hasSearchCriteria) {

			for (String line : filteredText) {

				try {

					SimpleAttributeSet attribute = new SimpleAttributeSet();
					this.document.insertString(this.document.getLength(), line
							+ String.valueOf(StringSupport.getJavaNewLine()),
							attribute);

				} catch (BadLocationException exception) {
					ExceptionSupport.handleException(exception);
					return;
				}
			}
			return;
		}

		for (int i = 0; i < filteredText.length; i++) {

			String line = new String(filteredText[i]);

			do {

				int closerTokenPosition = Integer.MAX_VALUE;
				int closerTokenIndex = Integer.MAX_VALUE;

				for (int j = 0; j < tokens.length; j++) {

					if ("".equals(tokens[j])) { //$NON-NLS-1$
						continue;
					}

					int position = caseSensitive[j] ? line.indexOf(tokens[j])
							: line.toUpperCase().indexOf(
									tokens[j].toUpperCase());

					if (position >= 0 && position < closerTokenPosition) {

						closerTokenPosition = position;
						closerTokenIndex = j;
					}
				}

				String prefix = null;
				String suffix = null;

				if (closerTokenPosition != Integer.MAX_VALUE) {

					if (closerTokenPosition > 0) {

						prefix = line.substring(0, closerTokenPosition);

						try {

							SimpleAttributeSet attribute = new SimpleAttributeSet();
							StyleConstants
									.setFontFamily(attribute, FONT_FAMILY);
							StyleConstants.setFontSize(attribute, 10);

							this.document.insertString(
									this.document.getLength(), prefix,
									attribute);

						} catch (BadLocationException exception) {
							ExceptionSupport.handleException(exception);
							return;
						}
					}

					String tokenMatch = line.substring(
							closerTokenPosition,
							closerTokenPosition
									+ tokens[closerTokenIndex].length());
					try {

						SimpleAttributeSet attribute = new SimpleAttributeSet();
						StyleConstants.setFontFamily(attribute, FONT_FAMILY);
						StyleConstants.setFontSize(attribute, 12);
						StyleConstants.setBold(attribute, true);
						StyleConstants.setForeground(attribute,
								this.TOKEN_COLORS[closerTokenIndex]);
						this.document.insertString(this.document.getLength(),
								tokenMatch, attribute);

					} catch (BadLocationException exception) {
						ExceptionSupport.handleException(exception);
						return;
					}

					suffix = line.substring(closerTokenPosition
							+ tokens[closerTokenIndex].length(), line.length());

					line = suffix;

				} else {

					try {
						SimpleAttributeSet attribute = new SimpleAttributeSet();
						StyleConstants.setFontFamily(attribute, FONT_FAMILY);
						StyleConstants.setFontSize(attribute, 10);
						this.document.insertString(this.document.getLength(),
								line, attribute);
					} catch (BadLocationException exception) {
						ExceptionSupport.handleException(exception);
						return;
					}
					line = ""; //$NON-NLS-1$
				}

			} while (line.length() > 0);

			try {
				SimpleAttributeSet attribute = new SimpleAttributeSet();
				this.document.insertString(this.document.getLength(),
						String.valueOf(StringSupport.getJavaNewLine()),
						attribute);
			} catch (BadLocationException exception) {
				ExceptionSupport.handleException(exception);
				return;
			}

		}

	}

}