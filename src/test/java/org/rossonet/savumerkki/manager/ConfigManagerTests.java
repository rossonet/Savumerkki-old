package org.rossonet.savumerkki.manager;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class ConfigManagerTests {

	private static final Logger logger = Logger.getLogger(ConfigManagerTests.class.getName());

	@AfterEach
	public void cleanTestsContext() throws Exception {
		logger.info("test completed");
	}

	@Test
	@Order(1)
	public void runMain() throws Exception {
		logger.info("ok");
	}

}
