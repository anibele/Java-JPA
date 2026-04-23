package inf.anibele.consultorioapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;
    private String crm;
}
