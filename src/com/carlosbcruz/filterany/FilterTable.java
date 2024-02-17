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

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;

/**
 * Store the element for a file mode.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterTable extends JTable {

	private static final long serialVersionUID = 1L;

	private static final int MAXIMUM_TARGET_WIDTH = 800;

	// The table model.
	FilterTableModel tableModel = null;

	/**
	 * Default constructor with the table model.
	 * 
	 * @param tableModel
	 *            The table model.
	 */
	protected FilterTable(FilterTableModel tableModel) {

		super(tableModel);

		this.tableModel = tableModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int column) {

		int modelColumn = convertColumnIndexToModel(column);

		if (modelColumn == FilterTableModel.COLUMN_TYPE
				|| modelColumn == FilterTableModel.COLUMN_STATUS) {

			return ImageIcon.class;
		}

		return Object.class;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#getToolTipText(java.awt.event.MouseEvent)
	 */
	@Override
	public String getToolTipText(MouseEvent event) {

		// Locate where is the mouse.
		java.awt.Point point = event.getPoint();

		int rowIndex = rowAtPoint(point);
		int colIndex = columnAtPoint(point);

		int realRowIndex = convertRowIndexToModel(rowIndex);
		int realColumnIndex = convertColumnIndexToModel(colIndex);

		String toolTip = this.tableModel.getTooltip(realRowIndex,
				realColumnIndex);
		if (toolTip != null) {
			return toolTip;
		}

		return super.getToolTipText(event);
	}

	/**
	 * Adapt the layout according to table content.
	 */
	protected void adaptLayout() {

		// No need to auto resize. This table has it's own logic.
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Resize row column
		TableColumn rowTableColumn = getColumnModel().getColumn(
				FilterTableModel.COLUMN_ROW_NUMBER);
		JLabel rowLabel = new JLabel(
				getColumnName(FilterTableModel.COLUMN_ROW_NUMBER));
		rowTableColumn.setPreferredWidth((int) rowLabel.getPreferredSize()
				.getWidth()
				+ FilterAnyConfiguration.getAdditionalWidthToColumn());

		// Resize the location column
		TableColumn locationTableColumn = getColumnModel().getColumn(
				FilterTableModel.COLUMN_LOCATION_TITLE);
		String longestTargetName = this.tableModel.getLongestTargetName();
		int preferredWidth = 0;

		// If there is no data than size by the column title
		// otherwise size by the longest target.
		if ("".equals(longestTargetName)) { //$NON-NLS-1$

			JLabel targetLabel = new JLabel(
					getColumnName(FilterTableModel.COLUMN_LOCATION_TITLE));
			preferredWidth = (int) targetLabel.getPreferredSize().getWidth()
					+ FilterAnyConfiguration.getAdditionalWidthToColumn();

		} else {

			JLabel locationLabel = new JLabel(longestTargetName);
			int locationColumnSize = (int) locationLabel.getPreferredSize()
					.getWidth();
			if (locationColumnSize > MAXIMUM_TARGET_WIDTH) {
				locationColumnSize = MAXIMUM_TARGET_WIDTH;
			}
			preferredWidth = locationColumnSize;

		}
		locationTableColumn.setPreferredWidth(preferredWidth);

		// Resize the type column
		TableColumn typeTableColumn = getColumnModel().getColumn(
				FilterTableModel.COLUMN_TYPE);
		JLabel typeLabel = new JLabel(
				getColumnName(FilterTableModel.COLUMN_TYPE));
		typeTableColumn.setPreferredWidth((int) typeLabel.getPreferredSize()
				.getWidth()
				+ FilterAnyConfiguration.getAdditionalWidthToColumn());

		// Resize the status column
		TableColumn statusTableColumn = getColumnModel().getColumn(
				FilterTableModel.COLUMN_STATUS);
		JLabel statusLabel = new JLabel(
				getColumnName(FilterTableModel.COLUMN_STATUS));
		statusTableColumn.setPreferredWidth((int) statusLabel
				.getPreferredSize().getWidth()
				+ FilterAnyConfiguration.getAdditionalWidthToColumn());

		// Resize the size column
		TableColumn sizeTableColumn = getColumnModel().getColumn(
				FilterTableModel.COLUMN_SIZE);
		JLabel sizeLabel = new JLabel(
				getColumnName(FilterTableModel.COLUMN_SIZE));
		sizeTableColumn.setPreferredWidth((int) sizeLabel.getPreferredSize()
				.getWidth()
				+ FilterAnyConfiguration.getAdditionalWidthToColumn());

	}
}
