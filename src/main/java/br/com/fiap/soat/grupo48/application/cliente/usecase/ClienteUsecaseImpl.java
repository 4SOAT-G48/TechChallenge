package br.com.fiap.soat.grupo48.application.cliente.usecase;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.cliente.port.api.ClientePort;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.ClienteRepositoryPort;

public class ClienteUsecaseImpl implements ClientePort {
    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteUsecaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Cliente buscarPeloCpf(String cpf) {
        Cliente cliente = this.clienteRepositoryPort.buscarPeloCpf(cpf);
        return cliente;
    }

    @Override
    public Cliente criarCliente(Cliente cliente) {
        return this.clienteRepositoryPort.salvar(cliente);
    }
}
