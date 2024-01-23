package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido;

import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.port.api.IPedidoEmAndamentoPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Tag(name = "Pedido em andamento", description = "Endpoints destinado ao estágio de montagem do pedido")
@RestController
@RequestMapping("api/pedidoemandamento")
public class PedidoEmAndamentoController {

    private final IPedidoEmAndamentoPort pedidoEmAndamentoPort;
    private final PedidoEmAndamentoDTOMapper pedidoEmAndamentoDTOMapper;

    public PedidoEmAndamentoController(IPedidoEmAndamentoPort pedidoEmAndamentoPort, PedidoEmAndamentoDTOMapper pedidoEmAndamentoDTOMapper) {
        this.pedidoEmAndamentoPort = pedidoEmAndamentoPort;
        this.pedidoEmAndamentoDTOMapper = pedidoEmAndamentoDTOMapper;
    }

    @Operation(summary = "Cria o pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido Criado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = IPedidoEmAndamentoPort.class))}),
            @ApiResponse(responseCode = "400", description = "Pedido inválido", content = { @Content }),
    })
    @PostMapping
    public ResponseEntity<Pedido> montagemPedido(@RequestBody PedidoRequest request) {
        if(Objects.isNull(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Pedido pedido = this.pedidoEmAndamentoDTOMapper.toPedido(request);
        pedido = this.pedidoEmAndamentoPort.montaPedido(pedido, request.getCpfCliente());
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }
}
