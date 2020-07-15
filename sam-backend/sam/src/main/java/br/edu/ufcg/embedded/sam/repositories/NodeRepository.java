package br.edu.ufcg.embedded.sam.repositories;

import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Integer> {
}
