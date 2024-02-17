package com.carlosbcruz.filterany.filters;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.carlosbcruz.filterany.FilterAnyLogic;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.TextAreaPanel;
import com.carlosbcruz.filterany.TextAreaPanelDemonstration;
import com.carlosbcruz.filterany.TextAreaPanelSimple;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SimpleActionDecorator;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionProvider;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

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

/**
 * Select the type of table should be generated.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TableFormatSelectionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final int DIALOG_WIDTH = 700;
	private static final int DIALOG_HEIGHT = 500;

	private String content[][] = null;

	private List<TableSymbols> tables = new ArrayList<>();

	// Preview area.
	private TextAreaPanel previewArea = null;

	// The list of tables.
	private JList<Object> tableList = null;

	// The new line characters.
	private String newLine = null;

	// Indicate which type of symbols to be used.
	private boolean useTable = true;

	// The current table selection.
	private int selectedElement = 0;

	// Text generated.
	private StringBuffer output = new StringBuffer();

	// Custom symbols.
	private JTextField beforeCellField = null;
	private JTextField afterCellField = null;
	private JTextField betweenCellField = null;

	// Type of text position.
	enum TEXT_POSITION_TYPES {
		LEFT, RIGHT, CENTER
	}

	private TEXT_POSITION_TYPES currentTextPosition = TEXT_POSITION_TYPES.RIGHT;

	private JRadioButton textOnTheLeft = null;
	private JRadioButton textOnTheRight = null;
	private JRadioButton textOnTheCenter = null;

	private SimpleActionDecorator okAction;
	private SimpleActionDecorator cancelAction;

	/**
	 * @param contentParameter
	 */
	public TableFormatSelectionDialog(String dialogTitle,
			String[][] contentParameter, String newLine) {

		super();

		setTitle(dialogTitle);

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		this.content = contentParameter;
		this.newLine = newLine;

		setSize(DIALOG_WIDTH, DIALOG_HEIGHT);

		int positionX = ((SwingUtil.getScreenWidth() - DIALOG_WIDTH) / 2);
		int positionY = ((SwingUtil.getScreenHeight() - DIALOG_HEIGHT) / 2);

		setLocation(positionX, positionY);

		populateTables();

		// Create the table types selection.

		this.tableList = new JList<>(this.tables.toArray());
		this.tableList.setSelectedIndex(0);
		Dimension listPreferredSize = this.tableList.getPreferredSize();
		listPreferredSize.height = 30;
		this.tableList.setMaximumSize(listPreferredSize);

		JPanel internalPanel = new JPanel(new BorderLayout());

		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		// Create the table selection control.

		JPanel tableSelectionPanel = new JPanel(new BorderLayout());
		tableSelectionPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.TABLE_FORMAT_SELECTION_LIST)));

		JRadioButton tableSelectionChoice = new JRadioButton(
				Text.get(Text.TABLE_FORMAT_SELECTION_LIST_TITLE));

		tableSelectionChoice.addActionListener(new ActionListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				TableFormatSelectionDialog.this.useTable = true;

				TableFormatSelectionDialog.this.textOnTheLeft.setEnabled(true);
				TableFormatSelectionDialog.this.textOnTheRight.setEnabled(true);
				TableFormatSelectionDialog.this.textOnTheCenter
						.setEnabled(true);

				showTextOnPreview();
			}
		});

		tableSelectionChoice.setSelected(true);

		tableSelectionPanel.add(tableSelectionChoice, BorderLayout.NORTH);

		tableSelectionPanel.add(new JScrollPane(this.tableList),
				BorderLayout.CENTER);

		controlPanel.add(tableSelectionPanel);

		// Create the custom selection control.
		JPanel customElementsPanel = new JPanel(new BorderLayout());
		customElementsPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.TABLE_FORMAT_CUSTOM_SELECTION_TITLE)));

		JRadioButton customSelectionChoice = new JRadioButton(
				Text.get(Text.TABLE_FORMAT_CUSTOM_SELECTION_CHOICE));

		customSelectionChoice.addActionListener(new ActionListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {

				TableFormatSelectionDialog.this.useTable = false;

				TableFormatSelectionDialog.this.textOnTheLeft.setEnabled(false);
				TableFormatSelectionDialog.this.textOnTheRight
						.setEnabled(false);
				TableFormatSelectionDialog.this.textOnTheCenter
						.setEnabled(false);

				showTextOnPreview();
			}
		});

		JPanel fieldsPanel = new JPanel(new GridLayout(5, 2));

		JLabel beforeCellLabel = new JLabel(
				Text.get(Text.TABLE_FORMAT_CUSTOM_SYMBOL_BEFORE_CELL));
		JLabel afterCellLabel = new JLabel(
				Text.get(Text.TABLE_FORMAT_CUSTOM_SYMBOL_AFTER_CELL));
		JLabel betweenCellLabel = new JLabel(
				Text.get(Text.TABLE_FORMAT_CUSTOM_SYMBOL_BETWEEN_CELL));

		this.beforeCellField = new JTextField();
		this.afterCellField = new JTextField();
		this.betweenCellField = new JTextField();

		this.beforeCellField.getDocument().addDocumentListener(
				new DocumentListener() {

					@SuppressWarnings("synthetic-access")
					@Override
					public void changedUpdate(DocumentEvent e) {

						showTextOnPreview();
					}

					@SuppressWarnings("synthetic-access")
					@Override
					public void removeUpdate(DocumentEvent e) {

						showTextOnPreview();
					}

					@SuppressWarnings("synthetic-access")
					@Override
					public void insertUpdate(DocumentEvent e) {

						showTextOnPreview();
					}
				});

		this.afterCellField.getDocument().addDocumentListener(
				new DocumentListener() {

					@SuppressWarnings("synthetic-access")
					@Override
					public void changedUpdate(DocumentEvent e) {

						showTextOnPreview();
					}

					@SuppressWarnings("synthetic-access")
					@Override
					public void removeUpdate(DocumentEvent e) {

						showTextOnPreview();
					}

					@SuppressWarnings("synthetic-access")
					@Override
					public void insertUpdate(DocumentEvent e) {

						showTextOnPreview();
					}
				});

		this.betweenCellField.getDocument().addDocumentListener(
				new DocumentListener() {

					@SuppressWarnings("synthetic-access")
					@Override
					public void changedUpdate(DocumentEvent e) {

						showTextOnPreview();
					}

					@SuppressWarnings("synthetic-access")
					@Override
					public void removeUpdate(DocumentEvent e) {

						showTextOnPreview();
					}

					@SuppressWarnings("synthetic-access")
					@Override
					public void insertUpdate(DocumentEvent e) {

						showTextOnPreview();
					}
				});

		Dimension preferredDimention = this.beforeCellField.getPreferredSize();
		preferredDimention.width = 10;
		this.beforeCellField.setMaximumSize(preferredDimention);
		this.afterCellField.setMaximumSize(preferredDimention);
		this.betweenCellField.setMaximumSize(preferredDimention);

		fieldsPanel.add(beforeCellLabel);
		fieldsPanel.add(this.beforeCellField);

		fieldsPanel.add(afterCellLabel);
		fieldsPanel.add(this.afterCellField);

		fieldsPanel.add(betweenCellLabel);
		fieldsPanel.add(this.betweenCellField);

		fieldsPanel.add(new JLabel(Text
				.get(Text.FILTER_WORD_SELECTION_TO_INSTRUCTION_1)));
		fieldsPanel.add(new JLabel(Text
				.get(Text.FILTER_WORD_SELECTION_TO_INSTRUCTION_2)));

		fieldsPanel.add(new JLabel(Text
				.get(Text.FILTER_WORD_SELECTION_TO_INSTRUCTION_3)));
		fieldsPanel.add(new JLabel(Text
				.get(Text.FILTER_WORD_SELECTION_TO_INSTRUCTION_4)));

		customElementsPanel.add(customSelectionChoice, BorderLayout.NORTH);
		customElementsPanel.add(fieldsPanel, BorderLayout.SOUTH);

		controlPanel.add(customElementsPanel);

		// Create the text position selection.

		JPanel textPositionPanel = new JPanel(new GridLayout(3, 1));
		textPositionPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_TEXT_POSITION)));

		this.textOnTheLeft = new JRadioButton(
				Text.get(Text.FILTER_WORD_SELECTION_TO_TABLE_LEFT));
		this.textOnTheRight = new JRadioButton(
				Text.get(Text.FILTER_WORD_SELECTION_TO_TABLE_RIGHT));
		this.textOnTheCenter = new JRadioButton(
				Text.get(Text.FILTER_WORD_SELECTION_TO_TABLE_CENTER));

		ButtonGroup textPositionRadioGroup = new ButtonGroup();
		textPositionRadioGroup.add(this.textOnTheLeft);
		textPositionRadioGroup.add(this.textOnTheRight);
		textPositionRadioGroup.add(this.textOnTheCenter);

		this.textOnTheRight.setSelected(true);

		textPositionPanel.add(this.textOnTheLeft);
		textPositionPanel.add(this.textOnTheRight);
		textPositionPanel.add(this.textOnTheCenter);

		controlPanel.add(textPositionPanel);

		this.textOnTheLeft.addActionListener(new ActionListener() {

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
				TableFormatSelectionDialog.this.currentTextPosition = TEXT_POSITION_TYPES.LEFT;
				showTextOnPreview();
			}
		});

		this.textOnTheRight.addActionListener(new ActionListener() {

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
				TableFormatSelectionDialog.this.currentTextPosition = TEXT_POSITION_TYPES.RIGHT;
				showTextOnPreview();
			}
		});

		this.textOnTheCenter.addActionListener(new ActionListener() {

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
				TableFormatSelectionDialog.this.currentTextPosition = TEXT_POSITION_TYPES.CENTER;
				showTextOnPreview();
			}
		});

		// Create the buttons selection control.

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(tableSelectionChoice);
		radioGroup.add(customSelectionChoice);

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));

		this.okAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "ok-icon.png", //$NON-NLS-1$
						Text.get(Text.FILTER_WORD_SELECTION_TO_TABLE_OK_BUTTOM),
						0,
						Text.get(Text.FILTER_WORD_SELECTION_TO_TABLE_OK_BUTTOM_INSTRUCTION));
		this.cancelAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "cancel-icon.png", //$NON-NLS-1$
						Text.get(Text.FILTER_WORD_SELECTION_TO_TABLE_CANCEL_BUTTOM),
						0,
						Text.get(Text.FILTER_WORD_SELECTION_TO_TABLE_CANCEL_BUTTOM_INSTRUCTION));

		this.okAction.addObserver(new SimpleActionObserver() {

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
				TableFormatSelectionDialog.this.previewArea.closingTextArea();
				setVisible(false);
			}
		});

		this.cancelAction.addObserver(new SimpleActionObserver() {

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
				TableFormatSelectionDialog.this.previewArea.closingTextArea();
				TableFormatSelectionDialog.this.output = new StringBuffer();
				setVisible(false);
			}
		});

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
			public void windowClosing(WindowEvent e) {

				TableFormatSelectionDialog.this.previewArea.closingTextArea();
				TableFormatSelectionDialog.this.output = new StringBuffer();

				super.windowClosing(e);
			}

		});

		buttonsPanel.add(new JButton(this.okAction));
		buttonsPanel.add(new JButton(this.cancelAction));

		controlPanel.add(buttonsPanel);

		internalPanel.add(controlPanel, BorderLayout.SOUTH);

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {

			// Add the text area where the previews are presented.
			this.previewArea = new TextAreaPanelDemonstration();

			this.previewArea.setBackground(new Color(255, 255, 255));

		} else {

			// Add the text area where the previews are presented.
			this.previewArea = new TextAreaPanelSimple();

		}

		this.previewArea.setFont(FilterAnyConfiguration.getTextAreaFont());
		internalPanel.add(this.previewArea, BorderLayout.CENTER);

		// Add the event listeners to the font list.
		this.tableList.addListSelectionListener(new ListSelectionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing
			 * .event.ListSelectionEvent)
			 */
			@SuppressWarnings({ "synthetic-access", "unchecked" })
			@Override
			public void valueChanged(ListSelectionEvent event) {

				TableFormatSelectionDialog.this.selectedElement = ((JList<Object>) event
						.getSource()).getSelectedIndex();

				showTextOnPreview();
			}
		});

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

				TableFormatSelectionDialog.this.previewArea.validate();
			}
		});
		redrawLineNumber.start();

		setContentPane(internalPanel);

		showTextOnPreview();

		setModal(true);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setVisible(true);

	}

	/**
	 * The symbols used to create a table.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class TableSymbols implements Serializable, Cloneable {

		private static final long serialVersionUID = 1L;

		String name = new String(); // The name of the set of symbols.
		String topLeft = new String(); // The top left symbol of a table.
		String topBorder = new String(); // The top line symbol of a table.
		String topColumnDivision = new String(); // The top column division
		// symbol of a table.
		String topRight = new String(); // The top right symbol of a table.
		String leftBorder = new String(); // The symbol used on the left border
		// of the table.
		String verticalLine = new String(); // The symbol used on vertical lines
		// of the table.
		String rightBorder = new String(); // the symbol used on the right
		// border of the table.
		String leftInternal = new String(); // The symbol on left part of the
		// internal horizontal line.
		String horizontalLine = new String(); // The symbol for horizontal
		// internal line.
		String centralInternal = new String(); // The symbol on central part of
		// the internal horizontal line.
		String rightnternal = new String(); // The symbol on right part of the
		// internal horizontal line.
		String bottomLeft = new String(); // The lower left symbol of a table.
		String bottomBorder = new String(); // The lower line symbol of a table.
		String lowerColumnDivision = new String(); // The lower column division
		// symbol of a table.
		String bottomRight = new String(); // The lower right symbol of a table.

		/**
		 * Default constructor.
		 */
		public TableSymbols() {

			super();
		}

		/**
		 * @param String
		 *            nameParameter The name of the set of symbols.
		 * @param String
		 *            topLeftParameter The top left symbol of a table.
		 * @param String
		 *            topBorderParameter The top line symbol of a table.
		 * @param String
		 *            topColumnDivisionParameter The top column division symbol
		 *            of a table.
		 * @param String
		 *            topRightParameter The top right symbol of a table.
		 * @param String
		 *            leftBorderParameter The symbol used on the left border of
		 *            the table.
		 * @param String
		 *            verticalLineParameter The symbol used on vertical lines of
		 *            the table.
		 * @param String
		 *            rightBorderParameter the symbol used on the right border
		 *            of the table.
		 * @param String
		 *            leftInternalParameter The symbol on left part of the
		 *            internal horizontal line.
		 * @param String
		 *            horizontalLineParameter The symbol for horizontal internal
		 *            line.
		 * @param String
		 *            centralInternalParameter The symbol on central part of the
		 *            internal horizontal line.
		 * @param String
		 *            rightnternalParameter The symbol on right part of the
		 *            internal horizontal line.
		 * @param String
		 *            bottomLeftParameter The lower left symbol of a table.
		 * @param String
		 *            bottomBorderParameter The lower line symbol of a table.
		 * @param String
		 *            lowerColumnDivisionParameter The lower column division
		 *            symbol of a table.
		 * @param String
		 *            bottomRightParameter The lower right symbol of a table.
		 */
		public TableSymbols(String nameParameter, String topLeftParameter,
				String topBorderParameter, String topColumnDivisionParameter,
				String topRightParameter, String leftBorderParameter,
				String verticalLineParameter, String rightBorderParameter,
				String leftInternalParameter, String horizontalLineParameter,
				String centralInternalParameter, String rightnternalParameter,
				String bottomLeftParameter, String bottomBorderParameter,
				String lowerColumnDivisionParameter, String bottomRightParameter) {

			super();

			this.name = nameParameter;
			this.topLeft = topLeftParameter;
			this.topBorder = topBorderParameter;
			this.topColumnDivision = topColumnDivisionParameter;
			this.topRight = topRightParameter;
			this.leftBorder = leftBorderParameter;
			this.verticalLine = verticalLineParameter;
			this.rightBorder = rightBorderParameter;
			this.leftInternal = leftInternalParameter;
			this.horizontalLine = horizontalLineParameter;
			this.centralInternal = centralInternalParameter;
			this.rightnternal = rightnternalParameter;
			this.bottomLeft = bottomLeftParameter;
			this.bottomBorder = bottomBorderParameter;
			this.lowerColumnDivision = lowerColumnDivisionParameter;
			this.bottomRight = bottomRightParameter;
		}

		/**
		 * Provide: The name of the set of symbols.
		 * 
		 * @return name The name of the set of symbols.
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * Set: The name of the set of symbols.
		 * 
		 * @param name
		 *            The name of the set of symbols.
		 */
		public void setName(String nameParameter) {
			this.name = nameParameter;
		}

		/**
		 * Provide: The top left symbol of a table.
		 * 
		 * @return topLeft The top left symbol of a table.
		 */
		public String getTopLeft() {
			return this.topLeft;
		}

		/**
		 * Set: The top left symbol of a table.
		 * 
		 * @param topLeft
		 *            The top left symbol of a table.
		 */
		public void setTopLeft(String topLeftParameter) {
			this.topLeft = topLeftParameter;
		}

		/**
		 * Provide: The top line symbol of a table.
		 * 
		 * @return topBorder The top line symbol of a table.
		 */
		public String getTopBorder() {
			return this.topBorder;
		}

		/**
		 * Set: The top line symbol of a table.
		 * 
		 * @param topBorder
		 *            The top line symbol of a table.
		 */
		public void setTopBorder(String topBorderParameter) {
			this.topBorder = topBorderParameter;
		}

		/**
		 * Provide: The top column division symbol of a table.
		 * 
		 * @return topColumnDivision The top column division symbol of a table.
		 */
		public String getTopColumnDivision() {
			return this.topColumnDivision;
		}

		/**
		 * Set: The top column division symbol of a table.
		 * 
		 * @param topColumnDivision
		 *            The top column division symbol of a table.
		 */
		public void setTopColumnDivision(String topColumnDivisionParameter) {
			this.topColumnDivision = topColumnDivisionParameter;
		}

		/**
		 * Provide: The top right symbol of a table.
		 * 
		 * @return topRight The top right symbol of a table.
		 */
		public String getTopRight() {
			return this.topRight;
		}

		/**
		 * Set: The top right symbol of a table.
		 * 
		 * @param topRight
		 *            The top right symbol of a table.
		 */
		public void setTopRight(String topRightParameter) {
			this.topRight = topRightParameter;
		}

		/**
		 * Provide: The symbol used on the left border of the table.
		 * 
		 * @return leftBorder The symbol used on the left border of the table.
		 */
		public String getLeftBorder() {
			return this.leftBorder;
		}

		/**
		 * Set: The symbol used on the left border of the table.
		 * 
		 * @param leftBorder
		 *            The symbol used on the left border of the table.
		 */
		public void setLeftBorder(String leftBorderParameter) {
			this.leftBorder = leftBorderParameter;
		}

		/**
		 * Provide: The symbol used on vertical lines of the table.
		 * 
		 * @return verticalLine The symbol used on vertical lines of the table.
		 */
		public String getVerticalLine() {
			return this.verticalLine;
		}

		/**
		 * Set: The symbol used on vertical lines of the table.
		 * 
		 * @param verticalLine
		 *            The symbol used on vertical lines of the table.
		 */
		public void setVerticalLine(String verticalLineParameter) {
			this.verticalLine = verticalLineParameter;
		}

		/**
		 * Provide: the symbol used on the right border of the table.
		 * 
		 * @return rightBorder the symbol used on the right border of the table.
		 */
		public String getRightBorder() {
			return this.rightBorder;
		}

		/**
		 * Set: the symbol used on the right border of the table.
		 * 
		 * @param rightBorder
		 *            the symbol used on the right border of the table.
		 */
		public void setRightBorder(String rightBorderParameter) {
			this.rightBorder = rightBorderParameter;
		}

		/**
		 * Provide: The symbol on left part of the internal horizontal line.
		 * 
		 * @return leftInternal The symbol on left part of the internal
		 *         horizontal line.
		 */
		public String getLeftInternal() {
			return this.leftInternal;
		}

		/**
		 * Set: The symbol on left part of the internal horizontal line.
		 * 
		 * @param leftInternal
		 *            The symbol on left part of the internal horizontal line.
		 */
		public void setLeftInternal(String leftInternalParameter) {
			this.leftInternal = leftInternalParameter;
		}

		/**
		 * Provide: The symbol for horizontal internal line.
		 * 
		 * @return horizontalLine The symbol for horizontal internal line.
		 */
		public String getHorizontalLine() {
			return this.horizontalLine;
		}

		/**
		 * Set: The symbol for horizontal internal line.
		 * 
		 * @param horizontalLine
		 *            The symbol for horizontal internal line.
		 */
		public void setHorizontalLine(String horizontalLineParameter) {
			this.horizontalLine = horizontalLineParameter;
		}

		/**
		 * Provide: The symbol on central part of the internal horizontal line.
		 * 
		 * @return centralInternal The symbol on central part of the internal
		 *         horizontal line.
		 */
		public String getCentralInternal() {
			return this.centralInternal;
		}

		/**
		 * Set: The symbol on central part of the internal horizontal line.
		 * 
		 * @param centralInternal
		 *            The symbol on central part of the internal horizontal
		 *            line.
		 */
		public void setCentralInternal(String centralInternalParameter) {
			this.centralInternal = centralInternalParameter;
		}

		/**
		 * Provide: The symbol on right part of the internal horizontal line.
		 * 
		 * @return rightnternal The symbol on right part of the internal
		 *         horizontal line.
		 */
		public String getRightnternal() {
			return this.rightnternal;
		}

		/**
		 * Set: The symbol on right part of the internal horizontal line.
		 * 
		 * @param rightnternal
		 *            The symbol on right part of the internal horizontal line.
		 */
		public void setRightnternal(String rightnternalParameter) {
			this.rightnternal = rightnternalParameter;
		}

		/**
		 * Provide: The lower left symbol of a table.
		 * 
		 * @return bottomLeft The lower left symbol of a table.
		 */
		public String getBottomLeft() {
			return this.bottomLeft;
		}

		/**
		 * Set: The lower left symbol of a table.
		 * 
		 * @param bottomLeft
		 *            The lower left symbol of a table.
		 */
		public void setBottomLeft(String bottomLeftParameter) {
			this.bottomLeft = bottomLeftParameter;
		}

		/**
		 * Provide: The lower line symbol of a table.
		 * 
		 * @return bottomBorder The lower line symbol of a table.
		 */
		public String getBottomBorder() {
			return this.bottomBorder;
		}

		/**
		 * Set: The lower line symbol of a table.
		 * 
		 * @param bottomBorder
		 *            The lower line symbol of a table.
		 */
		public void setBottomBorder(String bottomBorderParameter) {
			this.bottomBorder = bottomBorderParameter;
		}

		/**
		 * Provide: The lower column division symbol of a table.
		 * 
		 * @return lowerColumnDivision The lower column division symbol of a
		 *         table.
		 */
		public String getLowerColumnDivision() {
			return this.lowerColumnDivision;
		}

		/**
		 * Set: The lower column division symbol of a table.
		 * 
		 * @param lowerColumnDivision
		 *            The lower column division symbol of a table.
		 */
		public void setLowerColumnDivision(String lowerColumnDivisionParameter) {
			this.lowerColumnDivision = lowerColumnDivisionParameter;
		}

		/**
		 * Provide: The lower right symbol of a table.
		 * 
		 * @return bottomRight The lower right symbol of a table.
		 */
		public String getBottomRight() {
			return this.bottomRight;
		}

		/**
		 * Set: The lower right symbol of a table.
		 * 
		 * @param bottomRight
		 *            The lower right symbol of a table.
		 */
		public void setBottomRight(String bottomRightParameter) {
			this.bottomRight = bottomRightParameter;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {

			return this.name;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#clone()
		 */
		@Override
		public Object clone() throws CloneNotSupportedException {

			return super.clone();
		}

	}

	/**
	 * Populate the various types of tables.
	 */
	private void populateTables() {

		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_SIMPLE), "*", //$NON-NLS-1$
				"-", "-", "*", "|", "|", "|", "|", "-", "+", "|", "*", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$
				"-", "-", "*")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_ROUNDED), "/", "-", //$NON-NLS-1$ //$NON-NLS-2$
				"-", "\\", "|", "|", "|", "|", "-", "+", "|", "\\", "-", "-", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"/")); //$NON-NLS-1$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_SINGLE_LINE), "┌", //$NON-NLS-1$
				"─", "┬", "┐", "│", "│", "│", "├", "─", "┼", "┤", "└", "─", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"┴", "┘")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_SHADOW_LIGHT), "░", //$NON-NLS-1$
				"░", "░", "░", "░", "░", "░", "░", "░", "░", "░", "░", "░", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"░", "░")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_SHADOW_MEDIUM), "▒", //$NON-NLS-1$
				"▒", "▒", "▒", "▒", "▒", "▒", "▒", "▒", "▒", "▒", "▒", "▒", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"▒", "▒")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_SHADOW_MEDIUM_2), "▒", //$NON-NLS-1$
				"▒", "▒", "▒", "▒", "░", "▒", "▒", "░", "░", "▒", "▒", "▒", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"▒", "▒")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_SHADOW_STRONG), "▓", //$NON-NLS-1$
				"▓", "▓", "▓", "▓", "▓", "▓", "▓", "▓", "▓", "▓", "▓", "▓", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"▓", "▓")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_SHADOW_STRONG_2), "▓", //$NON-NLS-1$
				"▓", "▓", "▓", "▓", "░", "▓", "▓", "░", "░", "▓", "▓", "▓", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"▓", "▓")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_SHADOW_STRONG_3), "▓", //$NON-NLS-1$
				"▓", "▓", "▓", "▓", "▒", "▓", "▓", "▒", "▒", "▓", "▓", "▓", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"▓", "▓")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_THICK_LINES), "█", //$NON-NLS-1$
				"▀", "█", "█", "█", "█", "█", "█", "■", "█", "█", "█", "▄", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"█", "█")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_THICK_LINES_2), "█", //$NON-NLS-1$
				"▀", "▀", "█", "█", "░", "█", "█", "░", "░", "█", "█", "▄", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"▄", "█")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_THICK_LINES_3), "█", //$NON-NLS-1$
				"▀", "▀", "█", "█", "▒", "█", "█", "▒", "▒", "█", "█", "▄", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"▄", "█")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_THICK_LINES_4), "█", //$NON-NLS-1$
				"▀", "▀", "█", "█", "▓", "█", "█", "▓", "▓", "█", "█", "▄", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"▄", "█")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_LINE), "╔", //$NON-NLS-1$
				"═", "╦", "╗", "║", "║", "║", "╠", "═", "╬", "╣", "╚", "═", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"╩", "╝")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_BORDER), "╔", //$NON-NLS-1$
				"═", "╤", "╗", "║", "│", "║", "╟", "─", "┼", "╢", "╚", "═", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"╧", "╝")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_INTERNAL), "┌", //$NON-NLS-1$
				"─", "╥", "┐", "│", "║", "│", "╞", "═", "╬", "╡", "└", "─", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"╨", "┘")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_HORIZONTAL),
				"┌", "─", "┬", "┐", "│", "│", "│", "╞", "═", "╪", "╡", "└", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"─", "┴", "┘")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_VERTICAL), "┌", //$NON-NLS-1$
				"─", "╥", "┐", "│", "║", "│", "├", "─", "╫", "┤", "└", "─", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"╨", "┘")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_HORIZONTAL_2),
				"╒", "═", "╤", "╕", "│", "│", "│", "╞", "═", "╪", "╡", "╘", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"═", "╧", "╛")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_DOUBLE_VERTICAL_2),
				"╓", "─", "╥", "╖", "║", "║", "║", "╟", "─", "╫", "╢", "╙", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"─", "╨", "╜")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_ONLY_BORDER_1), "╔", //$NON-NLS-1$
				"═", "═", "╗", "║", " ", "║", "║", " ", " ", "║", "╚", "═", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"═", "╝")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_ONLY_BORDER_2), "┌", //$NON-NLS-1$
				"─", "─", "┐", "│", " ", "│", "│", " ", " ", "│", "└", "─", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"─", "┘")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_ONLY_BORDER_3), "╒", //$NON-NLS-1$
				"═", "═", "╕", "│", " ", "│", "│", " ", " ", "│", "╘", "═", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"═", "╛")); //$NON-NLS-1$ //$NON-NLS-2$
		this.tables.add(new TableSymbols(Text
				.get(Text.FILTER_WORD_SELECTION_TO_TABLE_ONLY_BORDER_4), "╓", //$NON-NLS-1$
				"─", "─", "╖", "║", " ", "║", "║", " ", " ", "║", "╙", "─", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$
				"─", "╜")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Calculate the length of the columns.
	 * 
	 * @return The length of the columns.
	 */
	private int[] columnsLength() {

		int numberOfColumns = this.content[0].length;

		int length[] = new int[numberOfColumns];

		for (int column = 0; column < numberOfColumns; column++) {

			length[column] = 0;

			for (int line = 0; line < this.content.length; line++) {

				if (this.content[line][column] != null) {
					if (this.content[line][column].length() > length[column]) {
						length[column] = this.content[line][column].length();
					}

				}
			}
		}

		return length;

	}

	/**
	 * Show the table preview for a set of table.
	 */
	private void showTextOnPreview() {

		TableSymbols tableSymbols = this.tables.get(this.selectedElement);

		int length[] = columnsLength();

		this.output = new StringBuffer();

		for (int line = 0; line < this.content.length; line++) {

			String[] rowContent = this.content[line];

			if (this.useTable) {

				if (line == 0) {

					this.output.append(tableSymbols.getTopLeft());

					for (int column = 0; column < length.length; column++) {

						for (int i = 0; i < length[column]; i++) {

							this.output.append(tableSymbols.getTopBorder());
						}

						if (column != length.length - 1) {
							this.output.append(tableSymbols
									.getTopColumnDivision());
						}
					}
					this.output.append(tableSymbols.getTopRight());

					this.output.append(this.newLine);
				}
			}

			for (int column = 0; column < rowContent.length; column++) {

				if (this.useTable) {
					if (column == 0) {
						this.output.append(tableSymbols.getLeftBorder());
					}
				}

				if (this.content[line][column] == null) {

					if (this.useTable) {
						for (int i = 0; i < length[column]; i++) {
							this.output.append(" "); //$NON-NLS-1$
						}
					}

				} else {

					if (this.useTable) {

						if (this.currentTextPosition == TEXT_POSITION_TYPES.RIGHT) {
							this.output
									.append(StringSupport.adjustSizeRight(
											this.content[line][column],
											length[column]));
						}
						if (this.currentTextPosition == TEXT_POSITION_TYPES.LEFT) {
							this.output
									.append(StringSupport.adjustSizeLeft(
											this.content[line][column],
											length[column]));
						}
						if (this.currentTextPosition == TEXT_POSITION_TYPES.CENTER) {
							this.output
									.append(StringSupport.adjustSizeCenter(
											this.content[line][column],
											length[column]));
						}

					} else {

						this.output.append(FilterAnyLogic
								.replaceSpecialCharacters(this.beforeCellField
										.getText()));
						this.output.append(String
								.valueOf(this.content[line][column]));
						this.output.append(FilterAnyLogic
								.replaceSpecialCharacters(this.afterCellField
										.getText()));

						if (column != rowContent.length - 1) {

							if ("".equals(FilterAnyLogic //$NON-NLS-1$
									.replaceSpecialCharacters(this.betweenCellField
											.getText()))) {
								this.output.append(" "); //$NON-NLS-1$
							} else {
								this.output
										.append(FilterAnyLogic
												.replaceSpecialCharacters(this.betweenCellField
														.getText()));
							}
						}
					}
				}

				if (this.useTable) {

					if (column == rowContent.length - 1) {
						this.output.append(tableSymbols.getRightBorder());
					} else {
						this.output.append(tableSymbols.getVerticalLine());
					}
				}
			}
			this.output.append(this.newLine);

			if (this.useTable) {

				if (line == this.content.length - 1) {

					this.output.append(tableSymbols.getBottomLeft());

					for (int column = 0; column < length.length; column++) {

						for (int i = 0; i < length[column]; i++) {

							this.output.append(tableSymbols.getBottomBorder());
						}

						if (column != length.length - 1) {
							this.output.append(tableSymbols
									.getLowerColumnDivision());
						}
					}
					this.output.append(tableSymbols.getBottomRight());

					this.output.append(this.newLine);

				} else {

					this.output.append(tableSymbols.getLeftInternal());

					for (int column = 0; column < length.length; column++) {

						for (int i = 0; i < length[column]; i++) {

							this.output
									.append(tableSymbols.getHorizontalLine());
						}

						if (column != length.length - 1) {
							this.output.append(tableSymbols
									.getCentralInternal());
						}
					}
					this.output.append(tableSymbols.getRightnternal());

					this.output.append(this.newLine);
				}
			}
		}

		this.previewArea.setTextArea(this.output.toString());
		this.previewArea.setCaretPosition(0);
	}

	/**
	 * Provide the content on a table format.
	 * 
	 * @return the output The content on a table format.
	 */
	public StringBuffer getTextOutput() {

		return this.output;
	}

}
