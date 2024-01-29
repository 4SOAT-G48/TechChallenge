package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.MetodoPagamentoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.MetodoPagamentoRequest;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.MetodoPagamentoResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MetodoPagamentoMapper {

    public MetodoPagamento toMetodoPagamento(MetodoPagamentoEntity metodoPagamentoEntity) {
        return new MetodoPagamento(metodoPagamentoEntity.getCodigo(),
                                    metodoPagamentoEntity.getNome(),
                                    metodoPagamentoEntity.getTipoPagamento(),
                                    metodoPagamentoEntity.getUrlImagem());
    }

    public List<MetodoPagamentoResponse> toListResponse(List<MetodoPagamento> metodoPagamentos) {
        return metodoPagamentos.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private MetodoPagamentoResponse toResponse(MetodoPagamento metodoPagamento) {
        return new MetodoPagamentoResponse(metodoPagamento.getCodigo(),metodoPagamento.getNome(), metodoPagamento.getTipoPagamento(),metodoPagamento.getUrlImagem());
    }
}
