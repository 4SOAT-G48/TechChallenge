package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.spi.ProdutoRepositoryPort;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProdutoRepository implements ProdutoRepositoryPort {

    private final SpringProdutoRepository springProdutoRepository;

    public ProdutoRepository(SpringProdutoRepository springProdutoRepository) {
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<Produto> buscarTodos() {
        List<ProdutoEntity> produtoEntity = this.springProdutoRepository.findAll();
        return produtoEntity.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto buscarPeloCodigo(UUID codigo) {
        Optional<ProdutoEntity> produtoEntityOptional = this.springProdutoRepository.findById(codigo);
        if (produtoEntityOptional.isPresent()) {
            ProdutoEntity produtoEntity = produtoEntityOptional.get();
            return produtoEntity.toProduto();
        } else {
            return null;
        }
    }

    @Override
    public void salvar(Produto produto) {
        ProdutoEntity produtoEntity;
        if(Objects.isNull(produto.getCodigo())) {
            produtoEntity = new ProdutoEntity(produto);
        } else {
            produtoEntity = this.springProdutoRepository.findById(produto.getCodigo()).get();
            produtoEntity.atualizar(produto);
        }

        this.springProdutoRepository.save(produtoEntity);
    }
}
