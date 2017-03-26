package br.edu.ufcg.computacao.si1.models.rating;


import br.edu.ufcg.computacao.si1.services.JsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Table(name = "tb_ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "publisher_name")
    @NotEmpty(message = "Rating's publisherName can not be empty")
    @NotNull(message = "Rating's publisherName can not be null")
    private String publisherName;

    @Column(name = "rated_entity_id")
    @NotNull(message = "Rating's ratedEntityId can not be null")
    private Long ratedEntityId;

    @Column(name = "comment")
    @NotEmpty(message = "Rating's comment can not be empty")
    @NotNull(message = "Rating's comment can not be null")
    private String comment;

    @Column(name = "grade")
    @NotNull(message = "Rating's grade can not be null")
    @Min(0) @Max(5)
    private double grade;

    @Column(name = "publication_date")
    @NotNull(message = "Rating's publicationDate can not be null")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date publicationDate;

    public Rating(String publisherName, Long ratedEntityId, String comment, double grade, Date publicationDate) {
        this.publisherName = publisherName;
        this.ratedEntityId = ratedEntityId;
        this.comment = comment;
        this.grade = grade;
        this.publicationDate = publicationDate;
    }

    public Rating() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        if(publicationDate == null){
            this.publicationDate = new Date();
        }else{
            this.publicationDate = publicationDate;
        }
    }

    public Long getRatedEntityId() {
        return ratedEntityId;
    }

    public void setRatedEntityId(Long ratedEntityId) {
        this.ratedEntityId = ratedEntityId;
    }
}
