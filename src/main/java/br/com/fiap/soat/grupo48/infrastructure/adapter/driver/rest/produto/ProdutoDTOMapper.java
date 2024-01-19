package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto;

import br.com.fiap.soat.grupo48.application.commons.exception.NomeException;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.valueobject.Imagem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoDTOMapper {
    public Produto toProduto(ProdutoRequest request) throws NomeException {
        return new Produto(request.getCodigo(), request.getNome(), request.getCategoria(), request.getPreco(), request.getDescricao(), request.getSituacao(),
                request.getImagens().stream().map(Imagem::new).collect(Collectors.toList()));
    }
    public ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(produto.getCodigo(),produto.getNome().nome(),produto.getCategoria(),produto.getPreco(),produto.getDescricao(),produto.getSituacao(),produto.getImagens().stream().map(Imagem::url).collect(Collectors.toList()));
    }

    public List<ProdutoResponse> toListResponse(List<Produto> listProduto) {
        return listProduto.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
