package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagamentoCriacaoRequest {

    private MetodoPagamentoConsultaRequest metodoPagamento;

}
