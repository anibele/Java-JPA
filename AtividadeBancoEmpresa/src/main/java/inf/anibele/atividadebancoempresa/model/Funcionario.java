package inf.anibele.atividadebancoempresa.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @Column(name = "cpf")
    private String cpf;

    @Embedded
    private NomeCompleto nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "Datanasc")
    private Date datanasc;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "salario")
    private Double salario;

    @OneToMany(mappedBy = "funcionario")
    private List<Dependente> dependentes;

    @ManyToOne
    @JoinColumn(name = "dnumero")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "cpf_supervisor")
    private Funcionario supervisor;

    @OneToMany(mappedBy = "supervisor")
    private List<Funcionario> comuns;

    @OneToMany(mappedBy = "funcionario")
    private List<TrabalhaEm> trabalhos;

    @OneToOne(mappedBy = "gerente")
    private Departamento dptGerenciado;

}
