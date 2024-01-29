package br.com.fiap.soat.grupo48.application.pagamento.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pagamento {

    private UUID codigo;
    private MetodoPagamento metodoPagamento;
    private SituacaoPagamento situacaoPagamento;


}
