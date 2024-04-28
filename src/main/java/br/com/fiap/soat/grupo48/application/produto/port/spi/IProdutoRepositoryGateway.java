package br.com.fiap.soat.grupo48.application.produto.port.spi;

import br.com.fiap.soat.grupo48.application.produto.exception.ProdutoNotFoundException;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProdutoRepositoryGateway {
  List<Produto> buscarTodos();

  Page<Produto> buscarTodos(Pageable pageable);

  Produto buscarPeloCodigo(UUID codigo) throws ProdutoNotFoundException;

  Produto salvar(Produto produto) throws ProdutoNotFoundException;

  boolean excluir(UUID codigo) throws ProdutoNotFoundException;
}
