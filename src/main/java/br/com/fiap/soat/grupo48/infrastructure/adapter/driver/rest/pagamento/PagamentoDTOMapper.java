package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PagamentoDTOMapper {

    public Pagamento toPagamento(PagamentoRequest request) {
        MetodoPagamento metodoPagamento = this.toMetodoPagamento(request.getMetodoPagamento());
        return new Pagamento(request.getCodigo(), metodoPagamento, request.getSituacaoPagamento());
    }

    public MetodoPagamento toMetodoPagamento(MetodoPagamentoRequest request) {
        return new MetodoPagamento(request.getCodigo(), request.getNome(), request.getTipoPagamento(), request.getUrlImagem());
    }

    public Pagamento toPagamentoCriacao(PagamentoCriacaoRequest request) {
        MetodoPagamento metodoPagamento = this.toMetodoPagamentoCriacao(request.getMetodoPagamento());
        return new Pagamento(null, metodoPagamento, null);
    }

    public MetodoPagamento toMetodoPagamentoCriacao(MetodoPagamentoConsultaRequest request) {
        if (Objects.isNull(request)) {
            return null;
        }
        return new MetodoPagamento(request.getCodigo(), null, null, null);
    }
}
