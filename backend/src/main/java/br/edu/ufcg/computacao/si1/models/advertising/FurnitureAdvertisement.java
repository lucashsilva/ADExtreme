package br.edu.ufcg.computacao.si1.models.advertising;

import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;



@Entity
@DiscriminatorValue(value = "furniture_ad")
public class FurnitureAdvertisement extends CostAd {

    public FurnitureAdvertisement(String title, Date publicationDate, Date expirationDate, double price, User user) {
        super(title, publicationDate, expirationDate, price, user);
    }

}
