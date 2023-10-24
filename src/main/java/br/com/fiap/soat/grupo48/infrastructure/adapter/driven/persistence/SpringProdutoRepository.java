package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence;

import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
}
