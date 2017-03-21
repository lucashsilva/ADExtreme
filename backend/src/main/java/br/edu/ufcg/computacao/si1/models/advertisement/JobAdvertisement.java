package br.edu.ufcg.computacao.si1.models.advertisement;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.models.user.Candidate;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisimentUserException;


@Entity
@DiscriminatorValue(value = "job_advertisement")
public class JobAdvertisement extends Advertisement {

    @ManyToMany
    @JoinColumn(name = "user_id")
    @NotNull(message = "Advertisement candidate can not be null.")
    private Set<Candidate> candidate;

    public JobAdvertisement(String title, LocalDate publicationDate, LocalDate expirationDate,
                            double salaryOffer, User user) throws InvalidAdvertisimentUserException {
        super(title, publicationDate, expirationDate, salaryOffer, user);

        if(user.getRole().equals(UserRole.NATURAL_PERSON))
            throw new InvalidAdvertisimentUserException();

        this.candidate = new HashSet<>();
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
}
