package org.rossonet.savumerkki.enrichment;

public interface EnrichMap {

	public static int DEFAULT_PRIORITY = 100;

	public String get(String key);

	public int getPriority();

}
