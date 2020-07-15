package br.edu.ufcg.embedded.sam.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Question")
@Table(name = "tb_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column
    private String question;

    @ManyToMany
    private List<Metric> metrics;

    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question(String question, List<Metric> metrics) {
        this.question = question;
        this.metrics = metrics;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Metric> getMetrics() {
        if(metrics == null){
            metrics = new ArrayList<>();
        }
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(question, question1.question) &&
                Objects.equals(metrics, question1.metrics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, metrics);
    }
}