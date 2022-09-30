package org.rossonet.savumerkki.config.validator;

import java.util.Collection;

import org.rossonet.savumerkki.config.event.UpdateEvent;

public interface Validator {

	public Collection<ValidationError> checkValidationErrors(UpdateEvent configToCheck);

}
