package br.com.fiap.soat.grupo48.application.produto.port.api;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;

import java.util.List;
import java.util.UUID;

public interface IProdutoPort {
    List<Produto> buscarProdutos();

    Produto buscarPeloCodigo(UUID codigo);

    Produto criarProduto(Produto produto);

    Produto atualizarProduto(UUID codigo, Produto produto);

    void excluirProduto(UUID codigo);
}
