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

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.Report;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Format a XML if it is valid.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class XMLFormatterFilter extends FilterAdapter implements CommandLine,
		Example, SpecialBehavior, Report {

	private static final long serialVersionUID = 1L;

	// If an error happens.
	private static String errorMessage = new String();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_XMLFORMATTERFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_XMLFORMATTERFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_XMLFORMATTERFILTER_INSTRUCTIONS);
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

		return new String[] { Text.get(Text.FILTER_XMLFORMATTERFILTER_FIELD1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_XMLFORMATTERFILTER_FIELD1_TOOLTIP) };
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

	/**
	 * Error handler.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	static class SimpleErrorHandler implements ErrorHandler {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public void warning(SAXParseException exception) throws SAXException {

			errorMessage = exception.getMessage();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public void error(SAXParseException exception) throws SAXException {

			errorMessage = exception.getMessage();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public void fatalError(SAXParseException exception) throws SAXException {

			errorMessage = exception.getMessage();
		}
	}

	/**
	 * Validate if the XML is valid.
	 */
	private static void validate(String inputXML) {

		errorMessage = new String();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setValidating(false);
		factory.setNamespaceAware(true);

		DocumentBuilder builder;
		try {

			builder = factory.newDocumentBuilder();

			builder.setErrorHandler(new SimpleErrorHandler());

			try {

				InputSource is = new InputSource(new StringReader(inputXML));
				builder.parse(is);

			} catch (SAXException exception) {

				errorMessage = exception.getMessage();

			} catch (IOException exception) {

				errorMessage = exception.getMessage();

			}

		} catch (ParserConfigurationException exception) {

			errorMessage = exception.getMessage();

		}
	}

	/**
	 * Format a XML file.
	 * 
	 * @param input
	 *            The input XML.
	 * @param numberOfSpacesToIndent
	 *            The number of spaces used to indent the tags.
	 * @return The indented XML.
	 */
	public static String prettyFormat(String input, int numberOfSpacesToIndent) {

		errorMessage = new String();

		try {

			Source xmlInput = new StreamSource(new StringReader(input));

			StringWriter stringWriter = new StringWriter();

			StreamResult xmlOutput = new StreamResult(stringWriter);

			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", String //$NON-NLS-1$
							.valueOf(numberOfSpacesToIndent));

			transformer.transform(xmlInput, xmlOutput);

			return xmlOutput.getWriter().toString();

		} catch (Exception exception) {

			errorMessage = exception.getMessage();
		}

		return Text.get(Text.FILTER_XMLFORMATTERFILTER_EXCEPTION, errorMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText) {

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		int numberOfSpaces = 8;
		String numberOfSpacesParameter = getField1();
		try {
			numberOfSpaces = Integer.parseInt(numberOfSpacesParameter);
		} catch (NumberFormatException exception) {
			numberOfSpaces = 8;
		}

		StringBuffer output = new StringBuffer();

		StringBuffer inputText = new StringBuffer();
		LineTokenizer tokenizer = new LineTokenizer(text);
		while (tokenizer.hasMoreTokens()) {
			inputText.append(tokenizer.nextToken().trim());
		}

		validate(inputText.toString());

		if (!"".equals(errorMessage)) { //$NON-NLS-1$
			output.append(Text
					.get(Text.FILTER_XMLFORMATTERFILTER_EXCEPTION_INVALID_XML));
		} else {
			output.append(prettyFormat(inputText.toString(), numberOfSpaces));
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

		return Text.get(Text.FILTER_XMLFORMATTERFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_XMLFORMATTERFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_XMLFORMATTERFILTER_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "XML"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return "".equals(errorMessage) ? Text //$NON-NLS-1$
				.get(Text.FILTER_XMLFORMATTERFILTER_REPORT_OK) : Text
				.get(Text.FILTER_XMLFORMATTERFILTER_REPORT_ERROR);
	}
}
