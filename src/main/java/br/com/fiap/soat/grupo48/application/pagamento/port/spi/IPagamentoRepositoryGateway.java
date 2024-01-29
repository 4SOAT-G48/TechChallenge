package br.com.fiap.soat.grupo48.application.pagamento.port.spi;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;

import java.util.UUID;

public interface IPagamentoRepositoryGateway {

    Pagamento salvar(Pagamento pagamento);

    Pagamento buscarPeloCodigo(UUID codigo);

    Pagamento atualizaSituacao(UUID codigo, SituacaoPagamento situacaoPagamento);
}
