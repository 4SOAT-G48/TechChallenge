package br.com.fiap.soat.grupo48.application.produto.service;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoServicePort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.ProdutoRepositoryPort;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
    public Produto buscarPeloCodigo(UUID codigo) {
        return this.produtoRepository.buscarPeloCodigo(codigo);
    }

    @Override
    public void criarProduto(Produto produto) {
        this.produtoRepository.salvar(produto);
    }

    @Override
    public void atualizarProduto(UUID codigo, Produto produto) {
        Produto produtoAtu = this.produtoRepository.buscarPeloCodigo(codigo);
        if (!Objects.isNull(produtoAtu)) {
            produto.setCodigo(codigo);
            this.produtoRepository.salvar(produto);
        }
    }

    @Override
    public void excluirProduto(Produto produto) {
        this.produtoRepository.salvar(produto);
    }
}
