package br.com.fiap.soat.grupo48.application.commons.valueobject;

import br.com.fiap.soat.grupo48.application.commons.exception.NomeException;
import lombok.SneakyThrows;

import java.util.Objects;

public record Nome(String nome) {
    @SneakyThrows
    public Nome {
        if (Objects.isNull(nome)) {
            throw new NomeException("O nome do produto não pode ser nulo");
        } else if (nome.isEmpty()) {
            throw new NomeException("O nome do produto não pode ser vazio");
        } else if (nome.length() < 3) {
            throw new NomeException("O nome do produto tem que ter o valor minimo de 3 caracteres");
        }
    }
}
