package inf.anibele.atividadebancoempresa.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DependenteId implements Serializable {

    @Column(name = "fcpf", length = 11)
    private String fcpf;

    @Column(name = "nome_dependente", length = 15)
    private String nomeDependente;

}