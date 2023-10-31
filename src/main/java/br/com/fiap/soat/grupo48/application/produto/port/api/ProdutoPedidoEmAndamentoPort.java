package br.com.fiap.soat.grupo48.application.produto.port.api;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;
import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;

import java.util.List;

public interface ProdutoPedidoEmAndamentoPort {
    List<ProdutoDto> buscarProdutosPorCategoria(Categoria categoria);
    List<ProdutoDto> buscarProdutosDiponiveisPorCategoria(Categoria categoria);
}
