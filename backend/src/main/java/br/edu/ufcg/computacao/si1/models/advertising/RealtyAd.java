package br.edu.ufcg.computacao.si1.models.advertising;

import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;


@Entity
@DiscriminatorValue(value = "realty_ad")
public class RealtyAd extends CostAd {

    public RealtyAd(String title, Date publicationDate, Date expirationDate, double price, User user) {
        super(title, publicationDate, expirationDate, price, user);
    }

}
