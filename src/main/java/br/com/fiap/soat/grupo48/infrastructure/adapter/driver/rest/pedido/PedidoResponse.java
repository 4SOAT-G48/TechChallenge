package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.PagamentoResponse;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido.situacao.SituacaoPedidoResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PedidoResponse {
    @Getter
    @Setter
    private UUID codigo;

    @Getter
    @Setter
    private Cliente cliente;

    @Getter
    @Setter
    private SituacaoPedidoResponse situacao;

    @Getter
    @Setter
    private String identificacao;

    @Getter
    @Setter
    private PagamentoResponse pagamento;

    @Getter
    @Setter
    private List<PedidoItemResponse> itens;

}
