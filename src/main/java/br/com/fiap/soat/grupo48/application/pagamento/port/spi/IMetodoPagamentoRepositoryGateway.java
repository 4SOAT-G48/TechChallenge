package br.com.fiap.soat.grupo48.application.pagamento.port.spi;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;

import java.util.List;
import java.util.UUID;

public interface IMetodoPagamentoRepositoryGateway {
    long buscarQuantidade();

    List<MetodoPagamento> buscarTodos();
    MetodoPagamento buscarPeloCodigo(UUID codigo);

    MetodoPagamento salvar(UUID codigo, String nome, TipoPagamento tipoPagamento, String urlImagem);
}
