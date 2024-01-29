package br.com.fiap.soat.grupo48.infrastructure.adapter.driven.persistence.event;

import br.com.fiap.soat.grupo48.application.pagamento.model.MetodoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.model.TipoPagamento;
import br.com.fiap.soat.grupo48.application.pagamento.port.api.IMetodoPagamentoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final IMetodoPagamentoPort metodoPagamentoPort;

    @Autowired
    public DataLoader(IMetodoPagamentoPort metodoPagamentoPort) {

        this.metodoPagamentoPort = metodoPagamentoPort;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() != null) {
            return;
        }

        this.carregaMetodoPagamento();
    }

    private void carregaMetodoPagamento() {
        if (this.metodoPagamentoPort.buscarQuantidade()==0) {
            MetodoPagamento metodoPagamento = new MetodoPagamento(null, "Mercado Pago", TipoPagamento.MERCADO_PAGO, "");
            this.metodoPagamentoPort.salvar(metodoPagamento);
        }
    }
}
