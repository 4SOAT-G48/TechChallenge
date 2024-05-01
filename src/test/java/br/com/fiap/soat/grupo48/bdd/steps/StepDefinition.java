package br.com.fiap.soat.grupo48.bdd.steps;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto.ProdutoResponse;
import br.com.fiap.soat.grupo48.utils.ProdutoHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinition {

  private Response response;

  private ProdutoResponse produtoResponse;

  private final String ENDPOINT_API_PRODUTO = "http://localhost:8080/api/produtos";

  @Quando("eu estou registrando um novo produto")
  public ProdutoResponse eu_estou_registrando_um_novo_produto() {
    var produtoRequest = ProdutoHelper.gerarProdutoRequest();
    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(ProdutoHelper.asJsonString(produtoRequest))
        .when()
        .post(ENDPOINT_API_PRODUTO);
    return response.then().extract().as(ProdutoResponse.class);
  }

  @Entao("o produto é criado com sucesso")
  public void o_produto_e_criado_com_sucesso() {
    response
        .then()
        .statusCode(HttpStatus.CREATED.value());
  }

  @Entao("deve ser apresentado")
  public void deve_ser_apresentado() {
    response
        .then()
        .body(matchesJsonSchemaInClasspath("schemas/ProdutoResponse.schema.json"));
  }


  @Dado("que um produto já foi criado")
  public void que_um_produto_ja_foi_criado() {
    produtoResponse = eu_estou_registrando_um_novo_produto();
  }

  @Quando("efetuar a busca do produto")
  public void efetuar_a_busca_do_produto() {
    response = when()
        .get(ENDPOINT_API_PRODUTO + "/{codigo}", produtoResponse.getCodigo());
  }

  @Entao("o produto é exibido com sucesso")
  public void o_produto_e_exibido_com_sucesso() {
    response
        .then()
        .body(matchesJsonSchemaInClasspath("schemas/ProdutoResponse.schema.json"));
  }

  @Quando("efetuar requisição para alterar produto")
  public void efetuar_requisicao_para_alterar_produto() {
    produtoResponse.setDescricao("Produto alterado");
    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(ProdutoHelper.asJsonString(produtoResponse))
        .when()
        .put(ENDPOINT_API_PRODUTO + "/{codigo}", produtoResponse.getCodigo());

  }

  @Entao("o produto é atualizado com sucesso")
  public void o_produto_e_atualizado_com_sucesso() {
    response
        .then()
        .statusCode(HttpStatus.ACCEPTED.value());
  }

  @Quando("requisitar a remoção do produto")
  public void requisitar_a_remocao_do_produto() {
    response = when()
        .delete(ENDPOINT_API_PRODUTO + "/{codigo}", produtoResponse.getCodigo());
  }

  @Entao("o produto é removido com sucesso")
  public void o_produto_e_removido_com_sucesso() {
    response
        .then()
        .statusCode(HttpStatus.OK.value());
  }

}
