package org.rossonet.savumerkki.config.enrichment.vault.hashicorp;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;

public class HashicorpVault implements EnrichMap {

	private final String logicalVaultKey;
	private final int priority;
	private final long timeoutResolutionMs;
	private Vault vault = null;
	private final VaultConfig vaultConfig;

	public HashicorpVault(final int priority, final VaultConfig vaultConfig, final String logicalVaultKey,
			final long timeoutResolutionMs) {
		this.priority = priority;
		this.vaultConfig = vaultConfig;
		this.logicalVaultKey = logicalVaultKey;
		this.timeoutResolutionMs = timeoutResolutionMs;
	}

	public HashicorpVault(final VaultConfig vaultConfig, final String logicalVaultKey) {
		this(EnrichMap.DEFAULT_PRIORITY, vaultConfig, logicalVaultKey, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
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

	@Override
	public synchronized void resetConnection() {
		vault = null;
	}

}
