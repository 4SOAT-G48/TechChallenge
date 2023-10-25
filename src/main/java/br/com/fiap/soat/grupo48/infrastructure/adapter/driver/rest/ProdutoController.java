package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoServicePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private final ProdutoServicePort produtoServicePort;

    public ProdutoController(ProdutoServicePort produtoServicePort) {
        this.produtoServicePort = produtoServicePort;
    }

    @GetMapping
    List<Produto> getProdutos() {
        return  this.produtoServicePort.buscarProdutos();
    }

    @GetMapping(value = "/{codigo}")
    Produto getProduto(@PathVariable UUID codigo) {
        return this.produtoServicePort.buscarPeloCodigo(codigo);
    }

    @PostMapping
    void criarProduto(@RequestBody Produto produto) {
        this.produtoServicePort.criarProduto(produto);
    }
    @PutMapping(value = "/{codigo}")
    void atualizarProduto(@PathVariable UUID codigo, @RequestBody Produto produto) {
        this.produtoServicePort.atualizarProduto(codigo,produto);
    }
}
