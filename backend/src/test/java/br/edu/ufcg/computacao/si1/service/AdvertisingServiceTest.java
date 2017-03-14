package br.edu.ufcg.computacao.si1.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.computacao.si1.enums.AdType;
import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisingUserException;
import br.edu.ufcg.computacao.si1.exceptions.UserAlreadyExistsException;
import br.edu.ufcg.computacao.si1.models.Advertising;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.repositories.AdvertisingRepository;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;
import br.edu.ufcg.computacao.si1.services.AdvertisingService;
import br.edu.ufcg.computacao.si1.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertisingServiceTest {

    @Autowired
    private AdvertisingService advertisingService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private AdvertisingRepository advertisingRepository;
    
    @Autowired
    private UserRepository userRepository;

    private Advertising ad1, ad2, ad3;
    private User user;


    @Before
    public void setUp() throws UserAlreadyExistsException {
        user = userService.create(new User("user", "Doe", "user@email.com","password", UserRole.LEGAL_PERSON));
                   
        ad1 = new Advertising("Ad of Furniture", new Date(), new Date(), 100, AdType.FURNITURE, user);
        ad1.setClassification(5);
        ad2 = new Advertising("Ad of House", new Date(), new Date(), 100000, AdType.SERVICE, user);
        ad1.setClassification(3);
        ad3 = new Advertising("Ad of Job", new Date(), new Date(), 0, AdType.JOB, user);
        ad1.setClassification(4);
    }

    @After
    public void tearDown() {
        advertisingRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void initializationTest() {
        assertNotNull("AdvertisingService was not installed correctly", advertisingService);
        assertTrue("AdvertisingService should not have any item", advertisingService.getAds().isEmpty());
    }


    @Test
    public void testCreateAd() throws InvalidAdvertisingUserException{
        Advertising ad1FromDB = advertisingService.create(ad1);
        Advertising ad2FromDB = advertisingService.create(ad2);
        Advertising ad3FromDB = advertisingService.create(ad3);

        assertNotNull(ad1FromDB);
        assertNotNull(ad2FromDB);
        assertNotNull(ad3FromDB);

        assertTrue(advertisingRepository.exists(ad1FromDB.getId()));
        assertTrue(advertisingRepository.exists(ad2FromDB.getId()));
        assertTrue(advertisingRepository.exists(ad3FromDB.getId()));

        assertEquals(ad1FromDB, ad1);
        assertEquals(ad2FromDB, ad2);
        assertEquals(ad3FromDB, ad3);

        assertEquals(ad1FromDB, advertisingService.getAdById(ad1FromDB.getId()).get());
        assertEquals(ad2FromDB, advertisingService.getAdById(ad2FromDB.getId()).get());
        assertEquals(ad3FromDB, advertisingService.getAdById(ad3FromDB.getId()).get());
    }

    @Test
    public void getByTypeTest() throws InvalidAdvertisingUserException{

        int EXPECTED_AMOUNT = 1;

        Advertising adFurniture = advertisingService.create(ad1);
        Advertising adHouse = advertisingService.create(ad2);
        Advertising adJob = advertisingService.create(ad3);

        assertNotNull(adFurniture);
        assertNotNull(adHouse);
        assertNotNull(adJob);

        assertEquals(AdType.FURNITURE, adFurniture.getType());
        assertEquals(AdType.SERVICE, adHouse.getType());
        assertEquals(AdType.JOB,adJob.getType());

        assertEquals(EXPECTED_AMOUNT, advertisingService.getAdByType(AdType.FURNITURE.toString()).size());
        assertEquals(EXPECTED_AMOUNT, advertisingService.getAdByType(AdType.SERVICE.toString()).size());
        assertEquals(EXPECTED_AMOUNT, advertisingService.getAdByType(AdType.JOB.toString()).size());

        assertTrue(advertisingService.getAdByType(AdType.FURNITURE.toString()).contains(adFurniture));
        assertTrue(advertisingService.getAdByType(AdType.SERVICE.toString()).contains(adHouse));
        assertTrue(advertisingService.getAdByType(AdType.JOB.toString()).contains(adJob));
    }

    @Test
    public void testGetAds() throws InvalidAdvertisingUserException{
        int EXPECTED_AMOUNT = 3;

        Advertising adFurniture = advertisingService.create(ad1);
        Advertising adHouse = advertisingService.create(ad2);
        Advertising adJob = advertisingService.create(ad3);

        assertNotNull(adFurniture);
        assertNotNull(adHouse);
        assertNotNull(adJob);

        assertEquals(EXPECTED_AMOUNT, advertisingService.getAds().size());

        assertTrue(advertisingService.getAds().contains(adFurniture));
        assertTrue(advertisingService.getAds().contains(adHouse));
        assertTrue(advertisingService.getAds().contains(adJob));

    }

    @Test
    public void testDelete() throws InvalidAdvertisingUserException{
        int EXPECTED_AMOUNT = 3;

        Advertising adFurniture = advertisingService.create(ad1);
        Advertising adHouse = advertisingService.create(ad2);
        Advertising adJob = advertisingService.create(ad3);

        assertEquals(EXPECTED_AMOUNT, advertisingService.getAds().size());
        assertTrue(advertisingService.getAds().contains(adFurniture));
        assertTrue(advertisingService.getAds().contains(adHouse));
        assertTrue(advertisingService.getAds().contains(adJob));

        assertTrue(advertisingService.delete(adFurniture.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, advertisingService.getAds().size());
        assertFalse(advertisingService.getAds().contains(adFurniture));
        assertTrue(advertisingService.getAds().contains(adHouse));
        assertTrue(advertisingService.getAds().contains(adJob));

        assertTrue(advertisingService.delete(adHouse.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, advertisingService.getAds().size());
        assertFalse(advertisingService.getAds().contains(adFurniture));
        assertFalse(advertisingService.getAds().contains(adHouse));
        assertTrue(advertisingService.getAds().contains(adJob));

        assertTrue(advertisingService.delete(adJob.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, advertisingService.getAds().size());
        assertFalse(advertisingService.getAds().contains(adFurniture));
        assertFalse(advertisingService.getAds().contains(adHouse));
        assertFalse(advertisingService.getAds().contains(adJob));

        assertEquals(0, EXPECTED_AMOUNT);

        assertFalse(advertisingService.delete(adFurniture.getId()));
        assertFalse(advertisingService.delete(adHouse.getId()));
        assertFalse(advertisingService.delete(adJob.getId()));
    }

    @Test
    public void testUpdate() throws InvalidAdvertisingUserException{

        String SUFFIX = " edited";

        Advertising adFurniture = advertisingService.create(ad1);
        Advertising adJob = advertisingService.create(ad2);
        Advertising adHouse = advertisingService.create(ad3);

        assertEquals(adFurniture, ad1);
        assertEquals(adJob, ad2);
        assertEquals(adHouse, ad3);

        //Update Title
        adFurniture.setTitle(adFurniture.getTitle() + SUFFIX);
        adJob.setTitle(adJob.getTitle() + SUFFIX);
        adJob.setTitle(adJob.getTitle() + SUFFIX);

        assertTrue(advertisingService.update(adFurniture));
        assertTrue(advertisingService.update(adJob));
        assertTrue(advertisingService.update(adJob));

        assertEquals(adFurniture.getTitle(), advertisingService.getAdById(adFurniture.getId()).get().getTitle());
        assertEquals(adJob.getTitle(), advertisingService.getAdById(adJob.getId()).get().getTitle());
        assertEquals(adJob.getTitle(), advertisingService.getAdById(adJob.getId()).get().getTitle());

        //Update Price
        adFurniture.setPrice(adJob.getPrice() * 2);
        adJob.setPrice(adJob.getPrice() * 2);
        adJob.setPrice(adJob.getPrice() * 2);

        assertTrue(advertisingService.update(adFurniture));
        assertTrue(advertisingService.update(adJob));
        assertTrue(advertisingService.update(adJob));

        assertEquals(adFurniture.getPrice(), advertisingService.getAdById(adFurniture.getId()).get().getPrice());
        assertEquals(adJob.getPrice(), advertisingService.getAdById(adJob.getId()).get().getPrice());
        assertEquals(adJob.getPrice(), advertisingService.getAdById(adJob.getId()).get().getPrice());

        //Update Classification
        adFurniture.setClassification(5);
        adJob.setClassification(3);
        adHouse.setClassification(4);

        assertTrue(advertisingService.update(adFurniture));
        assertTrue(advertisingService.update(adHouse));
        assertTrue(advertisingService.update(adJob));

        assertEquals(5.0, advertisingService.getAdById(adFurniture.getId()).get().getClassification());
        assertEquals(3.0, advertisingService.getAdById(adJob.getId()).get().getClassification());
        assertEquals(4.0, advertisingService.getAdById(adHouse.getId()).get().getClassification());
    }

}
