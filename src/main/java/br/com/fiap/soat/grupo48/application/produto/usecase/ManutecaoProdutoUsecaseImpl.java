package br.com.fiap.soat.grupo48.application.produto.usecase;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoPort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class ManutecaoProdutoUsecaseImpl implements ProdutoPort {

    private final IProdutoRepositoryGateway produtoRepository;

    public ManutecaoProdutoUsecaseImpl(IProdutoRepositoryGateway produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoDto> buscarProdutos() {
        List<Produto> produtos = this.produtoRepository.buscarTodos();
        List<ProdutoDto> produtoDTOS = produtos.stream().map(Produto::toProdutoDto).collect(Collectors.toList());
        return produtoDTOS;
    }

    @Override
    public ProdutoDto buscarPeloCodigo(UUID codigo) {
        return this.produtoRepository.buscarPeloCodigo(codigo).toProdutoDto();
    }

    @Override
    public ProdutoDto criarProduto(ProdutoDto produtoDto) {
        Produto produto = new Produto(produtoDto);
        return this.produtoRepository.salvar(produto).toProdutoDto();
    }

    @Override
    public ProdutoDto atualizarProduto(UUID codigo, ProdutoDto produtoDto) {
        Produto produtoAtu = this.produtoRepository.buscarPeloCodigo(codigo);
        if (!Objects.isNull(produtoAtu)) {
            produtoAtu.atualiza(produtoDto);
            return this.produtoRepository.salvar(produtoAtu).toProdutoDto();
        } else {
            return null;
        }
    }

    @Override
    public void excluirProduto(UUID codigo) {
        this.produtoRepository.excluir(codigo);
    }
}
