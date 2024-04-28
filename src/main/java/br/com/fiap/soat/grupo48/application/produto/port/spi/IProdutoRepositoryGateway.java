package br.com.fiap.soat.grupo48.application.produto.port.spi;

import br.com.fiap.soat.grupo48.application.produto.exception.ProdutoNotFoundException;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;

import java.util.List;
import java.util.UUID;

public interface IProdutoRepositoryGateway {
  List<Produto> buscarTodos();

  Produto buscarPeloCodigo(UUID codigo) throws ProdutoNotFoundException;

  Produto salvar(Produto produto);

  boolean excluir(UUID codigo) throws ProdutoNotFoundException;
}
