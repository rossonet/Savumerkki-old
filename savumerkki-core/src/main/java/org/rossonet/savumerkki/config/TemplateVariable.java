package org.rossonet.savumerkki.config;

public final class TemplateVariable {

	public static String DEFAULT_AFTER_VARIABLE = "}%%";
	public static String DEFAULT_BEFORE_DEFAULT = "|";
	public static String DEFAULT_BEFORE_NAME = "%%{";
	private String afterVariable = DEFAULT_AFTER_VARIABLE;
	private String beforeDefaultValue = DEFAULT_BEFORE_DEFAULT;
	private String beforeVariableName = DEFAULT_BEFORE_NAME;

	public String getAfterVariable() {
		return afterVariable;
	}

	public String getBeforeDefaultValue() {
		return beforeDefaultValue;
	}

	public String getBeforeVariableName() {
		return beforeVariableName;
	}

	public void setAfterVariable(final String afterVariable) {
		this.afterVariable = afterVariable;
	}

	public void setBeforeDefaultValue(final String beforeDefaultValue) {
		this.beforeDefaultValue = beforeDefaultValue;
	}

	public void setBeforeVariableName(final String beforeVariableName) {
		this.beforeVariableName = beforeVariableName;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("TemplateVariable [beforeVariableName=");
		builder.append(beforeVariableName);
		builder.append(", beforeDefaultValue=");
		builder.append(beforeDefaultValue);
		builder.append(", afterVariable=");
		builder.append(afterVariable);
		builder.append("]");
		return builder.toString();
	}

}
