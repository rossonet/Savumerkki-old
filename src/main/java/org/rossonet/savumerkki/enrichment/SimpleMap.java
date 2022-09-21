package org.rossonet.savumerkki.enrichment;

import java.util.HashMap;
import java.util.Map;

public class SimpleMap implements EnrichMap {

	private final Map<String, String> map;
	private final int priority;

	public SimpleMap() {
		this(EnrichMap.DEFAULT_PRIORITY, new HashMap<>());
	}

	public SimpleMap(final int priority) {
		this(priority, new HashMap<>());
	}

	public SimpleMap(final int priority, final Map<String, String> map) {
		this.map = map;
		this.priority = priority;
	}

	public SimpleMap(final Map<String, String> map) {
		this(EnrichMap.DEFAULT_PRIORITY, map);
	}

	@Override
	public String get(final String key) {
		return null;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	public Map<String, String> getMap() {
		return map;
	}

}
