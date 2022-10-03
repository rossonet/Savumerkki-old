package org.rossonet.savumerkki.config.puller.git;

public class GithubPuller extends GitPuller {
	// TODO implement this puller

	private static final String GITHUB_PULLER_PROTOCOL = "github";

	@Override
	protected String getProtocol() {
		return GITHUB_PULLER_PROTOCOL;
	}

}
