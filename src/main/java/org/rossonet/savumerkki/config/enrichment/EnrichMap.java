package org.rossonet.savumerkki.config.enrichment;

public interface EnrichMap {

	public static boolean DEFAULT_NOT_LOG = false;
	public static int DEFAULT_PRIORITY = 100;
	public static int DEFAULT_TIMEOUT_RESOLUTION_MS = 60000;

	public boolean dontLogTheValue();

	public String get(String key) throws Exception;

	public int getPriority();

	public long getTimeoutResolutionMs();

}
