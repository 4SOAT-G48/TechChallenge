package br.com.fiap.soat.grupo48.application.pedido.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PedidoDto {

    @Getter
    @Setter
    private UUID codigo;

    @Getter
    @Setter
    private UUID codigoCliente;

    @Getter
    @Setter
    private List<ItemPedidoDto> itens;

    @Getter
    @Setter

    private BigDecimal total;

}
