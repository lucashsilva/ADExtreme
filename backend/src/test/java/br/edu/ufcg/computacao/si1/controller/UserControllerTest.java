package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

	@Autowired
	private UserController controller;
	
	@Autowired
	private UserRepository userRepository;
    
	private User validUser, userWithInvalidName;

	@Before
	public void setUp() {
		validUser = new User("Odravison", "Doe", "Ojunior4@fake.com", "hao123", UserRole.LEGAL_PERSON);
		userWithInvalidName = new User("", "Doe", "Ojunior4@fake.com", "hao123", UserRole.NATURAL_PERSON);
	}
	
	@After
    public void tearDown() {
        userRepository.deleteAll();
    }
	
	@Test
	public void testCreateUserSuccessfully() {
		assertEquals(new ResponseEntity<>(HttpStatus.CREATED), controller.addUser(validUser));
	}

	@Test
	public void testCreateUserUnsuccessfully() {
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), controller.addUser(userWithInvalidName));
	}
	
	@Test
	public void duplicatedUserTest() {
		controller.addUser(validUser);
		
		assertEquals(new ResponseEntity<>(HttpStatus.CONFLICT), controller.addUser(validUser));
	}
	
	

}
