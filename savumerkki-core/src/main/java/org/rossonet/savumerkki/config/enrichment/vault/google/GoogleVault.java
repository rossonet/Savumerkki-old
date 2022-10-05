package org.rossonet.savumerkki.config.enrichment.vault.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.Credential.AccessMethod;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vault.v1.Vault;

public class GoogleVault extends AbstractEnrichMap {

	public static final String APPLICATION_NAME_FIELD = "applicationName";

	public static final String MATTER_FIELD = "matter";

	public static final String TOKEN_PASSWORD_FIELD = "token-password";

	public static final String TOKEN_USERNAME_FIELD = "token-username";

	static {
		EnrichMap.registerEnrichMap(GoogleVault.class);
	}

	private String applicationName;

	private Credential credential;

	private String matter;

	private String password;

	private Vault service = null;

	private String username;

	public GoogleVault() {
		this(null, null, null, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public GoogleVault(final String applicationName, final Credential credential, final String matter) {
		this(applicationName, credential, matter, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public GoogleVault(final String applicationName, final Credential credential, final String matter,
			final int priority, final long timeoutResolutionMs) {
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		setDontLogTheValue(true);
		this.matter = matter;
		this.applicationName = applicationName;
		this.credential = credential;

	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		super.configureFromJson(jsonConfig);
		setMatter(jsonConfig.getString(MATTER_FIELD));
		setApplicationName(jsonConfig.getString(APPLICATION_NAME_FIELD));
		setCredential(jsonConfig.getString(TOKEN_USERNAME_FIELD), jsonConfig.getString(TOKEN_PASSWORD_FIELD));
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		super.configureFromYaml(yamlConfig);
		// TODO implementazione YAML
		this.service = null;
	}

	@Override
	public String get(final String key) throws Exception {
		if (service == null) {
			synchronized (this) {
				if (service == null) {
					try {
						this.service = new Vault.Builder(GoogleNetHttpTransport.newTrustedTransport(),
								GsonFactory.getDefaultInstance(), credential).setApplicationName(applicationName)
										.build();
					} catch (GeneralSecurityException | IOException e) {
						this.service = null;
					}
				}
			}
		}
		return service != null ? service.matters().get(matter).get(key).toString() : null;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public Credential getCredential() {
		return credential;
	}

	@Override
	public JSONObject getEnrichMapAsJson() {
		final JSONObject json = super.getEnrichMapAsJson();
		json.put(MATTER_FIELD, matter);
		json.put(APPLICATION_NAME_FIELD, applicationName);
		json.put(TOKEN_USERNAME_FIELD, username);
		json.put(TOKEN_PASSWORD_FIELD, password);
		return json;
	}

	@Override
	public String getEnrichMapAsYaml() {
		// TODO implementazione YAML
		return null;
	}

	public String getMatter() {
		return matter;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setApplicationName(final String applicationName) {
		this.applicationName = applicationName;
		this.service = null;
	}

	public void setCredential(final Credential credential) {
		this.credential = credential;
		this.service = null;
	}

	public void setCredential(final String username, final String password) {
		this.username = username;
		this.password = password;
		final AccessMethod method = BearerToken.authorizationHeaderAccessMethod();
		final HttpExecuteInterceptor token = new BasicAuthentication(username, password);
		credential = new Credential.Builder(method).setClientAuthentication(token).build();
	}

	public void setMatter(final String matter) {
		this.matter = matter;
		this.service = null;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GoogleVault [");
		if (applicationName != null) {
			builder.append("applicationName=");
			builder.append(applicationName);
			builder.append(", ");
		}
		if (matter != null) {
			builder.append("matter=");
			builder.append(matter);
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
