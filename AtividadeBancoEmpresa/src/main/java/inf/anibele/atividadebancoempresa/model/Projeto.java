package inf.anibele.atividadebancoempresa.model;
import jakarta.persistence.*;

@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @Column(name = "Projnumero")
    private Integer numero;

    @Column(name = "Projnome", length = 15)
    private String nome;

    @Column(name = "Projlocal", length = 15)
    private String local;

    @ManyToOne
    @JoinColumn(name = "dnum")
    private Departamento departamento;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
