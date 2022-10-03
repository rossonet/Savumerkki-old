package org.rossonet.savumerkki.config.enrichment.vault.dns;

import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.utils.DnsHelper;
import org.rossonet.savumerkki.utils.TextHelper;

public class DnsVault implements EnrichMap {

	private final String domain;
	private final int priority;
	private final String secretKey;

	private final long timeoutResolutionMs;

	public DnsVault(final String domain, final String secretKey) {
		this(domain, secretKey, EnrichMap.DEFAULT_PRIORITY, EnrichMap.DEFAULT_TIMEOUT_RESOLUTION_MS);
	}

	public DnsVault(final String domain, final String secretKey, final int priority, final long timeoutResolutionMs) {
		this.priority = priority;
		this.timeoutResolutionMs = timeoutResolutionMs;
		this.domain = domain;
		this.secretKey = secretKey;

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
	public int getPriority() {
		return priority;
	}

	@Override
	public long getTimeoutResolutionMs() {
		return timeoutResolutionMs;
	}

	@Override
	public void resetConnection() {
		// nothing to do
	}

}
