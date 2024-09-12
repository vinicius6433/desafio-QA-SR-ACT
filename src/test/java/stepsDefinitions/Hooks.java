package stepsDefinitions;

import static utils.Utils.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.util.HashMap;
import java.util.Map;

public class Hooks {
	@Before()
	public void browserSetup(Scenario scenario) {
		Map<String, String> tagToUrlMapping = new HashMap<>();
		tagToUrlMapping.put("@Register", "/Register.html");
		tagToUrlMapping.put("@Frames", "/Frames.html");
		tagToUrlMapping.put("@Datepicker","/Datepicker.html");
		tagToUrlMapping.put("@Slider","/Slider.html");
		acessarPagina(scenario, tagToUrlMapping);
	}

	private void acessarPagina(Scenario scenario, Map<String, String> tagToUrlMapping) {
		for (String tagName : scenario.getSourceTagNames()) {
			if (tagToUrlMapping.containsKey(tagName)) {
				acessarSistema(tagToUrlMapping.get(tagName));
				return;
			}
		}
		throw new RuntimeException("Nenhuma página correspondente encontrada para as tags fornecidas.");
	}
	 @After()
	 public void afterScenario(Scenario scenario) {
		try {
			tearDown(scenario, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();
	}

	public void tearDown(Scenario scenario, Throwable throwable) {
		System.out.println("Finalizando Driver para a step: " + scenario.getName());
		if (throwable != null) {
			try {
				WebDriver augmentedDriver = new Augmenter().augment(driver);
				byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Screenshot");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
