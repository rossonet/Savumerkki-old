package org.rossonet.savumerkki.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.rossonet.savumerkki.config.enrichment.external.ExternalEnrich;
import org.rossonet.savumerkki.config.enrichment.external.ExternalEnrichFunction;
import org.rossonet.savumerkki.config.enrichment.external.map.JavaMap;
import org.rossonet.savumerkki.config.enrichment.system.EnviromentsMap;
import org.rossonet.savumerkki.config.enrichment.vault.azure.AzureVault;
import org.rossonet.savumerkki.config.enrichment.vault.google.GoogleVault;
import org.rossonet.savumerkki.config.enrichment.vault.hashicorp.HashicorpVault;
import org.rossonet.savumerkki.config.event.ConfigUpdateEventObserver;
import org.rossonet.savumerkki.config.event.UpdateEvent;
import org.rossonet.savumerkki.config.puller.Puller;
import org.rossonet.savumerkki.config.puller.file.FilePuller;
import org.rossonet.savumerkki.config.puller.git.GitPuller;
import org.rossonet.savumerkki.config.puller.git.GithubPuller;
import org.rossonet.savumerkki.config.puller.git.GitlabPuller;
import org.rossonet.savumerkki.config.puller.http.HttpPuller;
import org.rossonet.savumerkki.config.puller.s3.S3Puller;
import org.rossonet.savumerkki.config.validator.Validator;
import org.rossonet.savumerkki.config.validator.json.JsonValidator;
import org.rossonet.savumerkki.config.validator.yaml.YamlValidator;
import org.rossonet.savumerkki.manager.ConfigManager;
import org.rossonet.savumerkki.manager.ConfigManagerBuilder;

import com.azure.core.credential.TokenCredential;
import com.bettercloud.vault.VaultConfig;
import com.google.api.client.auth.oauth2.Credential;

@TestMethodOrder(OrderAnnotation.class)
public class ConfigManagerTests {

	private static final Logger logger = Logger.getLogger(ConfigManagerTests.class.getName());

	@Test
	@Order(1)
	public void builderJsonTest() throws Exception {
		final JSONObject jsonConfig = new JSONObject();
		jsonConfig.put("...", false);// TODO completare il json per il test
		final ConfigManager m1 = ConfigManager.getNewBuilder().fromJson(jsonConfig).build();
		assertNotNull(m1);
		final JSONObject exportedConfig = m1.getConfigAsJson();
	}

	@Test
	@Order(4)
	public void builderWithMethodObserverTest() throws Exception {
		final ConfigManagerBuilder bm1 = ConfigManager.getNewBuilder().setGlobalTimeoutMs(2000);
		bm1.addUpdateObserver(generateObserver());
		addTestingConfigsBase(bm1);
		final ConfigManager m1 = bm1.build();
		assertNotNull(m1);
		final String exportedConfigYaml = m1.getConfigAsYaml();
		final JSONObject exportedConfigJson = m1.getConfigAsJson();
	}

	@Test
	@Order(3)
	public void builderWithMethodsTest() throws Exception {
		final ConfigManagerBuilder bm1 = ConfigManager.getNewBuilder();
		bm1.setGlobalTimeoutMs(2000);
		bm1.addGlobalEnrichMap(new JavaMap(3));
		bm1.addGlobalEnrichMap(new JavaMap(50, new LinkedHashMap<>(), 3400, true));
		bm1.addGlobalEnrichMap(new ExternalEnrich(generateExternalEnrishFunction()));
		final VaultConfig hashicorpVaultConfig = new VaultConfig();
		// TODO completare test Hashicorp Vault
		bm1.addGlobalEnrichMap(new HashicorpVault(hashicorpVaultConfig, "default-context"));
		final TokenCredential azureToken = null; // TODO completare test Azure Vault
		final String azureVaultUrl = null;
		bm1.addGlobalEnrichMap(new AzureVault(azureToken, azureVaultUrl));
		final Credential googleCredential = null; // TODO completare test Google Vault
		final String googleMatter = null;
		bm1.addGlobalEnrichMap(new GoogleVault("google-vault-test", googleCredential, googleMatter));
		bm1.addGlobalEnrichMap(new EnviromentsMap());
		addTestingConfigsBase(bm1);
		final ConfigManager m1 = bm1.build();
		assertNotNull(m1);
		final String exportedConfigYaml = m1.getConfigAsYaml();
		final JSONObject exportedConfigJson = m1.getConfigAsJson();
	}

	@Test
	@Order(5)
	public void builderWithPullersTest() throws Exception {
		final ConfigManagerBuilder bm1 = ConfigManager.getNewBuilder().setGlobalTimeoutMs(2000);
		bm1.addUpdateObserver(generateObserver());
		addTestingConfigsBase(bm1);
		addTestingConfigS3(bm1);
		addTestingConfigHttp(bm1);
		addTestingConfigsGit(bm1);
		final ConfigManager m1 = bm1.build();
		assertNotNull(m1);
		final String exportedConfigYaml = m1.getConfigAsYaml();
		final JSONObject exportedConfigJson = m1.getConfigAsJson();
	}

