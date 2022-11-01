package net.rossonet.savumerkki.savuctl;

import org.json.JSONObject;

public interface AppManager extends AutoCloseable {

	JSONObject getJsonStatus();

	String getStatusFilePath();

}
