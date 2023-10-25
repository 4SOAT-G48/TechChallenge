package br.com.fiap.soat.grupo48.application.produto.service;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoServicePort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.ProdutoRepositoryPort;

import java.util.List;

public class ManutecaoProdutoServiceImpl implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepository;

    public ManutecaoProdutoServiceImpl(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> buscarProdutos() {
        List<Produto> produtos = this.produtoRepository.buscarTodos();
        return produtos;
    }

    @Override
    public void criarProduto(Produto produto) {

    }

    @Override
    public void atualizarProduto(Produto produto) {

    }

    @Override
    public void excluirProduto(Produto produto) {

    }
}
