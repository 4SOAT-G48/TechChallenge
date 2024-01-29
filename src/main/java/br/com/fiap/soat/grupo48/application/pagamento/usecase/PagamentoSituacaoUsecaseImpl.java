package br.com.fiap.soat.grupo48.application.pagamento.usecase;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.api.IPagamentoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;

import java.util.UUID;

public class PagamentoSituacaoUsecaseImpl implements IPagamentoSituacaoPort {

    private final IPagamentoRepositoryGateway pagamentoRepositoryGateway;

    private final IPedidoRepositoryGateway pedidoRepositoryGateway;

    public PagamentoSituacaoUsecaseImpl(IPagamentoRepositoryGateway pagamentoRepositoryGateway, IPedidoRepositoryGateway pedidoRepositoryGateway) {
        this.pagamentoRepositoryGateway = pagamentoRepositoryGateway;
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
    }

    @Override
    public boolean atualizaSituacao(Pagamento pagamento) {
        Pagamento pagamentoAntes = this.pagamentoRepositoryGateway.buscarPeloCodigo(pagamento.getCodigo());
        if (pagamentoAntes.getSituacaoPagamento()!= SituacaoPagamento.APROVADO
                || pagamentoAntes.getSituacaoPagamento()!=SituacaoPagamento.REPROVADO) {
            this.pagamentoRepositoryGateway.atualizaSituacao(pagamentoAntes.getCodigo(),pagamento.getSituacaoPagamento());
            return true;
        } else {
        return false;
        }
    }

    @Override
    public Pagamento buscarPagamento(UUID codigo) {
        return this.pagamentoRepositoryGateway.buscarPeloCodigo(codigo);
    }
}
