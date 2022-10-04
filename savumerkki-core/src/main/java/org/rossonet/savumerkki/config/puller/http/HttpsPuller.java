package org.rossonet.savumerkki.config.puller.http;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class HttpsPuller extends AbstractPuller {

	private static final String HTTPS_PULLER_PROTOCOL = "https";

	public HttpsPuller(final URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	public URL geturl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getProtocol() {
		return HTTPS_PULLER_PROTOCOL;
	}

}
