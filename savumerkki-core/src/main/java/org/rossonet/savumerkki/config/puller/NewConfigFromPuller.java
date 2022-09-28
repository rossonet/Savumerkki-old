package org.rossonet.savumerkki.config.puller;

public final class NewConfigFromPuller {

	private final String channelControlValue;
	private final Puller puller;
	private final long pullerConfigGeneration;
	private final String templateConfig;

	public NewConfigFromPuller(final Puller puller, final String templateConfig, final long pullerConfigGeneration,
			final String channelControlValue) {
		this.puller = puller;
		this.templateConfig = templateConfig;
		this.pullerConfigGeneration = pullerConfigGeneration;
		this.channelControlValue = channelControlValue;
	}

	public String getChannelControlValue() {
		return channelControlValue;
	}

	public Puller getPuller() {
		return puller;
	}

	public long getPullerConfigGeneration() {
		return pullerConfigGeneration;
	}

	public String getTemplateConfig() {
		return templateConfig;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("NewConfigFromPuller [puller=");
		builder.append(puller);
		builder.append(", pullerConfigGeneration=");
		builder.append(pullerConfigGeneration);
		builder.append(", channelControlValue=");
		builder.append(channelControlValue);
		builder.append("]");
		return builder.toString();
	}

}
