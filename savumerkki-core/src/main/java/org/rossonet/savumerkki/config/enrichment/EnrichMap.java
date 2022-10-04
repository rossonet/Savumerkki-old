package org.rossonet.savumerkki.config.enrichment;

import java.util.Set;

import org.json.JSONObject;

public interface EnrichMap {

	public static boolean DEFAULT_NOT_LOG = false;

	public static int DEFAULT_PRIORITY = 100;

	public static int DEFAULT_TIMEOUT_RESOLUTION_MS = 60000;

	public static EnrichMap fromJson(final JSONObject jsonConfig) {
		return AbstractEnrichMap.fromJson(jsonConfig);
	}

	public static EnrichMap fromYaml(final String yamlConfig) {
		return AbstractEnrichMap.fromYaml(yamlConfig);
	}

	public static Set<Class<? extends EnrichMap>> getEnrichmaps() {
		return AbstractEnrichMap.getEnrichmaps();
	}

	public static void registerEnrichMap(final Class<? extends EnrichMap> enrichMap) {
		AbstractEnrichMap.registerEnrichMap(enrichMap);
	}

	public void configureFromJson(JSONObject jsonConfig);

	public void configureFromYaml(String yamlConfig);

	public boolean dontLogTheValue();

	public String get(String key) throws Exception;

	public JSONObject getEnrichMapAsJson();

	public String getEnrichMapAsYaml();

	public int getPriority();

	public long getTimeoutResolutionMs();

	public void resetConnection();

}
