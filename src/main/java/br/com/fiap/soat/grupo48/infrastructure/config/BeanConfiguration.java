package br.com.fiap.soat.grupo48.infrastructure.config;

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
import br.com.fiap.soat.grupo48.application.cliente.port.api.ClientePort;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.IClienteRepositoryGateway;
import br.com.fiap.soat.grupo48.application.cliente.usecase.ClienteUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    IProdutoPort manutencaoProdutoUseCase(IProdutoRepositoryGateway IProdutoRepositoryGateway) {
        return new ManutecaoProdutoUsecaseImpl(IProdutoRepositoryGateway);
    }

    @Bean
    IProdutoPedidoEmAndamentoPort produtoPedidoUseCase(IProdutoPedidoRepositoryGateway IProdutoPedidoRepositoryGateway) {
        return new ProdutoPedidoUseCaseImpl(IProdutoPedidoRepositoryGateway);
    }

    @Bean
    ClientePort clienteUseCase(IClienteRepositoryGateway IClienteRepositoryGateway) {
        return new ClienteUsecaseImpl(IClienteRepositoryGateway);
    }

    @Bean
    IPedidoEmAndamentoPort pedidoUseCase(IPedidoRepositoryGateway IPedidoRepositoryGateway, IClienteRepositoryGateway IClienteRepositoryGateway, IProdutoRepositoryGateway IProdutoRepositoryGateway) {
        return new PedidoEmAndamentoUseCaseImpl(IPedidoRepositoryGateway, IClienteRepositoryGateway, IProdutoRepositoryGateway);
    }

    @Bean
    IPedidoSituacaoPort pedidoSituacaoPort(IPedidoRepositoryGateway IPedidoRepositoryGateway) {
        return  new PedidoSituacaoUseCaseImpl(IPedidoRepositoryGateway);
    }
}
