package org.rossonet.savumerkki.manager;

import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

/**
 * Text Configuration Manager for json and yaml format
 *
 * @author andrea ambrosini
 *
 */
final class ConfigManagerInterpreter {

	static JSONObject getJsonFromConfig(final ConfigManager configManager) {
		// TODO JSON
		return null;
	}

	static Yaml getYamlFromConfig(final ConfigManager configManager) {
		// TODO Yaml
		return null;
	}

	private ConfigManagerInterpreter() {
		throw new UnsupportedOperationException("Just for static usage");
	}

}
