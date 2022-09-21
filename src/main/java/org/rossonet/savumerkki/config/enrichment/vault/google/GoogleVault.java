package org.rossonet.savumerkki.config.enrichment.vault.google;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;

// TODO Completare!
public class GoogleVault implements EnrichMap {

	private final String logicalVaultKey;
	private final int priority;
	private final long timeoutResolutionMs;
	private Vault vault = null;
	private final VaultConfig vaultConfig;

	public GoogleVault(final int priority, final VaultConfig vaultConfig, final String logicalVaultKey,
			final long timeoutResolutionMs) {
		this.priority = priority;
		this.vaultConfig = vaultConfig;
		this.logicalVaultKey = logicalVaultKey;
		this.timeoutResolutionMs = timeoutResolutionMs;
	}

	public GoogleVault(final VaultConfig vaultConfig, final String logicalVaultKey) {
		this(EnrichMap.DEFAULT_PRIORITY, vaultConfig, logicalVaultKey, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	@Override
	public boolean dontLogTheValue() {
		return true;
	}

	@Override
	public String get(final String key) throws Exception {
		if (vault == null) {
			vault = new Vault(vaultConfig);
		}
		return vault.logical().read(logicalVaultKey).getData().get(key);
	}

	public String getLogicalVaultKey() {
		return logicalVaultKey;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public long getTimeoutResolutionMs() {
		return timeoutResolutionMs;
	}

	public VaultConfig getVaultConfig() {
		return vaultConfig;
	}

}
