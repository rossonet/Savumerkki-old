package org.rossonet.savumerkki.config.puller.s3;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class S3Puller extends AbstractPuller {

	private static final String S3_PULLER_PROTOCOL = "s3";

	public S3Puller(final URL url) {
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
		return S3_PULLER_PROTOCOL;
	}

}
