package br.com.fiap.soat.grupo48.application.pagamento.model;

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
public class MetodoPagamento {

    private UUID codigo;

    private String nome;

    private TipoPagamento tipoPagamento;

    private String urlImagem;
}
