package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

public class GithubPuller extends GitPuller {
	// TODO implement this puller

	private static final String GITHUB_PULLER_PROTOCOL = "github";

	public GithubPuller(final URL url) {
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
		return GITHUB_PULLER_PROTOCOL;
	}
}
