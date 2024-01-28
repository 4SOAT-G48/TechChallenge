package br.com.fiap.soat.grupo48.application.pagamento.port.api;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;

import java.util.List;

public interface IMetodoPagamentoPort {
    long buscarQuantidade();

    List<MetodoPagamento> buscarMetodosPagamentos();

    MetodoPagamento salvar(MetodoPagamento metodoPagamento);
}
