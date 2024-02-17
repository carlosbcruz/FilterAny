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

import java.io.Serializable;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.StringSupport;

/**
 * Represents a general structure of a filter.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public abstract class FilterAdapter implements Filter, Serializable {

	private static final long serialVersionUID = 1L;

	private boolean useDOSFormat = true;

	// Indicate that the filter requested to start an alert.
	private boolean issueAnAlert = false;

	// Filter parameters.
	private String field1 = new String();
	private String field2 = new String();
	private String field3 = new String();
	private boolean checkBox1 = false;
	private boolean checkBox2 = false;
	private String targetFileOrDirectory1 = new String();
	private String file1 = new String();
	private String directory1 = new String();

	private transient JDialog superComponent = null;

	/**
	 * Default constructor.
	 */
	public FilterAdapter() {
	}

	/**
	 * Indicate the filter name.
	 * 
	 * @return The filter name.
	 */
	@Override
	public String getFilterName() {

		return null;
	}

	/**
	 * Do no changes to the text.
	 * 
	 * @param text
	 *            The text.
	 * @return The text.
	 */
	@SuppressWarnings({ "static-method", "unused" })
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText)
			throws FilterException {

		return text;
	}

	/**
	 * Indicate that the non selected text should be included in the result.
	 * 
	 * @return true if it should be included or false otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean includeNonSelectedText() {

		return true;
	}

	/**
	 * Set the type of line feed to use, if DOS or UNIX format.
	 * 
	 * @param useDOSFormat
	 *            the useDOSFormat to set
	 */
	protected void setUseDOSFormat(boolean useDOSFormat) {
		this.useDOSFormat = useDOSFormat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * int, int)
	 */
	@Override
	public StringBuffer filter(StringBuffer inputText,
			StringBuffer auxiliarText, int selectionStart, int selectionEnd)
			throws FilterException {

		// If there is no selection
		if (selectionStart == selectionEnd) {

			return filter(inputText, auxiliarText);
		}

		StringBuffer output = new StringBuffer();

		if (includeNonSelectedText()) {
			output.append(inputText.substring(0, selectionStart));
		}

		output.append(filter(
				new StringBuffer(inputText.substring(selectionStart,
						selectionEnd)), auxiliarText));

		if (includeNonSelectedText()) {
			output.append(inputText.substring(selectionEnd));
		}

		if (FilterAnyConfiguration.isRunningInGraphicalMode()) {
			JOptionPane
					.showMessageDialog(
							getSuperComponent(),
							Text.get(Text.FILTER_APPLIED_TO_SELECTION_ONLY_DIALOG_MESSAGE),
							Text.get(Text.FILTER_APPLIED_TO_SELECTION_ONLY_DIALOG_TITLE),
							JOptionPane.WARNING_MESSAGE);
		}

		return output;

	}

	/**
	 * Provide the line feed.
	 * 
	 * @return The line feed.
	 */
	protected String getNewLine() {

		return this.useDOSFormat ? StringSupport.getDOSNewLine()
				: StringSupport.getUnixNewLine();
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
	 * Inform the super component.
	 * 
	 * @return the superComponent
	 */
	protected JDialog getSuperComponent() {

		return this.superComponent;
	}

	/**
	 * @param superComponent
	 *            the superComponent to set
	 */
	@Override
	public void setSuperComponent(JDialog superComponent) {

		this.superComponent = superComponent;
	}

	/**
	 * Indicate if the filter requires an alert or not.
	 * 
	 * @return the issueAnAlert True if the filter requires an alert or not.
	 */
	protected boolean isIssueAnAlert() {

		return this.issueAnAlert;
	}

	/**
	 * Set if the filter requires an alert or not.
	 * 
	 * @param issueAnAlert
	 *            the issueAnAlert to set. True to indicate the filter requires
	 *            an alert or not.
	 */
	protected void setIssueAnAlert(boolean issueAnAlert) {

		this.issueAnAlert = issueAnAlert;
	}

	/**
	 * Indicate that the filter run on background.
	 * 
	 * @return True if it run in background.
	 */
	@SuppressWarnings("static-method")
	protected boolean isRunInBackground() {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return getFilterName();
	}
}
