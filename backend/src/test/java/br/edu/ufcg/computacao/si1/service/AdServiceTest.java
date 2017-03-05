package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.models.Ad;
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

    private Ad ad1, ad2, ad3;


    @Before
    public void setUp() {
        ad1 = new Ad("Anuncio de Movel", new Date(), 100, 5, "movel");
        ad2 = new Ad("Anuncio de Imovel", new Date(), 100000, 3, "imovel");
        ad3 = new Ad("Anuncio de Emprego", new Date(), 0, 4, "emprego");
    }

    @After
    public void tearDown() {
        adRepository.deleteAll();
    }

    @Test
    public void initializationTest() {
        assertNotNull("AnuncioService não foi instânciado corretamente", adService);
        assertTrue("AnuncioService não deveria conter nenhum item", adService.getAds().isEmpty());
    }


    @Test
    public void testCreateAd() {
        Ad ad1FromDB = adService.create(ad1);
        Ad ad2FromDB = adService.create(ad2);
        Ad ad3FromDB = adService.create(ad3);

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

        Ad adFurniture = adService.create(ad1);
        Ad adHouse = adService.create(ad2);
        Ad adJob = adService.create(ad3);

        assertNotNull(adFurniture);
        assertNotNull(adHouse);
        assertNotNull(adJob);

        assertEquals(adFurniture.getType(), "movel");
        assertEquals(adHouse.getType(), "imovel");
        assertEquals(adJob.getType(), "emprego");

        assertEquals(EXPECTED_AMOUNT, adService.getAd("movel").size());
        assertEquals(EXPECTED_AMOUNT, adService.getAd("imovel").size());
        assertEquals(EXPECTED_AMOUNT, adService.getAd("emprego").size());

        assertTrue(adService.getAd("movel").contains(adFurniture));
        assertTrue(adService.getAd("imovel").contains(adHouse));
        assertTrue(adService.getAd("emprego").contains(adJob));
    }

    @Test
    public void testgetAds() {
        int EXPECTED_AMOUNT = 3;

        Ad adFurniture = adService.create(ad1);
        Ad adHouse = adService.create(ad2);
        Ad adJob = adService.create(ad3);

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
        int QTDE_ANUNCIOS_ESPERADA = 3;

        Ad anuncioMovel = adService.create(ad1);
        Ad anuncioImovel = adService.create(ad2);
        Ad anuncioEmprego = adService.create(ad3);

        assertEquals(QTDE_ANUNCIOS_ESPERADA, adService.getAds().size());
        assertTrue(adService.getAds().contains(anuncioMovel));
        assertTrue(adService.getAds().contains(anuncioImovel));
        assertTrue(adService.getAds().contains(anuncioEmprego));

        assertTrue(adService.delete(anuncioMovel.getId()));
        QTDE_ANUNCIOS_ESPERADA -= 1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, adService.getAds().size());
        assertFalse(adService.getAds().contains(anuncioMovel));
        assertTrue(adService.getAds().contains(anuncioImovel));
        assertTrue(adService.getAds().contains(anuncioEmprego));

        assertTrue(adService.delete(anuncioImovel.getId()));
        QTDE_ANUNCIOS_ESPERADA -= 1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, adService.getAds().size());
        assertFalse(adService.getAds().contains(anuncioMovel));
        assertFalse(adService.getAds().contains(anuncioImovel));
        assertTrue(adService.getAds().contains(anuncioEmprego));

        assertTrue(adService.delete(anuncioEmprego.getId()));
        QTDE_ANUNCIOS_ESPERADA -= 1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, adService.getAds().size());
        assertFalse(adService.getAds().contains(anuncioMovel));
        assertFalse(adService.getAds().contains(anuncioImovel));
        assertFalse(adService.getAds().contains(anuncioEmprego));

        assertEquals(0, QTDE_ANUNCIOS_ESPERADA);

        assertFalse(adService.delete(anuncioMovel.getId()));
        assertFalse(adService.delete(anuncioImovel.getId()));
        assertFalse(adService.delete(anuncioEmprego.getId()));
    }

    @Test
    public void testUpdate() {

        String SUFIXO = " editado";

        Ad anuncioMovel = adService.create(ad1);
        Ad anuncioImovel = adService.create(ad2);
        Ad anuncioEmprego = adService.create(ad3);

        assertEquals(anuncioMovel, ad1);
        assertEquals(anuncioImovel, ad2);
        assertEquals(anuncioEmprego, ad3);

        //Update Titulo
        anuncioMovel.setTitle(anuncioMovel.getTitle() + SUFIXO);
        anuncioImovel.setTitle(anuncioImovel.getTitle() + SUFIXO);
        anuncioEmprego.setTitle(anuncioEmprego.getTitle() + SUFIXO);

        assertTrue(adService.update(anuncioMovel));
        assertTrue(adService.update(anuncioImovel));
        assertTrue(adService.update(anuncioEmprego));

        assertEquals(anuncioMovel.getTitle(), adService.getAd(anuncioMovel.getId()).get().getTitle());
        assertEquals(anuncioImovel.getTitle(), adService.getAd(anuncioImovel.getId()).get().getTitle());
        assertEquals(anuncioEmprego.getTitle(), adService.getAd(anuncioEmprego.getId()).get().getTitle());

        //Update preço
        anuncioMovel.setPrice(anuncioImovel.getPrice() * 2);
        anuncioImovel.setPrice(anuncioImovel.getPrice() * 2);
        anuncioEmprego.setPrice(anuncioEmprego.getPrice() * 2);

        assertTrue(adService.update(anuncioMovel));
        assertTrue(adService.update(anuncioImovel));
        assertTrue(adService.update(anuncioEmprego));

        assertEquals(anuncioMovel.getPrice(), adService.getAd(anuncioMovel.getId()).get().getPrice());
        assertEquals(anuncioImovel.getPrice(), adService.getAd(anuncioImovel.getId()).get().getPrice());
        assertEquals(anuncioEmprego.getPrice(), adService.getAd(anuncioEmprego.getId()).get().getPrice());

        //Update nota
        anuncioMovel.setClassification(5);
        anuncioImovel.setClassification(3);
        anuncioEmprego.setClassification(4);

        assertTrue(adService.update(anuncioMovel));
        assertTrue(adService.update(anuncioImovel));
        assertTrue(adService.update(anuncioEmprego));

        assertEquals(5, adService.getAd(anuncioMovel.getId()).get().getClassification());
        assertEquals(3, adService.getAd(anuncioImovel.getId()).get().getClassification());
        assertEquals(4, adService.getAd(anuncioEmprego.getId()).get().getClassification());
    }

}
