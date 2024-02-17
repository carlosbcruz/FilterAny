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
package com.carlosbcruz.filterany.filters;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SimpleActionDecorator;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionProvider;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.SwingUtil;

/**
 * A dialog to hold the free selection canvas to select words.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public abstract class FreeSelectionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// Minimum windows dimension.
	private static final int MINIMUM_WIDTH = 700;
	private static final int MINIMUM_HEIGHT = 600;

	private FreeSelectionCanvas canvas = null;

	private JPanel tablePanel = null;
	private FreeSelectionTable table = null;
	private FreeSelectionTableModel tableModel = null;

	private SimpleActionDecorator invertAction;
	private SimpleActionDecorator okAction;
	private SimpleActionDecorator cancelAction;
	private SimpleActionDecorator addColumnToTheRightAction;
	private SimpleActionDecorator addColumnToTheLeftAction;
	private SimpleActionDecorator removeSelectedColumnAction;

	private JDialog thisDialog = null;

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The window title.
	 * @param contentParameter
	 *            A text.
	 * @param tableSelection
	 *            Indicate if a table creation is necessary.
	 */
	public FreeSelectionDialog(String title, StringBuffer contentParameter,
			boolean tableSelection) {

		setTitle(title);

		this.thisDialog = this;

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		// Get the canvas from sub-classes.
		this.canvas = getCanvas(contentParameter);

		int maximumDialogWidth = (int) (SwingUtil.getScreenWidth() * 0.7);
		int maximumDialogHeight = (int) (SwingUtil.getScreenHeight() * 0.9);

		int dialogWidth = this.canvas.canvasWidth + 100;
		int dialogHeight = this.canvas.canvasHeight + 100;

		if (dialogWidth > maximumDialogWidth) {
			dialogWidth = maximumDialogWidth;
		}
		if (dialogWidth < MINIMUM_WIDTH) {
			dialogWidth = MINIMUM_WIDTH;
		}

		if (dialogHeight > maximumDialogHeight) {
			dialogHeight = maximumDialogHeight;
		}
		if (dialogHeight < MINIMUM_HEIGHT) {
			dialogHeight = MINIMUM_HEIGHT;
		}

		setSize(dialogWidth, dialogHeight);

		int positionX = ((SwingUtil.getScreenWidth() - dialogWidth) / 2);
		int positionY = ((SwingUtil.getScreenHeight() - dialogHeight) / 2);

		setLocation(positionX, positionY);

		this.invertAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "invert-icon.png", //$NON-NLS-1$
				Text.get(Text.ADD_SELECTED_ELEMENT_INVERT), 0,
				Text.get(Text.ADD_SELECTED_ELEMENT_INVERT_INSTRUCTION));
		this.okAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "ok-icon.png", Text //$NON-NLS-1$
						.get(Text.ADD_SELECTED_ELEMENT_OK), 0,
				Text.get(Text.ADD_SELECTED_ELEMENT_OK_INSTRUCTION));
		this.cancelAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "cancel-icon.png", //$NON-NLS-1$
				Text.get(Text.ADD_SELECTED_ELEMENT_CANCEL), 0,
				Text.get(Text.ADD_SELECTED_ELEMENT_CANCEL_INSTRUCTION));

		if (tableSelection) {

			this.tablePanel = new JPanel(new BorderLayout());

			this.tableModel = new FreeSelectionTableModel();
			this.table = new FreeSelectionTable(this.tableModel);

			this.tablePanel.add(new JScrollPane(this.table),
					BorderLayout.CENTER);

			JPanel selectionPanel = new JPanel(new BorderLayout());
			selectionPanel.add(new JScrollPane(this.canvas),
					BorderLayout.CENTER);

			this.invertAction.addObserver(new SimpleActionObserver() {

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
					FreeSelectionDialog.this.canvas.invertSelection();
				}
			});
			
			this.okAction.addObserver(new SimpleActionObserver() {

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
					FreeSelectionDialog.this.table.removeAllColumns();
					setVisible(false);
				}
			});

			JPanel buttonPanel = new JPanel();
			buttonPanel.add(new JLabel(Text
					.get(Text.ADD_SELECTED_ELEMENT_INSTRUCTION)));
			buttonPanel.add(new JButton(this.invertAction));
			buttonPanel.add(new JButton(this.okAction));
			buttonPanel.add(new JButton(this.cancelAction));

			selectionPanel.add(buttonPanel, BorderLayout.SOUTH);

			JPanel controlPanel = new JPanel();

			this.addColumnToTheRightAction = SimpleActionProvider
					.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "arrow-turn-right-icon.png", Text //$NON-NLS-1$
							.get(Text.ADD_COLUMN_TO_THE_RIGHT_BUTTOM),
							SwingUtil.getKeyEvent(Text
									.get(Text.ADD_COLUMN_TO_THE_RIGHT_KEY)),
							Text.get(Text.ADD_COLUMN_TO_THE_RIGHT_INSTRUCTIONS));

			this.addColumnToTheLeftAction = SimpleActionProvider
					.getSimpleAction(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "arrow-turn-left-icon.png", Text //$NON-NLS-1$
							.get(Text.ADD_COLUMN_TO_THE_LEFT_BUTTOM), SwingUtil
							.getKeyEvent(Text
									.get(Text.ADD_COLUMN_TO_THE_LEFT_KEY)),
							Text.get(Text.ADD_COLUMN_TO_THE_LEFT_INSTRUCTIONS));

			this.removeSelectedColumnAction = SimpleActionProvider
					.getSimpleAction(
							FilterAnyConfiguration.RESOURCE_LOCATION
									+ "delete-column-icon.png", Text //$NON-NLS-1$
									.get(Text.REMOVE_SELECTED_COLUMN_BUTTON_TITLE),
							0,
							Text.get(Text.REMOVE_SELECTED_COLUMN_BUTTON_INSTRUCTIONS));

			this.addColumnToTheRightAction
					.addObserver(new SimpleActionObserver() {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * com.carlosbcruz.util.SimpleActionObserver#update(
						 * com.carlosbcruz .util.SimpleActionSubject)
						 */
						@SuppressWarnings("synthetic-access")
						@Override
						public void update(SimpleActionSubject simpleSubject) {

							ArrayList<ArrayList<SelectedElement>> selectedLines = FreeSelectionDialog.this.canvas
									.getSelectedLines();

							boolean hasSelectedElements = hasElements(selectedLines);

							if (!hasSelectedElements) {
								JOptionPane
										.showMessageDialog(
												FreeSelectionDialog.this.thisDialog,
												Text.get(Text.ADD_SELECTED_ERROR_NO_SELECTION_MESSAGE),
												Text.get(Text.ADD_SELECTED_ERROR_NO_SELECTION_MESSAGE_TITLE),
												JOptionPane.OK_OPTION);
							} else {

								FreeSelectionDialog.this.tableModel
										.addColumnToTheRight(selectedLines);
								FreeSelectionDialog.this.table.adaptLayout();

								FreeSelectionDialog.this.canvas.repaint();
							}
						}
					});

			this.addColumnToTheLeftAction
					.addObserver(new SimpleActionObserver() {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * com.carlosbcruz.util.SimpleActionObserver#update(
						 * com.carlosbcruz .util.SimpleActionSubject)
						 */
						@SuppressWarnings("synthetic-access")
						@Override
						public void update(SimpleActionSubject simpleSubject) {

							ArrayList<ArrayList<SelectedElement>> selectedLines = FreeSelectionDialog.this.canvas
									.getSelectedLines();

							boolean hasSelectedElements = hasElements(selectedLines);

							if (!hasSelectedElements) {
								JOptionPane
										.showMessageDialog(
												FreeSelectionDialog.this.thisDialog,
												Text.get(Text.ADD_SELECTED_ERROR_NO_SELECTION_MESSAGE),
												Text.get(Text.ADD_SELECTED_ERROR_NO_SELECTION_MESSAGE_TITLE),
												JOptionPane.OK_OPTION);
							} else {

								FreeSelectionDialog.this.tableModel
										.addColumnToTheLeft(selectedLines);
								FreeSelectionDialog.this.table.adaptLayout();

								FreeSelectionDialog.this.canvas.repaint();
							}
						}
					});

			this.removeSelectedColumnAction
					.addObserver(new SimpleActionObserver() {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * com.carlosbcruz.util.SimpleActionObserver#update(
						 * com.carlosbcruz .util.SimpleActionSubject)
						 */
						@SuppressWarnings("synthetic-access")
						@Override
						public void update(SimpleActionSubject simpleSubject) {

							int[] selectedColumns = FreeSelectionDialog.this.table
									.getSelectedColumns();

							if (selectedColumns.length == 0) {
								JOptionPane
										.showMessageDialog(
												FreeSelectionDialog.this.thisDialog,
												Text.get(Text.REMOVE_SELECTED_COLUMN_ERROR_MESSAGE),
												Text.get(Text.REMOVE_SELECTED_COLUMN_ERROR_MESSAGE_TITLE),
												JOptionPane.OK_OPTION);
							} else {

								FreeSelectionDialog.this.table
										.removeColumn(selectedColumns);
								FreeSelectionDialog.this.table.adaptLayout();

								FreeSelectionDialog.this.canvas.repaint();
							}
						}

					});

			JButton right = new JButton(this.addColumnToTheRightAction);
			JButton left = new JButton(this.addColumnToTheLeftAction);
			JButton removeColumn = new JButton(this.removeSelectedColumnAction);
			controlPanel.add(left);
			controlPanel.add(right);
			controlPanel.add(removeColumn);
			selectionPanel.add(controlPanel, BorderLayout.NORTH);

			JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
					this.tablePanel, selectionPanel);

			add(splitPane);

			splitPane.setDividerLocation((int) (dialogHeight * 0.3));

		} else {

			JPanel internalPanel = new JPanel(new BorderLayout());
			internalPanel
					.add(new JScrollPane(this.canvas), BorderLayout.CENTER);

			this.invertAction.addObserver(new SimpleActionObserver() {

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
					FreeSelectionDialog.this.canvas.invertSelection();
				}
			});
			
			this.okAction.addObserver(new SimpleActionObserver() {

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

					FreeSelectionDialog.this.canvas.selecteAllElements();

					setVisible(false);
				}
			});

			JPanel buttonPanel = new JPanel();

			buttonPanel.add(new JLabel(Text
					.get(Text.SELECT_ELEMENT_INSTRUCTION)));

			buttonPanel.add(new JButton(this.invertAction));
			buttonPanel.add(new JButton(this.okAction));
			buttonPanel.add(new JButton(this.cancelAction));

			internalPanel.add(buttonPanel, BorderLayout.SOUTH);

			add(internalPanel);
		}

		setModal(true);

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
			public void windowClosing(WindowEvent e) {

				FreeSelectionDialog.this.canvas.selecteAllElements();

				setVisible(false);
			}
		});

		setVisible(true);
	}

	/**
	 * Indicate if there are selected elements.
	 * 
	 * @param selectedLines
	 *            The elements.
	 * @return True if there is at least one selected element.
	 */
	private static boolean hasElements(
			ArrayList<ArrayList<SelectedElement>> selectedLines) {

		boolean hasSelectedElements = false;

		for (ArrayList<SelectedElement> line : selectedLines) {
			if (line.size() > 0) {
				hasSelectedElements = true;
			}
		}

		return hasSelectedElements;
	}

	/**
	 * Inform the canvas being used.
	 * 
	 * @param contentParameter
	 *            The text to work with.
	 * @return The canvas.
	 */
	abstract FreeSelectionCanvas getCanvas(StringBuffer contentParameter);

	/**
	 * Provide the current selection.
	 * 
	 * @return the lines The selection.
	 */
	protected ArrayList<ArrayList<SelectedElement>> getLines() {

		return this.canvas.getLines();
	}

	/**
	 * Provide the table content.
	 * 
	 * @return The table content.
	 */
	public ArrayList<ArrayList<ArrayList<SelectedElement>>> getTableContent() {

		return this.table.getTableContent();
	}

}
