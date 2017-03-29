package br.edu.ufcg.computacao.si1.models.user;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gustavo on 20/03/17.
 */

@Entity
@Table(name="tb_candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @JoinColumn(name = "name_id")
    @NotNull(message = "User name can not be null.")
    private String name;

    @Column(name = "email", unique = true)
    @NotNull(message = "User email can not be null.")
    @NotEmpty(message = "User email can not be empty.")
    private String email;

    public Candidate() {}

    public Candidate(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
