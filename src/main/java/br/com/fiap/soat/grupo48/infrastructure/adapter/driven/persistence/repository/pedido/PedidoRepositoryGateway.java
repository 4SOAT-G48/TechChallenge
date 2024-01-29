package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pedido;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PagamentoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PedidoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento.SpringPagamentoRepository;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido.PedidoDTOMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PedidoRepositoryGateway implements IPedidoRepositoryGateway {

    private final SpringPedidoRepository springPedidoRepository;

    private final PedidoEntityMapper pedidoEntityMapper;

    private final SpringPagamentoRepository springPagamentoRepository;

    public PedidoRepositoryGateway(SpringPedidoRepository springPedidoRepository, PedidoEntityMapper pedidoEntityMapper, SpringPagamentoRepository springPagamentoRepository) {
        this.springPedidoRepository = springPedidoRepository;
        this.pedidoEntityMapper = pedidoEntityMapper;
        this.springPagamentoRepository = springPagamentoRepository;
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
        if (Objects.nonNull(pedido.getPagamento()) && Objects.nonNull(pedido.getPagamento().getCodigo())) {
            Optional<PagamentoEntity> byId = this.springPagamentoRepository.findById(pedido.getPagamento().getCodigo());
            if (byId.isPresent()) {
                pedidoEntity.setPagamento(byId.get());
            }
        }

        Pedido pedidoCriado = this.pedidoEntityMapper.toPedido(this.springPedidoRepository.save(pedidoEntity));
        return pedidoCriado;
    }

    @Override
    public Pedido atualizarSituacao(UUID codigo, SituacaoPedido situacao) {
        Optional<PedidoEntity> byId = this.springPedidoRepository.findById(codigo);
        if (byId.isPresent()) {
            PedidoEntity pedidoEntity = byId.get();
            pedidoEntity.setSituacao(situacao);
            Pedido pedido = this.pedidoEntityMapper.toPedido(this.springPedidoRepository.save(pedidoEntity));
            return pedido;
        } else {
            return null;
        }
    }

    @Override
    public List<Pedido> buscaPedidosPorSituacoes(List<SituacaoPedido> situacoes) {
        List<PedidoEntity> bySituacaoIn = this.springPedidoRepository.findBySituacaoIn(situacoes);
        return bySituacaoIn.stream().map(this.pedidoEntityMapper::toPedido).collect(Collectors.toList());
    }

    @Override
    public List<Pedido> buscaPedidosMostradosMonitorCliente() {
        List<PedidoEntity> pedidosWithoutFinalizados = this.springPedidoRepository.findPedidosWithoutFinalizados();
        return pedidosWithoutFinalizados.stream().map(this.pedidoEntityMapper::toPedido).collect(Collectors.toList());
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
