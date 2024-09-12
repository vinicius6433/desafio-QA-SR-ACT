package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

	public static WebDriver driver;
	public static final Logger logger = Logger.getLogger(Utils.class.getName());

	public static void acessarSistema(String path) {

		FirefoxOptions options = new FirefoxOptions();
		//options.addArguments("--remote-allow-origins=*");
		//options.addArguments("--headless=new");


		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver(options);


		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("https://demo.automationtesting.in" + path);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


		logger.log(Level.INFO, "Acessou o sistema com sucesso!");
	}

	public static WebElement findXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
	}

	private static LocalDate getDate(){
		return LocalDate.now();
	}

	JavascriptExecutor js = (JavascriptExecutor) driver;

	public static void disableAds(List<WebElement> adspopup) throws InterruptedException {

		if((adspopup.size() > 0) || driver.getCurrentUrl().contains("google_vignette"))  {
// Comparing the web URL
//            assertEquals("https://practice.automationtesting.in/#google_vignette", driver.getCurrentUrl());

//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[contains(@class,'close')]"))));



			Thread.sleep(1000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
          /*  WebElement AdButton = driver.findElement(By.xpath("//div[contains(@class,'close')]"));
            AdButton.click();*/

// Comparing the web URL
//            String newURL = driver.getCurrentUrl();
//            assertEquals("https://practice.automationtesting.in/my-account/", newURL);
		}



	}

	private void hideElements()
	{

		js.executeScript("const elements = document.getElementsByClassName('GoogleActiveViewElement'); while (elements.length > 0) elements[0].remove()");

	}






	public static String buscandoDadosNoJsonComObjeto(String path, String key){
		String chave = "";
		JSONObject jsonObject;
		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();

		try {
			//Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(
					"dados/"+path+".json"));

			//Salva nas variaveis os dados retirados do arquivo
			chave = (String) jsonObject.get(key);

		}
		//Trata as exceptions que podem ser lançadas no decorrer do processo
		catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chave;
	}


}
