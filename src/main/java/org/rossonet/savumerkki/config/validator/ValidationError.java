package org.rossonet.savumerkki.config.validator;

public final class ValidationError {

	private final long errorBeginCharacter;
	private final String errorDescription;
	private final long errorEndCharacter;

	public ValidationError(final String errorDescription, final long errorBeginCharacter,
			final long errorEndCharacter) {
		super();
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

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ValidationError [errorDescription=");
		builder.append(errorDescription);
		builder.append(", errorBeginCharacter=");
		builder.append(errorBeginCharacter);
		builder.append(", errorEndCharacter=");
		builder.append(errorEndCharacter);
		builder.append("]");
		return builder.toString();
	}

}
