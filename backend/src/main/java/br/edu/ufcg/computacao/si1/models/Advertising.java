package br.edu.ufcg.computacao.si1.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.edu.ufcg.computacao.si1.enums.AdType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_advertising")
public class Advertising {

    @SuppressWarnings("unused")
	private final static DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title")
    @NotNull(message = "Advertising title can not be null.")
    @NotEmpty(message = "Advertising title can not be empty.")
    private String title;

    @Column(name = "publication_date")
    @NotNull(message = "Advertising creation date can not be null.")
    private Date publicationDate;

	@Column(name = "expiration_date")
	@NotNull(message = "Advertising creation date can not be null.")
	private Date expirationDate;

    @Column(name = "price")
    @NotNull(message = "Advertising price can not be null.")
    @Min(0)
    private double price;

    @Column(name = "classification")
    @NotNull(message = "Advertising classification can not be null.")
    @Min(0) @Max(5)
    private double classification;
    
    @Column(name = "type")
    @NotNull(message = "Advertising type can not be null.")
	@Enumerated(EnumType.STRING)
    private AdType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Advertising user can not be null.")
    private User user;

    @Column
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advertising", orphanRemoval = true)
    private Set<Question> questions;

    public Advertising(String title, Date publicationDate, Date expirationDate, double price, double classification, AdType type, User user) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.classification = classification;
        this.type = type;
        this.user = user;
        this.questions = new HashSet<>();
    }

    public Advertising() {}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public double getPrice() {
		return price;
	}

	public double getClassification() {
		return classification;
	}

	public AdType getType() { return type; }

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setClassification(int classification) {
		this.classification = classification;
	}

	public void setType(AdType type) {
		this.type = type;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        if(questions == null){
            this.questions = new HashSet<>();
        }
        this.questions = questions;
    }

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
		Advertising other = (Advertising) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Advertising [id=" + id + ", title=" + title + ", creation date=" + publicationDate +
                ", expiration date="+ expirationDate + ", price=" + price +
                ", classification=" + classification + ", type=" + type + "]";
	}

	
}
