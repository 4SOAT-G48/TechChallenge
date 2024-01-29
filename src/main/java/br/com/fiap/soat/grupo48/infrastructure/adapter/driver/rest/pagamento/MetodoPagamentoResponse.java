package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Quais as formas de pagamento o cliente pode usar para pagar pelo seu pedido
 */

@AllArgsConstructor
@Getter
@Setter
public class MetodoPagamentoResponse {

    private UUID codigo;

    private String nome;

    private TipoPagamento tipoPagamento;

    private String urlImagem;
}
