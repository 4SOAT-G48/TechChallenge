package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido.situacao;

public enum SituacaoPedidoResponse {
    /**
     * Cliente está montando seu pedido
     */
    EM_ANDAMENTO,

    /**
     * Cliente terminou de escolher os itens
     * para seu pedido e fez seu pagamento
     */
    RECEBIDO,
    /**
     * A cozinha separou o pedido para
     * começar a montagem
     */
    EM_PREPARACAO,

    /**
     * A cozinha terminou a montagem e
     * passou para o atendente fazer a entrega
     */
    PRONTO,

    /**
     * O atendente passou o pedido para a
     * faze de entrega, o cliente pode vir buscar o pedido.
     */
    EM_ENTREGA,

    /**
     * pedido entregue ao cliente
     */
    FINALIZADO

}