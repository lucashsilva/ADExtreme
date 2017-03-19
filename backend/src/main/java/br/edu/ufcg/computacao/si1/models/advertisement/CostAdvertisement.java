package br.edu.ufcg.computacao.si1.models.advertisement;

import br.edu.ufcg.computacao.si1.models.user.User;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by gustavo on 18/03/17.
 */
public class CostAdvertisement extends  Advertisement{

    public CostAdvertisement(String title, LocalDate publicationDate, LocalDate expirationDate, double price, User user) {
        super(title, publicationDate, expirationDate, price, user);
    }

}
