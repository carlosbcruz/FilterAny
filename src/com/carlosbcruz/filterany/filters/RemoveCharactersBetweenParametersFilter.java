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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;

/**
 * Remove all contents between parameteres.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class RemoveCharactersBetweenParametersFilter extends FilterAdapter
		implements CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int partsRemoved = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INSTRUCTIONS);
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
				FilterControls.INPUT_FIELD_2, FilterControls.CHECK_BOX_1,
				FilterControls.CHECK_BOX_2 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.ANYTHING, FilterType.ANYTHING,
				FilterType.NOT_RELEVANT, FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
				FilterParameter.REQUIRED, FilterParameter.REQUIRED,
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
				Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INPUT_1),
				Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INPUT_2),
				Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_CHECKBOX_1),
				Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_CHECKBOX_2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INPUT_1_TOOLTIP),
				Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_INPUT_2_TOOLTIP),
				Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_CHECKBOX_1_TOOLTIP),
				Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_CHECKBOX_2_TOOLTIP) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] {
				Behavior.ACCEPT_AUTOMATIC_BEHAVIOR,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}

	class Span {

		private int start = -1;
		private int end = -1;

		public Span(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		/**
		 * @return the start
		 */
		public int getStart() {
			return this.start;
		}

		/**
		 * @param start
		 *            the start to set
		 */
		public void setStart(int start) {
			this.start = start;
		}

		/**
		 * @return the end
		 */
		public int getEnd() {
			return this.end;
		}

		/**
		 * @param end
		 *            the end to set
		 */
		public void setEnd(int end) {
			this.end = end;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@SuppressWarnings("nls")
		@Override
		public String toString() {
			return "Span [start=" + this.start + ", end=" + this.end + "]\n";
		}

	}

	class Landmark implements Comparable<Landmark> {

		private boolean from = false;

		private int position = -1;

		public Landmark(boolean from, int position) {
			super();
			this.from = from;
			this.position = position;
		}

		/**
		 * @return the from
		 */
		public boolean isFrom() {
			return this.from;
		}

		/**
		 * @param from
		 *            the from to set
		 */
		public void setFrom(boolean from) {
			this.from = from;
		}

		/**
		 * @return the position
		 */
		public int getPosition() {
			return this.position;
		}

		/**
		 * @param position
		 *            the position to set
		 */
		public void setPosition(int position) {
			this.position = position;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@SuppressWarnings("nls")
		@Override
		public String toString() {
			return "Landmark [from=" + this.from + ", position="
					+ this.position + "]\n";
		}

		@Override
		public int compareTo(Landmark o) {

			if (this.position < o.getPosition()) {
				return -1;
			}
			if (this.position > o.getPosition()) {
				return 1;
			}
			if (this.from == o.isFrom()) {
				return 0;
			}
			if (this.from) {
				return -1;
			}
			return 1;
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

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		this.partsRemoved = 0;

		String input = text.toString();

		StringBuffer outputBuffer = new StringBuffer();

		String parameterFrom = getField1();
		String parameterTo = getField2();

		if (parameterFrom == null || "".equals(parameterFrom)) { //$NON-NLS-1$
			throw new FilterException(
					Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_REQUIRED_PARAMETER1));
		}

		if (parameterTo == null || "".equals(parameterTo)) { //$NON-NLS-1$
			throw new FilterException(
					Text.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_REQUIRED_PARAMETER2));
		}

		boolean firstOccurrence = isCheckBox1();
		boolean includeParameters = isCheckBox2();

		List<Landmark> landmarks = new ArrayList<>();

		int currentPosition = -1;
		int newPosition = -1;
		do {
			currentPosition = newPosition + 1;

			newPosition = input.indexOf(parameterFrom, currentPosition);

			if (newPosition >= 0) {
				landmarks.add(new Landmark(true, newPosition));
			}

		} while (newPosition != -1);

		currentPosition = -1;
		newPosition = -1;
		do {
			currentPosition = newPosition + 1;

			newPosition = input.indexOf(parameterTo, currentPosition);

			if (newPosition >= 0) {
				landmarks.add(new Landmark(false, newPosition));
			}

		} while (newPosition != -1);

		Collections.sort(landmarks);

		List<Span> spans = new ArrayList<>();

		if (firstOccurrence) {
			Landmark lastLandmark = null;
			for (Landmark currentLandmark : landmarks) {

				if (currentLandmark.isFrom()) {
					if (lastLandmark == null) {
						lastLandmark = currentLandmark;
					}
				} else {

					if (lastLandmark != null) {
						spans.add(new Span(lastLandmark.getPosition(),
								currentLandmark.getPosition()));

						lastLandmark = null;
					}
				}
			}
		} else {
			Landmark lastLandmark = new Landmark(true, 0);
			for (Landmark currentLandmark : landmarks) {

				if (currentLandmark.isFrom()) {
					lastLandmark = currentLandmark;
				} else {
					if (lastLandmark != null) {
						spans.add(new Span(lastLandmark.getPosition(),
								currentLandmark.getPosition()));
						lastLandmark = null;
					}
				}
			}
		}

		if (spans.isEmpty()) {
			outputBuffer.append(input.toString());
		} else {

			boolean firstSpan = true;

			Span lastSpan = new Span(0, 0);
			for (Span span : spans) {

				this.partsRemoved++;

				if (includeParameters) {
					outputBuffer.append(input.substring(lastSpan.getEnd(),
							span.getStart() + parameterFrom.length()));
				} else {
					if (firstSpan) {
						if (span.getStart() == 0) {
							// Do nothing
						} else {
							outputBuffer.append(input.substring(
									lastSpan.getEnd(), span.getStart()));
						}
					} else {
						if (span.getStart() > lastSpan.getEnd()
								+ parameterTo.length()) {
							outputBuffer.append(input.substring(
									lastSpan.getEnd() + parameterTo.length(),
									span.getStart()));
						}
					}
				}
				lastSpan = span;
				firstSpan = false;
			}
			if (includeParameters) {
				outputBuffer.append(input.substring(lastSpan.getEnd()));
			} else {
				outputBuffer.append(input.substring(lastSpan.getEnd()
						+ parameterTo.length()));
			}
		}

		return outputBuffer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text
				.get(Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "RBet"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(
				Text.FILTER_REMOVECHARACTERSBETWEENPARAMETERSFILTER_REPORT,
				String.valueOf(this.partsRemoved));
	}
}
