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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Command line generator.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CommandLineGenerator extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String FILTER_NAME_BEGIN = "<i>"; //$NON-NLS-1$
	private static final String FILTER_NAME_END = "</i>"; //$NON-NLS-1$

	private static final String COMMAND_BEGIN = "<span style=\"font-weight: bold;\">"; //$NON-NLS-1$
	private static final String COMMAND_END = "</span>"; //$NON-NLS-1$

	private static final String INPUT_FILE_BEGIN = "<span style=\"color: rgb(51, 51, 255);\">"; //$NON-NLS-1$
	private static final String INPUT_FILE_END = "</span>"; //$NON-NLS-1$

	private static final String AUXILIAR_FILE_BEGIN = "<span style=\"color: rgb(0, 150, 0);\">"; //$NON-NLS-1$
	private static final String AUXILIAR_FILE_END = "</span>"; //$NON-NLS-1$

	private static final String OUTPUT_FILE_BEGIN = "<span style=\"color: rgb(200, 0, 0);\">"; //$NON-NLS-1$
	private static final String OUTPUT_FILE_END = "</span>"; //$NON-NLS-1$

	private static final String PARAMETER_BEGIN = "<span style=\"color: rgb(255, 0, 0);\"><strong>"; //$NON-NLS-1$
	private static final String PARAMETER_END = "</strong></span>"; //$NON-NLS-1$

	private static final String BEGIN_PARAGRAPH = "<p>"; //$NON-NLS-1$
	private static final String END_PARAGRAPH = "</p>"; //$NON-NLS-1$
	private static final String NEW_LINE = "<br/>"; //$NON-NLS-1$

	private static CommandLineGenerator singleInstance = null;

	private JEditorPane filterHelp = new JEditorPane();

	// Filter parameters.
	private FilterAdapter adapter = null;
	private String field1 = new String();
	private String field2 = new String();
	private String field3 = new String();
	private boolean checkBox1 = false;
	private boolean checkBox2 = false;
	private String targetFileOrDirectory1 = new String();
	private String file1 = new String();
	private String directory1 = new String();
	private String targetDirectoryText = new String();

	private List<String> commands = new ArrayList<>();

	/**
	 * Provide the command line generator instance.
	 * 
	 * @return The command line generator instance.
	 */
	public static CommandLineGenerator getInstance() {

		if (singleInstance == null) {

			singleInstance = new CommandLineGenerator();

			singleInstance.showCommands();
		}

		return singleInstance;
	}

	/**
	 * Create a command line equivalent to the graphical execution.
	 */
	private CommandLineGenerator() {

		super(Text.get(Text.COMMAND_LINE_GENERATOR_TITLE));

		// Change the frame icon.
		SwingUtil.changeWindowIcon(this,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$

		this.filterHelp.setContentType("text/html;charset=" //$NON-NLS-1$
				+ FilterAnyConfiguration.getMimeType());
		this.filterHelp.setEditable(false);

		JPanel internalPanel = new JPanel(new BorderLayout());
		internalPanel
				.add(new JScrollPane(this.filterHelp), BorderLayout.CENTER);

		setContentPane(internalPanel);

		this.filterHelp.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyPressed(KeyEvent event) {

				super.keyPressed(event);

				if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {

					setVisible(false);
				}

			}
		});

		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setWindowPosition();
	}

	/**
	 * Clear all the internal fields.
	 */
	private void clearFields() {

		this.adapter = null;
		this.field1 = new String();
		this.field2 = new String();
		this.field3 = new String();
		this.checkBox1 = false;
		this.checkBox2 = false;
		this.targetFileOrDirectory1 = new String();
		this.file1 = new String();
		this.directory1 = new String();
		this.targetDirectoryText = new String();

	}

	/**
	 * Set the HTML text.
	 * 
	 * @param html
	 *            The HTML text.
	 */
	public void setHTMLText(String html) {

		this.filterHelp.setText(html);
		this.filterHelp.setCaretPosition(0);
	}

	/**
	 * Position the window.
	 */
	private void setWindowPosition() {

		// Get window dimensions.
		int screenHeight = SwingUtil.getScreenHeight();
		int screenWidth = SwingUtil.getScreenWidth();

		// Get size of the screen.
		int windowHeight = (int) (screenHeight * 0.3);
		int windowWidth = (int) (screenWidth * 0.3);

		// Set the size and location of the window.
		setSize(windowWidth, windowHeight);
		setLocation((int) (screenWidth * 0.1), (int) (screenHeight * 0.1));

	}

	/**
	 * Set the new content of the editor.
	 */
	private void showCommands() {

		StringBuffer newContent = new StringBuffer();

		newContent.append("<html>"); //$NON-NLS-1$
		newContent.append("<body>"); //$NON-NLS-1$

		newContent.append(BEGIN_PARAGRAPH
				+ Text.get(Text.COMMAND_LINE_GENERATOR_INTERNAL_INSTRUCTION)
				+ END_PARAGRAPH);

		newContent.append(NEW_LINE);

		for (String line : this.commands) {
			newContent.append(line);
		}
		newContent.append("</body>"); //$NON-NLS-1$
		newContent.append("</html>"); //$NON-NLS-1$

		this.filterHelp.setText(newContent.toString());

	}

	/**
	 * Verify if a control is present on the list of controls.
	 * 
	 * @param control
	 *            A control.
	 * @param controls
	 *            The list of controls.
	 * @return True if the control exists.
	 */
	private static boolean hasControl(FilterControls control,
			FilterControls controls[]) {

		for (FilterControls thisControl : controls) {

			if (thisControl == control) {

				return true;
			}
		}

		return false;
	}

	/**
	 * Indicate that the text mode was activated.
	 */
	public void setTextMode() {

		StringBuffer commandGenerated = new StringBuffer();

		commandGenerated.append(FILTER_NAME_BEGIN + "# " //$NON-NLS-1$
				+ Text.get(Text.TEXT_MODE_ACTIVATED) + FILTER_NAME_END);

		commandGenerated.append(BEGIN_PARAGRAPH);

		commandGenerated.append(COMMAND_BEGIN + "TextMode" + COMMAND_END); //$NON-NLS-1$

		commandGenerated.append(END_PARAGRAPH);

		this.commands.add(commandGenerated.toString());

		showCommands();
	}

	/**
	 * Indicate that the file mode was activated.
	 */
	public void setFileMode() {

		StringBuffer commandGenerated = new StringBuffer();

		commandGenerated.append(FILTER_NAME_BEGIN + "# " //$NON-NLS-1$
				+ Text.get(Text.FILE_MODE_ACTIVATED) + FILTER_NAME_END);

		commandGenerated.append(BEGIN_PARAGRAPH);

		commandGenerated.append(COMMAND_BEGIN + "FileMode" + COMMAND_END); //$NON-NLS-1$

		commandGenerated.append(END_PARAGRAPH);

		this.commands.add(commandGenerated.toString());

		showCommands();
	}

	/**
	 * Log a execution.
	 */
	public void logExecution() {

		FilterControls controls[] = this.adapter.getControls();

		StringBuffer commandGenerated = new StringBuffer();

		if (this.adapter instanceof CommandLine) {

			CommandLine command = (CommandLine) this.adapter;

			String commandLine = command.getCommandName();

			commandGenerated.append(BEGIN_PARAGRAPH + FILTER_NAME_BEGIN
					+ "# " //$NON-NLS-1$
					+ this.adapter.getFilterName() + FILTER_NAME_END
					+ END_PARAGRAPH);

			commandGenerated.append(BEGIN_PARAGRAPH + COMMAND_BEGIN
					+ commandLine + COMMAND_END);

			commandGenerated.append(" "); //$NON-NLS-1$

			commandGenerated.append(INPUT_FILE_BEGIN
					+ Text.get(Text.COMMAND_LINE_INPUT_FILE) + INPUT_FILE_END);

			if (command instanceof SpecialBehavior
					&& FilterAnyLogic.hasSpecialBehavior(
							(SpecialBehavior) command,
							SpecialBehavior.Behavior.WORK_ON_DUAL_PANE)) {

				commandGenerated.append(" "); //$NON-NLS-1$

				commandGenerated.append(AUXILIAR_FILE_BEGIN
						+ Text.get(Text.COMMAND_LINE_AUXILIAR_FILE)
						+ AUXILIAR_FILE_END);
			}

			commandGenerated.append(" "); //$NON-NLS-1$

			commandGenerated
					.append(OUTPUT_FILE_BEGIN
							+ Text.get(Text.COMMAND_LINE_OUTPUT_FILE)
							+ OUTPUT_FILE_END);

			commandGenerated.append(" "); //$NON-NLS-1$

			if (hasControl(FilterControls.TARGET_FILE_OR_DIRECTORY_1, controls)) {

				if (this.targetFileOrDirectory1 != null
						&& !"".equals(this.targetFileOrDirectory1)) { //$NON-NLS-1$

					this.targetFileOrDirectory1 = StringSupport.replace(
							this.targetFileOrDirectory1, "\\", "\\\\"); //$NON-NLS-1$ //$NON-NLS-2$

					if (this.targetFileOrDirectory1
							.equals(this.targetFileOrDirectory1.trim())
							&& this.targetFileOrDirectory1.indexOf(' ') == -1
							&& this.targetFileOrDirectory1.indexOf('\t') == -1) {
						commandGenerated.append(PARAMETER_BEGIN
								+ "-t1 " //$NON-NLS-1$
								+ PARAMETER_END + this.targetFileOrDirectory1
								+ " "); //$NON-NLS-1$
					} else {
						this.targetFileOrDirectory1 = StringSupport
								.textToHTML(this.targetFileOrDirectory1);

						commandGenerated.append(PARAMETER_BEGIN
								+ "-t1 " //$NON-NLS-1$
								+ PARAMETER_END
								+ "\"" + this.targetFileOrDirectory1 //$NON-NLS-1$
								+ "\" "); //$NON-NLS-1$
					}

				}
			}

			if (hasControl(FilterControls.TARGET_DIRECTORY_1, controls)) {

				if (this.directory1 != null && !"".equals(this.directory1)) { //$NON-NLS-1$

					this.directory1 = StringSupport.replace(this.directory1,
							"\\", "\\\\"); //$NON-NLS-1$  //$NON-NLS-2$

					if (this.directory1.equals(this.directory1.trim())
							&& this.directory1.indexOf(' ') == -1
							&& this.directory1.indexOf('\t') == -1) {
						commandGenerated.append(PARAMETER_BEGIN + "-d1 " //$NON-NLS-1$
								+ PARAMETER_END + this.directory1 + " "); //$NON-NLS-1$
					} else {
						this.directory1 = StringSupport
								.textToHTML(this.directory1);

						commandGenerated.append(PARAMETER_BEGIN
								+ "-d1 " //$NON-NLS-1$
								+ PARAMETER_END
								+ "\"" + this.directory1 + "\" "); //$NON-NLS-1$  //$NON-NLS-2$
					}
				}
			}

			if (hasControl(FilterControls.TARGET_FILE_1, controls)) {

				if (this.file1 != null && !"".equals(this.file1)) { //$NON-NLS-1$

					this.file1 = StringSupport
							.replace(this.file1, "\\", "\\\\"); //$NON-NLS-1$ //$NON-NLS-2$

					if (this.file1.equals(this.file1.trim())
							&& this.file1.indexOf(' ') == -1
							&& this.file1.indexOf('\t') == -1) {
						commandGenerated.append(PARAMETER_BEGIN + "-l1 " //$NON-NLS-1$
								+ PARAMETER_END + this.file1 + " "); //$NON-NLS-1$
					} else {
						this.file1 = StringSupport.textToHTML(this.file1);

						commandGenerated.append(PARAMETER_BEGIN + "-l1 " //$NON-NLS-1$
								+ PARAMETER_END + "\"" + this.file1 + "\" "); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}

			if (hasControl(FilterControls.INPUT_FIELD_1, controls)) {

				if (this.field1 != null && !"".equals(this.field1)) { //$NON-NLS-1$

					if (this.field1.equals(this.field1.trim())
							&& this.field1.indexOf(' ') == -1
							&& this.field1.indexOf('\t') == -1) {
						commandGenerated.append(PARAMETER_BEGIN + "-f1 " //$NON-NLS-1$
								+ PARAMETER_END + this.field1 + " "); //$NON-NLS-1$
					} else {
						this.field1 = StringSupport.textToHTML(this.field1);

						commandGenerated.append(PARAMETER_BEGIN + "-f1 " //$NON-NLS-1$
								+ PARAMETER_END + "\"" + this.field1 + "\" "); //$NON-NLS-1$  //$NON-NLS-2$
					}

				}
			}

			if (hasControl(FilterControls.INPUT_FIELD_2, controls)) {

				if (this.field2 != null && !"".equals(this.field2)) { //$NON-NLS-1$

					if (this.field2.equals(this.field2.trim())
							&& this.field2.indexOf(' ') == -1
							&& this.field2.indexOf('\t') == -1) {
						commandGenerated.append(PARAMETER_BEGIN + "-f2 " //$NON-NLS-1$
								+ PARAMETER_END + this.field2 + " "); //$NON-NLS-1$
					} else {
						this.field2 = StringSupport.textToHTML(this.field2);
						commandGenerated.append(PARAMETER_BEGIN + "-f2 " //$NON-NLS-1$
								+ PARAMETER_END + "\"" + this.field2 + "\" "); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}

			if (hasControl(FilterControls.INPUT_FIELD_3, controls)) {

				if (this.field3 != null && !"".equals(this.field3)) { //$NON-NLS-1$

					if (this.field3.equals(this.field3.trim())
							&& this.field3.indexOf(' ') == -1
							&& this.field3.indexOf('\t') == -1) {
						commandGenerated.append(PARAMETER_BEGIN + "-f3 " //$NON-NLS-1$
								+ PARAMETER_END + this.field3 + " "); //$NON-NLS-1$
					} else {
						this.field3 = StringSupport.textToHTML(this.field3);
						commandGenerated.append(PARAMETER_BEGIN + "-f3 " //$NON-NLS-1$
								+ PARAMETER_END + "\"" + this.field3 + "\" "); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}

			if (hasControl(FilterControls.CHECK_BOX_1, controls)) {

				commandGenerated
						.append(PARAMETER_BEGIN
								+ "-c1 " //$NON-NLS-1$
								+ PARAMETER_END
								+ (this.checkBox1 ? "yes" : "no") + " "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}

			if (hasControl(FilterControls.CHECK_BOX_2, controls)) {

				commandGenerated
						.append(PARAMETER_BEGIN
								+ "-c2 " //$NON-NLS-1$
								+ PARAMETER_END
								+ (this.checkBox2 ? "yes" : "no") + " "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}

			if (this.targetDirectoryText != null
					&& !"".equals(this.targetDirectoryText.trim())) { //$NON-NLS-1$ 

				this.targetDirectoryText = StringSupport.replace(
						this.targetDirectoryText, "\\", "\\\\"); //$NON-NLS-1$ //$NON-NLS-2$ 

				if (this.targetDirectoryText.equals(this.targetDirectoryText
						.trim())
						&& this.targetDirectoryText.indexOf(' ') == -1
						&& this.targetDirectoryText.indexOf('\t') == -1) {
					commandGenerated.append(PARAMETER_BEGIN + "-TDir " //$NON-NLS-1$
							+ PARAMETER_END + this.targetDirectoryText + " "); //$NON-NLS-1$
				} else {
					this.targetDirectoryText = StringSupport
							.textToHTML(this.targetDirectoryText);

					commandGenerated.append(PARAMETER_BEGIN + "-TDir " //$NON-NLS-1$
							+ PARAMETER_END + "\"" + this.targetDirectoryText //$NON-NLS-1$
							+ "\" "); //$NON-NLS-1$
				}
			}

			commandGenerated.append(END_PARAGRAPH);

			this.commands.add(commandGenerated.toString());

			showCommands();
		}

		clearFields();
	}

	/**
	 * Show the last line.
	 */
	public void showLastLine() {

		int length = this.filterHelp.getDocument().getLength();

		this.filterHelp.setCaretPosition(length - 1);
	}

	/**
	 * Provide the field 1.
	 * 
	 * @return the field1
	 */
	protected String getField1() {

		return this.field1;
	}

	/**
	 * Set the field 1.
	 * 
	 * @param field1
	 *            the field1 to set
	 */
	protected void setField1(String field1) {

		this.field1 = field1;
	}

	/**
	 * Provide the field 2.
	 * 
	 * @return the field2
	 */
	protected String getField2() {

		return this.field2;
	}

	/**
	 * Set the field 2.
	 * 
	 * @param field2
	 *            the field2 to set
	 */
	protected void setField2(String field2) {

		this.field2 = field2;
	}

	/**
	 * Provide the field 3.
	 * 
	 * @return the field3
	 */
	protected String getField3() {

		return this.field3;
	}

	/**
	 * Set the field 3.
	 * 
	 * @param field3
	 *            the field3 to set
	 */
	protected void setField3(String field3) {

		this.field3 = field3;
	}

	/**
	 * Provide the check box 1.
	 * 
	 * @return the checkBox1
	 */
	protected boolean isCheckBox1() {

		return this.checkBox1;
	}

	/**
	 * Set the check box 1.
	 * 
	 * @param checkBox1
	 *            the checkBox1 to set
	 */
	protected void setCheckBox1(boolean checkBox1) {

		this.checkBox1 = checkBox1;
	}

	/**
	 * Provide the check box 2.
	 * 
	 * @return the checkBox2
	 */
	protected boolean isCheckBox2() {

		return this.checkBox2;
	}

	/**
	 * Set the check box 3.
	 * 
	 * @param checkBox2
	 *            the checkBox2 to set
	 */
	protected void setCheckBox2(boolean checkBox2) {

		this.checkBox2 = checkBox2;
	}

	/**
	 * Provide the target file or directory.
	 * 
	 * @return the targetFileOrDirectory1
	 */
	protected String getTargetFileOrDirectory1() {

		return this.targetFileOrDirectory1;
	}

	/**
	 * Set the target file or directory.
	 * 
	 * @param targetFileOrDirectory1
	 *            the targetFileOrDirectory1 to set
	 */
	protected void setTargetFileOrDirectory1(String target1) {

		this.targetFileOrDirectory1 = target1;
	}

	/**
	 * Provide the target file 1.
	 * 
	 * @return the file1
	 */
	protected String getFile1() {

		return this.file1;
	}

	/**
	 * Set the target file 1.
	 * 
	 * @param file1
	 *            the file1 to set
	 */
	protected void setFile1(String file1) {

		this.file1 = file1;
	}

	/**
	 * Provide the target directory 1.
	 * 
	 * @return the directory1
	 */
	protected String getDirectory1() {

		return this.directory1;
	}

	/**
	 * Set the directory 1.
	 * 
	 * @param directory1
	 *            the directory1 to set
	 */
	protected void setDirectory1(String directory1) {

		this.directory1 = directory1;
	}

	/**
	 * Set the filter being used.
	 * 
	 * @param adapter
	 *            the adapter to set
	 */
	public void setAdapter(FilterAdapter adapter) {

		this.adapter = adapter;
	}

	/**
	 * Inform the target directory on file mode.
	 * 
	 * @return the targetDirectoryText The target directory on file mode.
	 */
	public String getTargetDirectoryText() {

		return this.targetDirectoryText;
	}

	/**
	 * Set the target directory on file mode.
	 * 
	 * @param targetDirectoryText
	 *            the targetDirectoryText to set The target directory on file
	 *            mode.
	 */
	public void setTargetDirectoryText(String targetDirectoryText) {

		this.targetDirectoryText = targetDirectoryText;
	}

}
