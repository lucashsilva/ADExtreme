package br.edu.ufcg.computacao.si1.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.computacao.si1.models.User;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

	@Autowired
	private UserController controller;
    
	private User validUser, userWithInvalidName;

	@Before
	public void setUp() {
		validUser = new User("Odravison", "Ojunior4@fake.com", "hao123", "user");
		userWithInvalidName = new User("", "Ojunior4@fake.com", "hao123", "user");
	}
	
	@Test
	public void testCreateUserSuccessfully() {
		assertEquals(new ResponseEntity<>(HttpStatus.CREATED), controller.addUser(validUser));
	}

	@Test
	public void testCreateUserUnsuccessfully() {
		assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), controller.addUser(userWithInvalidName));
	}
	
	@Test
	public void duplicatedUserTest() {
		controller.addUser(validUser);
		
		assertEquals(new ResponseEntity<>(HttpStatus.CONFLICT), controller.addUser(validUser));
	}
	
	

}
