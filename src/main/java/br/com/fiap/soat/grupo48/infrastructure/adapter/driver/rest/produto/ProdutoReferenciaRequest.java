package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoReferenciaRequest {

    private UUID codigo;
}
