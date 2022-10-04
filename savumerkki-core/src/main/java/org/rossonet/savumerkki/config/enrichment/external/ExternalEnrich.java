package org.rossonet.savumerkki.config.enrichment.external;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

public class ExternalEnrich extends AbstractEnrichMap {

	private ExternalEnrichFunction function;

	public ExternalEnrich(final ExternalEnrichFunction function) {
		this(EnrichMap.DEFAULT_PRIORITY, function, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS, EnrichMap.DEFAULT_NOT_LOG);
	}

	public ExternalEnrich(final int priority, final ExternalEnrichFunction function, final long timeoutResolutionMs,
			final boolean dontLogTheValue) {
		this.function = function;
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		setDontLogTheValue(dontLogTheValue);
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
		return function.getValue(key);
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

	public ExternalEnrichFunction getFunction() {
		return function;
	}

	@Override
	public void resetConnection() {
		function.reset();
	}

	public void setFunction(final ExternalEnrichFunction function) {
		this.function = function;
	}

}
