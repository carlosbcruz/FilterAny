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
package com.carlosbcruz.filterany.filters;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.SimpleActionDecorator;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionProvider;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.SwingUtil;

/**
 * A dialog to hold the free movement of character on a canvas.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public abstract class FreeMoveDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// Minimum windows dimension.
	private static final int MINIMUM_WIDTH = 700;
	private static final int MINIMUM_HEIGHT = 600;

	private FreeMoveCanvas canvas = null;

	private FreeSelectionTable table = null;

	private SimpleActionDecorator okAction;

	private JButton okButton = null;

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The window title.
	 * @param contentParameter
	 *            A text.
	 */
	public FreeMoveDialog(String title, StringBuffer contentParameter) {

		setTitle(title);

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		// Get the canvas from sub-classes.
		this.canvas = getCanvas(contentParameter);

		int maximumDialogWidth = (int) (SwingUtil.getScreenWidth() * 0.7);
		int maximumDialogHeight = (int) (SwingUtil.getScreenHeight() * 0.9);

		int dialogWidth = this.canvas.canvasWidth + 100;
		int dialogHeight = this.canvas.canvasHeight + 100;

		if (dialogWidth > maximumDialogWidth) {
			dialogWidth = maximumDialogWidth;
		}
		if (dialogWidth < MINIMUM_WIDTH) {
			dialogWidth = MINIMUM_WIDTH;
		}

		if (dialogHeight > maximumDialogHeight) {
			dialogHeight = maximumDialogHeight;
		}
		if (dialogHeight < MINIMUM_HEIGHT) {
			dialogHeight = MINIMUM_HEIGHT;
		}

		setSize(dialogWidth, dialogHeight);

		int positionX = ((SwingUtil.getScreenWidth() - dialogWidth) / 2);
		int positionY = ((SwingUtil.getScreenHeight() - dialogHeight) / 2);

		setLocation(positionX, positionY);

		this.okAction = SimpleActionProvider.getSimpleAction(
				FilterAnyConfiguration.RESOURCE_LOCATION + "ok-icon.png", Text //$NON-NLS-1$
						.get(Text.FILTER_CHARACTER_MOVE_OK), 0,
				Text.get(Text.FILTER_CHARACTER_MOVE_OK_INSTRUCTION));

		JPanel internalPanel = new JPanel(new BorderLayout());
		internalPanel.add(new JScrollPane(this.canvas), BorderLayout.CENTER);

		this.okAction.addObserver(new SimpleActionObserver() {

			private static final long serialVersionUID = 1L;

			@Override
			public void update(SimpleActionSubject simpleSubject) {
				setVisible(false);
			}
		});

		JPanel buttonPanel = new JPanel();

		buttonPanel.add(new JLabel(Text
				.get(Text.FILTER_CHARACTER_MOVE_ELEMENT_INSTRUCTION)));

		this.okButton = new JButton(this.okAction);

		buttonPanel.add(this.okButton);

		internalPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(internalPanel);

		setModal(true);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void windowClosing(WindowEvent e) {

				FreeMoveDialog.this.canvas.selecteAllElements();

				setVisible(false);
			}
		});

		this.okButton.addKeyListener(CharacterMoveKeyListener.getInstance());

		setVisible(true);
	}

	/**
	 * Inform the canvas being used.
	 * 
	 * @param contentParameter
	 *            The text to work with.
	 * @return The canvas.
	 */
	abstract FreeMoveCanvas getCanvas(StringBuffer contentParameter);

	/**
	 * Provide the current selection.
	 * 
	 * @return the lines The selection.
	 */
	protected ArrayList<ArrayList<SelectedElement>> getLines() {

		return this.canvas.getLines();
	}

	/**
	 * Provide the table content.
	 * 
	 * @return The table content.
	 */
	public ArrayList<ArrayList<ArrayList<SelectedElement>>> getTableContent() {

		return this.table.getTableContent();
	}
}
