package br.com.fiap.soat.grupo48.application.pagamento.port.api;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;

public interface IMetodoPagamentoPort {
    long buscarQuantidade();

    MetodoPagamento salvar(MetodoPagamento metodoPagamento);
}
