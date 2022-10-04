package org.rossonet.savumerkki.config.enrichment.external;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

public class ExternalEnrich extends AbstractEnrichMap {

	public static final String FUNCTION_FIELD = "function";
	static {
		AbstractEnrichMap.registerEnrichMap(ExternalEnrich.class);
	}

	private ExternalEnrichFunction function;

	public ExternalEnrich() {
		this(EnrichMap.DEFAULT_PRIORITY, null, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS, EnrichMap.DEFAULT_NOT_LOG);
	}

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
		super.configureFromJson(jsonConfig);
		final String functionClass = jsonConfig.getString(FUNCTION_FIELD);
		try {
			final Constructor<?> functionConstructor = Class.forName(functionClass).getConstructor();
			function = (ExternalEnrichFunction) functionConstructor.newInstance();
		} catch (SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
			throw new ConfigurationException(e);
		}
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		super.configureFromYaml(yamlConfig);
		// TODO Auto-generated method stub

	}

	@Override
	public String get(final String key) {
		return function.getValue(key);
	}

	@Override
	public JSONObject getEnrichMapAsJson() {
		final JSONObject json = super.getEnrichMapAsJson();
		json.put(FUNCTION_FIELD, function.getClass().getName());
		return json;
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

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ExternalEnrich [");
		if (function != null) {
			builder.append("function=");
			builder.append(function);
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
