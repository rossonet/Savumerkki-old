package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.PullerContent;

public class GitlabPuller extends GitPuller {

	private static final String GITLAB_PULLER_PROTOCOL = "gitlab";

	static {
		Puller.registerPuller(GitlabPuller.class);
	}

	public GitlabPuller(final URL url) {
		super(url);
	}

	@Override
	public String getProtocol() {
		return GITLAB_PULLER_PROTOCOL;
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
