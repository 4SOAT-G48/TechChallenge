package br.com.fiap.soat.grupo48.application.produto.model;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;
import br.com.fiap.soat.grupo48.application.produto.valueobject.Imagem;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Produto {
    @Getter @Setter private UUID codigo;
    @Getter @Setter private String nome;
    @Getter @Setter private Categoria categoria;
    @Getter @Setter private Double preco;
    @Getter @Setter private String descricao;
    @Getter @Setter private SituacaoProduto situacao;
    @Getter private List<Imagem> imagens;



    public Produto(UUID codigo, String nome, Categoria categoria, Double preco, String descricao, SituacaoProduto situacao, List<Imagem> imagens) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
        this.situacao = situacao;
        this.imagens = imagens;
    }

    public Produto(ProdutoDto produtoDto) {
        this.codigo = produtoDto.getCodigo();
        this.nome = produtoDto.getNome();
        this.categoria = produtoDto.getCategoria();
        this.preco = produtoDto.getPreco();
        this.descricao = produtoDto.getDescricao();
        this.situacao = produtoDto.getSituacao();
        this.imagens = produtoDto.getImagens().stream().map(Imagem::new).collect(Collectors.toList());
    }

    public void atualiza(ProdutoDto produtoDto) {
        this.nome = produtoDto.getNome();
        this.categoria = produtoDto.getCategoria();
        this.preco = produtoDto.getPreco();
        this.descricao = produtoDto.getDescricao();
        this.situacao = produtoDto.getSituacao();
        this.imagens = produtoDto.getImagens().stream().map(Imagem::new).collect(Collectors.toList());
    }
    public ProdutoDto toProdutoDto() {
        return new ProdutoDto(this.codigo,this.nome,this.categoria,this.preco,this.descricao,this.situacao,
                this.imagens.stream().map(Imagem::url).collect(Collectors.toList()));
    }

}
