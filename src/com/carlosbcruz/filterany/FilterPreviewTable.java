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

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;

/**
 * Show the file list preview in a table format.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterPreviewTable extends JTable {

	private static final long serialVersionUID = 1L;

	// The table model.
	FilterPreviewTableModel tableModel = null;

	public FilterPreviewTable(FilterPreviewTableModel tableModel) {

		super(tableModel);

		this.tableModel = tableModel;
	}

	/**
	 * Customize the table colors.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class FilterPreviewTableCellRender extends JLabel implements
			TableCellRenderer {

		private static final long serialVersionUID = 1L;

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

			Object cellValue = FilterPreviewTable.this.tableModel.getValueAt(
					row, column);

			setText(cellValue.toString());

			// The new target is painted with different color.
			if (row % 2 == 1) {
				setBackground(new Color(255, 212, 0));
				setOpaque(true);
			}

			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#getCellRenderer(int, int)
	 */
	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {

		return new FilterPreviewTableCellRender();
	}

	/**
	 * Adapt the layout according to table content.
	 * 
	 * @return The sum of width of all columns
	 */
	protected int adaptLayout() {

		// Verify a good preview width based on the target names.
		// The maximum is 80% of the screen.
		JLabel labelAction = new JLabel(
				this.tableModel
						.getLongestColumnContent(FilterPreviewTableModel.COLUMN_ACTION_TITLE));
		int actionWidth = labelAction.getPreferredSize().width
				+ FilterAnyConfiguration.getAdditionalWidthToColumn();
		// Resize the type column
		TableColumn actionTableColumn = getColumnModel().getColumn(
				FilterPreviewTableModel.COLUMN_ACTION_TITLE);
		actionTableColumn.setPreferredWidth(actionWidth);

		JLabel labelTarget = new JLabel(
				this.tableModel
						.getLongestColumnContent(FilterPreviewTableModel.COLUMN_LOCATION_TITLE));
		int targetWidth = labelTarget.getPreferredSize().width;
		// Resize the type column
		TableColumn targetTableColumn = getColumnModel().getColumn(
				FilterPreviewTableModel.COLUMN_LOCATION_TITLE);
		targetTableColumn.setPreferredWidth(targetWidth);

		return actionWidth + targetWidth;
	}

}
