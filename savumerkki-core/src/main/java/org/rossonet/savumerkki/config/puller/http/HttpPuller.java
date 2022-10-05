package org.rossonet.savumerkki.config.puller.http;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.PullerContent;

public class HttpPuller extends AbstractPuller {

	private static final String HTTP_PULLER_PROTOCOL = "http";

	static {
		Puller.registerPuller(HttpPuller.class);
	}

	public HttpPuller(final URL url) {
		super(url);
	}

	@Override
	public String getProtocol() {
		return HTTP_PULLER_PROTOCOL;
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
