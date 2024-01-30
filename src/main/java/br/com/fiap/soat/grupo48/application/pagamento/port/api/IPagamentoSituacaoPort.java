package br.com.fiap.soat.grupo48.application.pagamento.port.api;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;

import java.util.UUID;

public interface IPagamentoSituacaoPort {

    boolean atualizaSituacao(Pagamento pagamento);

    Pagamento buscarPagamento(UUID codigo);

    void buscarSituacaoFontePagadora(TipoPagamento tipoPagamento, String id);
}
