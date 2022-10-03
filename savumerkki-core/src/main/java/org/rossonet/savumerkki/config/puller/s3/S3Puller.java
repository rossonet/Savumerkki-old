package org.rossonet.savumerkki.config.puller.s3;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class S3Puller extends AbstractPuller {

	private static final String S3_PULLER_PROTOCOL = "s3";

	@Override
	protected void configureFromUrl(final URL url) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getProtocol() {
		return S3_PULLER_PROTOCOL;
	}

}
