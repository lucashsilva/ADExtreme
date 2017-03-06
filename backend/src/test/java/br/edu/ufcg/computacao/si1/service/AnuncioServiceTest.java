/*
package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.models.Ad;
import br.edu.ufcg.computacao.si1.models.Notas;
import br.edu.ufcg.computacao.si1.repositories.AdRepository;
import br.edu.ufcg.computacao.si1.services.AnuncioService;

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
public class AnuncioServiceTest {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private AdRepository anuncioRepository;

    private Ad anuncio1, anuncio2, anuncio3;


    @Before
    public void setUp() {
        anuncio1 = new Ad("Anuncio de Movel", new Date(), 100, Notas.notas[2], "movel");
        anuncio2 = new Ad("Anuncio de Imovel", new Date(), 100000, Notas.notas[3], "imovel");
        anuncio3 = new Ad("Anuncio de Emprego", new Date(), 0, Notas.notas[1], "emprego");
    }

    @After
    public void tearDown() {
        anuncioRepository.deleteAll();
    }

    @Test
    public void testInicializacao() {
        assertNotNull("AnuncioService não foi instânciado corretamente", anuncioService);
        assertTrue("AnuncioService não deveria conter nenhum item", anuncioService.getAll().isEmpty());
    }


    @Test
    public void testCreateAd() {
        Ad anuncio1FromDB = anuncioService.create(anuncio1);
        Ad anuncio2FromDB = anuncioService.create(anuncio2);
        Ad anuncio3FromDB = anuncioService.create(anuncio3);

        assertNotNull(anuncio1FromDB);
        assertNotNull(anuncio2FromDB);
        assertNotNull(anuncio3FromDB);

        assertTrue(anuncioRepository.exists(anuncio1FromDB.get_id()));
        assertTrue(anuncioRepository.exists(anuncio2FromDB.get_id()));
        assertTrue(anuncioRepository.exists(anuncio3FromDB.get_id()));

        assertEquals(anuncio1FromDB, anuncio1);
        assertEquals(anuncio2FromDB, anuncio2);
        assertEquals(anuncio3FromDB, anuncio3);

        assertEquals(anuncio1FromDB, anuncioService.getAd(anuncio1FromDB.get_id()).get());
        assertEquals(anuncio2FromDB, anuncioService.getAd(anuncio2FromDB.get_id()).get());
        assertEquals(anuncio3FromDB, anuncioService.getAd(anuncio3FromDB.get_id()).get());
    }

    @Test
    public void testGetPorTipo() {

        int QTDE_ANUNCIOS_ESPERADA = 1;

        Ad anuncioMovel = anuncioService.create(anuncio1);
        Ad anuncioImovel = anuncioService.create(anuncio2);
        Ad anuncioEmprego = anuncioService.create(anuncio3);

        assertNotNull(anuncioMovel);
        assertNotNull(anuncioImovel);
        assertNotNull(anuncioEmprego);

        assertEquals(anuncioMovel.getTipo(), "movel");
        assertEquals(anuncioImovel.getTipo(), "imovel");
        assertEquals(anuncioEmprego.getTipo(), "emprego");

        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAd("movel").size());
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAd("imovel").size());
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAd("emprego").size());

        assertTrue(anuncioService.getAd("movel").contains(anuncioMovel));
        assertTrue(anuncioService.getAd("imovel").contains(anuncioImovel));
        assertTrue(anuncioService.getAd("emprego").contains(anuncioEmprego));
    }

    @Test
    public void testGetAll() {
        int QTDE_ANUNCIOS_ESPERADA = 3;

        Ad anuncioMovel = anuncioService.create(anuncio1);
        Ad anuncioImovel = anuncioService.create(anuncio2);
        Ad anuncioEmprego = anuncioService.create(anuncio3);

        assertNotNull(anuncioMovel);
        assertNotNull(anuncioImovel);
        assertNotNull(anuncioEmprego);

        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAll().size());

        assertTrue(anuncioService.getAll().contains(anuncioMovel));
        assertTrue(anuncioService.getAll().contains(anuncioImovel));
        assertTrue(anuncioService.getAll().contains(anuncioEmprego));

    }

    @Test
    public void testDelete() {
        int QTDE_ANUNCIOS_ESPERADA = 3;

        Ad anuncioMovel = anuncioService.create(anuncio1);
        Ad anuncioImovel = anuncioService.create(anuncio2);
        Ad anuncioEmprego = anuncioService.create(anuncio3);

        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAll().size());
        assertTrue(anuncioService.getAll().contains(anuncioMovel));
        assertTrue(anuncioService.getAll().contains(anuncioImovel));
        assertTrue(anuncioService.getAll().contains(anuncioEmprego));

        assertTrue(anuncioService.delete(anuncioMovel.get_id()));
        QTDE_ANUNCIOS_ESPERADA-=1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAll().size());
        assertFalse(anuncioService.getAll().contains(anuncioMovel));
        assertTrue(anuncioService.getAll().contains(anuncioImovel));
        assertTrue(anuncioService.getAll().contains(anuncioEmprego));

        assertTrue(anuncioService.delete(anuncioImovel.get_id()));
        QTDE_ANUNCIOS_ESPERADA-=1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAll().size());
        assertFalse(anuncioService.getAll().contains(anuncioMovel));
        assertFalse(anuncioService.getAll().contains(anuncioImovel));
        assertTrue(anuncioService.getAll().contains(anuncioEmprego));

        assertTrue(anuncioService.delete(anuncioEmprego.get_id()));
        QTDE_ANUNCIOS_ESPERADA-=1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAll().size());
        assertFalse(anuncioService.getAll().contains(anuncioMovel));
        assertFalse(anuncioService.getAll().contains(anuncioImovel));
        assertFalse(anuncioService.getAll().contains(anuncioEmprego));

        assertEquals(0, QTDE_ANUNCIOS_ESPERADA);

        assertFalse(anuncioService.delete(anuncioMovel.get_id()));
        assertFalse(anuncioService.delete(anuncioImovel.get_id()));
        assertFalse(anuncioService.delete(anuncioEmprego.get_id()));
    }

    @Test
    public void testUpdate() {

        String SUFIXO = " editado";

        Ad anuncioMovel = anuncioService.create(anuncio1);
        Ad anuncioImovel = anuncioService.create(anuncio2);
        Ad anuncioEmprego = anuncioService.create(anuncio3);

        assertEquals(anuncioMovel, anuncio1);
        assertEquals(anuncioImovel, anuncio2);
        assertEquals(anuncioEmprego, anuncio3);

        //Update Titulo
        anuncioMovel.setTitulo(anuncioMovel.getTitulo()+SUFIXO);
        anuncioImovel.setTitulo(anuncioImovel.getTitulo()+SUFIXO);
        anuncioEmprego.setTitulo(anuncioEmprego.getTitulo()+SUFIXO);

        assertTrue(anuncioService.update(anuncioMovel));
        assertTrue(anuncioService.update(anuncioImovel));
        assertTrue(anuncioService.update(anuncioEmprego));

        assertEquals(anuncioMovel.getTitulo(), anuncioService.getAd(anuncioMovel.get_id()).get().getTitulo());
        assertEquals(anuncioImovel.getTitulo(), anuncioService.getAd(anuncioImovel.get_id()).get().getTitulo());
        assertEquals(anuncioEmprego.getTitulo(), anuncioService.getAd(anuncioEmprego.get_id()).get().getTitulo());

        //Update preço
        anuncioMovel.setPreco(anuncioMovel.getPreco()*2);
        anuncioImovel.setPreco(anuncioImovel.getPreco()*2);
        anuncioEmprego.setPreco(anuncioEmprego.getPreco()*2);

        assertTrue(anuncioService.update(anuncioMovel));
        assertTrue(anuncioService.update(anuncioImovel));
        assertTrue(anuncioService.update(anuncioEmprego));

        assertEquals(anuncioMovel.getPreco(), anuncioService.getAd(anuncioMovel.get_id()).get().getPreco());
        assertEquals(anuncioImovel.getPreco(), anuncioService.getAd(anuncioImovel.get_id()).get().getPreco());
        assertEquals(anuncioEmprego.getPreco(), anuncioService.getAd(anuncioEmprego.get_id()).get().getPreco());

        //Update nota
        anuncioMovel.setNota(Notas.notas[4]);
        anuncioImovel.setNota(Notas.notas[4]);
        anuncioEmprego.setNota(Notas.notas[4]);

        assertTrue(anuncioService.update(anuncioMovel));
        assertTrue(anuncioService.update(anuncioImovel));
        assertTrue(anuncioService.update(anuncioEmprego));

        assertEquals(Notas.notas[4], anuncioService.getAd(anuncioMovel.get_id()).get().getNota());
        assertEquals(Notas.notas[4], anuncioService.getAd(anuncioImovel.get_id()).get().getNota());
        assertEquals(Notas.notas[4], anuncioService.getAd(anuncioEmprego.get_id()).get().getNota());
    }

}

*/