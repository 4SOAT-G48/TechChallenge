package br.com.fiap.soat.grupo48.infrastructure.config;

import br.com.fiap.soat.grupo48.application.produto.port.api.ProdutoServicePort;
import br.com.fiap.soat.grupo48.application.produto.port.spi.ProdutoRepositoryPort;
import br.com.fiap.soat.grupo48.application.produto.usecase.ManutecaoProdutoUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProdutoServicePort produtoService(ProdutoRepositoryPort produtoRepositoryPort) {
        return new ManutecaoProdutoUsecaseImpl(produtoRepositoryPort);
    }
}
