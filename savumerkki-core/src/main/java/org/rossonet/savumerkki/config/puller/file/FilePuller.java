package org.rossonet.savumerkki.config.puller.file;

import java.net.URL;

import org.rossonet.savumerkki.config.puller.AbstractPuller;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.PullerContent;

public class FilePuller extends AbstractPuller {

	private static final String FILE_PULLER_PROTOCOL = "file";

	static {
		Puller.registerPuller(FilePuller.class);
	}

	public FilePuller(final URL url) {
		super(url);
	}

	@Override
	public String getProtocol() {
		return FILE_PULLER_PROTOCOL;
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
