package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pedido;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.PedidoRepositoryPort;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PedidoRepository implements PedidoRepositoryPort {

    private final SpringPedidoRepository springPedidoRepository;

    public PedidoRepository(SpringPedidoRepository springPedidoRepository) {
        this.springPedidoRepository = springPedidoRepository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        PedidoEntity pedidoEntity;
        if (Objects.isNull(pedido.getCodigo())) {
            pedidoEntity = new PedidoEntity(pedido);
        } else {
            pedidoEntity = this.springPedidoRepository.findById(pedido.getCodigo()).get();
            pedidoEntity.atualizar(pedido);
        }

        return this.springPedidoRepository.save(pedidoEntity).toPedido();
    }
}
