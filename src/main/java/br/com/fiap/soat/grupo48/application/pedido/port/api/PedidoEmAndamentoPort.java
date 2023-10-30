package br.com.fiap.soat.grupo48.application.pedido.port.api;

import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;

public interface PedidoEmAndamentoPort {
    /**
     * Faze de montagem do pedido.
     * Pode ser desde a adição do primeiro item
     * até ele escolher concluir o pedido.
     * @param pedidoDto
     * @return
     */
    PedidoDto montaPedido(PedidoDto pedidoDto);

    /**
     * Passa para o pagamento.
     */
    void efetuaPagamento();

    /**
     * Pedido concluido e pagamento feito.
     * @param pedidoDto
     * @return
     */
    PedidoDto concluiPedido(PedidoDto pedidoDto);
}
