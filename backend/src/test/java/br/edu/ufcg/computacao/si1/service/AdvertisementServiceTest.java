package br.edu.ufcg.computacao.si1.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.computacao.si1.enums.AdvertisementType;
import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisementUserException;
import br.edu.ufcg.computacao.si1.exceptions.UserAlreadyExistsException;
import br.edu.ufcg.computacao.si1.models.advertisement.Advertisement;
import br.edu.ufcg.computacao.si1.models.advertisement.FurnitureAdvertisement;
import br.edu.ufcg.computacao.si1.models.advertisement.JobAdvertisement;
import br.edu.ufcg.computacao.si1.models.advertisement.RealtyAdvertisement;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.repositories.AdvertisementRepository;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;
import br.edu.ufcg.computacao.si1.services.AdvertisementService;
import br.edu.ufcg.computacao.si1.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertisementServiceTest {

    @Autowired
    private AdvertisementService advertisementService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private AdvertisementRepository advertisementRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private Advertisement ad1, ad2, ad3;
    
    private User user;


    @Before
    public void setUp() throws UserAlreadyExistsException, InvalidAdvertisementUserException {
        user = userService.create(new User("user", "Doe", "user@email.com","password", UserRole.LEGAL_PERSON));
                   
        ad1 = new FurnitureAdvertisement("Ad of Furniture", new Date(), new Date(), 100, user);
        
        ad2 = new RealtyAdvertisement("Ad of House", new Date(), new Date(), 100000, user);
        ad3 = new JobAdvertisement("Ad of Job", new Date(), new Date(), 0, user);
    }

    @After
    public void tearDown() {
        advertisementRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void initializationTest() {
        assertNotNull("AdvertisementService was not installed correctly", advertisementService);
        assertTrue("AdvertisementService should not have any item", advertisementService.getAds().isEmpty());
    }


    @Test
    public void testCreateAd() throws InvalidAdvertisementUserException {
    	assertNotEquals(ad1, ad2);
    	assertNotEquals(ad2, ad3);
    	assertNotEquals(ad3, ad1);

    	assertNotNull(ad1);
        assertNotNull(ad2);
        assertNotNull(ad3);
    }

}
