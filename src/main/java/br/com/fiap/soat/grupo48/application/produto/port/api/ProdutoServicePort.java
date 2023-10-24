package br.com.fiap.soat.grupo48.application.produto.port.api;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;

import java.util.List;

public interface ProdutoServicePort {
    List<Produto> buscarProdutos();

    void criarProduto(Produto produto);

    void atualizarProduto(Produto produto);

    void excluirProduto(Produto produto);
}
