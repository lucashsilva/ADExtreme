package br.edu.ufcg.computacao.si1.models.advertisement;

import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@DiscriminatorValue(value = "realty_advertisement")
public class RealtyAdvertisement extends Advertisement {

    public RealtyAdvertisement(String title, LocalDate publicationDate, LocalDate expirationDate, double price, User user) {
        super(title, publicationDate, expirationDate, price, user);
    }

}
