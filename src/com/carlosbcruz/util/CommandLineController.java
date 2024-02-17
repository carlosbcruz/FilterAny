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

package com.carlosbcruz.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Controls the interaction with the command line.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class CommandLineController {

	private static int MAX_BUFFER = 10000;
	private CommandLineListener listener;

	/**
	 * Constructor.
	 * 
	 * @param listener
	 *            The listener to command line commands.
	 */
	public CommandLineController(CommandLineListener listener) {

		this.listener = listener;

		Out.println(listener.getAvailableCommandsDescription());
	}

	/**
	 * Monitor the input from command line.
	 */
	public void monitorCommandLine() {

		byte[] command = new byte[MAX_BUFFER];
		int bytesRead = 0;

		try {

			// While there are commands to be read.
			while ((bytesRead = System.in.read(command)) != -1) {

				// Verify if typed text is greater than buffer.
				if (bytesRead > MAX_BUFFER) {
					ExceptionSupport.handleException(InternalResource
							.get(InternalResource.EXCEPTION_BUFFER));
					continue;
				}

				String text = new String(command, 0, bytesRead);

				// Get the words typed.
				StringTokenizer tokenizer = new StringTokenizer(text);
				boolean isFirstToken = true;
				String firstToken = null;
				List<String> argumentsList = new ArrayList<>();
				while (tokenizer.hasMoreTokens()) {

					String nextToken = tokenizer.nextToken().toUpperCase();
					if (isFirstToken) {
						firstToken = nextToken;
						isFirstToken = false;
					} else {
						argumentsList.add(nextToken);
					}
				}

				// Call listener with arguments.
				String arguments[] = new String[argumentsList.size()];
				argumentsList.toArray(arguments);
				this.listener.executeCommand(firstToken, arguments);

				if ("QUIT".equals(firstToken)) { //$NON-NLS-1$
					break;
				}
			}

			this.listener.executeCommand("QUIT", new String[0]); //$NON-NLS-1$
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
