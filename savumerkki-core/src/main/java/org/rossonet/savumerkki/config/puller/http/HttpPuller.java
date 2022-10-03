package org.rossonet.savumerkki.config.puller.http;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class HttpPuller extends AbstractPuller {

	private static final String HTTP_PULLER_PROTOCOL = "http";

	@Override
	protected void configureFromUrl(final URL url) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getProtocol() {
		return HTTP_PULLER_PROTOCOL;
	}

}
