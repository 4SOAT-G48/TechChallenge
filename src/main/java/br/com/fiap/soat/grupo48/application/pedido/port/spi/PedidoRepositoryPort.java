package br.com.fiap.soat.grupo48.application.pedido.port.spi;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);
}
