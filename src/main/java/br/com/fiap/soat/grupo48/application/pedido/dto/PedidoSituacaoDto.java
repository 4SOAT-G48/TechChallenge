package br.com.fiap.soat.grupo48.application.pedido.dto;

import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PedidoSituacaoDto {

    private UUID codigo;

    private SituacaoPedido situacao;
}
