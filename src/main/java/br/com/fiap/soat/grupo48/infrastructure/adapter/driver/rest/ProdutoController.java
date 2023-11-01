package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.grupo48.application.produto.dto.ProdutoDto;
import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
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


    @GetMapping("/populaprodutosiniciais")
    public ResponseEntity<Void> getGeraProdutosIniciais() {

        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer Simples", Categoria.LANCHE,20.0,"Hambúrguer de carne 150g com queijo e alface", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer Simples", Categoria.LANCHE, 20.0,"Hambúrguer de carne 150g com queijo e alface", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer Vegetariano", Categoria.LANCHE, 21.0,"Hambúrguer de grão de bico 150g com queijo e alface", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer Duplo", Categoria.LANCHE, 30.0,"2 Hambúrgueres de carne 150g com queijo, alface e tomate", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer de Frango", Categoria.LANCHE, 22.0,"Hambúrguer de frango 150g com molho especial", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer Picanha", Categoria.LANCHE, 25.0,"Hambúrguer de picanha 180g com queijo prato", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer de Peixe", Categoria.LANCHE, 23.0,"Hambúrguer de peixe 150g com alface e tartar", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer Bacon", Categoria.LANCHE, 24.0,"Hambúrguer de carne 150g com bacon e cheddar", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer de Cordeiro", Categoria.LANCHE, 26.0,"Hambúrguer de cordeiro 160g com rúcula e tzatziki", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hambúrguer Texano", Categoria.LANCHE, 24.5,"Hambúrguer de carne 150g com onion rings e barbecue", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Sanduíche de Frango", Categoria.LANCHE, 18.0,"Peito de frango grelhado, alface, tomate e maionese", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Sanduíche de Atum", Categoria.LANCHE, 19.0,"Atum, alface, tomate, azeitonas e maionese", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Sanduíche Vegetal", Categoria.LANCHE, 17.0,"Mix de verduras frescas com molho especial", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Wrap de Frango", Categoria.LANCHE, 20.0,"Frango desfiado, alface, tomate, cenoura e molho rosé", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Wrap Vegano", Categoria.LANCHE, 19.5,"Hummus, rúcula, tomate, pepino e azeitonas", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hot Dog Simples", Categoria.LANCHE, 13.0,"Salsicha, molho, milho e batata palha", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Hot Dog Completo", Categoria.LANCHE, 15.5,"Salsicha, molho, milho, batata palha, queijo e bacon", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Taco de Carne", Categoria.LANCHE, 16.0,"Taco com carne moída, alface, tomate, queijo e molho picante", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Taco de Frango", Categoria.LANCHE, 16.0,"Taco com frango desfiado, alface, tomate, queijo e sour cream", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Burrito Vegano", Categoria.LANCHE, 20.0,"Arroz, feijão, guacamole, alface e pico de gallo", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Burrito de Carne", Categoria.LANCHE, 21.0,"Arroz, feijão, carne moída, queijo, alface e pico de gallo", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Batata Frita", Categoria.ACOMPANHAMENTO, 15.0,"250g de pura batata", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Batata Frita com Cheddar", Categoria.ACOMPANHAMENTO, 17.0,"250g de batata coberta com cheddar", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Batata Frita com Bacon", Categoria.ACOMPANHAMENTO, 18.0,"250g de batata coberta com pedaços de bacon", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Onion Rings", Categoria.ACOMPANHAMENTO, 16.0,"Anéis de cebola empanados e fritos", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Anéis de Lula", Categoria.ACOMPANHAMENTO, 20.0,"Anéis de lula empanados", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Mandioca Frita", Categoria.ACOMPANHAMENTO, 15.5,"250g de mandioca frita e crocante", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Polenta Frita", Categoria.ACOMPANHAMENTO, 14.0,"Tiras de polenta frita", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Salada Verde", Categoria.ACOMPANHAMENTO, 12.0,"Mix de folhas verdes, tomate e pepino", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Salada Caesar", Categoria.ACOMPANHAMENTO, 19.0,"Alface, croutons, frango grelhado e molho Caesar", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Coleslaw", Categoria.ACOMPANHAMENTO, 13.0,"Salada de repolho e cenoura com molho agridoce", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Picles", Categoria.ACOMPANHAMENTO, 8.0,"Porção de picles em conserva", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Guacamole", Categoria.ACOMPANHAMENTO, 15.0,"Pasta de abacate temperada", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Arroz Branco", Categoria.ACOMPANHAMENTO, 10.0,"Porção de arroz branco", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Arroz Carreteiro", Categoria.ACOMPANHAMENTO, 20.0,"Arroz com pedaços de carne e legumes", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Feijão Tropeiro", Categoria.ACOMPANHAMENTO, 18.5,"Feijão misturado com farinha, bacon e linguiça", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Ovo Frito", Categoria.ACOMPANHAMENTO, 6.0,"2 ovos fritos", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Queijo Coalho", Categoria.ACOMPANHAMENTO, 15.0,"Porção de queijo coalho grelhado", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Azeitonas Temperadas", Categoria.ACOMPANHAMENTO, 9.0,"Porção de azeitonas temperadas", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Farofa", Categoria.ACOMPANHAMENTO, 8.0,"Farofa temperada", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Purê de Batata", Categoria.ACOMPANHAMENTO, 12.0,"Purê cremoso de batata", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante Lata", Categoria.BEBIDA, 6.0,"Lata 350ml", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante 600ml", Categoria.BEBIDA, 8.0,"Garrafa 600ml", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Suco Natural Laranja", Categoria.BEBIDA, 8.0,"Copo de 300ml de suco natural de laranja", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Água Mineral", Categoria.BEBIDA, 4.0,"Garrafa 500ml sem gás", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante Coca-Cola Lata", Categoria.BEBIDA, 5.0,"Lata 350ml de Coca-Cola", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante Pepsi Lata", Categoria.BEBIDA, 4.5,"Lata 350ml de Pepsi", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante Guaraná Lata", Categoria.BEBIDA, 4.5,"Lata 350ml de Guaraná Antarctica", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante Sprite Lata", Categoria.BEBIDA, 4.5,"Lata 350ml de Sprite", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante Fanta Lata", Categoria.BEBIDA, 4.5,"Lata 350ml de Fanta", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante Zero Lata", Categoria.BEBIDA, 5.0,"Lata 350ml de refrigerante zero açúcar", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Refrigerante Diet Lata", Categoria.BEBIDA, 4.5,"Lata 350ml de refrigerante diet", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Suco de Laranja Natural", Categoria.BEBIDA, 7.0,"Copo de 300ml de suco natural de laranja", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Suco de Maçã Natural", Categoria.BEBIDA, 6.5,"Copo de 300ml de suco natural de maçã", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Água Mineral com Gás", Categoria.BEBIDA, 3.5,"Garrafa 500ml de água com gás", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Água Mineral sem Gás", Categoria.BEBIDA, 3.0,"Garrafa 500ml de água mineral sem gás", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Água de Coco", Categoria.BEBIDA, 5.0,"Garrafa 330ml de água de coco natural", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Chá Gelado de Pêssego", Categoria.BEBIDA, 6.0,"Copo 500ml de chá gelado de pêssego", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Chá Gelado de Limão", Categoria.BEBIDA, 6.0,"Copo 500ml de chá gelado de limão", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Chá Gelado de Framboesa", Categoria.BEBIDA, 6.0,"Copo 500ml de chá gelado de framboesa", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Milkshake Chocolate", Categoria.SOBREMESA, 14.0,"Milkshake cremoso sabor chocolate", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Milkshake Morango", Categoria.SOBREMESA, 14.0,"Milkshake cremoso sabor morango", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Milkshake Baunilha", Categoria.SOBREMESA, 14.0,"Milkshake cremoso sabor baunilha", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Milkshake Oreo", Categoria.SOBREMESA, 15.0,"Milkshake cremoso com biscoitos Oreo", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Milkshake Café", Categoria.SOBREMESA, 13.0,"Milkshake cremoso sabor café", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Torta de Limão", Categoria.SOBREMESA, 12.0,"Fatia de torta com recheio de limão", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Sorvete", Categoria.SOBREMESA, 10.0,"Pote de sorvete sabor baunilha 150g", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Pudim de Leite", Categoria.SOBREMESA, 9.0,"Pudim de leite condensado", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Brigadeiro", Categoria.SOBREMESA, 8.0,"Porção de brigadeiros", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Cheesecake de Morango", Categoria.SOBREMESA, 15.0,"Fatia de cheesecake com calda de morango", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Sorvete de Chocolate", Categoria.SOBREMESA, 10.0,"Pote de sorvete sabor chocolate 150g", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null,"Torta de Maçã", Categoria.SOBREMESA, 12.0,"Fatia de torta de maçã com canela", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Pavê de Chocolate", Categoria.SOBREMESA, 11.0, "Porção de pavê de chocolate", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Waffle com Sorvete", Categoria.SOBREMESA, 16.0, "Waffle quente com sorvete e calda de chocolate", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Bolo de Cenoura", Categoria.SOBREMESA, 10.0, "Fatia de bolo de cenoura com cobertura de chocolate", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Churros", Categoria.SOBREMESA, 7.0, "Porção de churros com doce de leite", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Sorvete de Morango", Categoria.SOBREMESA, 10.0, "Pote de sorvete sabor morango 150g", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Mousse de Chocolate", Categoria.SOBREMESA, 9.0, "Mousse cremoso de chocolate", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Banana Split", Categoria.SOBREMESA, 14.0, "Banana cortada com sorvete, chantilly e coberturas", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Creme Brulée", Categoria.SOBREMESA, 12.0, "Creme brulée com açúcar queimado", SituacaoProduto.DISPONIVEL, null));
        this.produtoServicePort.criarProduto(new ProdutoDto(null, "Bolo de Chocolate", Categoria.SOBREMESA, 11.0, "Fatia de bolo de chocolate com cobertura de ganache", SituacaoProduto.DISPONIVEL, null));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
