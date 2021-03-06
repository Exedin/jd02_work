package it.academy.service;

import it.academy.AppConfig;
import it.academy.dao.DaoConfiguration;
import it.academy.dao.PromoDaoImpl;
import it.academy.model.Promo;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@ContextConfiguration(classes = {AppConfig.class, DaoConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PromoServiceTest {

    @Autowired
    PromoService promoService;

    @org.junit.Test
    public void findAllPromo() {
        //given
        PromoDaoImpl promoDaoMock = Mockito.mock(PromoDaoImpl.class);
        Promo promo= new Promo();
        promo.setDescription("Promo1");
        Mockito
                .when(promoDaoMock.findAllPromo())
                .thenReturn(List.of(promo, promo));
        promoService.setPromoDao(promoDaoMock);
        //when
        List<Promo> allPromo = promoService.findAllPromo();
        //then
        assertNotNull(allPromo);
        assertEquals(2, allPromo.size());
        assertEquals("Promo1", allPromo.get(0).getDescription());
        assertNull(allPromo.get(1).getPromoId());
    }
}