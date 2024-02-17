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
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;

import com.carlosbcruz.filterany.SpecialBehavior.Behavior;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.AutoCleanUpTextField;
import com.carlosbcruz.util.CustomSizeButton;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.PositiveNumberField;
import com.carlosbcruz.util.Serialization;
import com.carlosbcruz.util.SimpleActionDecorator;

/**
 * Control component.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Standard size of the text fields.
	private final int FIELDS_SIZE = 10;

	// Store the current filter.
	private Filter currentFilter = null;

	private JTextField field1 = new AutoCleanUpTextField(this.FIELDS_SIZE,
			FilterAnyConfiguration.isAutoCleanupBehavior());
	private JTextField field2 = new AutoCleanUpTextField(this.FIELDS_SIZE,
			FilterAnyConfiguration.isAutoCleanupBehavior());
	private JTextField field3 = new AutoCleanUpTextField(this.FIELDS_SIZE,
			FilterAnyConfiguration.isAutoCleanupBehavior());
	private JCheckBox checkBox1 = new JCheckBox();
	private JCheckBox checkBox2 = new JCheckBox();
	private JButton targetFileOrDirectory1Buttom = new JButton(
			Event.getFilterTarget_1_ButtomAction());
	private JTextField targetFileOrDirectory1Field = new AutoCleanUpTextField(
			this.FIELDS_SIZE, FilterAnyConfiguration.isAutoCleanupBehavior());
	private JButton targetDirectoryButtom = new JButton(
			Event.getFilterTarget_Directory_ButtomAction());
	private JTextField targetDirectoryField = new AutoCleanUpTextField(
			this.FIELDS_SIZE, FilterAnyConfiguration.isAutoCleanupBehavior());
	private JButton targetFile1Buttom = new JButton(
			Event.getFileSelectionButtonAction());
	private JTextField target1FileField = new AutoCleanUpTextField(
			this.FIELDS_SIZE, FilterAnyConfiguration.isAutoCleanupBehavior());
	private JButton targetDirectory1Buttom = new JButton(
			Event.getDirectorySelectionButtonAction());
	private JTextField target1DirectoryField = new AutoCleanUpTextField(
			this.FIELDS_SIZE, FilterAnyConfiguration.isAutoCleanupBehavior());

	private JButton instructionButton = new JButton(
			Event.getInstructionAction());
	private JButton exampleButton = new JButton(Event.getExampleAction());

	private JButton executeButton = null;

	private JLabel executionStatus = new JLabel();

	private Hashtable<String, ControlFieldsBean> controlFields = new Hashtable<>();

	private EnterOnTextFieldsListener enterOnTextFieldsListener = null;

	/**
	 * Constructor.
	 * 
	 * @param enterOnTextFieldsListenerParameter
	 *            The listener to enter keys.
	 */
	ControlPanel(EnterOnTextFieldsListener enterOnTextFieldsListenerParameter) {

		this.enterOnTextFieldsListener = enterOnTextFieldsListenerParameter;
	}

	/**
	 * Update the controls based on
	 * 
	 * @param currentFilterParameter
	 */
	public void updateControls(Filter currentFilterParameter) {

		this.currentFilter = currentFilterParameter;

		ControlFieldsBean currentFields = this.controlFields
				.get(this.currentFilter.getClass().getName());

		// Show the filter controls.
		FilterControls controls[] = this.currentFilter.getControls();
		FilterType types[] = this.currentFilter.getControlsType();
		String controlsLabel[] = this.currentFilter.getControlsLabels();
		String controlsToolTipTexts[] = this.currentFilter
				.getControlsToolTipTexts();
		FilterParameter parameters[] = this.currentFilter.gerControlsRequired();

		// Create an internal panel to receive all fields.
		JPanel internalControlsPanel = new JPanel();
		GroupLayout layout = new GroupLayout(internalControlsPanel);
		internalControlsPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup horizontalGroup = layout
				.createSequentialGroup();
		GroupLayout.SequentialGroup verticalGroup = layout
				.createSequentialGroup();

		// Add the appropriate execution bottom based on automatic mode
		// configuration.
		if (FilterAnyConfiguration.isAutomaticMode()
				&& this.currentFilter instanceof SpecialBehavior
				&& FilterAnyLogic.hasSpecialBehavior(
						(SpecialBehavior) this.currentFilter,
						Behavior.ACCEPT_AUTOMATIC_BEHAVIOR)) {

			this.executeButton = new JButton(Event.getAutomaticExecuteAction());
			this.executeButton.setEnabled(false);

		} else {

			this.executeButton = new JButton(Event.getExecuteAction());

			// Allow the enter key to execute the filter.
			this.executeButton.addKeyListener(new KeyAdapter() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
				 */
				@Override
				public void keyPressed(KeyEvent event) {

					super.keyPressed(event);

					if (event.getKeyCode() == KeyEvent.VK_ENTER) {

						SimpleActionDecorator action = Event.getExecuteAction();

						action.actionPerformed(null);
					}
				}
			});
			this.executeButton.setEnabled(true);
		}

		// Binary files only work on automatic mode.
		if (FilterAnyConfiguration.isTextMode()
				&& this.currentFilter instanceof BinaryFilesFilter) {
			this.executeButton.setEnabled(false);
		}

		// Parallel group to receive components.
		ParallelGroup parallelGroup = layout.createParallelGroup();

		boolean fieldsEnabled = true;

		if (this.currentFilter instanceof BinaryFilesFilter) {
			// Binary files only work on automatic mode.
			fieldsEnabled = !FilterAnyConfiguration.isTextMode();
		}

		// If filter works only on text that enable fields only on text
		// mode.
		if (this.currentFilter instanceof SpecialBehavior
				&& FilterAnyLogic.hasSpecialBehavior(
						(SpecialBehavior) this.currentFilter,
						SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT)) {
			fieldsEnabled = FilterAnyConfiguration.isTextMode();
			this.executeButton.setEnabled(FilterAnyConfiguration.isTextMode());
		}

		// Indicate the component to request focus.
		Component componentToRequestFocus = null;

		// Follow all required components to the filter.
		for (int i = 0; i < controls.length; i++) {

			JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			switch (controls[i]) {
			case INPUT_FIELD_1: {

				if (parameters[i] == FilterParameter.REQUIRED) {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"red\"><b>" //$NON-NLS-1$
							+ Text.get(Text.REQUIRED_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$

					field.setToolTipText(controlsToolTipTexts[i]);
					rowPanel.add(field);
				} else {
					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"green\"><b>" //$NON-NLS-1$
							+ Text.get(Text.OPTIONAL_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$
					field.setToolTipText(controlsToolTipTexts[i]);
					rowPanel.add(field);
				}

				if (types[i] == FilterType.NUMBER_POSITION
						|| types[i] == FilterType.NUMBER_GENERIC) {
					this.field1 = new PositiveNumberField(Integer.MAX_VALUE,
							FilterAnyConfiguration.isAutoCleanupBehavior());
				} else {
					this.field1 = new AutoCleanUpTextField(this.FIELDS_SIZE,
							FilterAnyConfiguration.isAutoCleanupBehavior());
				}

				// Alert that ehe enter key has been pressed.
				this.field1.addKeyListener(new KeyAdapter() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent
					 * )
					 */
					@SuppressWarnings({ "synthetic-access",
							"unqualified-field-access" })
					@Override
					public void keyPressed(KeyEvent event) {

						if (event.getKeyChar() == KeyEvent.VK_ENTER) {

							enterOnTextFieldsListener.enterPressed();
						}
					}
				});

				if (parameters[i] == FilterParameter.REQUIRED) {
					this.field1.setBackground(new Color(255, 220, 220));
				} else {
					this.field1.setBackground(new Color(220, 255, 220));
				}

				rowPanel.add(this.field1);

				if (FilterAnyConfiguration.isClearFieldsButton()) {

					JButton clearField1Button = new CustomSizeButton(
							Event.getClearFieldAction(), 12, 12);

					clearField1Button.addActionListener(new ActionListener() {

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * java.awt.event.ActionListener#actionPerformed(java
						 * .awt .event.ActionEvent)
						 */
						@SuppressWarnings({ "synthetic-access",
								"unqualified-field-access" })
						@Override
						public void actionPerformed(ActionEvent e) {
							field1.setText(""); //$NON-NLS-1$
							field1.requestFocus();
						}
					});
					rowPanel.add(clearField1Button);
				}

				this.field1.setEnabled(fieldsEnabled);

				if (fieldsEnabled && componentToRequestFocus == null) {
					componentToRequestFocus = this.field1;
				}

				if (currentFields != null) {
					this.field1.setText(currentFields.getField1());

					this.field1.setSelectionStart(0);
					this.field1.setSelectionEnd(this.field1.getText().length());
				}

				this.field1.setToolTipText(controlsToolTipTexts[i]);

				break;
			}
			case INPUT_FIELD_2: {

				if (parameters[i] == FilterParameter.REQUIRED) {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"red\"><b>" //$NON-NLS-1$
							+ Text.get(Text.REQUIRED_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$
					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);

				} else {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"green\"><b>" //$NON-NLS-1$
							+ Text.get(Text.OPTIONAL_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$
					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				}

				if (types[i] == FilterType.NUMBER_POSITION
						|| types[i] == FilterType.NUMBER_GENERIC) {
					this.field2 = new PositiveNumberField(Integer.MAX_VALUE,
							FilterAnyConfiguration.isAutoCleanupBehavior());
				} else {
					this.field2 = new AutoCleanUpTextField(this.FIELDS_SIZE,
							FilterAnyConfiguration.isAutoCleanupBehavior());
				}

				// Alert that ehe enter key has been pressed.
				this.field2.addKeyListener(new KeyAdapter() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent
					 * )
					 */
					@SuppressWarnings({ "synthetic-access",
							"unqualified-field-access" })
					@Override
					public void keyPressed(KeyEvent event) {

						if (event.getKeyChar() == KeyEvent.VK_ENTER) {

							enterOnTextFieldsListener.enterPressed();
						}
					}
				});

				if (parameters[i] == FilterParameter.REQUIRED) {
					this.field2.setBackground(new Color(255, 220, 220));
				} else {
					this.field2.setBackground(new Color(220, 255, 220));
				}
				rowPanel.add(this.field2);

				if (FilterAnyConfiguration.isClearFieldsButton()) {

					JButton clearField2Button = new CustomSizeButton(
							Event.getClearFieldAction(), 12, 12);

					clearField2Button.addActionListener(new ActionListener() {

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * java.awt.event.ActionListener#actionPerformed(java
						 * .awt .event.ActionEvent)
						 */
						@Override
						@SuppressWarnings({ "synthetic-access",
								"unqualified-field-access" })
						public void actionPerformed(ActionEvent e) {
							field2.setText(""); //$NON-NLS-1$
							field2.requestFocus();
						}
					});
					rowPanel.add(clearField2Button);
				}

				this.field2.setEnabled(fieldsEnabled);

				if (fieldsEnabled && componentToRequestFocus == null) {
					componentToRequestFocus = this.field2;
				}

				if (currentFields != null) {
					this.field2.setText(currentFields.getField2());
					this.field2.setSelectionStart(0);
					this.field2.setSelectionEnd(this.field2.getText().length());
				}

				this.field2.setToolTipText(controlsToolTipTexts[i]);

				break;
			}
			case INPUT_FIELD_3: {

				if (parameters[i] == FilterParameter.REQUIRED) {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"red\"><b>" //$NON-NLS-1$
							+ Text.get(Text.REQUIRED_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$
					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				} else {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"green\"><b>" //$NON-NLS-1$
							+ Text.get(Text.OPTIONAL_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$

					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				}

				if (types[i] == FilterType.NUMBER_POSITION
						|| types[i] == FilterType.NUMBER_GENERIC) {
					this.field3 = new PositiveNumberField(Integer.MAX_VALUE,
							FilterAnyConfiguration.isAutoCleanupBehavior());
				} else {
					this.field3 = new AutoCleanUpTextField(this.FIELDS_SIZE,
							FilterAnyConfiguration.isAutoCleanupBehavior());
				}

				// Alert that ehe enter key has been pressed.
				this.field3.addKeyListener(new KeyAdapter() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent
					 * )
					 */
					@SuppressWarnings({ "synthetic-access",
							"unqualified-field-access" })
					@Override
					public void keyPressed(KeyEvent event) {

						if (event.getKeyChar() == KeyEvent.VK_ENTER) {

							enterOnTextFieldsListener.enterPressed();
						}
					}
				});

				if (parameters[i] == FilterParameter.REQUIRED) {
					this.field3.setBackground(new Color(255, 220, 220));
				} else {
					this.field3.setBackground(new Color(220, 255, 220));
				}
				rowPanel.add(this.field3);

				if (FilterAnyConfiguration.isClearFieldsButton()) {

					JButton clearField3Button = new CustomSizeButton(
							Event.getClearFieldAction(), 12, 12);

					clearField3Button.addActionListener(new ActionListener() {

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * java.awt.event.ActionListener#actionPerformed(java
						 * .awt .event.ActionEvent)
						 */
						@Override
						@SuppressWarnings({ "synthetic-access",
								"unqualified-field-access" })
						public void actionPerformed(ActionEvent e) {
							field3.setText(""); //$NON-NLS-1$
							field3.requestFocus();
						}
					});
					rowPanel.add(clearField3Button);
				}

				this.field3.setEnabled(fieldsEnabled);

				if (fieldsEnabled && componentToRequestFocus == null) {
					componentToRequestFocus = this.field3;
				}

				if (currentFields != null) {
					this.field3.setText(currentFields.getField3());
					this.field3.setSelectionStart(0);
					this.field3.setSelectionEnd(this.field3.getText().length());
				}

				this.field3.setToolTipText(controlsToolTipTexts[i]);

				break;
			}
			case CHECK_BOX_1: {

				this.checkBox1 = new JCheckBox(controlsLabel[i]);

				this.checkBox1.setToolTipText(controlsToolTipTexts[i]);

				rowPanel.add(this.checkBox1);
				rowPanel.add(new JLabel());
				this.checkBox1.setEnabled(fieldsEnabled);

				if (currentFields != null) {
					this.checkBox1.setSelected(currentFields.getCheckBox1());
				}

				break;
			}
			case CHECK_BOX_2: {

				this.checkBox2 = new JCheckBox(controlsLabel[i]);

				this.checkBox2.setToolTipText(controlsToolTipTexts[i]);

				rowPanel.add(this.checkBox2);
				rowPanel.add(new JLabel());
				this.checkBox2.setEnabled(fieldsEnabled);

				if (currentFields != null) {
					this.checkBox2.setSelected(currentFields.getCheckBox2());
				}

				break;
			}
			case TARGET_FILE_OR_DIRECTORY_1: {
				if (parameters[i] == FilterParameter.REQUIRED) {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"red\"><b>" //$NON-NLS-1$
							+ Text.get(Text.REQUIRED_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$
					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				} else {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"green\"><b>" //$NON-NLS-1$
							+ Text.get(Text.OPTIONAL_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$
					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				}

				rowPanel.add(this.targetFileOrDirectory1Buttom);
				rowPanel.add(this.targetFileOrDirectory1Field);
				this.targetFileOrDirectory1Buttom.setEnabled(fieldsEnabled);
				this.targetFileOrDirectory1Field.setEnabled(fieldsEnabled);
				if (parameters[i] == FilterParameter.REQUIRED) {
					this.targetFileOrDirectory1Field.setBackground(new Color(
							255, 220, 220));
				} else {
					this.targetFileOrDirectory1Field.setBackground(new Color(
							220, 255, 220));
				}

				if (fieldsEnabled && parameters[i] == FilterParameter.REQUIRED
						&& componentToRequestFocus == null) {
					componentToRequestFocus = this.targetFileOrDirectory1Buttom;
				}

				if (currentFields != null) {
					this.targetFileOrDirectory1Field.setText(currentFields
							.getTargetFileOrDirectory1());
					this.targetFileOrDirectory1Field.setSelectionStart(0);
					this.targetFileOrDirectory1Field
							.setSelectionEnd(this.targetFileOrDirectory1Field
									.getText().length());
				}

				this.targetFileOrDirectory1Field
						.setToolTipText(controlsToolTipTexts[i]);

				break;
			}
			case TARGET_FILE_1: {
				if (parameters[i] == FilterParameter.REQUIRED) {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"red\"><b>" //$NON-NLS-1$
							+ Text.get(Text.REQUIRED_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$
					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				} else {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"green\"><b>" //$NON-NLS-1$
							+ Text.get(Text.OPTIONAL_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$

					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				}

				rowPanel.add(this.targetFile1Buttom);
				rowPanel.add(this.target1FileField);
				this.targetFile1Buttom.setEnabled(fieldsEnabled);
				this.target1FileField.setEnabled(fieldsEnabled);
				if (parameters[i] == FilterParameter.REQUIRED) {
					this.target1FileField
							.setBackground(new Color(255, 220, 220));
				} else {
					this.target1FileField
							.setBackground(new Color(220, 255, 220));
				}

				if (fieldsEnabled && parameters[i] == FilterParameter.REQUIRED
						&& componentToRequestFocus == null) {
					componentToRequestFocus = this.targetFile1Buttom;
				}

				if (currentFields != null) {
					this.target1FileField.setText(currentFields
							.getTarget1File());
					this.target1FileField.setSelectionStart(0);
					this.target1FileField.setSelectionEnd(this.target1FileField
							.getText().length());
				}

				this.target1FileField.setToolTipText(controlsToolTipTexts[i]);

				break;
			}
			case TARGET_DIRECTORY_1: {

				if (parameters[i] == FilterParameter.REQUIRED) {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"red\"><b>" //$NON-NLS-1$
							+ Text.get(Text.REQUIRED_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$

					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				} else {

					JLabel field = new JLabel("<html>" + controlsLabel[i] //$NON-NLS-1$
							+ " <font color=\"green\"><b>" //$NON-NLS-1$
							+ Text.get(Text.OPTIONAL_FIELD)
							+ "</b></font></html>"); //$NON-NLS-1$

					field.setToolTipText(controlsToolTipTexts[i]);

					rowPanel.add(field);
				}

				rowPanel.add(this.targetDirectory1Buttom);
				rowPanel.add(this.target1DirectoryField);
				this.targetDirectory1Buttom.setEnabled(fieldsEnabled);
				this.target1DirectoryField.setEnabled(fieldsEnabled);
				if (parameters[i] == FilterParameter.REQUIRED) {
					this.target1DirectoryField.setBackground(new Color(255,
							220, 220));
				} else {
					this.target1DirectoryField.setBackground(new Color(220,
							255, 220));
				}

				if (fieldsEnabled && parameters[i] == FilterParameter.REQUIRED
						&& componentToRequestFocus == null) {
					componentToRequestFocus = this.targetDirectory1Buttom;
				}

				if (currentFields != null) {
					this.target1DirectoryField.setText(currentFields
							.getTarget1Directory());
					this.target1DirectoryField.setSelectionStart(0);
					this.target1DirectoryField
							.setSelectionEnd(this.target1DirectoryField
									.getText().length());
				}

				this.target1DirectoryField
						.setToolTipText(controlsToolTipTexts[i]);

				break;
			}

			default:
				ExceptionSupport.handleException(Text
						.get(Text.INTERNAL_ERROR_1));
			}

			// Add the row to the component.
			parallelGroup = parallelGroup.addComponent(rowPanel);
			verticalGroup.addGroup(layout.createParallelGroup(
					Alignment.BASELINE).addComponent(rowPanel));
		}

		// If it is on table mode then add the target button.
		if (!FilterAnyConfiguration.isTextMode()) {

			// If the filter works only on text than do not need to add target
			// field.
			if (this.currentFilter instanceof SpecialBehavior
					&& FilterAnyLogic.hasSpecialBehavior(
							(SpecialBehavior) this.currentFilter,
							SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT)) {

				// Do nothing.

			} else {

				// Add target field.
				JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

				if (this.currentFilter instanceof SpecialBehavior
						&& FilterAnyLogic
								.hasSpecialBehavior(
										(SpecialBehavior) this.currentFilter,
										SpecialBehavior.Behavior.DO_NOT_SHOW_TARGET_DIRECTORY)) {
					// Do nothing
				} else {
					rowPanel.add(this.targetDirectoryButtom);
					rowPanel.add(this.targetDirectoryField);
				}

				// Add the row to the component.
				parallelGroup = parallelGroup.addComponent(rowPanel);
				verticalGroup.addGroup(layout.createParallelGroup(
						Alignment.BASELINE).addComponent(rowPanel));
			}
		}

		// Only show the example button if the filter has example to show.
		JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		if (this.currentFilter instanceof Example) {
			rowPanel.add(this.exampleButton);
			rowPanel.add(Box.createVerticalStrut(1));
		}

		// Add buttons to the component.
		rowPanel.add(this.instructionButton);
		rowPanel.add(Box.createVerticalStrut(1));

		this.instructionButton.setEnabled(!FilterAnyConfiguration
				.isAutomaticHelp());

		rowPanel.add(this.executeButton);

		if (fieldsEnabled && componentToRequestFocus == null) {
			componentToRequestFocus = this.executeButton;
		}

		// Add the row to the component.
		parallelGroup = parallelGroup.addComponent(rowPanel);
		verticalGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rowPanel));
		horizontalGroup.addGroup(parallelGroup);

		// Finish the layout configuration.
		layout.setHorizontalGroup(horizontalGroup);
		layout.setVerticalGroup(verticalGroup);

		// The controls frame has the control panel and a status label.
		setLayout(new BorderLayout());

		// Add the controls panel.
		add(internalControlsPanel, BorderLayout.CENTER);

		// Add the status panel when in automatic mode.
		if (FilterAnyConfiguration.isAutomaticMode()) {
			add(this.executionStatus, BorderLayout.SOUTH);
		}

		// Set border.
		setBorder(BorderFactory.createTitledBorder(this.currentFilter
				.getFilterName()));

		revalidate();

		// Request focus.
		if (componentToRequestFocus != null) {
			componentToRequestFocus.requestFocus();
		}
	}

	/**
	 * Store the current control state.
	 */
	public void storeCurrentFilterControlsState() {

		// Store only if there is a filter.
		if (this.currentFilter == null) {
			return;
		}

		// Safe guard.
		if (this.controlFields == null) {
			this.controlFields = new Hashtable<>();
		}

		ControlFieldsBean control = new ControlFieldsBean(this.currentFilter
				.getClass().getName(), this.field1.getText(),
				this.field2.getText(), this.field3.getText(),
				this.checkBox1.isSelected(), this.checkBox2.isSelected(),
				this.targetFileOrDirectory1Field.getText(),
				this.targetDirectoryField.getText(),
				this.target1FileField.getText(),
				this.target1DirectoryField.getText());

		this.controlFields.put(control.getFilterClass(), control);
	}

	/**
	 * Load the controls from file.
	 */
	@SuppressWarnings("unchecked")
	public void loadControls() {

		// Load controls
		try {

			this.controlFields = (Hashtable<String, ControlFieldsBean>) Serialization
					.deserializeObject(FilterAnyConfiguration.FIELDS_MEMORY_FILE);

		} catch (Throwable e) {
			// Ignore. Not too relevant.
		}
	}

	/**
	 * Save the controls on file.
	 */
	public void saveControls() {

		// Save the controls
		try {
			Serialization.serializeObject(
					FilterAnyConfiguration.FIELDS_MEMORY_FILE,
					this.controlFields);
		} catch (FileNotFoundException e) {
			// Ignore. Not too relevant.
		} catch (IOException e) {
			// Ignore. Not too relevant.
		}
	}

	/**
	 * Set the status message.
	 * 
	 * @param text
	 *            the executionStatus to set
	 */
	public void setExecutionStatus(String text) {

		this.executionStatus.setText(text);
	}

	/**
	 * Set the field 1.
	 * 
	 * @param field1
	 *            the field1 to set
	 */
	public void setField1(String field1Text) {

		this.field1.setText(field1Text);
	}

	/**
	 * Set the field 2.
	 * 
	 * @param field2
	 *            the field2 to set
	 */
	public void setField2(String field2Text) {

		this.field2.setText(field2Text);
	}

	/**
	 * Set the field 3.
	 * 
	 * @param field3
	 *            the field3 to set
	 */
	public void setField3(String field3Text) {

		this.field3.setText(field3Text);
	}

	/**
	 * Set if the help button is enabled or not.
	 * 
	 * @param enabled
	 *            true to enable and false to disable.
	 */
	public void setHelpButtonEnabled(boolean enabled) {

		this.instructionButton.setEnabled(enabled);
	}

	/**
	 * Provide the field 1 text.
	 * 
	 * @return The field 1 text.
	 */
	public String getField1Text() {

		return this.field1.getText();
	}

	/**
	 * Provide the field 2 text.
	 * 
	 * @return The field 2 text.
	 */
	public String getField2Text() {

		return this.field2.getText();
	}

	/**
	 * Provide the field 3 text.
	 * 
	 * @return The field 3 text.
	 */
	public String getField3Text() {

		return this.field3.getText();
	}

	/**
	 * Indicate if the check box 1 is selected.
	 * 
	 * @return true if the check box 1 is selected.
	 */
	public boolean isCheckBox1Selected() {

		return this.checkBox1.isSelected();
	}

	/**
	 * Indicate if the check box 2 is selected.
	 * 
	 * @return true if the check box 2 is selected.
	 */
	public boolean isCheckBox2Selected() {

		return this.checkBox2.isSelected();
	}

	/**
	 * Provide the target file or directory.
	 * 
	 * @return The target file or directory.
	 */
	public String getTargetFileOrDirectory1Field() {

		return this.targetFileOrDirectory1Field.getText();
	}

	/**
	 * Set the target file or directory.
	 * 
	 * @param text
	 *            The target file or directory.
	 */
	public void setTargetFileOrDirectory1Field(String text) {

		this.targetFileOrDirectory1Field.setText(text);
	}

	/**
	 * Provide the target 1 file text.
	 * 
	 * @return The target 1 file text.
	 */
	public String getTarget1FileText() {

		return this.target1FileField.getText();
	}

	/**
	 * Set the target 1 file text.
	 * 
	 * @param text
	 *            The target 1 file text.
	 */
	public void setTarget1FileText(String text) {

		this.target1FileField.setText(text);
	}

	/**
	 * Inform the target directory 1.
	 * 
	 * @return The target directory 1.
	 */
	public String getTarget1DirectoryText() {

		return this.target1DirectoryField.getText();
	}

	/**
	 * Set the target directory 1.
	 * 
	 * @param text
	 *            The target directory 1.
	 */
	public void setTarget1DirectoryText(String text) {

		this.target1DirectoryField.setText(text);
	}

	/**
	 * Set the target directory.
	 * 
	 * @return The target directory.
	 */
	public String getTargetDirectoryText() {

		return this.targetDirectoryField.getText();
	}

	/**
	 * Set the target directory.
	 * 
	 * @param text
	 *            The target directory.
	 */
	public void setTargetDirectoryText(String text) {

		this.targetDirectoryField.setText(text);
	}

}
