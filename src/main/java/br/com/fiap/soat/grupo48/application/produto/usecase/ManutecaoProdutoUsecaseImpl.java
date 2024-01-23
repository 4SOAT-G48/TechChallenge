package br.com.fiap.soat.grupo48.application.produto.usecase;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.api.IProdutoPort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ManutecaoProdutoUsecaseImpl implements IProdutoPort {

    private final IProdutoRepositoryGateway produtoRepository;

    public ManutecaoProdutoUsecaseImpl(IProdutoRepositoryGateway produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> buscarProdutos() {
        return this.produtoRepository.buscarTodos();
    }

    @Override
    public Produto buscarPeloCodigo(UUID codigo) {
        return this.produtoRepository.buscarPeloCodigo(codigo);
    }

    @Override
    public Produto criarProduto(Produto produto) {
        return this.produtoRepository.salvar(produto);
    }

    @Override
    public Produto atualizarProduto(UUID codigo, Produto produto) {
        Produto produtoAtu = this.produtoRepository.buscarPeloCodigo(codigo);
        if (!Objects.isNull(produtoAtu)) {
            produtoAtu.atualiza(produto);
            return this.produtoRepository.salvar(produtoAtu);
        } else {
            return null;
        }
    }

    @Override
    public void excluirProduto(UUID codigo) {
        this.produtoRepository.excluir(codigo);
    }
}
