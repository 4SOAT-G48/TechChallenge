package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoSituacaoDto;
import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoSituacaoPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/pedidosituacao")
public class PedidoSituacaoController {

    private final PedidoSituacaoPort pedidoSituacaoPort;

    public PedidoSituacaoController(PedidoSituacaoPort pedidoSituacaoPort) {
        this.pedidoSituacaoPort = pedidoSituacaoPort;
    }

    @Operation(summary = "Atualiza situação do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido Atualizado", content = { @Content }),
            @ApiResponse(responseCode = "404", description = "Pedido não Encontrado", content = { @Content }),
    })
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<Void> updateSituacaoPedido(@PathVariable UUID codigo, @RequestBody PedidoSituacaoDto pedidoSituacao) {
        if (Objects.nonNull(codigo)) {
            pedidoSituacao.setCodigo(codigo);
        }
        if (this.pedidoSituacaoPort.atualizarSituacao(pedidoSituacao)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
