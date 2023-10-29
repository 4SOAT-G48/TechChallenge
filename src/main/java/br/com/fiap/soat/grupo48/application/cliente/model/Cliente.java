package br.com.fiap.soat.grupo48.application.cliente.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Cliente {
    private UUID codigo;
    private String nome;
    private String cpf;
    private String email;

    public Cliente(UUID codigo, String nome, String cpf, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this. email = email;
    }

}
