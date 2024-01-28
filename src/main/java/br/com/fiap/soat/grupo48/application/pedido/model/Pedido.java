package br.com.fiap.soat.grupo48.application.pedido.model;

import br.com.fiap.soat.grupo48.application.cliente.model.Cliente;
import br.com.fiap.soat.grupo48.application.pagamento.model.Pagamento;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Pedido {



    @Getter
    @Setter
    private UUID codigo;

    @Getter
    @Setter
    private Cliente cliente;

    @Getter
    @Setter
    private SituacaoPedido situacao;

    @Getter
    @Setter
    private String identificacao;

    @Getter
    @Setter
    private List<PedidoItem> itens;

    @Getter
    @Setter
    private Pagamento pagamento;

    @Getter
    @Setter
    private Date dataCriacao;

    @Getter
    @Setter
    private Date dataAtualizacao;

    public Pedido(UUID codigo, Cliente cliente, SituacaoPedido situacao, String identificacao, List<PedidoItem> itens) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.situacao = situacao;
        this.identificacao = identificacao;
        this.itens = itens;
    }

}
