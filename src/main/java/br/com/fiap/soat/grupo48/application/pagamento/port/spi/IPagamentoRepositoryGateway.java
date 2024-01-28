package br.com.fiap.soat.grupo48.application.pagamento.port.spi;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;

public interface IPagamentoRepositoryGateway {

    Pagamento salvar(Pagamento pagamento);
}
