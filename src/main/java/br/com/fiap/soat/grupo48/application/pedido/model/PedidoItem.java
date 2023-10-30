package br.com.fiap.soat.grupo48.application.pedido.model;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;

public class PedidoItem {
    private Produto produto;

    private Integer quantidade;
    private Double precoUnitario;

    private String observacao;
}
