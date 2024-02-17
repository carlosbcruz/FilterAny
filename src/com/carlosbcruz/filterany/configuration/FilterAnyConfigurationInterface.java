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

package com.carlosbcruz.filterany.configuration;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.carlosbcruz.filterany.Event;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.util.ColumnLayout;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.SwingUtil;

/**
 * An user interface to the FilterAny configuration.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterAnyConfigurationInterface extends JDialog {

	private static final long serialVersionUID = 1L;

	private static FilterAnyConfigurationInterface thisComponent = null;

	// Window dimension.
	private static int configurationWindowWidth = 0;
	private static int configurationWindowHeight = 0;

	// Button to apply the selection.
	private static JButton okBottom = null;

	/**
	 * Default constructor.
	 */
	FilterAnyConfigurationInterface() {

		thisComponent = this;

		// Set the window title.
		setTitle(Text.get(Text.CONFIGURATION_WINDOW_TITLE));

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		// The configuration is a border layout.
		setLayout(new BorderLayout());

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel lookAndFeelPanel = new JPanel(new BorderLayout());
		tabbedPane.addTab(Text.get(Text.CONFIGURATION_TAB_LOOK_AND_FEEL), null,
				lookAndFeelPanel,
				Text.get(Text.CONFIGURATION_TAB_LOOK_AND_FEEL_INFORMATION));

		JPanel behaviorPanel = new JPanel(new ColumnLayout());
		tabbedPane.addTab(Text.get(Text.CONFIGURATION_TAB_BEHAVIOR), null,
				behaviorPanel,
				Text.get(Text.CONFIGURATION_TAB_BEHAVIOR_INFORMATION));

		// All central components are added here.

		lookAndFeelPanel.add(new JLabel("Só na versão completa/Only on full version"),
				BorderLayout.CENTER);

		JPanel themeAndTreeLinePanel = new JPanel(new BorderLayout());

		JPanel themeRow_1 = new JPanel();
		JPanel themeRow_2 = new JPanel();
		JPanel themeRow_3 = new JPanel();

		themeRow_1.add(new FilterAnyConfigurationStructureInterface());
		themeRow_1.add(new FilterAnyConfigurationThemeInterface());

		themeRow_2.add(new FilterAnyConfigurationTreeLineInterface());
		themeRow_2.add(new FilterAnyConfigurationDockingThemeInterface());

		themeRow_3.add(new FilterAnyConfigurationSplitStructureInterface());

		themeAndTreeLinePanel.add(themeRow_1, BorderLayout.NORTH);
		themeAndTreeLinePanel.add(themeRow_2, BorderLayout.CENTER);
		themeAndTreeLinePanel.add(themeRow_3, BorderLayout.SOUTH);

		lookAndFeelPanel.add(themeAndTreeLinePanel, BorderLayout.EAST);

		JPanel firstRow = new JPanel();

		firstRow.add(new FilterAnyConfigurationLanguageInterface());

		firstRow.add(new FilterAnyConfigurationSaveFormatInterface());

		behaviorPanel.add(firstRow);

		JPanel secondRow = new JPanel();

		secondRow.add(new FilterAnyConfigurationSaveTextAreasInterface());

		secondRow.add(new FilterAnyConfigurationTextFieldsInterface());

		behaviorPanel.add(secondRow);

		JPanel thirdRow = new JPanel();

		thirdRow.add(new FilterAnyConfigurationEncodingInterface());

		behaviorPanel.add(thirdRow);

		add(tabbedPane, BorderLayout.CENTER);

		// Add the buttons.
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel buttonsPanel = new JPanel();
		okBottom = new JButton(Event.getConfigurationOkButtonAction());
		buttonsPanel.add(okBottom);

		Event.getConfigurationOkButtonAction().addObserver(new AcceptSetting());

		buttonsPanel
				.add(new JButton(Event.getConfigurationCancelButtonAction()));

		// Discard any selection.
		Event.getConfigurationCancelButtonAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("synthetic-access")
					@Override
					public void update(SimpleActionSubject simpleSubject) {

						restoreOptions();

						setVisible(false);
					}
				});
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		pack();

		// Set the window size to the half of the screen.
		Point centerOfScreen = SwingUtil.getCenterOfScreen();
		configurationWindowWidth = centerOfScreen.x;
		configurationWindowHeight = centerOfScreen.y;

		// Set the location and size of the configuration window.
		setLocation(configurationWindowWidth - (getWidth() / 2),
				configurationWindowHeight - (getHeight() / 2));

		// Restore options when window is closed.
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

				restoreOptions();
			}

		});

	}

	/**
	 * Restore previous options.
	 */
	private static void restoreOptions() {

		FilterAnyConfigurationStructureInterface.restorePreviousSelection();
		FilterAnyConfigurationThemeInterface.restorePreviousSelection();
		FilterAnyConfigurationTreeLineInterface.restorePreviousSelection();
		FilterAnyConfigurationDockingThemeInterface.restorePreviousSelection();
		FilterAnyConfigurationSplitStructureInterface
				.restorePreviousSelection();
		FilterAnyConfigurationLanguageInterface.restorePreviousSelection();
		FilterAnyConfigurationSaveFormatInterface.restorePreviousSelection();
		FilterAnyConfigurationSaveTextAreasInterface.restorePreviousSelection();
		FilterAnyConfigurationTextFieldsInterface.restorePreviousSelection();
		FilterAnyConfigurationEncodingInterface
				.restorePreviousEncodingSelection();
	}

	/**
	 * Accept the setting selected by the user.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class AcceptSetting implements SimpleActionObserver {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void update(SimpleActionSubject simpleSubject) {

			boolean restartApplication = false;

			if (FilterAnyConfigurationLanguageInterface.applyLanguage()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationTreeLineInterface.applyTreeLine()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationThemeInterface.applyTheme()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationStructureInterface.applyStructure()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationSaveFormatInterface.applySaveStyle()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationSaveTextAreasInterface.applySaveStyle()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationTextFieldsInterface
					.applyFieldsBehaviorStyle()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationDockingThemeInterface.applyTheme()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationSplitStructureInterface.applyTheme()) {
				restartApplication = true;
			}

			if (FilterAnyConfigurationEncodingInterface.applyEncoding()) {
				restartApplication = true;
			}

			if (restartApplication) {

				JOptionPane.showMessageDialog(thisComponent,
						Text.get(Text.CHANGES_REQUIRES_RESTART));

				FilterAnyConfiguration.setInitializeFilterListRequired();
			}

			setVisible(false);
		}
	}

	/**
	 * Provides the only singleton instance of the interface.
	 * 
	 * @return the thisComponent The component.
	 */
	public static FilterAnyConfigurationInterface getInstance() {

		if (thisComponent == null) {
			thisComponent = new FilterAnyConfigurationInterface();
		}

		return thisComponent;
	}
}
