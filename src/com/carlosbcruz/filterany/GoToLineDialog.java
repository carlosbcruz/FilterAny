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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.PositiveNumberField;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * A dialog to go to a specific line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class GoToLineDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private Component superComponent = null;

	private PositiveNumberField lineField = new PositiveNumberField(
			Integer.MAX_VALUE, true);

	private JPanel internalPanel = new JPanel(new BorderLayout());

	private JButton goButton = new JButton(Text.get(Text.GO_TO_LINE_GO_BUTTON));

	private JTextArea textArea = null;

	/**
	 * Constructor.
	 * 
	 * @param superComponentParameter
	 *            The super component.
	 * @param textAreaParameter
	 *            The text area that the panel will be attached to.
	 */
	GoToLineDialog(Component superComponentParameter,
			JTextArea textAreaParameter) {

		this.superComponent = superComponentParameter;

		this.textArea = textAreaParameter;

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		setTitle(Text.get(Text.GO_TO_LINE_TITLE));

		this.internalPanel.setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.GO_TO_LINE_BORDER_TITLE)));

		this.internalPanel.add(this.lineField, BorderLayout.WEST);

		this.goButton.setToolTipText(Text
				.get(Text.GO_TO_LINE_GO_BUTTON_TOOLTIP));
		this.internalPanel.add(this.goButton, BorderLayout.EAST);

		this.goButton.addActionListener(new ActionListener() {

			/**
			 * Button pressed.
			 * 
			 * @param event
			 *            The event.
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent event) {

				goToLine();
			}

		});

		KeyListener listener = new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent event) {

				if (event.getKeyChar() == KeyEvent.VK_ENTER) {

					goToLine();
				}

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					setVisible(false);
				}
			}
		};

		this.lineField.addKeyListener(listener);
		this.goButton.addKeyListener(listener);

		addWindowListener(new WindowAdapter() {

			/**
			 * Hide when it is deactivated.
			 * 
			 * @param event
			 *            Ignored.
			 */
			@Override
			public void windowDeactivated(WindowEvent event) {

				setVisible(false);
			}
		});

		add(this.internalPanel);

		positionOnTheScreen();

	}

	/**
	 * Go to the specific line if possible.
	 */
	private void goToLine() {

		// Calculate the component thickness.
		int charactersPerLine[] = StringSupport
				.getCharactersPerLine(this.textArea.getText());

		String lineText = this.lineField.getText();

		int line = -1;

		try {
			line = Integer.parseInt(lineText);
		} catch (NumberFormatException e) {
			return;
		}

		if (line < 1 || line > charactersPerLine.length) {

			return;
		}

		int position = StringSupport.getPositionOfBeginnintOfLine(
				new StringBuffer(this.textArea.getText()), line);

		this.textArea.setCaretPosition(position);

		setVisible(false);
	}

	/**
	 * Show the dialog.
	 */
	public void positionOnTheScreen() {

		pack();

		Dimension superDimension = this.superComponent.getSize();

		Dimension dimension = getSize();

		int x = (superDimension.width - dimension.width) / 2;
		int y = (superDimension.height - dimension.height) / 2;

		Point superLocation = this.superComponent.getLocationOnScreen();

		superLocation.x += x;
		superLocation.y += y;

		setLocation(superLocation);

		this.lineField.setText(new String());
	}
}