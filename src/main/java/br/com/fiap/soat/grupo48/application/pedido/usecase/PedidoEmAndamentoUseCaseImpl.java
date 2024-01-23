package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.IClienteRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pedido.aggregate.PedidoAggregate;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.port.api.IPedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;

import java.util.Objects;

public class PedidoEmAndamentoUseCaseImpl implements IPedidoEmAndamentoPort {
    private final IPedidoRepositoryGateway pedidoRepositoryGateway;
    private final IClienteRepositoryGateway clienteRepositoryGateway;

    private final IProdutoRepositoryGateway produtoRepositoryGateway;

    public PedidoEmAndamentoUseCaseImpl(IPedidoRepositoryGateway pedidoRepositoryGateway, IClienteRepositoryGateway clienteRepositoryGateway, IProdutoRepositoryGateway produtoRepositoryGateway) {
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
        this.clienteRepositoryGateway = clienteRepositoryGateway;
        this.produtoRepositoryGateway = produtoRepositoryGateway;
    }

    @Override
    public Pedido montaPedido(Pedido pedido,String cpfCliente) {
        Cliente cliente = null;
        if (Objects.nonNull(cpfCliente)) {
            // se tiver cpf no pedido o cliente se identificou
            cliente = this.clienteRepositoryGateway.buscarPeloCpf(cpfCliente);
        }

        PedidoAggregate pedidoAggregate = new PedidoAggregate();
        pedidoAggregate.montaPedido(pedido, cliente);

        pedidoAggregate.getPedido().getItens().forEach(pedidoItem -> {
            Produto produto = this.produtoRepositoryGateway.buscarPeloCodigo(pedidoItem.getProduto().getCodigo());
            pedidoItem.setProduto(produto);

        });

        return this.pedidoRepositoryGateway.salvar(pedidoAggregate.getPedido());
    }

    @Override
    public void efetuaPagamento() {

    }

    @Override
    public Pedido concluiPedido(Pedido pedido) {
        return null;
    }
}
