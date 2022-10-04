package org.rossonet.savumerkki.manager;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.MonitoredConfig;

/**
 * Text Configuration Manager for json and yaml format
 *
 * @author andrea ambrosini
 *
 */
public final class ConfigInterpreter {

	public static ConfigManager getConfigManagerFromJson(final JSONObject configManager) {
		// TODO JSON implementare configurazione in json
		return null;
	}

	public static ConfigManager getConfigManagerFromYaml(final String configManager) {
		// TODO implementare configurazione in Yaml
		return null;
	}

	public static JSONObject getJsonFromConfigManager(final ConfigManager configManager) {
		// TODO JSON implementare configurazione in json
		return null;
	}

	public static JSONObject getJsonFromMonitoredConfig(final MonitoredConfig monitoredConfig) {
		// TODO Auto-generated method stub
		return null;
	}

	public static MonitoredConfig getMonitoredConfigFromJson(final JSONObject jsonConfig) {
		// TODO Auto-generated method stub
		return null;
	}

	public static MonitoredConfig getMonitoredConfigFromYaml(final String yamlConfig) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getYamlFromConfigManager(final ConfigManager configManager) {
		// TODO implementare configurazione in Yaml
		return null;
	}

	public static String getYamlFromMonitoredConfig(final MonitoredConfig monitoredConfig) {
		// TODO Auto-generated method stub
		return null;
	}

	private ConfigInterpreter() {
		throw new UnsupportedOperationException("Just for static usage");
	}

}
