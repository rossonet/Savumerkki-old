package org.rossonet.savumerkki.config.enrichment.vault.azure;

import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azure.core.credential.BasicAuthenticationCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.core.util.Base64Util;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

public class AzureVault extends AbstractEnrichMap {

	private final static Logger LOG = LoggerFactory.getLogger(AzureVault.class);
	public static final String TOKEN_PASSWORD_FIELD = "token-password";

	public static final String TOKEN_USERNAME_FIELD = "token-username";

	public static final String URL_FIELD = "url";
	static {
		AbstractEnrichMap.registerEnrichMap(AzureVault.class);
	}
	private TokenCredential azureCredential;
	private String keyVaultUrl;
	private String password;

	private SecretClient secretClient = null;

	private String username;

	public AzureVault() {
		this(null, null, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public AzureVault(final TokenCredential azureCredential, final String keyVaultUrl) {
		this(azureCredential, keyVaultUrl, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public AzureVault(final TokenCredential azureCredential, final String keyVaultUrl, final int priority,
			final long timeoutResolutionMs) {
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		setDontLogTheValue(true);
		this.keyVaultUrl = keyVaultUrl;
		this.azureCredential = azureCredential;
	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		super.configureFromJson(jsonConfig);
		setAzureCredential(jsonConfig.getString(TOKEN_USERNAME_FIELD), jsonConfig.getString(TOKEN_PASSWORD_FIELD));
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		super.configureFromYaml(yamlConfig);
		// TODO Auto-generated method stub
		secretClient = null;

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
		final JSONObject json = super.getEnrichMapAsJson();
		json.put(URL_FIELD, keyVaultUrl);
		json.put(TOKEN_USERNAME_FIELD, username);
		json.put(TOKEN_PASSWORD_FIELD, password);
		return json;
	}

	@Override
	public String getEnrichMapAsYaml() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getKeyVaultUrl() {
		return keyVaultUrl;
	}

	public void setAzureCredential(final String username, final String password) {
		this.username = username;
		this.password = password;
		this.azureCredential = new BasicAuthenticationCredential(username, password);
		secretClient = null;
	}

	public void setAzureCredential(final TokenCredential azureTokenCredential) {
		this.azureCredential = azureTokenCredential;
		try {
			final String stringToken = azureTokenCredential.getToken(null).block().getToken();
			final String credentialDecoded = new String(Base64Util.decodeString(stringToken), StandardCharsets.UTF_8);
			this.username = credentialDecoded.split(":")[0];
			this.password = credentialDecoded.split(":")[1];
		} catch (final Exception a) {
			LOG.error("setting credential", a);
		}
		secretClient = null;
	}

	public void setKeyVaultUrl(final String keyVaultUrl) {
		this.keyVaultUrl = keyVaultUrl;
		secretClient = null;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("AzureVault [");
		if (keyVaultUrl != null) {
			builder.append("keyVaultUrl=");
			builder.append(keyVaultUrl);
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
