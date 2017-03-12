package br.edu.ufcg.computacao.si1.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Username can not be null.")
    @NotEmpty(message = "Username can not be empty.")
    private String name;

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
    @NotEmpty(message = "User role can not be empty.")
	@Enumerated(EnumType.STRING)
    private UserRole role;

	@Column(name = "credit")
	@NotNull(message = "User credit can not be null.")
    private double credit;

	@Column
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	@NotNull(message = "User advertisings can not be null.")
    private Set<Advertising> advertisings;

    public User() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }

    public User(String name, String email, String password, UserRole role) {

        super(email, password, AuthorityUtils.createAuthorityList(role.name()));

        this.name = name;
        this.email = email;
        this.password = validatePassword(password);
        this.role = role;
        this.credit = 0.0;
        this.advertisings = new HashSet<>();
    }

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<Advertising> getAdvertisings() {
		return advertisings;
	}

	public void setAdvertisings(Set<Advertising> advertisings) {
		if(advertisings == null)
			advertisings = new HashSet<>();
		this.advertisings = advertisings;
	}

	public boolean authenticate(String password) {
		return this.password.equals(password);
	}
	
	private String validatePassword(String password){
		if(password.isEmpty())
			throw new IllegalArgumentException();
		return password;
	}


}
