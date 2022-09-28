package org.rossonet.savumerkki.config.enrichment.vault.google;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

// TODO Completare!
public class GoogleVault implements EnrichMap {

	private final int priority;
	private final long timeoutResolutionMs;

	public GoogleVault() {
		this(EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public GoogleVault(final int priority, final long timeoutResolutionMs) {
		this.priority = priority;
		this.timeoutResolutionMs = timeoutResolutionMs;
	}

	@Override
	public boolean dontLogTheValue() {
		return true;
	}

	@Override
	public String get(final String key) throws Exception {
		// TODO
		return null;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public long getTimeoutResolutionMs() {
		return timeoutResolutionMs;
	}

	@Override
	public void resetConnection() {
		// TODO Auto-generated method stub

	}

}
