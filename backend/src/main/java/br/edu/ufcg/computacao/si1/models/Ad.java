package br.edu.ufcg.computacao.si1.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_anuncio")
public class Ad {
    private final static DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title")
    @NotNull(message = "Ad title can not be null.")
    @NotEmpty(message = "Ad title can not be empty.")
    private String title;

    @Column(name = "creation_date")
    @NotNull(message = "Ad creation date can not be null.")
    private Date creationDate;

    @Column(name = "price")
    @NotNull(message = "Ad price can not be null.")
    @Min(0)
    private double price;

    @Column(name = "classification")
    @NotNull(message = "Ad classification can not be null.")
    @Min(0) @Max(5)
    private int classification;
    
    @Column(name = "type")
    @NotNull(message = "Ad type can not be null.")
    private String type;

    public Ad(String title, Date creationDate, double price, int classification, String type) {
        this.title = title;
        this.creationDate = creationDate;
        this.price = price;
        this.classification = classification;
        this.type = type;
    }

    public Ad() {};

	public Long getId() {
		return id;
	}



	public String getTitle() {
		return title;
	}



	public Date getCreationDate() {
		return creationDate;
	}



	public double getPrice() {
		return price;
	}



	public int getClassification() {
		return classification;
	}



	public String getType() {
		return type;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public void setClassification(int classification) {
		this.classification = classification;
	}



	public void setType(String type) {
		this.type = type;
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
		Ad other = (Ad) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Ad [id=" + id + ", title=" + title + ", creation date=" + creationDate + ", price=" + price
				+ ", classification=" + classification + ", type=" + type + "]";
	}

	
}
