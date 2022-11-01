package org.rossonet.savumerkki.enrichment.tests;

import org.rossonet.savumerkki.config.enrichment.external.ExternalEnrichFunction;

public class ExampleFunction implements ExternalEnrichFunction {

	@Override
	public String getValue(final String key) {
		return "just for test, key is " + key;
	}

	@Override
	public void reset() {
		System.out.println("reset called");

	}

}
