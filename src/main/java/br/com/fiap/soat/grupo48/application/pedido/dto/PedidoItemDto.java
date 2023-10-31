package br.com.fiap.soat.grupo48.application.pedido.dto;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PedidoItemDto {

    private UUID codigo;
    private ProdutoDto produto;

    private Integer quantidade;
    private Double precoUnitario;

    private String observacao;
}
