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

package com.carlosbcruz.util;

/**
 * Provided words inside a string.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class WordTokenizer {

	private StringBuffer content = new StringBuffer();
	private int index = 0;

	public enum ELEMENT_TYPE {
		NOT_DEFINED, SPACES, SEPARATION, WORD
	}

	private ELEMENT_TYPE currentType = ELEMENT_TYPE.NOT_DEFINED;
	private ELEMENT_TYPE previousType = ELEMENT_TYPE.NOT_DEFINED;

	private StringBuffer currentWord = new StringBuffer();

	/**
	 * Default constructor.
	 * 
	 * @param content
	 *            The text to navigate line by line.
	 */
	public WordTokenizer(StringBuffer content) {
		super();
		this.content = content;
	}

	/**
	 * Indicate if there are more words to read
	 * 
	 * @return true if there are more lines to read.
	 */
	public boolean hasMoreElements() {

		if (!"".equals(this.currentWord.toString())) { //$NON-NLS-1$
			return true;
		}

		return this.index < this.content.length();
	}

	/**
	 * Indicate the type of content provided.
	 * 
	 * @return It can be spaces, separation or word.
	 */
	public ELEMENT_TYPE getType() {

		return this.previousType;
	}

	/**
	 * Get the first character when the current status is not defined.
	 * 
	 * @param thisCharacter
	 *            The current character.
	 */
	private void getFirstCharacter(char thisCharacter) {

		if (thisCharacter == ' ') {

			this.currentWord.append(thisCharacter);
			this.currentType = ELEMENT_TYPE.SPACES;

		} else {

			if (Character.isLetterOrDigit(thisCharacter)) {

				this.currentWord.append(thisCharacter);
				this.currentType = ELEMENT_TYPE.WORD;

			} else {

				this.currentWord.append(thisCharacter);
				this.currentType = ELEMENT_TYPE.SEPARATION;
			}
		}
	}

	/**
	 * Provide the word.
	 * 
	 * @return The next line.
	 */
	public String nextToken() {

		while (this.index < this.content.length()) {

			char thisCharacter = this.content.charAt(this.index);

			if (this.currentType == ELEMENT_TYPE.NOT_DEFINED) {

				getFirstCharacter(thisCharacter);

			} else {

				if (thisCharacter == ' ') {

					if (this.currentType == ELEMENT_TYPE.SPACES) {

						this.currentWord.append(thisCharacter);

					} else {

						this.previousType = this.currentType;
						String word = this.currentWord.toString();
						this.currentWord = new StringBuffer();
						getFirstCharacter(thisCharacter);
						this.index++;
						return word;
					}

				} else {

					if (Character.isLetterOrDigit(thisCharacter)) {

						if (this.currentType == ELEMENT_TYPE.WORD) {

							this.currentWord.append(thisCharacter);

						} else {

							this.previousType = this.currentType;
							String word = this.currentWord.toString();
							this.currentWord = new StringBuffer();
							getFirstCharacter(thisCharacter);
							this.index++;
							return word;
						}

					} else {

						this.previousType = this.currentType;
						String word = this.currentWord.toString();
						this.currentWord = new StringBuffer();
						getFirstCharacter(thisCharacter);
						this.index++;
						return word;
					}
				}
			}

			this.index++;
		}

		this.previousType = this.currentType;
		String word = this.currentWord.toString();
		this.currentWord = new StringBuffer();
		return word;
	}
}
