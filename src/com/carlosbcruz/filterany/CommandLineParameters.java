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
 * Store all the additional parameters for all filtersWindow and positioning
 * preferences of a file.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class CommandLineParameters {

	// Location on the text where the filter is applied.
	private int characterStart = 0;
	private boolean characterStartProvided = false;
	private boolean characterStartProvidedWithError = false;
	private int characterEnd = 0;
	private boolean characterEndProvided = false;
	private boolean characterEndProvidedWithError = false;
	private int lineStart = 0;
	private boolean lineStartProvided = false;
	private boolean lineStartProvidedWithError = false;
	private int lineEnd = 0;
	private boolean lineEndProvided = false;
	private boolean lineEndProvidedWithError = false;

	// General parameters to filtersWindow.
	private String field1 = null;
	private String field2 = null;
	private String field3 = null;

	// The values of the check boxes.
	private boolean checkbox1 = false;
	private boolean checkbox2 = false;

	// Indicate if the check boxes were set or not.
	private boolean checkbox1Set = false;
	private boolean checkbox2Set = false;

	// Target directory for Search for files filter.
	private String target1 = null;

	// Target file.
	private String file1 = null;

	// Target directory.
	private String directory1 = null;

	// Indicate the target directory when the file mode is activated.
	private String targetDirectoryFileMode = null;

	// Indicate if the input parameters are well formatted.
	private boolean wellFormattedPosition = true;

	// Indicate the file that has the auxiliary text.
	private String auxiliarFile = null;

	/**
	 * Inform the character position to start the filter.
	 * 
	 * @return the characterStart The character position to start the filter.
	 */
	protected int getCharacterStart() {

		return this.characterStart;
	}

	/**
	 * Set the character position to start the filter.
	 * 
	 * @param characterStart
	 *            the characterStart to set The character position to start the
	 *            filter.
	 */
	protected void setCharacterStart(int characterStart) {

		this.characterStart = characterStart;
	}

	/**
	 * Inform the character position to end the filter.
	 * 
	 * @return the characterEnd The character position to end the filter.
	 */
	protected int getCharacterEnd() {

		return this.characterEnd;
	}

	/**
	 * Set the character position to end the filter.
	 * 
	 * @param characterEnd
	 *            the characterEnd to set The character position to end the
	 *            filter.
	 */
	protected void setCharacterEnd(int characterEnd) {

		this.characterEnd = characterEnd;
	}

	/**
	 * Inform the line to start the filter.
	 * 
	 * @return the lineStart The line to start the filter.
	 */
	protected int getLineStart() {

		return this.lineStart;
	}

	/**
	 * Set the line to start the filter.
	 * 
	 * @param lineStart
	 *            the lineStart to set The line to start the filter.
	 */
	protected void setLineStart(int lineStart) {

		this.lineStart = lineStart;
	}

	/**
	 * Inform the line to end the filter.
	 * 
	 * @return the lineEnd The line to end the filter.
	 */
	protected int getLineEnd() {

		return this.lineEnd;
	}

	/**
	 * Set the line to end the filter.
	 * 
	 * @param lineEnd
	 *            the lineEnd to set The line to end the filter.
	 */
	protected void setLineEnd(int lineEnd) {

		this.lineEnd = lineEnd;
	}

	/**
	 * Inform if the optional positioning was well formatted.
	 * 
	 * @return the wellFormattedPosition If the optional positioning was well
	 *         formatted.
	 */
	protected boolean isWellFormattedPosition() {

		return this.wellFormattedPosition;
	}

	/**
	 * Set if the optional positioning was well formatted.
	 * 
	 * @param wellFormattedPosition
	 *            the wellFormattedPosition to set If the optional positioning
	 *            was well formatted.
	 */
	protected void setWellFormattedPosition(boolean wellFormattedPosition) {

		this.wellFormattedPosition = wellFormattedPosition;
	}

	/**
	 * Provide the field 1.
	 * 
	 * @return the field1 The field 1.
	 */
	protected String getField1() {

		return this.field1;
	}

	/**
	 * Set the field 1.
	 * 
	 * @param field1
	 *            the field1 to set The field 1.
	 */
	protected void setField1(String field1) {

		this.field1 = field1;
	}

	/**
	 * Provide the field 2.
	 * 
	 * @return the field2 The field 2.
	 */
	protected String getField2() {

		return this.field2;
	}

	/**
	 * Set the field 2.
	 * 
	 * @param field2
	 *            the field2 to set The field 2.
	 */
	protected void setField2(String field2) {

		this.field2 = field2;
	}

	/**
	 * Provide the field 3.
	 * 
	 * @return the field3 The field 3.
	 */
	protected String getField3() {

		return this.field3;
	}

	/**
	 * Set the field 3.
	 * 
	 * @param field3
	 *            the field3 to set The field 3.
	 */
	protected void setField3(String field3) {

		this.field3 = field3;
	}

	/**
	 * Provide the checkbox 1.
	 * 
	 * @return the checkbox1 The checkbox 1.
	 */
	protected boolean isCheckbox1() {

		return this.checkbox1;
	}

	/**
	 * Set the checkbox 1.
	 * 
	 * @param checkbox1
	 *            the checkbox1 to set The checkbox 1.
	 */
	protected void setCheckbox1(boolean checkbox1) {

		this.checkbox1 = checkbox1;
	}

	/**
	 * Provide the checkbox 2.
	 * 
	 * @return the checkbox2 The checkbox 2.
	 */
	protected boolean isCheckbox2() {

		return this.checkbox2;
	}

	/**
	 * Set the checkbox 2.
	 * 
	 * @param checkbox2
	 *            the checkbox2 to set The checkbox 2.
	 */
	protected void setCheckbox2(boolean checkbox2) {

		this.checkbox2 = checkbox2;
	}

	/**
	 * Indicate if the checkbox1 was set.
	 * 
	 * @return the checkbox1Set The indication if the checkbox1 was set.
	 */
	protected boolean isCheckbox1Set() {

		return this.checkbox1Set;
	}

	/**
	 * Indicate that the checkbox1 was set.
	 * 
	 * @param checkbox1Set
	 *            the checkbox1Set to set Indicate that the checkbox1 was set.
	 */
	protected void setCheckbox1Set(boolean checkbox1Set) {

		this.checkbox1Set = checkbox1Set;
	}

	/**
	 * Indicate if the checkbox2 was set.
	 * 
	 * @return the checkbox1Set The indication if the checkbox2 was set.
	 */
	protected boolean isCheckbox2Set() {

		return this.checkbox2Set;
	}

	/**
	 * Indicate that the checkbox2 was set.
	 * 
	 * @param checkbox1Set
	 *            the checkbox2Set to set Indicate that the checkbox1 was set.
	 */
	protected void setCheckbox2Set(boolean checkbox2Set) {

		this.checkbox2Set = checkbox2Set;
	}

	/**
	 * Indicate the target directory when in file mode.
	 * 
	 * @return the targetDirectoryFileMode The target directory when in file
	 *         mode.
	 */
	protected String getTargetDirectoryFileMode() {

		return this.targetDirectoryFileMode;
	}

	/**
	 * Set the target directory when in file mode.
	 * 
	 * @param targetDirectoryFileMode
	 *            the targetDirectoryFileMode to set The target directory when
	 *            in file mode.
	 */
	protected void setTargetDirectoryFileMode(String targetDirectoryFileMode) {

		this.targetDirectoryFileMode = targetDirectoryFileMode;
	}

	/**
	 * Provide the target file or directory.
	 * 
	 * @return the target1 The target file or directory.
	 */
	protected String getTarget1() {

		return this.target1;
	}

	/**
	 * Set the target file or directory.
	 * 
	 * @param target1
	 *            the target1 to set The target file or directory.
	 */
	protected void setTarget1(String target1) {

		this.target1 = target1;
	}

	/**
	 * Provide the target file.
	 * 
	 * @return the file1 The target file.
	 */
	protected String getFile1() {

		return this.file1;
	}

	/**
	 * Set the target file.
	 * 
	 * @param file1
	 *            the file1 to set The target file.
	 */
	protected void setFile1(String file1) {

		this.file1 = file1;
	}

	/**
	 * Provide the directory1
	 * 
	 * @return the directory1
	 */
	protected String getDirectory1() {

		return this.directory1;
	}

	/**
	 * Set the directory1
	 * 
	 * @param directory1
	 *            the directory1 to set
	 */
	protected void setDirectory1(String directory1) {

		this.directory1 = directory1;
	}

	/**
	 * Inform if the character start has been provided.
	 * 
	 * @return the characterStartProvided
	 */
	protected boolean isCharacterStartProvided() {

		return this.characterStartProvided;
	}

	/**
	 * Set if the character start has been provided.
	 * 
	 * @param characterStartProvided
	 *            the characterStartProvided to set
	 */
	protected void setCharacterStartProvided(boolean characterStartProvided) {

		this.characterStartProvided = characterStartProvided;
	}

	/**
	 * Inform if the character end has been provided.
	 * 
	 * @return the characterEndProvided
	 */
	protected boolean isCharacterEndProvided() {

		return this.characterEndProvided;
	}

	/**
	 * Set if the character end has been provided.
	 * 
	 * @param characterEndProvided
	 *            the characterEndProvided to set
	 */
	protected void setCharacterEndProvided(boolean characterEndProvided) {

		this.characterEndProvided = characterEndProvided;
	}

	/**
	 * Inform if the line start has been provided.
	 * 
	 * @return the lineStartProvided
	 */
	protected boolean isLineStartProvided() {

		return this.lineStartProvided;
	}

	/**
	 * Set if the line start has been provided.
	 * 
	 * @param lineStartProvided
	 *            the lineStartProvided to set
	 */
	protected void setLineStartProvided(boolean lineStartProvided) {

		this.lineStartProvided = lineStartProvided;
	}

	/**
	 * Inform if the line end has been provided.
	 * 
	 * @return the lineEndProvided
	 */
	protected boolean isLineEndProvided() {

		return this.lineEndProvided;
	}

	/**
	 * Set if the line end has been provided.
	 * 
	 * @param lineEndProvided
	 *            the lineEndProvided to set
	 */
	protected void setLineEndProvided(boolean lineEndProvided) {

		this.lineEndProvided = lineEndProvided;
	}

	/**
	 * Inform if the parameter provided is invalid.
	 * 
	 * @return the characterStartProvidedWithError
	 */
	protected boolean isCharacterStartProvidedWithError() {

		return this.characterStartProvidedWithError;
	}

	/**
	 * Set if the parameter provided is invalid.
	 * 
	 * @param characterStartProvidedWithError
	 *            the characterStartProvidedWithError to set
	 */
	protected void setCharacterStartProvidedWithError(
			boolean characterStartProvidedWithError) {

		this.characterStartProvidedWithError = characterStartProvidedWithError;
	}

	/**
	 * Inform if the parameter provided is invalid.
	 * 
	 * @return the characterEndProvidedWithError
	 */
	protected boolean isCharacterEndProvidedWithError() {

		return this.characterEndProvidedWithError;
	}

	/**
	 * Set if the parameter provided is invalid.
	 * 
	 * @param characterEndProvidedWithError
	 *            the characterEndProvidedWithError to set
	 */
	protected void setCharacterEndProvidedWithError(
			boolean characterEndProvidedWithError) {

		this.characterEndProvidedWithError = characterEndProvidedWithError;
	}

	/**
	 * Inform if the parameter provided is invalid.
	 * 
	 * @return the lineStartProvidedWithError
	 */
	protected boolean isLineStartProvidedWithError() {

		return this.lineStartProvidedWithError;
	}

	/**
	 * Set if the parameter provided is invalid.
	 * 
	 * @param lineStartProvidedWithError
	 *            the lineStartProvidedWithError to set
	 */
	protected void setLineStartProvidedWithError(
			boolean lineStartProvidedWithError) {

		this.lineStartProvidedWithError = lineStartProvidedWithError;
	}

	/**
	 * Inform if the parameter provided is invalid.
	 * 
	 * @return the lineEndProvidedWithError
	 */
	protected boolean isLineEndProvidedWithError() {

		return this.lineEndProvidedWithError;
	}

	/**
	 * Set if the parameter provided is invalid.
	 * 
	 * @param lineEndProvidedWithError
	 *            the lineEndProvidedWithError to set
	 */
	protected void setLineEndProvidedWithError(boolean lineEndProvidedWithError) {

		this.lineEndProvidedWithError = lineEndProvidedWithError;
	}

	/**
	 * Inform the file that has the auxiliary text.
	 * 
	 * @return the auxiliarFile
	 */
	public String getAuxiliarFile() {

		return this.auxiliarFile;
	}

	/**
	 * Set the file that has the auxiliar text.
	 * 
	 * @param auxiliarFile
	 *            the auxiliarFile to set
	 */
	public void setAuxiliarFile(String auxiliarFile) {

		this.auxiliarFile = auxiliarFile;
	}
}
