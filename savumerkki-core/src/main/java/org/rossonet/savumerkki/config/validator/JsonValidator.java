package org.rossonet.savumerkki.config.validator;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.event.UpdateEvent;

public class JsonValidator implements Validator {

	@Override
	public Collection<ValidationError> checkValidationErrors(final UpdateEvent configToCheck) {
		final Collection<ValidationError> errors = new ArrayList<>();
		try {
			new JSONObject(configToCheck.getPayloadElaborated());
		} catch (final Exception a) {
			errors.add(new ValidationError(a.getMessage(), 0, 0));

		}
		return errors;
	}

}
