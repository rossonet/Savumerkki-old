package org.rossonet.savumerkki.manager;

import org.json.JSONObject;

/**
 * Text Configuration Manager for json and yaml format
 *
 * @author andrea ambrosini
 *
 */
final class ConfigManagerInterpreter {

	static ConfigManager getConfigFromJson(final JSONObject configManager) {
		// TODO JSON implementare configurazione in json
		return null;
	}

	static ConfigManager getConfigFromYaml(final String configManager) {
		// TODO implementare configurazione in Yaml
		return null;
	}

	static JSONObject getJsonFromConfig(final ConfigManager configManager) {
		// TODO JSON implementare configurazione in json
		return null;
	}

	static String getYamlFromConfig(final ConfigManager configManager) {
		// TODO implementare configurazione in Yaml
		return null;
	}

	private ConfigManagerInterpreter() {
		throw new UnsupportedOperationException("Just for static usage");
	}

}
