package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.produto;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ProdutoEntity;
import br.com.fiap.soat.grupo48.utils.ProdutoHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class SpringProdutoRepositoryIT {

  @Autowired
  private SpringProdutoRepository springProdutoRepository;

  @Test
  void devePermitirCriarTabelaProduto() {
    var totalDeRegistros = springProdutoRepository.count();
    assertThat(totalDeRegistros).isPositive();
  }

  @Test
  void devePermitirCadastrarProduto() {
    // Arrange
    var produtoEntity = ProdutoHelper.gerarProdutoEntity();

    // Act
    var produtoArmazenado = springProdutoRepository.save(produtoEntity);

    // Assert
    assertThat(produtoArmazenado)
        .isInstanceOf(ProdutoEntity.class)
        .isNotNull();
    assertThat(produtoArmazenado.getCodigo()).isEqualTo(produtoEntity.getCodigo());
    assertThat(produtoArmazenado.getNome()).isEqualTo(produtoEntity.getNome());
    assertThat(produtoArmazenado.getDescricao()).isEqualTo(produtoEntity.getDescricao());
    assertThat(produtoArmazenado.getPreco()).isEqualTo(produtoEntity.getPreco());
    assertThat(produtoArmazenado.getSituacao()).isEqualTo(produtoEntity.getSituacao());
    assertThat(produtoArmazenado.getCategoria()).isEqualTo(produtoEntity.getCategoria());
  }

  @Test
  void devePermitirBuscarProduto() {
    // Arrange
    var codigo = UUID.fromString("69e3f2a1-ee20-4c4c-a3c5-87c85fe4efa8");

    // Act
    var produtoEncontradoOpicional = this.springProdutoRepository.findById(codigo);

    // Assert
    assertThat(produtoEncontradoOpicional)
        .isPresent();
    assertThat(produtoEncontradoOpicional.get())
        .isInstanceOf(ProdutoEntity.class);
    produtoEncontradoOpicional.ifPresent(produtoEntityEncontrado -> {
      assertThat(produtoEntityEncontrado.getCodigo()).isEqualTo(codigo);

    });
  }

  @Test
  void devePermitirApagarProduto() {
    // Arrange
    var codigo = UUID.fromString("3c2bea4f-7687-4e0a-8c3d-9153fb4eb639");

    // Act
    this.springProdutoRepository.deleteById(codigo);

    // Assert
    var produtoEncontradoOpicional = this.springProdutoRepository.findById(codigo);
    assertThat(produtoEncontradoOpicional)
        .isNotPresent();
  }

  @Test
  void devePermitirListarProdutos() {
    // Act
    var resultadosObtidos = this.springProdutoRepository.findAll();

    // Assert
    assertThat(resultadosObtidos)
        .hasSizeGreaterThan(0);
  }

}
