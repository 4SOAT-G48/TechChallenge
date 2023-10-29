package br.com.fiap.soat.grupo48.application.produto.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Produto {
    private UUID codigo;
    private String nome;
    private Categoria categoria;
    private Double preco;
    private String descricao;
    private SituacaoProduto situacao;

    //private List<ImagemProduto> imagens;

    public Produto(UUID codigo, String nome, Categoria categoria, Double preco, String descricao, SituacaoProduto situacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
        this.situacao = situacao;
    }
}
