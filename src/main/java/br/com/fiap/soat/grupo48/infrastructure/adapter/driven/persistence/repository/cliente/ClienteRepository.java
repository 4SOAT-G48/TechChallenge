package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.cliente;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.ClienteRepositoryPort;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepository implements ClienteRepositoryPort {
    private final SpringClienteRepository springClienteRepository;

    public ClienteRepository(SpringClienteRepository springClienteRepository) {
        this.springClienteRepository = springClienteRepository;
    }

    @Override
    public Cliente buscarPeloCpf(String cpf) {
        return this.springClienteRepository.findByCpf(cpf);
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity;
        clienteEntity = new ClienteEntity(cliente);

        return this.springClienteRepository.save(clienteEntity).toCliente();
    }
}
