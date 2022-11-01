package org.rossonet.savumerkki.validator.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.rossonet.savumerkki.config.enrichment.EnrichLoggerLine;
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
		final LocalDateTime now = LocalDateTime.now();
		final Collection<EnrichLoggerLine> logger = new HashSet<>();
		final EnrichLoggerLine logLine = new EnrichLoggerLine(null, "key", 60, true, false, null);
		logger.add(logLine);
		final UpdateEvent event = new UpdateEvent(now, null, JSON_OK_TEST, JSON_OK_TEST, 6, logger, null);
		final Collection<ValidationError> errors = Validator.getValidator(jsonValidatorString)
				.checkValidationErrors(event);
		assertEquals(6, event.getGeneration());
		assertEquals(JSON_OK_TEST, event.getPayloadTemplate());
		assertEquals(JSON_OK_TEST, event.getPayloadElaborated());
		assertNull(event.getMonitoredConfig());
		assertEquals("EnrichLoggerLine [enrichMap=null, key=key, secret=true, value=null, priority=60, used=false]\n",
				event.getEnrichLoggerAsString());
		assertEquals(1, event.getEnrichLogger().size());

		assertNull(event.getEnrichLogger().iterator().next().getEnrichMap());
		assertEquals("key", event.getEnrichLogger().iterator().next().getKey());
		assertNull(event.getEnrichLogger().iterator().next().getValue());
		assertTrue(event.getEnrichLogger().iterator().next().isSecret());
		assertFalse(event.getEnrichLogger().iterator().next().isUsed());
		assertEquals(60, event.getEnrichLogger().iterator().next().getPriority());

		assertNull(event.getErrors());
		assertFalse(event.isCompletedWithFault());
		assertTrue(event.isCompletedWithoutErros());
		assertEquals(now, event.getDatetime());
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
		// [ValidationError [errorDescription=Expected a ',' or ']' at 20 [character 21
		// line 1], errorBeginCharacter=0, errorEndCharacter=0]]
		assertEquals("Expected a ',' or ']' at 20 [character 21 line 1]",
				errors.iterator().next().getErrorDescription());
		assertEquals(0, errors.iterator().next().getErrorBeginCharacter());
		assertEquals(0, errors.iterator().next().getErrorEndCharacter());
		assertEquals(0, errors.iterator().next().getErrorLine());
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
	public void yamlValidatorKoNullObjectTest() throws Exception {
		final YamlValidator yamlValidator = new YamlValidator();
		final UpdateEvent event = new UpdateEvent(LocalDateTime.now(), null, YAML_OK_TEST, "", 0, null, null);
		final Collection<ValidationError> errors = yamlValidator.checkValidationErrors(event);
		System.out.println(event.toString());
		System.out.println(errors);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals(1, errors.size());
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
