package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "pagamentos")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigo;

    @ManyToOne
    @JoinColumn(name = "metodo_pagamento", nullable = false)
    private MetodoPagamentoEntity metodoPagamento;

    @Column(name = "situacao_pagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private SituacaoPagamento situacaoPagamento;

    @OneToOne(mappedBy = "pagamento")
    private PedidoEntity pedido;
    @Column(name = "data_criacao", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public PagamentoEntity(UUID codigo, MetodoPagamentoEntity metodoPagamento, SituacaoPagamento situacaoPagamento, PedidoEntity pedido) {
        this.codigo = codigo;
        this.metodoPagamento = metodoPagamento;
        this.situacaoPagamento = situacaoPagamento;
        this.pedido = pedido;
    }

    public PagamentoEntity(UUID codigo) {
        this.codigo = codigo;
    }

    public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
        this.situacaoPagamento = situacaoPagamento;
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
