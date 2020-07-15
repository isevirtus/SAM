package br.edu.ufcg.embedded.sam.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity(name = "Project")
@Table(name = "tb_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the person creating the project.
     */
    @NotBlank(message = "{project.name.notempty}")
    @Column(name = "name")
    private String name;

    /**
     * Function of the person creating the project.
     */
    @NotBlank(message = "{project.function.notempty}")
    @Column(name = "function")
    private String function;

    /**
     * Experience time of the person creating the project, in months.
     */
    @DecimalMin(value = "0", message = "{project.experienceTime.nonnegative}")
    @Column(name = "experience_time")
    private Integer experienceTime;

    /**
     * Duration of the project in months.
     */
    @DecimalMin(value = "0", message = "{project.duration.nonnegative}")
    @Column(name = "duration")
    private Integer duration;

    /**
     * Describes the quantity of people of each role in the project.
     */
    @ElementCollection
    @CollectionTable(name = "tb_roles_quantity_project")
    private Map<Role, Integer> amountOfPeople;

    @ElementCollection
    @CollectionTable(name = "tb_methodologies_project")
    private List<String> methodologies;

    /**
     * Type of this project. It may be prototype or product.
     */
    @NotBlank(message = "{project.type.notempty}")
    @Column(name = "project_type")
    private String projectType;

    /**
     * {@link Objective}s related to this project.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Objective> objectives;

    public Project() {
    }

    public Project(String name, String function, Integer experienceTime, Integer duration, Map<Role, Integer> amountOfPeople, List<String> methodologies, String projectType, List<Objective> objectives) {
        this.name = name;
        this.function = function;
        this.experienceTime = experienceTime;
        this.duration = duration;
        this.amountOfPeople = amountOfPeople;
        this.methodologies = methodologies;
        this.projectType = projectType;
        this.objectives = objectives;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Integer getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(Integer experienceTime) {
        this.experienceTime = experienceTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Map<Role, Integer> getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(Map<Role, Integer> amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public List<String> getMethodologies() {
        if (methodologies == null) {
            methodologies = new ArrayList<>();
        }
        return methodologies;
    }

    public void setMethodologies(List<String> methodologies) {
        this.methodologies = methodologies;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    /**
     * Add an {@link Objective} to this project.
     *
     * @param abstractionSheet {@link Objective} to be added to this project.
     */
    public void addObjective(Objective abstractionSheet) {
        if (this.objectives == null) {
            this.objectives = new ArrayList<>();
        }
        this.objectives.add(abstractionSheet);
    }

    /**
     * Removes the {@code abstractionSheet} of this project.
     *
     * @param objective {@link Objective} to be removed.
     */
    public void removeObjective(Objective objective) {
        if (this.objectives != null) {
            this.objectives.remove(objective);
        }
    }
}
