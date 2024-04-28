package br.com.fiap.soat.grupo48.application.produto.model;


import br.com.fiap.soat.grupo48.application.commons.exception.NomeException;
import br.com.fiap.soat.grupo48.application.commons.valueobject.Nome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProdutoTest {

  private Produto produto;

  @BeforeEach
  void setup() throws NomeException {
    produto = new Produto(null,
        "Hambúrguer Simples",
        Categoria.LANCHE,
        20.0,
        "Hambúrguer de carne 150g com queijo e alface",
        SituacaoProduto.DISPONIVEL,
        null);
  }

  @Test
  void deveCriar() {
    assertThat(produto).isNotNull();
    assertThat(produto.getCodigo()).isNull();
    assertThat(produto.getNome().nome()).isEqualTo("Hambúrguer Duplo");
    assertEquals(Categoria.LANCHE, produto.getCategoria());
    assertEquals(20.0, produto.getPreco());
    assertEquals("Hambúrguer de carne 150g com queijo e alface", produto.getDescricao());
    assertEquals(SituacaoProduto.DISPONIVEL, produto.getSituacao());
    assertNull(produto.getImagens());
  }

  @Test
  void deveAtualizarNome() {
    produto.setNome(new Nome("Hambúrguer Duplo"));
    assertEquals("Hambúrguer Duplo", produto.getNome().nome());
  }

  @Test
  void deveAtualizarDescricao() {
    produto.setDescricao("2 Hambúrgueres de carne 150g com queijo, alface e tomate");
    assertEquals("2 Hambúrgueres de carne 150g com queijo, alface e tomate", produto.getDescricao());
  }

  @Test
  void deveAtualizarPreco() {
    produto.setPreco(30.0);
    assertEquals(30.0, produto.getPreco());
  }

  @Test
  void deveAtualizarSituacao() {
    produto.setSituacao(SituacaoProduto.INDISPONIVEL);
    assertEquals(SituacaoProduto.INDISPONIVEL, produto.getSituacao());
  }

  @Test
  void deveCriar_GerarExecaoQuandoNomeNulo() {
    Throwable exception = catchThrowable(() -> new Produto(null, (String) null, null, null, null, null, null));
    assertThat(exception).isInstanceOf(NomeException.class)
        .hasMessage("O nome do produto não pode ser nulo");
  }

  @Test
  void deveCriar_GerarExecaoQuandoNomeVazio() {
    Throwable exception = catchThrowable(() -> new Produto(null, "", null, null, null, null, null));
    assertThat(exception).isInstanceOf(NomeException.class)
        .hasMessage("O nome do produto não pode ser vazio");
  }

  @Test
  void deveCriar_GerarExecaoQuandoNomeMenorQue3Caracteres() {
    Throwable exception = catchThrowable(() -> new Produto(null, "a", null, null, null, null, null));
    assertThat(exception).isInstanceOf(NomeException.class)
        .hasMessage("O nome do produto tem que ter o valor minimo de 3 caracteres");
  }
}
