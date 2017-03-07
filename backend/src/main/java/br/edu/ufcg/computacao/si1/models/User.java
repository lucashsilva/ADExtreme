package br.edu.ufcg.computacao.si1.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User extends org.springframework.security.core.userdetails.User implements Authentication {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column
    @NotNull(message = "Name can not be null.")
    @NotEmpty(message = "Name can not be empty.")
    private String name;

	@Column
	@NotNull(message = "Username can not be null.")
	@NotEmpty(message = "Username can not be empty.")
	private String username;

    @Column
    @NotNull(message = "User role can not be null.")
    @NotEmpty(message = "User role can not be emptt.")
    private String role;

    @Column
	private boolean authenticated;

	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	@Autowired
	private UserCredentials userCredentials;

	@Column
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Advertising> advertisings;

    public User() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
		this.userCredentials = new UserCredentials();
    }

    public User(String name, String username, String email, String password, String role) {

        super(username, password, AuthorityUtils.createAuthorityList(role));

        this.username = username;
        this.userCredentials = new UserCredentials();
        this.name = name;
        this.userCredentials.setEmail(email);
        this.userCredentials.setPassword(validatePassword(password));
        this.role = role;
        this.authenticated = false;
    }

	public User(String username) {
		super(username, "default", AuthorityUtils.createAuthorityList("USER"));
		this.username = username;
		this.authenticated = false;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getEmail() {
		return userCredentials.getEmail();
	}

	@Override
	public String getPassword() {
		return userCredentials.getPassword();
	}

	public String getRole() {
		return role;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.userCredentials.setEmail(email);
	}

	public void setPassword(String password) {
		this.userCredentials.setPassword(validatePassword(password));
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean authenticate(String password) {
		return this.userCredentials.getPassword().equals(password);
	}
	
	private String validatePassword(String password){
		if(password.isEmpty())
			throw new IllegalArgumentException();
		return password;
	}

	public Set<Advertising> getAdvertisings() {
		return advertisings;
	}

	public void addAdvertisings(Set<Advertising> advertisings) {
		if(advertisings == null)
			advertisings = new HashSet<>();
		this.advertisings = advertisings;
	}

	@Override
	public Object getCredentials() {
		return this.userCredentials;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	public void setAuthenticated(boolean b) throws IllegalArgumentException {
		this.authenticated = b;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
