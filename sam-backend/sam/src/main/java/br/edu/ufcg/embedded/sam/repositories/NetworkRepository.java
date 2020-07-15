package br.edu.ufcg.embedded.sam.repositories;

import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Network;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkRepository extends JpaRepository<Network, Integer> {
    Network findByObjectiveId(Integer idObjective);
}
