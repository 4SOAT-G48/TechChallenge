package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.MetodoPagamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class MetodoPagamentoMapper {

    public MetodoPagamento toMetodoPagamento(MetodoPagamentoEntity metodoPagamentoEntity) {
        return new MetodoPagamento(metodoPagamentoEntity.getCodigo(),
                                    metodoPagamentoEntity.getNome(),
                                    metodoPagamentoEntity.getTipoPagamento(),
                                    metodoPagamentoEntity.getUrlImagem());
    }
}
