package br.com.act.rest.tests;

import br.com.act.rest.core.BaseTest;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginTest extends BaseTest {

    private static final String ENDPOINT_LOGIN = "/auth/login";
    private static final String USUARIO_VALIDO = "emilys";
    private static final String SENHA_VALIDA = "emilyspass";
    private static final String USUARIO_INVALIDO = "wronguser";
    private static final String SENHA_INVALIDA = "wrongpass";

    private Response realizarLogin(String usuario, String senha) {
        Map<String, String> corpoRequisicao = new HashMap<>();
        corpoRequisicao.put("username", usuario);
        corpoRequisicao.put("password", senha);

        return given()
                .body(corpoRequisicao)
                .when()
                .post(ENDPOINT_LOGIN);
    }

    @Test
    public void testLoginComCredenciaisValidas() {
        Response resposta = realizarLogin(USUARIO_VALIDO, SENHA_VALIDA);

        resposta.then().assertThat()
                .statusCode(200)
                .body("username", equalTo(USUARIO_VALIDO))
                .body("token", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    public void testLoginComCredenciaisInvalidas() {
        Response resposta = realizarLogin(USUARIO_INVALIDO, SENHA_INVALIDA);

        resposta.then().assertThat()
                .statusCode(400)
                .body("message", is("Invalid credentials"));
    }

    @Test
    public void testLoginComDadosFaltando1() {
        Map<String, String> corpoRequisicao = new HashMap<>();
        corpoRequisicao.put("username", USUARIO_INVALIDO);

        Response resposta = given()
                .body(corpoRequisicao)
                .when()
                .post(ENDPOINT_LOGIN);

        resposta.then().assertThat()
                .statusCode(400)
                .body("message", is("Username and password required"));
    }

    @Test
    public void testLoginComDadosFaltando2() {
        Map<String, String> corpoRequisicao = new HashMap<>();
        corpoRequisicao.put("password", SENHA_INVALIDA);

        Response resposta = given()
                .body(corpoRequisicao)
                .when()
                .post(ENDPOINT_LOGIN);

        resposta.then().assertThat()
                .statusCode(400)
                .body("message", is("Username and password required"));
    }

    @Test
    public void testEstruturaRespostaLogin() {
        Response resposta = realizarLogin(USUARIO_VALIDO, SENHA_VALIDA);

        resposta.then().assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("username", equalTo(USUARIO_VALIDO))
                .body("email", notNullValue())
                .body("firstName", notNullValue())
                .body("lastName", notNullValue())
                .body("token", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    public void testTokenAutenticacao() {
        Response resposta = realizarLogin(USUARIO_VALIDO, SENHA_VALIDA);

        resposta.then().assertThat()
                .statusCode(200)
                .body("token", notNullValue());
    }
}
