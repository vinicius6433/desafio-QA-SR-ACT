package br.com.act.selenium.stepsDefinitions;

import br.com.act.selenium.PageObjects.desafioPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.net.MalformedURLException;

public class desafioSteps {
    desafioPage dp = new desafioPage();
    String pathBase = "https://demo.automationtesting.in/";
    @Dado("que eu esteja na URL {string}")
    public void queEuEstejaNaURL(String url) throws MalformedURLException, InterruptedException {
        dp.validacaoUrlPage( pathBase+ url);

    }
    @Quando("eu preencher o iframe {string}")
    public void euPreencherOIframe(String texto) {
        dp.iframe1(texto);
    }
    @E("que eu preencha os campos do cadastro via {string} utilizando a {string}, {string} e {string}")
    public void queEuPreenchaOsCamposDoCadastroUtilizandoAE(String tipoCampo,String label, String valor, String indice) {
        dp.typeViaLabel(tipoCampo, label,valor,indice);
    }

    @E("que eu clique no campo via {string} e clique no botao {string}")
    public void queEuCliqueNoCampoViaECliqueNoBotaoNoIndice(String label,String botao) {
        dp.clickInputText(label, botao);
    }


    @E("que eu selecione no campo via {string} e {string} e {string}")
    public void queEuSelecioneNoCampoViaEE(String label, String value,String indice) {
        dp.selectValue(label,value,indice);
    }


    @E("que eu preencha os campos select country {string} e languages")
    public void queEuPreenchaOsCamposSelectCountryELanguages(String value) {
        dp.preencherLanguagesECountry(value);
    }



    @E("que eu preencha os campos do cadastro Select Country com {string}")
    public void queEuPreenchaOsCamposDoCadastroSelectCountryCom(String country) {
        dp.preencherSelectCountry(country);
    }

    @Entao("pressiono o botao de salvar mas nada ocorre por que a api esta off rsrs")
    public void pressionoOBotaoDeSalvarMasNadaOcorrePorQueAApiEstaOffRsrs() {
        dp.clickBtnSubmit();
    }


    @Quando("eu preencher o iframe dentro do iframe {string}")
    public void euPreencherOIframeDentroDoIframe(String arg0) {
        dp.iframe2(arg0);
    }


    @Quando("eu preencher os campos de data  {string} e  {string}")
    public void euPreencherOsCamposDeDataE(String arg0, String arg1) {
        dp.preencherDatas(arg0,arg1);
    }

    @E("eu mova o slider para {int} porcento")
    public void euMovaOSliderParaPorcento(int arg0) {
        dp.slider();
    }

    @Entao("valido que o slide esta com o atributo em {int} porcento")
    public void validoQueOSlideEstaComOAtributoEmPorcento(int arg0) {
        dp.validacaoSlide();
    }
}
