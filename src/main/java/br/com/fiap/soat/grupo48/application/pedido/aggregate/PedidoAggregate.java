package br.com.fiap.soat.grupo48.application.pedido.aggregate;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Getter
public class PedidoAggregate {

    private Pedido pedido;
    //TODO: refactor
    public void montaPedido(Pedido pedido, Cliente cliente) {

        this.pedido = new Pedido();
        this.pedido.setCodigo(pedido.getCodigo());
        this.pedido.setSituacao(pedido.getSituacao());
        this.pedido.setCliente(cliente);
        if (Objects.nonNull(pedido.getNumero())
                && !pedido.getNumero().isEmpty()) {
            //TODO: transformar em valueobject e validar
            this.pedido.setNumero(pedido.getNumero());
        }
        this.pedido.setItens(pedido.getItens());
    }

}
