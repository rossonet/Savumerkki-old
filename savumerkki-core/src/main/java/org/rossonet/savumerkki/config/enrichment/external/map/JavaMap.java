package org.rossonet.savumerkki.config.enrichment.external.map;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

public class JavaMap extends AbstractEnrichMap {
	private Map<String, String> map;

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
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		setDontLogTheValue(dontLogTheValue);
	}

	public JavaMap(final Map<String, String> map) {
		this(EnrichMap.DEFAULT_PRIORITY, map, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS, EnrichMap.DEFAULT_NOT_LOG);
	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public String get(final String key) {
		if (map != null) {
			return map.get(key);
		} else {
			return null;
		}
	}

	@Override
	public JSONObject getEnrichMapAsJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEnrichMapAsYaml() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(final Map<String, String> map) {
		this.map = map;
	}
}
