package br.com.fiap.soat.grupo48.application.pedido.port.api;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;

public interface IPedidoEmAndamentoPort {
    /**
     * Faze de montagem do pedido.
     * Pode ser desde a adição do primeiro item
     * até ele escolher concluir o pedido.
     * @param pedido pedido com os dados a serem salvos
     * @param cpfCliente número do cpf do cliente, quando logado
     * @return pedido com os dados conforme salvos
     */
    Pedido montaPedido(Pedido pedido, String cpfCliente);

    /**
     * Passa para o pagamento.
     */
    void efetuaPagamento();
}
