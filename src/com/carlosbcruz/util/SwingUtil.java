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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.PlainDocument;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * Simple Swing Utilities.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
/**
 * Comment
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class SwingUtil {

	private static JLabel calendarLabel = null;
	private static boolean calendarAlreadyUsed = false;

	private static String resource = new String();

	/**
	 * Indicate that only one instance of the calendar is allowed.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	static class InstanceAlreadyUsedException extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}

	/**
	 * Change the icon of a window.
	 * 
	 * @param window
	 *            The window.
	 * @param imageLocation
	 *            The image location.
	 */
	public static void changeWindowIcon(Window window, String imageLocation) {

		BufferedImage image = null;
		try {
			image = ImageIO.read(ClassLoader.getSystemClassLoader()
					.getResource(imageLocation));
		} catch (IOException e) {
			ExceptionSupport.handleException(InternalResource.get(
					InternalResource.IMAGE_FILE_NOT_FOUND, imageLocation));
		}
		window.setIconImage(image);

	}

	/**
	 * Indicate the center of the screen
	 * 
	 * @return the center point.
	 */
	public static Point getCenterOfScreen() {

		// Create a temporary frame in the center of the screen.
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolKit.getScreenSize();
		int middleX = screenSize.width / 2;
		int middleY = screenSize.height / 2;
		return new Point(middleX, middleY);
	}

	/**
	 * Inform the screen width.
	 * 
	 * @return The screen width.
	 */
	public static int getScreenWidth() {

		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolKit.getScreenSize();
		return screenSize.width;
	}

	/**
	 * Inform the screen height.
	 * 
	 * @return The screen height.
	 */
	public static int getScreenHeight() {

		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolKit.getScreenSize();
		return screenSize.height;
	}

	/**
	 * Retrieve an empty frame centered in the screen.
	 * 
	 * @return the frame.
	 */
	public static JFrame retrieveCenteredTemporaryFrame() {

		JFrame tempFrame = new JFrame();
		tempFrame.setLocation(getCenterOfScreen().x, getCenterOfScreen().y);
		return tempFrame;
	}

	/**
	 * Load an image.
	 * 
	 * @param imageLocation
	 *            The image location.
	 * @return The image.
	 */
	public static ImageIcon loadImage(String imageLocation) {

		if (imageLocation == null)
			return null;

		// Retrieve the icon
		URL iconURL = ClassLoader.getSystemClassLoader().getResource(
				imageLocation);
		if (iconURL != null) {
			return new ImageIcon(iconURL);
		}

		ExceptionSupport.handleException(InternalResource.get(
				InternalResource.IMAGE_FILE_NOT_FOUND, imageLocation));

		return null;
	}

	/**
	 * Load an image resource into a label.
	 * 
	 * @param resourceLocation
	 *            The resource location.
	 * @return The label.
	 */
	public static JLabel loadImageIntoLabel(String resourceLocation) {

		URL url = ClassLoader.getSystemClassLoader().getResource(
				resourceLocation);

		BufferedImage image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException exception) {
			ExceptionSupport.handleException(exception);
		}

		ImageIcon icon = new ImageIcon(image);

		return new JLabel(icon);
	}

	/**
	 * Modify the position of a window.
	 * 
	 * @param position
	 *            The position bean.
	 */
	public static void setWindowPosition(JComponent component,
			WindowPositionBean position) {

		component.setLocation(position.getX(), position.getY());
		component.setSize(position.getWidth(), position.getHeight());
	}

	/**
	 * Modify the position of a window.
	 * 
	 * @param desktop
	 *            The internal frame desktop.
	 * @param component
	 *            The internal component.
	 * @param position
	 *            The position bean.
	 */
	public static void setWindowPosition(JDesktopPane desktop,
			JComponent component, InternalWindowPositionBean position) {

		setWindowPosition(component, position);

		desktop.setComponentZOrder(component, position.getZ());
	}

	/**
	 * Generates a panel with a title on the top.
	 * 
	 * @param title
	 *            The panel title.
	 * @param font
	 *            Optional font to be used.
	 * @param color
	 *            Optional color.
	 * @param panel
	 *            The panel to receive a title.
	 * @return The panel with title.
	 */
	public static JPanel addTitleToJPanel(String title, JPanel panel,
			Font font, Color color) {

		// Create a title with optional font and color.
		JLabel titleLabel = new JLabel(title);
		if (font != null) {
			titleLabel.setFont(font);
		}
		if (color != null) {
			titleLabel.setForeground(color);
		}

		JPanel centeredTitlePanel = new JPanel();
		centeredTitlePanel.add(titleLabel);

		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.add(centeredTitlePanel, BorderLayout.CENTER);

		// To center the centered panel.
		titlePanel.add(new JPanel(), BorderLayout.WEST);
		titlePanel.add(new JPanel(), BorderLayout.EAST);

		JPanel finalPanel = new JPanel(new BorderLayout());
		finalPanel.add(titlePanel, BorderLayout.NORTH);
		finalPanel.add(panel, BorderLayout.CENTER);

		return finalPanel;
	}

	/**
	 * Provide a label that has an automatic calendar.
	 * 
	 * @param font
	 *            The font to be used.
	 * @return The JLabel with the calendar.
	 */
	public static JLabel getClock(Font font) {

		// Only one instance is available to be used.
		if (calendarAlreadyUsed) {
			throw new InstanceAlreadyUsedException();
		}

		calendarLabel = new JLabel();

		calendarLabel.setFont(font);

		Timer calendarTimer = new Timer(1000, new ActionListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {

				String calendarText = StringSupport.getCurrentDateText();

				String finalCalendarText = new String();
				for (int i = 0; i < calendarText.length(); i++) {

					char thisCharacter = calendarText.charAt(i);

					if (Character.isDigit(thisCharacter)) {
						finalCalendarText += "<strong>" + thisCharacter //$NON-NLS-1$
								+ "</strong>"; //$NON-NLS-1$
					} else {
						finalCalendarText += thisCharacter;
					}
				}
				finalCalendarText = "<html>" + finalCalendarText + "</html>"; //$NON-NLS-1$ //$NON-NLS-2$
				calendarLabel.setText(finalCalendarText);

			}
		});

		calendarTimer.start();

		// The only available instance is used.
		calendarAlreadyUsed = true;

		return calendarLabel;

	}

	/**
	 * Try to play a WAV file.
	 * 
	 * @param resourceParameter
	 *            The resource pointing to the WAV file.
	 */
	public static void tryToPlayWavFile(String resourceParameter) {

		resource = resourceParameter;

		Thread soundThread = new Thread(new Runnable() {

			@Override
			public void run() {

				try (@SuppressWarnings("synthetic-access")
				AudioInputStream audioInput = AudioSystem
						.getAudioInputStream(ClassLoader.getSystemClassLoader()
								.getResource(resource))) {

					try (Clip clip = AudioSystem.getClip()) {

						clip.open(audioInput);

						clip.start();
					}

				} catch (UnsupportedAudioFileException exeption) {
					// Ignore.
				} catch (IOException exeption) {
					// Ignore.
				} catch (LineUnavailableException exeption) {
					// Ignore.
				}

			}

		});

		soundThread.start();
	}

	/**
	 * Block the copy and paste of all text components. If affects all
	 * application.
	 * 
	 * @param textArea
	 *            the text area.
	 */
	public static void setNoCopyAndPasteKeyBindings(JTextArea textArea) {

		JTextComponent.KeyBinding[] newBindings = {
				new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(
						KeyEvent.VK_C, InputEvent.CTRL_MASK),
						DefaultEditorKit.beepAction),
				new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(
						KeyEvent.VK_V, InputEvent.CTRL_MASK),
						DefaultEditorKit.beepAction),
				new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(
						KeyEvent.VK_X, InputEvent.CTRL_MASK),
						DefaultEditorKit.beepAction) };

		Keymap keyMap = textArea.getKeymap();

		JTextComponent.loadKeymap(keyMap, newBindings, textArea.getActions());
	}

	/**
	 * Load an image.
	 * 
	 * @param location
	 *            The image location.
	 * @return The image.
	 */
	public static BufferedImage loadBufferedImage(String location) {

		URL url = ClassLoader.getSystemClassLoader().getResource(location);

		BufferedImage image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			// Do nothing.
		}

		return image;
	}

	/**
	 * Provide a no selectable caret.
	 * 
	 * @return A no selectable caret.
	 */
	public static Caret getNonSelectableCaret() {

		return new Caret() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.swing.text.Caret#install(javax.swing.text.JTextComponent)
			 */
			@Override
			public void install(JTextComponent c) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.swing.text.Caret#deinstall(javax.swing.text.JTextComponent)
			 */
			@Override
			public void deinstall(JTextComponent c) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#paint(java.awt.Graphics)
			 */
			@Override
			public void paint(Graphics g) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seejavax.swing.text.Caret#addChangeListener(javax.swing.event.
			 * ChangeListener)
			 */
			@Override
			public void addChangeListener(ChangeListener l) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.swing.text.Caret#removeChangeListener(javax.swing.event
			 * .ChangeListener)
			 */
			@Override
			public void removeChangeListener(ChangeListener l) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#isVisible()
			 */
			@Override
			public boolean isVisible() {
				return false;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#setVisible(boolean)
			 */
			@Override
			public void setVisible(boolean v) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#isSelectionVisible()
			 */
			@Override
			public boolean isSelectionVisible() {
				// Do nothing.
				return false;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#setSelectionVisible(boolean)
			 */
			@Override
			public void setSelectionVisible(boolean v) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#setMagicCaretPosition(java.awt.Point)
			 */
			@Override
			public void setMagicCaretPosition(Point p) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#getMagicCaretPosition()
			 */
			@Override
			public Point getMagicCaretPosition() {
				return new Point(0, 0);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#setBlinkRate(int)
			 */
			@Override
			public void setBlinkRate(int rate) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#getBlinkRate()
			 */
			@Override
			public int getBlinkRate() {
				return 10000;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#getDot()
			 */
			@Override
			public int getDot() {
				return 0;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#getMark()
			 */
			@Override
			public int getMark() {
				return 0;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#setDot(int)
			 */
			@Override
			public void setDot(int dot) {
				// Do nothing.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.text.Caret#moveDot(int)
			 */
			@Override
			public void moveDot(int dot) {
				// Do nothing.
			}

		};
	}

	/**
	 * Convert the first character of a string into a key event.
	 * 
	 * @param character
	 *            The string containing the character to be converted to key
	 *            event.
	 * @return The key event.
	 */
	public static int getKeyEvent(String character) {

		if (character == null || "".equals(character.trim())) { //$NON-NLS-1$

			return 0;
		}

		switch (Character.toUpperCase(character.charAt(0))) {
		case 'A': {
			return KeyEvent.VK_A;
		}
		case 'B': {
			return KeyEvent.VK_B;
		}
		case 'C': {
			return KeyEvent.VK_C;
		}
		case 'D': {
			return KeyEvent.VK_D;
		}
		case 'E': {
			return KeyEvent.VK_E;
		}
		case 'F': {
			return KeyEvent.VK_F;
		}
		case 'G': {
			return KeyEvent.VK_G;
		}
		case 'H': {
			return KeyEvent.VK_H;
		}
		case 'I': {
			return KeyEvent.VK_I;
		}
		case 'J': {
			return KeyEvent.VK_J;
		}
		case 'K': {
			return KeyEvent.VK_K;
		}
		case 'L': {
			return KeyEvent.VK_L;
		}
		case 'M': {
			return KeyEvent.VK_M;
		}
		case 'N': {
			return KeyEvent.VK_N;
		}
		case 'O': {
			return KeyEvent.VK_O;
		}
		case 'P': {
			return KeyEvent.VK_P;
		}
		case 'Q': {
			return KeyEvent.VK_Q;
		}
		case 'R': {
			return KeyEvent.VK_R;
		}
		case 'S': {
			return KeyEvent.VK_S;
		}
		case 'T': {
			return KeyEvent.VK_T;
		}
		case 'U': {
			return KeyEvent.VK_U;
		}
		case 'V': {
			return KeyEvent.VK_V;
		}
		case 'W': {
			return KeyEvent.VK_W;
		}
		case 'X': {
			return KeyEvent.VK_X;
		}
		case 'Y': {
			return KeyEvent.VK_Y;
		}
		case 'Z': {
			return KeyEvent.VK_Z;
		}
		default:
			// Ignore.
			break;
		}

		return 0;
	}

	/**
	 * Returns a TreePath containing the specified node.
	 * 
	 * @param nodeParameter
	 *            A specific node.
	 * @return The tree path.
	 */
	public static TreePath getTreePath(TreeNode nodeParameter) {

		TreeNode node = nodeParameter;

		List<TreeNode> list = new ArrayList<>();

		// Get all nodes to the root of the tree.
		while (node != null) {
			list.add(node);
			node = node.getParent();
		}

		Collections.reverse(list);

		// Convert the list into a TreePath
		return new TreePath(list.toArray());
	}

	/**
	 * Generates a table with simple information on a table format.
	 * 
	 * @param dataParameter
	 *            The table data.
	 * @param headerParameter
	 *            The table header.
	 * @param position
	 *            the position o the text on the columns.
	 * @return The panel with the information required.
	 */
	public static JPanel getViewTable(String[][] dataParameter,
			String[] headerParameter, int[] position) {

		/**
		 * Defines a standard view table model.
		 */
		class ViewTableModel extends DefaultTableModel {

			private static final long serialVersionUID = 1L;

			private String[] header = null;
			private String[][] data = null;

			/**
			 * Constructor.
			 * 
			 * @param data
			 *            The table content.
			 * @param header
			 *            The table header.
			 */
			ViewTableModel(String[][] dataParameterInstance,
					String[] headerParameterInstance) {

				this.data = dataParameterInstance;
				this.header = headerParameterInstance;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
			 */
			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.DefaultTableModel#getColumnCount()
			 */
			@Override
			public int getColumnCount() {

				return this.header == null ? 0 : this.header.length;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.DefaultTableModel#getColumnName(int)
			 */
			@Override
			public String getColumnName(int column) {

				return this.header[column];
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.DefaultTableModel#getRowCount()
			 */
			@Override
			public int getRowCount() {

				return this.data == null ? 0 : this.data.length;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
			 */
			@Override
			public Object getValueAt(int row, int column) {

				return this.data[row][column];
			}

		}

		/**
		 * A table to show static information.
		 */
		class ViewTable extends JTable {

			private static final long serialVersionUID = 1L;

			/**
			 * Constructor.
			 * 
			 * @param model
			 *            The table model.
			 */
			public ViewTable(TableModel model) {

				super(model);

			}
		}

		ViewTableModel model = new ViewTableModel(dataParameter,
				headerParameter);
		ViewTable table = new ViewTable(model);

		for (int i = 0; i < headerParameter.length; i++) {
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(position[i]);
			table.getColumn(headerParameter[i]).setCellRenderer(dtcr);
		}

		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setFocusable(false);
		table.setDragEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		for (int i = 0; i < headerParameter.length; i++) {

			int maximumLength = new JLabel(headerParameter[i])
					.getPreferredSize().width;

			for (int j = 0; j < dataParameter.length; j++) {

				JLabel cell = new JLabel(dataParameter[j][i]);
				int cellWidth = cell.getPreferredSize().width;
				if (maximumLength < cellWidth) {
					maximumLength = cellWidth;
				}

			}

			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(maximumLength + 4);

		}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(table, BorderLayout.CENTER);
		return tablePanel;
	}

	/**
	 * Validation interface.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public interface TextValidation {

		boolean valid(String text);
	}

	/**
	 * Text changed interface.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public interface TextListener {

		void textChanged();
	}

	/**
	 * Define a maximum size of a text field.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	public static class JTextFieldValidation extends PlainDocument {

		private static final long serialVersionUID = 1L;

		private int limit;

		private TextValidation validation = null;

		private JTextField textField = null;

		private final Color RED = new Color(255, 200, 200);
		private final Color GREEN = new Color(200, 255, 200);

		private boolean valid = false;

		private TextListener textListener = null;

		/**
		 * Constructor.
		 * 
		 * @param limit
		 *            The maximum length.
		 * @param textFieldParameter
		 *            The text field.
		 * @param validationParameter
		 *            The validation class.
		 */
		JTextFieldValidation(int limit, JTextField textFieldParameter,
				TextValidation validationParameter) {

			super();

			this.limit = limit;
			this.textField = textFieldParameter;
			this.validation = validationParameter;

			validate(new StringBuffer());
		}

		/**
		 * Set the text change listener.
		 * 
		 * @param listenerParameter
		 *            The text change listener.
		 */
		public void setTextListener(TextListener listenerParameter) {

			this.textListener = listenerParameter;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.text.AbstractDocument#remove(int, int)
		 */
		@Override
		public void remove(int offs, int len) throws BadLocationException {

			StringBuffer previousText = new StringBuffer(
					getText(0, getLength()));
			previousText.delete(offs, offs + len);

			validate(previousText);

			super.remove(offs, len);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.text.AbstractDocument#replace(int, int,
		 * java.lang.String, javax.swing.text.AttributeSet)
		 */
		@Override
		public void replace(int offset, int length, String text,
				AttributeSet attrs) throws BadLocationException {

			StringBuffer previousText = new StringBuffer(
					getText(0, getLength()));
			previousText.replace(offset, offset + length, text);
			if (previousText.length() <= this.limit) {

				validate(previousText);
			}

			super.replace(offset, length, text, attrs);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.text.PlainDocument#insertString(int,
		 * java.lang.String, javax.swing.text.AttributeSet)
		 */
		@Override
		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {

			if (str == null) {
				return;
			}

			if ((getLength() + str.length()) <= this.limit) {

				StringBuffer previousText = new StringBuffer(getText(0,
						getLength()));
				previousText.insert(offset, str);

				validate(previousText);

				super.insertString(offset, str, attr);
			}
		}

		/**
		 * Validate the text.
		 * 
		 * @param previousText
		 *            The text.
		 */
		private void validate(StringBuffer previousText) {

			if (this.validation != null && this.textField != null) {

				if (this.validation.valid(previousText.toString())) {
					this.textField.setBackground(this.GREEN);
					this.valid = true;
				} else {
					this.textField.setBackground(this.RED);
					this.valid = false;
				}
			}

			if (this.textListener != null) {

				this.textListener.textChanged();
			}
		}

		/**
		 * Indicate if the field is valid.
		 * 
		 * @return the valid
		 */
		public boolean isValid() {

			return this.valid;
		}
	}

	/**
	 * Provide a document that limit the length of a text field.
	 * 
	 * @param limit
	 *            The maximum number of characters.
	 * @param textFieldParameter
	 *            The text field.
	 * @param validationParameter
	 *            The validation class.
	 * @return The class the implements the maximum length of a document.
	 */
	public static JTextFieldValidation getFieldValidation(int limit,
			JTextField textFieldParameter, TextValidation validationParameter) {

		return new JTextFieldValidation(limit, textFieldParameter,
				validationParameter);
	}

	/**
	 * Center a window on the screen.
	 * 
	 * @param window
	 *            The window.
	 */
	public static void centerOnScreen(Window window) {

		Toolkit toolkit = window.getToolkit();

		Dimension dimension = toolkit.getScreenSize();

		Dimension windowsDimension = window.getSize();

		int i = (dimension.width - windowsDimension.width) / 2;
		int j = (dimension.height - windowsDimension.height) / 2;

		window.setLocation(i, j);

	}

	/**
	 * Limit the size of the window by a percentage of the screen.
	 * 
	 * @param window
	 *            The window.
	 * @param percentage
	 *            a percentage from 10% to 100%.
	 */
	/**
	 * @param window
	 */
	public static void limitWindowByPercentageOfScreen(Window window,
			double percentage) {

		if (percentage < 10 || percentage > 100) {

			return;
		}

		Toolkit toolkit = window.getToolkit();

		Dimension dimension = toolkit.getScreenSize();

		int maximumWidth = (int) (dimension.width * percentage / 100d);
		int maximumHeight = (int) (dimension.height * percentage / 100d);

		int windowWidth = window.getWidth() > maximumWidth ? maximumWidth
				: window.getWidth();
		int windowHeight = window.getHeight() > maximumHeight ? maximumHeight
				: window.getHeight();

		window.setSize(windowWidth, windowHeight);

	}

	/**
	 * Verify if a component is using the entire screen size.
	 * 
	 * @param component
	 *            The component.
	 * @return true if it is using the entire screen size.
	 */
	public static boolean isMaximumDimension(Component component) {

		Point location = component.getLocation();
		Dimension componentDimension = component.getSize();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		return (location.getX() == 0 && location.getY() == 0
				&& componentDimension.getHeight() == screenSize.getHeight() && componentDimension
					.getWidth() == screenSize.getWidth());

	}
}
