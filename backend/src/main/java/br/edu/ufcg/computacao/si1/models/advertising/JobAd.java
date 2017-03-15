package br.edu.ufcg.computacao.si1.models.advertising;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisingUserException;
import br.edu.ufcg.computacao.si1.models.user.User;

/**
 * Created by Jhonatan on 15/03/2017.
 */

@Entity
@DiscriminatorValue(value = "job_ad")
public class JobAd extends Advertising {

    @Column(name = "salary_offer")
    @NotNull(message = "Job salary offer can not be null.")
    @Min(0)
    private double salaryOffer;

    public JobAd(String title, Date publicationDate, Date expirationDate, double salaryOffer, User user) throws InvalidAdvertisingUserException {
        super(title, publicationDate, expirationDate, user);

        if(user.getRole().equals(UserRole.NATURAL_PERSON))
            throw new InvalidAdvertisingUserException();

        this.salaryOffer = salaryOffer;
    }

    public double getSalaryOffer() {
        return salaryOffer;
    }

    public void setSalaryOffer(double salaryOffer) {
        this.salaryOffer = salaryOffer;
    }
}
