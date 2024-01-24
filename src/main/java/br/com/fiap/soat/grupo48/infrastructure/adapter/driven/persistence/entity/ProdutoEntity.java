package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity;

import br.com.fiap.soat.grupo48.application.commons.exception.NomeException;
import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import br.com.fiap.soat.grupo48.application.produto.valueobject.Imagem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @ElementCollection
    @CollectionTable(name = "produto_imagens", joinColumns = @JoinColumn(name = "produto_codigo"))
    private List<ImageEntity> images;

    public ProdutoEntity() {
    }


    public ProdutoEntity(Produto produto) {
        this.codigo = produto.getCodigo();
        this.atualizar(produto);
    }

    public Produto toProduto() {
        try {
            return new Produto(this.codigo,this.nome,this.categoria,this.preco,this.descricao,this.situacao,
                    this.images.stream().map(ImageEntity::toImage).collect(Collectors.toList()));
        } catch (NomeException e) {
            return null;
        }
    }

    public void atualizar(Produto produto) {
        this.nome = produto.getNome().nome();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
        this.situacao = produto.getSituacao();
        this.images = new ArrayList<>();
        for (Imagem imagem : produto.getImagens()) {
            String url = imagem.url();
            this.images.add(new ImageEntity(url));
        }
    }
}
