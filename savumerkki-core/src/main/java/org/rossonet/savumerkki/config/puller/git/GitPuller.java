package org.rossonet.savumerkki.config.puller.git;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;

public class GitPuller extends AbstractPuller {

	private static final String GIT_PULLER_PROTOCOL = "git";

	static {
		Puller.registerPuller(GitPuller.class);
	}

	public GitPuller(final URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getProtocol() {
		return GIT_PULLER_PROTOCOL;
	}

	@Override
	public URL geturl() {
		// TODO Auto-generated method stub
		return null;
	}

}
