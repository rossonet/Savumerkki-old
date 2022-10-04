package org.rossonet.savumerkki.config.validator;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractValidator implements Validator {

	private final static Logger LOG = LoggerFactory.getLogger(AbstractValidator.class);
	private static final Set<Class<? extends Validator>> validators = new HashSet<>();

	static Set<Class<? extends Validator>> getValidators() {
		return validators;
	}

	static void registerValidator(final Class<? extends Validator> validatorClass) {
		LOG.info(validatorClass.getName() + " registered as validator");
		validators.add(validatorClass);
	}

}
