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
import java.util.StringTokenizer;

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterException;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Calculates a knapsack problem.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class KnapSackFilter extends FilterAdapter implements SpecialBehavior,
		Example, CommandLine {

	private static final long serialVersionUID = 1L;

	private List<Sack> sacks = new ArrayList<>();
	private List<LineItem> lineItems = new ArrayList<>();

	/**
	 * The bean to store a line item.
	 */
	public class LineItem implements Serializable {

		private static final long serialVersionUID = 1L;

		// An internal identification number.
		private int identification = 0;
		// The size of the item.
		private int size = 0;
		// The description of the item.
		private String description = new String();

		/**
		 * Default constructor.
		 */
		public LineItem() {

			super();
		}

		/**
		 * @param int identificationParameter An internal identification number.
		 * @param int sizeParameter The size of the item.
		 * @param String
		 *            descriptionParameter The description of the item.
		 */
		public LineItem(int identificationParameter, int sizeParameter,
				String descriptionParameter) {

			super();

			this.identification = identificationParameter;
			this.size = sizeParameter;
			this.description = descriptionParameter;
		}

		/**
		 * Provide: An internal identification number.
		 * 
		 * @return identification An internal identification number.
		 */
		public int getIdentification() {

			return this.identification;
		}

		/**
		 * Set: An internal identification number.
		 * 
		 * @param identification
		 *            An internal identification number.
		 */
		public void setIdentification(int identificationParameter) {

			this.identification = identificationParameter;
		}

		/**
		 * Provide: The size of the item.
		 * 
		 * @return size The size of the item.
		 */
		public int getSize() {

			return this.size;
		}

		/**
		 * Set: The size of the item.
		 * 
		 * @param size
		 *            The size of the item.
		 */
		public void setSize(int sizeParameter) {

			this.size = sizeParameter;
		}

		/**
		 * Provide: The description of the item.
		 * 
		 * @return description The description of the item.
		 */
		public String getDescription() {

			return this.description;
		}

		/**
		 * Set: The description of the item.
		 * 
		 * @param description
		 *            The description of the item.
		 */
		public void setDescription(String descriptionParameter) {

			this.description = descriptionParameter;
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

			out.append("LineItem [\n");

			out.append("\tidentification=" + this.identification + ",\n");
			out.append("\tsize=" + this.size + ",\n");
			out.append("\tdescription=" + this.description + "]\n");

			return out.toString();
		}

	}

	/**
	 * A bean representing a Sack.
	 */
	public class Sack implements Serializable {

		private static final long serialVersionUID = 1L;

		// The size of the sack.
		private Integer size = new Integer(0);
		// The list of items inserted into this sack
		private List<LineItem> items = new ArrayList<>();

		/**
		 * Default constructor.
		 */
		public Sack() {

			super();
		}

		/**
		 * @param Integer
		 *            sizeParameter The size of the sack.
		 * @param List
		 *            itemsParameter The list of items inserted into this sack
		 */
		public Sack(Integer sizeParameter, List<LineItem> itemsParameter) {

			super();

			this.size = sizeParameter;
			this.items = itemsParameter;
		}

		/**
		 * Provide: The size of the sack.
		 * 
		 * @return size The size of the sack.
		 */
		public Integer getSize() {

			return this.size;
		}

		/**
		 * Set: The size of the sack.
		 * 
		 * @param size
		 *            The size of the sack.
		 */
		public void setSize(Integer sizeParameter) {

			this.size = sizeParameter;
		}

		/**
		 * Provide: The list of items inserted into this sack
		 * 
		 * @return items The list of items inserted into this sack
		 */
		public List<LineItem> getItems() {

			return this.items;
		}

		/**
		 * Set: The list of items inserted into this sack
		 * 
		 * @param items
		 *            The list of items inserted into this sack
		 */
		public void setItems(List<LineItem> itemsParameter) {

			this.items = itemsParameter;
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

			out.append("Sack [\n");

			out.append("\tsize=" + this.size + ",\n");
			out.append("\titems=" + this.items + "]\n");

			return out.toString();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_KNAPSACK_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_KNAPSACK_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_KNAPSACK_INSTRUCTIONS);
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

		return new FilterControls[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {};
	}

	/**
	 * Reads the input text and parse the knapsack problem data.
	 * 
	 * @param input
	 *            The input text.
	 * @throws FilterException
	 *             If there is a problem parsing the data.
	 */
	private void readData(StringBuffer input) throws FilterException {

		LineTokenizer tokenizer = new LineTokenizer(input);

		boolean firstLine = true;
		int lineCounter = 0;
		int lineItemCounter = 0;
		while (tokenizer.hasMoreTokens()) {

			String line = tokenizer.nextToken().trim();
			lineCounter++;

			if (line.length() == 0) {
				continue;
			}

			if (firstLine) {

				StringTokenizer sacksTokenizer = new StringTokenizer(line);

				while (sacksTokenizer.hasMoreElements()) {

					String sackSizeText = sacksTokenizer.nextToken();

					Integer sackSize = Integer.valueOf(0);
					try {
						sackSize = Integer.valueOf(Integer
								.parseInt(sackSizeText));
					} catch (NumberFormatException e) {
						throw new FilterException(Text.get(
								Text.FILTER_KNAPSACK_ERROR_1, sackSizeText,
								String.valueOf(lineCounter)));
					}

					if (sackSize.intValue() > 10000000) {
						throw new FilterException(Text.get(
								Text.FILTER_KNAPSACK_ERROR_5, sackSizeText));
					}

					Sack sack = new Sack();
					sack.setSize(sackSize);
					this.sacks.add(sack);
				}

				firstLine = false;

			} else {

				// Find the first space.
				int spacePosition = -1;
				for (int i = 0; i < line.length(); i++) {
					if (Character.isSpaceChar(line.charAt(i))) {
						spacePosition = i;
						break;
					}
				}

				if (spacePosition == -1) {
					throw new FilterException(Text.get(
							Text.FILTER_KNAPSACK_ERROR_2,
							String.valueOf(lineCounter)));
				}

				String sizeText = line.substring(0, spacePosition);

				int size = -1;
				try {
					size = Integer.parseInt(sizeText);
				} catch (NumberFormatException e) {
					throw new FilterException(Text.get(
							Text.FILTER_KNAPSACK_ERROR_2, sizeText,
							String.valueOf(lineCounter)));
				}

				if (size > 10000000) {
					throw new FilterException(Text.get(
							Text.FILTER_KNAPSACK_ERROR_6, sizeText,
							String.valueOf(lineCounter)));
				}

				lineItemCounter++;

				LineItem item = new LineItem(lineItemCounter, size,
						line.substring(spacePosition));
				this.lineItems.add(item);
			}

		}

	}

	/**
	 * Calculate one single knapsack problem.
	 * 
	 * @param sack
	 *            The sack definition.
	 * @throws FilterException
	 *             If there is any problem.
	 */
	private void calculateSingleSack(Sack sack) throws FilterException {
		ExceptionSupport.handleException("This filter is only on full version./Este filtro é somente na versão completa.");
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

		this.sacks = new ArrayList<>();
		this.lineItems = new ArrayList<>();

		StringBuffer output = new StringBuffer();

		readData(text);

		if (this.sacks.isEmpty()) {

			return new StringBuffer();
		}

		if (this.lineItems.size() > 1000) {
			throw new FilterException(Text.get(Text.FILTER_KNAPSACK_ERROR_7,
					String.valueOf(this.lineItems.size())));
		}

		for (Sack sack : this.sacks) {

			calculateSingleSack(sack);
		}

		output.append(Text.get(Text.FILTER_KNAPSACK_OUTPUT_1) + getNewLine()
				+ getNewLine());

		for (Sack sack : this.sacks) {

			int used = 0;
			for (LineItem item : sack.getItems()) {
				used += item.getSize();
			}

			output.append(Text.get(Text.FILTER_KNAPSACK_OUTPUT_2,
					String.valueOf(sack.getSize()), String.valueOf(used))
					+ getNewLine() + getNewLine());

			for (LineItem item : sack.getItems()) {
				output.append(item.getDescription().trim() + getNewLine());
			}
			output.append(getNewLine());

		}

		output.append(Text.get(Text.FILTER_KNAPSACK_OUTPUT_3) + getNewLine()
				+ getNewLine());
		for (LineItem item : this.lineItems) {
			output.append(item.getDescription().trim() + getNewLine());
		}
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

		return Text.get(Text.FILTER_KNAPSACK_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_KNAPSACK_EXAMPLE_OUTPUT);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Knap"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
