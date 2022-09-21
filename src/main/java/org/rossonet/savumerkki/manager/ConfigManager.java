package org.rossonet.savumerkki.manager;

import java.util.Collection;

import org.rossonet.savumerkki.config.MonitoredConfig;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventCallback;

public interface ConfigManager extends AutoCloseable {

	public static ConfigManagerBuilder getNewBuilder() {
		return new ConfigManagerBuilder();
	}

	public void addEnrichMap(EnrichMap enrichMap);

	public void addMonitoredConfig(MonitoredConfig monitoredConfig);

	public void addUpdateCallback(ConfigUpdateEventCallback callback);

	public long getConfigGeneration();

	public ConfigManagerFromToString getGlobalConfig();

	public Collection<EnrichMap> getGlobalEnrichMap();

	public long getGlobalTimeoutMs();

	public Collection<MonitoredConfig> getMonitoredConfigs();

	public default MonitoredConfigBuilder getNewMonitoredConfigBuilder() {
		return new MonitoredConfigBuilder(this);
	}

	public Collection<ConfigUpdateEventCallback> getUpdateCallbacks();

	public void removeEnrichMap(EnrichMap enrichMap);

	public void removeMonitoredConfig(MonitoredConfig monitoredConfig);

	public void removeUpdateCallback(ConfigUpdateEventCallback callback);

	public void reset();

	public void setGlobalTimeoutMs(long timeout);

	public void updateAll();

}
