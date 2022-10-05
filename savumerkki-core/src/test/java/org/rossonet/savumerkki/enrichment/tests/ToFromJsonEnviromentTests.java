package org.rossonet.savumerkki.enrichment.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.rossonet.savumerkki.config.enrichment.AbstractEnrichMap;
import org.rossonet.savumerkki.config.enrichment.EnrichMap;
import org.rossonet.savumerkki.config.enrichment.external.ConfigurationException;
import org.rossonet.savumerkki.config.enrichment.external.ExternalEnrich;
import org.rossonet.savumerkki.config.enrichment.external.ExternalEnrichFunction;
import org.rossonet.savumerkki.config.enrichment.external.map.JavaMap;
import org.rossonet.savumerkki.config.enrichment.system.EnviromentsMap;
import org.rossonet.savumerkki.config.enrichment.vault.azure.AzureVault;
import org.rossonet.savumerkki.config.enrichment.vault.dns.DnsVault;
import org.rossonet.savumerkki.config.enrichment.vault.google.GoogleVault;
import org.rossonet.savumerkki.config.enrichment.vault.hashicorp.HashicorpVault;

import com.azure.core.credential.BasicAuthenticationCredential;
import com.azure.core.util.Base64Util;
import com.bettercloud.vault.VaultConfig;

@TestMethodOrder(OrderAnnotation.class)
public class ToFromJsonEnviromentTests {

