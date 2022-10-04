package org.rossonet.savumerkki.config.enrichment.external;

public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 7332329216110336426L;

	public ConfigurationException(final Exception exception) {
		super(exception);
	}

	public ConfigurationException(final String message) {
		super(message);
	}

}
