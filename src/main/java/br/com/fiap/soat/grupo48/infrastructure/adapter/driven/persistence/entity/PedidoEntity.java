package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigo;

    @ManyToOne
    @JoinColumn(name = "cliente_codigo")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private SituacaoPedido situacao;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItemEntity> itens = new ArrayList<>();
}
