package net.rossonet.savumerkki.savuctl;

public interface DaemonManager {

	static DaemonManager getInstance() {
		return DaemonImplementation.getInstance();
	}

	void reloadConfiguration();

}
