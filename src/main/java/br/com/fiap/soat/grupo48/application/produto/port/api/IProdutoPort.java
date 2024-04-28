package br.com.fiap.soat.grupo48.application.produto.port.api;

import br.com.fiap.soat.grupo48.application.produto.exception.ProdutoNotFoundException;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProdutoPort {
  List<Produto> buscarProdutos();

  Produto buscarPeloCodigo(UUID codigo) throws ProdutoNotFoundException;

  Produto criarProduto(Produto produto) throws ProdutoNotFoundException;

  Produto atualizarProduto(UUID codigo, Produto produto) throws ProdutoNotFoundException;

  boolean excluirProduto(UUID codigo) throws ProdutoNotFoundException;

  Page<Produto> buscarProdutosPaginados(Pageable pageable);
}
