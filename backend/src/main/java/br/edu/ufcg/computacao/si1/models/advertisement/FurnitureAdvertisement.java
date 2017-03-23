package br.edu.ufcg.computacao.si1.models.advertisement;

import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@DiscriminatorValue(value = "furniture_advertisement")
public class FurnitureAdvertisement extends Advertisement {

    public FurnitureAdvertisement(String title, LocalDate publicationDate, LocalDate expirationDate, double price, User user) {
        super(title, publicationDate, expirationDate, price, user);
    }

    public FurnitureAdvertisement() {
    }
}
