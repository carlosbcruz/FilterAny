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

import java.util.Calendar;
import java.util.Random;

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
 * Replace certain characters with numbers and the text will still be readible.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextWithErrorsFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int numberOfSwitches = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_TEXTWITHERRORSFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_TEXTWITHERRORSFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_TEXTWITHERRORSFILTER_INSTRUCTIONS);
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

		return new FilterControls[] { FilterControls.INPUT_FIELD_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NUMBER_GENERIC };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.OPTIONAL };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text.get(Text.FILTER_TEXTWITHERRORSFILTER_FIELD1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_TEXTWITHERRORSFILTER_FIELD1_TOOLTIP) };
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

	/**
	 * Switch the characters position inside a word.
	 * 
	 * @param wordParameter
	 *            The word to have it's character switched.
	 * @param numberOfchanges
	 *            The number of switches.
	 * @return The word with characters switched.
	 */
	private static StringBuffer scrableInternalSideOfWords(
			StringBuffer wordParameter, int numberOfchanges) {

		StringBuffer word = new StringBuffer(wordParameter);

		Random random = new Random(Calendar.getInstance().getTimeInMillis());

		if (word.length() <= 3) {

			return word;
		}

		for (int i = 0; i < numberOfchanges; i++) {

			// Do not change first and last character.
			int position = random.nextInt(word.length() - 2) + 1;

			int nextPosition = position + 1;

			if (nextPosition == word.length() - 1) {
				nextPosition = 1;
			}

			char tempChar = word.charAt(position);
			word.setCharAt(position, word.charAt(nextPosition));
			word.setCharAt(nextPosition, tempChar);

		}

		return word;
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

		StringBuffer output = new StringBuffer();

		this.numberOfSwitches = 0;

		String numberOfCharactersParameter = getField1().trim();

		if ("".equals(numberOfCharactersParameter)) { //$NON-NLS-1$

			this.numberOfSwitches = 1;

		} else {

			try {
				this.numberOfSwitches = Integer
						.parseInt(numberOfCharactersParameter);
			} catch (NumberFormatException exception) {
				throw new FilterException(Text.get(
						Text.FILTER_TEXTWITHERRORSFILTER_EXCEPTION,
						numberOfCharactersParameter));
			}
			if (this.numberOfSwitches < 0) {
				throw new FilterException(Text.get(
						Text.FILTER_TEXTWITHERRORSFILTER_EXCEPTION,
						numberOfCharactersParameter));
			}
		}

		LineTokenizer tokenizer = new LineTokenizer(text);

		while (tokenizer.hasMoreTokens()) {

			String line = tokenizer.nextToken();

			StringBuffer word = new StringBuffer();
			boolean insideWord = false;

			for (int i = 0; i < line.length(); i++) {

				char character = line.charAt(i);

				if (Character.isSpaceChar(character)) {

					if (insideWord) {

						insideWord = false;
						output.append(scrableInternalSideOfWords(word,
								this.numberOfSwitches));
						word = new StringBuffer();
						output.append(character);

					} else {

						output.append(character);
					}

				} else {

					if (insideWord) {

						word.append(character);

					} else {

						insideWord = true;
						word.append(character);
					}
				}
			}

			if (insideWord) {

				insideWord = false;
				output.append(scrableInternalSideOfWords(word,
						this.numberOfSwitches));
				word = new StringBuffer();
			}

			output.append(getNewLine());
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

		return Text.get(Text.FILTER_TEXTWITHERRORSFILTER_COMMAND_LINE_HELP);
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

		return Text.get(Text.FILTER_TEXTWITHERRORSFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_TEXTWITHERRORSFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "TxErr"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_TEXTWITHERRORSFILTER_REPORT,
				String.valueOf(this.numberOfSwitches));
	}

}
