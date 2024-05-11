package br.com.fiap.soat.grupo48.application.produto.exception;

import br.com.fiap.soat.grupo48.application.commons.exception.ApplicationException;

public class ProdutoNotFoundException extends ApplicationException {
  public ProdutoNotFoundException(String mensagem) {
    super(mensagem);
  }
}
