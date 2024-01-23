package br.com.fiap.soat.grupo48.application.pedido.port.api;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;

public interface IPedidoEmAndamentoPort {
    /**
     * Faze de montagem do pedido.
     * Pode ser desde a adição do primeiro item
     * até ele escolher concluir o pedido.
     * @param pedido
     * @param cpfCliente
     * @return
     */
    Pedido montaPedido(Pedido pedido, String cpfCliente);

    /**
     * Passa para o pagamento.
     */
    void efetuaPagamento();

    /**
     * Pedido concluido e pagamento feito.
     * @param pedidoDto
     * @return
     */
    Pedido concluiPedido(Pedido pedidoDto);
}
