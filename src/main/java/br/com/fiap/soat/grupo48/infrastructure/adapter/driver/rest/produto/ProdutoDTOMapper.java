package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.valueobject.Imagem;

import java.util.stream.Collectors;

public class ProdutoDTOMapper {
    public Produto toProduto(ProdutoRequest request) {
        return new Produto(request.getCodigo(), request.getNome(), request.getCategoria(), request.getPreco(), request.getDescricao(), request.getSituacao(),
                request.getImagens().stream().map(Imagem::new).collect(Collectors.toList()));
    }

    public ProdutoResponse toReponse(Produto produto) {
        return new ProdutoResponse(produto.getCodigo(),produto.getNome(),produto.getCategoria(),produto.getPreco(),produto.getDescricao(),produto.getSituacao(),produto.getImagens().stream().map(Imagem::url).collect(Collectors.toList()));
    }
}
