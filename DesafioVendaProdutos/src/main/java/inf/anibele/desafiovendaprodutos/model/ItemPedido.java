package inf.anibele.desafiovendaprodutos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @EmbeddedId
    private ItemPedidoId id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produto;

    @Column()
    private Integer quantidade;

    @Column()
    private BigDecimal precoUnitario;

    @Column()
    private BigDecimal subtotal;
}
