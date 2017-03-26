package br.edu.ufcg.computacao.si1.models.advertisement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.edu.ufcg.computacao.si1.exceptions.InvalidAdvertisementUserException;
import br.edu.ufcg.computacao.si1.models.user.MinimalUser;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.services.JsonDateDeserializer;
import br.edu.ufcg.computacao.si1.util.Util;

@Entity
@Table(name = "tb_advertisement")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = FurnitureAdvertisement.class, name = "FURNITURE"),
	@JsonSubTypes.Type(value = JobAdvertisement.class, name = "JOB"),
	@JsonSubTypes.Type(value = RealtyAdvertisement.class, name = "REALTY"),
	@JsonSubTypes.Type(value = ServiceAdvertisement.class, name = "SERVICE")
})
public abstract class Advertisement {

    @SuppressWarnings("unused")
	private final static DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title")
    @NotNull(message = "Advertisement title can not be null.")
    @NotEmpty(message = "Advertisement title can not be empty.")
    private String title;

    @Column(name = "publication_date")
    @NotNull(message = "Advertisement creation date can not be null.")
	@JsonDeserialize(using = JsonDateDeserializer.class)
    private Date publicationDate;

	@Column(name = "expiration_date")
	@NotNull(message = "Advertisement creation date can not be null.")
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date expirationDate;

	@Column(name = "value")
	@NotNull(message = "Advertisement value can not be null.")
	@Min(0)
	private double value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Advertisement user can not be null.")
    private User user;


    public Advertisement(String title, Date publicationDate, Date expirationDate, double value, User user) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.expirationDate = expirationDate;
		this.value = value;
		this.user = user;
    }

    public Advertisement() {}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPublicationDate(Date publicationDate) {
		if(publicationDate == null) {
            this.publicationDate = Date.from(Instant.now());
        }else{
            this.publicationDate = publicationDate;
        }
	}

    public MinimalUser getUser() {
        return Util.minimalUserFor(this.user);
    }

    public void setUser(User user) throws InvalidAdvertisementUserException {
        this.user = user;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        if(expirationDate == null) {
            this.expirationDate = Date.from(Instant.now());
        } else {
            this.expirationDate = expirationDate;
        }
    }

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public abstract String showType();


    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Advertisement other = (Advertisement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", title=" + title + ", creation date=" + publicationDate.toString() +
                ", expiration date="+ expirationDate.toString() + ", type=" + showType() + "]";
	}
}
