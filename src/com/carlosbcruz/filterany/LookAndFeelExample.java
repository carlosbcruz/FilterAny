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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SwingUtil;

/**
 * An example of the look and feel.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class LookAndFeelExample extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param mainComponent
	 *            The super component.
	 */
	LookAndFeelExample(Frame mainComponent) {

		super(Text.get(Text.LOOK_AND_FEEL_EXAMPLE_TITLE));

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		// Get window dimensions.
		int screenHeight = SwingUtil.getScreenHeight();
		int screenWidth = SwingUtil.getScreenWidth();

		setLocation(screenWidth / 2, screenHeight / 2);

		JPanel dropDownSelectionPanel = new JPanel();
		dropDownSelectionPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.LOOK_AND_FEEL_EXAMPLE_BORDER)));
		JPanel dropDownPanel = new JPanel();
		dropDownPanel.setLayout(new GridLayout(1, 2));
		dropDownPanel
				.add(new JLabel(Text.get(Text.LOOK_AND_FEEL_EXAMPLE_LABEL)));
		String[] itemList = new String[] {
				Text.get(Text.LOOK_AND_FEEL_EXAMPLE_ITEM1),
				Text.get(Text.LOOK_AND_FEEL_EXAMPLE_ITEM2) };
		dropDownPanel.add(new JComboBox<Object>(itemList));
		dropDownSelectionPanel.add(dropDownPanel);

		JPanel radioSelectionPanel = new JPanel();
		radioSelectionPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.LOOK_AND_FEEL_EXAMPLE_BORDER)));
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(1, 2));
		radioPanel.add(new JLabel(Text.get(Text.LOOK_AND_FEEL_EXAMPLE_LABEL)));
		JPanel radioColumnPanel = new JPanel(new GridLayout(2, 1));
		radioColumnPanel.add(new JRadioButton(Text
				.get(Text.LOOK_AND_FEEL_EXAMPLE_ITEM1)));
		radioColumnPanel.add(new JRadioButton(Text
				.get(Text.LOOK_AND_FEEL_EXAMPLE_ITEM2)));
		radioPanel.add(radioColumnPanel);
		radioSelectionPanel.add(radioPanel);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel buttonsPanel = new JPanel();
		JButton okBottom = new JButton(Event.getConfigurationOkButtonAction());
		JButton cancelBurron = new JButton(
				Event.getConfigurationCancelButtonAction());
		buttonsPanel.add(okBottom);
		buttonsPanel.add(cancelBurron);
		bottomPanel.add(buttonsPanel, BorderLayout.CENTER);

		JPanel topPanel = new JPanel(new GridLayout(1, 2));
		JTextArea area = new JTextArea(
				Text.get(Text.LOOK_AND_FEEL_EXAMPLE_TEXT_AREA));
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setPreferredSize(new Dimension(50, 100));

		JPanel inputAndCheckBoxPanel = new JPanel(new GridLayout(2, 1));

		JPanel inputTextPanel = new JPanel();

		inputTextPanel.add(new JLabel(Text
				.get(Text.LOOK_AND_FEEL_EXAMPLE_TEXT_LABEL)));
		JTextField textField = new JTextField(
				Text.get(Text.LOOK_AND_FEEL_EXAMPLE_TEXT_FIELD), 10);
		inputTextPanel.add(textField);

		inputAndCheckBoxPanel.add(inputTextPanel);
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.add(new JCheckBox(Text
				.get(Text.LOOK_AND_FEEL_EXAMPLE_TEXT_CHECK_BOX)));
		inputAndCheckBoxPanel.add(checkBoxPanel);

		topPanel.add(scrollPane);
		topPanel.add(inputAndCheckBoxPanel);

		JPanel internalPanel = new JPanel(new BorderLayout());

		internalPanel.add(topPanel, BorderLayout.NORTH);

		internalPanel.add(dropDownSelectionPanel, BorderLayout.WEST);
		internalPanel.add(radioSelectionPanel, BorderLayout.EAST);
		internalPanel.add(bottomPanel, BorderLayout.SOUTH);

		internalPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		setContentPane(internalPanel);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu 1"); //$NON-NLS-1$
		menu.setMnemonic('M');
		JMenu menu2 = new JMenu("Menu 2"); //$NON-NLS-1$
		menu2.setMnemonic('e');
		menuBar.add(menu);
		menuBar.add(menu2);
		setJMenuBar(menuBar);

		pack();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setVisible(true);
	}
}
