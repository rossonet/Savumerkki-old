package org.rossonet.savumerkki.config.enrichment.vault.azure;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

//TODO Completare!
public class AzureVault implements EnrichMap {
	private final int priority;
	private final long timeoutResolutionMs;

	public AzureVault() {
		this(EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public AzureVault(final int priority, final long timeoutResolutionMs) {
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
