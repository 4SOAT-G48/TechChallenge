package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.IClienteRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pedido.aggregate.PedidoAggregate;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;

import java.util.Objects;

public class PedidoEmAndamentoUseCaseImpl implements PedidoEmAndamentoPort {
    private final IPedidoRepositoryGateway IPedidoRepositoryGateway;
    private final IClienteRepositoryGateway IClienteRepositoryGateway;

    private final IProdutoRepositoryGateway IProdutoRepositoryGateway;

    public PedidoEmAndamentoUseCaseImpl(IPedidoRepositoryGateway IPedidoRepositoryGateway, IClienteRepositoryGateway IClienteRepositoryGateway, IProdutoRepositoryGateway IProdutoRepositoryGateway) {
        this.IPedidoRepositoryGateway = IPedidoRepositoryGateway;
        this.IClienteRepositoryGateway = IClienteRepositoryGateway;
        this.IProdutoRepositoryGateway = IProdutoRepositoryGateway;
    }

    @Override
    public PedidoDto montaPedido(PedidoDto pedidoDto) {
        Cliente cliente = null;
        if (Objects.nonNull(pedidoDto.getCpfCliente())) {
            // se tiver cpf no pedido o cliente se identificou
            cliente = this.IClienteRepositoryGateway.buscarPeloCpf(pedidoDto.getCpfCliente());
        }

        PedidoAggregate pedidoAggregate = new PedidoAggregate();
        pedidoAggregate.montaPedido(pedidoDto, cliente);

        pedidoAggregate.getPedido().getItens().forEach(pedidoItem -> {
            Produto produto = this.IProdutoRepositoryGateway.buscarPeloCodigo(pedidoItem.getProduto().getCodigo());
            pedidoItem.setProduto(produto);

        });

        return this.IPedidoRepositoryGateway.salvar(pedidoAggregate.getPedido()).toPedidoDto();
    }

    @Override
    public void efetuaPagamento() {

    }

    @Override
    public PedidoDto concluiPedido(PedidoDto pedidoDto) {
        return null;
    }
}
