package inf.anibele.atividadebancoempresa.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @Column(name = "dnumero")
    private Integer numero;

    @Column(name = "dnome", nullable = false, unique = true, length = 15)
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio_gerente")
    private Date dataInicioGerente;

    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> listaFuncionarios;

    @OneToOne
    @JoinColumn(name = "cpf_gerente")
    private Funcionario gerente;

    @OneToMany(mappedBy = "departamento")
    private List<Projeto> projetos;

    @OneToMany(mappedBy = "departamento")
    private List<LocalizacaoDepartamento> localizacoes;

}