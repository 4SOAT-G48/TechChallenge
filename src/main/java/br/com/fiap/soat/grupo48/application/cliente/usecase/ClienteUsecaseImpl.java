package br.com.fiap.soat.grupo48.application.cliente.usecase;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.cliente.port.api.ClientePort;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.IClienteRepositoryGateway;

public class ClienteUsecaseImpl implements ClientePort {
    private final IClienteRepositoryGateway IClienteRepositoryGateway;

    public ClienteUsecaseImpl(IClienteRepositoryGateway IClienteRepositoryGateway) {
        this.IClienteRepositoryGateway = IClienteRepositoryGateway;
    }

    @Override
    public Cliente buscarPeloCpf(String cpf) {
        Cliente cliente = this.IClienteRepositoryGateway.buscarPeloCpf(cpf);
        return cliente;
    }

    @Override
    public Cliente criarCliente(Cliente cliente) {
        return this.IClienteRepositoryGateway.salvar(cliente);
    }
}
