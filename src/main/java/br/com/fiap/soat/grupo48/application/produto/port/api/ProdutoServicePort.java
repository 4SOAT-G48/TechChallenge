package br.com.fiap.soat.grupo48.application.produto.port.api;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoServicePort {
    List<Produto> buscarProdutos();

    Produto buscarPeloCodigo(UUID codigo);

    void criarProduto(Produto produto);

    void atualizarProduto(UUID codigo, Produto produto);

    void excluirProduto(Produto produto);
}
