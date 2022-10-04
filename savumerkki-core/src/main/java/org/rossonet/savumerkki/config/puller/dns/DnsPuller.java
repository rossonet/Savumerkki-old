package org.rossonet.savumerkki.config.puller.dns;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class DnsPuller extends AbstractPuller {

	private static final String DNS_PULLER_PROTOCOL = "dns";

	public DnsPuller(final URL url) {
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
		return DNS_PULLER_PROTOCOL;
	}

}
