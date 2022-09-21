package org.rossonet.savumerkki.config.enrichment.external.map;

import java.util.HashMap;
import java.util.Map;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

public class JavaMap implements EnrichMap {

	private final boolean dontLogTheValue;
	private final Map<String, String> map;

	private final int priority;
	private final long timeoutResolutionMs;

	public JavaMap() {
		this(EnrichMap.DEFAULT_PRIORITY, new HashMap<>(), EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS,
				EnrichMap.DEFAULT_NOT_LOG);
	}

	public JavaMap(final int priority) {
		this(priority, new HashMap<>(), EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS, EnrichMap.DEFAULT_NOT_LOG);
	}

	public JavaMap(final int priority, final Map<String, String> map, final long timeoutResolutionMs,
			final boolean dontLogTheValue) {
		this.map = map;
		this.priority = priority;
		this.timeoutResolutionMs = timeoutResolutionMs;
		this.dontLogTheValue = dontLogTheValue;
	}

	public JavaMap(final Map<String, String> map) {
		this(EnrichMap.DEFAULT_PRIORITY, map, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS, EnrichMap.DEFAULT_NOT_LOG);
	}

	@Override
	public boolean dontLogTheValue() {
		return dontLogTheValue;
	}

	@Override
	public String get(final String key) {
		return map.get(key);
	}

	public Map<String, String> getMap() {
		return map;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public long getTimeoutResolutionMs() {
		return timeoutResolutionMs;
	}

}
