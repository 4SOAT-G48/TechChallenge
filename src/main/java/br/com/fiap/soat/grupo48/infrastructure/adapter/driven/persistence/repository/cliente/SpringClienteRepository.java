package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.cliente;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    Cliente findByCpf(String cpf);
}
