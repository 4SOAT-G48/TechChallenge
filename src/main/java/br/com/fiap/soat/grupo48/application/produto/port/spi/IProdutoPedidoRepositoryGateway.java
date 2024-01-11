package br.com.fiap.soat.grupo48.application.produto.port.spi;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;

import java.util.List;

public interface IProdutoPedidoRepositoryGateway {
    List<Produto> buscarPorCategoria(Categoria categoria);

    List<Produto> buscarPorCategoriaESituacao(Categoria categoria, SituacaoProduto situacao);
}
