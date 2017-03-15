package br.edu.ufcg.computacao.si1.models;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "publisher_name")
    @NotEmpty(message = "Rating's publisher name can not be empty")
    @NotNull(message = "Rating's publisher name can not be null")
    private String publisherName;

    @Column(name = "comment")
    @NotEmpty(message = "Rating's comment name can not be empty")
    @NotNull(message = "Rating's comment name can not be null")
    private String comment;

    @Column(name = "grade")
    @NotNull(message = "Rating's value can not be null")
    @Min(0) @Max(5)
    private double grade;

    @Column(name = "publication_date")
    @NotNull(message = "Rating's comment name can not be null")
    private Date publicationDate;

    public Rating(String publisherName, String comment, double grade, Date publicationDate) {
        this.publisherName = publisherName;
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
}
