package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoSituacaoDto;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.PedidoRepositoryPort;

import java.util.List;

public class PedidoSituacaoUseCaseImpl implements PedidoSituacaoPort {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    public PedidoSituacaoUseCaseImpl(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Override
    public boolean atualizarSituacao(PedidoSituacaoDto pedido) {
        SituacaoPedido situacaoPedido = this.pedidoRepositoryPort.buscaSituacaoPedido(pedido.getCodigo());
        if (situacaoPedido != SituacaoPedido.FINALIZADO) {
            this.pedidoRepositoryPort.atualizarSituacao(pedido.getCodigo(),pedido.getSituacao());
            return true;
        }
        return false;
    }

    @Override
    public List<PedidoDto> buscarPedidosPorSituacao(List<SituacaoPedido> situacoes) {
        //return this.pedidoRepositoryPort.buscaPedidosPorSituacoes(situacoes);
        return null;
    }

}
