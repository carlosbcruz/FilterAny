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

package com.carlosbcruz.filterany.configuration;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.carlosbcruz.filterany.FilterAnyEncoding;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.util.ExceptionSupport;

/**
 * Interface to configure the encoding.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
class FilterAnyConfigurationEncodingInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	// Input encoding selection.
	static private JComboBox<String> inputEncodingList = null;
	// Output encoding selection.
	static private JComboBox<String> outputEncodingList = null;

	// Indicate which is the last input encoding selection.
	static private int lastInputEncodingSelection = 0;
	// Indicate which is the last output encoding selection.
	static private int lastOutputEncodingSelection = 0;

	/**
	 * Constructor.
	 */
	FilterAnyConfigurationEncodingInterface() {

		// Create a encoding selection.
		setBorder(BorderFactory.createTitledBorder(Text
				.get(Text.CONFIGURATION_ENCODING_TITLE)));

		JPanel internalEncodingPanel = new JPanel();
		internalEncodingPanel.setLayout(new GridLayout(2, 2));

		internalEncodingPanel.add(new JLabel(Text
				.get(Text.CONFIGURATION_INPUT_ENCODING_PARAMETER)));

		// Options to select the input encoding.
		String inputEncodingSelectionList[] = null;
		// Options to select the output encoding.
		String outputEncodingSelectionList[] = null;

		String encodings[] = FilterAnyEncoding.getEncodings();

		inputEncodingSelectionList = new String[encodings.length + 2];
		inputEncodingSelectionList[0] = Text
				.get(Text.CONFIGURATION_INPUT_ENCODING_DO_NOT_SET);
		inputEncodingSelectionList[1] = Text
				.get(Text.CONFIGURATION_INPUT_ENCODING_DETECT);

		outputEncodingSelectionList = new String[encodings.length + 1];
		outputEncodingSelectionList[0] = Text
				.get(Text.CONFIGURATION_INPUT_ENCODING_DO_NOT_SET);

		for (int i = 0; i < encodings.length; i++) {

			inputEncodingSelectionList[2 + i] = encodings[i];
			outputEncodingSelectionList[1 + i] = encodings[i];
		}

		inputEncodingList = new JComboBox<>(inputEncodingSelectionList);
		outputEncodingList = new JComboBox<>(outputEncodingSelectionList);

		inputEncodingList.setToolTipText(Text
				.get(Text.CONFIGURATION_INPUT_ENCODING_TOOLTIP));
		outputEncodingList.setToolTipText(Text
				.get(Text.CONFIGURATION_OUTPUT_ENCODING_TOOLTIP));

		if (FilterAnyConfigurationEncoding.DO_NOT_SET
				.equals(FilterAnyConfigurationEncoding
						.getCurrentInputEncoding())) {
			lastInputEncodingSelection = 0;
		} else {

			if (FilterAnyConfigurationEncoding.DETECT
					.equals(FilterAnyConfigurationEncoding
							.getCurrentInputEncoding())) {
				lastInputEncodingSelection = 1;
			} else {

				int encodingFound = -1;

				for (int i = 0; i < encodings.length; i++) {

					if (encodings[i].equals(FilterAnyConfigurationEncoding
							.getCurrentInputEncoding())) {
						encodingFound = i + 2;
					}
				}

				if (encodingFound == -1) {
					ExceptionSupport
							.handleException(
									null,
									Text.get(Text.CONFIGURATION_INPUT_ENCODING_INTERNAL_PROBLEM));

					encodingFound = 0;
					FilterAnyConfigurationEncoding
							.setInputEncoding(FilterAnyConfigurationEncoding.DO_NOT_SET);

				}

				lastInputEncodingSelection = encodingFound;
			}
		}

		if (FilterAnyConfigurationEncoding.DO_NOT_SET
				.equals(FilterAnyConfigurationEncoding
						.getCurrentOutputEncoding())) {

			lastOutputEncodingSelection = 0;

		} else {

			int encodingFound = -1;

			for (int i = 0; i < encodings.length; i++) {

				if (encodings[i].equals(FilterAnyConfigurationEncoding
						.getCurrentOutputEncoding())) {
					encodingFound = i + 1;
				}
			}

			if (encodingFound == -1) {
				ExceptionSupport
						.handleException(
								null,
								Text.get(Text.CONFIGURATION_INPUT_ENCODING_INTERNAL_PROBLEM));

				encodingFound = 0;
				FilterAnyConfigurationEncoding
						.setOutputEncoding(FilterAnyConfigurationEncoding.DO_NOT_SET);

			}

			lastOutputEncodingSelection = encodingFound;

		}

		inputEncodingList.setSelectedIndex(lastInputEncodingSelection);
		outputEncodingList.setSelectedIndex(lastOutputEncodingSelection);

		internalEncodingPanel.add(inputEncodingList);

		internalEncodingPanel.add(new JLabel(Text
				.get(Text.CONFIGURATION_OUTPUT_ENCODING_PARAMETER)));

		internalEncodingPanel.add(outputEncodingList);

		add(internalEncodingPanel);
	}

	/**
	 * Restore the previous input encoding selection.
	 */
	static void restorePreviousEncodingSelection() {

		inputEncodingList.setSelectedIndex(lastInputEncodingSelection);
		outputEncodingList.setSelectedIndex(lastOutputEncodingSelection);
	}

	/**
	 * Apply the input encoding.
	 * 
	 * @return Return true to indicate it is necessary to restart the
	 *         application or false otherwise.
	 */
	static boolean applyEncoding() {

		int inputSelection = inputEncodingList.getSelectedIndex();

		if (inputSelection == 0) {

			FilterAnyConfigurationEncoding
					.setInputEncoding(FilterAnyConfigurationEncoding.DO_NOT_SET);

		} else {

			if (inputSelection == 1) {

				FilterAnyConfigurationEncoding
						.setInputEncoding(FilterAnyConfigurationEncoding.DETECT);

			} else {

				String encodings[] = FilterAnyEncoding.getEncodings();

				FilterAnyConfigurationEncoding
						.setInputEncoding(encodings[inputSelection - 2]);
			}
		}

		lastInputEncodingSelection = inputSelection;

		int outputSelection = outputEncodingList.getSelectedIndex();

		if (outputSelection == 0) {

			FilterAnyConfigurationEncoding
					.setOutputEncoding(FilterAnyConfigurationEncoding.DO_NOT_SET);

		} else {

			String encodings[] = FilterAnyEncoding.getEncodings();

			FilterAnyConfigurationEncoding
					.setOutputEncoding(encodings[outputSelection - 1]);
		}

		lastOutputEncodingSelection = outputSelection;

		return false;
	}
}
