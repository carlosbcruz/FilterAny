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
 * Generate a java bean from the fields.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class JavaBeanFilter extends FilterAdapter implements CommandLine,
		SpecialBehavior, Example {

	private static final long serialVersionUID = 1L;

	private static final String SUFFIX = "Parameter"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_JAVABEANFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_JAVABEANFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_JAVABEANFILTER_INSTRUCTIONS);
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
				FilterControls.CHECK_BOX_1, FilterControls.CHECK_BOX_2 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.ANYTHING, FilterType.NOT_RELEVANT,
				FilterType.NOT_RELEVANT };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.OPTIONAL,
				FilterParameter.REQUIRED, FilterParameter.REQUIRED };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text.get(Text.FILTER_JAVABEANFILTER_FIELD_1),
				Text.get(Text.FILTER_JAVABEANFILTER_CHECKBOX_1),
				Text.get(Text.FILTER_JAVABEANFILTER_CHECKBOX_2) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] {
				Text.get(Text.FILTER_JAVABEANFILTER_FIELD_1_TOOLTIP),
				Text.get(Text.FILTER_JAVABEANFILTER_CHECKBOX_1_TOOLTIP),
				Text.get(Text.FILTER_JAVABEANFILTER_CHECKBOX_2_TOOLTIP) };
	}

	/**
	 * Store a single Java field.
	 * 
	 * @author Carlos Fernando Bella Cruz - <a
	 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
	 */
	class FieldsBean {

		String type, fieldName, comment;

		/**
		 * @param type
		 *            The type of the field.
		 * @param fieldName
		 *            The field name.
		 * @param comment
		 *            The optional comment.
		 */
		public FieldsBean(String type, String fieldName, String comment) {

			super();

			this.type = type;
			this.fieldName = fieldName;
			this.comment = comment;
		}

		/**
		 * @return the type
		 */
		protected String getType() {
			return this.type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		protected void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the fieldName
		 */
		protected String getFieldName() {
			return this.fieldName;
		}

		/**
		 * @param fieldName
		 *            the fieldName to set
		 */
		protected void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		/**
		 * @return the comment
		 */
		protected String getComment() {
			return this.comment;
		}

		/**
		 * @param comment
		 *            the comment to set
		 */
		protected void setComment(String comment) {
			this.comment = comment;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {

			StringBuffer out = new StringBuffer();

			out.append("FieldsBean [\n"); //$NON-NLS-1$

			out.append("\ttype=" + this.type + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
			out.append("\tfieldName=" + this.fieldName + ",\n"); //$NON-NLS-1$ //$NON-NLS-2$
			out.append("\tcomment=" + this.comment + "]\n"); //$NON-NLS-1$ //$NON-NLS-2$

			return super.toString();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@SuppressWarnings("nls")
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText) {

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		StringBuffer output = new StringBuffer();

		LineTokenizer tokenizer = new LineTokenizer(text);

		ArrayList<FieldsBean> fields = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken().trim();

			// Retrieve the first field.
			String firstToken = null;
			int index = 0;
			for (int i = 0; i < token.length(); i++) {
				if (' ' == token.charAt(i)) {
					index = i + 1;
					firstToken = token.substring(0, i);
					break;
				}
			}

			// If there is no first field than ignore line.
			if (firstToken == null || firstToken.length() == 0) {
				continue;
			}

			// Ignorar coment�rios come�ando com #.
			if ("#".equals(firstToken) || firstToken.charAt(0) == '#') {
				continue;
			}

			// Retrieve the second field.
			String secondToken = new String();
			int secondIndex = 0;
			boolean hasFoundAContent = false;
			for (int i = index; i < token.length(); i++) {

				if (' ' == token.charAt(i)) {
					if (hasFoundAContent) {
						secondIndex = i + 1;
						break;
					}
				} else {
					hasFoundAContent = true;
					secondToken += token.charAt(i);
					secondIndex = i + 1;
				}
			}

			String thirdToken = token.substring(secondIndex);

			fields.add(new FieldsBean(firstToken, secondToken, thirdToken));
		}

		String className = "GeneratedClass";
		String classParameter = getField1().trim();
		if (!"".equals(classParameter)) {
			className = classParameter;
		}

		output.append("" + getNewLine());
		output.append("import java.io.Serializable;" + getNewLine());
		output.append("" + getNewLine());

		output.append("/**" + getNewLine());
		output.append(" * " + getNewLine());
		output.append(" */" + getNewLine());

		String cloneText = "";
		if (isCheckBox1()) {
			cloneText = ", Cloneable";
		}
		output.append("public class " + className + " implements Serializable"
				+ cloneText + " {" + getNewLine());
		output.append("" + getNewLine());
		output.append("	private static final long serialVersionUID = 1L;"
				+ getNewLine());
		output.append("" + getNewLine());

		for (FieldsBean field : fields) {

			output.append("	// " + field.getComment() + getNewLine());
			output.append("	private " + field.getType() + " "
					+ field.getFieldName());

			if ("int".equals(field.getType()) || "char".equals(field.getType())
					|| "byte".equals(field.getType())
					|| "short".equals(field.getType())
					|| "long".equals(field.getType())
					|| "float".equals(field.getType())
					|| "double".equals(field.getType())) {
				output.append(" = 0;");
			} else {
				if ("boolean".equals(field.getType())) {

					output.append(" = false;");
				} else {

					output.append(" = new " + field.getType() + "();");
				}
			}

			output.append(getNewLine());
		}

		output.append("" + getNewLine());
		output.append("	/**" + getNewLine());
		output.append("	 * Default constructor." + getNewLine());
		output.append("	 */" + getNewLine());
		output.append("	public " + className + "() {" + getNewLine());
		output.append("		" + getNewLine());
		output.append("		super();" + getNewLine());
		output.append("	}" + getNewLine());

		output.append("" + getNewLine());

		if (isCheckBox2()) {

			output.append("	/**" + getNewLine());

			for (FieldsBean field : fields) {

				output.append("	 * @param " + field.getType() + ""
						+ getNewLine());
				output.append("	 *            " + field.getFieldName() + SUFFIX
						+ " " + field.getComment() + "" + getNewLine());

			}
			output.append("	 */" + getNewLine());

			output.append("	public " + className + "(");

			boolean firstField = true;
			for (FieldsBean field : fields) {

				if (firstField) {
					firstField = false;
				} else {
					output.append(", ");
				}
				output.append(field.getType() + " " + field.getFieldName()
						+ SUFFIX);

			}

			output.append(") {" + getNewLine());

			output.append("" + getNewLine());
			output.append("		super();" + getNewLine());
			output.append("" + getNewLine());

			for (FieldsBean field : fields) {

				output.append("		this." + field.getFieldName() + " = "
						+ field.getFieldName() + SUFFIX + ";" + getNewLine());

			}

			output.append("	}" + getNewLine());

			output.append("" + getNewLine());
		}

		for (FieldsBean field : fields) {

			output.append("	/**" + getNewLine());
			output.append("	 * Provide: " + field.getComment() + ""
					+ getNewLine());
			output.append("	 * @return " + field.getFieldName() + ""
					+ getNewLine());
			output.append("	 *            " + field.getComment() + ""
					+ getNewLine());
			output.append("	 */" + getNewLine());
			output.append("	public " + field.getType()
					+ ("boolean".equals(field.getType()) ? " is" : " get")
					+ StringSupport.toUpCaseFirstLetter(field.getFieldName())
					+ "() {" + getNewLine() + getNewLine());
			output.append("		return " + field.getFieldName() + ";"
					+ getNewLine());
			output.append("	}" + getNewLine());
			output.append("" + getNewLine());
			output.append("	/**" + getNewLine());
			output.append("	 * Set: " + field.getComment() + "" + getNewLine());
			output.append("	 * @param " + field.getFieldName() + ""
					+ getNewLine());
			output.append("	 *            " + field.getComment() + ""
					+ getNewLine());
			output.append("	 */" + getNewLine());
			output.append("	public void set"
					+ StringSupport.toUpCaseFirstLetter(field.getFieldName())
					+ "(" + field.getType() + " " + field.getFieldName()
					+ SUFFIX + ") {" + getNewLine() + getNewLine());
			output.append("		this." + field.getFieldName() + " = "
					+ field.getFieldName() + SUFFIX + ";" + getNewLine());
			output.append("	}" + getNewLine() + getNewLine());

		}

		output.append("	/*" + getNewLine());
		output.append("	 * (non-Javadoc)" + getNewLine());
		output.append("	 * " + getNewLine());
		output.append("	 * @see java.lang.Object#toString()" + getNewLine());
		output.append("	 */" + getNewLine());
		output.append("	@Override" + getNewLine());
		output.append("	public String toString() {" + getNewLine());
		output.append("" + getNewLine());
		output.append("		StringBuffer out = new StringBuffer();" + getNewLine());
		output.append("" + getNewLine());
		output.append("		out.append(\"" + className + " [\\n\");"
				+ getNewLine());
		output.append("" + getNewLine());

		boolean firstTime = true;
		for (FieldsBean field : fields) {

			if (firstTime) {
				firstTime = false;
			} else {
				output.append("+ \",\\n\");" + getNewLine());
			}
			output.append("		out.append(\"\\t" + field.getFieldName()
					+ "=\" + " + field.getFieldName());
		}
		output.append("+ \"]\\n\");" + getNewLine());

		output.append("" + getNewLine());
		output.append("		return out.toString();" + getNewLine());
		output.append("	}" + getNewLine());

		output.append("" + getNewLine());

		if (isCheckBox1()) {

			output.append("\t/*" + getNewLine());
			output.append("\t * (non-Javadoc)" + getNewLine());
			output.append("\t *" + getNewLine());
			output.append("\t * @see java.lang.Object#clone()" + getNewLine());
			output.append("\t */" + getNewLine());
			output.append("\tpublic Object clone() throws CloneNotSupportedException {"
					+ getNewLine());
			output.append("" + getNewLine());
			output.append("\t\treturn super.clone();" + getNewLine());
			output.append("\t}" + getNewLine());

		}

		output.append("" + getNewLine());
		output.append("}" + getNewLine());

		output.append(getNewLine());
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

		return Text.get(Text.FILTER_JAVABEANFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_JAVABEANFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Bean"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_JAVABEANFILTER_COMMAND_LINE_HELP);
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
