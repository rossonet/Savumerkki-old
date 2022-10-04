package org.rossonet.savumerkki.config.puller.http;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;

public class HttpPuller extends AbstractPuller {

	private static final String HTTP_PULLER_PROTOCOL = "http";

	static {
		Puller.registerPuller(HttpPuller.class);
	}

	public HttpPuller(final URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getProtocol() {
		return HTTP_PULLER_PROTOCOL;
	}

	@Override
	public URL geturl() {
		// TODO Auto-generated method stub
		return null;
	}

}
