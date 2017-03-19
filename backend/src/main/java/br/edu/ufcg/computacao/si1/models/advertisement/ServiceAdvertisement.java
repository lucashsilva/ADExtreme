package br.edu.ufcg.computacao.si1.models.advertisement;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisimentUserException;
import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by gustavo on 18/03/17.
 */

@Entity
@DiscriminatorValue(value = "service_advertisement")
public class ServiceAdvertisement extends CostAdvertisement {

    @Column(name = "scheduled_date")
    @NotNull(message = "Advertisement scheduled date can not be null.")
    private Date scheduledDate;

    public ServiceAdvertisement(String title, Date publicationDate, Date expirationDate, Date scheduledDate, double price, User user) throws InvalidAdvertisimentUserException {
        super(title, publicationDate, expirationDate, price, user);

        if(user.getRole().equals(UserRole.NATURAL_PERSON))
            throw new InvalidAdvertisimentUserException();

        this.scheduledDate = scheduledDate;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
