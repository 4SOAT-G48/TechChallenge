package br.com.fiap.soat.grupo48.application.produto.dto;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {
    
    private UUID codigo;
    private String nome;
    private Categoria categoria;
    private Double preco;
    private String descricao;
    private SituacaoProduto situacao;
    private List<String> imagens;

}
