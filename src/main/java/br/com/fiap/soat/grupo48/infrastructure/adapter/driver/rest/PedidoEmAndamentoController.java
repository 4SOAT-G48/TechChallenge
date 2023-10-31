package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.grupo48.application.pedido.dto.PedidoDto;
import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoEmAndamentoPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/pedidoemandamento")
public class PedidoEmAndamentoController {

    private final PedidoEmAndamentoPort pedidoEmAndamentoPort;

    public PedidoEmAndamentoController(PedidoEmAndamentoPort pedidoEmAndamentoPort) {
        this.pedidoEmAndamentoPort = pedidoEmAndamentoPort;
    }

    @PostMapping
    public ResponseEntity<PedidoDto> montagemPedido(@RequestBody PedidoDto pedidoDto) {
        if(Objects.isNull(pedidoDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PedidoDto pedido = this.pedidoEmAndamentoPort.montaPedido(pedidoDto);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }
}
