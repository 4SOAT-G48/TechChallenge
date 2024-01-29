package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PagamentoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PedidoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.MetodoPagamentoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PagamentoMapper {

    private final MetodoPagamentoMapper metodoPagamentoMapper;

    @Autowired
    public PagamentoMapper(MetodoPagamentoMapper metodoPagamentoMapper) {
        this.metodoPagamentoMapper = metodoPagamentoMapper;
    }

    public Pagamento toPagamento(PagamentoEntity pagamentoEntity) {
      return new Pagamento(pagamentoEntity.getCodigo(),
              this.metodoPagamentoMapper.toMetodoPagamento(pagamentoEntity.getMetodoPagamento()),
              pagamentoEntity.getSituacaoPagamento());
    }
}
