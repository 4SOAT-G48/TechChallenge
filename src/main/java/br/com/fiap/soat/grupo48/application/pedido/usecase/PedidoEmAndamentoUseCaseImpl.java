package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.ClienteRepositoryPort;
import br.com.fiap.soat.grupo48.application.pedido.aggregate.PedidoAggregate;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.PedidoRepositoryPort;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.spi.ProdutoRepositoryPort;

import java.util.Objects;

public class PedidoEmAndamentoUseCaseImpl implements PedidoEmAndamentoPort {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public PedidoEmAndamentoUseCaseImpl(PedidoRepositoryPort pedidoRepositoryPort, ClienteRepositoryPort clienteRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public PedidoDto montaPedido(PedidoDto pedidoDto) {
        Cliente cliente = null;
        if (Objects.nonNull(pedidoDto.getCpfCliente())) {
            // se tiver cpf no pedido o cliente se identificou
            cliente = this.clienteRepositoryPort.buscarPeloCpf(pedidoDto.getCpfCliente());
        }

        PedidoAggregate pedidoAggregate = new PedidoAggregate();
        pedidoAggregate.montaPedido(pedidoDto, cliente);

        pedidoAggregate.getPedido().getItens().forEach(pedidoItem -> {
            Produto produto = this.produtoRepositoryPort.buscarPeloCodigo(pedidoItem.getProduto().getCodigo());
            pedidoItem.setProduto(produto);

        });

        return this.pedidoRepositoryPort.salvar(pedidoAggregate.getPedido()).toPedidoDto();
    }

    @Override
    public void efetuaPagamento() {

    }

    @Override
    public PedidoDto concluiPedido(PedidoDto pedidoDto) {
        return null;
    }
}
