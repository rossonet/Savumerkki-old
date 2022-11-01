package org.rossonet.savumerkki.config.puller.http;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.PullerContent;

public class HttpsPuller extends AbstractPuller {

	private static final String HTTPS_PULLER_PROTOCOL = "https";

	static {
		Puller.registerPuller(HttpsPuller.class);
	}

	public HttpsPuller(final URL url) {
		super(url);
	}

	@Override
	public String getProtocol() {
		return HTTPS_PULLER_PROTOCOL;
	}

	@Override
	public URL getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PullerContent getContentData() {
		// TODO Auto-generated method stub
		return null;
	}

}
