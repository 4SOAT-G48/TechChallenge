package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.integration.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IConsultaInformacaoPagamentoIntegartionGateway;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PagamentoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento.SpringPagamentoRepository;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pedido.SpringPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class ConsultaInformacaoPagamentoGateway implements IConsultaInformacaoPagamentoIntegartionGateway {

    @Autowired
    private SpringPagamentoRepository springPagamentoRepository;

    @Override
    public Pagamento buscarSituacaoPagamento(TipoPagamento tipoPagamento, String id) {
        Random random = new Random();
        double randomNumber = random.nextDouble();

        //busca todos os pagamentos que estão aguardando pagamento
        List<PagamentoEntity> bySituacaoPagamento = this.springPagamentoRepository.findBySituacaoPagamento(SituacaoPagamento.AGUARDANDO_PAGAMENTO);
        //escolhe um randomicamente
        PagamentoEntity pagamento = bySituacaoPagamento.get(random.nextInt(bySituacaoPagamento.size()));
        UUID codigo_pagamento = pagamento.getCodigo();
        if (randomNumber <= 0.8) {
            return new Pagamento(codigo_pagamento,null, SituacaoPagamento.APROVADO);
        } else {
            return new Pagamento(codigo_pagamento,null, SituacaoPagamento.REPROVADO);
        }
    }
}
