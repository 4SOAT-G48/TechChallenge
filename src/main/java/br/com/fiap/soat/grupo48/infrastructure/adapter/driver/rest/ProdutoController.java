package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
    private final ProdutoServicePort produtoServicePort;

    public ProdutoController(ProdutoServicePort produtoServicePort) {
        this.produtoServicePort = produtoServicePort;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getProdutos() {
        List<Produto> produtos = this.produtoServicePort.buscarProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Produto> getProduto(@PathVariable UUID codigo) {
        Produto produto = this.produtoServicePort.buscarPeloCodigo(codigo);
        if (Objects.isNull(produto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto produtoSave = this.produtoServicePort.criarProduto(produto);
        return new ResponseEntity<>(produtoSave, HttpStatus.CREATED);
    }
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable UUID codigo, @RequestBody Produto produto) {
        if (Objects.isNull(this.produtoServicePort.buscarPeloCodigo(codigo))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Produto produtoAtualizado = this.produtoServicePort.atualizarProduto(codigo,produto);
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "{codigo}")
    public ResponseEntity<Void> excluirProduto(@PathVariable UUID codigo) {
        if (Objects.isNull(this.produtoServicePort.buscarPeloCodigo(codigo))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.produtoServicePort.excluirProduto(codigo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
