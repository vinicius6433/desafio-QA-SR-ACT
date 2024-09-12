package br.com.act.selenium.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepsDefinitions",
        dryRun = false,
        tags = "@automationtesting",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = false,
        plugin = {
                "pretty",
                "json:target/cucumber-results/cucumber.json",
                "html:target/cucumber-results/cucumber.html"
        }
)
public class RunnerTest {

}