package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.repository.pagamento;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.SituacaoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.MetodoPagamentoEntity;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.entity.PagamentoEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class PagamentoGateway implements IPagamentoRepositoryGateway {

    private final SpringPagamentoRepository springPagamentoRepository;

    private final PagamentoMapper pagamentoMapper;

    public PagamentoGateway(SpringPagamentoRepository springPagamentoRepository, PagamentoMapper pagamentoMapper) {
        this.springPagamentoRepository = springPagamentoRepository;
        this.pagamentoMapper = pagamentoMapper;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        PagamentoEntity pagamentoEntity = null;
        if (Objects.isNull(pagamento.getCodigo())) {
            MetodoPagamento metodoPagamento = pagamento.getMetodoPagamento();
            MetodoPagamentoEntity metodoPagamentoEntity = new MetodoPagamentoEntity(metodoPagamento.getCodigo(), metodoPagamento.getNome(), metodoPagamento.getTipoPagamento(), metodoPagamento.getUrlImagem());
            pagamentoEntity = new PagamentoEntity(null, metodoPagamentoEntity, pagamento.getSituacaoPagamento(), null);
        } else {
            pagamentoEntity = this.springPagamentoRepository.findById(pagamento.getCodigo()).get();
            pagamentoEntity.setSituacaoPagamento(pagamento.getSituacaoPagamento());
        }

        Pagamento pagamentoCriado = this.pagamentoMapper.toPagamento(this.springPagamentoRepository.save(pagamentoEntity));

        return pagamentoCriado;
    }

    @Override
    public Pagamento buscarPeloCodigo(UUID codigo) {
        Optional<PagamentoEntity> byId = this.springPagamentoRepository.findById(codigo);
        if (byId.isPresent()) {
            PagamentoEntity pagamentoEntity = byId.get();
            return this.pagamentoMapper.toPagamento(pagamentoEntity);
        } else {
            return null;
        }
    }

    @Override
    public Pagamento atualizaSituacao(UUID codigo, SituacaoPagamento situacaoPagamento) {
        Optional<PagamentoEntity> byId = this.springPagamentoRepository.findById(codigo);
        if (byId.isPresent()) {
            PagamentoEntity pagamentoEntity = byId.get();
            pagamentoEntity.setSituacaoPagamento(situacaoPagamento);
            return this.pagamentoMapper.toPagamento(this.springPagamentoRepository.save(pagamentoEntity));
        } else {
            return null;
        }
    }
}
