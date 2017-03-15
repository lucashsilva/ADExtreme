package br.edu.ufcg.computacao.si1.models.advertising;

import br.edu.ufcg.computacao.si1.enums.AdType;
import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisingUserException;
import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
