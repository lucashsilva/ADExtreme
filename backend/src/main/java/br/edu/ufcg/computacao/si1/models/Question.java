package br.edu.ufcg.computacao.si1.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tb_question")
public class Question {

    private final String NO_ANSWER = "";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "body")
    @NotNull(message = "Question body can not be null.")
    @NotEmpty(message = "Question body can not be empty.")
    private String body;

    @Column(name ="answer")
    @NotNull(message = "Question answer can not be null.")
    private String answer;

    @Column(name = "creation_date")
    @NotNull(message = "Question creation date can not be null.")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "advertising_id")
    @NotNull(message = "Question advertising can not be null.")
    private Advertising advertising;


    public Question(String body, Advertising advertising) {
        this.body = body;
        this.answer = NO_ANSWER;
        this.creationDate = new Date();
        this.advertising = advertising;
    }

    public Question(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Advertising getAdvertising() {
        return advertising;
    }

    public void setAdvertising(Advertising advertising) {
        this.advertising = advertising;
    }
}
