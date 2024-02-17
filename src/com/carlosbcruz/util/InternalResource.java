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

/**
 * Provides a singleton for a simple internationalization feature only for the
 * com.carlosbcruz.util classes.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class InternalResource {

	private static ResourceUtility resourceUtility = null;

	/**
	 * @param key
	 *            The key to search for.
	 * @return The content.
	 */
	protected static String get(String key) {
		if (resourceUtility == null) {
			resourceUtility = new ResourceUtility();
		}
		return resourceUtility.get(key);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @return The content.
	 */
	public static String get(String key, String arg0) {

		if (resourceUtility == null) {
			resourceUtility = new ResourceUtility();
		}
		return resourceUtility.get(key, arg0);
	}

	/**
	 * @param key
	 *            The key to search for.
	 * @param arg0
	 *            Argument 0
	 * @param arg1
	 *            Argument 1
	 * @return The content.
	 */
	public static String get(String key, String arg0, String arg1) {

		if (resourceUtility == null) {
			resourceUtility = new ResourceUtility();
		}
		return resourceUtility.get(key, arg0, arg1);
	}

	// Current available contents
	protected static final String OPEN_FILE = "file.open"; //$NON-NLS-1$
	protected static final String SAVE_FILE = "file.save"; //$NON-NLS-1$
	protected static final String CONFIRM = "confirm"; //$NON-NLS-1$
	protected static final String CANCEL = "cancel"; //$NON-NLS-1$
	protected static final String ALERT = "alert"; //$NON-NLS-1$
	protected static final String OVERWRITE_FILE_QUESTION = "file.overwrite.question"; //$NON-NLS-1$
	protected static final String EXECUTION = "execution"; //$NON-NLS-1$
	protected static final String START_DATE = "start.date"; //$NON-NLS-1$
	protected static final String END_DATE = "end.date"; //$NON-NLS-1$
	protected static final String SECONDS = "seconds"; //$NON-NLS-1$
	protected static final String MINUTES = "minutes"; //$NON-NLS-1$
	protected static final String TASK_INTERRUPTED = "task.interrupted"; //$NON-NLS-1$
	protected static final String TIME_ELAPSED = "time.elapsed"; //$NON-NLS-1$
	protected static final String TIME_REMAINING = "time.remaining"; //$NON-NLS-1$
	protected static final String INDICATED_PROGRESS = "progress.indication"; //$NON-NLS-1$
	protected static final String UNITS = "progress.units"; //$NON-NLS-1$
	protected static final String TASK_FINITE_WITH_INFINITE_MESSAGE = "task.mix.finite.infinite.message"; //$NON-NLS-1$
	protected static final String TASK_INFINITE_WITH_FINITE_MESSAGE = "task.mix.infinite.finite.message"; //$NON-NLS-1$
	protected static final String TASK_INTERRUPTION_CONFIRMATION_TITLE = "interrupt.confirmation.title"; //$NON-NLS-1$
	protected static final String TASK_INTERRUPTION_CONFIRMATION = "interrupt.confirmation"; //$NON-NLS-1$
	protected static final String INTERRUPT_ALL_TASKS = "interrupt.all.tasks"; //$NON-NLS-1$
	protected static final String WINDOW_CLOSE_CONFIRMATION_TITLE = "window.close.confirmation.title"; //$NON-NLS-1$
	protected static final String PROGRESS_LIMIT = "progress.limits"; //$NON-NLS-1$
	protected static final String TASK_INTERRUPTED_SHORT_MESSAGE = "task.interrupted.short.message"; //$NON-NLS-1$
	protected static final String TASK_FINISHED = "task.finished"; //$NON-NLS-1$
	protected static final String TASK_EXCEPTION = "task.exception"; //$NON-NLS-1$
	protected static final String TASK_EXCEPTION_TITLE = "task.exception.title"; //$NON-NLS-1$
	protected static final String RESULT_EXCEPTION = "result.exception"; //$NON-NLS-1$
	protected static final String STOP = "stop"; //$NON-NLS-1$
	protected static final String FILE_BROWSE = "file.browse"; //$NON-NLS-1$
	protected static final String CONTINUE = "continue"; //$NON-NLS-1$
	protected static final String FILE_SELECTION_PROPERTY = "file.selection.property"; //$NON-NLS-1$
	protected static final String FILE_CONTINUE_SELECTION = "file.continue.selection"; //$NON-NLS-1$
	protected static final String FILE_SELECTION = "file.selection"; //$NON-NLS-1$
	protected static final String DIRECTORY_SELECTION_PROPERTY = "directory.selection.property"; //$NON-NLS-1$
	protected static final String DIRECTORY_CONTINUE_SELECTION = "directory.continue.selection"; //$NON-NLS-1$
	protected static final String DIRECTORY_SELECTION = "directory.selection"; //$NON-NLS-1$
	protected static final String STEPS_SEPARATION = "steps.separation"; //$NON-NLS-1$
	protected static final String EXCEPTION_OCCURRED = "exception.occurred"; //$NON-NLS-1$
	protected static final String FILE_SERIALIZATION_DESCRIPTION = "file.serialization.description"; //$NON-NLS-1$
	protected static final String FILE_TOO_BIG = "file.too.big"; //$NON-NLS-1$
	protected static final String IMAGE_FILE_NOT_FOUND = "image.file.not.found"; //$NON-NLS-1$
	protected static final String EXCEPTION_BUFFER = "exception.buffer"; //$NON-NLS-1$
	protected static final String EXCEPTION_COULD_NO_DELETE = "exception.could.not.delete"; //$NON-NLS-1$
	protected static final String EXCEPTION_MISSING_FILE_NAME = "exception.missing.file.name"; //$NON-NLS-1$
	protected static final String ERROR_PRINTINTG_STACKTRACE = "error.printintg.stacktrace"; //$NON-NLS-1$
	protected static final String NETWORK_EXCEPTION = "network.exception"; //$NON-NLS-1$

}
