package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.PullerContent;

public class GitPuller extends AbstractPuller {

	private static final String GIT_PULLER_PROTOCOL = "git";

	static {
		Puller.registerPuller(GitPuller.class);
	}

	public GitPuller(final URL url) {
		super(url);
	}

	@Override
	public String getProtocol() {
		return GIT_PULLER_PROTOCOL;
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
