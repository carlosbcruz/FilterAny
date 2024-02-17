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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
import com.carlosbcruz.filterany.TaskStatus;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.TextBridgeFactory;
import com.carlosbcruz.filterany.TextBridgeFactory.TextBridge.TextInterfaceBean;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.ExceptionSupport;

/**
 * Download content from interent but blocking the interface.
 * 
 * @author Carlos Fernandbo Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class DownloadInternetBlockerFilter extends FilterAdapter implements
		CommandLine, SpecialBehavior, Example, Report {

	private static final long serialVersionUID = 1L;

	private int charactersRead = 0;

	private String url = new String();
	private String proxy = new String();
	private String portParameter = new String();
	private URL finalURL = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_INSTRUCTIONS);
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

		return new FilterControls[] { FilterControls.INPUT_FIELD_1,
				FilterControls.INPUT_FIELD_2, FilterControls.INPUT_FIELD_3 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.ANYTHING, FilterType.ANYTHING,
				FilterType.NUMBER_GENERIC };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.REQUIRED,
				FilterParameter.OPTIONAL, FilterParameter.OPTIONAL };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] {
				Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_1),
				Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_2),
				Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_3) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_1_TOOLTIP),
				Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_2_TOOLTIP),
				Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_FIELD_3_TOOLTIP) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] {
				Behavior.WORK_ONLY_ON_TEXT,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}

	/**
	 * Download the text from internet.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	private class DownloadFilterThread implements Runnable {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@SuppressWarnings({ "synthetic-access" })
		@Override
		public void run() {

			StringBuffer output = new StringBuffer();

			TextBridgeFactory.TextBridge bridge = TextBridgeFactory
					.getInstance();

			TextInterfaceBean textInterfaceShare = bridge
					.createNewInterfaceText();

			textInterfaceShare.setStatus(TaskStatus.RUNNING);

			textInterfaceShare.setSourceFilter(Text
					.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_LABEL));

			try {

				URLConnection connection = DownloadInternetBlockerFilter.this.finalURL
						.openConnection();

				if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
					textInterfaceShare.setStatus(TaskStatus.CANCELED);
					return;
				}

				@SuppressWarnings("resource")
				InputStreamReader inputStream = new InputStreamReader(
						connection.getInputStream());

				if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
					textInterfaceShare.setStatus(TaskStatus.CANCELED);
					return;
				}

				BufferedReader buffer = new BufferedReader(inputStream);

				if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
					textInterfaceShare.setStatus(TaskStatus.CANCELED);
					return;
				}

				int bytes = 0;

				while (true) {

					String nextLine = buffer.readLine();

					if (nextLine != null) {
						bytes += nextLine.length();
					}

					textInterfaceShare.setProgress(Text.get(
							Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_PROGRESS,
							String.valueOf(bytes)));

					if (textInterfaceShare.getStatus() == TaskStatus.REQUEST_CANCEL) {
						textInterfaceShare.setStatus(TaskStatus.CANCELED);
						return;
					}

					if (nextLine != null) {

						output.append(nextLine + getNewLine());

						DownloadInternetBlockerFilter.this.charactersRead += nextLine
								.length();

					} else {

						break;
					}
				}

				inputStream.close();

			} catch (IOException exception) {

				String exceptionText = Text
						.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXCEPTION_CONNECTION,
								exception.getClass().getName() + " " //$NON-NLS-1$
										+ exception.getMessage());
				ExceptionSupport.handleException(null, exceptionText);
			}

			textInterfaceShare.setContent(output);

			textInterfaceShare.setStatus(TaskStatus.COMPLETE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@SuppressWarnings({ "synthetic-access" })
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText)
			throws FilterException {

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		this.charactersRead = 0;

		StringBuffer output = new StringBuffer();
		output.append(text);

		this.url = getField1();
		this.proxy = getField2();
		this.portParameter = getField3();

		if ("".equals(this.url.trim())) { //$NON-NLS-1$
			throw new FilterException(
					Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXCEPTION_PARAMETER));
		}

		int port = 0;
		try {
			port = Integer.parseInt(this.portParameter);
		} catch (NumberFormatException e) {
			port = 0;
		}
		if (port != 0) {
			if (port < 1 || port > 65535) {
				throw new FilterException(
						Text.get(
								Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXCEPTION_PORT,
								String.valueOf(port)));
			}
		}

		if (!this.url.toUpperCase().startsWith("HTTP://")) { //$NON-NLS-1$
			this.url = "http://" + this.url; //$NON-NLS-1$
		}

		try {
			if (!"".equals(this.proxy.trim()) && port != 0) { //$NON-NLS-1$
				this.finalURL = new URL("http", this.proxy, port, this.url); //$NON-NLS-1$
			} else {
				this.finalURL = new URL(this.url);
			}
		} catch (MalformedURLException exception) {
			String exceptionText = Text
					.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXCEPTION_CONNECTION,
							exception.getClass().getName() + " " //$NON-NLS-1$
									+ exception.getMessage());
			return new StringBuffer(exceptionText);
		}

		if (FilterAnyConfiguration.isRunningInGraphicalMode()) {

			Thread downloadThread = new Thread(new DownloadFilterThread());
			downloadThread.start();

		} else {

			try {

				URLConnection connection = this.finalURL.openConnection();

				@SuppressWarnings("resource")
				InputStreamReader inputStream = new InputStreamReader(
						connection.getInputStream());
				BufferedReader buffer = new BufferedReader(inputStream);

				while (true) {

					String nextLine = buffer.readLine();

					if (nextLine != null) {

						output.append(nextLine + getNewLine());

						this.charactersRead += nextLine.length();
					} else {

						break;
					}
				}

				inputStream.close();

			} catch (IOException exception) {

				String exceptionText = Text
						.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXCEPTION_CONNECTION,
								exception.getClass().getName() + " " //$NON-NLS-1$
										+ exception.getMessage());
				return new StringBuffer(exceptionText);
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
				.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_COMMAND_LINE_HELP);
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
				.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text
				.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "DownB"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#isRunInBackground()
	 */
	@Override
	public boolean isRunInBackground() {

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Report#getReportText()
	 */
	@Override
	public String getReportText() {

		return Text.get(Text.FILTER_DOWNLOADINTERNETBLOCKERFILTER_REPORT,
				String.valueOf(this.charactersRead));
	}
}
