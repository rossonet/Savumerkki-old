package org.rossonet.savumerkki.config.puller;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.rossonet.savumerkki.config.MonitoredConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPuller implements Puller {

	private final static Logger LOG = LoggerFactory.getLogger(AbstractPuller.class);

	private static final Set<Class<? extends Puller>> pullers = new HashSet<>();

	static Puller fromUrl(final URL url) {
		// TODO implementare Puller HTTP, HTTPS, DNS, GIT, LOCAL_FILE e S3
		return null;
	}

	static Set<Class<? extends Puller>> getPullers() {
		return pullers;
	}

	static void registerPuller(final Class<? extends Puller> pullerClass) {
		LOG.info(pullerClass.getName() + " registered as puller");
		pullers.add(pullerClass);
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

	protected URL getOriginalUrl() {
		return originalUrl;
	}

	protected abstract String getProtocol();

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

}
