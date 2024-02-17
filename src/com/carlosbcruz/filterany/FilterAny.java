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

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.carlosbcruz.filterany.SpecialBehavior.Behavior;
import com.carlosbcruz.filterany.TextBridgeFactory.TextBridge.TextInterfaceBean;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.filterany.configuration.FilterAnyConfigurationInterface;
import com.carlosbcruz.filterany.configuration.FilterAnyConfigurationJDialogStatus;
import com.carlosbcruz.filterany.configuration.FilterAnyInternalWindowPositionLayouts;
import com.carlosbcruz.filterany.configuration.FilterAnyInternalWindowsPositionBeam;
import com.carlosbcruz.util.ClipboardUtil;
import com.carlosbcruz.util.CustomSizeButton;
import com.carlosbcruz.util.EventHelperAdapter;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.FileDialogs;
import com.carlosbcruz.util.FileSupport;
import com.carlosbcruz.util.GlobalKeyJDialog;
import com.carlosbcruz.util.GlobalKeyListener;
import com.carlosbcruz.util.HelpPanel;
import com.carlosbcruz.util.InternalWindowPositionBean;
import com.carlosbcruz.util.Serialization;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.StatusBar;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Controls the graphical interface and the execution of filtersWindow.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAny extends GlobalKeyJDialog implements
		FilterChangeListener, EnterOnTextFieldsListener {

	private static final long serialVersionUID = 1L;

	private static TextAreaPanel topPanel = null;
	private static TextAreaPanel auxiliarPanel = null;
	private static TextAreaPanel bottomPanel = null;

	private static ScrapBook scrapbook = null;
	private static AboutWindow aboutWindow = null;

	private static FilterHelpShell helpShell = null;
	private static HelpPanel helpPanel = null;

	private JSplitPane textAreaSplit = null;
	private JSplitPane inputAreaSplit = null;
	private static JSplitPane mainVerticalSplit = null;
	private FiltersTree filtersTree = null;

	private JPanel internalStatusBar = null;

	private JToolBar toolBar = new JToolBar();

	private static ControlPanel controlsPanel = null;

	private static JDialog mainComponent = null;

	private static FilterAdapter currentFilter = null;

	private JDesktopPane desktopPane = null;
	private JInternalFrame topFrame = null;
	private JInternalFrame auxiliarFrame = null;
	private JInternalFrame bottomFrame = null;
	private JInternalFrame controlFrame = null;
	private JInternalFrame selectionFrame = null;
	private static JInternalFrame helpFrame = null;

	private static JMenu mainSendToPopupMenu = null;

	private static TaskManager taskManger = null;

	private TrayIcon trayIcon = null;
	private boolean traySupported = true;
	private boolean stillRunningMessagePresented = false;

	/**
	 * Construct the graphical interface.
	 */
	FilterAny() {

		setTitle(Text.get(Text.APPLICATON_TITLE));

		mainComponent = this;

		// Change the frame icon.
		SwingUtil.changeWindowIcon(getOwner(),
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		setJMenuBar(FilterAnyLogic.getMainMenuBar());

		// Construct the top, auxiliary input and bottom output panel.
		topPanel = new TextAreaPanel(mainComponent,
				Text.get(Text.TOP_AREA_FRAME_TITLE), new Color(230, 230, 255),
				new Color(220, 220, 255), new Color(0, 0, 200), new Color(150,
						150, 255), new Color(80, 80, 80), new Color(100, 0, 0),
				new Color(150, 150, 255), new Color(0, 0, 200), new Color(150,
						150, 240), Color.blue,
				TextAreaPanel.FIND_LOCATION.TOP_LEFT, true);

		topPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarSwitchBlueRedAction()));
		topPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarSwitchBlueGreenAction()));

		topPanel.addSeparatorToPopup();

		topPanel.addMenuItemToPopup(new JMenuItem(Event
				.getTextAreaFindAndReplaceMainAction()));
		topPanel.addMenuItemToPopup(new JMenuItem(Event.getGoToLineBlueAction()));
		topPanel.addMenuItemToPopup(new JMenuItem(Event.getCutFromMainAction()));
		topPanel.addMenuItemToPopup(new JMenuItem(Event.getCopyFromMainAction()));
		topPanel.addMenuItemToPopup(new JMenuItem(Event.getPasteToMainAction()));

		topPanel.addSeparatorToPopup();

		topPanel.addActionOnInternalFormattedEditorPopup();

		topPanel.addActionOnInternalFormattedEditorPopup(Event
				.getTextAreaFindAndReplaceMainFormattedAction());

		topPanel.addActionOnInternalFormattedEditorPopup(Event
				.getGoToLineBlueFormattedEditorAction());

		topPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCompareMainWithOutputAction()));
		topPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCompareMainWithAuxiliarAction()));

		topPanel.setGoToLineAction(Event.getGoToLineBlueAction());
		topPanel.setGoToLineOnFormattedEditorAction(Event
				.getGoToLineBlueFormattedEditorAction());
		topPanel.setFindOnFormattedEditorAction(Event
				.getTextAreaFindAndReplaceMainFormattedAction());

		mainSendToPopupMenu = new JMenu(Text.get(Text.MAIN_SEND_TO_POPUP_MENU));
		topPanel.addMenuToPopup(mainSendToPopupMenu);
		updateMainSendToPopupMenu();

		topPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarNewLineTopAction()));

		auxiliarPanel = new TextAreaPanel(mainComponent,
				Text.get(Text.AUXILIAR_INPUT_PANEL_TITLE), new Color(230, 255,
						230), new Color(220, 255, 220), new Color(0, 100, 0),
				new Color(150, 255, 150), new Color(80, 80, 80), new Color(100,
						0, 0), new Color(150, 255, 150), new Color(0, 100, 0),
				new Color(150, 240, 150), Color.green,
				TextAreaPanel.FIND_LOCATION.TOP_RIGHT, true);

		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarSwitchGreenRedAction()));
		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarSwitchBlueGreenAction()));

		auxiliarPanel.addSeparatorToPopup();

		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getTextAreaFindAndReplaceAuxiliarAction()));
		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getGoToLineGreenAction()));
		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCutFromAuxiliarAction()));
		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCopyFromAuxiliarAction()));
		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getPasteToAuxiliarAction()));

		auxiliarPanel.addSeparatorToPopup();

		auxiliarPanel.addActionOnInternalFormattedEditorPopup();

		auxiliarPanel.addActionOnInternalFormattedEditorPopup(Event
				.getTextAreaFindAndReplaceAuxiliarFormattedAction());

		auxiliarPanel.addActionOnInternalFormattedEditorPopup(Event
				.getGoToLineGreenFormattedEditorAction());

		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCompareAuxiliarWithOutputAction()));
		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCompareMainWithAuxiliarAction()));

		auxiliarPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarNewLineAuxiliarAction()));

		auxiliarPanel.setGoToLineAction(Event.getGoToLineGreenAction());
		auxiliarPanel.setGoToLineOnFormattedEditorAction(Event
				.getGoToLineGreenFormattedEditorAction());
		auxiliarPanel.setFindOnFormattedEditorAction(Event
				.getTextAreaFindAndReplaceAuxiliarFormattedAction());

		bottomPanel = new TextAreaPanel(mainComponent,
				Text.get(Text.BOTTOM_AREA_FRAME_TITLE),
				new Color(255, 230, 230), new Color(255, 220, 220), new Color(
						200, 0, 0), new Color(255, 150, 150), new Color(80, 80,
						80), new Color(100, 0, 0), new Color(255, 150, 150),
				new Color(200, 0, 0), new Color(240, 150, 150), Color.red,
				TextAreaPanel.FIND_LOCATION.BOTTOM_LEFT, true);

		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarSwitchBlueRedAction()));
		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarSwitchGreenRedAction()));

		bottomPanel.addSeparatorToPopup();

		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getTextAreaFindAndReplaceOutputAction()));
		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getGoToLineRedAction()));
		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCutFromOutputAction()));
		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCopyFromOutputAction()));
		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getPasteToOutputAction()));

		bottomPanel.addSeparatorToPopup();

		bottomPanel.addActionOnInternalFormattedEditorPopup();

		bottomPanel.addActionOnInternalFormattedEditorPopup(Event
				.getTextAreaFindAndReplaceOutputFormattedAction());

		bottomPanel.addActionOnInternalFormattedEditorPopup(Event
				.getGoToLineRedFormattedEditorAction());

		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCompareMainWithOutputAction()));
		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getCompareAuxiliarWithOutputAction()));

		bottomPanel.addMenuItemToPopup(new JMenuItem(Event
				.getToolbarNewLineBottomAction()));

		bottomPanel.setGoToLineAction(Event.getGoToLineRedAction());
		bottomPanel.setGoToLineOnFormattedEditorAction(Event
				.getGoToLineRedFormattedEditorAction());
		bottomPanel.setFindOnFormattedEditorAction(Event
				.getTextAreaFindAndReplaceOutputFormattedAction());

		// Create the left control.
		JPanel leftControllerPanel = new JPanel();

		leftControllerPanel.setLayout(new BorderLayout());

		DefaultMutableTreeNode rootNode = FilterAnyLogic.generateTree();
		this.filtersTree = new FiltersTree(rootNode, this, new Color(200, 255,
				255));

		// The auxiliar input is only enabled on certain filters.
		auxiliarPanel.setEnabledTextArea(false);

		// Load tree if available.
		loadTree();

		ToolTipManager.sharedInstance().registerComponent(this.filtersTree);

		// Initialize the control panel.
		controlsPanel = new ControlPanel(this);
		leftControllerPanel.add(controlsPanel, BorderLayout.SOUTH);

		controlsPanel.loadControls();

		JScrollPane selectionPanel = new JScrollPane(this.filtersTree);
		selectionPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.FILTER_LIST)));

		JPanel memoryPanel = new JPanel();

		JButton searchFilter = new CustomSizeButton(
				Event.getSearchForAFilterAction(), 60, 20);

		memoryPanel.add(searchFilter);
		memoryPanel.add(new JLabel("-")); //$NON-NLS-1$
		memoryPanel.add(this.filtersTree.getMemory1());
		memoryPanel.add(this.filtersTree.getMemory2());
		memoryPanel.add(this.filtersTree.getMemory3());
		memoryPanel.add(this.filtersTree.getMemory4());
		memoryPanel.add(this.filtersTree.getMemory5());
		memoryPanel.add(this.filtersTree.getClearMemory());

		JPanel treePanel = new JPanel(new BorderLayout());
		treePanel.add(selectionPanel, BorderLayout.CENTER);
		treePanel.add(memoryPanel, BorderLayout.SOUTH);
		leftControllerPanel.add(treePanel, BorderLayout.CENTER);

		StatusBar mainStatusBar = new StatusBar();

		// The internal frame ignores the clock, the right icon and is not
		// opaque.
		this.internalStatusBar = new JPanel(new BorderLayout());
		this.internalStatusBar.setOpaque(false);

		// Just fill the left side with an empty panel.
		JPanel emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);
		this.internalStatusBar.add(emptyPanel, BorderLayout.WEST);

		JButton button = new CustomSizeButton(Event.getGarbageAction(), 16, 16);

		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setContentAreaFilled(false);

		JPanel garbagePanel = new JPanel();
		garbagePanel.setOpaque(false);
		garbagePanel.add(new Memory());
		garbagePanel.add(button);

		this.internalStatusBar.add(garbagePanel, BorderLayout.EAST);

		mainStatusBar.add(this.internalStatusBar, BorderLayout.CENTER);

		mainStatusBar.add(
				SwingUtil.getClock(FilterAnyConfiguration.getCalendarFont()),
				BorderLayout.WEST);

		// Add the central panel that store the final status label.
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			centerPanel.add(new JLabel(Text
					.get(Text.DEMONSTRATION_VERSION_TEXT)));
		} else {

			JLabel registeredUser = new JLabel("Open Source"); //$NON-NLS-1$
			centerPanel.add(registeredUser);

			FilterAnyAnimation.animate(Text.get(Text.REGISTER_PREFIX),
					"Open Source", "OpenSource@user.com");
		}

		this.internalStatusBar.add(centerPanel, BorderLayout.CENTER);

		setLocation(FilterAnyConfiguration.getWindowPositionX(),
				FilterAnyConfiguration.getWindowPositionY());

		setSize(FilterAnyConfiguration.getWindowWidth(),
				FilterAnyConfiguration.getWindowHeight());

		attachEvent();

		// Update the toolbar.
		updateToolbar(this.toolBar);

		if (currentFilter != null) {
			updateFilterChanged(currentFilter);
		}

		setDefaultCloseOperation(HIDE_ON_CLOSE);

		// Instantiate and initialize the help.
		helpPanel = new HelpPanel(FilterAnyConfiguration.getMimeType(),
				FilterAnyConfiguration.getFilterHelpBackgroundColor());

		if (FilterAnyConfiguration.isAutomaticHelp()) {
			helpPanel.setHTMLText(Text
					.get(Text.HELP_PANEL_INITIAL_TEXT_AUTOMATIC));
		} else {
			helpPanel.setHTMLText(Text.get(Text.HELP_PANEL_INITIAL_TEXT));
		}
		helpShell = new FilterHelpShell(mainComponent, helpPanel);

		if (FilterAnyConfiguration.isUseInternalWindow()) {

			this.desktopPane = new JDesktopPane();

			this.topFrame = new JInternalFrame();
			this.topFrame.setTitle(Text.get(Text.TOP_AREA_FRAME_TITLE));
			this.topFrame.setContentPane(topPanel);
			this.topFrame.setClosable(false);
			this.topFrame.setResizable(true);
			this.topFrame.setMaximizable(true);
			this.topFrame.setIconifiable(true);
			this.topFrame.setFrameIcon(SwingUtil
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png")); //$NON-NLS-1$
			this.topFrame.pack();
			this.topFrame.setVisible(true);

			this.auxiliarFrame = new JInternalFrame();
			this.auxiliarFrame.setTitle(Text
					.get(Text.AUXILIAR_INPUT_PANEL_TITLE));
			this.auxiliarFrame.setContentPane(auxiliarPanel);
			this.auxiliarFrame.setClosable(false);
			this.auxiliarFrame.setResizable(true);
			this.auxiliarFrame.setMaximizable(true);
			this.auxiliarFrame.setIconifiable(true);
			this.auxiliarFrame.setFrameIcon(SwingUtil
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png")); //$NON-NLS-1$
			this.auxiliarFrame.pack();
			this.auxiliarFrame.setVisible(true);

			this.bottomFrame = new JInternalFrame();
			this.bottomFrame.setTitle(Text.get(Text.BOTTOM_AREA_FRAME_TITLE));
			this.bottomFrame.setContentPane(bottomPanel);
			this.bottomFrame.setClosable(false);
			this.bottomFrame.setResizable(true);
			this.bottomFrame.setResizable(true);
			this.bottomFrame.setMaximizable(true);
			this.bottomFrame.setIconifiable(true);
			this.bottomFrame.setFrameIcon(SwingUtil
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png")); //$NON-NLS-1$
			this.bottomFrame.pack();
			this.bottomFrame.setVisible(true);

			this.controlFrame = new JInternalFrame();
			this.controlFrame.setTitle(Text.get(Text.CONTROL_FRAME_TITLE));
			this.controlFrame.setContentPane(controlsPanel);
			this.controlFrame.setClosable(false);
			this.controlFrame.setResizable(true);
			this.controlFrame.setResizable(true);
			this.controlFrame.setMaximizable(true);
			this.controlFrame.setIconifiable(true);
			this.controlFrame.setFrameIcon(SwingUtil
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png")); //$NON-NLS-1$
			this.controlFrame.pack();
			this.controlFrame.setVisible(true);

			this.selectionFrame = new JInternalFrame();
			this.selectionFrame.setTitle(Text.get(Text.FILTER_FRAME_TITLE));
			this.selectionFrame.setContentPane(treePanel);
			this.selectionFrame.setClosable(false);
			this.selectionFrame.setResizable(true);
			this.selectionFrame.setResizable(true);
			this.selectionFrame.setMaximizable(true);
			this.selectionFrame.setIconifiable(true);
			this.selectionFrame.setFrameIcon(SwingUtil
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png")); //$NON-NLS-1$
			this.selectionFrame.pack();
			this.selectionFrame.setVisible(true);

			helpFrame = new JInternalFrame();

			helpFrame.setTitle(Text.get(Text.HELP_PANEL_TITLE));
			helpFrame.setContentPane(helpPanel);
			helpFrame.setClosable(false);
			helpFrame.setResizable(true);
			helpFrame.setResizable(true);
			helpFrame.setMaximizable(true);
			helpFrame.setIconifiable(true);
			helpFrame.setFrameIcon(SwingUtil
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png")); //$NON-NLS-1$

			this.desktopPane.add(this.topFrame);
			this.desktopPane.add(this.auxiliarFrame);
			this.desktopPane.add(this.bottomFrame);
			this.desktopPane.add(this.controlFrame);
			this.desktopPane.add(this.selectionFrame);
			this.desktopPane.add(FilterAny.helpFrame);

			JPanel mainInternalPanel = new JPanel(new BorderLayout());
			mainInternalPanel.add(this.toolBar, BorderLayout.PAGE_START);
			mainInternalPanel.add(this.desktopPane, BorderLayout.CENTER);
			mainInternalPanel.add(mainStatusBar, BorderLayout.SOUTH);

			setContentPane(mainInternalPanel);

			setVisible(true);

			Dimension size = this.desktopPane.getSize();

			if (FilterAnyConfiguration.isInitializeFilterList()) {

				Dimension desktopSize = this.desktopPane.getSize();

				FilterAnyInternalWindowsPositionBeam newPosition = FilterAnyConfiguration
						.getWindowsPosition(
								desktopSize.width,
								desktopSize.height,
								FilterAnyInternalWindowPositionLayouts.DEFAULT_POSITION);

				changeWindowPosition(newPosition);

			} else {
				FilterAnyInternalWindowsPositionBeam position = FilterAnyConfiguration
						.loadWindowsPosition(size.width, size.height);

				changeWindowPosition(position);
			}

			EventHelperAdapter desktopMouseEvents = new EventHelperAdapter();
			desktopMouseEvents.addMenuItemToPopup(new JMenuItem(Event
					.getDefaultPositionAction()));
			desktopMouseEvents.addMenuItemToPopup(new JMenuItem(Event
					.getVerticalAreasAction()));
			desktopMouseEvents.addMenuItemToPopup(new JMenuItem(Event
					.getControlOnTheMiddleAction()));
			desktopMouseEvents.addMenuItemToPopup(new JMenuItem(Event
					.getControlInTheRightVerticalAction()));
			desktopMouseEvents.addMenuItemToPopup(new JMenuItem(Event
					.getControlInTheRightAction()));
			this.desktopPane.addMouseListener(desktopMouseEvents);

			// Change the layout to the default;
			Event.getDefaultPositionAction().addObserver(
					new SimpleActionObserver() {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * com.carlosbcruz.util.SimpleActionObserver#update(
						 * com.carlosbcruz.util.SimpleActionSubject)
						 */
						@Override
						@SuppressWarnings({ "synthetic-access",
								"unqualified-field-access" })
						public void update(SimpleActionSubject simpleSubject) {

							Dimension desktopSize = desktopPane.getSize();

							FilterAnyInternalWindowsPositionBeam newPosition = FilterAnyConfiguration
									.getWindowsPosition(
											desktopSize.width,
											desktopSize.height,
											FilterAnyInternalWindowPositionLayouts.DEFAULT_POSITION);

							changeWindowPosition(newPosition);
						}

					});

			// Change the layout to the vertical position.
			Event.getVerticalAreasAction().addObserver(
					new SimpleActionObserver() {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * com.carlosbcruz.util.SimpleActionObserver#update(
						 * com.carlosbcruz.util.SimpleActionSubject)
						 */
						@Override
						@SuppressWarnings({ "synthetic-access",
								"unqualified-field-access" })
						public void update(SimpleActionSubject simpleSubject) {

							Dimension desktopSize = desktopPane.getSize();

							FilterAnyInternalWindowsPositionBeam newPosition = FilterAnyConfiguration
									.getWindowsPosition(
											desktopSize.width,
											desktopSize.height,
											FilterAnyInternalWindowPositionLayouts.VERTICAL_AREAS);

							changeWindowPosition(newPosition);
						}

					});

			// Change the layout to the controls in the middle.
			Event.getControlOnTheMiddleAction().addObserver(
					new SimpleActionObserver() {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * com.carlosbcruz.util.SimpleActionObserver#update(
						 * com.carlosbcruz.util.SimpleActionSubject)
						 */
						@Override
						@SuppressWarnings({ "synthetic-access",
								"unqualified-field-access" })
						public void update(SimpleActionSubject simpleSubject) {

							Dimension desktopSize = desktopPane.getSize();

							FilterAnyInternalWindowsPositionBeam newPosition = FilterAnyConfiguration
									.getWindowsPosition(
											desktopSize.width,
											desktopSize.height,
											FilterAnyInternalWindowPositionLayouts.CONTROL_ON_THE_MIDDLE);

							changeWindowPosition(newPosition);
						}

					});

			// Change the layout to the controls in right and areas on
			// vertical.
			Event.getControlInTheRightVerticalAction().addObserver(
					new SimpleActionObserver() {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * com.carlosbcruz.util.SimpleActionObserver#update(
						 * com.carlosbcruz.util.SimpleActionSubject)
						 */
						@Override
						@SuppressWarnings({ "synthetic-access",
								"unqualified-field-access" })
						public void update(SimpleActionSubject simpleSubject) {

							Dimension desktopSize = desktopPane.getSize();

							FilterAnyInternalWindowsPositionBeam newPosition = FilterAnyConfiguration
									.getWindowsPosition(
											desktopSize.width,
											desktopSize.height,
											FilterAnyInternalWindowPositionLayouts.CONTROL_ON_THE_RIGHT_WITH_VERTICAL_AREAS);

							changeWindowPosition(newPosition);
						}

					});

			// Change the layout to the controls in right and areas on
			// horizontal.
			Event.getControlInTheRightAction().addObserver(
					new SimpleActionObserver() {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * com.carlosbcruz.util.SimpleActionObserver#update(
						 * com.carlosbcruz.util.SimpleActionSubject)
						 */
						@Override
						@SuppressWarnings({ "synthetic-access",
								"unqualified-field-access" })
						public void update(SimpleActionSubject simpleSubject) {

							Dimension desktopSize = desktopPane.getSize();

							FilterAnyInternalWindowsPositionBeam newPosition = FilterAnyConfiguration
									.getWindowsPosition(
											desktopSize.width,
											desktopSize.height,
											FilterAnyInternalWindowPositionLayouts.CONTROL_ON_THE_RIGHT);

							changeWindowPosition(newPosition);
						}

					});
		}

		if (FilterAnyConfiguration.isUseSplitters()) {

			JPanel mainPanel = new JPanel(new BorderLayout());

			mainPanel.add(this.toolBar, BorderLayout.PAGE_START);

			JPanel topPanelWithTitle = SwingUtil.addTitleToJPanel(
					Text.get(Text.TOP_AREA_FRAME_TITLE), topPanel, null, null);
			JPanel auxiliarPanelWithTitle = SwingUtil.addTitleToJPanel(
					Text.get(Text.AUXILIAR_INPUT_PANEL_TITLE), auxiliarPanel,
					null, null);
			JPanel bottomPanelWithTitle = SwingUtil.addTitleToJPanel(
					Text.get(Text.BOTTOM_AREA_FRAME_TITLE), bottomPanel, null,
					null);

			// Add the text areas on a vertical or horizontal.
			if (FilterAnyConfiguration.isUseVerticalTextSplit()) {
				this.inputAreaSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
						topPanelWithTitle, auxiliarPanelWithTitle);
				this.textAreaSplit = new JSplitPane(
						JSplitPane.HORIZONTAL_SPLIT, this.inputAreaSplit,
						bottomPanelWithTitle);
			} else {
				// Split the left side in two.
				this.inputAreaSplit = new JSplitPane(
						JSplitPane.HORIZONTAL_SPLIT, topPanelWithTitle,
						auxiliarPanelWithTitle);
				this.textAreaSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
						this.inputAreaSplit, bottomPanelWithTitle);
			}
			this.textAreaSplit.setDividerLocation(FilterAnyConfiguration
					.getTextAreasSplitterLocation());
			this.inputAreaSplit.setDividerLocation(FilterAnyConfiguration
					.getInputAreasSplitterLocation());

			this.inputAreaSplit.setOneTouchExpandable(true);
			this.textAreaSplit.setOneTouchExpandable(true);

			// Add the controls on the right or left.
			if (FilterAnyConfiguration.isControlsAreOnTheRight()) {
				// Split the window in two and controls on the right.
				mainVerticalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
						this.textAreaSplit, leftControllerPanel);
			} else {
				// Split the window in two and controls on the left.
				mainVerticalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
						leftControllerPanel, this.textAreaSplit);
			}
			mainVerticalSplit.setDividerLocation(FilterAnyConfiguration
					.getControlsSplitterLocation());

			mainPanel.add(mainVerticalSplit, BorderLayout.CENTER);

			mainPanel.add(mainStatusBar, BorderLayout.SOUTH);

			setContentPane(mainPanel);

			setVisible(true);
		}

		// See if it is on automatic mode.
		Timer executionTimer = new Timer(1000, new ActionListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!FilterAnyConfiguration.isAutomaticMode()) {
					return;
				}

				if (currentFilter == null) {
					return;
				}

				if (currentFilter instanceof SpecialBehavior
						&& FilterAnyLogic.hasSpecialBehavior(
								(SpecialBehavior) currentFilter,
								Behavior.ACCEPT_AUTOMATIC_BEHAVIOR)) {

					executeFilterOnText();
				}
			}
		});

		loadTextAreas();

		executionTimer.start();

		requestFocus();

		toFront();

		// Code that validated the hardware information.
		// if (!FilterAnyConfiguration.isRunningADemonstrationVersion()) {
		//
		// RegistrationComputer registrationComputer = new
		// RegistrationComputer();
		// if (!ComputerIdentity.getComputerIdentification().equals(
		// registrationComputer.getRegisteredText())) {
		// new RegistrationDialog(mainComponent);
		// }
		// }

		FilterAnyConfiguration.setInitializeFilterList(false);

		setTray();

	}

	/**
	 * Set the tray.
	 */
	private void setTray() {

		this.traySupported = SystemTray.isSupported();

		if (this.traySupported) {

			SystemTray tray = SystemTray.getSystemTray();

			ImageIcon icon = SwingUtil
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnySmallIcon.png"); //$NON-NLS-1$

			PopupMenu popup = new PopupMenu();

			MenuItem showItem = new MenuItem(
					Text.get(Text.TRAY_MENY_ITEM_SHOW), new MenuShortcut(
							SwingUtil.getKeyEvent(Text
									.get(Text.TRAY_MENY_ITEM_SHOW_KEY))));

			showItem.addActionListener(new ActionListener() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.ActionListener#actionPerformed(java.awt.event
				 * .ActionEvent)
				 */
				@Override
				public void actionPerformed(ActionEvent arg0) {

					setVisible(true);

				}

			});

			MenuItem exitItem = new MenuItem(
					Text.get(Text.TRAY_MENY_ITEM_EXIT), new MenuShortcut(
							SwingUtil.getKeyEvent(Text
									.get(Text.TRAY_MENY_ITEM_EXIT_KEY))));

			exitItem.addActionListener(new ActionListener() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.ActionListener#actionPerformed(java.awt.event
				 * .ActionEvent)
				 */
				@SuppressWarnings("synthetic-access")
				@Override
				public void actionPerformed(ActionEvent arg0) {

					deactivateGlobalKeyListener();

					exitApplication();

					System.exit(0);
				}

			});

			popup.add(showItem);
			popup.add("-"); //$NON-NLS-1$
			popup.add(exitItem);

			this.trayIcon = new TrayIcon(icon.getImage(),
					Text.get(Text.TRAY_TOOLTIP), popup);

			// Respond to double click.
			this.trayIcon.addActionListener(new ActionListener() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.ActionListener#actionPerformed(java.awt.event
				 * .ActionEvent)
				 */
				@SuppressWarnings("synthetic-access")
				@Override
				public void actionPerformed(ActionEvent e) {

					if (isVisible()) {
						hideFilterAnyOnTray();
						setVisible(false);
					} else {
						setVisible(true);
					}
				}
			});

			try {
				tray.add(this.trayIcon);
			} catch (AWTException exception) {
				ExceptionSupport.handleException(mainComponent, Text.get(
						Text.EXCEPTION_WHILE_ADDING_AN_ICON_TO_TRAY,
						exception.getMessage()));
			}

		} else {

			ImageIcon icon = SwingUtil
					.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png"); //$NON-NLS-1$

			JOptionPane.showMessageDialog(mainComponent,
					Text.get(Text.TRAY_MESSAGE),
					Text.get(Text.TRAY_MESSAGE_TITLE),
					JOptionPane.WARNING_MESSAGE, icon);
		}
	}

	/**
	 * Modify the position of all windows.
	 * 
	 * @param position
	 *            The position bean.
	 */
	private void changeWindowPosition(
			FilterAnyInternalWindowsPositionBeam position) {

		SwingUtil.setWindowPosition(this.selectionFrame,
				position.getFiltersWindow());

		SwingUtil.setWindowPosition(this.topFrame, position.getTopWindow());

		SwingUtil.setWindowPosition(this.auxiliarFrame,
				position.getAuxiliarWindow());

		SwingUtil.setWindowPosition(this.bottomFrame,
				position.getBottomWindow());

		SwingUtil.setWindowPosition(this.controlFrame,
				position.getControlWindow());

		SwingUtil.setWindowPosition(helpFrame, position.getHelpWindow());
	}

	/**
	 * Attach many types of listeners and observers to events.
	 */
	private void attachEvent() {

		// Close the application when requested.
		addWindowListener(new WindowAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent
			 * )
			 */
			@Override
			public void windowClosing(WindowEvent e) {

				// exitApplication();

			}
		});

		// Exit the application when exit option is selected.
		Event.getExitAction().addObserver(new SimpleActionObserver() {

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

				deactivateGlobalKeyListener();

				exitApplication();

				System.exit(0);

			}
		});

		Event.getConfigurationAction().addObserver(new SimpleActionObserver() {

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

				FilterAnyConfigurationInterface configuration = FilterAnyConfigurationInterface
						.getInstance();
				configuration.setVisible(true);
			}
		});

		// Show the help instructions.
		Event.getContentAction().addObserver(new SimpleActionObserver() {

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

				try {

					new Help(mainComponent);

				} catch (IOException exception) {
					ExceptionSupport.handleException(mainComponent, Text.get(
							Text.EXCEPTION_WHILE_READING_HELP_FILE,
							exception.getMessage()));
				}
			}
		});

		// Add the shorcut list.
		Event.getShortCutAction().addObserver(new SimpleActionObserver() {

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

				try {

					new ShortCuts(mainComponent);

				} catch (IOException exception) {
					ExceptionSupport.handleException(mainComponent, Text.get(
							Text.EXCEPTION_WHILE_READING_SHORCUT_FILE,
							exception.getMessage()));
				}

			}
		});

		// Show instruction when instruction button is pressed.
		Event.getInstructionAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "synthetic-access" })
			public void update(SimpleActionSubject simpleSubject) {

				updateFilterHelp();

			}

		});

		Event.getExampleAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "synthetic-access" })
			public void update(SimpleActionSubject simpleSubject) {

				if (currentFilter instanceof Example) {

					Example example = (Example) currentFilter;

					boolean isAnyAreaNotSaved = false;

					String mainInputMessage = new String();
					if (!topPanel.isTextAreaSaved()) {
						mainInputMessage = Text
								.get(Text.EXAMPLE_OVERWRITE_MAIN_INPUT);
						isAnyAreaNotSaved = true;
					}

					String outputMessage = new String();
					if (!bottomPanel.isTextAreaSaved()) {
						outputMessage = Text.get(Text.EXAMPLE_OVERWRITE_OUTPUT);
						isAnyAreaNotSaved = true;
					}

					String auxiliarMessage = new String();
					if (!auxiliarPanel.isTextAreaSaved()
							&& !"".equals(example.getAuxiliarInputExample())) { //$NON-NLS-1$
						auxiliarMessage = Text
								.get(Text.EXAMPLE_OVERWRITE_AUXILIAR);
						isAnyAreaNotSaved = true;
					}

					if (isAnyAreaNotSaved) {
						int response = JOptionPane.showConfirmDialog(
								mainComponent,
								Text.get(
										Text.EXAMPLE_OVERWRITE_QUESTION_MESSAGE,
										mainInputMessage, outputMessage,
										auxiliarMessage),
								Text.get(Text.EXAMPLE_OVERWRITE_QUESTION_TITLE),
								JOptionPane.YES_NO_OPTION);
						if (response == JOptionPane.YES_OPTION) {
							topPanel.setTextArea(example.getMainInputExample());
							auxiliarPanel.setTextArea(example
									.getAuxiliarInputExample());
							bottomPanel.setTextArea(example.getOutputExample());
						}

					} else {

						topPanel.setTextArea(example.getMainInputExample());
						auxiliarPanel.setTextArea(example
								.getAuxiliarInputExample());
						bottomPanel.setTextArea(example.getOutputExample());
					}
				}
			}

		});

		// Executed the selected filter when the execute button is pressed.
		Event.getExecuteAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				try {

					executeCurrentFilter();

				} catch (Throwable exception) {

					ExceptionSupport.handleException(exception,
							Text.get(Text.THROWABLE_EXCEPTION_MESSAGE),
							Text.get(Text.OUT_OF_MEMORY_EXCEPTION_MESSAGE));

				}
			}

		});

		// Event to switch the contents between Main and Output Area.
		Event.getToolbarSwitchBlueRedAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						switchMainWithOutputPanelsContent();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.SWITCH);
					}

				});

		// Event to switch the contents between Auxiliar and Output Area.
		Event.getToolbarSwitchGreenRedAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						switchAuxiliarWithOutputPanelsContent();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.SWITCH);
					}

				});

		// Event to switch the contents between Main and Auxiliar Area.
		Event.getToolbarSwitchBlueGreenAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						switchMainWithAuxiliarPanelsContent();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.SWITCH);
					}

				});

		// Save the Main Area into a file;
		Event.getToolbarSaveTopAsAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						topPanel.saveTextArea();
						topPanel.updateLabels();
					}

				});

		// Save the auxiliar area into a file;
		Event.getToolbarSaveAuxiliarAsAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						auxiliarPanel.saveTextArea();
						auxiliarPanel.updateLabels();
					}
				});

		// Save the output area into a file;
		Event.getToolbarSaveBottomAsAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						bottomPanel.saveTextArea();
						bottomPanel.updateLabels();
					}

				});

		// Update the text area statistics.
		Event.getToolbarUpdateAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				topPanel.updateLabels();
				auxiliarPanel.updateLabels();
				bottomPanel.updateLabels();

				FilterAnySound.playSound(FilterAnySound.SOUNDS.GO);

			}
		});

		// Switch between new line type of DOS and UNIX.
		Event.getToolbarNewLineAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
			 * .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				topPanel.setTextArea(StringSupport.convertLineType(topPanel
						.getTextArea()));
				topPanel.updateLabels();

				auxiliarPanel.setTextArea(StringSupport
						.convertLineType(auxiliarPanel.getTextArea()));

				auxiliarPanel.updateLabels();

				bottomPanel.setTextArea(StringSupport
						.convertLineType(bottomPanel.getTextArea()));

				bottomPanel.updateLabels();

				FilterAnySound.playSound(FilterAnySound.SOUNDS.SWITCH);

			}
		});

		// Switch between new line type of DOS and UNIX on top text area.
		Event.getToolbarNewLineTopAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						topPanel.setTextArea(StringSupport
								.convertLineType(topPanel.getTextArea()));

						topPanel.updateLabels();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.SWITCH);
					}
				});

		// Switch between new line type of DOS and UNIX on bottom text area.
		Event.getToolbarNewLineAuxiliarAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						auxiliarPanel.setTextArea(StringSupport
								.convertLineType(auxiliarPanel.getTextArea()));

						auxiliarPanel.updateLabels();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.SWITCH);
					}
				});

		// Switch between new line type of DOS and UNIX on bottom text area.
		Event.getToolbarNewLineBottomAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						bottomPanel.setTextArea(StringSupport
								.convertLineType(bottomPanel.getTextArea()));

						bottomPanel.updateLabels();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.SWITCH);
					}
				});

		// Switch between text and file mode.
		Event.getToolbarSwitchBetweenTextAndFile().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						switchBetweenTextAndTable();

						FilterAnyLogic.turnFileMode();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.ON);

					}

				});

		// Switch between text and file mode.
		Event.getToolbarSwitchBetweenTextAndFileTurnedOn().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						switchBetweenTextAndTable();

						FilterAnyLogic.turnTextMode();

					}

				});

		// Turn automatic mode off.
		Event.getToolBarAutomaticModeActionTurnedOff().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						switchAutomaticMode();

						FilterAnyLogic.turnAutomaticModeOn();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.OFF);
					}
				});

		addComponentListener(new ComponentListener() {

			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			private void updateStatus() {

				if (!SwingUtil.isMaximumDimension(mainComponent)) {

					if (FilterAnyConfigurationJDialogStatus.wasFlaggedMaximized()) {

						updateToolbar(toolBar);
					}

					FilterAnyConfigurationJDialogStatus.recordWindowStatus(mainComponent);
				}

			}

			@Override
			public void componentShown(ComponentEvent e) {
				updateStatus();
			}

			@Override
			public void componentResized(ComponentEvent e) {
				updateStatus();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				updateStatus();
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				updateStatus();
			}
		});

		// Turn automatic mode on.
		Event.getToolBarAutomaticModeActionTurnedOn().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						switchAutomaticMode();

						FilterAnyLogic.turnAutomaticModeOff();

						FilterAnySound.playSound(FilterAnySound.SOUNDS.ON);

					}
				});

		// Maximize the window.
		Event.getToolBarMaximizeAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings({ "synthetic-access",
							"unqualified-field-access" })
					public void update(SimpleActionSubject simpleSubject) {

						if (!SwingUtil.isMaximumDimension(mainComponent)) {

							FilterAnyConfigurationJDialogStatus
									.maximizeWindow(mainComponent);

							FilterAnyConfigurationJDialogStatus.flagWindowMaximized();

							updateToolbar(toolBar);
						}
					}
				});

		// Maximize the window.
		Event.getToolBarRestoreAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz.util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			public void update(SimpleActionSubject simpleSubject) {

				if (SwingUtil.isMaximumDimension(mainComponent)) {

					FilterAnyConfigurationJDialogStatus
							.restorePreviousWindowStatus(mainComponent);

					updateToolbar(toolBar);
				}
			}
		});

		// Turn the automatic help mode off.
		Event.getAutomaticHelpModeActionTurnedOn().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings({ "synthetic-access",
							"unqualified-field-access" })
					public void update(SimpleActionSubject simpleSubject) {

						FilterAnyConfiguration
								.setAutomaticHelp(!FilterAnyConfiguration
										.isAutomaticHelp());

						FilterAnySound.playSound(FilterAnySound.SOUNDS.ON);

						updateToolbar(toolBar);

						FilterAnyLogic.turnAutomaticHelpOff();

						if (FilterAnyConfiguration.isAutomaticHelp()) {
							helpPanel.setHTMLText(Text
									.get(Text.HELP_PANEL_INITIAL_TEXT_AUTOMATIC));
						} else {
							helpPanel.setHTMLText(Text
									.get(Text.HELP_PANEL_INITIAL_TEXT));
						}

						controlsPanel.setHelpButtonEnabled(true);

					}
				});

		// Turn the automatic help mode on.
		Event.getAutomaticHelpModeActionTurnedOff().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings({ "synthetic-access",
							"unqualified-field-access" })
					public void update(SimpleActionSubject simpleSubject) {

						FilterAnyConfiguration
								.setAutomaticHelp(!FilterAnyConfiguration
										.isAutomaticHelp());

						FilterAnySound.playSound(FilterAnySound.SOUNDS.OFF);

						updateToolbar(toolBar);

						FilterAnyLogic.turnAutomaticHelpOn();

						if (FilterAnyConfiguration.isAutomaticHelp()) {
							helpPanel.setHTMLText(Text
									.get(Text.HELP_PANEL_INITIAL_TEXT_AUTOMATIC));
						} else {
							helpPanel.setHTMLText(Text
									.get(Text.HELP_PANEL_INITIAL_TEXT));
						}

						controlsPanel.setHelpButtonEnabled(false);
					}
				});

		// Add the event to the Filter Search button.
		Event.getFilterTarget_1_ButtomAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						String target = FileDialogs
								.selectInputFileOrDirectoryBySwing(
										mainComponent, null,
										JFileChooser.FILES_AND_DIRECTORIES);
						controlsPanel.setTargetFileOrDirectory1Field(target);
					}
				});

		// Add the event to the target directory button.
		Event.getFilterTarget_Directory_ButtomAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						String target = FileDialogs
								.selectInputFileOrDirectoryBySwing(
										mainComponent, null,
										JFileChooser.DIRECTORIES_ONLY);
						controlsPanel.setTargetDirectoryText(target);
					}
				});

		// Add the event to the target file button.
		Event.getFileSelectionButtonAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						String target = FileDialogs
								.selectInputFileOrDirectoryBySwing(
										mainComponent, null,
										JFileChooser.FILES_ONLY);
						controlsPanel.setTarget1FileText(target);
					}
				});

		// Add the event to the target directory button.
		Event.getDirectorySelectionButtonAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz.util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						String target = FileDialogs
								.selectInputFileOrDirectoryBySwing(
										mainComponent, null,
										JFileChooser.DIRECTORIES_ONLY);
						controlsPanel.setTarget1DirectoryText(target);
					}
				});

		// Add the event that shows the find dialog to the formatted editor
		// inside main area.
		Event.getTextAreaFindAndReplaceMainAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						topPanel.showFindDialog();
					}
				});

		// Add the event that shows the find dialog to the auxiliar area.
		Event.getTextAreaFindAndReplaceAuxiliarAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						auxiliarPanel.showFindDialog();
					}
				});

		// Add the event that shows the find dialog to the output area.
		Event.getTextAreaFindAndReplaceOutputAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						bottomPanel.showFindDialog();
					}
				});

		// Add the event that shows the find dialog to the main area.
		Event.getTextAreaFindAndReplaceMainFormattedAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						topPanel.showFindDialogOnFormattedEditor();
					}
				});

		// Add the event that shows the find dialog to the the formatted editor
		// inside auxiliar area.
		Event.getTextAreaFindAndReplaceAuxiliarFormattedAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						auxiliarPanel.showFindDialogOnFormattedEditor();
					}
				});

		// Add the event that shows the find dialog to the the formatted editor
		// inside the output area.
		Event.getTextAreaFindAndReplaceOutputFormattedAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						bottomPanel.showFindDialogOnFormattedEditor();
					}
				});

		// Show the find window on the scrapbook formatted editor.
		Event.getTextAreaFindAndReplaceScrapBookFormattedAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						scrapbook.showGoToLineDialogOnFormattedEditor();
					}
				});

		// Add the go to line functionality to the main formatted editor.
		Event.getGoToLineBlueFormattedEditorAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						topPanel.showGoToLineDialogOnFormattedEditor();
					}
				});

		// Add the go to line functionality to the output formatted editor.
		Event.getGoToLineRedFormattedEditorAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						bottomPanel.showGoToLineDialogOnFormattedEditor();
					}
				});

		// Add the go to line functionality to the output formatted editor.
		Event.getGoToLineGreenFormattedEditorAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						auxiliarPanel.showGoToLineDialogOnFormattedEditor();
					}
				});

		// Compare main with output text.
		Event.getCompareMainWithOutputAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						ExceptionSupport.handleException("Implementado somente na vers�o completa / Implemented on full version only.");
					}
				});

		// Compare main with auxiliar text.
		Event.getCompareMainWithAuxiliarAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						ExceptionSupport.handleException("Implementado somente na vers�o completa / Implemented on full version only.");
					}
				});

		// Compare auxiliar with output text.
		Event.getCompareAuxiliarWithOutputAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {
						
						ExceptionSupport.handleException("Implementado somente na vers�o completa / Implemented on full version only.");
					}
				});

		// Open the scrapbook.
		Event.getScrapBookAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				if (scrapbook != null) {
					scrapbook.setVisible(true);
				} else {
					scrapbook = new ScrapBook(mainComponent);

					scrapbook.addActionOnInternalFormattedEditorPopup(Event
							.getTextAreaFindAndReplaceScrapBookFormattedAction());
				}

			}
		});

		// Show the command line generator.
		Event.getCommandLineGeneratorAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					public void update(SimpleActionSubject simpleSubject) {

						CommandLineGenerator commandLineGenerator = CommandLineGenerator
								.getInstance();

						commandLineGenerator.setVisible(true);

						commandLineGenerator.showLastLine();
					}
				});

		// Show the task manager.
		Event.getTaskManagerAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				showTaskManager();
			}

		});

		// Show the network.
		Event.getNetworkAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				ExceptionSupport.handleException("Implementado somente na vers�o completa / Implemented on full version only.");
			}

		});

		// Show the about window.
		Event.getAboutAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				if (aboutWindow == null) {

					aboutWindow = new AboutWindow(mainComponent);
				}

				aboutWindow.setVisible(true);

			}
		});

		// Show a registration form.
		Event.getRegisterButtonAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				ExceptionSupport.handleException("Implementado somente na vers�o completa / Implemented on full version only.");
			}
		});

		// Show the license agreement.
		Event.getLicenseAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings({ "unused" })
			public void update(SimpleActionSubject simpleSubject) {

				return;
			}
		});

		// Cut the text from main action.
		Event.getCutFromMainAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				String text = topPanel.cut();

				ClipboardFactory.getClipboardInstance().setClipboardContents(
						text);
			}
		});

		// Cut the text from auxiliar action.
		Event.getCutFromAuxiliarAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						String text = auxiliarPanel.cut();

						ClipboardFactory.getClipboardInstance()
								.setClipboardContents(text);
					}
				});

		// Cut the text from output action.
		Event.getCutFromOutputAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				String text = bottomPanel.cut();

				ClipboardFactory.getClipboardInstance().setClipboardContents(
						text);
			}
		});

		// Paste the content from clipboard to the Main area action.
		Event.getPasteToMainAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				ClipboardFactory.getClipboardInstance();
				String text = ClipboardUtil.getClipboardContents();

				topPanel.paste(text);
			}
		});

		// Paste the content from clipboard to the Auxiliar area action.
		Event.getPasteToAuxiliarAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						ClipboardFactory.getClipboardInstance();
						String text = ClipboardUtil.getClipboardContents();

						auxiliarPanel.paste(text);
					}
				});

		// Paste the content from clipboard to the Output area action.
		Event.getPasteToOutputAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				ClipboardFactory.getClipboardInstance();
				String text = ClipboardUtil.getClipboardContents();

				bottomPanel.paste(text);
			}
		});

		// Copy the content from the Main area to the clipboard action.
		Event.getCopyFromMainAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				String text = topPanel.copy();

				ClipboardFactory.getClipboardInstance().setClipboardContents(
						text);
			}
		});

		// Copy the content from the Auxiliar area to the clipboard action.
		Event.getCopyFromAuxiliarAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings("synthetic-access")
					public void update(SimpleActionSubject simpleSubject) {

						String text = auxiliarPanel.copy();

						ClipboardFactory.getClipboardInstance()
								.setClipboardContents(text);
					}
				});

		// Copy the content from the Output area to the clipboard action.
		Event.getCopyFromOutputAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				String text = bottomPanel.copy();

				ClipboardFactory.getClipboardInstance().setClipboardContents(
						text);
			}
		});

		// Search for a filter.
		Event.getSearchForAFilterAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.carlosbcruz.util.SimpleActionObserver#update(com.
					 * carlosbcruz .util.SimpleActionSubject)
					 */
					@Override
					@SuppressWarnings({ "synthetic-access",
							"unqualified-field-access" })
					public void update(SimpleActionSubject simpleSubject) {

						filtersTree.showSearchFilterDialog(
								mainComponent.getLocation().x,
								mainComponent.getLocation().y,
								mainComponent.getSize().width,
								mainComponent.getSize().height);

					}
				});

		// Shows filter search dialog.
		addGlobalKeyListener(new GlobalKeyListener() {

			@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
			@Override
			public void globalKeyPressed(KeyEvent e) {

				int code = e.getKeyCode();
				if (code == KeyEvent.VK_F1) {

					filtersTree.showSearchFilterDialog(
							mainComponent.getLocation().x,
							mainComponent.getLocation().y,
							mainComponent.getSize().width,
							mainComponent.getSize().height);

				}

				if (code == KeyEvent.VK_Q && e.isControlDown()) {

					deactivateGlobalKeyListener();

					exitApplication();

					System.exit(0);
				}

			}

		});

		// Clear the memory.
		Event.getGarbageAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				topPanel.setTextArea(""); //$NON-NLS-1$
				topPanel.clearHistory();
				topPanel.setTextAreaSaved(true);
				auxiliarPanel.setTextArea(""); //$NON-NLS-1$
				auxiliarPanel.clearHistory();
				auxiliarPanel.setTextAreaSaved(true);
				bottomPanel.setTextArea(""); //$NON-NLS-1$
				bottomPanel.clearHistory();
				bottomPanel.setTextAreaSaved(true);

				if (scrapbook != null) {
					scrapbook.clearHistory();
				}

				System.gc();
			}
		});

		// Save the windows position everytime the FilterAny window is closed.
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
			public void windowClosing(WindowEvent arg0) {

				hideFilterAnyOnTray();
			}

		});

		// Show Go To Line panel.
		Event.getGoToLineRedAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				bottomPanel.showGoToLineDialog();
			}
		});

		// Show Go To Line panel.
		Event.getGoToLineGreenAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				auxiliarPanel.showGoToLineDialog();
			}
		});

		// Show Go To Line panel.
		Event.getGoToLineBlueAction().addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.carlosbcruz.util.SimpleActionObserver#update(com.
			 * carlosbcruz .util.SimpleActionSubject)
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void update(SimpleActionSubject simpleSubject) {

				topPanel.showGoToLineDialog();
			}
		});

		// Hide FilterAny with an ESC.
		addGlobalKeyListener(new GlobalKeyListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void globalKeyPressed(KeyEvent e) {

				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ESCAPE) {

					// Key pressed is the ESCAPE key. Hide this Dialog.
					hideFilterAnyOnTray();
					setVisible(false);
				}
			}

		});
	}

	/**
	 * Hide the FilterAny on System Tray.
	 */
	private void hideFilterAnyOnTray() {
		// Start hiding the windows.

		if (scrapbook != null) {

			scrapbook.hideFindDialog();
			scrapbook.setVisible(false);
		}

		if (taskManger != null) {

			taskManger.setVisible(false);
		}

		CommandLineGenerator commandLineGenerator = CommandLineGenerator
				.getInstance();

		commandLineGenerator.setVisible(false);

		FilterAnyConfigurationInterface configuration = FilterAnyConfigurationInterface
				.getInstance();

		configuration.setVisible(false);

		TextInformationController.hideAll();

		if (FilterAnyConfiguration.isUseSplitters()) {

			// Avoid problem when the panel has not been initialized yet.
			if (helpShell != null) {

				helpShell.setVisible(false);
			}
		}

		topPanel.hideAllDialogs();
		auxiliarPanel.hideAllDialogs();
		bottomPanel.hideAllDialogs();

		saveWindowLocation();

		if (this.traySupported) {

			if (!this.stillRunningMessagePresented) {

				this.trayIcon.displayMessage(
						Text.get(Text.STILL_RUNNING_CAPTION),
						Text.get(Text.STILL_RUNNING_TEXT), MessageType.INFO);

				this.stillRunningMessagePresented = true;
			}

		} else {

			deactivateGlobalKeyListener();

			exitApplication();

			System.exit(0);
		}
	}

	/**
	 * Show the task manager.
	 */
	private static void showTaskManager() {

		if (taskManger == null) {

			taskManger = new TaskManager(mainComponent, bottomPanel);
		}
		taskManger.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.EnterOnTextFieldsListener#enterPressed()
	 */
	@Override
	public void enterPressed() {

		executeCurrentFilter();
	}

	/**
	 * Execute the current filter.
	 */
	private static void executeCurrentFilter() {

		// No filter selected
		if (currentFilter == null) {
			return;
		}

		FilterAnySound.playSound(FilterAnySound.SOUNDS.GO);

		// If it is on text mode than work with text areas.
		if (FilterAnyConfiguration.isTextMode()) {

			executeFilterOnText();

		} else {

			executeFilterOnFiles();
		}
	}

	/**
	 * Switch the current automatic mode.
	 */
	private void switchAutomaticMode() {

		// Get current mode.
		boolean automaticMode = FilterAnyConfiguration.isAutomaticMode();

		// Switch mode.
		automaticMode = !automaticMode;

		// If turned on than set status and show message.
		FilterAnyConfiguration.setAutomaticMode(automaticMode);

		// Update filter controls.
		if (currentFilter != null) {
			filterChanged(currentFilter);
		}

		updateToolbar(this.toolBar);
	}

	/**
	 * Switch the contents between panels.
	 */
	private static void switchMainWithOutputPanelsContent() {

		// If it is on text mode than work with text areas.
		if (FilterAnyConfiguration.isTextMode()) {

			String inputText = topPanel.getTextArea();
			String outputText = bottomPanel.getTextArea();

			topPanel.setTextArea(outputText);
			bottomPanel.setTextArea(inputText);

			topPanel.updateLabels();
			bottomPanel.updateLabels();

			topPanel.setCaretPosition(0);
			bottomPanel.setCaretPosition(0);

		} else {

			// Work with the tables.
			String topText = topPanel.exportTableModel();
			String bottomText = bottomPanel.exportTableModel();

			topPanel.setRows(FilterAnyLogic.generateTableModel(bottomText));
			bottomPanel.setRows(FilterAnyLogic.generateTableModel(topText));

			topPanel.updateLabels();
			bottomPanel.updateLabels();

			topPanel.updateLabels();
			bottomPanel.updateLabels();

			topPanel.packTable();
			bottomPanel.packTable();

			// Redraw the components.
			topPanel.revalidate();
			bottomPanel.revalidate();
			topPanel.repaint();
			bottomPanel.repaint();

		}

		// Swap the flags
		boolean tempSaveStatus = topPanel.isTextAreaSaved();
		topPanel.setTextAreaSaved(bottomPanel.isTextAreaSaved());
		bottomPanel.setTextAreaSaved(tempSaveStatus);
	}

	/**
	 * Switch the contents between panels.
	 */
	private static void switchAuxiliarWithOutputPanelsContent() {

		// If it is on text mode than work with text areas.
		if (FilterAnyConfiguration.isTextMode()) {

			String auxiliarText = auxiliarPanel.getTextArea();
			String outputText = bottomPanel.getTextArea();

			auxiliarPanel.setTextArea(outputText);
			bottomPanel.setTextArea(auxiliarText);

			if (auxiliarPanel.isEnabled()) {
				auxiliarPanel.updateLabels();
			}
			bottomPanel.updateLabels();

			auxiliarPanel.setCaretPosition(0);
			bottomPanel.setCaretPosition(0);

		}

		// Swap the flags
		boolean tempSaveStatus = auxiliarPanel.isTextAreaSaved();
		auxiliarPanel.setTextAreaSaved(bottomPanel.isTextAreaSaved());
		bottomPanel.setTextAreaSaved(tempSaveStatus);
	}

	/**
	 * Switch the contents between panels.
	 */
	private static void switchMainWithAuxiliarPanelsContent() {

		// If it is on text mode than work with text areas.
		if (FilterAnyConfiguration.isTextMode()) {

			String auxiliarText = auxiliarPanel.getTextArea();
			String mainText = topPanel.getTextArea();

			auxiliarPanel.setTextArea(mainText);
			topPanel.setTextArea(auxiliarText);

			if (auxiliarPanel.isEnabled()) {
				auxiliarPanel.updateLabels();
			}
			topPanel.updateLabels();

			auxiliarPanel.setCaretPosition(0);
			topPanel.setCaretPosition(0);

		}

		// Swap the flags
		boolean tempSaveStatus = auxiliarPanel.isTextAreaSaved();
		auxiliarPanel.setTextAreaSaved(topPanel.isTextAreaSaved());
		topPanel.setTextAreaSaved(tempSaveStatus);
	}

	/**
	 * Switch between text and table.
	 */
	private void switchBetweenTextAndTable() {

		CommandLineGenerator commandLineGenerator = CommandLineGenerator
				.getInstance();

		if (FilterAnyConfiguration.isTextMode()) {

			commandLineGenerator.setFileMode();

			topPanel.switchToTable();

			bottomPanel.switchToTable();

			// Switch context.
			FilterAnyConfiguration.setTextMode(false);

			topPanel.setRows(FilterAnyLogic.generateTableModel(topPanel
					.getTextArea()));
			bottomPanel.setRows(FilterAnyLogic.generateTableModel(bottomPanel
					.getTextArea()));

			// Deactivate specific text button commands.
			Event.getToolbarSwitchBlueGreenAction().setEnabled(false);
			Event.getToolbarSwitchGreenRedAction().setEnabled(false);
			Event.getToolbarUpdateAction().setEnabled(false);
			Event.getToolbarNewLineAction().setEnabled(false);

			String topContent = topPanel.exportTableModel();
			topPanel.setTextArea(topContent);

			String bottomContent = bottomPanel.exportTableModel();
			bottomPanel.setTextArea(bottomContent);

			// Clear caret position. and status.
			topPanel.setPositionLabel(""); //$NON-NLS-1$
			bottomPanel.setPositionLabel(""); //$NON-NLS-1$

			topPanel.packTable();
			bottomPanel.packTable();

			// Redraw the components.
			topPanel.revalidate();
			bottomPanel.revalidate();
			topPanel.repaint();
			bottomPanel.repaint();

			if (FilterAnyConfiguration.isAutomaticMode()) {

				FilterAnyLogic.turnAutomaticModeOff();
				switchAutomaticMode();
			}

			Event.getToolBarAutomaticModeActionTurnedOn().setEnabled(false);
			Event.getToolBarAutomaticModeActionTurnedOff().setEnabled(false);

			FilterAnySound.playSound(FilterAnySound.SOUNDS.OFF);

		} else {

			commandLineGenerator.setTextMode();

			topPanel.swithToTextArea();
			bottomPanel.swithToTextArea();

			// Switch context.
			FilterAnyConfiguration.setTextMode(true);

			// Enable specific text button commands.
			Event.getToolbarSwitchBlueGreenAction().setEnabled(true);
			Event.getToolbarSwitchGreenRedAction().setEnabled(true);
			Event.getToolbarUpdateAction().setEnabled(true);
			Event.getToolbarNewLineAction().setEnabled(true);

			// Bring back the data from table.
			topPanel.setTextArea(topPanel.exportTableModel());
			bottomPanel.setTextArea(bottomPanel.exportTableModel());

			// Redraw the components.
			topPanel.packTable();
			bottomPanel.packTable();

			// Redraw the components.
			topPanel.revalidate();
			bottomPanel.revalidate();
			topPanel.repaint();
			bottomPanel.repaint();

			FilterAnyLogic.turnAutomaticModeOff();

			Event.getToolBarAutomaticModeActionTurnedOn().setEnabled(true);
			Event.getToolBarAutomaticModeActionTurnedOff().setEnabled(true);

			FilterAnySound.playSound(FilterAnySound.SOUNDS.ON);

		}

		this.filtersTree.repaint();

		// Table filter has target directory
		// than update controls.
		updateFilterChanged(currentFilter);

		topPanel.updateLabels();
		bottomPanel.updateLabels();

		// Update the icon on the tool bar.
		updateToolbar(this.toolBar);
	}

	/**
	 * Execute a filter on the text areas.
	 */
	private static void executeFilterOnText() {

		boolean endedOnException = false;

		updateFilter();

		StringBuffer inputText = new StringBuffer(topPanel.getTextArea());
		StringBuffer auxiliarText = new StringBuffer(
				auxiliarPanel.getTextArea());
		int selectionStart = topPanel.getTextStartSelection();
		int selectionEnd = topPanel.getTextEndSelection();

		StringBuffer outputText = null;
		try {

			currentFilter.setSuperComponent(mainComponent);
			outputText = currentFilter.filter(inputText, auxiliarText,
					selectionStart, selectionEnd);

			CommandLineGenerator commandLineGenerator = CommandLineGenerator
					.getInstance();

			commandLineGenerator.logExecution();

			if (currentFilter.isRunInBackground()) {

				showTaskManager();

				JOptionPane.showMessageDialog(mainComponent,
						Text.get(Text.TASK_STARTED_INFORMATION),
						Text.get(Text.TASK_STARTED_INFORMATION_TITLE),
						JOptionPane.INFORMATION_MESSAGE);
			}

			if (currentFilter.isIssueAnAlert()) {
				currentFilter.setIssueAnAlert(false);
				FilterAnyAlert.alertUser(mainComponent);
			}

			if (currentFilter instanceof Report) {
				Report reportFilter = (Report) currentFilter;
				bottomPanel.setReportText(reportFilter.getReportText());
			} else {
				bottomPanel.setReportText(""); //$NON-NLS-1$
			}

			if (!currentFilter.isRunInBackground()) {

				bottomPanel.setTextArea(outputText.toString());

				bottomPanel.setTextAreaSaved(false);

				bottomPanel.updateLabels();
			}

			if (selectionStart == selectionEnd) {
				// TODO atualizar o status bar.
				// topStatusLabel.setForeground(new Color(0, 0, 0));
				// topStatusLabel.setText(Text
				// .get(Text.FILTER_APPLIED_ON_TEXT));
			} else {
				// topStatusLabel.setForeground(new Color(0, 0, 255));
				// topStatusLabel.setText(Text
				// .get(Text.FILTER_APPLIED_ON_SELECTION));
			}

			bottomPanel.repaintSupportInformation();

		} catch (FilterException exception) {

			if (FilterAnyConfiguration.isAutomaticMode()) {
				endedOnException = true;
				controlsPanel.setExecutionStatus(exception.getMessage());
				return;
			}
			ExceptionSupport.handleException(mainComponent,
					exception.getMessage());
		}

		if (FilterAnyConfiguration.isAutomaticMode() && !endedOnException) {
			controlsPanel.setExecutionStatus(""); //$NON-NLS-1$
			return;
		}

	}

	/**
	 * Update the toolbar according to context.
	 * 
	 * @param toolBarParameter
	 *            The toolbar.
	 */
	private static void updateToolbar(JToolBar toolBarParameter) {

		toolBarParameter.removeAll();

		// Add the toolbar.
		toolBarParameter.add(Event.getToolbarSwitchBlueRedAction());
		toolBarParameter.add(Event.getToolbarSwitchGreenRedAction());
		toolBarParameter.add(Event.getToolbarSwitchBlueGreenAction());

		toolBarParameter.addSeparator();
		toolBarParameter.add(Event.getCompareMainWithOutputAction());
		toolBarParameter.addSeparator();

		toolBarParameter.add(Event.getToolbarUpdateAction());
		toolBarParameter.add(Event.getToolbarNewLineAction());

		toolBarParameter.addSeparator();

		toolBarParameter.add(Event.getScrapBookAction());
		toolBarParameter.add(Event.getTaskManagerAction());
		toolBarParameter.add(Event.getNetworkAction());
		toolBarParameter.add(Event.getCommandLineGeneratorAction());

		toolBarParameter.addSeparator();

		if (FilterAnyConfiguration.isTextMode()) {
			toolBarParameter.add(Event.getToolbarSwitchBetweenTextAndFile());
		} else {
			toolBarParameter.add(Event
					.getToolbarSwitchBetweenTextAndFileTurnedOn());
		}

		// Show different icons depending on the automatic mode.
		if (FilterAnyConfiguration.isAutomaticMode()) {
			toolBarParameter.add(Event.getToolBarAutomaticModeActionTurnedOn());
		} else {
			toolBarParameter
					.add(Event.getToolBarAutomaticModeActionTurnedOff());
		}

		// Show different icons depending on the automatic help enable or not.
		if (FilterAnyConfiguration.isAutomaticHelp()) {
			toolBarParameter.add(Event.getAutomaticHelpModeActionTurnedOn());
		} else {
			toolBarParameter.add(Event.getAutomaticHelpModeActionTurnedOff());
		}

		toolBarParameter.addSeparator();

		if (SwingUtil.isMaximumDimension(mainComponent)) {
			toolBarParameter.add(Event.getToolBarRestoreAction());
		} else {
			toolBarParameter.add(Event.getToolBarMaximizeAction());
		}

		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {

			toolBarParameter.add(Box.createHorizontalGlue());

			toolBarParameter.add(Event.getRegisterButtonAction());
		}

	}

	/**
	 * Update filter help.
	 */
	private static void updateFilterHelp() {

		// No filter selected
		if (currentFilter == null) {

			if (helpPanel != null) {
				if (FilterAnyConfiguration.isAutomaticHelp()) {
					helpPanel.setHTMLText(Text
							.get(Text.HELP_PANEL_INITIAL_TEXT_AUTOMATIC));
				} else {
					helpPanel.setHTMLText(Text
							.get(Text.HELP_PANEL_INITIAL_TEXT));
				}
			}
			return;
		}

		// Avoid problem when the panel has not been initialized yet.
		if (helpPanel != null) {
			helpPanel.setHTMLText(currentFilter.getFilterInstructions());
		}

		if (FilterAnyConfiguration.isUseSplitters()) {

			// Avoid problem when the panel has not been initialized yet.
			if (helpShell != null) {

				if (FilterAnyConfiguration.isAutomaticHelp()) {
					if (!helpShell.isVisible()) {
						helpShell.setVisible(true);
					}
				} else {
					helpShell.setVisible(true);
				}
			}
		}

		if (FilterAnyConfiguration.isUseInternalWindow()) {

			// Avoid problem when the panel has not been initialized yet.
			if (helpFrame != null) {
				helpFrame.toFront();
				try {
					helpFrame.setIcon(false);
				} catch (PropertyVetoException exception) {
					// Ignore. Not too relevant.
				}
				helpFrame.setVisible(true);
			}
		}
	}

	/**
	 * Update the current filter with the possible options.
	 */
	private static void updateFilter() {

		FilterAdapter adapter = currentFilter;

		// Set the filter parameters.
		adapter.setUseDOSFormat(FilterAnyConfiguration
				.isGenerateTextInDOSFormat());
		adapter.setField1(FilterAnyLogic.replaceSpecialCharacters(controlsPanel
				.getField1Text()));
		adapter.setField2(FilterAnyLogic.replaceSpecialCharacters(controlsPanel
				.getField2Text()));
		adapter.setField3(FilterAnyLogic.replaceSpecialCharacters(controlsPanel
				.getField3Text()));
		adapter.setCheckBox1(controlsPanel.isCheckBox1Selected());
		adapter.setCheckBox2(controlsPanel.isCheckBox2Selected());
		adapter.setTargetFileOrDirectory1(controlsPanel
				.getTargetFileOrDirectory1Field());
		adapter.setFile1(controlsPanel.getTarget1FileText());
		adapter.setDirectory1(controlsPanel.getTarget1DirectoryText());

		CommandLineGenerator commandLineGenerator = CommandLineGenerator
				.getInstance();

		commandLineGenerator.setField1(controlsPanel.getField1Text());
		commandLineGenerator.setField2(controlsPanel.getField2Text());
		commandLineGenerator.setField3(controlsPanel.getField3Text());
		commandLineGenerator.setCheckBox1(controlsPanel.isCheckBox1Selected());
		commandLineGenerator.setCheckBox2(controlsPanel.isCheckBox2Selected());
		commandLineGenerator.setTargetFileOrDirectory1(controlsPanel
				.getTargetFileOrDirectory1Field());
		commandLineGenerator.setFile1(controlsPanel.getTarget1FileText());
		commandLineGenerator.setDirectory1(controlsPanel
				.getTarget1DirectoryText());
		commandLineGenerator.setAdapter(adapter);
	}

	/**
	 * Execute a filter on files.
	 */
	private static void executeFilterOnFiles() {

		ArrayList<FileOrURLBean> rows = FilterAnyLogic
				.generateTableModel(topPanel.exportTableModel());
		ArrayList<FileOrURLBean> filteredRows = FilterAnyLogic
				.filterValidRows(rows);

		updateFilter();

		// If the filter work only on text than do not show preview.
		if (currentFilter instanceof SpecialBehavior
				&& (FilterAnyLogic.hasSpecialBehavior(
						(SpecialBehavior) currentFilter,
						SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT) || FilterAnyLogic
						.hasSpecialBehavior(
								(SpecialBehavior) currentFilter,
								SpecialBehavior.Behavior.BINARY_RECEIVE_FULL_FILE_LIST))) {

			// Do not show preview

		} else {

			// Show preview.

			filteredRows = FilterAnyLogic.setNewTargets(
					controlsPanel.getTargetDirectoryText(), filteredRows);

			// Apply the rename file name filter if necessary.
			try {

				if (currentFilter instanceof RenameFile) {

					RenameFile filter = (RenameFile) currentFilter;

					for (FileOrURLBean bean : filteredRows) {
						bean.setNewTarget(filter.getNewFileName(bean
								.getNewTarget()));
					}
				}

			} catch (FilterException exception) {

				ExceptionSupport.handleException(exception);
				return;
			}

			FilterPreviewTableModel previewTable = new FilterPreviewTableModel(
					filteredRows);

			if (currentFilter instanceof RenameFile) {

				previewTable.setRenameFileRequested(true);
			}

			FilterPreview preview = new FilterPreview(mainComponent,
					Text.get(Text.PREVIEW_DIALOG_WINDOW_TITLE), previewTable);

			// If the user cancel the operation than return.
			if (!preview.isConfirmed()) {

				return;
			}
		}

		CommandLineGenerator commandLineGenerator = CommandLineGenerator
				.getInstance();

		commandLineGenerator.setTargetDirectoryText(controlsPanel
				.getTargetDirectoryText());

		commandLineGenerator.logExecution();

		// If the filter works only on text than apply it only to text.
		if (currentFilter instanceof SpecialBehavior
				&& FilterAnyLogic.hasSpecialBehavior(
						(SpecialBehavior) currentFilter,
						SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT)) {

			StringBuffer input = new StringBuffer(topPanel.exportTableModel());
			StringBuffer auxiliar = new StringBuffer(
					auxiliarPanel.exportTableModel());

			Filter filter = currentFilter;
			StringBuffer output = null;
			filter.setSuperComponent(mainComponent);
			try {
				output = filter.filter(input, auxiliar, 0, 0);
			} catch (FilterException exception) {
				ExceptionSupport.handleException(mainComponent,
						exception.getMessage());
			}

			StringBuffer newOutput = new StringBuffer();
			newOutput.append(input);
			newOutput.append(output);

			bottomPanel.setRows(FilterAnyLogic.generateTableModel(newOutput
					.toString()));

			bottomPanel.packTable();

			bottomPanel.setTextAreaSaved(false);

			return;
		}

		/**
		 * Task to execute a filter on the files.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		class FileExecutionThread implements Runnable {

			ArrayList<FileOrURLBean> filteredRowsFile = null;

			/**
			 * Constructor.
			 * 
			 * @param filteredRowsParameter
			 *            The file list to execute on.
			 */
			FileExecutionThread(ArrayList<FileOrURLBean> filteredRowsParameter) {

				this.filteredRowsFile = filteredRowsParameter;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@SuppressWarnings({ "synthetic-access" })
			@Override
			public void run() {

				TextBridgeFactory.TextBridge bridge = TextBridgeFactory
						.getInstance();

				TextInterfaceBean textInterfaceShare = bridge
						.createNewInterfaceText();

				textInterfaceShare.setStatus(TaskStatus.RUNNING);

				textInterfaceShare.setSourceFilter(Text
						.get(Text.TASK_INFORMATION_FILE_MODE_EXECUTION));

				StringBuffer newOutput = new StringBuffer();

				String auxiliarText = auxiliarPanel.getTextArea();

				// Read the file depending on the format requested.
				if (currentFilter instanceof BinaryFilesFilter) {

					BinaryFilesFilter binaryFilter = (BinaryFilesFilter) currentFilter;

					if (binaryFilter instanceof SpecialBehavior
							&& FilterAnyLogic.hasSpecialBehavior(
									(SpecialBehavior) binaryFilter,
									Behavior.BINARY_RECEIVE_FULL_FILE_LIST)) {

						StringBuffer inputText = new StringBuffer();
						for (FileOrURLBean row : this.filteredRowsFile) {
							inputText
									.append(row.getTarget()
											+ (FilterAnyConfiguration
													.isGenerateTextInDOSFormat() ? StringSupport
													.getDOSNewLine()
													: StringSupport
															.getUnixNewLine()));
						}

						textInterfaceShare.setProgress(Text
								.get(Text.BINARY_EXECUTION_MESSAGE));

						String executionStatus = new String();
						try {

							executionStatus = binaryFilter.filter(
									inputText.toString(), new String());

						} catch (FilterException exception) {

							String message = Text.get(
									Text.FAILURE_APPLYING_A_FILTER,
									currentFilter.getFilterName(),
									exception.getMessage())
									+ (FilterAnyConfiguration
											.isGenerateTextInDOSFormat() ? StringSupport
											.getDOSNewLine() : StringSupport
											.getUnixNewLine());

							ExceptionSupport.handleException(message);

							textInterfaceShare.setContent(new StringBuffer(
									message));

							textInterfaceShare.setStatus(TaskStatus.ERROR);

							return;

						}

						textInterfaceShare
								.setContent(new StringBuffer(
										executionStatus
												+ (FilterAnyConfiguration
														.isGenerateTextInDOSFormat() ? StringSupport
														.getDOSNewLine()
														: StringSupport
																.getUnixNewLine())
												+ Text.get(
														Text.BINARY_RECEIVE_FULL_FILE_LIST_EXECUTION_MESSAGE,
														currentFilter
																.getFilterName())));

						textInterfaceShare.setStatus(TaskStatus.COMPLETE);

						return;
					}
				}

				int completedFiles = 0;
				// Apply the filter to all rows.
				for (FileOrURLBean row : this.filteredRowsFile) {

					if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
						textInterfaceShare.setStatus(TaskStatus.CANCELED);
						return;
					}

					StringBuffer inputText = new StringBuffer();

					if (row.getType() == FileOrURLBean.FileOrURLBeanType.FILE) {

						// If the filter is binary file then it is not necessary
						// to read the file.
						if (!(currentFilter instanceof BinaryFilesFilter)) {

							inputText = FileSupport.readEncodedTextFile(row
									.getTarget(),
									FilterAnyEncoding.getInstance()
											.getDefaultFileEncoding());
						}
					}

					Filter filter = currentFilter;
					StringBuffer outputContent = new StringBuffer();
					filter.setSuperComponent(mainComponent);
					try {
						// Read the file depending on the format requested.
						if (currentFilter instanceof BinaryFilesFilter) {

							BinaryFilesFilter binaryFilter = (BinaryFilesFilter) currentFilter;

							binaryFilter.filter(row.getTarget(),
									row.getNewTarget());

						} else {

							outputContent = filter.filter(inputText,
									new StringBuffer(auxiliarText), 0, 0);
						}

					} catch (FilterException exception) {
						newOutput
								.append(Text.get(
										Text.FAILURE_APPLYING_A_FILTER,
										row.getNewTarget(),
										exception.getLocalizedMessage())
										+ (FilterAnyConfiguration
												.isGenerateTextInDOSFormat() ? StringSupport
												.getDOSNewLine()
												: StringSupport
														.getUnixNewLine()));
						continue;
					}

					String message = null;
					// Write the file if the filter is not binary file.
					if (!(currentFilter instanceof BinaryFilesFilter)) {

						String path = FileSupport.getOnlyPath(row
								.getNewTarget());

						if (!FileSupport.createDirectory(path)) {

							newOutput
									.append(Text.get(
											Text.FAILURE_CREATING_DIRECTORIES,
											row.getNewTarget())
											+ (FilterAnyConfiguration
													.isGenerateTextInDOSFormat() ? StringSupport
													.getDOSNewLine()
													: StringSupport
															.getUnixNewLine()));
							continue;

						}

						message = FileSupport.writeEncodedTextFile(row
								.getNewTarget(), outputContent.toString(),
								FilterAnyEncoding.getInstance()
										.getOutputFileEncoding());
					}

					if (message != null) {
						newOutput
								.append(Text.get(Text.FAILURE_PROCESSING_FILE,
										row.getNewTarget(), message)
										+ (FilterAnyConfiguration
												.isGenerateTextInDOSFormat() ? StringSupport
												.getDOSNewLine()
												: StringSupport
														.getUnixNewLine()));
						continue;
					}

					newOutput
							.append(Text.get(Text.SUCCESS_PROCESSING_FILE, row
									.getNewTarget() == null ? row.getTarget()
									: row.getNewTarget())
									+ (FilterAnyConfiguration
											.isGenerateTextInDOSFormat() ? StringSupport
											.getDOSNewLine() : StringSupport
											.getUnixNewLine()));

					completedFiles++;

					int percentage = (int) (((float) completedFiles)
							/ ((float) this.filteredRowsFile.size()) * 100f);
					textInterfaceShare.setProgress(Text.get(
							Text.TASK_INFORMATION_FILE_MODE_EXECUTION_PROGRESS,
							String.valueOf(percentage),
							String.valueOf(completedFiles),
							String.valueOf(this.filteredRowsFile.size())));

				}

				textInterfaceShare.setContent(newOutput);

				textInterfaceShare.setStatus(TaskStatus.COMPLETE);
			}
		}

		if (currentFilter instanceof SpecialBehavior
				&& FilterAnyLogic
						.hasSpecialBehavior(
								(SpecialBehavior) currentFilter,
								SpecialBehavior.Behavior.THE_FILTER_WILL_CONTROL_THE_THREAD)) {

			try {

				if (currentFilter instanceof BinaryFilesFilter) {

					BinaryFilesFilter binaryFilter = (BinaryFilesFilter) currentFilter;
					binaryFilter.filter(topPanel.getTextArea(),
							auxiliarPanel.getTextArea());
				}

			} catch (FilterException exception) {

				String message = Text.get(Text.FAILURE_APPLYING_A_FILTER,
						currentFilter.getFilterName(), exception.getMessage())
						+ (FilterAnyConfiguration.isGenerateTextInDOSFormat() ? StringSupport
								.getDOSNewLine() : StringSupport
								.getUnixNewLine());

				ExceptionSupport.handleException(message);

				return;
			}

			showTaskManager();

			JOptionPane.showMessageDialog(mainComponent,
					Text.get(Text.TASK_STARTED_INFORMATION),
					Text.get(Text.TASK_STARTED_INFORMATION_TITLE),
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			Thread downloadThread = new Thread(new FileExecutionThread(
					filteredRows));

			downloadThread.start();

			showTaskManager();

			JOptionPane.showMessageDialog(mainComponent,
					Text.get(Text.TASK_STARTED_INFORMATION),
					Text.get(Text.TASK_STARTED_INFORMATION_TITLE),
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	/**
	 * Load the serialized tree.
	 */
	private void loadTree() {

		// Load the tree
		FiltersTree loadedTree = null;
		try {
			loadedTree = (FiltersTree) Serialization
					.deserializeObject(FilterAnyConfiguration.TREE_FILE);

		} catch (FileNotFoundException e) {
			// Do nothing.
		} catch (IOException e) {
			ExceptionSupport.handleException(e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			ExceptionSupport.handleException(e);
			throw new RuntimeException(e);
		}

		// Initialize the filter list if necessary.
		if (FilterAnyConfiguration.isInitializeFilterList()) {

			loadedTree = null;

		}

		// Load a saved tree.
		if (loadedTree != null) {
			this.filtersTree = loadedTree;

			// Verify if selection is on a filter and set current filter.
			TreePath path = this.filtersTree.getSelectionPath();
			if (path != null) {
				Object objects[] = path.getPath();
				if (objects != null && objects.length > 0) {
					Object lastObject = objects[objects.length - 1];
					if (lastObject instanceof DefaultMutableTreeNode) {
						DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) lastObject;
						Object userObject = treeNode.getUserObject();
						if (userObject instanceof Filter) {
							currentFilter = (FilterAdapter) userObject;
						}
					}
				}
			}
		}

		this.filtersTree.setFilterChangeListener(this);

		topPanel.loadMemory(FilterAnyConfiguration.TOP_MEMORY_FILE);

		auxiliarPanel.loadMemory(FilterAnyConfiguration.AUXILIAR_MEMORY_FILE);

		bottomPanel.loadMemory(FilterAnyConfiguration.BOTTOM_MEMORY_FILE);
	}

	/**
	 * Exit the application saving the required context.
	 */
	private void exitApplication() {

		saveAllProperties();

		controlsPanel.storeCurrentFilterControlsState();
		
		// Save tree
		try {
			Serialization.serializeObject(FilterAnyConfiguration.TREE_FILE,
					this.filtersTree);

			topPanel.serializeMemory(FilterAnyConfiguration.TOP_MEMORY_FILE);
			auxiliarPanel
					.serializeMemory(FilterAnyConfiguration.AUXILIAR_MEMORY_FILE);
			bottomPanel
					.serializeMemory(FilterAnyConfiguration.BOTTOM_MEMORY_FILE);

		} catch (FileNotFoundException e) {
			ExceptionSupport.handleException(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			ExceptionSupport.handleException(e);
			throw new RuntimeException(e);
		}

		controlsPanel.saveControls();

		if (FilterAnyConfiguration.isSaveInWarnMode()) {

			topPanel.verifyUnsavedContent(Text.get(
					Text.TEXT_AREA_NOT_SAVED_TEXT,
					Text.get(Text.TOP_AREA_FRAME_TITLE)));
			auxiliarPanel.verifyUnsavedContent(Text.get(
					Text.TEXT_AREA_NOT_SAVED_TEXT,
					Text.get(Text.AUXILIAR_INPUT_PANEL_TITLE)));
			bottomPanel.verifyUnsavedContent(Text.get(
					Text.TEXT_AREA_NOT_SAVED_TEXT,
					Text.get(Text.BOTTOM_AREA_FRAME_TITLE)));
		}

		if (FilterAnyConfiguration.isSaveInPersistMode()) {

			try {

				StringBuffer topText = new StringBuffer(topPanel.getTextArea());
				Serialization.zipSerializeObject(
						FilterAnyConfiguration.TOP_PERSIST_FILE, topText);

				StringBuffer auxiliarText = new StringBuffer(
						auxiliarPanel.getTextArea());
				Serialization.zipSerializeObject(
						FilterAnyConfiguration.AUXILIAR_PERSIST_FILE,
						auxiliarText);

				StringBuffer outputText = new StringBuffer(
						bottomPanel.getTextArea());
				Serialization.zipSerializeObject(
						FilterAnyConfiguration.BOTTOM_PERSIST_FILE, outputText);

			} catch (FileNotFoundException e) {
				ExceptionSupport.handleException(e);
				throw new RuntimeException(e);
			} catch (IOException e) {
				ExceptionSupport.handleException(e);
				throw new RuntimeException(e);
			}
		}

		// Save the scrap book content if it was changed.
		if (scrapbook != null) {

			scrapbook.saveContent();

		}

		FilterAnyConfiguration.setCaretPositionForMainInput(topPanel
				.getCaretPosition());
		FilterAnyConfiguration.setCaretPositionForAuxiliarInput(auxiliarPanel
				.getCaretPosition());
		FilterAnyConfiguration.setCaretPositionForOutputInput(bottomPanel
				.getCaretPosition());
	}

	/**
	 * Load text areas if configured to.
	 */
	private static void loadTextAreas() {

		if (FilterAnyConfiguration.isSaveInPersistMode()) {

			try {

				StringBuffer topText = (StringBuffer) Serialization
						.zipDeserializeObject(FilterAnyConfiguration.TOP_PERSIST_FILE);
				topPanel.setTextArea(topText.toString());

				topPanel.setCaretPosition(FilterAnyConfiguration
						.getCaretPositionForMainInput());

				topPanel.clearUndo();

				StringBuffer auxiliarText = (StringBuffer) Serialization
						.zipDeserializeObject(FilterAnyConfiguration.AUXILIAR_PERSIST_FILE);
				auxiliarPanel.setTextArea(auxiliarText.toString());

				auxiliarPanel.setCaretPosition(FilterAnyConfiguration
						.getCaretPositionForAuxiliarInput());

				auxiliarPanel.clearUndo();

				StringBuffer outputText = (StringBuffer) Serialization
						.zipDeserializeObject(FilterAnyConfiguration.BOTTOM_PERSIST_FILE);
				bottomPanel.setTextArea(outputText.toString());

				bottomPanel.setCaretPosition(FilterAnyConfiguration
						.getCaretPositionForOutputInput());

				bottomPanel.clearUndo();

			} catch (FileNotFoundException e) {
				// Ignore. Not relevant.
			} catch (IOException e) {
				ExceptionSupport.handleException(e);
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				ExceptionSupport.handleException(e);
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * Update the interface when a selection is done.
	 * 
	 * @param filter
	 *            Indicate the new current filter.
	 */
	@Override
	public void updateFilterChanged(FilterAdapter newCurrentFilter) {

		filterChanged(newCurrentFilter);
	}

	/**
	 * Update the interface when a selection is done.
	 * 
	 * @param newCurrentFilter
	 *            Indicate the new current filter.
	 */
	private static void filterChanged(FilterAdapter newCurrentFilter) {

		controlsPanel.storeCurrentFilterControlsState();

		currentFilter = newCurrentFilter;

		updateMainSendToPopupMenu();

		if (FilterAnyConfiguration.isAutomaticHelp()) {
			updateFilterHelp();
		}

		// Only enable auxiliar panel if the filter works
		// on dual pane.
		if (currentFilter instanceof SpecialBehavior
				&& FilterAnyLogic.hasSpecialBehavior(
						(SpecialBehavior) currentFilter,
						SpecialBehavior.Behavior.WORK_ON_DUAL_PANE)) {
			auxiliarPanel.setEnabledTextArea(true);
		} else {
			auxiliarPanel.setEnabledTextArea(false);
		}

		controlsPanel.removeAll();

		// If no filter is selected.
		if (currentFilter == null) {

			controlsPanel.setBorder(null);

			controlsPanel.revalidate();
			controlsPanel.repaint();

			return;
		}

		// Update the controls based on the previous and new current filter.
		controlsPanel.updateControls(currentFilter);

		// Only adjust the vertical split if splits are being used.
		if (FilterAnyConfiguration.isUseSplitters()) {

			// This method can be called before setting up the
			// split. So avoid null pointer exception.
			if (mainVerticalSplit != null) {

				// Adjust the split according to the position right or left.
				if (FilterAnyConfiguration.isControlsAreOnTheRight()) {

					final int ESTIMATED_BORDER_WIDTH = 15;
					int maximumLocation = mainVerticalSplit.getSize().width
							- (ESTIMATED_BORDER_WIDTH * 2);
					int currentLocation = mainVerticalSplit
							.getDividerLocation();
					int availableSpace = maximumLocation - currentLocation;

					// Move the vertical scroll if there is no
					// space for the parameters.
					if (availableSpace < controlsPanel.getPreferredSize().width) {

						mainVerticalSplit.setDividerLocation(maximumLocation
								- controlsPanel.getPreferredSize().width);
						mainVerticalSplit.repaint();
					}
				} else {

					// Move the vertical scroll if there is no
					// space for the parameters.
					if (mainVerticalSplit.getDividerLocation() < controlsPanel
							.getPreferredSize().width) {
						mainVerticalSplit.setDividerLocation(controlsPanel
								.getPreferredSize().width);
						mainVerticalSplit.repaint();
					}
				}
			}
		}

		controlsPanel.repaint();

	}

	/**
	 * Save the window location.
	 */
	private void saveWindowLocation() {

		if (isVisible()) {

			// Save the window position
			Point position = getLocationOnScreen();

			FilterAnyConfiguration.setWindowPositionX((int) position.getX());
			FilterAnyConfiguration.setWindowPositionY((int) position.getY());

			FilterAnyConfiguration.setWindowWidth(getWidth());
			FilterAnyConfiguration.setWindowHeight(getHeight());
		}
	}

	/**
	 * Save all application properties.
	 */
	private void saveAllProperties() {

		saveWindowLocation();

		// Only save the vertical split if splits are being used.
		if (mainVerticalSplit != null) {

			FilterAnyConfiguration
					.setControlsSplitterLocation(mainVerticalSplit
							.getDividerLocation());
			FilterAnyConfiguration
					.setTextAreasSplitterLocation(this.textAreaSplit
							.getDividerLocation());
			FilterAnyConfiguration
					.setInputAreasSplitterLocation(this.inputAreaSplit
							.getDividerLocation());
		}

		// If the internal desktop panel is being used than save its
		// configuration.
		if (this.desktopPane != null) {

			FilterAnyInternalWindowsPositionBeam internalWindowPosition = new FilterAnyInternalWindowsPositionBeam();

			InternalWindowPositionBean filtersWindow = internalWindowPosition
					.getFiltersWindow();

			Point selectionFrameLocation = this.selectionFrame.getLocation();
			filtersWindow.setX((int) selectionFrameLocation.getX());
			filtersWindow.setY((int) selectionFrameLocation.getY());
			Dimension selectionFrameDimension = this.selectionFrame.getSize();
			filtersWindow.setWidth((int) selectionFrameDimension.getWidth());
			filtersWindow.setHeight((int) selectionFrameDimension.getHeight());
			filtersWindow.setZ(this.desktopPane
					.getComponentZOrder(this.selectionFrame));

			InternalWindowPositionBean topWindow = internalWindowPosition
					.getTopWindow();

			Point topFrameLocation = this.topFrame.getLocation();
			topWindow.setX((int) topFrameLocation.getX());
			topWindow.setY((int) topFrameLocation.getY());
			Dimension topFrameDimension = this.topFrame.getSize();
			topWindow.setWidth((int) topFrameDimension.getWidth());
			topWindow.setHeight((int) topFrameDimension.getHeight());
			topWindow.setZ(this.desktopPane.getComponentZOrder(this.topFrame));

			InternalWindowPositionBean auxiliarWindow = internalWindowPosition
					.getAuxiliarWindow();

			Point auxiliarFrameLocation = this.auxiliarFrame.getLocation();
			auxiliarWindow.setX((int) auxiliarFrameLocation.getX());
			auxiliarWindow.setY((int) auxiliarFrameLocation.getY());
			Dimension auxiliarFrameDimension = this.auxiliarFrame.getSize();
			auxiliarWindow.setWidth((int) auxiliarFrameDimension.getWidth());
			auxiliarWindow.setHeight((int) auxiliarFrameDimension.getHeight());
			auxiliarWindow.setZ(this.desktopPane
					.getComponentZOrder(this.auxiliarFrame));

			InternalWindowPositionBean bottomWindow = internalWindowPosition
					.getBottomWindow();

			Point bottomFrameLocation = this.bottomFrame.getLocation();
			bottomWindow.setX((int) bottomFrameLocation.getX());
			bottomWindow.setY((int) bottomFrameLocation.getY());
			Dimension bottomFrameDimension = this.bottomFrame.getSize();
			bottomWindow.setWidth((int) bottomFrameDimension.getWidth());
			bottomWindow.setHeight((int) bottomFrameDimension.getHeight());
			bottomWindow.setZ(this.desktopPane
					.getComponentZOrder(this.bottomFrame));

			InternalWindowPositionBean controlWindow = internalWindowPosition
					.getControlWindow();

			Point controlFrameLocation = this.controlFrame.getLocation();
			controlWindow.setX((int) controlFrameLocation.getX());
			controlWindow.setY((int) controlFrameLocation.getY());
			Dimension controlFrameDimension = this.controlFrame.getSize();
			controlWindow.setWidth((int) controlFrameDimension.getWidth());
			controlWindow.setHeight((int) controlFrameDimension.getHeight());
			controlWindow.setZ(this.desktopPane
					.getComponentZOrder(this.controlFrame));

			InternalWindowPositionBean helpWindow = internalWindowPosition
					.getHelpWindow();

			Point helpFrameLocation = helpFrame.getLocation();
			helpWindow.setX((int) helpFrameLocation.getX());
			helpWindow.setY((int) helpFrameLocation.getY());
			Dimension helpFrameDimension = helpFrame.getSize();
			helpWindow.setWidth((int) helpFrameDimension.getWidth());
			helpWindow.setHeight((int) helpFrameDimension.getHeight());
			helpWindow.setZ(this.desktopPane.getComponentZOrder(helpFrame));

			FilterAnyConfiguration
					.saveInternalWindowsPosition(internalWindowPosition);
		}

	}

	/**
	 * Update the popup menu according to the current filter.
	 */
	private static void updateMainSendToPopupMenu() {

		mainSendToPopupMenu.removeAll();
		mainSendToPopupMenu.setEnabled(false);

		if (currentFilter == null) {
			return;
		}

		FilterControls[] controls = currentFilter.getControls();
		FilterType[] types = currentFilter.getControlsType();
		String[] labels = currentFilter.getControlsLabels();

		boolean hasPositionMenuItem = false;
		for (int i = 0; i < types.length; i++) {

			if (types[i] == FilterType.NUMBER_POSITION) {

				if (controls[i] == FilterControls.INPUT_FIELD_1) {

					/**
					 * An action to populate a field parameter with the current
					 * cursor column.
					 * 
					 * @author Carlos Fernando Bella Cruz - <a
					 *         href="mailto:pessoal@carlosbcruz.com"
					 *         >pessoal@carlosbcruz.com</a>
					 */
					Action action = new AbstractAction(labels[i]) {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * java.awt.event.ActionListener#actionPerformed(java
						 * .awt.event.ActionEvent)
						 */
						@Override
						@SuppressWarnings("synthetic-access")
						public void actionPerformed(ActionEvent e) {

							controlsPanel.setField1(String.valueOf(topPanel
									.getCaretColumn()));
						}
					};
					mainSendToPopupMenu.add(new JMenuItem(action));
					hasPositionMenuItem = true;
				}
				if (controls[i] == FilterControls.INPUT_FIELD_2) {

					/**
					 * An action to populate a field parameter with the current
					 * cursor column.
					 * 
					 * @author Carlos Fernando Bella Cruz - <a
					 *         href="mailto:pessoal@carlosbcruz.com"
					 *         >pessoal@carlosbcruz.com</a>
					 */
					Action action = new AbstractAction(labels[i]) {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * java.awt.event.ActionListener#actionPerformed(java
						 * .awt.event.ActionEvent)
						 */
						@Override
						@SuppressWarnings("synthetic-access")
						public void actionPerformed(ActionEvent e) {

							controlsPanel.setField2(String.valueOf(topPanel
									.getCaretColumn()));
						}
					};
					mainSendToPopupMenu.add(new JMenuItem(action));
					hasPositionMenuItem = true;
				}
				if (controls[i] == FilterControls.INPUT_FIELD_3) {

					/**
					 * An action to populate a field parameter with the current
					 * cursor column.
					 * 
					 * @author Carlos Fernando Bella Cruz - <a
					 *         href="mailto:pessoal@carlosbcruz.com"
					 *         >pessoal@carlosbcruz.com</a>
					 */
					Action action = new AbstractAction(labels[i]) {

						private static final long serialVersionUID = 1L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * java.awt.event.ActionListener#actionPerformed(java
						 * .awt.event.ActionEvent)
						 */
						@Override
						@SuppressWarnings("synthetic-access")
						public void actionPerformed(ActionEvent e) {

							controlsPanel.setField3(String.valueOf(topPanel
									.getCaretColumn()));
						}
					};
					mainSendToPopupMenu.add(new JMenuItem(action));
					hasPositionMenuItem = true;
				}

			}
		}

		if (hasPositionMenuItem) {
			mainSendToPopupMenu.setEnabled(true);
		}
	}

	/**
	 * Instantiate the Text Utility application.
	 * 
	 * @param args
	 *            Ignored.
	 */
	public static void start(String[] args) {

		FilterAnyConfiguration.initialize(true);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {

				// Open user interface.
				new FilterAny();

			}
		});

	}
}
