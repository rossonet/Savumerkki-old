package org.rossonet.savumerkki.config.puller.s3;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.PullerContent;

public class S3Puller extends AbstractPuller {

	private static final String S3_PULLER_PROTOCOL = "s3";

	static {
		Puller.registerPuller(S3Puller.class);
	}

	public S3Puller(final URL url) {
		super(url);
	}

	@Override
	public String getProtocol() {
		return S3_PULLER_PROTOCOL;
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
