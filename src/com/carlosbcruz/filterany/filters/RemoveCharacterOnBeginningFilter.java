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

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterException;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.Report;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Remove a certain number of characters from the beginning of each line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class RemoveCharacterOnBeginningFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private static final int NOT_DEFINED = -1;

	private int charactersRemoved = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_INSTRUCTIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getFilterLevel()
	 */
	@Override
	public FilterLevel getFilterLevel() {

		return FilterLevel.GENERAL_USER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsRequired()
	 */
	@Override
	public FilterControls[] getControls() {

		return new FilterControls[] { FilterControls.INPUT_FIELD_1,
				FilterControls.CHECK_BOX_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NUMBER_GENERIC,
				FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.OPTIONAL,
				FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {
				Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_FIELD1),
				Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_CHECKBOX1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_FIELD1_TOOLTIP),
				Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_CHECKBOX1_TOOLTIP) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] { Behavior.ACCEPT_AUTOMATIC_BEHAVIOR };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText)
			throws FilterException {

		this.charactersRemoved = 0;

		int numberOfCharacters = NOT_DEFINED;

		String numberOfCharactersParameter = getField1().trim();

		if ("".equals(numberOfCharactersParameter)) { //$NON-NLS-1$

			numberOfCharacters = 1;

		} else {

			try {
				numberOfCharacters = Integer
						.parseInt(numberOfCharactersParameter);
			} catch (NumberFormatException exception) {
				throw new FilterException(Text.get(
						Text.FILTER_REMOVECHARACTERBEGINNING_EXCEPTION,
						numberOfCharactersParameter));
			}
			if (numberOfCharacters < 0) {
				throw new FilterException(Text.get(
						Text.FILTER_REMOVECHARACTERBEGINNING_EXCEPTION,
						numberOfCharactersParameter));
			}
		}

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		if (isCheckBox1()) {

			while (tokenizer.hasMoreTokens()) {

				String token = tokenizer.nextToken();

				String newToken = null;
				if (numberOfCharacters <= token.length()) {
					newToken = token.substring(numberOfCharacters);
					this.charactersRemoved += numberOfCharacters;
				} else {
					this.charactersRemoved += token.length();
					newToken = ""; //$NON-NLS-1$
				}

				if (tokenizer.isFoundNewLine()) {
					output.append(newToken + getNewLine());
				} else {
					output.append(newToken);
				}
			}

		} else {

			while (tokenizer.hasMoreTokens()) {

				String token = tokenizer.nextToken();

				// Find the first valid character.
				int start = 0;
				for (int i = 0; i < token.length(); i++) {
					if (token.charAt(i) <= ' ') {
						start++;
					} else {
						break;
					}
				}

				// Get the real line to work with.
				String workToken = start > 0 ? token.substring(start) : token;
				String spaces = start > 0 ? token.substring(0, start) : ""; //$NON-NLS-1$

				// Remove the number of characters requested.
				if (numberOfCharacters <= workToken.length()) {
					workToken = workToken.substring(numberOfCharacters);
					this.charactersRemoved += numberOfCharacters;
				} else {
					this.charactersRemoved += workToken.length();
					workToken = ""; //$NON-NLS-1$
				}

				// The final line is the previous spaces and the lines with the
				// characters removed.
				token = spaces + workToken;

				if (tokenizer.isFoundNewLine()) {
					output.append(token + getNewLine());
				} else {
					output.append(token);
				}
			}
		}
		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getAuxiliarInputExample()
	 */
	@Override
	public String getAuxiliarInputExample() {

		return new String();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getMainInputExample()
	 */
	@Override
	public String getMainInputExample() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERBEGINNING_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "RemCB"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_REMOVECHARACTERBEGINNING_REPORT,
				String.valueOf(this.charactersRemoved));
	}

}
