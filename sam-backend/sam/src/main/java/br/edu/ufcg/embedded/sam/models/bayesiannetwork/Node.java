package br.edu.ufcg.embedded.sam.models.bayesiannetwork;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Node")
@Table(name = "tb_node")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer node_id;

    @Column(name = "variance")
    private Double variance;

    @Column(name = "node_source")
    private String nodeSource;

    @ElementCollection
    @CollectionTable(name = "tb_node_states")
    @Column(name = "state")
    private List<Integer> states;

    private Integer evidence;
    private String name;
    private String nameAndEvidence;

    @ElementCollection
    @CollectionTable(name = "tb_node_states_name")
    private List<String> statesName;


    @ElementCollection
    @CollectionTable(name = "tb_node_npt")
    private List<Double> npt;


    private String function;
    private Integer min;
    private Integer max;
    private String id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ParentNode> parents;

    public Node() {
    }

    public Integer getNode_id() {
        return node_id;
    }

    public void setNode_id(Integer node_id) {
        this.node_id = node_id;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }

    public List<Integer> getStates() {
        if (states == null) {
            states = new ArrayList<>();
        }
        return states;
    }

    public void setStates(List<Integer> states) {
        this.states = states;
    }

    public Integer getEvidence() {
        return evidence;
    }

    public void setEvidence(Integer evidence) {
        this.evidence = evidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAndEvidence() {
        return nameAndEvidence;
    }

    public void setNameAndEvidence(String nameAndEvidence) {
        this.nameAndEvidence = nameAndEvidence;
    }

    public List<String> getStatesName() {
        if (statesName == null) {
            statesName = new ArrayList<>();
        }
        return statesName;
    }

    public void setStatesName(List<String> statesName) {
        this.statesName = statesName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ParentNode> getParents() {
        if (parents == null) {
            parents = new ArrayList<>();
        }
        return parents;
    }

    public boolean addParent(ParentNode parent) {
        return getParents().add(parent);
    }

    public void setParents(List<ParentNode> parents) {
        this.parents = parents;
    }

    public List<Double> getNpt() {
        if (npt == null) {
            npt = new ArrayList<>();
        }
        return npt;
    }

    public String getNodeSource() {
        return nodeSource;
    }

    public void setNodeSource(String nodeSource) {
        this.nodeSource = nodeSource;
    }

    public void setNpt(List<Double> npt) {
        this.npt = npt;
    }
}
