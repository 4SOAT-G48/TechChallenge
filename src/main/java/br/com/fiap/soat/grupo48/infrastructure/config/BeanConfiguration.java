package br.com.fiap.soat.grupo48.infrastructure.config;

import br.com.fiap.soat.grupo48.application.pagamento.port.api.IMetodoPagamentoPort;
import br.com.fiap.soat.grupo48.application.pagamento.port.api.IPagamentoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IConsultaInformacaoPagamentoIntegartionGateway;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IMetodoPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pagamento.port.spi.IPagamentoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pagamento.usecase.MetodoPagamentoUsecaseImpl;
import br.com.fiap.soat.grupo48.application.pagamento.usecase.PagamentoSituacaoUsecaseImpl;
import br.com.fiap.soat.grupo48.application.pedido.port.api.IPedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.api.IPedidoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pedido.usecase.PedidoEmAndamentoUseCaseImpl;
import br.com.fiap.soat.grupo48.application.pedido.usecase.PedidoSituacaoUseCaseImpl;
import br.com.fiap.soat.grupo48.application.produto.port.api.IProdutoPedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.produto.port.api.IProdutoPort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoPedidoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.produto.port.spi.IProdutoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.produto.usecase.ManutecaoProdutoUsecaseImpl;
import br.com.fiap.soat.grupo48.application.produto.usecase.ProdutoPedidoUseCaseImpl;
import br.com.fiap.soat.grupo48.application.cliente.port.api.IClientePort;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.IClienteRepositoryGateway;
import br.com.fiap.soat.grupo48.application.cliente.usecase.ClienteUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    IProdutoPort manutencaoProdutoUseCase(IProdutoRepositoryGateway produtoRepositoryGateway) {
        return new ManutecaoProdutoUsecaseImpl(produtoRepositoryGateway);
    }

    @Bean
    IProdutoPedidoEmAndamentoPort produtoPedidoUseCase(IProdutoPedidoRepositoryGateway produtoPedidoRepositoryGateway) {
        return new ProdutoPedidoUseCaseImpl(produtoPedidoRepositoryGateway);
    }

    @Bean
    IClientePort clienteUseCase(IClienteRepositoryGateway clienteRepositoryGateway) {
        return new ClienteUsecaseImpl(clienteRepositoryGateway);
    }

    @Bean
    IPedidoEmAndamentoPort pedidoUseCase(IPedidoRepositoryGateway pedidoRepositoryGateway,
                                         IClienteRepositoryGateway clienteRepositoryGateway,
                                         IProdutoRepositoryGateway produtoRepositoryGateway,
                                         IMetodoPagamentoRepositoryGateway metodoPagamentoRepositoryGateway,
                                         IPagamentoRepositoryGateway pagamentoRepositoryGateway) {
        return new PedidoEmAndamentoUseCaseImpl(pedidoRepositoryGateway, clienteRepositoryGateway, produtoRepositoryGateway,metodoPagamentoRepositoryGateway, pagamentoRepositoryGateway);
    }

    @Bean
    IPedidoSituacaoPort pedidoSituacaoPort(IPedidoRepositoryGateway pedidoRepositoryGateway) {
        return  new PedidoSituacaoUseCaseImpl(pedidoRepositoryGateway);
    }

    @Bean
    IMetodoPagamentoPort metodoPagamentoUseCase(IMetodoPagamentoRepositoryGateway metodoPagamentoRepositoryGateway) {
        return new MetodoPagamentoUsecaseImpl(metodoPagamentoRepositoryGateway);
    }

    @Bean
    IPagamentoSituacaoPort pagamentoSituacaoUseCase(IPagamentoRepositoryGateway pagamentoRepositoryGateway, IPedidoRepositoryGateway pedidoRepositoryGateway, IConsultaInformacaoPagamentoIntegartionGateway consultaInformacaoPagamentoIntegartionGateway){
        return new PagamentoSituacaoUsecaseImpl(pagamentoRepositoryGateway,pedidoRepositoryGateway, consultaInformacaoPagamentoIntegartionGateway);
    }
}
