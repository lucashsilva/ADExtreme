package br.edu.ufcg.computacao.si1.models;

import org.junit.Test;

import br.edu.ufcg.computacao.si1.models.User;

public class UserTest {
	
	@SuppressWarnings("unused")
	private User userWithInvalidEmail, userWithInvalidPassword, userWithInvalidRole, userWithNullEmail, userWithNullPassword, userWithNullRole;
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidEmailTest() {
		userWithInvalidEmail = new User("Odravison", "odra","", "hao123", "user");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullEmailTest() {
		userWithInvalidEmail = new User("Odravison",  "odra",null, "hao123", "user");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidPasswordTest() {
		userWithInvalidEmail = new User("Odravison", "odra", "Ojunior4@fake.com", "", "user");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullPasswordTest() {
		userWithInvalidEmail = new User("Odravison",  "odra","Ojunior4@fake.com", null, "user");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidRoleTest() {
		userWithInvalidEmail = new User("Odravison", "odra", "Ojunior4@fake.com", "hao123", "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullRoleTest() {
		userWithInvalidEmail = new User("Odravison",  "odra","Ojunior4@fake.com", "hao123", null);
	}
}
