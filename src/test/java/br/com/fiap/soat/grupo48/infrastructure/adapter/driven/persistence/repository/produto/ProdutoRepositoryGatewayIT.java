package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.produto;

import br.com.fiap.soat.grupo48.application.commons.valueobject.Nome;
import br.com.fiap.soat.grupo48.application.produto.exception.ProdutoNotFoundException;
import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import br.com.fiap.soat.grupo48.utils.ProdutoHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ProdutoRepositoryGatewayIT {

  @Autowired
  private SpringProdutoRepository springProdutoRepository;

  @Autowired
  private ProdutoRepositoryGateway produtoRepositoryGateway;

  @Nested
  class CadastrarProduto {
    @Test
    void devePermitirCadastrarProduto() throws ProdutoNotFoundException {
      // Arrange
      var produto = ProdutoHelper.gerarProduto();

      // Act
      var produtoCadastrado = produtoRepositoryGateway.salvar(produto);

      // Assert
      assertThat(produtoCadastrado)
          .isInstanceOf(Produto.class)
          .isNotNull();
      assertThat(produtoCadastrado.getCodigo()).isNotNull();

    }

  }

  @Nested
  class BuscarProduto {

    @Test
    void devePermitirBuscarProduto() throws ProdutoNotFoundException {
      // Arrange
      var id = UUID.fromString("fe91ab2c-289b-4023-9f2d-a2e00056d84d");

      // Act
      var produtoEncontrado = produtoRepositoryGateway.buscarPeloCodigo(id);

      // Assert
      assertThat(produtoEncontrado)
          .isInstanceOf(Produto.class)
          .isNotNull();
      assertThat(produtoEncontrado.getCodigo())
          .isNotNull()
          .isEqualTo(id);
      assertThat(produtoEncontrado.getNome())
          .isNotNull()
          .isEqualTo(new Nome("Hambúrguer Bacon"));
      assertThat(produtoEncontrado.getDescricao())
          .isNotNull()
          .isEqualTo("Hambúrguer de carne 150g com bacon e cheddar");
      assertThat(produtoEncontrado.getPreco())
          .isNotNull()
          .isEqualTo(24.0);
      assertThat(produtoEncontrado.getSituacao())
          .isNotNull()
          .isEqualTo(SituacaoProduto.DISPONIVEL);
      assertThat(produtoEncontrado.getCategoria())
          .isNotNull()
          .isEqualTo(Categoria.LANCHE);


    }

    @Test
    void devePermitirBuscarProduto_GerarException_QuandoIdNaoExiste() {
      // Arrange
      var id = UUID.fromString("e5bcbbfd-6e32-4710-8334-ef1195dd7eac");

      // Act & Assert
      assertThatThrownBy(() -> produtoRepositoryGateway.buscarPeloCodigo(id))
          .isInstanceOf(ProdutoNotFoundException.class)
          .hasMessage("Produto não encontrado");

    }

  }

  @Nested
  class AtualizarProduto {
    @Test
    void devePermitirAtualizarProduto() throws ProdutoNotFoundException {
      // Arrange
      var id = UUID.fromString("fe91ab2c-289b-4023-9f2d-a2e00056d84d");

      // Act
      var produtoEncontrado = produtoRepositoryGateway.buscarPeloCodigo(id);
      String testeDeAlteracao = "Teste de alteração";
      produtoEncontrado.setDescricao(testeDeAlteracao);
      produtoRepositoryGateway.salvar(produtoEncontrado);
      //Busca novamente da base para garantir as alterações
      produtoEncontrado = produtoRepositoryGateway.buscarPeloCodigo(id);

      // Assert
      assertThat(produtoEncontrado)
          .isInstanceOf(Produto.class)
          .isNotNull();
      assertThat(produtoEncontrado.getCodigo())
          .isNotNull()
          .isEqualTo(id);
      assertThat(produtoEncontrado.getDescricao())
          .isEqualTo(testeDeAlteracao);
    }
  }

  @Nested
  class ExcluirProduto {

    @Test
    void devePermitirExcluirProduto() throws ProdutoNotFoundException {
      // Arrange
      var id = UUID.fromString("bf385ed8-5c2d-4fd1-b37d-6c141589a4be");

      // Act
      boolean excluired = produtoRepositoryGateway.excluir(id);

      // Assert
      assertThat(excluired).isTrue();
    }

    @Test
    void devePermitirExcluirProduto_GerarException_QuandoIdNaoExiste() {
      // Arrange
      var id = UUID.fromString("0d0dfbe7-c7ec-4b2a-9fde-30e711905ac5");

      // Act & Assert
      assertThatThrownBy(() -> produtoRepositoryGateway.excluir(id))
          .isInstanceOf(ProdutoNotFoundException.class)
          .hasMessage("Não foi possível excluir o produto");
    }
  }

  @Nested
  class ListarProdutos {

    @Test
    void devePermitirListarProdutos() {
      // Act
      var produtos = produtoRepositoryGateway.buscarTodos();

      // Assert
      assertThat(produtos)
          .hasSizeGreaterThan(0);
    }
  }
}
