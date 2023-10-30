package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.PedidoRepositoryPort;

public class PedidoEmAndamentoUseCaseImpl implements PedidoEmAndamentoPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;

    public PedidoEmAndamentoUseCaseImpl(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Override
    public PedidoDto montaPedido(PedidoDto pedidoDto) {
        return null;
    }

    @Override
    public void efetuaPagamento() {

    }

    @Override
    public PedidoDto concluiPedido(PedidoDto pedidoDto) {
        return null;
    }
}
