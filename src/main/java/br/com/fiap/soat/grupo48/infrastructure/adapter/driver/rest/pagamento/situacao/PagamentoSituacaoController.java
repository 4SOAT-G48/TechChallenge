package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.situacao;


import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.api.IPagamentoSituacaoPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@Tag(name = "Acompanhamento da situação do pagamento")
@RestController
@RequestMapping("api/pagamento/situacao")
public class PagamentoSituacaoController {

    private final IPagamentoSituacaoPort pagamentoSituacaoPort;

    private final PagamentoSituacaoMapper pagamentoSituacaoMapper;

    public PagamentoSituacaoController(IPagamentoSituacaoPort pagamentoSituacaoPort, PagamentoSituacaoMapper pagamentoSituacaoMapper) {
        this.pagamentoSituacaoPort = pagamentoSituacaoPort;
        this.pagamentoSituacaoMapper = pagamentoSituacaoMapper;
    }

    @Operation(summary = "Atualiza situação do pagamento do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Situação do Pagamento Atualizado", content = { @Content}),
            @ApiResponse(responseCode = "404", description = "Pagamento não Encontrado", content = { @Content }),
    })
    @PutMapping
    public ResponseEntity<?> updateSituacaoPagamento(@RequestBody PagamentoSituacaoRequest request) {
        if (Objects.nonNull(request)) {
            boolean atualizaSituacao = this.pagamentoSituacaoPort.atualizaSituacao(this.pagamentoSituacaoMapper.toPagamento(request));
            if (atualizaSituacao) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você deve informar um corpo com código e situação de pagamentos");
        }
    }

    @Operation(summary = "Recupera a situação do pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado e situação devolvida",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = PagamentoSituacaoResponse.class))})
    })
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<PagamentoSituacaoResponse> getSituacaoPagamento(@PathVariable UUID codigo) {
        Pagamento pagamento = this.pagamentoSituacaoPort.buscarPagamento(codigo);
        PagamentoSituacaoResponse pagamentoSituacaoResponse = this.pagamentoSituacaoMapper.toPagamentoSituacaoResponse(pagamento);
        return new ResponseEntity<>(pagamentoSituacaoResponse, HttpStatus.OK);
    }

}
