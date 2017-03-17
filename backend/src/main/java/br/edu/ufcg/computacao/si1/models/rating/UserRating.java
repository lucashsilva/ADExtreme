package br.edu.ufcg.computacao.si1.models.rating;

import br.edu.ufcg.computacao.si1.models.rating.Rating;
import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "tb_user_rating")
public class UserRating extends Rating {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Rating user can not be null")
    private User user;

    public UserRating(String publisherName, String comment, double grade, Date publicationDate, User user) {
        super(publisherName, comment, grade, publicationDate);
        this.user = user;
    }

    public UserRating() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
