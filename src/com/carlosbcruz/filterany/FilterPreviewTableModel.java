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

import java.io.File;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.FileSupport;

/**
 * Store the file list preview table information.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterPreviewTableModel extends AbstractTableModel {

	private String[] columnNames = { Text.get(Text.TABLE_COLUMN_ACTION),
			Text.get(Text.TABLE_COLUMN_LOCATION_TITLE) };

	private ArrayList<FileOrURLBean> rows = new ArrayList<>();

	protected static final int COLUMN_ACTION_TITLE = 0;
	protected static final int COLUMN_LOCATION_TITLE = 1;

	private boolean renameFileRequested = false;

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor with the rows to be presented.
	 * 
	 * @param rows
	 *            The rows.
	 */
	public FilterPreviewTableModel(ArrayList<FileOrURLBean> rows) {

		super();

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

		return this.rows.size() * 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {

		FileOrURLBean bean = this.rows.get(row / 2);

		switch (column) {

		case COLUMN_ACTION_TITLE: {

			// Column action.
			File previousFile = new File(bean.getTarget());
			File newFile = bean.getNewTarget() == null ? new File(new String())
					: new File(bean.getNewTarget());

			if (row % 2 == 0) {

				return Text.get(Text.TABLE_ACTION_READ);

			}

			if (this.renameFileRequested) {

				if (bean.getNewTarget() == null) {

					return Text.get(Text.TABLE_ACTION_DELETE);
				}

				if (FileSupport.isInSameDirectory(previousFile, newFile)) {

					if (FileSupport.hasSameFileName(previousFile, newFile)) {

						return Text.get(Text.TABLE_ACTION_OVERWRITE);

					}

					return Text.get(Text.TABLE_ACTION_RENAME_TO);

				}

				return Text.get(Text.TABLE_ACTION_CREATE);

			}

			if (newFile.exists()) {
				return Text.get(Text.TABLE_ACTION_OVERWRITE);
			}

			return Text.get(Text.TABLE_ACTION_CREATE);

		}

		case COLUMN_LOCATION_TITLE: {

			// Column target.

			if (row % 2 == 0) {

				return bean.getTarget();

			}

			if (bean.getNewTarget() == null) {

				return bean.getTarget();

			}

			return bean.getNewTarget();

		}
		default:
			ExceptionSupport.handleException(Text.get(Text.INTERNAL_ERROR_3));
		}

		return null;
	}

	/**
	 * Inform the longest content of a column
	 * 
	 * @param column
	 *            The column to search for the longest content.
	 * @return The longest name.
	 */
	public String getLongestColumnContent(int column) {

		String longestTarget = new String();

		switch (column) {

		case COLUMN_ACTION_TITLE: {

			int numberOfRows = getRowCount();

			for (int i = 0; i < numberOfRows; i++) {

				String content = getValueAt(i, COLUMN_ACTION_TITLE).toString();
				if (content.length() > longestTarget.length()) {
					longestTarget = content;
				}
			}
			break;
		}

		case COLUMN_LOCATION_TITLE: {

			for (FileOrURLBean row : this.rows) {

				if (row.getTarget().length() > longestTarget.length()) {

					longestTarget = row.getTarget();

				}

				if (row.getNewTarget() != null) {

					if (row.getNewTarget().length() > longestTarget.length()) {

						longestTarget = row.getNewTarget();

					}
				}
			}
			break;
		}
		default:
			ExceptionSupport.handleException(Text.get(Text.INTERNAL_ERROR_4));
		}

		return longestTarget;
	}

	/**
	 * @param renameFileRequested
	 *            the renameFileRequested to set
	 */
	protected void setRenameFileRequested(boolean renameFileRequested) {
		this.renameFileRequested = renameFileRequested;
	}

}
