package org.rossonet.savumerkki.config.puller.dns;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;

public class DnsPuller extends AbstractPuller {

	private static final String DNS_PULLER_PROTOCOL = "dns";

	static {
		Puller.registerPuller(DnsPuller.class);
	}

	public DnsPuller(final URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getProtocol() {
		return DNS_PULLER_PROTOCOL;
	}

	@Override
	public URL geturl() {
		// TODO Auto-generated method stub
		return null;
	}

}
