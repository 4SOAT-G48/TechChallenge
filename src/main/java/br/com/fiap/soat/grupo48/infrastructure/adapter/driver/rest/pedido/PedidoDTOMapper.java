package br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pedido;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import br.com.fiap.soat.grupo48.application.pedido.model.Pedido;
import br.com.fiap.soat.grupo48.application.pedido.model.PedidoItem;
import br.com.fiap.soat.grupo48.application.pedido.model.SituacaoPedido;
import br.com.fiap.soat.grupo48.application.produto.model.Produto;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.pagamento.PagamentoDTOMapper;
import br.com.fiap.soat.grupo48.infrastructure.adapter.driver.rest.produto.ProdutoDTOMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoDTOMapper {

  @Autowired
  private ProdutoDTOMapper produtoDTOMapper;

  @Autowired
  private PagamentoDTOMapper pagamentoDTOMapper;

  public Pedido toPedido(PedidoRequest request) {
    Cliente cliente = null;
    SituacaoPedido situacao = this.toSituacaoPedido(request.getSituacao());
    List<PedidoItem> itens = this.toListPedidoItensWithProdutoReferenciado(request.getItens());
    Pagamento pagamento = this.pagamentoDTOMapper.toPagamento(request.getPagamento());
    return new Pedido(request.getCodigo(), cliente, situacao, request.getIdentificacao(), itens, pagamento);
  }

  public Pedido toPedidoCriacao(PedidoCriacaoRequest request) {
    Cliente cliente = null;
    SituacaoPedido situacao = this.toSituacaoPedido(request.getSituacao());
    List<PedidoItem> itens = this.toListPedidoItensWithProdutoReferenciado(request.getItens());
    Pagamento pagamentoCriacao = this.pagamentoDTOMapper.toPagamentoCriacao(request.getPagamento());
    return new Pedido(request.getCodigo(), cliente, situacao, request.getIdentificacao(), itens, pagamentoCriacao);
  }

  public SituacaoPedido toSituacaoPedido(String situacao) {
    return SituacaoPedido.valueOf(situacao);
  }

  @SneakyThrows
  private List<PedidoItem> toListPedidoItensWithProdutoReferenciado(List<PedidoItemRequest> itens) {
    return itens.stream().map(this::toPedidoItem).collect(Collectors.toList());
  }

  private PedidoItem toPedidoItem(PedidoItemRequest pedidoItemRequest) {
    Produto produto = this.produtoDTOMapper.toProduto(pedidoItemRequest.getProduto().getCodigo());
    return new PedidoItem(pedidoItemRequest.getCodigo(), produto, pedidoItemRequest.getQuantidade(), pedidoItemRequest.getPrecoUnitario(), pedidoItemRequest.getObservacao());
  }


}
