package br.com.fiap.soat.grupo48.application.pedido.usecase;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.IClienteRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IMetodoPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pedido.exception.MetodoPagamentoInvalidoException;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.port.api.IPedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pedido.valueobject.GeradorDeNumeroSequencial;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;

import java.util.Objects;

public class PedidoEmAndamentoUseCaseImpl implements IPedidoEmAndamentoPort {
    private final IPedidoRepositoryGateway pedidoRepositoryGateway;
    private final IClienteRepositoryGateway clienteRepositoryGateway;

    private final IProdutoRepositoryGateway produtoRepositoryGateway;

    private final IMetodoPagamentoRepositoryGateway metodoPagamentoRepositoryGateway;

    private final IPagamentoRepositoryGateway pagamentoRepositoryGateway;

    public PedidoEmAndamentoUseCaseImpl(IPedidoRepositoryGateway pedidoRepositoryGateway, IClienteRepositoryGateway clienteRepositoryGateway, IProdutoRepositoryGateway produtoRepositoryGateway, IMetodoPagamentoRepositoryGateway metodoPagamentoRepositoryGateway, IPagamentoRepositoryGateway pagamentoRepositoryGateway) {
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
        this.clienteRepositoryGateway = clienteRepositoryGateway;
        this.produtoRepositoryGateway = produtoRepositoryGateway;
        this.metodoPagamentoRepositoryGateway = metodoPagamentoRepositoryGateway;
        this.pagamentoRepositoryGateway = pagamentoRepositoryGateway;
    }

    @Override
    public Pedido montaPedido(Pedido pedido,String cpfCliente) throws MetodoPagamentoInvalidoException {
        Cliente cliente;
        if (Objects.nonNull(cpfCliente)) {
            // se tiver cpf no pedido o cliente se identificou
            cliente = this.clienteRepositoryGateway.buscarPeloCpf(cpfCliente);
            pedido.setCliente(cliente);
        }

        // verficação de informações de pagamento
        if (Objects.nonNull(pedido.getPagamento()) && Objects.isNull(pedido.getPagamento().getCodigo())) {
            // verifica o metodo de pagamento
            if (Objects.isNull(pedido.getPagamento().getMetodoPagamento()) || Objects.isNull(pedido.getPagamento().getMetodoPagamento().getCodigo())) {
                throw new MetodoPagamentoInvalidoException("Método de pagamento não informado ou inválido.");
            } else {
                MetodoPagamento metodoPagamento = this.metodoPagamentoRepositoryGateway.buscarPeloCodigo(pedido.getPagamento().getMetodoPagamento().getCodigo());
                if (Objects.isNull(metodoPagamento)) {
                    throw new MetodoPagamentoInvalidoException("Método de pagamento não exites na nossa base.");
                } else {
                    pedido.getPagamento().setMetodoPagamento(metodoPagamento);
                }
            }
            //TODO: quando mandar para a fonte pagadora mudar novamente para pendente
            //pedido.getPagamento().setSituacaoPagamento(SituacaoPagamento.PENDENTE);
            //Setando como aguardando pagamento diretamente, até implementação das integrações
            pedido.getPagamento().setSituacaoPagamento(SituacaoPagamento.AGUARDANDO_PAGAMENTO);
            pedido.setPagamento( this.pagamentoRepositoryGateway.salvar(pedido.getPagamento()));
        }

        if (Objects.nonNull(pedido.getIdentificacao())
                && !pedido.getIdentificacao().isEmpty()) {
            pedido.setIdentificacao(pedido.getIdentificacao());
        } else {
            pedido.setIdentificacao(GeradorDeNumeroSequencial.getInstance().proximoNumero());
        }

        pedido.getItens().forEach(pedidoItem -> {
            Produto produto = this.produtoRepositoryGateway.buscarPeloCodigo(pedidoItem.getProduto().getCodigo());
            pedidoItem.setProduto(produto);
        });

        return this.pedidoRepositoryGateway.salvar(pedido);
    }

    @Override
    public void efetuaPagamento() {

    }

}
