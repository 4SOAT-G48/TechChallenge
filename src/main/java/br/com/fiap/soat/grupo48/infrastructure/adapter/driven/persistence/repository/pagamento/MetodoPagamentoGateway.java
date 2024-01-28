package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IMetodoPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.MetodoPagamentoEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class MetodoPagamentoGateway implements IMetodoPagamentoRepositoryGateway {

    private final SpringMetodoPagamentoRepository springMetodoPagamentoRepository;
    private final MetodoPagamentoMapper metodoPagamentoMapper;

    public MetodoPagamentoGateway(SpringMetodoPagamentoRepository springMetodoPagamentoRepository, MetodoPagamentoMapper metodoPagamentoMapper) {
        this.springMetodoPagamentoRepository = springMetodoPagamentoRepository;
        this.metodoPagamentoMapper = metodoPagamentoMapper;
    }

    @Override
    public long buscarQuantidade() {
        return this.springMetodoPagamentoRepository.count();
    }

    @Override
    public MetodoPagamento salvar(UUID codigo, String nome, TipoPagamento tipoPagamento, String urlImagem) {
        MetodoPagamentoEntity metodoPagamentoEntity;
        if (Objects.isNull(codigo)) {
            metodoPagamentoEntity = new MetodoPagamentoEntity(codigo, nome, tipoPagamento, urlImagem);
        } else {
            metodoPagamentoEntity = this.springMetodoPagamentoRepository.findById(codigo).get();
            metodoPagamentoEntity.atualizar(nome,tipoPagamento,urlImagem);
        }
        MetodoPagamentoEntity save = this.springMetodoPagamentoRepository.save(metodoPagamentoEntity);
        return this.metodoPagamentoMapper.toMetodoPagamento(save);
    }
}
