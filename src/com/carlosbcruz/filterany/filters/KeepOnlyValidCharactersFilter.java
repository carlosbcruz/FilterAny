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
 * Transform all content into upper case text.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class KeepOnlyValidCharactersFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int charactersRemoved = 0;

	private char[] validCharacters = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i',
			'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x',
			'c', 'v', 'b', 'n', 'm', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '0', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A',
			'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B',
			'N', 'M' };

	private char[] validSymbols = { '\'', '"', '!', '@', '#', '$', '%', '�',
			'&', '*', '(', ')', '-', '_', '=', '+', '�', '`', '[', '{', ']',
			'}', ',', '<', '.', '>', ';', ':', '\\', '|', '/', '?', '~', '^' };

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_INSTRUCTIONS);
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

		return new FilterControls[] { FilterControls.CHECK_BOX_1,
				FilterControls.INPUT_FIELD_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NOT_RELEVANT, FilterType.ANYTHING };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
				FilterParameter.OPTIONAL };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {
				Text.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_CHECKBOX_1),
				Text.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_FIELD_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_CHECKBOX_1_TOOLTIP),
				Text.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_FIELD_1_TOOLTIP) };
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

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		while (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();
			String outputLine = new String();

			boolean characterIncluded = false;

			for (int i = 0; i < token.length(); i++) {

				char character = token.charAt(i);

				boolean validLetter = false;

				if (Character.isSpaceChar(character)) {

					validLetter = true;
				}

				for (int j = 0; j < this.validCharacters.length; j++) {

					if (this.validCharacters[j] == character) {
						validLetter = true;
						break;
					}
				}

				boolean validSymbol = false;
				for (int j = 0; j < this.validSymbols.length; j++) {

					if (this.validSymbols[j] == character) {
						validSymbol = true;
					}
				}

				boolean customSymbols = false;
				char custom[] = getField1().toCharArray();
				for (int j = 0; j < custom.length; j++) {

					if (custom[j] == character) {
						customSymbols = true;
						break;
					}
				}

				if (isCheckBox1()) {
					if (validSymbol) {
						outputLine += character;
						characterIncluded = true;
					}
				}

				if (validLetter) {
					outputLine += character;
					characterIncluded = true;
				}

				if (custom.length > 0 && customSymbols == true) {
					outputLine += character;
					characterIncluded = true;
				}

				if (characterIncluded) {
					this.charactersRemoved++;
				}
			}

			if (tokenizer.isFoundNewLine()) {
				output.append(outputLine + getNewLine());
			} else {
				output.append(outputLine);
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

		return Text
				.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text
				.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Vald"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_KEEPONLYVALIDCHARACTERSFILTER_REPORT,
				String.valueOf(this.charactersRemoved));
	}

}
