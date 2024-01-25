package br.com.fiap.soat.grupo48.application.pedido.port.api;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;

import java.util.List;
import java.util.UUID;

public interface IPedidoSituacaoPort {
    boolean atualizarSituacao(UUID codigoPedido, SituacaoPedido situacaoPedido);

    List<Pedido> buscarPedidosPorSituacao(List<SituacaoPedido> situacoes);

    List<Pedido> buscarPedidosMostradosMonitorCliente();
}
