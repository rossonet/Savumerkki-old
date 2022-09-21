package org.rossonet.savumerkki.config.puller;

public interface PullerCallback {

	public void newConfigFound(NewConfigFromPuller event);

}
