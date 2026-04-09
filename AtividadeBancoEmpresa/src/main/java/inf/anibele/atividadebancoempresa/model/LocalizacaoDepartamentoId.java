package inf.anibele.atividadebancoempresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LocalizacaoDepartamentoId implements Serializable {

    @Column(name = "dnumero")
    private Integer dnumero;

    @Column(name = "dlocal", length = 15)
    private String dlocal;

}
