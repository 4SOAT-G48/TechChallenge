package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pedido;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringPedidoRepository extends JpaRepository<PedidoEntity, UUID> {
}
