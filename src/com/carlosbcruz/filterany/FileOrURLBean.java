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

/**
 * Stores the table information.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FileOrURLBean {

	private String target = null;
	private String newTarget = null;
	private FileOrURLBeanType type = null;
	private long size = -1;
	private FileOrURLBeanStatus currentStatus;

	/**
	 * Indicate the possible file status.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public enum FileOrURLBeanStatus {
		OK, NOT_VERIFIED, INVALID
	}

	/**
	 * Indicte the possible file types.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public enum FileOrURLBeanType {
		FILE, URL, TEXT
	}

	/**
	 * Stores a single row for the table information.
	 * 
	 * @param target
	 *            The target file or URL.
	 * @param type
	 *            Indicate what is the type of the row content.
	 * @param size
	 *            Indicate the file size if exists.
	 * @param currentStatus
	 *            Indicate the current status.
	 */
	public FileOrURLBean(String target, FileOrURLBeanType type, long size,
			FileOrURLBeanStatus currentStatus) {

		super();

		this.target = target;
		this.type = type;
		this.size = size;
		this.currentStatus = currentStatus;
	}

	/**
	 * Indicate the target file or URL.
	 * 
	 * @return the target The target file or URL.
	 */
	protected String getTarget() {
		return this.target;
	}

	/**
	 * Inform what is the type of the row content.
	 * 
	 * @return the type What is the type of the row content.
	 */
	protected FileOrURLBeanType getType() {
		return this.type;
	}

	/**
	 * Inform the file size if exists.
	 * 
	 * @return the size The file size if exists.
	 */
	protected long getSize() {
		return this.size;
	}

	/**
	 * Inform the current status.
	 * 
	 * @return the currentStatus The current status.
	 */
	protected FileOrURLBeanStatus getCurrentStatus() {
		return this.currentStatus;
	}

	/**
	 * Indicate the new target name.
	 * 
	 * @return the newTarget The new target name.
	 */
	protected String getNewTarget() {
		return this.newTarget;
	}

	/**
	 * Set the new target name.
	 * 
	 * @param newTarget
	 *            the newTarget to set The new target name.
	 */
	protected void setNewTarget(String newTarget) {
		this.newTarget = newTarget;
	}

}
