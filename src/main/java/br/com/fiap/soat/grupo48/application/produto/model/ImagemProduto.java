package br.com.fiap.soat.grupo48.application.produto.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ImagemProduto {
    private UUID codigo;
    private String url;

    public ImagemProduto(UUID codigo, String url) {
        this.codigo = codigo;
        this.url = url;
    }
}
