package org.rossonet.savumerkki.config.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.rossonet.savumerkki.config.event.UpdateEvent;
import org.yaml.snakeyaml.Yaml;

public class YamlValidator implements Validator {

	private static final Yaml snakeyaml = new Yaml();

	@Override
	public Collection<ValidationError> checkValidationErrors(final UpdateEvent configToCheck) {
		final Collection<ValidationError> errors = new ArrayList<>();
		try {
			final Map<String, Object> obj = snakeyaml.load(configToCheck.getPayloadElaborated());
			if (obj == null || obj.isEmpty()) {
				errors.add(new ValidationError("error parsing yaml file", 0, 0));
			}
		} catch (final Exception a) {
			errors.add(new ValidationError(a.getMessage(), 0, 0));
		}
		return errors;
	}

}
