package br.com.fiap.soat.grupo48.application.pedido.port.api;

import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoSituacaoDto;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;

import java.util.List;

public interface PedidoSituacaoPort {
    boolean atualizarSituacao(PedidoSituacaoDto pedido);

    List<Pedido> buscarPedidosPorSituacao(List<SituacaoPedido> situacoes);
}
