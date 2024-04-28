package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.produto;

import br.com.fiap.soat.grupo48.application.produto.exception.ProdutoNotFoundException;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementação de classe para manutenção do produto.
 *
 * @author andrelaus
 */
@Component
public class ProdutoRepositoryGateway implements IProdutoRepositoryGateway {

  private final SpringProdutoRepository springProdutoRepository;

  public ProdutoRepositoryGateway(SpringProdutoRepository springProdutoRepository) {
    this.springProdutoRepository = springProdutoRepository;
  }

  @Override
  public List<Produto> buscarTodos() {
    List<ProdutoEntity> produtoEntity = this.springProdutoRepository.findAll();
    return produtoEntity.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
  }

  @Override
  public Produto buscarPeloCodigo(UUID codigo) throws ProdutoNotFoundException {
    Optional<ProdutoEntity> produtoEntityOptional = this.springProdutoRepository.findById(codigo);
    if (produtoEntityOptional.isEmpty()) {
      throw new ProdutoNotFoundException("Produto não encontrado");
    }
    ProdutoEntity produtoEntity = produtoEntityOptional.get();
    return produtoEntity.toProduto();
  }

  @Override
  public Produto salvar(Produto produto) {
    ProdutoEntity produtoEntity;
    if (Objects.isNull(produto.getCodigo())) {
      produtoEntity = new ProdutoEntity(produto);
    } else {
      produtoEntity = this.springProdutoRepository.findById(produto.getCodigo()).get();
      produtoEntity.atualizar(produto);
    }

    return this.springProdutoRepository.save(produtoEntity).toProduto();
  }

  @Override
  public boolean excluir(UUID codigo) throws ProdutoNotFoundException {
    Optional<ProdutoEntity> produtoEntityOptional = this.springProdutoRepository.findById(codigo);
    if (produtoEntityOptional.isEmpty()) {
      throw new ProdutoNotFoundException("Não foi possível excluir o produto");
    }
    this.springProdutoRepository.deleteById(codigo);
    return true;
  }
}
