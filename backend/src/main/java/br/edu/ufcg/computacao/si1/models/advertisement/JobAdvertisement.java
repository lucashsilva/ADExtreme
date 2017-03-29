package br.edu.ufcg.computacao.si1.models.advertisement;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisementUserException;
import br.edu.ufcg.computacao.si1.models.user.Candidate;
import br.edu.ufcg.computacao.si1.models.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@DiscriminatorValue(value = "job_advertisement")
public class JobAdvertisement extends Advertisement {

    @ManyToMany
    @JoinColumn(name = "user_id")
    @NotNull(message = "Advertisement candidate can not be null.")
    private Set<Candidate> candidate;

    public JobAdvertisement(String title, Date publicationDate, Date expirationDate,
                            double salaryOffer, User user) throws InvalidAdvertisementUserException {
        super(title, publicationDate, expirationDate, salaryOffer, user);

        if(user.getRole().equals(UserRole.NATURAL_PERSON))
            throw new InvalidAdvertisementUserException();

        this.candidate = new HashSet<>();
    }

    public JobAdvertisement() {
    }

    public Set<Candidate> getCandidate() {
        return candidate;
    }

    public void setCandidate(Set<Candidate> candidate) {
        if(candidate == null)
            this.candidate = new HashSet<>();
        else
            this.candidate = candidate;
    }

    public boolean addCandidate(Candidate candidate) {
        return this.candidate.add(candidate);
    }

    @Override
    public void setUser(User user) throws InvalidAdvertisementUserException {
        if(user.getRole().equals(UserRole.NATURAL_PERSON))
            throw new InvalidAdvertisementUserException();
    }

    @Override
    public String showType() {
        return "job";
    }
}
