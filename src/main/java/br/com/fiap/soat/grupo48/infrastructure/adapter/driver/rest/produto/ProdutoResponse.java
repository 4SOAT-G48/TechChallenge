package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto;

import br.com.fiap.soat.grupo48.application.produto.model.Categoria;
import br.com.fiap.soat.grupo48.application.produto.model.SituacaoProduto;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Jacksonized
@Builder(toBuilder = true)
@AllArgsConstructor
public class ProdutoResponse {

  private UUID codigo;
  private String nome;
  private Categoria categoria;
  private Double preco;
  private String descricao;
  private SituacaoProduto situacao;
  private List<String> imagens;

  public List<String> getImagens() {
    if (imagens == null) {
      imagens = new ArrayList<>();
    }
    return imagens;
  }
}
