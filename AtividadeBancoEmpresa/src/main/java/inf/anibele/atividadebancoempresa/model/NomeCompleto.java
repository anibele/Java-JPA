package inf.anibele.atividadebancoempresa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class NomeCompleto implements Serializable {
    @Column(name = "Pnome", length = 15)
    private String pNome;

    @Column(name = "Minicial", length = 1)
    private String mInicial;

    @Column(name = "Uname", length = 15)
    private String uNome;
}
