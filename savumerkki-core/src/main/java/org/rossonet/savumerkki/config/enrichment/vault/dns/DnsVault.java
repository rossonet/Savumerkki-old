package org.rossonet.savumerkki.config.enrichment.vault.dns;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.utils.DnsHelper;
import org.rossonet.savumerkki.utils.TextHelper;

public class DnsVault extends AbstractEnrichMap {

	public static final String DOMAIN_FIELD = "domain";
	public static final String SECRET_FIELD = "secret-key";

	static {
		AbstractEnrichMap.registerEnrichMap(DnsVault.class);
	}
	private String domain;

	private String secretKey;

	public DnsVault() {
		this(null, null, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public DnsVault(final String domain, final String secretKey) {
		this(domain, secretKey, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public DnsVault(final String domain, final String secretKey, final int priority, final long timeoutResolutionMs) {
		setPriority(priority);
		setTimeoutResolutionMs(timeoutResolutionMs);
		setDontLogTheValue(true);
		this.domain = domain;
		this.secretKey = secretKey;

	}

	@Override
	public void configureFromJson(final JSONObject jsonConfig) {
		super.configureFromJson(jsonConfig);
		domain = jsonConfig.getString(DOMAIN_FIELD);
		secretKey = jsonConfig.getString(SECRET_FIELD);
	}

	@Override
	public void configureFromYaml(final String yamlConfig) {
		super.configureFromYaml(yamlConfig);
		// TODO Auto-generated method stub
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
		final JSONObject json = super.getEnrichMapAsJson();
		json.put(DOMAIN_FIELD, domain);
		json.put(SECRET_FIELD, secretKey);
		return json;
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

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("DnsVault [");
		if (domain != null) {
			builder.append("domain=");
			builder.append(domain);
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
