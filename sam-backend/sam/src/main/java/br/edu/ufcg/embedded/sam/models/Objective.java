package br.edu.ufcg.embedded.sam.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class responsible for representing an Objective in the system.
 */

@Entity(name = "Objective")
@Table(name = "tb_objective")
public class Objective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    /**
     * Object of study of this objective.
     */
    @Column(name = "object_of_study")
    private String objectOfStudy;

    /**
     * Purpose of this objective.
     */
    @Column(name = "purpose")
    private String purpose;

    /**
     * View point to which this objective is related.
     */
    @NotBlank(message = "{objective.viewPoint.notempty}")
    @Column(name = "view_point")
    private String viewPoint;

    @NotBlank(message = "{objective.qualityFocus.notempty}")
    @Column(name = "quality_focus")
    private String qualityFocus;

    @ManyToMany
    private List<Question> questions;

    public Objective() {
    }

    public Objective(String objectsOfStudy, String purpose, String viewPoint, String qualityFocus, List<Question> questions) {
        this.objectOfStudy = objectsOfStudy;
        this.purpose = purpose;
        this.viewPoint = viewPoint;
        this.qualityFocus = qualityFocus;
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectOfStudy() {
        return objectOfStudy;
    }

    public void setObjectOfStudy(String objectOfStudy) {
        this.objectOfStudy = objectOfStudy;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getViewPoint() {
        return viewPoint;
    }

    public void setViewPoint(String viewPoint) {
        this.viewPoint = viewPoint;
    }

    public String getQualityFocus() {
        return qualityFocus;
    }

    public void setQualityFocus(String qualityFocus) {
        this.qualityFocus = qualityFocus;
    }

    public List<Question> getQuestions() {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return Objects.equals(id, objective.id) &&
                Objects.equals(objectOfStudy, objective.objectOfStudy) &&
                Objects.equals(purpose, objective.purpose) &&
                Objects.equals(viewPoint, objective.viewPoint) &&
                Objects.equals(qualityFocus, objective.qualityFocus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectOfStudy, purpose, viewPoint, qualityFocus);
    }
}
