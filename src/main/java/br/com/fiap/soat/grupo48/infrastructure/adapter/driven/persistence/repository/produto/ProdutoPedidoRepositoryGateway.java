package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.produto;

import br.com.fiap.soat.grupo48.application.commons.exception.NomeException;
import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoPedidoRepositoryGateway;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoPedidoRepositoryGateway implements IProdutoPedidoRepositoryGateway {

    private final SpringProdutoRepository springProdutoRepository;

    public ProdutoPedidoRepositoryGateway(SpringProdutoRepository springProdutoRepository) {
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<Produto> buscarPorCategoria(Categoria categoria) {
        List<ProdutoEntity> produtoEntity = this.springProdutoRepository.findByCategoria(categoria);
        return produtoEntity.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public List<Produto> buscarPorCategoriaESituacao(Categoria categoria, SituacaoProduto situacao) {
        List<ProdutoEntity> produtoEntity = this.springProdutoRepository.findByCategoriaAndSituacao(categoria, situacao);
        return produtoEntity.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }
}
