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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.SwingUtil;

/**
 * Allow a preview of the file names to be created by applying a filter.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FilterPreview extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton confirmButtom = new JButton(
			Event.getPreviewConfirmButtonAction());
	private JButton cancelButtom = new JButton(
			Event.getPreviewCancelButtonAction());

	private boolean confirmed = false;

	/**
	 * Crate a preview dialog containing the file list.
	 * 
	 * @param parentFrame
	 *            The father's frame.
	 * @param dialogTitle
	 *            The dialog title.
	 * @param tableModel
	 *            The table information to be presented.
	 */
	public FilterPreview(JDialog parentFrame, String dialogTitle,
			FilterPreviewTableModel tableModel) {

		super(parentFrame, dialogTitle);

		setModal(true);

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		setLayout(new BorderLayout());

		FilterPreviewTable table = new FilterPreviewTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane, BorderLayout.CENTER);

		pack();

		int suggestedWidth = table.adaptLayout();

		int maxWidth = (int) (0.8 * SwingUtil.getScreenWidth());
		if (suggestedWidth > maxWidth) {
			suggestedWidth = maxWidth;
		}

		// Adjust the width and maintain the height.
		Dimension dimension = getSize();
		setSize(suggestedWidth, (int) dimension.getHeight());

		// Center the preview window on the screen.
		setLocation((SwingUtil.getScreenWidth() - suggestedWidth) / 2,
				(SwingUtil.getScreenHeight() - dimension.height) / 2);

		// Add choices buttons.
		JPanel choicePainel = new JPanel();
		choicePainel.add(this.cancelButtom);
		choicePainel.add(this.confirmButtom);

		add(choicePainel, BorderLayout.SOUTH);

		JLabel messageLabel = new JLabel(Text.get(Text.PREVIEW_MESSAGE_TOP));
		ImageIcon messageIcon = SwingUtil
				.loadImage(FilterAnyConfiguration.RESOURCE_LOCATION
						+ "attention-icon.png"); //$NON-NLS-1$
		messageLabel.setIcon(messageIcon);

		add(messageLabel, BorderLayout.NORTH);

		Event.getPreviewCancelButtonAction().addObserver(
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
					public void update(SimpleActionSubject simpleSubject) {

						// Hide the window.
						setVisible(false);
					}
				});

		Event.getPreviewConfirmButtonAction().addObserver(
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

						confirmed = true;

						// Hide the window.
						setVisible(false);
					}
				});

		// Show the window.
		setVisible(true);

	}

	/**
	 * Indicate that the user has confirmed the filter execution.
	 * 
	 * @return the confirmed true if the user has confirmed the filter
	 *         execution.
	 */
	public boolean isConfirmed() {

		return this.confirmed;
	}
}
