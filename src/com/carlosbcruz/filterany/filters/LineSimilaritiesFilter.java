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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterException;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.TextAreaPanel;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.DurationBean;
import com.carlosbcruz.util.DurationUtil;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.LineTokenizer;
import com.carlosbcruz.util.PercentageGraph;
import com.carlosbcruz.util.SimpleActionDecorator;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionProvider;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Show the similarities between all lines.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class LineSimilaritiesFilter extends FilterAdapter implements
		SpecialBehavior, Example {

	private static final long serialVersionUID = 1L;

	// Transform the input text into an array of lines.
	private String[] linesArray;

	// Indicate if the search should consider the case of letters or not.
	private boolean caseSensitive = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_LINESIMILARITIESFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_LINESIMILARITIESFILTER_INSTRUCTIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_LINESIMILARITIESFILTER_SMALL_DESCRIPTION);
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

		return new FilterControls[] { FilterControls.CHECK_BOX_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NOT_RELEVANT };
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
				.get(Text.FILTER_LINESIMILARITIESFILTER_CHECKBOX1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_LINESIMILARITIESFILTER_CHECKBOX1_TOOLTIP) };
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
	 * Dialog to show similarities among the lines.
	 */
	class ShowSimilaritiesDialog extends JFrame {

		private static final long serialVersionUID = 1L;

		// Panel to store the text area and progress bar.
		private JPanel intermediateReportPanel = new JPanel(new BorderLayout());
		// Panel to store the progress texts and progress bar.
		private JPanel progressPanel = new JPanel(new BorderLayout());
		// Panel to store the three blocks of progress information.
		private JPanel statisticsPanel = new JPanel();

		// Text to inform the progress of line analysis.
		private JLabel currentLine = new JLabel();
		private JLabel totalOfLines = new JLabel();
		private JLabel remainingLines = new JLabel();

		// Text to inform the progress of time.
		private JLabel timeSpent = new JLabel();
		private JLabel estimationTime = new JLabel();
		private JLabel totalTime = new JLabel();

		// Graph to show the distribution into percentage slots.
		private PercentageGraph graph = new PercentageGraph();
		// Text area to store the line analysis output.
		private TextAreaPanel textArea = null;
		// Simple progress bar.
		private JProgressBar progressBar = null;

		// Panel to store the cancel button.
		JPanel cancelButtonPanel = new JPanel();
		// Cancel button.
		private JButton cancelButton = null;

		// Indicate if the user has pressed the cancel button.
		private boolean cancelled = false;

		// The set of lines being analyzed.
		private String[] inputLines = null;

		// Indicate if the search is case sensitive.
		private boolean isCaseSensitive = false;

		// Constants to generate color on labels.
		private static final String HTML_START = "<html>"; //$NON-NLS-1$
		private static final String HTML_END = "</html>"; //$NON-NLS-1$
		private static final String INFO_START = "<strong><font color=\"blue\">"; //$NON-NLS-1$
		private static final String INFO_END = "</font></strong>"; //$NON-NLS-1$

		/**
		 * Constructor.
		 */
		ShowSimilaritiesDialog(String title, String[] inputLines,
				boolean caseSensitive) {

			super(title);

			// Store the parameters to be use on other classes.
			this.inputLines = inputLines;
			this.isCaseSensitive = caseSensitive;

			// Change the frame icon.
			SwingUtil.changeWindowIcon(this,
					FilterAnyConfiguration.RESOURCE_LOCATION
							+ "FilterAnyIcon.png"); //$NON-NLS-1$

			setLayout(new BorderLayout());

			// Add the text area on the center.
			this.textArea = new TextAreaPanel(null,
					Text.get(Text.SCRAP_BOOK_WINDOW_TITLE), new Color(240, 240,
							240), new Color(255, 255, 255), new Color(0, 0, 0),
					new Color(200, 200, 200), new Color(180, 180, 180),
					new Color(180, 180, 180), new Color(200, 200, 200),
					new Color(0, 0, 0), new Color(240, 240, 240), Color.black,
					TextAreaPanel.FIND_LOCATION.CENTER, false);
			this.intermediateReportPanel
					.add(this.textArea, BorderLayout.CENTER);

			// Construct the current line set of information.
			JPanel currentLinePanel = new JPanel(new GridLayout(3, 1));
			currentLinePanel.setBorder(BorderFactory.createTitledBorder(Text
					.get(Text.FILTER_LINESIMILARITIESFILTER_LINES)));

			this.totalOfLines
					.setText(getTotalLinesLabelText(inputLines.length));
			currentLinePanel.add(this.totalOfLines);

			this.currentLine.setText(getCurrentLineLabelText(0,
					inputLines.length));
			currentLinePanel.add(this.currentLine);

			this.remainingLines.setText(getRemainingLineLabelText(0,
					inputLines.length));
			currentLinePanel.add(this.remainingLines);

			// Construct the current time set of information.
			JPanel timePanel = new JPanel(new GridLayout(3, 1));
			timePanel.setBorder(BorderFactory.createTitledBorder(Text
					.get(Text.FILTER_LINESIMILARITIESFILTER_DURATION)));

			this.timeSpent.setText(getElapsedtimeLabelText(0));
			timePanel.add(this.timeSpent);

			this.estimationTime.setText(getExpectedDurationLabelText(0));
			timePanel.add(this.estimationTime);

			this.totalTime.setText(getTotalExpectedDurationLabelText(0));
			timePanel.add(this.totalTime);

			// Construct the distribution graph panel.
			JPanel graphPanel = new JPanel(new BorderLayout());
			graphPanel.setBorder(BorderFactory.createTitledBorder(Text
					.get(Text.FILTER_LINESIMILARITIESFILTER_DISTRIBUTION)));
			graphPanel.add(this.graph, BorderLayout.CENTER);

			// Add the tree panels.
			this.statisticsPanel.add(currentLinePanel);
			this.statisticsPanel.add(timePanel);
			this.statisticsPanel.add(graphPanel);

			// Add the progress bar.
			this.progressPanel.setBorder(BorderFactory.createTitledBorder(Text
					.get(Text.FILTER_LINESIMILARITIESFILTER_PROGRESS)));
			this.progressPanel.add(this.statisticsPanel, BorderLayout.WEST);
			this.progressBar = new JProgressBar(1, inputLines.length);
			this.progressPanel.add(this.progressBar, BorderLayout.SOUTH);
			this.intermediateReportPanel.add(this.progressPanel,
					BorderLayout.SOUTH);

			add(this.intermediateReportPanel, BorderLayout.CENTER);

			// Define the cancel button.
			SimpleActionDecorator cancelAction = SimpleActionProvider
					.getSimpleAction(
							FilterAnyConfiguration.RESOURCE_LOCATION
									+ "cancel-icon.png", //$NON-NLS-1$
							Text.get(Text.FILTER_LINESIMILARITIESFILTER_CANCEL),
							SwingUtil.getKeyEvent(Text
									.get(Text.FILTER_LINESIMILARITIESFILTER_CANCEL_KEY)),
							Text.get(Text.FILTER_LINESIMILARITIESFILTER_CANCEL_INSTRUCTIONS));

			this.cancelButton = new JButton(cancelAction);

			// Cancel event implementation.
			cancelAction.addObserver(new SimpleActionObserver() {

				private static final long serialVersionUID = 1L;

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.carlosbcruz.util.SimpleActionObserver#update(com.carlosbcruz
				 * .util.SimpleActionSubject)
				 */
				@SuppressWarnings("synthetic-access")
				@Override
				public void update(SimpleActionSubject simpleSubject) {

					ShowSimilaritiesDialog.this.cancelled = true;
					ShowSimilaritiesDialog.this.cancelButtonPanel
							.remove(ShowSimilaritiesDialog.this.cancelButton);
					ShowSimilaritiesDialog.this.cancelButtonPanel.add(
							new JLabel(
									Text.get(Text.FILTER_LINESIMILARITIESFILTER_OPERATION_CANCELED)),
							BorderLayout.CENTER);

				}
			});

			// Add the cancel button.
			JPanel cancelPanel = new JPanel(new BorderLayout());
			cancelPanel.add(new JPanel(), BorderLayout.WEST);
			this.cancelButtonPanel.add(this.cancelButton);
			cancelPanel.add(this.cancelButtonPanel, BorderLayout.CENTER);
			cancelPanel.add(new JPanel(), BorderLayout.EAST);
			add(cancelPanel, BorderLayout.SOUTH);

			pack();

			Timer redrawLineNumber = new Timer(1000, new ActionListener() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
				 * ActionEvent)
				 */
				@SuppressWarnings("synthetic-access")
				@Override
				public void actionPerformed(ActionEvent e) {

					ShowSimilaritiesDialog.this.textArea.validate();
				}
			});
			redrawLineNumber.start();

			// Adjust the window size and position.
			Point centerOfScreen = SwingUtil.getCenterOfScreen();
			setSize(getSize().width, 500);
			setLocation(centerOfScreen.x - (getSize().width / 2),
					centerOfScreen.y - (getSize().height / 2));
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setVisible(true);

			// Start the analysis.
			Similarities similarities = new Similarities(inputLines);
			similarities.execute();
		}

		/**
		 * Provide the text to the current line.
		 * 
		 * @param currentLineParameter
		 *            the current line.
		 * @param totalOfLinesParameter
		 *            the total number of lines.
		 * @return The text to the current line label.
		 */
		private String getCurrentLineLabelText(int currentLineParameter,
				int totalOfLinesParameter) {

			return HTML_START
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_CURRENT_LINE)
					+ " " + INFO_START //$NON-NLS-1$
					+ StringSupport.prefixWithZeroes(currentLineParameter,
							totalOfLinesParameter) + INFO_END + HTML_END;
		}

		/**
		 * Provide the text to the remaining lines.
		 * 
		 * @param currentLineParameter
		 *            the current line.
		 * @param totalOfLinesParameter
		 *            the total number of lines.
		 * @return The text to the remaining line label.
		 */
		private String getRemainingLineLabelText(int currentLineParameter,
				int totalOfLinesParameter) {

			return HTML_START
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_REMAINING_LINES)
					+ " " //$NON-NLS-1$
					+ INFO_START
					+ StringSupport.prefixWithZeroes(totalOfLinesParameter
							- currentLineParameter, totalOfLinesParameter)
					+ INFO_END + HTML_END;
		}

		/**
		 * Provide the text to the total of lines.
		 * 
		 * @param totalOfLinesParameter
		 *            the total number of lines.
		 * @return The text to the total of lines.
		 */
		private String getTotalLinesLabelText(int totalOfLinesParameter) {

			return HTML_START
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_TOTAL_OF_LINES)
					+ " " + INFO_START + totalOfLinesParameter + INFO_END + HTML_END; //$NON-NLS-1$
		}

		/**
		 * Provide a text representation of a duration.
		 * 
		 * @param time
		 *            the time to be converted to a text.
		 * @return the text of the time provided.
		 */
		public String getElapsedTimeText(long time) {

			DurationBean bean = DurationUtil.getDuration(time);

			StringBuffer output = new StringBuffer();
			output.append(StringSupport.prefixWithZeroes((int) bean.getHours(),
					24) + Text.get(Text.FILTER_LINESIMILARITIESFILTER_HOUR));
			output.append(StringSupport.prefixWithZeroes(
					(int) bean.getMinutes(), 60)
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_MINUTE));
			output.append(StringSupport.prefixWithZeroes(
					(int) bean.getSeconds(), 60)
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_SECOND));

			return output.toString();
		}

		/**
		 * Provide the text to the elapsed time.
		 * 
		 * @param elapsedTime
		 *            The elapsed time.
		 * @return the text to the elapsed time label.
		 */
		private String getElapsedtimeLabelText(long elapsedTime) {

			return HTML_START
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_ELAPSED_TIME)
					+ " " + INFO_START + getElapsedTimeText(elapsedTime) //$NON-NLS-1$
					+ INFO_END + HTML_END;
		}

		/**
		 * Provide the text to the elapsed time.
		 * 
		 * @param elapsedTime
		 *            The elapsed time.
		 * @return the text to the elapsed time label.
		 */
		private String getExpectedDurationLabelText(long expectedDuration) {

			return HTML_START
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_REMAINING)
					+ " " + INFO_START + getElapsedTimeText(expectedDuration) //$NON-NLS-1$
					+ INFO_END + HTML_END;
		}

		/**
		 * Provide the text to the total expected time.
		 * 
		 * @param elapsedTime
		 *            The elapsed time.
		 * @return the text to the total expected time.
		 */
		private String getTotalExpectedDurationLabelText(long expectedDuration) {

			return HTML_START
					+ Text.get(Text.FILTER_LINESIMILARITIESFILTER_EXPECTED_TIME)
					+ " " + INFO_START + getElapsedTimeText(expectedDuration) //$NON-NLS-1$
					+ INFO_END + HTML_END;
		}

		/**
		 * Store the difference analysis between two lines.
		 */
		class LineDifferenceAnalysisBean implements Serializable, Cloneable,
				Comparator<LineDifferenceAnalysisBean> {

			private static final long serialVersionUID = 1L;

			// First line being analyzed.
			private int firstLine = 0;
			// The line that has the most similarities.
			private int secondLine = 0;
			// Percentage of similarities.
			private float similarity = 0;

			/**
			 * Default constructor.
			 */
			public LineDifferenceAnalysisBean() {

				super();
			}

			/**
			 * @param int firstLineParameter First line being analyzed.
			 * @param int secondLineParameter The line that has the most
			 *        similarities.
			 * @param float similarityParameter Percentage of similarities.
			 */
			public LineDifferenceAnalysisBean(int firstLineParameter,
					int secondLineParameter, float similarityParameter) {

				super();

				this.firstLine = firstLineParameter;
				this.secondLine = secondLineParameter;
				this.similarity = similarityParameter;
			}

			/**
			 * Provide: First line being analyzed.
			 * 
			 * @return firstLine First line being analyzed.
			 */
			public int getFirstLine() {

				return this.firstLine;
			}

			/**
			 * Set: First line being analyzed.
			 * 
			 * @param firstLine
			 *            First line being analyzed.
			 */
			public void setFirstLine(int firstLineParameter) {

				this.firstLine = firstLineParameter;
			}

			/**
			 * Provide: The line that has the most similarities.
			 * 
			 * @return secondLine The line that has the most similarities.
			 */
			public int getSecondLine() {

				return this.secondLine;
			}

			/**
			 * Set: The line that has the most similarities.
			 * 
			 * @param secondLine
			 *            The line that has the most similarities.
			 */
			public void setSecondLine(int secondLineParameter) {

				this.secondLine = secondLineParameter;
			}

			/**
			 * Provide: Percentage of similarities.
			 * 
			 * @return similarity Percentage of similarities.
			 */
			public float getSimilarity() {

				return this.similarity;
			}

			/**
			 * Set: Percentage of similarities.
			 * 
			 * @param similarity
			 *            Percentage of similarities.
			 */
			public void setSimilarity(float similarityParameter) {

				this.similarity = similarityParameter;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {

				StringBuffer out = new StringBuffer();

				out.append("LineDifferenceAnalysisBean [\n"); //$NON-NLS-1$

				out.append("\tfirstLine=" + this.firstLine + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
				out.append("\tsecondLine=" + this.secondLine + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
				out.append("\tsimilarity=" + this.similarity + "]\n"); //$NON-NLS-1$ //$NON-NLS-2$

				return out.toString();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#clone()
			 */
			@Override
			public Object clone() throws CloneNotSupportedException {

				return super.clone();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 * java.lang.Object)
			 */
			@Override
			public int compare(LineDifferenceAnalysisBean object1,
					LineDifferenceAnalysisBean object2) {

				if (object1.getSimilarity() < object2.getSimilarity()) {
					return 1;
				}

				if (object1.getSimilarity() > object2.getSimilarity()) {
					return -1;
				}

				return 0;
			}
		}

		/**
		 * Store a preliminary report of the analysis.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		class IntermediateReport implements Serializable {

			private static final long serialVersionUID = 1L;

			// Store a preliminary list of similar lines.
			private String intermediaryResult = new String();
			// The lines being analyzed to indicate the progress.
			private int progress = 0;
			// The time that has elapsed since the start of execution.
			private long elapsedTime = 0;
			// An estimative of total duration.
			private long expectedDuration = 0;
			// The distribution of the analysis in the percentage slot.
			private int[] distribution = null;

			/**
			 * Default constructor.
			 */
			public IntermediateReport() {

				super();
			}

			/**
			 * Provide: Store a preliminary list of similar lines.
			 * 
			 * @return intermediaryResult Store a preliminary list of similar
			 *         lines.
			 */
			public String getIntermediaryResult() {

				return this.intermediaryResult;
			}

			/**
			 * Set: Store a preliminary list of similar lines.
			 * 
			 * @param intermediaryResult
			 *            Store a preliminary list of similar lines.
			 */
			public void setIntermediaryResult(String intermediaryResultParameter) {

				this.intermediaryResult = intermediaryResultParameter;
			}

			/**
			 * Provide: The lines being analyzed to indicate the progress.
			 * 
			 * @return progress The lines being analyzed to indicate the
			 *         progress.
			 */
			public int getProgress() {

				return this.progress;
			}

			/**
			 * Set: The lines being analyzed to indicate the progress.
			 * 
			 * @param progress
			 *            The lines being analyzed to indicate the progress.
			 */
			public void setProgress(int progressParameter) {

				this.progress = progressParameter;
			}

			/**
			 * Provide: The time that has elapsed since the start of execution.
			 * 
			 * @return elapsedTime The time that has elapsed since the start of
			 *         execution.
			 */
			public long getElapsedTime() {

				return this.elapsedTime;
			}

			/**
			 * Set: The time that has elapsed since the start of execution.
			 * 
			 * @param elapsedTime
			 *            The time that has elapsed since the start of
			 *            execution.
			 */
			public void setElapsedTime(long elapsedTimeParameter) {

				this.elapsedTime = elapsedTimeParameter;
			}

			/**
			 * Provide: An estimative of total duration.
			 * 
			 * @return expectedDuration An estimative of total duration.
			 */
			public long getExpectedDuration() {

				return this.expectedDuration;
			}

			/**
			 * Set: An estimative of total duration.
			 * 
			 * @param expectedDuration
			 *            An estimative of total duration.
			 */
			public void setExpectedDuration(long expectedDurationParameter) {

				this.expectedDuration = expectedDurationParameter;
			}

			/**
			 * Provide: The distribution of the analysis in the percentage slot.
			 * 
			 * @return distribution The distribution of the analysis in the
			 *         percentage slot.
			 */
			public int[] getDistribution() {

				return this.distribution;
			}

			/**
			 * Set: The distribution of the analysis in the percentage slot.
			 * 
			 * @param distribution
			 *            The distribution of the analysis in the percentage
			 *            slot.
			 */
			public void setDistribution(int[] distributionParameter) {

				this.distribution = distributionParameter;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {

				StringBuffer out = new StringBuffer();

				out.append("IntermediateReport [\n"); //$NON-NLS-1$

				out.append("\tintermediaryResult=" + this.intermediaryResult //$NON-NLS-1$
						+ ",\n"); //$NON-NLS-1$
				out.append("\tprogress=" + this.progress + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
				out.append("\telapsedTime=" + this.elapsedTime + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
				out.append("\texpectedDuration=" + this.expectedDuration + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
				out.append("\tdistribution=" + this.distribution + "]\n"); //$NON-NLS-1$ //$NON-NLS-2$

				return out.toString();
			}

		}

		/**
		 * Generate an output report.
		 * 
		 * @param analysis
		 *            The current analysis.
		 * @return The output report.
		 */
		@SuppressWarnings("synthetic-access")
		private StringBuffer generateTextArea(
				ArrayList<LineDifferenceAnalysisBean> analysis) {

			StringBuffer intermediaryResult = new StringBuffer();
			for (int i = 0; i < analysis.size(); i++) {

				LineDifferenceAnalysisBean intermediaryAnalysis = analysis
						.get(i);

				intermediaryResult.append(Text
						.get(Text.FILTER_LINESIMILARITIESFILTER_SIMILARITY)
						+ " " + intermediaryAnalysis.similarity + "%\n"); //$NON-NLS-1$ //$NON-NLS-2$
				if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
					intermediaryResult
							.append(Text
									.get(Text.FILTER_LINESIMILARITIESFILTER_LINE_DEMONSTRATION)
									+ " " //$NON-NLS-1$
									+ this.inputLines[intermediaryAnalysis.firstLine]
									+ "\n"); //$NON-NLS-1$
					intermediaryResult
							.append(Text
									.get(Text.FILTER_LINESIMILARITIESFILTER_LINE_DEMONSTRATION)
									+ " " //$NON-NLS-1$
									+ this.inputLines[intermediaryAnalysis.secondLine]
									+ "\n\n"); //$NON-NLS-1$

				} else {
					intermediaryResult.append(Text
							.get(Text.FILTER_LINESIMILARITIESFILTER_LINE)
							+ " " //$NON-NLS-1$
							+ (intermediaryAnalysis.firstLine + 1)
							+ ": " //$NON-NLS-1$
							+ this.inputLines[intermediaryAnalysis.firstLine]
							+ "\n"); //$NON-NLS-1$
					intermediaryResult.append(Text
							.get(Text.FILTER_LINESIMILARITIESFILTER_LINE)
							+ " " //$NON-NLS-1$
							+ (intermediaryAnalysis.secondLine + 1)
							+ ": " //$NON-NLS-1$
							+ this.inputLines[intermediaryAnalysis.secondLine]
							+ "\n\n"); //$NON-NLS-1$
				}
			}

			return intermediaryResult;
		}

		/**
		 * Search for similarities.
		 */
		class Similarities
				extends
				SwingWorker<ArrayList<LineDifferenceAnalysisBean>, IntermediateReport> {

			// The lines being analyzed.
			private String[] lines = new String[0];

			// The distribution of the analysis into the percentage slot.
			int[] distribution = new int[10];

			// The set of analysis.
			ArrayList<LineDifferenceAnalysisBean> analysis = new ArrayList<>();

			/**
			 * Constructor.
			 * 
			 * @param linesParameter
			 *            the lines to work with.
			 */
			Similarities(String[] linesParameter) {

				this.lines = linesParameter;

				// Initialize the distribution array.
				for (int i = 0; i < this.distribution.length; i++) {
					this.distribution[i] = 0;
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.SwingWorker#doInBackground()
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			protected ArrayList<LineDifferenceAnalysisBean> doInBackground() {

				ExceptionSupport.handleException("This filter is only on full version./Este filtro é somente na versão completa.");
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.SwingWorker#process(java.util.List)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			protected void process(List<IntermediateReport> chunks) {

				super.process(chunks);

				// Show the report.
				if (chunks != null && chunks.size() > 0) {

					IntermediateReport firstReport = chunks
							.get(chunks.size() - 1);

					ShowSimilaritiesDialog.this.textArea
							.setTextArea(firstReport.getIntermediaryResult());
					ShowSimilaritiesDialog.this.progressBar
							.setValue(firstReport.getProgress());
					ShowSimilaritiesDialog.this.currentLine
							.setText(getCurrentLineLabelText(
									firstReport.getProgress(),
									this.lines.length));
					ShowSimilaritiesDialog.this.remainingLines
							.setText(getRemainingLineLabelText(
									firstReport.getProgress(),
									this.lines.length));
					ShowSimilaritiesDialog.this.timeSpent
							.setText(getElapsedtimeLabelText(firstReport
									.getElapsedTime()));
					ShowSimilaritiesDialog.this.estimationTime
							.setText(getExpectedDurationLabelText(firstReport
									.getExpectedDuration()));
					ShowSimilaritiesDialog.this.totalTime
							.setText(getTotalExpectedDurationLabelText(firstReport
									.getElapsedTime()
									+ firstReport.getExpectedDuration()));
					ShowSimilaritiesDialog.this.graph
							.setDistribution(firstReport.getDistribution());
					ShowSimilaritiesDialog.this.textArea.setCaretPosition(0);
					ShowSimilaritiesDialog.this.textArea.repaint();

					if (firstReport.getProgress() == this.lines.length) {
						ShowSimilaritiesDialog.this.cancelButtonPanel
								.remove(ShowSimilaritiesDialog.this.cancelButton);
						ShowSimilaritiesDialog.this.cancelButtonPanel
								.add(new JLabel(
										Text.get(Text.FILTER_LINESIMILARITIESFILTER_FINISHED_SUCCESSFULY)),
										BorderLayout.CENTER);
					}
				}
			}
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

		// Generate a list of lines.
		LineTokenizer tokenizer = new LineTokenizer(text);
		ArrayList<String> lines = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			if (!"".equals(line)) { //$NON-NLS-1$
				lines.add(line);
			}
		}

		if (lines.size() < 3) {
			throw new FilterException(
					Text.get(Text.FILTER_LINESIMILARITIESFILTER_EXCEPTION));
		}

		this.linesArray = new String[lines.size()];
		this.linesArray = lines.toArray(this.linesArray);

		// Store the case sensitive.
		this.caseSensitive = isCheckBox1();

		// Run the analysis on a separete thread.
		Thread similarityThread = new Thread() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@SuppressWarnings({ "unused", "synthetic-access" })
			@Override
			public void run() {

				new ShowSimilaritiesDialog(
						Text.get(Text.FILTER_LINESIMILARITIESFILTER_TITLE),
						LineSimilaritiesFilter.this.linesArray,
						LineSimilaritiesFilter.this.caseSensitive);
			}

		};
		similarityThread.start();

		// Return the Swing event thread otherwise the window get's blank.
		return new StringBuffer();
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

		return Text.get(Text.FILTER_LINESIMILARITIESFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_LINESIMILARITIESFILTER_EXAMPLE_OUTPUT);
	}

}
