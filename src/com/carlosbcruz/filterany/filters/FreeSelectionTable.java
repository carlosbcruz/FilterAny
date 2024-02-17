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

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;

/**
 * Store the element for a free selection table.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FreeSelectionTable extends JTable {

	private static final long serialVersionUID = 1L;

	private static final int ADDITIONAL_SPACE_TO_CELLS = 20;

	// The table model.
	private FreeSelectionTableModel tableModel = null;

	/**
	 * Default constructor with the table model.
	 * 
	 * @param tableModel
	 *            The table model.
	 */
	protected FreeSelectionTable(FreeSelectionTableModel tableModel) {

		super(tableModel);

		this.tableModel = tableModel;

		adaptLayout();

		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		setColumnSelectionAllowed(true);
		setRowSelectionAllowed(false);
	}

	/**
	 * Adapt the layout according to table content.
	 */
	public void adaptLayout() {

		// No need to auto resize. This table has it's own logic.
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		int columns = this.tableModel.getColumnCount();
		for (int i = 0; i < columns; i++) {

			TableColumn rowTableColumn = getColumnModel().getColumn(i);
			JLabel rowLabel = new JLabel(this.tableModel.getLongestCell(i));

			rowTableColumn.setPreferredWidth((int) rowLabel.getPreferredSize()
					.getWidth() + ADDITIONAL_SPACE_TO_CELLS);
		}

	}

	/**
	 * Remove a specific column.
	 * 
	 * @param columns
	 *            The columns number. The first column is 0 (zero).
	 */
	public void removeColumn(int columns[]) {

		int modelColumns[] = new int[columns.length];

		for (int i = 0; i < columns.length; i++) {

			modelColumns[i] = getColumnModel().getColumn(columns[i])
					.getModelIndex();
		}

		this.tableModel.removeColumn(modelColumns);
	}

	/**
	 * Remove all columns.
	 */
	public void removeAllColumns() {

		this.tableModel.removeAllColumns();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#tableChanged(javax.swing.event.TableModelEvent)
	 */
	@Override
	public void tableChanged(TableModelEvent event) {

		super.tableChanged(event);

	}

	/**
	 * Provide the content of the table.
	 * 
	 * @return The content of the table.
	 */
	public ArrayList<ArrayList<ArrayList<SelectedElement>>> getTableContent() {

		ArrayList<ArrayList<ArrayList<SelectedElement>>> columns = new ArrayList<>();

		int numberOfColumns = this.tableModel.getColumnCount();
		for (int i = 0; i < numberOfColumns; i++) {

			int modelColumn = getColumnModel().getColumn(i).getModelIndex();

			ArrayList<ArrayList<SelectedElement>> lines = this.tableModel
					.getColumnContent(modelColumn);

			columns.add(lines);

		}

		return columns;

	}
}
