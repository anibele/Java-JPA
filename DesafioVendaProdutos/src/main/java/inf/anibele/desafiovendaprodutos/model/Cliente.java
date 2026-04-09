package inf.anibele.desafiovendaprodutos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Column()
    private String nome;

    @Column()
    private String email;

    @Column()
    private String telefone;

    @Column()
    private String endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @OneToOne(mappedBy = "cliente")
    private Carrinho carrinho;

}
