package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pedido;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.PedidoRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class PedidoRepository implements PedidoRepositoryPort {

    private final SpringPedidoRepository springPedidoRepository;

    public PedidoRepository(SpringPedidoRepository springPedidoRepository) {
        this.springPedidoRepository = springPedidoRepository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        return null;
    }
}
