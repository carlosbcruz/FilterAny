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

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Table model to store files to be processed.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = { Text.get(Text.TABLE_COLUMN_ROW_NUMBER),
			Text.get(Text.TABLE_COLUMN_LOCATION_TITLE),
			Text.get(Text.TABLE_COLUMN_TYPE),
			Text.get(Text.TABLE_COLUMN_STATUS),
			Text.get(Text.TABLE_COLUMN_SIZE) };

	protected static final int COLUMN_ROW_NUMBER = 0;
	protected static final int COLUMN_LOCATION_TITLE = 1;
	protected static final int COLUMN_TYPE = 2;
	protected static final int COLUMN_STATUS = 3;
	protected static final int COLUMN_SIZE = 4;

	private ArrayList<FileOrURLBean> rows = new ArrayList<>();

	private ImageIcon fileIcon = SwingUtil
			.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "file-icon.png"); //$NON-NLS-1$
	private ImageIcon urlIcon = SwingUtil
			.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "earth-icon.png"); //$NON-NLS-1$
	private ImageIcon textIcon = SwingUtil
			.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "text-document-icon.png"); //$NON-NLS-1$

	private ImageIcon fileVerifiedIcon = SwingUtil
			.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "green-check-icon.png"); //$NON-NLS-1$
	private ImageIcon notVerifiedIcon = SwingUtil
			.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "yellow-cross-icon.png"); //$NON-NLS-1$
	private ImageIcon invalidIcon = SwingUtil
			.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
					+ "invalid-icon.png"); //$NON-NLS-1$

	/**
	 * Set the rows to populate the model.
	 * 
	 * @param rows
	 *            the rows to set The rows to populate the model.
	 */
	protected void setRows(ArrayList<FileOrURLBean> rows) {

		this.rows = rows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {

		return this.columnNames.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {

		return this.columnNames[column];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {

		return this.rows.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {

		switch (column) {
		case COLUMN_ROW_NUMBER:
			// Column row.
			return new Integer(1 + row);
		case COLUMN_LOCATION_TITLE:
			// Column target.
			return this.rows.get(row).getTarget();
		case COLUMN_TYPE:
			// Column type.
			switch (this.rows.get(row).getType()) {
			case FILE:
				return this.fileIcon;
			case URL:
				return this.urlIcon;
			case TEXT:
				return this.textIcon;
			default:
				ExceptionSupport.handleException(Text
						.get(Text.INTERNAL_ERROR_5));
				break;
			}
			return new String();

		case COLUMN_STATUS:
			// Column status.
			switch (this.rows.get(row).getCurrentStatus()) {
			case OK:
				return this.fileVerifiedIcon;
			case NOT_VERIFIED:
				return this.notVerifiedIcon;
			default:
				return this.invalidIcon;
			}
		case COLUMN_SIZE:
			// Column size.
			long size = this.rows.get(row).getSize();

			if (size == -1) {
				return new String();
			}

			if (size < 1024) {
				int label = (int) size;
				return Text.get(Text.TABLE_COLUMN_SIZE_LABEL_BYTES,
						String.valueOf(label));
			}

			if (size < 1024 * 1024) {
				int label = (int) (size / 1024);
				return Text.get(Text.TABLE_COLUMN_SIZE_LABEL_KILOBYTES,
						String.valueOf(label));
			}

			if (size < 1024 * 1024 * 1024) {
				int label = (int) (size / (1024 * 1024));
				return Text.get(Text.TABLE_COLUMN_SIZE_LABEL_MEGABYTES,
						String.valueOf(label));
			}

			int label = (int) (size / (1024 * 1024 * 1024));
			return Text.get(Text.TABLE_COLUMN_SIZE_LABEL_GIGABYTES,
					String.valueOf(label));

		default:
			return new String();
		}
	}

	/**
	 * Export the table content into a text format.
	 * 
	 * @return The text with the table content.
	 */
	protected String exportContent() {

		StringBuffer output = new StringBuffer();

		for (FileOrURLBean row : this.rows) {

			output.append(row.getTarget());

			output.append(FilterAnyConfiguration.isGenerateTextInDOSFormat() ? "\r\n" //$NON-NLS-1$
					: "\n"); //$NON-NLS-1$
		}

		return output.toString();
	}

	/**
	 * Provide the longest target.
	 * 
	 * @return The longest target.
	 */
	protected String getLongestTargetName() {

		String longestTarget = new String();
		for (FileOrURLBean row : this.rows) {

			String target = row.getTarget();
			if (target.length() > longestTarget.length()) {
				longestTarget = target;
			}
		}

		return longestTarget;
	}

	/**
	 * Provide the tooltip of specific icon cells.
	 * 
	 * @param realRowIndex
	 * @param realColumnIndex
	 * @return
	 */
	protected String getTooltip(int realRowIndex, int realColumnIndex) {

		switch (realColumnIndex) {

		case COLUMN_TYPE:

			switch (this.rows.get(realRowIndex).getType()) {
			case FILE:
				return Text.get(Text.TABLE_TOOLTIP_TYPE_FILE);
			case URL:
				return Text.get(Text.TABLE_TOOLTIP_TYPE_URL);
			case TEXT:
				return Text.get(Text.TABLE_TOOLTIP_TYPE_TEXT);
			default:
				ExceptionSupport.handleException(Text
						.get(Text.INTERNAL_ERROR_7));
				break;
			}

			break;
		case COLUMN_STATUS:

			switch (this.rows.get(realRowIndex).getCurrentStatus()) {
			case OK:
				return Text.get(Text.TABLE_TOOLTIP_STATUS_OK);
			case NOT_VERIFIED:
				return Text.get(Text.TABLE_TOOLTIP_STATUS_NOT_VERIFIED);
			case INVALID:
				return Text.get(Text.TABLE_TOOLTIP_STATUS_INVALID);
			default:
				ExceptionSupport.handleException(Text
						.get(Text.INTERNAL_ERROR_8));
				break;
			}

			break;
		default:
			// Ignore tooltip
			break;

		}

		return null;
	}

	/**
	 * Inform the rows that defines this table model.
	 * 
	 * @return the rows The rows that defines this table model.
	 */
	protected ArrayList<FileOrURLBean> getRows() {

		return this.rows;
	}

	/**
	 * Clear the table content.
	 */
	public void clearContent() {

		this.rows = new ArrayList<>();

		fireTableDataChanged();
	}
}
