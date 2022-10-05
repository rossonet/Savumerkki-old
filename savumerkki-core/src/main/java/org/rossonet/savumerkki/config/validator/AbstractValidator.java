package org.rossonet.savumerkki.config.validator;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractValidator implements Validator {

	private final static Logger LOG = LoggerFactory.getLogger(AbstractValidator.class);
	private static final Set<Class<? extends Validator>> validators = new HashSet<>();

	static Validator getValidator(final String validatorClass) {
		for (final Class<? extends Validator> v : validators) {
			if (v.getName().equals(validatorClass)) {
				try {
					return v.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					LOG.error("creating validator " + validatorClass, e);
					return null;
				}
			}
		}
		return null;
	}

	static Set<Class<? extends Validator>> getValidators() {
		return validators;
	}

	static void registerValidator(final Class<? extends Validator> validatorClass) {
		LOG.info(validatorClass.getName() + " registered as validator");
		validators.add(validatorClass);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("AbstractValidator [");
		if (getClass() != null) {
			builder.append("getClass()=");
			builder.append(getClass().getName());
		}
		builder.append("]");
		return builder.toString();
	}

}
