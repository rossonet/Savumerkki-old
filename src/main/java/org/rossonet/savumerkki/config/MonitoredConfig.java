package org.rossonet.savumerkki.config;

import java.util.Collection;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventCallback;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.validator.Validator;

public interface MonitoredConfig extends AutoCloseable {

	public void addEnrichMap(EnrichMap enrichMap);

	public void addPuller(Puller puller);

	public void addUpdateCallback(ConfigUpdateEventCallback callback);

	public long getConfigGeneration();

	public Validator getConfigValidator();

	public Collection<EnrichMap> getEnrichMap();

	public String getLastConfigPopolatedSha256();

	public String getLastConfigTemplateSha256();

	public long getMaxUpdateDelayMs();

	public long getMinUpdateDelayMs();

	public Collection<Puller> getPuller();

	public TemplateVariable getTemplateVariable();

	public Collection<ConfigUpdateEventCallback> getUpdateCallbacks();

	public long getUpdateDelayMs();

	public boolean isAdaptableUpdateDelay();

	public void pushTemplateConfig(String templateConfig);

	public void removeEnrichMap(EnrichMap enrichMap);

	public void removePuller(Puller puller);

	public void removeUpdateCallback(ConfigUpdateEventCallback callback);

	public void reset();

	public void setAdaptableUpdateDelay(boolean isActive);

	public long setDefaultUpdateDelayMs(long valueInMs);

	public void setMaxUpdateDelayMs(long valueInMs);

	public void setMinUpdateDelayMs(long valueInMs);

	public void update();
}
