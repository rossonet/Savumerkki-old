package org.rossonet.savumerkki.config.enrichment.vault.azure;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

import com.azure.core.credential.TokenCredential;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

public class AzureVault extends AbstractEnrichMap {

	private TokenCredential azureCredential;
	private String keyVaultUrl;
	private SecretClient secretClient = null;

	public AzureVault(final TokenCredential azureCredential, final String keyVaultUrl) {
		this(azureCredential, keyVaultUrl, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public AzureVault(final TokenCredential azureCredential, final String keyVaultUrl, final int priority,
			final long timeoutResolutionMs) {
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		this.keyVaultUrl = keyVaultUrl;
		this.azureCredential = azureCredential;
	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		// TODO Auto-generated method stub
		secretClient = null;
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		// TODO Auto-generated method stub
		secretClient = null;

	}

	@Override
	public boolean dontLogTheValue() {
		return true;
	}

	@Override
	public String get(final String key) throws Exception {
		if (secretClient == null) {
			synchronized (this) {
				if (secretClient == null) {
					secretClient = new SecretClientBuilder().vaultUrl(keyVaultUrl).credential(azureCredential)
							.buildClient();
				}
			}
		}
		return secretClient != null ? secretClient.getSecret(key).getValue() : null;
	}

	public TokenCredential getAzureCredential() {
		return azureCredential;
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

	public String getKeyVaultUrl() {
		return keyVaultUrl;
	}

	public void setAzureCredential(final TokenCredential azureCredential) {
		this.azureCredential = azureCredential;
		secretClient = null;
	}

	public void setKeyVaultUrl(final String keyVaultUrl) {
		this.keyVaultUrl = keyVaultUrl;
		secretClient = null;
	}
}
