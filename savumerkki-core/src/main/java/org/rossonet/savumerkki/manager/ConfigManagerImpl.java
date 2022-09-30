package org.rossonet.savumerkki.manager;

import java.util.Collection;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.MonitoredConfig;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventObserver;

public class ConfigManagerImpl implements ConfigManager {

	// mantenere non pubblico!
	ConfigManagerImpl() {

	}

	@Override
	public void addGlobalEnrichMap(final EnrichMap enrichMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMonitoredConfig(final MonitoredConfig monitoredConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUpdateObserver(final ConfigUpdateEventObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void forceReloadAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject getConfigAsJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConfigAsYaml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getConfigGeneration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<EnrichMap> getGlobalEnrichMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGlobalTimeoutMs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<MonitoredConfig> getMonitoredConfigs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigUpdateEventObserver> getUpdateObservers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeGlobalEnrichMap(final EnrichMap enrichMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMonitoredConfig(final MonitoredConfig monitoredConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUpdateObserver(final ConfigUpdateEventObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGlobalTimeoutMs(final long timeout) {
		// TODO Auto-generated method stub

	}

}
