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

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;
import com.carlosbcruz.util.LineTokenizer;
import com.carlosbcruz.util.StringSupport;

/**
 * Format a JSP file.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class JspFormatterFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_NUMBER_OF_SPACES = 3;
	private static final int MINIMUM_OF_SPACES = 1;
	private static final int MAXIMUM_OF_SPACES = 10;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_JSPFORMATTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_JSPFORMATTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_JSPFORMATTER_INSTRUCTIONS);
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

		return new String[] { Text.get(Text.FILTER_JSPFORMATTER_FIELD_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_JSPFORMATTER_FIELD_1_TOOLTIP) };
	}

	/**
	 * Types of contents stored.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	enum TokenType {
		COMMENT, SCRIPT, SELECTED, DISCARTED, UNDEFINED
	}

	/**
	 * A token can be a pair of begin and and. A token can be unique.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	enum TokenSemantic {
		STARTING, ENDING, UNIQUE
	}

	/**
	 * Represents a token.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class Token {

		// Token definition.
		TokenType type = null;
		TokenSemantic semantic = null;

		// The position in the html content.
		Integer startingPosition;
		Integer endingPosition;

		// The content between the begin and end.
		String content = null;

		// The html tag name.
		String tagName = null;

		// A begin token reference the correspondent end token.
		Token pair = null;

		/**
		 * Constructor.
		 * 
		 * @param startingPosition
		 *            The starting position.
		 * @param endingPosition
		 *            The ending position.
		 * @param type
		 *            The type of the token.
		 */
		public Token(Integer startingPosition, Integer endingPosition,
				TokenType type) {

			super();

			this.type = type;
			this.startingPosition = startingPosition;
			this.endingPosition = endingPosition;
		}

		/**
		 * Provide the starting position.
		 * 
		 * @return The starting position.
		 */
		public Integer getStartingPosition() {

			return this.startingPosition;
		}

		/**
		 * Set the starting position.
		 * 
		 * @param startingPosition
		 *            The starting position.
		 */
		public void setStartingPosition(Integer startingPosition) {

			this.startingPosition = startingPosition;
		}

		/**
		 * Provide the ending position.
		 * 
		 * @return The ending position.
		 */
		public Integer getEndingPosition() {

			return this.endingPosition;
		}

		/**
		 * Set the ending position.
		 * 
		 * @param endingPosition
		 *            The ending position.
		 */
		public void setEndingPosition(Integer endingPosition) {

			this.endingPosition = endingPosition;
		}

		/**
		 * Provide the content between the starting and ending token.
		 * 
		 * @return The content between the starting and ending token.
		 */
		public String getContent() {

			return this.content;
		}

		/**
		 * Set the content between the starting and ending token.
		 * 
		 * @param content
		 *            The content between the starting and ending token.
		 */
		public void setContent(String content) {

			this.content = content;
		}

		/**
		 * Provide the token type.
		 * 
		 * @return The token type.
		 */
		public TokenType getType() {

			return this.type;
		}

		/**
		 * Set the token type.
		 * 
		 * @param type
		 *            The token type.
		 */
		public void setType(TokenType type) {

			this.type = type;
		}

		/**
		 * Provide the html tag name.
		 * 
		 * @return The html tag name.
		 */
		public String getTagName() {

			return this.tagName;
		}

		/**
		 * Set the html tag name.
		 * 
		 * @param tagName
		 *            The html tag name.
		 */
		public void setTagName(String tagName) {

			this.tagName = tagName;
		}

		/**
		 * Provide the token semantic.
		 * 
		 * @return The token semantic.
		 */
		public TokenSemantic getSemantic() {

			return this.semantic;
		}

		/**
		 * Set the token semantic.
		 * 
		 * @param semantic
		 *            The token semantic.
		 */
		public void setSemantic(TokenSemantic semantic) {

			this.semantic = semantic;
		}

		/**
		 * Provide the pair of this token.
		 * 
		 * @return The pair of this token.
		 */
		public Token getPair() {

			return this.pair;
		}

		/**
		 * Set the pair of this token.
		 * 
		 * @param pair
		 */
		public void setPair(Token pair) {

			this.pair = pair;
		}

		@SuppressWarnings("nls")
		@Override
		public String toString() {
			return "Token [type=" + this.type + ", semantic=" + this.semantic
					+ ", startingPosition=" + this.startingPosition
					+ ", endingPosition=" + this.endingPosition + ", content="
					+ this.content + ", tagName=" + this.tagName + ", pair="
					+ this.pair + "]";
		}
	}

	/**
	 * Get the position of all tokens represented by "delimiter" in the input
	 * buffer. If beginning is specified then the starting of the delimiter is
	 * provided, if not, then the ending position of the delimiter is provided.
	 * 
	 * @param input
	 *            The input buffer.
	 * @param delimiter
	 *            The delimiter to be searched.
	 * @param isBeginning
	 *            Indicate if the beginning of all delimiters should be provided
	 *            or the end.
	 * @return The list o positions of all delimiters.
	 */
	private static List<Integer> getAllSymbolPositions(StringBuffer input,
			String delimiter, boolean isBeginning) {

		List<Integer> positions = new ArrayList<>();

		int position = -1;
		do {
			position = input.indexOf(delimiter, position);

			if (position != -1) {
				positions.add(isBeginning ? Integer.valueOf(position) : Integer
						.valueOf(position + delimiter.length()));
				position++;
			}
		} while (position != -1);

		return positions;
	}

	/**
	 * Search the starting and ending symbols to pair them into tokens. The list
	 * of starting and ending symbols should be of the same type. The type of
	 * the token provided is used only to initiate the tokens on the
	 * constructor. No logic changes according to different tokens types.
	 * 
	 * @param startingSymbols
	 *            The list of starting symbols.
	 * @param endingSymbols
	 *            The list of ending symbols.
	 * @param type
	 *            The type of the token to be created.
	 * @return The list of tokens with the pairs found.
	 */
	private List<Token> retrieveAllTokens(List<Integer> startingSymbols,
			List<Integer> endingSymbols, TokenType type) {

		List<Token> tokens = new ArrayList<>();

		// For each starting symbol do a search for the ending.
		for (int i = 0; i < startingSymbols.size(); i++) {

			// Work with the current and the next starting token.
			Integer startingSymbol = startingSymbols.get(i);
			Integer nextStartingSymbol = null;

			// Get next starting token if it exist.
			if (i < startingSymbols.size() - 1) {
				nextStartingSymbol = startingSymbols.get(i + 1);
			}

			// Search for the ending token on the entire ending symbols.
			for (Integer currentEndingSymbol : endingSymbols) {

				// Only get the ending symbol that is after the starting symbol.
				if (currentEndingSymbol.intValue() > startingSymbol.intValue()) {

					// If the next starting symbol exist than only create a
					// token if the ending symbol is before the next starting
					// symbol.
					if (nextStartingSymbol != null) {

						if (startingSymbol.intValue() < nextStartingSymbol
								.intValue()) {
							Token token = new Token(startingSymbol,
									currentEndingSymbol, type);
							tokens.add(token);
						}

					} else {

						// There is no next symbol, than create a token anyway.
						Token token = new Token(startingSymbol,
								currentEndingSymbol, type);
						tokens.add(token);
					}

					break;
				}
			}
		}

		return tokens;
	}

	/**
	 * Identify in the input text which parts belong to each token and than
	 * indicate inside of each token where it begins and where it ends.
	 * 
	 * @param input
	 *            The input text.
	 * @param tokens
	 *            The token list.
	 * @return The token list with the indication of where each of of them
	 *         starts and ends in the text.
	 */
	private static List<Token> populateTokensWithContent(StringBuffer input,
			List<Token> tokens) {

		for (Token token : tokens) {

			token.setContent(input.substring(token.getStartingPosition()
					.intValue(), token.getEndingPosition().intValue()));
		}

		return tokens;
	}

	/**
	 * Get the list of symbols that are within the token lists. Usually this
	 * method is used to remove symbols like script start and end that are
	 * between comments, so they are not considered.
	 * 
	 * @param symbols
	 *            The list of symbols.
	 * @param tokens
	 *            The list of tokens.
	 * @param beginning
	 *            A flag that indicates if the search can begin on the exact
	 *            point of the starting tokens or after the next character of
	 *            the starting position.
	 * @return The list of symbols that should not be considered and may be
	 *         removed.
	 */
	private static List<Integer> getListOfSymbolsWithinTokensToBeRemoved(
			List<Integer> symbols, List<Token> tokens, boolean beginning) {

		List<Integer> cleanedList = new ArrayList<>();

		// For each symbol, check if they are within tokens or not.
		for (Integer position : symbols) {

			boolean discart = false;

			// Search all the tokens.
			for (Token token : tokens) {

				if (beginning) {
					// If the symbol is withing the token, than it should be
					// removed.
					if (position.intValue() >= token.getStartingPosition()
							.intValue()
							&& position.intValue() <= token.getEndingPosition()
									.intValue()) {
						discart = true;
					}

				} else {

					// If the symbol is within the token, than it should be
					// removed.
					if (position.intValue() > token.getStartingPosition()
							.intValue()
							&& position.intValue() <= token.getEndingPosition()
									.intValue()) {
						discart = true;
					}
				}
			}

			// Add it to the removing list.
			if (!discart) {

				cleanedList.add(position);
			}
		}

		return cleanedList;
	}

	/**
	 * Merge all the list maintaining the order of all tokens.
	 * 
	 * @param lists
	 *            The lists to be merged.
	 * @return The combined list.
	 */
	private static List<Token> mergeList(
			@SuppressWarnings("unchecked") final List<Token>... lists) {

		// Append one list over the other.
		List<Token> combinedList = new ArrayList<>();
		for (List<Token> list : lists) {
			combinedList.addAll(list);
		}

		List<Token> mergedList = new ArrayList<>();
		// Create a merged list with the tokens ordered.
		do {

			// Find token with the lowest starting position.
			long sortestPosition = Long.MAX_VALUE;
			Token selectedElement = null;
			for (Token token : combinedList) {
				if (token.startingPosition.intValue() < sortestPosition) {
					sortestPosition = token.startingPosition.intValue();
					selectedElement = token;
				}
			}

			// Insert this token into the merged list and remove from the other.
			if (selectedElement != null) {
				mergedList.add(selectedElement);
				combinedList.remove(selectedElement);
			}

		} while (!combinedList.isEmpty());

		return mergedList;
	}

	/**
	 * Validate if the list of tokens are sequential. It is an internal sanity
	 * check.
	 * 
	 * @param list
	 *            The list of tokens.
	 * @return A message if something goes wrong.
	 */
	private static String validateList(List<Token> list) {

		// No message if no list.
		if (list.isEmpty()) {

			return null;
		}

		Token lastToken = null;
		for (Token token : list) {

			if (lastToken == null) {
				// Do nothing
			} else {

				// Verify if next token overlaps with previous.
				if (token.getStartingPosition().intValue() < lastToken
						.getEndingPosition().intValue()) {

					return Text.get(Text.FILTER_JSPFORMATTER_ERROR_MESSAGE_1);
				}
			}

			lastToken = token;
		}

		return null;
	}

	/**
	 * Execute an internal sanity check. Append the content of all tokens. The
	 * result should have the same length of the original input content.
	 * 
	 * @param list
	 *            The list of tokens.
	 * @param original
	 *            The original file.
	 * @return A message if something goes wrong.
	 */
	private static String validateContent(List<Token> list,
			StringBuffer original) {

		StringBuffer generatedContent = new StringBuffer();

		for (Token token : list) {
			generatedContent.append(token.getContent());
		}

		if (original.length() != generatedContent.length()) {

			return Text.get(Text.FILTER_JSPFORMATTER_ERROR_MESSAGE_2);
		}

		return null;
	}

	/**
	 * Insert the texts in between tokens into tokens with type undefined.
	 * 
	 * @param list
	 *            The list of tokens.
	 * @param original
	 *            The original text.
	 * @return The complete list of tokens that represent the entire text.
	 */
	private List<Token> insertRemainingParts(List<Token> list,
			StringBuffer original) {

		List<Token> completelist = new ArrayList<>();

		// If no tokens than just create one with the original text.
		if (list.isEmpty()) {
			Token newToken = new Token(Integer.valueOf(0),
					Integer.valueOf(original.length()), TokenType.UNDEFINED);
			newToken.setContent(original.substring(0, original.length()));
			completelist.add(newToken);
			return completelist;
		}

		// For each identified token.
		Token lastToken = null;
		for (Token token : list) {

			// If it is the first token.
			if (lastToken == null) {

				// If the first token starts after the first character.
				if (token.getStartingPosition().intValue() > 0) {

					// Add the undefined token with the text.
					Token newToken = new Token(Integer.valueOf(0),
							token.getStartingPosition(), TokenType.UNDEFINED);
					newToken.setContent(original.substring(0, token
							.getStartingPosition().intValue()));
					completelist.add(newToken);

					// Add the first token.
					completelist.add(token);

				} else {

					// The first token starts in the first position.
					// So just add it.
					completelist.add(token);
				}

			} else {

				// If there are characters between tokens.
				if (token.getStartingPosition() != lastToken
						.getEndingPosition()) {

					// Add the text between token.
					Token newToken = new Token(lastToken.getEndingPosition(),
							token.getStartingPosition(), TokenType.UNDEFINED);
					newToken.setContent(original.substring(lastToken
							.getEndingPosition().intValue(), token
							.getStartingPosition().intValue()));
					completelist.add(newToken);

					// Add the token.
					completelist.add(token);

				} else {

					// There is not text between tokens.
					// Just add it.
					completelist.add(token);
				}
			}

			lastToken = token;

		}

		// If there is a last token.
		if (lastToken != null) {

			// If there are text after the last token.
			// Add this text.
			if (lastToken.getEndingPosition().intValue() < original.length()) {
				Token newToken = new Token(lastToken.getEndingPosition(),
						Integer.valueOf(original.length()), TokenType.UNDEFINED);
				newToken.setContent(original.substring(lastToken
						.getEndingPosition().intValue()));
				completelist.add(newToken);
			}
		}

		return completelist;
	}

	/**
	 * Categorize the token list according to the predefined types.
	 * 
	 * @param list
	 *            The token list.
	 * @return The categorized list.
	 */
	private static List<Token> identifySemantic(List<Token> list) {

		// Analyze all tokens.
		for (Token token : list) {

			// The type selected can be of three types.
			if (token.getType() == TokenType.SELECTED) {

				String content = token.getContent().trim();

				if (content.charAt(1) == '/') {

					// Ending token.
					token.setSemantic(TokenSemantic.ENDING);

				} else {

					if (content.charAt(content.length() - 2) == '/') {

						// Unique token.
						token.setSemantic(TokenSemantic.UNIQUE);

					} else {

						// Beginning token.
						token.setSemantic(TokenSemantic.STARTING);
					}
				}
			}

			// The tokens below are, by definition, unique.
			// There is no start and end tag.
			if ("INPUT".equals(token.getTagName())) { //$NON-NLS-1$
				token.setSemantic(TokenSemantic.UNIQUE);
			}

			if ("BR".equals(token.getTagName())) { //$NON-NLS-1$
				token.setSemantic(TokenSemantic.UNIQUE);
			}

			if ("A".equals(token.getTagName())) { //$NON-NLS-1$
				token.setSemantic(TokenSemantic.UNIQUE);
			}

			if ("IMG".equals(token.getTagName())) { //$NON-NLS-1$
				token.setSemantic(TokenSemantic.UNIQUE);
			}
		}

		return list;
	}

	private static List<Token> identifyPairs(List<Token> list) {

		boolean foundPair = false;

		do {

			foundPair = false;

			Stack<Token> stack = new Stack<>();

			for (Token token : list) {

				Token top = null;

				try {
					top = stack.peek();
				} catch (EmptyStackException e) {
					top = null;
				}

				if (token.getPair() != null) {
					continue;
				}

				if (token.getType() == TokenType.SELECTED) {

					switch (token.getSemantic()) {

					case STARTING: {

						stack.push(token);

						break;
					}

					case ENDING: {

						if (top != null) {

							if (top.getTagName().equals(token.getTagName())) {

								top.setPair(token);
								token.setPair(top);
								stack.pop();

								foundPair = true;
							}
						}

						break;
					}
					case UNIQUE: {
						// Do nothing.
						break;
					}
					default:
						// Irrelevant
						break;
					}
				}
			}
		} while (foundPair);

		return list;
	}

	/**
	 * Transform the content of each token into one single line without tabs.
	 * 
	 * @param list
	 *            The token list.
	 * @return The list of tokens with the content of each token in one line
	 *         only.
	 */
	private static List<Token> inlineSelectedTokens(List<Token> list) {

		// For all tokens that are type selected.
		for (Token token : list) {

			if (token.getType() == TokenType.SELECTED) {

				StringBuffer content = new StringBuffer(token.getContent());

				// Make the content be in one line only.
				LineTokenizer tokenizer = new LineTokenizer(content);

				StringBuffer output = new StringBuffer();
				boolean firstToken = true;
				while (tokenizer.hasMoreTokens()) {

					String nextToken = tokenizer.nextToken().trim();

					if (firstToken) {

						firstToken = false;

					} else {

						output.append(" "); //$NON-NLS-1$

					}

					output.append(nextToken);
				}

				// Replace tabs by space.
				String noTabs = StringSupport.replace(output.toString(), "\t", //$NON-NLS-1$
						" "); //$NON-NLS-1$

				token.setContent(noTabs);
			}
		}

		return list;
	}

	/**
	 * Generate the final text indenting the tags.
	 * 
	 * @param list
	 *            The list of tokens.
	 * @param numberOfSpacesInIndentation
	 *            The number of spaces that should be use for indentation.
	 * @return The formatted text.
	 */
	private static StringBuffer generateContent(List<Token> list,
			int numberOfSpacesInIndentation) {

		StringBuffer buffer = new StringBuffer();
		int level = 0;

		for (Token token : list) {

			if (token.getType() == TokenType.SELECTED) {

				switch (token.getSemantic()) {

				case STARTING: {

					buffer.append(StringSupport.getConsecutiveSpaces(level
							* numberOfSpacesInIndentation)
							+ token.getContent());
					level++;

					break;
				}

				case ENDING: {

					level--;
					buffer.append(StringSupport.getConsecutiveSpaces(level
							* numberOfSpacesInIndentation)
							+ token.getContent());

					break;
				}

				default: {
					buffer.append(StringSupport.getConsecutiveSpaces(level
							* numberOfSpacesInIndentation)
							+ token.getContent());
				}
				}

			} else {

				if (StringSupport.hasRelevantCharacters(token.getContent())) {
					buffer.append(StringSupport.getConsecutiveSpaces(level
							* numberOfSpacesInIndentation)
							+ token.getContent());
				}
			}

			buffer.append(StringSupport.getDOSNewLine());
		}

		return buffer;
	}

	/**
	 * Get the name of a token represented by the content provided. The token
	 * can have the symbol ":".
	 * 
	 * @param content
	 *            The token content.
	 * @return The token content in upper case.
	 */
	private static StringBuffer getTokenName(String content) {

		char charactes[] = content.toCharArray();

		StringBuffer tokenName = new StringBuffer();

		boolean hasFoundValidChar = false;
		for (char character : charactes) {

			if (Character.isLetterOrDigit(character) || ':' == character) {

				tokenName.append(Character.toUpperCase(character));

				hasFoundValidChar = true;

			} else {
				// Ignore the start token symbol "<" and stop before the end
				// symbol ">".
				if (hasFoundValidChar) {
					break;
				}
			}
		}
		return tokenName;
	}

	/**
	 * Use the token content to generate the tag name.
	 * 
	 * @param list
	 *            The token list.
	 * @return The list of tokens with the tag name filled up.
	 */
	private static List<Token> populateTagName(List<Token> list) {

		for (Token token : list) {

			if (token.getType() == TokenType.SELECTED) {

				StringBuffer tokenName = getTokenName(token.getContent());

				token.setTagName(tokenName.toString());
			}
		}

		return list;
	}

	/**
	 * Get all the comments from the text.
	 * 
	 * @param original
	 *            The text.
	 * @param upperCaseText
	 *            the text in upper case.
	 * @return The list of tokens representing the comments.
	 */
	private List<Token> getAllComments(StringBuffer original,
			StringBuffer upperCaseText) {
		// Get all the comments.

		List<Integer> commentsStartList = getAllSymbolPositions(upperCaseText,
				"<!--", true); //$NON-NLS-1$

		List<Integer> commentsEndList = getAllSymbolPositions(upperCaseText,
				"-->", false); //$NON-NLS-1$

		List<Token> comments = retrieveAllTokens(commentsStartList,
				commentsEndList, TokenType.COMMENT);

		comments = populateTokensWithContent(original, comments);

		return comments;
	}

	/**
	 * Get all the tokens representing the scripts
	 * 
	 * @param original
	 *            The original text.
	 * @param upperCaseText
	 *            the text in upper case.
	 * @param comments
	 *            The tokens that represent the comments.
	 * @return The list of tokens representing the scripts.
	 */
	private List<Token> getAllScripts(StringBuffer original,
			StringBuffer upperCaseText, List<Token> comments) {
		// Get all the scripts.

		List<Integer> scriptsStartList = getAllSymbolPositions(upperCaseText,
				"<SCRIPT", true); //$NON-NLS-1$

		List<Integer> scriptEndList = getAllSymbolPositions(upperCaseText,
				"/SCRIPT>", false); //$NON-NLS-1$

		scriptsStartList = getListOfSymbolsWithinTokensToBeRemoved(
				scriptsStartList, comments, true);
		scriptEndList = getListOfSymbolsWithinTokensToBeRemoved(scriptEndList,
				comments, false);

		List<Token> scripts = retrieveAllTokens(scriptsStartList,
				scriptEndList, TokenType.SCRIPT);

		scripts = populateTokensWithContent(original, scripts);
		return scripts;
	}

	/**
	 * Get all the valid tokens.
	 * 
	 * @param original
	 *            The original text.
	 * @param upperCaseText
	 *            The text in upper case.
	 * @param comments
	 *            The comments found.
	 * @param scripts
	 *            The scripts found.
	 * @return The list of valid tokens.
	 */
	private List<Token> getAllValidTokens(StringBuffer original,
			StringBuffer upperCaseText, List<Token> comments,
			List<Token> scripts) {

		// Get all, no exception.
		List<Integer> lessThanSymbols = getAllSymbolPositions(upperCaseText,
				"<", true); //$NON-NLS-1$

		// Remove the ones between comments.
		lessThanSymbols = getListOfSymbolsWithinTokensToBeRemoved(
				lessThanSymbols, comments, true);

		// Remove the ones between scripts.
		lessThanSymbols = getListOfSymbolsWithinTokensToBeRemoved(
				lessThanSymbols, scripts, true);

		// Get all, no exception.
		List<Integer> greaterThanSymbols = getAllSymbolPositions(upperCaseText,
				">", false); //$NON-NLS-1$

		// Remove the ones between comments.
		greaterThanSymbols = getListOfSymbolsWithinTokensToBeRemoved(
				greaterThanSymbols, comments, false);

		// Remove the ones between scripts.
		greaterThanSymbols = getListOfSymbolsWithinTokensToBeRemoved(
				greaterThanSymbols, scripts, false);

		// Retrieve all tokens using the valid less then and greater then.
		List<Token> tokens = retrieveAllTokens(lessThanSymbols,
				greaterThanSymbols, TokenType.UNDEFINED);

		tokens = populateTokensWithContent(original, tokens);

		// Select the valid tokens.
		List<Token> selectedTokens = new ArrayList<>();
		for (Token token : tokens) {

			Boolean acceptToken = Boolean.valueOf(false);

			// The number of less than and greater than symbols must match.
			if (StringSupport.countOccurrences(token.getContent(), "<") != StringSupport //$NON-NLS-1$
					.countOccurrences(token.getContent(), ">")) { //$NON-NLS-1$

				acceptToken = Boolean.valueOf(false);

			} else {

				// Begin with letter
				if (StringSupport.isAlphabetic(token.getContent().charAt(1))) {
					acceptToken = Boolean.valueOf(true);
				}

				// Accept end token.
				if (token.getContent().charAt(1) == '/') {
					acceptToken = Boolean.valueOf(true);
				}
			}

			if (acceptToken.booleanValue()) {

				token.setType(TokenType.SELECTED);
				selectedTokens.add(token);

			} else {

				token.setType(TokenType.DISCARTED);
			}
		}

		return selectedTokens;
	}

	/**
	 * Try to format a JSP file.
	 * 
	 * @param original
	 *            The original text.
	 * @param numberOfSpacesInIndentation
	 *            The number of spaces used for indentation.
	 * @return The formatted text.
	 */
	private StringBuffer formatJSP(StringBuffer original,
			int numberOfSpacesInIndentation) {

		// Work with upper case text.
		StringBuffer upperCaseText = StringSupport.toUpperCase(original);

		List<Token> comments = getAllComments(original, upperCaseText);

		List<Token> scripts = getAllScripts(original, upperCaseText, comments);

		List<Token> selectedTokens = getAllValidTokens(original, upperCaseText,
				comments, scripts);

		@SuppressWarnings("unchecked")
		List<Token> mergedList = mergeList(comments, scripts, selectedTokens);

		String message = validateList(mergedList);

		if (message != null) {
			return new StringBuffer(message);
		}

		List<Token> finalList = insertRemainingParts(mergedList, original);

		message = validateContent(finalList, original);

		if (message != null) {
			return new StringBuffer(message);
		}

		finalList = populateTagName(finalList);

		finalList = identifySemantic(finalList);

		finalList = identifyPairs(finalList);

		finalList = inlineSelectedTokens(finalList);

		StringBuffer content = generateContent(finalList,
				numberOfSpacesInIndentation);

		StringBuffer output = StringSupport.removeEmptyLines(content);

		return output;

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

		int numberOfSpaces = DEFAULT_NUMBER_OF_SPACES;
		String spaces = getField1();
		if (spaces != null) {

			int parameter = 0;
			try {
				parameter = Integer.parseInt(spaces);
			} catch (NumberFormatException e) {
				// Do nothing
			}

			if (parameter >= MINIMUM_OF_SPACES
					&& parameter <= MAXIMUM_OF_SPACES) {
				numberOfSpaces = parameter;
			}
		}

		return formatJSP(text, numberOfSpaces);
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

		return Text.get(Text.FILTER_JSPFORMATTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_JSPFORMATTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "JSP"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_JSPFORMATTER_COMMAND_LINE_HELP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new SpecialBehavior.Behavior[] {
				Behavior.ACCEPT_AUTOMATIC_BEHAVIOR,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}

}
