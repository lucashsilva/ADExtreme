package br.edu.ufcg.computacao.si1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.authority.AuthorityUtils;

@Entity
@Table(name = "tb_user")
public class User extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column
    @NotNull(message = "Username can not be null.")
    @NotEmpty(message = "Username can not be empty.")
    private String name;
    @Column(unique = true)
    @NotNull(message = "User email can not be null.")
    @NotEmpty(message = "User email can not be empty.")
    private String email;
    @Column
    @NotNull(message = "User password can not be null.")
    @NotEmpty(message = "User password can not be empty")
    private String password;
    @Column
    @NotNull(message = "User role can not be null.")
    @NotEmpty(message = "User role can not be emptt.")
    private String role;

    public User() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }

    public User(String name, String email, String password, String role) {

        super(email, password, AuthorityUtils.createAuthorityList(role));

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

	public String getRole() {
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

	public void setRole(String role) {
		this.role = role;
	}

	public boolean authenticate(String senha) {
		return this.password.equals(senha);
	}

}
