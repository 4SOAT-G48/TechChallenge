package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagamentoResponse {

    private UUID codigo;
    private MetodoPagamentoResponse metodoPagamento;
    private SituacaoPagamento situacaoPagamento;


}
