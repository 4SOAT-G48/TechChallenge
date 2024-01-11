package br.com.fiap.soat.grupo48.infrastructure.config;

import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoSituacaoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.IPedidoRepositoryGateway;
import br.com.fiap.soat.grupo48.application.pedido.usecase.PedidoEmAndamentoUseCaseImpl;
import br.com.fiap.soat.grupo48.application.pedido.usecase.PedidoSituacaoUseCaseImpl;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoPedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoPort;
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
    ProdutoPort manutencaoProdutoUseCase(IProdutoRepositoryGateway IProdutoRepositoryGateway) {
        return new ManutecaoProdutoUsecaseImpl(IProdutoRepositoryGateway);
    }

    @Bean
    ProdutoPedidoEmAndamentoPort produtoPedidoUseCase(IProdutoPedidoRepositoryGateway IProdutoPedidoRepositoryGateway) {
        return new ProdutoPedidoUseCaseImpl(IProdutoPedidoRepositoryGateway);
    }

    @Bean
    ClientePort clienteUseCase(IClienteRepositoryGateway IClienteRepositoryGateway) {
        return new ClienteUsecaseImpl(IClienteRepositoryGateway);
    }

    @Bean
    PedidoEmAndamentoPort pedidoUseCase(IPedidoRepositoryGateway IPedidoRepositoryGateway, IClienteRepositoryGateway IClienteRepositoryGateway, IProdutoRepositoryGateway IProdutoRepositoryGateway) {
        return new PedidoEmAndamentoUseCaseImpl(IPedidoRepositoryGateway, IClienteRepositoryGateway, IProdutoRepositoryGateway);
    }

    @Bean
    PedidoSituacaoPort pedidoSituacaoPort(IPedidoRepositoryGateway IPedidoRepositoryGateway) {
        return  new PedidoSituacaoUseCaseImpl(IPedidoRepositoryGateway);
    }
}
