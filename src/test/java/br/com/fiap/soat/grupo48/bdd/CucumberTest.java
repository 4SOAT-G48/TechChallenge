package br.com.fiap.soat.grupo48.bdd;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("Cucumber Tests")
@IncludeEngines("cucumber")
@SelectClasspathResource("br/com/fiap/soat/grupo48/bdd/steps")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features/produto.feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "br/com/fiap/soat/grupo48/bdd/steps")
@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report/cucumber.html")
public class CucumberTest {

}
