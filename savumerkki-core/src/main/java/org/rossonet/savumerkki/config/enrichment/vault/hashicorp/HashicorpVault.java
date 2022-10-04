package org.rossonet.savumerkki.config.enrichment.vault.hashicorp;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;

public class HashicorpVault extends AbstractEnrichMap {

	private String logicalVaultKey;
	private Vault vault = null;
	private VaultConfig vaultConfig;

	public HashicorpVault(final int priority, final VaultConfig vaultConfig, final String logicalVaultKey,
			final long timeoutResolutionMs) {
		this.vaultConfig = vaultConfig;
		this.logicalVaultKey = logicalVaultKey;
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
	}

	public HashicorpVault(final VaultConfig vaultConfig, final String logicalVaultKey) {
		this(EnrichMap.DEFAULT_PRIORITY, vaultConfig, logicalVaultKey, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		// TODO Auto-generated method stub
		vault = null;
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		// TODO Auto-generated method stub
		vault = null;
	}

	@Override
	public boolean dontLogTheValue() {
		return true;
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
		// TODO Auto-generated method stub
		return null;
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

}
