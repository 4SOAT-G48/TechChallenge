package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_itens")
public class PedidoItemEntity {
    @ManyToOne
    @JoinColumn(name = "produto_codigo")
    private Produto produto;

    private Integer quantidade;
    private Double precoUnitario;

    private String observacao;
}
