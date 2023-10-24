package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.entity;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "produto")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;
    private String nome;
    private Categoria categoria;
    private Double preco;
    private String descricao;
    //TODO: array de imagens
    private SituacaoProduto situacao;

    public Produto toProduto() {
        return new Produto(this.codigo,this.nome,this.categoria,this.preco,this.descricao,this.situacao);
    }
}
