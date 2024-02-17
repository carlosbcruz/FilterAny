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
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.SwingUtil;

/**
 * Provides a dialog where the user indicate which level is he in.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class UserLevelDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color backGroundColor = new Color(152, 217, 227);

	private static JRadioButton basicOption = null;
	private static JRadioButton advancedOption = null;

	private static JDialog thisComponent = null;

	/**
	 * Default constructor.
	 */
	public UserLevelDialog() {

		thisComponent = this;

		JPanel userDialogPanel = new JPanel(new BorderLayout());

		JLabel filterAnyImage = SwingUtil
				.loadImageIntoLabel(FilterAnyConfiguration.IMAGE_RESOURCE_LOCATION
						+ "FilterAnyHeader.png"); //$NON-NLS-1$

		userDialogPanel.add(filterAnyImage, BorderLayout.NORTH);

		userDialogPanel.setBackground(backGroundColor);

		JPanel questionPainel = new JPanel(new GridLayout(3, 1));
		questionPainel.setBorder(BorderFactory.createLineBorder(new Color(0, 0,
				0)));

		questionPainel.setBackground(backGroundColor);

		questionPainel
				.add(new JLabel(Text.get(Text.USER_LEVEL_DIALOG_QUESTION)));

		basicOption = new JRadioButton(
				Text.get(Text.USER_LEVEL_DIALOG_BASIC_OPTION));
		advancedOption = new JRadioButton(
				Text.get(Text.USER_LEVEL_DIALOG_ADVANCED_OPTION));

		basicOption.setBackground(backGroundColor);
		advancedOption.setBackground(backGroundColor);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(basicOption);
		radioGroup.add(advancedOption);

		advancedOption.setSelected(true);

		questionPainel.add(basicOption);
		questionPainel.add(advancedOption);

		JPanel internalPanel = new JPanel();
		internalPanel.add(questionPainel);

		internalPanel.setBackground(backGroundColor);

		userDialogPanel.add(internalPanel, BorderLayout.CENTER);

		Event.getUserLevelDialogSelectionAction().addObserver(
				new SimpleActionObserver() {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("synthetic-access")
					@Override
					public void update(SimpleActionSubject simpleSubject) {

						if (basicOption.isSelected()) {
							FilterAnyConfiguration.setGeneralUser();
						}
						if (advancedOption.isSelected()) {
							FilterAnyConfiguration.setAdvancedUser();
						}

						thisComponent.setVisible(false);
					}

				});

		JButton okButton = new JButton(
				Event.getUserLevelDialogSelectionAction());

		JPanel selectionPanel = new JPanel(new BorderLayout());
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(backGroundColor);
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(backGroundColor);

		selectionPanel.add(leftPanel, BorderLayout.WEST);
		selectionPanel.add(rightPanel, BorderLayout.EAST);

		selectionPanel.setBackground(backGroundColor);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.setBackground(backGroundColor);

		selectionPanel.add(buttonPanel, BorderLayout.SOUTH);

		userDialogPanel.add(selectionPanel, BorderLayout.SOUTH);

		setContentPane(userDialogPanel);

		pack();

		int dialongPositionX = (SwingUtil.getScreenWidth() / 2)
				- (getWidth() / 2);
		int dialongPositionY = (SwingUtil.getScreenHeight() / 2)
				- (getHeight() / 2);

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		setTitle(Text.get(Text.USER_LEVEL_DIALOG_TITLE));

		setLocation(dialongPositionX, dialongPositionY);

		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setVisible(true);
	}
}
