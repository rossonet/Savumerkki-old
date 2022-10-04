package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

public class GitlabPuller extends GitPuller {
	// TODO implement this puller

	private static final String GITLAB_PULLER_PROTOCOL = "gitlab";

	public GitlabPuller(final URL url) {
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
		return GITLAB_PULLER_PROTOCOL;
	}

}
