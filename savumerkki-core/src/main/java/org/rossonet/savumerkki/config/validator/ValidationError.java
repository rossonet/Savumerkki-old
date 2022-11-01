package org.rossonet.savumerkki.config.validator;

import java.io.Serializable;

public final class ValidationError implements Serializable {

	private static final long serialVersionUID = 4365284538766857797L;
	private final long errorBeginCharacter;

	private final String errorDescription;
	private final long errorEndCharacter;
	private final long errorLine;

	public ValidationError(final String errorDescription, final long errorLine, final long errorBeginCharacter,
			final long errorEndCharacter) {
		this.errorLine = errorLine;
		this.errorDescription = errorDescription;
		this.errorBeginCharacter = errorBeginCharacter;
		this.errorEndCharacter = errorEndCharacter;
	}

	public long getErrorBeginCharacter() {
		return errorBeginCharacter;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public long getErrorEndCharacter() {
		return errorEndCharacter;
	}

	public long getErrorLine() {
		return errorLine;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ValidationError [errorDescription=");
		builder.append(errorDescription);
		builder.append(", errorLine=");
		builder.append(errorLine);
		builder.append(", errorBeginCharacter=");
		builder.append(errorBeginCharacter);
		builder.append(", errorEndCharacter=");
		builder.append(errorEndCharacter);
		builder.append("]");
		return builder.toString();
	}

}
