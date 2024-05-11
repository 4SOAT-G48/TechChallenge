package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.api.IMetodoPagamentoPort;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento.MetodoPagamentoMapper;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento.PagamentoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Método de Pagamento", description = "Endpoints relacionados com métodos de pagamentos")
@RestController
@RequestMapping("api/metodospagamento")
public class MetodoPagamentoController {
    private final IMetodoPagamentoPort metodoPagamentoPort;

    private final PagamentoMapper pagamentoMapper;

    private final MetodoPagamentoMapper metodoPagamentoMapper;

    public MetodoPagamentoController(IMetodoPagamentoPort metodoPagamentoPort, PagamentoMapper pagamentoMapper, MetodoPagamentoMapper metodoPagamentoMapper) {
        this.metodoPagamentoPort = metodoPagamentoPort;
        this.pagamentoMapper = pagamentoMapper;
        this.metodoPagamentoMapper = metodoPagamentoMapper;
    }

    @Operation(summary = "Recupera lista de metodos de pagamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metodos de pagamentos encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetodoPagamentoResponse.class)
                    )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<MetodoPagamentoResponse>> getMetodos() {
        List<MetodoPagamento> metodoPagamentos = this.metodoPagamentoPort.buscarMetodosPagamentos();
        List<MetodoPagamentoResponse> responseList = this.metodoPagamentoMapper.toListResponse(metodoPagamentos);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
