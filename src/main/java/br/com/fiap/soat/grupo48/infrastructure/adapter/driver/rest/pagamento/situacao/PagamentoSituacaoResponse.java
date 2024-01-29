package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.situacao;

import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PagamentoSituacaoResponse {

    private UUID codigo;
    private SituacaoPagamento situacaoPagamento;
}
