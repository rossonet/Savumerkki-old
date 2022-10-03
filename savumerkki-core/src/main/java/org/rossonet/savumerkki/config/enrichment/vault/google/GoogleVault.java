package org.rossonet.savumerkki.config.enrichment.vault.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vault.v1.Vault;

public class GoogleVault implements EnrichMap {

	private final String matter;
	private final int priority;

	private Vault service = null;
	private final long timeoutResolutionMs;

	public GoogleVault(final String applicationName, final Credential credential, final String matter) {
		this(applicationName, credential, matter, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public GoogleVault(final String applicationName, final Credential credential, final String matter,
			final int priority, final long timeoutResolutionMs) {
		this.priority = priority;
		this.timeoutResolutionMs = timeoutResolutionMs;
		this.matter = matter;
		try {
			this.service = new Vault.Builder(GoogleNetHttpTransport.newTrustedTransport(),
					GsonFactory.getDefaultInstance(), credential).setApplicationName(applicationName).build();
		} catch (GeneralSecurityException | IOException e) {
			this.service = null;
		}

	}

	@Override
	public boolean dontLogTheValue() {
		return true;
	}

	@Override
	public String get(final String key) throws Exception {
		return service != null ? service.matters().get(matter).get(key).toString() : null;
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
		if (service != null) {
			service = null;
		}

	}

}
