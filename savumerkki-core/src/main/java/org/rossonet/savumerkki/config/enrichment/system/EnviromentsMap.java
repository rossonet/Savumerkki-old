package org.rossonet.savumerkki.config.enrichment.system;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

public class EnviromentsMap extends AbstractEnrichMap {

	public static final String POSTFIX_FIELD = "postfix";

	public static final String PREFIX_FIELD = "prefix";

	static {
		EnrichMap.registerEnrichMap(EnviromentsMap.class);
	}

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
		super.configureFromJson(jsonConfig);
		postfix = jsonConfig.getString(POSTFIX_FIELD);
		prefix = jsonConfig.getString(PREFIX_FIELD);
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		super.configureFromYaml(yamlConfig);
		// TODO Auto-generated method stub

	}

	@Override
	public String get(final String key) throws Exception {
		final String completedKey = (prefix != null ? prefix : "") + key + (postfix != null ? postfix : "");
		return System.getenv(completedKey);
	}

	@Override
	public JSONObject getEnrichMapAsJson() {
		final JSONObject json = super.getEnrichMapAsJson();
		json.put(PREFIX_FIELD, prefix);
		json.put(POSTFIX_FIELD, postfix);
		return json;
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

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("EnviromentsMap [");
		if (postfix != null) {
			builder.append("postfix=");
			builder.append(postfix);
			builder.append(", ");
		}
		if (prefix != null) {
			builder.append("prefix=");
			builder.append(prefix);
			builder.append(", ");
		}
		if (super.toString() != null) {
			builder.append("toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}

}
