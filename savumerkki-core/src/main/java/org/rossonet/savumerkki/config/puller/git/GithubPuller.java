package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.Puller;

public class GithubPuller extends GitPuller {

	private static final String GITHUB_PULLER_PROTOCOL = "github";

	// TODO implement this puller

	static {
		Puller.registerPuller(GithubPuller.class);
	}

	public GithubPuller(final URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getProtocol() {
		return GITHUB_PULLER_PROTOCOL;
	}

	@Override
	public URL geturl() {
		// TODO Auto-generated method stub
		return null;
	}
}
