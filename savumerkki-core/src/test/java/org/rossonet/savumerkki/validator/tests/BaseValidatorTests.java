package org.rossonet.savumerkki.validator.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.rossonet.savumerkki.config.event.UpdateEvent;
import org.rossonet.savumerkki.config.validator.ValidationError;
import org.rossonet.savumerkki.config.validator.json.JsonValidator;
import org.rossonet.savumerkki.config.validator.yaml.YamlValidator;

@TestMethodOrder(OrderAnnotation.class)
public class BaseValidatorTests {

	@Test
	@Order(3)
	public void jsonValidatorKoTest() throws Exception {
		final JsonValidator jsonValidator = new JsonValidator();
		final String text = "{-- 'field':['data':1,'data2','string data]}";
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, text, text, 0, null, null);
		final Collection<ValidationError> errors = jsonValidator.checkValidationErrors(event);
		System.out.println(errors);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals(1, errors.size());
	}

	@Test
	@Order(1)
	public void jsonValidatorOkTest() throws Exception {
		final JsonValidator jsonValidator = new JsonValidator();
		final String text = "{'field':{'data':1,'data2':'string data'},'array':['ciao','hello']}";
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, text, text, 0, null, null);
		final Collection<ValidationError> errors = jsonValidator.checkValidationErrors(event);
		System.out.println(errors);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());

	}

	@Test
	@Order(4)
	public void yamlValidatorKoTest() throws Exception {
		final YamlValidator yamlValidator = new YamlValidator();
		final String text = "{data\n  test1\n error";
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, text, text, 0, null, null);
		final Collection<ValidationError> errors = yamlValidator.checkValidationErrors(event);
		System.out.println(errors);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals(1, errors.size());
	}

	@Test
	@Order(2)
	public void yamlValidatorOkTest() throws Exception {
		final YamlValidator yamlValidator = new YamlValidator();
		final String text = "firstName: \"John\"\n" + "lastName: \"Doe\"\n" + "age: 31\n" + "contactDetails:\n"
				+ "   - type: \"mobile\"\n" + "     number: 123456789\n" + "   - type: \"landline\"\n"
				+ "     number: 456786868\n" + "homeAddress:\n" + "   line: \"Xyz, DEF Street\"\n"
				+ "   city: \"City Y\"\n" + "   state: \"State Y\"\n" + "   zip: 345657";
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, text, text, 0, null, null);
		final Collection<ValidationError> errors = yamlValidator.checkValidationErrors(event);
		System.out.println(errors);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());

	}

}
