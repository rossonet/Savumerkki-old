package org.rossonet.savumerkki.config.enrichment.external;

public interface ExternalEnrichFunction {

	public String getValue(String key);

	public void reset();

}
