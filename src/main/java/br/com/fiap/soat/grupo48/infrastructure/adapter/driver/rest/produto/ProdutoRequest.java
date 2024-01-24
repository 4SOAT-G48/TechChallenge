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
public class ProdutoRequest {
    
    private UUID codigo;
    private String nome;
    private Categoria categoria;
    private Double preco;
    private String descricao;
    private SituacaoProduto situacao;
    private List<String> imagens;

    public List<String> getImagens() {
        if (imagens == null) {
            imagens = new ArrayList<>();
        }
        return imagens;
    }
}