	@Test
	@Order(4)
	public void azureVaultTest() {
		final AzureVault e = new AzureVault();
		final int priority = UUID.randomUUID().toString().hashCode();
		e.setPriority(priority);
		final boolean dontLogTheValue = priority % 2 == 0;
		e.setDontLogTheValue(dontLogTheValue);
		final long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
		e.setTimeoutResolutionMs(leastSignificantBits);
		final String testUrl = "https://" + UUID.randomUUID().toString();
		e.setKeyVaultUrl(testUrl);
		final String username = UUID.randomUUID().toString();
		final String password = UUID.randomUUID().toString();
		e.setAzureCredential(username, password);
		final EnrichMap c = EnrichMap.fromJson(e.getEnrichMapAsJson());
		final JSONObject enrichMapAsJson = e.getEnrichMapAsJson();
		// c.configureFromJson(enrichMapAsJson);
		assertEquals(AzureVault.class.getName(), enrichMapAsJson.getString(AbstractEnrichMap.DRIVER_FIELD));
		assertEquals(priority, c.getPriority());
		assertEquals(dontLogTheValue, c.dontLogTheValue());
		assertEquals(leastSignificantBits, c.getTimeoutResolutionMs());
		assertEquals(Base64Util.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8)),
				((AzureVault) c).getAzureCredential().getToken(null).block().getToken());
		System.out.println("--- json file ---\n" + enrichMapAsJson.toString(2));
	}

	@Test
	@Order(5)
	public void azureVaultTokenTest() {
		final AzureVault e = new AzureVault();
		final int priority = UUID.randomUUID().toString().hashCode();
		e.setPriority(priority);
		final boolean dontLogTheValue = priority % 2 == 0;
		e.setDontLogTheValue(dontLogTheValue);
		final long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
		e.setTimeoutResolutionMs(leastSignificantBits);
		final String testUrl = "https://" + UUID.randomUUID().toString();
		e.setKeyVaultUrl(testUrl);
		final String username = UUID.randomUUID().toString();
		final String password = UUID.randomUUID().toString();
		e.setAzureCredential(new BasicAuthenticationCredential(username, password));
		final EnrichMap c = EnrichMap.fromJson(e.getEnrichMapAsJson());
		final JSONObject enrichMapAsJson = e.getEnrichMapAsJson();
		// c.configureFromJson(enrichMapAsJson);
		assertEquals(AzureVault.class.getName(), enrichMapAsJson.getString(AbstractEnrichMap.DRIVER_FIELD));
		assertEquals(priority, c.getPriority());
		assertEquals(dontLogTheValue, c.dontLogTheValue());
		assertEquals(leastSignificantBits, c.getTimeoutResolutionMs());
		assertEquals(Base64Util.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8)),
				((AzureVault) c).getAzureCredential().getToken(null).block().getToken());
		System.out.println("--- json file ---\n" + enrichMapAsJson.toString(2));
	}

	@Test
	@Order(6)
	public void dnsVaultTest() {
		final DnsVault e = new DnsVault();
		final int priority = UUID.randomUUID().toString().hashCode();
		e.setPriority(priority);
		final boolean dontLogTheValue = priority % 2 == 0;
		e.setDontLogTheValue(dontLogTheValue);
		final long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
		e.setTimeoutResolutionMs(leastSignificantBits);
		final String domain = UUID.randomUUID().toString();
		e.setDomain(domain);
		final String secret = UUID.randomUUID().toString();
		e.setSecretKey(secret);
		final EnrichMap c = EnrichMap.fromJson(e.getEnrichMapAsJson());
		final JSONObject enrichMapAsJson = e.getEnrichMapAsJson();
		// c.configureFromJson(enrichMapAsJson);
		assertEquals(DnsVault.class.getName(), enrichMapAsJson.getString(AbstractEnrichMap.DRIVER_FIELD));
		assertEquals(domain, ((DnsVault) c).getDomain());
		assertEquals(secret, ((DnsVault) c).getSecretKey());
		assertEquals(priority, c.getPriority());
		assertEquals(dontLogTheValue, c.dontLogTheValue());
		assertEquals(leastSignificantBits, c.getTimeoutResolutionMs());
		System.out.println("--- json file ---\n" + enrichMapAsJson.toString(2));
	}

	@Test
	@Order(1)
	public void enviromentsMapTest() {
		final EnviromentsMap e = new EnviromentsMap();
		final int priority = UUID.randomUUID().toString().hashCode();
		e.setPriority(priority);
		final boolean dontLogTheValue = priority % 2 == 0;
		e.setDontLogTheValue(dontLogTheValue);
		final long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
		e.setTimeoutResolutionMs(leastSignificantBits);
		final String postfixValue = UUID.randomUUID().toString();
		e.setPostfix(postfixValue);
		final String prefixValue = UUID.randomUUID().toString();
		e.setPrefix(prefixValue);
		final EnrichMap c = EnrichMap.fromJson(e.getEnrichMapAsJson());
		final JSONObject enrichMapAsJson = e.getEnrichMapAsJson();
		// c.configureFromJson(enrichMapAsJson);
		assertEquals(EnviromentsMap.class.getName(), enrichMapAsJson.getString(AbstractEnrichMap.DRIVER_FIELD));
		assertEquals(prefixValue, ((EnviromentsMap) c).getPrefix());
		assertEquals(postfixValue, ((EnviromentsMap) c).getPostfix());
		assertEquals(priority, c.getPriority());
		assertEquals(dontLogTheValue, c.dontLogTheValue());
		assertEquals(leastSignificantBits, c.getTimeoutResolutionMs());
		System.out.println("--- json file ---\n" + enrichMapAsJson.toString(2));
	}

	@Test
	@Order(2)
	public void externalEnrichTest() {
		final ExternalEnrichFunction function = new ExampleFunction();
		final ExternalEnrich e = new ExternalEnrich(function);
		final int priority = UUID.randomUUID().toString().hashCode();
		e.setPriority(priority);
		final boolean dontLogTheValue = priority % 2 == 0;
		e.setDontLogTheValue(dontLogTheValue);
		final long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
		e.setTimeoutResolutionMs(leastSignificantBits);
		final EnrichMap c = EnrichMap.fromJson(e.getEnrichMapAsJson());
		final JSONObject originalEnrichMapAsJson = e.getEnrichMapAsJson();
		final Throwable exception = assertThrows(ConfigurationException.class, () -> {
			final JSONObject jsonForException = e.getEnrichMapAsJson();
			jsonForException.put(ExternalEnrich.FUNCTION_FIELD, "org.classNotFound");
			c.configureFromJson(jsonForException);
		});
		assertEquals(ExternalEnrich.class.getName(), originalEnrichMapAsJson.getString(AbstractEnrichMap.DRIVER_FIELD));
		assertEquals("java.lang.ClassNotFoundException: org.classNotFound", exception.getMessage());
		assertEquals(function.getClass().getName(), originalEnrichMapAsJson.getString("function"));
		c.configureFromJson(originalEnrichMapAsJson);
		final String keyTest = UUID.randomUUID().toString();
		assertEquals("just for test, key is " + keyTest, ((ExternalEnrich) c).get(keyTest));
		assertEquals(priority, c.getPriority());
		assertEquals(dontLogTheValue, c.dontLogTheValue());
		assertEquals(leastSignificantBits, c.getTimeoutResolutionMs());
		System.out.println("--- json file ---\n" + originalEnrichMapAsJson.toString(2));
	}

	@Test
	@Order(7)
	public void googleVaultTokenTest() {
		final GoogleVault e = new GoogleVault();
		final int priority = UUID.randomUUID().toString().hashCode();
		e.setPriority(priority);
		final boolean dontLogTheValue = priority % 2 == 0;
		e.setDontLogTheValue(dontLogTheValue);
		final long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
		e.setTimeoutResolutionMs(leastSignificantBits);
		final String applicationName = UUID.randomUUID().toString();
		final String matter = UUID.randomUUID().toString();
		e.setApplicationName(applicationName);
		e.setMatter(matter);
		final String username = UUID.randomUUID().toString();
		final String password = UUID.randomUUID().toString();
		e.setCredential(username, password);
		final EnrichMap c = EnrichMap.fromJson(e.getEnrichMapAsJson());
		final JSONObject enrichMapAsJson = e.getEnrichMapAsJson();
		// c.configureFromJson(enrichMapAsJson);
		assertEquals(GoogleVault.class.getName(), enrichMapAsJson.getString(AbstractEnrichMap.DRIVER_FIELD));
		assertEquals(priority, c.getPriority());
		assertEquals(dontLogTheValue, c.dontLogTheValue());
		assertEquals(leastSignificantBits, c.getTimeoutResolutionMs());
		assertEquals(matter, ((GoogleVault) c).getMatter());
		assertEquals(applicationName, ((GoogleVault) c).getApplicationName());
		assertEquals(username, ((GoogleVault) c).getUsername());
		assertEquals(password, ((GoogleVault) c).getPassword());
		System.out.println("--- json file ---\n" + enrichMapAsJson.toString(2));
	}

	@Test
	@Order(8)
	public void hashicorpVaultTokenTest() {
		final HashicorpVault e = new HashicorpVault();
		final int priority = UUID.randomUUID().toString().hashCode();
		e.setPriority(priority);
		final boolean dontLogTheValue = priority % 2 == 0;
		e.setDontLogTheValue(dontLogTheValue);
		final long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
		e.setTimeoutResolutionMs(leastSignificantBits);
		final String logicalVaultKey = UUID.randomUUID().toString();
		e.setLogicalVaultKey(logicalVaultKey);
		final String token = UUID.randomUUID().toString();
		final VaultConfig vaultConfig = new VaultConfig();
		vaultConfig.token(token);
		e.setVaultConfig(vaultConfig);
		final EnrichMap c = EnrichMap.fromJson(e.getEnrichMapAsJson());
		final JSONObject enrichMapAsJson = e.getEnrichMapAsJson();
		// c.configureFromJson(enrichMapAsJson);
		assertEquals(HashicorpVault.class.getName(), enrichMapAsJson.getString(AbstractEnrichMap.DRIVER_FIELD));
		assertEquals(priority, c.getPriority());
		assertEquals(dontLogTheValue, c.dontLogTheValue());
		assertEquals(leastSignificantBits, c.getTimeoutResolutionMs());
		assertEquals(logicalVaultKey, ((HashicorpVault) c).getLogicalVaultKey());
		assertEquals(token, ((HashicorpVault) c).getVaultConfig().getToken());
		System.out.println("--- json file ---\n" + enrichMapAsJson.toString(2));
	}

	@Test
	@Order(3)
	public void javaMapTest() {
		final JavaMap e = new JavaMap();
		final int priority = UUID.randomUUID().toString().hashCode();
		e.setPriority(priority);
		final boolean dontLogTheValue = priority % 2 == 0;
		e.setDontLogTheValue(dontLogTheValue);
		final long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
		e.setTimeoutResolutionMs(leastSignificantBits);
		final HashMap<String, String> testMap = new HashMap<>();
		final String key = UUID.randomUUID().toString();
		final String value = UUID.randomUUID().toString();
		final String key2 = UUID.randomUUID().toString();
		final String value2 = UUID.randomUUID().toString();
		final String key3 = UUID.randomUUID().toString();
		final String value3 = UUID.randomUUID().toString();
		testMap.put(key, value);
		testMap.put(key2, value2);
		testMap.put(key3, value3);
		e.setMap(testMap);
		final EnrichMap c = EnrichMap.fromJson(e.getEnrichMapAsJson());
		final JSONObject originalEnrichMapAsJson = e.getEnrichMapAsJson();
		// final JSONObject enrichMapAsJson = originalEnrichMapAsJson;
		// c.configureFromJson(enrichMapAsJson);
		assertEquals(JavaMap.class.getName(), originalEnrichMapAsJson.getString(AbstractEnrichMap.DRIVER_FIELD));
		assertEquals(priority, c.getPriority());
		assertEquals(dontLogTheValue, c.dontLogTheValue());
		assertEquals(leastSignificantBits, c.getTimeoutResolutionMs());
		assertEquals(3, ((JavaMap) c).getMap().size());
		assertEquals(value, ((JavaMap) c).getMap().get(key));
		assertEquals(value2, ((JavaMap) c).getMap().get(key2));
		assertEquals(value3, ((JavaMap) c).getMap().get(key3));
		System.out.println("--- json file ---\n" + originalEnrichMapAsJson.toString(2));
	}

	@Test
	@Order(20)
	public void printAll() throws InstantiationException, IllegalAccessException {
		for (final Class<? extends EnrichMap> e : EnrichMap.getEnrichmaps()) {
			System.out.println(e.newInstance().toString());
		}
	}

}
