package br.com.fiap.soat.grupo48.utils;

import br.com.fiap.soat.grupo48.application.commons.valueobject.Nome;
import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ProdutoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto.ProdutoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.ArrayList;

public abstract class ProdutoHelper {

  public static Produto gerarProduto() {
    return Produto.builder()
        .nome(new Nome("Hambúrguer Simples"))
        .descricao("Hambúrguer de carne 150g com queijo e alface")
        .preco(20.0)
        .situacao(SituacaoProduto.DISPONIVEL)
        .categoria(Categoria.LANCHE)
        .imagens(new ArrayList<>(0))
        .build();
  }

  public static ProdutoEntity gerarProdutoEntity() {
    return ProdutoEntity.builder()
        .nome("Hambúrguer Simples")
        .descricao("Hambúrguer de carne 150g com queijo e alface")
        .preco(20.0)
        .situacao(SituacaoProduto.DISPONIVEL)
        .categoria(Categoria.LANCHE)
        .build();
  }

  public static ProdutoRequest gerarProdutoRequest() {
    return ProdutoRequest.builder()
        .nome("Hambúrguer Simples")
        .descricao("Hambúrguer de carne 150g com queijo e alface")
        .preco(20.0)
        .situacao(SituacaoProduto.DISPONIVEL)
        .categoria(Categoria.LANCHE)
        .build();
  }

  public static String asJsonString(final Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String asXmlString(final Object object) {
    try {
      //retornar o objeto passado em uma string com formato xml
      XmlMapper xmlMapper = new XmlMapper();
      return xmlMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
