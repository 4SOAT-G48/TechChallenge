package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.pedido.model.PedidoItem;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "pedido_itens")
public class PedidoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigo;
    @ManyToOne
    @JoinColumn(name = "pedido_codigo")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "produto_codigo")
    private ProdutoEntity produto;

    private Integer quantidade;
    private Double precoUnitario;

    private String observacao;

    public PedidoItemEntity() {
    }

    public PedidoItemEntity(PedidoItem pedidoItem) {
        this.atualizar(pedidoItem);
    }

    public PedidoItem toPedidoItem() {
        return new PedidoItem(this.codigo, this.produto.toProduto(), this.quantidade, this.precoUnitario, this.observacao);
    }

    public void atualizar(PedidoItem pedidoItem) {
        this.produto = new ProdutoEntity(pedidoItem.getProduto());
        this.quantidade = pedidoItem.getQuantidade();
        this.precoUnitario = pedidoItem.getPrecoUnitario();
        this.observacao = pedidoItem.getObservacao();
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
}
