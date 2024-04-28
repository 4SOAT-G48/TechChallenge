package br.com.fiap.soat.grupo48.application.produto.port.api;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;

import java.util.List;

public interface IProdutoPedidoEmAndamentoPort {
    List<Produto> buscarProdutosPorCategoria(Categoria categoria);

    List<Produto> buscarProdutosDiponiveisPorCategoria(Categoria categoria);
}
