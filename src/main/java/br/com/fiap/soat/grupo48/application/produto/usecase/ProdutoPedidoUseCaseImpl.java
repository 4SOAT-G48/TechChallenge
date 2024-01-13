package br.com.fiap.soat.grupo48.application.produto.usecase;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoPedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoPedidoRepositoryGateway;

import java.util.List;

public class ProdutoPedidoUseCaseImpl implements ProdutoPedidoEmAndamentoPort {

    private final IProdutoPedidoRepositoryGateway IProdutoPedidoRepositoryGateway;

    public ProdutoPedidoUseCaseImpl(IProdutoPedidoRepositoryGateway IProdutoPedidoRepositoryGateway) {
        this.IProdutoPedidoRepositoryGateway = IProdutoPedidoRepositoryGateway;
    }


    @Override
    public List<Produto> buscarProdutosPorCategoria(Categoria categoria) {
        List<Produto> produtos = this.IProdutoPedidoRepositoryGateway.buscarPorCategoria(categoria);
        return produtos;
    }

    @Override
    public List<Produto> buscarProdutosDiponiveisPorCategoria(Categoria categoria) {
        //fixo por produtos disponiveis
        List<Produto> produtos = this.IProdutoPedidoRepositoryGateway.buscarPorCategoriaESituacao(categoria, SituacaoProduto.DISPONIVEL);
        return produtos;
    }
}
