package org.rossonet.savumerkki.config.puller.http;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class HttpsPuller extends AbstractPuller {

	private static final String HTTPS_PULLER_PROTOCOL = "https";

	@Override
	protected void configureFromUrl(final URL url) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getProtocol() {
		return HTTPS_PULLER_PROTOCOL;
	}

}
