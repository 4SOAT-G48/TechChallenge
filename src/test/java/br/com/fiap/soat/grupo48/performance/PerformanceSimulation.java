package br.com.fiap.soat.grupo48.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class PerformanceSimulation extends Simulation {
  private final HttpProtocolBuilder httpProtocolBuilder =
      http
          .baseUrl("http://localhost:8080/api")
          .acceptHeader("application/json")
          .contentTypeHeader("application/json");

  ActionBuilder adicionarProdutoRequest = http("Adicionar produto")
      .post("/produtos")
      .body(CoreDsl.StringBody("{\"nome\": \"Batata frita teste (2000g)\",\"descricao\": \"Batatas para um grupo todo.\",\"categoria\": \"ACOMPANHAMENTO\",\"preco\": 75.90,\"situacao\": \"INDISPONIVEL\",\"imagens\": []}"))
      .check(status().is(201))
      .check(jsonPath("$.codigo").saveAs("idProduto"));


  ActionBuilder buscarProdutoRequest = http("Buscar produto")
      .get("/produtos/#{idProduto}")
      .check(status().is(200));

  ActionBuilder removerProdutoRequest = http("Remover produto")
      .delete("/produtos/#{idProduto}")
      .check(status().is(200));

  ScenarioBuilder adicionarProdutoScenario = scenario("Adicionar produto")
      .exec(adicionarProdutoRequest);

  ScenarioBuilder buscarProdutoScenario = scenario("Buscar produto")
      .exec(adicionarProdutoRequest)
      .exec(buscarProdutoRequest);

  ScenarioBuilder removerProdutoScenario = scenario("Remover produto")
      .exec(adicionarProdutoRequest)
      .exec(removerProdutoRequest);

  {
    setUp(
        adicionarProdutoScenario.injectOpen(
            rampUsersPerSec(1)
                .to(2)
                .during(Duration.ofSeconds(10)),
            constantUsersPerSec(2)
                .during(Duration.ofSeconds(20)),
            rampUsersPerSec(2)
                .to(1)
                .during(Duration.ofSeconds(10))
        ),
        buscarProdutoScenario.injectOpen(
            rampUsersPerSec(1)
                .to(10)
                .during(Duration.ofSeconds(10)),
            constantUsersPerSec(10)
                .during(Duration.ofSeconds(20)),
            rampUsersPerSec(10)
                .to(1)
                .during(Duration.ofSeconds(10))
        ),

        removerProdutoScenario.injectOpen(
            rampUsersPerSec(1)
                .to(5)
                .during(Duration.ofSeconds(10)),
            constantUsersPerSec(5)
                .during(Duration.ofSeconds(20)),
            rampUsersPerSec(5)
                .to(1)
                .during(Duration.ofSeconds(10))
        )
    )
        .protocols(httpProtocolBuilder)
        .assertions(
            CoreDsl.global().responseTime().max().lt(50)
        );

  }
}
