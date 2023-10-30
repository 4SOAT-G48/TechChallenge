package br.com.fiap.soat.grupo48.application.pedido.dto;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ItemPedidoDto {

    private ProdutoDto produto;

    private Integer quantidade;
    private Double precoUnitario;

    private String observacao;
}
