package br.edu.ufcg.computacao.si1.models.advertisement;

import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;


@MappedSuperclass
public abstract class CostAdvertisement extends Advertisement {

    @Column(name = "price")
    @NotNull(message = "Advertisement price can not be null.")
    @Min(0)
    private double price;

    public CostAdvertisement(String title, Date publicationDate, Date expirationDate, double price, User user) {
       super(title, publicationDate, expirationDate, user);
       this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
