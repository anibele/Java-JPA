package inf.anibele.desafiovendaprodutos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pagamento_id")
    private Long id;

    @Column()
    private String metodoPagamento;

    @Column()
    private BigDecimal valor;

    @Column()
    private String status;

    @Column()
    private String transacaoId;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
