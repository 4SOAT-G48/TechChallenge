package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigo;

    @ManyToOne
    @JoinColumn(name = "cliente_codigo", nullable = true)
    private ClienteEntity cliente;

    @Setter
    @Enumerated(EnumType.STRING)
    private SituacaoPedido situacao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItemEntity> itens = new ArrayList<>();

    private String identificacao;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_codigo")
    private PagamentoEntity pagamento;

    @Column(name = "data_criacao", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;


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
        this.identificacao = pedido.getIdentificacao();
    }

    @PrePersist
    public void insereDatas() {
        if (Objects.isNull(this.dataCriacao)) {
            this.dataCriacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
            this.dataAtualizacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
        }
    }

    @PreUpdate
    public void atualizaDataAtualizacao() {
        this.dataAtualizacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }
}
