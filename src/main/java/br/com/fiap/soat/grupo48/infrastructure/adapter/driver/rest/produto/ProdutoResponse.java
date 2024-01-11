package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {
    
    private UUID codigo;
    private String nome;
    private Categoria categoria;
    private Double preco;
    private String descricao;
    private SituacaoProduto situacao;
    private List<String> imagens;

    public ProdutoResponse(UUID codigo, String nome, Categoria categoria, Double preco, String descricao, SituacaoProduto situacao, List<String> imagens) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
        this.situacao = situacao;
        this.imagens = imagens;
    }

    public List<String> getImagens() {
        if (imagens == null) {
            imagens = new ArrayList<>();
        }
        return imagens;
    }
}
