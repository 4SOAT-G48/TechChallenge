package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pedido;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.PedidoItem;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PedidoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PedidoItemEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento.PagamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoEntityMapper {

    private final PagamentoMapper pagamentoMapper;

    @Autowired
    public PedidoEntityMapper(PagamentoMapper pagamentoMapper) {
        this.pagamentoMapper = pagamentoMapper;
    }


    public Pedido toPedido(PedidoEntity pedidoEntity) {
        return new Pedido(pedidoEntity.getCodigo(),pedidoEntity.getCliente().toCliente(),
                pedidoEntity.getSituacao(),pedidoEntity.getIdentificacao(),
                pedidoEntity.getItens().stream().map(this::toPedidoItem).collect(Collectors.toList()),
                this.pagamentoMapper.toPagamento(pedidoEntity.getPagamento()),
                pedidoEntity.getDataCriacao(), pedidoEntity.getDataAtualizacao());
    }

    private PedidoItem toPedidoItem(PedidoItemEntity pedidoItemEntity) {
        return new PedidoItem(pedidoItemEntity.getCodigo(),pedidoItemEntity.getProduto().toProduto(),
                pedidoItemEntity.getQuantidade(),pedidoItemEntity.getPrecoUnitario(),pedidoItemEntity.getObservacao());
    }
}
