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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.carlosbcruz.filterany.TextBridgeFactory.TextBridge;
import com.carlosbcruz.filterany.TextBridgeFactory.TextBridge.TextInterfaceBean;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.DurationBean;
import com.carlosbcruz.util.DurationUtil;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Controls the pending tasks.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TaskManager extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel internalPanel = new JPanel(new BorderLayout());

	private JPanel tablePanel = null;

	private JTable taskTable = null;

	private TextBridge bridge = TextBridgeFactory.getInstance();

	private static final int BUTTON_COLUMN = 5;

	private boolean alreadySetLocation = false;

	private TextAreaPanel outputPanel = null;

	/**
	 * Constructor.
	 * 
	 * @param mainComponent
	 *            The main component.
	 */
	TaskManager(Component mainComponent, TextAreaPanel outputPanelParameter) {

		super(Text.get(Text.TASK_MANAGER_TITLE));

		if (outputPanelParameter != null) {

			this.outputPanel = outputPanelParameter;
		}

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		setContentPane(this.internalPanel);

		drawTable(new ArrayList<TextInterfaceBean>());

		setVisible(true);

		// Redraw at each two seconds.
		Timer updateTasks = new Timer(2000, new ActionListener() {

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

				ArrayList<TextInterfaceBean> interfacesTemporary = new ArrayList<>();

				synchronized (TaskManager.this.bridge) {

					int currentIndex = 0;

					TextInterfaceBean textInterface = null;

					do {

						textInterface = TaskManager.this.bridge
								.getInterfaceTextByIndex(currentIndex);

						if (textInterface != null) {

							interfacesTemporary.add(textInterface);
						}
						currentIndex++;

					} while (textInterface != null);

				}

				drawTable(interfacesTemporary);

			}

		});

		updateTasks.start();
	}

	/**
	 * Draw the table.
	 * 
	 * @param interfacesTemporary
	 *            The list of tasks.
	 */
	private void drawTable(ArrayList<TextInterfaceBean> interfacesTemporary) {

		// Only remove the table if it was initiated.
		if (this.tablePanel != null) {
			this.internalPanel.remove(this.tablePanel);
		}

		TasksTableModel model = new TasksTableModel(interfacesTemporary);

		this.taskTable = new JTable(model);

		// Set a column as buttons.
		this.taskTable.getColumnModel().getColumn(BUTTON_COLUMN)
				.setCellRenderer(new ButtonRenderer());
		this.taskTable.getColumnModel().getColumn(BUTTON_COLUMN)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		// Do not resize the table.
		this.taskTable.setColumnSelectionAllowed(false);
		this.taskTable.setRowSelectionAllowed(false);
		this.taskTable.setCellSelectionEnabled(false);
		this.taskTable.setFocusable(false);
		this.taskTable.setDragEnabled(false);
		this.taskTable.getTableHeader().setReorderingAllowed(false);
		this.taskTable.getTableHeader().setResizingAllowed(false);
		this.taskTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Add title.
		this.tablePanel = new JPanel(new BorderLayout());
		this.tablePanel
				.add(this.taskTable.getTableHeader(), BorderLayout.NORTH);

		// The body can be scrolled.
		JScrollPane scroll = new JScrollPane(this.taskTable);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		this.tablePanel.add(scroll, BorderLayout.CENTER);

		this.internalPanel.add(this.tablePanel, BorderLayout.CENTER);

		// Refresh the panel.
		this.internalPanel.doLayout();
		this.tablePanel.doLayout();

		// Calculate the size.
		int totalWidth = 0;
		final int COLUMNS_WIDTH_INCREMENT = 10, ADD_PIXELS_PER_COLUMN = 3, BUTTONS_COLUMN_ADDITIONAL_WIDTH = 40, SCROLL_BAR_WIDTH = 10;
		for (int i = 0; i < model.getColumnCount(); i++) {

			int maximumLength = new JLabel(model.getColumnName(i))
					.getPreferredSize().width + 20;

			for (int j = 0; j < model.getRowCount(); j++) {

				JLabel cell = new JLabel((String) model.getValueAt(j, i));
				int cellWidth = cell.getPreferredSize().width;

				// Button column.
				if (i == BUTTON_COLUMN) {
					cellWidth += BUTTONS_COLUMN_ADDITIONAL_WIDTH;
				}

				if (maximumLength < cellWidth) {
					maximumLength = cellWidth;
				}

			}

			// Set the column.
			TableColumn column = this.taskTable.getColumnModel().getColumn(i);
			column.setPreferredWidth(maximumLength + COLUMNS_WIDTH_INCREMENT);

			// Keep adding it to know the total width.
			totalWidth += maximumLength + COLUMNS_WIDTH_INCREMENT;

		}

		// Add the spaces in between columns and the vertical scroll bar.
		totalWidth += model.getColumnCount() * ADD_PIXELS_PER_COLUMN
				+ SCROLL_BAR_WIDTH;

		// Get window dimensions.
		int screenHeight = SwingUtil.getScreenHeight();
		int screenWidth = SwingUtil.getScreenWidth();

		int windowHeight = 400;

		// Set the size and location of the window.
		setSize(totalWidth, windowHeight);

		// Just set the location once.
		if (!this.alreadySetLocation) {
			setLocation((screenWidth - totalWidth) / 8,
					(screenHeight - windowHeight) / 8);
			this.alreadySetLocation = true;

		}

		this.internalPanel.invalidate();
		this.internalPanel.repaint();

		this.tablePanel.invalidate();
		this.tablePanel.repaint();

		setResizable(false);

	}

	/**
	 * Holds the tasks on a table.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class TasksTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		private ArrayList<TextInterfaceBean> interfaces = new ArrayList<>();

		/**
		 * Constructor.
		 */
		TasksTableModel(ArrayList<TextInterfaceBean> interfacesParameter) {

			this.interfaces = interfacesParameter;
		}

		/**
		 * Provide a text representation of a duration.
		 * 
		 * @param time
		 *            the time to be converted to a text.
		 * @return the text of the time provided.
		 */
		private String getElapsedTimeText(long time) {

			DurationBean bean = DurationUtil.getDuration(time);

			StringBuffer output = new StringBuffer();
			output.append(StringSupport.prefixWithZeroes((int) bean.getHours(),
					24) + Text.get(Text.FILTER_LINESIMILARITIESFILTER_HOUR));
			output.append(StringSupport.prefixWithZeroes(
					(int) bean.getMinutes(), 60)
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_MINUTE));
			output.append(StringSupport.prefixWithZeroes(
					(int) bean.getSeconds(), 60)
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_SECOND));

			return output.toString();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
		 */
		@Override
		public String getColumnName(int column) {

			switch (column) {

			case 0:
				return Text.get(Text.TASK_MANAGER_COLUMN_FILTER_NUMBER);

			case 1:
				return Text.get(Text.TASK_MANAGER_COLUMN_FILTER_NAME);

			case 2:
				return Text.get(Text.TASK_MANAGER_COLUMN_FILTER_MESSAGE);

			case 3:
				return Text.get(Text.TASK_MANAGER_COLUMN_FILTER_DURATION);

			case 4:
				return Text.get(Text.TASK_MANAGER_COLUMN_FILTER_STATUS);

			case BUTTON_COLUMN:
				return Text.get(Text.TASK_MANAGER_COLUMN_FILTER_ACTION);

			default:
				ExceptionSupport.handleException(Text
						.get(Text.INTERNAL_ERROR_12));
				break;
			}

			return new String();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		@Override
		public int getColumnCount() {

			return 6;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		@Override
		public int getRowCount() {

			if (this.interfaces == null) {
				return 0;
			}

			return this.interfaces.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
		 */
		@Override
		public boolean isCellEditable(int row, int column) {

			return column == BUTTON_COLUMN;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			TaskStatus status = TaskStatus.ERROR;

			synchronized (TaskManager.this.bridge) {

				String content = new String();

				switch (columnIndex) {

				case 0:

					if (rowIndex < this.interfaces.size()) {
						content = String.valueOf(this.interfaces.get(rowIndex)
								.getTaskNumber());
					}

					return content;

				case 1:

					if (rowIndex < this.interfaces.size()) {
						content = this.interfaces.get(rowIndex)
								.getSourceFilter();
					}

					return content;

				case 2:

					if (rowIndex < this.interfaces.size()) {
						content = this.interfaces.get(rowIndex).getProgress();
					}

					return content;

				case 3:

					String durationText = new String();

					if (rowIndex < this.interfaces.size()) {
						TextInterfaceBean bean = this.interfaces.get(rowIndex);

						status = bean.getStatus();

						if (status != TaskStatus.RUNNING) {

							if (bean.getTimeEnded() == 0) {
								bean.setTimeEnded(System.currentTimeMillis());
							}
							durationText = getElapsedTimeText(bean
									.getTimeEnded() - bean.getTimeStarted());
						} else {
							durationText = getElapsedTimeText(System
									.currentTimeMillis()
									- bean.getTimeStarted());
						}
					}

					return durationText;

				case 4:

					if (rowIndex < this.interfaces.size()) {
						status = this.interfaces.get(rowIndex).getStatus();
					}

					switch (status) {

					case CREATED:
						return Text
								.get(Text.TASK_MANAGER_COLUMN_FILTER_STATUS_CREATED);
					case RUNNING:
						return Text
								.get(Text.TASK_MANAGER_COLUMN_FILTER_STATUS_RUNNING);
					case REQUEST_CANCEL:
						return Text
								.get(Text.TASK_MANAGER_COLUMN_FILTER_STATUS_REQUEST_CANCEL);
					case CANCELED:
						return Text
								.get(Text.TASK_MANAGER_COLUMN_FILTER_STATUS_CANCELED);
					case COMPLETE:
						return Text
								.get(Text.TASK_MANAGER_COLUMN_FILTER_STATUS_COMPLETE);
					case ERROR:
						return Text
								.get(Text.TASK_MANAGER_COLUMN_FILTER_STATUS_ERROR);
					default:
						ExceptionSupport.handleException(Text
								.get(Text.INTERNAL_ERROR_15));
						break;
					}

					return content;

				case BUTTON_COLUMN:

					final String BEGIN_HTML = "<html>"; //$NON-NLS-1$
					final String END_HTML = "</html>"; //$NON-NLS-1$
					final String BEGIN_STRONG = "<strong><font color=\"red\">"; //$NON-NLS-1$
					final String END_STRONG = "</font></strong>"; //$NON-NLS-1$
					final String BEGIN_INFORMATION_GREEN = "<font color=\"green\"><strong>"; //$NON-NLS-1$
					final String END_INFORMATION_GREEN = "</strong></font>"; //$NON-NLS-1$

					TextInterfaceBean task = this.interfaces.get(rowIndex);

					if (rowIndex < this.interfaces.size()) {
						status = task.getStatus();
					}

					switch (status) {

					case CREATED:
						return BEGIN_HTML
								+ BEGIN_STRONG
								+ Text.get(
										Text.TASK_MANAGER_COLUMN_FILTER_ACTION_CANCEL,
										String.valueOf(task.getTaskNumber()))
								+ END_STRONG + END_HTML;
					case RUNNING:
						return BEGIN_HTML
								+ BEGIN_STRONG
								+ Text.get(
										Text.TASK_MANAGER_COLUMN_FILTER_ACTION_CANCEL,
										String.valueOf(task.getTaskNumber()))
								+ END_STRONG + END_HTML;
					case REQUEST_CANCEL:
						return BEGIN_HTML
								+ BEGIN_STRONG
								+ Text.get(
										Text.TASK_MANAGER_COLUMN_FILTER_ACTION_WAIT,
										String.valueOf(task.getTaskNumber()))
								+ END_STRONG + END_HTML;
					case CANCELED:
						return BEGIN_HTML
								+ BEGIN_STRONG
								+ Text.get(
										Text.TASK_MANAGER_COLUMN_FILTER_ACTION_CLEAR,
										String.valueOf(task.getTaskNumber()))
								+ END_STRONG + END_HTML;
					case COMPLETE:
						return BEGIN_HTML
								+ BEGIN_INFORMATION_GREEN
								+ Text.get(
										Text.TASK_MANAGER_COLUMN_FILTER_ACTION_GET_RESULT,
										String.valueOf(task.getTaskNumber()))
								+ END_INFORMATION_GREEN + END_HTML;
					case ERROR:
						return BEGIN_HTML
								+ BEGIN_STRONG
								+ Text.get(
										Text.TASK_MANAGER_COLUMN_FILTER_ACTION_CLEAR,
										String.valueOf(task.getTaskNumber()))
								+ END_STRONG + END_HTML;
					default:
						ExceptionSupport.handleException(Text
								.get(Text.INTERNAL_ERROR_14));
						break;
					}

					return content;

				default:
					ExceptionSupport.handleException(Text
							.get(Text.INTERNAL_ERROR_13));
					break;

				}

				return content;
			}
		}
	}

	/**
	 * Render a button on a table.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class ButtonRenderer extends JButton implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		/**
		 * Constructor.
		 */
		public ButtonRenderer() {

			setOpaque(true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent
		 * (javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			if (isSelected) {

				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());

			} else {

				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background")); //$NON-NLS-1$

			}

			setText((value == null) ? "" : value.toString()); //$NON-NLS-1$

			return this;
		}
	}

	/**
	 * A button editor.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class ButtonEditor extends DefaultCellEditor {

		private static final long serialVersionUID = 1L;

		protected JButton button;

		private String label;

		/**
		 * Constructor.
		 * 
		 * @param checkBoxParamter
		 *            The checkbox to initialize the editor.
		 */
		public ButtonEditor(JCheckBox checkBoxParamter) {

			super(checkBoxParamter);

			this.button = new JButton();

			this.button.setOpaque(true);

			this.button.addActionListener(new ActionListener() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.ActionListener#actionPerformed(java.awt.event
				 * .ActionEvent)
				 */
				@SuppressWarnings("synthetic-access")
				@Override
				public void actionPerformed(ActionEvent event) {

					Object buttonEvent = event.getSource();
					if (buttonEvent instanceof JButton) {

						JButton buttonClicked = (JButton) buttonEvent;

						String labelText = buttonClicked.getText();

						labelText = StringSupport.leaveOnlyDigits(labelText);

						int index = -1;

						try {
							index = Integer.parseInt(labelText);
						} catch (NumberFormatException e) {
							// Do nothing.
						}

						TextInterfaceBean bean = TaskManager.this.bridge
								.getInterfaceTextByTaskNumber(index);

						if (bean != null
								&& TaskManager.this.outputPanel != null
								&& bean.getStatus() == TaskStatus.COMPLETE) {

							if (TaskManager.this.outputPanel.isTextMode()) {

								TaskManager.this.outputPanel.setTextArea(bean
										.getContent().toString());

								TaskManager.this.outputPanel.updateLabels();

							} else {

								TaskManager.this.outputPanel
										.setRows(FilterAnyLogic
												.generateTableModel(bean
														.getContent()
														.toString()));

								TaskManager.this.outputPanel.updateLabels();

								TaskManager.this.outputPanel.packTable();

								// Redraw the components.
								TaskManager.this.outputPanel.revalidate();
								TaskManager.this.outputPanel.repaint();
							}

							TaskManager.this.outputPanel
									.setTextAreaSaved(false);

						} else {

							if (bean != null
									&& bean.getStatus() != TaskStatus.COMPLETE) {
								bean.setStatus(TaskStatus.REQUEST_CANCEL);
							}
						}

						TaskManager.this.bridge.removeInterfaceText(index);
					}

				}
			});
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.DefaultCellEditor#getTableCellEditorComponent(javax.swing
		 * .JTable, java.lang.Object, boolean, int, int)
		 */
		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {

			this.label = (value == null) ? "" : value.toString(); //$NON-NLS-1$

			this.button.setText(this.label);

			return this.button;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.DefaultCellEditor#stopCellEditing()
		 */
		@Override
		public boolean stopCellEditing() {

			return true;
		}
	}
}
