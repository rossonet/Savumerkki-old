package org.rossonet.savumerkki.config.enrichment.vault.azure;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

import com.azure.core.credential.TokenCredential;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

public class AzureVault implements EnrichMap {
	private final int priority;
	private SecretClient secretClient = null;

	private final long timeoutResolutionMs;

	public AzureVault(final TokenCredential azureCredential, final String keyVaultUrl) {
		this(azureCredential, keyVaultUrl, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public AzureVault(final TokenCredential azureCredential, final String keyVaultUrl, final int priority,
			final long timeoutResolutionMs) {
		this.priority = priority;
		this.timeoutResolutionMs = timeoutResolutionMs;
		this.secretClient = new SecretClientBuilder().vaultUrl(keyVaultUrl).credential(azureCredential).buildClient();
	}

	@Override
	public boolean dontLogTheValue() {
		return true;
	}

	@Override
	public String get(final String key) throws Exception {
		return secretClient != null ? secretClient.getSecret(key).getValue() : null;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public long getTimeoutResolutionMs() {
		return timeoutResolutionMs;
	}

	@Override
	public void resetConnection() {
		if (secretClient != null) {
			secretClient = null;
		}

	}
}
