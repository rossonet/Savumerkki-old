package org.rossonet.savumerkki.config.enrichment.system;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

public class EnviromentsMap implements EnrichMap {

	private final boolean dontLogTheValue;
	private final String postfix;

	private final String prefix;

	private final int priority;

	private final long timeoutResolutionMs;

	public EnviromentsMap() {
		this(null, null, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS,
				EnrichMap.DEFAULT_NOT_LOG);
	}

	public EnviromentsMap(final String prefix, final String postfix, final int priority, final long timeoutResolutionMs,
			final boolean dontLogTheValue) {
		this.priority = priority;
		this.timeoutResolutionMs = timeoutResolutionMs;
		this.dontLogTheValue = dontLogTheValue;
		this.prefix = prefix;
		this.postfix = postfix;
	}

	@Override
	public boolean dontLogTheValue() {
		return dontLogTheValue;
	}

	@Override
	public String get(final String key) throws Exception {
		final String completedKey = (prefix != null ? prefix : "") + key + (postfix != null ? postfix : "");
		return System.getenv(completedKey);
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
		// not used
	}
}
