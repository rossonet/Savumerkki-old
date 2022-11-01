package org.rossonet.savumerkki.manager;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.MonitoredConfig;
import org.rossonet.savumerkki.config.TemplateVariable;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventObserver;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.validator.Validator;

public class MonitoredConfigBuilder {

	private final MonitoredConfig monitoredConfig;

	// mantenere non pubblico!
	MonitoredConfigBuilder(final ConfigManager manager) {
		this.monitoredConfig = new MonitoredConfigImpl(manager);
	}

	MonitoredConfigBuilder(final ConfigManager manager, final JSONObject jsonConfig) {
		this.monitoredConfig = ConfigInterpreter.getMonitoredConfigFromJson(jsonConfig);
		((MonitoredConfigImpl) monitoredConfig).setConfigManager(manager);
	}

	MonitoredConfigBuilder(final ConfigManager manager, final String yamlConfig) {
		this.monitoredConfig = ConfigInterpreter.getMonitoredConfigFromYaml(yamlConfig);
		((MonitoredConfigImpl) monitoredConfig).setConfigManager(manager);
	}

	public MonitoredConfigBuilder addEnrichMap(final EnrichMap enrichMap) {
		monitoredConfig.addEnrichMap(enrichMap);
		return this;
	}

	public MonitoredConfigBuilder addPuller(final Puller puller) {
		puller.setMonitoredConfig(monitoredConfig);
		monitoredConfig.addPuller(puller);
		return this;
	}

	public MonitoredConfigBuilder addUpdateObserver(final ConfigUpdateEventObserver observer) {
		monitoredConfig.addUpdateObserver(observer);
		return this;
	}

	public MonitoredConfig build() {
		return monitoredConfig;
	}

	public MonitoredConfigBuilder setAdaptableUpdateDelay(final boolean isActive) {
		monitoredConfig.setAdaptableUpdateDelay(isActive);
		return this;
	}

	public MonitoredConfigBuilder setDefaultUpdateDelayMs(final long valueInMs) {
		monitoredConfig.setDefaultUpdateDelayMs(valueInMs);
		return this;
	}

	public MonitoredConfigBuilder setMaxUpdateDelayMs(final long valueInMs) {
		monitoredConfig.setMaxUpdateDelayMs(valueInMs);
		return this;
	}

	public MonitoredConfigBuilder setMinUpdateDelayMs(final long valueInMs) {
		monitoredConfig.setMinUpdateDelayMs(valueInMs);
		return this;
	}

	public MonitoredConfigBuilder setTemplateVariable(final TemplateVariable templateVariable) {
		monitoredConfig.setTemplateVariable(templateVariable);
		return this;
	}

	public MonitoredConfigBuilder setValidator(final Validator validator) {
		monitoredConfig.setValidator(validator);
		return this;
	}

}
