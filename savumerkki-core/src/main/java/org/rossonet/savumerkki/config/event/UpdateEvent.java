package org.rossonet.savumerkki.config.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import org.rossonet.savumerkki.config.MonitoredConfig;
import org.rossonet.savumerkki.config.enrichment.EnrichLoggerLine;
import org.rossonet.savumerkki.config.validator.ValidationError;

public final class UpdateEvent implements Serializable {

	private static final long serialVersionUID = -1183108184837669816L;
	private final LocalDateTime datetime;
	private final Collection<EnrichLoggerLine> enrichLogger;
	private final Collection<ValidationError> errors;
	private final long generation;
	private transient final MonitoredConfig monitoredConfig;
	private final String payloadElaborated;
	private final String payloadTemplate;

	public UpdateEvent(final LocalDateTime datetime, final MonitoredConfig monitoredConfig,
			final String payloadTemplate, final String payloadElaborated, final long generation,
			final Collection<EnrichLoggerLine> enrichLogger, final Collection<ValidationError> errors) {
		this.datetime = datetime;
		this.monitoredConfig = monitoredConfig;
		this.payloadElaborated = payloadElaborated;
		this.payloadTemplate = payloadTemplate;
		this.generation = generation;
		this.enrichLogger = enrichLogger;
		this.errors = errors;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public Collection<EnrichLoggerLine> getEnrichLogger() {
		return enrichLogger;
	}

	public String getEnrichLoggerAsString() {
		final StringBuilder log = new StringBuilder();
		if (getEnrichLogger() != null) {
			for (final EnrichLoggerLine l : getEnrichLogger()) {
				log.append(l.toString() + "\n");
			}
		}
		return log.toString();
	}

	public Collection<ValidationError> getErrors() {
		return errors;
	}

	public long getGeneration() {
		return generation;
	}

	public MonitoredConfig getMonitoredConfig() {
		return monitoredConfig;
	}

	public String getPayloadElaborated() {
		return payloadElaborated;
	}

	public String getPayloadTemplate() {
		return payloadTemplate;
	}

	public boolean isCompletedWithFault() {
		return (errors != null && !errors.isEmpty());
	}

	public boolean isCompletedWithoutErros() {
		return !isCompletedWithFault();
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("UpdateEvent [datetime=");
		builder.append(datetime);
		builder.append(", generation=");
		builder.append(generation);
		builder.append(", monitoredConfig=");
		builder.append(monitoredConfig);
		builder.append("]");
		return builder.toString();
	}

}
