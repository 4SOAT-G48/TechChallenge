package br.com.fiap.soat.grupo48.infrastructure.config;

import br.com.fiap.soat.grupo48.application.pedido.port.api.PedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.pedido.port.spi.PedidoRepositoryPort;
import br.com.fiap.soat.grupo48.application.pedido.usecase.PedidoEmAndamentoUseCaseImpl;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoPedidoEmAndamentoPort;
import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoPort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.ProdutoPedidoRepositoryPort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.ProdutoRepositoryPort;
import br.com.fiap.soat.grupo48.application.produto.usecase.ManutecaoProdutoUsecaseImpl;
import br.com.fiap.soat.grupo48.application.produto.usecase.ProdutoPedidoUseCaseImpl;
import br.com.fiap.soat.grupo48.application.cliente.port.api.ClientePort;
import br.com.fiap.soat.grupo48.application.cliente.port.spi.ClienteRepositoryPort;
import br.com.fiap.soat.grupo48.application.cliente.usecase.ClienteUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProdutoPort manutencaoProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort) {
        return new ManutecaoProdutoUsecaseImpl(produtoRepositoryPort);
    }

    @Bean
    ProdutoPedidoEmAndamentoPort produtoPedidoUseCase(ProdutoPedidoRepositoryPort produtoPedidoRepositoryPort) {
        return new ProdutoPedidoUseCaseImpl(produtoPedidoRepositoryPort);
    }

    @Bean
    ClientePort clienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteUsecaseImpl(clienteRepositoryPort);
    }

    @Bean
    PedidoEmAndamentoPort pedidoUseCase(PedidoRepositoryPort pedidoRepositoryPort, ClienteRepositoryPort clienteRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort) {
        return new PedidoEmAndamentoUseCaseImpl(pedidoRepositoryPort, clienteRepositoryPort, produtoRepositoryPort);
    }
}
