package org.rossonet.savumerkki.config.puller;

import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.rossonet.savumerkki.config.MonitoredConfig;
import org.rossonet.savumerkki.config.enrichment.external.ConfigurationException;
import org.rossonet.savumerkki.utils.TextHelper;
import org.rossonet.savumerkki.utils.ThreadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPuller implements Puller {

	public static String DELAY_PARAMETER = "delay";

	public static String ONE_TIME_PARAMETER = "one-time";

	public static String TIMEOUT_PARAMETER = "timeout";

	private final static Logger LOG = LoggerFactory.getLogger(AbstractPuller.class);

	private static final Set<Class<? extends Puller>> pullers = new HashSet<>();

	static Puller fromUrl(final URL url) {
		Puller puller = null;
		try {
			for (final Class<? extends Puller> p : pullers) {
				if (p.newInstance().getProtocol().equals(url.getProtocol())) {
					final Constructor<? extends Puller> constructor = p.getConstructor(URL.class);
					puller = constructor.newInstance(url);
					break;
				}
			}
		} catch (final Exception e) {
			LOG.error("working on url " + url, e);
		}
		return puller;
	}

	static Set<Class<? extends Puller>> getPullers() {
		return pullers;
	}

	static void registerPuller(final Class<? extends Puller> pullerClass) {
		LOG.info(pullerClass.getName() + " registered as puller");
		pullers.add(pullerClass);
	}

	private long configGeneration = 1L;

	private long delayMs = 1000L;

	private String host;

	private PullerContent lastData = null;
	private MonitoredConfig monitoredConfig = null;

	private PullerContent notifiedData = null;

	private boolean oneTimeOnlyConfigured = false;

	private String path;

	private int port;

	private PullerCallback pullerObserver = null;

	private String ref;

	private long timeoutMs = 6000L;

	private Timer timer = null;

	private final TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			try {
				ThreadHelper.runWithTimeout(new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						fire();
						return null;
					}
				}, timeoutMs, TimeUnit.MILLISECONDS);
			} catch (final Exception e) {
				LOG.error("timer task of " + getUrl(), e);
			}

		}
	};

	private String userInfo;

	public AbstractPuller() {

	}

	public AbstractPuller(final URL url) {
		if (url.getProtocol().equals(getProtocol())) {
			elaborateUrl(url);
			addTimerTask();
		} else {
			throw new ConfigurationException(
					"protocol " + url.getProtocol() + " not match with managed protocol " + getProtocol());
		}
	}

	@Override
	public void fire() {
		if (getPullerObserver() != null) {
			if (!(oneTimeOnlyConfigured && notifiedData != null)) {
				lastData = getContentData();
				if (!lastData.getControlValue().equals(notifiedData.getControlValue())) {
					if (lastData.getDateTime() == null
							|| (!lastData.getDateTime().isAfter(notifiedData.getDateTime()))) {
						notifiedData = lastData;
						configGeneration++;
						final NewConfigFromPuller event = new NewConfigFromPuller(this, notifiedData.getData(),
								configGeneration, notifiedData.getControlValue());
						getPullerObserver().newConfigFound(event);
					}
				}
			}
		}
	}

	@Override
	public String getChannelControlValue() {
		return notifiedData.getControlValue();
	}

	@Override
	public long getConfigGeneration() {
		return configGeneration;
	}

	@Override
	public long getDelayMs() {
		return delayMs;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getLastChannelControlValue() {
		return lastData.getControlValue();
	}

	@Override
	public MonitoredConfig getMonitoredConfig() {
		return monitoredConfig;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public PullerCallback getPullerObserver() {
		return pullerObserver;
	}

	@Override
	public String getRef() {
		return ref;
	}

	@Override
	public long getTimeoutMs() {
		return timeoutMs;
	}

	@Override
	public Timer getTimer() {
		return timer;
	}

	@Override
	public URL getUrl() {
		final String spec = getProtocol() + "://" + (getUserInfo() != null ? getUserInfo() + "@" : "") + getHost()
				+ (getPort() != 0 ? ":" + getPort() : "") + (getPath() != null ? "/" + getPath() : "") + "?"
				+ getUrlQueryPart();
		URL url = null;
		try {
			url = new URL(spec);
		} catch (final MalformedURLException e) {
			LOG.info("parsing " + spec + " as url", e);
		}
		return url;
	}

	@Override
	public String getUserInfo() {
		return userInfo;
	}

	@Override
	public boolean isOneTimeOnlyConfigured() {
		return oneTimeOnlyConfigured;
	}

	@Override
	public void setDelayMs(final long delay) {
		this.delayMs = delay;
	}

	@Override
	public void setHost(final String host) {
		this.host = host;
	}

	@Override
	public void setMonitoredConfig(final MonitoredConfig monitoredConfig) {
		this.monitoredConfig = monitoredConfig;
	}

	@Override
	public void setOneTimeOnlyConfigured(final boolean oneTimeOnlyConfigured) {
		this.oneTimeOnlyConfigured = oneTimeOnlyConfigured;
	}

	@Override
	public void setPath(final String path) {
		this.path = path;
	}

	@Override
	public void setPort(final int port) {
		this.port = port;
	}

	@Override
	public void setPullerObserver(final PullerCallback pullerObserver) {
		this.pullerObserver = pullerObserver;
	}

	@Override
	public void setRef(final String ref) {
		this.ref = ref;
	}

	@Override
	public void setTimeoutMs(final long timeoutMs) {
		this.timeoutMs = timeoutMs;
	}

	@Override
	public void setTimer(final Timer timer) {
		this.timer = timer;
	}

	@Override
	public void setUserInfo(final String userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("AbstractPuller [configGeneration=");
		builder.append(configGeneration);
		builder.append(", host=");
		builder.append(host);
		builder.append(", port=");
		builder.append(port);
		builder.append(", path=");
		builder.append(path);
		builder.append("]");
		return builder.toString();
	}

	private void addTimerTask() {
		if (timer == null) {
			LOG.info("created timer for puller " + this);
			timer = new Timer();
		}
		timer.schedule(timerTask, getDelayMs(), getDelayMs());
	}

	private void elaborateUrl(final URL url) {
		host = url.getHost();
		path = url.getPath();
		port = url.getPort();
		userInfo = url.getUserInfo();
		ref = url.getRef();
		getParametersDetails(url.getQuery());
	}

	private void getParametersDetails(final String query) {
		final Map<String, String> map = TextHelper.getParametersInUrlQuery(query);
		if (map.containsKey(DELAY_PARAMETER)) {
			setDelayMs(Long.valueOf(map.get(DELAY_PARAMETER)));
		}
		if (map.containsKey(ONE_TIME_PARAMETER)) {
			setOneTimeOnlyConfigured(Boolean.valueOf(map.get(ONE_TIME_PARAMETER)));
		}
		if (map.containsKey(TIMEOUT_PARAMETER)) {
			setTimeoutMs(Long.valueOf(map.get(TIMEOUT_PARAMETER)));
		}
	}

	private String getUrlQueryPart() {
		final StringBuilder sb = new StringBuilder();
		sb.append(DELAY_PARAMETER);
		sb.append("=");
		sb.append(String.valueOf(getDelayMs()));
		sb.append("&");
		sb.append(ONE_TIME_PARAMETER);
		sb.append("=");
		sb.append(String.valueOf(isOneTimeOnlyConfigured()));
		sb.append("&");
		sb.append(TIMEOUT_PARAMETER);
		sb.append("=");
		sb.append(String.valueOf(getTimeoutMs()));
		return sb.toString();
	}

	protected abstract PullerContent getContentData();

	protected void setConfigGeneration(final long configGeneration) {
		this.configGeneration = configGeneration;
	}
}
