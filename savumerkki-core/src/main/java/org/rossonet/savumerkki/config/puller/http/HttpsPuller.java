package org.rossonet.savumerkki.config.puller.http;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;

public class HttpsPuller extends AbstractPuller {

	private static final String HTTPS_PULLER_PROTOCOL = "https";

	static {
		Puller.registerPuller(HttpsPuller.class);
	}

	public HttpsPuller(final URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getProtocol() {
		return HTTPS_PULLER_PROTOCOL;
	}

	@Override
	public URL geturl() {
		// TODO Auto-generated method stub
		return null;
	}

}
