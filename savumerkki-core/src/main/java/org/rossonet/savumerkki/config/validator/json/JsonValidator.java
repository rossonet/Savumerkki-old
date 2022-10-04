package org.rossonet.savumerkki.config.validator.json;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;
import org.rossonet.savumerkki.config.event.UpdateEvent;
import org.rossonet.savumerkki.config.validator.AbstractValidator;
import org.rossonet.savumerkki.config.validator.ValidationError;
import org.rossonet.savumerkki.config.validator.Validator;

public class JsonValidator extends AbstractValidator {

	static {
		Validator.registerValidator(JsonValidator.class);
	}

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
