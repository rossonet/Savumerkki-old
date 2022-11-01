package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.PullerContent;

public class GithubPuller extends GitPuller {

	private static final String GITHUB_PULLER_PROTOCOL = "github";

	static {
		Puller.registerPuller(GithubPuller.class);
	}

	public GithubPuller(final URL url) {
		super(url);
	}

	@Override
	public String getProtocol() {
		return GITHUB_PULLER_PROTOCOL;
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
