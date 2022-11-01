package org.rossonet.savumerkki.config;

import java.util.Collection;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventObserver;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.validator.Validator;
import org.rossonet.savumerkki.manager.ConfigManager;
import org.rossonet.savumerkki.manager.ConfigInterpreter;

public interface MonitoredConfig extends AutoCloseable {

	public void addEnrichMap(EnrichMap enrichMap);

	public void addPuller(Puller puller);

	public void addUpdateObserver(ConfigUpdateEventObserver observer);

	public void forceReload();

	public default JSONObject getConfigAsJson() {
		return ConfigInterpreter.getJsonFromMonitoredConfig(this);
	}

	public default String getConfigAsYaml() {
		return ConfigInterpreter.getYamlFromMonitoredConfig(this);
	}

	public long getConfigGeneration();

	public ConfigManager getConfigManager();

	public Validator getConfigValidator();

	public Collection<EnrichMap> getEnrichMap();

	public String getLastConfigPopolatedSha256();

	public String getLastConfigTemplateSha256();

	public long getMaxUpdateDelayMs();

	public long getMinUpdateDelayMs();

	public Collection<Puller> getPuller();

	public TemplateVariable getTemplateVariable();

	public long getUpdateDelayMs();

	public Collection<ConfigUpdateEventObserver> getUpdateObservers();

	public Validator getValidator();

	public boolean isAdaptableUpdateDelay();

	public void pushTemplateConfig(String templateConfig);

	public void removeEnrichMap(EnrichMap enrichMap);

	public void removePuller(Puller puller);

	public void removeUpdateObserver(ConfigUpdateEventObserver observer);

	public void reset();

	public void setAdaptableUpdateDelay(boolean isActive);

	public long setDefaultUpdateDelayMs(long valueInMs);

	public void setMaxUpdateDelayMs(long valueInMs);

	public void setMinUpdateDelayMs(long valueInMs);

	public void setTemplateVariable(TemplateVariable templateVariable);

	public void setValidator(Validator validator);
}
