package br.edu.ufcg.embedded.sam.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Metric")
@Table(name = "tb_metric")
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    /**
     * Description of this quality focus.
     */
    @NotBlank(message = "{qualityFocus.description.notempty}")
    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<VariationFactor> variationFactors;

    @Column(name = "baseline_hypothesis")
    private String baselineHypothesis;

    public Metric() {
    }

    public Metric(String description, String baselineHypothesis) {
        this.description = description;
        this.baselineHypothesis = baselineHypothesis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void addVariationFactor(VariationFactor variationFactor) {
        if (variationFactor == null) {
            throw new UnsupportedOperationException("The variation factor cannot be null.");
        }
        if (variationFactors == null) {
            this.variationFactors = new ArrayList<>();
        }
        variationFactors.add(variationFactor);
    }

    public boolean removeVariationFactor(VariationFactor variationFactor) {
        if (variationFactor == null) {
            return false;
        }
        return variationFactors.remove(variationFactor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metric that = (Metric) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description);
    }

    public List<VariationFactor> getVariationFactors() {
        return variationFactors;
    }

    public void setVariationFactors(List<VariationFactor> variationFactors) {
        this.variationFactors = variationFactors;
    }

    public String getBaselineHypothesis() {
        return baselineHypothesis;
    }

    public void setBaselineHypothesis(String baselineHypothesis) {
        this.baselineHypothesis = baselineHypothesis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
