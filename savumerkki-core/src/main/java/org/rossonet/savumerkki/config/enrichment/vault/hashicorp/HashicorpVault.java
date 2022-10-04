package org.rossonet.savumerkki.config.enrichment.vault.hashicorp;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.utils.TextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;

public class HashicorpVault extends AbstractEnrichMap {

	private final static Logger LOG = LoggerFactory.getLogger(HashicorpVault.class);
	public static final String LOGICAL_VAULT_KEY_FIELD = "logical-vault-key";

	public static final String VAULT_CONFIG_KEY_FIELD = "vault-config";
	static {
		AbstractEnrichMap.registerEnrichMap(HashicorpVault.class);
	}
	private String logicalVaultKey;

	private Vault vault = null;

	private VaultConfig vaultConfig;

	public HashicorpVault() {
		this(EnrichMap.DEFAULT_PRIORITY, null, null, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public HashicorpVault(final int priority, final VaultConfig vaultConfig, final String logicalVaultKey,
			final long timeoutResolutionMs) {
		this.vaultConfig = vaultConfig;
		this.logicalVaultKey = logicalVaultKey;
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		setDontLogTheValue(true);
	}

	public HashicorpVault(final VaultConfig vaultConfig, final String logicalVaultKey) {
		this(EnrichMap.DEFAULT_PRIORITY, vaultConfig, logicalVaultKey, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		super.configureFromJson(jsonConfig);
		logicalVaultKey = jsonConfig.getString(LOGICAL_VAULT_KEY_FIELD);
		try {
			vaultConfig = TextHelper.objectFromString(jsonConfig.getString(VAULT_CONFIG_KEY_FIELD), VaultConfig.class);
		} catch (ClassNotFoundException | JSONException | IOException e) {
			LOG.error("configuration from json", e);
		}
		vault = null;
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		super.configureFromYaml(yamlConfig);
		// TODO Auto-generated method stub
		vault = null;
	}

	@Override
	public String get(final String key) throws Exception {
		if (vault == null) {
			synchronized (this) {
				if (vault == null) {
					vault = new Vault(vaultConfig);
				}
			}
		}
		return vault.logical().read(logicalVaultKey).getData().get(key);
	}

	@Override
	public JSONObject getEnrichMapAsJson() {
		final JSONObject json = super.getEnrichMapAsJson();
		json.put(LOGICAL_VAULT_KEY_FIELD, logicalVaultKey);
		try {
			json.put(VAULT_CONFIG_KEY_FIELD, TextHelper.objectToString(vaultConfig));
		} catch (JSONException | IOException e) {
			LOG.error("configuration to json", e);
		}
		return json;
	}

	@Override
	public String getEnrichMapAsYaml() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLogicalVaultKey() {
		return logicalVaultKey;
	}

	public VaultConfig getVaultConfig() {
		return vaultConfig;
	}

	public void setLogicalVaultKey(final String logicalVaultKey) {
		this.logicalVaultKey = logicalVaultKey;
		vault = null;
	}

	public void setVaultConfig(final VaultConfig vaultConfig) {
		this.vaultConfig = vaultConfig;
		vault = null;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("HashicorpVault [");
		if (logicalVaultKey != null) {
			builder.append("logicalVaultKey=");
			builder.append(logicalVaultKey);
			builder.append(", ");
		}
		if (super.toString() != null) {
			builder.append("toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}

}
