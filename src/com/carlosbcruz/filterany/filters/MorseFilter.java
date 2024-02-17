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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
 * Transform a test into a morse code.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class MorseFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private Map<Character, String> morseTable = new HashMap<>();
	private Map<String, Character> morseTableInverse = new HashMap<>();

	private Map<Character, String> morsePonctuationTable = new HashMap<>();
	private Map<String, Character> morsePonctuationTableInverse = new HashMap<>();

	private int charactersChanged = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_MORSE_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_MORSE_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_MORSE_INSTRUCTIONS);
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
				FilterControls.CHECK_BOX_2 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NOT_RELEVANT,
				FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
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

		Text.get(Text.FILTER_MORSE_CHECKBOX1),
				Text.get(Text.FILTER_MORSE_CHECKBOX2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {

		Text.get(Text.FILTER_MORSE_CHECKBOX1_TOOLTIP),
				Text.get(Text.FILTER_MORSE_CHECKBOX2_TOOLTIP) };
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
	 * Converts a text to Morse Code.
	 * 
	 * @param text
	 *            The text.
	 * @return The Morse Code.
	 */
	private StringBuffer fromTextToMorse(StringBuffer text) {

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		while (tokenizer.hasMoreTokens()) {

			String line = tokenizer.nextToken();

			for (int i = 0; i < line.length(); i++) {

				Character character = Character.valueOf(Character
						.toUpperCase(line.charAt(i)));

				if (Character.isSpaceChar(character.charValue())) {

					output.append(character);

					continue;
				}

				String code = this.morseTable.get(character);

				if (isCheckBox1() && code == null) {

					code = this.morsePonctuationTable.get(character);

				}

				if (code != null) {

					output.append(code + " "); //$NON-NLS-1$
				}
			}

			output.append(getNewLine());
		}

		return output;
	}

	/**
	 * Converts a Morse Code back to Text.
	 * 
	 * @param text
	 *            The Morse Code.
	 * @return The text.
	 */
	private StringBuffer fromMorseToText(StringBuffer text) {

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		while (tokenizer.hasMoreTokens()) {

			String line = tokenizer.nextToken();

			StringBuffer codeRecognized = new StringBuffer();

			for (int i = 0; i < line.length(); i++) {

				char currentCharacter = line.charAt(i);
				if (currentCharacter == '.' || currentCharacter == '-') {
					codeRecognized.append(currentCharacter);
				} else {
					Character convertedCharacter = this.morseTableInverse
							.get(codeRecognized.toString());
					if (convertedCharacter != null) {
						output.append(convertedCharacter.charValue());
					} else {
						convertedCharacter = this.morsePonctuationTableInverse
								.get(codeRecognized.toString());
						if (convertedCharacter != null) {
							output.append(convertedCharacter.charValue());
						} else {
							output.append(' ');
						}
					}

					codeRecognized = new StringBuffer();
				}

			}

			output.append(getNewLine());
		}

		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@SuppressWarnings({ "nls", "boxing" })
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText)
			throws FilterException {

		// Lazy populate the codes.
		if (this.morseTable.isEmpty()) {

			this.morseTable.put('A', ".-");
			this.morseTable.put('B', "-...");
			this.morseTable.put('C', "-.-.");
			this.morseTable.put('D', "-..");
			this.morseTable.put('E', ".");
			this.morseTable.put('F', "..-.");
			this.morseTable.put('G', "--.");
			this.morseTable.put('H', "....");
			this.morseTable.put('I', "..");
			this.morseTable.put('J', ".---");
			this.morseTable.put('K', "-.-");
			this.morseTable.put('L', ".-..");
			this.morseTable.put('M', "--");
			this.morseTable.put('N', "-.");
			this.morseTable.put('O', "---");
			this.morseTable.put('P', ".--.");
			this.morseTable.put('Q', "--.-");
			this.morseTable.put('R', ".-.");
			this.morseTable.put('S', "...");
			this.morseTable.put('T', "-");
			this.morseTable.put('U', "..-");
			this.morseTable.put('V', "...-");
			this.morseTable.put('W', ".--");
			this.morseTable.put('X', "-..-");
			this.morseTable.put('Y', "-.--");
			this.morseTable.put('Z', "--..");
			this.morseTable.put('1', ".----");
			this.morseTable.put('2', "..---");
			this.morseTable.put('3', "...--");
			this.morseTable.put('4', "....-");
			this.morseTable.put('5', ".....");
			this.morseTable.put('6', "-....");
			this.morseTable.put('7', "--...");
			this.morseTable.put('8', "---..");
			this.morseTable.put('9', "----.");
			this.morseTable.put('0', "-----");

			// Pontuations.
			this.morsePonctuationTable.put('.', ".-.-.-");
			this.morsePonctuationTable.put(',', "--..--");
			this.morsePonctuationTable.put('?', "..--..");
			this.morsePonctuationTable.put('\'', ".----.");
			this.morsePonctuationTable.put('!', "-.-.--");
			this.morsePonctuationTable.put('/', "-..-.");
			this.morsePonctuationTable.put('(', "-.--.");
			this.morsePonctuationTable.put(')', "-.--.-");
			this.morsePonctuationTable.put('&', ".-...");
			this.morsePonctuationTable.put(':', "----...");
			this.morsePonctuationTable.put(';', "-.-.-.");
			this.morsePonctuationTable.put('=', "-...-");
			this.morsePonctuationTable.put('-', "-....-");
			this.morsePonctuationTable.put('_', "..--.-");
			this.morsePonctuationTable.put('\"', ".-..-.");
			this.morsePonctuationTable.put('$', "...-..-");
			this.morsePonctuationTable.put('@', ".--.-.");

			Set<Entry<Character, String>> morseSet = this.morseTable.entrySet();
			Iterator<Entry<Character, String>> morseSetIterator = morseSet
					.iterator();
			while (morseSetIterator.hasNext()) {
				Entry<Character, String> entry = morseSetIterator.next();
				this.morseTableInverse.put(entry.getValue(), entry.getKey());
			}

			Set<Entry<Character, String>> morsePonctuationSet = this.morsePonctuationTable
					.entrySet();
			Iterator<Entry<Character, String>> morsePonctuationSetIterator = morsePonctuationSet
					.iterator();
			while (morsePonctuationSetIterator.hasNext()) {
				Entry<Character, String> entry = morsePonctuationSetIterator
						.next();
				this.morsePonctuationTableInverse.put(entry.getValue(),
						entry.getKey());
			}

		}

		this.charactersChanged = 0;

		StringBuffer output = isCheckBox2() ? fromMorseToText(text)
				: fromTextToMorse(text);

		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_MORSE_COMMAND_LINE_HELP);
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

		return Text.get(Text.FILTER_MORSE_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_MORSE_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Morse"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_MORSE_REPORT,
				String.valueOf(this.charactersChanged));
	}

}
