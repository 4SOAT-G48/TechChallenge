package br.com.fiap.soat.grupo48.application.pedido.aggregate;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.valueobject.GeradorDeNumeroSequencial;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

@NoArgsConstructor
@Getter
public class PedidoAggregate {

    private Pedido pedido;

    public void montaPedido(Pedido pedido, Cliente cliente) {

        this.pedido = new Pedido();
        this.pedido.setCodigo(pedido.getCodigo());
        this.pedido.setSituacao(pedido.getSituacao());
        this.pedido.setCliente(cliente);
        if (Objects.nonNull(pedido.getIdentificacao())
                && !pedido.getIdentificacao().isEmpty()) {
            this.pedido.setIdentificacao(pedido.getIdentificacao());
        } else {
            this.pedido.setIdentificacao(GeradorDeNumeroSequencial.getInstance().proximoNumero());
        }
        this.pedido.setItens(pedido.getItens());

        this.pedido.setPagamento(pedido.getPagamento());

        this.pedido.setDataCriacao(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    }


}
