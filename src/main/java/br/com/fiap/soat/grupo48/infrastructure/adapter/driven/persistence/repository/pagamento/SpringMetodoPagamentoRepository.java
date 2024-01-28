package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.MetodoPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringMetodoPagamentoRepository extends JpaRepository<MetodoPagamentoEntity, UUID> {

}
