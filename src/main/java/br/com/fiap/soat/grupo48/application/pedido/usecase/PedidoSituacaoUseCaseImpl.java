package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoSituacaoDto;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import br.com.fiap.soat.grupo48.application.pedido.port.api.IPedidoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;

import java.util.List;

public class PedidoSituacaoUseCaseImpl implements IPedidoSituacaoPort {

    private final IPedidoRepositoryGateway pedidoRepositoryGateway;

    public PedidoSituacaoUseCaseImpl(IPedidoRepositoryGateway pedidoRepositoryGateway) {
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
    }

    @Override
    public boolean atualizarSituacao(PedidoSituacaoDto pedido) {
        SituacaoPedido situacaoPedido = this.pedidoRepositoryGateway.buscaSituacaoPedido(pedido.getCodigo());
        if (situacaoPedido != SituacaoPedido.FINALIZADO) {
            this.pedidoRepositoryGateway.atualizarSituacao(pedido.getCodigo(),pedido.getSituacao());
            return true;
        }
        return false;
    }

    @Override
    public List<Pedido> buscarPedidosPorSituacao(List<SituacaoPedido> situacoes) {
        List<Pedido> pedidos = this.pedidoRepositoryGateway.buscaPedidosPorSituacoes(situacoes);
        return pedidos;
    }

}
