package br.edu.ufcg.computacao.si1.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ufcg.computacao.si1.enums.UserRole;

@Entity
@Table(name = "tb_user")
public class User {

	private final double INITIAL_CREDIT = 0.0;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
    private Long id;

	@OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER, orphanRemoval = true)
	@PrimaryKeyJoinColumn
    private Name name;

    @Column(name = "email", unique = true)
    @NotNull(message = "User email can not be null.")
    @NotEmpty(message = "User email can not be empty.")
    private String email;

    @Column(name = "password")
    @NotNull(message = "User password can not be null.")
    @NotEmpty(message = "User password can not be empty")
    private String password;

    @Column(name="role")
    @NotNull(message = "User role can not be null.")
	@Enumerated(EnumType.STRING)
    private UserRole role;

	@Column(name = "credit")
	@NotNull(message = "User credit can not be null.")
    private double credit;


    public User() {
        
    }

    public User(String firstName, String lastName, String email, String password, UserRole role) {
        this.name = new Name(firstName, lastName);
        this.email = email;
        this.password = validatePassword(password);
        this.role = role;
        this.credit = INITIAL_CREDIT;
    }

	public Long getId() {
		return id;
	}



	public String getEmail() {
		return email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public void increaseCredit(double amount){
    	this.credit += amount;
	}

	public void discountCredit(double amount){
		this.credit -= amount;
	}


	public boolean authenticate(String password) {
		return this.password.equals(password);
	}
	
	private String validatePassword(String password){
		if(password.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return password;
	}



}
