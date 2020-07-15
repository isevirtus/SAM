package br.edu.ufcg.embedded.sam.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class responsible for representing variation factors on the system.
 */
//Metrica
@Entity(name = "VariationFactor")
@Table(name = "tb_variation_factor")
public class VariationFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Description of this quality focus.
     */
    @NotBlank(message = "{qualityFocus.description.notempty}")
    @Column(name = "description")
    private String description;

    @ManyToOne
    private Metric variationMetric;

    /**
     * Variation hypothesis for this variation factor.
     */
    @NotBlank(message = "{variationFactor.variationHypothesis.notempty}")
    @Column(name = "variation_hypothesis")
    private String variationHypothesis;

    public VariationFactor() {
    }

    public VariationFactor(String description, String variationHypothesis) {
        this.description = description;
        this.variationHypothesis = variationHypothesis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariationHypothesis() {
        return variationHypothesis;
    }

    public void setVariationHypothesis(String variationHypothesis) {
        this.variationHypothesis = variationHypothesis;
    }

    public Metric getVariationMetric() {
        return variationMetric;
    }

    public void setVariationMetric(Metric variationMetric) {
        this.variationMetric = variationMetric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariationFactor that = (VariationFactor) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(variationHypothesis, that.variationHypothesis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, variationHypothesis);
    }
}
