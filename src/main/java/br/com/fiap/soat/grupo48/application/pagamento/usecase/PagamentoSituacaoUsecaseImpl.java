package br.com.fiap.soat.grupo48.application.pagamento.usecase;

import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.api.IPagamentoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IConsultaInformacaoPagamentoIntegartionGateway;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;

import java.util.UUID;

public class PagamentoSituacaoUsecaseImpl implements IPagamentoSituacaoPort {

    private final IPagamentoRepositoryGateway pagamentoRepositoryGateway;

    private final IPedidoRepositoryGateway pedidoRepositoryGateway;

    private final IConsultaInformacaoPagamentoIntegartionGateway consultaInformacaoPagamentoIntegartionGateway;

    public PagamentoSituacaoUsecaseImpl(IPagamentoRepositoryGateway pagamentoRepositoryGateway, IPedidoRepositoryGateway pedidoRepositoryGateway, IConsultaInformacaoPagamentoIntegartionGateway consultaInformacaoPagamentoIntegartionGateway) {
        this.pagamentoRepositoryGateway = pagamentoRepositoryGateway;
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
        this.consultaInformacaoPagamentoIntegartionGateway = consultaInformacaoPagamentoIntegartionGateway;
    }

    @Override
    public boolean atualizaSituacao(Pagamento pagamento) {
        Pagamento pagamentoAntes = this.pagamentoRepositoryGateway.buscarPeloCodigo(pagamento.getCodigo());
        // se o pagamento já foi atualizado para os estagios finais não deixa atualizar
        if (pagamentoAntes.getSituacaoPagamento() != SituacaoPagamento.APROVADO
                || pagamentoAntes.getSituacaoPagamento() != SituacaoPagamento.REPROVADO) {

            Pagamento pagamentoAtual = this.pagamentoRepositoryGateway.atualizaSituacao(pagamentoAntes.getCodigo(), pagamento.getSituacaoPagamento());

            // se o pagamento foi aprovado
            if (pagamentoAntes.getSituacaoPagamento().equals(SituacaoPagamento.AGUARDANDO_PAGAMENTO)
                    && pagamentoAtual.getSituacaoPagamento().equals(SituacaoPagamento.APROVADO)) {
                Pedido pedido = this.pedidoRepositoryGateway.buscarPedidoPeloPagamento(pagamentoAtual.getCodigo());
                this.pedidoRepositoryGateway.atualizarSituacao(pedido.getCodigo(), SituacaoPedido.RECEBIDO);

                //se o pagamento foi reprovado
            } else if (pagamentoAntes.getSituacaoPagamento().equals(SituacaoPagamento.AGUARDANDO_PAGAMENTO)
                    && pagamentoAtual.getSituacaoPagamento().equals(SituacaoPagamento.REPROVADO)) {
                Pedido pedido = this.pedidoRepositoryGateway.buscarPedidoPeloPagamento(pagamentoAtual.getCodigo());
                //TODO: mudar para cancelado quando puder
                this.pedidoRepositoryGateway.atualizarSituacao(pedido.getCodigo(), SituacaoPedido.FINALIZADO);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Pagamento buscarPagamento(UUID codigo) {
        return this.pagamentoRepositoryGateway.buscarPeloCodigo(codigo);
    }

    @Override
    public void buscarSituacaoFontePagadora(TipoPagamento tipoPagamento, String id) {
        Pagamento pagamento = this.consultaInformacaoPagamentoIntegartionGateway.buscarSituacaoPagamento(tipoPagamento, id);
        this.atualizaSituacao(pagamento);
    }
}
