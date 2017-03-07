package br.edu.ufcg.computacao.si1.models;

import javax.persistence.*;

@Entity
@Table(name="userCredentials")
@SequenceGenerator(name="CREDENTIALS", sequenceName="CREDENTIALS", allocationSize=1, initialValue=0)
public class UserCredentials {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CREDENTIALS")
	private Long id;
	@Column
	private String email;
	@Column
	private String password;

	public UserCredentials() {
	}

	public UserCredentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
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

}