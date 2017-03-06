package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.enums.AdType;
import br.edu.ufcg.computacao.si1.models.Advertising;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.repositories.AdRepository;
import br.edu.ufcg.computacao.si1.services.AdService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdServiceTest {

    @Autowired
    private AdService adService;

    @Autowired
    private AdRepository adRepository;

    private Advertising ad1, ad2, ad3;
    private User user;


    @Before
    public void setUp() {
        user = new User("user", "user@email.com","password","natural person");
        ad1 = new Advertising("Ad of Furniture", new Date(), 100, 5, AdType.FORNITURE, user);
        ad2 = new Advertising("Ad of House", new Date(), 100000, 3, AdType.FORNITURE, user);
        ad3 = new Advertising("Ad of Job", new Date(), 0, 4, AdType.JOB, user);
    }

    @After
    public void tearDown() {
        adRepository.deleteAll();
    }

    @Test
    public void initializationTest() {
        assertNotNull("AdService was not installed correctly", adService);
        assertTrue("AdService should not have any item", adService.getAds().isEmpty());
    }


    @Test
    public void testCreateAd() {
        Advertising ad1FromDB = adService.create(ad1);
        Advertising ad2FromDB = adService.create(ad2);
        Advertising ad3FromDB = adService.create(ad3);

        assertNotNull(ad1FromDB);
        assertNotNull(ad2FromDB);
        assertNotNull(ad3FromDB);

        assertTrue(adRepository.exists(ad1FromDB.getId()));
        assertTrue(adRepository.exists(ad2FromDB.getId()));
        assertTrue(adRepository.exists(ad3FromDB.getId()));

        assertEquals(ad1FromDB, ad1);
        assertEquals(ad2FromDB, ad2);
        assertEquals(ad3FromDB, ad3);

        assertEquals(ad1FromDB, adService.getAd(ad1FromDB.getId()).get());
        assertEquals(ad2FromDB, adService.getAd(ad2FromDB.getId()).get());
        assertEquals(ad3FromDB, adService.getAd(ad3FromDB.getId()).get());
    }

    @Test
    public void getByTypeTest() {

        int EXPECTED_AMOUNT = 1;

        Advertising adFurniture = adService.create(ad1);
        Advertising adHouse = adService.create(ad2);
        Advertising adJob = adService.create(ad3);

        assertNotNull(adFurniture);
        assertNotNull(adHouse);
        assertNotNull(adJob);

        assertEquals(adFurniture.getType(), "furniture");
        assertEquals(adHouse.getType(), "house");
        assertEquals(adJob.getType(), "job");

        assertEquals(EXPECTED_AMOUNT, adService.getAd("furniture").size());
        assertEquals(EXPECTED_AMOUNT, adService.getAd("house").size());
        assertEquals(EXPECTED_AMOUNT, adService.getAd("job").size());

        assertTrue(adService.getAd("furniture").contains(adFurniture));
        assertTrue(adService.getAd("house").contains(adHouse));
        assertTrue(adService.getAd("job").contains(adJob));
    }

    @Test
    public void testGetAds() {
        int EXPECTED_AMOUNT = 3;

        Advertising adFurniture = adService.create(ad1);
        Advertising adHouse = adService.create(ad2);
        Advertising adJob = adService.create(ad3);

        assertNotNull(adFurniture);
        assertNotNull(adHouse);
        assertNotNull(adJob);

        assertEquals(EXPECTED_AMOUNT, adService.getAds().size());

        assertTrue(adService.getAds().contains(adFurniture));
        assertTrue(adService.getAds().contains(adHouse));
        assertTrue(adService.getAds().contains(adJob));

    }

    @Test
    public void testDelete() {
        int EXPECTED_AMOUNT = 3;

        Advertising adFurniture = adService.create(ad1);
        Advertising adHouse = adService.create(ad2);
        Advertising adJob = adService.create(ad3);

        assertEquals(EXPECTED_AMOUNT, adService.getAds().size());
        assertTrue(adService.getAds().contains(adFurniture));
        assertTrue(adService.getAds().contains(adHouse));
        assertTrue(adService.getAds().contains(adJob));

        assertTrue(adService.delete(adFurniture.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, adService.getAds().size());
        assertFalse(adService.getAds().contains(adFurniture));
        assertTrue(adService.getAds().contains(adHouse));
        assertTrue(adService.getAds().contains(adJob));

        assertTrue(adService.delete(adHouse.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, adService.getAds().size());
        assertFalse(adService.getAds().contains(adFurniture));
        assertFalse(adService.getAds().contains(adHouse));
        assertTrue(adService.getAds().contains(adJob));

        assertTrue(adService.delete(adJob.getId()));
        EXPECTED_AMOUNT -= 1;
        assertEquals(EXPECTED_AMOUNT, adService.getAds().size());
        assertFalse(adService.getAds().contains(adFurniture));
        assertFalse(adService.getAds().contains(adHouse));
        assertFalse(adService.getAds().contains(adJob));

        assertEquals(0, EXPECTED_AMOUNT);

        assertFalse(adService.delete(adFurniture.getId()));
        assertFalse(adService.delete(adHouse.getId()));
        assertFalse(adService.delete(adJob.getId()));
    }

    @Test
    public void testUpdate() {

        String SUFFIX = " edited";

        Advertising adFurniture = adService.create(ad1);
        Advertising adJob = adService.create(ad2);
        Advertising adHouse = adService.create(ad3);

        assertEquals(adFurniture, ad1);
        assertEquals(adJob, ad2);
        assertEquals(adHouse, ad3);

        //Update Title
        adFurniture.setTitle(adFurniture.getTitle() + SUFFIX);
        adJob.setTitle(adJob.getTitle() + SUFFIX);
        adJob.setTitle(adJob.getTitle() + SUFFIX);

        assertTrue(adService.update(adFurniture));
        assertTrue(adService.update(adJob));
        assertTrue(adService.update(adJob));

        assertEquals(adFurniture.getTitle(), adService.getAd(adFurniture.getId()).get().getTitle());
        assertEquals(adJob.getTitle(), adService.getAd(adJob.getId()).get().getTitle());
        assertEquals(adJob.getTitle(), adService.getAd(adJob.getId()).get().getTitle());

        //Update Price
        adFurniture.setPrice(adJob.getPrice() * 2);
        adJob.setPrice(adJob.getPrice() * 2);
        adJob.setPrice(adJob.getPrice() * 2);

        assertTrue(adService.update(adFurniture));
        assertTrue(adService.update(adJob));
        assertTrue(adService.update(adJob));

        assertEquals(adFurniture.getPrice(), adService.getAd(adFurniture.getId()).get().getPrice());
        assertEquals(adJob.getPrice(), adService.getAd(adJob.getId()).get().getPrice());
        assertEquals(adJob.getPrice(), adService.getAd(adJob.getId()).get().getPrice());

        //Update Classification
        adFurniture.setClassification(5);
        adJob.setClassification(3);
        adHouse.setClassification(4);

        assertTrue(adService.update(adFurniture));
        assertTrue(adService.update(adHouse));
        assertTrue(adService.update(adJob));

        assertEquals(5, adService.getAd(adFurniture.getId()).get().getClassification());
        assertEquals(3, adService.getAd(adJob.getId()).get().getClassification());
        assertEquals(4, adService.getAd(adHouse.getId()).get().getClassification());
    }

}
