package br.edu.ufcg.computacao.si1.models.name;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_name")
public class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    
    @Column(name = "first_name")
    @NotNull(message = "User first name can not be null.")
    @NotEmpty(message = "User last name can not be empty.")
    private String firstName;
    
    @Column(name = "last_name")
    @NotNull(message = "User last name can not be null.")
    @NotEmpty(message = "User last name can not be empty.")
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString(){
        return firstName + " " + lastName;
    }

}
