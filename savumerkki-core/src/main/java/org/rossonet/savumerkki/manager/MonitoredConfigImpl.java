package org.rossonet.savumerkki.manager;

import java.util.Collection;

import org.rossonet.savumerkki.config.MonitoredConfig;
import org.rossonet.savumerkki.config.TemplateVariable;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventObserver;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.validator.Validator;

public class MonitoredConfigImpl implements MonitoredConfig {

	// mantenere non pubblico!
	MonitoredConfigImpl(final ConfigManager manager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addEnrichMap(final EnrichMap enrichMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPuller(final Puller puller) {
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
	public void forceReload() {
		// TODO Auto-generated method stub

	}

	@Override
	public long getConfigGeneration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ConfigManager getConfigManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator getConfigValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<EnrichMap> getEnrichMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastConfigPopolatedSha256() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastConfigTemplateSha256() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getMaxUpdateDelayMs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getMinUpdateDelayMs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Puller> getPuller() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateVariable getTemplateVariable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUpdateDelayMs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<ConfigUpdateEventObserver> getUpdateObservers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdaptableUpdateDelay() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pushTemplateConfig(final String templateConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEnrichMap(final EnrichMap enrichMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePuller(final Puller puller) {
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
	public void setAdaptableUpdateDelay(final boolean isActive) {
		// TODO Auto-generated method stub

	}

	@Override
	public long setDefaultUpdateDelayMs(final long valueInMs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxUpdateDelayMs(final long valueInMs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMinUpdateDelayMs(final long valueInMs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTemplateVariable(final TemplateVariable templateVariable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValidator(final Validator validator) {
		// TODO Auto-generated method stub

	}

}
