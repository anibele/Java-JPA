package inf.anibele.atividadebancoempresa.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trabalha_em")
public class TrabalhaEm {

    @EmbeddedId
    private TrabalhaEmId id;

    @ManyToOne
    @JoinColumn(name = "fcpf", insertable = false, updatable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "pnumero", insertable = false, updatable = false)
    private Projeto projeto;

    @Column(name = "horas")
    private BigDecimal horas;

}
