package org.rossonet.savumerkki.config.puller.dns;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class DnsPuller extends AbstractPuller {

	private static final String DNS_PULLER_PROTOCOL = "dns";

	@Override
	protected void configureFromUrl(final URL url) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getProtocol() {
		return DNS_PULLER_PROTOCOL;
	}

}
