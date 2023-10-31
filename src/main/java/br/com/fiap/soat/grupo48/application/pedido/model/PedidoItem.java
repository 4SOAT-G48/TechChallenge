package br.com.fiap.soat.grupo48.application.pedido.model;

import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoItemDto;
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
public class PedidoItem {
    private UUID codigo;

    private Produto produto;

    private Integer quantidade;
    private Double precoUnitario;

    private String observacao;

    public Double getTotal() {
        return precoUnitario * quantidade;
    }

    public PedidoItemDto toPedidoItemDto() {
        return new PedidoItemDto(this.getCodigo(),
                                 this.produto.toProdutoDto(),
                                 this.quantidade,
                                 this.precoUnitario,
                                 this.observacao);
    }
}
