package br.edu.ufcg.computacao.si1.models.advertisement;

import br.edu.ufcg.computacao.si1.models.user.User;

import java.util.Date;

/**
 * Created by gustavo on 18/03/17.
 */
public class CostAdvertisement extends  Advertisement{

    public CostAdvertisement(String title, Date publicationDate, Date expirationDate, double price, User user) {
        super(title, publicationDate, expirationDate, price, user);
    }

}
