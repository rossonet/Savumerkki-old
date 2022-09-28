package org.rossonet.savumerkki.config.event;

public interface ConfigUpdateEventObserver {

	public void fire(UpdateEvent event);

}
