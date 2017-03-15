package br.edu.ufcg.computacao.si1.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tb_advertising_rating")
public class AdvertisingRating extends Rating {

    @ManyToOne
    @JoinColumn(name = "advertising_id")
    @NotNull(message = "Rating advertising can not be null")
    private Advertising advertising;

    public AdvertisingRating(String publisherName, String comment, double grade, Date publicationDate, Advertising advertising) {
        super(publisherName, comment, grade, publicationDate);
        this.advertising = advertising;
    }

    public AdvertisingRating() {
    }

    public Advertising getAdvertising() {
        return advertising;
    }

    public void setAdvertising(Advertising advertising) {
        this.advertising = advertising;
    }
}
