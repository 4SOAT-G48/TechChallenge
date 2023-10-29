package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigo;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Double preco;
    private String descricao;
    //TODO: array de imagens
    @Enumerated(EnumType.STRING)
    private SituacaoProduto situacao;

    public ProdutoEntity() {
    }


    public ProdutoEntity(Produto produto) {
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
        this.situacao = produto.getSituacao();
    }

    public Produto toProduto() {
        return new Produto(this.codigo,this.nome,this.categoria,this.preco,this.descricao,this.situacao);
    }

    public void atualizar(Produto produto) {
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
        this.situacao = produto.getSituacao();
    }
}
