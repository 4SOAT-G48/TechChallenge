package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IMetodoPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.MetodoPagamentoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<MetodoPagamento> buscarTodos() {
        List<MetodoPagamentoEntity> all = this.springMetodoPagamentoRepository.findAll();
        return all.stream().map(this.metodoPagamentoMapper::toMetodoPagamento).collect(Collectors.toList());
    }

    @Override
    public MetodoPagamento buscarPeloCodigo(UUID codigo) {
        Optional<MetodoPagamentoEntity> byId = this.springMetodoPagamentoRepository.findById(codigo);
        if (byId.isPresent()) {
            MetodoPagamentoEntity metodoPagamentoEntity = byId.get();
            return this.metodoPagamentoMapper.toMetodoPagamento(metodoPagamentoEntity);
        } else {
            return null;
        }
    }

    @Override
    public MetodoPagamento salvar(UUID codigo, String nome, TipoPagamento tipoPagamento, String urlImagem) {
        MetodoPagamentoEntity metodoPagamentoEntity;
        if (Objects.isNull(codigo)) {
            metodoPagamentoEntity = new MetodoPagamentoEntity(codigo, nome, tipoPagamento, urlImagem);
        } else {
            metodoPagamentoEntity = this.springMetodoPagamentoRepository.findById(codigo).get();
            metodoPagamentoEntity.atualizar(nome, tipoPagamento, urlImagem);
        }
        MetodoPagamentoEntity save = this.springMetodoPagamentoRepository.save(metodoPagamentoEntity);
        return this.metodoPagamentoMapper.toMetodoPagamento(save);
    }
}
