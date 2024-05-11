package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto;

import br.com.fiap.soat.grupo48.application.produto.exception.ProdutoNotFoundException;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.produto.valueobject.Imagem;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProdutoDTOMapper {

  private final IProdutoRepositoryGateway produtoRepositoryGateway;

  public ProdutoDTOMapper(IProdutoRepositoryGateway produtoRepositoryGateway) {
    this.produtoRepositoryGateway = produtoRepositoryGateway;
  }

  public Produto toProduto(ProdutoRequest request) {
    return new Produto(request.getCodigo(), request.getNome(), request.getCategoria(), request.getPreco(), request.getDescricao(), request.getSituacao(),
        request.getImagens().stream().map(Imagem::new).toList());
  }

  public ProdutoResponse toResponse(Produto produto) {
    return new ProdutoResponse(produto.getCodigo(), produto.getNome().nome(), produto.getCategoria(), produto.getPreco(), produto.getDescricao(), produto.getSituacao(), produto.getImagens().stream().map(Imagem::url).toList());
  }

  public Produto toProduto(ProdutoReferenciaRequest produtoReferenciaRequest) {
    return this.toProduto(produtoReferenciaRequest.getCodigo());
  }

  public Produto toProduto(UUID codigo) {
    try {
      return this.produtoRepositoryGateway.buscarPeloCodigo(codigo);
    } catch (ProdutoNotFoundException e) {
      return null;
    }
  }

  public List<ProdutoResponse> toListResponse(List<Produto> listProduto) {
    return listProduto.stream().map(this::toResponse).toList();
  }

  public Page<ProdutoResponse> toListResponse(@NotNull Page<Produto> produtos) {
    return produtos.map(this::toResponse);
  }
}
