package br.com.fiap.soat.grupo48.application.pedido.port.spi;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;

import java.util.List;
import java.util.UUID;

public interface IPedidoRepositoryGateway {

    Pedido salvar(Pedido pedido);

    Pedido atualizarSituacao(UUID codigo, SituacaoPedido situacao);

    List<Pedido> buscaPedidosPorSituacoes(List<SituacaoPedido> situacoes);

    List<Pedido> buscaPedidosMostradosMonitorCliente();

    SituacaoPedido buscaSituacaoPedido(UUID codigo);

    Pedido buscarPedidoPeloPagamento(UUID codigoPagamento);
}
