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

import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Generates a Java method that produces the text provided.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class JavaToMethodOutputFilter extends FilterAdapter implements
		SpecialBehavior, Example {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_JAVATOMETHODOUTPUTFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_JAVATOMETHODOUTPUTFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_JAVATOMETHODOUTPUTFILTER_INSTRUCTIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getFilterLevel()
	 */
	@Override
	public FilterLevel getFilterLevel() {

		return FilterLevel.ADVANCED_TECHNICAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsRequired()
	 */
	@Override
	public FilterControls[] getControls() {

		return new FilterControls[] { FilterControls.CHECK_BOX_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text
				.get(Text.FILTER_JAVATOMETHODOUTPUTFILTER_CHECKBOX_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_JAVATOMETHODOUTPUTFILTER_CHECKBOX_1_TOOLTIP) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@SuppressWarnings("nls")
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText) {

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		StringBuffer output = new StringBuffer();

		// Generate initial part of the method.
		output.append("\t/**" + getNewLine());
		output.append("\t * Generates the text." + getNewLine());
		output.append("\t * @return The text." + getNewLine());
		output.append("\t */" + getNewLine());
		output.append("\tpublic static String generateText() {" + getNewLine());
		output.append(getNewLine());

		// Count the number of characters approximatelly.
		LineTokenizer temporaryTokenizer = new LineTokenizer(text);
		int size = 0;
		while (temporaryTokenizer.hasMoreTokens()) {

			String token = temporaryTokenizer.nextToken();

			for (int i = 0; i < token.length(); i++) {
				if ('\"' == token.charAt(i)) {
					size++;
				}
			}
			size += token.length() + 5; // Add an extra 5 to be have extra
			// space.
		}

		// Generate the string buffer.
		output.append("\t\tStringBuffer output = new StringBuffer(" + size
				+ ");" + getNewLine());

		// Generate the core method code.
		boolean insideText = false;
		boolean foundSlash = false;
		LineTokenizer tokenizer = new LineTokenizer(text);
		while (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();

			output.append("\t\toutput.append(\"");

			if (isCheckBox1()) {

				for (int i = 0; i < token.length(); i++) {

					if ('\"' == token.charAt(i)) {

						if (foundSlash) {

							foundSlash = false;
							output.append("\\\\\"");

						} else {

							output.append("\\\"");
						}

						insideText = !insideText;

					} else {

						if ('\\' == token.charAt(i)) {

							if (insideText) {

								if (foundSlash) {

									foundSlash = false;
									output.append("\\\\\\");
								} else {
									foundSlash = true;
									output.append("\\");
								}
							} else {

								output.append("\\\\");
							}

						} else {

							output.append(token.charAt(i));
						}
					}
				}

			} else {

				for (int i = 0; i < token.length(); i++) {

					if ('\"' == token.charAt(i)) {

						output.append("\\\"");
					} else {

						if ('\\' == token.charAt(i)) {

							output.append("\\\\");

						} else {

							output.append(token.charAt(i));
						}
					}

				}

			}

			output.append("\\n\");" + getNewLine());
		}

		output.append(getNewLine());
		output.append("\t\treturn output.toString();" + getNewLine());
		output.append(getNewLine());
		output.append("\t}" + getNewLine());

		return output;
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
				.get(Text.FILTER_JAVATOMETHODOUTPUTFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_JAVATOMETHODOUTPUTFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new SpecialBehavior.Behavior[] {
				SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT,
				Behavior.ACCEPT_AUTOMATIC_BEHAVIOR,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}
}
