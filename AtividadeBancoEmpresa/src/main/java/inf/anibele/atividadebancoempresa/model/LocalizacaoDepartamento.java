package inf.anibele.atividadebancoempresa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "localizacao_dep")
public class LocalizacaoDepartamento {

    @EmbeddedId
    private LocalizacaoDepartamentoId id;

    @ManyToOne
    @JoinColumn(name = "dnumero", insertable = false, updatable = false)
    private Departamento departamento;
    
}
