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
import org.rossonet.savumerkki.config.validator.Validator;
import org.rossonet.savumerkki.config.validator.json.JsonValidator;
import org.rossonet.savumerkki.config.validator.yaml.YamlValidator;

@TestMethodOrder(OrderAnnotation.class)
public class BaseValidatorTests {

	private static final String JSON_KO_TEST = "{-- 'field':['data':1,'data2','string data]}";
	private static final String JSON_OK_TEST = "{'field':{'data':1,'data2':'string data'},'array':['ciao','hello']}";
	private static final String YAML_KO_TEST = "{data\n  test1\n error";
	private static final String YAML_OK_TEST = "firstName: \"John\"\n" + "lastName: \"Doe\"\n" + "age: 31\n"
			+ "contactDetails:\n" + "   - type: \"mobile\"\n" + "     number: 123456789\n" + "   - type: \"landline\"\n"
			+ "     number: 456786868\n" + "homeAddress:\n" + "   line: \"Xyz, DEF Street\"\n" + "   city: \"City Y\"\n"
			+ "   state: \"State Y\"\n" + "   zip: 345657";

	@Test
	@Order(10)
	public void checkRegisterSize() throws Exception {
		final String jsonValidatorString = JsonValidator.class.getName();
		final String yamlValidatorString = YamlValidator.class.getName();
		assertEquals(2, Validator.getValidators().size());
		System.out.println("looking for " + jsonValidatorString);
		assertTrue(Validator.getValidators().contains(JsonValidator.class));
		System.out.println("looking for " + yamlValidatorString);
		assertTrue(Validator.getValidators().contains(YamlValidator.class));
		for (final Class<? extends Validator> v : Validator.getValidators()) {
			System.out.println(v.newInstance().toString());
		}
	}

	@Test
	@Order(8)
	public void jsonValidatorFromNameTest() throws Exception {
		final String jsonValidatorString = JsonValidator.class.getName();
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, JSON_OK_TEST, JSON_OK_TEST, 0, null, null);
		final Collection<ValidationError> errors = Validator.getValidator(jsonValidatorString)
				.checkValidationErrors(event);
		System.out.println(event.toString());
		System.out.println(errors);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());
	}

	@Test
	@Order(3)
	public void jsonValidatorKoTest() throws Exception {
		final JsonValidator jsonValidator = new JsonValidator();
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, JSON_OK_TEST, JSON_KO_TEST, 0, null, null);
		final Collection<ValidationError> errors = jsonValidator.checkValidationErrors(event);
		System.out.println(event.toString());
		System.out.println(errors);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals(1, errors.size());
	}

	@Test
	@Order(1)
	public void jsonValidatorOkTest() throws Exception {
		final JsonValidator jsonValidator = new JsonValidator();
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, JSON_KO_TEST, JSON_OK_TEST, 0, null, null);
		final Collection<ValidationError> errors = jsonValidator.checkValidationErrors(event);
		System.out.println(event.toString());
		System.out.println(errors);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());

	}

	@Test
	@Order(9)
	public void yamlValidatorFromNameTest() throws Exception {
		final String yamlValidatorString = YamlValidator.class.getName();
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, YAML_OK_TEST, YAML_OK_TEST, 0, null, null);
		final Collection<ValidationError> errors = Validator.getValidator(yamlValidatorString)
				.checkValidationErrors(event);
		System.out.println(event.toString());
		System.out.println(errors);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());

	}

	@Test
	@Order(4)
	public void yamlValidatorKoTest() throws Exception {
		final YamlValidator yamlValidator = new YamlValidator();
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, YAML_OK_TEST, YAML_KO_TEST, 0, null, null);
		final Collection<ValidationError> errors = yamlValidator.checkValidationErrors(event);
		System.out.println(event.toString());
		System.out.println(errors);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals(1, errors.size());
	}

	@Test
	@Order(2)
	public void yamlValidatorOkTest() throws Exception {
		final YamlValidator yamlValidator = new YamlValidator();
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, YAML_KO_TEST, YAML_OK_TEST, 0, null, null);
		final Collection<ValidationError> errors = yamlValidator.checkValidationErrors(event);
		System.out.println(event.toString());
		System.out.println(errors);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());

	}

}
