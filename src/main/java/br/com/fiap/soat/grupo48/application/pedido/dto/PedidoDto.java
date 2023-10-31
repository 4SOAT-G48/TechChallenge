package br.com.fiap.soat.grupo48.application.pedido.dto;

import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PedidoDto {

    @Getter
    @Setter
    private UUID codigo;

    @Getter
    @Setter
    private String cpfCliente;

    @Getter
    @Setter
    private SituacaoPedido situacao;

    @Getter
    @Setter
    private String numero;

    @Getter
    @Setter
    private List<PedidoItemDto> itens;

    @Getter
    @Setter

    private Double total;

}
