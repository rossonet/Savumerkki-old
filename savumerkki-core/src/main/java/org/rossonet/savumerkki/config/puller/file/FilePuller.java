package org.rossonet.savumerkki.config.puller.file;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;

public class FilePuller extends AbstractPuller {

	private static final String FILE_PULLER_PROTOCOL = "file";

	public FilePuller(final URL url) {
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
		return FILE_PULLER_PROTOCOL;
	}

}
