package org.rossonet.savumerkki.config.enrichment.vault.dns;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.utils.DnsHelper;
import org.rossonet.savumerkki.utils.TextHelper;

public class DnsVault extends AbstractEnrichMap {

	private String domain;
	private String secretKey;

	public DnsVault(final String domain, final String secretKey) {
		this(domain, secretKey, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public DnsVault(final String domain, final String secretKey, final int priority, final long timeoutResolutionMs) {
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		this.domain = domain;
		this.secretKey = secretKey;

	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean dontLogTheValue() {
		return true;
	}

	@Override
	public String get(final String key) throws Exception {
		final String encryptedData = DnsHelper.fromDnsRecord(key, domain);
		return new String(TextHelper.decryptData(encryptedData.getBytes(), secretKey.getBytes()));
	}

	public String getDomain() {
		return domain;
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

	public String getSecretKey() {
		return secretKey;
	}

	public void setDomain(final String domain) {
		this.domain = domain;
	}

	public void setSecretKey(final String secretKey) {
		this.secretKey = secretKey;
	}

}
