package br.com.fiap.soat.grupo48.application.cliente.usecase;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.cliente.port.api.IClientePort;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.IClienteRepositoryGateway;

public class ClienteUsecaseImpl implements IClientePort {
  private final IClienteRepositoryGateway iClienteRepositoryGateway;

  public ClienteUsecaseImpl(IClienteRepositoryGateway iClienteRepositoryGatewayParam) {
    this.iClienteRepositoryGateway = iClienteRepositoryGatewayParam;
  }

  @Override
  public Cliente buscarPeloCpf(String cpf) {
    return this.iClienteRepositoryGateway.buscarPeloCpf(cpf);
  }

  @Override
  public Cliente criarCliente(Cliente cliente) {
    return this.iClienteRepositoryGateway.salvar(cliente);
  }
}
