package org.rossonet.savumerkki.config.enrichment.vault.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vault.v1.Vault;

public class GoogleVault extends AbstractEnrichMap {

	private String applicationName;

	private Credential credential;

	private String matter;

	private Vault service = null;

	public GoogleVault(final String applicationName, final Credential credential, final String matter) {
		this(applicationName, credential, matter, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public GoogleVault(final String applicationName, final Credential credential, final String matter,
			final int priority, final long timeoutResolutionMs) {
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		this.matter = matter;
		this.applicationName = applicationName;
		this.credential = credential;

	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		// TODO Auto-generated method stub
		this.service = null;
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		// TODO Auto-generated method stub
		this.service = null;
	}

	@Override
	public boolean dontLogTheValue() {
		return true;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEnrichMapAsYaml() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMatter() {
		return matter;
	}

	public void setApplicationName(final String applicationName) {
		this.applicationName = applicationName;
		this.service = null;
	}

	public void setCredential(final Credential credential) {
		this.credential = credential;
		this.service = null;
	}

	public void setMatter(final String matter) {
		this.matter = matter;
		this.service = null;
	}

}
