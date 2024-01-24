package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido.situacao;

import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import lombok.*;

import java.util.UUID;

@Data
public class PedidoSituacaoRequest {

    private UUID codigo;

    private SituacaoPedido situacao;
}