package br.com.fiap.soat.grupo48.application.pagamento.port.api;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;

public interface IPagamentoSituacaoPort {

    boolean atualizaSituacao(Pagamento pagamento);
}
