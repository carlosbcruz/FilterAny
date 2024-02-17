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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.CustomSizeButton;
import com.carlosbcruz.util.EventHelperAdapter;
import com.carlosbcruz.util.JTreeEventHelperListener;
import com.carlosbcruz.util.SimpleActionDecorator;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionProvider;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.SwingUtil;

/**
 * A tree to store filtersWindow.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FiltersTree extends JTree implements JTreeEventHelperListener {

	private static final int NUMBER_OF_TREE_MEMORIES = 5;

	private static final long serialVersionUID = 1L;

	private transient FilterChangeListener filterListener = null;

	private DefaultMutableTreeNode[] memories = new DefaultMutableTreeNode[NUMBER_OF_TREE_MEMORIES];

	private JMenuItem addMemory1MenuItem = null;
	private JMenuItem addMemory2MenuItem = null;
	private JMenuItem addMemory3MenuItem = null;
	private JMenuItem addMemory4MenuItem = null;
	private JMenuItem addMemory5MenuItem = null;

	private SimpleActionDecorator addMemory1Action;
	private SimpleActionDecorator addMemory2Action;
	private SimpleActionDecorator addMemory3Action;
	private SimpleActionDecorator addMemory4Action;
	private SimpleActionDecorator addMemory5Action;

	private JButton memory1 = new CustomSizeButton(Event.getMemory1Action(),
			50, 20);
	private JButton memory2 = new CustomSizeButton(Event.getMemory2Action(),
			50, 20);
	private JButton memory3 = new CustomSizeButton(Event.getMemory3Action(),
			50, 20);
	private JButton memory4 = new CustomSizeButton(Event.getMemory4Action(),
			50, 20);
	private JButton memory5 = new CustomSizeButton(Event.getMemory5Action(),
			50, 20);
	private JButton clearMemory = new CustomSizeButton(
			Event.getClearMemoryAction(), 20, 20);

	private Color backgroundColor = new Color(255, 255, 255);

	private transient SearchFilter searchFilter = null;

	/**
	 * Creates a tree specifically to store filtersWindow.
	 * 
	 * @param treeNodeParameter
	 *            The tree node with the filter structure.
	 * @param listener
	 *            The listener when the selected filter has changed.
	 * @param backgroundColorParameter
	 *            The background color.
	 */
	FiltersTree(DefaultMutableTreeNode treeNodeParameter,
			FilterChangeListener listener, Color backgroundColorParameter) {

		super(treeNodeParameter);

		if (backgroundColorParameter != null) {
			this.backgroundColor = backgroundColorParameter;
			setBackground(backgroundColorParameter);
		}

		// Initialize the memory.
		for (int i = 0; i < NUMBER_OF_TREE_MEMORIES; i++) {
			this.memories[i] = null;
		}

		this.filterListener = listener;

		putClientProperty("JTree.lineStyle", FilterAnyConfiguration //$NON-NLS-1$
				.getTreeLineStyle());

		setCellRenderer(new FilterTreeCellRenderer(this.backgroundColor));

		attachSelectionListeners();

	}

	/**
	 * Search for a filter.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class SearchFilter extends JDialog {

		private static final long serialVersionUID = 1L;

		private List<DefaultMutableTreeNode> listOfFilter = new ArrayList<>();

		private DefaultMutableTreeNode defaultMutableTreeNode = null;

		private JTextField searchField = new JTextField(20);

		private FiltersFoundTableModel model = null;

		private ViewTable requestsTable = null;

		/**
		 * Constructor.
		 * 
		 * @param defaultMutableTreeNodeParameter
		 *            The tree model that store the nodes.
		 * @param mainWindowXPosition
		 *            The start X position of the main window.
		 * @param mainWindowYPosition
		 *            The start Y position of the main window.
		 * @param mainWindowWidth
		 *            The width of the main window.
		 * @param mainWindowHeight
		 *            The height of the main window.
		 */
		@SuppressWarnings("unqualified-field-access")
		SearchFilter(DefaultMutableTreeNode defaultMutableTreeNodeParameter,
				int mainWindowXPosition, int mainWindowYPosition,
				int mainWindowWidth, int mainWindowHeight) {

			setTitle(Text.get(Text.SEARCH_PARAMETER_WINDOW_TITLE));

			// Change the frame icon.
			SwingUtil.changeWindowIcon(this,
					FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png"); //$NON-NLS-1$

			this.defaultMutableTreeNode = defaultMutableTreeNodeParameter;

			setPosition(mainWindowXPosition, mainWindowYPosition,
					mainWindowWidth, mainWindowHeight);

			setModal(true);

			// Populate the list of filters.
			if (this.defaultMutableTreeNode != null) {

				DefaultMutableTreeNode node = this.defaultMutableTreeNode
						.getFirstLeaf();

				while (node != null) {

					this.listOfFilter.add(node);
					node = node.getNextLeaf();
				}
			}

			this.model = new FiltersFoundTableModel(this.listOfFilter);

			JPanel mainPanel = new JPanel(new BorderLayout());

			this.requestsTable = new ViewTable(this.model);

			// Do not resize the table.
			this.requestsTable.setColumnSelectionAllowed(false);
			this.requestsTable.setRowSelectionAllowed(true);
			this.requestsTable.setCellSelectionEnabled(false);
			this.requestsTable.setFocusable(true);
			this.requestsTable.setDragEnabled(false);
			this.requestsTable.setTableHeader(null);
			this.requestsTable
					.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			this.requestsTable.addKeyListener(new KeyAdapter() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
				 */
				@SuppressWarnings({ "synthetic-access" })
				@Override
				public void keyPressed(KeyEvent event) {

					int key = event.getKeyCode();

					if (key == KeyEvent.VK_ENTER) {

						int rowSelected = requestsTable.getSelectedRow();

						if (showFilter(rowSelected)) {

							setVisible(false);

							searchField.setText(""); //$NON-NLS-1$
						}
					}

					if (key == KeyEvent.VK_ESCAPE) {

						setVisible(false);

						searchField.setText(""); //$NON-NLS-1$
					}
				}

			});

			// The body can be scrolled.
			JScrollPane scroll = new JScrollPane(this.requestsTable);

			JPanel searchBorderPanel = new JPanel(new BorderLayout());
			searchBorderPanel.setBorder(BorderFactory.createTitledBorder(Text
					.get(Text.SEARCH_PARAMETER_BORDER)));
			searchBorderPanel.add(this.searchField, BorderLayout.CENTER);

			mainPanel.add(searchBorderPanel, BorderLayout.NORTH);
			mainPanel.add(scroll, BorderLayout.CENTER);

			setContentPane(mainPanel);

			Document document = this.searchField.getDocument();

			this.model.setSearchText(""); //$NON-NLS-1$

			this.searchField.addKeyListener(new KeyAdapter() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
				 */
				@SuppressWarnings({ "synthetic-access" })
				@Override
				public void keyPressed(KeyEvent event) {

					int key = event.getKeyCode();

					if (key == KeyEvent.VK_DOWN) {

						if (requestsTable.getRowCount() > 0) {

							requestsTable.changeSelection(0, 0, false, false);
							requestsTable.requestFocus();
						}

					}

					if (key == KeyEvent.VK_ENTER) {

						if (requestsTable.getRowCount() == 1) {

							if (showFilter(0)) {

								setVisible(false);

								searchField.setText(""); //$NON-NLS-1$

							}

						}

					}

					if (key == KeyEvent.VK_ESCAPE) {

						setVisible(false);

						searchField.setText(""); //$NON-NLS-1$
					}
				}

			});

			document.addDocumentListener(new DocumentListener() {

				/**
				 * A change on the document has occurred.
				 * 
				 * @param event
				 *            The event.
				 */
				@SuppressWarnings({ "synthetic-access" })
				private void documentChanged(DocumentEvent event) {

					int length = event.getDocument().getLength();

					try {

						model.setSearchText(event.getDocument().getText(0,
								length));

					} catch (BadLocationException e) {
						// Do nothing.
					}
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * javax.swing.event.DocumentListener#changedUpdate(javax.swing
				 * .event.DocumentEvent)
				 */
				@Override
				public void changedUpdate(DocumentEvent event) {

					documentChanged(event);
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * javax.swing.event.DocumentListener#insertUpdate(javax.swing
				 * .event.DocumentEvent)
				 */
				@Override
				public void insertUpdate(DocumentEvent event) {

					documentChanged(event);
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * javax.swing.event.DocumentListener#removeUpdate(javax.swing
				 * .event.DocumentEvent)
				 */
				@Override
				public void removeUpdate(DocumentEvent event) {

					documentChanged(event);
				}

			});

			requestsTable.addMouseListener(new MouseAdapter() {

				@SuppressWarnings("synthetic-access")
				@Override
				public void mouseClicked(MouseEvent event) {

					int row = requestsTable.rowAtPoint(event.getPoint());

					showFilter(row);

					setVisible(false);

					searchField.setText(""); //$NON-NLS-1$
				}

			});

		}

		/**
		 * Clear the search text.
		 */
		public void clearSearchText() {

			this.searchField.setText(""); //$NON-NLS-1$

			this.model.setSearchText(""); //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.Dialog#setVisible(boolean)
		 */
		@Override
		public void setVisible(boolean parameter) {

			super.setVisible(parameter);

			this.searchField.requestFocus();
		}

		/**
		 * Show a specific filter.
		 * 
		 * @param rowSelected
		 *            The row on the table where the filter is.
		 */
		private boolean showFilter(int rowSelected) {

			if (rowSelected < 0) {

				return false;
			}

			DefaultMutableTreeNode node = this.model.getNodeAtRow(rowSelected);

			DefaultMutableTreeNode memory = node;
			TreeSelectionModel treeSelectionModel = getSelectionModel();
			TreePath treePath = SwingUtil.getTreePath(memory);
			treeSelectionModel.setSelectionPath(treePath);
			makeVisible(treePath);

			Rectangle rectangle = getPathBounds(treePath);
			scrollRectToVisible(rectangle);

			return true;
		}

		/**
		 * A table to show static information.
		 */
		class ViewTable extends JTable {

			private static final long serialVersionUID = 1L;

			/**
			 * Constructor.
			 * 
			 * @param model
			 *            The table model.
			 */
			public ViewTable(TableModel model) {

				super(model);

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.JTable#getToolTipText(java.awt.event.MouseEvent)
			 */
			@SuppressWarnings({ "unqualified-field-access", "synthetic-access" })
			@Override
			public String getToolTipText(MouseEvent event) {

				Point point = event.getPoint();

				int row = requestsTable.rowAtPoint(point);

				if (row >= 0 && row <= model.getRowCount()) {

					return model.getTooltip(row);
				}

				return new String();
			}
		}

		/**
		 * Position the search filter dialog.
		 * 
		 * @param mainWindowXPosition
		 *            The start X position of the main window.
		 * @param mainWindowYPosition
		 *            The start Y position of the main window.
		 * @param mainWindowWidth
		 *            The width of the main window.
		 * @param mainWindowHeight
		 *            The height of the main window.
		 */
		public void setPosition(int mainWindowXPosition,
				int mainWindowYPosition, int mainWindowWidth,
				int mainWindowHeight) {

			int width = 600;
			int height = 400;

			int x = mainWindowXPosition + mainWindowWidth / 2 - width / 2;
			int y = mainWindowYPosition + mainWindowHeight / 2 - width / 2;

			setSize(width, height);
			setLocation(x, y);
		}

		/**
		 * Holds the list of filters found.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class FiltersFoundTableModel extends DefaultTableModel {

			private static final long serialVersionUID = 1L;

			private String searchText = new String();

			private List<DefaultMutableTreeNode> allFiltersFound = new ArrayList<>();

			private List<DefaultMutableTreeNode> filtersFound = new ArrayList<>();
			private List<String> filtersFoundHightLightNames = new ArrayList<>();
			private List<String> filtersTooltipFoundHightLightNames = new ArrayList<>();

			/**
			 * Constructor.
			 * 
			 * @param filtersFoundParameter
			 *            The list of filteres.
			 */
			FiltersFoundTableModel(
					List<DefaultMutableTreeNode> filtersFoundParameter) {

				this.allFiltersFound = filtersFoundParameter;
			}

			/**
			 * Provide the node at a specific row.
			 * 
			 * @param row
			 *            The table row.
			 * @return The node.
			 */
			public DefaultMutableTreeNode getNodeAtRow(int row) {

				return this.filtersFound.get(row);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.TableModel#getColumnCount()
			 */
			@Override
			public int getColumnCount() {

				return 1;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.TableModel#getRowCount()
			 */
			@Override
			public int getRowCount() {

				if (this.filtersFound == null) {

					return 0;
				}

				return this.filtersFound.size();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.TableModel#getValueAt(int, int)
			 */
			@Override
			@SuppressWarnings("nls")
			public Object getValueAt(int rowIndex, int columnIndex) {

				if (this.filtersFound == null) {

					return new String();
				}

				return "<html><i>"
						+ this.filtersFoundHightLightNames.get(rowIndex)
						+ "</i></html>";
			}

			/**
			 * Provide the filter name from a specific tree node.
			 * 
			 * @param node
			 *            The tree node.
			 * @return The filter name.
			 */
			private String getFilterName(DefaultMutableTreeNode node) {

				Object userObject = node.getUserObject();

				Filter filter = null;
				if (userObject instanceof Filter) {

					filter = (Filter) userObject;
				}

				if (filter == null) {

					return new String();
				}

				return filter.getFilterName();
			}

			/**
			 * Provide the filter tooltip from a specific tree node.
			 * 
			 * @param node
			 *            The tree node.
			 * @return The filter name.
			 */
			private String getFilterTooltip(DefaultMutableTreeNode node) {

				Object userObject = node.getUserObject();

				Filter filter = null;
				if (userObject instanceof Filter) {

					filter = (Filter) userObject;
				}

				if (filter == null) {

					return new String();
				}

				return filter.getSmallDescription();
			}

			/**
			 * Set the search text.
			 * 
			 * @param textParameter
			 *            The search text.
			 */
			@SuppressWarnings("unqualified-field-access")
			public void setSearchText(String textParameter) {

				searchText = textParameter;

				filtersFound = new ArrayList<>();
				filtersFoundHightLightNames = new ArrayList<>();
				filtersTooltipFoundHightLightNames = new ArrayList<>();

				List<String> words = new ArrayList<>();

				StringTokenizer tokenizer = new StringTokenizer(searchText);
				while (tokenizer.hasMoreElements()) {

					words.add(tokenizer.nextToken().toUpperCase());
				}

				for (int i = 0; i < allFiltersFound.size(); i++) {

					String filterName = getFilterName(allFiltersFound.get(i));

					String searchedFileName = filterName;

					List<Integer> positions = new ArrayList<>();

					// Avoid matching works break by substrings.
					int allWordsExist = 0;
					for (int j = 0; j < words.size(); j++) {

						String searchWord = words.get(j);

						int index = searchedFileName.toUpperCase().indexOf(
								searchWord.toUpperCase());

						if (index >= 0) {

							allWordsExist++;
						}
					}

					if (allWordsExist != words.size()) {

						continue;
					}

					int wordsFound = 0;
					for (int j = 0; j < words.size(); j++) {

						String searchWord = words.get(j);

						int index = searchedFileName.toUpperCase().indexOf(
								searchWord);

						if (index >= 0) {

							wordsFound++;

							String newSearchedFileName = searchedFileName
									.substring(0, index);

							newSearchedFileName += searchedFileName.substring(
									index + searchWord.length(),
									searchedFileName.length());

							positions.add(new Integer(index));

							searchedFileName = newSearchedFileName;
						}
					}

					if (wordsFound == words.size()) {

						List<Integer> positionsIncremented = new ArrayList<>();

						for (int k = positions.size() - 1; k >= 0; k--) {

							int increments = 0;
							for (int l = 0; l < positionsIncremented.size(); l++) {

								if (positionsIncremented.get(l).intValue() <= positions
										.get(k).intValue()) {

									increments++;
								}
							}

							StringBuffer temporary = new StringBuffer(
									searchedFileName);

							temporary.insert((increments * 7)
									+ positions.get(k).intValue(), "<b>" //$NON-NLS-1$
									+ words.get(k) + "</b>"); //$NON-NLS-1$

							// temporary.insert( positions.get(k),
							// words.get(k));

							positionsIncremented.add(positions.get(k));

							// Adjust the positions.
							boolean hasAlreadyIncrementedAnEqual = false;
							for (int l = 0; l < positionsIncremented.size(); l++) {

								if (positionsIncremented.get(l).intValue() > positions
										.get(k).intValue()) {
									positionsIncremented.set(l, new Integer(
											positionsIncremented.get(l)
													.intValue()
													+ words.get(k).length()));
								} else {
									if (positionsIncremented.get(l) == positions
											.get(k)) {

										if (!hasAlreadyIncrementedAnEqual) {
											positionsIncremented
													.set(l,
															new Integer(
																	positionsIncremented
																			.get(l)
																			.intValue()
																			+ words.get(
																					k)
																					.length()));
											hasAlreadyIncrementedAnEqual = true;
										}
									}

								}
							}

							searchedFileName = temporary.toString();
						}

						filtersFound.add(allFiltersFound.get(i));
						filtersFoundHightLightNames.add(searchedFileName);
						filtersTooltipFoundHightLightNames
								.add(getFilterTooltip(allFiltersFound.get(i)));
					}
				}

				fireTableDataChanged();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
			 */
			@Override
			public boolean isCellEditable(int arg0, int arg1) {

				return false;
			}

			/**
			 * Provide the tool tip for a specific row.
			 * 
			 * @param row
			 *            the row.
			 * @return The agent used.
			 */
			public String getTooltip(int row) {

				return this.filtersTooltipFoundHightLightNames.get(row);
			}
		}
	}

	/**
	 * Set the dialog position.
	 * 
	 * @param mainWindowXPosition
	 *            The start X position of the main window.
	 * @param mainWindowYPosition
	 *            The start Y position of the main window.
	 * @param mainWindowWidth
	 *            The width of the main window.
	 * @param mainWindowHeight
	 *            The height of the main window.
	 */
	protected void showSearchFilterDialog(int mainWindowXPosition,
			int mainWindowYPosition, int mainWindowWidth, int mainWindowHeight) {

		DefaultMutableTreeNode defaultMutableTreeNode = null;

		TreeModel treeSearchFilterModel = getModel();

		if (treeSearchFilterModel instanceof DefaultTreeModel) {

			DefaultTreeModel defaultTreeModel = (DefaultTreeModel) treeSearchFilterModel;

			Object root = defaultTreeModel.getRoot();

			if (root instanceof DefaultMutableTreeNode) {
				defaultMutableTreeNode = (DefaultMutableTreeNode) root;
			}

		}

		if (this.searchFilter == null) {

			this.searchFilter = new SearchFilter(defaultMutableTreeNode,
					mainWindowXPosition, mainWindowYPosition, mainWindowWidth,
					mainWindowHeight);
		}

		this.searchFilter.clearSearchText();

		this.searchFilter.setVisible(true);

	}

	/**
	 * Attach the selection event listeners.
	 */
	private void attachSelectionListeners() {

		EventHelperAdapter treeEvents = new EventHelperAdapter(this);

		this.addMemory1Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_1_small_arrow.png", //$NON-NLS-1$
				Text.get(Text.ADD_MEMORY_1_TITLE), 0,
				Text.get(Text.ADD_MEMORY_1_INFORMATION));

		this.addMemory2Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_2_small_arrow.png", //$NON-NLS-1$
				Text.get(Text.ADD_MEMORY_2_TITLE), 0,
				Text.get(Text.ADD_MEMORY_2_INFORMATION));

		this.addMemory3Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_3_small_arrow.png", //$NON-NLS-1$
				Text.get(Text.ADD_MEMORY_3_TITLE), 0,
				Text.get(Text.ADD_MEMORY_3_INFORMATION));

		this.addMemory4Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_4_small_arrow.png", //$NON-NLS-1$
				Text.get(Text.ADD_MEMORY_4_TITLE), 0,
				Text.get(Text.ADD_MEMORY_4_INFORMATION));

		this.addMemory5Action = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION
						+ "glass_numbers_5_small_arrow.png", //$NON-NLS-1$
				Text.get(Text.ADD_MEMORY_5_TITLE), 0,
				Text.get(Text.ADD_MEMORY_5_INFORMATION));

		this.addMemory1MenuItem = new JMenuItem(this.addMemory1Action);
		this.addMemory2MenuItem = new JMenuItem(this.addMemory2Action);
		this.addMemory3MenuItem = new JMenuItem(this.addMemory3Action);
		this.addMemory4MenuItem = new JMenuItem(this.addMemory4Action);
		this.addMemory5MenuItem = new JMenuItem(this.addMemory5Action);

		this.memory1.setEnabled(isMemorySet(0));
		this.memory2.setEnabled(isMemorySet(1));
		this.memory3.setEnabled(isMemorySet(2));
		this.memory4.setEnabled(isMemorySet(3));
		this.memory5.setEnabled(isMemorySet(4));

		this.clearMemory.setEnabled(isAnyMemorySet());

		// Add filter to the memory 1.
		this.addMemory1Action.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			public void update(SimpleActionSubject simpleSubject) {

				setMemoryToTheSelectedNode(0);

				if (isMemorySet(0)) {
					memory1.setEnabled(true);
					memory1.setToolTipText(Text.get(
							Text.MEMORY_ACTION_TEXT_1_INSTRUCTION,
							getFilterDescriptionForMemory(0)));
					clearMemory.setEnabled(true);
				}
			}
		});

		// Add filter to the memory 2.
		this.addMemory2Action.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			public void update(SimpleActionSubject simpleSubject) {

				setMemoryToTheSelectedNode(1);

				if (isMemorySet(1)) {
					memory2.setEnabled(true);
					memory2.setToolTipText(Text.get(
							Text.MEMORY_ACTION_TEXT_2_INSTRUCTION,
							getFilterDescriptionForMemory(1)));
					clearMemory.setEnabled(true);
				}
			}
		});

		// Add filter to the memory 3.
		this.addMemory3Action.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			public void update(SimpleActionSubject simpleSubject) {

				setMemoryToTheSelectedNode(2);

				if (isMemorySet(2)) {
					memory3.setEnabled(true);
					memory3.setToolTipText(Text.get(
							Text.MEMORY_ACTION_TEXT_3_INSTRUCTION,
							getFilterDescriptionForMemory(2)));
					clearMemory.setEnabled(true);
				}
			}
		});

		// Add filter to the memory 4.
		this.addMemory4Action.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			public void update(SimpleActionSubject simpleSubject) {

				setMemoryToTheSelectedNode(3);

				if (isMemorySet(3)) {
					memory4.setEnabled(true);
					memory4.setToolTipText(Text.get(
							Text.MEMORY_ACTION_TEXT_4_INSTRUCTION,
							getFilterDescriptionForMemory(3)));
					clearMemory.setEnabled(true);
				}
			}
		});

		// Add filter to the memory 5.
		this.addMemory5Action.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			public void update(SimpleActionSubject simpleSubject) {

				setMemoryToTheSelectedNode(4);

				if (isMemorySet(4)) {
					memory5.setEnabled(true);
					memory5.setToolTipText(Text.get(
							Text.MEMORY_ACTION_TEXT_5_INSTRUCTION,
							getFilterDescriptionForMemory(4)));
					clearMemory.setEnabled(true);
				}
			}
		});

		// Dispatch memory 1 event.
		this.memory1.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				selectMemory(0);
			}

		});

		// Dispatch memory 2 event.
		this.memory2.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				selectMemory(1);
			}

		});

		// Dispatch memory 3 event.
		this.memory3.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				selectMemory(2);
			}

		});

		// Dispatch memory 4 event.
		this.memory4.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				selectMemory(3);
			}

		});

		// Dispatch memory 5 event.
		this.memory5.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				selectMemory(4);
			}

		});

		// Clear the memory.
		this.clearMemory.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < 5; i++) {
					memories[i] = null;
				}

				memory1.setEnabled(false);
				memory2.setEnabled(false);
				memory3.setEnabled(false);
				memory4.setEnabled(false);
				memory5.setEnabled(false);

				clearMemory.setEnabled(false);
			}

		});

		treeEvents.addMenuItemToPopup(this.addMemory1MenuItem);
		treeEvents.addMenuItemToPopup(this.addMemory2MenuItem);
		treeEvents.addMenuItemToPopup(this.addMemory3MenuItem);
		treeEvents.addMenuItemToPopup(this.addMemory4MenuItem);
		treeEvents.addMenuItemToPopup(this.addMemory5MenuItem);

		treeEvents.addEventHelperListener(this);

		addMouseListener(treeEvents);
		addTreeSelectionListener(treeEvents);

		treeEvents.addEventHelperListener(new JTreeEventHelperListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.JTreeEventHelperListener#eventOnUserObject
			 * (java.lang.Object)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void eventOnUserObject(Object object) {

				if (object instanceof FilterAdapter) {
					dispatchFilterChangeEvent((FilterAdapter) object);

				} else {
					dispatchFilterChangeEvent(null);
				}
			}
		});

	}

	/**
	 * Indicate if any of the memory is set.
	 * 
	 * @return True if any of the memory is set.
	 */
	private boolean isAnyMemorySet() {

		for (int i = 0; i < 5; i++) {
			if (isMemorySet(i)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Dispatch an event indication that a filter selection has changed.
	 * 
	 * @param filter
	 *            The new fileter.
	 */
	private void dispatchFilterChangeEvent(FilterAdapter filter) {

		this.filterListener.updateFilterChanged(filter);
	}

	/**
	 * Set the filter change listener.
	 * 
	 * @param listener
	 *            The filter change listener.
	 */
	public void setFilterChangeListener(FilterChangeListener listener) {

		this.filterListener = listener;

		attachSelectionListeners();

	}

	/**
	 * Provide the selected node if it exist.
	 * 
	 * @return The selected node or null if it does not exist.
	 */
	private DefaultMutableTreeNode getSelectedFilter() {

		TreeSelectionModel selectionNodeModel = getSelectionModel();
		TreePath selectedPath = selectionNodeModel.getSelectionPath();

		if (selectedPath != null) {
			Object lastPathObject = selectedPath.getLastPathComponent();
			if (lastPathObject != null
					&& lastPathObject instanceof DefaultMutableTreeNode) {
				return (DefaultMutableTreeNode) lastPathObject;
			}
		}

		return null;
	}

	/**
	 * Set a memory position with the selected node if it is selected.
	 * 
	 * @param memoryLocation
	 *            The memory position.
	 * @return True if the memory is set or false otherwise.
	 */
	public boolean setMemoryToTheSelectedNode(int memoryLocation) {

		DefaultMutableTreeNode selectedNode = getSelectedFilter();
		if (selectedNode != null && selectedNode.isLeaf()) {
			this.memories[memoryLocation] = selectedNode;
			return true;
		}

		return false;
	}

	/**
	 * Select a specific memory.
	 * 
	 * @param memoryLocation
	 *            The memory number.
	 */
	public void selectMemory(int memoryLocation) {

		DefaultMutableTreeNode memory = this.memories[memoryLocation];
		TreeSelectionModel memorySelectionModel = getSelectionModel();
		TreePath treePath = SwingUtil.getTreePath(memory);
		memorySelectionModel.setSelectionPath(treePath);
		makeVisible(treePath);

		Rectangle rectangle = getPathBounds(treePath);
		scrollRectToVisible(rectangle);

	}

	/**
	 * Indicate if a memory is set or not.
	 * 
	 * @param memoryLocation
	 *            The memory location.
	 * @return True if the memory is set.
	 */
	public boolean isMemorySet(int memoryLocation) {

		return this.memories[memoryLocation] != null;

	}

	/**
	 * Provide the name of the filter stored on a specific memory.
	 * 
	 * @param memoryLocation
	 *            the memory location.
	 * @return The filter name.
	 */
	public String getFilterDescriptionForMemory(int memoryLocation) {

		DefaultMutableTreeNode memory = this.memories[memoryLocation];
		if (memory == null) {
			return Text.get(Text.MEMORY_NOT_DEFINED);
		}

		Object userObject = memory.getUserObject();
		if (userObject instanceof Filter) {

			Filter filter = (Filter) userObject;
			return filter.getFilterName();
		}

		return Text.get(Text.MEMORY_NOT_DEFINED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.util.JTreeEventHelperListener#eventOnUserObject(java.
	 * lang.Object)
	 */
	@Override
	public void eventOnUserObject(Object object) {

		if (object instanceof Filter) {

			this.addMemory1MenuItem.setEnabled(true);
			this.addMemory2MenuItem.setEnabled(true);
			this.addMemory3MenuItem.setEnabled(true);
			this.addMemory4MenuItem.setEnabled(true);
			this.addMemory5MenuItem.setEnabled(true);

		} else {

			this.addMemory1MenuItem.setEnabled(false);
			this.addMemory2MenuItem.setEnabled(false);
			this.addMemory3MenuItem.setEnabled(false);
			this.addMemory4MenuItem.setEnabled(false);
			this.addMemory5MenuItem.setEnabled(false);
		}

	}

	/**
	 * Provide the memory button 1.
	 * 
	 * @return the memory1
	 */
	public JButton getMemory1() {
		return this.memory1;
	}

	/**
	 * Provide the memory button 2.
	 * 
	 * @return the memory2
	 */
	public JButton getMemory2() {
		return this.memory2;
	}

	/**
	 * Provide the memory button 3.
	 * 
	 * @return the memory3
	 */
	public JButton getMemory3() {
		return this.memory3;
	}

	/**
	 * Provide the memory button 4.
	 * 
	 * @return the memory4
	 */
	public JButton getMemory4() {
		return this.memory4;
	}

	/**
	 * Provide the memory button 5.
	 * 
	 * @return the memory5
	 */
	public JButton getMemory5() {
		return this.memory5;
	}

	/**
	 * provide the clear memory button.
	 * 
	 * @return the clearMemory
	 */
	public JButton getClearMemory() {
		return this.clearMemory;
	}
}
