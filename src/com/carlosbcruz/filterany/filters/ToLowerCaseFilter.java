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
import com.carlosbcruz.util.StringSupport;

/**
 * Transform all content into upper case text.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ToLowerCaseFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private static final int NOT_DEFINED = -1;

	private int charactersReplaced = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_TOLOWERCASEFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_TOLOWERCASEFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_TOLOWERCASEFILTER_INSTRUCTIONS);
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
				FilterControls.INPUT_FIELD_2 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NUMBER_POSITION,
				FilterType.NUMBER_POSITION };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.OPTIONAL,
				FilterParameter.OPTIONAL };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text.get(Text.FILTER_TOLOWERCASEFILTER_FIELD_1),
				Text.get(Text.FILTER_TOLOWERCASEFILTER_FIELD_2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_TOLOWERCASEFILTER_FIELD_1_TOOLTIP),
				Text.get(Text.FILTER_TOLOWERCASEFILTER_FIELD_2_TOOLTIP) };
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

		this.charactersReplaced = 0;

		String position1Text = getField1().trim();
		String position2Text = getField2().trim();

		int position1 = NOT_DEFINED;
		int position2 = NOT_DEFINED;

		if (!"".equals(position1Text)) { //$NON-NLS-1$

			try {
				position1 = Integer.parseInt(position1Text);
			} catch (NumberFormatException exception) {
				throw new FilterException(Text.get(
						Text.FILTER_TOLOWERCASEFILTER_EXCEPTION_1,
						position1Text));
			}

			if (position1 < 0) {
				throw new FilterException(Text.get(
						Text.FILTER_TOLOWERCASEFILTER_EXCEPTION_1_1,
						position1Text));
			}
		}

		if (!"".equals(position2Text)) { //$NON-NLS-1$

			try {
				position2 = Integer.parseInt(position2Text);
			} catch (NumberFormatException exception) {
				throw new FilterException(Text.get(
						Text.FILTER_TOLOWERCASEFILTER_EXCEPTION_2,
						position2Text));
			}

			if (position2 < 1) {
				throw new FilterException(Text.get(
						Text.FILTER_TOLOWERCASEFILTER_EXCEPTION_2_1,
						position2Text));
			}
		}

		if (position1 != NOT_DEFINED && position2 != NOT_DEFINED) {

			if (position1 >= position2) {

				throw new FilterException(
						Text.get(Text.FILTER_TOLOWERCASEFILTER_EXCEPTION_3));
			}
		}

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		while (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();

			if (position1 == NOT_DEFINED && position2 == NOT_DEFINED) {

				if (tokenizer.isFoundNewLine()) {
					String converted = token.toLowerCase();
					this.charactersReplaced += StringSupport
							.getNumberOfDifferentCharacters(token, converted);
					output.append(converted + getNewLine());
				} else {
					String converted = token.toLowerCase();
					this.charactersReplaced += StringSupport
							.getNumberOfDifferentCharacters(token, converted);
					output.append(converted);
				}

			} else {

				if (position1 >= 0 && position2 == NOT_DEFINED) {

					if (token.length() > position1) {

						String part1 = token.substring(0, position1);
						String part2 = token
								.substring(position1, position1 + 1);
						String part3 = token.substring(position1 + 1);

						if (tokenizer.isFoundNewLine()) {
							String converted = part2.toLowerCase();
							this.charactersReplaced += StringSupport
									.getNumberOfDifferentCharacters(part2,
											converted);
							output.append(part1 + converted + part3
									+ getNewLine());
						} else {
							String converted = part2.toLowerCase();
							this.charactersReplaced += StringSupport
									.getNumberOfDifferentCharacters(part2,
											converted);
							output.append(part1 + converted + part3);
						}

					} else {

						if (tokenizer.isFoundNewLine()) {
							output.append(token + getNewLine());
						} else {
							output.append(token);
						}
					}

				} else {

					if (position2 >= 1 && position1 == NOT_DEFINED) {

						if (token.length() > position2 - 1) {

							String part1 = token.substring(0, position2 - 1);
							String part2 = token.substring(position2 - 1,
									position2);
							String part3 = token.substring(position2);

							if (tokenizer.isFoundNewLine()) {
								String converted = part2.toLowerCase();
								this.charactersReplaced += StringSupport
										.getNumberOfDifferentCharacters(part2,
												converted);
								output.append(part1 + converted + part3
										+ getNewLine());
							} else {
								String converted = part2.toLowerCase();
								this.charactersReplaced += StringSupport
										.getNumberOfDifferentCharacters(part2,
												converted);
								output.append(part1 + converted + part3);
							}

						} else {

							if (tokenizer.isFoundNewLine()) {
								output.append(token + getNewLine());
							} else {
								output.append(token);
							}
						}

					} else {

						if (position1 >= 0 && position2 >= 1) {

							if (token.length() <= position1) {

								if (tokenizer.isFoundNewLine()) {
									output.append(token + getNewLine());
								} else {
									output.append(token);
								}

							} else {

								if (token.length() > position1
										&& token.length() <= position2) {

									String part1 = token
											.substring(0, position1);
									String part2 = token.substring(position1);

									if (tokenizer.isFoundNewLine()) {
										String converted = part2.toLowerCase();
										this.charactersReplaced += StringSupport
												.getNumberOfDifferentCharacters(
														part2, converted);
										output.append(part1 + converted
												+ getNewLine());
									} else {
										String converted = part2.toLowerCase();
										this.charactersReplaced += StringSupport
												.getNumberOfDifferentCharacters(
														part2, converted);
										output.append(part1 + converted);
									}
								} else {

									String part1 = token
											.substring(0, position1);
									String part2 = token.substring(position1,
											position2);
									String part3 = token.substring(position2);

									if (tokenizer.isFoundNewLine()) {
										String converted = part2.toLowerCase();
										this.charactersReplaced += StringSupport
												.getNumberOfDifferentCharacters(
														part2, converted);
										output.append(part1 + converted + part3
												+ getNewLine());
									} else {
										String converted = part2.toLowerCase();
										this.charactersReplaced += StringSupport
												.getNumberOfDifferentCharacters(
														part2, converted);
										output.append(part1 + converted + part3);
									}
								}
							}
						}
					}
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

		return Text.get(Text.FILTER_TOLOWERCASEFILTER_COMMAND_LINE_HELP);
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

		return Text.get(Text.FILTER_TOLOWERCASEFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_TOLOWERCASEFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "ToLo"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_TOLOWERCASEFILTER_REPORT,
				String.valueOf(this.charactersReplaced));
	}
}
