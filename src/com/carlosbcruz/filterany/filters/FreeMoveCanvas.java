package com.carlosbcruz.filterany.filters;

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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.carlosbcruz.util.ExceptionSupport;

/**
 * Provides a window to allow the free movement of characters.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public abstract class FreeMoveCanvas extends JPanel implements MouseListener,
		MouseMotionListener, CharacterMoveArrowListenerInterface {

	private static final long serialVersionUID = 1L;

	// Colors used on the panel.
	private static final Color SELECTED_TEXT_COLOR = new Color(150, 255, 150,
			150);
	private static final Color MOUSE_SELECTION_BOX = new Color(150, 150, 255,
			150);
	private static final Color MOUSE_DESELECTION_BOX = new Color(255, 150, 150,
			150);

	final Font TEXT_FONT = new Font("Lucida Console", Font.PLAIN, 11); //$NON-NLS-1$

	// The box surrounding the text has a delta from top left position.
	static final int TEXT_BOX_DELTA_X = 60;
	static final int TEXT_BOX_DELTA_Y = 20;

	// A small adjustment to the right border of the text.
	static final int RIGHT_BORDER_ADJUSTMENT = 20;

	// The text has some space from the top left position of the surrounding
	// box.
	static final int INITIAL_WIDTH = 20;
	static final int INITIAL_HEIGHT = 20;

	// It is possible to control how distant is the lines between each other.
	static final int SPACE_BETWEEN_LINES = 5;

	static final int ELEMENT_BOX_Y_START_ADJUSTEMENT = 4;
	static final int ELEMENT_BOX_Y_END_ADJUSTEMENT = 12;

	// Position of the mouse.
	private int mouseXStart = 0;
	private int mouseYStart = 0;
	private int mouseXEnd = 0;
	private int mouseYEnd = 0;
	// The mouse coordinates are converted to a normal square
	// where the start is on left top position.
	private int mouseNormalizedXStart = 0;
	private int mouseNormalizedYStart = 0;
	private int mouseNormalizedXEnd = 0;
	private int mouseNormalizedYEnd = 0;
	// The dimension of the selected box.
	private int selectionBoxWidth = 0;
	private int selectionBoxHeight = 0;

	// Mouse indication.
	private boolean beingDragged = false;
	// Indication if it is to select or deselect.
	private boolean deselectContent = false;

	// The canvas size.
	int canvasWidth = 0;
	int canvasHeight = 0;

	// The elements being controlled.
	private ArrayList<ArrayList<SelectedElement>> lines = null;

	private ArrayList<SelectedElement> lineNumbers = null;

	// Controls to generate the text.
	int currentLineUsed = 0;
	int currentColumnUsed = 0;

	FontMetrics fontMetricsUsed = null;
	int fontHeight = 0;

	/**
	 * Default constructor.
	 */
	public FreeMoveCanvas(StringBuffer contentParameter) {

		// Initialize the variable because they are used by subclasses.
		this.currentLineUsed = TEXT_BOX_DELTA_Y + INITIAL_WIDTH;
		this.currentColumnUsed = TEXT_BOX_DELTA_X + INITIAL_HEIGHT;
		this.fontMetricsUsed = getFontMetrics(this.TEXT_FONT);
		this.fontHeight = this.fontMetricsUsed.getAscent();

		this.lines = getElements(contentParameter);

		createLineNumbers();

		setBackground(Color.white);

		addMouseListener(this);
		addMouseMotionListener(this);
		CharacterMoveKeyListener.setArrowListener(this);
	}

	/**
	 * Add a text to a element.
	 * 
	 * @param element
	 *            The text.
	 * @return The element.
	 */
	SelectedElement addElement(String element) {

		SelectedElement word = new SelectedElement();

		word.setText(element);

		word.setX(this.currentColumnUsed);
		word.setY(this.currentLineUsed);

		word.setBoxXStart(this.currentColumnUsed);
		word.setBoxYStart(this.currentLineUsed - ELEMENT_BOX_Y_END_ADJUSTEMENT);

		// All characters should have the same size of letter A.
		this.currentColumnUsed += this.fontMetricsUsed.stringWidth("A"); //$NON-NLS-1$

		word.setBoxXEnd(this.currentColumnUsed);
		word.setBoxYEnd(this.currentLineUsed + ELEMENT_BOX_Y_START_ADJUSTEMENT);

		if (this.currentColumnUsed > this.canvasWidth) {
			this.canvasWidth = this.currentColumnUsed;
		}

		return word;
	}

	/**
	 * Create the line numbers.
	 */
	private void createLineNumbers() {

		// Initialize again.
		this.currentLineUsed = TEXT_BOX_DELTA_Y + INITIAL_WIDTH;
		this.currentColumnUsed = INITIAL_HEIGHT;

		FontMetrics fontMetrics = getFontMetrics(this.TEXT_FONT);

		this.lineNumbers = new ArrayList<>();

		for (int i = 1; i <= this.lines.size(); i++) {

			String element = String.valueOf(i);

			SelectedElement word = new SelectedElement();

			word.setText(element);

			word.setX(this.currentColumnUsed);
			word.setY(this.currentLineUsed);

			word.setBoxXStart(this.currentColumnUsed);
			word.setBoxYStart(this.currentLineUsed
					- ELEMENT_BOX_Y_END_ADJUSTEMENT);

			this.currentColumnUsed += fontMetrics.stringWidth(element);

			word.setBoxXEnd(this.currentColumnUsed);
			word.setBoxYEnd(this.currentLineUsed
					+ ELEMENT_BOX_Y_START_ADJUSTEMENT);

			this.lineNumbers.add(word);

			this.currentLineUsed += this.fontHeight + SPACE_BETWEEN_LINES;
			this.currentColumnUsed = INITIAL_HEIGHT;
		}
	}

	/**
	 * Provide the elements to be used on the free selection canvas.
	 * 
	 * @param contentParameter
	 *            The text.
	 * @return The elements to be used.
	 */
	abstract ArrayList<ArrayList<SelectedElement>> getElements(
			StringBuffer contentParameter);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {

		return new Dimension(TEXT_BOX_DELTA_X * 2 + this.canvasWidth,
				TEXT_BOX_DELTA_Y * 2 + this.canvasHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);

		graphics.setColor(Color.gray);

		// Clear the background.
		graphics.drawRect(TEXT_BOX_DELTA_X, TEXT_BOX_DELTA_Y, this.canvasWidth
				- TEXT_BOX_DELTA_X + RIGHT_BORDER_ADJUSTMENT, this.canvasHeight
				- TEXT_BOX_DELTA_Y);

		// Draw the elements and selection boxes.
		graphics.setFont(this.TEXT_FONT);
		for (ArrayList<SelectedElement> line : this.lines) {
			for (SelectedElement element : line) {

				drawElement(graphics, element);
			}
		}

		for (SelectedElement element : this.lineNumbers) {

			drawElement(graphics, element);
		}

		// If being dragged then draw the selection box.
		if (this.beingDragged) {

			// If the shift is pressed then the color is different.
			if (this.deselectContent) {
				graphics.setColor(MOUSE_DESELECTION_BOX);
			} else {
				graphics.setColor(MOUSE_SELECTION_BOX);
			}

			normalizeCoordinates();

			// Draw the rectangle.
			graphics.fillRect(this.mouseNormalizedXStart,
					this.mouseNormalizedYStart, this.selectionBoxWidth,
					this.selectionBoxHeight);

		}

		graphics.setColor(Color.black);

	}

	/**
	 * Draw a specific element.
	 * 
	 * @param graphics
	 *            The graphics.
	 * @param element
	 *            The element.
	 */
	private static void drawElement(Graphics graphics, SelectedElement element) {

		// Draw the element.
		if (element.isVisible()) {
			graphics.setColor(Color.black);
		} else {
			graphics.setColor(Color.gray);
		}

		graphics.drawString(element.getText(), element.getX(), element.getY());

		// If it is selected then draw the surrounding box.
		if (element.isSelectedWord()) {

			graphics.setColor(SELECTED_TEXT_COLOR);
			graphics.fillRect(element.getBoxXStart(), element.getBoxYStart(),
					element.getWidth(), element.getHeight());
		}
	}

	/**
	 * Adjust the coordinates.
	 */
	private void normalizeCoordinates() {

		if (this.mouseXStart < this.mouseXEnd
				&& this.mouseYStart < this.mouseYEnd) {

			this.mouseNormalizedXStart = this.mouseXStart;
			this.mouseNormalizedYStart = this.mouseYStart;
			this.mouseNormalizedXEnd = this.mouseXEnd;
			this.mouseNormalizedYEnd = this.mouseYEnd;
		}

		if (this.mouseXStart < this.mouseXEnd
				&& this.mouseYStart >= this.mouseYEnd) {

			this.mouseNormalizedXStart = this.mouseXStart;
			this.mouseNormalizedYStart = this.mouseYEnd;
			this.mouseNormalizedXEnd = this.mouseXEnd;
			this.mouseNormalizedYEnd = this.mouseYStart;
		}

		if (this.mouseXStart >= this.mouseXEnd
				&& this.mouseYStart < this.mouseYEnd) {

			this.mouseNormalizedXStart = this.mouseXEnd;
			this.mouseNormalizedYStart = this.mouseYStart;
			this.mouseNormalizedXEnd = this.mouseXStart;
			this.mouseNormalizedYEnd = this.mouseYEnd;
		}

		if (this.mouseXStart >= this.mouseXEnd
				&& this.mouseYStart >= this.mouseYEnd) {

			this.mouseNormalizedXStart = this.mouseXEnd;
			this.mouseNormalizedYStart = this.mouseYEnd;
			this.mouseNormalizedXEnd = this.mouseXStart;
			this.mouseNormalizedYEnd = this.mouseYStart;
		}

		this.selectionBoxWidth = this.mouseNormalizedXEnd
				- this.mouseNormalizedXStart;
		this.selectionBoxHeight = this.mouseNormalizedYEnd
				- this.mouseNormalizedYStart;

	}

	/**
	 * Verify if a point is within the selection.
	 * 
	 * @param x
	 *            The X position.
	 * @param y
	 *            The Y position.
	 * @return True if the point is within the selection.
	 */
	private boolean isCoordinateWithinSelection(int x, int y) {

		// Verify if any word coordinate is within the selection.
		if (x >= this.mouseNormalizedXStart && x <= this.mouseNormalizedXEnd
				&& y >= this.mouseNormalizedYStart
				&& y <= this.mouseNormalizedYEnd) {
			return true;
		}

		return false;
	}

	/**
	 * Indicate if the selection is entirely inside the coordinate.
	 * 
	 * @param x
	 *            The point x.
	 * @param y
	 *            The point y.
	 * @param coordinateStartX
	 *            The coordinate start X.
	 * @param coordinateEndX
	 *            The coordinate end X.
	 * @param coordinateStartY
	 *            The coordinate start Y.
	 * @param coordinateEndY
	 *            The coordinate end Y.
	 * @return True if the selection is entirely inside the coordinate.
	 */
	private static boolean isPointWithinCoordinate(int x, int y,
			int coordinateStartX, int coordinateEndX, int coordinateStartY,
			int coordinateEndY) {

		if (x >= coordinateStartX && x <= coordinateEndX
				&& y >= coordinateStartY && y <= coordinateEndY) {
			return true;
		}
		return false;

	}

	/**
	 * Indicate if the selection is entirely inside the coordinate.
	 * 
	 * @param coordinateStartX
	 *            The coordinate start X.
	 * @param coordinateEndX
	 *            The coordinate end X.
	 * @param coordinateStartY
	 *            The coordinate start Y.
	 * @param coordinateEndY
	 *            The coordinate end Y.
	 * @return True if the selection is entirely inside the coordinate.
	 */
	private boolean isAnySelectionPointWithinCoordinate(int coordinateStartX,
			int coordinateEndX, int coordinateStartY, int coordinateEndY) {

		if (isPointWithinCoordinate(this.mouseNormalizedXStart,
				this.mouseNormalizedYStart, coordinateStartX, coordinateEndX,
				coordinateStartY, coordinateEndY)) {
			return true;
		}

		if (isPointWithinCoordinate(this.mouseNormalizedXStart,
				this.mouseNormalizedYEnd, coordinateStartX, coordinateEndX,
				coordinateStartY, coordinateEndY)) {
			return true;
		}

		if (isPointWithinCoordinate(this.mouseNormalizedXEnd,
				this.mouseNormalizedYStart, coordinateStartX, coordinateEndX,
				coordinateStartY, coordinateEndY)) {
			return true;
		}

		if (isPointWithinCoordinate(this.mouseNormalizedXEnd,
				this.mouseNormalizedYEnd, coordinateStartX, coordinateEndX,
				coordinateStartY, coordinateEndY)) {
			return true;
		}

		return false;

	}

	/**
	 * Verify if there is a vertical intersection.
	 * 
	 * @param coordinateStartX
	 *            The coordinate start X.
	 * @param coordinateEndX
	 *            The coordinate end X.
	 * @param coordinateStartY
	 *            The coordinate start Y.
	 * @param coordinateEndY
	 *            The coordinate end Y.
	 * @return True if there is a vertical intersection.
	 */
	private boolean verifyVerticalInterseption(int coordinateStartX,
			int coordinateEndX, int coordinateStartY, int coordinateEndY) {

		if (this.mouseNormalizedXStart >= coordinateStartX
				&& this.mouseNormalizedXStart <= coordinateEndX
				&& this.mouseNormalizedXEnd >= coordinateStartX
				&& this.mouseNormalizedXEnd <= coordinateEndX) {

			if (this.mouseNormalizedYStart < coordinateStartY
					&& this.mouseNormalizedYEnd > coordinateEndY) {
				return true;
			}

		}

		return false;

	}

	/**
	 * Verify if there is a horizontal intersection.
	 * 
	 * @param coordinateStartX
	 *            The coordinate start X.
	 * @param coordinateEndX
	 *            The coordinate end X.
	 * @param coordinateStartY
	 *            The coordinate start Y.
	 * @param coordinateEndY
	 *            The coordinate end Y.
	 * @return True if there is a vertical intersection.
	 */
	private boolean verifyHorizontalInterseption(int coordinateStartX,
			int coordinateEndX, int coordinateStartY, int coordinateEndY) {

		if (this.mouseNormalizedYStart >= coordinateStartY
				&& this.mouseNormalizedYStart <= coordinateEndY
				&& this.mouseNormalizedYEnd >= coordinateStartY
				&& this.mouseNormalizedYEnd <= coordinateEndY) {

			if (this.mouseNormalizedXStart < coordinateStartX
					&& this.mouseNormalizedXEnd > coordinateEndX) {
				return true;
			}

		}

		return false;

	}

	/**
	 * Verify which elements were selected.
	 */
	private void verifySelection() {

		normalizeCoordinates();

		for (ArrayList<SelectedElement> line : this.lines) {
			for (SelectedElement element : line) {

				if (element.isVisible() && verifySelection(element)) {
					element.setSelectedWord(!this.deselectContent);
				}
			}
		}

		for (SelectedElement element : this.lineNumbers) {

			if (verifySelection(element)) {

				element.setSelectedWord(!this.deselectContent);

				int lineNumber = Integer.parseInt(element.getText());

				ArrayList<SelectedElement> selectedLine = this.lines
						.get(lineNumber - 1);

				for (SelectedElement internalElement : selectedLine) {

					if (internalElement.isVisible()) {
						internalElement.setSelectedWord(!this.deselectContent);
					}
				}
			}
		}
	}

	/**
	 * Verify if an element is selected.
	 * 
	 * @param element
	 *            The element.
	 */
	private boolean verifySelection(SelectedElement element) {

		if (isCoordinateWithinSelection(element.getBoxXStart(),
				element.getBoxYStart())) {
			return true;
		}

		if (isCoordinateWithinSelection(element.getBoxXStart(),
				element.getBoxYEnd())) {
			return true;
		}

		if (isCoordinateWithinSelection(element.getBoxXEnd(),
				element.getBoxYStart())) {
			return true;
		}

		if (isCoordinateWithinSelection(element.getBoxXEnd(),
				element.getBoxYEnd())) {
			return true;
		}

		if (isAnySelectionPointWithinCoordinate(element.getBoxXStart(),
				element.getBoxXEnd(), element.getBoxYStart(),
				element.getBoxYEnd())) {
			return true;
		}

		if (verifyVerticalInterseption(element.getBoxXStart(),
				element.getBoxXEnd(), element.getBoxYStart(),
				element.getBoxYEnd())) {
			return true;
		}

		if (verifyHorizontalInterseption(element.getBoxXStart(),
				element.getBoxXEnd(), element.getBoxYStart(),
				element.getBoxYEnd())) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		// Do nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {

		this.mouseXEnd = event.getX();
		this.mouseYEnd = event.getY();

		if (this.mouseXEnd < 0) {
			this.mouseXEnd = 0;
		}

		if (this.mouseXEnd > getPreferredSize().width) {
			this.mouseXEnd = getPreferredSize().width;
		}

		if (this.mouseYEnd < 0) {
			this.mouseYEnd = 0;
		}

		if (this.mouseYEnd > getPreferredSize().height) {
			this.mouseYEnd = getPreferredSize().height;
		}

		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {

		this.mouseXEnd = event.getX();
		this.mouseYEnd = event.getY();

		if (this.mouseXEnd < 0) {
			this.mouseXEnd = 0;
		}

		if (this.mouseXEnd > getPreferredSize().width) {
			this.mouseXEnd = getPreferredSize().width;
		}

		if (this.mouseYEnd < 0) {
			this.mouseYEnd = 0;
		}

		if (this.mouseYEnd > getPreferredSize().height) {
			this.mouseYEnd = getPreferredSize().height;
		}

		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {

		this.deselectContent = (event.getButton() == MouseEvent.BUTTON3);

		this.mouseXStart = event.getX();
		this.mouseYStart = event.getY();

		this.beingDragged = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {

		this.deselectContent = (event.getButton() == MouseEvent.BUTTON3);

		this.beingDragged = false;
		this.mouseXEnd = event.getX();
		this.mouseYEnd = event.getY();
		verifySelection();
		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent event) {

		this.mouseXEnd = event.getX();
		this.mouseYEnd = event.getY();

		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent event) {

		this.beingDragged = false;
	}

	/**
	 * Provide the current selection.
	 * 
	 * @return the lines The selection.
	 */
	protected ArrayList<ArrayList<SelectedElement>> getLines() {

		return this.lines;
	}

	/**
	 * Provide the current selection.
	 * 
	 * @return the lines The selection.
	 */
	protected ArrayList<ArrayList<SelectedElement>> getSelectedLines() {

		ArrayList<ArrayList<SelectedElement>> selectedLines = new ArrayList<>();

		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> line = this.lines.get(i);

			ArrayList<SelectedElement> selectedLine = new ArrayList<>();

			for (int j = 0; j < line.size(); j++) {

				SelectedElement element = line.get(j);

				if (element.isSelectedWord()) {
					element.setSelectedWord(false);
					element.setVisible(false);
					selectedLine.add(element);
				}
			}

			selectedLines.add(selectedLine);
		}

		return selectedLines;
	}

	/**
	 * Remove any selection of the line number
	 */
	private void removeSelectionFromLineNumber() {

		for (SelectedElement element : this.lineNumbers) {
			element.setSelectedWord(false);
		}

	}

	/**
	 * Select all elements on all lines.
	 */
	protected void selecteAllElements() {

		// Select all elements.
		for (ArrayList<SelectedElement> line : this.lines) {
			for (SelectedElement element : line) {
				element.setSelectedWord(true);
			}
		}
	}

	/**
	 * Indicate that the go up arrow was pressed.
	 */
	@Override
	public void goUp() {
		moveUp();
		removeSelectionFromLineNumber();
	}

	/**
	 * Indicate that the go down arrow was pressed.
	 */
	@Override
	public void goDown() {
		moveDown();
		removeSelectionFromLineNumber();
	}

	/**
	 * Indicate that the go right arrow was pressed.
	 */
	@Override
	public void goRight() {
		moveRight();
		removeSelectionFromLineNumber();
	}

	/**
	 * Indicate that the go left arrow was pressed.
	 */
	@Override
	public void goLeft() {
		moveLeft();
		removeSelectionFromLineNumber();
	}

	/**
	 * Swap the internal content of the two elements.
	 * 
	 * @param firstElement
	 *            The first element.
	 * @param secondElement
	 *            The second element.
	 */
	private static void swapContent(SelectedElement firstElement,
			SelectedElement secondElement) {

		String auxiliar = firstElement.getText();
		boolean auxiliarFlag = firstElement.isSelectedWord();
		boolean shouldBeRemoved = firstElement.isShouldBeRemoved();

		firstElement.setText(secondElement.getText());
		firstElement.setSelectedWord(secondElement.isSelectedWord());
		firstElement.setShouldBeRemoved(secondElement.isShouldBeRemoved());

		secondElement.setText(auxiliar);
		secondElement.setSelectedWord(auxiliarFlag);
		secondElement.setShouldBeRemoved(shouldBeRemoved);
	}

	/**
	 * Copy one element to the other.
	 * 
	 * @param from
	 *            The element to be copied.
	 * @param to
	 *            The element to receive the copy.
	 */
	private static void copyContentOnly(SelectedElement from, SelectedElement to) {

		to.setText(from.getText());
		to.setSelectedWord(from.isSelectedWord());
		to.setShouldBeRemoved(from.isShouldBeRemoved());
	}

	/**
	 * Create an empty column on the right.
	 */
	@SuppressWarnings("null")
	private void createColumnOnTheRight() {

		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> line = this.lines.get(i);

			SelectedElement lastElement = line.get(line.size() - 1);

			SelectedElement clone = null;
			try {
				clone = (SelectedElement) lastElement.clone();
			} catch (CloneNotSupportedException exception) {
				ExceptionSupport.handleException(null, "TO CORRECT"); //$NON-NLS-1$
			}

			clone.setText(" "); //$NON-NLS-1$
			clone.setSelectedWord(false);

			int currentColumn = lastElement.getX();
			int currentLine = lastElement.getY();
			int newColumn = currentColumn
					+ this.fontMetricsUsed.stringWidth(lastElement.getText());

			clone.setBoxXStart(newColumn);
			clone.setBoxXEnd(newColumn
					+ this.fontMetricsUsed.stringWidth(lastElement.getText()));
			clone.setBoxYStart(currentLine - ELEMENT_BOX_Y_END_ADJUSTEMENT);
			clone.setBoxYEnd(currentLine + ELEMENT_BOX_Y_START_ADJUSTEMENT);

			clone.setX(newColumn);
			clone.setY(currentLine);

			if (newColumn > this.canvasWidth) {
				this.canvasWidth = newColumn;
			}

			line.add(clone);
		}
	}

	/**
	 * Create a row at the end of the text.
	 */
	@SuppressWarnings("null")
	private void createRowAtTheEnd() {

		ArrayList<SelectedElement> newLine = new ArrayList<>();
		ArrayList<SelectedElement> lastLine = this.lines
				.get(this.lines.size() - 1);

		int numberOfElements = lastLine.size();

		for (int i = 0; i < numberOfElements; i++) {

			SelectedElement newElement = null;
			try {
				newElement = (SelectedElement) lastLine.get(i).clone();
			} catch (CloneNotSupportedException exception) {
				ExceptionSupport.handleException(null, "TO CORRECT"); //$NON-NLS-1$
			}

			newElement.setText(" "); //$NON-NLS-1$
			newLine.add(newElement);
		}

		SelectedElement lastLineNumber = this.lineNumbers.get(this.lineNumbers
				.size() - 1);

		SelectedElement newLastLineNumber = null;
		try {
			newLastLineNumber = (SelectedElement) lastLineNumber.clone();
		} catch (CloneNotSupportedException exception) {
			ExceptionSupport.handleException(null, "TO CORRECT"); //$NON-NLS-1$
		}
		newLastLineNumber.setText(String.valueOf(this.lineNumbers.size() + 1));

		for (int i = 0; i < numberOfElements; i++) {

			SelectedElement element = newLine.get(i);

			element.setY(this.currentLineUsed);

			element.setBoxYStart(this.currentLineUsed
					- ELEMENT_BOX_Y_END_ADJUSTEMENT);
			element.setBoxYEnd(this.currentLineUsed
					+ ELEMENT_BOX_Y_START_ADJUSTEMENT);

		}

		newLastLineNumber.setY(this.currentLineUsed);
		newLastLineNumber.setBoxYStart(this.currentLineUsed
				- ELEMENT_BOX_Y_END_ADJUSTEMENT);
		newLastLineNumber.setBoxYEnd(this.currentLineUsed
				+ ELEMENT_BOX_Y_START_ADJUSTEMENT);

		this.currentLineUsed += this.fontHeight + SPACE_BETWEEN_LINES;

		if (this.currentLineUsed > this.canvasHeight) {
			this.canvasHeight = this.currentLineUsed;
		}

		this.lines.add(newLine);
		this.lineNumbers.add(newLastLineNumber);
	}

	/**
	 * Copy the content from one line to the other.
	 * 
	 * @param from
	 *            The line to be copied.
	 * @param to
	 *            The line that will be equal to the other.
	 */
	private static void copyLines(ArrayList<SelectedElement> from,
			ArrayList<SelectedElement> to) {

		for (int i = 0; i < from.size(); i++) {

			copyContentOnly(from.get(i), to.get(i));
		}
	}

	/**
	 * Move the entire text one line below.
	 */
	private void moveContentOneLineBelow() {

		for (int i = this.lines.size() - 2; i >= 0; i--) {

			copyLines(this.lines.get(i), this.lines.get(i + 1));
		}
	}

	/**
	 * Set the line to spaces.
	 * 
	 * @param line
	 *            The line.
	 */
	private static void emptyLine(ArrayList<SelectedElement> line) {

		for (int i = 0; i < line.size(); i++) {
			SelectedElement element = line.get(i);
			element.setText(" "); //$NON-NLS-1$
			element.setSelectedWord(false);
			element.setShouldBeRemoved(false);
		}
	}

	/**
	 * Move part of the line to the right.
	 * 
	 * @param line
	 *            The line to be moved.
	 * @param endPosition
	 *            The position where the move operation will stop.
	 */
	private static void movePartOfLineToTheRight(
			ArrayList<SelectedElement> line, int endPosition) {

		for (int j = line.size() - 1; j > endPosition; j--) {

			SelectedElement element = line.get(j);
			swapContent(line.get(j - 1), element);
		}
	}

	/**
	 * Move part of the line to the left.
	 * 
	 * @param line
	 *            The line to be moved.
	 * @param endPosition
	 *            The position where the move operation will stop.
	 */
	private static void movePartOfLineToTheLeft(
			ArrayList<SelectedElement> line, int endPosition) {

		int j = endPosition;
		while (j < line.size() - 1) {

			SelectedElement element = line.get(j);

			int nonSelectedPosition = -1;
			// Find the next non selected element.
			int k = j + 1;
			while (k < line.size() && nonSelectedPosition == -1) {

				SelectedElement elementToBeVerified = line.get(k);

				if (!elementToBeVerified.isSelectedWord()) {

					nonSelectedPosition = k;
				} else {
					k++;
				}
			}

			if (nonSelectedPosition != -1) {
				swapContent(element, line.get(k));
				j = k;
			} else {
				j++;
			}
		}

		SelectedElement lastElement = line.get(j);
		lastElement.setText(" "); //$NON-NLS-1$
		lastElement.setSelectedWord(false);
		lastElement.setShouldBeRemoved(false);

	}

	/**
	 * Move all elements one column to the right.
	 */
	private void moveElementOneColumnToTheRight() {

		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> line = this.lines.get(i);

			movePartOfLineToTheRight(line, 0);
		}
	}

	/**
	 * Move the selected words to the left.
	 */
	private void moveLeft() {

		boolean hasSelectedLineOnFirstPosition = false;

		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> line = this.lines.get(i);

			if (line.size() > 0) {

				SelectedElement element = line.get(0);

				if (element.isSelectedWord()) {
					hasSelectedLineOnFirstPosition = true;
				}
			}
		}

		if (hasSelectedLineOnFirstPosition) {

			createColumnOnTheRight();
			moveElementOneColumnToTheRight();
		}

		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> line = this.lines.get(i);

			for (int j = 0; j < line.size(); j++) {

				SelectedElement element = line.get(j);

				if (element.isSelectedWord()) {
					swapContent(line.get(j - 1), element);
				}
			}
		}

		revalidate();
		repaint();
	}

	/**
	 * Move the selected words to the right.
	 */
	private void moveRight() {

		boolean hasSelectedLineOnLastPosition = false;

		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> line = this.lines.get(i);

			if (line.size() > 0) {

				SelectedElement element = line.get(line.size() - 1);

				if (element.isSelectedWord()) {
					hasSelectedLineOnLastPosition = true;
				}
			}
		}

		if (hasSelectedLineOnLastPosition) {
			createColumnOnTheRight();
		}

		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> line = this.lines.get(i);

			for (int j = line.size() - 2; j >= 0; j--) {

				SelectedElement element = line.get(j);

				if (element.isSelectedWord()) {
					swapContent(line.get(j + 1), element);
				}
			}
		}

		revalidate();
		repaint();
	}

	/**
	 * Move the selected words one line above.
	 */
	private void moveUp() {

		boolean hasSelectedLineOnFirstRow = false;

		ArrayList<SelectedElement> line = this.lines.get(0);

		for (int i = 0; i < line.size(); i++) {

			SelectedElement element = line.get(i);

			if (element.isSelectedWord()) {
				hasSelectedLineOnFirstRow = true;
			}
		}

		if (hasSelectedLineOnFirstRow) {

			createRowAtTheEnd();
			moveContentOneLineBelow();
			emptyLine(this.lines.get(0));

		}

		for (int i = 1; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> lineAbove = this.lines.get(i - 1);
			ArrayList<SelectedElement> currentLine = this.lines.get(i);

			for (int j = 0; j < currentLine.size(); j++) {

				SelectedElement elementAbove = lineAbove.get(j);
				SelectedElement element = currentLine.get(j);

				if (element.isSelectedWord()) {

					// Verify if there is a space on line above to shift
					// content to the right.
					SelectedElement lastElementOnLineAbove = lineAbove
							.get(lineAbove.size() - 1);
					if (" ".equals(lastElementOnLineAbove.getText())) { //$NON-NLS-1$
						// There is a space. Then do nothing.
					} else {
						// Has to create a space.
						createColumnOnTheRight();
					}

					if (elementAbove.isShouldBeRemoved()) {
						elementAbove.setShouldBeRemoved(false);
					} else {
						movePartOfLineToTheRight(lineAbove, j);
					}
					copyContentOnly(element, elementAbove);
					element.setSelectedWord(false);
					element.setText("!"); //$NON-NLS-1$
					element.setShouldBeRemoved(true);
				}
			}
		}

		// Remove the elements marked.
		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> currentLine = this.lines.get(i);

			int j = 0;
			do {

				SelectedElement element = currentLine.get(j);

				if (element.isShouldBeRemoved()) {

					movePartOfLineToTheLeft(currentLine, j);

				} else {

					j++;
				}

			} while (j < currentLine.size());
		}

		revalidate();
		repaint();
	}

	/**
	 * Move the selected words one line below.
	 */
	private void moveDown() {

		boolean hasSelectedLineOnLastRow = false;

		ArrayList<SelectedElement> line = this.lines.get(this.lines.size() - 1);

		for (int i = 0; i < line.size(); i++) {

			SelectedElement element = line.get(i);

			if (element.isSelectedWord()) {
				hasSelectedLineOnLastRow = true;
			}
		}

		if (hasSelectedLineOnLastRow) {

			createRowAtTheEnd();
			emptyLine(this.lines.get(this.lines.size() - 1));
		}

		for (int i = this.lines.size() - 2; i >= 0; i--) {

			ArrayList<SelectedElement> lineBelow = this.lines.get(i + 1);
			ArrayList<SelectedElement> currentLine = this.lines.get(i);

			for (int j = 0; j < currentLine.size(); j++) {

				SelectedElement elementBelow = lineBelow.get(j);
				SelectedElement element = currentLine.get(j);

				if (element.isSelectedWord()) {

					// Verify if there is a space on line below to shift
					// content to the right.
					SelectedElement lastElementOnLineBelow = lineBelow
							.get(lineBelow.size() - 1);
					if (" ".equals(lastElementOnLineBelow.getText())) { //$NON-NLS-1$
						// There is a space. Then do nothing.
					} else {
						// Has to create a space.
						createColumnOnTheRight();
					}

					if (elementBelow.isShouldBeRemoved()) {

						elementBelow.setShouldBeRemoved(false);
					} else {
						movePartOfLineToTheRight(lineBelow, j);
					}
					copyContentOnly(element, elementBelow);
					element.setSelectedWord(false);
					element.setText("!"); //$NON-NLS-1$
					element.setShouldBeRemoved(true);
				}
			}
		}

		// Remove the elements marked.
		for (int i = 0; i < this.lines.size(); i++) {

			ArrayList<SelectedElement> currentLine = this.lines.get(i);

			int j = 0;
			do {

				SelectedElement element = currentLine.get(j);

				if (element.isShouldBeRemoved()) {

					movePartOfLineToTheLeft(currentLine, j);

				} else {

					j++;
				}

			} while (j < currentLine.size());
		}

		revalidate();
		repaint();

	}

}
