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

/**
 * Store the controls of a filter.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ControlFieldsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String filterClass = new String(); // The filters class name.
	private String field1 = new String(); // The content of field 1.
	private String field2 = new String(); // The content of field 2.
	private String field3 = new String(); // The content of field 3.
	private boolean checkBox1 = false; // The content of checkBox 1.
	private boolean checkBox2 = false; // The content of checkBox 2.
	private String targetFileOrDirectory1 = new String(); // The content of
	// target file
	// or directory
	// 1.
	private String targetDirectory = new String(); // The content of target
	// directory.
	private String target1File = new String(); // The content of target 1
	// file.
	private String target1Directory = new String(); // The content of target

	// 1 directory.

	/**
	 * Default constructor.
	 */
	public ControlFieldsBean() {

		super();
	}

	/**
	 * @param String
	 *            filterClassParameter The filters class name.
	 * @param String
	 *            field1Parameter The content of field 1.
	 * @param String
	 *            field2Parameter The content of field 2.
	 * @param String
	 *            field3Parameter The content of field 3.
	 * @param boolean checkBox1Parameter The content of checkBox 1.
	 * @param boolean checkBox2Parameter The content of checkBox 2.
	 * @param String
	 *            targetFileOrDirectory1Parameter The content of target file or
	 *            directory 1.
	 * @param String
	 *            targetDirectoryParameter The content of target directory.
	 * @param String
	 *            target1FileParameter The content of target 1 file.
	 * @param String
	 *            target1DirectoryParameter The content of target 1 directory.
	 */
	public ControlFieldsBean(String filterClassParameter,
			String field1Parameter, String field2Parameter,
			String field3Parameter, boolean checkBox1Parameter,
			boolean checkBox2Parameter, String targetFileOrDirectory1Parameter,
			String targetDirectoryParameter, String target1FileParameter,
			String target1DirectoryParameter) {

		super();

		this.filterClass = filterClassParameter;
		this.field1 = field1Parameter;
		this.field2 = field2Parameter;
		this.field3 = field3Parameter;
		this.checkBox1 = checkBox1Parameter;
		this.checkBox2 = checkBox2Parameter;
		this.targetFileOrDirectory1 = targetFileOrDirectory1Parameter;
		this.targetDirectory = targetDirectoryParameter;
		this.target1File = target1FileParameter;
		this.target1Directory = target1DirectoryParameter;
	}

	/**
	 * Provide: The filters class name.
	 * 
	 * @return filterClass The filters class name.
	 */
	public String getFilterClass() {
		return this.filterClass;
	}

	/**
	 * Set: The filters class name.
	 * 
	 * @param filterClass
	 *            The filters class name.
	 */
	public void setFilterClass(String filterClassParameter) {
		this.filterClass = filterClassParameter;
	}

	/**
	 * Provide: The content of field 1.
	 * 
	 * @return field1 The content of field 1.
	 */
	public String getField1() {
		return this.field1;
	}

	/**
	 * Set: The content of field 1.
	 * 
	 * @param field1
	 *            The content of field 1.
	 */
	public void setField1(String field1Parameter) {
		this.field1 = field1Parameter;
	}

	/**
	 * Provide: The content of field 2.
	 * 
	 * @return field2 The content of field 2.
	 */
	public String getField2() {
		return this.field2;
	}

	/**
	 * Set: The content of field 2.
	 * 
	 * @param field2
	 *            The content of field 2.
	 */
	public void setField2(String field2Parameter) {
		this.field2 = field2Parameter;
	}

	/**
	 * Provide: The content of field 3.
	 * 
	 * @return field3 The content of field 3.
	 */
	public String getField3() {
		return this.field3;
	}

	/**
	 * Set: The content of field 3.
	 * 
	 * @param field3
	 *            The content of field 3.
	 */
	public void setField3(String field3Parameter) {
		this.field3 = field3Parameter;
	}

	/**
	 * Provide: The content of checkBox 1.
	 * 
	 * @return checkBox1 The content of checkBox 1.
	 */
	public boolean getCheckBox1() {
		return this.checkBox1;
	}

	/**
	 * Set: The content of checkBox 1.
	 * 
	 * @param checkBox1
	 *            The content of checkBox 1.
	 */
	public void setCheckBox1(boolean checkBox1Parameter) {
		this.checkBox1 = checkBox1Parameter;
	}

	/**
	 * Provide: The content of checkBox 2.
	 * 
	 * @return checkBox2 The content of checkBox 2.
	 */
	public boolean getCheckBox2() {
		return this.checkBox2;
	}

	/**
	 * Set: The content of checkBox 2.
	 * 
	 * @param checkBox2
	 *            The content of checkBox 2.
	 */
	public void setCheckBox2(boolean checkBox2Parameter) {
		this.checkBox2 = checkBox2Parameter;
	}

	/**
	 * Provide: The content of target file or directory 1.
	 * 
	 * @return targetFileOrDirectory1 The content of target file or directory 1.
	 */
	public String getTargetFileOrDirectory1() {
		return this.targetFileOrDirectory1;
	}

	/**
	 * Set: The content of target file or directory 1.
	 * 
	 * @param targetFileOrDirectory1
	 *            The content of target file or directory 1.
	 */
	public void setTargetFileOrDirectory1(String targetFileOrDirectory1Parameter) {
		this.targetFileOrDirectory1 = targetFileOrDirectory1Parameter;
	}

	/**
	 * Provide: The content of target directory.
	 * 
	 * @return targetDirectory The content of target directory.
	 */
	public String getTargetDirectory() {
		return this.targetDirectory;
	}

	/**
	 * Set: The content of target directory.
	 * 
	 * @param targetDirectory
	 *            The content of target directory.
	 */
	public void setTargetDirectory(String targetDirectoryParameter) {
		this.targetDirectory = targetDirectoryParameter;
	}

	/**
	 * Provide: The content of target 1 file.
	 * 
	 * @return target1File The content of target 1 file.
	 */
	public String getTarget1File() {
		return this.target1File;
	}

	/**
	 * Set: The content of target 1 file.
	 * 
	 * @param target1File
	 *            The content of target 1 file.
	 */
	public void setTarget1File(String target1FileParameter) {
		this.target1File = target1FileParameter;
	}

	/**
	 * Provide: The content of target 1 directory.
	 * 
	 * @return target1Directory The content of target 1 directory.
	 */
	public String getTarget1Directory() {
		return this.target1Directory;
	}

	/**
	 * Set: The content of target 1 directory.
	 * 
	 * @param target1Directory
	 *            The content of target 1 directory.
	 */
	public void setTarget1Directory(String target1DirectoryParameter) {
		this.target1Directory = target1DirectoryParameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@SuppressWarnings("nls")
	@Override
	public String toString() {

		StringBuffer out = new StringBuffer();

		out.append("ControlFieldsBean [\n");

		out.append("\tfilterClass=" + this.filterClass + ",\n");
		out.append("\tfield1=" + this.field1 + ",\n");
		out.append("\tfield2=" + this.field2 + ",\n");
		out.append("\tfield3=" + this.field3 + ",\n");
		out.append("\tcheckBox1=" + this.checkBox1 + ",\n");
		out.append("\tcheckBox2=" + this.checkBox2 + ",\n");
		out.append("\ttargetFileOrDirectory1=" + this.targetFileOrDirectory1
				+ ",\n");
		out.append("\ttargetDirectory=" + this.targetDirectory + ",\n");
		out.append("\ttarget1File=" + this.target1File + ",\n");
		out.append("\ttarget1Directory=" + this.target1Directory + "]\n");

		return out.toString();
	}
}
