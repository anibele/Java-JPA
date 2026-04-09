package inf.anibele.desafiovendaprodutos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ItemPedidoId implements Serializable {
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "produto_id")
    private Long produtoId;
}
