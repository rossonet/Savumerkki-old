package org.rossonet.savumerkki.config.puller.file;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;

public class FilePuller extends AbstractPuller {

	private static final String FILE_PULLER_PROTOCOL = "file";

	static {
		Puller.registerPuller(FilePuller.class);
	}

	public FilePuller(final URL url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getProtocol() {
		return FILE_PULLER_PROTOCOL;
	}

	@Override
	public URL geturl() {
		// TODO Auto-generated method stub
		return null;
	}

}
