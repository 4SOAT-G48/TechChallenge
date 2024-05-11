package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto;

import br.com.fiap.soat.grupo48.utils.ProdutoHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class ProdutoControllerIT {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    // Quando falar ele vai apresentar
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Nested
  class CadastrarProduto {
    @Test
    void devePermitirCriarProduto() {
      // Arrange
      var produtoRequest = ProdutoHelper.gerarProdutoRequest();

      // Act & Assert
      given()
          .filter(new AllureRestAssured())
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(produtoRequest)
          //.log().all()
          .when()
          .post("/api/produtos")
          .then()
          //.log().all()
          .statusCode(HttpStatus.CREATED.value())
          .body(matchesJsonSchemaInClasspath("schemas/ProdutoResponse.schema.json"));
    }

    @Test
    void deveRetornarUnsupportedMediaTypeQuandoPassadoPayloadXML() {
      // Arrange
      var produtoRequest = ProdutoHelper.gerarProdutoRequest();

      // Act & Assert
      given()
          .filter(new AllureRestAssured())
          .contentType(MediaType.APPLICATION_XML_VALUE)
          .body(ProdutoHelper.asXmlString(produtoRequest))
//          .log().all()
          .when()
          .post("/api/produtos")
          .then()
//          .log().all()
          .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
          .body(matchesJsonSchemaInClasspath("schemas/Error.schema.json"))
          .body("error", equalTo("Unsupported Media Type"))
          .body("path", equalTo("/api/produtos"));
    }

    @Test
    void deveGerarProdutosIniciais() {
      // Act & Assert
      given()
          .filter(new AllureRestAssured())
          .when()
          .get("/api/produtos/populaprodutosiniciais")
          .then()
          .statusCode(HttpStatus.OK.value());
    }
  }

  @Nested
  class BuscarProduto {
    @Test
    void deveRetornarProduto() {
      // Arrange
      var id = UUID.fromString("3c2bea4f-7687-4e0a-8c3d-9153fb4eb639");

      // Act & Assert
      given()
          .filter(new AllureRestAssured())
          .when()
          .get("/api/produtos/{id}", id)
          .then()
//          .log().all()
          .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveGerarExceptionQuandoIdNaoExiste() {
      // Arrange
      var id = UUID.fromString("7dffa7d6-713a-4bac-bcdd-5fcf0fbcf3a5");

      // Act & Assert
      given()
          .filter(new AllureRestAssured())
          .when()
          .get("/api/produtos/{id}", id)
          .then()
          .statusCode(HttpStatus.NOT_FOUND.value());
    }
  }

  @Nested
  class AtualizarProduto {
    @Test
    void devePermitirAtualizarProduto() {
      // Arrange
      var id = UUID.fromString("69e3f2a1-ee20-4c4c-a3c5-87c85fe4efa8");
      var produtoRequest = ProdutoHelper.gerarProdutoRequest();
      produtoRequest.setCodigo(id);
      produtoRequest.setNome("Produto Atualizado");
      produtoRequest.setDescricao("Descrição Produto Atualizado");

      // Act & Assert
      given()
          .filter(new AllureRestAssured())
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(produtoRequest)
          .when()
          .put("/api/produtos/{id}", id)
          .then()
          .statusCode(HttpStatus.ACCEPTED.value())
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(matchesJsonSchemaInClasspath("schemas/ProdutoResponse.schema.json"));
    }


    @Test
    void deveRetornarBadRequestQuandoIdNaoExiste() {
      // Arrange
      var idNaoExiste = UUID.fromString("527d522b-1793-4b6b-b68d-fdc479900858");
      var produtoRequest = ProdutoHelper.gerarProdutoRequest();
      produtoRequest.setCodigo(idNaoExiste);
      produtoRequest.setNome("Produto Atualizado");
      produtoRequest.setDescricao("Descrição Produto Atualizado");

      // Act & Assert
      given()
          .filter(new AllureRestAssured())
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(produtoRequest)
          .when()
          .put("/api/produtos/{id}", idNaoExiste)
          .then()
          .statusCode(HttpStatus.BAD_REQUEST.value())
//          .log().all()
          .body(equalTo("Produto não encontrado"))
      ;
    }

    @Test
    void deveBadRequestQuandoOsIdsSaoDiferentes() {
      // Arrange
      var idNaoExiste = UUID.fromString("527d522b-1793-4b6b-b68d-fdc479900858");
      var id = UUID.fromString("69e3f2a1-ee20-4c4c-a3c5-87c85fe4efa8");
      var produtoRequest = ProdutoHelper.gerarProdutoRequest();
      produtoRequest.setCodigo(id);
      produtoRequest.setNome("Produto Atualizado");
      produtoRequest.setDescricao("Descrição Produto Atualizado");

      // Act & Assert
      given()
          .filter(new AllureRestAssured())
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(produtoRequest)
          .when()
          .put("/api/produtos/{id}", idNaoExiste)
          .then()
          .statusCode(HttpStatus.BAD_REQUEST.value())
//          .log().all()
          .body(equalTo("Produto não encontrado"))
      ;
    }


    @Test
    void deveRetornarUnsupportedMediaType() {
      // Arrange
      var id = UUID.fromString("69e3f2a1-ee20-4c4c-a3c5-87c85fe4efa8");
      var produtoRequest = ProdutoHelper.gerarProdutoRequest();
      produtoRequest.setCodigo(id);
      produtoRequest.setNome("Produto Atualizado");
      produtoRequest.setDescricao("Descrição Produto Atualizado");

      // Act & Assert
      given()
          .contentType(MediaType.APPLICATION_XML_VALUE)
          .body(ProdutoHelper.asXmlString(produtoRequest))
          .when()
          .put("/api/produtos/{id}", id)
          .then()
          .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
          .body(matchesJsonSchemaInClasspath("schemas/Error.schema.json"))
          .body("error", equalTo("Unsupported Media Type"))
          .body("path", equalTo("/api/produtos/" + id));
    }

  }


  @Nested
  class ExcluirProduto {
    @Test
    void devePermitirExcluirProduto() {
      // Arrange
      var id = UUID.fromString("1494d828-4a41-46de-a340-fea1ff79cd63");

      // Act & Assert
      when()
          .delete("/api/produtos/{id}", id)
          .then()
          .statusCode(HttpStatus.OK.value())
          .body(equalTo("Produto excluído"));
    }

    @Test
    void deveRetornarBadRequestQuandoIdNaoExiste() {
      // Arrange
      var id = UUID.fromString("1494d828-4a41-46de-a340-fea1ff79cd66");

      // Act & Assert
      when()
          .delete("/api/produtos/{id}", id)
          .then()
          .statusCode(HttpStatus.BAD_REQUEST.value())
          .body(equalTo("Produto não encontrado"));
    }
  }

  @Nested
  class ListarProdutos {
    @Test
    void devePermitirBuscarProdutosPaginados() {
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .param("page", 0)
          .param("size", 10)
          .when()
          .get("/api/produtos/paginados")
          .then()
          .statusCode(HttpStatus.OK.value())
          .body(matchesJsonSchemaInClasspath("schemas/ProdutoResponsePaginado.schema.json"));
    }

    @Test
    void devePermitirBuscarProdutos() {
      given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .when()
          .get("/api/produtos")
          .then()
          .statusCode(HttpStatus.OK.value());
    }

    @Test
    void devePermitirBuscarProdutosPaginadosQuandoNaoInformadaPaginacao() {
      when()
          .get("/api/produtos/paginados")
          .then()
//          .log().all()
          .statusCode(HttpStatus.OK.value())
          .body(matchesJsonSchemaInClasspath("schemas/ProdutoResponsePaginado.schema.json"))
      ;
    }
  }

}
