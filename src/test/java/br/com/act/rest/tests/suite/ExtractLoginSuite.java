package br.com.act.rest.tests.suite;

import br.com.act.rest.core.BaseTest;
import br.com.act.rest.tests.AuthProductsTest;
import br.com.act.rest.tests.LoginTest;
import br.com.act.rest.tests.ProductsTest;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	AuthProductsTest.class, LoginTest.class, ProductsTest.class
})
public class ExtractLoginSuite extends BaseTest {

	private static final String LOGIN_URL = "/auth/login";
	private static final String EMAIL = "emilys";
	private static final String PASSWORD = "emilyspass";

	@BeforeClass
	public static void setup() {
		String token = performLogin();
		RestAssured.requestSpecification.header("Authorization", "Bearer " + token);
	}

	private static String performLogin() {
		Map<String, String> loginCredentials = new HashMap<>();
		loginCredentials.put("email", EMAIL);
		loginCredentials.put("senha", PASSWORD);

		return given()
				.body(loginCredentials)
				.when()
				.post(LOGIN_URL)
				.then()
				.statusCode(200)
				.extract()
				.path("token");
	}
}
