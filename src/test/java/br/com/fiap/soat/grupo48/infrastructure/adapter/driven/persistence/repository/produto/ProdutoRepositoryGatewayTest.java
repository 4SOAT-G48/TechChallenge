package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.produto;

import br.com.fiap.soat.grupo48.application.commons.valueobject.Nome;
import br.com.fiap.soat.grupo48.application.produto.exception.ProdutoNotFoundException;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ProdutoEntity;
import br.com.fiap.soat.grupo48.utils.ProdutoHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoRepositoryGatewayTest {

  AutoCloseable mock;
  private ProdutoRepositoryGateway produtoRepositoryGateway;
  @Mock
  private SpringProdutoRepository springProdutoRepository;

  @BeforeEach
  void setUp() {
    mock = MockitoAnnotations.openMocks(this);
    produtoRepositoryGateway = new ProdutoRepositoryGateway(springProdutoRepository);
  }

  @AfterEach
  void tearDown() throws Exception {
    mock.close();
  }

  @Test
  void devePermitirCadastrarProduto() throws ProdutoNotFoundException {
    // Arrange
    var produto = ProdutoHelper.gerarProduto();

    when(springProdutoRepository.save(any(ProdutoEntity.class)))
        // o código é gerado pelo banco de dados
        .thenAnswer(i -> {
          ProdutoEntity produtoEntity = (ProdutoEntity) i.getArguments()[0];
          produtoEntity.setCodigo(UUID.randomUUID());
          return produtoEntity;
        });


    // Act
    var produtoCadastrado = produtoRepositoryGateway.salvar(produto);

    // Assert
    assertThat(produtoCadastrado)
        .isInstanceOf(Produto.class)
        .isNotNull();

    //antes de cadastrar não tem código ainda
    assertThat(produto.getCodigo()).isNull();
    //o código vai ser gerado pelo serviço
    assertThat(produtoCadastrado.getCodigo()).isNotNull();
    assertThat(produtoCadastrado.getNome()).isEqualTo(produto.getNome());
    assertThat(produtoCadastrado.getDescricao()).isEqualTo(produto.getDescricao());
    assertThat(produtoCadastrado.getPreco()).isEqualTo(produto.getPreco());
    assertThat(produtoCadastrado.getSituacao()).isEqualTo(produto.getSituacao());
    assertThat(produtoCadastrado.getCategoria()).isEqualTo(produto.getCategoria());

    verify(springProdutoRepository, times(1)).save(any(ProdutoEntity.class));

  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  void devePermitirBuscarProduto() throws ProdutoNotFoundException {
    // Arrange
    var id = UUID.randomUUID();
    var produto = ProdutoHelper.gerarProduto();
    produto.setCodigo(id);

    when(springProdutoRepository.findById(id))
        .thenReturn(java.util.Optional.of(new ProdutoEntity(produto)));

    // Act
    var produtoEncontrado = produtoRepositoryGateway.buscarPeloCodigo(id);

    // Assert
    assertThat(produtoEncontrado).isEqualTo(produto);
    verify(springProdutoRepository, times(1)).findById(any(UUID.class));

  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Description("Valida o cenário de exeção ao efetuar uma busca do produto quando o ID não existir")
  void devePermitirBuscarProduto_GerarException_QuandoIdNaoExiste() {

    // Arrange
    var id = UUID.randomUUID();

    when(springProdutoRepository.findById(id))
        .thenReturn(Optional.empty());

    // Act

    // Assert
    assertThatThrownBy(() -> produtoRepositoryGateway.buscarPeloCodigo(id))
        .isInstanceOf(ProdutoNotFoundException.class)
        .hasMessage("Produto não encontrado");
    verify(springProdutoRepository, times(1)).findById(any(UUID.class));
  }

  @Test
  void devePermitirAtualizarProduto() throws ProdutoNotFoundException {
    // Arrange
    var id = UUID.randomUUID();
    var produtoAntigo = ProdutoHelper.gerarProduto();
    produtoAntigo.setCodigo(id);
    // clonar o produto antigo para não alterar o original
    var produtoAtualizado = ProdutoHelper.gerarProduto();
    produtoAtualizado.setCodigo(id);
    produtoAtualizado.setNome(new Nome("Hambúrger"));

    when(springProdutoRepository.findById(id))
        .thenReturn(Optional.of(new ProdutoEntity(produtoAntigo)));
    when(springProdutoRepository.save(any(ProdutoEntity.class)))
        .thenReturn(new ProdutoEntity(produtoAtualizado));


    // Act
    var produtoEncontrado = produtoRepositoryGateway.salvar(produtoAtualizado);

    // Assert
    assertThat(produtoEncontrado)
        .isNotEqualTo(produtoAntigo)
        .isEqualTo(produtoAtualizado);
    verify(springProdutoRepository, times(1)).findById(id);
    verify(springProdutoRepository, times(1)).save(any(ProdutoEntity.class));

  }

  @Test
  void devePermitirExcluirProduto() throws ProdutoNotFoundException {
    // Arrange
    var id = UUID.randomUUID();
    var produto = ProdutoHelper.gerarProduto();
    produto.setCodigo(id);
    when(springProdutoRepository.findById(id))
        .thenReturn(Optional.of(new ProdutoEntity(produto)));
    doNothing().when(springProdutoRepository).deleteById(any(UUID.class));

    // Act
    boolean excluired = produtoRepositoryGateway.excluir(id);

    // Assert
    assertThat(excluired).isTrue();
    verify(springProdutoRepository, times(1)).findById(id);
    verify(springProdutoRepository, times(1)).deleteById(id);
  }

  @Test
  void devePermitirExcluirProduto_GerarException_QuandoIdNaoExiste() {

    // Arrange
    var id = UUID.randomUUID();

    when(springProdutoRepository.findById(id))
        .thenReturn(Optional.empty());

    // Act

    // Assert
    assertThatThrownBy(() -> produtoRepositoryGateway.excluir(id))
        .isInstanceOf(ProdutoNotFoundException.class)
        .hasMessage("Não foi possível excluir o produto");
    verify(springProdutoRepository, times(1)).findById(any(UUID.class));
    verify(springProdutoRepository, never()).deleteById(any(UUID.class));
  }


  @Test
  void devePermitirListarProdutos() {
    // Arrange
    var listaProdutos = Arrays.asList(
        ProdutoHelper.gerarProdutoEntity(),
        ProdutoHelper.gerarProdutoEntity());
    when(springProdutoRepository.findAll())
        .thenReturn(listaProdutos);
    List<Produto> produtos = listaProdutos.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());

    // Act
    var listaProdutosEncontrados = produtoRepositoryGateway.buscarTodos();

    // Assert
    assertThat(listaProdutosEncontrados)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .containsExactlyInAnyOrderElementsOf(produtos);
    verify(springProdutoRepository, times(1))
        .findAll();
  }
}
