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

package com.carlosbcruz.filterany;

import java.util.Vector;

/**
 * A singleton that servers as a brige between processes that generates contents
 * for files.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class TextBridgeFactory {

	private static TextBridge processBridge = null;

	private static int taskNumberIdentification = 0;

	/**
	 * Internal class to control text exchanges.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public static class TextBridge {

		private Vector<TextInterfaceBean> interfaceTexts = new Vector<>();

		/**
		 * Only TextBridgeFactory class can instantiate the TextBridge class.
		 */
		private TextBridge() {

			super();
		}

		/**
		 * A bean to store a text to be exchanged between threads.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		public class TextInterfaceBean {

			private int taskNumber = -1;

			private String sourceFilter = new String();

			private StringBuffer content = new StringBuffer();

			private TaskStatus status = TaskStatus.CREATED;

			private String progress = new String();

			private long timeStarted = 0L;

			private long timeEnded = 0L;

			/**
			 * Constructor.
			 * 
			 * @param taskNumberParamter
			 *            The task number.
			 */
			TextInterfaceBean(int taskNumberParamter) {

				this.taskNumber = taskNumberParamter;
			}

			/**
			 * Provide: The task number.
			 * 
			 * @return taskNumber The task number.
			 */
			public int getTaskNumber() {

				return this.taskNumber;
			}

			/**
			 * Set: The task number.
			 * 
			 * @param taskNumber
			 *            The task number.
			 */
			public void setTaskNumber(int taskNumberParameter) {

				this.taskNumber = taskNumberParameter;
			}

			/**
			 * Inform the filter generating the content.
			 * 
			 * @return the sourceFilter The filter generating the content.
			 */
			public String getSourceFilter() {

				return this.sourceFilter;
			}

			/**
			 * Set the filter generating the content.
			 * 
			 * @param sourceFilter
			 *            the sourceFilter to set The filter generating the
			 *            content.
			 */
			public void setSourceFilter(String sourceFilter) {

				this.sourceFilter = sourceFilter;
			}

			/**
			 * Provide the text stored on the text interface.
			 * 
			 * @return the content The text stored on the text interface.
			 */
			public StringBuffer getContent() {

				return this.content;
			}

			/**
			 * Set the text to be stored on the text interface.
			 * 
			 * @param content
			 *            the content to set The text to be stored on the text
			 *            interface.
			 */
			public void setContent(StringBuffer content) {

				this.content = content;
			}

			/**
			 * Inform the task status.
			 * 
			 * @return the status The task status.
			 */
			public TaskStatus getStatus() {

				return this.status;
			}

			/**
			 * Set the task status.
			 * 
			 * @param status
			 *            the status to set The task status.
			 */
			public void setStatus(TaskStatus status) {

				this.status = status;
			}

			/**
			 * Inform the progress text.
			 * 
			 * @return the progress The progress text.
			 */
			public String getProgress() {

				return this.progress;
			}

			/**
			 * Set the progress text.
			 * 
			 * @param progress
			 *            the progress to set The progress text.
			 */
			public void setProgress(String progress) {

				this.progress = progress;
			}

			/**
			 * Inform the time the task has started.
			 * 
			 * @return the timeStarted The time the task has started.
			 */
			public long getTimeStarted() {

				return this.timeStarted;
			}

			/**
			 * Set the time the task has started.
			 * 
			 * @param timeStarted
			 *            the timeStarted to set The time the task has started.
			 */
			public void setTimeStarted(long timeStarted) {

				this.timeStarted = timeStarted;
			}

			/**
			 * Inform the time the task ended.
			 * 
			 * @return the timeEnded The time the task ended.
			 */
			public long getTimeEnded() {

				return this.timeEnded;
			}

			/**
			 * Set the time the task ended.
			 * 
			 * @param timeEnded
			 *            the timeEnded to set The time the task ended.
			 */
			public void setTimeEnded(long timeEnded) {

				this.timeEnded = timeEnded;
			}
		}

		/**
		 * Provide a new text interface.
		 * 
		 * @return the interfaceTexts The text interface.
		 */
		@SuppressWarnings("synthetic-access")
		public TextInterfaceBean createNewInterfaceText() {

			synchronized (processBridge) {

				++taskNumberIdentification;

				TextInterfaceBean newInterfaceCreated = new TextInterfaceBean(
						taskNumberIdentification);

				newInterfaceCreated.setTimeStarted(System.currentTimeMillis());

				this.interfaceTexts.add(newInterfaceCreated);

				return newInterfaceCreated;
			}
		}

		/**
		 * Provide the text interface on a specific position. If there is no
		 * element on this position then it returns null.
		 * 
		 * @return the interfaceTexts The text interface.
		 */
		public synchronized TextInterfaceBean getInterfaceTextByIndex(int index) {

			if (index >= this.interfaceTexts.size()) {

				return null;
			}

			return this.interfaceTexts.get(index);
		}

		/**
		 * Provide the text interface on a specific task index. If there is no
		 * element on this position then it returns null.
		 * 
		 * @return the interfaceTexts The text interface.
		 */
		public synchronized TextInterfaceBean getInterfaceTextByTaskNumber(
				int taskNumber) {

			for (int i = 0; i < this.interfaceTexts.size(); i++) {

				TextInterfaceBean bean = this.interfaceTexts.get(i);

				if (bean.getTaskNumber() == taskNumber) {

					return bean;
				}
			}

			return null;
		}

		/**
		 * Remove a specific task.
		 */
		public synchronized void removeInterfaceText(int taskNumber) {

			for (int i = 0; i < this.interfaceTexts.size(); i++) {

				TextInterfaceBean bean = this.interfaceTexts.get(i);

				if (bean.getTaskNumber() == taskNumber) {

					this.interfaceTexts.remove(bean);
				}
			}
		}

		/**
		 * Extract the text interface on a specific position. If there is no
		 * element on this position then it returns null.
		 * 
		 * @return the interfaceTexts The text interface.
		 */
		public synchronized TextInterfaceBean extractInterfaceText(int index) {

			if (index >= this.interfaceTexts.size()) {

				return null;
			}

			TextInterfaceBean interfaceToBeReturned = this.interfaceTexts
					.get(index);

			this.interfaceTexts.remove(index);

			return interfaceToBeReturned;
		}
	}

	/**
	 * Provide the TextBridge instance
	 * 
	 * @return the TextBridge instance. The TextBridge instance.
	 */
	@SuppressWarnings("synthetic-access")
	public synchronized static TextBridge getInstance() {

		if (processBridge == null) {

			processBridge = new TextBridge();

		}

		return processBridge;
	}
}
