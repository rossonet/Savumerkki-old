package org.rossonet.savumerkki.config.enrichment.system;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

public class EnviromentsMap extends AbstractEnrichMap {

	private String postfix;

	private String prefix;

	public EnviromentsMap() {
		this(null, null, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS,
				EnrichMap.DEFAULT_NOT_LOG);
	}

	public EnviromentsMap(final String prefix, final String postfix, final int priority, final long timeoutResolutionMs,
			final boolean dontLogTheValue) {
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		setDontLogTheValue(dontLogTheValue);
		this.prefix = prefix;
		this.postfix = postfix;
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
	public String get(final String key) throws Exception {
		final String completedKey = (prefix != null ? prefix : "") + key + (postfix != null ? postfix : "");
		return System.getenv(completedKey);
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

	public String getPostfix() {
		return postfix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPostfix(final String postfix) {
		this.postfix = postfix;
	}

	public void setPrefix(final String prefix) {
		this.prefix = prefix;
	}

}