	@Test
	@Order(2)
	public void builderYamlTest() throws Exception {
		final String yamlConfig = ""; // TODO completare file yaml per il test
		final ConfigManager m1 = ConfigManager.getNewBuilder().fromYaml(yamlConfig).build();
		assertNotNull(m1);
		final String exportedConfig = m1.getConfigAsYaml();
	}

	@AfterEach
	public void cleanTestsContext() throws Exception {
		logger.info("test completed");
	}

	private void addTestingConfigHttp(final ConfigManagerBuilder bm1) {
		final Map<String, String> map = new LinkedHashMap<>();
		map.put("env1", "val1");
		final URL httpUrl = null; // TODO implementare ambiente test
		final Puller pullerHttp = new HttpPuller(httpUrl);
		bm1.addMonitoredConfig(bm1.newMonitoredConfigBuilder().addEnrichMap(new JavaMap(50, map, 3400, true))
				.addPuller(pullerHttp).addUpdateObserver(generateObserver()));

	}

	private void addTestingConfigS3(final ConfigManagerBuilder bm1) {
		final Map<String, String> map = new LinkedHashMap<>();
		map.put("env1", "val1");
		final URL s3Url = null;// TODO implementare ambiente test
		final Puller pullerS3 = new S3Puller(s3Url);
		bm1.addMonitoredConfig(bm1.newMonitoredConfigBuilder().addEnrichMap(new JavaMap(50, map, 3400, true))
				.addPuller(pullerS3).addUpdateObserver(generateObserver()));

	}

	private void addTestingConfigsBase(final ConfigManagerBuilder bm1) throws MalformedURLException {
		final Puller pullerDns = Puller.fromUrl(new URL("dns://test-savumerkki.ar4k.org"));
		bm1.addMonitoredConfig(bm1.newMonitoredConfigBuilder()
				.addEnrichMap(new ExternalEnrich(generateExternalEnrishFunction())).addPuller(pullerDns)
				.setValidator(getYamlValidator()).addUpdateObserver(generateObserver()).build());

		final URL fileUrl = null; // TODO implementare ambiente test
		final Puller pullerFile = new FilePuller(fileUrl);
		final Map<String, String> map = new LinkedHashMap<>();
		map.put("env1", "val1");
		bm1.addMonitoredConfig(bm1.newMonitoredConfigBuilder().addEnrichMap(new JavaMap(50, map, 3400, true))
				.addPuller(pullerFile).setValidator(getJsonValidator()).addUpdateObserver(generateObserver()));
	}

	private void addTestingConfigsGit(final ConfigManagerBuilder bm1) {
		final Map<String, String> map = new LinkedHashMap<>();
		map.put("env1", "val1");
		final URL gitUrl = null; // TODO implementare ambiente test
		final Puller pullerGenericGit = new GitPuller(gitUrl);
		bm1.addMonitoredConfig(bm1.newMonitoredConfigBuilder().addEnrichMap(new JavaMap(50, map, 3400, true))
				.addPuller(pullerGenericGit).addUpdateObserver(generateObserver()));
		final URL githubUrl = null; // TODO implementare ambiente test
		final Puller pullerGithub = new GithubPuller(githubUrl);
		bm1.addMonitoredConfig(bm1.newMonitoredConfigBuilder().addEnrichMap(new JavaMap(50, map, 3400, true))
				.addPuller(pullerGithub).addUpdateObserver(generateObserver()));
		final URL gitlabUrl = null; // TODO implementare ambiente test
		final Puller pullerGiLab = new GitlabPuller(gitlabUrl);
		bm1.addMonitoredConfig(bm1.newMonitoredConfigBuilder().addEnrichMap(new JavaMap(50, map, 3400, true))
				.addPuller(pullerGiLab).addUpdateObserver(generateObserver()));

	}

	private ExternalEnrichFunction generateExternalEnrishFunction() {
		return new ExternalEnrichFunction() {

			@Override
			public String getValue(final String key) {
				logger.info("getValue called for key " + key);
				return "value of key " + key;
			}

			@Override
			public void reset() {
				logger.info("reset called");

			}

		};
	}

	private ConfigUpdateEventObserver generateObserver() {
		return new ConfigUpdateEventObserver() {

			@Override
			public void fire(final UpdateEvent event) {
				logger.info("fire event " + event);
				logger.info("template payload: " + event.getPayloadTemplate());
				logger.info("enrich phase logs: " + event.getEnrichLogger());
				logger.info("completed payload: " + event.getPayloadElaborated());
				logger.info("erros: " + event.getErrors());
			}
		};
	}

	private Validator getJsonValidator() {
		return new JsonValidator();
	}

	private Validator getYamlValidator() {
		return new YamlValidator();
	}

}
