package org.rossonet.savumerkki.config;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class ManagedConfigTests {

	private static final Logger logger = Logger.getLogger(ManagedConfigTests.class.getName());

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
