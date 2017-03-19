package br.edu.ufcg.computacao.si1.models.advertisement;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisimentUserException;


@Entity
@DiscriminatorValue(value = "job_advertisement")
public class JobAdvertisement extends Advertisement {

    public JobAdvertisement(String title, Date publicationDate, Date expirationDate, double salaryOffer, User user) throws InvalidAdvertisimentUserException {
        super(title, publicationDate, expirationDate, salaryOffer, user);

        if(user.getRole().equals(UserRole.NATURAL_PERSON))
            throw new InvalidAdvertisimentUserException();
    }
}
