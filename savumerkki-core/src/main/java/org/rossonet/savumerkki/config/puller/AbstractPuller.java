package org.rossonet.savumerkki.config.puller;

import java.net.URL;

import org.rossonet.savumerkki.config.MonitoredConfig;

public abstract class AbstractPuller implements Puller {

	static Puller fromUrl(final URL url) {
		// TODO implementare Puller HTTP, HTTPS, DNS, GIT, LOCAL_FILE e S3
		return null;
	}

	static void registerPuller(final Class<Puller> pullerClass) {
		// TODO implementare registrazione per resolver da url
		// TODO registrazione automatica classi base
	}

	private final URL originalUrl;

	public AbstractPuller(final URL url) {
		this.originalUrl = url;
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getChannelControlValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getConfigGeneration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLastChannelControlValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonitoredConfig getMonitoredConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PullerCallback getPullerObserver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOneTimeOnlyConfigured() {
		return false;
	}

	@Override
	public void updateDelayMs(final long newDelay) {
		// TODO Auto-generated method stub

	}

	protected URL getOriginalUrl() {
		return originalUrl;
	}

	protected abstract String getProtocol();

}
