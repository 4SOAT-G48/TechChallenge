package br.com.fiap.soat.grupo48.application.pedido.model;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
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
    private String numero;

    @Getter
    @Setter
    private List<PedidoItem> itens;

    public PedidoDto toPedidoDto() {
        return new PedidoDto(this.codigo,
                            this.cliente.getCpf(),
                            this.situacao,
                            this.numero,
                            this.itens.stream().map(PedidoItem::toPedidoItemDto).collect(Collectors.toList()),
                            this.itens.stream().mapToDouble(PedidoItem::getTotal).sum());
    }
}
