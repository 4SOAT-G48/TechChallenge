package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "pagamentos")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigo;

    @ManyToOne
    @JoinColumn(name = "metodo_pagamento" , nullable = false)
    private MetodoPagamentoEntity metodoPagamento;

    @Column(name = "situacao_pagamento",nullable = false)
    @Enumerated(EnumType.STRING)
    private SituacaoPedido situacaoPedido;

    @OneToOne(mappedBy = "pagamento")
    private PedidoEntity pedido;
}
