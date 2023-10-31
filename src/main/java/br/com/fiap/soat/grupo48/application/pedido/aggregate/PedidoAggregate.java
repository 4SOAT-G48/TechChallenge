package br.com.fiap.soat.grupo48.application.pedido.aggregate;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoItemDto;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.PedidoItem;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PedidoAggregate {

    private Pedido pedido;

    public void montaPedido(PedidoDto pedidoDto, Cliente cliente) {
        this.pedidoDtoParaPedido(pedidoDto,cliente);
    }

    private void pedidoDtoParaPedido(PedidoDto pedidoDto, Cliente cliente) {
        this.pedido = new Pedido();
        this.pedido.setCodigo(pedidoDto.getCodigo());
        this.pedido.setSituacao(pedidoDto.getSituacao());
        this.pedido.setCliente(cliente);
        if (Objects.nonNull(pedidoDto.getNumero())
                && !pedidoDto.getNumero().isEmpty()) {
            //TODO: transformar em valueobject e validar
            this.pedido.setNumero(pedidoDto.getNumero());
        }
        this.pedido.setItens(this.itensPedidoDtoParaItensPedido(pedidoDto.getItens()));
    }

    private List<PedidoItem> itensPedidoDtoParaItensPedido(List<PedidoItemDto> itensDto) {
        if (itensDto.isEmpty()) {
            //TODO: erro dependnedo da situacao do pedido
            return null;
        }

        return itensDto.stream().map(this::itemDtoParaItem).collect(Collectors.toList());

    }

    private PedidoItem itemDtoParaItem(PedidoItemDto pedidoItemDto) {
        return new PedidoItem(pedidoItemDto.getCodigo(),
                new Produto(pedidoItemDto.getProduto()),
                pedidoItemDto.getQuantidade(),
                pedidoItemDto.getPrecoUnitario(),
                pedidoItemDto.getObservacao());
    }

}
