package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.situacao;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoSituacaoMapper {

    public Pagamento toPagamento(PagamentoSituacaoRequest request) {
        return new Pagamento(request.getCodigo(),null,request.getSituacaoPagamento());
    }
}
