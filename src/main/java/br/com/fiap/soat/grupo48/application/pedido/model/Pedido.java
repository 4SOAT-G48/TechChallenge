package br.com.fiap.soat.grupo48.application.pedido.model;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Pedido {
    @Getter
    @Setter
    private UUID codigo;

    @Getter
    @Setter
    private Cliente cliente;

    @Getter
    @Setter
    private SituacaoPedido situacao;

    @Getter
    @Setter
    private List<PedidoItem> itens;
}
