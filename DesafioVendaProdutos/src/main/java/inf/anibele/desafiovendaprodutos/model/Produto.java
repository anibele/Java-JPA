package inf.anibele.desafiovendaprodutos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Long id;

    @Column()
    private String nome;

    @Column()
    private String descricao;

    @Column()
    private BigDecimal preco;

    @Column()
    private String categoria;

    @Column()
    private Boolean disponivel;

    @Column()
    private String imagemUrl;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itensPedido;

    @OneToMany(mappedBy = "produto")
    private List<ItemCarrinho> itensCarrinho;

}
