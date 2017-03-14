package br.edu.ufcg.computacao.si1.models;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import org.junit.Test;

import br.edu.ufcg.computacao.si1.models.User;

public class UserTest {
	
	@SuppressWarnings("unused")
	private User userWithInvalidEmail, userWithInvalidPassword, userWithInvalidRole, userWithNullEmail, userWithNullPassword, userWithNullRole;
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidEmailTest() {
		userWithInvalidEmail = new User("Odravison", "Doe", "", "hao123", UserRole.NATURAL_PERSON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullEmailTest() {
		userWithInvalidEmail = new User("Odravison", "Doe", null, "hao123", UserRole.LEGAL_PERSON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidPasswordTest() {
		userWithInvalidEmail = new User("Odravison", "Doe", "Ojunior4@fake.com", "", UserRole.NATURAL_PERSON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullPasswordTest() {
		userWithInvalidEmail = new User("Odravison", "Doe", "Ojunior4@fake.com", null, UserRole.LEGAL_PERSON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidRoleTest() {
		userWithInvalidEmail = new User("Odravison", "Doe", "Ojunior4@fake.com", "hao123", null); //TODO
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullRoleTest() {
		userWithInvalidEmail = new User("Odravison", "Doe", "Ojunior4@fake.com", "hao123", null);
	}
}
