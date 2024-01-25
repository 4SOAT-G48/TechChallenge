package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import br.com.fiap.soat.grupo48.application.pedido.port.api.IPedidoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;

import java.util.List;
import java.util.UUID;

public class PedidoSituacaoUseCaseImpl implements IPedidoSituacaoPort {

    private final IPedidoRepositoryGateway pedidoRepositoryGateway;

    public PedidoSituacaoUseCaseImpl(IPedidoRepositoryGateway pedidoRepositoryGateway) {
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
    }

    @Override
    public boolean atualizarSituacao(UUID codigoPedido, SituacaoPedido situacaoPedido) {
        SituacaoPedido situacaoPedidoAtual = this.pedidoRepositoryGateway.buscaSituacaoPedido(codigoPedido);
        if (situacaoPedidoAtual != SituacaoPedido.FINALIZADO) {
            this.pedidoRepositoryGateway.atualizarSituacao(codigoPedido,situacaoPedido);
            return true;
        }
        return false;
    }

    @Override
    public List<Pedido> buscarPedidosPorSituacao(List<SituacaoPedido> situacoes) {
        return this.pedidoRepositoryGateway.buscaPedidosPorSituacoes(situacoes);
    }

    @Override
    public List<Pedido> buscarPedidosMostradosMonitorCliente() {
        return this.pedidoRepositoryGateway.buscaPedidosMostradosMonitorCliente();
    }

}
