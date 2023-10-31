package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pedido;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PedidoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringPedidoItemRepository extends JpaRepository<PedidoItemEntity, UUID> {
}
