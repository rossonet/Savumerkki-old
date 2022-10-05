package org.rossonet.savumerkki.config.puller.dns;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.PullerContent;

public class DnsPuller extends AbstractPuller {

	private static final String DNS_PULLER_PROTOCOL = "dns";

	static {
		Puller.registerPuller(DnsPuller.class);
	}

	public DnsPuller(final URL url) {
		super(url);
	}

	@Override
	public String getProtocol() {
		return DNS_PULLER_PROTOCOL;
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
