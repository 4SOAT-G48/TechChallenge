package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.PagamentoCriacaoRequest;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PedidoCriacaoRequest {

    @Getter
    @Setter
    private UUID codigo;

    @Getter
    @Setter
    private String cpfCliente;

    @Getter
    @Setter
    private String situacao;

    @Getter
    @Setter
    private String identificacao;

    @Getter
    @Setter
    private List<PedidoItemRequest> itens;

    @Getter
    @Setter
    private PagamentoCriacaoRequest pagamento;

    @Getter
    @Setter

    private Double total;

}
