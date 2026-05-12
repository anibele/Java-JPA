package inf.anibele.jpa_query_methods.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
