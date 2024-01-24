package br.com.fiap.soat.grupo48.application.produto.model;

import br.com.fiap.soat.grupo48.application.commons.exception.NomeException;
import br.com.fiap.soat.grupo48.application.commons.valueobject.Nome;
import br.com.fiap.soat.grupo48.application.produto.valueobject.Imagem;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Produto {
    @Setter private UUID codigo;
    @Setter private Nome nome;
    @Setter private Categoria categoria;
    @Setter private Double preco;
    @Setter private String descricao;
    @Setter private SituacaoProduto situacao;
    private List<Imagem> imagens;

    public Produto(UUID codigo, String nome, Categoria categoria, Double preco, String descricao, SituacaoProduto situacao, List<Imagem> imagens) throws NomeException {
        this.codigo = codigo;
        this.nome = new Nome(nome);
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
        this.situacao = situacao;
        this.imagens = imagens;
    }

    public void atualiza(Produto produto) {
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
        this.situacao = produto.getSituacao();
        this.imagens = produto.getImagens();
    }
}
