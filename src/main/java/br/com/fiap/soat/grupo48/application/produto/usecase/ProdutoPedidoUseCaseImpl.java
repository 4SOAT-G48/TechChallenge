package br.com.fiap.soat.grupo48.application.produto.usecase;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;
import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoPedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.ProdutoPedidoRepositoryPort;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoPedidoUseCaseImpl implements ProdutoPedidoEmAndamentoPort {

    private final ProdutoPedidoRepositoryPort produtoPedidoRepositoryPort;

    public ProdutoPedidoUseCaseImpl(ProdutoPedidoRepositoryPort produtoPedidoRepositoryPort) {
        this.produtoPedidoRepositoryPort = produtoPedidoRepositoryPort;
    }


    @Override
    public List<ProdutoDto> buscarProdutosPorCategoria(Categoria categoria) {
        List<Produto> produtos = this.produtoPedidoRepositoryPort.buscarPorCategoria(categoria);
        return produtos.stream().map(Produto::toProdutoDto).collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDto> buscarProdutosDiponiveisPorCategoria(Categoria categoria) {
        //fixo por produtos disponiveis
        List<Produto> produtos = this.produtoPedidoRepositoryPort.buscarPorCategoriaESituacao(categoria, SituacaoProduto.DISPONIVEL);
        return produtos.stream().map(Produto::toProdutoDto).collect(Collectors.toList());
    }
}
