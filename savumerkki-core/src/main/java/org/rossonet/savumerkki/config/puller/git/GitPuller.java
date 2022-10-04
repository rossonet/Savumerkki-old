package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class GitPuller extends AbstractPuller {

	private static final String GIT_PULLER_PROTOCOL = "git";

	public GitPuller(final URL url) {
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
		return GIT_PULLER_PROTOCOL;
	}

}
