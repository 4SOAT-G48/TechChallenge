package br.com.fiap.soat.grupo48.application.produto.usecase;

import br.com.fiap.soat.grupo48.application.produto.exception.ProdutoNotFoundException;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.api.IProdutoPort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ManutecaoProdutoUsecaseImpl implements IProdutoPort {

  private final IProdutoRepositoryGateway produtoRepository;

  public ManutecaoProdutoUsecaseImpl(IProdutoRepositoryGateway produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  @Override
  public List<Produto> buscarProdutos() {
    return this.produtoRepository.buscarTodos();
  }

  @Override
  public Produto buscarPeloCodigo(UUID codigo) throws ProdutoNotFoundException {
    return this.produtoRepository.buscarPeloCodigo(codigo);
  }

  @Override
  public Produto criarProduto(Produto produto) throws ProdutoNotFoundException {
    return this.produtoRepository.salvar(produto);
  }

  @Override
  public Produto atualizarProduto(UUID codigo, Produto produto) throws ProdutoNotFoundException {
    Produto produtoAtu = this.produtoRepository.buscarPeloCodigo(codigo);
    if (Objects.nonNull(produtoAtu) && produto.getCodigo().equals(codigo)) {
      produtoAtu.atualiza(produto);
      return this.produtoRepository.salvar(produtoAtu);
    } else {
      throw new ProdutoNotFoundException("Produto não apresenta o ID correto");
    }
  }

  @Override
  public boolean excluirProduto(UUID codigo) throws ProdutoNotFoundException {
    Produto produtoASerExcluido = this.produtoRepository.buscarPeloCodigo(codigo);
    if (Objects.nonNull(produtoASerExcluido)) {
      return this.produtoRepository.excluir(codigo);
    } else {
      throw new ProdutoNotFoundException("Produto não apresenta o ID correto");
    }
  }

  /**
   * @param pageable
   * @return
   */
  @Override
  public Page<Produto> buscarProdutosPaginados(Pageable pageable) {

    return this.produtoRepository.buscarTodos(pageable);
  }


}
