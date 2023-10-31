package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigo;

    @ManyToOne
    @JoinColumn(name = "cliente_codigo", nullable = true)
    private ClienteEntity cliente;

    @Enumerated(EnumType.STRING)
    private SituacaoPedido situacao;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItemEntity> itens = new ArrayList<>();

    private String numero;

    public PedidoEntity() {
    }

    public PedidoEntity(Pedido pedido) {
        this.atualizar(pedido);
    }

    public void atualizar(Pedido pedido) {
        if (Objects.nonNull(pedido.getCliente())) {
            this.cliente = new ClienteEntity(pedido.getCliente());
        }
        this.situacao = pedido.getSituacao();
        if (Objects.nonNull(pedido.getItens())) {
            this.itens = pedido.getItens().stream().map(pedidoItem -> {
                PedidoItemEntity pedidoItemEntity = new PedidoItemEntity(pedidoItem);
                pedidoItemEntity.setPedido(this);
                return pedidoItemEntity;
            }).collect(Collectors.toList());
        }
        this.situacao = pedido.getSituacao();
    }

    public Pedido toPedido() {
        return new Pedido(this.codigo,this.cliente.toCliente(),this.situacao,this.numero,
                this.itens.stream().map(PedidoItemEntity::toPedidoItem).collect(Collectors.toList()));
    }

    public void setSituacao(SituacaoPedido situacao) {
        this.situacao = situacao;
    }

    public SituacaoPedido getSituacao() {
        return situacao;
    }
}
