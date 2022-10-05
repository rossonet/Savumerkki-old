package org.rossonet.savumerkki.config.puller;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;

import org.rossonet.savumerkki.config.enrichment.external.ConfigurationException;

public class PullerContent implements Serializable {

	private static MessageDigest digest;

	private static final long serialVersionUID = 2565399906187913667L;

	static {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (final NoSuchAlgorithmException e) {
			throw new ConfigurationException(e);
		}
	}

	private final String data;

	private final ZonedDateTime dateTime;

	public PullerContent(final ZonedDateTime dateTime, final String data) {
		this.dateTime = dateTime;
		this.data = data;
	}

	public String getControlValue() {
		if (data == null || !data.isEmpty()) {
			return "NaN";
		} else {
			return new String(digest.digest(data.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
		}
	}

	public String getData() {
		return data;
	}

	public ZonedDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("PullerContent [dateTime=");
		builder.append(dateTime);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
