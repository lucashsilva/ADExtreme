package br.edu.ufcg.computacao.si1.util;

import br.edu.ufcg.computacao.si1.models.user.MinimalUser;
import br.edu.ufcg.computacao.si1.models.user.User;

public class Util {
	public static MinimalUser minimalUserFor(User user) {
		return new MinimalUser(user);
	}
}
