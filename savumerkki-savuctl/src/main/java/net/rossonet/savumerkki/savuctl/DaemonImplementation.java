package net.rossonet.savumerkki.savuctl;

public class DaemonImplementation implements DaemonManager {

	private static DaemonManager daemon = null;

	public static DaemonManager getInstance() {
		if (daemon == null) {
			daemon = new DaemonImplementation();
		}
		return daemon;

	}

	@Override
	public void reloadConfiguration() {
		// TODO Auto-generated method stub

	}

}
