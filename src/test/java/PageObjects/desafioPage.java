package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;

import static utils.Utils.*;

public class desafioPage {
    public void validacaoUrlPage(String currentURL) throws MalformedURLException, InterruptedException {
        if(!driver.getCurrentUrl().equals(currentURL)){
            logger.severe(String.format("A URL atual não corresponde à URL final. URL atual: %s", driver.getCurrentUrl()));
            throw new MalformedURLException(String.format("A URL atual não corresponde à URL final. URL atual: %s", driver.getCurrentUrl()));
        }
        if(driver.getCurrentUrl().equals("https://demo.automationtesting.in/Register.html")){
            mockUpCountry("Australia");
        }
        removeAds();
    }
    private void removeAds() throws InterruptedException {
        String script = "var elementsByClass = document.querySelectorAll('.adsbygoogle.adsbygoogle-noablate');" +
                "elementsByClass.forEach(function(element) {" +
                "    element.remove();" +
                "});" +
                "var elementById = document.getElementById('ad_iframe');" +
                "if (elementById) {" +
                "    elementById.remove();" +
                "}";

        ((JavascriptExecutor) driver).executeScript(script);
    }
    private void mockUpCountry(String valueToMock){
        String script = "var selectElement = document.getElementById('countries');" +
                "var option = document.createElement('option');" +
                "option.value = '"+valueToMock+"';" +
                "option.text = '"+valueToMock+"';" +
                "selectElement.appendChild(option);";

        ((JavascriptExecutor) driver).executeScript(script);
    }
    public void clickInputText(String label, String botao){
        findXpath(String.format("//*[text()[contains(.,'%s')]]/ancestor::div[1]//input[@value='%s']",label,botao)).click();
    }
    public void selectValue(String label, String value,String indice){
        WebElement selectXpath = findXpath(String.format("(//*[text()[contains(.,'%s')]]/ancestor::div[1]//select)[%s]",label,indice));
        Select select = new Select(selectXpath);
        select.selectByValue(value);
    }
    public void typeViaLabel(String tipoCampo, String label,String valor,String indice){
        String nomeLabel = label;

        WebElement input = driver.findElement(By.xpath(String.format("(//div[label[contains(text(), '%s')]]//%s)[%s]",nomeLabel,tipoCampo,indice)));
        input.sendKeys(valor);

    }
    public void preencherLanguagesECountry(String value){
        WebElement languagesDropdown = driver.findElement(By.id("msdd"));
        languagesDropdown.click();
        WebElement valueDropDown = driver.findElement(By.xpath(String.format("//*[@id='msdd']/ancestor::div[1]//div[@style='display: block;']/ul/li/a[text()='%s']",value)));
        valueDropDown.click();
        driver.findElement(By.cssSelector("body")).click();

    }
    public void preencherSelectCountry(String country){
        driver.findElement(By.cssSelector("[class=\"select2-selection__arrow\"]")).click();
        driver.findElement(By.xpath(String.format("//*[@class='select2-results']/ul/li[contains(text(), '%s')]",country))).click();

    }
    public void clickBtnSubmit(){
        driver.findElement(By.id("submitbtn")).click();
    }
    public void iframe1(String texto){
        WebElement iframeElement = driver.findElement(By.id("singleframe")); // Substitua "seuIDdoIframe" pelo ID do iframe

        driver.switchTo().frame(iframeElement);

        WebElement elementoDentroDoIframe = driver.findElement(By.xpath("//input[@type=\"text\"]")); // Substitua "elementoDentroDoIframe" pelo ID do elemento dentro do iframe
        elementoDentroDoIframe.sendKeys(texto);

        driver.switchTo().defaultContent();
    }
    public void iframe2(String texto){
        driver.findElement(By.xpath(String.format("//*[text()[contains(.,'%s')]]","Iframe with in an Iframe"))).click();
        WebElement iframePrincipal = driver.findElement(By.xpath("//div[@id='Multiple']/iframe"));
        driver.switchTo().frame(iframePrincipal);
        WebElement iframeInterno = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(iframeInterno);
        WebElement campoTexto = driver.findElement(By.xpath("//input"));
        campoTexto.sendKeys(texto);
    }
    public void preencherDatas(String date1, String date2){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("document.getElementById('datepicker1').value = '%s';",date1));
        js.executeScript(String.format("document.getElementById('datepicker2').value = '%s';",date2));
    }
    public void slider(){
        WebElement sliderHandle = driver.findElement(By.cssSelector("a.ui-slider-handle"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.left = '49%';", sliderHandle);

    }
    public void validacaoSlide(){
        driver.findElement(By.cssSelector("[class=\"ui-slider-handle ui-state-default ui-corner-all\"][style=\"left: 49%;\"]")).isDisplayed();
    }
}
