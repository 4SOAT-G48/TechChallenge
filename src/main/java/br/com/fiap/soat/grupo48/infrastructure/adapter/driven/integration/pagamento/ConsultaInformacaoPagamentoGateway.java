package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.integration.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IConsultaInformacaoPagamentoIntegartionGateway;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class ConsultaInformacaoPagamentoGateway implements IConsultaInformacaoPagamentoIntegartionGateway {

    @Override
    public Pagamento buscarSituacaoPagamento(TipoPagamento tipoPagamento, String id) {
        Random random = new Random();
        double randomNumber = random.nextDouble();

        UUID codigo_pagamento = UUID.fromString("3855b70e-9e08-4935-833c-3ba7532b4497");
        if (randomNumber <= 0.8) {
            return new Pagamento(codigo_pagamento,null, SituacaoPagamento.APROVADO);
        } else {
            return new Pagamento(codigo_pagamento,null, SituacaoPagamento.REPROVADO);
        }
    }
}
