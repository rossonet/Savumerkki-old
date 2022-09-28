package org.rossonet.savumerkki.config.enrichment.external;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

public class ExternalEnrich implements EnrichMap {

	private final boolean dontLogTheValue;
	private final ExternalEnrichFunction function;
	private final int priority;
	private final long timeoutResolutionMs;

	public ExternalEnrich(final ExternalEnrichFunction function) {
		this(EnrichMap.DEFAULT_PRIORITY, function, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS, EnrichMap.DEFAULT_NOT_LOG);
	}

	public ExternalEnrich(final int priority, final ExternalEnrichFunction function, final long timeoutResolutionMs,
			final boolean dontLogTheValue) {
		this.function = function;
		this.priority = priority;
		this.timeoutResolutionMs = timeoutResolutionMs;
		this.dontLogTheValue = dontLogTheValue;
	}

	@Override
	public boolean dontLogTheValue() {
		return dontLogTheValue;
	}

	@Override
	public String get(final String key) {
		return function.getValue(key);
	}

	public ExternalEnrichFunction getFunction() {
		return function;
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
		function.reset();
	}

}
