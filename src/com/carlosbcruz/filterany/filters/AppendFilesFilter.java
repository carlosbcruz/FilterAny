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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.carlosbcruz.filterany.BinaryFilesFilter;
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
import com.carlosbcruz.util.FileSupport;
import com.carlosbcruz.util.LineTokenizer;

/**
 * Append all files into a single file.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class AppendFilesFilter extends FilterAdapter implements CommandLine,
		Example, BinaryFilesFilter, SpecialBehavior {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_APPENDFILESFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_APPENDFILESFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_APPENDFILESFILTER_INSTRUCTIONS);
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
	 * @see com.carlosbcruz.util.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_APPENDFILESFILTER_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsRequired()
	 */
	@Override
	public FilterControls[] getControls() {

		return new FilterControls[] { FilterControls.TARGET_FILE_OR_DIRECTORY_1 };
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
				.get(Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_TOOLTIP_1_TOOLTIP) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.BinaryFilesFilter#filter(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	public String filter(String mainInput, String auxiliarInput)
			throws FilterException {

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {

			return new String();
		}

		String target = getTargetFileOrDirectory1();

		if (target == null || "".equals(target.trim())) { //$NON-NLS-1$

			throw new FilterException(
					Text.get(Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_1));
		}

		File targetFileParameter = new File(target);

		if (targetFileParameter.isDirectory()) {

			String targetDirectory = targetFileParameter.getAbsolutePath();

			targetFileParameter = new File(
					FileSupport.addDirectorySymbolAtTheEnd(targetDirectory)
							+ Text.get(Text.FILTER_APPENDFILESFILTER_TARGET_FILE_NAME));

		} else {

			if (targetFileParameter.isFile()) {

				if (targetFileParameter.exists()) {

					if (!targetFileParameter.delete()) {

						throw new FilterException(
								Text.get(
										Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2,
										Text.get(Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2_A)));
					}
				}

			}
		}

		try {

			if (!targetFileParameter.createNewFile()) {

				throw new FilterException(
						Text.get(
								Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2,
								Text.get(Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2_B)));
			}

		} catch (IOException exception) {

			throw new FilterException(
					Text.get(
							Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2,
							exception.getMessage()));
		}

		FileOutputStream outputStream = null;
		try {

			outputStream = new FileOutputStream(targetFileParameter);

		} catch (FileNotFoundException exception) {

			throw new FilterException(
					Text.get(
							Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2,
							exception.getMessage()));

		}
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

		StringBuffer executionResult = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(new StringBuffer(mainInput));
		while (tokenizer.hasMoreTokens()) {

			String line = tokenizer.nextToken();

			File sourceFile = new File(line);

			if (!sourceFile.isFile()) {

				continue;
			}

			if (!sourceFile.exists()) {

				continue;
			}

			// It is a file and it exists.

			FileInputStream stream = null;

			try {

				stream = new FileInputStream(sourceFile);

			} catch (FileNotFoundException exception) {

				executionResult.append(Text.get(
						Text.FILTER_APPENDFILESFILTER_SOURCE_FILE_READ_PROBLEM,
						sourceFile.getAbsoluteFile().toString(),
						exception.getMessage())
						+ getNewLine());
				continue;
			}

			DataInputStream data = new DataInputStream(stream);

			byte buffer[] = new byte[1024];

			try {

				int bytesRead = data.read(buffer);

				while (bytesRead > 0) {

					if (bytesRead > 0) {
						dataOutputStream.write(buffer, 0, bytesRead);
					}
					bytesRead = data.read(buffer);
				}

			} catch (IOException exception) {

				executionResult.append(Text.get(
						Text.FILTER_APPENDFILESFILTER_SOURCE_FILE_READ_PROBLEM,
						sourceFile.getAbsoluteFile().toString(),
						exception.getMessage())
						+ getNewLine());
				continue;

			}

			try {

				data.close();
				stream.close();

			} catch (IOException exception) {

				executionResult.append(Text.get(
						Text.FILTER_APPENDFILESFILTER_SOURCE_FILE_READ_PROBLEM,
						sourceFile.getAbsoluteFile().toString(),
						exception.getMessage())
						+ getNewLine());
				continue;

			}

			executionResult.append(Text.get(
					Text.FILTER_APPENDFILESFILTER_SOURCE_SUCCESS,
					sourceFile.getName())
					+ getNewLine());

		}

		try {

			outputStream.close();
			dataOutputStream.close();

		} catch (IOException exception) {

			throw new FilterException(
					Text.get(
							Text.FILTER_APPENDFILESFILTER_TARGET_FILE_OR_DIRECTORY_1_EXCEPTION_2,
							exception.getMessage()));

		}

		return executionResult.toString();

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

		return Text.get(Text.FILTER_APPENDFILESFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return new String();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new Behavior[] {
				Behavior.DO_NOT_SHOW_TARGET_DIRECTORY,
				Behavior.BINARY_RECEIVE_FULL_FILE_LIST,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.util.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "App"; //$NON-NLS-1$
	}

}
