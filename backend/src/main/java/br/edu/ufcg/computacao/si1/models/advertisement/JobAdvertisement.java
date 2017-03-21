package br.edu.ufcg.computacao.si1.models.advertisement;


import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisimentUserException;


@Entity
@DiscriminatorValue(value = "job_advertisement")
public class JobAdvertisement extends Advertisement {

    public JobAdvertisement(String title, LocalDate publicationDate, LocalDate expirationDate, double salaryOffer, User user) throws InvalidAdvertisimentUserException {
        super(title, publicationDate, expirationDate, salaryOffer, user);

        if(user.getRole().equals(UserRole.NATURAL_PERSON))
            throw new InvalidAdvertisimentUserException();
    }

    @Override
    public void setUser(User user) throws InvalidAdvertisimentUserException {
        if(user.getRole().equals(UserRole.NATURAL_PERSON))
            throw new InvalidAdvertisimentUserException();
    }
}
