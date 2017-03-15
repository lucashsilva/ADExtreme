package br.edu.ufcg.computacao.si1.models.advertising;

import br.edu.ufcg.computacao.si1.enums.AdType;
import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Jhonatan on 15/03/2017.
 */

@MappedSuperclass
public abstract class CostAd extends Advertising {

    @Column(name = "price")
    @NotNull(message = "Advertising price can not be null.")
    @Min(0)
    private double price;

    public CostAd(String title, Date publicationDate, Date expirationDate, double price, User user) {
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
