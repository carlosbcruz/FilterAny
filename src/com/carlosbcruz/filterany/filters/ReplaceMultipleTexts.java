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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterAnyLogic;
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
import com.carlosbcruz.util.StringSupport.ReplaceResult;

/**
 * Replace multiple contents on the text.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class ReplaceMultipleTexts extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int contentsReplaced = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_REPLACEMULTIPLETEXTS_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_REPLACEMULTIPLETEXTS_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_REPLACEMULTIPLETEXTS_INSTRUCTIONS);
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

		return new FilterType[] { FilterType.ANYTHING };
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

		return new String[] { Text
				.get(Text.FILTER_REPLACEMULTIPLETEXTS_FIELD_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_REPLACEMULTIPLETEXTS_FIELD_1_TOOLTIP) };
	}

	/**
	 * Store the contents to be searched for and replaced by.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class Replace implements Serializable {

		private static final long serialVersionUID = 1L;

		// The content that is going to be search.
		private String from = new String();
		// The content to replace the previous content.
		private String to = new String();

		/**
		 * @param String
		 *            fromParameter The content that is going to be search.
		 * @param String
		 *            toParameter The content to replace the previous content.
		 */
		public Replace(String fromParameter, String toParameter) {

			super();

			this.from = fromParameter;
			this.to = toParameter;
		}

		/**
		 * Provide: The content that is going to be search.
		 * 
		 * @return from The content that is going to be search.
		 */
		public String getFrom() {

			return this.from;
		}

		/**
		 * Provide: The content to replace the previous content.
		 * 
		 * @return to The content to replace the previous content.
		 */
		public String getTo() {

			return this.to;
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

			out.append("Replace [\n");

			out.append("\tfrom=" + this.from + ",\n");
			out.append("\tto=" + this.to + "]\n");

			return out.toString();
		}

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

		this.contentsReplaced = 0;

		StringBuffer output = new StringBuffer();

		List<Replace> replacement = new ArrayList<>();

		String separator = getField1();

		if ("".equals(separator)) { //$NON-NLS-1$

			LineTokenizer auxiliarTokenizer = new LineTokenizer(auxiliarText);
			while (auxiliarTokenizer.hasMoreTokens()) {

				String token = auxiliarTokenizer.nextToken();

				Replace replace = new Replace(token, new String());
				replacement.add(replace);
			}

		} else {

			LineTokenizer auxiliarTokenizer = new LineTokenizer(auxiliarText);
			while (auxiliarTokenizer.hasMoreTokens()) {

				String token = auxiliarTokenizer.nextToken();

				int index = token.indexOf(separator);

				if (index == -1) {

					if (!"".equals(token)) { //$NON-NLS-1$
						Replace replace = new Replace(token, new String());
						replacement.add(replace);
					}

				} else {

					String from = token.substring(0, index);
					String to = token.substring(index + separator.length(),
							token.length());
					to = FilterAnyLogic.replaceSpecialCharacters(to);
					Replace replace = new Replace(from, to);
					replacement.add(replace);
				}
			}

		}

		String[] lines = StringSupport.breakContentInLines(text);

		for (Replace replace : replacement) {

			for (int i = 0; i < lines.length; i++) {

				String currentLine = lines[i];

				ReplaceResult result = StringSupport.replaceExtended(
						currentLine, replace.getFrom(), replace.getTo());

				this.contentsReplaced += result.getNumber();

				lines[i] = result.getText();
			}
		}

		for (int i = 0; i < lines.length; i++) {

			output.append(lines[i] + getNewLine());
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

		return Text.get(Text.FILTER_REPLACEMULTIPLETEXTS_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getAuxiliarInputExample()
	 */
	@Override
	public String getAuxiliarInputExample() {

		return Text
				.get(Text.FILTER_REPLACEMULTIPLETEXTS_EXAMPLE_AUXILIAR_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getMainInputExample()
	 */
	@Override
	public String getMainInputExample() {

		return Text.get(Text.FILTER_REPLACEMULTIPLETEXTS_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_REPLACEMULTIPLETEXTS_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "MRepl"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] { Behavior.WORK_ON_DUAL_PANE,
				Behavior.ACCEPT_AUTOMATIC_BEHAVIOR };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_REPLACEMULTIPLETEXTS_REPORT,
				String.valueOf(this.contentsReplaced));
	}
}
