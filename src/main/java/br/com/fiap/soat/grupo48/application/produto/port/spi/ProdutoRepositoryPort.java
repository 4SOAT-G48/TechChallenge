package br.com.fiap.soat.grupo48.application.produto.port.spi;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepositoryPort {
    List<Produto> buscarTodos();

    Produto buscarPeloCodigo(UUID codigo);

    void salvar(Produto produto);

    void excluir(UUID codigo);
}
