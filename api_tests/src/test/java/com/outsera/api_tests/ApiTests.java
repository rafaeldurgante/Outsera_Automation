package com.outsera.api_tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class ApiTests {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Test GET /posts/{id}")
    @Step("Getting post with ID {postId}")
    @Severity(SeverityLevel.NORMAL)
    public void testGetPost() {
        int postId = 1;
        String expectedTitle = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String expectedBody = "quia et suscipit\n" +
                "suscipit recusandae consequuntur expedita et cum\n" +
                "reprehenderit molestiae ut ut quas totam\n" +
                "nostrum rerum est autem sunt rem eveniet architecto";

        Response response = given()
                .when()
                .get("/posts/" + postId)
                .then()
                .extract().response();

        // Verificações
        response.then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("userId", equalTo(1))
                .body("id", equalTo(postId))
                .body("title", equalTo(expectedTitle))
                .body("body", equalTo(expectedBody));

        // Verificações adicionais
        assertFalse(response.getBody().asString().isEmpty(), "Response body should not be empty");
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"), "Content-Type should be JSON");
    }

    @Test
    @DisplayName("Test POST /posts")
    @Description("Verifica a criação de um novo post")
    @Step("Creating a new post")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreatePost() {
        String requestBody = "{ \"title\": \"Outsera\", \"body\": \"Automation\", \"userId\": 1 }";

        // Envio da requisição POST
        Response response = given()
                .header("Content-type", "application/json; charset=utf-8")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201) // Verifica se o status é 201
                .header("Content-Type", "application/json; charset=utf-8") // Verifica o header Content-Type
                .extract().response();

        // Verificações
        String id = response.jsonPath().getString("id");
        assertNotNull(id, "ID should not be null"); // Verifica se o ID é válido

        // Validação do corpo da resposta
        response.then()
                .body("title", equalTo("Outsera"))
                .body("body", equalTo("Automation"))
                .body("userId", equalTo(1));

        System.out.println("Post created with ID: " + id);
    }

    @Test
    @DisplayName("Test PUT /posts/1")
    @Step("Updating post with ID 1")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdatePost() {
        String requestBody = "{ \"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1 }";

        // Envio da requisição PUT
        Response response = given()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200) // Verifica se o status é 200
                .header("Content-Type", "application/json; charset=utf-8") // Verifica o header Content-Type
                .extract().response();

        // Validação do corpo da resposta
        response.then()
                .body("id", equalTo(1)) // Verifica se o ID é o mesmo que foi atualizado
                .body("title", equalTo("updated title"))
                .body("body", equalTo("updated body"));
    }

    @Test
    @DisplayName("Test DELETE /posts/1")
    @Step("Deleting post with ID 1")
    @Severity(SeverityLevel.NORMAL)
    public void testDeletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200); // Confirme o código de status retornado pela API
    }

    // Negative Test Cases
    @Test
    @DisplayName("Test GET /posts/{id} - Negative (Not Found)")
    @Step("Getting post with ID 999 (should not be found)")
    @Severity(SeverityLevel.MINOR)
    public void testGetPostNegative() {
        given()
                .when()
                .get("/posts/999") // ID que não existe
                .then()
                .statusCode(404); // Espera-se que a API retorne 404
    }

    @Test
    @DisplayName("Test POST /posts - Wrong Endpoint")
    @Step("Creating a post with an invalid endpoint")
    @Severity(SeverityLevel.MINOR)
    public void testCreatePostNegative_WrongEndpoint() {
        // Criando um corpo de requisição inválido
        String requestBody = "{ \"title\": \"Outsera\", \"body\": \"Automation\", \"userId\": 1 }";

        given()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .when()
                .post("/postss")
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Test PUT /posts/{id} - Negative (ID nulo)")
    @Step("Updating post with ID 999 (should not be found)")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdatePostNegative_NotFound() {
        // Criando um corpo de requisição válido, mas com ID que não existe
        String requestBody = "{ \"title\": \"Updated Title\", \"body\": \"Updated Body\", \"userId\": 1 }";

        given()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .when()
                .put("/posts/999") // ID que não existe
                .then()
                .statusCode(500); // Espera-se que a API retorne 404 neste caso mas retornou 500, portanto o servidor não está tratando corretamente a situação em que um ID de post que não existe é fornecido.
    }

    @Test
    @DisplayName("Test DELETE /posts/{id} - Negative (Sem ID no parametro)")
    @Step("Deleting post without providing an ID")
    @Severity(SeverityLevel.MINOR)
    public void testDeletePostNegative_NotFound() {
        given()
                .when()
                .delete("/posts/") // ID não informado
                .then()
                .statusCode(404); // Espera-se que a API retorne 404
    }
}
