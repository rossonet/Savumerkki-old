package org.rossonet.savumerkki.config.validator;

import java.util.Collection;

import org.rossonet.savumerkki.config.event.UpdateEvent;

public interface Validator {

	// TODO implementare Validator JSON e YAML cone facilitazioni per subclass

	public Collection<ValidationError> checkValidationErrors(UpdateEvent configToCheck);

}
