package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.Puller;

public class GitlabPuller extends GitPuller {

	private static final String GITLAB_PULLER_PROTOCOL = "gitlab";

	// TODO implement this puller

	static {
		Puller.registerPuller(GitlabPuller.class);
	}

	public GitlabPuller(final URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getProtocol() {
		return GITLAB_PULLER_PROTOCOL;
	}

	@Override
	public URL geturl() {
		// TODO Auto-generated method stub
		return null;
	}

}
