package br.com.fiap.soat.grupo48.application.cliente.port.spi;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;

public interface IClienteRepositoryGateway {
    Cliente buscarPeloCpf(String cpf);

    Cliente salvar(Cliente cliente);
}
