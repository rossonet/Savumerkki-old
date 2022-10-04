package org.rossonet.savumerkki.config.enrichment;

import org.json.JSONObject;

public abstract class AbstractEnrichMap implements EnrichMap {

	static EnrichMap fromJson(final JSONObject jsonConfig) {
		// TODO implementare logiche
		return null;
	}

	static EnrichMap fromYaml(final String yamlConfig) {
		// TODO implementare logiche
		return null;
	}

	static void registerEnrichMap(final Class<EnrichMap> enrichMap) {
		// TODO implementare registrazione per resolver da nome classe
	}

	private boolean dontLogTheValue = false;
	private int priority = 50;

	private long timeoutResolutionMs = 2000;

	@Override
	public boolean dontLogTheValue() {
		return dontLogTheValue;
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
		// do nothing

	}

	public void setDontLogTheValue(final boolean dontLogTheValue) {
		this.dontLogTheValue = dontLogTheValue;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

	public void setTimeoutResolutionMs(final long timeoutResolutionMs) {
		this.timeoutResolutionMs = timeoutResolutionMs;
	}

}
