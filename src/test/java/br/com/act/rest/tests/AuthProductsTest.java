package br.com.act.rest.tests;

import br.com.act.rest.core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.*;

public class AuthProductsTest extends BaseTest {
    private static final String LOGIN_URL = "/auth/login";
    private static final String PRODUCTS_URL = "/auth/products";
    private static final String USERNAME = "emilys";
    private static final String PASSWORD = "emilyspass";
    private static final String INVALID_TOKEN = "INVALID_TOKEN";

        @Before
        public void setUp() {
            String token = autenticarELogar(USERNAME, PASSWORD);
            configurarCabecalhoDeAutorizacao(token);
        }

        @After
        public void tearDown() {
            // Limpar o cabeçalho de autorização após cada teste
            removerCabecalhoDeAutorizacao();
        }

        private String autenticarELogar(String username, String password) {
            return given()
                    .body(criarPayloadLogin(username, password))
                    .when()
                    .post(LOGIN_URL)
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("token");
        }

        private Map<String, String> criarPayloadLogin(String username, String password) {
            Map<String, String> payload = new HashMap<>();
            payload.put("username", username);
            payload.put("password", password);
            return payload;
        }

        private void configurarCabecalhoDeAutorizacao(String token) {
            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
        }

        private void removerCabecalhoDeAutorizacao() {
            FilterableRequestSpecification req = (FilterableRequestSpecification) requestSpecification;
            req.removeHeader("Authorization");
        }

        @Test
        public void testarAcessoComTokenValido() {
            Response response = given()
                    .when()
                    .get(PRODUCTS_URL);

            response.then()
                    .statusCode(200)
                    .body("products", notNullValue())
                    .body("products.size()", greaterThan(0))
                    .body("total", greaterThan(0));
        }

        @Test
        public void testarEstruturaDeRespostaComTokenValido() {
            Response response = given()
                    .when()
                    .get(PRODUCTS_URL);

            response.then()
                    .statusCode(200)
                    .body("products", notNullValue())
                    .body("total", notNullValue())
                    .body("skip", notNullValue())
                    .body("limit", notNullValue())
                    .body("products[0].id", notNullValue())
                    .body("products[0].title", notNullValue())
                    .body("products[0].price", notNullValue())
                    .body("products[0].discountPercentage", notNullValue())
                    .body("products[0].rating", notNullValue())
                    .body("products[0].stock", notNullValue())
                    .body("products[0].brand", notNullValue())
                    .body("products[0].category", notNullValue())
                    .body("products[0].thumbnail", notNullValue());
        }

        @Test
        public void testarAcessoComTokenInvalido() {
            removerCabecalhoDeAutorizacao();
            configurarCabecalhoDeAutorizacao(INVALID_TOKEN);

            Response response = given()
                    .when()
                    .get(PRODUCTS_URL);

            response.then()
                    .statusCode(401)
                    .body("name", equalTo("JsonWebTokenError"))
                    .body("message", equalTo("Invalid/Expired Token!"));
        }

        @Test
        public void testarAcessoSemToken() {
            removerCabecalhoDeAutorizacao();

            Response response = given()
                    .when()
                    .get(PRODUCTS_URL);

            response.then()
                    .statusCode(403)
                    .body("message", equalTo("Authentication Problem"));
        }
    }

