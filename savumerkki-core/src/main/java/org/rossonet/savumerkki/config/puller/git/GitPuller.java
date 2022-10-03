package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class GitPuller extends AbstractPuller {

	private static final String GIT_PULLER_PROTOCOL = "git";

	@Override
	protected void configureFromUrl(final URL url) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getProtocol() {
		return GIT_PULLER_PROTOCOL;
	}

}
