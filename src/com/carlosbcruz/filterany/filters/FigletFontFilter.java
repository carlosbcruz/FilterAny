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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import com.carlosbcruz.filterany.TextAreaPanelDemonstration;
import com.carlosbcruz.filterany.TextAreaPanelSimple;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.ExceptionSupport;
import com.carlosbcruz.util.FigletASCII;
import com.carlosbcruz.util.FigletFont;
import com.carlosbcruz.util.FileSupport;
import com.carlosbcruz.util.LineTokenizer;
import com.carlosbcruz.util.SimpleActionDecorator;
import com.carlosbcruz.util.SimpleActionObserver;
import com.carlosbcruz.util.SimpleActionProvider;
import com.carlosbcruz.util.SimpleActionSubject;
import com.carlosbcruz.util.StringSupport;
import com.carlosbcruz.util.SwingUtil;

/**
 * Transform all new lines to DOS format.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class FigletFontFilter extends FilterAdapter implements SpecialBehavior,
		Example {

	private static final long serialVersionUID = 1L;

	// Path for the resources.
	private static final String RESOURCE_LOCATION = "/figletFonts/"; //$NON-NLS-1$

	private static final int DIALOG_WIDTH = 1000;
	private static final int DIALOG_HEIGHT = 400;

	// Test to be converted.
	private String token = null;
	// List of file fonts.
	private FileBean fileArray[];
	// Preview area.
	transient private TextAreaPanel previewArea = null;
	// Preview dialog
	transient private JDialog dialog = null;
	// Indicate the font file selected.
	private int selectedFile = 0;

	// Indicate that the input should be copied.
	private boolean copyInput = false;

	/**
	 * Store the file name and the content key.
	 */
	public class FileBean implements Serializable {

		private static final long serialVersionUID = 1L;

		String fileName = new String();
		String contentKey = new String();

		/**
		 * Default constructor.
		 */
		public FileBean() {

			super();
		}

		/**
		 * @param String
		 *            fileName.
		 * @param String
		 *            contentKey.
		 */
		public FileBean(String fileName, String contentKey) {

			super();

			this.fileName = fileName;
			this.contentKey = contentKey;
		}

		/**
		 * Provide: The file name
		 * 
		 * @return fileName The file name
		 */
		public String getFileName() {
			return this.fileName;
		}

		/**
		 * Store: The file name
		 * 
		 * @param fileName
		 *            The file name
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * Provide: The content key for a meaningful name
		 * 
		 * @return contentKey The content key for a meaningful name
		 */
		public String getContentKey() {
			return this.contentKey;
		}

		/**
		 * Store: The content key for a meaningful name
		 * 
		 * @param contentKey
		 *            The content key for a meaningful name
		 */
		public void setContentKey(String contentKey) {
			this.contentKey = contentKey;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {

			return Text.get(this.contentKey);
		}

	}

	/**
	 * Generate the file name list.
	 */
	@SuppressWarnings("nls")
	private void getFiles() {

		ArrayList<FileBean> files = new ArrayList<>();

		files.add(new FileBean("5lineoblique.zip",
				Text.FIGLET_FILE_5LINEOBLIQUE_ZIP_NAME));
		files.add(new FileBean("acrobatic.zip",
				Text.FIGLET_FILE_ACROBATIC_ZIP_NAME));
		files.add(new FileBean("alligator.zip",
				Text.FIGLET_FILE_ALLIGATOR_ZIP_NAME));
		files.add(new FileBean("alligator2.zip",
				Text.FIGLET_FILE_ALLIGATOR2_ZIP_NAME));
		files.add(new FileBean("alphabet.zip",
				Text.FIGLET_FILE_ALPHABET_ZIP_NAME));
		files.add(new FileBean("asc_____.zip",
				Text.FIGLET_FILE_ASC______ZIP_NAME));
		files.add(new FileBean("banner3-D.zip",
				Text.FIGLET_FILE_BANNER3_D_ZIP_NAME));
		files.add(new FileBean("banner3.zip", Text.FIGLET_FILE_BANNER3_ZIP_NAME));
		files.add(new FileBean("barbwire.zip",
				Text.FIGLET_FILE_BARBWIRE_ZIP_NAME));
		files.add(new FileBean("basic.zip", Text.FIGLET_FILE_BASIC_ZIP_NAME));
		files.add(new FileBean("bell.zip", Text.FIGLET_FILE_BELL_ZIP_NAME));
		files.add(new FileBean("big.zip", Text.FIGLET_FILE_BIG_ZIP_NAME));
		files.add(new FileBean("bigchief.zip",
				Text.FIGLET_FILE_BIGCHIEF_ZIP_NAME));
		files.add(new FileBean("block.zip", Text.FIGLET_FILE_BLOCK_ZIP_NAME));
		files.add(new FileBean("broadway.zip",
				Text.FIGLET_FILE_BROADWAY_ZIP_NAME));
		files.add(new FileBean("bulbhead.zip",
				Text.FIGLET_FILE_BULBHEAD_ZIP_NAME));
		files.add(new FileBean("calgphy2.zip",
				Text.FIGLET_FILE_CALGPHY2_ZIP_NAME));
		files.add(new FileBean("caligraphy.zip",
				Text.FIGLET_FILE_CALIGRAPHY_ZIP_NAME));
		files.add(new FileBean("catwalk.zip", Text.FIGLET_FILE_CATWALK_ZIP_NAME));
		files.add(new FileBean("char2___.zip",
				Text.FIGLET_FILE_CHAR2____ZIP_NAME));
		files.add(new FileBean("chunky.zip", Text.FIGLET_FILE_CHUNKY_ZIP_NAME));
		files.add(new FileBean("coinstak.zip",
				Text.FIGLET_FILE_COINSTAK_ZIP_NAME));
		files.add(new FileBean("colossal.zip",
				Text.FIGLET_FILE_COLOSSAL_ZIP_NAME));
		files.add(new FileBean("computer.zip",
				Text.FIGLET_FILE_COMPUTER_ZIP_NAME));
		files.add(new FileBean("contessa.zip",
				Text.FIGLET_FILE_CONTESSA_ZIP_NAME));
		files.add(new FileBean("cosmic.zip", Text.FIGLET_FILE_COSMIC_ZIP_NAME));
		files.add(new FileBean("crawford.zip",
				Text.FIGLET_FILE_CRAWFORD_ZIP_NAME));
		files.add(new FileBean("cricket.zip", Text.FIGLET_FILE_CRICKET_ZIP_NAME));
		files.add(new FileBean("cyberlarge.zip",
				Text.FIGLET_FILE_CYBERLARGE_ZIP_NAME));
		files.add(new FileBean("cybermedium.zip",
				Text.FIGLET_FILE_CYBERMEDIUM_ZIP_NAME));
		files.add(new FileBean("cybersmall.zip",
				Text.FIGLET_FILE_CYBERSMALL_ZIP_NAME));
		files.add(new FileBean("demo_m__.zip",
				Text.FIGLET_FILE_DEMO_M___ZIP_NAME));
		files.add(new FileBean("doh.zip", Text.FIGLET_FILE_DOH_ZIP_NAME));
		files.add(new FileBean("doom.zip", Text.FIGLET_FILE_DOOM_ZIP_NAME));
		files.add(new FileBean("dosrebel.zip",
				Text.FIGLET_FILE_DOSREBEL_ZIP_NAME));
		files.add(new FileBean("dotmatrix.zip",
				Text.FIGLET_FILE_DOTMATRIX_ZIP_NAME));
		files.add(new FileBean("double.zip", Text.FIGLET_FILE_DOUBLE_ZIP_NAME));
		files.add(new FileBean("eftifont.zip",
				Text.FIGLET_FILE_EFTIFONT_ZIP_NAME));
		files.add(new FileBean("eftirobot.zip",
				Text.FIGLET_FILE_EFTIROBOT_ZIP_NAME));
		files.add(new FileBean("eftitalic.zip",
				Text.FIGLET_FILE_EFTITALIC_ZIP_NAME));
		files.add(new FileBean("eftiwater.zip",
				Text.FIGLET_FILE_EFTIWATER_ZIP_NAME));
		files.add(new FileBean("epic.zip", Text.FIGLET_FILE_EPIC_ZIP_NAME));
		files.add(new FileBean("fender.zip", Text.FIGLET_FILE_FENDER_ZIP_NAME));
		files.add(new FileBean("fourtops.zip",
				Text.FIGLET_FILE_FOURTOPS_ZIP_NAME));
		files.add(new FileBean("fraktur.zip", Text.FIGLET_FILE_FRAKTUR_ZIP_NAME));
		files.add(new FileBean("fuzzy.zip", Text.FIGLET_FILE_FUZZY_ZIP_NAME));
		files.add(new FileBean("goofy.zip", Text.FIGLET_FILE_GOOFY_ZIP_NAME));
		files.add(new FileBean("gothic.zip", Text.FIGLET_FILE_GOTHIC_ZIP_NAME));
		files.add(new FileBean("graceful.zip",
				Text.FIGLET_FILE_GRACEFUL_ZIP_NAME));
		files.add(new FileBean("graffiti.zip",
				Text.FIGLET_FILE_GRAFFITI_ZIP_NAME));
		files.add(new FileBean("hollywood.zip",
				Text.FIGLET_FILE_HOLLYWOOD_ZIP_NAME));
		files.add(new FileBean("invita.zip", Text.FIGLET_FILE_INVITA_ZIP_NAME));
		files.add(new FileBean("isometric1.zip",
				Text.FIGLET_FILE_ISOMETRIC1_ZIP_NAME));
		files.add(new FileBean("isometric2.zip",
				Text.FIGLET_FILE_ISOMETRIC2_ZIP_NAME));
		files.add(new FileBean("isometric3.zip",
				Text.FIGLET_FILE_ISOMETRIC3_ZIP_NAME));
		files.add(new FileBean("isometric4.zip",
				Text.FIGLET_FILE_ISOMETRIC4_ZIP_NAME));
		files.add(new FileBean("italic.zip", Text.FIGLET_FILE_ITALIC_ZIP_NAME));
		files.add(new FileBean("ivrit.zip", Text.FIGLET_FILE_IVRIT_ZIP_NAME));
		files.add(new FileBean("kban.zip", Text.FIGLET_FILE_KBAN_ZIP_NAME));
		files.add(new FileBean("larry3d.zip", Text.FIGLET_FILE_LARRY3D_ZIP_NAME));
		files.add(new FileBean("lean.zip", Text.FIGLET_FILE_LEAN_ZIP_NAME));
		files.add(new FileBean("letters.zip", Text.FIGLET_FILE_LETTERS_ZIP_NAME));
		files.add(new FileBean("marquee.zip", Text.FIGLET_FILE_MARQUEE_ZIP_NAME));
		files.add(new FileBean("mini.zip", Text.FIGLET_FILE_MINI_ZIP_NAME));
		files.add(new FileBean("nancyj-fancy.zip",
				Text.FIGLET_FILE_NANCYJ_FANCY_ZIP_NAME));
		files.add(new FileBean("nancyj.zip", Text.FIGLET_FILE_NANCYJ_ZIP_NAME));
		files.add(new FileBean("nipples.zip", Text.FIGLET_FILE_NIPPLES_ZIP_NAME));
		files.add(new FileBean("npn_____.zip",
				Text.FIGLET_FILE_NPN______ZIP_NAME));
		files.add(new FileBean("nvscript.zip",
				Text.FIGLET_FILE_NVSCRIPT_ZIP_NAME));
		files.add(new FileBean("o8.zip", Text.FIGLET_FILE_O8_ZIP_NAME));
		files.add(new FileBean("ogre.zip", Text.FIGLET_FILE_OGRE_ZIP_NAME));
		files.add(new FileBean("pawp.zip", Text.FIGLET_FILE_PAWP_ZIP_NAME));
		files.add(new FileBean("peaks.zip", Text.FIGLET_FILE_PEAKS_ZIP_NAME));
		files.add(new FileBean("pebbles.zip", Text.FIGLET_FILE_PEBBLES_ZIP_NAME));
		files.add(new FileBean("pepper.zip", Text.FIGLET_FILE_PEPPER_ZIP_NAME));
		files.add(new FileBean("poison.zip", Text.FIGLET_FILE_POISON_ZIP_NAME));
		files.add(new FileBean("puffy.zip", Text.FIGLET_FILE_PUFFY_ZIP_NAME));
		files.add(new FileBean("radical_.zip",
				Text.FIGLET_FILE_RADICAL__ZIP_NAME));
		files.add(new FileBean("rectangles.zip",
				Text.FIGLET_FILE_RECTANGLES_ZIP_NAME));
		files.add(new FileBean("rev.zip", Text.FIGLET_FILE_REV_ZIP_NAME));
		files.add(new FileBean("roman.zip", Text.FIGLET_FILE_ROMAN_ZIP_NAME));
		files.add(new FileBean("rounded.zip", Text.FIGLET_FILE_ROUNDED_ZIP_NAME));
		files.add(new FileBean("rowancap.zip",
				Text.FIGLET_FILE_ROWANCAP_ZIP_NAME));
		files.add(new FileBean("rozzo.zip", Text.FIGLET_FILE_ROZZO_ZIP_NAME));
		files.add(new FileBean("sblood.zip", Text.FIGLET_FILE_SBLOOD_ZIP_NAME));
		files.add(new FileBean("script.zip", Text.FIGLET_FILE_SCRIPT_ZIP_NAME));
		files.add(new FileBean("serifcap.zip",
				Text.FIGLET_FILE_SERIFCAP_ZIP_NAME));
		files.add(new FileBean("shadow.zip", Text.FIGLET_FILE_SHADOW_ZIP_NAME));
		files.add(new FileBean("slant.zip", Text.FIGLET_FILE_SLANT_ZIP_NAME));
		files.add(new FileBean("slscript.zip",
				Text.FIGLET_FILE_SLSCRIPT_ZIP_NAME));
		files.add(new FileBean("small.zip", Text.FIGLET_FILE_SMALL_ZIP_NAME));
		files.add(new FileBean("smisome1.zip",
				Text.FIGLET_FILE_SMISOME1_ZIP_NAME));
		files.add(new FileBean("smscript.zip",
				Text.FIGLET_FILE_SMSCRIPT_ZIP_NAME));
		files.add(new FileBean("smshadow.zip",
				Text.FIGLET_FILE_SMSHADOW_ZIP_NAME));
		files.add(new FileBean("smslant.zip", Text.FIGLET_FILE_SMSLANT_ZIP_NAME));
		files.add(new FileBean("space_op.zip",
				Text.FIGLET_FILE_SPACE_OP_ZIP_NAME));
		files.add(new FileBean("speed.zip", Text.FIGLET_FILE_SPEED_ZIP_NAME));
		files.add(new FileBean("stacey.zip", Text.FIGLET_FILE_STACEY_ZIP_NAME));
		files.add(new FileBean("stampatello.zip",
				Text.FIGLET_FILE_STAMPATELLO_ZIP_NAME));
		files.add(new FileBean("standard.zip",
				Text.FIGLET_FILE_STANDARD_ZIP_NAME));
		files.add(new FileBean("starwars.zip",
				Text.FIGLET_FILE_STARWARS_ZIP_NAME));
		files.add(new FileBean("stop.zip", Text.FIGLET_FILE_STOP_ZIP_NAME));
		files.add(new FileBean("straight.zip",
				Text.FIGLET_FILE_STRAIGHT_ZIP_NAME));
		files.add(new FileBean("tanja.zip", Text.FIGLET_FILE_TANJA_ZIP_NAME));
		files.add(new FileBean("thick.zip", Text.FIGLET_FILE_THICK_ZIP_NAME));
		files.add(new FileBean("thin.zip", Text.FIGLET_FILE_THIN_ZIP_NAME));
		files.add(new FileBean("threepoint.zip",
				Text.FIGLET_FILE_THREEPOINT_ZIP_NAME));
		files.add(new FileBean("tinker-toy.zip",
				Text.FIGLET_FILE_TINKER_TOY_ZIP_NAME));
		files.add(new FileBean("tombstone.zip",
				Text.FIGLET_FILE_TOMBSTONE_ZIP_NAME));
		files.add(new FileBean("univers.zip", Text.FIGLET_FILE_UNIVERS_ZIP_NAME));
		files.add(new FileBean("weird.zip", Text.FIGLET_FILE_WEIRD_ZIP_NAME));
		files.add(new FileBean("whimsy.zip", Text.FIGLET_FILE_WHIMSY_ZIP_NAME));

		// Transform into an fileArray.
		this.fileArray = new FileBean[files.size()];
		this.fileArray = files.toArray(this.fileArray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_FIGLETFONTFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_FIGLETFONTFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_FIGLETFONTFILTER_INSTRUCTIONS);
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
	 * Generate the banner.
	 * 
	 * @throws IOException
	 *             If a problem happens.
	 * @throws URISyntaxException
	 *             If thre is a problem.
	 */
	@SuppressWarnings({ "nls", "resource" })
	private static String generateText(String file, String text)
			throws IOException, URISyntaxException {


		ExceptionSupport.handleException("This filter is only on full version./Este filtro é somente na versão completa.");
		return null;	}

	/**
	 * Show the text on the preview area.
	 * 
	 * @param position
	 *            The type of font requested.
	 */
	private void showTextOnPreview(int position) {

		try {
			this.previewArea.setTextArea(generateText(
					this.fileArray[position].getFileName(), this.token));
		} catch (IOException exeption) {
			this.previewArea.setTextArea(Text
					.get(Text.FILTER_FIGLETFONTFILTER_EXCEPTION));
		} catch (URISyntaxException exception) {
			this.previewArea.setTextArea(Text
					.get(Text.FILTER_FIGLETFONTFILTER_EXCEPTION));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@SuppressWarnings("static-access")
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText)
			throws FilterException {

		StringBuffer output = new StringBuffer();

		// Get only the first line.
		LineTokenizer tokenizer = new LineTokenizer(text);
		this.token = null;
		while (tokenizer.hasMoreTokens()) {
			String oneLine = tokenizer.nextToken().trim();
			if (!"".equals(oneLine) && this.token == null) { //$NON-NLS-1$
				this.token = oneLine;
			}
		}
		// Use an example string if no line is inserted.
		if (this.token == null || "".equals(this.token)) { //$NON-NLS-1$
			this.token = Text.get(Text.FIGLET_PREVIEW_WINDOW_EXAMPLE);
		}

		// Get the position and size of the dialog.
		Point superFrameLocation = getSuperComponent().getLocation();
		Dimension superFrameDimension = getSuperComponent().getSize();

		int superFrameCenterX = superFrameLocation.x
				+ ((int) superFrameDimension.getWidth() / 2);
		int superFrameCenterY = superFrameLocation.y
				+ ((int) superFrameDimension.getHeight() / 2);

		int dialongPositionX = superFrameCenterX - DIALOG_WIDTH / 2;
		int dialongPositionY = superFrameCenterY - DIALOG_HEIGHT / 2;

		this.dialog = new JDialog(getSuperComponent(),
				Text.get(Text.FIGLET_PREVIEW_WINDOW_TITLE));

		this.dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.dialog.setLocation(dialongPositionX, dialongPositionY);
		this.dialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);

		// Populate the list of fonts.
		getFiles();

		// Create the panel that is going to be inserted on the dialog.
		JPanel internalPanel = new JPanel(new BorderLayout());

		// Add the list of fonts.
		JList<FileBean> fileList = new JList<>(this.fileArray);
		fileList.setSelectedIndex(0);
		internalPanel.add(new JScrollPane(fileList), BorderLayout.WEST);

		// Add the event listeners to the font list.
		fileList.addListSelectionListener(new ListSelectionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing
			 * .event.ListSelectionEvent)
			 */
			@SuppressWarnings({ "synthetic-access", "unchecked" })
			@Override
			public void valueChanged(ListSelectionEvent event) {

				showTextOnPreview(FigletFontFilter.this.selectedFile = ((JList<Object>) event
						.getSource()).getSelectedIndex());
			}
		});

		fileList.addKeyListener(new KeyListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					FigletFontFilter.this.dialog.setVisible(false);
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyTyped(KeyEvent e) {
				// Do nothing.
			}
		});

		// Add the text area where the previews are presented.
		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			this.previewArea = new TextAreaPanelDemonstration();
			this.previewArea.setBackground(new Color(255, 255, 255));
		} else {
			this.previewArea = new TextAreaPanelSimple();
			this.previewArea.setFont(FilterAnyConfiguration.getTextAreaFont());
		}

		internalPanel.add(new JScrollPane(this.previewArea),
				BorderLayout.CENTER);

		// The first item is the selecterd one.
		showTextOnPreview(0);

		SimpleActionDecorator okButtonAction = SimpleActionProvider
				.getSimpleAction(
						FilterAnyConfiguration.RESOURCE_LOCATION
								+ "ok-icon.png", //$NON-NLS-1$
						Text.get(Text.FIGLET_PREVIEW_WINDOW_BUTTON),
						SwingUtil.getKeyEvent(Text
								.get(Text.FIGLET_PREVIEW_WINDOW_BUTTON_KEY)),
						Text.get(Text.FIGLET_PREVIEW_WINDOW_BUTTON_INSTRUCTIONS));

		// Add the ok button.
		JButton okButtom = new JButton(okButtonAction);
		okButtom.setAlignmentX(Component.CENTER_ALIGNMENT);
		// Centralize the ok button.
		JPanel confirmationPanel = new JPanel();
		confirmationPanel.setLayout(new BoxLayout(confirmationPanel,
				BoxLayout.PAGE_AXIS));
		confirmationPanel.add(Box.createVerticalGlue());
		confirmationPanel.add(okButtom);
		confirmationPanel.add(Box.createVerticalGlue());
		internalPanel.add(confirmationPanel, BorderLayout.SOUTH);

		// Add the event to the button.
		okButtonAction.addObserver(new SimpleActionObserver() {

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

				FigletFontFilter.this.copyInput = false;
				FigletFontFilter.this.dialog.setVisible(false);
			}

		});

		// Add the dialog instruction.
		JLabel textToBeGenerated = new JLabel(
				Text.get(Text.FIGLET_PREVIEW_WINDOW_INSTRUCTIONS));
		internalPanel.add(textToBeGenerated, BorderLayout.NORTH);

		// Show the dialog.
		this.dialog.add(internalPanel);
		this.dialog.setModal(true);
		// Change the frame icon.
		SwingUtil.changeWindowIcon(this.dialog,
				FilterAnyConfiguration.RESOURCE_LOCATION + "FilterAnyIcon.png"); //$NON-NLS-1$
		this.dialog.setVisible(true);

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {

			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		if (this.copyInput) {

			output = new StringBuffer(text);

		} else {

			this.copyInput = true;

			// Return the text with the selected font.
			if (this.token != null) {

				try {
					return new StringBuffer(generateText(
							this.fileArray[this.selectedFile].getFileName(),
							this.token));
				} catch (IOException exception) {
					throw new FilterException(
							Text.get(Text.FILTER_FIGLETFONTFILTER_EXCEPTION),
							exception.getLocalizedMessage());
				} catch (URISyntaxException exception) {
					throw new FilterException(
							Text.get(Text.FILTER_FIGLETFONTFILTER_EXCEPTION),
							exception.getLocalizedMessage());
				}
			}
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

		return Text.get(Text.FILTER_FIGLETFONTFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_FIGLETFONTFILTER_EXAMPLE_OUTPUT);
	}
}
