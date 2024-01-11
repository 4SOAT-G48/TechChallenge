package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoSituacaoDto;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoSituacaoUseCaseImpl implements PedidoSituacaoPort {

    private final IPedidoRepositoryGateway IPedidoRepositoryGateway;

    public PedidoSituacaoUseCaseImpl(IPedidoRepositoryGateway IPedidoRepositoryGateway) {
        this.IPedidoRepositoryGateway = IPedidoRepositoryGateway;
    }

    @Override
    public boolean atualizarSituacao(PedidoSituacaoDto pedido) {
        SituacaoPedido situacaoPedido = this.IPedidoRepositoryGateway.buscaSituacaoPedido(pedido.getCodigo());
        if (situacaoPedido != SituacaoPedido.FINALIZADO) {
            this.IPedidoRepositoryGateway.atualizarSituacao(pedido.getCodigo(),pedido.getSituacao());
            return true;
        }
        return false;
    }

    @Override
    public List<PedidoDto> buscarPedidosPorSituacao(List<SituacaoPedido> situacoes) {
        List<Pedido> pedidos = this.IPedidoRepositoryGateway.buscaPedidosPorSituacoes(situacoes);
        return pedidos.stream().map(Pedido::toPedidoDto).collect(Collectors.toList());
    }

}
