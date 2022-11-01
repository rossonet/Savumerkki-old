package net.rossonet.savumerkki.savuctl;

import org.json.JSONObject;

public class AppManagerImplementation implements AppManager {

	private static final String FIXED_LOCAL_STATUS_LOG = "local-status.log";
	private static final int GOOD_STATUS = 192;
	private static final String STATUS_KEY = "q";

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject getJsonStatus() {
		final JSONObject json = new JSONObject();
		json.put(STATUS_KEY, GOOD_STATUS);
		return json;
	}

	@Override
	public String getStatusFilePath() {
		return FIXED_LOCAL_STATUS_LOG;
	}

}
