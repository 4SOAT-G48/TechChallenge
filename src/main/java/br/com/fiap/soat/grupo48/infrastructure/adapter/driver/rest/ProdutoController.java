package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Tag(name = "Manuteção de Produtos", description = "Endpoints destinados a manutenção dos produtos usados pela administração")
@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
    private final ProdutoPort produtoServicePort;

    public ProdutoController(ProdutoPort produtoServicePort) {
        this.produtoServicePort = produtoServicePort;
    }

    @Operation(summary = "Recupera lista de produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoDto.class)) }),
             })
    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getProdutos() {
        List<ProdutoDto> produtos = this.produtoServicePort.buscarProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @Operation(summary = "Recupera um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content) })
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<ProdutoDto> getProduto(@PathVariable UUID codigo) {
        ProdutoDto produto = this.produtoServicePort.buscarPeloCodigo(codigo);
        if (Objects.isNull(produto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> createProduto(@RequestBody ProdutoDto produto) {
        ProdutoDto produtoSave = this.produtoServicePort.criarProduto(produto);
        return new ResponseEntity<>(produtoSave, HttpStatus.CREATED);
    }
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<ProdutoDto> updateProduto(@PathVariable UUID codigo, @RequestBody ProdutoDto produto) {
        if (Objects.isNull(this.produtoServicePort.buscarPeloCodigo(codigo))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProdutoDto produtoAtualizado = this.produtoServicePort.atualizarProduto(codigo,produto);
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "{codigo}")
    public ResponseEntity<Void> deleteProduto(@PathVariable UUID codigo) {
        if (Objects.isNull(this.produtoServicePort.buscarPeloCodigo(codigo))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.produtoServicePort.excluirProduto(codigo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
