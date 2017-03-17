package br.edu.ufcg.computacao.si1.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import java.util.Date;

import br.edu.ufcg.computacao.si1.models.advertising.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.computacao.si1.enums.AdType;
import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisimentUserException;
import br.edu.ufcg.computacao.si1.exceptions.UserAlreadyExistsException;
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
    public void setUp() throws UserAlreadyExistsException, InvalidAdvertisimentUserException {
        user = userService.create(new User("user", "Doe", "user@email.com","password", UserRole.LEGAL_PERSON));
                   
        ad1 = new FurnitureAd("Ad of Furniture", new Date(), new Date(), 100, user);
        //ad1.setClassification(5);
        ad2 = new RealtyAd("Ad of House", new Date(), new Date(), 100000, user);
       //ad1.setClassification(3);
        ad3 = new JobAd("Ad of Job", new Date(), new Date(), 0, user);
       //ad1.setClassification(4);
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
    public void testCreateAd() throws InvalidAdvertisimentUserException {
        Advertisement ad1FromDB = advertisementService.create(ad1);
        Advertisement ad2FromDB = advertisementService.create(ad2);
        Advertisement ad3FromDB = advertisementService.create(ad3);

        assertNotNull(ad1FromDB);
        assertNotNull(ad2FromDB);
        assertNotNull(ad3FromDB);

        assertTrue(advertisementRepository.exists(ad1FromDB.getId()));
        assertTrue(advertisementRepository.exists(ad2FromDB.getId()));
        assertTrue(advertisementRepository.exists(ad3FromDB.getId()));

        assertEquals(ad1FromDB, ad1);
        assertEquals(ad2FromDB, ad2);
        assertEquals(ad3FromDB, ad3);

        assertEquals(ad1FromDB, advertisementService.getAdById(ad1FromDB.getId()).get());
        assertEquals(ad2FromDB, advertisementService.getAdById(ad2FromDB.getId()).get());
        assertEquals(ad3FromDB, advertisementService.getAdById(ad3FromDB.getId()).get());
    }

    @Test
    public void getByTypeTest() throws InvalidAdvertisimentUserException {

        int EXPECTED_AMOUNT = 1;

        Advertisement adFurniture = advertisementService.create(ad1);
        Advertisement adHouse = advertisementService.create(ad2);
        Advertisement adJob = advertisementService.create(ad3);

        assertNotNull(adFurniture);
        assertNotNull(adHouse);
        assertNotNull(adJob);

        assertEquals(FurnitureAd.class, adFurniture.getClass());
        assertEquals(RealtyAd.class, adHouse.getClass());
        assertEquals(JobAd.class, adJob.getClass());

        assertEquals(EXPECTED_AMOUNT, advertisementService.getAdByType(AdType.FURNITURE.toString()).size());
        assertEquals(EXPECTED_AMOUNT, advertisementService.getAdByType(AdType.SERVICE.toString()).size());
        assertEquals(EXPECTED_AMOUNT, advertisementService.getAdByType(AdType.JOB.toString()).size());

        assertTrue(advertisementService.getAdByType(AdType.FURNITURE.toString()).contains(adFurniture));
        assertTrue(advertisementService.getAdByType(AdType.SERVICE.toString()).contains(adHouse));
        assertTrue(advertisementService.getAdByType(AdType.JOB.toString()).contains(adJob));
    }

    @Test
    public void testGetAds() throws InvalidAdvertisimentUserException {
        int EXPECTED_AMOUNT = 3;

        Advertisement adFurniture = advertisementService.create(ad1);
        Advertisement adHouse = advertisementService.create(ad2);
        Advertisement adJob = advertisementService.create(ad3);

        assertNotNull(adFurniture);
        assertNotNull(adHouse);
        assertNotNull(adJob);

        assertEquals(EXPECTED_AMOUNT, advertisementService.getAds().size());

        assertTrue(advertisementService.getAds().contains(adFurniture));
        assertTrue(advertisementService.getAds().contains(adHouse));
        assertTrue(advertisementService.getAds().contains(adJob));

    }

    @Test
    public void testDelete() throws InvalidAdvertisimentUserException {
        int EXPECTED_AMOUNT = 3;

        Advertisement adFurniture = advertisementService.create(ad1);
        Advertisement adHouse = advertisementService.create(ad2);
        Advertisement adJob = advertisementService.create(ad3);

        assertEquals(EXPECTED_AMOUNT, advertisementService.getAds().size());
        assertTrue(advertisementService.getAds().contains(adFurniture));
        assertTrue(advertisementService.getAds().contains(adHouse));
        assertTrue(advertisementService.getAds().contains(adJob));

        assertTrue(advertisementService.delete(adFurniture.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, advertisementService.getAds().size());
        assertFalse(advertisementService.getAds().contains(adFurniture));
        assertTrue(advertisementService.getAds().contains(adHouse));
        assertTrue(advertisementService.getAds().contains(adJob));

        assertTrue(advertisementService.delete(adHouse.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, advertisementService.getAds().size());
        assertFalse(advertisementService.getAds().contains(adFurniture));
        assertFalse(advertisementService.getAds().contains(adHouse));
        assertTrue(advertisementService.getAds().contains(adJob));

        assertTrue(advertisementService.delete(adJob.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, advertisementService.getAds().size());
        assertFalse(advertisementService.getAds().contains(adFurniture));
        assertFalse(advertisementService.getAds().contains(adHouse));
        assertFalse(advertisementService.getAds().contains(adJob));

        assertEquals(0, EXPECTED_AMOUNT);

        assertFalse(advertisementService.delete(adFurniture.getId()));
        assertFalse(advertisementService.delete(adHouse.getId()));
        assertFalse(advertisementService.delete(adJob.getId()));
    }

    @Test
    public void testUpdate() throws InvalidAdvertisimentUserException {

        String SUFFIX = " edited";

        Advertisement adFurniture = advertisementService.create(ad1);
        Advertisement adJob = advertisementService.create(ad2);
        Advertisement adHouse = advertisementService.create(ad3);

        assertEquals(adFurniture, ad1);
        assertEquals(adJob, ad2);
        assertEquals(adHouse, ad3);

        //Update Title
        adFurniture.setTitle(adFurniture.getTitle() + SUFFIX);
        adJob.setTitle(adJob.getTitle() + SUFFIX);
        adJob.setTitle(adJob.getTitle() + SUFFIX);

        assertTrue(advertisementService.update(adFurniture));
        assertTrue(advertisementService.update(adJob));
        assertTrue(advertisementService.update(adJob));

        assertEquals(adFurniture.getTitle(), advertisementService.getAdById(adFurniture.getId()).get().getTitle());
        assertEquals(adJob.getTitle(), advertisementService.getAdById(adJob.getId()).get().getTitle());
        assertEquals(adJob.getTitle(), advertisementService.getAdById(adJob.getId()).get().getTitle());

        //Update Price
        ((CostAd) adFurniture).setPrice(((JobAd) adJob).getSalaryOffer() * 2);
        ((JobAd) adJob).setSalaryOffer(((JobAd) adJob).getSalaryOffer() * 2);
        ((JobAd) adJob).setSalaryOffer(((JobAd) adJob).getSalaryOffer() * 2);

        assertTrue(advertisementService.update(adFurniture));
        assertTrue(advertisementService.update(adJob));
        assertTrue(advertisementService.update(adJob));

        /*assertEquals(((CostAd) adFurniture).getPrice(), advertisementService.getAdById(adFurniture.getId()).get().getPrice());
        assertEquals(((JobAd) adJob).getSalaryOffer(), advertisementService.getAdById(adJob.getId()).get().getPrice());
        assertEquals(((JobAd) adJob).getSalaryOffer(), advertisementService.getAdById(adJob.getId()).get().getPrice());*/

        //Update Classification
        /*
        adFurniture.setClassification(5);
        adJob.setClassification(3);
        adHouse.setClassification(4);

        assertTrue(advertisementService.update(adFurniture));
        assertTrue(advertisementService.update(adHouse));
        assertTrue(advertisementService.update(adJob));

        assertEquals(5.0, advertisementService.getAdById(adFurniture.getId()).get().getClassification());
        assertEquals(3.0, advertisementService.getAdById(adJob.getId()).get().getClassification());
        assertEquals(4.0, advertisementService.getAdById(adHouse.getId()).get().getClassification());
        */
    }

}
