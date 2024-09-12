package br.com.act.rest.tests;

import br.com.act.rest.core.BaseTest;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class ProductsTest extends BaseTest {

    private static final String PRODUCTS_ENDPOINT = "/products";
    private static final String ADD_PRODUCT_ENDPOINT = "/products/add";

    private Response postarProduto(Map<String, Object> corpoRequisicao) {
        return given()
                .body(corpoRequisicao)
                .when()
                .post(ADD_PRODUCT_ENDPOINT);
    }

    @Test
    public void testarCriarProdutoDadosValidos() {
        Map<String, Object> corpoRequisicao = criarCorpoRequisicaoProdutoValido();

        postarProduto(corpoRequisicao)
                .then()
                .statusCode(201)
                .body("title", equalTo("Perfume Oil"))
                .body("price", equalTo(13))
                .body("stock", equalTo(65))
                .body("rating", equalTo(4.26f))
                .body("thumbnail", equalTo("https://i.dummyjson.com/data/products/11/thumnail.jpg"))
                .body("description", equalTo("Mega Discount, Impression of A..."))
                .body("brand", equalTo("Impression of Acqua Di Gio"))
                .body("category", equalTo("fragrances"));
    }

    @Test
    public void testarCriarProdutoCampoFaltando() {
        Map<String, Object> corpoRequisicao = new HashMap<>();
        corpoRequisicao.put("title", "Perfume Oil");
        corpoRequisicao.put("price", 13);
        corpoRequisicao.put("rating", 4.26);
        corpoRequisicao.put("stock", 65);

        postarProduto(corpoRequisicao)
                .then();
        //.statusCode(400)
        //.body("error", containsString("Missing required fields"));
    }

    @Test
    public void testarCriarProdutoDadosInvalidos() {
        Map<String, Object> corpoRequisicao = criarCorpoRequisicaoProdutoValido();
        corpoRequisicao.put("price", -13); // Preço inválido

        postarProduto(corpoRequisicao)
                .then();
        //.statusCode(400)
        //.body("error", containsString("Invalid price"));
    }

    @Test
    public void testarBuscarTodosProdutos() {
        Response resposta = given()
                .when()
                .get(PRODUCTS_ENDPOINT);

        assertEquals(200, resposta.getStatusCode());

        Map<String, Object> corpoResposta = resposta.as(Map.class);
        assertTrue(corpoResposta.containsKey("products"));

        List<Map<String, Object>> produtos = (List<Map<String, Object>>) corpoResposta.get("products");
        Map<String, Object> primeiroProduto = produtos.get(0);

        assertEquals("Essence Mascara Lash Princess", primeiroProduto.get("title"));
        assertEquals(9.99, primeiroProduto.get("price"));
        assertNotNull(primeiroProduto.get("reviews"));
        assertTrue(primeiroProduto.get("reviews") instanceof List);

        List<Map<String, Object>> avaliacoes = (List<Map<String, Object>>) primeiroProduto.get("reviews");
        assertFalse("A lista de avaliações não deve estar vazia", avaliacoes.isEmpty());

        Map<String, Object> primeiraAvaliacao = avaliacoes.get(0);
        assertEquals("John Doe", primeiraAvaliacao.get("reviewerName"));
        //assertEquals(2.0, primeiraAvaliacao.get("rating"));
    }

    @Test
    public void testarBuscarProdutoPorId_ProdutoExistente() {
        Response resposta = given()
                .when()
                .get(PRODUCTS_ENDPOINT + "/1");

        assertEquals(200, resposta.getStatusCode());

        Map<String, Object> produto = resposta.as(Map.class);
        //assertEquals(1, produto.get("id"));
        assertEquals("Essence Mascara Lash Princess", produto.get("title"));
        assertEquals(9.99, produto.get("price"));
        assertEquals("beauty", produto.get("category"));
        assertTrue(produto.containsKey("reviews"));
        assertTrue(produto.containsKey("images"));
        assertTrue(produto.containsKey("thumbnail"));
    }

    @Test
    public void testarBuscarProdutoPorId_ProdutoNaoExistente() {
        Response resposta = given()
                .when()
                .get(PRODUCTS_ENDPOINT + "/9999");

        assertEquals(404, resposta.getStatusCode());

        Map<String, Object> respostaErro = resposta.as(Map.class);
        assertEquals("Product with id '9999' not found", respostaErro.get("message"));
    }

    private Map<String, Object> criarCorpoRequisicaoProdutoValido() {
        Map<String, Object> corpoRequisicao = new HashMap<>();
        corpoRequisicao.put("title", "Perfume Oil");
        corpoRequisicao.put("description", "Mega Discount, Impression of A...");
        corpoRequisicao.put("price", 13);
        corpoRequisicao.put("discountPercentage", 8.4);
        corpoRequisicao.put("rating", 4.26);
        corpoRequisicao.put("stock", 65);
        corpoRequisicao.put("brand", "Impression of Acqua Di Gio");
        corpoRequisicao.put("category", "fragrances");
        corpoRequisicao.put("thumbnail", "https://i.dummyjson.com/data/products/11/thumnail.jpg");
        return corpoRequisicao;
    }
}
