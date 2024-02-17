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

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 * Table model to store elements from free selection.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FreeSelectionTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ArrayList<ArrayList<ArrayList<SelectedElement>>> columns = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {

		return this.columns.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {

		return String.valueOf(column + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {

		if (this.columns.size() > 0) {
			ArrayList<ArrayList<SelectedElement>> lines = this.columns.get(0);

			return lines.size();
		}

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {

		ArrayList<ArrayList<SelectedElement>> lines = this.columns.get(column);

		ArrayList<SelectedElement> line = lines.get(row);

		StringBuffer content = new StringBuffer();
		for (SelectedElement element : line) {
			content.append(element.getText());
		}
		return content.toString();
	}

	/**
	 * Add a set of lines to a column on the right.
	 * 
	 * @param A
	 *            set of lines.
	 */
	public void addColumnToTheRight(ArrayList<ArrayList<SelectedElement>> lines) {

		this.columns.add(lines);

		TableModelEvent event = new TableModelEvent(this);
		fireTableChanged(event);

		this.fireTableStructureChanged();
	}

	/**
	 * Add a set of lines to a column on the left.
	 * 
	 * @param A
	 *            set of lines.
	 */
	public void addColumnToTheLeft(ArrayList<ArrayList<SelectedElement>> lines) {

		this.columns.add(0, lines);

		TableModelEvent event = new TableModelEvent(this);
		fireTableChanged(event);

		this.fireTableStructureChanged();
	}

	/**
	 * Remove a specific column.
	 * 
	 * @param columns
	 *            The columns number. The first column is 0 (zero).
	 */
	public void removeColumn(int modelColumns[]) {

		for (int i = 0; i < modelColumns.length; i++) {

			// See the greater column number.
			int greater = -1;
			int greaterIndex = -1;
			for (int j = 0; j < modelColumns.length; j++) {
				if (modelColumns[j] > greater) {
					greater = modelColumns[j];
					greaterIndex = j;
				}
			}
			modelColumns[greaterIndex] = -1;

			ArrayList<ArrayList<SelectedElement>> requestedColumn = this.columns
					.get(greater);

			for (ArrayList<SelectedElement> line : requestedColumn) {

				for (SelectedElement element : line) {
					element.setVisible(true);
				}
			}

			this.columns.remove(greater);

		}
		this.fireTableStructureChanged();

	}

	/**
	 * Inform the longest cell content.
	 * 
	 * @param column
	 *            A specific column.
	 * @return The content of the longest cell;
	 */
	public String getLongestCell(int column) {

		StringBuffer longest = new StringBuffer();

		ArrayList<ArrayList<SelectedElement>> lines = this.columns.get(column);

		for (ArrayList<SelectedElement> line : lines) {

			StringBuffer content = new StringBuffer();
			for (SelectedElement element : line) {
				content.append(element.getText());
			}
			if (content.length() > longest.length()) {
				longest = content;
			}
		}
		return longest.toString();
	}

	/**
	 * Provide the content of a column.
	 * 
	 * @param column
	 *            The column.
	 * @return The column content.
	 */
	public ArrayList<ArrayList<SelectedElement>> getColumnContent(int column) {

		return this.columns.get(column);
	}

	/**
	 * Remove all columns.
	 */
	public void removeAllColumns() {

		this.columns = new ArrayList<>();

		this.fireTableStructureChanged();
	}

}
