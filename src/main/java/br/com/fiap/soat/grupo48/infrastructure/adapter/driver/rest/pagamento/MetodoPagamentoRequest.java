package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Quais as formas de pagamento o cliente pode usar para pagar pelo seu pedido
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MetodoPagamentoRequest {

    private UUID codigo;

    private String nome;

    private TipoPagamento tipoPagamento;

    private String urlImagem;
}
