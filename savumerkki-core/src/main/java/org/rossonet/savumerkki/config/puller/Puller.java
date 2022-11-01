package org.rossonet.savumerkki.config.puller;

import java.net.URL;
import java.util.Set;
import java.util.Timer;

import org.rossonet.savumerkki.config.MonitoredConfig;

public interface Puller {

	public static Puller fromUrl(final URL url) {
		return AbstractPuller.fromUrl(url);
	}

	public static Set<Class<? extends Puller>> getPullers() {
		return AbstractPuller.getPullers();
	}

	public static void registerPuller(final Class<? extends Puller> pullerClass) {
		AbstractPuller.registerPuller(pullerClass);
	}

	public void fire();

	public String getChannelControlValue();

	public long getConfigGeneration();

	public long getDelayMs();

	public String getHost();

	/**
	 * @return a string the last update hash from the server
	 */
	public String getLastChannelControlValue();

	public MonitoredConfig getMonitoredConfig();

	public String getPath();

	public int getPort();

	public String getProtocol();

	public PullerCallback getPullerObserver();

	public long getTimeoutMs();

	public URL getUrl();

	public String getUserInfo();

	/**
	 * @return true if the config need to be download just one time from the remote
	 */
	public boolean isOneTimeOnlyConfigured();

	/**
	 * @param newDelay. The method also manage the Scheduled tasks after the
	 *                  update()
	 */
	public void setDelayMs(long delay);

	public void setHost(String host);

	public void setMonitoredConfig(MonitoredConfig monitoredConfig);

	public void setOneTimeOnlyConfigured(boolean oneTimeOnlyConfigured);

	public void setPath(String path);

	public void setPort(int port);

	public void setPullerObserver(PullerCallback pullerObserver);

	public void setTimeoutMs(long timeoutMs);

	public void setUserInfo(String userInfo);

	String getRef();

	Timer getTimer();

	void setRef(String ref);

	void setTimer(Timer timer);

}
