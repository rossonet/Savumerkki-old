package org.rossonet.savumerkki.manager;

import java.util.Collection;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.MonitoredConfig;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventObserver;

public interface ConfigManager extends AutoCloseable {

	public static ConfigManagerBuilder getNewBuilder() {
		return new ConfigManagerBuilder();
	}

	public void addGlobalEnrichMap(EnrichMap enrichMap);

	public void addMonitoredConfig(MonitoredConfig monitoredConfig);

	public void addUpdateObserver(ConfigUpdateEventObserver observer);

	public void forceReloadAll();

	public JSONObject getConfigAsJson();

	public String getConfigAsYaml();

	public long getConfigGeneration();

	public Collection<EnrichMap> getGlobalEnrichMap();

	public long getGlobalTimeoutMs();

	public Collection<MonitoredConfig> getMonitoredConfigs();

	public default MonitoredConfigBuilder getNewMonitoredConfigBuilder() {
		return new MonitoredConfigBuilder(this);
	}

	public Collection<ConfigUpdateEventObserver> getUpdateObservers();

	public void removeGlobalEnrichMap(EnrichMap enrichMap);

	public void removeMonitoredConfig(MonitoredConfig monitoredConfig);

	public void removeUpdateObserver(ConfigUpdateEventObserver observer);

	public void reset();

	public void setGlobalTimeoutMs(long timeout);
}
