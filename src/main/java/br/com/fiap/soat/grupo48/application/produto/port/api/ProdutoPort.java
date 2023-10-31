package br.com.fiap.soat.grupo48.application.produto.port.api;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;

import java.util.List;
import java.util.UUID;

public interface ProdutoPort {
    List<ProdutoDto> buscarProdutos();

    ProdutoDto buscarPeloCodigo(UUID codigo);

    ProdutoDto criarProduto(ProdutoDto produto);

    ProdutoDto atualizarProduto(UUID codigo, ProdutoDto produto);

    void excluirProduto(UUID codigo);
}
