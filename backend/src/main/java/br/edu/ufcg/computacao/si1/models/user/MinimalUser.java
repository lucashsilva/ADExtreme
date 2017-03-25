package br.edu.ufcg.computacao.si1.models.user;

import br.edu.ufcg.computacao.si1.models.name.Name;

public class MinimalUser {
	private User user;
	
	public MinimalUser(User user) {
		this.user = user;
	}
	
	public Name getName() {
		return this.user.getName();
	}
	
	public long getId() {
		return this.user.getId();
	}

}
