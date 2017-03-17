package br.edu.ufcg.computacao.si1.models.rating;


import br.edu.ufcg.computacao.si1.models.advertising.Advertisement;
import br.edu.ufcg.computacao.si1.models.rating.Rating;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tb_advertisement_rating")
public class AdvertisementRating extends Rating {

    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    @NotNull(message = "Rating advertisement can not be null")
    private Advertisement advertisement;

    public AdvertisementRating(String publisherName, String comment, double grade, Date publicationDate, Advertisement advertisement) {
        super(publisherName, comment, grade, publicationDate);
        this.advertisement = advertisement;
    }

    public AdvertisementRating() {
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
