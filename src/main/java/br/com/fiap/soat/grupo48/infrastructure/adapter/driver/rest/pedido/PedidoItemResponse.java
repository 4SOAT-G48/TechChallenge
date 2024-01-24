package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoItemResponse {
    private UUID codigo;

    private Produto produto;

    private Integer quantidade;
    private Double precoUnitario;

    private String observacao;

    public Double getTotal() {
        return precoUnitario * quantidade;
    }

}
