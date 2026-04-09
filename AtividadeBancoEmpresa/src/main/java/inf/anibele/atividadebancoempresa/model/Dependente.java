package inf.anibele.atividadebancoempresa.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dependente")
public class Dependente {

    @EmbeddedId
    private DependenteId id;

    @ManyToOne
    @JoinColumn(name = "fcpf", insertable = false, updatable = false)
    private Funcionario funcionario;

    @Column(length = 1)
    private String sexo;

    @Temporal(TemporalType.DATE)
    private Date datanasc;

    @Column(name = "parentesco", length = 8)
    private String parentesco;

}