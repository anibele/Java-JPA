package inf.anibele.desafiovendaprodutos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ItemCarrinhoId implements Serializable {
    @Column(name = "carrinho_id")
    private Long carrinhoId;

    @Column(name = "produto_id")
    private Long produtoId;
}
