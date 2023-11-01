package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pedido;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.PedidoRepositoryPort;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public Pedido atualizarSituacao(UUID codigo, SituacaoPedido situacao) {
        Optional<PedidoEntity> byId = this.springPedidoRepository.findById(codigo);
        if (byId.isPresent()) {
            PedidoEntity pedidoEntity = byId.get();
            pedidoEntity.setSituacao(situacao);
            return this.springPedidoRepository.save(pedidoEntity).toPedido();
        } else {
            return null;
        }
    }

    @Override
    public List<Pedido> buscaPedidosPorSituacoes(List<SituacaoPedido> situacoes) {
        List<PedidoEntity> bySituacaoIn = this.springPedidoRepository.findBySituacaoIn(situacoes);
        return bySituacaoIn.stream().map(PedidoEntity::toPedido).collect(Collectors.toList());
    }

    @Override
    public SituacaoPedido buscaSituacaoPedido(UUID codigo) {
        Optional<PedidoEntity> byId = this.springPedidoRepository.findById(codigo);
        if (byId.isPresent()) {
            PedidoEntity pedidoEntity = byId.get();
            return pedidoEntity.getSituacao();
        } else {
            return null;
        }
    }
}
