package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.produto;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    List<ProdutoEntity> findByCategoria(Categoria categoria);
    List<ProdutoEntity> findByCategoriaAndSituacao(Categoria categoria, SituacaoProduto situacao);
}
