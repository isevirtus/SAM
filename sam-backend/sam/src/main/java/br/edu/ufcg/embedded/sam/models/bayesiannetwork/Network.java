package br.edu.ufcg.embedded.sam.models.bayesiannetwork;

import br.edu.ufcg.embedded.sam.models.Objective;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Network")
@Table(name = "tb_network")
public class Network {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    @JsonIgnore
    @OneToOne
    private Objective objective;

    @ElementCollection
    @CollectionTable(name = "tb_network_node")
    private List<Node> nodes;

    public Network() {
        nodes = new ArrayList<>();
    }

    public boolean addNode(Node node){
        return nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
