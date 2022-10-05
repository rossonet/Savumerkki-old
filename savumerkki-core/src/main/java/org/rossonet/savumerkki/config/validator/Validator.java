package org.rossonet.savumerkki.config.validator;

import java.util.Collection;
import java.util.Set;

import org.rossonet.savumerkki.config.event.UpdateEvent;

public interface Validator {

	public static Validator getValidator(final String validatorClass) {
		return AbstractValidator.getValidator(validatorClass);
	}

	public static Set<Class<? extends Validator>> getValidators() {
		return AbstractValidator.getValidators();
	}

	public static void registerValidator(final Class<? extends Validator> validatorClass) {
		AbstractValidator.registerValidator(validatorClass);
	}

	public Collection<ValidationError> checkValidationErrors(UpdateEvent configToCheck);

}
