package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.situacao;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PagamentoSituacaoMapper {

    public Pagamento toPagamento(PagamentoSituacaoRequest request) {
        return new Pagamento(request.getCodigo(),null,request.getSituacaoPagamento());
    }

    public PagamentoSituacaoResponse toPagamentoSituacaoResponse(Pagamento pagamento) {
        return new PagamentoSituacaoResponse(pagamento.getCodigo(),pagamento.getSituacaoPagamento());
    }
}
