package org.rossonet.savumerkki.manager;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.MonitoredConfig;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventObserver;

public class ConfigManagerBuilder {

	private ConfigManager configManager;

	// mantenere non pubblico!
	ConfigManagerBuilder() {
		configManager = new ConfigManagerImpl();
	}

	public ConfigManagerBuilder addGlobalEnrichMap(final EnrichMap enrichMap) {
		configManager.addGlobalEnrichMap(enrichMap);
		return this;
	}

	public ConfigManagerBuilder addMonitoredConfig(final MonitoredConfig monitoredConfig) {
		configManager.addMonitoredConfig(monitoredConfig);
		return this;
	}

	public ConfigManagerBuilder addMonitoredConfig(final MonitoredConfigBuilder monitoredConfigBuilder) {
		return addMonitoredConfig(monitoredConfigBuilder.build());
	}

	public ConfigManagerBuilder addUpdateObserver(final ConfigUpdateEventObserver observer) {
		configManager.addUpdateObserver(observer);
		return this;
	}

	public ConfigManager build() {
		return configManager;
	}

	public ConfigManagerBuilder fromJson(final JSONObject jsonConfiguration) {
		configManager = ConfigInterpreter.getConfigManagerFromJson(jsonConfiguration);
		return this;
	}

	public ConfigManagerBuilder fromYaml(final String yamlConfiguration) {
		configManager = ConfigInterpreter.getConfigManagerFromYaml(yamlConfiguration);
		return this;
	}

	public MonitoredConfigBuilder newMonitoredConfigBuilder() {
		return configManager.getNewMonitoredConfigBuilder();
	}

	public ConfigManagerBuilder setGlobalTimeoutMs(final long timeout) {
		configManager.setGlobalTimeoutMs(timeout);
		return this;
	}

}
