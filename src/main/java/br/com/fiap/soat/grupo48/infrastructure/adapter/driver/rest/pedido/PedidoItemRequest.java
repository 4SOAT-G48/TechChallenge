package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto.ProdutoRequest;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PedidoItemRequest {

    private UUID codigo;
    private ProdutoRequest produto;

    private Integer quantidade;
    private Double precoUnitario;

    private String observacao;
}
