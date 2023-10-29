package br.com.fiap.soat.grupo48.application.cliente.port.api;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;

public interface ClientePort {
    Cliente buscarPeloCpf(String cpf);

    Cliente criarCliente(Cliente cliente);
}
