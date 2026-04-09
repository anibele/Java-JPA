package inf.anibele.desafiovendaprodutos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column()
    private String username;

    @Column()
    private String senha;

    @Column()
    private String nome;

    @Column()
    private String email;

}
