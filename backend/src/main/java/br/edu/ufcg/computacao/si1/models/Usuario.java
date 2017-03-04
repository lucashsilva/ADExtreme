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
@Table(name = "tb_usuario")
public class Usuario extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column
    @NotNull(message = "Nome do usuario nao pode ser nulo.")
    @NotEmpty(message = "Nome do usuario nao pode ser vazio.")
    private String nome;
    @Column(unique = true)
    @NotNull(message = "Email do usuario nao pode ser nulo.")
    @NotEmpty(message = "Email do usuario nao pode ser vazio.")
    private String email;
    @Column
    @NotNull(message = "Senha do usuario nao pode ser nula.")
    @NotEmpty(message = "Senha do usuario nao pode ser vazia.")
    private String senha;
    @Column
    @NotNull(message = "Role do usuario nao pode ser nulo.")
    @NotEmpty(message = "Role do usuario nao pode ser vazio.")
    private String role;

    public Usuario() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }

    public Usuario(String nome, String email, String senha, String role) {

        super(email, senha, AuthorityUtils.createAuthorityList(role));

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public boolean autenticar(String senha) {
		return this.senha.equals(senha);
	}

}
