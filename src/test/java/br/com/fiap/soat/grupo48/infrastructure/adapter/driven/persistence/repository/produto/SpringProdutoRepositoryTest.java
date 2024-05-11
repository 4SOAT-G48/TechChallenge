package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.produto;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ProdutoEntity;
import br.com.fiap.soat.grupo48.utils.ProdutoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SpringProdutoRepositoryTest {

    AutoCloseable openMocks;
    @Mock
    private SpringProdutoRepository springProdutoRepository;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCadastrarProduto() {
        // Arrange
        var produtoEntity = ProdutoHelper.gerarProdutoEntity();

        when(springProdutoRepository.save(any(ProdutoEntity.class)))
                .thenReturn(produtoEntity);

        // Act
        var produtoArmazenado = springProdutoRepository.save(produtoEntity);

        // Assert
        assertThat(produtoArmazenado)
                .isNotNull()
                .isEqualTo(produtoEntity);
        verify(springProdutoRepository, times(1))
                .save(any(ProdutoEntity.class));
    }

    @Test
    void devePermitirBuscarProduto() {
        // Arrange
        var codigo = UUID.randomUUID();
        var produtoEntity = ProdutoHelper.gerarProdutoEntity();
        produtoEntity.setCodigo(codigo);

        when(springProdutoRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(produtoEntity));

        // Act
        var produtoEncontradoOpcional = springProdutoRepository.findById(codigo);

        // Assert
        assertThat(produtoEncontradoOpcional)
                .isPresent()
                .contains(produtoEntity);
        produtoEncontradoOpcional.ifPresent(produtoEncontrado -> {
            assertThat(produtoEncontrado.getCodigo()).isEqualTo(produtoEntity.getCodigo());
            assertThat(produtoEncontrado.getNome()).isEqualTo(produtoEntity.getNome());
            assertThat(produtoEncontrado.getDescricao()).isEqualTo(produtoEntity.getDescricao());
            assertThat(produtoEncontrado.getPreco()).isEqualTo(produtoEntity.getPreco());
            assertThat(produtoEncontrado.getSituacao()).isEqualTo(produtoEntity.getSituacao());
            assertThat(produtoEncontrado.getCategoria()).isEqualTo(produtoEntity.getCategoria());
        });
        verify(springProdutoRepository, times(1))
                .findById(any(UUID.class));

    }

    @Test
    void devePermitirApagarProduto() {
        // Arrange
        var codigo = UUID.randomUUID();
        doNothing().when(springProdutoRepository).deleteById(any(UUID.class));

        // Act
        springProdutoRepository.deleteById(codigo);

        // Assert
        verify(springProdutoRepository, times(1))
                .deleteById(any(UUID.class));
    }

    @Test
    void devePermitirListarProdutos() {
        // Arrange
        var listaProdutos = Arrays.asList(
                ProdutoHelper.gerarProdutoEntity(),
                ProdutoHelper.gerarProdutoEntity());
        when(springProdutoRepository.findAll())
                .thenReturn(listaProdutos);

        // Act
        var listaProdutosEncontrados = springProdutoRepository.findAll();

        // Assert
        assertThat(listaProdutosEncontrados)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrderElementsOf(listaProdutos);
        verify(springProdutoRepository, times(1))
                .findAll();
    }
}
