package inf.anibele.desafiovendaprodutos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_carrinho")
public class ItemCarrinho {
    @EmbeddedId
    private ItemCarrinhoId id;

    @ManyToOne
    @JoinColumn(name = "carrinho_id", insertable = false, updatable = false)
    private Carrinho carrinho;

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
