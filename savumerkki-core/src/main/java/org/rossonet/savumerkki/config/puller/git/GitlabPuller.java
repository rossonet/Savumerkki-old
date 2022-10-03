package org.rossonet.savumerkki.config.puller.git;

public class GitlabPuller extends GitPuller {
	// TODO implement this puller

	private static final String GITLAB_PULLER_PROTOCOL = "gitlab";

	@Override
	protected String getProtocol() {
		return GITLAB_PULLER_PROTOCOL;
	}

}
